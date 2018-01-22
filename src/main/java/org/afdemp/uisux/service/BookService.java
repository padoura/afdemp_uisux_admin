package org.afdemp.uisux.service;

import java.util.List;

import org.afdemp.uisux.domain.Book;

public interface BookService {
	
	Book save(Book book);

	List<Book> findAll();
	
	Book findOne(Long id);
	
	void removeOne(Long id);
}
