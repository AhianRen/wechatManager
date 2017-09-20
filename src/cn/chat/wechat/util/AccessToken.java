package cn.chat.wechat.util;
/**
 * 全局接口调用凭证
 * @author haibo
 *
 */
public class AccessToken {
	private String access_token;//获取到的凭证 
	private int expires_in;//凭证有效时间，单位：秒 
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	
}
