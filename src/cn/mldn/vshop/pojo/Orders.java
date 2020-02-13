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
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "vshop")
public class Orders implements java.io.Serializable {

	// Fields

	private Integer oid;
	private Member member = new Member();
	private Address address = new Address() ;
	private Date subdate;
	private Double price;
	private String note;
	private Set<Details> detailses = new HashSet<Details>(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(Member member, Address address, Timestamp subdate,
			Double price, String note, Set<Details> detailses) {
		this.member = member;
		this.address = address;
		this.subdate = subdate;
		this.price = price;
		this.note = note;
		this.detailses = detailses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "oid", unique = true, nullable = false)
	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adid")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "subdate", length = 19)
	public Date getSubdate() {
		return this.subdate;
	}

	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "note", length = 65535)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Details> getDetailses() {
		return this.detailses;
	}

	public void setDetailses(Set<Details> detailses) {
		this.detailses = detailses;
	}

}