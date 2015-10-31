package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.GdwkService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontGdwkAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private GdwkService gdwkService = new GdwkService();

	private int page;
	private Integer id;

	private int pageCount = (double) (gdwkService.allGdwk().size())
			/ (double) 12 > (gdwkService.allGdwk().size() / 12) ? gdwkService
			.allGdwk().size() / 12 + 1 : gdwkService.allGdwk().size() / 12;

	public String gdwkList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("gdwkList", gdwkService.pagefGdwk(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("gdwkList", gdwkService.pagefGdwk(1));
			}
		} else {
			session.put("page", page);
			session.put("gdwkList", gdwkService.pagefGdwk(page));
		}
		return "gdwkList";
	}

	// 上一页
	public String gdwkSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gdwkList", gdwkService.pagefGdwk(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "gdwkList";
	}

	public String gdwkXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gdwkList", gdwkService.pagefGdwk(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "gdwkList";
	}

	public String gdwkDetail() {
		session.put("bookDetail", gdwkService.loadGdwk(id));
		session.put("bookType", "高等文科");
		return "gdwkDetail";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
