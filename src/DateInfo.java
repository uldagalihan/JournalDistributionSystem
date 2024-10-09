import java.io.Serializable;

public class DateInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	private int startMonth;
    private int startYear;
    private int endMonth;

    public DateInfo(int startMonth, int startYear, int endMonth) {
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public boolean isSubscriptionActive(int currentMonth) {
        int currentYear = 2023;  

        if (currentYear > startYear && currentYear < startYear + 1) {
            return true;
        } else if (currentYear == startYear) {
            return currentMonth >= startMonth;
        } else if (currentYear == startYear + 1) {
            return currentMonth <= endMonth;
        } else {
            return false;
        }
    }
}
