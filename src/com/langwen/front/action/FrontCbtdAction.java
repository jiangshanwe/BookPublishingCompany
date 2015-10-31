package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Cbtd;
import com.langwen.admin.bean.Gxxw;
import com.langwen.admin.bean.Hydt;
import com.langwen.admin.service.CbtdService;
import com.langwen.admin.service.HydtService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontCbtdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Map<String, Object> session = ActionContext.getContext().getSession();

	private CbtdService cbtdService = new CbtdService();
	private HydtService hydtService = new HydtService();

	private int page;
	private Integer id;

	private int pageCount = (double) (hydtService.allHydt().size())
			/ (double) 10 > (hydtService.allHydt().size() / 10) ? hydtService
			.allHydt().size() / 10 + 1 : hydtService.allHydt().size() / 10;

	private List<Cbtd> cbtdList = cbtdService.allCbtd();

	public String cbtd() {
		session.put("cbtdList", cbtdList);
		// 根据id判断显示哪一个朗文简介项目
		if (id == null) {
			Integer lid = cbtdService.allCbtd().get(0).getId();
			session.put("lid", lid);
			setId(lid);
		} else {
			session.put("lid", getId());
		}
		session.put("cbtd", cbtdService.loadCbtd(id));
		return "cbtdList";
	}

	public String hydtList() {
		session.put("cbtdList", cbtdList);
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				List<Hydt> hydtList = hydtService.pageHydt(ipage);
				for (int i = 0; i < hydtList.size(); i++) {
					hydtList.get(i).setTianjiashijian(
							hydtList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("hydtList", hydtList);
			} else {
				page = 1;
				session.put("page", page);
				List<Hydt> hydtList = hydtService.pageHydt(1);
				for (int i = 0; i < hydtList.size(); i++) {
					hydtList.get(i).setTianjiashijian(
							hydtList.get(i).getTianjiashijian()
									.substring(0, 10));
				}
				session.put("hydtList", hydtList);
			}
		} else {
			session.put("page", page);
			List<Hydt> hydtList = hydtService.pageHydt(page);
			for (int i = 0; i < hydtList.size(); i++) {
				hydtList.get(i).setTianjiashijian(
						hydtList.get(i).getTianjiashijian().substring(0, 10));
			}
			session.put("hydtList", hydtList);
		}
		return "hydtList";
	}

	// 上一页
	public String hydtSyy() {
		session.put("cbtdList", cbtdList);
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Hydt> hydtList = hydtService.pageHydt(npage - 1);
		for (int i = 0; i < hydtList.size(); i++) {
			hydtList.get(i).setTianjiashijian(
					hydtList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("hydtList", hydtList);
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "hydtList";
	}

	public String hydtXyy() {
		session.put("cbtdList", cbtdList);
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		List<Hydt> hydtList = hydtService.pageHydt(npage + 1);
		for (int i = 0; i < hydtList.size(); i++) {
			hydtList.get(i).setTianjiashijian(
					hydtList.get(i).getTianjiashijian().substring(0, 10));
		}
		session.put("hydtList", hydtList);
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "hydtList";
	}

	public String hydtDetail() {
		session.put("cbtdList", cbtdList);
		session.put("hydtDetail", hydtService.loadHydt(id));
		return "hydtDetail";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
