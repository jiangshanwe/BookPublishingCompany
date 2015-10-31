package com.langwen.admin.service;

import java.util.List;

import org.junit.Test;

import com.langwen.admin.bean.Qx;
import com.langwen.admin.dao.QxDao;

public class QxService {
	private QxDao qxDao = new QxDao();

	public void addQx(Qx qx) {
		qxDao.addQx(qx);
	}

	public void deleteQx(Integer id) {
		qxDao.deleteQx(id);
	}

	public void updateQx(Qx qx) {
		qxDao.updateQx(qx);
	}

	public Qx loadQx(Integer id) {
		return qxDao.loadQx(id);
	}

	public List<Qx> allQx() {
		return qxDao.allQx();
	}

	@Test
	public void testQx() {
		// Qx qx = new Qx("系统设置");
		// this.addQx(qx);
		// deleteQx(2);
		// Qx qx = new Qx("权限管理");
		// qx.setId(3);
		// updateQx(qx);
		System.out.println(allQx());
	}
}
