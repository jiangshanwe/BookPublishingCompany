package com.langwen.admin.service;

import java.util.List;

import org.junit.Test;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.dao.GuanliyuanDao;

public class GuanliyuanService {
	private GuanliyuanDao guanliyuanDao = new GuanliyuanDao();

	public void addGuanliyuan(Guanliyuan guanliyuan) {
		guanliyuanDao.addGuanliyuan(guanliyuan);
	}

	public void deleteGuanliyuan(Integer id) {
		guanliyuanDao.deleteGuanliyuan(id);
	}

	public void updateGuanliyuan(Guanliyuan guanliyuan) {
		guanliyuanDao.updateGuanliyuan(guanliyuan);
	}

	public Guanliyuan loadGuanliyuan(Integer id) {
		return guanliyuanDao.loadGuanliyuan(id);
	}

	public List<Guanliyuan> allGuanliyuan() {
		return guanliyuanDao.allGuanliyuan();
	}

	public GuanliyuanDao getGuanliyuanDao() {
		return guanliyuanDao;
	}

	public void setGuanliyuanDao(GuanliyuanDao guanliyuanDao) {
		this.guanliyuanDao = guanliyuanDao;
	}

	public Guanliyuan guanliyuanLogin(String gonghao, String mima) {
		return guanliyuanDao.guanliyuanLogin(gonghao, mima);
	}

	public boolean checkGuanliyuanQx(Integer glyid, Integer qxid) {
		return guanliyuanDao.checkGuanliyuanQx(glyid, qxid);
	}

	@Test
	public void testService() {
		Guanliyuan tg = new Guanliyuan("2015", "123456", "½¯ÉÆÎ°", 1);
		addGuanliyuan(tg);
	}
}
