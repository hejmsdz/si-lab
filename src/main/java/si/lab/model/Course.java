package si.lab.model;

import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;
import si.lab.rest.CourseResource;
import si.lab.rest.CoursesCollection;
import si.lab.utils.ObjectIdJaxbAdapter;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Entity
@XmlRootElement
public class Course {
    @Id
    @XmlTransient
    private ObjectId _id;
    private String name;
    private String teacher;

    @InjectLinks({
            @InjectLink(
                    resource = CourseResource.class,
                    bindings = {@Binding(name="id", value="${instance.id}")},
                    rel = "self"),
            @InjectLink(
                    resource = CoursesCollection.class,
                    rel = "parent")
    })
    @XmlElement(name="link")
    @XmlElementWrapper(name = "links")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    @Transient
    List<Link> links;

    public Course() {
    }

    public Course(int _id, String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    @XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    public ObjectId getId() {
        return _id;
    }
    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
