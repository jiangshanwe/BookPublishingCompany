package com.langwen.admin.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Rczp;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.RczpService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RczpAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String LWJJSHOWALL = "rczpShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private RczpService rczpService = new RczpService();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String xiangxi;
	private int zhiding;

	public String rczpShowall() {
		session.put("allRczps", rczpService.allRczp());
		return "allRczps";
	}

	public String rczpTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			return "rczpTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpAdd() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			Rczp rczp = new Rczp(biaoti, xiangxi);
			if (zhiding == 1) {
				rczp.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			rczp.setFaburen(gly.getXingming());
			rczp.setZhiding(zhiding);
			rczpService.addRczp(rczp);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			Rczp rczp = rczpService.loadRczp(id);
			rczp.setZhiding(1);
			rczp.setZhidingshijian(new Timestamp(new Date().getTime()));
			rczpService.updateRczp(rczp);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpQxZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			Rczp rczp = rczpService.loadRczp(id);
			rczp.setZhiding(0);
			rczp.setZhidingshijian(null);
			rczpService.updateRczp(rczp);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpShanchu() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			rczpService.deleteRczp(id);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpBianji() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			session.put("rczpBianji", rczpService.loadRczp(id));
			return "rczpBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpUpdate() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 26)) {
			Rczp rczp = rczpService.loadRczp(id);
			rczp.setBiaoti(biaoti);
			rczp.setXiangxi(xiangxi);
			rczp.setZhiding(zhiding);
			if (zhiding == 1) {
				rczp.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			rczp.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			rczp.setGengxinshijian(curdate);
			rczpService.updateRczp(rczp);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String rczpYulan(){
		Rczp rczp = rczpService.loadRczp(id);
		session.put("rczpYulan", rczp);
		return "rczpYulan";
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
