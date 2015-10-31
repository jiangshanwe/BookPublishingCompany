package com.langwen.admin.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rczp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rczp", catalog = "langwen")
public class Rczp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String biaoti;
	private String xiangxi;
	private String faburen;
	private String fabushijian;
	private Integer zhiding;
	private Timestamp zhidingshijian;
	private String gengxinren;
	private String gengxinshijian;

	// Constructors

	/** default constructor */
	public Rczp() {
	}

	/** minimal constructor */
	public Rczp(String biaoti, String xiangxi) {
		this.biaoti = biaoti;
		this.xiangxi = xiangxi;
	}

	/** full constructor */
	public Rczp(String biaoti, String xiangxi, String faburen,
			String fabushijian, Integer zhiding, Timestamp zhidingshijian,
			String gengxinren, String gengxinshijian) {
		this.biaoti = biaoti;
		this.xiangxi = xiangxi;
		this.faburen = faburen;
		this.fabushijian = fabushijian;
		this.zhiding = zhiding;
		this.zhidingshijian = zhidingshijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
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

	@Column(name = "xiangxi", nullable = false, length = 16277215)
	public String getXiangxi() {
		return this.xiangxi;
	}

	public void setXiangxi(String xiangxi) {
		this.xiangxi = xiangxi;
	}

	@Column(name = "faburen", length = 125)
	public String getFaburen() {
		return this.faburen;
	}

	public void setFaburen(String faburen) {
		this.faburen = faburen;
	}

	@Column(name = "fabushijian", length = 55)
	public String getFabushijian() {
		return this.fabushijian;
	}

	public void setFabushijian(String fabushijian) {
		this.fabushijian = fabushijian;
	}

	@Column(name = "zhiding")
	public Integer getZhiding() {
		return this.zhiding;
	}

	public void setZhiding(Integer zhiding) {
		this.zhiding = zhiding;
	}

	@Column(name = "zhidingshijian", length = 19)
	public Timestamp getZhidingshijian() {
		return this.zhidingshijian;
	}

	public void setZhidingshijian(Timestamp zhidingshijian) {
		this.zhidingshijian = zhidingshijian;
	}

	@Column(name = "gengxinren", length = 125)
	public String getGengxinren() {
		return this.gengxinren;
	}

	public void setGengxinren(String gengxinren) {
		this.gengxinren = gengxinren;
	}

	@Column(name = "gengxinshijian", length = 55)
	public String getGengxinshijian() {
		return this.gengxinshijian;
	}

	public void setGengxinshijian(String gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}

	public Rczp(Integer id, String biaoti, String faburen, String fabushijian,
			Integer zhiding, String gengxinren, String gengxinshijian) {
		this.id = id;
		this.biaoti = biaoti;
		this.faburen = faburen;
		this.fabushijian = fabushijian;
		this.zhiding = zhiding;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
	}

	@Override
	public String toString() {
		return "Rczp [id=" + id + ", biaoti=" + biaoti + ", xiangxi=" + xiangxi
				+ ", faburen=" + faburen + ", fabushijian=" + fabushijian
				+ ", zhiding=" + zhiding + "]" + "\n";
	}

}