package edu.sdccd.cisc191.ciphers;

public class Vigenere {
    public static String encode (String inputText, String key) {
        int[] keyArr = new int[key.length()];
        key = key.toUpperCase();

        //Convert key into int[]
        for(int i=0; i<key.length(); i++){
            keyArr[i] = key.charAt(i) - 'A';
        }

        return transformText(inputText,keyArr);
    }

    public static String decode (String inputText, String key) {
        int[] keyArr = new int[key.length()];
        key = key.toUpperCase();

        //Convert key into negative int[]
        for(int i=0; i<key.length(); i++){
            keyArr[i] = 26-(key.charAt(i) - 'A');
        }

        return transformText(inputText,keyArr);
    }

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
}
