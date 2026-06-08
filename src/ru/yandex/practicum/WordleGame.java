package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {
    Scanner scanner = new Scanner(System.in);
    String word;
    String hint = "-----";
    private String answer;

    private int steps;

    private WordleDictionary dictionary;


    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        answer = dictionary.getRandom();
        steps = 6;
    }

    public void play() {
        if (checkBeforeStart()) {
            System.out.println("Игра началась");
            while (steps > 0) {
                try {
                    System.out.println("Пишите слово, попыток: " + steps);
                    word = scanner.nextLine();
                    hint = WordleDictionary.checkWord(word, answer);
                    if(word =="\n") { // Доработать функцию подсказки
                        System.out.println("Слова похожие: " + giveAdvice());
                    }
                    System.out.println(hint);
                    if (hint.equals("+++++")) {
                        System.out.println("Ура вы победили, загаданное слово: " + answer);
                        break;
                    }
                    steps--;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Ошибка: введите слово из пяти букв");
                }
            }
        }
    }

    public boolean checkBeforeStart() {
        if (dictionary.getWords().contains(answer)
                && answer.length() == 5) {
            return true;
        } else return false;


    }

    public List<String> giveAdvice() {
        List<String> result = new ArrayList<>();
        for (String wordFromList : dictionary.getWords()) {
            boolean contin = true;
            for (int i = 0; i <hint.length(); i++) {
            if (hint.charAt(i)=='+' && wordFromList.charAt(i)!=word.charAt(i)){
                contin = false;
                break;
            }
            }
            if (contin){
                result.add(wordFromList);
            }
        }
        return result;
    }

}
