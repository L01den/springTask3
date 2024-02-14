package ru.gb.springdemo.model;

import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  private long readerId;

  /**
   * Идентификатор книги
   */
  private long bookId;

  public IssueRequest(long reader, long book) {
    readerId = reader;
    bookId = book;
  }
}
