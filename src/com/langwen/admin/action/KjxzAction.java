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
import com.langwen.admin.bean.Kjxz;
import com.langwen.admin.service.KjxzService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class KjxzAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String KJXZSHOWALL = "kjxzShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private KjxzService kjxzService = new KjxzService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	// private List<File> upload;
	// private List<String> uploadContentType;
	// private List<String> uploadFileName;

	// private String savePath;
	// public String tupianTypes;

	private Integer id;
	private String biaoti;
	private String jieshao;
	private String dyts;
	private String fujian;
	private String bdfx;
	private String fxmm;
	private String tupian;
	private Integer paixu;
	// private int typelimit;

	private int page;

	private int pageCount = (double) (kjxzService.allKjxz().size())
			/ (double) 10 > (kjxzService.allKjxz().size() / 10) ? kjxzService
			.allKjxz().size() / 10 + 1 : kjxzService.allKjxz().size() / 10;

	public String kjxzShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allKjxzs", kjxzService.pageKjxz(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allKjxzs", kjxzService.pageKjxz(1));
			}
		} else {
			session.put("page", page);
			session.put("allKjxzs", kjxzService.pageKjxz(page));
		}
		return "allKjxzs";
	}

	// 上一页
	public String kjxzSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allKjxzs", kjxzService.pageKjxz(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allKjxzs";
	}

	// 下一页
	public String kjxzXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allKjxzs", kjxzService.pageKjxz(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allKjxzs";
	}

	public String kjxzMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			kjxzService.moveUpKjxz(id);
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			kjxzService.moveDownKjxz(id);
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			// File df1 = new File(getSavePath() + "\\"
			// + kjxzService.loadKjxz(id).getTupian());
			// File df2 = new File(getSavePath() + "\\"
			// + kjxzService.loadKjxz(id).getFujian());
			// df1.delete();
			// df2.delete();
			kjxzService.deleteKjxz(id);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			kjxzService.rePaixunKjxz();
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			return "kjxzTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			Kjxz kjxz = new Kjxz();
			// String filterResult = filterType(getTupianTypes().split(","));
			// if (typelimit == 1 && filterResult != null) {
			// addActionError("文件类型错误");
			// return ERROR;
			// }
			// String newName1 = UUID.randomUUID()
			// + uploadFileName.get(0).substring(
			// uploadFileName.get(0).lastIndexOf("."));
			// FileOutputStream fos1 = new FileOutputStream(getSavePath() + "\\"
			// + newName1);
			// FileInputStream fis1 = new FileInputStream(getUpload().get(0));
			// byte[] buffer1 = new byte[1024];
			// int len1 = 0;
			// while ((len1 = fis1.read(buffer1)) > 0) {
			// fos1.write(buffer1, 0, len1);
			// }
			// kjxz.setTupian(newName1);
			kjxz.setTianjiaren(gly.getXingming());
			kjxz.setBiaoti(biaoti);
			kjxz.setJieshao(jieshao);
			kjxz.setDyts(dyts);
			kjxz.setBdfx(bdfx);
			kjxz.setFxmm(fxmm);
			kjxzService.addKjxz(kjxz);
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzYulan() {
		session.put("kjxzYulan", kjxzService.loadKjxz(id));
		return "kjxzYulan";
	}

	public String kjxzBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			session.put("kjxzBianji", kjxzService.loadKjxz(id));
			return "kjxzBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String kjxzUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 19)) {
			Kjxz kjxz = kjxzService.loadKjxz(id);
			// if (getUpload() != null && getUpload().size() == 1) {
			// File df1 = new File(getSavePath() + "\\"
			// + kjxzService.loadKjxz(id).getTupian());
			// df1.delete();
			// String filterResult = filterType(getTupianTypes().split(","));
			// if (typelimit == 1 && filterResult != null) {
			// addActionError("文件类型错误");
			// return ERROR;
			// }
			// String newName1 = UUID.randomUUID()
			// + uploadFileName.get(0).substring(
			// uploadFileName.get(0).lastIndexOf("."));
			// FileOutputStream fos1 = new FileOutputStream(getSavePath()
			// + "\\" + newName1);
			// FileInputStream fis1 = new FileInputStream(getUpload().get(0));
			// byte[] buffer1 = new byte[1024];
			// int len1 = 0;
			// while ((len1 = fis1.read(buffer1)) > 0) {
			// fos1.write(buffer1, 0, len1);
			// }
			// kjxz.setTupian(newName1);
			// }
			kjxz.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			kjxz.setGengxinshijian(curdate);
			kjxz.setBiaoti(biaoti);
			kjxz.setBdfx(bdfx);
			kjxz.setDyts(dyts);
			kjxz.setFxmm(fxmm);
			kjxz.setJieshao(jieshao);
			kjxzService.updateKjxz(kjxz);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return KJXZSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	// public List<File> getUpload() {
	// return upload;
	// }
	//
	// public void setUpload(List<File> upload) {
	// this.upload = upload;
	// }
	//
	// public List<String> getUploadContentType() {
	// return uploadContentType;
	// }
	//
	// public void setUploadContentType(List<String> uploadContentType) {
	// this.uploadContentType = uploadContentType;
	// }
	//
	// public List<String> getUploadFileName() {
	// return uploadFileName;
	// }
	//
	// public void setUploadFileName(List<String> uploadFileName) {
	// this.uploadFileName = uploadFileName;
	// }
	//
	// public String getSavePath() {
	// return ServletActionContext.getRequest().getRealPath(savePath);
	// }
	//
	// public void setSavePath(String savePath) {
	// this.savePath = savePath;
	// }
	//
	// public String getTupianTypes() {
	// return tupianTypes;
	// }
	//
	// public void setTupianTypes(String tupianTypes) {
	// this.tupianTypes = tupianTypes;
	// }

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

	// public String filterType(String[] types) {
	// int re = 0;
	// for (int i = 0; i < uploadContentType.size(); i++) {
	// String fileType = getUploadContentType().get(i);
	// for (String type : types) {
	// if (type.equals(fileType)) {
	// re++;
	// }
	// }
	// }
	// if (re == getUploadContentType().size()) {
	// return null;
	// }
	// return INPUT;
	// }
	//
	// public int getTypelimit() {
	// return typelimit;
	// }
	//
	// public void setTypelimit(int typelimit) {
	// this.typelimit = typelimit;
	// }

}
