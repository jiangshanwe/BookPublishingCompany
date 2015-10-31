package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Qyxw;
import com.langwen.admin.service.QyxwService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontQyxwAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private Integer id;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private QyxwService qyxwService = new QyxwService();

	private int pageCount = (double) (qyxwService.allQyxw().size())
			/ (double) 10 > (qyxwService.allQyxw().size() / 10) ? qyxwService
			.allQyxw().size() / 10 + 1 : qyxwService.allQyxw().size() / 10;

	public String qyxwList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Qyxw> qyxwList = qyxwService.pageQyxw(ipage);
				for (int i = 0; i < qyxwList.size(); i++) {
					qyxwList.get(i).setTianjiashijian(
							qyxwList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("qyxwList", qyxwList);
			} else {
				page = 1;
				session.put("page", page);
				List<Qyxw> qyxwList = qyxwService.pageQyxw(1);
				for (int i = 0; i < qyxwList.size(); i++) {
					qyxwList.get(i).setTianjiashijian(
							qyxwList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("qyxwList", qyxwList);
			}
		} else {
			session.put("page", page);
			List<Qyxw> qyxwList = qyxwService.pageQyxw(page);
			for (int i = 0; i < qyxwList.size(); i++) {
				qyxwList.get(i).setTianjiashijian(
						qyxwList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("qyxwList", qyxwList);
		}
		return "qyxwList";
	}

	// ÉÏÒ»Ò³
	public String qyxwSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Qyxw> qyxwList = qyxwService.pageQyxw(npage - 1);
		for (int i = 0; i < qyxwList.size(); i++) {
			qyxwList.get(i).setTianjiashijian(
					qyxwList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("qyxwList", qyxwList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "qyxwList";
	}

	public String qyxwXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Qyxw> qyxwList = qyxwService.pageQyxw(npage + 1);
		for (int i = 0; i < qyxwList.size(); i++) {
			qyxwList.get(i).setTianjiashijian(
					qyxwList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("qyxwList", qyxwList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "qyxwList";
	}

	public String qyxwDetail() {
		session.put("qyxwDetail", qyxwService.loadQyxw(id));
		return "qyxwDetail";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
