package cn.chat.lifemanage.main;

import java.util.ArrayList;
import java.util.List;

import cn.chat.wechat.pojo.menu.Button;
import cn.chat.wechat.pojo.menu.CommonButton;
import cn.chat.wechat.pojo.menu.ComplexButton;
import cn.chat.wechat.pojo.menu.Menu;
import cn.chat.wechat.util.AccessToken;
import cn.chat.wechat.util.MessageUtil;
import cn.chat.wechat.util.WeChatUtils;
import net.sf.json.JSONObject;

public class MenuManage {
	public static void main(String[] args) {
		//获取Accesstoken
		String AppID = "wx02611549a85e2673";
		String AppSecret = "5580e635dfaca3e865514f027aabbf81";
		AccessToken at = WeChatUtils.getAccessToken(AppID, AppSecret);
		String access_token=at.getAccess_token();
		
		//准备菜单数据
		Menu menu = new Menu();
		List<Button> button = new ArrayList<Button>();
		
		CommonButton cb1 = new CommonButton(); 
		cb1.setName("主页");
		cb1.setType(MessageUtil.EVENT_TYPE_VIEW);
		cb1.setUrl("http://www.baidu.com");
		
		ComplexButton cxb = new ComplexButton();
		cxb.setName("借阅之家");
		List<CommonButton> sub_button = new ArrayList<CommonButton>();
		
		CommonButton sub_button1 = new CommonButton(); 
		sub_button1.setName("用户注册");
		sub_button1.setType(MessageUtil.EVENT_TYPE_VIEW);
		sub_button1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx02611549a85e2673&redirect_uri=http%3a%2f%2fmywechatmanage.duapp.com%2fuser%2fcode&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		CommonButton sub_button2 = new CommonButton(); 
		sub_button2.setName("图书导航");
		sub_button2.setType(MessageUtil.EVENT_TYPE_VIEW);
		sub_button2.setUrl("http://www.baidu.com");
		
		CommonButton sub_button3 = new CommonButton(); 
		sub_button3.setName("在线预定");
		sub_button3.setType(MessageUtil.EVENT_TYPE_VIEW);
		sub_button3.setUrl("http://www.baidu.com");
		
		CommonButton sub_button4 = new CommonButton(); 
		sub_button4.setName("图书收藏");
		sub_button4.setType(MessageUtil.EVENT_TYPE_VIEW);
		sub_button4.setUrl("http://www.baidu.com");
		
		CommonButton sub_button5 = new CommonButton(); 
		sub_button5.setName("还书提醒");
		sub_button5.setType(MessageUtil.EVENT_TYPE_VIEW);
		sub_button5.setUrl("http://www.baidu.com");
		
		sub_button.add(sub_button1);
		sub_button.add(sub_button2);
		sub_button.add(sub_button3);
		sub_button.add(sub_button4);
		sub_button.add(sub_button5);
		cxb.setSub_button(sub_button);
		
		CommonButton cb2 = new CommonButton(); 
		cb2.setName("社区交流");
		cb2.setType(MessageUtil.EVENT_TYPE_CLICK);
		cb2.setKey(MessageUtil.KEY_COMMUNITY_BTN);
		
		button.add(cb1);
		button.add(cxb);
		button.add(cb2);
		
		menu.setButton(button);
		
		String menuJsonStr = JSONObject.fromObject(menu).toString();
		Integer createMenu = WeChatUtils.createMenu(access_token, menuJsonStr);
		
		if(createMenu==0){
			System.out.println("创建菜单成功");
		}else{
			System.out.println("创建菜单失败");
		}
	}
}


