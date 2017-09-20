package cn.chat.wechat.pojo.response;
/**
 * 公共响应消息属性
 * @author 默然丶丿浅笑
 *
 */
public class BaseMessageAttribute {
	
	private String ToUserName;//发送方帐号（一个OpenID）
	private String FromUserName;//开发者微信号
	private long CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	
	
}
