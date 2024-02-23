package edu.sdccd.cisc191.ciphers;

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
                keyMatrix[i][j] = key.charAt((i*n+j)%(key.length())) - 'A';
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

    public static int[][] findInverse(int[][] inputMatrix) {
        int det = findDeterminant(inputMatrix);
        try {
            if (det % 2 == 0 || det == 13)
                throw new IOException("The key is non-invertible");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        double[][] matrix = augmentIdentityMatrix(inputMatrix);
        int[][] inverse = new int[inputMatrix.length][inputMatrix[0].length];
        int inverseDet = MULT_INVERSE[((det%26)+26)%26 - 1];

        for(int i=0; i<matrix.length; i++) {
            matrix[i] = scaleRow(matrix[i], 1/matrix[i][i]);
            for(int j=0; j<matrix.length; j++) {
                if(j!=i)
                    matrix[j] = addRow(matrix[j], scaleRow(matrix[i], -1*matrix[j][i]));
            }
        }

        for(int i=0; i<matrix.length; i++) {
            for(int j=matrix.length; j<matrix[0].length; j++) {
                inverse[i][j-matrix.length] = (int) (Math.round(((matrix[i][j] * det * inverseDet)%26))+26)%26;
            }
        }
        return inverse;
    }

    public static double[] addRow(double[] outputVector, double[] b) {
        for(int i=0; i<outputVector.length; i++) {
            outputVector[i] += b[i];
        }
        return outputVector;
    }

    public static double[] scaleRow(double[] row, double scalar) {
        double[] output = new double[row.length];
        for(int i=0; i<row.length; i++) {
            output[i]= row[i] * scalar;
        }
        return output;
    }

    public static double[][] augmentIdentityMatrix(int[][] matrix) {
        double[][] ret = new double[matrix.length][matrix[0].length*2];
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++) {
                ret[i][j] = matrix[i][j];
            }
            ret[i][i + matrix.length] = 1;
        }
        return ret;
    }
}
