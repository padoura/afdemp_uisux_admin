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
	
	@OneToOne(fetch = FetchType.EAGER)
	private Wishlist wishlist;
	
	@OneToOne(fetch = FetchType.EAGER)
	private ShoppingCart shoppingCart;
	
	@OneToMany(mappedBy="userRole", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<AbstractSale> abstractSale;
	
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
