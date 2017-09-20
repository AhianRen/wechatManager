package cn.chat.lifemanage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chat.lifemanage.po.User;
import cn.chat.wechat.util.WeChatUtils;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns={"/user/code","/user/getuserinfo"})
public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getRequestURI();
		//System.out.println(url);
		String path = req.getContextPath();
		//System.out.println(path);
		if(url.equals(path+"/user/code")){
			String code = req.getParameter("code");
			User user = WeChatUtils.getOAuth2UserInfo(code);
			req.getSession().setAttribute("LOGIN_USER", user);
			resp.sendRedirect("/html/register.html");//请求重定向
		}
		else if(url.equals(path+"/user/getuserinfo")){
			User user = (User) req.getSession().getAttribute("LOGIN_USER");
			String result = JSONObject.fromObject(user).toString();
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(result);
		}
		
	}
	
}
