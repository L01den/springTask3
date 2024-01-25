package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.NoSuchElementException;

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
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public Book createBook(String title) {
        Book book = new Book(title);
        bookRepository.save(book);
        return book;

    }
}
