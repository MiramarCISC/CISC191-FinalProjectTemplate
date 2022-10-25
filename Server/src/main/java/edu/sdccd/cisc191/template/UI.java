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
    private JLabel player2_Score;
    private JLabel hostLabel;
    private JLabel portLabel;
    private JLabel turnLabel;
    private JButton resetButton;

    private Player player1;

    private Player player2;

    private Board board;

    public UI(Board board){
        this.board = board;
        player1 = board.getPlayer1();
        player2 = board.getPlayer2();
                setContentPane(boardPanel);
        setTitle("Board");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
        turnLabel.setText("Turn: " + board.playerMove);

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
                    board.move(player1, 0,0);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button1.setText("O");
                    board.move(player2, 0,0);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button1.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button2.setText("X");
                    board.move(player1, 0,1);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button2.setText("O");
                    board.move(player2, 0,1);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button2.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button3.setText("X");
                    board.move(player1, 0,2);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button3.setText("O");
                    board.move(player2, 0,2);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button3.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button4.setText("X");
                    board.move(player1, 1,0);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button4.setText("O");
                    board.move(player2, 1,0);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button4.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button5.setText("X");
                    board.move(player1, 1,1);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button5.setText("O");
                    board.move(player2, 1,1);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button5.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button6.setText("X");
                    board.move(player1, 1,2);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());

                    }
                }
                else{
                    button6.setText("O");
                    board.move(player2, 1,2);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button6.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button7.setText("X");
                    board.move(player1, 2,0);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button7.setText("O");
                    board.move(player2, 2,0);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button7.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button8.setText("X");
                    board.move(player1, 2,1);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button8.setText("O");
                    board.move(player2, 2,1);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button8.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        button9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                if(board.playerMove == player1){
                    button9.setText("X");
                    board.move(player1, 2,2);
                    if(board.winner(player1)){
                        player1.increaseScore();
                        player1_Score.setText(player1.getUsername() + ": " + player1.getScore());
                    }
                }
                else{
                    button9.setText("O");
                    board.move(player2, 2,2);
                    if(board.winner(player2)){
                        player2.increaseScore();
                        player2_Score.setText(player2.getUsername() + ": " + player2.getScore());
                    }
                }
                board.nextPlayer();
                button9.setEnabled(false);
                turnLabel.setText("Turn: " + board.playerMove);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetBoard();
                button1.setEnabled(true);
                button1.setText("");
                button2.setEnabled(true);
                button2.setText("");
                button3.setEnabled(true);
                button3.setText("");
                button4.setEnabled(true);
                button4.setText("");
                button5.setEnabled(true);
                button5.setText("");
                button6.setEnabled(true);
                button6.setText("");
                button7.setEnabled(true);
                button7.setText("");
                button8.setEnabled(true);
                button8.setText("");
                button9.setEnabled(true);
                button9.setText("");
            }
        });
    }
}
