package org.afdemp.uisux.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.MemberSale;
import org.afdemp.uisux.repository.MemberSaleRepository;
import org.afdemp.uisux.service.MemberSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSaleServiceImpl implements MemberSaleService{
	
	@Autowired
	private MemberSaleRepository memberSaleRepository;
	
	@Override
	public AbstractSale createMemberSale(MemberSale memberSale)
	{
		Date submittedDate=Date.valueOf(LocalDate.now());
		memberSale.setSubmittedDate(submittedDate);
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
