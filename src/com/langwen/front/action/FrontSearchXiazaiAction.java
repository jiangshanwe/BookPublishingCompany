package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.bean.Xzzx;
import com.langwen.front.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontSearchXiazaiAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String key;

	private SearchService searchService = new SearchService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	private int page;
	private Integer id;

	private int pageCount;

	public String asearchXiazai() {
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
		xiazaiList();
		return "xiazaiList";
	}

	public void xiazaiList() {
		List<Xzzx> xiazaiList = searchService.searchXiazai(key);
		for (int i = 0; i < xiazaiList.size(); i++) {
			xiazaiList.get(i).setTianjiashijian(
					xiazaiList.get(i).getTianjiashijian().substring(0, 10));
		}
		int xiazaiCount = xiazaiList.size();
		pageCount = (double) (xiazaiList.size()) / (double) 10 > (xiazaiList
				.size() / 10) ? xiazaiList.size() / 10 + 1
				: xiazaiList.size() / 10;
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("xiazaiList", xiazaiList.subList((ipage - 1) * 10,
						ipage * 10));
			} else {
				page = 1;
				session.put("page", page);
				session.put("xiazaiList", xiazaiList.subList(0,
						xiazaiCount > 10 ? 10 : xiazaiCount));
			}
		} else {
			session.put("page", page);
			session.put("xiazaiList", xiazaiList.subList((page - 1) * 10,
					xiazaiList.size() - (page - 1) * 10 > 10 ? page * 10
							: xiazaiList.size()));
		}
	}

	public String asearchXiazaiSyy() {
		List<Xzzx> xiazaiList = searchService.searchXiazai((String) session
				.get("key"));
		for (int i = 0; i < xiazaiList.size(); i++) {
			xiazaiList.get(i).setTianjiashijian(
					xiazaiList.get(i).getTianjiashijian().substring(0, 10));
		}
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage - 1));
		session.put("xiazaiList", xiazaiList.subList((npage - 1 - 1) * 10,
				(npage - 1) * 10));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "xiazaiList";
	}

	public String asearchXiazaiXyy() {
		List<Xzzx> xiazaiList = searchService.searchXiazai((String) session
				.get("key"));
		for (int i = 0; i < xiazaiList.size(); i++) {
			xiazaiList.get(i).setTianjiashijian(
					xiazaiList.get(i).getTianjiashijian().substring(0, 10));
		}
		int npage = (Integer) session.get("page");
		session.put("xiazaiList", xiazaiList.subList((npage) * 10, xiazaiList
				.size()
				- npage * 10 > 10 ? 10 * (npage + 1) : xiazaiList.size()));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "xiazaiList";
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
