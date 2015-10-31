package com.langwen.admin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.langwen.admin.bean.Yssq;
import com.ssh.HibernateSessionFactory;

public class YssqDao {
	public Yssq loadYssq() {
		Yssq yssq = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			yssq = (Yssq) session.get(Yssq.class, 1);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yssq;
	}
	
	public void updateYssq(Yssq yssq) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(yssq);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
