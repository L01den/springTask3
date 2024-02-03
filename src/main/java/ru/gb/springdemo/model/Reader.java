package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "reader_v2")
@Schema(name = "Читатель")
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;
  public Reader(String name) {
    this.name = name;
  }
}
