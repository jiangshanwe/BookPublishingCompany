package com.langwen.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.bean.Xzzx;
import com.langwen.admin.service.XzzxService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class XzzxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "xzzxShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private XzzxService xzzxService = new XzzxService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	private String savePath;
	public String tupianTypes;

	private Integer id;
	private String biaoti;
	private String jieshao;
	private String dyts;
	private String fujian;
	private String bdfx;
	private String fxmm;
	private String tupian;
	private Integer paixu;
	private int typelimit;

	private int page;

	private int pageCount = (double) (xzzxService.allXzzx().size())
			/ (double) 10 > (xzzxService.allXzzx().size() / 10) ? xzzxService
			.allXzzx().size() / 10 + 1 : xzzxService.allXzzx().size() / 10;

	public String xzzxShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allXzzxs", xzzxService.pageXzzx(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allXzzxs", xzzxService.pageXzzx(1));
			}
		} else {
			session.put("page", page);
			session.put("allXzzxs", xzzxService.pageXzzx(page));
		}
		return "allXzzxs";
	}

	// 上一页
	public String xzzxSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allXzzxs", xzzxService.pageXzzx(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allXzzxs";
	}

	// 下一页
	public String xzzxXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allXzzxs", xzzxService.pageXzzx(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allXzzxs";
	}

	public String xzzxMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			xzzxService.moveUpXzzx(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			xzzxService.moveDownXzzx(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			File df1 = new File(getSavePath() + "\\"
					+ xzzxService.loadXzzx(id).getTupian());
			File df2 = new File(getSavePath() + "\\"
					+ xzzxService.loadXzzx(id).getFujian());
			df1.delete();
			df2.delete();
			xzzxService.deleteXzzx(id);
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

	public String xzzxRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			xzzxService.rePaixunXzzx();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			return "xzzxTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			Xzzx xzzx = new Xzzx();
			String newName2 = uploadFileName.get(0);
			FileOutputStream fos2 = new FileOutputStream(getSavePath() + "\\"
					+ newName2);
			FileInputStream fis2 = new FileInputStream(getUpload().get(0));
			byte[] buffer2 = new byte[1024];
			int len2 = 0;
			while ((len2 = fis2.read(buffer2)) > 0) {
				fos2.write(buffer2, 0, len2);
			}
			xzzx.setFujian(newName2);
			xzzx.setTianjiaren(gly.getXingming());
			xzzx.setBiaoti(biaoti);
			xzzx.setJieshao(jieshao);
			xzzx.setDyts(dyts);
			xzzx.setBdfx(bdfx);
			xzzx.setFxmm(fxmm);
			xzzxService.addXzzx(xzzx);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxYulan() {
		session.put("xzzxYulan", xzzxService.loadXzzx(id));
		return "xzzxYulan";
	}

	public String xzzxBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			session.put("xzzxBianji", xzzxService.loadXzzx(id));
			return "xzzxBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String xzzxUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 20)) {
			Xzzx xzzx = xzzxService.loadXzzx(id);
			if (getUpload() != null && getUpload().size() == 1) {
				File df1 = new File(getSavePath() + "\\"
						+ xzzxService.loadXzzx(id).getFujian());
				df1.delete();
				String newName2 = uploadFileName.get(0);
				FileOutputStream fos2 = new FileOutputStream(getSavePath()
						+ "\\" + newName2);
				FileInputStream fis2 = new FileInputStream(getUpload().get(0));
				byte[] buffer2 = new byte[1024];
				int len2 = 0;
				while ((len2 = fis2.read(buffer2)) > 0) {
					fos2.write(buffer2, 0, len2);
				}
				xzzx.setFujian(newName2);
			}
			xzzx.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			xzzx.setGengxinshijian(curdate);
			xzzx.setBiaoti(biaoti);
			xzzx.setBdfx(bdfx);
			xzzx.setDyts(dyts);
			xzzx.setFxmm(fxmm);
			xzzx.setJieshao(jieshao);
			xzzxService.updateXzzx(xzzx);
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

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
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

	public String getJieshao() {
		return jieshao;
	}

	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}

	public String getDyts() {
		return dyts;
	}

	public void setDyts(String dyts) {
		this.dyts = dyts;
	}

	public String getFujian() {
		return fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	public String getBdfx() {
		return bdfx;
	}

	public void setBdfx(String bdfx) {
		this.bdfx = bdfx;
	}

	public String getFxmm() {
		return fxmm;
	}

	public void setFxmm(String fxmm) {
		this.fxmm = fxmm;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public Integer getPaixu() {
		return paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
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

	public String filterType(String[] types) {
		int re = 0;
		String fileType = getUploadContentType().get(0);
		for (String type : types) {
			if (type.equals(fileType)) {
				re++;
			}
		}
		if (re == 1) {
			return null;
		}
		return INPUT;
	}

	public int getTypelimit() {
		return typelimit;
	}

	public void setTypelimit(int typelimit) {
		this.typelimit = typelimit;
	}

}
