import java.util.Scanner;
public class Assignment {

    private String nameOfAssignment;
    private int daysUntilDueDate;
    private double pointsOfAssignment;
    //private int estInMinutes;
    private boolean busyWork;

    public String getNameOfAssignment() {
        return nameOfAssignment;
    }

    public void setNameOfAssignment(String nameOfAssignment) {
        this.nameOfAssignment = nameOfAssignment;
    }

    public double getPointsOfAssignment() {
        return pointsOfAssignment;
    }
    public void setPointsOfAssignment(double pointsOfAssignment) {
        this.pointsOfAssignment = pointsOfAssignment;
    }

//    public int getEstInMinutes() {
//        return estInMinutes;
//    }

//    public void setEstInMinutes(int estInMinutes) {
//        this.estInMinutes = estInMinutes;
//    }

    public boolean isBusyWork() {
        return busyWork;
    }

    public void setBusyWork(boolean busyWork) {
        this.busyWork = busyWork;
    }

    public int getDaysUntilDueDate() {
        return daysUntilDueDate;
    }

    public void setDaysUntilDueDate(int daysUntilDueDate) {
        this.daysUntilDueDate = daysUntilDueDate;
    }
}
