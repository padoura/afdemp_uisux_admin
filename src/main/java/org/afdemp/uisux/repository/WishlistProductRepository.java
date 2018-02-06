package org.afdemp.uisux.repository;

import java.util.List;

import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.Wishlist;
import org.afdemp.uisux.domain.WishlistProduct;
import org.springframework.data.repository.CrudRepository;

public interface WishlistProductRepository extends CrudRepository<WishlistProduct, Long> {

	WishlistProduct findByWishlistAndProduct(Wishlist wishlist,Product product);
	
	List<WishlistProduct> findByWishlist(Wishlist wishlist);
	
}
