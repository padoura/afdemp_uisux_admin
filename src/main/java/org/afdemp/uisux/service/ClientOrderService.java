package org.afdemp.uisux.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.security.UserRole;

public interface ClientOrderService {

	AbstractSale createClientOrder(UserRole userRole,BigDecimal grandTotal, Address shippingAddress, Address billingAddress, CreditCard creditCard);
	
	//TODO implementation
	List<ClientOrder> fetchOrdersByPeriod(Date from, Date to);

}
