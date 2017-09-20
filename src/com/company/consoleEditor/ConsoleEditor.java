package com.company.consoleEditor;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ConsoleEditor implements Editor {


    @Override
    public List<String> readFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.singletonList("Read file error");
    }

    @Override
    public void write(String fileName, String rowValue) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(rowValue);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void append(String fileName, String rowValue) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("\n" + rowValue);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String fileName, List<String> values) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (String string : values) {
            stringJoiner.add(string);
        }
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(stringJoiner.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void appendToLine(String fileName, String rowValue, int lineNumber) {
        ArrayList<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (i == lineNumber) {
                    result.add(rowValue);
                }
                result.add(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        write(fileName, result);
    }
}
