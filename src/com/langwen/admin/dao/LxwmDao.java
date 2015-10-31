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

import com.langwen.admin.bean.Lxwm;
import com.ssh.HibernateSessionFactory;

public class LxwmDao {
	public void addLxwm(Lxwm lxwm) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			lxwm.setFabushijian(curdate);
			if (lxwm.getZhiding() == null) {
				lxwm.setZhiding(0);
			}
			session.save(lxwm);
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

	public void deleteLxwm(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Lxwm lxwm = (Lxwm) session.load(Lxwm.class, id);
			session.delete(lxwm);
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

	public Lxwm loadLxwm(Integer id) {
		Lxwm lxwm = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			lxwm = (Lxwm) session.get(Lxwm.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return lxwm;
	}

	public List<Lxwm> allLxwm() {
		List<Lxwm> lxwms = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Lxwm as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Lxwm(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Lxwm as jj where jj.zhiding=1 ORDER BY zhidingshijian DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			lxwms = query.list();
			transaction.commit();
			// String hql2 =
			// "select jj from Lxwm as jj where jj.zhiding=0 order by id DESC";
			String hql2 = "select new Lxwm(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Lxwm as jj where jj.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Lxwm> fzdLxwm = new ArrayList<Lxwm>();
			fzdLxwm = q2.list();
			Iterator<Lxwm> it = fzdLxwm.iterator();
			while (it.hasNext()) {
				lxwms.add(it.next());
			}
			if (!Hibernate.isInitialized(lxwms)) {
				Hibernate.initialize(lxwms);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return lxwms;
	}

	public void updateLxwm(Lxwm lxwm) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (lxwm.getZhiding() == null) {
				lxwm.setZhiding(0);
			}
			session.update(lxwm);
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

	@Test
	public void testLxwm() {
		// Lxwm lxwm = new Lxwm("历史回顾", "历史回顾的详细内容");
		// addLxwm(lxwm);
		deleteLxwm(5);
		// System.out.println(loadLxwm(2));
		// System.out.println(allLxwm());
		// Lxwm lxwm = new Lxwm("朗文历史", "历史回顾的详细内容");
		// lxwm.setId(5);
		// updateLxwm(lxwm);
	}
}
