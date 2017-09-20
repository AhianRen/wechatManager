package cn.chat.wechat.controller;

import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.junit.Test;

import cn.chat.wechat.thread.AccessTokenThread;
import cn.chat.wechat.util.WeChatUtils;
/**
 * 
 * @author 默然丶丿浅笑
 *
 */
@WebServlet(loadOnStartup=0,urlPatterns="/init")//项目启动时就初始化)
public class InitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		
		//获得配置信息得到appId和appSecret
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		WeChatUtils.appID  = bundle.getString("appID");
		WeChatUtils.appSecret = bundle.getString("appSecret");
	
		//开启AccessTokenThread线程，获取access_token
		AccessTokenThread att = new AccessTokenThread();
		Thread at = new Thread(att);
		at.start();
		super.init();
	}
	
}
