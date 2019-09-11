package main;

import gui.TemperatureConverterFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TemperatureConverterFrame()::createFrame);
    }
}