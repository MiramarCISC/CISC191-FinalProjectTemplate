package edu.sdccd.cisc191.template;
import java.io.*; // 1.0
import java.util.Scanner;

public class Server {
    public static void main(String[] args)
    {




        Stock one = new Stock("one",19);
        Stock two = new Stock("two", 200);
        Stock three = new Stock("three",30);
        Stock[] list = new Stock[3];
        list[0] = one;
        list[1] = two;
        list[2] = three;
        LinkedList <String> names = new LinkedList<>();
        Scanner s = new Scanner(System.in);
        String name;
        String age;
        String occupation;
        boolean firstTime = true;
        //7.0 I/O Streams
        try {
            File CustomerInfo = new File("CustomerInfo.txt");
            if (CustomerInfo.createNewFile()) {
                System.out.println("Welcome");
            } else {
                firstTime = false;
                System.out.println("Welcome Back");
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        String moreToAdd = "y";
        while((moreToAdd.equals("y")))
        {
            System.out.println("Enter Name of Customer");
            name = s.nextLine();
            System.out.println("Enter age of Customer");
            age = s.nextLine();
            System.out.println("Enter occupation of Customer");
            occupation= s.nextLine();
            RegularCustomer p = new RegularCustomer(name,age,occupation);
            RegularCustomer.addCustomer(p);
            names.add(names.size(),p.getName());

            System.out.println("more customer?");
            moreToAdd = s.nextLine();
        }
        int i = 0;
        while(i < RegularCustomer.getCustomerCount())
        {
            System.out.println("Buy Stocks for " + RegularCustomer.customers.get(i).name + "?");
            moreToAdd = s.nextLine();
            if(moreToAdd.equals("y"))
            {
                int j = 0;
                int num;
                while(j < list.length)
                {
                    System.out.print("How many of " + list[j].getSymbol());
                    num = s.nextInt();
                    RegularCustomer.customers.get(i).stocks[j][0] = num;
                    j++;
                }
            }
            moreToAdd = s.nextLine();
            i++;
        }

        System.out.println("\n\n\n\nEnd of Day Report\n\n");

        for(i = 0; i < RegularCustomer.getCustomerCount(); i++)
        {
            System.out.println(RegularCustomer.customers.get(i).getName() + " owns\n");
            System.out.println(RegularCustomer.customers.get(i).stocks[0][0] + " of " + list[0].getSymbol());
            System.out.println(RegularCustomer.customers.get(i).stocks[1][0] + " of " + list[1].getSymbol());
            System.out.println(RegularCustomer.customers.get(i).stocks[2][0]+ " of " + list[2].getSymbol() +"\n\n\n");
        }




        try{
            FileWriter Writer = new FileWriter("CustomerInfo.txt");
            Writer.write("StockAPP Customer Names:\n\n\n");
            Writer.append(names.traverse());
            Writer.close();
        } catch(Exception e) {}

    }




} //end class Server
