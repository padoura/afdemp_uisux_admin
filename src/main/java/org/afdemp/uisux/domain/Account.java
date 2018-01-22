package org.afdemp.uisux.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.afdemp.uisux.domain.security.UserRole;



@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="withdrawAccount", fetch=FetchType.LAZY)
	private List<Transaction> withdrawList;
	
	@OneToMany(mappedBy="depositAccount", fetch=FetchType.LAZY)
	private List<Transaction> depositList;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_role_id")
	private UserRole userRole;
	
	private double balance;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transaction> getWithdrawList() {
		return withdrawList;
	}

	public void setWithdrawList(List<Transaction> withdrawList) {
		this.withdrawList = withdrawList;
	}

	public List<Transaction> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<Transaction> depositList) {
		this.depositList = depositList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
