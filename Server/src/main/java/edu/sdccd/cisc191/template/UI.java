package edu.sdccd.cisc191.template;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame{
    private JButton button7;
    private JButton button4;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button6;
    private JButton button9;
    private JButton button8;
    private JButton button5;
    private JButton exitButton;
    private JPanel boardPanel;
    private JLabel player1_Score;
    private JLabel scoreLabel;
    private JLabel player2_score;
    private JLabel hostLabel;
    private JLabel portLabel;
    private JLabel turnLabel;

    private Player player1;

    private Player player2;

    private Board board;

    public UI(){
        player1 = new Player("Player1", 1);
        player2 = new Player("Player2", 2);
        board = new Board(player1, player2);
        setContentPane(boardPanel);
        setTitle("Board");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //exit button to close out application. May change to
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button1.setText("X");
                }
                else{
                    button1.setText("O");
                }
                board.nextPlayer();
                button1.setEnabled(false);
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button2.setText("X");
                }
                else{
                    button2.setText("O");
                }
                board.nextPlayer();
                button2.setEnabled(false);
            }
        });

        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button3.setText("X");
                }
                else{
                    button3.setText("O");
                }
                board.nextPlayer();
                button3.setEnabled(false);
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button4.setText("X");
                }
                else{
                    button4.setText("O");
                }
                board.nextPlayer();
                button4.setEnabled(false);
            }
        });

        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button5.setText("X");
                }
                else{
                    button5.setText("O");
                }
                board.nextPlayer();
                button5.setEnabled(false);
            }
        });

        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button6.setText("X");
                }
                else{
                    button6.setText("O");
                }
                board.nextPlayer();
                button6.setEnabled(false);
            }
        });

        button7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button7.setText("X");
                }
                else{
                    button7.setText("O");
                }
                board.nextPlayer();
                button7.setEnabled(false);
            }
        });

        button8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button8.setText("X");
                }
                else{
                    button8.setText("O");
                }
                board.nextPlayer();
                button8.setEnabled(false);
            }
        });

        button9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button9.setText("X");
                }
                else{
                    button9.setText("O");
                }
                board.nextPlayer();
                button9.setEnabled(false);
            }
        });


        //implement code so that player1 and player2 are changed to usernames entered
        player1_Score.setText("Player1: 0");
        player2_score.setText("Player2: 0");
    }
}
