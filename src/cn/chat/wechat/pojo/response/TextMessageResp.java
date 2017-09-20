package cn.chat.wechat.pojo.response;
/**
 * 文本响应消息
 * @author 默然丶丿浅笑
 *
 */
public class TextMessageResp extends BaseMessageAttribute{
	private String Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
