package cn.mldn.vshop.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "province", catalog = "vshop")
public class Province implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String title;
	private Set<City> cities = new HashSet<City>(0);
	private Set<Address> addresses = new HashSet<Address>(0);

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** minimal constructor */
	public Province(String title) {
		this.title = title;
	}

	/** full constructor */
	public Province(String title, Set<City> cities, Set<Address> addresses) {
		this.title = title;
		this.cities = cities;
		this.addresses = addresses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pid", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}