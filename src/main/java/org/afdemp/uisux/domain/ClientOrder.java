package org.afdemp.uisux.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.afdemp.uisux.domain.security.UserRole;

@Entity
public class ClientOrder extends AbstractSale {
	
	private String orderStatus;
	boolean distributed=false;
	
	public ClientOrder() {};

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isDistributed() {
		return distributed;
	}

	public void setDistributed(boolean distributed) {
		this.distributed = distributed;
	}
	
}
