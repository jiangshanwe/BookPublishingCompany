package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.bean.Gzjy;
import com.langwen.admin.dao.GzjyDao;

public class GzjyService {
	GzjyDao gzjyDao = new GzjyDao();

	public void addGzjy(Gzjy gzjy) {
		gzjyDao.addGzjy(gzjy);
	}

	public void deleteGzjy(Integer id) {
		gzjyDao.deleteGzjy(id);
	}

	public Gzjy loadGzjy(Integer id) {
		return gzjyDao.loadGzjy(id);
	}

	public List<Gzjy> allGzjy() {
		return gzjyDao.allGzjy();
	}

	public void updateGzjy(Gzjy gzjy) {
		gzjyDao.updateGzjy(gzjy);
	}

	public void moveUpGzjy(Integer id) {
		gzjyDao.moveUpGzjy(id);
	}

	public void moveDownGzjy(Integer id) {
		gzjyDao.moveDownGzjy(id);
	}

	public void rePaixunGzjy() {
		gzjyDao.rePaixuGzjy();
	}

	public List<Gzjy> pageGzjy(int page) {
		return gzjyDao.pageGzjy(page);
	}
	
	public List<Gzjy> pagefGzjy(int page) {
		return gzjyDao.pagefGzjy(page);
	}
}
