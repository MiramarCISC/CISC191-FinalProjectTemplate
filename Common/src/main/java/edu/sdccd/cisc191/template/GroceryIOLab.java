package edu.sdccd.cisc191;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
public class GroceryIOLab {
    //Reads the grocery items and list their name, price, and ****Aisle?*** (going to change third variable)
    public static String readTestResults(String file) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new
                FileReader("src/main/resources/" + file))) {
            String line;
            while (
                    (line = br.readLine()) != null) {
                result.append(line.trim()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return result.toString().trim();
    }

    public static void appendTestResult(String file, String addToFile) {
        try (BufferedWriter bw = new BufferedWriter(new
                FileWriter("src/main/resources/" + file, true))) {
            bw.write(addToFile + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}