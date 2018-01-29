package org.afdemp.uisux.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;



@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "shoppingCart"})})
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Product product;
		
	private int qty;
		
	@ManyToOne
	@JoinColumn(name="shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name="abstract_sale_id")
	private AbstractSale abstractSale;
	
	private BigDecimal currentPrice;

	public CartItem() {}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
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
	
	
	
	
}
