package com.langwen.admin.service;

import com.langwen.admin.bean.Yssq;
import com.langwen.admin.dao.YssqDao;

public class YssqService {
	private YssqDao yssqDao = new YssqDao();

	public Yssq loadYssq() {
		return yssqDao.loadYssq();
	}

	public void updateYssq(Yssq yssq) {
		yssqDao.updateYssq(yssq);
	}
}
