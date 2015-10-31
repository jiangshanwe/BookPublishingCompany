package com.langwen.admin.dao;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.langwen.admin.bean.Gdlg;
import com.ssh.HibernateSessionFactory;

public class GdlgDao {
	public void addGdlg(Gdlg gdlg) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gdlg.setTianjiashijian(curdate);
			if (gdlg.getZhiding() == null) {
				gdlg.setZhiding(0);
			}
			gdlg.setType("gdlg");
			String hql = "select count(*) from Gdlg gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			gdlg.setPaixu(paixu);
			session.save(gdlg);
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

	public void deleteGdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdlg gdlg = (Gdlg) session.load(Gdlg.class, id);
			int paixu = gdlg.getPaixu();
			session.delete(gdlg);
			String hql = "update Gdlg gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Gdlg loadGdlg(Integer id) {
		Gdlg gdlg = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			gdlg = (Gdlg) session.get(Gdlg.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdlg;
	}

	public List<Gdlg> allGdlg() {
		List<Gdlg> gdlgs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gdlg as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdlg as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gdlgs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gdlgs)) {
				Hibernate.initialize(gdlgs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdlgs;
	}

	public List<Gdlg> allGdlgById() {
		List<Gdlg> gdlgs = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gdlg as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Gdlg as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gdlgs = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gdlgs)) {
				Hibernate.initialize(gdlgs);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdlgs;
	}

	public void updateGdlg(Gdlg gdlg) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (gdlg.getZhiding() == null) {
				gdlg.setZhiding(0);
			}
			if (gdlg.getPaixu() == null) {
				gdlg.setPaixu(loadGdlg(gdlg.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(gdlg);
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
	public void moveDownGdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdlg gdlg = (Gdlg) session.load(Gdlg.class, id);
			if (gdlg.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = gdlg.getPaixu();
			String hql = "update Gdlg gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			gdlg.setPaixu(oldPaixu - 1);
			updateGdlg(gdlg);
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
	public void moveUpGdlg(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdlg gdlg = (Gdlg) session.load(Gdlg.class, id);
			int oldPaixu = gdlg.getPaixu();
			String isMax = "select new Gdlg(jj.id) FROM Gdlg as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Gdlg> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Gdlg gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			gdlg.setPaixu(oldPaixu + 1);
			updateGdlg(gdlg);
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
	public void rePaixuGdlg() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Gdlg> gdlgs = allGdlgById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < gdlgs.size(); i++) {
				Gdlg temp = gdlgs.get(i);
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

	public List<Gdlg> pageGdlg(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gdlg> gdlgs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdlg as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			gdlgs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdlgs;
	}

	public List<Gdlg> pagefGdlg(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gdlg> gdlgs = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdlg as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			gdlgs = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdlgs;
	}

	@Test
	public void testGdlg() {
		Gdlg gdlg = new Gdlg();
		gdlg.setFenlei("计算机类");
		gdlg.setZuozhe("Harold");
		gdlg.setIsbn("439123823");
		gdlg.setChubanshe("清华大学出版社");
		for (int i = 68; i < 88; i++) {
			gdlg.setShuming("No." + i + "Java" + "Book");
			addGdlg(gdlg);
		}
		// addGdlg(gdlg);
		// gdlg.setId(3);
		// updateGdlg(gdlg);
		// deleteGdlg(2);
		// System.out.println(loadGdlg(4));
		// moveUpGdlg(1);
		// moveDownGdlg(7);
		// System.out.println(allGdlg());
		// rePaixuGdlg();
		// System.out.println(allGdlg());
	}
}
