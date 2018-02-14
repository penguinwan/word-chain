package com.penguinwan.word.chain.infrastructure;

import com.penguinwan.word.chain.Result;
import com.penguinwan.word.chain.WordChain;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private View view;
    private WordChain wordChain;

    public Controller(View view) {
        this.view = view;

        this.view.addDictionaryFilePathConsumer(this::consumeDictionaryFilePath);
        this.view.addInputConsumer(this::consumerInput);
    }

    public void consumeDictionaryFilePath(String dictionaryPath) {
        try {
            wordChain = new WordChain(new FileWordDictionary(Paths.get(dictionaryPath)));
        } catch (Exception ex) {
            view.displayError(ex.getMessage());
            view.exit();
        }
    }

    public void consumerInput(List<String[]> inputs) {
        List<Result> results = inputs.stream().map(each -> wordChain.wordOf(each[0], each[1])).collect(Collectors.toList());
        results.stream().forEach(result -> {
            if (result.isChain()) {
                view.displayResult("YES " + result.getChains());
            } else {
                view.displayResult("NO");
            }
        });
    }
}
