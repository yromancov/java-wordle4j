package ru.yandex.practicum;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) throws IOException {
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt");
//    int randomIndex = (int) (Math.random() * dictionary.getWords().size());
//    String secretWord = dictionary.getWords().get(randomIndex);
        WordleGame wordleGame = new WordleGame(dictionary);
        wordleGame.play();


    }
}
