package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Lorden on 21.01.2024
 */
@Service
public class UiService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public UiService(BookRepository bookRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public List<Book> findBookAll(){
        return bookRepository.findAll();
    }

    public List<Reader> findReaderAll(){
        return readerRepository.findAll();
    }

    public List<IssueFormat> getIssues(){
        List<Issue> issues = issueRepository.findAll();
        List<IssueFormat> issueFormats = issueFormated(issues);
        return issueFormats;
    }

    public List<IssueFormat> getIssuesByReaderId(long id){
        List<Issue> issues = issueRepository.findByReaderId(id);
        issues = issues.stream().filter(it -> it.getReturnedAt() == null).toList();
        List<IssueFormat> issueFormats = issueFormated(issues);
        return issueFormats;
    }

    private List<IssueFormat> issueFormated(List<Issue> issues){
        List<IssueFormat> issueFormats = new ArrayList<>();
        for (int i = 0; i < issues.size(); i++) {
            Reader reader = readerRepository.findById(issues.get(i).getReaderId()).orElseThrow(() -> new NoSuchElementException());
            Book book = bookRepository.findById(issues.get(i).getBookId()).orElseThrow(() -> new NoSuchElementException());
            IssueFormat issueFormat = new IssueFormat(issues.get(i).getId(), book.getName(), reader.getName(), issues.get(i).getIssuedAt());
            issueFormat.setReturned_at(issues.get(i).getReturnedAt());
            issueFormats.add(issueFormat);
        }
        return issueFormats;
    }

}
