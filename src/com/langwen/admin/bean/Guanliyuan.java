package com.langwen.admin.bean;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Test;

/**
 * Guanliyuan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "guanliyuan", catalog = "langwen")
public class Guanliyuan implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gonghao;
	private String mima;
	private String xingming;
	private Integer status;

	private Set<Qx> qxs;
	

	// Constructors

	/** default constructor */
	public Guanliyuan() {
	}

	/** minimal constructor */
	public Guanliyuan(String gonghao, String mima, Integer status) {
		this.gonghao = gonghao;
		this.mima = mima;
		this.status = status;
	}

	/** full constructor */
	public Guanliyuan(String gonghao, String mima, String xingming,
			Integer status) {
		this.gonghao = gonghao;
		this.mima = mima;
		this.xingming = xingming;
		this.status = status;
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

	@Column(name = "gonghao", nullable = false, length = 12)
	public String getGonghao() {
		return this.gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

	@Column(name = "mima", nullable = false, length = 12)
	public String getMima() {
		return this.mima;
	}

	public void setMima(String mima) {
		this.mima = mima;
	}

	@Column(name = "xingming", length = 12)
	public String getXingming() {
		return this.xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "glyqx", joinColumns = @JoinColumn(name = "glyid"), inverseJoinColumns = @JoinColumn(name = "qxid"))
	public Set<Qx> getQxs() {
		return qxs;
	}

	public void setQxs(Set<Qx> qxs) {
		this.qxs = qxs;
	}

	@Override
	public String toString() {
		return "Guanliyuan [id=" + id + ", gonghao=" + gonghao + ", mima="
				+ mima + ", xingming=" + xingming + ", status=" + status + "]";
	}


}