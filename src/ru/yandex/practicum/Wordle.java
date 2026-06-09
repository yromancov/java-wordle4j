package ru.yandex.practicum;


import ru.yandex.practicum.exceptions.DictioanaryUploudException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter log = null;

        try {
            log = new PrintWriter(new FileWriter("wordle.log", true));
            log.println("Запуск игры");
            WordleDictionaryLoader loader = new WordleDictionaryLoader(log);
            WordleDictionary dictionary = loader.load("words_ru.txt", log);
            WordleGame wordleGame = new WordleGame(dictionary, log);
            play(wordleGame, dictionary, log);
        } catch (DictioanaryUploudException e) {
            if (log != null) {
                log.println("Ошибка " + e.getMessage());
                e.printStackTrace(log);
            }
        } catch (Exception e) {
            if (log != null) {
                log.println("Ошибка " + e.getMessage());
                e.printStackTrace(log);
            }
        } finally {
            if (log != null) {
                log.println("Завершение работы программы");
                log.close();
            }
        }

    }


    public static void play(WordleGame wordleGame, WordleDictionary dictionary, PrintWriter log) {
        String word = null;
        String hint = null;
        Scanner scanner = new Scanner(System.in);
        if (!wordleGame.checkBeforeStart()) {
            return;
        }
        System.out.println("Игра началась");
        while (wordleGame.getSteps() > 0) {
            try {
//                        System.out.println(wordleGame.getAnswer());
                System.out.println("Пишите слово, попыток: " + wordleGame.getSteps());
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    if (hint == null) {
                        System.out.println("Подсказка недоступна введите слово");
                    } else { // Доработать функцию подсказки
                        System.out.println("Слова похожие: " + wordleGame.giveAdvice(hint, word));
                    }
                    continue;
                }

                word = input;

                if (word.length() != 5) {
                    System.out.println("Введите слово из 5 букв");
                }
                if (!dictionary.getWords().contains(word)) {
                    System.out.println("Слово отсутствует в словаре");
                }
                hint = WordleDictionary.checkWord(word, wordleGame.getAnswer());
                System.out.println(hint);
                if (hint.equals("+++++")) {
                    System.out.println("Ура вы победили, загаданное слово: " + wordleGame.getAnswer());
                    break;
                }
                wordleGame.setSteps(wordleGame.getSteps() - 1);
                log.println("Ход слово= " + input + ", результат=" + hint + ", осталось попыток" + wordleGame.getSteps());

            } catch (StringIndexOutOfBoundsException e) {
                log.println("Ошибка: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                log.println(e.getMessage());
            }
        }
        System.out.println("Вы проиграли");

    }

}


