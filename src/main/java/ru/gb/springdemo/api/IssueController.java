package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.LimitBookException;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssueController {


  private final IssuerService issuerService;

  public IssueController(IssuerService service) {
    this.issuerService = service;
  }

  @PostMapping
  @Operation(summary = "Выдать книгу пользователю", description = "В методе проверяется кол-во выданных книг на руки одному пользователь. Не более 5 книг на пользователя")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = issuerService.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (LimitBookException e){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получение выдачи по id")
  public Issue getIssueById(@PathVariable long id){
    return issuerService.getIssueById(id);
  }

  @PutMapping("/{issueId}")
  @Operation(summary = "Вернуть книгу")
  public Issue returnBook(@PathVariable long issueId){
    return issuerService.returnBook(issueId);
  }

}