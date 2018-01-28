package org.afdemp.uisux.repository;

import org.afdemp.uisux.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository <Transaction, Long>{

}
