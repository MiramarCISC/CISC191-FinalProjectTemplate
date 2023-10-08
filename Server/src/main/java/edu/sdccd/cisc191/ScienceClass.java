package edu.sdccd.cisc191;

//extends from the Class class, used to represent science-subject classes

public class ScienceClass extends Class implements ScienceInfo
{
    private String labLocation;
    private String labDate;

    public ScienceClass(String sub, String cla, String teacher, String location, int roomNum,
                        String first, String last, String date1, String date2,
                        String labLoc, String labDat)
    {
        super(sub, cla, teacher, location, roomNum, first, last, date1, date2);
        labLocation = labLoc;
        labDate = labDat;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public String getLabDate() {
        return labDate;
    }

    public String getBook()
    {
        return "";
    }

    public String getMaterial() {
        return "";
    }

    public String getCalculator() {
        return "";
    }

}
