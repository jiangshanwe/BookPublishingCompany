package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Zszzjy;
import com.ssh.HibernateSessionFactory;

public class ZszzjyDao {
	public void addZszzjy(Zszzjy zszzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zszzjy.setTianjiashijian(curdate);
			String hql = "select count(*) from Zszzjy gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			zszzjy.setPaixu(paixu);
			session.save(zszzjy);
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

	public void deleteZszzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zszzjy zszzjy = (Zszzjy) session.load(Zszzjy.class, id);
			int paixu = zszzjy.getPaixu();
			session.delete(zszzjy);
			String hql = "update Zszzjy gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Zszzjy loadZszzjy(Integer id) {
		Zszzjy zszzjy = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zszzjy = (Zszzjy) session.get(Zszzjy.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zszzjy;
	}

	public List<Zszzjy> allZszzjy() {
		List<Zszzjy> zszzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zszzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zszzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zszzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zszzjys)) {
				Hibernate.initialize(zszzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zszzjys;
	}

	public List<Zszzjy> allZszzjyById() {
		List<Zszzjy> zszzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zszzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zszzjy as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zszzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zszzjys)) {
				Hibernate.initialize(zszzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zszzjys;
	}

	public void updateZszzjy(Zszzjy zszzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (zszzjy.getPaixu() == null) {
				zszzjy.setPaixu(loadZszzjy(zszzjy.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(zszzjy);
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
	public void moveDownZszzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zszzjy zszzjy = (Zszzjy) session.load(Zszzjy.class, id);
			if (zszzjy.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = zszzjy.getPaixu();
			String hql = "update Zszzjy gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			zszzjy.setPaixu(oldPaixu - 1);
			updateZszzjy(zszzjy);
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
	public void moveUpZszzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zszzjy zszzjy = (Zszzjy) session.load(Zszzjy.class, id);
			int oldPaixu = zszzjy.getPaixu();
			String isMax = "select new Zszzjy(jj.id) FROM Zszzjy as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Zszzjy> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Zszzjy gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			zszzjy.setPaixu(oldPaixu + 1);
			updateZszzjy(zszzjy);
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
	public void rePaixuZszzjy() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Zszzjy> zszzjys = allZszzjyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < zszzjys.size(); i++) {
				Zszzjy temp = zszzjys.get(i);
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

	public List<Zszzjy> pageZszzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zszzjy> zszzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Zszzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			zszzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zszzjys;
	}

	@Test
	public void testZszzjy() {
		for (int i = 1; i < 100; i++) {
			Zszzjy zszzjy = new Zszzjy();
			zszzjy.setShuming("No." + i + "Zszzjy");
			addZszzjy(zszzjy);
		}
	}

}
