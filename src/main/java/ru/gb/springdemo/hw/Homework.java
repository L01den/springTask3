package ru.gb.springdemo.hw;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.ReaderV2;


@Component
public class Homework {


    /**
     * Домашнее задание 7:
     * 1. Для ресурсов, возвращающих HTML-страницы, реализовать авторизацию через login-форму.
     * Остальные /api ресурсы, возвращающие JSON, закрывать не нужно!
     * 2.1* Реализовать таблицы User(id, name, password) и Role(id, name), связанные многие ко многим
     * 2.2* Реализовать UserDetailsService, который предоставляет данные из БД (таблицы User и Role)
     * 3.3* Ресурсы выдачей (issue) доступны обладателям роли admin
     * 3.4* Ресурсы читателей (reader) доступны всем обладателям роли reader
     * 3.5* Ресурсы книг (books) доступны всем авторизованным пользователям
     *
     */

}
