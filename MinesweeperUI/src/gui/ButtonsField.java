package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsField {
    private int width;
    private int heigth;
    private CellButton[][] buttonsField;

    public ButtonsField(int width, int height) {
        this.width = width;
        this.heigth = height;
        buttonsField = new CellButton[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j< width; j++){
                buttonsField[i][j] = new CellButton(j, i);
            }
        }
    }

    public CellButton[][] getButtonsField() {
        return buttonsField;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public JButton getButton(int indexWidth, int indexHeight){
        return buttonsField[indexHeight][indexWidth].getButton();
    }
}
