package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Hyyq;
import com.langwen.admin.service.HyyqService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontHyyqAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private Integer id;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private HyyqService hyyqService = new HyyqService();

	private int pageCount = (double) (hyyqService.allHyyq().size())
			/ (double) 10 > (hyyqService.allHyyq().size() / 10) ? hyyqService
			.allHyyq().size() / 10 + 1 : hyyqService.allHyyq().size() / 10;

	public String hyyqList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Hyyq> hyyqList = hyyqService.pageHyyq(ipage);
				for (int i = 0; i < hyyqList.size(); i++) {
					hyyqList.get(i).setTianjiashijian(
							hyyqList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("hyyqList", hyyqList);
			} else {
				page = 1;
				session.put("page", page);
				List<Hyyq> hyyqList = hyyqService.pageHyyq(1);
				for (int i = 0; i < hyyqList.size(); i++) {
					hyyqList.get(i).setTianjiashijian(
							hyyqList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("hyyqList", hyyqList);
			}
		} else {
			session.put("page", page);
			List<Hyyq> hyyqList = hyyqService.pageHyyq(page);
			for (int i = 0; i < hyyqList.size(); i++) {
				hyyqList.get(i).setTianjiashijian(
						hyyqList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("hyyqList", hyyqList);
		}
		return "hyyqList";
	}

	// ÉÏÒ»Ò³
	public String hyyqSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Hyyq> hyyqList = hyyqService.pageHyyq(npage - 1);
		for (int i = 0; i < hyyqList.size(); i++) {
			hyyqList.get(i).setTianjiashijian(
					hyyqList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("hyyqList", hyyqList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "hyyqList";
	}

	public String hyyqXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Hyyq> hyyqList = hyyqService.pageHyyq(npage + 1);
		for (int i = 0; i < hyyqList.size(); i++) {
			hyyqList.get(i).setTianjiashijian(
					hyyqList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("hyyqList", hyyqList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "hyyqList";
	}

	public String hyyqDetail() {
		session.put("hyyqDetail", hyyqService.loadHyyq(id));
		return "hyyqDetail";
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
