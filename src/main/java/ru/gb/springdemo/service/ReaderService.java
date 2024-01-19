package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

/**
 * Created by Lorden on 18.01.2024
 */
@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Reader createReader(String name) {
        Reader reader = new Reader(name);
        readerRepository.addReader(reader);
        return reader;
    }

    public void deleteReaderById(long id) {
        readerRepository.deleteReaderById(id);

    }

    public Reader getReaderById(long id) {
        return readerRepository.getReaderById(id);
    }
}
