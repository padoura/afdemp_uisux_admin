package org.afdemp.uisux.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Account depositAccount;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Account withdrawAccount;
	
	@ManyToOne
	@JoinColumn(name="abstract_sale_id")
	private AbstractSale abstractSale;
	
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

	public Account getDepositAccount() {
		return depositAccount;
	}

	public void setDepositAccount(Account depositAccount) {
		this.depositAccount = depositAccount;
	}

	public Account getWithdrawAccount() {
		return withdrawAccount;
	}

	public void setWithdrawAccount(Account withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
	}
	
	

}
