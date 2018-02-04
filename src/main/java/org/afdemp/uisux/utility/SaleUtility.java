package org.afdemp.uisux.utility;

import org.afdemp.uisux.domain.ClientOrder;
import org.springframework.stereotype.Component;

@Component
public class SaleUtility {

	
	public static ClientOrder copyValuesToNewObject(ClientOrder co) {
		ClientOrder order = new ClientOrder();
		order.setId(co.getId());
		order.setOrderStatus(co.getOrderStatus());
		order.setSubmittedDate(co.getSubmittedDate());
		order.setTotal(co.getTotal());
		order.setShippingDate(co.getShippingDate());
		order.setShippingMethod(co.getShippingMethod());
		return order;
	}
	
}
