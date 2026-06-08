package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    List<String> words = new ArrayList<>();

    public WordleDictionary load(String fileName, PrintWriter log) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase().replace('ё', 'е');
                if (line.length() == 5) {
                    words.add(line);
                }
            }
        }
        return new WordleDictionary(words, log);
    }
}
