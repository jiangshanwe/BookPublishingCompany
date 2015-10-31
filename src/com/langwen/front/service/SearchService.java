package com.langwen.front.service;

import java.util.List;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.bean.Xzzx;
import com.langwen.front.dao.SearchDao;

public class SearchService {
	private SearchDao searchDao = new SearchDao();

	public List<Object> searchBookByShuming(String key) {
		return searchDao.searchBookByShuming(key);
	}

	public List<Object> searchBookByZuozhe(String key) {
		return searchDao.searchBookByZuozhe(key);
	}

	public List<Object> searchBookByIsbn(String key) {
		return searchDao.searchBookByIsbn(key);
	}

	public List<Object> searchBook(String key) {
		return searchDao.searchBook(key);
	}

	public List<Kjxz> searchKejian(String key) {
		return searchDao.searchKejian(key);
	}

	public List<Xzzx> searchXiazai(String key) {
		return searchDao.searchXiazai(key);
	}
}
