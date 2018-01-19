package com.musket.docker.manager.util.impl;

import com.musket.docker.manager.util.interfaces.Iconnect;
import com.musket.docker.manager.util.vo.LoginUser;
import com.musket.docker.manager.util.vo.Url;

public abstract class DbConnect implements Iconnect {

	private LoginUser user;

	private LoginUser loginUser;

	public boolean creatForuser(Url url, LoginUser user) {
		return false;
	}

}
