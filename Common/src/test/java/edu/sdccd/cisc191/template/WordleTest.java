package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static edu.sdccd.cisc191.template.Wordle.initialize;
import static edu.sdccd.cisc191.template.Wordle.mergeSort;
import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @Test
    void excludeLetters() {
        ArrayList<Wordle> wordList = initialize();
        String test = "asdf";
        String[] array = new String[test.length()];
        for (int i = 0; i < test.length(); i++) {
            array[i] = Character.toString(test.charAt(i));
        }
        System.out.println(array[1]);
        assert wordList != null;
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
        String positionOne = "e";
        String positionTwo = "r";
        String positionThree = "";
        String positionFour = "";
        String positionFive = "";
        assert wordList != null;
        Wordle.containsLetters(wordList, positionOne, positionTwo, positionThree, positionFour, positionFive);

        assertFalse(wordList.get(0).getContainedLetters());
        assertTrue(wordList.get(1).getContainedLetters());
        assertFalse(wordList.get(2).getContainedLetters());
        assertFalse(wordList.get(3).getContainedLetters());
        assertFalse(wordList.get(4).getContainedLetters());
        assertFalse(wordList.get(5).getContainedLetters());
        assertFalse(wordList.get(6).getContainedLetters());
        assertFalse(wordList.get(7).getContainedLetters());
        assertFalse(wordList.get(8).getContainedLetters());
        assertTrue(wordList.get(9).getContainedLetters());
    }

    @Test
    void containsLettersEliminateByPosition() {
        ArrayList<Wordle> wordList = initialize();
        String positionOne = "";
        String positionTwo = "r";
        String positionThree = "";
        String positionFour = "";
        String positionFive = "k";
        String a;

        assert wordList != null;
        Wordle.containsLetters(wordList, positionOne, positionTwo, positionThree, positionFour, positionFive);

        for (Wordle wordle : wordList) {
            a = Character.toString(wordle.getTestString().charAt(0));
            if (a.equals(positionOne) && wordle.getContainedLettersOne())
                fail();

            a = Character.toString(wordle.getTestString().charAt(1));
            if (a.equals(positionTwo) && wordle.getContainedLettersTwo())
                fail();

            a = Character.toString(wordle.getTestString().charAt(2));
            if (a.equals(positionThree) && wordle.getContainedLettersThree())
                fail();

            a = Character.toString(wordle.getTestString().charAt(3));
            if (a.equals(positionFour) && wordle.getContainedLettersFour())
                fail();

            a = Character.toString(wordle.getTestString().charAt(4));
            if (a.equals(positionFive) && wordle.getContainedLettersFive())
                fail();
        }
    }

    @Test
    void positionalLetters() {
        ArrayList<Wordle> wordList = initialize();
        assert wordList != null;
        Wordle.positionalLetters(wordList, "t", "r", "e", "a", "t");
        int counter = 0;
        for (Wordle wordle : wordList) {
            if (wordle.getPositionalLetter())
                counter += 1;
        }
        assertEquals(1, counter);
    }

    @Test
    void resetValues() {
        ArrayList<Wordle> wordList = initialize();
        assert wordList != null;
        for (Wordle value : wordList) {
            value.setExcludedLetters(true);
            value.setContainedLetters(true);
            value.setContainedLettersOne(true);
            value.setContainedLettersTwo(true);
            value.setContainedLettersThree(true);
            value.setContainedLettersFour(true);
            value.setContainedLettersFive(true);
            value.setPositionalLettersOne(true);
            value.setPositionalLettersTwo(true);
            value.setPositionalLettersThree(true);
            value.setPositionalLettersFour(true);
            value.setPositionalLettersFive(true);
            value.setPositionalLetters(true);
        }

        Wordle.resetValues(wordList);
        for (Wordle wordle : wordList) {
            if (wordle.getExcludedLetters() ||
                    wordle.getContainedLettersOne() ||
                    wordle.getContainedLettersTwo() ||
                    wordle.getContainedLettersThree() ||
                    wordle.getContainedLettersFour() ||
                    wordle.getContainedLettersFive() ||
                    wordle.getContainedLetters() ||
                    wordle.getPositionalLettersOne() ||
                    wordle.getPositionalLettersTwo() ||
                    wordle.getPositionalLettersThree() ||
                    wordle.getPositionalLettersFour() ||
                    wordle.getPositionalLettersFive() ||
                    wordle.getPositionalLetter()
            )
                fail();
        }

    }
    @Test
    void alphabeticalOrder() {
        ArrayList<Wordle> wordList = initialize();
        assert wordList != null;
        mergeSort(wordList);
        assertEquals("aalii", wordList.get(0).getTestString());
        assertEquals("aargh", wordList.get(1).getTestString());
        assertEquals("aarti", wordList.get(2).getTestString());
        assertEquals("abaca", wordList.get(3).getTestString());
        assertEquals("abaci", wordList.get(4).getTestString());
        assertEquals("aback", wordList.get(5).getTestString());
        assertEquals("abacs", wordList.get(6).getTestString());
        assertEquals("abaft", wordList.get(7).getTestString());
        assertEquals("abaka", wordList.get(8).getTestString());
        assertEquals("abamp", wordList.get(9).getTestString());
    }
}