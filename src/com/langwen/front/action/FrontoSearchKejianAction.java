package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Kjxz;
import com.langwen.front.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontoSearchKejianAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String okkey;

	private SearchService searchService = new SearchService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	private int page;
	private Integer id;

	private int pageCount;

	public String osearchKejian() {
		if (okkey == null) {
			okkey = (String) session.get("okkey");
		} else {
			okkey = okkey.replaceAll("\\s*", "");
			session.put("okkey", okkey);
		}
		List<Kjxz> kejianList = searchService.searchKejian(okkey);
		if (kejianList.size() == 0) {
			return "noResult";
		}
		session.put("kejianList", kejianList);
		kejianList();
		return "okejianList";
	}

	public void kejianList() {
		List<Kjxz> kejianList = searchService.searchKejian(okkey);
		for (int i = 0; i < kejianList.size(); i++) {
			kejianList.get(i).setTianjiashijian(
					kejianList.get(i).getTianjiashijian().substring(0, 10));
		}
		int kejianCount = kejianList.size();
		pageCount = (double) (kejianList.size()) / (double) 10 > (kejianList
				.size() / 10) ? kejianList.size() / 10 + 1
				: kejianList.size() / 10;
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("kejianList", kejianList.subList((ipage - 1) * 10,
						ipage * 10));
			} else {
				page = 1;
				session.put("page", page);
				session.put("kejianList", kejianList.subList(0,
						kejianCount > 10 ? 10 : kejianCount));
			}
		} else {
			session.put("page", page);
			session.put("kejianList", kejianList.subList((page - 1) * 10,
					kejianList.size() - (page - 1) * 10 > 10 ? page * 10
							: kejianList.size()));
		}
	}

	public String osearchKejianSyy() {
		List<Kjxz> kejianList = searchService.searchKejian((String) session
				.get("okkey"));
		for (int i = 0; i < kejianList.size(); i++) {
			kejianList.get(i).setTianjiashijian(
					kejianList.get(i).getTianjiashijian().substring(0, 10));
		}
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage - 1));
		session.put("kejianList", kejianList.subList((npage - 1 - 1) * 10,
				(npage - 1) * 10));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "okejianList";
	}

	public String osearchKejianXyy() {
		List<Kjxz> kejianList = searchService.searchKejian((String) session
				.get("okkey"));
		for (int i = 0; i < kejianList.size(); i++) {
			kejianList.get(i).setTianjiashijian(
					kejianList.get(i).getTianjiashijian().substring(0, 10));
		}
		int npage = (Integer) session.get("page");
		session.put("kejianList", kejianList.subList((npage) * 10, kejianList
				.size()
				- npage * 10 > 10 ? 10 * (npage + 1) : kejianList.size()));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "okejianList";
	}

	public String getOkkey() {
		return okkey;
	}

	public void setOkkey(String okkey) {
		this.okkey = okkey;
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
