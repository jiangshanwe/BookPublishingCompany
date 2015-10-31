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
import com.langwen.admin.bean.Gzjy;
import com.ssh.HibernateSessionFactory;

public class GzjyDao {
	public void addGzjy(Gzjy gzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gzjy.setTianjiashijian(curdate);
			if (gzjy.getZhiding() == null) {
				gzjy.setZhiding(0);
			}
			gzjy.setType("gzjy");
			String hql = "select count(*) from Gzjy gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			gzjy.setPaixu(paixu);
			session.save(gzjy);
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

	public void deleteGzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gzjy gzjy = (Gzjy) session.load(Gzjy.class, id);
			int paixu = gzjy.getPaixu();
			session.delete(gzjy);
			String hql = "update Gzjy gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Gzjy loadGzjy(Integer id) {
		Gzjy gzjy = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			gzjy = (Gzjy) session.get(Gzjy.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gzjy;
	}

	public List<Gzjy> allGzjy() {
		List<Gzjy> gzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Gzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gzjys)) {
				Hibernate.initialize(gzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gzjys;
	}

	public List<Gzjy> allGzjyById() {
		List<Gzjy> gzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gzjy as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gzjys)) {
				Hibernate.initialize(gzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gzjys;
	}

	public void updateGzjy(Gzjy gzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (gzjy.getZhiding() == null) {
				gzjy.setZhiding(0);
			}
			if (gzjy.getPaixu() == null) {
				gzjy.setPaixu(loadGzjy(gzjy.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(gzjy);
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
	public void moveDownGzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gzjy gzjy = (Gzjy) session.load(Gzjy.class, id);
			if (gzjy.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = gzjy.getPaixu();
			String hql = "update Gzjy gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			gzjy.setPaixu(oldPaixu - 1);
			updateGzjy(gzjy);
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
	public void moveUpGzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gzjy gzjy = (Gzjy) session.load(Gzjy.class, id);
			int oldPaixu = gzjy.getPaixu();
			String isMax = "select new Gzjy(jj.id) FROM Gzjy as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Gzjy> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Gzjy gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			gzjy.setPaixu(oldPaixu + 1);
			updateGzjy(gzjy);
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

	public List<Gzjy> pageGzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gzjy> gzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			gzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gzjys;
	}

	// 重新按照添加时间排序
	public void rePaixuGzjy() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Gzjy> gzjys = allGzjyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < gzjys.size(); i++) {
				Gzjy temp = gzjys.get(i);
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

	public List<Gzjy> pagefGzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gzjy> gzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			gzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gzjys;
	}
	
	@Test
	public void testGzjy() {
		Gzjy gzjy = new Gzjy();
		// gzjy.setShuming("Head first C++");
		gzjy.setFenlei("计算机类");
		gzjy.setZuozhe("Bruce Eckel");
		gzjy.setIsbn("439123823");
		gzjy.setChubanshe("机械工业出版社");
		gzjy.setTianjiaren("Harold");
		for (int i = 1; i < 78; i++) {
			gzjy.setShuming("No. " + i + " Book");
			addGzjy(gzjy);
		}
		// gzjy.setId(9);
		// updateGzjy(gzjy);
		// deleteGzjy(6);
		// System.out.println(loadGzjy(8));
		// moveUpGzjy(7);
		// moveDownGzjy(11);
		// System.out.println(allGzjy());
		// rePaixuGzjy();
		// System.out.println(allGzjy());
		// System.out.println((double) 31 / (double) 10 > (31 / 10) ? 31 / 10 +
		// 1
		// : 31 / 10);
	}
}
