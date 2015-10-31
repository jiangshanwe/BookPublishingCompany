package com.langwen.admin.service;

import com.langwen.admin.bean.Jxfwjs;
import com.langwen.admin.dao.JxfwjsDao;

public class JxfwjsService {
	private JxfwjsDao jxfwjsDao = new JxfwjsDao();

	public Jxfwjs loadJxfwjs() {
		return jxfwjsDao.loadJxfwjs();
	}

	public void updateJxfwjs(Jxfwjs jxfwjs) {
		jxfwjsDao.updateJxfwjs(jxfwjs);
	}
}
