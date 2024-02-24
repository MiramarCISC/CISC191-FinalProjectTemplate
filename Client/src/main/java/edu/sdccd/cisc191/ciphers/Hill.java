package edu.sdccd.cisc191.ciphers;

import java.io.IOException;

public class Hill {
    private static final int[] MULT_INVERSE= {1,0,9,0,21,0,15,0,3,0,19,0,0,0,7,0,23,0,11,0,5,0,17,0,25};

    /**************************************************************************
     * Encrypts plain text using a Hill Cipher given a key word
     *************************************************************************/
    public static String encode(String inputText, String key) {
        //Removes all non alphabet characters and spaces
        String plainText = inputText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder cipherText = new StringBuilder();
        int[][] keyMatrix = createKeyMatrix(key);
        int matrixLength = keyMatrix.length;

        //Append Z's to allow key matrix to transform entire plain text
        if(plainText.length()%matrixLength != 0)
            for(int i=0; i<plainText.length()%matrixLength; i++) {
                plainText = plainText.concat("Z");
            }

        //Transforms plain text matrix.length letters at a time
        for(int i=0; i<=plainText.length()-matrixLength; i+=matrixLength) {
            //Arranges letters as a vector
            int[] letterVector = new int[matrixLength];
            for(int c=0; c<letterVector.length; c++){
                letterVector[c%matrixLength] = plainText.charAt(c+i) - 'A';
            }

            letterVector = matrixMultiply(keyMatrix, letterVector);

            //Appends output with transformed vector
            for(int c : letterVector) {
                cipherText.append((char)(c + 'A'));
            }
        }

        return cipherText.toString();
    }

    /**************************************************************************
     * Decodes cipher text using a Hill Cipher given the encryption key
     *************************************************************************/
    public static String decode(String cipherText, String key){
        //Removes all spaces and non-alphabetic characters
        cipherText = cipherText.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder plainText = new StringBuilder();

        //Creates decryption matrix by inverting the key matrix
        int[][] decryptionMatrix = findInverse(createKeyMatrix(key));
        int matrixLength = decryptionMatrix.length;

        //Transforms plain text matrix.length letters at a time
        for(int i=0; i<=cipherText.length()-matrixLength; i+=matrixLength) {
            //Arranges letters into a vector
            int[] letterVector = new int[matrixLength];
            for(int c=0; c<letterVector.length; c++){
                letterVector[c%matrixLength] = cipherText.charAt(c+i) - 'A';
            }

            letterVector = matrixMultiply(decryptionMatrix, letterVector);

            //Appends output with transformed vector
            for(int c : letterVector) {
                plainText.append((char)(c + 'A'));
            }
        }

        return plainText.toString();
    }

    /**************************************************************************
     * Creates square matrix, looping through the word if the word length
     * isn't a perfect square
     *************************************************************************/
    private static int[][] createKeyMatrix(String key) {
        //Find dimensions required to contain entire key
        int n = (int) (Math.sqrt(key.length())+0.99);
        int[][] keyMatrix = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                keyMatrix[i][j] = key.charAt((i*n+j)%(key.length())) - 'A';
            }
        }

        return keyMatrix;
    }

    /**************************************************************************
     * Multiplies matrix by vector
     *************************************************************************/
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

    /**************************************************************************
     * Finds determinant using Laplace Expansion by calling itself recursively
     *************************************************************************/
    private static int findDeterminant(int[][] matrix){
        int det = 0, sign = 1;

        //Find 2x2 determinant
        if(matrix.length == 2){
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }

        //Find nxn determinant where n>2
        for (int i = 0; i < matrix[0].length; i++) {
            det += matrix[0][i] * findDeterminant(getSubMatrix(matrix, 0, i)) * sign;
            sign *= -1;
        }

        return det;
    }

    /**************************************************************************
     * Returns minor matrix for laplace expansion
     *************************************************************************/
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

    /**************************************************************************
     * Finds inverse matrix using Gauss-Jordan elimination
     *************************************************************************/
    public static int[][] findInverse(int[][] inputMatrix) {
        int det = findDeterminant(inputMatrix);

        //Throws exception if matrix is non-invertible mod26
        try {
            if (det % 2 == 0 || det == 13)
                throw new IOException("The key is non-invertible");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        double[][] matrix = augmentIdentityMatrix(inputMatrix);
        int[][] inverse = new int[inputMatrix.length][inputMatrix[0].length];
        int inverseDet = MULT_INVERSE[((det%26)+26)%26 - 1];

        //Gauss-Jordan Elimination
        for(int i=0; i<matrix.length; i++) {
            matrix[i] = scaleRow(matrix[i], 1/matrix[i][i]);    //Scales pivot to equal 1
            for(int j=0; j<matrix.length; j++) {    //Performs row reduction
                if(j!=i)
                    matrix[j] = addRow(matrix[j], scaleRow(matrix[i], -1*matrix[j][i]));
            }
        }

        //Set output matrix by iterating through augmented matrix
        for(int i=0; i<matrix.length; i++) {
            for(int j=matrix.length; j<matrix[0].length; j++) {
                //Turns output into its mod26 inverse
                inverse[i][j-matrix.length] = (int) (Math.round(((matrix[i][j] * det * inverseDet)%26))+26)%26;
            }
        }
        return inverse;
    }

    /**************************************************************************
     * Sets 1st row equal to the sum of both rows
     *************************************************************************/
    public static double[] addRow(double[] outputVector, double[] b) {
        for(int i=0; i<outputVector.length; i++) {
            outputVector[i] += b[i];
        }
        return outputVector;
    }

    /**************************************************************************
     * Scales row by given scalar
     *************************************************************************/
    public static double[] scaleRow(double[] row, double scalar) {
        double[] output = new double[row.length];
        for(int i=0; i<row.length; i++) {
            output[i]= row[i] * scalar;
        }
        return output;
    }

    /**************************************************************************
     * Augments identity matrix to the key matrix
     *************************************************************************/
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
