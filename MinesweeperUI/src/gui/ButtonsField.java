package gui;

import model.MinesFieldTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ButtonsField {
    private int width;
    private int height;
    private int minesQuantity;
    private CellButton[][] buttonsField;
    private boolean[][] revealed;
    private int unRevealedCellsQuantity;
    private int flagsQuantity;

    ButtonsField(int width, int height, int minesQuantity) {
        this.width = width;
        this.height = height;
        this.minesQuantity = minesQuantity;
        unRevealedCellsQuantity = width * height;
        buttonsField = new CellButton[height][width];
        revealed = new boolean[height][width];
        MinesFieldTable minesFieldTable = new MinesFieldTable(minesQuantity, width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int nearMinesQuantity = minesFieldTable.getCellValue(y, x);
                CellButton cellButton = new CellButton(String.valueOf(nearMinesQuantity));
                cellButton.getButton().addMouseListener(new MouseClicker(x, y));
                switch (nearMinesQuantity) {
                    case 1:
                        cellButton.getButton().setForeground(Color.BLUE);
                        break;
                    case 2:
                        cellButton.getButton().setForeground(Color.GREEN);
                        break;
                    case 3:
                        cellButton.getButton().setForeground(Color.RED);
                        break;
                    case 4:
                        cellButton.getButton().setForeground(Color.BLACK);
                        break;
                }
                buttonsField[y][x] = cellButton;
            }
        }
    }

    public class MouseClicker implements MouseListener {
        private int posX;
        private int posY;

        MouseClicker(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ImageIcon bomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\mines_icon.jpg");
            JButton button = buttonsField[posY][posX].getButton();
            String value = buttonsField[posY][posX].getValue();
            if (e.getButton() == MouseEvent.BUTTON1) {

                if (value.equals("9")) {
                    button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
                    FaultFrame.createFaultFrame();
                } else {
                    if (value.equals("0")) {
                        button.setText("");
                        reveal(posX, posY);
                    } else {
                        button.setContentAreaFilled(false);
                        unRevealedCellsQuantity--;
                        button.setText(value);
                    }
                }
            }

            ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flag.jpg");
            Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

            if (e.getButton() == MouseEvent.BUTTON3) {
                if (!revealed[posY][posX]) {
                    if (button.getIcon() == null) {
                        if (flagsQuantity < minesQuantity) {
                            button.setIcon(flag);
                            flagsQuantity++;
                        }
                    } else {
                        button.setIcon(null);
                        flagsQuantity--;
                    }
                }
            }
            if (unRevealedCellsQuantity == minesQuantity && flagsQuantity == minesQuantity) {
                GameField.recordTable.addNewRecord(GameField.timerValue.get());
                WinFrame.createWinFrame();
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

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    JButton getButton(int indexWidth, int indexHeight) {
        return buttonsField[indexHeight][indexWidth].getButton();
    }

    private void reveal(int x, int y) {
        if (outBounds(x, y)) return;
        if (revealed[y][x]) return;
        JButton button = buttonsField[y][x].getButton();
        if (button.getIcon() != null) {
            return;
        }

        unRevealedCellsQuantity--;
        String text = buttonsField[y][x].getValue();
        button.setContentAreaFilled(false);
        if (text.equals("0")) {
            button.setText("");
        } else {
            button.setText(text);
        }
        revealed[y][x] = true;

        if (!text.equals("0")) return;
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
