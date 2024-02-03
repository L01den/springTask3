package ru.gb.springdemo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.model.ReaderV2;
import ru.gb.springdemo.repository.ReaderRepository;
import ru.gb.springdemo.repository.ReaderRepositoryV2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorden on 02.02.2024
 */
@Component
public class CustomUserDetailsService  implements UserDetailsService {
    private final ReaderRepository readerRepository;

    public CustomUserDetailsService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        System.out.println(reader);
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(reader.getLogin()));

        return new User(reader.getLogin(), reader.getPassword(), roles);

    }
}
