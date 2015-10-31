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

import com.langwen.admin.bean.Swny;
import com.langwen.admin.bean.Guanliyuan;
import com.langwen.admin.service.SwnyService;
import com.langwen.admin.service.GuanliyuanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SwnyAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String GDWKSHOWALL = "swnyShowall";
	Map<String, Object> session = ActionContext.getContext().getSession();
	private GuanliyuanService guanliyuanService = new GuanliyuanService();
	private SwnyService swnyService = new SwnyService();
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

	private int pageCount = (double) (swnyService.allSwny().size())
			/ (double) 10 > (swnyService.allSwny().size() / 10) ? swnyService
			.allSwny().size() / 10 + 1 : swnyService.allSwny().size() / 10;

	public String swnyShowall() {
		session.put("pageCount", pageCount);
		if (page == 0) {
			int ipage = 0;
			if (session.get("ipage") != null) {
				ipage = (Integer) session.get("ipage");
				session.put("page", ipage);
				setPage(ipage);
				session.put("allSwnys", swnyService.pageSwny(ipage));
			} else {
				page = 1;
				session.put("page", page);
				session.put("allSwnys", swnyService.pageSwny(1));
			}
		} else {
			session.put("page", page);
			session.put("allSwnys", swnyService.pageSwny(page));
		}
		return "allSwnys";
	}

	// 上一页
	public String swnySyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allSwnys", swnyService.pageSwny(npage - 1));
		session.put("page", npage - 1);
		setPage(npage - 1);
		return "allSwnys";
	}

	// 下一页
	public String swnyXyy() {
		session.put("pageCount", pageCount);
		int npage = (Integer) session.get("page");
		session.put("allSwnys", swnyService.pageSwny(npage + 1));
		session.put("page", npage + 1);
		setPage(npage + 1);
		return "allSwnys";
	}

	public String swnyMoveUp() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			swnyService.moveUpSwny(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyMoveDown() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			int ipage = 1;
			if (session.get("page") != null) {
				ipage = (Integer) session.get("page");
			}
			session.put("ipage", ipage);
			swnyService.moveDownSwny(id);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyShanchu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			File df1 = new File(getSavePath() + "\\"
					+ swnyService.loadSwny(id).getTupian1());
			File df2 = new File(getSavePath() + "\\"
					+ swnyService.loadSwny(id).getTupian2());
			File df3 = new File(getSavePath() + "\\"
					+ swnyService.loadSwny(id).getTupian3());
			File df4 = new File(getSavePath() + "\\"
					+ swnyService.loadSwny(id).getTupian4());
			df1.delete();
			df2.delete();
			df3.delete();
			df4.delete();
			swnyService.deleteSwny(id);
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

	public String swnyRePaixu() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			swnyService.rePaixunSwny();
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyTianjia() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			return "swnyTianjia";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyAdd() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			Swny swny = new Swny();
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
			swny.setTupian1(newName1);
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
			swny.setTupian2(newName2);
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
			swny.setTupian3(newName3);
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
			swny.setTupian4(newName4);
			swny.setShuming(shuming);
			swny.setBanci(banci);
			swny.setBaozhuang(baozhuang);
			swny.setChubanshe(chubanshe);
			swny.setChubanshijian(chubanshijian);
			swny.setDingjia(dingjia);
			swny.setFenlei(fenlei);
			swny.setTianjiaren(gly.getXingming());
			swny.setFenlei2(fenlei2);
			swny.setIsbn(isbn);
			swny.setJingcaishuzhai(jingcaishuzhai);
			swny.setKaiben(kaiben);
			swny.setMulu(mulu);
			swny.setNeirongjianjie(neirongjianjie);
			swny.setQianyan(qianyan);
			swny.setYeshu(yeshu);
			swny.setZuozhe(zuozhe);
			swny.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				swny.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			swny.setZhanshi(zhanshi);
			swnyService.addSwny(swny);
			return GDWKSHOWALL;
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			Swny swny = swnyService.loadSwny(id);
			swny.setZhanshi(1);
			swny.setZhanshishijian(new Timestamp(new Date().getTime()));
			swnyService.updateSwny(swny);
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

	public String swnyQxZhanshi() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			Swny swny = swnyService.loadSwny(id);
			swny.setZhanshi(0);
			swny.setZhanshishijian(null);
			swnyService.updateSwny(swny);
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

	public String swnyBianji() {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			session.put("swnyBianji", swnyService.loadSwny(id));
			return "swnyBianji";
		} else {
			addActionError("无权限操作此项");
			return ERROR;
		}
	}

	public String swnyUpdate() throws Exception {
		if (guanliyuanService.checkGuanliyuanQx(gly.getId(), 12)) {
			Swny swny = swnyService.loadSwny(id);
			if (getUpload() != null && getUpload().size() == 4) {
				File df1 = new File(getSavePath() + "\\"
						+ swnyService.loadSwny(id).getTupian1());
				File df2 = new File(getSavePath() + "\\"
						+ swnyService.loadSwny(id).getTupian2());
				File df3 = new File(getSavePath() + "\\"
						+ swnyService.loadSwny(id).getTupian3());
				File df4 = new File(getSavePath() + "\\"
						+ swnyService.loadSwny(id).getTupian4());
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
				swny.setTupian1(newName1);
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
				swny.setTupian2(newName2);
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
				swny.setTupian3(newName3);
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
				swny.setTupian4(newName4);
			}
			swny.setShuming(shuming);
			swny.setBanci(banci);
			swny.setBaozhuang(baozhuang);
			swny.setChubanshe(chubanshe);
			swny.setChubanshijian(chubanshijian);
			swny.setDingjia(dingjia);
			swny.setFenlei(fenlei);
			swny.setFenlei2(fenlei2);
			swny.setIsbn(isbn);
			swny.setJingcaishuzhai(jingcaishuzhai);
			swny.setKaiben(kaiben);
			swny.setMulu(mulu);
			swny.setNeirongjianjie(neirongjianjie);
			swny.setQianyan(qianyan);
			swny.setYeshu(yeshu);
			swny.setZuozhe(zuozhe);
			swny.setZuozhejianjie(zuozhejianjie);
			if (zhanshi == 1) {
				swny.setZhanshishijian(new Timestamp(new Date().getTime()));
			}
			swny.setZhanshi(zhanshi);
			swny.setGengxinren(gly.getXingming());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String curdate = df.format(date);
			swny.setGengxinshijian(curdate);
			swnyService.updateSwny(swny);
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

	public String swnyYulan() {
		session.put("swnyYulan", swnyService.loadSwny(id));
		return "swnyYulan";
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

	public SwnyService getSwnyService() {
		return swnyService;
	}

	public void setSwnyService(SwnyService swnyService) {
		this.swnyService = swnyService;
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
