package si.lab.storage;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;
import si.lab.utils.Seed;

import java.util.Collection;

public class MongoStore implements Store {
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

        Seed.seed(this);
    }

    @Override
    public Collection<Student> getStudents() {
        return datastore.createQuery(Student.class).asList();
    }

    @Override
    public Collection<Course> getCourses() {
        return datastore.createQuery(Course.class).asList();
    }

    @Override
    public Course getCourse(int id) {
        return null;
    }

    @Override
    public void addCourse(Course course) {
        datastore.save(course);
    }

    @Override
    public Course updateCourse(int id, Course newCourse) {
        return null;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }

    @Override
    public void addStudent(Student student) {
        datastore.save(student);
    }

    @Override
    public Student updateStudent(long index, Student newStudent) {
        return null;
    }

    @Override
    public boolean deleteStudent(long index) {
        return false;
    }

    @Override
    public Student getStudent(long index) {
        return null;
    }

    @Override
    public Collection<Grade> getStudentGrades(long index) {
        return datastore.find(Student.class)
                .filter("index", index)
                .get()
                .getGrades();
    }

    @Override
    public Grade getStudentGrade(long index, int id) {
        return null;
    }

    @Override
    public Grade addStudentGrade(long index, Grade grade) {
        return null;
    }

    @Override
    public Grade addStudentGrade(Student student, Grade grade) {
        grade.setStudent(student);
        student.getGrades().add(grade);
        datastore.save(student);
        return grade;
    }

    @Override
    public boolean deleteStudentGrade(long index, int id) {
        return false;
    }

    @Override
    public Grade updateStudentGrade(long index, int id, Grade newGrade) {
        return null;
    }
}
