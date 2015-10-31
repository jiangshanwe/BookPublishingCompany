package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Qyxw;
import com.ssh.HibernateSessionFactory;

public class QyxwDao {
	public void addQyxw(Qyxw qyxw) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			qyxw.setTianjiashijian(curdate);
			String hql = "select count(*) from Qyxw gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			qyxw.setPaixu(paixu);
			session.save(qyxw);
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

	public void deleteQyxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Qyxw qyxw = (Qyxw) session.load(Qyxw.class, id);
			int paixu = qyxw.getPaixu();
			session.delete(qyxw);
			String hql = "update Qyxw gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Qyxw loadQyxw(Integer id) {
		Qyxw qyxw = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			qyxw = (Qyxw) session.get(Qyxw.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return qyxw;
	}

	public List<Qyxw> allQyxw() {
		List<Qyxw> qyxws = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Qyxw as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Qyxw(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Qyxw as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			qyxws = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(qyxws)) {
				Hibernate.initialize(qyxws);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return qyxws;
	}

	public List<Qyxw> allQyxwById() {
		List<Qyxw> qyxws = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Qyxw as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Qyxw as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			qyxws = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(qyxws)) {
				Hibernate.initialize(qyxws);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return qyxws;
	}

	public void updateQyxw(Qyxw qyxw) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (qyxw.getPaixu() == null) {
				qyxw.setPaixu(loadQyxw(qyxw.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(qyxw);
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
	public void moveDownQyxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Qyxw qyxw = (Qyxw) session.load(Qyxw.class, id);
			if (qyxw.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = qyxw.getPaixu();
			String hql = "update Qyxw gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			qyxw.setPaixu(oldPaixu - 1);
			updateQyxw(qyxw);
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
	public void moveUpQyxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Qyxw qyxw = (Qyxw) session.load(Qyxw.class, id);
			int oldPaixu = qyxw.getPaixu();
			String isMax = "select new Qyxw(jj.id) FROM Qyxw as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Qyxw> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Qyxw gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			qyxw.setPaixu(oldPaixu + 1);
			updateQyxw(qyxw);
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
	public void rePaixuQyxw() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Qyxw> qyxws = allQyxwById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < qyxws.size(); i++) {
				Qyxw temp = qyxws.get(i);
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

	public List<Qyxw> pageQyxw(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Qyxw> qyxws = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Qyxw(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Qyxw as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			qyxws = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return qyxws;
	}

	@Test
	public void testQyxw() {
		for (int i = 1; i < 78; i++) {
			Qyxw qyxw = new Qyxw();
			qyxw.setBiaoti("No." + i + " Qyxw");
			if (i % 3 == 0) {
				qyxw.setLeixing(1);
			} else if (i % 3 == 1) {
				qyxw.setLeixing(2);
			} else {
				qyxw.setLeixing(3);
			}
			addQyxw(qyxw);
		}
	}
}
