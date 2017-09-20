package cn.chat.wechat.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.chat.wechat.pojo.response.Article;
import cn.chat.wechat.pojo.response.Image;
import cn.chat.wechat.pojo.response.ImageMessageResp;
import cn.chat.wechat.pojo.response.NewsMessageResp;
import cn.chat.wechat.pojo.response.TextMessageResp;
import cn.chat.wechat.pojo.response.Voice;
import cn.chat.wechat.pojo.response.VoiceMessageResp;
import cn.chat.wechat.thread.AccessTokenThread;
import cn.chat.wechat.util.MessageUtil;
import cn.chat.wechat.util.ReqMessageUtil;
import cn.chat.wechat.util.WeChatUtils;
import net.sf.json.JSONObject;

public class ProcessRespMessage {
	
	public static String processRespMessage(String requestType){
		
		//返回给微信服务器的需要解析成xml的字符串
		String responseString = null;
		
		
		TextMessageResp text=null;
		
		switch (requestType) {
		case MessageUtil.RESP_MESSAGE_TYPE_TEXT:
			text = new TextMessageResp();
			text.setToUserName(ReqMessageUtil.REQ_MESSAGE_FromUserName);
			text.setFromUserName(ReqMessageUtil.REQ_MESSAGE_ToUserName);
			text.setCreateTime(new Date().getTime());
			text.setMsgType(ReqMessageUtil.REQ_MESSAGE_MsgType);
			text.setContent(ProcessReqMessage.responseContent);
			//解析成xml
			responseString = MessageUtil.textMessageToXml(text);
			break;
		case MessageUtil.RESP_MESSAGE_TYPE_IMAGE:
			ImageMessageResp imageMessage = new ImageMessageResp();
			imageMessage.setToUserName(ReqMessageUtil.REQ_MESSAGE_FromUserName);
			imageMessage.setFromUserName(ReqMessageUtil.REQ_MESSAGE_ToUserName);
			imageMessage.setCreateTime(new Date().getTime());
			imageMessage.setMsgType(ReqMessageUtil.REQ_MESSAGE_MsgType);
			Image image = new Image();
			image.setMediaId(ReqMessageUtil.REQ_MESSAGE_IMAGE_MediaId);
			imageMessage.setImage(image);
			//解析成xml
			responseString = MessageUtil.imageMessageToXml(imageMessage);
			
			break;
		case MessageUtil.RESP_MESSAGE_TYPE_VOICE:
			VoiceMessageResp voiceMessage = new VoiceMessageResp();
			voiceMessage.setToUserName(ReqMessageUtil.REQ_MESSAGE_FromUserName);
			voiceMessage.setFromUserName(ReqMessageUtil.REQ_MESSAGE_ToUserName);
			voiceMessage.setCreateTime(new Date().getTime());
			voiceMessage.setMsgType(ReqMessageUtil.REQ_MESSAGE_MsgType);
			Voice voice = new Voice();
			voice.setMediaId(ReqMessageUtil.REQ_MESSAGE_VOICE_MediaId);
			voiceMessage.setVoice(voice);
			//解析成xml
			responseString = MessageUtil.voiceMessageToXml(voiceMessage);
			
			break;
		case MessageUtil.RESP_MESSAGE_TYPE_NEWS:
			NewsMessageResp newsMessage = new NewsMessageResp();
			newsMessage.setToUserName(ReqMessageUtil.REQ_MESSAGE_FromUserName);
			newsMessage.setFromUserName(ReqMessageUtil.REQ_MESSAGE_ToUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(ReqMessageUtil.REQ_MESSAGE_MsgType);
			newsMessage.setArticleCount(0);
			Article article = new Article();
			article.setDescription(ReqMessageUtil.REQ_MESSAGE_NEWS_Description);
			article.setPicUrl(ReqMessageUtil.REQ_MESSAGE_NEWS_PicUrl);
			article.setTitle(ReqMessageUtil.REQ_MESSAGE_NEWS_Title);
			article.setUrl(ReqMessageUtil.REQ_MESSAGE_NEWS_Url);
			List<Article> alist = new ArrayList<>();
			newsMessage.setArticle(alist);
			//解析成xml
			responseString = MessageUtil.newsMessageToXml(newsMessage);
			System.out.println(responseString);
			break;
		case MessageUtil.EVENT_TYPE_SUBSCRIBE:
			
			
			break;
		

		default:
			break;
		}
		
		return responseString;
	}
}
