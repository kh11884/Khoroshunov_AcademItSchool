package main;

import gui.StartFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartFrame::createStartFrame);
    }
}
