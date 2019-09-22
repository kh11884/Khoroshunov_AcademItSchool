package gui;

import model.MinesFieldTable;

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

    private void openField() {
        ImageIcon bomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\mines_icon.jpg");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                JButton checkButton = buttonsField[y][x].getButton();
                if (buttonsField[y][x].getValue().equals("9")) {
                    if (checkButton.getIcon() == null) {
                        checkButton.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
                    }
                } else if (checkButton.getIcon() != null) {
                    setFlagX(checkButton);
                }
            }
        }
        FaultFrame.createFaultFrame();
    }

    private void setRedBomb(JButton button) {
        ImageIcon bomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\red_mines_icon.jpg");
        button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
    }

    private void setFlag(JButton button) {
        ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flag.jpg");
        Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        button.setIcon(flag);
    }

    private void setFlagX(JButton button) {
        ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flagX.jpg");
        Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        button.setIcon(flag);
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
                if (value.equals("9")) {
                    setRedBomb(button);
                    openField();
                } else {
                    reveal(posX, posY);
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
                                        setRedBomb(buttonsField[y][x].getButton());
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
                            setFlag(button);
                            flagsQuantity++;
                        }
                    } else {
                        button.setIcon(null);
                        flagsQuantity--;
                    }
                }
            }

            if (unRevealedCellsQuantity == minesQuantity && flagsQuantity == minesQuantity) {

                WinFrame.createWinFrame();
                int timerResult = GameField.timerValue.get();
                String name = null;
                if(GameField.recordTable.isNewRecord(timerResult)){
                    name = NewRecordDialog.createNewRecordDialog();
                }
                if(name != null){
                    if (name.length() > 17) {
                        name = name.substring(0, 16);
                    }
                    GameField.recordTable.addNewRecord(name, timerResult);
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
