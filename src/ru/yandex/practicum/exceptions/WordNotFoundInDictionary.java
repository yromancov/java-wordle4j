package ru.yandex.practicum.exceptions;

public class WordNotFoundInDictionary extends Throwable {
    public WordNotFoundInDictionary(String message) {
        super(message);
    }
}
