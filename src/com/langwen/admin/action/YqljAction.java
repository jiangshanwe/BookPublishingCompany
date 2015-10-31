package com.langwen.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Yqlj;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.YqljService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class YqljAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "yqljShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private YqljService yqljService = new YqljService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String shuming;
	private String chubanshe;
	private String tupian;
	private String lianjie;

	private int page;

	private int pageCount = (double) (yqljService.allYqlj().size())
			/ (double) 10 > (yqljService.allYqlj().size() / 10) ? yqljService
			.allYqlj().size() / 10 + 1 : yqljService.allYqlj().size() / 10;

	public String yqljShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allYqljs", yqljService.pageYqlj(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allYqljs", yqljService.pageYqlj(1));
			}
		} else {
			session.put("page", page);
			session.put("allYqljs", yqljService.pageYqlj(page));
		}
		return "allYqljs";
	}

	public String yqljMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			yqljService.moveUpYqlj(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			yqljService.moveDownYqlj(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	// 上一页
	public String yqljSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allYqljs", yqljService.pageYqlj(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allYqljs";
	}

	// 下一页
	public String yqljXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allYqljs", yqljService.pageYqlj(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allYqljs";
	}

	public String yqljShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			yqljService.deleteYqlj(id);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			yqljService.rePaixunYqlj();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			return "yqljTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			Yqlj yqlj = new Yqlj();
			yqlj.setTianjiaren(gly.getXingming());
			yqlj.setShuming(shuming);
			yqlj.setLianjie(lianjie);
			yqljService.addYqlj(yqlj);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			session.put("yqljBianji", yqljService.loadYqlj(id));
			return "yqljBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yqljUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 3)) {
			Yqlj yqlj = yqljService.loadYqlj(id);
			yqlj.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			yqlj.setGengxinshijian(curdate);
			yqlj.setShuming(shuming);
			yqlj.setLianjie(lianjie);
			yqljService.updateYqlj(yqlj);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShuming() {
		return shuming;
	}

	public void setShuming(String shuming) {
		this.shuming = shuming;
	}

	public String getChubanshe() {
		return chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public String getLianjie() {
		return lianjie;
	}

	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
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
