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

import com.langwen.admin.bean.Cbtd;
import com.ssh.HibernateSessionFactory;

public class CbtdDao {
	public void addCbtd(Cbtd cbtd) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			cbtd.setFabushijian(curdate);
			if (cbtd.getZhiding() == null) {
				cbtd.setZhiding(0);
			}
			session.save(cbtd);
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

	public void deleteCbtd(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Cbtd cbtd = (Cbtd) session.load(Cbtd.class, id);
			session.delete(cbtd);
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

	public Cbtd loadCbtd(Integer id) {
		Cbtd cbtd = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			cbtd = (Cbtd) session.get(Cbtd.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return cbtd;
	}

	public List<Cbtd> allCbtd() {
		List<Cbtd> cbtds = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Cbtd as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Cbtd(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Cbtd as jj where jj.zhiding=1 ORDER BY zhidingshijian DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			cbtds = query.list();
			transaction.commit();
			// String hql2 =
			// "select jj from Cbtd as jj where jj.zhiding=0 order by id DESC";
			String hql2 = "select new Cbtd(jj.id, jj.biaoti, jj.faburen, jj.fabushijian,jj.zhiding, jj.gengxinren, jj.gengxinshijian) from Cbtd as jj where jj.zhiding=0 order by id DESC";
			Query q2 = session.createQuery(hql2);
			List<Cbtd> fzdCbtd = new ArrayList<Cbtd>();
			fzdCbtd = q2.list();
			Iterator<Cbtd> it = fzdCbtd.iterator();
			while (it.hasNext()) {
				cbtds.add(it.next());
			}
			if (!Hibernate.isInitialized(cbtds)) {
				Hibernate.initialize(cbtds);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return cbtds;
	}

	public void updateCbtd(Cbtd cbtd) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (cbtd.getZhiding() == null) {
				cbtd.setZhiding(0);
			}
			session.update(cbtd);
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
	public void testCbtd() {
		// Cbtd cbtd = new Cbtd("历史回顾", "历史回顾的详细内容");
		// addCbtd(cbtd);
		deleteCbtd(5);
		// System.out.println(loadCbtd(2));
		// System.out.println(allCbtd());
		// Cbtd cbtd = new Cbtd("朗文历史", "历史回顾的详细内容");
		// cbtd.setId(5);
		// updateCbtd(cbtd);
	}
}
