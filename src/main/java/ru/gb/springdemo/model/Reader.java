package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "reader")
@Schema(name = "Читатель")
public class Reader {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(name = "Идентификатор")
  private long id;

  @Column(name = "name")
  @Schema(name = "Имя")
  private String name;

  public Reader(String name) {
    this.name = name;
  }
}
