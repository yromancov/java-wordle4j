package ru.yandex.practicum;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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
        PrintWriter log = new PrintWriter(new FileWriter("wordle.log",true));
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt",log);
        WordleGame wordleGame = new WordleGame(dictionary,log);
        wordleGame.play();


    }
}
