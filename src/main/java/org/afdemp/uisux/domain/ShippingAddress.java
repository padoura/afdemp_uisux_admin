package org.afdemp.uisux.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id") 
public class ShippingAddress extends Address{
	
	@OneToMany(mappedBy="shippingAddress", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<AbstractSale> abstractSaleList;

	public List<AbstractSale> getAbstractSaleList() {
		return abstractSaleList;
	}

	public void setAbstractSaleList(List<AbstractSale> abstractSaleList) {
		this.abstractSaleList = abstractSaleList;
	}
	
	

}
