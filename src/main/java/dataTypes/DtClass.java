package dataTypes;


import java.util.Date;
import java.util.Map;

public class DtClass {

    private final String name;
    private final Date dateAndTime;
    private final Date registerDate;
    private final String url;
    // The String in the map represent the users id.
    private final Map<String, DtEnrollment> enrollments;

    public DtClass(String name, Date dateAndTime, Date registerDate, String url, Map<String, DtEnrollment> enrollments) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.registerDate = registerDate;
        this.url = url;
        this.enrollments = enrollments;
    }

    public String getName() {
        return name;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, DtEnrollment> getEnrollments() {
        return enrollments;
    }
}