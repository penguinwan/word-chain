package com.penguinwan.word.chain;

import com.penguinwan.word.chain.util.PermutationBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PermutationBuilderTest {

    @Test
    public void buildPermutationFor4() throws Exception {
        // each permutation is different
        assert PermutationBuilder.buildPermutation(4).stream().map(Arrays::toString).distinct().collect(Collectors.toList()).size() == 24;
    }

    @Test
    public void buildPermutationFor5() {
        // each permutation is different
        assert PermutationBuilder.buildPermutation(5).stream().map(Arrays::toString).distinct().collect(Collectors.toList()).size() == 120;

    }

    @Test
    public void buildPermutationFor6() {
        // each permutation is different
        assert PermutationBuilder.buildPermutation(6).stream().map(Arrays::toString).distinct().collect(Collectors.toList()).size() == 720;
    }

}