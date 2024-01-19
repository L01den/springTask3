package ru.gb.springdemo;

/**
 * Created by Lorden on 18.01.2024
 */
public class LimitBookException extends  Exception{

    public LimitBookException(String message) {
        super(message);
    }
}
