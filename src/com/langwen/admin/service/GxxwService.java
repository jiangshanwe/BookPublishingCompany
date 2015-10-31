package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gxxw;
import com.langwen.admin.dao.GxxwDao;

public class GxxwService {
	GxxwDao gxxwDao = new GxxwDao();

	public void addGxxw(Gxxw gxxw) {
		gxxwDao.addGxxw(gxxw);
	}

	public void deleteGxxw(Integer id) {
		gxxwDao.deleteGxxw(id);
	}

	public Gxxw loadGxxw(Integer id) {
		return gxxwDao.loadGxxw(id);
	}

	public List<Gxxw> allGxxw() {
		return gxxwDao.allGxxw();
	}

	public void updateGxxw(Gxxw gxxw) {
		gxxwDao.updateGxxw(gxxw);
	}

	public void moveUpGxxw(Integer id) {
		gxxwDao.moveUpGxxw(id);
	}

	public void moveDownGxxw(Integer id) {
		gxxwDao.moveDownGxxw(id);
	}

	public void rePaixunGxxw() {
		gxxwDao.rePaixuGxxw();
	}

	public List<Gxxw> pageGxxw(int page) {
		return gxxwDao.pageGxxw(page);
	}
}
