package gui;

import model.MinesFieldTable;
import resources.IconsSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ButtonsField {
    private int width;
    private int height;
    private int minesQuantity;
    private CellButton[][] buttonsField;
    private boolean[][] revealed;
    private int unRevealedCellsQuantity;
    private int flagsQuantity;
    private IconsSet iconsSet;

    ButtonsField(int width, int height, int minesQuantity) {
        this.width = width;
        this.height = height;
        this.minesQuantity = minesQuantity;
        unRevealedCellsQuantity = width * height;
        buttonsField = new CellButton[height][width];
        revealed = new boolean[height][width];
        iconsSet = new IconsSet();
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

    private void openField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                JButton checkButton = buttonsField[y][x].getButton();
                if (buttonsField[y][x].getValue().equals("9")) {
                    if (checkButton.getIcon() == null) {
                        checkButton.setIcon(iconsSet.getBombIcon());
                    }
                } else if (checkButton.getIcon() != null) {
                    checkButton.setIcon(iconsSet.getFlagXIcon());
                }
            }
        }
        FaultFrame.createFaultFrame();
    }

    public class MouseClicker extends MouseAdapter {
        private int posX;
        private int posY;

        MouseClicker(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JButton button = buttonsField[posY][posX].getButton();
            String value = buttonsField[posY][posX].getValue();

            if (e.getButton() == MouseEvent.BUTTON1) {
                if (button.getIcon() == null) {
                    if (value.equals("9")) {
                        button.setIcon(iconsSet.getRedBombIcon());
                        openField();
                    } else {
                        reveal(posX, posY);
                    }
                }
            }
            if (e.getButton() == MouseEvent.BUTTON2) {
                if (revealed[posY][posX]) {
                    int nearCellsFlagsQuantity = 0;
                    for (int y = posY - 1; y <= posY + 1; y++) {
                        for (int x = posX - 1; x <= posX + 1; x++) {
                            if (!outBounds(x, y)) {
                                if (buttonsField[y][x].getButton().getIcon() != null) {
                                    nearCellsFlagsQuantity++;
                                }
                            }
                        }
                    }
                    if (nearCellsFlagsQuantity == Integer.valueOf(value)) {
                        boolean isFault = false;
                        for (int y = posY - 1; y <= posY + 1; y++) {
                            for (int x = posX - 1; x <= posX + 1; x++) {
                                if (!outBounds(x, y)) {
                                    if (buttonsField[y][x].getValue().equals("9") && buttonsField[y][x].getButton().getIcon() == null) {
                                        isFault = true;
                                        buttonsField[y][x].getButton().setIcon(iconsSet.getRedBombIcon());
                                    }
                                }
                                reveal(x, y);
                            }
                        }
                        if (isFault) {
                            openField();
                        }
                    }
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (!revealed[posY][posX]) {
                    if (button.getIcon() == null) {
                        if (flagsQuantity < minesQuantity) {
                            button.setIcon(iconsSet.getFlagIcon());
                            flagsQuantity++;
                        }
                    } else {
                        button.setIcon(null);
                        flagsQuantity--;
                    }
                }
            }
            if (unRevealedCellsQuantity == minesQuantity && flagsQuantity == minesQuantity) {
                int timerResult = GameField.timerValue.get();

                if (GameField.recordTable.isNewRecord(timerResult)) {
                    NewRecordWinFrame.createNewRecordWinFrame(timerResult);
                } else {
                    WinFrame.createWinFrame();
                }
            }
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
        String text = buttonsField[y][x].getValue();
        unRevealedCellsQuantity--;
        button.setContentAreaFilled(false);
        if (text.equals("0") || text.equals("9")) {
            button.setText("");
        } else {
            button.setText(text);
        }
        revealed[y][x] = true;

        if (!text.equals("0")) {
            return;
        }
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
