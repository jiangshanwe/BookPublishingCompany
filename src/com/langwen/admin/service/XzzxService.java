package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Xzzx;
import com.langwen.admin.dao.XzzxDao;

public class XzzxService {
	XzzxDao xzzxDao = new XzzxDao();

	public void addXzzx(Xzzx xzzx) {
		xzzxDao.addXzzx(xzzx);
	}

	public void deleteXzzx(Integer id) {
		xzzxDao.deleteXzzx(id);
	}

	public Xzzx loadXzzx(Integer id) {
		return xzzxDao.loadXzzx(id);
	}

	public List<Xzzx> allXzzx() {
		return xzzxDao.allXzzx();
	}

	public void updateXzzx(Xzzx xzzx) {
		xzzxDao.updateXzzx(xzzx);
	}

	public void moveUpXzzx(Integer id) {
		xzzxDao.moveUpXzzx(id);
	}

	public void moveDownXzzx(Integer id) {
		xzzxDao.moveDownXzzx(id);
	}

	public void rePaixunXzzx() {
		xzzxDao.rePaixuXzzx();
	}

	public List<Xzzx> pageXzzx(int page) {
		return xzzxDao.pageXzzx(page);
	}
}
