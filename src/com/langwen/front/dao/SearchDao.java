package com.langwen.front.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.bean.Xzzx;
import com.ssh.HibernateSessionFactory;

public class SearchDao {
	public List<Object> searchBookByShuming(String key) {
		List<Object> result = new ArrayList<Object>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String gdlgsmHql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdlg as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query gdlgsmquery = session.createQuery(gdlgsmHql);
			gdlgsmquery.setString("name", "%" + key + "%");
			result.addAll(gdlgsmquery.list());
			String gdwksmHql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdwk as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query gdwksmquery = session.createQuery(gdwksmHql);
			gdwksmquery.setString("name", "%" + key + "%");
			result.addAll(gdwksmquery.list());
			String swnysmHql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Swny as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query swnysmquery = session.createQuery(swnysmHql);
			swnysmquery.setString("name", "%" + key + "%");
			result.addAll(swnysmquery.list());
			String gzjysmHql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gzjy as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query gzjysmquery = session.createQuery(gzjysmHql);
			gzjysmquery.setString("name", "%" + key + "%");
			result.addAll(gzjysmquery.list());
			String zzjysmHql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Zzjy as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query zzjysmquery = session.createQuery(zzjysmHql);
			zzjysmquery.setString("name", "%" + key + "%");
			result.addAll(zzjysmquery.list());
			String jsjysmHql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Jsjy as jj where jj.shuming like :name ORDER BY paixu DESC";
			Query jsjysmquery = session.createQuery(jsjysmHql);
			jsjysmquery.setString("name", "%" + key + "%");
			result.addAll(jsjysmquery.list());
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

	public List<Object> searchBookByZuozhe(String key) {
		List<Object> result = new ArrayList<Object>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String gdlgzzHql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdlg as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query gdlgzzquery = session.createQuery(gdlgzzHql);
			gdlgzzquery.setString("name", "%" + key + "%");
			result.removeAll(gdlgzzquery.list());
			result.addAll(gdlgzzquery.list());
			String gdwkzzHql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdwk as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query gdwkzzquery = session.createQuery(gdwkzzHql);
			gdwkzzquery.setString("name", "%" + key + "%");
			result.removeAll(gdwkzzquery.list());
			result.addAll(gdwkzzquery.list());
			String swnyzzHql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Swny as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query swnyzzquery = session.createQuery(swnyzzHql);
			swnyzzquery.setString("name", "%" + key + "%");
			result.removeAll(swnyzzquery.list());
			result.addAll(swnyzzquery.list());
			String gzjyzzHql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gzjy as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query gzjyzzquery = session.createQuery(gzjyzzHql);
			gzjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(gzjyzzquery.list());
			result.addAll(gzjyzzquery.list());
			String zzjyzzHql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Zzjy as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query zzjyzzquery = session.createQuery(zzjyzzHql);
			zzjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(zzjyzzquery.list());
			result.addAll(zzjyzzquery.list());
			String jsjyzzHql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.zuozhe,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Jsjy as jj where jj.zuozhe like :name ORDER BY paixu DESC";
			Query jsjyzzquery = session.createQuery(jsjyzzHql);
			jsjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(jsjyzzquery.list());
			result.addAll(jsjyzzquery.list());
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

	public List<Object> searchBookByIsbn(String key) {
		List<Object> result = new ArrayList<Object>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String gdlgzzHql = "select new Gdlg(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdlg as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query gdlgzzquery = session.createQuery(gdlgzzHql);
			gdlgzzquery.setString("name", "%" + key + "%");
			result.removeAll(gdlgzzquery.list());
			result.addAll(gdlgzzquery.list());
			String gdwkzzHql = "select new Gdwk(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gdwk as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query gdwkzzquery = session.createQuery(gdwkzzHql);
			gdwkzzquery.setString("name", "%" + key + "%");
			result.removeAll(gdwkzzquery.list());
			result.addAll(gdwkzzquery.list());
			String swnyzzHql = "select new Swny(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Swny as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query swnyzzquery = session.createQuery(swnyzzHql);
			swnyzzquery.setString("name", "%" + key + "%");
			result.removeAll(swnyzzquery.list());
			result.addAll(swnyzzquery.list());
			String gzjyzzHql = "select new Gzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Gzjy as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query gzjyzzquery = session.createQuery(gzjyzzHql);
			gzjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(gzjyzzquery.list());
			result.addAll(gzjyzzquery.list());
			String zzjyzzHql = "select new Zzjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Zzjy as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query zzjyzzquery = session.createQuery(zzjyzzHql);
			zzjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(zzjyzzquery.list());
			result.addAll(zzjyzzquery.list());
			String jsjyzzHql = "select new Jsjy(jj.id, jj.fenlei,jj.fenlei2, jj.shuming, jj.isbn,jj.chubanshe, jj.isbn, jj.banci, jj.baozhuang,jj.kaiben, jj.chubanshijian, jj.yeshu, jj.dingjia, jj.tianjiashijian, jj.tianjiaren, jj.tupian1,jj.gengxinren, jj.gengxinshijian, jj.zhanshi,jj.paixu,jj.type) from Jsjy as jj where jj.isbn like :name ORDER BY paixu DESC";
			Query jsjyzzquery = session.createQuery(jsjyzzHql);
			jsjyzzquery.setString("name", "%" + key + "%");
			result.removeAll(jsjyzzquery.list());
			result.addAll(jsjyzzquery.list());
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

	public List<Object> searchBook(String key) {
		List<Object> result = new ArrayList<Object>();
		result.addAll(searchBookByShuming(key));
		result.removeAll(searchBookByZuozhe(key));
		result.addAll(searchBookByZuozhe(key));
		result.removeAll(searchBookByIsbn(key));
		result.addAll(searchBookByIsbn(key));
		return result;
	}

	public List<Kjxz> searchKejian(String key) {
		List<Kjxz> result = new ArrayList<Kjxz>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String kjxzHql = "select new Kjxz(jj.id, jj.biaoti, jj.dyts, jj.fujian,	jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Kjxz as jj where jj.biaoti like :name ORDER BY paixu DESC";
			Query kjxzquery = session.createQuery(kjxzHql);
			kjxzquery.setString("name", "%" + key + "%");
			result.removeAll(kjxzquery.list());
			result.addAll(kjxzquery.list());
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

	public List<Xzzx> searchXiazai(String key) {
		List<Xzzx> result = new ArrayList<Xzzx>();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String kjxzHql = "select new Xzzx(jj.id, jj.biaoti, jj.dyts, jj.fujian, jj.bdfx, jj.fxmm, jj.tupian, jj.tianjiaren, jj.tianjiashijian, jj.gengxinren, jj.gengxinshijian, jj.paixu) from Xzzx as jj where jj.biaoti like :name ORDER BY paixu DESC";
			Query kjxzquery = session.createQuery(kjxzHql);
			kjxzquery.setString("name", "%" + key + "%");
			result.removeAll(kjxzquery.list());
			result.addAll(kjxzquery.list());
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

	@Test
	public void testSearch() {
		// System.out.println(searchBookByShuming("No.53Book"));
		// System.out.println(searchBookByZuozhe("Bruce Eckel"));
		// System.out.println(searchBookByIsbn("439123823"));
		System.out.println(searchBook("Java"));
		// System.out.println(searchKejian("下载"));
		// System.out.println(searchXiazai("下载"));
	}
}
