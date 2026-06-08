package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @BeforeAll
    public static void beforeall() throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter("wordle.log"),true);
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt",log);
        WordleGame wordleGame = new WordleGame(dictionary,log);

    }

    @Test
    void shouldBeReturnPositiveFivePluses(){
        String result = WordleDictionary.checkWord("отлив","отлив");
        assertEquals("+++++",result);
    }
    @Test
    void shouldBeReturnNegativeFiveMinus(){
        String result = WordleDictionary.checkWord("отлив","арбуз");
        assertEquals("-----",result);
    }
    @Test
    void shouldBeReturnTwoPlus(){
        String result = WordleDictionary.checkWord("отлив","обрыв");
        assertEquals("+---+",result);
    }
    @Test
    void shouldBeReturnPostitveContainsWord() throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter("wordle.log"),true);
        List<String> words = List.of("отлив", "обрыв");
        WordleDictionary dictionary = new WordleDictionary(words,log);
        assertTrue(words.contains(dictionary.getRandom()));
    }
    @Test
    void shouldBeCheckBeforeStart() throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter("wordle.log"),true);
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt",log);
        WordleGame wordleGame = new WordleGame(dictionary,log);
        String answer = dictionary.getRandom();
        assertTrue(wordleGame.checkBeforeStart());

    }
}
