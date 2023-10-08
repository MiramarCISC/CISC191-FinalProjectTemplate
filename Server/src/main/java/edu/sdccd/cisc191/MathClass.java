package edu.sdccd.cisc191;

//extends from the Class class, used to represent math-subject classes
public class MathClass extends Class implements MathInfo
{
    private String material;
    private String calculator;

    public MathClass(String sub, String cla, String teacher, String location, int roomNum,
                     String first, String last, String date1, String date2, String mat, String calc)
    {
        super(sub, cla, teacher, location, roomNum, first, last, date1, date2);
        material = mat;
        calculator = calc;
    }

    public String getMaterial() {
        return material;
    }

    public String getCalculator() {
        return calculator;
    }

    public String getBook()
    {
        return "";
    }

    public String getLabLocation() {
        return "";
    }

    public String getLabDate() {
        return "";
    }

}
