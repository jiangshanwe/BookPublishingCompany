package com.langwen.admin.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gdwk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gdwk", catalog = "langwen")
public class Gdwk implements java.io.Serializable {

	// Fields

	private Integer id;
	private String fenlei;
	private String fenlei2;
	private String shuming;
	private String zuozhe;
	private String chubanshe;
	private String isbn;
	private Integer banci;
	private String baozhuang;
	private Integer kaiben;
	private String chubanshijian;
	private Integer yeshu;
	private Float dingjia;
	private String neirongjianjie;
	private String zuozhejianjie;
	private String mulu;
	private String jingcaishuzhai;
	private String qianyan;
	private String tianjiashijian;
	private String tianjiaren;
	private String tupian1;
	private String tupian2;
	private String tupian3;
	private String tupian4;
	private String gengxinren;
	private String gengxinshijian;
	private Integer zhanshi;
	private Timestamp zhanshishijian;
	private Integer paixu;
	private Integer zhiding;
	private Timestamp zhidingshijian;
	private String type;

	// Constructors

	/** default constructor */
	public Gdwk() {
	}

	/** minimal constructor */
	public Gdwk(String shuming, String zuozhe, String chubanshe, String isbn) {
		this.shuming = shuming;
		this.zuozhe = zuozhe;
		this.chubanshe = chubanshe;
		this.isbn = isbn;
	}

