package org.afdemp.uisux.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
	
	private BigDecimal amount;
	private Timestamp dateTime;
	
	@ManyToOne( fetch=FetchType.LAZY)
	private Account depositAccount;
	
	@ManyToOne( fetch=FetchType.LAZY)
	private Account withdrawAccount;
	
	@ManyToOne
	@JoinColumn(name="abstract_sale_id")
	private AbstractSale abstractSale;
	
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
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
