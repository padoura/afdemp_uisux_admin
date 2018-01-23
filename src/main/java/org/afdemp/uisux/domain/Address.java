package org.afdemp.uisux.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.afdemp.uisux.domain.security.UserRole;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String receiverName;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private boolean userShippingDefault;
	
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole userRole;
	
	public boolean isUserShippingDefault() {
		return userShippingDefault;
	}
	public void setUserShippingDefault(boolean userShippingDefault) {
		this.userShippingDefault = userShippingDefault;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	@OneToMany(mappedBy="billingAddress", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<AbstractSale> billingSaleList;
	
	@OneToMany(mappedBy="shippingAddress", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<AbstractSale> shippingSaleList;
	
	@OneToOne
	private CreditCard creditCard;

	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public List<AbstractSale> getBillingSaleList() {
		return billingSaleList;
	}
	public void setBillingSaleList(List<AbstractSale> billingSaleList) {
		this.billingSaleList = billingSaleList;
	}
	public List<AbstractSale> getShippingSaleList() {
		return shippingSaleList;
	}
	public void setShippingSaleList(List<AbstractSale> shippingSaleList) {
		this.shippingSaleList = shippingSaleList;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	

}
