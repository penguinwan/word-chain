package com.penguinwan.word.chain.util;

import java.util.ArrayList;
import java.util.List;

public final class PermutationBuilder {

    public static List<int[]> buildPermutation(int number) {
        Character[] characters = new Character[number];
        for (int i = 0; i < number; i++) {
            characters[i] = new Character();
            characters[i].character = (i + 1);
        }

        List<int[]> results = new ArrayList();

        permuteRecursively(characters, new int[number], 0, results);

        return results;
    }

    private static void permuteRecursively(Character[] characters, int result[], int level, List<int[]> resultList) {
        if (level == result.length) {
            resultList.add(result.clone());
            return;
        }

        for (Character character : characters) {
            if (character.count == 0) {
                continue;
            }
            result[level] = character.character;
            character.count--;
            permuteRecursively(characters, result, level + 1, resultList);
            character.count++;
        }
    }

    private static class Character {
        int character;
        int count = 1;
    }
}
