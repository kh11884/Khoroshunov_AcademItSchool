//package main;
//
//import model.MinesFieldTable;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class FieldButtonClick implements MouseListener {
//
//    private JFrame frame;
//    private JButton button;
//    private String text;
//    private int posX;
//    private int posY;
//    private ButtonsField buttonsField;
//
//    public FieldButtonClick(JFrame frame, ButtonsField buttonsField, JButton button, int posX, int posY, String text) {
//        this.frame = frame;
//        this.button = button;
//        this.text = text;
//        this.posX = posX;
//        this.posY = posY;
//        this.buttonsField = buttonsField;
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        ImageIcon bomb = new ImageIcon(".\\MinesweeperUI\\src\\resources\\mines_icon.jpg");
//
//        if (e.getButton() == MouseEvent.BUTTON1) {
//
//            button.setContentAreaFilled(false);
//            if (text.equals("9")) {
//                button.setIcon(new ImageIcon(bomb.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
//                FaultFrame.createFaultFrame();
//            } else {
//                button.setText(text);
////                System.out.println("x: " + posX + " y: " + posY + " text: " + button.getText());
////                reveal(button.getX(), button.getY());
//            }
//        }
//
//        ImageIcon imageFlag = new ImageIcon(".\\MinesweeperUI\\src\\resources\\flag.jpg");
//        Icon flag = new ImageIcon(imageFlag.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
//
//        if (e.getButton() == MouseEvent.BUTTON3) {
//            if (button.getIcon() == null) {
//                button.setIcon(flag);
//            } else {
//                button.setIcon(null);
//            }
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//
//}
