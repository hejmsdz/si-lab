package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;

import java.util.Collection;

public interface Store {
    Collection<Student> getStudents();

    Collection<Course> getCourses();

    Course getCourse(int id);

    void addCourse(Course course);

    Course updateCourse(int id, Course newCourse);

    boolean deleteCourse(int id);

    void addStudent(Student student);

    Student updateStudent(long index, Student newStudent);

    boolean deleteStudent(long index);

    Student getStudent(long index);

    Collection<Grade> getStudentGrades(long index);

    Grade getStudentGrade(long index, int id);

    Grade addStudentGrade(long index, Grade grade);

    Grade addStudentGrade(Student student, Grade grade);

    boolean deleteStudentGrade(long index, int id);

    Grade updateStudentGrade(long index, int id, Grade newGrade);
}
