package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zsgdwk;
import com.langwen.admin.dao.ZsgdwkDao;

public class ZsgdwkService {
	ZsgdwkDao zsgdwkDao = new ZsgdwkDao();

	public void addZsgdwk(Zsgdwk zsgdwk) {
		zsgdwkDao.addZsgdwk(zsgdwk);
	}

	public void deleteZsgdwk(Integer id) {
		zsgdwkDao.deleteZsgdwk(id);
	}

	public Zsgdwk loadZsgdwk(Integer id) {
		return zsgdwkDao.loadZsgdwk(id);
	}

	public List<Zsgdwk> allZsgdwk() {
		return zsgdwkDao.allZsgdwk();
	}

	public void updateZsgdwk(Zsgdwk zsgdwk) {
		zsgdwkDao.updateZsgdwk(zsgdwk);
	}

	public void moveUpZsgdwk(Integer id) {
		zsgdwkDao.moveUpZsgdwk(id);
	}

	public void moveDownZsgdwk(Integer id) {
		zsgdwkDao.moveDownZsgdwk(id);
	}

	public void rePaixunZsgdwk() {
		zsgdwkDao.rePaixuZsgdwk();
	}

	public List<Zsgdwk> pageZsgdwk(int page) {
		return zsgdwkDao.pageZsgdwk(page);
	}
}
