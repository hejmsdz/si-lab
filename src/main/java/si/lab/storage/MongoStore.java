package si.lab.storage;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;
import si.lab.utils.Seed;

import java.util.Collection;

public class MongoStore {
    private final String DB_NAME = "sintLab";
    private final Datastore datastore;

    private static MongoStore ourInstance = new MongoStore();
    public static MongoStore getInstance() {
        return ourInstance;
    }

    private MongoStore() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("si.lab.model");

        datastore = morphia.createDatastore(new MongoClient(), this.DB_NAME);
        datastore.ensureIndexes();

//        Seed.seed(this);
    }

    public Collection<Student> getStudents() {
        return datastore.createQuery(Student.class).asList();
    }

    public Collection<Course> getCourses() {
        return datastore.createQuery(Course.class).asList();
    }

    public Course getCourse(String id) {
        return datastore.get(Course.class, new ObjectId(id));
    }

    public void addCourse(Course course) {
        datastore.save(course);
    }

    public Course updateCourse(String id, Course newCourse) {
        return null;
    }

    public boolean deleteCourse(String id) {
        return false;
    }

    public void addStudent(Student student) {
        datastore.save(student);
    }

    public Student updateStudent(long index, Student newStudent) {
        return null;
    }

    public boolean deleteStudent(long index) {
        return false;
    }

    public Student getStudent(long index) {
        return datastore.find(Student.class)
                .filter("index", index)
                .get();
    }

    public Collection<Grade> getStudentGrades(long index) {
        Student student = getStudent(index);
        if (student == null) return null;
        return student.getGrades();
    }

    public Grade getStudentGrade(long index, int id) {
        return null;
    }

    public Grade addStudentGrade(long index, Grade grade) {
        return null;
    }

    public Grade addStudentGrade(Student student, Grade grade) {
        grade.setStudent(student);
        student.getGrades().add(grade);
        datastore.save(student);
        return grade;
    }

    public boolean deleteStudentGrade(long index, int id) {
        return false;
    }

    public Grade updateStudentGrade(long index, int id, Grade newGrade) {
        return null;
    }
}
