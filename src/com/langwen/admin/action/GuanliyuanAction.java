package com.langwen.admin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.langwen.admin.bean.Glyqx;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Qx;
import com.langwen.admin.service.GlyqxService;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.QxService;
import com.langwen.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GuanliyuanAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESSMSG = "successMsg";
	private static final String GLYSHOWALL = "glyShowall";

	private Integer id;
	private String gonghao;
	private String mima; // now mima
	private String nmima; // new password
	private String cmima; // confirm password

	private Integer status;
	private String xingming;

	private String tmima; // 添加管理员的设置密码
	private String tcmima;

	private Integer[] qxList;

	GuanliyuanService guanliyuanService = new GuanliyuanService();
	QxService qxService = new QxService();
	GlyqxService glyqxService = new GlyqxService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	public String glyLogin() {
		Guanliyuan gly = guanliyuanService.guanliyuanLogin(gonghao, mima);
		if (gly != null) {
			if (gly.getStatus() == 1) {
				session.put("gly", gly);
				return SUCCESS;
			} else {
				addActionError("该账号已被冻结，请联系系统管理员");
				return ERROR;
			}
		} else {
			addActionError("输入的账号或者密码错误，请重新输入");
			return ERROR;
		}
	}

	public String glyLogout() {
		session.clear();
		return "reLogin";
	}

	public String glyXgmm() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (MD5Util.getMD5(mima + "BBC99F82DA25C6004229639D455BAC21").equals(
				gly.getMima())) {
			if (nmima.equals(cmima)) {
				gly.setMima(MD5Util.getMD5(cmima
						+ "BBC99F82DA25C6004229639D455BAC21"));
				guanliyuanService.updateGuanliyuan(gly);
				addActionMessage("修改密码成功");
				return SUCCESSMSG;
			} else {
				addActionError("两次新密码输入不一致");
				return ERROR;
			}
		} else {
			addActionError("当前密码输入错误");
			return ERROR;
		}
	}

	public String glyShowall() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			session.put("allGlys", guanliyuanService.allGuanliyuan());
			return "allGlys";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyShanchu() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			guanliyuanService.deleteGuanliyuan(id);
			return GLYSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyTianjia() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			return "glyTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyQxpz() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			Guanliyuan glyQxpz = guanliyuanService.loadGuanliyuan(id);
			session.put("glyQxpz", glyQxpz);
			List<Qx> qll = new ArrayList<Qx>(glyQxpz.getQxs());
			List<Integer> ql = new ArrayList<Integer>();
			for (int i = 0; i < qll.size(); i++) {
				ql.add(qll.get(i).getId());
			}
			session.put("ql", ql);
			session.put("qxAll", qxService.allQx());
			return "glyQxpz";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyAdd() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			if (tcmima == null || tmima == null || tmima.equals("")
					|| tcmima.equals("")) {
				addActionError("请输入完整的密码及确认密码");
				return ERROR;
			} else if (!tmima.equals(tcmima)) {
				addActionError("两次密码输入不一致");
				return ERROR;
			} else {
				Guanliyuan ngly = new Guanliyuan();
				ngly.setGonghao(gonghao);
				ngly.setMima(tmima);
				ngly.setXingming(xingming);
				ngly.setStatus(status);
				guanliyuanService.addGuanliyuan(ngly);
				return GLYSHOWALL;
			}
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyJinyong() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			Guanliyuan ugly = guanliyuanService.loadGuanliyuan(id);
			ugly.setStatus(status);
			guanliyuanService.updateGuanliyuan(ugly);
			return GLYSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String glyUpdate() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 28)) {
			glyqxService.qkGlyqx(id);
			for (int i = 0; i < qxList.length; i++) {
				Glyqx glyqx = new Glyqx();
				glyqx.setGlyid(id);
				glyqx.setQxid(qxList[i]);
				glyqxService.addGlyqx(glyqx);
			}
			return GLYSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public GuanliyuanService getGuanliyuanService() {
		return guanliyuanService;
	}

	public void setGuanliyuanService(GuanliyuanService guanliyuanService) {
		this.guanliyuanService = guanliyuanService;
	}

	public String getGonghao() {
		return gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

	public String getMima() {
		return mima;
	}

	public void setMima(String mima) {
		this.mima = mima;
	}

	public String getCmima() {
		return cmima;
	}

	public void setCmima(String cmima) {
		this.cmima = cmima;
	}

	public String getNmima() {
		return nmima;
	}

	public void setNmima(String nmima) {
		this.nmima = nmima;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTmima() {
		return tmima;
	}

	public void setTmima(String tmima) {
		this.tmima = tmima;
	}

	public String getTcmima() {
		return tcmima;
	}

	public void setTcmima(String tcmima) {
		this.tcmima = tcmima;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public Integer[] getQxList() {
		return qxList;
	}

	public void setQxList(Integer[] qxList) {
		this.qxList = qxList;
	}

}
