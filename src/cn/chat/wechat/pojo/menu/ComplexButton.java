package cn.chat.wechat.pojo.menu;

import java.util.List;

public class ComplexButton extends Button{
	
	private List<CommonButton> sub_button = null;//二级菜单数组，个数应为1~5个

	public List<CommonButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<CommonButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	
	
}
