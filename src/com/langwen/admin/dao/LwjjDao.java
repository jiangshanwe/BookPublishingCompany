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

import com.langwen.admin.bean.Lwjj;
import com.ssh.HibernateSessionFactory;

public class LwjjDao {
	public void addLwjj(Lwjj lwjj) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			lwjj.setFabushijian(curdate);
			if (lwjj.getZhiding() == null) {
				lwjj.setZhiding(0);
			}
			session.save(lwjj);
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

	public void deleteLwjj(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Lwjj lwjj = (Lwjj) session.load(Lwjj.class, id);
			session.delete(lwjj);
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

	public Lwjj loadLwjj(Integer id) {
		Lwjj lwjj = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			lwjj = (Lwjj) session.get(Lwjj.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return lwjj;
	}

	public List<Lwjj> allLwjj() {
		List<Lwjj> lwjjs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Lwjj as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Lwjj(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Lwjj as jj where jj.zhiding=1 ORDER BY zhidingshijian DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			lwjjs = query.list();
			transaction.commit();
			// String hql2 =
			// "select jj from Lwjj as jj where jj.zhiding=0 order by id DESC";
			String hql2 = "select new Lwjj(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Lwjj as jj where jj.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Lwjj> fzdLwjj = new ArrayList<Lwjj>();
			fzdLwjj = q2.list();
			Iterator<Lwjj> it = fzdLwjj.iterator();
			while (it.hasNext()) {
				lwjjs.add(it.next());
			}
			if (!Hibernate.isInitialized(lwjjs)) {
				Hibernate.initialize(lwjjs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return lwjjs;
	}

	public void updateLwjj(Lwjj lwjj) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (lwjj.getZhiding() == null) {
				lwjj.setZhiding(0);
			}
			session.update(lwjj);
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
	public void testLwjj() {
		// Lwjj lwjj = new Lwjj("历史回顾", "历史回顾的详细内容");
		// addLwjj(lwjj);
		deleteLwjj(5);
		// System.out.println(loadLwjj(2));
		// System.out.println(allLwjj());
		// Lwjj lwjj = new Lwjj("朗文历史", "历史回顾的详细内容");
		// lwjj.setId(5);
		// updateLwjj(lwjj);
	}
}
