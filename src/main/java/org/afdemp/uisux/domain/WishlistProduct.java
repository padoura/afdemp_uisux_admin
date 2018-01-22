package org.afdemp.uisux.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WishlistProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Wishlist wishlist;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}