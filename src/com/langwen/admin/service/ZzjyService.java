package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.bean.Zzjy;
import com.langwen.admin.dao.ZzjyDao;

public class ZzjyService {
	ZzjyDao zzjyDao = new ZzjyDao();

	public void addZzjy(Zzjy zzjy) {
		zzjyDao.addZzjy(zzjy);
	}

	public void deleteZzjy(Integer id) {
		zzjyDao.deleteZzjy(id);
	}

	public Zzjy loadZzjy(Integer id) {
		return zzjyDao.loadZzjy(id);
	}

	public List<Zzjy> allZzjy() {
		return zzjyDao.allZzjy();
	}

	public void updateZzjy(Zzjy zzjy) {
		zzjyDao.updateZzjy(zzjy);
	}

	public void moveUpZzjy(Integer id) {
		zzjyDao.moveUpZzjy(id);
	}

	public void moveDownZzjy(Integer id) {
		zzjyDao.moveDownZzjy(id);
	}

	public void rePaixunZzjy() {
		zzjyDao.rePaixuZzjy();
	}

	public List<Zzjy> pageZzjy(int page) {
		return zzjyDao.pageZzjy(page);
	}

	public List<Zzjy> pagefZzjy(int page) {
		return zzjyDao.pagefZzjy(page);
	}
}
