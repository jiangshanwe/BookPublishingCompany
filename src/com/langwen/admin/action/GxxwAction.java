package com.langwen.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Gxxw;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.GxxwService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GxxwAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String QYXWSHOWALL = "gxxwShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private GxxwService gxxwService = new GxxwService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String zhengwen;
	private String ywlj;
	private Integer leixing;
	private Integer paixu;

	private int page;

	private int pageCount = (double) (gxxwService.allGxxw().size())
			/ (double) 10 > (gxxwService.allGxxw().size() / 10) ? gxxwService
			.allGxxw().size() / 10 + 1 : gxxwService.allGxxw().size() / 10;

	public String gxxwShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allGxxws", gxxwService.pageGxxw(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allGxxws", gxxwService.pageGxxw(1));
			}
		} else {
			session.put("page", page);
			session.put("allGxxws", gxxwService.pageGxxw(page));
		}
		return "allGxxws";
	}

	// 上一页
	public String gxxwSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allGxxws", gxxwService.pageGxxw(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allGxxws";
	}

	// 下一页
	public String gxxwXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allGxxws", gxxwService.pageGxxw(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allGxxws";
	}

	public String gxxwMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			gxxwService.moveUpGxxw(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			gxxwService.moveDownGxxw(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			gxxwService.deleteGxxw(id);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			gxxwService.rePaixunGxxw();
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			return "gxxwTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			Gxxw gxxw = new Gxxw();
			gxxw.setTianjiaren(gly.getXingming());
			gxxw.setBiaoti(biaoti);
			gxxw.setZhengwen(zhengwen);
			gxxw.setYwlj(ywlj);
			gxxwService.addGxxw(gxxw);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwYulan() {
		session.put("gxxwYulan", gxxwService.loadGxxw(id));
		return "gxxwYulan";
	}

	public String gxxwBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			session.put("gxxwBianji", gxxwService.loadGxxw(id));
			return "gxxwBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gxxwUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 24)) {
			Gxxw gxxw = gxxwService.loadGxxw(id);
			gxxw.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gxxw.setGengxinshijian(curdate);
			gxxw.setBiaoti(biaoti);
			gxxw.setZhengwen(zhengwen);
			gxxw.setYwlj(ywlj);
			gxxwService.updateGxxw(gxxw);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return QYXWSHOWALL;
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

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getZhengwen() {
		return zhengwen;
	}

	public void setZhengwen(String zhengwen) {
		this.zhengwen = zhengwen;
	}

	public String getYwlj() {
		return ywlj;
	}

	public void setYwlj(String ywlj) {
		this.ywlj = ywlj;
	}

	public Integer getLeixing() {
		return leixing;
	}

	public void setLeixing(Integer leixing) {
		this.leixing = leixing;
	}

	public Integer getPaixu() {
		return paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
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
