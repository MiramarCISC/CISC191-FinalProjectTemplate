package edu.sdccd.cisc191.template;

import javax.swing.*;

public class UI extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton exitButton;
    private JPanel board;

    public UI(){
        setContentPane(board);
        setTitle("Board");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        UI ui = new UI();
    }
}
