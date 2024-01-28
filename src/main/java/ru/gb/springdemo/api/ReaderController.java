package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

/**
 * Created by Lorden on 18.01.2024
 */

// 1.2 Реализовать контроллер по управлению читателями
// 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя

@RestController
@RequestMapping("/reader")
@Tag(name = "Reader")
public class ReaderController {
    private final IssuerService issuerService;
    private final ReaderService readerService;

    public ReaderController(IssuerService issuerService, ReaderService readerService) {
        this.issuerService = issuerService;
        this.readerService = readerService;
    }

    @GetMapping( "/{id}")
    public Reader getReaderById(@PathVariable long id){
        return readerService.getReaderById(id);
    }

    @DeleteMapping( "/{id}")
    public void deleteReaderById(@PathVariable long id){
        readerService.deleteReaderById(id);
    }

    @PostMapping
    public Reader createReader(@RequestBody String name){
        return readerService.createReader(name);
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getIssuesByReader(@PathVariable long id){
        return issuerService.getIssuesByReader(id);

    }
}
