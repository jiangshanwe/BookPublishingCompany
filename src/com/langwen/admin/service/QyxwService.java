package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Qyxw;
import com.langwen.admin.dao.QyxwDao;

public class QyxwService {
	QyxwDao qyxwDao = new QyxwDao();

	public void addQyxw(Qyxw qyxw) {
		qyxwDao.addQyxw(qyxw);
	}

	public void deleteQyxw(Integer id) {
		qyxwDao.deleteQyxw(id);
	}

	public Qyxw loadQyxw(Integer id) {
		return qyxwDao.loadQyxw(id);
	}

	public List<Qyxw> allQyxw() {
		return qyxwDao.allQyxw();
	}

	public void updateQyxw(Qyxw qyxw) {
		qyxwDao.updateQyxw(qyxw);
	}

	public void moveUpQyxw(Integer id) {
		qyxwDao.moveUpQyxw(id);
	}

	public void moveDownQyxw(Integer id) {
		qyxwDao.moveDownQyxw(id);
	}

	public void rePaixunQyxw() {
		qyxwDao.rePaixuQyxw();
	}

	public List<Qyxw> pageQyxw(int page) {
		return qyxwDao.pageQyxw(page);
	}
}
