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

        Seed.seed(this);
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
        Course course = getCourse(id);
        if (course == null) {
            return null;
        }
        course.setName(newCourse.getName());
        course.setTeacher(newCourse.getTeacher());
        datastore.save(course);
        return course;
    }

    public boolean deleteCourse(String id) {
        Course course = getCourse(id);
        if (course == null) {
            return false;
        }
        ObjectId oid = new ObjectId(id);
        System.out.println("delete course "+id +" = " + oid);
        datastore.createQuery(Student.class).forEach(student -> {
            System.out.println("student " + student.getIndex());
            if (student.getGrades().removeIf(grade -> {
                System.out.println("grade" + grade + "; " + grade.getCourseId() + ".." + grade.getCourse());
                return grade.getCourseId().equals(oid);
            })) {
                System.out.println("deleted some");
                datastore.save(student);
            }
        });
        return datastore.delete(course).getN() == 1;
    }

    public void addStudent(Student student) {
        datastore.save(student);
    }

    public Student updateStudent(long index, Student newStudent) {
        Student student = getStudent(index);
        if (student == null) {
            return null;
        }
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setBirthDate(newStudent.getBirthDate());
        datastore.save(student);
        return student;
    }

    public boolean deleteStudent(long index) {
        Student student = getStudent(index);
        if (student == null) {
            return false;
        }
        return datastore.delete(student).getN() == 1;
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
        Student student = getStudent(index);
        if (student == null) return null;
        if (student.getGrades().size() <= id) return null;
        return student.getGrades().get(id);
    }

    public Grade addStudentGrade(long index, Grade grade) {
        return addStudentGrade(getStudent(index), grade);
    }

    public Grade addStudentGrade(Student student, Grade grade) {
        grade.setStudent(student);
        grade.setId(student.getGrades().size());
        student.getGrades().add(grade);
        datastore.save(student);
        return grade;
    }

    public boolean deleteStudentGrade(long index, int id) {
        Student student = getStudent(index);
        if (student == null) return false;
        if (student.getGrades().size() <= id) return false;
        student.getGrades().remove(id);
        datastore.save(student);
        return true;
    }

    public Grade updateStudentGrade(long index, int id, Grade newGrade) {
        return null;
    }
}
