package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.repository.AddressRepository;
import org.afdemp.uisux.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
	
	private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address createAddress(Address address)
	{
		if (address.getReceiverName()==null || address.getStreet1()==null || address.getCity()==null || address.getZipcode()==null)
		{
			LOG.info("\n\n\nFAILURE: Insufficient information passed. Unable to create a valid address.\n\n");
			return null;
		}
		else
		{
			Address tempAddress=addressRepository.findByReceiverNameAndStreet1AndCityAndZipcode(address.getReceiverName(), address.getStreet1(), address.getCity(), address.getZipcode());
			if(tempAddress==null) 
			{
				tempAddress = new Address();
				tempAddress.setReceiverName(tempAddress.getReceiverName());
				tempAddress.setStreet1(tempAddress.getStreet1());
				tempAddress.setStreet2(tempAddress.getStreet2());
				tempAddress.setCity(tempAddress.getCity());
				tempAddress.setState(tempAddress.getState());
				tempAddress.setCountry(tempAddress.getCountry());
				tempAddress.setZipcode(tempAddress.getZipcode());
				addressRepository.save(tempAddress);
				LOG.info("SUCCESS: Address Succesfully Added!!");
			}
			
			return tempAddress;
			
			
		}
		
	}

}
