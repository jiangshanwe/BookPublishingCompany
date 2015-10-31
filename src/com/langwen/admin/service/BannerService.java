package com.langwen.admin.service;

import java.util.List;

import com.langwen.admin.bean.Banner;
import com.langwen.admin.dao.BannerDao;

public class BannerService {
	private BannerDao bannerDao = new BannerDao();

	public void addBanner(Banner banner) {
		bannerDao.addBanner(banner);
	}

	public void deleteBanner(Integer id) {
		bannerDao.deleteBanner(id);
	}

	public Banner loadBanner(Integer id) {
		return bannerDao.loadBanner(id);
	}

	public List<Banner> allBanners() {
		return bannerDao.allBanners();
	}

	public void updateBanner(Banner banner) {
		bannerDao.updateBanner(banner);
	}

	public List<Banner> pageBanner(int page) {
		return bannerDao.pageBanner(page);
	}
}
