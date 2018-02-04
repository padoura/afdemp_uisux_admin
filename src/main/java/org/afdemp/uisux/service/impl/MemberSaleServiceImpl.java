package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.MemberSale;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.MemberSaleRepository;
import org.afdemp.uisux.service.MemberSaleService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberSaleServiceImpl implements MemberSaleService{
	
	@Autowired
	private MemberSaleRepository memberSaleRepository;
	
	@Override
	public AbstractSale createMemberSale(UserRole userRole,BigDecimal grandTotal,Address shippingAddress, Address billingAddress, CreditCard creditCard)
	{
		MemberSale memberSale=new MemberSale();
		Date submittedDate=Date.valueOf(LocalDate.now());
		memberSale.setSubmittedDate(submittedDate);
		memberSale.setShippingAddress(shippingAddress);
		memberSale.setBillingAddress(billingAddress);
		memberSale.setUserRole(userRole);
		memberSale.setCreditCard(creditCard);
		memberSale.setTotal(grandTotal);
		memberSale.setSaleStatus("Pending");
		
		memberSaleRepository.save(memberSale);
		return(memberSale);
		
		
		
	}
	
	@Override
	public List<MemberSale> fetchSalesByPeriod(Timestamp fromTimestamp, Timestamp toTimestamp)
	{
		List<MemberSale> memberSales=new ArrayList<MemberSale>();
		
		memberSales=memberSaleRepository.findSalesFromTo(fromTimestamp, toTimestamp);
		return memberSales;
	}

}
