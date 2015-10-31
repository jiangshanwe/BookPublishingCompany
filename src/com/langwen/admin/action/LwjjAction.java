package com.langwen.admin.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Lwjj;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.LwjjService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LwjjAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String LWJJSHOWALL = "lwjjShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private LwjjService lwjjService = new LwjjService();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String xiangxi;
	private int zhiding;

	public String lwjjShowall() {
		session.put("allLwjjs", lwjjService.allLwjj());
		return "allLwjjs";
	}

	public String lwjjTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			return "lwjjTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjAdd() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			Lwjj lwjj = new Lwjj(biaoti, xiangxi);
			if (zhiding == 1) {
				lwjj.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			lwjj.setFaburen(gly.getXingming());
			lwjj.setZhiding(zhiding);
			lwjjService.addLwjj(lwjj);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			Lwjj lwjj = lwjjService.loadLwjj(id);
			lwjj.setZhiding(1);
			lwjj.setZhidingshijian(new Timestamp(new Date().getTime()));
			lwjjService.updateLwjj(lwjj);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjQxZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			Lwjj lwjj = lwjjService.loadLwjj(id);
			lwjj.setZhiding(0);
			lwjj.setZhidingshijian(null);
			lwjjService.updateLwjj(lwjj);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjShanchu() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			lwjjService.deleteLwjj(id);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjBianji() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			session.put("lwjjBianji", lwjjService.loadLwjj(id));
			return "lwjjBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjUpdate() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 25)) {
			Lwjj lwjj = lwjjService.loadLwjj(id);
			lwjj.setBiaoti(biaoti);
			lwjj.setXiangxi(xiangxi);
			lwjj.setZhiding(zhiding);
			if (zhiding == 1) {
				lwjj.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			lwjj.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			lwjj.setGengxinshijian(curdate);
			lwjjService.updateLwjj(lwjj);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String lwjjYulan(){
		Lwjj lwjj = lwjjService.loadLwjj(id);
		session.put("lwjjYulan", lwjj);
		return "lwjjYulan";
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
