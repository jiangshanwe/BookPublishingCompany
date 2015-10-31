package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Yssq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "yssq", catalog = "langwen")
public class Yssq implements java.io.Serializable {

	// Fields

	private Integer id;
	private String biaoti;
	private String neirong;
	private String fujian;
	private String xiugairen;
	private String xiugaishijian;

	// Constructors

	/** default constructor */
	public Yssq() {
	}

	/** minimal constructor */
	public Yssq(String biaoti, String neirong) {
		this.biaoti = biaoti;
		this.neirong = neirong;
	}

	/** full constructor */
	public Yssq(String biaoti, String neirong, String fujian, String xiugairen,
			String xiugaishijian) {
		this.biaoti = biaoti;
		this.neirong = neirong;
		this.fujian = fujian;
		this.xiugairen = xiugairen;
		this.xiugaishijian = xiugaishijian;
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

	@Column(name = "neirong", nullable = false, length = 16277215)
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

	@Column(name = "xiugairen", length = 155)
	public String getXiugairen() {
		return this.xiugairen;
	}

	public void setXiugairen(String xiugairen) {
		this.xiugairen = xiugairen;
	}

	@Column(name = "xiugaishijian", length = 155)
	public String getXiugaishijian() {
		return this.xiugaishijian;
	}

	public void setXiugaishijian(String xiugaishijian) {
		this.xiugaishijian = xiugaishijian;
	}

	@Override
	public String toString() {
		return "Yssq [id=" + id + ", biaoti=" + biaoti + ", neirong=" + neirong
				+ ", fujian=" + fujian + ", xiugairen=" + xiugairen
				+ ", xiugaishijian=" + xiugaishijian + "]";
	}

}