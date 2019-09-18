package gui;

import javax.swing.*;

public class CellButton {
    private JButton button = new JButton();
    private int posX;
    private int posY;
    private String value;


    public String getValue() {
        return value;
    }

    CellButton(int posX, int posY, String value) {
        this.posX = posX;
        this.posY = posY;
        this.value = value;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public CellButton() {
    }

    public JButton getButton() {
        return button;
    }

    public void setText(String text) {
        button.setText(text);
    }
}
