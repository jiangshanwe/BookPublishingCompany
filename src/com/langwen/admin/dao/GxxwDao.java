package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Gxxw;
import com.ssh.HibernateSessionFactory;

public class GxxwDao {
	public void addGxxw(Gxxw gxxw) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gxxw.setTianjiashijian(curdate);
			String hql = "select count(*) from Gxxw gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			gxxw.setPaixu(paixu);
			session.save(gxxw);
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

	public void deleteGxxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gxxw gxxw = (Gxxw) session.load(Gxxw.class, id);
			int paixu = gxxw.getPaixu();
			session.delete(gxxw);
			String hql = "update Gxxw gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Gxxw loadGxxw(Integer id) {
		Gxxw gxxw = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			gxxw = (Gxxw) session.get(Gxxw.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gxxw;
	}

	public List<Gxxw> allGxxw() {
		List<Gxxw> gxxws = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gxxw as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Gxxw(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Gxxw as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gxxws = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gxxws)) {
				Hibernate.initialize(gxxws);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gxxws;
	}

	public List<Gxxw> allGxxwById() {
		List<Gxxw> gxxws = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gxxw as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Gxxw as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gxxws = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gxxws)) {
				Hibernate.initialize(gxxws);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gxxws;
	}

	public void updateGxxw(Gxxw gxxw) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (gxxw.getPaixu() == null) {
				gxxw.setPaixu(loadGxxw(gxxw.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(gxxw);
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
	public void moveDownGxxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gxxw gxxw = (Gxxw) session.load(Gxxw.class, id);
			if (gxxw.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = gxxw.getPaixu();
			String hql = "update Gxxw gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			gxxw.setPaixu(oldPaixu - 1);
			updateGxxw(gxxw);
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
	public void moveUpGxxw(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gxxw gxxw = (Gxxw) session.load(Gxxw.class, id);
			int oldPaixu = gxxw.getPaixu();
			String isMax = "select new Gxxw(jj.id) FROM Gxxw as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Gxxw> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Gxxw gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			gxxw.setPaixu(oldPaixu + 1);
			updateGxxw(gxxw);
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
	public void rePaixuGxxw() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Gxxw> gxxws = allGxxwById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < gxxws.size(); i++) {
				Gxxw temp = gxxws.get(i);
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

	public List<Gxxw> pageGxxw(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gxxw> gxxws = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gxxw(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Gxxw as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			gxxws = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gxxws;
	}

	@Test
	public void testGxxw() {
		for (int i = 1; i < 122; i++) {
			Gxxw gxxw = new Gxxw();
			gxxw.setBiaoti("No." + i + " Gxxw");
			if (i % 3 == 0) {
				gxxw.setLeixing(1);
			} else if (i % 3 == 1) {
				gxxw.setLeixing(2);
			} else {
				gxxw.setLeixing(3);
			}
			addGxxw(gxxw);
		}
	}
}
