package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "issue")
@Schema(name = "Выдача")
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "book_id", nullable = false)
  private long bookId;
  @Column(name = "reader_id", nullable = false)
  private long readerId;
  @Column(name = "issued_id", nullable = false)
  private LocalDateTime issuedAt;
  @Column(name = "returned_id")
  private  LocalDateTime  returnedAt;


  public Issue(long bookId, long readerId, LocalDateTime issuedAt) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = issuedAt;
  }


}
