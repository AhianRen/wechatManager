package cn.chat.wechat.Service;

import java.util.Map;

import cn.chat.lifemanage.po.User;
import cn.chat.wechat.thread.AccessTokenThread;
import cn.chat.wechat.util.MessageUtil;
import cn.chat.wechat.util.ReqMessageUtil;
import cn.chat.wechat.util.WeChatUtils;
import net.sf.json.JSONObject;

public class ProcessReqMessage {

	/**
	 * 默认的回复文本内容
	 */
	public static  String responseContent = "欢迎怪蛋来参观";
	
	public static String processReqmessage(Map<String,String> map){
		String requestType = null;
		//通过Map得到发来的消息数据
		ReqMessageUtil.REQ_MESSAGE_ToUserName = map.get("ToUserName");
		ReqMessageUtil.REQ_MESSAGE_FromUserName = map.get("FromUserName");
		ReqMessageUtil.REQ_MESSAGE_CreateTime = map.get("CreateTime");
		ReqMessageUtil.REQ_MESSAGE_MsgType = map.get("MsgType");
		ReqMessageUtil.REQ_MESSAGE_TEXT_Content = map.get("Content");
		ReqMessageUtil.REQ_MESSAGE_IMAGE_PicUrl = map.get("PicUrl");
		ReqMessageUtil.REQ_MESSAGE_IMAGE_MediaId = map.get("MediaId");
		ReqMessageUtil.REQ_MESSAGE_VOICE_MediaId = map.get("MediaId");
		
		switch (ReqMessageUtil.REQ_MESSAGE_MsgType) {
		case MessageUtil.REQ_MESSAGE_TYPE_TEXT:
			//接收到的是文本消息
			
			//textMessage.setContent("你发送的消息是："+ Content);
			if(		ReqMessageUtil.REQ_MESSAGE_TEXT_Content.equals("0")){
				//返回图文消息：关于我们
				requestType = MessageUtil.RESP_MESSAGE_TYPE_NEWS;
			}else{
				//返回文本消息
				requestType = MessageUtil.RESP_MESSAGE_TYPE_TEXT;
				responseContent = "主公,发送的消息："+ReqMessageUtil.REQ_MESSAGE_TEXT_Content+"\n"+"宝宝知道了,么么哒";
			}
			
			
			break;
		case MessageUtil.REQ_MESSAGE_TYPE_IMAGE://图片消息
			requestType = MessageUtil.RESP_MESSAGE_TYPE_IMAGE;
			break;
		case MessageUtil.REQ_MESSAGE_TYPE_VOICE://图文消息
			requestType = MessageUtil.RESP_MESSAGE_TYPE_VOICE;
			break;
		case MessageUtil.REQ_MESSAGE_TYPE_EVENT://关注事件
			ReqMessageUtil.REQ_MESSAGE_EVENT = map.get("Event");
			if(ReqMessageUtil.REQ_MESSAGE_EVENT.toString().equals(MessageUtil.EVENT_TYPE_SUBSCRIBE.toString())){
				//
				requestType = MessageUtil.RESP_MESSAGE_TYPE_TEXT;
				//获取用户基本信息
				System.out.println("AccessTokenThread.at.getAccess_token()"+AccessTokenThread.at.getAccess_token());
				User user = WeChatUtils.getUserInfo(AccessTokenThread.at.getAccess_token(), ReqMessageUtil.REQ_MESSAGE_FromUserName);
				 
				//关注消息
				responseContent=user.getNickname()+"欢迎关注！";
				
				
			}else if(ReqMessageUtil.REQ_MESSAGE_EVENT.equals(MessageUtil.EVENT_TYPE_CLICK)){ //处理菜单点击事件
				String EventKey = map.get("EventKey");
				if(EventKey.equals(MessageUtil.KEY_MUSIC_BTN)){//点击“音乐搜索”
					requestType = MessageUtil.RESP_MESSAGE_TYPE_TEXT;
					responseContent = " ";
				}
				else if(EventKey.equals(MessageUtil.KEY_FACE_BTN)){//点击“人脸检测”
					//TODO 				
				}
				else if(EventKey.equals(MessageUtil.KEY_ABOUT_BTN)){//点击“关于我们”
					requestType = MessageUtil.RESP_MESSAGE_TYPE_NEWS;
				}else if(EventKey.equals(MessageUtil.EVENT_TYPE_VIEW)){
					requestType = MessageUtil.RESP_MESSAGE_TYPE_TEXT;
				}
			}
			
			break;
			
		default:
			break;
		}
		return requestType;//返回消息类型
	}
	
}
