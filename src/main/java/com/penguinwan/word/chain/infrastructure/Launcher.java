package com.penguinwan.word.chain.infrastructure;

public class Launcher {

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        view.welcomeMessage();
        view.promptForDictionaryFilePath();
        view.promptForInput();
    }
}
