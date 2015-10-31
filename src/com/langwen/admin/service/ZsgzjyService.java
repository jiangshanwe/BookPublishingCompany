package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zsgzjy;
import com.langwen.admin.dao.ZsgzjyDao;

public class ZsgzjyService {
	ZsgzjyDao zsgzjyDao = new ZsgzjyDao();

	public void addZsgzjy(Zsgzjy zsgzjy) {
		zsgzjyDao.addZsgzjy(zsgzjy);
	}

	public void deleteZsgzjy(Integer id) {
		zsgzjyDao.deleteZsgzjy(id);
	}

	public Zsgzjy loadZsgzjy(Integer id) {
		return zsgzjyDao.loadZsgzjy(id);
	}

	public List<Zsgzjy> allZsgzjy() {
		return zsgzjyDao.allZsgzjy();
	}

	public void updateZsgzjy(Zsgzjy zsgzjy) {
		zsgzjyDao.updateZsgzjy(zsgzjy);
	}

	public void moveUpZsgzjy(Integer id) {
		zsgzjyDao.moveUpZsgzjy(id);
	}

	public void moveDownZsgzjy(Integer id) {
		zsgzjyDao.moveDownZsgzjy(id);
	}

	public void rePaixunZsgzjy() {
		zsgzjyDao.rePaixuZsgzjy();
	}

	public List<Zsgzjy> pageZsgzjy(int page) {
		return zsgzjyDao.pageZsgzjy(page);
	}
}
