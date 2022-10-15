package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {

public class LinkedList <T>
{
    Node<T> head;
    Node<T> tail;
    int size = 0;
    class Node<T> {
        T element;
        Node<T> next;

        public Node(T o) {
            element = o;
        }
 }



    public LinkedList() {
        Node<T> head = null;
        Node<T> tail = null;
    }

    public int size()
    {
        return size;
    }

    public void addFirst(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null)
            tail = head;
    }

    public void addLast(T e) {
        if (tail == null) {
            head = tail = new Node<>(e);
        }
        else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }

    public void add(int index, T e) {
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<T> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<T> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    public String traverse()
    {
        String s ="";
        Node current;
        current = head;
        while ( current != null ) {
            s += (current.element +"\n");
            current = current.next;
        }
        return s;
    }
}

public class RegularCustomer extends Customer implements CustomerType // 5.0 Inheritance, Polymorphism, Abstract Classes
{
    static ArrayList <RegularCustomer> customers = new ArrayList<>();
    static int customerCount = 0;
    int [][] stocks = new int[3][1000]; // 2.0


    RegularCustomer(String name, String age, String occupation) //4.0 Object Oriented Programming
    {
        this.name = name;
        this.age = age;
        this.occupation = occupation;

        customerCount++;
    }

    static public RegularCustomer getCustomer(int i)
    {
        return customers.get(i);
    }

    static public int getCustomerCount()
    {
        return customerCount;
    }

    public static void addCustomer(RegularCustomer p)
    {
        customers.add(p);
    }


    public String getName(){
        return this.name;
    }

    @Override // 2.0 Multidimensional Arrays and Best Practices
    public String toString()
    {
        return  "\nname:" + name +
                "\nage:" + age +
                "\noccupation:" + occupation + "\n";
    }
}

public class Stock
{
    String symbol;
    int price;

    public Stock(){}

    public Stock(String symbol, int boughtPrice)
    {
        this.symbol = symbol;
        this.price = boughtPrice;
    }
    public String getSymbol()
    {
        return this.symbol;
    }

    public int getPrice() {
        return price;
    }
}

public interface CustomerType
{
    String getName();
}

public class Customer
{
    String name;
    String age;
    String occupation;
}

} //end class Client

