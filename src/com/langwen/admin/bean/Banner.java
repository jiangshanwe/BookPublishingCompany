package com.langwen.admin.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "banner", catalog = "langwen")
public class Banner implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer zhiding;
	private String miaoshu;
	private String lianjie;
	private String tupian;
	private String faburen;
	private String fabushijian;
	private String gengxinren;
	private Timestamp gengxinshijian;
	private Timestamp zhidingshijian;

	// Constructors

	/** default constructor */
	public Banner() {
	}

	/** minimal constructor */
	public Banner(Integer zhiding, String tupian) {
		this.zhiding = zhiding;
		this.tupian = tupian;
	}

	/** full constructor */
	public Banner(Integer zhiding, String miaoshu, String lianjie,
			String tupian, String faburen, String fabushijian,
			String gengxinren, Timestamp gengxinshijian,
			Timestamp zhidingshijian) {
		this.zhiding = zhiding;
		this.miaoshu = miaoshu;
		this.lianjie = lianjie;
		this.tupian = tupian;
		this.faburen = faburen;
		this.fabushijian = fabushijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.zhidingshijian = zhidingshijian;
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

	@Column(name = "zhiding", nullable = false)
	public Integer getZhiding() {
		return this.zhiding;
	}

	public void setZhiding(Integer zhiding) {
		this.zhiding = zhiding;
	}

	@Column(name = "miaoshu")
	public String getMiaoshu() {
		return this.miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	@Column(name = "lianjie")
	public String getLianjie() {
		return this.lianjie;
	}

	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}

	@Column(name = "tupian", nullable = false)
	public String getTupian() {
		return this.tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	@Column(name = "faburen")
	public String getFaburen() {
		return this.faburen;
	}

	public void setFaburen(String faburen) {
		this.faburen = faburen;
	}

	@Column(name = "fabushijian", length = 122)
	public String getFabushijian() {
		return this.fabushijian;
	}

	public void setFabushijian(String fabushijian) {
		this.fabushijian = fabushijian;
	}

	@Column(name = "gengxinren")
	public String getGengxinren() {
		return this.gengxinren;
	}

	public void setGengxinren(String gengxinren) {
		this.gengxinren = gengxinren;
	}

	@Column(name = "gengxinshijian", length = 19)
	public Timestamp getGengxinshijian() {
		return this.gengxinshijian;
	}

	public void setGengxinshijian(Timestamp gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}

	@Column(name = "zhidingshijian", length = 19)
	public Timestamp getZhidingshijian() {
		return this.zhidingshijian;
	}

	public void setZhidingshijian(Timestamp zhidingshijian) {
		this.zhidingshijian = zhidingshijian;
	}

	@Override
	public String toString() {
		return "Banner [faburen=" + faburen + ", fabushijian=" + fabushijian
				+ ", gengxinren=" + gengxinren + ", gengxinshijian="
				+ gengxinshijian + ", id=" + id + ", lianjie=" + lianjie
				+ ", miaoshu=" + miaoshu + ", tupian=" + tupian + ", zhiding="
				+ zhiding + ", zhidingshijian=" + zhidingshijian + "]";
	}

}