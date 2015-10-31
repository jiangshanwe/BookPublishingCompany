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
import com.langwen.admin.bean.Zzjy;
import com.ssh.HibernateSessionFactory;

public class ZzjyDao {
	public void addZzjy(Zzjy zzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zzjy.setTianjiashijian(curdate);
			if (zzjy.getZhiding() == null) {
				zzjy.setZhiding(0);
			}
			zzjy.setType("zzjy");
			String hql = "select count(*) from Zzjy gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			zzjy.setPaixu(paixu);
			session.save(zzjy);
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

	public void deleteZzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zzjy zzjy = (Zzjy) session.load(Zzjy.class, id);
			int paixu = zzjy.getPaixu();
			session.delete(zzjy);
			String hql = "update Zzjy gw set gw.paixu = gw.paixu -1 where gw.paixu > "
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

	public Zzjy loadZzjy(Integer id) {
		Zzjy zzjy = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			zzjy = (Zzjy) session.get(Zzjy.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zzjy;
	}

	public List<Zzjy> allZzjy() {
		List<Zzjy> zzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Zzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zzjys)) {
				Hibernate.initialize(zzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zzjys;
	}

	public List<Zzjy> allZzjyById() {
		List<Zzjy> zzjys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Zzjy as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Zzjy as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			zzjys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(zzjys)) {
				Hibernate.initialize(zzjys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zzjys;
	}

	public void updateZzjy(Zzjy zzjy) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (zzjy.getZhiding() == null) {
				zzjy.setZhiding(0);
			}
			if (zzjy.getPaixu() == null) {
				zzjy.setPaixu(loadZzjy(zzjy.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(zzjy);
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
	public void moveDownZzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zzjy zzjy = (Zzjy) session.load(Zzjy.class, id);
			if (zzjy.getPaixu() == 1) {
				return; // 已经排在最前面
			}
			int oldPaixu = zzjy.getPaixu();
			String hql = "update Zzjy gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			zzjy.setPaixu(oldPaixu - 1);
			updateZzjy(zzjy);
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
	public void moveUpZzjy(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Zzjy zzjy = (Zzjy) session.load(Zzjy.class, id);
			int oldPaixu = zzjy.getPaixu();
			String isMax = "select new Zzjy(jj.id) FROM Zzjy as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Zzjy> gl = quMax.list();
			if (gl.size() == 0) {
				return; // 已经排在最后面
			}
			String hql = "update Zzjy gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			zzjy.setPaixu(oldPaixu + 1);
			updateZzjy(zzjy);
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

	public List<Zzjy> pageZzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zzjy> zzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Zzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			zzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zzjys;
	}

	// 重新按照添加时间排序
	public void rePaixuZzjy() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Zzjy> zzjys = allZzjyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < zzjys.size(); i++) {
				Zzjy temp = zzjys.get(i);
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

	public List<Zzjy> pagefZzjy(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Zzjy> zzjys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Zzjy as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			zzjys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return zzjys;
	}
	
	@Test
	public void testZzjy() {
		Zzjy zzjy = new Zzjy();
		// zzjy.setShuming("Head first C++");
		zzjy.setFenlei("计算机类");
		zzjy.setZuozhe("Bruce Eckel");
		zzjy.setIsbn("439123823");
		zzjy.setChubanshe("机械工业出版社");
		zzjy.setTianjiaren("Harold");
		for (int i = 1; i < 178; i++) {
			zzjy.setShuming("No. " + i + " Book");
			addZzjy(zzjy);
		}
		// zzjy.setId(9);
		// updateZzjy(zzjy);
		// deleteZzjy(6);
		// System.out.println(loadZzjy(8));
		// moveUpZzjy(7);
		// moveDownZzjy(11);
		// System.out.println(allZzjy());
		// rePaixuZzjy();
		// System.out.println(allZzjy());
		// System.out.println((double) 31 / (double) 10 > (31 / 10) ? 31 / 10 +
		// 1
		// : 31 / 10);
	}
}
