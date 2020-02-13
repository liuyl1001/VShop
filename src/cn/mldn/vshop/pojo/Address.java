package cn.mldn.vshop.pojo;

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
 * Address entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "address", catalog = "vshop")
public class Address implements java.io.Serializable {

	// Fields

	private Integer adid;
	private City city = new City();
	private Province province = new Province() ;
	private Member member = new Member();
	private String addr;
	private String receiver;
	private String phone;
	private Integer deflag;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(City city, Province province, Member member, String addr,
			String receiver, String phone, Integer deflag, Set<Orders> orderses) {
		this.city = city;
		this.province = province;
		this.member = member;
		this.addr = addr;
		this.receiver = receiver;
		this.phone = phone;
		this.deflag = deflag;
		this.orderses = orderses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "adid", unique = true, nullable = false)
	public Integer getAdid() {
		return this.adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "addr", length = 200)
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "receiver", length = 50)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "deflag")
	public Integer getDeflag() {
		return this.deflag;
	}

	public void setDeflag(Integer deflag) {
		this.deflag = deflag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}