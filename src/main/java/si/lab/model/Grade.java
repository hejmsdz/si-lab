package si.lab.model;

import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import si.lab.rest.CourseResource;
import si.lab.rest.StudentGradeResource;
import si.lab.rest.StudentGradesCollection;
import si.lab.rest.StudentResource;
import si.lab.storage.MongoStore;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Embedded
public class Grade {
    private int id;
    private Score score;
    private Date insertedAt;
    @XmlTransient
    @Reference
    private Course course;
    @XmlTransient
    @Transient
    private Student student;

    @InjectLinks({
            @InjectLink(resource = StudentGradeResource.class,
                    bindings = {
                        @Binding(name="index", value="${instance.student.index}"),
                        @Binding(name="id", value="${instance.id}")
                    },
                    rel = "self"),
            @InjectLink(
                    resource = StudentResource.class,
                    bindings = {@Binding(name="index", value="${instance.student.index}")},
                    rel = "student"),
            @InjectLink(
                    resource = StudentGradesCollection.class,
                    bindings = {@Binding(name="index", value="${instance.student.index}")},
                    rel = "parent"),
            @InjectLink(
                    resource = CourseResource.class,
                    bindings = {@Binding(name="id", value="${instance.course.id}")},
                    rel = "course")

    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @Transient
    List<Link> links;

    public Grade() {
    }

    public Grade(int id, Score score, Date insertedAt, Course course, Student student) {
        this.id = id;
        this.score = score;
        this.insertedAt = insertedAt;
        this.course = course;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @XmlTransient
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ObjectId getCourseId() {
        return course.getId();
    }

    public void setCourseId(ObjectId id) {
        this.course = MongoStore.getInstance().getCourse(id.toHexString());
    }

}
