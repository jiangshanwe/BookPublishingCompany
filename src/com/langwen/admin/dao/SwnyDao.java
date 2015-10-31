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
import com.langwen.admin.bean.Swny;
import com.ssh.HibernateSessionFactory;

public class SwnyDao {
	public void addSwny(Swny swny) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			swny.setTianjiashijian(curdate);
			if (swny.getZhiding() == null) {
				swny.setZhiding(0);
			}
			swny.setType("swny");
			String hql = "select count(*) from Swny gw";
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			Integer paixu = Integer.parseInt(count.toString()) + 1;
			swny.setPaixu(paixu);
			session.save(swny);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public void deleteSwny(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Swny swny = (Swny) session.load(Swny.class, id);
			int paixu = swny.getPaixu();
			session.delete(swny);
			String hql = "update Swny gw set gw.paixu = gw.paixu -1 where gw.paixu > "
					+ paixu;
			Query query = session.createQuery(hql);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Swny loadSwny(Integer id) {
		Swny swny = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			swny = (Swny) session.get(Swny.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return swny;
	}

	public List<Swny> allSwny() {
		List<Swny> swnys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Swny as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Swny as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			swnys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(swnys)) {
				Hibernate.initialize(swnys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return swnys;
	}

	public List<Swny> allSwnyById() {
		List<Swny> swnys = null;
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			// String hql =
			// "SELECT jj FROM Swny as jj WHERE jj.zhiding=1 ORDER BY zhidingshijian DESC";
			String hql = "from Swny as jj ORDER BY id ASC ";
			Query query = session.createQuery(hql);
			transaction = session.beginTransaction();
			swnys = query.list();
			transaction.commit();
			if (!Hibernate.isInitialized(swnys)) {
				Hibernate.initialize(swnys);
			}
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return swnys;
	}

	public void updateSwny(Swny swny) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (swny.getZhiding() == null) {
				swny.setZhiding(0);
			}
			if (swny.getPaixu() == null) {
				swny.setPaixu(loadSwny(swny.getId()).getPaixu());
			}
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			session.update(swny);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	// ��ָ����ͼ�飬���������ƶ�һλ
	public void moveDownSwny(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Swny swny = (Swny) session.load(Swny.class, id);
			if (swny.getPaixu() == 1) {
				return; // �Ѿ�������ǰ��
			}
			int oldPaixu = swny.getPaixu();
			String hql = "update Swny gw set gw.paixu = gw.paixu + 1 where gw.paixu = "
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
			swny.setPaixu(oldPaixu - 1);
			updateSwny(swny);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	// ��ָ����ͼ�飬���������ƶ�һλ
	public void moveUpSwny(Integer id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Swny swny = (Swny) session.load(Swny.class, id);
			int oldPaixu = swny.getPaixu();
			String isMax = "select new Swny(jj.id) FROM Swny as jj WHERE jj.paixu = "
					+ (oldPaixu + 1);
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				// transaction = session.beginTransaction();
			}
			Query quMax = session.createQuery(isMax);
			List<Swny> gl = quMax.list();
			if (gl.size() == 0) {
				return; // �Ѿ����������
			}
			String hql = "update Swny gw set gw.paixu = gw.paixu - 1 where gw.paixu = "
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
			swny.setPaixu(oldPaixu + 1);
			updateSwny(swny);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<Swny> pageSwny(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Swny> swnys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Swny as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(10 * (page - 1));
			query.setMaxResults(10);
			swnys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return swnys;
	}

	// ���°������ʱ������
	public void rePaixuSwny() {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			List<Swny> swnys = allSwnyById();
			if (!session.isOpen()) {
				session = HibernateSessionFactory.getSession();
				transaction = session.beginTransaction();
			}
			for (int i = 0; i < swnys.size(); i++) {
				Swny temp = swnys.get(i);
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
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<Swny> pagefSwny(int page) {
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		List<Swny> swnys = null;
		try {
			transaction = session.beginTransaction();
			String hql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu) from Swny as jj ORDER BY paixu DESC ";
			Query query = session.createQuery(hql);
			query.setFirstResult(12 * (page - 1));
			query.setMaxResults(12);
			swnys = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("��ϵͳ�����ڳ־û�����ʱ����ԭ���ǣ�");
				e.printStackTrace();
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return swnys;
	}
	
	@Test
	public void testSwny() {
		Swny swny = new Swny();
		// swny.setShuming("Head first C++");
		swny.setFenlei("�������");
		swny.setZuozhe("Harold");
		swny.setIsbn("439123823");
		swny.setChubanshe("��е��ҵ������");
		for (int i = 1; i < 78; i++) {
			swny.setShuming("��" + i + "��");
			addSwny(swny);
		}
		// swny.setId(9);
		// updateSwny(swny);
		// deleteSwny(6);
		// System.out.println(loadSwny(8));
		// moveUpSwny(7);
		// moveDownSwny(11);
		// System.out.println(allSwny());
		// rePaixuSwny();
		// System.out.println(allSwny());
		// System.out.println((double) 31 / (double) 10 > (31 / 10) ? 31 / 10 +
		// 1
		// : 31 / 10);
	}
}
