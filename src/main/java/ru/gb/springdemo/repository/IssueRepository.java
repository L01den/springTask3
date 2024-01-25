package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{
  List<Issue> findAll();

  List<Issue> findByReaderId(long id);
}
//public class IssueRepository {
//
//  private final List<Issue> issues;
//
//  public IssueRepository() {
//    this.issues = new ArrayList<>();
//  }
//
//  public void save(Issue issue) {
//    // insert into ....
//    issues.add(issue);
//  }
//
//  public Issue getIssueById(long id) {
//    return issues.stream().
//            filter(it -> Objects.equals(it.getId(), id)).
//            findFirst().
//            orElse(null);
//  }
//
//  public List<Issue> getIssues(){
//    return issues;
//  }
//
//  public List<Issue> getIssuesByReader(long id){
//    List<Issue> issuesByReader = issues.stream().
//            filter(it -> Objects.equals(it.getReaderId(), id)).toList();
//    return issuesByReader;
//  }
//
//  public Issue returnBook(long id){
//    Issue issue = issues.stream().
//            filter(it -> Objects.equals(it.getId(), id)).
//            findFirst().
//            orElse(null);
//    issue.setReturnedAt(LocalDateTime.now());
//    return issue;
//  }
//}
