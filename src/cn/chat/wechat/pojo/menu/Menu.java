package cn.chat.wechat.pojo.menu;

import java.util.List;

public class Menu {
	private List<Button> button;//一级菜单数组，个数应为1~3个

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}
	
	
	
}
