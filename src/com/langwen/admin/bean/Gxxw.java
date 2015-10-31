package com.langwen.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gxxw entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gxxw", catalog = "langwen")
public class Gxxw implements java.io.Serializable {

	// Fields

	private Integer id;
	private String biaoti;
	private String zhengwen;
	private String tianjiaren;
	private String tianjiashijian;
	private String ywlj;
	private String gengxinren;
	private String gengxinshijian;
	private Integer leixing;
	private Integer paixu;

	// Constructors

	/** default constructor */
	public Gxxw() {
	}

	/** full constructor */
	public Gxxw(String biaoti, String zhengwen, String tianjiaren,
			String tianjiashijian, String ywlj, String gengxinren,
			String gengxinshijian, Integer leixing, Integer paixu) {
		this.biaoti = biaoti;
		this.zhengwen = zhengwen;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.ywlj = ywlj;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.leixing = leixing;
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

	@Column(name = "zhengwen", length = 16277215)
	public String getZhengwen() {
		return this.zhengwen;
	}

	public void setZhengwen(String zhengwen) {
		this.zhengwen = zhengwen;
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

	@Column(name = "ywlj", length = 355)
	public String getYwlj() {
		return this.ywlj;
	}

	public void setYwlj(String ywlj) {
		this.ywlj = ywlj;
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

	@Column(name = "leixing")
	public Integer getLeixing() {
		return this.leixing;
	}

	public void setLeixing(Integer leixing) {
		this.leixing = leixing;
	}

	@Column(name = "paixu")
	public Integer getPaixu() {
		return this.paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
	}
	
	public Gxxw(Integer id, String biaoti, String tianjiaren,
			String tianjiashijian, String ywlj, String gengxinren,
			String gengxinshijian, Integer leixing, Integer paixu) {
		super();
		this.id = id;
		this.biaoti = biaoti;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.ywlj = ywlj;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.leixing = leixing;
		this.paixu = paixu;
	}

	public Gxxw(Integer id) {
		super();
		this.id = id;
	}

}