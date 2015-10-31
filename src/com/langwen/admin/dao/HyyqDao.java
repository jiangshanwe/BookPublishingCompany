package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Hyyq;
import com.ssh.HibernateSessionFactory;

public class HyyqDao {
	public void addHyyq(Hyyq hyyq) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			hyyq.setTianjiashijian(curdate);
			String hql = "select count(*) from Hyyq gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			hyyq.setPaixu(paixu);
			session.save(hyyq);
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

	public void deleteHyyq(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hyyq hyyq = (Hyyq) session.load(Hyyq.class, id);
			int paixu = hyyq.getPaixu();
			session.delete(hyyq);
			String hql = "update Hyyq gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Hyyq loadHyyq(Integer id) {
		Hyyq hyyq = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			hyyq = (Hyyq) session.get(Hyyq.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hyyq;
	}

	public List<Hyyq> allHyyq() {
		List<Hyyq> hyyqs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Hyyq as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Hyyq(jj.id, jj.biaoti, jj.fujian, jj.tianjiaren,jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian,jj.paixu) from Hyyq as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			hyyqs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(hyyqs)) {
				Hibernate.initialize(hyyqs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hyyqs;
	}

	public List<Hyyq> allHyyqById() {
		List<Hyyq> hyyqs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Hyyq as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Hyyq as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			hyyqs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(hyyqs)) {
				Hibernate.initialize(hyyqs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hyyqs;
	}

	public void updateHyyq(Hyyq hyyq) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (hyyq.getPaixu() == null) {
				hyyq.setPaixu(loadHyyq(hyyq.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(hyyq);
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

	public void moveDownHyyq(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hyyq hyyq = (Hyyq) session.load(Hyyq.class, id);
			if (hyyq.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = hyyq.getPaixu();
			String hql = "update Hyyq gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			hyyq.setPaixu(oldPaixu - 1);
			updateHyyq(hyyq);
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

	public void moveUpHyyq(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hyyq hyyq = (Hyyq) session.load(Hyyq.class, id);
			int oldPaixu = hyyq.getPaixu();
			String isMax = "select new Hyyq(jj.id) FROM Hyyq as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Hyyq> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Hyyq gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			hyyq.setPaixu(oldPaixu + 1);
			updateHyyq(hyyq);
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
	public void rePaixuHyyq() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Hyyq> hyyqs = allHyyqById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < hyyqs.size(); i++) {
				Hyyq temp = hyyqs.get(i);
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

	public List<Hyyq> pageHyyq(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Hyyq> hyyqs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Hyyq(jj.id, jj.biaoti, jj.fujian, jj.tianjiaren,jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian,jj.paixu) from Hyyq as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			hyyqs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hyyqs;
	}

	@Test
	public void testHyyq() {
		for (int i = 1; i < 78; i++) {
			Hyyq hyyq = new Hyyq();
			hyyq.setBiaoti("No." + i + " Hyyq");
			hyyq.setFujian("No." + i + ".txt");
			hyyq.setNeirong("No." + i + "Context");
			addHyyq(hyyq);
		}
	}

}
