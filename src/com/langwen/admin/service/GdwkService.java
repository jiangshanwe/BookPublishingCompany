package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.bean.Gdwk;
import com.langwen.admin.dao.GdwkDao;

public class GdwkService {
	GdwkDao gdwkDao = new GdwkDao();

	public void addGdwk(Gdwk gdwk) {
		gdwkDao.addGdwk(gdwk);
	}

	public void deleteGdwk(Integer id) {
		gdwkDao.deleteGdwk(id);
	}

	public Gdwk loadGdwk(Integer id) {
		return gdwkDao.loadGdwk(id);
	}

	public List<Gdwk> allGdwk() {
		return gdwkDao.allGdwk();
	}

	public void updateGdwk(Gdwk gdwk) {
		gdwkDao.updateGdwk(gdwk);
	}

	public void moveUpGdwk(Integer id) {
		gdwkDao.moveUpGdwk(id);
	}

	public void moveDownGdwk(Integer id) {
		gdwkDao.moveDownGdwk(id);
	}

	public void rePaixunGdwk() {
		gdwkDao.rePaixuGdwk();
	}

	public List<Gdwk> pageGdwk(int page) {
		return gdwkDao.pageGdwk(page);
	}
	
	public List<Gdwk> pagefGdwk(int page) {
		return gdwkDao.pagefGdwk(page);
	}
}
