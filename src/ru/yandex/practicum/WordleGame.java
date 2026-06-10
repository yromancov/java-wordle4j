package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    private PrintWriter log;
    private String answer;

    private int steps;

    private WordleDictionary dictionary;


    public WordleGame(WordleDictionary dictionary, PrintWriter log) {
        this.log = log;
        this.dictionary = dictionary;
        answer = dictionary.getRandom();
        steps = 6;

    }

    public boolean checkBeforeStart() {
        if (dictionary.getWords().contains(answer) && answer.length() == 5) {
            return true;
        } else return false;


    }

    public List<String> giveAdvice(String hint, String word) {
        List<String> result = new ArrayList<>();
        for (String wordFromList : dictionary.getWords()) {
            boolean contin = true;
            for (int i = 0; i < hint.length(); i++) {
                if (hint.charAt(i) == '+' && wordFromList.charAt(i) != word.charAt(i)) {
                    contin = false;
                    break;
                }
            }
            if (contin) {
                result.add(wordFromList);
            }
        }
        return result;
    }

    public String getAnswer() {
        return answer;
    }

    public int getSteps() {
        return steps;
    }


    public void setSteps(int steps) {
        this.steps = steps;
    }
}
