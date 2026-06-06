package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final Random random = new Random();

    private static LinkedHashMap<Integer,Character> charsToRight = new LinkedHashMap<>();

    private List<String> words;

    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public String getRandom() {
        return words.get(random.nextInt(words.size()));
    }

    public static String checkWord(String guess, String answer) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < answer.length(); i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == answer.charAt(i)) {
                charsToRight.put(i,answer.charAt(i));
                builder.append('+');
            } else if (answer.indexOf(guessChar) >= 0) {
                builder.append('^');
            } else {
                builder.append('-');
            }
        }
        return builder.toString();
    }
    public List<String> getAdvice(){
        List<Character> characters = new ArrayList<>();
        for (Character chars : charsToRight.values()){
            characters.add(chars);
        }

    }
}
