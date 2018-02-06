package org.afdemp.uisux.service.impl;

import org.afdemp.uisux.domain.Wishlist;
import org.afdemp.uisux.domain.security.UserRole;
import org.afdemp.uisux.repository.WishlistRepository;
import org.afdemp.uisux.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

	private static final Logger LOG = LoggerFactory.getLogger(WishlistService.class);
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Override
	public boolean createWishlist(UserRole userRole) 
	{
		Wishlist wishlist=new Wishlist();
		wishlist.setUserRole(userRole);
		if(wishlistRepository.save(wishlist)!=null)
		{
			LOG.info("\n\nSUCCESS: Wishlist generated succesfully for user {}.\n",userRole.getUser().getUsername());
			return true;
		}
		LOG.info("\n\nFAILURE: Unable to generate corresponding wishlist.");
		return false;
	}

}
