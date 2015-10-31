package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Xzzx;
import com.langwen.admin.service.XzzxService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontXzzxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private Integer id;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private XzzxService xzzxService = new XzzxService();

	private int pageCount = (double) (xzzxService.allXzzx().size())
			/ (double) 10 > (xzzxService.allXzzx().size() / 10) ? xzzxService
			.allXzzx().size() / 10 + 1 : xzzxService.allXzzx().size() / 10;

	public String xzzxList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Xzzx> xzzxList = xzzxService.pageXzzx(ipage);
				for (int i = 0; i < xzzxList.size(); i++) {
					xzzxList.get(i).setTianjiashijian(
							xzzxList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("xzzxList", xzzxList);
			} else {
				page = 1;
				session.put("page", page);
				List<Xzzx> xzzxList = xzzxService.pageXzzx(1);
				for (int i = 0; i < xzzxList.size(); i++) {
					xzzxList.get(i).setTianjiashijian(
							xzzxList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("xzzxList", xzzxList);
			}
		} else {
			session.put("page", page);
			List<Xzzx> xzzxList = xzzxService.pageXzzx(page);
			for (int i = 0; i < xzzxList.size(); i++) {
				xzzxList.get(i).setTianjiashijian(
						xzzxList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("xzzxList", xzzxList);
		}
		return "xzzxList";
	}

	// ÉÏÒ»Ò³
	public String xzzxSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Xzzx> xzzxList = xzzxService.pageXzzx(npage - 1);
		for (int i = 0; i < xzzxList.size(); i++) {
			xzzxList.get(i).setTianjiashijian(
					xzzxList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("xzzxList", xzzxList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "xzzxList";
	}

	public String xzzxXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Xzzx> xzzxList = xzzxService.pageXzzx(npage + 1);
		for (int i = 0; i < xzzxList.size(); i++) {
			xzzxList.get(i).setTianjiashijian(
					xzzxList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("xzzxList", xzzxList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "xzzxList";
	}

	public String xzzxDetail() {
		session.put("xzzxDetail", xzzxService.loadXzzx(id));
		return "xzzxDetail";
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
