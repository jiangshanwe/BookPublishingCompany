package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Zsswny;
import com.langwen.admin.dao.ZsswnyDao;

public class ZsswnyService {
	ZsswnyDao zsswnyDao = new ZsswnyDao();

	public void addZsswny(Zsswny zsswny) {
		zsswnyDao.addZsswny(zsswny);
	}

	public void deleteZsswny(Integer id) {
		zsswnyDao.deleteZsswny(id);
	}

	public Zsswny loadZsswny(Integer id) {
		return zsswnyDao.loadZsswny(id);
	}

	public List<Zsswny> allZsswny() {
		return zsswnyDao.allZsswny();
	}

	public void updateZsswny(Zsswny zsswny) {
		zsswnyDao.updateZsswny(zsswny);
	}

	public void moveUpZsswny(Integer id) {
		zsswnyDao.moveUpZsswny(id);
	}

	public void moveDownZsswny(Integer id) {
		zsswnyDao.moveDownZsswny(id);
	}

	public void rePaixunZsswny() {
		zsswnyDao.rePaixuZsswny();
	}

	public List<Zsswny> pageZsswny(int page) {
		return zsswnyDao.pageZsswny(page);
	}
}
