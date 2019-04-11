package si.lab.model;

import si.lab.storage.Store;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement
public class Grade {
    private int id;
    private Score score;
    private Date insertedAt;
    private Course course;

    public Grade() {
    }

    public Grade(int id, Score score, Date insertedAt, Course course) {
        this.id = id;
        this.score = score;
        this.insertedAt = insertedAt;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourseId() {
        return course.getId();
    }

    public void setCourseId(int id) {
        this.course = Store.getInstance().getCourse(id);
    }

    public enum Score {
        A(5),
        B(4.5),
        C(4),
        D(3.5),
        E(3),
        F(2);
        private double value;

        Score(double value) {
            this.value = value;
        }

        double getValue() {
            return this.value;
        }
    }
}
