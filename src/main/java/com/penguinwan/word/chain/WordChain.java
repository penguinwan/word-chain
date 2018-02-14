package com.penguinwan.word.chain;

import com.penguinwan.word.chain.util.PermutationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordChain {
    private static final char[] CHARACTER_SET = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private IWordDictionary dictionary;

    public WordChain(IWordDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Result wordOf(String start, String end) {
        if (isStartAndEndEmpty(start, end)) {
            return new Result(false);
        }

        if (!isStartAndEndSameLength(start, end)) {
            return new Result(false);
        }

        List<int[]> instructions = PermutationBuilder.buildPermutation(end.length());

        ChainBuilder chainBuilder = new ChainBuilder();
        chainBuilder.start = start;
        chainBuilder.end = end;

        List<List<String>> results = instructions.
                stream().
                map(chainBuilder::build).
                filter(this::isWholeChainValidDictionaryWord).
                distinct().
                collect(Collectors.toList());

        if (!results.isEmpty()) {
            return new Result(true, results);
        } else {
            return new Result(false);
        }

    }

    private boolean isStartAndEndSameLength(String start, String end) {
        if (start.length() == end.length()) {
            return true;
        }
        return false;
    }

    private boolean isStartAndEndEmpty(String start, String end) {
        if (start == null ||
                start.length() == 0 ||
                end == null ||
                end.length() == 0) {
            return true;
        }
        return false;
    }

    private boolean isWholeChainValidDictionaryWord(List<String> words) {
        return words.stream().filter(dictionary::hasWord).count() == words.size();
    }

    class ChainBuilder {
        String start;
        String end;

        List<String> build(int[] instructions) {
            List<String> result = new ArrayList();
            result.add(start);

            char[] editable = start.toCharArray();
            char[] substitute = end.toCharArray();
            for (int instruction : instructions) {
                // if both the character is the same, skip this instruction
                if (editable[instruction - 1] == substitute[instruction - 1]) {
                    continue;
                }

                editable[instruction - 1] = substitute[instruction - 1];
                result.add(String.valueOf(editable));
            }

            return result;
        }

    }

}
