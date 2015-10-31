package com.langwen.front.action;

import java.util.Map;

import com.langwen.admin.service.ZzjyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontZzjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private ZzjyService zzjyService = new ZzjyService();

	private int page;
	private Integer id;

	private int pageCount = (double) (zzjyService.allZzjy().size())
			/ (double) 12 > (zzjyService.allZzjy().size() / 12) ? zzjyService
			.allZzjy().size() / 12 + 1 : zzjyService.allZzjy().size() / 12;

	public String zzjyList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("zzjyList", zzjyService.pagefZzjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("zzjyList", zzjyService.pagefZzjy(1));
			}
		} else {
			session.put("page", page);
			session.put("zzjyList", zzjyService.pagefZzjy(page));
		}
		return "zzjyList";
	}

	// 上一页
	public String zzjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("zzjyList", zzjyService.pagefZzjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "zzjyList";
	}

	public String zzjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("zzjyList", zzjyService.pagefZzjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "zzjyList";
	}

	public String zzjyDetail() {
		session.put("bookType", "中职教育");
		session.put("bookDetail", zzjyService.loadZzjy(id));
		return "zzjyDetail";
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
