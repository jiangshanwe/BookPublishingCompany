package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zszzjy;
import com.langwen.admin.dao.ZszzjyDao;

public class ZszzjyService {
	ZszzjyDao zszzjyDao = new ZszzjyDao();

	public void addZszzjy(Zszzjy zszzjy) {
		zszzjyDao.addZszzjy(zszzjy);
	}

	public void deleteZszzjy(Integer id) {
		zszzjyDao.deleteZszzjy(id);
	}

	public Zszzjy loadZszzjy(Integer id) {
		return zszzjyDao.loadZszzjy(id);
	}

	public List<Zszzjy> allZszzjy() {
		return zszzjyDao.allZszzjy();
	}

	public void updateZszzjy(Zszzjy zszzjy) {
		zszzjyDao.updateZszzjy(zszzjy);
	}

	public void moveUpZszzjy(Integer id) {
		zszzjyDao.moveUpZszzjy(id);
	}

	public void moveDownZszzjy(Integer id) {
		zszzjyDao.moveDownZszzjy(id);
	}

	public void rePaixunZszzjy() {
		zszzjyDao.rePaixuZszzjy();
	}

	public List<Zszzjy> pageZszzjy(int page) {
		return zszzjyDao.pageZszzjy(page);
	}
}
