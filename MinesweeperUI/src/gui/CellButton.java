package gui;

import javax.swing.*;

public class CellButton {
    private JButton button = new JButton();

    public CellButton() {
    }

    public JButton getButton() {
        return button;
    }

    public void setText(String text){
        button.setText(text);
    }
}
