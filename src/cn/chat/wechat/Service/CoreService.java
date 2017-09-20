package cn.chat.wechat.Service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.chat.wechat.pojo.request.TextMessageReq;
import cn.chat.wechat.pojo.response.TextMessageResp;
import cn.chat.wechat.util.MessageUtil;

/**
 * 核心业务类
 * @author 默然丶丿浅笑
 *
 */
public class CoreService {
	/**
	 * 处理微信发来的消息
	 * @param req 
	 * @return
	 * @throws Exception 
	 */
	public static String processRequest(HttpServletRequest req) throws Exception{
		req.setCharacterEncoding("utf-8");

		//将得到的xml解析成map集合
		Map<String,String> map = MessageUtil.parseXml(req);
		
		//返回响应消息类型
		String reqType = ProcessReqMessage.processReqmessage(map);
		System.out.println("reqType"+reqType);
		
		//返回解析后的字符串
		String responseString = ProcessRespMessage.processRespMessage(reqType);
		
		return responseString;
	
		
	}
}
