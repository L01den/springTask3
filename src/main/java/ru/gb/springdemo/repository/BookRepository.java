package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

  List<Book> findAll();
}

//public class BookRepository {
//
//  private final List<Book> books;
//
//  public BookRepository() {
//    this.books = new ArrayList<>();
//  }
//
//  @PostConstruct
//  public void generateData() {
//    books.addAll(List.of(
//      new Book("Обратный отсчёт"),
//      new Book("Токийский гуль"),
//      new Book("Поднятие уровня в одиночку"),
//      new Book("Сновидение Ехо"),
//      new Book("Пикник на обочине")
//    ));
//  }
//
//  public Book getBookById(long id) {
//    return books.stream().filter(it -> Objects.equals(it.getId(), id))
//      .findFirst()
//      .orElse(null);
//  }
//
//  public void deleteBookById(long id){
//    books.remove(id);
//  }
//
//  public void addBook(Book book) {
//    books.add(book);
//  }
//
//  public List<Book> getBooks() {
//    return books;
//  }
//}
