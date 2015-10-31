package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Xzzx;
import com.ssh.HibernateSessionFactory;

public class XzzxDao {
	public void addXzzx(Xzzx xzzx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			xzzx.setTianjiashijian(curdate);
			String hql = "select count(*) from Xzzx gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			xzzx.setPaixu(paixu);
			session.save(xzzx);
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

	public void deleteXzzx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Xzzx xzzx = (Xzzx) session.load(Xzzx.class, id);
			int paixu = xzzx.getPaixu();
			session.delete(xzzx);
			String hql = "update Xzzx gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Xzzx loadXzzx(Integer id) {
		Xzzx xzzx = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			xzzx = (Xzzx) session.get(Xzzx.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return xzzx;
	}

	public List<Xzzx> allXzzx() {
		List<Xzzx> xzzxs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Xzzx as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Xzzx(jj.id, jj.biaoti, jj.dyts, jj.fujian,	jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Xzzx as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			xzzxs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(xzzxs)) {
				Hibernate.initialize(xzzxs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return xzzxs;
	}

	public List<Xzzx> allXzzxById() {
		List<Xzzx> xzzxs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Xzzx as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Xzzx as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			xzzxs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(xzzxs)) {
				Hibernate.initialize(xzzxs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return xzzxs;
	}

	public void updateXzzx(Xzzx xzzx) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (xzzx.getPaixu() == null) {
				xzzx.setPaixu(loadXzzx(xzzx.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(xzzx);
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
	public void moveDownXzzx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Xzzx xzzx = (Xzzx) session.load(Xzzx.class, id);
			if (xzzx.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = xzzx.getPaixu();
			String hql = "update Xzzx gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			xzzx.setPaixu(oldPaixu - 1);
			updateXzzx(xzzx);
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
	public void moveUpXzzx(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Xzzx xzzx = (Xzzx) session.load(Xzzx.class, id);
			int oldPaixu = xzzx.getPaixu();
			String isMax = "select new Xzzx(jj.id) FROM Xzzx as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Xzzx> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Xzzx gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			xzzx.setPaixu(oldPaixu + 1);
			updateXzzx(xzzx);
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
	public void rePaixuXzzx() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Xzzx> xzzxs = allXzzxById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < xzzxs.size(); i++) {
				Xzzx temp = xzzxs.get(i);
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

	public List<Xzzx> pageXzzx(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Xzzx> xzzxs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Xzzx(jj.id, jj.biaoti, jj.dyts, jj.fujian,	jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Xzzx as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			xzzxs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return xzzxs;
	}

	@Test
	public void testXzzx() {
		for (int i = 1; i < 78; i++) {
			Xzzx xzzx = new Xzzx();
			xzzx.setBiaoti("No." + i + "Xzzx");
			addXzzx(xzzx);
		}
	}

}
