package cn.mldn.vshop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Details entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "details", catalog = "vshop")
public class Details implements java.io.Serializable {

	// Fields

	private Integer dtid;
	private Goods goods = new Goods();
	private Orders orders = new Orders();
	private Integer amount;

	// Constructors

	/** default constructor */
	public Details() {
	}

	/** full constructor */
	public Details(Goods goods, Orders orders, Integer amount) {
		this.goods = goods;
		this.orders = orders;
		this.amount = amount;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "dtid", unique = true, nullable = false)
	public Integer getDtid() {
		return this.dtid;
	}

	public void setDtid(Integer dtid) {
		this.dtid = dtid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gid")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oid")
	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}