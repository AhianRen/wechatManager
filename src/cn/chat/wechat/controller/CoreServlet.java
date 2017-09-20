package cn.chat.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chat.wechat.Service.CoreService;
import cn.chat.wechat.util.SignUtil;

/**
 * 处理微信请求的核心控制类
 * @author haibo
 *
 */
@WebServlet("/core")
public class CoreServlet extends HttpServlet{
	/**
	 * 验证服务器的有效性
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//微信加密签名
		String signature = req.getParameter("signature");
		//时间戳
		String timestamp = req.getParameter("timestamp");
		//随机数
		String nonce = req.getParameter("nonce");
		//随机字符串
		String echostr = req.getParameter("echostr");
		//对timestamp、nonce、echostr进行加密，与signature进行校验，如果校验成功，则原样返回echostr，表示接入成功；否则接入失败。
		boolean result = SignUtil.checkSignature(signature, timestamp, nonce);
		PrintWriter out = resp.getWriter();
		//校验成功，原样返回echostr
		if(result){
			out.write(echostr);
		}
		out.close();
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求和响应的编码
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		//接受消息，处理消息
		String responseString = "";
		try {
			responseString = CoreService.processRequest(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//响应信息
		PrintWriter out = resp.getWriter();
		out.write(responseString);
		out.close();
	}
	
}