package cn.mldn.vshop.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "member", catalog = "vshop")
public class Member implements java.io.Serializable {

	// Fields

	private String mid;
	private String password;
	private Integer adminflag;
	private String email;
	private String phone;
	private Date reg;
	private Integer delflag;
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<Shopcar> shopcars = new HashSet<Shopcar>(0);
	private Set<Orders> orderses = new HashSet<Orders>(0);
	private Set<Goods> goodses = new HashSet<Goods>(0);
	

	// Constructors

	/** default constructor */
	public Member() {
	}

	/** minimal constructor */
	public Member(String mid) {
		this.mid = mid;
	}

	/** full constructor */
	public Member(String mid, String password, Integer adminflag, String email,
			String phone, Timestamp reg, Integer delflag,
			Set<Address> addresses, Set<Shopcar> shopcars,
			Set<Orders> orderses, Set<Goods> goodses) {
		this.mid = mid;
		this.password = password;
		this.adminflag = adminflag;
		this.email = email;
		this.phone = phone;
		this.reg = reg;
		this.delflag = delflag;
		this.addresses = addresses;
		this.shopcars = shopcars;
		this.orderses = orderses;
		this.goodses = goodses;
	}
	
	// Property accessors
	@Id
	@Column(name = "mid", unique = true, nullable = false, length = 50)
	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "adminflag")
	public Integer getAdminflag() {
		return this.adminflag;
	}

	public void setAdminflag(Integer adminflag) {
		this.adminflag = adminflag;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "reg", length = 19)
	public Date getReg() {
		return this.reg;
	}

	public void setReg(Date reg) {
		this.reg = reg;
	}

	@Column(name = "delflag")
	public Integer getDelflag() {
		return this.delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Shopcar> getShopcars() {
		return this.shopcars;
	}

	public void setShopcars(Set<Shopcar> shopcars) {
		this.shopcars = shopcars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

}