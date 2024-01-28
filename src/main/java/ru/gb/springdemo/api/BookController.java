package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.service.BookService;

/**
 * Created by Lorden on 18.01.2024
 */

//   * 1.1 Реализовать контроллер по управлению книгами с ручками:
//   GET /book/{id} - получить описание книги,
//   DELETE /book/{id} - удалить книгу,
//   POST /book - создать книгу

@RestController
@RequestMapping("/book")
@Tag(name = "Book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping( "/{id}")
    @Operation(summary = "Получение книги по id")
    public Book getBookById(@PathVariable long id){
       return bookService.getBookById(id);
    }

    @DeleteMapping( "/{id}")
    @Operation(summary = "Удаление книги по id")
    public void deleteBookById(@PathVariable long id){
        bookService.deleteBookById(id);
    }

    @PostMapping
    @Operation(summary = "Создание книги")
    public Book createBook(@RequestBody String title){
        return bookService.createBook(title);
    }
}
