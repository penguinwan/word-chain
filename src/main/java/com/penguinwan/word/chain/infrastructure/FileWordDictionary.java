package com.penguinwan.word.chain.infrastructure;

import com.penguinwan.word.chain.IWordDictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileWordDictionary implements IWordDictionary {
    private List<String> words = new ArrayList();

    public FileWordDictionary(Path filePath) throws InvalidFileException, InvalidFileContentException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null.");
        }
        if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
            throw new InvalidFileException("File path: " + filePath.toString());
        }
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            List<String> words = reader.lines().collect(Collectors.toList());
            this.words.addAll(words);
        } catch (IOException ex) {
            throw new InvalidFileContentException("Error reading dictionary. ", ex);
        }
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
