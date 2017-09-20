package cn.chat.wechat.pojo.request;
/**
 * 图片消息
 * @author 默然丶丿浅笑
 *
 */

public class ImageMessageReq extends BaseMessageAttribute{
	
	private String PicUrl;//图片链接（由系统生成）
	private String MediaId;//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private long MsgId;//消息id，64位整型
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	
	
}
