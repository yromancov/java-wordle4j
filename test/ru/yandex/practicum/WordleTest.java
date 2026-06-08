package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @BeforeAll
    public static void beforeall() throws IOException {
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt");
        WordleGame wordleGame = new WordleGame(dictionary);

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
        List<String> words = List.of("отлив", "обрыв");
        WordleDictionary dictionary = new WordleDictionary(words);
        assertTrue(words.contains(dictionary.getRandom()));
    }
    @Test
    void shouldBeCheckBeforeStart() throws IOException {
        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.load("words_ru.txt");
        WordleGame wordleGame = new WordleGame(dictionary);
        String answer = dictionary.getRandom();
        assertTrue(wordleGame.checkBeforeStart());

    }
}
