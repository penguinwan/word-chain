package com.penguinwan.word.chain;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private boolean isChain = false;
    private List<List<String>> chains = new ArrayList<>();

    public Result(boolean isChain) {
        this.isChain = isChain;
    }

    public Result(boolean isChain, List<List<String>> chains) {
        this.isChain = isChain;
        this.chains.addAll(chains);
    }

    public boolean isChain() {
        return isChain;
    }

    public List<List<String>> getChains() {
        return chains;
    }
}
