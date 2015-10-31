package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hyyq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hyyq", catalog = "langwen")
public class Hyyq implements java.io.Serializable {

	// Fields

	private Integer id;
	private String biaoti;
	private String neirong;
	private String fujian;
	private String tianjiaren;
	private String tianjiashijian;
	private String gengxinren;
	private String gengxinshijian;
	private Integer paixu;

	// Constructors

	/** default constructor */
	public Hyyq() {
	}

	/** full constructor */
	public Hyyq(String biaoti, String neirong, String fujian,
			String tianjiaren, String tianjiashijian, String gengxinren,
			String gengxinshijian, Integer paixu) {
		this.biaoti = biaoti;
		this.neirong = neirong;
		this.fujian = fujian;
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

	@Column(name = "biaoti")
	public String getBiaoti() {
		return this.biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	@Column(name = "neirong", length = 16277215)
	public String getNeirong() {
		return this.neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	@Column(name = "fujian")
	public String getFujian() {
		return this.fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
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

	public Hyyq(Integer id, String biaoti, String fujian, String tianjiaren,
			String tianjiashijian, String gengxinren, String gengxinshijian,
			Integer paixu) {
		super();
		this.id = id;
		this.biaoti = biaoti;
		this.fujian = fujian;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.paixu = paixu;
	}

	public Hyyq(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hyyq [id=" + id + ", biaoti=" + biaoti + ", fujian=" + fujian
				+ ", paixu=" + paixu + ", neirong=" + neirong + ", gengxinren="
				+ gengxinren + ", gengxinshijian=" + gengxinshijian
				+ ", tianjiaren=" + tianjiaren + ", tianjiashijian="
				+ tianjiashijian + "]";
	}

}