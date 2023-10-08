package edu.sdccd.cisc191;

//extends from the Class class, used to represent english-subject classes
public class EnglishClass extends Class implements EnglishInfo
{
    private String book;

    public EnglishClass(String sub, String cla, String teacher, String location, int roomNum,
                        String first, String last, String date1, String date2, String b)
    {
        super(sub, cla, teacher, location, roomNum, first, last, date1, date2);
        book = b;
    }

    public String getBook() {
        return book;
    }

    public String getLabLocation() {
        return "";
    }

    public String getLabDate() {
        return "";
    }

    public String getMaterial() {
        return "";
    }

    public String getCalculator() {
        return "";
    }

}
