package com.company.consoleEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleEditor consoleEditor = new ConsoleEditor();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Waiting command");
            while (true) {
                String inputCommand = br.readLine();
                String[] commandArray = inputCommand.split(" ");
                String command = commandArray[0];

                if ("exit".equals(inputCommand)) {
                    System.out.println("Exiting");
                    break;
                }

                checkRead(consoleEditor, commandArray, command);

                checkWrite(consoleEditor, commandArray, command);

                checkAppend(consoleEditor, commandArray, command);

                checkAppendToLine(consoleEditor, commandArray, command);

            }
        } catch (IOException e) {
            System.out.println("ERROR!" + "\n" + e.getMessage());
        }
    }

    private static void checkAppendToLine(ConsoleEditor consoleEditor, String[] commandArray, String command) {
        if ("appendToLine".equals(command)) {
            if (commandArray.length != 4) {
                throw new RuntimeException("Incorrect params");
            }
            String fileName = commandArray[1].replaceAll("\"", "");
            String value = commandArray[2].replaceAll("\"", "");
            int lineNumber = Integer.parseInt(commandArray[3].replaceAll("\"", ""));
            consoleEditor.appendToLine(fileName, value, lineNumber);
        }
    }

    private static void checkAppend(ConsoleEditor consoleEditor, String[] commandArray, String command) {
        if ("append".equals(command)) {
            if (commandArray.length != 3) {
                throw new RuntimeException("Incorrect params");
            }
            String fileName = commandArray[1].replaceAll("\"", "");
            String value = commandArray[2].replaceAll("\"", "");
            consoleEditor.append(fileName, value);
        }
    }

    private static void checkWrite(ConsoleEditor consoleEditor, String[] commandArray, String command) {
        if ("write".equals(command)) {
            if (commandArray.length != 3) {
                throw new RuntimeException("Incorrect params");
            }
            String fileName = commandArray[1].replaceAll("\"", "");
            String value = commandArray[2].replaceAll("\"", "");
            consoleEditor.write(fileName, value);
        }
    }

    private static void checkRead(ConsoleEditor consoleEditor, String[] commandArray, String command) {
        if ("read".equals(command)) {
            if (commandArray.length != 2) {
                throw new RuntimeException("Incorrect params");
            }
            List<String> strings = consoleEditor.readFile(commandArray[1].replaceAll("\"", ""));
            for (String string : strings) {
                System.out.println(string);
            }
        }
    }


}
