package cn.chat.wechat.pojo.menu;
/**
 * 定义一个按钮基类
 * @author 默然丶丿浅笑
 *
 */
public class Button {
	private String name;//菜单标题，不超过16个字节，子菜单不超过60个字节

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
