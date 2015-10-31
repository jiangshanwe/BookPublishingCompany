package com.langwen.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Glyqx;
import com.ssh.HibernateSessionFactory;

public class GlyqxDao {
	public void addGlyqx(Glyqx glyqx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(glyqx);
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

	public void deleteGlyqx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Glyqx temp = (Glyqx) session.load(Glyqx.class, id);
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

	public void updateGlyqx(Glyqx glyqx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(glyqx);
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

	public Glyqx loadGlyqx(Integer id) {
		Glyqx glyqx = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			glyqx = (Glyqx) session.get(Glyqx.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return glyqx;
	}

	public List<Glyqx> allGlyqx() {
		List<Glyqx> glyqxs = new ArrayList<Glyqx>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "select a from Glyqx as a order by a.id";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			glyqxs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(glyqxs)) {
				Hibernate.initialize(glyqxs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return glyqxs;
	}

	// 清空指定管理员的全部权限
	public void qkGlyqx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "delete Glyqx gq where gq.glyid=" + id;
			Query query = session.createQuery(hql);
			query.executeUpdate();
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
	public void testGlyqx() {
		// Glyqx qx = new Glyqx("系统设置");
		// this.addGlyqx(qx);
		// deleteGlyqx(2);
		// Glyqx qx = new Glyqx("权限管理");
		// qx.setId(3);
		// updateGlyqx(qx);
		System.out.println(allGlyqx());
	}
}
