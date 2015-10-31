package com.langwen.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Qyxw;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.QyxwService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QyxwAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String QYXWSHOWALL = "qyxwShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private QyxwService qyxwService = new QyxwService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String zhengwen;
	private String ywlj;
	private Integer leixing;
	private Integer paixu;

	private int page;

	private int pageCount = (double) (qyxwService.allQyxw().size())
			/ (double) 10 > (qyxwService.allQyxw().size() / 10) ? qyxwService
			.allQyxw().size() / 10 + 1 : qyxwService.allQyxw().size() / 10;

	public String qyxwShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allQyxws", qyxwService.pageQyxw(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allQyxws", qyxwService.pageQyxw(1));
			}
		} else {
			session.put("page", page);
			session.put("allQyxws", qyxwService.pageQyxw(page));
		}
		return "allQyxws";
	}

	// 上一页
	public String qyxwSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allQyxws", qyxwService.pageQyxw(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allQyxws";
	}

	// 下一页
	public String qyxwXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allQyxws", qyxwService.pageQyxw(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allQyxws";
	}

	public String qyxwMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			qyxwService.moveUpQyxw(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			qyxwService.moveDownQyxw(id);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			qyxwService.deleteQyxw(id);
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

	public String qyxwRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			qyxwService.rePaixunQyxw();
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			return "qyxwTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			Qyxw qyxw = new Qyxw();
			qyxw.setTianjiaren(gly.getXingming());
			qyxw.setBiaoti(biaoti);
			qyxw.setZhengwen(zhengwen);
			qyxw.setYwlj(ywlj);
			qyxwService.addQyxw(qyxw);
			return QYXWSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwYulan() {
		session.put("qyxwYulan", qyxwService.loadQyxw(id));
		return "qyxwYulan";
	}

	public String qyxwBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			session.put("qyxwBianji", qyxwService.loadQyxw(id));
			return "qyxwBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String qyxwUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 23)) {
			Qyxw qyxw = qyxwService.loadQyxw(id);
			qyxw.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			qyxw.setGengxinshijian(curdate);
			qyxw.setBiaoti(biaoti);
			qyxw.setZhengwen(zhengwen);
			qyxw.setYwlj(ywlj);
			qyxwService.updateQyxw(qyxw);
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
