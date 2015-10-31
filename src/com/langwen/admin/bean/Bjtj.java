package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bjtj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bjtj", catalog = "langwen")
public class Bjtj implements java.io.Serializable {

	// Fields

	private Integer id;
	private String shuming;
	private String chubanshe;
	private String tupian;
	private String lianjie;
	private String tianjiaren;
	private String tianjiashijian;
	private String gengxinren;
	private String gengxinshijian;
	private Integer paixu;

	// Constructors

	/** default constructor */
	public Bjtj() {
	}

	/** full constructor */
	public Bjtj(String shuming, String chubanshe, String tupian,
			String lianjie, String tianjiaren, String tianjiashijian,
			String gengxinren, String gengxinshijian, Integer paixu) {
		this.shuming = shuming;
		this.chubanshe = chubanshe;
		this.tupian = tupian;
		this.lianjie = lianjie;
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

	@Column(name = "shuming")
	public String getShuming() {
		return this.shuming;
	}

	public void setShuming(String shuming) {
		this.shuming = shuming;
	}

	@Column(name = "chubanshe")
	public String getChubanshe() {
		return this.chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	@Column(name = "tupian")
	public String getTupian() {
		return this.tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	@Column(name = "lianjie")
	public String getLianjie() {
		return this.lianjie;
	}

	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
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

}