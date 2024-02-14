package ru.gb.springdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Lorden on 12.02.2024
 */

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IssuerControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    IssuerService issuerService;
    @Autowired
    BookService bookService;
    @Autowired
    ReaderService readerService;
    private IssueRequest issueRequest;

    @BeforeEach
    void initData() {
        issuerService.deleteAll();
        readerService.save(new Reader("Dasha"));
        bookService.save(new Book("Колесо времени"));
        long reader = readerService.getAll().get(0).getId();
        long book = bookService.getAll().get(0).getId();
        issueRequest = new IssueRequest(reader, book);

    }

    @Test
    void testIssueBook() throws LimitBookException {
        Issue expected = issuerService.issue(issueRequest);

        Issue response = webTestClient.post()
                .uri("/issue")
                .bodyValue(issueRequest)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Issue.class)
                .returnResult().getResponseBody();

        assertNotNull(response);
        assertEquals(expected.getBookId(), response.getBookId());
        assertEquals(expected.getReaderId(), response.getReaderId());
    }

}