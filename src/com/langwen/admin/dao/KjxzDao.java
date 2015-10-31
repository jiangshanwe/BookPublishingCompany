package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Kjxz;
import com.ssh.HibernateSessionFactory;

public class KjxzDao {
	public void addKjxz(Kjxz kjxz) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			kjxz.setTianjiashijian(curdate);
			String hql = "select count(*) from Kjxz gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			kjxz.setPaixu(paixu);
			session.save(kjxz);
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

	public void deleteKjxz(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Kjxz kjxz = (Kjxz) session.load(Kjxz.class, id);
			int paixu = kjxz.getPaixu();
			session.delete(kjxz);
			String hql = "update Kjxz gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Kjxz loadKjxz(Integer id) {
		Kjxz kjxz = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			kjxz = (Kjxz) session.get(Kjxz.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return kjxz;
	}

	public List<Kjxz> allKjxz() {
		List<Kjxz> kjxzs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Kjxz as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Kjxz(jj.id, jj.biaoti, jj.dyts, jj.fujian,	jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Kjxz as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			kjxzs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(kjxzs)) {
				Hibernate.initialize(kjxzs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return kjxzs;
	}

	public List<Kjxz> allKjxzById() {
		List<Kjxz> kjxzs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Kjxz as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Kjxz as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			kjxzs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(kjxzs)) {
				Hibernate.initialize(kjxzs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return kjxzs;
	}

	public void updateKjxz(Kjxz kjxz) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (kjxz.getPaixu() == null) {
				kjxz.setPaixu(loadKjxz(kjxz.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(kjxz);
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
	public void moveDownKjxz(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Kjxz kjxz = (Kjxz) session.load(Kjxz.class, id);
			if (kjxz.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = kjxz.getPaixu();
			String hql = "update Kjxz gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			kjxz.setPaixu(oldPaixu - 1);
			updateKjxz(kjxz);
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
	public void moveUpKjxz(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Kjxz kjxz = (Kjxz) session.load(Kjxz.class, id);
			int oldPaixu = kjxz.getPaixu();
			String isMax = "select new Kjxz(jj.id) FROM Kjxz as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Kjxz> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Kjxz gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			kjxz.setPaixu(oldPaixu + 1);
			updateKjxz(kjxz);
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
	public void rePaixuKjxz() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Kjxz> kjxzs = allKjxzById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < kjxzs.size(); i++) {
				Kjxz temp = kjxzs.get(i);
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

	public List<Kjxz> pageKjxz(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Kjxz> kjxzs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Kjxz(jj.id, jj.biaoti, jj.dyts, jj.fujian,	jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Kjxz as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			kjxzs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return kjxzs;
	}

	@Test
	public void testKjxz() {
		for (int i = 1; i < 77; i++) {
			Kjxz kjxz = new Kjxz();
			kjxz.setBiaoti("No." + i + "Kjxz");
			addKjxz(kjxz);
		}
	}

}
