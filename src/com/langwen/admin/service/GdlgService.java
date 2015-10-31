package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.dao.GdlgDao;

public class GdlgService {
	GdlgDao gdlgDao = new GdlgDao();

	public void addGdlg(Gdlg gdlg) {
		gdlgDao.addGdlg(gdlg);
	}

	public void deleteGdlg(Integer id) {
		gdlgDao.deleteGdlg(id);
	}

	public Gdlg loadGdlg(Integer id) {
		return gdlgDao.loadGdlg(id);
	}

	public List<Gdlg> allGdlg() {
		return gdlgDao.allGdlg();
	}

	public void updateGdlg(Gdlg gdlg) {
		gdlgDao.updateGdlg(gdlg);
	}

	public void moveUpGdlg(Integer id) {
		gdlgDao.moveUpGdlg(id);
	}

	public void moveDownGdlg(Integer id) {
		gdlgDao.moveDownGdlg(id);
	}

	public void rePaixunGdlg() {
		gdlgDao.rePaixuGdlg();
	}

	public List<Gdlg> pageGdlg(int page) {
		return gdlgDao.pageGdlg(page);
	}

	public List<Gdlg> pagefGdlg(int page) {
		return gdlgDao.pagefGdlg(page);
	}
}
