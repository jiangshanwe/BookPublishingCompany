package com.langwen.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Hydt;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.HydtService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HydtAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String QYXWSHOWALL = "hydtShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private HydtService hydtService = new HydtService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String zhengwen;
	private String ywlj;
	private Integer leixing;
	private Integer paixu;

	private int page;

	private int pageCount = (double) (hydtService.allHydt().size())
			/ (double) 10 > (hydtService.allHydt().size() / 10) ? hydtService
			.allHydt().size() / 10 + 1 : hydtService.allHydt().size() / 10;

	public String hydtShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allHydts", hydtService.pageHydt(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allHydts", hydtService.pageHydt(1));
			}
		} else {
			session.put("page", page);
			session.put("allHydts", hydtService.pageHydt(page));
		}
		return "allHydts";
	}

	// 上一页
	public String hydtSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allHydts", hydtService.pageHydt(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allHydts";
	}

	// 下一页
	public String hydtXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allHydts", hydtService.pageHydt(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allHydts";
	}

	public String hydtMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			hydtService.moveUpHydt(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			hydtService.moveDownHydt(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			hydtService.deleteHydt(id);
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

	public String hydtRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			hydtService.rePaixunHydt();
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			return "hydtTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			Hydt hydt = new Hydt();
			hydt.setTianjiaren(gly.getXingming());
			hydt.setBiaoti(biaoti);
			hydt.setZhengwen(zhengwen);
			hydt.setYwlj(ywlj);
			hydtService.addHydt(hydt);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtYulan() {
		session.put("hydtYulan", hydtService.loadHydt(id));
		return "hydtYulan";
	}

	public String hydtBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			session.put("hydtBianji", hydtService.loadHydt(id));
			return "hydtBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hydtUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 22)) {
			Hydt hydt = hydtService.loadHydt(id);
			hydt.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			hydt.setGengxinshijian(curdate);
			hydt.setBiaoti(biaoti);
			hydt.setZhengwen(zhengwen);
			hydt.setYwlj(ywlj);
			hydtService.updateHydt(hydt);
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
