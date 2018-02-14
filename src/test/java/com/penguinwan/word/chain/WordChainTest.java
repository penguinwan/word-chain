package com.penguinwan.word.chain;

import com.penguinwan.word.chain.infrastructure.InMemoryWordDictionary;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class WordChainTest {
    WordChain instance;

    @Test
    public void shouldReturnFalseWhenNullStartEnd() {
        instance = new WordChain(dictionaryOf(""));

        Result result = instance.wordOf(null, null);

        assert result.isChain() == false;
    }

    @Test
    public void shouldReturnFalseWhenEmptyStart() {
        instance = new WordChain(dictionaryOf(""));

        Result result = instance.wordOf("", "cat");

        assert result.isChain() == false;
    }

    @Test
    public void shouldReturnFalseWhenEmptyEnd() {
        instance = new WordChain(dictionaryOf(""));

        Result result = instance.wordOf("cat", "dog");

        assert result.isChain() == false;
    }

    @Test
    public void shouldReturnFalseWhenInvalidChain() {
        instance = new WordChain(dictionaryOf("DEMONIC", "UMBRELLA"));

        assert instance.wordOf("DEMONIC", "UMBRELLA").isChain() == false;
    }

    @Test
    public void shouldReturnTrueWhenValidChain() {
        instance = new WordChain(dictionaryOf("cat", "cot", "cog", "dog"));

        Result result = instance.wordOf("cat", "dog");

        assert result.isChain() == true;
        assert result.getChains().size() == 1;
        assert result.getChains().get(0).get(0).equals("cat");
        assert result.getChains().get(0).get(1).equals("cot");
        assert result.getChains().get(0).get(2).equals("cog");
        assert result.getChains().get(0).get(3).equals("dog");
    }

    @Test
    public void shouldReturnFalseWhenStartAndEndNonDictionaryWord() {
        instance = new WordChain(dictionaryOf("cot", "cog"));

        Result result = instance.wordOf("cat", "dog");
        assert result.isChain() == false;
    }

    @Test
    public void shouldReturnFalseWhenEndNonDictionaryWord() {
        instance = new WordChain(dictionaryOf("cat", "cot", "cog"));

        Result result = instance.wordOf("cat", "dog");
        assert result.isChain() == false;
    }

    @Test
    public void shouldPerformMinimalStepWhenDuplicateLetters() {
        instance = new WordChain(dictionaryOf("kilt", "kill", "pill"));

        Result result = instance.wordOf("kilt", "pill");

        assert result.isChain() == true;
        assert result.getChains().size() == 1;
        assert result.getChains().get(0).size() == 3;
        assert result.getChains().get(0).get(0).equals("kilt") == true;
        assert result.getChains().get(0).get(1).equals("kill") == true;
        assert result.getChains().get(0).get(2).equals("pill") == true;
    }

    @Ignore
    @Test
    public void thisIsTheReasonWhyThisProgramIsNotValid() {
        instance = new WordChain(dictionaryOf("john", "hohn", "hoon", "hook", "hock", "hick", "wick"));

        Result result = instance.wordOf("john", "wick");

        assert result.isChain() == true;
    }

    private IWordDictionary dictionaryOf(String... words) {
        return new InMemoryWordDictionary(Arrays.asList(words));
    }

}