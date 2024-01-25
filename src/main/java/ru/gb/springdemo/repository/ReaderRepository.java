package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    List<Reader> findAll();


}
//public class ReaderRepository {
//
//    private final List<Reader> readers;
//
//    public ReaderRepository() {
//        this.readers = new ArrayList<>();
//    }
//
//    @PostConstruct
//    public void generateData() {
//        readers.addAll(List.of(
//                new Reader("Игорь"),
//                new Reader("Даша"),
//                new Reader("Влад")
//        ));
//    }
//
//    public Reader getReaderById(long id) {
//        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void deleteReaderById(long id) {
//        readers.remove(id);
//    }
//
//    public void addReader(Reader reader) {
//        readers.add(reader);
//    }
//
//    public List<Reader> getReaders() {
//        return readers;
//    }
//}
