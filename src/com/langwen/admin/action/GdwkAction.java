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

import com.langwen.admin.bean.Gdwk;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.GdwkService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GdwkAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String GDWKSHOWALL = "gdwkShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private GdwkService gdwkService = new GdwkService();
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

	private int pageCount = (double) (gdwkService.allGdwk().size())
			/ (double) 10 > (gdwkService.allGdwk().size() / 10) ? gdwkService
			.allGdwk().size() / 10 + 1 : gdwkService.allGdwk().size() / 10;

	public String gdwkShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allGdwks", gdwkService.pageGdwk(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allGdwks", gdwkService.pageGdwk(1));
			}
		} else {
			session.put("page", page);
			session.put("allGdwks", gdwkService.pageGdwk(page));
		}
		return "allGdwks";
	}

	// 上一页
	public String gdwkSyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allGdwks", gdwkService.pageGdwk(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allGdwks";
	}

	// 下一页
	public String gdwkXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allGdwks", gdwkService.pageGdwk(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allGdwks";
	}

	public String gdwkMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			gdwkService.moveUpGdwk(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			gdwkService.moveDownGdwk(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			File df1 = new File(getSavePath() + "\\"
					+ gdwkService.loadGdwk(id).getTupian1());
			File df2 = new File(getSavePath() + "\\"
					+ gdwkService.loadGdwk(id).getTupian2());
			File df3 = new File(getSavePath() + "\\"
					+ gdwkService.loadGdwk(id).getTupian3());
			File df4 = new File(getSavePath() + "\\"
					+ gdwkService.loadGdwk(id).getTupian4());
			df1.delete();
			df2.delete();
			df3.delete();
			df4.delete();
			gdwkService.deleteGdwk(id);
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

	public String gdwkRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			gdwkService.rePaixunGdwk();
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			return "gdwkTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			Gdwk gdwk = new Gdwk();
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
			gdwk.setTupian1(newName1);
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
			gdwk.setTupian2(newName2);
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
			gdwk.setTupian3(newName3);
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
			gdwk.setTupian4(newName4);
			gdwk.setShuming(shuming);
			gdwk.setBanci(banci);
			gdwk.setBaozhuang(baozhuang);
			gdwk.setChubanshe(chubanshe);
			gdwk.setChubanshijian(chubanshijian);
			gdwk.setDingjia(dingjia);
			gdwk.setFenlei(fenlei);
			gdwk.setTianjiaren(gly.getXingming());
			gdwk.setFenlei2(fenlei2);
			gdwk.setIsbn(isbn);
			gdwk.setJingcaishuzhai(jingcaishuzhai);
			gdwk.setKaiben(kaiben);
			gdwk.setMulu(mulu);
			gdwk.setNeirongjianjie(neirongjianjie);
			gdwk.setQianyan(qianyan);
			gdwk.setYeshu(yeshu);
			gdwk.setZuozhe(zuozhe);
			gdwk.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				gdwk.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			gdwk.setZhanshi(zhanshi);
			gdwkService.addGdwk(gdwk);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			Gdwk gdwk = gdwkService.loadGdwk(id);
			gdwk.setZhanshi(1);
			gdwk.setZhanshishijian(new Timestamp(new Date().getTime()));
			gdwkService.updateGdwk(gdwk);
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

	public String gdwkQxZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			Gdwk gdwk = gdwkService.loadGdwk(id);
			gdwk.setZhanshi(0);
			gdwk.setZhanshishijian(null);
			gdwkService.updateGdwk(gdwk);
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

	public String gdwkBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			session.put("gdwkBianji", gdwkService.loadGdwk(id));
			return "gdwkBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String gdwkUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 11)) {
			Gdwk gdwk = gdwkService.loadGdwk(id);
			if (getUpload() != null && getUpload().size() == 4) {
				File df1 = new File(getSavePath() + "\\"
						+ gdwkService.loadGdwk(id).getTupian1());
				File df2 = new File(getSavePath() + "\\"
						+ gdwkService.loadGdwk(id).getTupian2());
				File df3 = new File(getSavePath() + "\\"
						+ gdwkService.loadGdwk(id).getTupian3());
				File df4 = new File(getSavePath() + "\\"
						+ gdwkService.loadGdwk(id).getTupian4());
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
				gdwk.setTupian1(newName1);
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
				gdwk.setTupian2(newName2);
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
				gdwk.setTupian3(newName3);
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
				gdwk.setTupian4(newName4);
			}
			gdwk.setShuming(shuming);
			gdwk.setBanci(banci);
			gdwk.setBaozhuang(baozhuang);
			gdwk.setChubanshe(chubanshe);
			gdwk.setChubanshijian(chubanshijian);
			gdwk.setDingjia(dingjia);
			gdwk.setFenlei(fenlei);
			gdwk.setFenlei2(fenlei2);
			gdwk.setIsbn(isbn);
			gdwk.setJingcaishuzhai(jingcaishuzhai);
			gdwk.setKaiben(kaiben);
			gdwk.setMulu(mulu);
			gdwk.setNeirongjianjie(neirongjianjie);
			gdwk.setQianyan(qianyan);
			gdwk.setYeshu(yeshu);
			gdwk.setZuozhe(zuozhe);
			gdwk.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				gdwk.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			gdwk.setZhanshi(zhanshi);
			gdwk.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			gdwk.setGengxinshijian(curdate);
			gdwkService.updateGdwk(gdwk);
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

	public String gdwkYulan() {
		session.put("gdwkYulan", gdwkService.loadGdwk(id));
		return "gdwkYulan";
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

	public GdwkService getGdwkService() {
		return gdwkService;
	}

	public void setGdwkService(GdwkService gdwkService) {
		this.gdwkService = gdwkService;
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
