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
import com.langwen.admin.bean.Zszzjy;
import com.langwen.admin.service.ZszzjyService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZszzjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "zszzjyShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private ZszzjyService zszzjyService = new ZszzjyService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	private String savePath;
	public String tupianTypes;

	private Integer id;
	private String shuming;
	private String chubanshe;
	private String tupian;
	private String lianjie;
	private Integer paixu;
	private int typelimit;

	private int page;

	private int pageCount = (double) (zszzjyService.allZszzjy().size())
			/ (double) 10 > (zszzjyService.allZszzjy().size() / 10) ? zszzjyService
			.allZszzjy().size() / 10 + 1
			: zszzjyService.allZszzjy().size() / 10;

	public String zszzjyShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allZszzjys", zszzjyService.pageZszzjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allZszzjys", zszzjyService.pageZszzjy(1));
			}
		} else {
			session.put("page", page);
			session.put("allZszzjys", zszzjyService.pageZszzjy(page));
		}
		return "allZszzjys";
	}

	// 上一页
	public String zszzjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZszzjys", zszzjyService.pageZszzjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allZszzjys";
	}

	// 下一页
	public String zszzjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZszzjys", zszzjyService.pageZszzjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allZszzjys";
	}

	public String zszzjyMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zszzjyService.moveUpZszzjy(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zszzjyService.moveDownZszzjy(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			File df1 = new File(getSavePath() + "\\"
					+ zszzjyService.loadZszzjy(id).getTupian());
			df1.delete();
			zszzjyService.deleteZszzjy(id);
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

	public String zszzjyRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			zszzjyService.rePaixunZszzjy();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			return "zszzjyTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			String filterResult = filterType(getTupianTypes().split(","));
			if (typelimit == 1 && filterResult != null) {
				addActionError("文件类型错误");
				return ERROR;
			}
			Zszzjy zszzjy = new Zszzjy();
			String newName1 = UUID.randomUUID()
					+ uploadFileName.get(0).substring(
							uploadFileName.get(0).lastIndexOf("."));
			FileOutputStream fos1 = new FileOutputStream(getSavePath() + "\\"
					+ newName1);
			FileInputStream fis1 = new FileInputStream(getUpload().get(0));
			byte[] buffer1 = new byte[1024];
			int len1 = 0;
			while ((len1 = fis1.read(buffer1)) > 0) {
				fos1.write(buffer1, 0, len1);
			}
			zszzjy.setTupian(newName1);
			zszzjy.setTianjiaren(gly.getXingming());
			zszzjy.setShuming(shuming);
			zszzjy.setChubanshe(chubanshe);
			zszzjy.setLianjie(lianjie);
			zszzjyService.addZszzjy(zszzjy);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			session.put("zszzjyBianji", zszzjyService.loadZszzjy(id));
			return "zszzjyBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zszzjyUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 8)) {
			Zszzjy zszzjy = zszzjyService.loadZszzjy(id);
			if (getUpload() != null && getUpload().size() == 1) {
				File df1 = new File(getSavePath() + "\\"
						+ zszzjyService.loadZszzjy(id).getTupian());
				df1.delete();
				String filterResult = filterType(getTupianTypes().split(","));
				if (typelimit == 1 && filterResult != null) {
					addActionError("文件类型错误");
					return ERROR;
				}
				String newName1 = UUID.randomUUID()
						+ uploadFileName.get(0).substring(
								uploadFileName.get(0).lastIndexOf("."));
				FileOutputStream fos1 = new FileOutputStream(getSavePath() + "\\"
						+ newName1);
				FileInputStream fis1 = new FileInputStream(getUpload().get(0));
				byte[] buffer1 = new byte[1024];
				int len1 = 0;
				while ((len1 = fis1.read(buffer1)) > 0) {
					fos1.write(buffer1, 0, len1);
				}
				zszzjy.setTupian(newName1);
			}
			zszzjy.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zszzjy.setGengxinshijian(curdate);
			zszzjy.setShuming(shuming);
			zszzjy.setChubanshe(chubanshe);
			zszzjy.setLianjie(lianjie);
			zszzjyService.updateZszzjy(zszzjy);
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShuming() {
		return shuming;
	}

	public void setShuming(String shuming) {
		this.shuming = shuming;
	}

	public String getChubanshe() {
		return chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public String getLianjie() {
		return lianjie;
	}

	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}

}
