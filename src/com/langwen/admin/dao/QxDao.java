package com.langwen.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Qx;
import com.ssh.HibernateSessionFactory;

public class QxDao {
	public void addQx(Qx qx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(qx);
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

	public void deleteQx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Qx temp = (Qx) session.load(Qx.class, id);
			session.delete(temp);
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

	public void updateQx(Qx qx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(qx);
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

	public Qx loadQx(Integer id) {
		Qx qx = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			qx = (Qx) session.get(Qx.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return qx;
	}

	public List<Qx> allQx() {
		List<Qx> glys = new ArrayList<Qx>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "select a from Qx as a order by a.id";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			glys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(glys)) {
				Hibernate.initialize(glys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return glys;
	}

	@Test
	public void testQx() {
		// Qx qx = new Qx("系统设置");
		// this.addQx(qx);
		// deleteQx(2);
		// Qx qx = new Qx("权限管理");
		// qx.setId(3);
		// updateQx(qx);
		System.out.println(allQx());
	}
}
