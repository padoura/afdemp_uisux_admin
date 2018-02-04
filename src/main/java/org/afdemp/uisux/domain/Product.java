package org.afdemp.uisux.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true,nullable=false)
	private String name;
	private String madeIn;
	private Long inStockNumber = Long.valueOf(0);
	private BigDecimal listPrice;
	private BigDecimal ourPrice;
	private boolean active;
	private BigDecimal priceBought;
	
	public Product() {}
	
	@Column(columnDefinition="text")
	private String description;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Set<CartItem> cartItems;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private Set<MemberCartItem> memberCartItems;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<WishlistProduct> wishlistProductList;
	
	@Transient
	private MultipartFile productImage;

	public MultipartFile getProductImage() {
		return productImage;
	}

	
	
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}



	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}



	public BigDecimal getOurPrice() {
		return ourPrice;
	}



	public void setOurPrice(BigDecimal ourPrice) {
		this.ourPrice = ourPrice;
	}



	public BigDecimal getPriceBought() {
		return priceBought;
	}



	public void setPriceBought(BigDecimal priceBought) {
		this.priceBought = priceBought;
	}



	public Set<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
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
