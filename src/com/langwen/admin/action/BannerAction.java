package com.langwen.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.langwen.admin.bean.Banner;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.BannerService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BannerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String BANNERSHOWALL = "bannerShowall";

	private BannerService bannerService = new BannerService();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Map<String, Object> session = ActionContext.getContext().getSession();

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	private Integer id;
	private int typelimit;
	private int zhiding;
	private String lianjie;
	private String miaoshu;
	private String tupianTypes;;

	public String bannerShowall() {
		session.put("allBanners", bannerService.allBanners());
		return "allBanners";
	}

	public String bannerTianjia() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 1)) {
			return "bannerTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bannerAdd() throws IOException {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 1)) {
			String filterResult = filterType(getTupianTypes().split(","));
			if (typelimit == 1 && filterResult != null) {
				addActionError("文件类型错误");
				return ERROR;
			}
			String newName = UUID.randomUUID()
					+ uploadFileName.substring(uploadFileName.lastIndexOf("."));
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ newName);
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			Banner banner = new Banner();
			if (zhiding == 1) {
				banner.setZhiding(1);
				banner.setZhidingshijian(new Timestamp(new Date().getTime()));
			} else {
				banner.setZhiding(0);
			}
			banner.setLianjie(lianjie);
			banner.setMiaoshu(miaoshu);
			banner.setTupian(newName);
			banner.setFaburen(gly.getXingming());
			bannerService.addBanner(banner);
			return BANNERSHOWALL;
		} else {
			addActionError("无权限操作！");
			return ERROR;
		}
	}

	public String bannerQxZhiding() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 1)) {
			Banner banner = bannerService.loadBanner(id);
			banner.setZhiding(0);
			banner.setZhidingshijian(null);
			bannerService.updateBanner(banner);
			return BANNERSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bannerZhiding() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 1)) {
			Banner banner = bannerService.loadBanner(id);
			banner.setZhiding(1);
			banner.setZhidingshijian(new Timestamp(new Date().getTime()));
			bannerService.updateBanner(banner);
			return BANNERSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String bannerShanchu() {
		Guanliyuan gly = (Guanliyuan) session.get("gly");
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 1)) {
			File df = new File(getSavePath() + "\\"
					+ bannerService.loadBanner(id).getTupian());
			df.delete();
			bannerService.deleteBanner(id);
			return BANNERSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public int getTypelimit() {
		return typelimit;
	}

	public void setTypelimit(int typelimit) {
		this.typelimit = typelimit;
	}

	public int getZhiding() {
		return zhiding;
	}

	public void setZhiding(int zhiding) {
		this.zhiding = zhiding;
	}

	public String getLianjie() {
		return lianjie;
	}

	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
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

	public String getTupianTypes() {
		return tupianTypes;
	}

	public void setTupianTypes(String tupianTypes) {
		this.tupianTypes = tupianTypes;
	}

	public String filterType(String[] types) {
		String fileType = getUploadContentType();
		for (String type : types) {
			if (type.equals(fileType)) {
				return null;
			}
		}
		return INPUT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
