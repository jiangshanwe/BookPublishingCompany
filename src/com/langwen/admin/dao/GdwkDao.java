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
import com.langwen.admin.bean.Gdwk;
import com.ssh.HibernateSessionFactory;

public class GdwkDao {
	public void addGdwk(Gdwk gdwk) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gdwk.setTianjiashijian(curdate);
			if (gdwk.getZhiding() == null) {
				gdwk.setZhiding(0);
			}
			gdwk.setType("gdwk");
			String hql = "select count(*) from Gdwk gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			gdwk.setPaixu(paixu);
			session.save(gdwk);
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

	public void deleteGdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdwk gdwk = (Gdwk) session.load(Gdwk.class, id);
			int paixu = gdwk.getPaixu();
			session.delete(gdwk);
			String hql = "update Gdwk gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Gdwk loadGdwk(Integer id) {
		Gdwk gdwk = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			gdwk = (Gdwk) session.get(Gdwk.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdwk;
	}

	public List<Gdwk> allGdwk() {
		List<Gdwk> gdwks = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gdwk as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdwk as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gdwks = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gdwks)) {
				Hibernate.initialize(gdwks);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdwks;
	}

	public List<Gdwk> allGdwkById() {
		List<Gdwk> gdwks = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Gdwk as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Gdwk as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			gdwks = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(gdwks)) {
				Hibernate.initialize(gdwks);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdwks;
	}

	public void updateGdwk(Gdwk gdwk) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (gdwk.getZhiding() == null) {
				gdwk.setZhiding(0);
			}
			if (gdwk.getPaixu() == null) {
				gdwk.setPaixu(loadGdwk(gdwk.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(gdwk);
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
	public void moveDownGdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdwk gdwk = (Gdwk) session.load(Gdwk.class, id);
			if (gdwk.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = gdwk.getPaixu();
			String hql = "update Gdwk gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			gdwk.setPaixu(oldPaixu - 1);
			updateGdwk(gdwk);
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
	public void moveUpGdwk(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Gdwk gdwk = (Gdwk) session.load(Gdwk.class, id);
			int oldPaixu = gdwk.getPaixu();
			String isMax = "select new Gdwk(jj.id) FROM Gdwk as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Gdwk> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Gdwk gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			gdwk.setPaixu(oldPaixu + 1);
			updateGdwk(gdwk);
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

	public List<Gdwk> pageGdwk(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gdwk> gdwks = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdwk as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			gdwks = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdwks;
	}

	// 重新按照添加时间排序
	public void rePaixuGdwk() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Gdwk> gdwks = allGdwkById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < gdwks.size(); i++) {
				Gdwk temp = gdwks.get(i);
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

	public List<Gdwk> pagefGdwk(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Gdwk> gdwks = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Gdwk as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			gdwks = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gdwks;
	}
	
	@Test
	public void testGdwk() {
		Gdwk gdwk = new Gdwk();
		// gdwk.setShuming("Head first C++");
		gdwk.setFenlei("计算机类");
		gdwk.setZuozhe("Harold");
		gdwk.setIsbn("439123823");
		gdwk.setChubanshe("机械工业出版社");
		for (int i = 31; i < 60; i++) {
			gdwk.setShuming("第" + i + "本");
			addGdwk(gdwk);
		}
		// gdwk.setId(9);
		// updateGdwk(gdwk);
		// deleteGdwk(6);
		// System.out.println(loadGdwk(8));
		// moveUpGdwk(7);
		// moveDownGdwk(11);
		// System.out.println(allGdwk());
		// rePaixuGdwk();
		// System.out.println(allGdwk());
		// System.out.println((double) 31 / (double) 10 > (31 / 10) ? 31 / 10 +
		// 1
		// : 31 / 10);
	}
}
