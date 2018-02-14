package com.penguinwan.word.chain.infrastructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InMemoryWordDictionaryTest {
    @Test
    public void hasWord() throws Exception {
        List<String> words = new ArrayList<>();
        words.add("cat");

        InMemoryWordDictionary dictionary = new InMemoryWordDictionary(words);
        assert dictionary.hasWord("cat") == true;
    }

    @Test
    public void noWord() throws Exception {
        List<String> words = new ArrayList<>();
        words.add("cat");

        InMemoryWordDictionary dictionary = new InMemoryWordDictionary(words);
        assert dictionary.hasWord("dog") == false;
    }

}