package com.langwen.admin.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Cbtd;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.CbtdService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CbtdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String LWJJSHOWALL = "cbtdShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private CbtdService cbtdService = new CbtdService();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private Integer id;
	private String biaoti;
	private String xiangxi;
	private int zhiding;

	public String cbtdShowall() {
		session.put("allCbtds", cbtdService.allCbtd());
		return "allCbtds";
	}

	public String cbtdTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			return "cbtdTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdAdd() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			Cbtd cbtd = new Cbtd(biaoti, xiangxi);
			if (zhiding == 1) {
				cbtd.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			cbtd.setFaburen(gly.getXingming());
			cbtd.setZhiding(zhiding);
			cbtdService.addCbtd(cbtd);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			Cbtd cbtd = cbtdService.loadCbtd(id);
			cbtd.setZhiding(1);
			cbtd.setZhidingshijian(new Timestamp(new Date().getTime()));
			cbtdService.updateCbtd(cbtd);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdQxZhiding() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			Cbtd cbtd = cbtdService.loadCbtd(id);
			cbtd.setZhiding(0);
			cbtd.setZhidingshijian(null);
			cbtdService.updateCbtd(cbtd);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdShanchu() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			cbtdService.deleteCbtd(id);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdBianji() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			session.put("cbtdBianji", cbtdService.loadCbtd(id));
			return "cbtdBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdUpdate() {
		// Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			Cbtd cbtd = cbtdService.loadCbtd(id);
			cbtd.setBiaoti(biaoti);
			cbtd.setXiangxi(xiangxi);
			cbtd.setZhiding(zhiding);
			if (zhiding == 1) {
				cbtd.setZhidingshijian(new Timestamp(new Date().getTime()));
			}
			cbtd.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			cbtd.setGengxinshijian(curdate);
			cbtdService.updateCbtd(cbtd);
			return LWJJSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String cbtdYulan(){
		Cbtd cbtd = cbtdService.loadCbtd(id);
		session.put("cbtdYulan", cbtd);
		return "cbtdYulan";
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
