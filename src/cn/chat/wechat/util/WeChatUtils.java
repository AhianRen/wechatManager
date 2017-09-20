package cn.chat.wechat.util;


import cn.chat.lifemanage.po.User;
import net.sf.json.JSONObject;

/**
 * 微信接口工具类
 * @author haibo
 *
 */
public class WeChatUtils {
	
	public static String appID;
	public static String appSecret;
	
	/**
	 * 获取全局调用凭证url
	 */
	private static final  String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
			+ "appid=APPID&secret=APPSECRET";
	/**
	 * 获取全局调用凭证Access_Token
	 * @param AppID
	 * @param AppSecret
	 * @return
	 */
	public static AccessToken getAccessToken(String AppID,String AppSecret){
		
		String url = access_token_url.replace("APPID", AppID).replace("APPSECRET", AppSecret);
		JSONObject jsonObject = HttpUtils.httpsRequest(url, "GET", null);
		AccessToken accessToken = new AccessToken();
		
		if(jsonObject!=null){
			accessToken.setAccess_token(jsonObject.getString("access_token"));
			accessToken.setExpires_in(jsonObject.getInt("expires_in"));
		}
		return accessToken;
	
	}
	/**
	 * 创建菜单接口的地址
	 */
	private  static final String create_menu_url =" https://api.weixin.qq.com/cgi-bin"
				+ "/menu/create?access_token=ACCESS_TOKEN";
	/**
	 * 
	 * @param access_token 接口调用凭证
	 * @param JsonMenuStr 获得菜单JSON数据
	 * @return errcode 0表示创建成功
	 */

	public static Integer createMenu(String access_token,String menuJsonStr){
			
		String url = create_menu_url.replace("ACCESS_TOKEN",access_token);
		JSONObject jsonObject = HttpUtils.httpsRequest(url, "POST", menuJsonStr);
		
		Integer errcode = null;
		if(jsonObject!=null){
			errcode = jsonObject.getInt("errcode");
		}
			return errcode;
	}
	/**
	 * 得到用户信息的URL
	 */
	private  static final String USER_INFO_url ="https://api.weixin.qq.com/cgi-bin/user/info?"
			+ "access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static User getUserInfo(String access_token,String openid){
		
		String url = USER_INFO_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		JSONObject jsonObject = HttpUtils.httpRequest(url,"GET", null);
		System.out.println("jsonObject"+jsonObject);
		User user = null;
		if(jsonObject!=null){
			user = new User();
			user.setNickname(jsonObject.getString("nickname"));
			user.setOpenid(jsonObject.getString("openid"));
			user.setHeadimgurl(jsonObject.getString("headimgurl"));
		}
		
		return user;
		
	}
	/**
	 * 获取网页授权AccessToken的URL
	 */
	private static final String oauth2_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)的URL
	 */
	private static final String oauth2_user_info_url = "https://api.weixin.qq.com/sns/userinfo?"
			+ "access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 获取网页授权AccessToken
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static User getOAuth2UserInfo(String code){
		//第二步：通过code换取网页授权access_token
		String requestUrl = oauth2_access_token_url.replace("APPID", WeChatUtils.appID).replace("SECRET", WeChatUtils.appSecret).replace("CODE", code);
		JSONObject jsonObject = HttpUtils.httpsRequest(requestUrl, "GET", null);
		User user = null;
		String openid;
		String access_token;
		if(jsonObject!=null){
			//第四步：拉取用户信息(需scope为 snsapi_userinfo)
			access_token = jsonObject.getString("access_token");
			openid = jsonObject.getString("openid");
			String userinfoUrl = oauth2_user_info_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
			JSONObject jsonObjectUserInfo = HttpUtils.httpsRequest(userinfoUrl, "GET", null);
			if(jsonObjectUserInfo!=null){
				user = new User();
				user.setOpenid(openid);
				user.setNickname(jsonObjectUserInfo.getString("nickname"));
				user.setHeadimgurl(jsonObjectUserInfo.getString("headimgurl"));
			}
		}
		
		return user;
	}
	

}



















