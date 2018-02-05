package org.afdemp.uisux.service;

import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.security.UserRole;

public interface AddressService {
	
	Address createAddress(Address address);

	Address findById(Long shippingAddressId);

	void setDefaultShippingAddress(Long defaultShippingAddressId, UserRole userRole);
	

}
