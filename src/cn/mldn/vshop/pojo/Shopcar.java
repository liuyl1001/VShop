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
 * Shopcar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shopcar", catalog = "vshop")
public class Shopcar implements java.io.Serializable {

	// Fields

	private Integer scid;
	private Goods goods = new Goods();
	private Member member = new Member();
	private Integer amount;

	// Constructors

	/** default constructor */
	public Shopcar() {
	}

	/** full constructor */
	public Shopcar(Goods goods, Member member, Integer amount) {
		this.goods = goods;
		this.member = member;
		this.amount = amount;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "scid", unique = true, nullable = false)
	public Integer getScid() {
		return this.scid;
	}

	public void setScid(Integer scid) {
		this.scid = scid;
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
	@JoinColumn(name = "mid")
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}