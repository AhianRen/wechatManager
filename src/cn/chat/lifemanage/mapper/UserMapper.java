package cn.chat.lifemanage.mapper;

import java.util.List;

import cn.chat.lifemanage.po.User;

public interface UserMapper {
	public User findUserByOpenid(User id) throws Exception;
	
	public void insertUser(User user) throws Exception;
	
	public User findUserRstMap(int id);
}
