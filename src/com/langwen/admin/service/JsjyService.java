package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Jsjy;
import com.langwen.admin.dao.JsjyDao;

public class JsjyService {
	JsjyDao jsjyDao = new JsjyDao();

	public void addJsjy(Jsjy jsjy) {
		jsjyDao.addJsjy(jsjy);
	}

	public void deleteJsjy(Integer id) {
		jsjyDao.deleteJsjy(id);
	}

	public Jsjy loadJsjy(Integer id) {
		return jsjyDao.loadJsjy(id);
	}

	public List<Jsjy> allJsjy() {
		return jsjyDao.allJsjy();
	}

	public void updateJsjy(Jsjy jsjy) {
		jsjyDao.updateJsjy(jsjy);
	}

	public void moveUpJsjy(Integer id) {
		jsjyDao.moveUpJsjy(id);
	}

	public void moveDownJsjy(Integer id) {
		jsjyDao.moveDownJsjy(id);
	}

	public void rePaixunJsjy() {
		jsjyDao.rePaixuJsjy();
	}

	public List<Jsjy> pageJsjy(int page) {
		return jsjyDao.pageJsjy(page);
	}
	
	public List<Jsjy> pagefJsjy(int page) {
		return jsjyDao.pagefJsjy(page);
	}
}
