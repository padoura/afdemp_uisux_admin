package org.afdemp.uisux.domain;

import javax.persistence.Entity;

@Entity
public class ClientOrder extends AbstractSale {
	
	private String orderStatus;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
