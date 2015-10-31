package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Zsgzjy;
import com.ssh.HibernateSessionFactory;

public class ZsgzjyDao {
	public void addZsgzjy(Zsgzjy zsgzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zsgzjy.setTianjiashijian(curdate);
			String hql = "select count(*) from Zsgzjy gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			zsgzjy.setPaixu(paixu);
			session.save(zsgzjy);
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

	public void deleteZsgzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgzjy zsgzjy = (Zsgzjy) session.load(Zsgzjy.class, id);
			int paixu = zsgzjy.getPaixu();
			session.delete(zsgzjy);
			String hql = "update Zsgzjy gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Zsgzjy loadZsgzjy(Integer id) {
		Zsgzjy zsgzjy = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zsgzjy = (Zsgzjy) session.get(Zsgzjy.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgzjy;
	}

	public List<Zsgzjy> allZsgzjy() {
		List<Zsgzjy> zsgzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgzjys)) {
				Hibernate.initialize(zsgzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgzjys;
	}

	public List<Zsgzjy> allZsgzjyById() {
		List<Zsgzjy> zsgzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zsgzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zsgzjy as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zsgzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zsgzjys)) {
				Hibernate.initialize(zsgzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgzjys;
	}

	public void updateZsgzjy(Zsgzjy zsgzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (zsgzjy.getPaixu() == null) {
				zsgzjy.setPaixu(loadZsgzjy(zsgzjy.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(zsgzjy);
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
	public void moveDownZsgzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgzjy zsgzjy = (Zsgzjy) session.load(Zsgzjy.class, id);
			if (zsgzjy.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = zsgzjy.getPaixu();
			String hql = "update Zsgzjy gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			zsgzjy.setPaixu(oldPaixu - 1);
			updateZsgzjy(zsgzjy);
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
	public void moveUpZsgzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zsgzjy zsgzjy = (Zsgzjy) session.load(Zsgzjy.class, id);
			int oldPaixu = zsgzjy.getPaixu();
			String isMax = "select new Zsgzjy(jj.id) FROM Zsgzjy as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Zsgzjy> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Zsgzjy gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			zsgzjy.setPaixu(oldPaixu + 1);
			updateZsgzjy(zsgzjy);
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
	public void rePaixuZsgzjy() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Zsgzjy> zsgzjys = allZsgzjyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < zsgzjys.size(); i++) {
				Zsgzjy temp = zsgzjys.get(i);
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

	public List<Zsgzjy> pageZsgzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zsgzjy> zsgzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Zsgzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			zsgzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zsgzjys;
	}

	@Test
	public void testZsgzjy() {
		for (int i = 1; i < 100; i++) {
			Zsgzjy zsgzjy = new Zsgzjy();
			zsgzjy.setShuming("No." + i + "Zsgzjy");
			addZsgzjy(zsgzjy);
		}
	}

}
