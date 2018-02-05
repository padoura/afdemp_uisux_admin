package org.afdemp.uisux.repository;

import java.util.HashSet;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.MemberCartItem;
import org.afdemp.uisux.domain.Product;
import org.afdemp.uisux.domain.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberCartItemRepository extends CrudRepository<MemberCartItem, Long>{
	
	MemberCartItem findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
	
	HashSet<MemberCartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	MemberCartItem findOne(Long memberCartItemId);

	int deleteByIdAndShoppingCartId(Long id, Long shoppingCartId);
	
	int deleteByShoppingCartId(Long shoppingCartId);
	
	@Transactional
	@Modifying
	@Query("UPDATE MemberCartItem mci SET mci.qty=mci.qty-:qty WHERE mci.product=:product AND mci.qty>:qty AND mci.shoppingCart=:shoppingCart")
	int partialPurchase(@Param("product") Product product,@Param("qty") int qty, @Param("shoppingCart") ShoppingCart shoppingCart);
	
	@Transactional
	@Modifying
	@Query("UPDATE MemberCartItem mci SET mci.abstractSale=:sale,mci.shoppingCart=null,mci.isVisible=false WHERE mci.product=:product AND mci.shoppingCart=:shoppingCart")
	int fullPurchase(@Param("product") Product product, @Param("shoppingCart") ShoppingCart shoppingCart,@Param("sale") AbstractSale abstractSale);
	
	List<MemberCartItem> findByProductIdAndIsVisibleTrue(Long id);
	
	

	
}
