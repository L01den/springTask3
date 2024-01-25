package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.LimitBookException;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IssuerService {

  @Value("${application.max-allowed-books:1}")
  private int myProperty;

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) throws LimitBookException {
    if (bookRepository.findById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.findById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if(myProperty <= checkCountBook(request.getReaderId())){
      throw new LimitBookException("Не можем выдать книгу слишком много книг на руках");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

    Issue issue = new Issue(request.getBookId(), request.getReaderId(), LocalDateTime.now());
    issueRepository.save(issue);
    return issue;
  }

  private long checkCountBook(long readerId){
    List<Issue> issues = issueRepository.findAll();
    return issues.stream().
            filter(it -> Objects.equals(it.getReaderId(), readerId) && it.getReturnedAt() == null).
            count();

  }

  public Issue getIssueById(long id) {
    Issue issue = issueRepository.findById(id).get();
    return issue;
  }

  public List<Issue> getIssuesByReader(long id) {
    return issueRepository.findByReaderId(id);
  }

  public Issue returnBook(long id) {
    Issue issue = issueRepository.findById(id).get();
    issue.setReturnedAt(LocalDateTime.now());
    issueRepository.save(issue);
    return issue;
  }
}
