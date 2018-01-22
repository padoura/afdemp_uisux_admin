package org.afdemp.uisux.service.impl;

import java.util.List;

import org.afdemp.uisux.domain.Book;
import org.afdemp.uisux.repository.BookRepository;
import org.afdemp.uisux.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}
	
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}
	
	public void removeOne(Long id) {
		bookRepository.delete(id);
	}
}
