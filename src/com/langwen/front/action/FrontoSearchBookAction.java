package com.langwen.front.action;

import java.util.List;
import java.util.Map;

import com.langwen.admin.service.GdlgService;
import com.langwen.admin.service.GdwkService;
import com.langwen.admin.service.GzjyService;
import com.langwen.admin.service.JsjyService;
import com.langwen.admin.service.SwnyService;
import com.langwen.admin.service.ZzjyService;
import com.langwen.front.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FrontoSearchBookAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String obkey;

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

	public String osearchBook() {
		if (obkey == null) {
			obkey = (String) session.get("obkey");
		} else {
			obkey = obkey.replaceAll("\\s*", "");
			session.put("obkey", obkey);
		}
		List<Object> bookList = searchService.searchBook(obkey);
		if(bookList.size()==0){
			return "noResult";
		}
		session.put("bookList", bookList);
		bookList();
		return "obookList";
	}

	public void bookList() {
		List<Object> bookList = searchService.searchBook(obkey);
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

	public String osearchBookSyy() {
		List<Object> bookList = searchService.searchBook((String) session
				.get("obkey"));
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage - 1));
		session.put("bookList", bookList.subList((npage - 1 - 1) * 12,
				(npage - 1) * 12));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "obookList";
	}

	public String osearchBookXyy() {
		List<Object> bookList = searchService.searchBook((String) session
				.get("obkey"));
		int npage = (Integer) session.get("page");
		// session.put("gdlgList", gdlgService.pagefGdlg(npage + 1));
		session.put("bookList", bookList.subList((npage) * 12, bookList.size()
				- npage * 12 > 12 ? 12 * (npage + 1) : bookList.size()));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "obookList";
	}

	public String osearchBookDetail() {
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

	public String getObkey() {
		return obkey;
	}

	public void setObkey(String obkey) {
		this.obkey = obkey;
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
