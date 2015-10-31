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

import com.langwen.admin.bean.Rczp;
import com.ssh.HibernateSessionFactory;

public class RczpDao {
	public void addRczp(Rczp rczp) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			rczp.setFabushijian(curdate);
			if (rczp.getZhiding() == null) {
				rczp.setZhiding(0);
			}
			session.save(rczp);
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

	public void deleteRczp(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Rczp rczp = (Rczp) session.load(Rczp.class, id);
			session.delete(rczp);
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

	public Rczp loadRczp(Integer id) {
		Rczp rczp = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			rczp = (Rczp) session.get(Rczp.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return rczp;
	}

	public List<Rczp> allRczp() {
		List<Rczp> rczps = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Rczp as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Rczp(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Rczp as jj where jj.zhiding=1 ORDER BY zhidingshijian DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			rczps = query.list();
			transaction.commit();
			// String hql2 =
			// "select jj from Rczp as jj where jj.zhiding=0 order by id DESC";
			String hql2 = "select new Rczp(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Rczp as jj where jj.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Rczp> fzdRczp = new ArrayList<Rczp>();
			fzdRczp = q2.list();
			Iterator<Rczp> it = fzdRczp.iterator();
			while (it.hasNext()) {
				rczps.add(it.next());
			}
			if (!Hibernate.isInitialized(rczps)) {
				Hibernate.initialize(rczps);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return rczps;
	}

	public void updateRczp(Rczp rczp) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (rczp.getZhiding() == null) {
				rczp.setZhiding(0);
			}
			session.update(rczp);
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
	public void testRczp() {
		// Rczp rczp = new Rczp("历史回顾", "历史回顾的详细内容");
		// addRczp(rczp);
		deleteRczp(5);
		// System.out.println(loadRczp(2));
		// System.out.println(allRczp());
		// Rczp rczp = new Rczp("朗文历史", "历史回顾的详细内容");
		// rczp.setId(5);
		// updateRczp(rczp);
	}
}
