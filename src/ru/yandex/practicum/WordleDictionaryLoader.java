package ru.yandex.practicum;

import ru.yandex.practicum.exceptions.DictioanaryUploudException;

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
    PrintWriter log;
    List<String> words = new ArrayList<>();

    public WordleDictionaryLoader(PrintWriter log) {
        this.log = log;
    }

    public WordleDictionaryLoader() {
        log = new PrintWriter(System.out);
    }

    public WordleDictionary load(String fileName, PrintWriter log) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase().replace('ё', 'е');
                if (line.length() == 5) {
                    words.add(line);
                }
            }
            log.println("Создан список из " + words.size() + " слов");
            return new WordleDictionary(words, log);
        } catch (IOException e) {
            log.println("Ошибка чтения файла" + e.getMessage());
            throw new DictioanaryUploudException("Не удалось загрузить словарь " + fileName);

        }

    }
}
