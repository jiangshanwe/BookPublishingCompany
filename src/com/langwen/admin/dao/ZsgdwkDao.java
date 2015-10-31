package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Zsgdwk;
import com.ssh.HibernateSessionFactory;

public class ZsgdwkDao {
	public void addZsgdwk(Zsgdwk zsgdwk) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zsgdwk.setTianjiashijian(curdate);
			String hql = "select count(*) from Zsgdwk gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			zsgdwk.setPaixu(paixu);
			session.save(zsgdwk);
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

	public void deleteZsgdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdwk zsgdwk = (Zsgdwk) session.load(Zsgdwk.class, id);
			int paixu = zsgdwk.getPaixu();
			session.delete(zsgdwk);
			String hql = "update Zsgdwk gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Zsgdwk loadZsgdwk(Integer id) {
		Zsgdwk zsgdwk = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zsgdwk = (Zsgdwk) session.get(Zsgdwk.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdwk;
	}

	public List<Zsgdwk> allZsgdwk() {
		List<Zsgdwk> zsgdwks = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgdwk as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgdwk as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgdwks = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgdwks)) {
				Hibernate.initialize(zsgdwks);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdwks;
	}

	public List<Zsgdwk> allZsgdwkById() {
		List<Zsgdwk> zsgdwks = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgdwk as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgdwk as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgdwks = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgdwks)) {
				Hibernate.initialize(zsgdwks);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdwks;
	}

	public void updateZsgdwk(Zsgdwk zsgdwk) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (zsgdwk.getPaixu() == null) {
				zsgdwk.setPaixu(loadZsgdwk(zsgdwk.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(zsgdwk);
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
	public void moveDownZsgdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdwk zsgdwk = (Zsgdwk) session.load(Zsgdwk.class, id);
			if (zsgdwk.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = zsgdwk.getPaixu();
			String hql = "update Zsgdwk gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			zsgdwk.setPaixu(oldPaixu - 1);
			updateZsgdwk(zsgdwk);
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
	public void moveUpZsgdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgdwk zsgdwk = (Zsgdwk) session.load(Zsgdwk.class, id);
			int oldPaixu = zsgdwk.getPaixu();
			String isMax = "select new Zsgdwk(jj.id) FROM Zsgdwk as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Zsgdwk> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Zsgdwk gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			zsgdwk.setPaixu(oldPaixu + 1);
			updateZsgdwk(zsgdwk);
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
	public void rePaixuZsgdwk() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Zsgdwk> zsgdwks = allZsgdwkById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < zsgdwks.size(); i++) {
				Zsgdwk temp = zsgdwks.get(i);
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

	public List<Zsgdwk> pageZsgdwk(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zsgdwk> zsgdwks = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Zsgdwk as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			zsgdwks = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgdwks;
	}

	@Test
	public void testZsgdwk() {
		for (int i = 1; i < 100; i++) {
			Zsgdwk zsgdwk = new Zsgdwk();
			zsgdwk.setShuming("No." + i + "Zsgdwk");
			addZsgdwk(zsgdwk);
		}
	}

}
