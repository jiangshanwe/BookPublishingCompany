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
import com.langwen.admin.bean.Hyyq;
import com.langwen.admin.service.GdlgService;
import com.langwen.admin.service.GuanliyuanService;
import com.langwen.admin.service.HyyqService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HyyqAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String HYYQSHOWALL = "hyyqShowall";

	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");
	private HyyqService hyyqService = new HyyqService();
	private String savePath;

	private Integer id;
	private String biaoti;
	private String neirong;
	private String fujian;
	private String tianjiaren;
	private String tianjiashijian;
	private String gengxinren;
	private String gengxinshijian;
	private Integer paixu;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	private int page;

	private int pageCount = (double) (hyyqService.allHyyq().size())
			/ (double) 10 > (hyyqService.allHyyq().size() / 10) ? hyyqService
			.allHyyq().size() / 10 + 1 : hyyqService.allHyyq().size() / 10;

	public String hyyqShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allHyyqs", hyyqService.pageHyyq(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allHyyqs", hyyqService.pageHyyq(1));
			}
		} else {
			session.put("page", page);
			session.put("allHyyqs", hyyqService.pageHyyq(page));
		}
		return "allHyyqs";
	}

	// 上一页
	public String hyyqSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allHyyqs", hyyqService.pageHyyq(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allHyyqs";
	}

	// 下一页
	public String hyyqXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allHyyqs", hyyqService.pageHyyq(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allHyyqs";
	}

	public String hyyqMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			hyyqService.moveUpHyyq(id);
			return HYYQSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			hyyqService.moveDownHyyq(id);
			return HYYQSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			File df1 = new File(getSavePath() + "\\"
					+ hyyqService.loadHyyq(id).getFujian());
			df1.delete();
			hyyqService.deleteHyyq(id);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return HYYQSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			hyyqService.rePaixuHyyq();
			return HYYQSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			hyyqService.rePaixuHyyq();
			return "hyyqTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			Hyyq hyyq = new Hyyq();
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ uploadFileName);
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			hyyq.setFujian(uploadFileName);
			hyyq.setBiaoti(biaoti);
			hyyq.setNeirong(neirong);
			hyyq.setTianjiaren(gly.getXingming());
			hyyqService.addHyyq(hyyq);
			return HYYQSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqYulan() {
		session.put("hyyqYulan", hyyqService.loadHyyq(id));
		return "hyyqYulan";
	}

	public String hyyqBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 18)) {
			session.put("hyyqBianji", hyyqService.loadHyyq(id));
			return "hyyqBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String hyyqUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 21)) {
			Hyyq hyyq = hyyqService.loadHyyq(id);
			if (upload != null) {
				File df = new File(getSavePath() + "\\"
						+ hyyqService.loadHyyq(id).getFujian());
				df.delete();
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + uploadFileName);
				FileInputStream fis = new FileInputStream(getUpload());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				hyyq.setFujian(uploadFileName);
			} else {
				hyyq.setFujian(hyyqService.loadHyyq(id).getFujian());
			}
			hyyq.setId(id);
			hyyq.setBiaoti(biaoti);
			hyyq.setNeirong(neirong);
			hyyq.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			hyyq.setGengxinshijian(curdate);
			hyyqService.updateHyyq(hyyq);
			return HYYQSHOWALL;
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

	public HyyqService getHyyqService() {
		return hyyqService;
	}

	public void setHyyqService(HyyqService hyyqService) {
		this.hyyqService = hyyqService;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
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

	public String getTianjiaren() {
		return tianjiaren;
	}

	public void setTianjiaren(String tianjiaren) {
		this.tianjiaren = tianjiaren;
	}

	public String getTianjiashijian() {
		return tianjiashijian;
	}

	public void setTianjiashijian(String tianjiashijian) {
		this.tianjiashijian = tianjiashijian;
	}

	public String getGengxinren() {
		return gengxinren;
	}

	public void setGengxinren(String gengxinren) {
		this.gengxinren = gengxinren;
	}

	public String getGengxinshijian() {
		return gengxinshijian;
	}

	public void setGengxinshijian(String gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}

	public Integer getPaixu() {
		return paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
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
