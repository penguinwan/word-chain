package com.penguinwan.word.chain.infrastructure;

import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.fail;

public class FileWordDictionaryTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullFilePathShouldThrowException() throws Exception {
        new FileWordDictionary(null);
        fail("Expected: IllegalArgumentException ; Result: None");
    }

    @Test(expected = InvalidFileException.class)
    public void invalidFilePathShouldThrowException() throws Exception {
        String invalidFilePath = "/non/exist.txt";
        new FileWordDictionary(Paths.get(invalidFilePath));
        fail("Expected: InvalidFileException ; Result: None");
    }

    @Test
    public void validFileShouldPopulateWords() throws Exception {
        URL url = FileWordDictionaryTest.class.getResource("FileWordDictionaryTest.txt");

        Path path = Paths.get(url.toURI());
        FileWordDictionary dictionary = new FileWordDictionary(path);
        assert dictionary.hasWord("name") == true;
    }

    @Test
    public void isCaseSensitive() throws Exception {
        URL url = FileWordDictionaryTest.class.getResource("FileWordDictionaryTest.txt");

        Path path = Paths.get(url.toURI());
        FileWordDictionary dictionary = new FileWordDictionary(path);
        assert dictionary.hasWord("cat") == false;
    }
}