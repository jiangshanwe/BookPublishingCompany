package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Cbtd;
import com.langwen.admin.dao.CbtdDao;

public class CbtdService {
	private CbtdDao cbtdDao = new CbtdDao();

	public void addCbtd(Cbtd cbtd) {
		cbtdDao.addCbtd(cbtd);
	}

	public void deleteCbtd(Integer id) {
		cbtdDao.deleteCbtd(id);
	}

	public Cbtd loadCbtd(Integer id) {
		return cbtdDao.loadCbtd(id);
	}

	public List<Cbtd> allCbtd() {
		return cbtdDao.allCbtd();
	}

	public void updateCbtd(Cbtd cbtd) {
		cbtdDao.updateCbtd(cbtd);
	}
}
