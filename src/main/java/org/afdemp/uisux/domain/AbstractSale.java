package org.afdemp.uisux.domain;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.afdemp.uisux.domain.security.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date submittedDate;

	private Date shippingDate;
	
	private String shippingMethod;
	private BigDecimal total;
	

	@OneToMany(mappedBy="abstractSale", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<CartItem> cartItemList;
	
	@OneToMany(mappedBy="abstractSale", fetch=FetchType.LAZY)
	private List<Transaction> TransactionList;

	
	@ManyToOne
	@JoinColumn(name="user_role_id")
	@JsonIgnore
	private UserRole userRole;
	
	@ManyToOne
	@JoinColumn(name="shipping_address_id")
	@JsonIgnore
	private Address shippingAddress;
	
	@ManyToOne
	@JoinColumn(name="billing_address_id")
	@JsonIgnore
	private Address billingAddress;
	
	@ManyToOne
	@JoinColumn(name="credit_card_id")
	@JsonIgnore
	private CreditCard creditCard;
	
	
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public List<Transaction> getTransactionList() {
		return TransactionList;
	}
	public void setTransactionList(List<Transaction> transactionList) {
		TransactionList = transactionList;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
