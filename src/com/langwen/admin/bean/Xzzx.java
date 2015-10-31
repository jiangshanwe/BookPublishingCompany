package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Xzzx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "xzzx", catalog = "langwen")
public class Xzzx implements java.io.Serializable {

	// Fields

	private Integer id;
	private String biaoti;
	private String jieshao;
	private String dyts;
	private String fujian;
	private String bdfx;
	private String fxmm;
	private String tupian;
	private String tianjiaren;
	private String tianjiashijian;
	private String gengxinren;
	private String gengxinshijian;
	private Integer paixu;

	// Constructors

	/** default constructor */
	public Xzzx() {
	}

	/** minimal constructor */
	public Xzzx(String biaoti) {
		this.biaoti = biaoti;
	}

	/** full constructor */
	public Xzzx(String biaoti, String jieshao, String dyts, String fujian,
			String bdfx, String fxmm, String tupian, String tianjiaren,
			String tianjiashijian, String gengxinren, String gengxinshijian,
			Integer paixu) {
		this.biaoti = biaoti;
		this.jieshao = jieshao;
		this.dyts = dyts;
		this.fujian = fujian;
		this.bdfx = bdfx;
		this.fxmm = fxmm;
		this.tupian = tupian;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.paixu = paixu;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "biaoti", nullable = false)
	public String getBiaoti() {
		return this.biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	@Column(name = "jieshao", length = 16277215)
	public String getJieshao() {
		return this.jieshao;
	}

	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}

	@Column(name = "dyts")
	public String getDyts() {
		return this.dyts;
	}

	public void setDyts(String dyts) {
		this.dyts = dyts;
	}

	@Column(name = "fujian")
	public String getFujian() {
		return this.fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	@Column(name = "bdfx")
	public String getBdfx() {
		return this.bdfx;
	}

	public void setBdfx(String bdfx) {
		this.bdfx = bdfx;
	}

	@Column(name = "fxmm", length = 16)
	public String getFxmm() {
		return this.fxmm;
	}

	public void setFxmm(String fxmm) {
		this.fxmm = fxmm;
	}

	@Column(name = "tupian", length = 155)
	public String getTupian() {
		return this.tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	@Column(name = "tianjiaren", length = 155)
	public String getTianjiaren() {
		return this.tianjiaren;
	}

	public void setTianjiaren(String tianjiaren) {
		this.tianjiaren = tianjiaren;
	}

	@Column(name = "tianjiashijian", length = 155)
	public String getTianjiashijian() {
		return this.tianjiashijian;
	}

	public void setTianjiashijian(String tianjiashijian) {
		this.tianjiashijian = tianjiashijian;
	}

	@Column(name = "gengxinren", length = 155)
	public String getGengxinren() {
		return this.gengxinren;
	}

	public void setGengxinren(String gengxinren) {
		this.gengxinren = gengxinren;
	}

	@Column(name = "gengxinshijian", length = 155)
	public String getGengxinshijian() {
		return this.gengxinshijian;
	}

	public void setGengxinshijian(String gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}

	@Column(name = "paixu")
	public Integer getPaixu() {
		return this.paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}

	public Xzzx(Integer id, String biaoti, String dyts, String fujian,
			String bdfx, String fxmm, String tupian, String tianjiaren,
			String tianjiashijian, String gengxinren, String gengxinshijian,
			Integer paixu) {
		super();
		this.id = id;
		this.biaoti = biaoti;
		this.dyts = dyts;
		this.fujian = fujian;
		this.bdfx = bdfx;
		this.fxmm = fxmm;
		this.tupian = tupian;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.paixu = paixu;
	}

	public Xzzx(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Xzzx [id=" + id + ", biaoti=" + biaoti + ", paixu=" + paixu
				+ "]" + "\n";
	}

}