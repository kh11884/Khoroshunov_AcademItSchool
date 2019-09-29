package resources;

import javax.swing.*;
import java.awt.*;

public class IconsSet {
    private Icon bombIcon;
    private Icon redBombIcon;
    private Icon flagIcon;
    private Icon flagXIcon;

    public IconsSet() {
        ImageIcon imageBomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\mines_icon.jpg");
        bombIcon = new ImageIcon(imageBomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        ImageIcon imageRedBomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\red_mines_icon.jpg");
        redBombIcon = new ImageIcon(imageRedBomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flag.jpg");
        flagIcon = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

        ImageIcon imageFlagX = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flagX.jpg");
        flagXIcon = new ImageIcon(imageFlagX.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
    }

    public Icon getBombIcon() {
        return bombIcon;
    }

    public Icon getRedBombIcon() {
        return redBombIcon;
    }

    public Icon getFlagIcon() {
        return flagIcon;
    }

    public Icon getFlagXIcon() {
        return flagXIcon;
    }
}