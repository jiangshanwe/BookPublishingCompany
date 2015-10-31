package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Glyqx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "glyqx", catalog = "langwen")
public class Glyqx implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer glyid;
	private Integer qxid;

	// Constructors

	/** default constructor */
	public Glyqx() {
	}

	/** full constructor */
	public Glyqx(Integer glyid, Integer qxid) {
		this.glyid = glyid;
		this.qxid = qxid;
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

	@Column(name = "glyid", nullable = false)
	public Integer getGlyid() {
		return this.glyid;
	}

	public void setGlyid(Integer glyid) {
		this.glyid = glyid;
	}

	@Column(name = "qxid", nullable = false)
	public Integer getQxid() {
		return this.qxid;
	}

	public void setQxid(Integer qxid) {
		this.qxid = qxid;
	}

	@Override
	public String toString() {
		return "Glyqx [id=" + id + ", glyid=" + glyid + ", qxid=" + qxid + "]";
	}

}