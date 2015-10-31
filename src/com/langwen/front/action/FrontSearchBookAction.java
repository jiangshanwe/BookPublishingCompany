package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.bean.Xzzx;
import com.langwen.admin.service.GdlgService;
import com.langwen.admin.service.GdwkService;
import com.langwen.admin.service.GzjyService;
import com.langwen.admin.service.JsjyService;
import com.langwen.admin.service.SwnyService;
import com.langwen.admin.service.ZzjyService;
import com.langwen.front.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontSearchBookAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String key;

	private SearchService searchService = new SearchService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	private GdlgService gdlgService = new GdlgService();
	private GdwkService gdwkService = new GdwkService();
	private SwnyService swnyService = new SwnyService();
	private GzjyService gzjyService = new GzjyService();
	private ZzjyService zzjyService = new ZzjyService();
	private JsjyService jsjyService = new JsjyService();

	private int page;
	private Integer id;
	private String type;

	private int pageCount;

	public String asearchBook() {
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
		if (bookList.size() == 0 && kejianList.size() == 0
				&& xiazaiList.size() == 0) {
			return "noResult";
		} else {
			if (bookList.size() != 0) {
				bookList();
				return "bookList";
			} else {
				if (kejianList.size() != 0) {
					return "kejianList";
				} else {
					return "xiazaiList";
				}
			}
		}
	}

	public void bookList() {
		List<Object> bookList = searchService.searchBook(key);
		int bookCount = bookList.size();
		pageCount = (double) (bookList.size()) / (double) 12 > (bookList.size() / 12) ? bookList
				.size() / 12 + 1
				: bookList.size() / 12;
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("bookList", bookList.subList((ipage - 1) * 12,
						bookList.size() - (ipage - 1) * 12 > 12 ? ipage * 12
								: bookList.size()));
			} else {
				page = 1;
				session.put("page", page);
				session.put("bookList", bookList.subList(0, bookCount > 12 ? 12
						: bookCount));
			}
		} else {
			session.put("page", page);
			session.put("bookList", bookList.subList((page - 1) * 12, bookList
					.size()
					- (page - 1) * 12 > 12 ? page * 12 : bookList.size()));
		}
	}

	public String asearchBookSyy() {
		List<Object> bookList = searchService.searchBook((String) session
				.get("key"));
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage - 1));
		session.put("bookList", bookList.subList((npage - 1 - 1) * 12,
				(npage - 1) * 12));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "bookList";
	}

	public String asearchBookXyy() {
		List<Object> bookList = searchService.searchBook((String) session
				.get("key"));
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage + 1));
		session.put("bookList", bookList.subList((npage) * 12, bookList.size()
				- npage * 12 > 12 ? 12 * (npage + 1) : bookList.size()));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "bookList";
	}

	public String asearchBookDetail() {
		if (type.equals("gdlg")) {
			session.put("bookDetail", gdlgService.loadGdlg(id));
			session.put("bookType", "高等理工");
		} else if (type.equals("gdwk")) {
			session.put("bookDetail", gdwkService.loadGdwk(id));
			session.put("bookType", "高等文科");
		} else if (type.equals("swny")) {
			session.put("bookDetail", swnyService.loadSwny(id));
			session.put("bookType", "生物农医");
		} else if (type.equals("gzjy")) {
			session.put("bookDetail", gzjyService.loadGzjy(id));
			session.put("bookType", "高职教育");
		} else if (type.equals("zzjy")) {
			session.put("bookDetail", zzjyService.loadZzjy(id));
			session.put("bookType", "中职教育");
		} else if (type.equals("jsjy")) {
			session.put("bookDetail", jsjyService.loadJsjy(id));
			session.put("bookType", "教师教育");
		}
		return "bookDetail";
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
