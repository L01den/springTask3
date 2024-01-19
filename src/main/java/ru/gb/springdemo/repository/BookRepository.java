package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
      new Book("Обратный отсчёт"),
      new Book("Токийский гуль"),
      new Book("Поднятие уровня в одиночку"),
      new Book("Сновидение Ехо"),
      new Book("Пикник на обочине")
    ));
  }

  public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void deleteBookById(long id){
    books.remove(id);
  }

  public void addBook(Book book) {
    books.add(book);
  }
}
