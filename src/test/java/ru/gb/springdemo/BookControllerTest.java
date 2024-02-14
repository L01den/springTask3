package ru.gb.springdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;



import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Lorden on 12.02.2024
 */


public class BookControllerTest extends JUnitSpringBootBase{
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookService bookService;


    @Test
    void testGetAll() {
        bookService.deleteAll();
        bookService.save(new Book("Токийский гуль"));
        bookService.save(new Book("Доказательная психосоматика"));
        List<Book> books = bookService.getAll();
        long id = books.get(books.size() - 1).getId();

        List<Book> expected = bookService.getAll();

        List<Book> responseBody = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult().getResponseBody();
        Assertions.assertEquals(expected.size(), responseBody.size());
        for (Book bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), bookResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testFindByIdSuccess() {
        // подготовил данные
        bookService.deleteAll();
        Book expected = bookService.save(new Book("book #1"));;
        List<Book> books = bookService.getAll();
        long id = books.get(books.size() - 1).getId();

        Book responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }


    @Test
    void testSave() {
        bookService.deleteAll();
        String bookName = "book #2";
        Book book = bookService.save(new Book(bookName ));
        List<Book> books = bookService.getAll();
        long id = books.get(books.size() - 1).getId();

        Book responseBook = webTestClient.post()
                .uri("/book")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .returnResult().getResponseBody();
        Book expected = bookService.getBookById(id);

        assertNotNull(responseBook);
        assertEquals(expected, responseBook);

    }

    @Test
    void testDelete() {
        bookService.save(new Book("book #3"));
        bookService.save(new Book( "book #4"));

        List<Book> expected = bookService.getAll();

        List<Book> response = webTestClient.delete()
                .uri("/book/" + 1)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult().getResponseBody();

        assertEquals(expected.size() - 1, bookService.getAll().size());
    }
}
