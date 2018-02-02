package org.afdemp.uisux.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.ClientOrderRepository;
import org.afdemp.uisux.service.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientOrderServiceImpl implements ClientOrderService{
	
	
	@Autowired
	private ClientOrderRepository clientOrderRepository;
	
	@Override
	public AbstractSale createClientOrder(UserRole userRole,BigDecimal grandTotal,Address shippingAddress, Address billingAddress, CreditCard creditCard)
	{
		ClientOrder clientOrder=new ClientOrder();
		Date submittedDate=new Date();
		//Hibernate transforms java.util.Date to java.mySQL.Timestamp for MySQL
		clientOrder.setSubmittedDate(submittedDate);
		clientOrder.setShippingAddress(shippingAddress);
		clientOrder.setBillingAddress(billingAddress);
		clientOrder.setUserRole(userRole);
		clientOrder.setCreditCard(creditCard);
		clientOrder.setTotal(grandTotal);
		clientOrder.setOrderStatus("Processing");
		
		clientOrderRepository.save(clientOrder);
		return(clientOrder);
		
		
		
	}
	
	@Override
	public List<ClientOrder> fetchOrdersByPeriod(Timestamp fromTimestamp, Timestamp toTimestamp)
	{
		List<ClientOrder> clientOrders=new ArrayList<ClientOrder>();
		
		clientOrders=clientOrderRepository.findOrdersFromTo(fromTimestamp, toTimestamp);
		return clientOrders;
	}

}
