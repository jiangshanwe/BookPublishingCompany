package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Bjtj;
import com.langwen.admin.dao.BjtjDao;

public class BjtjService {
	BjtjDao bjtjDao = new BjtjDao();

	public void addBjtj(Bjtj bjtj) {
		bjtjDao.addBjtj(bjtj);
	}

	public void deleteBjtj(Integer id) {
		bjtjDao.deleteBjtj(id);
	}

	public Bjtj loadBjtj(Integer id) {
		return bjtjDao.loadBjtj(id);
	}

	public List<Bjtj> allBjtj() {
		return bjtjDao.allBjtj();
	}

	public void updateBjtj(Bjtj bjtj) {
		bjtjDao.updateBjtj(bjtj);
	}

	public void moveUpBjtj(Integer id) {
		bjtjDao.moveUpBjtj(id);
	}

	public void moveDownBjtj(Integer id) {
		bjtjDao.moveDownBjtj(id);
	}

	public void rePaixunBjtj() {
		bjtjDao.rePaixuBjtj();
	}

	public List<Bjtj> pageBjtj(int page) {
		return bjtjDao.pageBjtj(page);
	}
}
