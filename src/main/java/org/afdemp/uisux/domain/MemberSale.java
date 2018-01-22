package org.afdemp.uisux.domain;

import javax.persistence.Entity;

@Entity
public class MemberSale extends AbstractSale {
	
	private String saleStatus;

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}
}
