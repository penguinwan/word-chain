package com.penguinwan.word.chain.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class View {
    private BufferedReader inputReader;
    private String inputOption = "1";
    private Consumer<String> dictionaryFilePathListener;
    private Consumer<List<String[]>> inputListener;


    public View() {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void welcomeMessage() {
        System.out.println("Welcome to word chain solver.");
        System.out.println("When you give me two words, I will tell you whether they could be chained.");
    }

    public void promptForDictionaryFilePath() {
        try {
            System.out.println("Please enter dictionary file's full path");
            String dictionaryFilePath = inputReader.readLine().trim();
            dictionaryFilePathListener.accept(dictionaryFilePath);
        } catch (IOException ex) {
            displayError(ex.toString());
            exit();
        }
    }

    public void promptForInput() {
        try {
            System.out.println("1. Type input in here");
            System.out.println("2. Read input from file");
            System.out.print("Please choose, [1 or 2] ");
            if (inputReader.readLine().trim().equals("2")) {
                inputOption = "2";
            }

            if (inputOption.equals("1")) {
                inputListener.accept(inputFromSystemIn());
            } else {
                inputListener.accept(inputFromFile());
            }
        } catch (IOException ex) {
            displayError(ex.toString());
            exit();
        }
    }

    public List<String[]> inputFromFile() {
        try {
            System.out.println("Now, please key in your input file's full path");
            String inputFileFullPath = inputReader.readLine().trim();
            Path inputFilePath = Paths.get(inputFileFullPath);
            BufferedReader fileReader = Files.newBufferedReader(inputFilePath);

            return fileReader.lines().map(eachLine -> eachLine.split(" ")).filter(each -> each.length == 2).collect(Collectors.toList());
        } catch (IOException ex) {
            displayError(ex.toString());
            exit();
        }

        return Collections.emptyList();
    }

    public List<String[]> inputFromSystemIn() {
        try {
            List<String[]> inputs = new ArrayList<>();
            boolean again = true;
            while (again) {
                System.out.print("Now, please key in your first word: ");
                String start = inputReader.readLine().trim();
                System.out.print("Now, please key in your second word: ");
                String end = inputReader.readLine().trim();
                inputs.add(new String[]{start, end});
                System.out.print("Do you want to key in more inputs? [yes or no] ");
                again = ("yes".equalsIgnoreCase(inputReader.readLine().trim()));
            }
            return inputs;
        } catch (IOException ex) {
            displayError(ex.toString());
            exit();
        }

        return Collections.emptyList();
    }

    public void displayError(String error) {
        System.err.println("Ops...error");
        System.err.println(error);
    }

    public void displayResult(String result) {
        System.out.println(result);
    }

    public void exit() {
        try {
            inputReader.close();
            System.exit(1);
        } catch (Exception ex) {
            // nothing to do
        }
    }

    public void addDictionaryFilePathConsumer(Consumer<String> dictionaryFilePathListener) {
        this.dictionaryFilePathListener = dictionaryFilePathListener;
    }

    public void addInputConsumer(Consumer<List<String[]>> inputListener) {
        this.inputListener = inputListener;
    }
}
