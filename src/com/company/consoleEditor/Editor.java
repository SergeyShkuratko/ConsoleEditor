package com.company.consoleEditor;

import java.util.List;

public interface Editor {

    List<String> readFile(String fileName);

    void write(String fileName, String rowValue);

    void append(String fileName, String rowValue);

    void appendToLine(String fileName, String rowValue, int lineNumber);
}
