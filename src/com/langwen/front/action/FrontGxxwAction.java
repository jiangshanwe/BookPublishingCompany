package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Gxxw;
import com.langwen.admin.service.GxxwService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontGxxwAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private Integer id;

	Map<String, Object> session = ActionContext.getContext().getSession();
	private GxxwService gxxwService = new GxxwService();

	private int pageCount = (double) (gxxwService.allGxxw().size())
			/ (double) 10 > (gxxwService.allGxxw().size() / 10) ? gxxwService
			.allGxxw().size() / 10 + 1 : gxxwService.allGxxw().size() / 10;

	public String gxxwList() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Gxxw> gxxwList = gxxwService.pageGxxw(ipage);
				for (int i = 0; i < gxxwList.size(); i++) {
					gxxwList.get(i).setTianjiashijian(
							gxxwList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("gxxwList", gxxwList);
			} else {
				page = 1;
				session.put("page", page);
				List<Gxxw> gxxwList = gxxwService.pageGxxw(1);
				for (int i = 0; i < gxxwList.size(); i++) {
					gxxwList.get(i).setTianjiashijian(
							gxxwList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("gxxwList", gxxwList);
			}
		} else {
			session.put("page", page);
			List<Gxxw> gxxwList = gxxwService.pageGxxw(page);
			for (int i = 0; i < gxxwList.size(); i++) {
				gxxwList.get(i).setTianjiashijian(
						gxxwList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("gxxwList", gxxwList);
		}
		return "gxxwList";
	}

	// ÉÏÒ»Ò³
	public String gxxwSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Gxxw> gxxwList = gxxwService.pageGxxw(npage - 1);
		for (int i = 0; i < gxxwList.size(); i++) {
			gxxwList.get(i).setTianjiashijian(
					gxxwList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("gxxwList", gxxwList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "gxxwList";
	}

	public String gxxwXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Gxxw> gxxwList = gxxwService.pageGxxw(npage + 1);
		for (int i = 0; i < gxxwList.size(); i++) {
			gxxwList.get(i).setTianjiashijian(
					gxxwList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("gxxwList", gxxwList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "gxxwList";
	}

	public String gxxwDetail() {
		session.put("gxxwDetail", gxxwService.loadGxxw(id));
		return "gxxwDetail";
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
