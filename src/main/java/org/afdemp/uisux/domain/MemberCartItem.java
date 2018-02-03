package org.afdemp.uisux.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MemberCartItem {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
	
	private boolean isVisible=false;
		
	private int qty;
		
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="abstract_sale_id")
	private AbstractSale abstractSale;
	
	private BigDecimal currentPurchasePrice;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public AbstractSale getAbstractSale() {
		return abstractSale;
	}

	public void setAbstractSale(AbstractSale abstractSale) {
		this.abstractSale = abstractSale;
	}

	public BigDecimal getCurrentPurchasePrice() {
		return currentPurchasePrice;
	}

	public void setCurrentPurchasePrice(BigDecimal currentPurchasePrice) {
		this.currentPurchasePrice = currentPurchasePrice;
	}
	
	//-------------------------Getters and Setters-------------------------//
	
	
	
}
