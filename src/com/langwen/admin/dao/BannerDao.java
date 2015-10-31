package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Banner;
import com.langwen.admin.bean.Zsgdlg;
import com.ssh.HibernateSessionFactory;

public class BannerDao {
	public void addBanner(Banner banner) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			banner.setFabushijian(curdate);
			session.save(banner);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public void deleteBanner(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Banner banner = (Banner) session.load(Banner.class, id);
			session.delete(banner);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Banner loadBanner(Integer id) {
		Banner banner = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			banner = (Banner) session.get(Banner.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return banner;
	}

	public List<Banner> allBanners() {
		List<Banner> banners = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "SELECT ba FROM Banner as ba WHERE ba.zhiding=1 ORDER BY zhidingshijian DESC";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			banners = query.list();
			transaction.commit();
			String hql2 = "select ba from Banner as ba where ba.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Banner> fzdBanner = new ArrayList<Banner>();
			fzdBanner = q2.list();
			Iterator<Banner> it = fzdBanner.iterator();
			while (it.hasNext()) {
				banners.add(it.next());
			}
			if (!Hibernate.isInitialized(banners)) {
				Hibernate.initialize(banners);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return banners;
	}

	public void updateBanner(Banner banner) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(banner);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<Banner> pageBanner(int page) {
		List<Banner> banners = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "SELECT ba FROM Banner as ba WHERE ba.zhiding=1 ORDER BY zhidingshijian DESC";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			banners = query.list();
			transaction.commit();
			if (banners.size() >= 5) {
				return banners;
			}
			String hql2 = "select ba from Banner as ba where ba.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Banner> fzdBanner = new ArrayList<Banner>();
			fzdBanner = q2.list();
			Iterator<Banner> it = fzdBanner.iterator();
			int i = banners.size();
			while (it.hasNext() && i < 6) {
				i++;
				banners.add(it.next());
			}
			if (!Hibernate.isInitialized(banners)) {
				Hibernate.initialize(banners);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return banners;
	}

	@Test
	public void testBanner() {
		// Banner banner = new Banner(0, "fjius.png");
		// banner.setId(1);
		// updateBanner(banner);
		System.out.println(allBanners());
	}
}
