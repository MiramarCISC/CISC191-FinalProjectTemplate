package edu.sdccd.cisc191.ciphers;

import edu.sdccd.cisc191.AlertBox;
import edu.sdccd.cisc191.CipherTools;

import javax.crypto.Cipher;
import java.util.Arrays;

public class Hill extends CipherTools {
    private static final int[] MULT_INVERSE= {1,0,9,0,21,0,15,0,3,0,19,0,0,0,7,0,23,0,11,0,5,0,17,0,25};
    private static String INPUT_TEXT,ALPHA_INPUT_TEXT;

    /**************************************************************************
     * Encrypts plain text using a Hill Cipher given a key word
     *************************************************************************/
    public static String encode(String inputText, String key) {
        //Removes all non alphabet characters and spaces
        INPUT_TEXT = inputText;
        ALPHA_INPUT_TEXT = INPUT_TEXT.toUpperCase().replaceAll("[^A-Z]", "");
        int[][] keyMatrix = createKeyMatrix(key.toUpperCase().replaceAll("[^A-Z]", ""));
        int matrixLength = keyMatrix.length;
        double det=1;

        //Copies key matrix as a double
        double[][] upperTriangular = new double[matrixLength][matrixLength];
        for(int i=0; i<matrixLength; i++){
            upperTriangular[i] = Arrays.stream(keyMatrix[i]).asDoubleStream().toArray();
        }

        //Finds determinant by creating upper triangular matrix
        gaussianElimination(upperTriangular);
        for(int i=0; i<matrixLength; i++){
            det *= upperTriangular[i][i];
        }

        det = Math.round(det);
        if (det % 2 == 0 || det == 13) {
            AlertBox.display("Error", "ERROR!\nYour keyword is non-invertible using a 26 letter alphabet (it won't be able to be decrypted)");
            throw new ArithmeticException("The key forms a non-invertible matrix");
        }

        //Append Z's to allow key matrix to transform entire plain text
        if(ALPHA_INPUT_TEXT.length()%matrixLength != 0)
            for(int i=0; i<ALPHA_INPUT_TEXT.length()%matrixLength; i++) {
                ALPHA_INPUT_TEXT = ALPHA_INPUT_TEXT.concat("Z");
                INPUT_TEXT += 'Z';
            }

        return transformText(keyMatrix);
    }

    /**************************************************************************
     * Decodes cipher text using a Hill Cipher given the encryption key
     *************************************************************************/
    public static String decode(String inputText, String key){
        //Removes all spaces and non-alphabetic characters
        INPUT_TEXT = inputText;
        ALPHA_INPUT_TEXT = INPUT_TEXT.toUpperCase().replaceAll("[^A-Z]", "");

        //Creates decryption matrix by inverting the key matrix
        return transformText(findInverse(createKeyMatrix(key.toUpperCase().replaceAll("[^A-Z]", ""))));
    }

    /**************************************************************************
     * Transforms text using encryption/decryption matrix
     *************************************************************************/
    private static String transformText(int[][] keyMatrix) {
        StringBuilder outputText = new StringBuilder();
        int matrixLength = keyMatrix.length;

        for(int i=0, offset=0; i<=ALPHA_INPUT_TEXT.length()-matrixLength; i+=matrixLength) {
            //Arranges letters as a vector
            int[] letterVector = new int[matrixLength];
            for(int c=0; c<letterVector.length; c++){
                letterVector[c%matrixLength] = ALPHA_INPUT_TEXT.charAt(c+i) - 'A';
            }

            letterVector = matrixMultiply(keyMatrix, letterVector);

            //Appends output with transformed vector
            for(int j=0; j<letterVector.length; j++) {
                char c = INPUT_TEXT.charAt((i+j+offset));

                if(c>=97 && c<=122){        //Appends letter as lowercase if lowercase in input text
                    outputText.append((char)(letterVector[j] + 'a'));
                } else if(c<65 || c>90){    //Appends punctuation then decrements j to reiterate index
                    outputText.append(c);
                    offset++;
                    j--;
                } else {                    //Appends letter without any alteration after encryption/decryption
                    outputText.append((char)(letterVector[j] + 'A'));
                }
            }
        }
        return outputText.toString();
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
     * Performs Gaussian Elimination to transform matrix into row echelon form
     *************************************************************************/
    private static void gaussianElimination(double[][] matrix) {
        for(int i=0; i<matrix.length; i++) {
            int row=i+1;
            while(matrix[i][i] == 0) {
                if(row>=matrix.length) {  //Whole column is 0
                    AlertBox.display("Error", "ERROR!\nYour keyword is non-invertible using a 26 letter alphabet (it won't be able to be decrypted)");
                    throw new ArithmeticException("The matrix is singular");
                }
                if(matrix[row][i]!=0)    //Swap row to make non-zero pivot
                    swapRow(matrix, i, row);
                row++;
            }

            for(int j=i+1; j<matrix.length; j++) {
                matrix[j] = addRow(matrix[j], scaleRow(matrix[i], -1*(matrix[j][i]/matrix[i][i])));
            }
        }
    }

    /**************************************************************************
     * Finds inverse matrix using Gauss-Jordan elimination
     *************************************************************************/
    private static int[][] findInverse(int[][] inputMatrix) {
        double det = 1;

        double[][] matrix = augmentIdentityMatrix(inputMatrix);
        gaussianElimination(matrix);

        for(int i=0; i<matrix.length; i++) {
            det *= matrix[i][i];
        }

        if (det % 2 == 0 || det == 13) {
            AlertBox.display("Error", "ERROR!\nYour keyword is non-invertible using a 26 letter alphabet (it won't be able to be decrypted)");
            throw new ArithmeticException("The key forms a non-invertible matrix");
        }

        int[][] inverse = new int[inputMatrix.length][inputMatrix[0].length];
        int inverseDet = MULT_INVERSE[(((int) Math.round(det)%26)+26)%26 - 1];

        //Gauss-Jordan Elimination
        for(int i=0; i<matrix.length; i++) {
            matrix[i] = scaleRow(matrix[i], 1/matrix[i][i]);    //Scales pivot to equal 1
            for(int j=i-1; j>=0; j--) {    //Performs row reduction of elements above pivot
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
    private static double[] addRow(double[] outputVector, double[] b) {
        for(int i=0; i<outputVector.length; i++) {
            outputVector[i] += b[i];
        }
        return outputVector;
    }

    /**************************************************************************
     * Scales row by given scalar
     *************************************************************************/
    private static double[] scaleRow(double[] row, double scalar) {
        double[] output = new double[row.length];
        for(int i=0; i<row.length; i++) {
            output[i]= row[i] * scalar;
        }
        return output;
    }

    /**************************************************************************
     * Swaps rows of a matrix
     *************************************************************************/
    private static void swapRow(double[][] matrix, int row1, int row2){
        for(int i=0; i<matrix[row1].length; i++){
            double temp = matrix[row1][i];
            matrix[row1][i] = matrix[row2][i];
            matrix[row2][i] = temp;
        }
    }

    /**************************************************************************
     * Augments identity matrix to the key matrix
     *************************************************************************/
    private static double[][] augmentIdentityMatrix(int[][] matrix) {
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