package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.Wishlist;
import org.afdemp.uisux.domain.WishlistProduct;

public interface WishlistProductService {
	
	boolean addToWishlist(Wishlist wishlist,Product product);

	boolean removeFromWishlist(WishlistProduct wishlistProduct);
	
	List<WishlistProduct> fetchAllProductsInWishlist(Wishlist wishlist);
	
}
