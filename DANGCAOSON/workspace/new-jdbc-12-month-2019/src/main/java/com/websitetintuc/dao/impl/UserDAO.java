package com.websitetintuc.dao.impl;

import java.util.List;

import com.websitetintuc.dao.IUserDAO;

import com.websitetintuc.mapper.UserMapper;
import com.websitetintuc.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> user = query(sql.toString(), new UserMapper(), userName, password, status);
		return user.isEmpty() ? null : user.get(0);
	}

}
