package com.langwen.admin.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Jxfwjs;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.JxfwjsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JxfwjsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private JxfwjsService jxfwjsService = new JxfwjsService();
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;

	private String biaoti;
	private String neirong;
	private String fujian;

	public String jxfwjsShow() {
		Jxfwjs jxfwjs = jxfwjsService.loadJxfwjs();
		session.put("jxfwjsShow", jxfwjs);
		return "jxfwjsShow";
	}

	public String jxfwjsBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 16)) {
			session.put("jxfwjsBianji", jxfwjsService.loadJxfwjs());
			return "jxfwjsBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String jxfwjsUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 16)) {
			Jxfwjs jxfwjs = new Jxfwjs();
			jxfwjs.setId(1);
			jxfwjs.setBiaoti(biaoti);
			jxfwjs.setNeirong(neirong);
			jxfwjs.setXiugairen(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			jxfwjs.setXiugaishijian(curdate);
			jxfwjsService.updateJxfwjs(jxfwjs);
			return "reJxfwjsShow";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getNeirong() {
		return neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	public String getFujian() {
		return fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
