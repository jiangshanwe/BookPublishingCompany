package com.langwen.admin.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Lxwm;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.LxwmService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LxwmAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String LWJJSHOWALL = "lxwmShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private LxwmService lxwmService = new LxwmService();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String xiangxi;
	private int zhiding;

	public String lxwmShowall() {
		session.put("allLxwms", lxwmService.allLxwm());
		return "allLxwms";
	}

	public String lxwmTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			return "lxwmTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmAdd() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			Lxwm lxwm = new Lxwm(biaoti, xiangxi);
			if (zhiding == 1) {
				lxwm.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			lxwm.setFaburen(gly.getXingming());
			lxwm.setZhiding(zhiding);
			lxwmService.addLxwm(lxwm);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			Lxwm lxwm = lxwmService.loadLxwm(id);
			lxwm.setZhiding(1);
			lxwm.setZhidingshijian(new Timestamp(new Date().getTime()));
			lxwmService.updateLxwm(lxwm);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmQxZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			Lxwm lxwm = lxwmService.loadLxwm(id);
			lxwm.setZhiding(0);
			lxwm.setZhidingshijian(null);
			lxwmService.updateLxwm(lxwm);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmShanchu() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			lxwmService.deleteLxwm(id);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmBianji() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			session.put("lxwmBianji", lxwmService.loadLxwm(id));
			return "lxwmBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmUpdate() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 27)) {
			Lxwm lxwm = lxwmService.loadLxwm(id);
			lxwm.setBiaoti(biaoti);
			lxwm.setXiangxi(xiangxi);
			lxwm.setZhiding(zhiding);
			if (zhiding == 1) {
				lxwm.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			lxwm.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			lxwm.setGengxinshijian(curdate);
			lxwmService.updateLxwm(lxwm);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lxwmYulan(){
		Lxwm lxwm = lxwmService.loadLxwm(id);
		session.put("lxwmYulan", lxwm);
		return "lxwmYulan";
	}
	
	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getXiangxi() {
		return xiangxi;
	}

	public void setXiangxi(String xiangxi) {
		this.xiangxi = xiangxi;
	}

	public int getZhiding() {
		return zhiding;
	}

	public void setZhiding(int zhiding) {
		this.zhiding = zhiding;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
