package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Lwjj;
import com.langwen.admin.dao.LwjjDao;

public class LwjjService {
	private LwjjDao lwjjDao = new LwjjDao();

	public void addLwjj(Lwjj lwjj) {
		lwjjDao.addLwjj(lwjj);
	}

	public void deleteLwjj(Integer id) {
		lwjjDao.deleteLwjj(id);
	}

	public Lwjj loadLwjj(Integer id) {
		return lwjjDao.loadLwjj(id);
	}

	public List<Lwjj> allLwjj() {
		return lwjjDao.allLwjj();
	}

	public void updateLwjj(Lwjj lwjj) {
		lwjjDao.updateLwjj(lwjj);
	}
}
