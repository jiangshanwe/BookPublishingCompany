package com.langwen.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Yssq;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.YssqService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class YssqAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private YssqService yssqService = new YssqService();
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

	public String yssqShow() {
		Yssq yssq = yssqService.loadYssq();
		session.put("yssqShow", yssq);
		return "yssqShow";
	}

	public String yssqBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 17)) {
			session.put("yssqBianji", yssqService.loadYssq());
			return "yssqBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String yssqUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 17)) {
			Yssq yssq = new Yssq();
			if (upload != null) {
				File df = new File(getSavePath() + "\\"
						+ yssqService.loadYssq().getFujian());
				df.delete();
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + uploadFileName);
				FileInputStream fis = new FileInputStream(getUpload());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				yssq.setFujian(uploadFileName);
			} else {
				yssq.setFujian(yssqService.loadYssq().getFujian());
			}
			yssq.setId(1);
			yssq.setBiaoti(biaoti);
			yssq.setNeirong(neirong);
			yssq.setXiugairen(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			yssq.setXiugaishijian(curdate);
			yssqService.updateYssq(yssq);
			return "reYssqShow";
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
