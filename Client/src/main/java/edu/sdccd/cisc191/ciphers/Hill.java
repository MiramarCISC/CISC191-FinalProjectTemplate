package edu.sdccd.cisc191.ciphers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

public class Hill {
    private static final int[] MULT_INVERSE= {1,0,9,0,21,0,15,0,3,0,19,0,0,0,7,0,23,0,11,0,5,0,17,0,25};

    public static String encode(String plainText, String key) {
        plainText = plainText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder cipherText = new StringBuilder();
        int[][] keyMatrix = createKeyMatrix(key);
        int matrixLength = keyMatrix.length;

        if(plainText.length()%matrixLength != 0)
            for(int i=0; i<plainText.length()%matrixLength; i++) {
                plainText = plainText.concat("Z");
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
    public static String decode(String cipherText, String key){
        cipherText = cipherText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder plainText = new StringBuilder();
        int[][] decryptionMatrix = findInverse(createKeyMatrix(key));
        int matrixLength = decryptionMatrix.length;

        for(int i=0; i<=cipherText.length()-matrixLength; i+=matrixLength) {
            int[] letterVector = new int[matrixLength];
            for(int c=0; c<letterVector.length; c++){
                letterVector[c%matrixLength] = cipherText.charAt(c+i) - 'A';
            }

            letterVector = matrixMultiply(decryptionMatrix, letterVector);

            for(int c : letterVector) {
                plainText.append((char)(c + 'A'));
            }
        }

        return plainText.toString();
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

    private static int findDeterminant(int[][] matrix){
        int det = 0, sign = 1;

        if(matrix.length > 2) {
            for (int i = 0; i < matrix[0].length; i++) {
                det += matrix[0][i] * findDeterminant(getSubMatrix(matrix, 0, i)) * sign;
                sign *= -1;
            }
        } else{
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }

        return det;
    }

    public static int[][] getSubMatrix(int[][] matrix, int i, int j) {
        int[][] subMatrix = new int[matrix.length - 1][matrix[0].length - 1];
        for(int row=0,outRow=0; row<matrix.length; row++) {
            for (int col=0,outCol=0; col<matrix.length; col++) {
                if(col!=j && row!=i) {
                    subMatrix[outRow][outCol] = matrix[row][col];
                    outCol++;
                }
            }
            if (row != i) {
                outRow++;
            }
        }
        return subMatrix;
    }

    public static int[][] findInverse(int[][] matrix) {
        int det = Math.abs(findDeterminant(matrix))%26;
        try {
            if (det % 2 == 0 || det == 13)
                throw new IOException("The key is non-invertible");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int[][] inverse = new int[matrix.length][matrix[0].length];
        int inverseDet = MULT_INVERSE[det-1];

        if (matrix.length>2) {
            for (int i = 0, iteration = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    inverse[i][j] = (int) (Math.pow(-1, iteration) * findDeterminant(getSubMatrix(matrix, j, i))%26);
                    iteration++;
                    if(inverse[i][j] < 0) {
                        inverse[i][j] %= 26;
                        inverse[i][j] += 26;
                    }
                    if(matrix.length%2 == 0)
                        iteration++;
                }
            }
        } else {
            inverse[0][0] = (matrix[1][1] * inverseDet)%26;
            inverse[1][1] = (matrix[0][0] * inverseDet)%26;
            inverse[0][1] = 26 - (matrix[0][1] * inverseDet)%26;
            inverse[1][0] = 26 - (matrix[1][0] * inverseDet)%26;
        }

        return inverse;
    }

}
