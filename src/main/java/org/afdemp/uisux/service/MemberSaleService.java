package org.afdemp.uisux.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.afdemp.uisux.domain.AbstractSale;
import org.afdemp.uisux.domain.Address;
import org.afdemp.uisux.domain.CreditCard;
import org.afdemp.uisux.domain.MemberSale;
import org.afdemp.uisux.domain.security.UserRole;

public interface MemberSaleService {
	
	AbstractSale createMemberSale(MemberSale memberSale);
	
	List<MemberSale> fetchSalesByPeriod(Timestamp fromTimestamp, Timestamp toTimestamp);

}
