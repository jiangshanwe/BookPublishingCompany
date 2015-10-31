package com.langwen.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Bjtj;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.BjtjService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BjtjAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "bjtjShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private BjtjService bjtjService = new BjtjService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String shuming;
	private String chubanshe;
	private String tupian;
	private String lianjie;

	private int page;

	private int pageCount = (double) (bjtjService.allBjtj().size())
			/ (double) 10 > (bjtjService.allBjtj().size() / 10) ? bjtjService
			.allBjtj().size() / 10 + 1 : bjtjService.allBjtj().size() / 10;

	public String bjtjShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allBjtjs", bjtjService.pageBjtj(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allBjtjs", bjtjService.pageBjtj(1));
			}
		} else {
			session.put("page", page);
			session.put("allBjtjs", bjtjService.pageBjtj(page));
		}
		return "allBjtjs";
	}

	public String bjtjMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			bjtjService.moveUpBjtj(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bjtjMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			bjtjService.moveDownBjtj(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	// 上一页
	public String bjtjSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allBjtjs", bjtjService.pageBjtj(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allBjtjs";
	}

	// 下一页
	public String bjtjXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allBjtjs", bjtjService.pageBjtj(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allBjtjs";
	}

	public String bjtjShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			bjtjService.deleteBjtj(id);
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

	public String bjtjRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			bjtjService.rePaixunBjtj();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bjtjTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			return "bjtjTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bjtjAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			Bjtj bjtj = new Bjtj();
			bjtj.setTianjiaren(gly.getXingming());
			bjtj.setShuming(shuming);
			bjtj.setLianjie(lianjie);
			bjtjService.addBjtj(bjtj);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bjtjBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			session.put("bjtjBianji", bjtjService.loadBjtj(id));
			return "bjtjBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bjtjUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 2)) {
			Bjtj bjtj = bjtjService.loadBjtj(id);
			bjtj.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			bjtj.setGengxinshijian(curdate);
			bjtj.setShuming(shuming);
			bjtj.setLianjie(lianjie);
			bjtjService.updateBjtj(bjtj);
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