	/** full constructor */
	public Gdwk(String fenlei, String fenlei2, String shuming, String zuozhe,
			String chubanshe, String isbn, Integer banci, String baozhuang,
			Integer kaiben, String chubanshijian, Integer yeshu, Float dingjia,
			String neirongjianjie, String zuozhejianjie, String mulu,
			String jingcaishuzhai, String qianyan, String tianjiashijian,
			String tianjiaren, String tupian1, String tupian2, String tupian3,
			String tupian4, String gengxinren, String gengxinshijian,
			Integer zhanshi, Timestamp zhanshishijian, Integer paixu,
			Integer zhiding, Timestamp zhidingshijian, String type) {
		this.fenlei = fenlei;
		this.fenlei2 = fenlei2;
		this.shuming = shuming;
		this.zuozhe = zuozhe;
		this.chubanshe = chubanshe;
		this.isbn = isbn;
		this.banci = banci;
		this.baozhuang = baozhuang;
		this.kaiben = kaiben;
		this.chubanshijian = chubanshijian;
		this.yeshu = yeshu;
		this.dingjia = dingjia;
		this.neirongjianjie = neirongjianjie;
		this.zuozhejianjie = zuozhejianjie;
		this.mulu = mulu;
		this.jingcaishuzhai = jingcaishuzhai;
		this.qianyan = qianyan;
		this.tianjiashijian = tianjiashijian;
		this.tianjiaren = tianjiaren;
		this.tupian1 = tupian1;
		this.tupian2 = tupian2;
		this.tupian3 = tupian3;
		this.tupian4 = tupian4;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.zhanshi = zhanshi;
		this.zhanshishijian = zhanshishijian;
		this.paixu = paixu;
		this.zhiding = zhiding;
		this.zhidingshijian = zhidingshijian;
		this.type = type;
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

	@Column(name = "fenlei", length = 155)
	public String getFenlei() {
		return this.fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	@Column(name = "fenlei2", length = 155)
	public String getFenlei2() {
		return this.fenlei2;
	}

	public void setFenlei2(String fenlei2) {
		this.fenlei2 = fenlei2;
	}

	@Column(name = "shuming", nullable = false)
	public String getShuming() {
		return this.shuming;
	}

	public void setShuming(String shuming) {
		this.shuming = shuming;
	}

	@Column(name = "zuozhe", nullable = false)
	public String getZuozhe() {
		return this.zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	@Column(name = "chubanshe", nullable = false)
	public String getChubanshe() {
		return this.chubanshe;
	}

	public void setChubanshe(String chubanshe) {
		this.chubanshe = chubanshe;
	}

	@Column(name = "isbn", nullable = false, length = 155)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "banci")
	public Integer getBanci() {
		return this.banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	@Column(name = "baozhuang", length = 155)
	public String getBaozhuang() {
		return this.baozhuang;
	}

	public void setBaozhuang(String baozhuang) {
		this.baozhuang = baozhuang;
	}

	@Column(name = "kaiben")
	public Integer getKaiben() {
		return this.kaiben;
	}

	public void setKaiben(Integer kaiben) {
		this.kaiben = kaiben;
	}

	@Column(name = "chubanshijian", length = 155)
	public String getChubanshijian() {
		return this.chubanshijian;
	}

	public void setChubanshijian(String chubanshijian) {
		this.chubanshijian = chubanshijian;
	}

	@Column(name = "yeshu")
	public Integer getYeshu() {
		return this.yeshu;
	}

	public void setYeshu(Integer yeshu) {
		this.yeshu = yeshu;
	}

	@Column(name = "dingjia", precision = 12, scale = 0)
	public Float getDingjia() {
		return this.dingjia;
	}

	public void setDingjia(Float dingjia) {
		this.dingjia = dingjia;
	}

	@Column(name = "neirongjianjie", length = 16277215)
	public String getNeirongjianjie() {
		return this.neirongjianjie;
	}

	public void setNeirongjianjie(String neirongjianjie) {
		this.neirongjianjie = neirongjianjie;
	}

	@Column(name = "zuozhejianjie", length = 16277215)
	public String getZuozhejianjie() {
		return this.zuozhejianjie;
	}

	public void setZuozhejianjie(String zuozhejianjie) {
		this.zuozhejianjie = zuozhejianjie;
	}

	@Column(name = "mulu", length = 16277215)
	public String getMulu() {
		return this.mulu;
	}

	public void setMulu(String mulu) {
		this.mulu = mulu;
	}

	@Column(name = "jingcaishuzhai", length = 16277215)
	public String getJingcaishuzhai() {
		return this.jingcaishuzhai;
	}

	public void setJingcaishuzhai(String jingcaishuzhai) {
		this.jingcaishuzhai = jingcaishuzhai;
	}

	@Column(name = "qianyan", length = 16277215)
	public String getQianyan() {
		return this.qianyan;
	}

	public void setQianyan(String qianyan) {
		this.qianyan = qianyan;
	}

	@Column(name = "tianjiashijian", length = 155)
	public String getTianjiashijian() {
		return this.tianjiashijian;
	}

	public void setTianjiashijian(String tianjiashijian) {
		this.tianjiashijian = tianjiashijian;
	}

	@Column(name = "tianjiaren", length = 155)
	public String getTianjiaren() {
		return this.tianjiaren;
	}

	public void setTianjiaren(String tianjiaren) {
		this.tianjiaren = tianjiaren;
	}

	@Column(name = "tupian1", length = 155)
	public String getTupian1() {
		return this.tupian1;
	}

	public void setTupian1(String tupian1) {
		this.tupian1 = tupian1;
	}

	@Column(name = "tupian2", length = 155)
	public String getTupian2() {
		return this.tupian2;
	}

	public void setTupian2(String tupian2) {
		this.tupian2 = tupian2;
	}

	@Column(name = "tupian3", length = 155)
	public String getTupian3() {
		return this.tupian3;
	}

	public void setTupian3(String tupian3) {
		this.tupian3 = tupian3;
	}

	@Column(name = "tupian4", length = 155)
	public String getTupian4() {
		return this.tupian4;
	}

	public void setTupian4(String tupian4) {
		this.tupian4 = tupian4;
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

	@Column(name = "zhanshi")
	public Integer getZhanshi() {
		return this.zhanshi;
	}

	public void setZhanshi(Integer zhanshi) {
		this.zhanshi = zhanshi;
	}

	@Column(name = "zhanshishijian", length = 19)
	public Timestamp getZhanshishijian() {
		return this.zhanshishijian;
	}

	public void setZhanshishijian(Timestamp zhanshishijian) {
		this.zhanshishijian = zhanshishijian;
	}

	@Column(name = "paixu")
	public Integer getPaixu() {
		return this.paixu;
	}

	public void setPaixu(Integer paixu) {
		this.paixu = paixu;
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

	@Column(name = "type", length = 55)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Gdwk(Integer id, String shuming, String tianjiaren,
			String tianjiashijian, String gengxinren, String gengxinshijian,
			Integer zhanshi, Integer paixu) {
		this.id = id;
		this.shuming = shuming;
		this.tianjiaren = tianjiaren;
		this.tianjiashijian = tianjiashijian;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.zhanshi = zhanshi;
		this.paixu = paixu;
	}

	public Gdwk(Integer id, String fenlei, String fenlei2, String shuming,
			String zuozhe, String chubanshe, String isbn, Integer banci,
			String baozhuang, Integer kaiben, String chubanshijian,
			Integer yeshu, Float dingjia, String tianjiashijian,
			String tianjiaren, String tupian1, String gengxinren,
			String gengxinshijian, Integer zhanshi, Integer paixu) {
		super();
		this.id = id;
		this.fenlei = fenlei;
		this.fenlei2 = fenlei2;
		this.shuming = shuming;
		this.zuozhe = zuozhe;
		this.chubanshe = chubanshe;
		this.isbn = isbn;
		this.banci = banci;
		this.baozhuang = baozhuang;
		this.kaiben = kaiben;
		this.chubanshijian = chubanshijian;
		this.yeshu = yeshu;
		this.dingjia = dingjia;
		this.tianjiashijian = tianjiashijian;
		this.tianjiaren = tianjiaren;
		this.tupian1 = tupian1;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.zhanshi = zhanshi;
		this.paixu = paixu;
	}

	public Gdwk(Integer id, String fenlei, String fenlei2, String shuming,
			String zuozhe, String chubanshe, String isbn, Integer banci,
			String baozhuang, Integer kaiben, String chubanshijian,
			Integer yeshu, Float dingjia, String tianjiashijian,
			String tianjiaren, String tupian1, String gengxinren,
			String gengxinshijian, Integer zhanshi, Integer paixu, String type) {
		super();
		this.id = id;
		this.fenlei = fenlei;
		this.fenlei2 = fenlei2;
		this.shuming = shuming;
		this.zuozhe = zuozhe;
		this.chubanshe = chubanshe;
		this.isbn = isbn;
		this.banci = banci;
		this.baozhuang = baozhuang;
		this.kaiben = kaiben;
		this.chubanshijian = chubanshijian;
		this.yeshu = yeshu;
		this.dingjia = dingjia;
		this.tianjiashijian = tianjiashijian;
		this.tianjiaren = tianjiaren;
		this.tupian1 = tupian1;
		this.gengxinren = gengxinren;
		this.gengxinshijian = gengxinshijian;
		this.zhanshi = zhanshi;
		this.paixu = paixu;
		this.type = type;
	}

	public Gdwk(Integer id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Gdwk [id=" + id + ", shuming=" + shuming + ", zuozhe=" + zuozhe
				+ ", isbn=" + isbn + ", paixu=" + paixu + "]" + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gdwk other = (Gdwk) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}