package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.bean.Swny;
import com.langwen.admin.dao.SwnyDao;

public class SwnyService {
	SwnyDao swnyDao = new SwnyDao();

	public void addSwny(Swny swny) {
		swnyDao.addSwny(swny);
	}

	public void deleteSwny(Integer id) {
		swnyDao.deleteSwny(id);
	}

	public Swny loadSwny(Integer id) {
		return swnyDao.loadSwny(id);
	}

	public List<Swny> allSwny() {
		return swnyDao.allSwny();
	}

	public void updateSwny(Swny swny) {
		swnyDao.updateSwny(swny);
	}

	public void moveUpSwny(Integer id) {
		swnyDao.moveUpSwny(id);
	}

	public void moveDownSwny(Integer id) {
		swnyDao.moveDownSwny(id);
	}

	public void rePaixunSwny() {
		swnyDao.rePaixuSwny();
	}

	public List<Swny> pageSwny(int page) {
		return swnyDao.pageSwny(page);
	}
	
	public List<Swny> pagefSwny(int page) {
		return swnyDao.pagefSwny(page);
	}
}
