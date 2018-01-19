package com.musket.docker.manager.impl;

import com.musket.docker.manager.interfaces.Iconnect;
import com.musket.docker.manager.vo.LoginUser;
import com.musket.docker.manager.vo.Url;

public abstract class DbConnect implements Iconnect {

	private LoginUser user;

	private LoginUser loginUser;

	public boolean creatForuser(Url url, LoginUser user) {
		return false;
	}

}
