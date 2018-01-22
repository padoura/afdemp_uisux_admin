package org.afdemp.uisux.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.afdemp.uisux.domain.security.UserRole;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="wishlist", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<WishlistProduct> wishlistProductList;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_role_id")
	private UserRole userRole;
	
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

	public List<WishlistProduct> getWishlistProductList() {
		return wishlistProductList;
	}

	public void setWishlistProductList(List<WishlistProduct> wishlistProductList) {
		this.wishlistProductList = wishlistProductList;
	}
	
}
