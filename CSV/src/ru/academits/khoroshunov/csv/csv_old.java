package ru.academits.khoroshunov.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class csv_old {

    public static void convertCSVToHtml(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Вы указали неправильное количество аргументов программы.");
            System.out.println("Нужно указать два аргумента:");
            System.out.println("1. Путь к CSV-файлу.");
            System.out.println("2. Путь к html-файлу.");
            return;
        }
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        File htmlFile = new File(outputFilePath);
        try (PrintWriter writer = new PrintWriter(htmlFile)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<title>Результат работы конвертера csv_to_html</title>");
            writer.println("<style>");
            writer.println("table{");
            writer.println("border: solid 1px black;");
            writer.println("border-collapse: collapse;");
            writer.println("margin: auto;");
            writer.println("text-align:center;");
            writer.println("}");
            writer.println("td{");
            writer.println("border: solid 1px black;");
            writer.println("}");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table>");

            boolean isNewLine = true;
            boolean isQuote = false;
            boolean isNewCell = true;

            try (Scanner scanner = new Scanner(new FileInputStream(inputFilePath))) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();

                    if (isNewLine) {
                        writer.println("<tr>");
                    }
                    if (isNewCell) {
                        writer.println("<td>");
                    }
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        switch (c) {
                            case '"':
                                if (isQuote) {
                                    isQuote = false;
                                    isNewLine = true;
                                    isNewCell = true;
                                } else {
                                    isQuote = true;
                                    isNewLine = false;
                                    isNewCell = false;
                                    if (i != 0) {
                                        if (line.charAt(i - 1) == '\"') {
                                            writer.print("\"");
                                        }
                                    }
                                }
                                break;
                            case '<':
                                writer.print("&lt;");
                                break;
                            case '>':
                                writer.print("&gt;");
                                break;
                            case '&':
                                writer.print("&amp;");
                                break;
                            case ',':
                                if (isNewCell) {
                                    writer.println("");
                                    writer.println("</td>");
                                    writer.println("<td>");
                                } else {
                                    writer.print(",");
                                }
                                break;
                            default:
                                writer.print(c);
                        }
                    }
                    if (isNewLine) {
                        writer.println("");
                        writer.println("</td>");
                        writer.println("</tr>");
                    } else {
                        writer.println("<br/>");
                    }
                }
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}

