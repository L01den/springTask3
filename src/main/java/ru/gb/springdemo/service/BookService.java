package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

/**
 * Created by Lorden on 18.01.2024
 */



@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book getBookById(long id) {
        return bookRepository.getBookById(id);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteBookById(id);
    }

    public Book createBook(String title) {
        Book book = new Book(title);
        bookRepository.addBook(book);
        return book;

    }
}
