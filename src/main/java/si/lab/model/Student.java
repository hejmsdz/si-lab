package si.lab.model;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import si.lab.rest.StudentGradesCollection;
import si.lab.rest.StudentResource;
import si.lab.rest.StudentsCollection;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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

    @InjectLinks({
            @InjectLink(
                    resource = StudentResource.class,
                    bindings = {@Binding(name="index", value="${instance.index}")},
                    rel = "self"),
            @InjectLink(
                    resource = StudentsCollection.class,
                    rel = "parent"),
            @InjectLink(resource = StudentGradesCollection.class,
                    bindings = {@Binding(name="index", value="${instance.index}")},
                    rel = "grades")
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    List<Link> links;

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
