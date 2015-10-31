package com.langwen.admin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.langwen.admin.bean.Jxfwjs;
import com.ssh.HibernateSessionFactory;

public class JxfwjsDao {
	public Jxfwjs loadJxfwjs() {
		Jxfwjs yssq = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			yssq = (Jxfwjs) session.get(Jxfwjs.class, 1);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yssq;
	}
	
	public void updateJxfwjs(Jxfwjs jxfwjs) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(jxfwjs);
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
}
