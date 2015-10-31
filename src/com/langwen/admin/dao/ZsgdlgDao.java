package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Zsgdlg;
import com.ssh.HibernateSessionFactory;

public class ZsgdlgDao {
	public void addZsgdlg(Zsgdlg zsgdlg) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zsgdlg.setTianjiashijian(curdate);
			String hql = "select count(*) from Zsgdlg gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			zsgdlg.setPaixu(paixu);
			session.save(zsgdlg);
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

	public void deleteZsgdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdlg zsgdlg = (Zsgdlg) session.load(Zsgdlg.class, id);
			int paixu = zsgdlg.getPaixu();
			session.delete(zsgdlg);
			String hql = "update Zsgdlg gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Zsgdlg loadZsgdlg(Integer id) {
		Zsgdlg zsgdlg = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zsgdlg = (Zsgdlg) session.get(Zsgdlg.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdlg;
	}

	public List<Zsgdlg> allZsgdlg() {
		List<Zsgdlg> zsgdlgs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgdlg as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgdlg as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgdlgs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgdlgs)) {
				Hibernate.initialize(zsgdlgs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdlgs;
	}

	public List<Zsgdlg> allZsgdlgById() {
		List<Zsgdlg> zsgdlgs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgdlg as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgdlg as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgdlgs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgdlgs)) {
				Hibernate.initialize(zsgdlgs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdlgs;
	}

	public void updateZsgdlg(Zsgdlg zsgdlg) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (zsgdlg.getPaixu() == null) {
				zsgdlg.setPaixu(loadZsgdlg(zsgdlg.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(zsgdlg);
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
	public void moveDownZsgdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdlg zsgdlg = (Zsgdlg) session.load(Zsgdlg.class, id);
			if (zsgdlg.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = zsgdlg.getPaixu();
			String hql = "update Zsgdlg gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			zsgdlg.setPaixu(oldPaixu - 1);
			updateZsgdlg(zsgdlg);
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
	public void moveUpZsgdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdlg zsgdlg = (Zsgdlg) session.load(Zsgdlg.class, id);
			int oldPaixu = zsgdlg.getPaixu();
			String isMax = "select new Zsgdlg(jj.id) FROM Zsgdlg as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Zsgdlg> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Zsgdlg gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			zsgdlg.setPaixu(oldPaixu + 1);
			updateZsgdlg(zsgdlg);
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
	public void rePaixuZsgdlg() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Zsgdlg> zsgdlgs = allZsgdlgById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < zsgdlgs.size(); i++) {
				Zsgdlg temp = zsgdlgs.get(i);
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

	public List<Zsgdlg> pageZsgdlg(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zsgdlg> zsgdlgs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Zsgdlg as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			zsgdlgs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdlgs;
	}

	@Test
	public void testZsgdlg() {
		for (int i = 1; i < 100; i++) {
			Zsgdlg zsgdlg = new Zsgdlg();
			zsgdlg.setShuming("No." + i + "Zsgdlg");
			addZsgdlg(zsgdlg);
		}
	}

}
