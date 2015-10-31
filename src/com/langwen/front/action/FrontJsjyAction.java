package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.JsjyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontJsjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private JsjyService jsjyService = new JsjyService();

	private int page;
	private Integer id;

	private int pageCount = (double) (jsjyService.allJsjy().size())
			/ (double) 12 > (jsjyService.allJsjy().size() / 12) ? jsjyService
			.allJsjy().size() / 12 + 1 : jsjyService.allJsjy().size() / 12;

	public String jsjyList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("jsjyList", jsjyService.pagefJsjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("jsjyList", jsjyService.pagefJsjy(1));
			}
		} else {
			session.put("page", page);
			session.put("jsjyList", jsjyService.pagefJsjy(page));
		}
		return "jsjyList";
	}

	// 上一页
	public String jsjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("jsjyList", jsjyService.pagefJsjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "jsjyList";
	}

	public String jsjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("jsjyList", jsjyService.pagefJsjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "jsjyList";
	}

	public String jsjyDetail() {
		session.put("bookType", "教师教育");
		session.put("bookDetail", jsjyService.loadJsjy(id));
		return "jsjyDetail";
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
