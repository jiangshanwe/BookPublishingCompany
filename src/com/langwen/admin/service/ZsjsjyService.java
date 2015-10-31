package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zsjsjy;
import com.langwen.admin.dao.ZsjsjyDao;

public class ZsjsjyService {
	ZsjsjyDao zsjsjyDao = new ZsjsjyDao();

	public void addZsjsjy(Zsjsjy zsjsjy) {
		zsjsjyDao.addZsjsjy(zsjsjy);
	}

	public void deleteZsjsjy(Integer id) {
		zsjsjyDao.deleteZsjsjy(id);
	}

	public Zsjsjy loadZsjsjy(Integer id) {
		return zsjsjyDao.loadZsjsjy(id);
	}

	public List<Zsjsjy> allZsjsjy() {
		return zsjsjyDao.allZsjsjy();
	}

	public void updateZsjsjy(Zsjsjy zsjsjy) {
		zsjsjyDao.updateZsjsjy(zsjsjy);
	}

	public void moveUpZsjsjy(Integer id) {
		zsjsjyDao.moveUpZsjsjy(id);
	}

	public void moveDownZsjsjy(Integer id) {
		zsjsjyDao.moveDownZsjsjy(id);
	}

	public void rePaixunZsjsjy() {
		zsjsjyDao.rePaixuZsjsjy();
	}

	public List<Zsjsjy> pageZsjsjy(int page) {
		return zsjsjyDao.pageZsjsjy(page);
	}
}
