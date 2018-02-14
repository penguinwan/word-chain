package com.penguinwan.word.chain.infrastructure;

import com.penguinwan.word.chain.IWordDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryWordDictionary implements IWordDictionary {
    private List<String> words = new ArrayList<>();

    public InMemoryWordDictionary(List<String> words) {
        this.words.addAll(words);
    }

    @Override
    public boolean hasWord(String word) {
        Optional<String> result = words.
                stream().
                filter(dictionaryWord -> dictionaryWord.equals(word)).
                findFirst();
        if (result.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
