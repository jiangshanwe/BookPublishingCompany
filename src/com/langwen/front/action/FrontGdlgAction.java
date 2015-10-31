package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.GdlgService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontGdlgAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private GdlgService gdlgService = new GdlgService();

	private int page;
	private Integer id;

	private int pageCount = (double) (gdlgService.allGdlg().size())
			/ (double) 12 > (gdlgService.allGdlg().size() / 12) ? gdlgService
			.allGdlg().size() / 12 + 1 : gdlgService.allGdlg().size() / 12;

	public String gdlgList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("gdlgList", gdlgService.pagefGdlg(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("gdlgList", gdlgService.pagefGdlg(1));
			}
		} else {
			session.put("page", page);
			session.put("gdlgList", gdlgService.pagefGdlg(page));
		}
		return "gdlgList";
	}

	// 上一页
	public String gdlgSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gdlgList", gdlgService.pagefGdlg(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "gdlgList";
	}

	public String gdlgXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("gdlgList", gdlgService.pagefGdlg(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "gdlgList";
	}

	public String gdlgDetail() {
		session.put("bookType", "高等理工");
		session.put("bookDetail", gdlgService.loadGdlg(id));
		return "gdlgDetail";
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
