package com.langwen.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.langwen.admin.bean.Zzjy;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.ZzjyService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZzjyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String GDWKSHOWALL = "zzjyShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private ZzjyService zzjyService = new ZzjyService();
	Guanliyuan gly = (Guanliyuan) session.get("gly");

	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	private String savePath;
	public String tupianTypes;

	private Integer id;
	private String fenlei;
	private String fenlei2;
	private String shuming;
	private String zuozhe;
	private String chubanshe;
	private String isbn;
	private Integer banci;
	private String baozhuang;
	private Integer kaiben;
	private String chubanshijian;
	private Integer yeshu;
	private Float dingjia;
	private String neirongjianjie;
	private String zuozhejianjie;
	private String mulu;
	private String jingcaishuzhai;
	private String qianyan;
	private String tupian1;
	private String tupian2;
	private String tupian3;
	private String tupian4;
	private int typelimit;
	private int zhanshi;

	private int page;

	private int pageCount = (double) (zzjyService.allZzjy().size())
			/ (double) 10 > (zzjyService.allZzjy().size() / 10) ? zzjyService
			.allZzjy().size() / 10 + 1 : zzjyService.allZzjy().size() / 10;

	public String zzjyShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				setPage(ipage);
				session.put("page", ipage);
				session.put("allZzjys", zzjyService.pageZzjy(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allZzjys", zzjyService.pageZzjy(1));
			}
		} else {
			session.put("page", page);
			session.put("allZzjys", zzjyService.pageZzjy(page));
		}
		return "allZzjys";
	}

	// 上一页
	public String zzjySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZzjys", zzjyService.pageZzjy(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allZzjys";
	}

	// 下一页
	public String zzjyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allZzjys", zzjyService.pageZzjy(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allZzjys";
	}

	public String zzjyMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zzjyService.moveUpZzjy(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			zzjyService.moveDownZzjy(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			File df1 = new File(getSavePath() + "\\"
					+ zzjyService.loadZzjy(id).getTupian1());
			File df2 = new File(getSavePath() + "\\"
					+ zzjyService.loadZzjy(id).getTupian2());
			File df3 = new File(getSavePath() + "\\"
					+ zzjyService.loadZzjy(id).getTupian3());
			File df4 = new File(getSavePath() + "\\"
					+ zzjyService.loadZzjy(id).getTupian4());
			df1.delete();
			df2.delete();
			df3.delete();
			df4.delete();
			zzjyService.deleteZzjy(id);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			zzjyService.rePaixunZzjy();
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			return "zzjyTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			Zzjy zzjy = new Zzjy();
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
			zzjy.setTupian1(newName1);
			String newName2 = UUID.randomUUID()
					+ uploadFileName.get(1).substring(
							uploadFileName.get(1).lastIndexOf("."));
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\"
					+ newName2);
			FileInputStream fis = new FileInputStream(getUpload().get(1));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			zzjy.setTupian2(newName2);
			String newName3 = UUID.randomUUID()
					+ uploadFileName.get(2).substring(
							uploadFileName.get(2).lastIndexOf("."));
			FileOutputStream fos3 = new FileOutputStream(getSavePath() + "\\"
					+ newName3);
			FileInputStream fis3 = new FileInputStream(getUpload().get(2));
			byte[] buffer3 = new byte[3024];
			int len3 = 0;
			while ((len3 = fis3.read(buffer3)) > 0) {
				fos3.write(buffer3, 0, len3);
			}
			zzjy.setTupian3(newName3);
			String newName4 = UUID.randomUUID()
					+ uploadFileName.get(3).substring(
							uploadFileName.get(3).lastIndexOf("."));
			FileOutputStream fos4 = new FileOutputStream(getSavePath() + "\\"
					+ newName4);
			FileInputStream fis4 = new FileInputStream(getUpload().get(3));
			byte[] buffer4 = new byte[4024];
			int len4 = 0;
			while ((len4 = fis4.read(buffer4)) > 0) {
				fos4.write(buffer4, 0, len4);
			}
			zzjy.setTupian4(newName4);
			zzjy.setShuming(shuming);
			zzjy.setBanci(banci);
			zzjy.setBaozhuang(baozhuang);
			zzjy.setChubanshe(chubanshe);
			zzjy.setChubanshijian(chubanshijian);
			zzjy.setDingjia(dingjia);
			zzjy.setFenlei(fenlei);
			zzjy.setTianjiaren(gly.getXingming());
			zzjy.setFenlei2(fenlei2);
			zzjy.setIsbn(isbn);
			zzjy.setJingcaishuzhai(jingcaishuzhai);
			zzjy.setKaiben(kaiben);
			zzjy.setMulu(mulu);
			zzjy.setNeirongjianjie(neirongjianjie);
			zzjy.setQianyan(qianyan);
			zzjy.setYeshu(yeshu);
			zzjy.setZuozhe(zuozhe);
			zzjy.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				zzjy.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			zzjy.setZhanshi(zhanshi);
			zzjyService.addZzjy(zzjy);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			Zzjy zzjy = zzjyService.loadZzjy(id);
			zzjy.setZhanshi(1);
			zzjy.setZhanshishijian(new Timestamp(new Date().getTime()));
			zzjyService.updateZzjy(zzjy);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyQxZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			Zzjy zzjy = zzjyService.loadZzjy(id);
			zzjy.setZhanshi(0);
			zzjy.setZhanshishijian(null);
			zzjyService.updateZzjy(zzjy);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			session.put("zzjyBianji", zzjyService.loadZzjy(id));
			return "zzjyBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 14)) {
			Zzjy zzjy = zzjyService.loadZzjy(id);
			if (getUpload() != null && getUpload().size() == 4) {
				File df1 = new File(getSavePath() + "\\"
						+ zzjyService.loadZzjy(id).getTupian1());
				File df2 = new File(getSavePath() + "\\"
						+ zzjyService.loadZzjy(id).getTupian2());
				File df3 = new File(getSavePath() + "\\"
						+ zzjyService.loadZzjy(id).getTupian3());
				File df4 = new File(getSavePath() + "\\"
						+ zzjyService.loadZzjy(id).getTupian4());
				df1.delete();
				df2.delete();
				df3.delete();
				df4.delete();
				String filterResult = filterType(getTupianTypes().split(","));
				if (typelimit == 1 && filterResult != null) {
					addActionError("文件类型错误");
					return ERROR;
				}
				String newName1 = UUID.randomUUID()
						+ uploadFileName.get(0).substring(
								uploadFileName.get(0).lastIndexOf("."));
				FileOutputStream fos1 = new FileOutputStream(getSavePath()
						+ "\\" + newName1);
				FileInputStream fis1 = new FileInputStream(getUpload().get(0));
				byte[] buffer1 = new byte[1024];
				int len1 = 0;
				while ((len1 = fis1.read(buffer1)) > 0) {
					fos1.write(buffer1, 0, len1);
				}
				zzjy.setTupian1(newName1);
				String newName2 = UUID.randomUUID()
						+ uploadFileName.get(1).substring(
								uploadFileName.get(1).lastIndexOf("."));
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + newName2);
				FileInputStream fis = new FileInputStream(getUpload().get(1));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				zzjy.setTupian2(newName2);
				String newName3 = UUID.randomUUID()
						+ uploadFileName.get(2).substring(
								uploadFileName.get(2).lastIndexOf("."));
				FileOutputStream fos3 = new FileOutputStream(getSavePath()
						+ "\\" + newName3);
				FileInputStream fis3 = new FileInputStream(getUpload().get(2));
				byte[] buffer3 = new byte[3024];
				int len3 = 0;
				while ((len3 = fis3.read(buffer3)) > 0) {
					fos3.write(buffer3, 0, len3);
				}
				zzjy.setTupian3(newName3);
				String newName4 = UUID.randomUUID()
						+ uploadFileName.get(3).substring(
								uploadFileName.get(3).lastIndexOf("."));
				FileOutputStream fos4 = new FileOutputStream(getSavePath()
						+ "\\" + newName4);
				FileInputStream fis4 = new FileInputStream(getUpload().get(3));
				byte[] buffer4 = new byte[4024];
				int len4 = 0;
				while ((len4 = fis4.read(buffer4)) > 0) {
					fos4.write(buffer4, 0, len4);
				}
				zzjy.setTupian4(newName4);
			}
			zzjy.setShuming(shuming);
			zzjy.setBanci(banci);
			zzjy.setBaozhuang(baozhuang);
			zzjy.setChubanshe(chubanshe);
			zzjy.setChubanshijian(chubanshijian);
			zzjy.setDingjia(dingjia);
			zzjy.setFenlei(fenlei);
			zzjy.setFenlei2(fenlei2);
			zzjy.setIsbn(isbn);
			zzjy.setJingcaishuzhai(jingcaishuzhai);
			zzjy.setKaiben(kaiben);
			zzjy.setMulu(mulu);
			zzjy.setNeirongjianjie(neirongjianjie);
			zzjy.setQianyan(qianyan);
			zzjy.setYeshu(yeshu);
			zzjy.setZuozhe(zuozhe);
			zzjy.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				zzjy.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			zzjy.setZhanshi(zhanshi);
			zzjy.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			zzjy.setGengxinshijian(curdate);
			zzjyService.updateZzjy(zzjy);
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String zzjyYulan() {
		session.put("zzjyYulan", zzjyService.loadZzjy(id));
		return "zzjyYulan";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public GuanliyuanService getGuanliyuanService() {
		return guanliyuanService;
	}

	public void setGuanliyuanService(GuanliyuanService guanliyuanService) {
		this.guanliyuanService = guanliyuanService;
	}

	public ZzjyService getZzjyService() {
		return zzjyService;
	}

	public void setZzjyService(ZzjyService zzjyService) {
		this.zzjyService = zzjyService;
	}

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getFenlei2() {
		return fenlei2;
	}

	public void setFenlei2(String fenlei2) {
		this.fenlei2 = fenlei2;
	}

	public String getShuming() {
		return shuming;
	}

	public void setShuming(String shuming) {
		this.shuming = shuming;
	}

	public String getZuozhe() {
		return zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	public String getChubanshe() {
		return chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getBaozhuang() {
		return baozhuang;
	}

	public void setBaozhuang(String baozhuang) {
		this.baozhuang = baozhuang;
	}

	public Integer getKaiben() {
		return kaiben;
	}

	public void setKaiben(Integer kaiben) {
		this.kaiben = kaiben;
	}

	public String getChubanshijian() {
		return chubanshijian;
	}

	public void setChubanshijian(String chubanshijian) {
		this.chubanshijian = chubanshijian;
	}

	public Integer getYeshu() {
		return yeshu;
	}

	public void setYeshu(Integer yeshu) {
		this.yeshu = yeshu;
	}

	public Float getDingjia() {
		return dingjia;
	}

	public void setDingjia(Float dingjia) {
		this.dingjia = dingjia;
	}

	public String getNeirongjianjie() {
		return neirongjianjie;
	}

	public void setNeirongjianjie(String neirongjianjie) {
		this.neirongjianjie = neirongjianjie;
	}

	public String getZuozhejianjie() {
		return zuozhejianjie;
	}

	public void setZuozhejianjie(String zuozhejianjie) {
		this.zuozhejianjie = zuozhejianjie;
	}

	public String getMulu() {
		return mulu;
	}

	public void setMulu(String mulu) {
		this.mulu = mulu;
	}

	public String getJingcaishuzhai() {
		return jingcaishuzhai;
	}

	public void setJingcaishuzhai(String jingcaishuzhai) {
		this.jingcaishuzhai = jingcaishuzhai;
	}

	public String getQianyan() {
		return qianyan;
	}

	public void setQianyan(String qianyan) {
		this.qianyan = qianyan;
	}

	public String getTupian1() {
		return tupian1;
	}

	public void setTupian1(String tupian1) {
		this.tupian1 = tupian1;
	}

	public String getTupian2() {
		return tupian2;
	}

	public void setTupian2(String tupian2) {
		this.tupian2 = tupian2;
	}

	public String getTupian3() {
		return tupian3;
	}

	public void setTupian3(String tupian3) {
		this.tupian3 = tupian3;
	}

	public String getTupian4() {
		return tupian4;
	}

	public void setTupian4(String tupian4) {
		this.tupian4 = tupian4;
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

	public String getTupianTypes() {
		return tupianTypes;
	}

	public void setTupianTypes(String tupianTypes) {
		this.tupianTypes = tupianTypes;
	}

	public int getTypelimit() {
		return typelimit;
	}

	public void setTypelimit(int typelimit) {
		this.typelimit = typelimit;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getZhanshi() {
		return zhanshi;
	}

	public void setZhanshi(int zhanshi) {
		this.zhanshi = zhanshi;
	}

	public String filterType(String[] types) {
		int re = 0;
		for (int i = 0; i < uploadContentType.size(); i++) {
			String fileType = getUploadContentType().get(i);
			for (String type : types) {
				if (type.equals(fileType)) {
					re++;
				}
			}
		}
		if (re == getUploadContentType().size()) {
			return null;
		}
		return INPUT;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
