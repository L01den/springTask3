package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

  public Issue getIssueById(long id) {
    return issues.stream().
            filter(it -> Objects.equals(it.getId(), id)).
            findFirst().
            orElse(null);
  }

  public List<Issue> getIssues(){
    return issues;
  }

  public List<Issue> getIssuesByReader(long id){
    List<Issue> issuesByReader = issues.stream().
            filter(it -> Objects.equals(it.getReaderId(), id)).toList();
    return issuesByReader;
  }

  public Issue returnBook(long id){
    Issue issue = issues.stream().
            filter(it -> Objects.equals(it.getId(), id)).
            findFirst().
            orElse(null);
    issue.setReturned_at(LocalDateTime.now());
    return issue;
  }
}
