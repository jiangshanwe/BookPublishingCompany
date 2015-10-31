package com.langwen.admin.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Qx entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qx", catalog = "langwen")
public class Qx implements java.io.Serializable {

	// Fields

	private Integer id;
	private String qxm;

	private Set<Guanliyuan> glys;

	// Constructors

	/** default constructor */
	public Qx() {
	}

	/** full constructor */
	public Qx(String qxm) {
		this.qxm = qxm;
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

	@Column(name = "qxm", nullable = false, length = 125)
	public String getQxm() {
		return this.qxm;
	}

	public void setQxm(String qxm) {
		this.qxm = qxm;
	}

	@ManyToMany(mappedBy = "qxs", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Guanliyuan> getGlys() {
		return glys;
	}

	public void setGlys(Set<Guanliyuan> glys) {
		this.glys = glys;
	}

	@Override
	public String toString() {
		return "Qx [id=" + id + ", qxm=" + qxm + "]";
	}

}