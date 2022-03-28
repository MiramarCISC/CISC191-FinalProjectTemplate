package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static edu.sdccd.cisc191.template.Wordle.initialize;
import static org.junit.jupiter.api.Assertions.*;

class WordleTest {


    @BeforeEach
    void setUp() {

    }

    @Test
    void excludeLetters() {
        ArrayList<Wordle> wordList = initialize();
        String test = "asdf";
        String[] array = new String[test.length()];
        for (int i = 0; i < test.length(); i++) {
            array[i] = Character.toString(test.charAt(i));
        }
        System.out.println(array[1]);
        Wordle.excludeLetters(wordList, test);
        for (String s : array)
            for (Wordle wordle : wordList) {
                if (wordle.getPositionalLetter()
                        && wordle.getTestString().contains(s))
                    fail();
            }

    }

    @Test
    void containsLetters() {
        ArrayList<Wordle> wordList = initialize();
        String positionOne = "";
        String positionTwo = "r";
        String positionThree = "";
        String positionFour = "";
        String positionFive = "k";
        String a;

        Wordle.containsLetters(wordList, positionOne, positionTwo, positionThree, positionFour, positionFive);

        for (int i = 0; i < wordList.size(); i++) {
            a = Character.toString(wordList.get(i).getTestString().charAt(0));
            if(a.equals(positionOne) && wordList.get(i).getContainedLettersOne())
                fail();

            a = Character.toString(wordList.get(i).getTestString().charAt(1));
            if(a.equals(positionTwo) && wordList.get(i).getContainedLettersTwo())
                fail();

            a = Character.toString(wordList.get(i).getTestString().charAt(2));
            if(a.equals(positionThree) && wordList.get(i).getContainedLettersThree())
                fail();

            a = Character.toString(wordList.get(i).getTestString().charAt(3));
            if(a.equals(positionFour) && wordList.get(i).getContainedLettersFour())
                fail();

            a = Character.toString(wordList.get(i).getTestString().charAt(4));
            if(a.equals(positionFive) && wordList.get(i).getContainedLettersFive())
                fail();
        }
    }

    @Test
    void positionalLetters() {
        ArrayList<Wordle> wordList = initialize();
        Wordle.positionalLetters(wordList, "t", "r", "e", "a", "t");
        int counter = 0;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getPositionalLetter())
                counter += 1;
        }
        assertEquals(1, counter);
    }

    @Test
    void resetValues() {
        ArrayList<Wordle> wordList = initialize();
        for (int i = 0; i < wordList.size(); i++) {
            wordList.get(i).setExcludedLetters(true);
            wordList.get(i).setContainedLetters(true);
            wordList.get(i).setContainedLettersOne(true);
            wordList.get(i).setContainedLettersTwo(true);
            wordList.get(i).setContainedLettersThree(true);
            wordList.get(i).setContainedLettersFour(true);
            wordList.get(i).setContainedLettersFive(true);
            wordList.get(i).setPositionalLettersOne(true);
            wordList.get(i).setPositionalLettersTwo(true);
            wordList.get(i).setPositionalLettersThree(true);
            wordList.get(i).setPositionalLettersFour(true);
            wordList.get(i).setPositionalLettersFive(true);
            wordList.get(i).setPositionalLetters(true);

        }
        Wordle.resetValues(wordList);
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getExcludedLetters() ||
            wordList.get(i).getContainedLettersOne() ||
            wordList.get(i).getContainedLettersTwo() ||
            wordList.get(i).getContainedLettersThree() ||
            wordList.get(i).getContainedLettersFour() ||
            wordList.get(i).getContainedLettersFive() ||
            wordList.get(i).getContainedLetters() ||
            wordList.get(i).getPositionalLettersOne() ||
            wordList.get(i).getPositionalLettersTwo() ||
            wordList.get(i).getPositionalLettersThree() ||
            wordList.get(i).getPositionalLettersFour() ||
            wordList.get(i).getPositionalLettersFive() ||
            wordList.get(i).getPositionalLetter()
            )
                fail();
        }


    }
}