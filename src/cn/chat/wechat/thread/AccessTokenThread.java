package cn.chat.wechat.thread;

import cn.chat.wechat.util.AccessToken;
import cn.chat.wechat.util.WeChatUtils;

/**
 * 通过线程定时刷新access_token的值
 * @author 默然丶丿浅笑
 *
 */
public class AccessTokenThread implements Runnable{
	
	public static AccessToken at = null;
	boolean flag = true;
	
	@Override
	public void run() {
		while(flag){
			at = WeChatUtils.getAccessToken(WeChatUtils.appID,WeChatUtils.appSecret);
			if(at!=null){
				try {
					Thread.sleep(7000*1000);//睡眠7000秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
