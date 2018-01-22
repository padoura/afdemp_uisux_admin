package org.afdemp.uisux.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NaturalId
	private String name;
	private String madeIn;
	private Long inStockNumber;
	private double listPrice;
	private double ourPrice;
	private boolean active;
	private double priceBought;
	
	public Product() {}
	
	@Column(columnDefinition="text")
	private String description;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<WishlistProduct> wishlistProductList;

	public List<WishlistProduct> getWishlistProductList() {
		return wishlistProductList;
	}

	public void setWishlistProductList(List<WishlistProduct> wishlistProductList) {
		this.wishlistProductList = wishlistProductList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public Long getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(Long inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOurPrice() {
		return ourPrice;
	}

	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getPriceBought() {
		return priceBought;
	}

	public void setPriceBought(double priceBought) {
		this.priceBought = priceBought;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
