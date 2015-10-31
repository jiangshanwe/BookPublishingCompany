package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Hydt;
import com.ssh.HibernateSessionFactory;

public class HydtDao {
	public void addHydt(Hydt hydt) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			hydt.setTianjiashijian(curdate);
			String hql = "select count(*) from Hydt gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			hydt.setPaixu(paixu);
			session.save(hydt);
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

	public void deleteHydt(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hydt hydt = (Hydt) session.load(Hydt.class, id);
			int paixu = hydt.getPaixu();
			session.delete(hydt);
			String hql = "update Hydt gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Hydt loadHydt(Integer id) {
		Hydt hydt = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			hydt = (Hydt) session.get(Hydt.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hydt;
	}

	public List<Hydt> allHydt() {
		List<Hydt> hydts = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Hydt as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Hydt(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Hydt as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			hydts = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(hydts)) {
				Hibernate.initialize(hydts);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hydts;
	}

	public List<Hydt> allHydtById() {
		List<Hydt> hydts = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Hydt as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Hydt as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			hydts = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(hydts)) {
				Hibernate.initialize(hydts);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hydts;
	}

	public void updateHydt(Hydt hydt) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (hydt.getPaixu() == null) {
				hydt.setPaixu(loadHydt(hydt.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(hydt);
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
	public void moveDownHydt(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hydt hydt = (Hydt) session.load(Hydt.class, id);
			if (hydt.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = hydt.getPaixu();
			String hql = "update Hydt gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			hydt.setPaixu(oldPaixu - 1);
			updateHydt(hydt);
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
	public void moveUpHydt(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Hydt hydt = (Hydt) session.load(Hydt.class, id);
			int oldPaixu = hydt.getPaixu();
			String isMax = "select new Hydt(jj.id) FROM Hydt as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Hydt> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Hydt gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			hydt.setPaixu(oldPaixu + 1);
			updateHydt(hydt);
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
	public void rePaixuHydt() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Hydt> hydts = allHydtById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < hydts.size(); i++) {
				Hydt temp = hydts.get(i);
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

	public List<Hydt> pageHydt(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Hydt> hydts = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Hydt(jj.id, jj.biaoti, jj.tianjiaren, jj.tianjiashijian, jj.ywlj, jj.gengxinren,jj.gengxinshijian, jj.leixing, jj.paixu) from Hydt as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			hydts = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return hydts;
	}

	@Test
	public void testHydt() {
		for (int i = 1; i < 112; i++) {
			Hydt hydt = new Hydt();
			hydt.setBiaoti("No." + i + " Hydt");
			if (i % 3 == 0) {
				hydt.setLeixing(1);
			} else if (i % 3 == 1) {
				hydt.setLeixing(2);
			} else {
				hydt.setLeixing(3);
			}
			addHydt(hydt);
		}
	}
}
