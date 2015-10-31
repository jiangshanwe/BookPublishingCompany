package com.langwen.admin.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Qx;
import com.langwen.util.MD5Util;
import com.ssh.HibernateSessionFactory;

public class GuanliyuanDao {
	public void addGuanliyuan(Guanliyuan guanliyuan) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			guanliyuan.setMima(MD5Util.getMD5(guanliyuan.getMima()
					+ "BBC99F82DA25C6004229639D455BAC21"));
			session.save(guanliyuan);
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

	public void deleteGuanliyuan(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Guanliyuan temp = (Guanliyuan) session.load(Guanliyuan.class, id);
			session.delete(temp);
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

	public void updateGuanliyuan(Guanliyuan guanliyuan) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (guanliyuan.getMima().length() != 32) {
				guanliyuan.setMima(MD5Util.getMD5(guanliyuan.getMima()
						+ "BBC99F82DA25C6004229639D455BAC21"));
			}
			session.update(guanliyuan);
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

	public Guanliyuan loadGuanliyuan(Integer id) {
		Guanliyuan guanliyuan = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			guanliyuan = (Guanliyuan) session.get(Guanliyuan.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return guanliyuan;
	}

	public List allGuanliyuan() {
		List glys = new ArrayList<Guanliyuan>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "select a from Guanliyuan as a order by a.id";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			glys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(glys)) {
				Hibernate.initialize(glys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return glys;
	}

	public Guanliyuan guanliyuanLogin(String gonghao, String mima) {
		Guanliyuan gly = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "from Guanliyuan gly where gly.gonghao='" + gonghao
					+ "' and gly.mima='"
					+ MD5Util.getMD5(mima + "BBC99F82DA25C6004229639D455BAC21")
					+ "'";
			Query query = session.createQuery(hql);
			List<Guanliyuan> glys = query.list();
			if (glys != null && glys.size() == 1) {
				gly = glys.get(0);
			}
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return gly;
	}

	public boolean checkGuanliyuanQx(Integer glyid, Integer qxid) {
		boolean result = false;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			String hql = "from Glyqx gq where gq.glyid=" + glyid
					+ " and gq.qxid=" + qxid;
			Query query = session.createQuery(hql);
			List<Guanliyuan> glys = query.list();
			if (glys != null && glys.size() == 1) {
				result = true;
			}
			transaction = session.beginTransaction();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("【系统错误】在持久化对象时出错，原因是：");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return result;
	}

	// public List<Qx> loadGlyQx(Integer id) {
	// Guanliyuan guanliyuan = loadGuanliyuan(id);
	// Session session = HibernateSessionFactory.getSession();
	// Transaction transaction = null;
	// try {
	// transaction = session.beginTransaction();
	// guanliyuan = (Guanliyuan) session.get(Guanliyuan.class, id);
	// transaction.commit();
	// } catch (Exception e) {
	// if (transaction != null) {
	// System.out.println("【系统错误】在持久化对象时出错，原因是：");
	// e.printStackTrace();
	// }
	// } finally {
	// HibernateSessionFactory.closeSession();
	// }
	// return guanliyuan;
	// }

	@Test
	public void testAdd() {
		// Guanliyuan guanliyuan = new Guanliyuan("2010", "123456", "ggaa", 1);
		// this.addGuanliyuan(guanliyuan);
		// this.deleteGuanliyuan(4);
//		QxDao qxDao = new QxDao();
//		Set<Qx> nowqx = new HashSet<Qx>();
//		nowqx.add(qxDao.loadQx(4));
//		nowqx.add(qxDao.loadQx(5));
//		loadGuanliyuan(2).setQxs(nowqx);
		System.out.println(loadGuanliyuan(1).getQxs());
	}
}
