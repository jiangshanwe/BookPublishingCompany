package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zsgdlg;
import com.langwen.admin.dao.ZsgdlgDao;

public class ZsgdlgService {
	ZsgdlgDao zsgdlgDao = new ZsgdlgDao();

	public void addZsgdlg(Zsgdlg zsgdlg) {
		zsgdlgDao.addZsgdlg(zsgdlg);
	}

	public void deleteZsgdlg(Integer id) {
		zsgdlgDao.deleteZsgdlg(id);
	}

	public Zsgdlg loadZsgdlg(Integer id) {
		return zsgdlgDao.loadZsgdlg(id);
	}

	public List<Zsgdlg> allZsgdlg() {
		return zsgdlgDao.allZsgdlg();
	}

	public void updateZsgdlg(Zsgdlg zsgdlg) {
		zsgdlgDao.updateZsgdlg(zsgdlg);
	}

	public void moveUpZsgdlg(Integer id) {
		zsgdlgDao.moveUpZsgdlg(id);
	}

	public void moveDownZsgdlg(Integer id) {
		zsgdlgDao.moveDownZsgdlg(id);
	}

	public void rePaixunZsgdlg() {
		zsgdlgDao.rePaixuZsgdlg();
	}

	public List<Zsgdlg> pageZsgdlg(int page) {
		return zsgdlgDao.pageZsgdlg(page);
	}
}
