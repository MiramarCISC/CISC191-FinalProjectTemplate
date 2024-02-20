package edu.sdccd.cisc191.ciphers;

public class Caesar {
    public static String encode(String plainText, String key) {
        plainText = plainText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder cipherText = new StringBuilder();
        int intKey = Integer.parseInt(key) % 26;

        for(int i=0; i<plainText.length(); i++) {
            cipherText.append((char) (((plainText.charAt(i) - 'A' + intKey)%26) + 'A'));
        }

        return cipherText.toString();
    }

    public static String decode(String cipherText, String key) {
        if(key==null)
            return Caesar.cryptanlysis();

        cipherText = cipherText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder plainText = new StringBuilder();
        int intKey = Integer.parseInt(key) % 26;

        for(int i=0; i<cipherText.length(); i++) {
            char temp = (char) (((cipherText.charAt(i) - 'A' - intKey)%26) + 'A');
            if(temp < 'A') {
                temp += 26;
            }
            plainText.append(temp);
        }

        return plainText.toString();
    }

    private static String cryptanlysis() {
        String output = "";
        return output;
    }
}
