package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.service.KjxzService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontKjxzAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private Integer id;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private KjxzService kjxzService = new KjxzService();

	private int pageCount = (double) (kjxzService.allKjxz().size())
			/ (double) 10 > (kjxzService.allKjxz().size() / 10) ? kjxzService
			.allKjxz().size() / 10 + 1 : kjxzService.allKjxz().size() / 10;

	public String kjxzList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Kjxz> kjxzList = kjxzService.pageKjxz(ipage);
				for (int i = 0; i < kjxzList.size(); i++) {
					kjxzList.get(i).setTianjiashijian(
							kjxzList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("kjxzList", kjxzList);
			} else {
				page = 1;
				session.put("page", page);
				List<Kjxz> kjxzList = kjxzService.pageKjxz(1);
				for (int i = 0; i < kjxzList.size(); i++) {
					kjxzList.get(i).setTianjiashijian(
							kjxzList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("kjxzList", kjxzList);
			}
		} else {
			session.put("page", page);
			List<Kjxz> kjxzList = kjxzService.pageKjxz(page);
			for (int i = 0; i < kjxzList.size(); i++) {
				kjxzList.get(i).setTianjiashijian(
						kjxzList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("kjxzList", kjxzList);
		}
		return "kjxzList";
	}

	// ÉÏÒ»Ò³
	public String kjxzSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Kjxz> kjxzList = kjxzService.pageKjxz(npage - 1);
		for (int i = 0; i < kjxzList.size(); i++) {
			kjxzList.get(i).setTianjiashijian(
					kjxzList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("kjxzList", kjxzList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "kjxzList";
	}

	public String kjxzXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Kjxz> kjxzList = kjxzService.pageKjxz(npage + 1);
		for (int i = 0; i < kjxzList.size(); i++) {
			kjxzList.get(i).setTianjiashijian(
					kjxzList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("kjxzList", kjxzList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "kjxzList";
	}

	public String kjxzDetail() {
		session.put("kjxzDetail", kjxzService.loadKjxz(id));
		return "kjxzDetail";
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
