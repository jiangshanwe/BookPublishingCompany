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
import com.langwen.admin.bean.Zsgzjy;
import com.langwen.admin.service.ZsgzjyService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZsgzjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "zsgzjyShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private ZsgzjyService zsgzjyService = new ZsgzjyService();
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

	private int pageCount = (double) (zsgzjyService.allZsgzjy().size())
			/ (double) 10 > (zsgzjyService.allZsgzjy().size() / 10) ? zsgzjyService
			.allZsgzjy().size() / 10 + 1
			: zsgzjyService.allZsgzjy().size() / 10;

	public String zsgzjyShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allZsgzjys", zsgzjyService.pageZsgzjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allZsgzjys", zsgzjyService.pageZsgzjy(1));
			}
		} else {
			session.put("page", page);
			session.put("allZsgzjys", zsgzjyService.pageZsgzjy(page));
		}
		return "allZsgzjys";
	}

	// 上一页
	public String zsgzjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZsgzjys", zsgzjyService.pageZsgzjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allZsgzjys";
	}

	// 下一页
	public String zsgzjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZsgzjys", zsgzjyService.pageZsgzjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allZsgzjys";
	}

	public String zsgzjyMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zsgzjyService.moveUpZsgzjy(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zsgzjyService.moveDownZsgzjy(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			File df1 = new File(getSavePath() + "\\"
					+ zsgzjyService.loadZsgzjy(id).getTupian());
			df1.delete();
			zsgzjyService.deleteZsgzjy(id);
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

	public String zsgzjyRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			zsgzjyService.rePaixunZsgzjy();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			return "zsgzjyTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			String filterResult = filterType(getTupianTypes().split(","));
			if (typelimit == 1 && filterResult != null) {
				addActionError("文件类型错误");
				return ERROR;
			}
			Zsgzjy zsgzjy = new Zsgzjy();
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
			zsgzjy.setTupian(newName1);
			zsgzjy.setTianjiaren(gly.getXingming());
			zsgzjy.setShuming(shuming);
			zsgzjy.setChubanshe(chubanshe);
			zsgzjy.setLianjie(lianjie);
			zsgzjyService.addZsgzjy(zsgzjy);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			session.put("zsgzjyBianji", zsgzjyService.loadZsgzjy(id));
			return "zsgzjyBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgzjyUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 7)) {
			Zsgzjy zsgzjy = zsgzjyService.loadZsgzjy(id);
			if (getUpload() != null && getUpload().size() == 1) {
				File df1 = new File(getSavePath() + "\\"
						+ zsgzjyService.loadZsgzjy(id).getTupian());
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
				zsgzjy.setTupian(newName1);
			}
			zsgzjy.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zsgzjy.setGengxinshijian(curdate);
			zsgzjy.setShuming(shuming);
			zsgzjy.setChubanshe(chubanshe);
			zsgzjy.setLianjie(lianjie);
			zsgzjyService.updateZsgzjy(zsgzjy);
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
