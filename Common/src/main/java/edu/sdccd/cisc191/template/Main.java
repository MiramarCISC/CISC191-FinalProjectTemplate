package edu.sdccd.cisc191.template;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.Random;


public class Main {
    public static void readFileData(String filePath) throws Exception //filepath is the .txt that the user will provide
    {
        //connects to the database named cps with the root username and password
        String url = "jdbc:mysql://localhost:3306/cps";
        String username = "Dev";
        String password = "Carly2019!@";
        Connection conn = DriverManager.getConnection(url, username, password);
        //creates a new 2d arraylist with string generics
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        //reads a txt file and puts data from the text file into the 2d arraylist
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp;
        while ((temp = br.readLine()) != null){
            int i = temp.indexOf(",");
            String userName = temp.substring(0, i);
            String passWord = temp.substring(i + 1, temp.length() - 1);
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add(userName);
            tempList.add(passWord);
            list.add(tempList);
        }
        //sorts the credentials alphabetically by username
        sortCredentials(list);

        //prints the list of users in alphabetical order, not needed for the database
        for(int i = 0; i < list.size() - 1; i++){
            String str = i + 1 + " ";
            for(int j = 0; j < list.get(i).size(); j++){
                str = str + list.get(i).get(j) + "-";
            }
            str = str.substring(0, str.length() - 1);
            System.out.println(str);
            System.out.println("---------------------");
        }
        //checks if the users passwords are less than 6 digits, if it is, generates new random password with 6 digits
        validatePassword(list);

        //adds all the data from the 2d arraylist into the table of users in the database
        for(int i = 0; i < list.size(); i++){
                try {
                    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, list.get(i).get(0));
                    stmt.setString(2, list.get(i).get(1));
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Unable to insert rows into table");
                    e.printStackTrace();
                    break;
            }
        }
    }

    //creates a ConcurrentSort2D object called sort and then sorts the list.
    private static void sortCredentials(ArrayList list) throws Exception {
        ConcurrentSort2D sort = new ConcurrentSort2D();
        sort.sort2D(list);
        }

    //streams through the arraylist and uses lambda function to check the length of the passwords, if the length
    //of passwords is less than 6, generates a new random password with a length of 6 in cps
    public static void validatePassword(ArrayList<ArrayList<String>> list) {
        //random and alpha are used to generate new passwords
        Random random = new Random();
        String alpha = "abcdefghijklmnopqrstuvwxyz1234567889";

        //streams through the password column, and uses lambda function to check if the password is less than 6 digits
        list.stream().forEach(column -> {
            Function<String, Integer> getLength = n -> n.length();
            if(checkLength(column.get(1), getLength)) {
                String temp = "";
                for(int i = 0; i < 6; i++){
                    temp = temp + alpha.charAt(random.nextInt(36));
                }
                column.remove(1);
                column.add(temp);
            }
        });
    }


    //checks if password length is less than 6 and returns a boolean
    public static boolean checkLength(String str, Function<String, Integer> getLength){
        if(getLength.apply(str) < 6) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        // Connect to the database
        String url = "jdbc:mysql://localhost:3306/cps";
        String username = "Dev";
        String password = "Carly2019!@";
        Connection conn = DriverManager.getConnection(url, username, password);

                //Checks if the "users" table exists if it doesn't exist, create a new users table
        //If it does exist, clean the table everytime so that the list gets updated
        try {
            String sql = "SHOW TABLES LIKE 'users'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String refresher = "TRUNCATE TABLE users";
                stmt.execute(refresher); //creates a clean table
                //creates the table
                sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))";
                //populates the table from the .txt file
                readFileData("C:\\Users\\Devan\\IdeaProjects\\CISC191-FinalProjectTemplate\\Common\\src\\main\\java\\edu\\sdccd\\cisc191\\template\\users.txt");
            } else {
                sql = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50))";
                stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        // Close the connection
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Unable to close connection");
            e.printStackTrace();
        }
    }
}

//to implement recursion and concurrency, we created a concurrent merge sort. We limited the number of threads to 4
class ConcurrentSort2D {
    private static final int NUM_THREADS = 4;  // number of threads to use
    private ArrayList<ArrayList<String>> list;

    public void sort2D(ArrayList<ArrayList<String>> list) throws Exception {
        this.list = list;
        // Base case: if the list is empty or has only one element, it is already sorted
        if (list.size() <= 1) {
            return;
        }
        // Divide the list into two halves
        int mid = list.size() / 2;
        ArrayList<ArrayList<String>> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<ArrayList<String>> right = new ArrayList<>(list.subList(mid, list.size()));
        // Recursively sort the two halves in separate threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Callable<Void> leftSort = () -> {
            sort2D(left);
            return null;
        };
        Callable<Void> rightSort = () -> {
            sort2D(right);
            return null;
        };
        Future<Void> leftResult = executor.submit(leftSort);
        Future<Void> rightResult = executor.submit(rightSort);
        leftResult.get();
        rightResult.get();
        executor.shutdown();
        // Merge the sorted halves back together
        merge2D(list, left, right);
    }

    //merges the two arrays passed in, then the list is sorted.
    private static void merge2D(ArrayList<ArrayList<String>> result, ArrayList<ArrayList<String>> left, ArrayList<ArrayList<String>> right) {
        // Indexes for the result, left, and right lists
        int i = 0, j = 0, k = 0;
        // Compare the elements at the current indexes of the left and right lists, and add the smaller one to the result
        while (i < left.size() && j < right.size()) {
            if (left.get(i).get(0).compareTo(right.get(j).get(0)) < 0) {
                result.set(k, left.get(i));
                i++;
            } else {
                result.set(k, right.get(j));
                j++;
            }
            k++;
        }
        // Add any remaining elements from the left list
        while (i < left.size()) {
            result.set(k, left.get(i));
            i++;
            k++;
        }
        // Add any remaining elements from the right list
        while (j < right.size()) {
            result.set(k, right.get(j));
            j++;
            k++;
        }
    }
}