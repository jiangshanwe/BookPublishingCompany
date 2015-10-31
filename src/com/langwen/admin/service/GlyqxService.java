package com.langwen.admin.service;

import java.util.List;

import org.junit.Test;

import com.langwen.admin.bean.Glyqx;
import com.langwen.admin.dao.GlyqxDao;

public class GlyqxService {
	private GlyqxDao glyqxDao = new GlyqxDao();

	public void addGlyqx(Glyqx glyqx) {
		glyqxDao.addGlyqx(glyqx);
	}

	public void deleteGlyqx(Integer id) {
		glyqxDao.deleteGlyqx(id);
	}

	public void updateGlyqx(Glyqx glyqx) {
		glyqxDao.updateGlyqx(glyqx);
	}

	public Glyqx loadGlyqx(Integer id) {
		return glyqxDao.loadGlyqx(id);
	}

	public List<Glyqx> allGlyqx() {
		return glyqxDao.allGlyqx();
	}

	public void qkGlyqx(Integer id) {
		glyqxDao.qkGlyqx(id);
	}
}
