package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.bean.Xzzx;
import com.langwen.front.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontSearchKejianAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String key;

	private SearchService searchService = new SearchService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	private int page;
	private Integer id;

	private int pageCount;

	public String asearchKejian() {
		if (key == null) {
			key = (String) session.get("key");
		} else {
			key = key.replaceAll("\\s*", "");
			session.put("key", key);
		}
		List<Object> bookList = searchService.searchBook(key);
		List<Kjxz> kejianList = searchService.searchKejian(key);
		List<Xzzx> xiazaiList = searchService.searchXiazai(key);
		session.put("bookList", bookList);
		session.put("kejianList", kejianList);
		session.put("xiazaiList", xiazaiList);
		kejianList();
		return "kejianList";
	}

	public void kejianList() {
		List<Kjxz> kejianList = searchService.searchKejian(key);
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

	public String asearchKejianSyy() {
		List<Kjxz> kejianList = searchService.searchKejian((String) session
				.get("key"));
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
		return "kejianList";
	}

	public String asearchKejianXyy() {
		List<Kjxz> kejianList = searchService.searchKejian((String) session
				.get("key"));
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
		return "kejianList";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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
