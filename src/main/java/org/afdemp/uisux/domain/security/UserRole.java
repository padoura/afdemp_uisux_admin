package org.afdemp.uisux.domain.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Account;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.ShoppingCart;
import org.afdemp.uisux.domain.User;
import org.afdemp.uisux.domain.Wishlist;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userRoleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToOne(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Wishlist wishlist;
	
	
	@OneToOne(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private ShoppingCart shoppingCart;
	
	@OneToOne(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Account account;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<AbstractSale> abstractSaleList;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Address> userShippingAddressList;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CreditCard> creditCardList;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public List<AbstractSale> getAbstractSaleList() {
		return abstractSaleList;
	}

	public void setAbstractSaleList(List<AbstractSale> abstractSaleList) {
		this.abstractSaleList = abstractSaleList;
	}

	public List<Address> getUserShippingAddressList() {
		return userShippingAddressList;
	}

	public void setUserShippingAddressList(List<Address> userShippingAddressList) {
		this.userShippingAddressList = userShippingAddressList;
	}

	public List<CreditCard> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<CreditCard> creditCardList) {
		this.creditCardList = creditCardList;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public UserRole(){}
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}


	public Long getUserRoleId() {
		return userRoleId;
	}


	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
