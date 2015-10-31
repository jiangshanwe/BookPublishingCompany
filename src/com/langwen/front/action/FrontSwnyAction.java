package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.SwnyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontSwnyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private SwnyService swnyService = new SwnyService();

	private int page;
	private Integer id;

	private int pageCount = (double) (swnyService.allSwny().size())
			/ (double) 12 > (swnyService.allSwny().size() / 12) ? swnyService
			.allSwny().size() / 12 + 1 : swnyService.allSwny().size() / 12;

	public String swnyList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("swnyList", swnyService.pagefSwny(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("swnyList", swnyService.pagefSwny(1));
			}
		} else {
			session.put("page", page);
			session.put("swnyList", swnyService.pagefSwny(page));
		}
		return "swnyList";
	}

	// 上一页
	public String swnySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("swnyList", swnyService.pagefSwny(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "swnyList";
	}

	public String swnyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("swnyList", swnyService.pagefSwny(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "swnyList";
	}

	public String swnyDetail() {
		session.put("bookType", "生物农医");
		session.put("bookDetail", swnyService.loadSwny(id));
		return "swnyDetail";
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
