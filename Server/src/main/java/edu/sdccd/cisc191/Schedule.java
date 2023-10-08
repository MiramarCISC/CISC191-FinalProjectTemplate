package edu.sdccd.cisc191;
import java.util.ArrayList;

//use to hold all the objects from the class classes in 1 place
public class Schedule
{
    private ArrayList<Class> classList;
    //we will use timeTable a lot so making is static help avoid using getter method
    public static Class[][] timeTable;

    public Schedule()
    {
        classList = new ArrayList<Class>();
        timeTable = new Class[5][5];
    }

    //methods to add and remove objects from the array list
    public void addClass(int period, Class c)
    {
        classList.add(period -1, c);
    }

    public void removeClass(int period)
    {
        classList.remove(period - 1);
    }

    public ArrayList<Class> getClassList()
    {
        return classList;
    }

    public void makeTimeTable()
    {
        //the row will represent the days of the week
        //the column will be the classes on those days

        Class[][] timeT = new Class[5][5];

        //loop through all the classes and put them in the date slot
        for (int i = 0; i < classList.size(); i++)
        {
            //if the class meet on monday, put it in column 1 and 3
            if (classList.get(i).getClassDate1().equalsIgnoreCase("Monday"))
            {
                //if the index is already taken, move 1 row down
                if (timeT[0][0] == null)
                {
                    timeT[0][0] = classList.get(i);
                    timeT[0][2] = classList.get(i);
                }
                else
                {
                    timeT[1][0] = classList.get(i);
                    timeT[1][2] = classList.get(i);
                }
            }
            //if the class meet on monday, put it in column 2 and 4
            if (classList.get(i).getClassDate1().equalsIgnoreCase("Tuesday"))
            {
                //if the index is already taken, move 1 row down
                if (timeT[0][1] == null)
                {
                    timeT[0][1] = classList.get(i);
                    timeT[0][3] = classList.get(i);
                }
                else
                {
                    timeT[1][1] = classList.get(i);
                    timeT[1][3] = classList.get(i);
                }
            }
        }

        timeTable = timeT;
    }
}