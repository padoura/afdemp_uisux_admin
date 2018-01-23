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
	private List<AbstractSale> abstractSale;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Address> userShippingAddress;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<CreditCard> creditCard;
	
	public List<Address> getUserShippingAddress() {
		return userShippingAddress;
	}

	public void setUserShippingAddress(List<Address> userShippingAddress) {
		this.userShippingAddress = userShippingAddress;
	}

	public List<CreditCard> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<AbstractSale> getAbstractSale() {
		return abstractSale;
	}

	public void setAbstractSale(List<AbstractSale> abstractSale) {
		this.abstractSale = abstractSale;
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
