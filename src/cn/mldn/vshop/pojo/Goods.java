package cn.mldn.vshop.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "vshop")
public class Goods implements java.io.Serializable {

	// Fields

	private Integer gid;
	private Subitem subitem = new Subitem();
	private Item item = new Item();
	private Member member = new Member() ;
	private String title;
	private Double price;
	private Date pubdate;
	private String note;
	private Integer delflag;
	private String photo;
	private Set<Shopcar> shopcars = new HashSet<Shopcar>(0);
	private Set<Details> detailses = new HashSet<Details>(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String title, Double price) {
		this.title = title;
		this.price = price;
	}

	/** full constructor */
	public Goods(Subitem subitem, Item item, Member member, String title,
			Double price, Timestamp pubdate, String note, Integer delflag,
			String photo, Set<Shopcar> shopcars, Set<Details> detailses) {
		this.subitem = subitem;
		this.item = item;
		this.member = member;
		this.title = title;
		this.price = price;
		this.pubdate = pubdate;
		this.note = note;
		this.delflag = delflag;
		this.photo = photo;
		this.shopcars = shopcars;
		this.detailses = detailses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "gid", unique = true, nullable = false)
	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid")
	public Subitem getSubitem() {
		return this.subitem;
	}

	public void setSubitem(Subitem subitem) {
		this.subitem = subitem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iid")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "price", nullable = false, precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "pubdate", length = 19)
	public Date getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	@Column(name = "note", length = 65535)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "delflag")
	public Integer getDelflag() {
		return this.delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	@Column(name = "photo", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Shopcar> getShopcars() {
		return this.shopcars;
	}

	public void setShopcars(Set<Shopcar> shopcars) {
		this.shopcars = shopcars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Details> getDetailses() {
		return this.detailses;
	}

	public void setDetailses(Set<Details> detailses) {
		this.detailses = detailses;
	}

}