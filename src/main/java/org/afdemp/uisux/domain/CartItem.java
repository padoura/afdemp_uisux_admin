package org.afdemp.uisux.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.afdemp.uisux.domain.ShoppingCart;

import org.afdemp.uisux.domain.AbstractSale;

@Entity
public class CartItem extends Product {

	public CartItem() {}
	
	private int qty;
	private BigDecimal subtotal;
	
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="abstract_sale_id")
	private AbstractSale abstractSale;

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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
	

}
