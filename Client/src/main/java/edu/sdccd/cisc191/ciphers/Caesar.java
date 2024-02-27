package edu.sdccd.cisc191.ciphers;

public class Caesar {
    private static final double[] LETTER_FREQ = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.000153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.0236, 0.0015, 0.01974, 0.00074};

    public static String encode(String inputText, String key) {
        return transformText(inputText, Integer.parseInt(key));
    }

    public static String decode(String inputText, String key) {
        if(key.isEmpty())
            return Caesar.cryptanlysis(inputText);

        int decryptionKey = 26-(Integer.parseInt(key)%26);

        return transformText(inputText, decryptionKey);
    }

    private static String transformText(String inputText, int key) {
        StringBuilder outputText = new StringBuilder();

        for(int i=0; i<inputText.length(); i++){
            char c = inputText.charAt(i);

            if(c>=97 && c<=122){
                c = (char) (((c-'a') + key)%26);
                outputText.append((char) (c + 'a'));
            } else if(c>=65 && c<=90){
                c = (char) (((c-'A') + key)%26);
                outputText.append((char) (c + 'A'));
            } else {
                outputText.append(c);
            }
        }

        return outputText.toString();
    }

    private static String cryptanlysis(String inputText) {
        String noPunct = inputText.toUpperCase().replaceAll("[^A-Z]", "");
        int[] letterFreq = new int[26];

        for(int c=0; c<noPunct.length(); c++) {  //Gets letter frequency of each letter in cipher text
            if(noPunct.charAt(c)>=65)
                letterFreq[noPunct.charAt(c) - 'A']++;
        }

        double[] chiSquared = new double[26];
        int chiLow = 0;
        for(int shift=0; shift<26; shift++) {   //Iterates through each caesar cipher shift
            for(int l=0; l<26; l++)             //Chi Square test to compare expected frequencies to real
                chiSquared[shift] += (Math.pow((letterFreq[(l+shift)%26] - (noPunct.length()*LETTER_FREQ[l])),2))/(LETTER_FREQ[l]*noPunct.length());
            if(chiSquared[shift]<chiSquared[chiLow])
                chiLow = shift;
        }

        return decode(inputText, String.valueOf(chiLow));
    }
}
