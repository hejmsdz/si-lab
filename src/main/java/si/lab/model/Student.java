package si.lab.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Student {
    private long index;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Grade> grades;

    public Student() {
        grades = new ArrayList<>();
    }

    public Student(long index, String firstName, String lastName, Date birthDate) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        grades = new ArrayList<>();
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @XmlTransient
    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
