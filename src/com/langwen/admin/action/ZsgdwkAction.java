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
import com.langwen.admin.bean.Zsgdwk;
import com.langwen.admin.service.ZsgdwkService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZsgdwkAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String XZZXSHOWALL = "zsgdwkShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private ZsgdwkService zsgdwkService = new ZsgdwkService();
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

	private int pageCount = (double) (zsgdwkService.allZsgdwk().size())
			/ (double) 10 > (zsgdwkService.allZsgdwk().size() / 10) ? zsgdwkService
			.allZsgdwk().size() / 10 + 1
			: zsgdwkService.allZsgdwk().size() / 10;

	public String zsgdwkShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allZsgdwks", zsgdwkService.pageZsgdwk(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allZsgdwks", zsgdwkService.pageZsgdwk(1));
			}
		} else {
			session.put("page", page);
			session.put("allZsgdwks", zsgdwkService.pageZsgdwk(page));
		}
		return "allZsgdwks";
	}

	// 上一页
	public String zsgdwkSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZsgdwks", zsgdwkService.pageZsgdwk(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allZsgdwks";
	}

	// 下一页
	public String zsgdwkXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZsgdwks", zsgdwkService.pageZsgdwk(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allZsgdwks";
	}

	public String zsgdwkMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zsgdwkService.moveUpZsgdwk(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zsgdwkService.moveDownZsgdwk(id);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			File df1 = new File(getSavePath() + "\\"
					+ zsgdwkService.loadZsgdwk(id).getTupian());
			df1.delete();
			zsgdwkService.deleteZsgdwk(id);
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

	public String zsgdwkRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			zsgdwkService.rePaixunZsgdwk();
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			return "zsgdwkTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			String filterResult = filterType(getTupianTypes().split(","));
			if (typelimit == 1 && filterResult != null) {
				addActionError("文件类型错误");
				return ERROR;
			}
			Zsgdwk zsgdwk = new Zsgdwk();
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
			zsgdwk.setTupian(newName1);
			zsgdwk.setTianjiaren(gly.getXingming());
			zsgdwk.setShuming(shuming);
			zsgdwk.setChubanshe(chubanshe);
			zsgdwk.setLianjie(lianjie);
			zsgdwkService.addZsgdwk(zsgdwk);
			return XZZXSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			session.put("zsgdwkBianji", zsgdwkService.loadZsgdwk(id));
			return "zsgdwkBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zsgdwkUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 5)) {
			Zsgdwk zsgdwk = zsgdwkService.loadZsgdwk(id);
			if (getUpload() != null && getUpload().size() == 1) {
				File df1 = new File(getSavePath() + "\\"
						+ zsgdwkService.loadZsgdwk(id).getTupian());
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
				zsgdwk.setTupian(newName1);
			}
			zsgdwk.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zsgdwk.setGengxinshijian(curdate);
			zsgdwk.setShuming(shuming);
			zsgdwk.setChubanshe(chubanshe);
			zsgdwk.setLianjie(lianjie);
			zsgdwkService.updateZsgdwk(zsgdwk);
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
