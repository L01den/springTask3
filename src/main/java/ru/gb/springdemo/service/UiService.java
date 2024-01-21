package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueFormat;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getBooks(){
        return bookRepository.getBooks();
    }

    public List<Reader> getReaders(){
        return readerRepository.getReaders();
    }

    public List<IssueFormat> getIssues(){
        List<Issue> issues = issueRepository.getIssues();
        List<IssueFormat> issueFormats = issueFormated(issues);
        return issueFormats;
    }

    public List<IssueFormat> getIssuesByReaderId(long id){
        List<Issue> issues = issueRepository.getIssuesByReader(id);
        issues = issues.stream().filter(it -> it.getReturned_at() == null).toList();
        List<IssueFormat> issueFormats = issueFormated(issues);
        return issueFormats;
    }

    private List<IssueFormat> issueFormated(List<Issue> issues){
        List<IssueFormat> issueFormats = new ArrayList<>();
        for (int i = 0; i < issues.size(); i++) {
            Reader reader = readerRepository.getReaderById(issues.get(i).getReaderId());
            Book book = bookRepository.getBookById(issues.get(i).getBookId());
            IssueFormat issueFormat = new IssueFormat(issues.get(i).getId(), book.getName(), reader.getName(), issues.get(i).getIssued_at());
            issueFormat.setReturned_at(issues.get(i).getReturned_at());
            issueFormats.add(issueFormat);
        }
        return issueFormats;
    }

}
