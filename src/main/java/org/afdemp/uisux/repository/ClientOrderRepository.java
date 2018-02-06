package org.afdemp.uisux.repository;

import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.ClientOrder;
import org.afdemp.uisux.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {
	
	ClientOrder findOne(Long id);

	@Query("SELECT c FROM ClientOrder c WHERE c.submittedDate BETWEEN :from AND :to")
    List<ClientOrder> findOrdersFromTo(@Param("from") Timestamp from, @Param("to") Timestamp to);
	
	List<ClientOrder> findByDistributedFalse();
	
	
}
