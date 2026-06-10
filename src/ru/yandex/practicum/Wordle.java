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
        int attmepts = 3;
        Scanner scanner = new Scanner(System.in);
        if (!wordleGame.checkBeforeStart()) {
            return;
        }
        System.out.println("Игра началась");
        System.out.printf("Правила игры: \n 1. Слово должно состоять из 5 букв\n 2. Слово должно быть на русском языке \n" +
                " 3. Для получения подсказки нажмите Enter \n 4. Кол-во попыток %d \n 5. Кол-во подсказок %d \n", wordleGame.getSteps(), attmepts);

        while (wordleGame.getSteps() > 0) {
            try {
//
                System.out.println("Пишите слово, попыток: " + wordleGame.getSteps());
                System.out.println("Подсказок: " + attmepts);
                String input = scanner.nextLine();
                if (input.equals("стоп")) {
                    System.out.println("Программа остановлена");
                    log.println("Экстренное завершение");
                    return;
                }
                if (input.isEmpty()) {
                    if (attmepts > 0) {
                        if (hint == null) {
                            System.out.println("Подсказка недоступна введите слово");
                        } else { // Доработать функцию подсказки
                            System.out.println("Слова похожие: " + wordleGame.giveAdvice(hint, word));
                            log.println("Выдана подсказка");
                            attmepts--;
                        }
                    } else {
                        System.out.println("Подсказки закончились");
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
                wordleGame.setSteps(wordleGame.getSteps() - 1);
                log.println("Ход слово = " + input + ", результат =" + hint + ", осталось попыток " + wordleGame.getSteps());
                if (hint.equals("+++++")) {
                    System.out.println("Ура вы победили, загаданное слово: " + wordleGame.getAnswer());
                    log.println("Победа");
                    return;
                }
            } catch (StringIndexOutOfBoundsException e) {
                log.println("Ошибка: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                log.println(e.getMessage());
            }
        }
        System.out.println("Вы проиграли");

    }

}


