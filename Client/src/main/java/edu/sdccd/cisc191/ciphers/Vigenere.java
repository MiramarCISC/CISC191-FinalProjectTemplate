package edu.sdccd.cisc191.ciphers;

import edu.sdccd.cisc191.CipherTools;

import java.util.Arrays;

public class Vigenere extends CipherTools {
    private static final int DEPTH_OF_SEARCH = 20;  //Max Length of keyword to search for

    /**************************************************************************
     * Encrypts plain text using a Vigenere Cipher given a key word
     *************************************************************************/
    public static String encode (String inputText, String key) {
        int[] keyArr = new int[key.length()];
        key = key.toUpperCase();

        //Convert key into int[]
        for(int i=0; i<key.length(); i++){
            keyArr[i] = key.charAt(i) - 'A';
        }

        return transformText(inputText,keyArr);
    }

    /**************************************************************************
     * Decodes cipher text using a Hill Cipher given the encryption key
     *************************************************************************/
    public static String decode (String inputText, String key) {
        if(key.isEmpty())
            return cryptanalyze(inputText);

        int[] keyArr = new int[key.length()];
        key = key.toUpperCase();

        //Convert key into modular negative int[]
        for(int i=0; i<key.length(); i++){
            keyArr[i] = 26-(key.charAt(i) - 'A');
        }

        return transformText(inputText,keyArr);
    }

    /**************************************************************************
     * Cryptanlyzes cipher text and attempts to decrypt without key
     *************************************************************************/
    private static String cryptanalyze (String inputText) {
        String noPunct = inputText.toUpperCase().replaceAll("[^A-Z]", "");
        int keyLength = findKeyLength(noPunct);

        String key = "";
        char[][] charArr = new char[keyLength][noPunct.length()/keyLength + 1];
        int[][] letterFreq = new int[keyLength][26];

        //Create keyLength number of rows of characters
        for(int i=0; i<noPunct.length(); i++)
            charArr[i%keyLength][i/keyLength] = (noPunct.charAt(i));

        for(int row=0; row<charArr.length; row++) {     //Iterate through each row which is essentially a caesar cipher
            letterFreq[row] = getLetterFrequency(Arrays.toString(charArr[row]));

            double[] chiSquared = new double[26];
            int chiLow = 0;

            for(int shift=0; shift<26; shift++) {   //Iterates through each caesar cipher shift
                chiSquared[shift] = chiSquareTestShifted(charArr.length, letterFreq[row], shift);
                if(chiSquared[shift]<chiSquared[chiLow])
                    chiLow = shift;
            }

            key += (char) (chiLow + 'A');           //Creates key word
        }


        return decode(inputText, key);
    }

    /**************************************************************************
     * Transforms the text by looping through the key word
     *************************************************************************/
    private static String transformText(String inputText, int[] keyArr) {
        StringBuilder outputText = new StringBuilder();

        for(int i=0, offset=0; i<inputText.length(); i++){
            char c = inputText.charAt(i);

            if(c>=97 && c<=122){
                c = (char) (((c-'a') + keyArr[(i-offset)%keyArr.length])%26);
                outputText.append((char) (c + 'a'));
            } else if(c>=65 && c<=90){
                c = (char) (((c-'A') + keyArr[(i-offset)%keyArr.length])%26);
                outputText.append((char) (c + 'A'));
            } else {
                outputText.append(c);
                offset++;
            }
        }

        return outputText.toString();
    }

    /**************************************************************************
     * Finds the most likely length of the key word
     *************************************************************************/
    private static int findKeyLength(String inputText) {
        double[] ICArr = new double[DEPTH_OF_SEARCH+1];

        for(int keyLength=1; keyLength<ICArr.length; keyLength++){
            char[][] letterRows = new char[keyLength][inputText.length()/keyLength + 1];
            int[][] letterFrequency = new int[keyLength][26];
            double[] friedmanArr = new double[keyLength];

            //Divides the text into keyLength number of columns
            for(int i=0; i<inputText.length(); i++)
                letterRows[i%keyLength][i/keyLength] = (inputText.charAt(i));

            //Stores letter frequencies of all characters
            for (int row=0; row<keyLength; row++) {
                char[] charRow = letterRows[row];
                letterFrequency[row] = getLetterFrequency(Arrays.toString(charRow));

                friedmanArr[row] = findIndexOfCoincidence(charRow.length, letterFrequency[row]);
            }

            //Finds average of IoC
            for(double i : friedmanArr){
                ICArr[keyLength] += i/keyLength;
            }
        }

        int max=0;      //Finds index with the largest coincidence
        for(int i=0; i< ICArr.length; i++) {
            if(ICArr[i]>ICArr[max]) {
                max=i;
            }
        }

        return max;
    }
}