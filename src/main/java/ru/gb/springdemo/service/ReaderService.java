package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.Optional;

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
        readerRepository.save(reader);
        return reader;
    }

    public void deleteReaderById(long id) {
        readerRepository.deleteById(id);

    }

    public Reader getReaderById(long id) {
        Reader reader = readerRepository.findById(id).get();
        return reader;
    }
}
