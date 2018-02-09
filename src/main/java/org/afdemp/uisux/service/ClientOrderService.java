package org.afdemp.uisux.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;

public interface ClientOrderService {

	ClientOrder createClientOrder(ClientOrder clientOrder);
	
	List<ClientOrder> fetchOrdersByPeriod(Timestamp fromTimestamp, Timestamp toTimestamp);
	
	ClientOrder updateOrderStatusToShipped(ClientOrder clientOrder);
	
	ClientOrder updateOrderStatusToDelivered(ClientOrder clientOrder);

	ClientOrder updateShippingMethod(ClientOrder clientOrder, String method);

	ClientOrder updateShippingDate(ClientOrder clientOrder, Date date);

	List<ClientOrder> findAllUndistributedEarnings();

	void distributeEarningsToAllMembers(Long clientOrderId);

	ClientOrder findOne(Long l);

	ClientOrder createPastClientOrder(ClientOrder clientOrder, Timestamp pastOrderDate);

	void distributePastEarningsToAllMembers(Long clientOrderId);

}
