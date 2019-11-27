package com.websitetintuc.service.impl;

import javax.inject.Inject;

import com.websitetintuc.dao.IUserDAO;
import com.websitetintuc.dao.impl.UserDAO;
import com.websitetintuc.model.UserModel;
import com.websitetintuc.service.IUserService;

public class UserService implements IUserService {
	@Inject
	private IUserDAO userDAO;
	public UserService(){
		userDAO = new UserDAO();
	}
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
