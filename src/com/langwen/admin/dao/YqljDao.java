package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Yqlj;
import com.ssh.HibernateSessionFactory;

public class YqljDao {
	public void addYqlj(Yqlj yqlj) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			yqlj.setTianjiashijian(curdate);
			String hql = "select count(*) from Yqlj gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			yqlj.setPaixu(paixu);
			session.save(yqlj);
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

	public void deleteYqlj(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Yqlj yqlj = (Yqlj) session.load(Yqlj.class, id);
			int paixu = yqlj.getPaixu();
			session.delete(yqlj);
			String hql = "update Yqlj gw set gw.paixu = gw.paixu -1 where gw.paixu > "
					+ paixu;
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

	public Yqlj loadYqlj(Integer id) {
		Yqlj yqlj = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			yqlj = (Yqlj) session.get(Yqlj.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yqlj;
	}

	public List<Yqlj> allYqlj() {
		List<Yqlj> yqljs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Yqlj as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Yqlj as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			yqljs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(yqljs)) {
				Hibernate.initialize(yqljs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yqljs;
	}

	public List<Yqlj> allYqljById() {
		List<Yqlj> yqljs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Yqlj as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Yqlj as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			yqljs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(yqljs)) {
				Hibernate.initialize(yqljs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yqljs;
	}

	public void updateYqlj(Yqlj yqlj) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (yqlj.getPaixu() == null) {
				yqlj.setPaixu(loadYqlj(yqlj.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(yqlj);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	// 将指定的图书，排序向下移动一位
	public void moveDownYqlj(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Yqlj yqlj = (Yqlj) session.load(Yqlj.class, id);
			if (yqlj.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = yqlj.getPaixu();
			String hql = "update Yqlj gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
					+ (oldPaixu - 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query query = session.createQuery(hql);
			query.executeUpdate();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			yqlj.setPaixu(oldPaixu - 1);
			updateYqlj(yqlj);
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

	// 将指定的图书，排序向上移动一位
	public void moveUpYqlj(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Yqlj yqlj = (Yqlj) session.load(Yqlj.class, id);
			int oldPaixu = yqlj.getPaixu();
			String isMax = "select new Yqlj(jj.id) FROM Yqlj as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Yqlj> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Yqlj gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query query = session.createQuery(hql);
			query.executeUpdate();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			yqlj.setPaixu(oldPaixu + 1);
			updateYqlj(yqlj);
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

	// 重新按照添加时间排序
	public void rePaixuYqlj() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Yqlj> yqljs = allYqljById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < yqljs.size(); i++) {
				Yqlj temp = yqljs.get(i);
				temp.setPaixu(i + 1);
				session.update(temp);
				if (i % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
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

	public List<Yqlj> pageYqlj(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Yqlj> yqljs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Yqlj as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			yqljs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return yqljs;
	}

	@Test
	public void testYqlj() {
		for (int i = 1; i < 8; i++) {
			Yqlj yqlj = new Yqlj();
			yqlj.setShuming("No." + i + "Yqlj");
			yqlj.setLianjie("http://www.sina.com");
			yqlj.setTianjiaren("Harold");
			addYqlj(yqlj);
		}
	}

}
