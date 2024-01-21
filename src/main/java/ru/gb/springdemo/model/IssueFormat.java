package ru.gb.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class IssueFormat {
    private long id;
    private String bookName;
    private String readerName;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public IssueFormat(long id, String bookName, String readerName, LocalDateTime issued_at) {
        this.id = id;
        this.bookName = bookName;
        this.readerName = readerName;
        this.issued_at = issued_at;
    }
}
