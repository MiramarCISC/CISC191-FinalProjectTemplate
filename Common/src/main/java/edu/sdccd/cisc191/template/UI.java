package edu.sdccd.cisc191.template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

//instead of using Javafx, used swing so that dependencies would not have to be installed for Maven
public class UI extends JFrame {
    private ArrayList<JLabel> labels = new ArrayList<>();
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
    private JLabel player1_Score; //had this commented out for the arraylist portion but didnt work
    private JLabel scoreLabel;
    private JLabel player2_Score; //had this commented out for the arraylist portion but didnt work
    private JLabel hostLabel;
    private JLabel portLabel;
    private JLabel turnLabel; //had this commented out for the arraylist portion but didnt work
    private JButton resetButton;

    private Player player1;

    private Player player2;

    private Board board;

    public UI(Board board) {
        this.board = board;
        labels.add(player1_Score); //player1's score (0)
        labels.add(player2_Score); //(1) player2's score
        labels.add(turnLabel); //(2) turnLabel
        player1 = board.getPlayer1();
        player2 = board.getPlayer2();
        setContentPane(boardPanel);
        setTitle("Board");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
        labels.get(2).setText("Turn: " + board.playerMove);

//        //exit button to close out application. May change to
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //button1 is the top left, button 2 is the top middle, and continues reading from top left to bottom right
        //once a button is pressed, the button greys/disables out, is changed to either blue or red and is an x or an
        //o depending on which players turn it is
        //if a winner is determined, the buttons reset themselves, the player that won has their score increased by one,
        //if no player is determined, the player can press the reset button to reset the board
        //since buttons were not put into an array, all the logic for each button was hard coded (copy and pasted)
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button1.setText("X");
                    button1.setBackground(Color.RED);
                    board.move(player1, 0, 0);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button1.setEnabled(false);
                    }
                } else {
                    button1.setText("O");
                    button1.setBackground(Color.BLUE);
                    board.move(player2, 0, 0);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else{

                        button1.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)  {
                button2.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button2.setText("X");
                    button2.setBackground(Color.RED);
                    board.move(player1, 0, 1);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();

                    }
                    else {
                        button2.setEnabled(false);
                    }
                } else{
                    button2.setText("O");
                    button2.setBackground(Color.BLUE);
                    board.move(player2, 0, 1);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();

                    }
                    else {

                        button2.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button3.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button3.setText("X");
                    button3.setBackground(Color.RED);
                    board.move(player1, 0, 2);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button3.setEnabled(false);
                    }
                } else {
                    button3.setText("O");
                    button3.setBackground(Color.BLUE);
                    board.move(player2, 0, 2);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }

                    else {

                        button3.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button4.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button4.setText("X");
                    button4.setBackground(Color.RED);
                    board.move(player1, 1, 0);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button4.setEnabled(false);
                    }
                } else {
                    button4.setText("O");
                    button4.setBackground(Color.BLUE);
                    board.move(player2, 1, 0);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    } else {

                        button4.setEnabled(false);

                    }
                }
                board.nextPlayer();

            }
        });

        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button5.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button5.setText("X");
                    button5.setBackground(Color.RED);
                    board.move(player1, 1, 1);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button5.setEnabled(false);
                    }
                } else {
                    button5.setText("O");
                    button5.setBackground(Color.BLUE);
                    board.move(player2, 1, 1);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else {

                        button5.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button6.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button6.setText("X");
                    button6.setBackground(Color.RED);
                    board.move(player1, 1, 2);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();

                    }
                    else {
                        button6.setEnabled(false);
                    }
                } else {
                    button6.setText("O");
                    button6.setBackground(Color.BLUE);
                    board.move(player2, 1, 2);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else {

                        button6.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button7.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button7.setText("X");
                    button7.setBackground(Color.RED);
                    board.move(player1, 2, 0);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button7.setEnabled(false);
                    }
                } else {
                    button7.setText("O");
                    button7.setBackground(Color.BLUE);
                    board.move(player2, 2, 0);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else {

                        button7.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button8.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button8.setText("X");
                    button8.setBackground(Color.RED);
                    board.move(player1, 2, 1);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button8.setEnabled(false);
                    }
                } else {
                    button8.setText("O");
                    button8.setBackground(Color.BLUE);
                    board.move(player2, 2, 1);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else {

                        button8.setEnabled(false);

                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        button9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                button9.setFont(new Font("MV Boli", Font.BOLD, 20));
                if (board.playerMove == player1) {
                    button9.setText("X");
                    button9.setBackground(Color.RED);
                    board.move(player1, 2, 2);
                    if (board.winner(player1)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player1.increaseScore();
                        labels.get(0).setText(player1.getUsername() + ": " + player1.getScore());
                        board.resetBoard();
                    }
                    else {
                        button9.setEnabled(false);
                    }
                } else {
                    button9.setText("O");
                    button9.setBackground(Color.BLUE);
                    board.move(player2, 2, 2);
                    if (board.winner(player2)) {

                        button1.setBackground(new Color(223, 234, 244));
                        button1.setEnabled(true);
                        button1.setText("");
                        button2.setEnabled(true);
                        button2.setText("");
                        button2.setBackground(new Color(223, 234, 244));
                        button3.setEnabled(true);
                        button3.setText("");
                        button3.setBackground(new Color(223, 234, 244));
                        button4.setEnabled(true);
                        button4.setText("");
                        button4.setBackground(new Color(223, 234, 244));
                        button5.setEnabled(true);
                        button5.setText("");
                        button5.setBackground(new Color(223, 234, 244));
                        button6.setEnabled(true);
                        button6.setText("");
                        button6.setBackground(new Color(223, 234, 244));
                        button7.setEnabled(true);
                        button7.setText("");
                        button7.setBackground(new Color(223, 234, 244));
                        button8.setEnabled(true);
                        button8.setText("");
                        button8.setBackground(new Color(223, 234, 244));
                        button9.setEnabled(true);
                        button9.setText("");
                        button9.setBackground(new Color(223, 234, 244));
                        player2.increaseScore();
                        labels.get(1).setText(player2.getUsername() + ": " + player2.getScore());
                        board.resetBoard();
                    }
                    else {
                        button9.setEnabled(false);
                    }
                }
                board.nextPlayer();
                labels.get(2).setText("Turn: " + board.playerMove);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetBoard();
                button1.setBackground(new Color(223, 234, 244));
                button1.setEnabled(true);
                button1.setText("");
                button2.setEnabled(true);
                button2.setText("");
                button2.setBackground(new Color(223, 234, 244));
                button3.setEnabled(true);
                button3.setText("");
                button3.setBackground(new Color(223, 234, 244));
                button4.setEnabled(true);
                button4.setText("");
                button4.setBackground(new Color(223, 234, 244));
                button5.setEnabled(true);
                button5.setText("");
                button5.setBackground(new Color(223, 234, 244));
                button6.setEnabled(true);
                button6.setText("");
                button6.setBackground(new Color(223, 234, 244));
                button7.setEnabled(true);
                button7.setText("");
                button7.setBackground(new Color(223, 234, 244));
                button8.setEnabled(true);
                button8.setText("");
                button8.setBackground(new Color(223, 234, 244));
                button9.setEnabled(true);
                button9.setText("");
                button9.setBackground(new Color(223, 234, 244));
            }
        });
    }
}