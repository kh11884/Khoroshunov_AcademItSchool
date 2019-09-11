package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class BunchOfButtons extends JPanel {
    private static final String[] TEXTS = {"One", "Two", "Three", "Four", "Five"};
    private ButtonGroup btnGroup = new ButtonGroup();
    private JTextField textField = new JTextField(20);

    public BunchOfButtons() {
        JPanel btnPanel = new JPanel(new GridLayout(1, 0, 5, 0));
        BtnListener btnListener = new BtnListener();
        for (String text : TEXTS) {
            JToggleButton toggleBtn = new JToggleButton(text);
            toggleBtn.addActionListener(btnListener);
            toggleBtn.setActionCommand(text);
            btnPanel.add(toggleBtn);
            btnGroup.add(toggleBtn);
        }
        JPanel otherPanel = new JPanel();
        otherPanel.add(textField ); // just to take focus elsewhere
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(0, 1, 0, 15));
        add(btnPanel);
        add(otherPanel);
    }
    private class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent aEvt) {
            textField.setText(aEvt.getActionCommand());
        }
    }
    private static void createAndShowGui() {
        BunchOfButtons mainPanel = new BunchOfButtons();
        JFrame frame = new JFrame("BunchOfButtons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
