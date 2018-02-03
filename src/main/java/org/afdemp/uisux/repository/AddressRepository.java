package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
	
	Address findByReceiverNameAndStreet1AndCityAndZipcode(String receiverName,String street1, String city, String zipcode);

}
