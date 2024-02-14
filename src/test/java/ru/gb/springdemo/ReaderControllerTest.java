package ru.gb.springdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Lorden on 14.02.2024
 */


public class ReaderControllerTest extends JUnitSpringBootBase {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderService readerService;

    @Test
    void testGetAll() {
        readerService.deleteAll();
        readerService.save(new Reader("Vlad"));
        readerService.save(new Reader("Nataxa"));

        List<Reader> expected = readerService.getAll();

        List<Reader> responseBody = webTestClient.get()
                .uri("/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Reader>>() {
                })
                .returnResult().getResponseBody();
        assertEquals(expected.size(), responseBody.size());
        for (Reader readerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), readerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), readerResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testFindByIdSuccess() {
        // подготовил данные
        readerService.deleteAll();
        Reader expected = readerService.save(new Reader("Misha"));
        ;
        List<Reader> books = readerService.getAll();
        long id = books.get(books.size() - 1).getId();

        Reader responseBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }


    @Test
    void testSave() {
        readerService.deleteAll();
        String bookName = "book #2";
        Reader book = readerService.save(new Reader(bookName));
        List<Reader> books = readerService.getAll();
        long id = books.get(books.size() - 1).getId();

        Reader responseBook = webTestClient.post()
                .uri("/book")
                .bodyValue(book)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Reader.class)
                .returnResult().getResponseBody();
        Reader expected = readerService.getReaderById(id);

        assertNotNull(responseBook);
        assertEquals(expected, responseBook);

    }

    @Test
    void testDelete() {
        readerService.save(new Reader("Nastia"));
        readerService.save(new Reader("Oleg"));

        List<Reader> expected = readerService.getAll();

        List<Reader> response = webTestClient.delete()
                .uri("/reader/" + 1)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(new ParameterizedTypeReference<List<Reader>>() {
                })
                .returnResult().getResponseBody();

        assertEquals(expected.size() - 1, readerService.getAll().size());
    }
}
