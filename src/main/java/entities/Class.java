package entities;

import dataTypes.DtClass;
import dataTypes.DtEnrollment;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class Class {

    @Id
    @Column(name = "name")
    private String name;
    // I put date and startTime together because Timestamp already contains date and time
    @Column(name = "dateAndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTime;
    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Column(name = "url")
	private String url;
    @Column(name = "imgName")
	private String imgName;
    @OneToMany(mappedBy = "aClass",cascade = CascadeType.ALL)
    // The String in the map represent the users' id.
    private Map<String, Enrollment> enrollments;

    public Class() {}

    public Class(String name, Date dateAndTime, Date registerDate, String url, String imgName) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.registerDate = registerDate;
        this.url = url;
        this.enrollments = new TreeMap<String, Enrollment>();
        this.imgName = imgName;
    }

    public Map<String, Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Map<String, Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getUrl() {
        return url;
    }
    
    

    public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setUrl(String url) {
        this.url = url;
    }

    public void createEnrollment(User user) {}

    public DtClass getData(){
        Map<String, DtEnrollment> dtE = new TreeMap<>();
        int enrollmentQty = 0;
        if (this.enrollments != null) {
        	for(Map.Entry<String, Enrollment> enrollment : this.enrollments.entrySet()){
        		dtE.put(enrollment.getKey(), enrollment.getValue().getData());
        	}
        enrollmentQty = this.enrollments.size();
        }
        return new DtClass(this.name, this.registerDate, this.dateAndTime,this.url,enrollmentQty, dtE, this.imgName);
    }
}
