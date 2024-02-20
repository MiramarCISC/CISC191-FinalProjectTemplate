package edu.sdccd.cisc191.ciphers;

public class Hill {
    public static String encode(String plainText, String key) {
        plainText = plainText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder cipherText = new StringBuilder();
        int[][] keyMatrix = createKeyMatrix(key);
        int matrixLength = keyMatrix.length;

        if(plainText.length()%matrixLength != 0)
            for(int i=0; i<plainText.length()%matrixLength; i++) {
                plainText.concat("A");
            }

        for(int i=0; i<=plainText.length()-matrixLength; i+=matrixLength) {
            int[] letterVector = new int[matrixLength];
            for(int c=0; c<letterVector.length; c++){
                letterVector[c%matrixLength] = plainText.charAt(c+i) - 'A';
            }

            letterVector = matrixMultiply(keyMatrix, letterVector);

            for(int c : letterVector) {
                cipherText.append((char)(c + 'A'));
            }
        }

        return cipherText.toString();
    }
    public static String decode(String plainText, String key) {
        return plainText + " " + key;
    }

    private static int[][] createKeyMatrix(String key) {
        int n = (int) (Math.sqrt(key.length())+0.99);
        int[][] keyMatrix = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                keyMatrix[i][j] = key.charAt(i*n+j) - 'A';
            }
        }

        return keyMatrix;
    }

    private static int[] matrixMultiply(int[][] matrix, int[] vector) {
        int n = matrix.length;
        int[] output = new int[n];

        for(int i=0; i<n; i++) {
            int sum=0;
            for(int j=0; j<n; j++) {
                sum += matrix[i][j]*vector[j];
            }
            output[i] = sum%26;
        }

        return output;
    }
}
