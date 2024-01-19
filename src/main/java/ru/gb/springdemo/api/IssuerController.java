package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
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
public class IssuerController {


  private final IssuerService issuerService;

  public IssuerController(IssuerService service) {
    this.issuerService = service;
  }

// 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи

  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = issuerService.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    } catch (LimitBookException e){
      return  ResponseEntity.badRequest().build(); // не хочет 409 выдавать
//    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
    }

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public Issue getIssueById(@PathVariable long id){
    return issuerService.getIssueById(id);
  }

  @PutMapping("/{issueId}")
  public Issue returnBook(@PathVariable long issueId){
    return issuerService.returnBook(issueId);
  }

}