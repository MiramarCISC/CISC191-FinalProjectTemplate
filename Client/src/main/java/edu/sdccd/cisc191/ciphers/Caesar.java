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
            return Caesar.cryptanlysis(cipherText);

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

    private static String cryptanlysis(String input) {
        double chiSquared=0.0, chiLow = Double.MAX_VALUE;
        double[] eFrequency = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.000153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.0236, 0.0015, 0.01974, 0.00074};

        for(int i=0; i<input.length(); i++) {
            int letterAcc=0;
            chiSquared += (Math.pow((letterAcc - (input.length()*eFrequency[i])),2))/(eFrequency[i]*input.length());
        }

        String output = "";

        return output;
    }
}
