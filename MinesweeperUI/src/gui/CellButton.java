package gui;

import javax.swing.*;
import java.awt.*;

class CellButton {
    private JButton button = new JButton();
    private String value;

    String getValue() {
        return value;
    }

    CellButton(String value) {

        this.value = value;
        button.setFont(new Font("Verdana", Font.BOLD, 9));
    }

    JButton getButton() {
        return button;
    }
}
