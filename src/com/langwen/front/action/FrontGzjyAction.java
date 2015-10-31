package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.GzjyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontGzjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private GzjyService gzjyService = new GzjyService();

	private int page;
	private Integer id;

	private int pageCount = (double) (gzjyService.allGzjy().size())
			/ (double) 12 > (gzjyService.allGzjy().size() / 12) ? gzjyService
			.allGzjy().size() / 12 + 1 : gzjyService.allGzjy().size() / 12;

	public String gzjyList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("gzjyList", gzjyService.pagefGzjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("gzjyList", gzjyService.pagefGzjy(1));
			}
		} else {
			session.put("page", page);
			session.put("gzjyList", gzjyService.pagefGzjy(page));
		}
		return "gzjyList";
	}

	// 上一页
	public String gzjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gzjyList", gzjyService.pagefGzjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "gzjyList";
	}

	public String gzjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gzjyList", gzjyService.pagefGzjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "gzjyList";
	}

	public String gzjyDetail() {
		session.put("bookType", "高职教育");
		session.put("bookDetail", gzjyService.loadGzjy(id));
		return "gzjyDetail";
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
