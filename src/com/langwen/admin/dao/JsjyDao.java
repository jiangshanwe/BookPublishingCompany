package com.langwen.admin.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Gdlg;
import com.langwen.admin.bean.Jsjy;
import com.ssh.HibernateSessionFactory;

public class JsjyDao {
	public void addJsjy(Jsjy jsjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			jsjy.setTianjiashijian(curdate);
			if (jsjy.getZhiding() == null) {
				jsjy.setZhiding(0);
			}
			jsjy.setType("jsjy");
			String hql = "select count(*) from Jsjy gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			jsjy.setPaixu(paixu);
			session.save(jsjy);
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

	public void deleteJsjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Jsjy jsjy = (Jsjy) session.load(Jsjy.class, id);
			int paixu = jsjy.getPaixu();
			session.delete(jsjy);
			String hql = "update Jsjy gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Jsjy loadJsjy(Integer id) {
		Jsjy jsjy = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			jsjy = (Jsjy) session.get(Jsjy.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return jsjy;
	}

	public List<Jsjy> allJsjy() {
		List<Jsjy> jsjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Jsjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Jsjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			jsjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(jsjys)) {
				Hibernate.initialize(jsjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return jsjys;
	}

	public List<Jsjy> allJsjyById() {
		List<Jsjy> jsjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Jsjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Jsjy as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			jsjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(jsjys)) {
				Hibernate.initialize(jsjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return jsjys;
	}

	public void updateJsjy(Jsjy jsjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (jsjy.getZhiding() == null) {
				jsjy.setZhiding(0);
			}
			if (jsjy.getPaixu() == null) {
				jsjy.setPaixu(loadJsjy(jsjy.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(jsjy);
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
	public void moveDownJsjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Jsjy jsjy = (Jsjy) session.load(Jsjy.class, id);
			if (jsjy.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = jsjy.getPaixu();
			String hql = "update Jsjy gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			jsjy.setPaixu(oldPaixu - 1);
			updateJsjy(jsjy);
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
	public void moveUpJsjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Jsjy jsjy = (Jsjy) session.load(Jsjy.class, id);
			int oldPaixu = jsjy.getPaixu();
			String isMax = "select new Jsjy(jj.id) FROM Jsjy as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Jsjy> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Jsjy gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			jsjy.setPaixu(oldPaixu + 1);
			updateJsjy(jsjy);
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

	public List<Jsjy> pageJsjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Jsjy> jsjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Jsjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			jsjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return jsjys;
	}

	// 重新按照添加时间排序
	public void rePaixuJsjy() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Jsjy> jsjys = allJsjyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < jsjys.size(); i++) {
				Jsjy temp = jsjys.get(i);
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

	public List<Jsjy> pagefJsjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Jsjy> jsjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Jsjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			jsjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return jsjys;
	}

	@Test
	public void testJsjy() {
		Jsjy jsjy = new Jsjy();
		// jsjy.setShuming("Head first C++");
		jsjy.setFenlei("中小学教师培训");
		jsjy.setFenlei("职业教师教师培训");
		jsjy.setZuozhe("Bruce Eckel");
		jsjy.setIsbn("439123823");
		jsjy.setChubanshe("机械工业出版社");
		jsjy.setTianjiaren("Harold");
		for (int i = 1; i < 178; i++) {
			jsjy.setShuming("No. " + i + " Book");
			addJsjy(jsjy);
		}
		// jsjy.setId(9);
		// updateJsjy(jsjy);
		// deleteJsjy(6);
		// System.out.println(loadJsjy(8));
		// moveUpJsjy(7);
		// moveDownJsjy(11);
		// System.out.println(allJsjy());
		// rePaixuJsjy();
		// System.out.println(allJsjy());
		// System.out.println((double) 31 / (double) 10 > (31 / 10) ? 31 / 10 +
		// 1
		// : 31 / 10);
	}
}
