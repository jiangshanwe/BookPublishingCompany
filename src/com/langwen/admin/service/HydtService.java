package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Hydt;
import com.langwen.admin.dao.HydtDao;

public class HydtService {
	HydtDao hydtDao = new HydtDao();

	public void addHydt(Hydt hydt) {
		hydtDao.addHydt(hydt);
	}

	public void deleteHydt(Integer id) {
		hydtDao.deleteHydt(id);
	}

	public Hydt loadHydt(Integer id) {
		return hydtDao.loadHydt(id);
	}

	public List<Hydt> allHydt() {
		return hydtDao.allHydt();
	}

	public void updateHydt(Hydt hydt) {
		hydtDao.updateHydt(hydt);
	}

	public void moveUpHydt(Integer id) {
		hydtDao.moveUpHydt(id);
	}

	public void moveDownHydt(Integer id) {
		hydtDao.moveDownHydt(id);
	}

	public void rePaixunHydt() {
		hydtDao.rePaixuHydt();
	}

	public List<Hydt> pageHydt(int page) {
		return hydtDao.pageHydt(page);
	}
}
