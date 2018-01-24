package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

}
