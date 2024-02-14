package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;


import java.util.List;

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
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
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

//    @DeleteMapping("/{id}")
//    @Operation(summary = "Удалить книгу", description = "Удаляет книгу, которую находит по указанному ID, и отдаёт её в ответе")
//    public ResponseEntity<Book> delByID(@PathVariable long id) {
//        bookService.deleteBookById(id);
//        return ResponseEntity.badRequest().build();
//    }

//    @PostMapping
//    @Operation(summary = "Создание книги")
//    public Book createBook(@RequestBody String title){
//        return bookService.createBook(title);
//    }

    @PostMapping
    @Operation(summary = "Создание книги")
    public ResponseEntity<Book> aadBook(@RequestBody Book book){
        System.out.println(bookService.save(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

//    @PostMapping
//    @Operation(summary = "Добавить книгу", description = "Добавляет книгу, переданную в теле запроса, и отдаёт её в ответе")
//    public ResponseEntity<Book> addBook(@RequestBody Book book) {
//        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(book));
//    }
}
