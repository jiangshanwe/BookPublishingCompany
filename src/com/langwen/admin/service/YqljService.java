package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Yqlj;
import com.langwen.admin.dao.YqljDao;

public class YqljService {
	YqljDao yqljDao = new YqljDao();

	public void addYqlj(Yqlj yqlj) {
		yqljDao.addYqlj(yqlj);
	}

	public void deleteYqlj(Integer id) {
		yqljDao.deleteYqlj(id);
	}

	public Yqlj loadYqlj(Integer id) {
		return yqljDao.loadYqlj(id);
	}

	public List<Yqlj> allYqlj() {
		return yqljDao.allYqlj();
	}

	public void updateYqlj(Yqlj yqlj) {
		yqljDao.updateYqlj(yqlj);
	}

	public void moveUpYqlj(Integer id) {
		yqljDao.moveUpYqlj(id);
	}

	public void moveDownYqlj(Integer id) {
		yqljDao.moveDownYqlj(id);
	}

	public void rePaixunYqlj() {
		yqljDao.rePaixuYqlj();
	}

	public List<Yqlj> pageYqlj(int page) {
		return yqljDao.pageYqlj(page);
	}
}
