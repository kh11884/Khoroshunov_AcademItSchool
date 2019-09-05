package main;

import view.MineField;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MineField::createMineField);
    }
}
