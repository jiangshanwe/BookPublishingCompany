package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.dao.KjxzDao;

public class KjxzService {
	KjxzDao kjxzDao = new KjxzDao();

	public void addKjxz(Kjxz kjxz) {
		kjxzDao.addKjxz(kjxz);
	}

	public void deleteKjxz(Integer id) {
		kjxzDao.deleteKjxz(id);
	}

	public Kjxz loadKjxz(Integer id) {
		return kjxzDao.loadKjxz(id);
	}

	public List<Kjxz> allKjxz() {
		return kjxzDao.allKjxz();
	}

	public void updateKjxz(Kjxz kjxz) {
		kjxzDao.updateKjxz(kjxz);
	}

	public void moveUpKjxz(Integer id) {
		kjxzDao.moveUpKjxz(id);
	}

	public void moveDownKjxz(Integer id) {
		kjxzDao.moveDownKjxz(id);
	}

	public void rePaixunKjxz() {
		kjxzDao.rePaixuKjxz();
	}

	public List<Kjxz> pageKjxz(int page) {
		return kjxzDao.pageKjxz(page);
	}
}
