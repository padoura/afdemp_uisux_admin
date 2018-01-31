package org.afdemp.uisux.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.afdemp.uisux.domain.ClientOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientOrderRepository extends CrudRepository<ClientOrder, Long> {

@Query("SELECT c FROM ClientOrder c WHERE c.submittedDate BETWEEN :from AND :to")
public List<ClientOrder> findOrdersFromTo( @Param("from") @Temporal(TemporalType.TIMESTAMP) Date from, @Param("to") @Temporal(TemporalType.TIMESTAMP) Date to);
	
	
}
