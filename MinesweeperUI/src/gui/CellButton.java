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
        Insets insets = new Insets(0, 0, 0, 0);
        button.setMargin(insets);
        button.setFont(new Font("verdana", Font.BOLD, 14));
    }

    JButton getButton() {
        return button;
    }
}
