package cn.chat.wechat.pojo.request;
/**
 * 文本消息
 * @author 默然丶丿浅笑
 *
 */

public class TextMessageReq extends BaseMessageAttribute{
	
	private String Content;//文本消息内容
//	private long MsgId;// 消息id，64位整型
	
//	public long getMsgId() {
//		return MsgId;
//	}
//	public void setMsgId(long msgId) {
//		MsgId = msgId;
//	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}	
