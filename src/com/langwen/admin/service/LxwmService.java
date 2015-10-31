package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Lxwm;
import com.langwen.admin.dao.LxwmDao;

public class LxwmService {
	private LxwmDao lxwmDao = new LxwmDao();

	public void addLxwm(Lxwm lxwm) {
		lxwmDao.addLxwm(lxwm);
	}

	public void deleteLxwm(Integer id) {
		lxwmDao.deleteLxwm(id);
	}

	public Lxwm loadLxwm(Integer id) {
		return lxwmDao.loadLxwm(id);
	}

	public List<Lxwm> allLxwm() {
		return lxwmDao.allLxwm();
	}

	public void updateLxwm(Lxwm lxwm) {
		lxwmDao.updateLxwm(lxwm);
	}
}
