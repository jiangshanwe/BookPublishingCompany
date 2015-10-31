package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Rczp;
import com.langwen.admin.dao.RczpDao;

public class RczpService {
	private RczpDao rczpDao = new RczpDao();

	public void addRczp(Rczp rczp) {
		rczpDao.addRczp(rczp);
	}

	public void deleteRczp(Integer id) {
		rczpDao.deleteRczp(id);
	}

	public Rczp loadRczp(Integer id) {
		return rczpDao.loadRczp(id);
	}

	public List<Rczp> allRczp() {
		return rczpDao.allRczp();
	}

	public void updateRczp(Rczp rczp) {
		rczpDao.updateRczp(rczp);
	}
}
