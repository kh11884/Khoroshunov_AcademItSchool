package gui;

import model.MinesFieldTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonsField {
    private int width;
    private int height;
    private CellButton[][] buttonsField;
    private boolean[][] revealed;

    public ButtonsField(int width, int height) {
        this.width = width;
        this.height = height;
        buttonsField = new CellButton[height][width];
        revealed = new boolean [height][width];
        MinesFieldTable minesFieldTable = new MinesFieldTable(10, width, height);
        for(int y = 0; y < height; y++){
            for(int x = 0; x< width; x++){
                String cellValue = String.valueOf(minesFieldTable.getCellValue(y, x));
                buttonsField[y][x] = new CellButton(x, y, cellValue);
                buttonsField[y][x].getButton().addMouseListener(new MouseClicker(x, y));
            }
        }
    }

    public class MouseClicker implements MouseListener {
        private int posX;
        private int posY;

        public MouseClicker(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ImageIcon bomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\mines_icon.jpg");
            JButton button = buttonsField[posY][posX].getButton();
            String value =  buttonsField[posY][posX].getValue();

            if (e.getButton() == MouseEvent.BUTTON1) {

                if (value.equals("9")) {
                    button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
                    FaultFrame.createFaultFrame();
                } else {
                    if(value.equals("0")){
                        button.setText("");
                        reveal(posX, posY);
                    }else {
                        button.setContentAreaFilled(false);
                        button.setText(value);
                    }
                System.out.println("x: " + posX + " y: " + posY + " text: " + button.getText());

                }
            }

            ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flag.jpg");
            Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (button.getIcon() == null) {
                    button.setIcon(flag);
                } else {
                    button.setIcon(null);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public CellButton[][] getButtonsField() {
        return buttonsField;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JButton getButton(int indexWidth, int indexHeight){
        return buttonsField[indexHeight][indexWidth].getButton();
    }

    void reveal(int x, int y) {
        if (outBounds(x, y)) return;
        if(revealed[y][x])return;
        JButton button = buttonsField[y][x].getButton();
        String text = buttonsField[y][x].getValue();
        button.setContentAreaFilled(false);
        if (text.equals("0")){
            button.setText("");
        }else {
            button.setText(text);
        }
        revealed[y][x] = true;
        if(!text.equals("0"))return;
        reveal(x + 1, y + 1);
        reveal(x - 1, y - 1);
        reveal(x - 1, y + 1);
        reveal(x + 1, y - 1);
        reveal(x - 1, y);
        reveal(x + 1, y);
        reveal(x, y - 1);
        reveal(x, y + 1);
    }

    private boolean outBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }
}
