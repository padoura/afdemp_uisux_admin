package org.afdemp.uisux.repository;

import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {

	@Query("SELECT c FROM ClientOrder c WHERE c.submittedDate BETWEEN :from AND :to")
    List<ClientOrder> findOrdersFromTo(@Param("from") Timestamp from, @Param("to") Timestamp to);
	
	//@Query("SELECT a from SELECT co FROM ClientOrder co INNER JOIN CartItem ci ON co=ci.abstractSale WHERE co.submittedDate BETWEEN :from AND :to AND ci.product=item") 
	//List<ClientOrder> findOrdersOfProductXFromTo(@Param("from") Timestamp from, @Param("to") Timestamp to,@Param("item") Product product);
	
	
}
