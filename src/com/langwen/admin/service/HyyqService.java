package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Hyyq;
import com.langwen.admin.dao.HyyqDao;

public class HyyqService {
	private HyyqDao hyyqDao = new HyyqDao();

	public void addHyyq(Hyyq hyyq) {
		hyyqDao.addHyyq(hyyq);
	}

	public void deleteHyyq(Integer id) {
		hyyqDao.deleteHyyq(id);
	}
	
	public Hyyq loadHyyq(Integer id) {
		return hyyqDao.loadHyyq(id);
	}
	
	public List<Hyyq> allHyyq() {
		return hyyqDao.allHyyq();
	}
	
	public void updateHyyq(Hyyq hyyq) {
		hyyqDao.updateHyyq(hyyq);
	}
	
	public void moveDownHyyq(Integer id) {
		hyyqDao.moveDownHyyq(id);
	}
	
	public void moveUpHyyq(Integer id) {
		hyyqDao.moveUpHyyq(id);
	}
	
	public void rePaixuHyyq() {
		hyyqDao.rePaixuHyyq();
	}
	
	public List<Hyyq> pageHyyq(int page) {
		return hyyqDao.pageHyyq(page);
	}
}
