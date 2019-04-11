package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;

import java.util.*;

public class Store {
    private static Store ourInstance = new Store();
    private Map<Long, Student> students;
    private List<Course> courses;

    public static Store getInstance() {
        return ourInstance;
    }

    private Store() {
        students = new HashMap<>();
        courses = new ArrayList<>();
        seed();
    }

    public Collection<Student> getStudents() {
        return students.values();
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public Course getCourse(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(int id) {
        students.values()
                .forEach(student -> student.getGrades().removeIf(grade -> grade.getCourse().getId() == id));
        courses.removeIf(course -> course.getId() == id);
    }

    public void addStudent(Student student) {
        students.put(student.getIndex(), student);
    }

    public Student updateStudent(long index, Student newStudent) {
        Student student = getStudent(index);
        if (student == null) {
            return null;
        }
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setBirthDate(newStudent.getBirthDate());
        return student;
    }

    public void deleteStudent(long index) {
        students.remove(index);
    }

    public Student getStudent(long index) {
        return students.get(index);
    }

    public Collection<Grade> getStudentGrades(long index) {
        Student student = getStudent(index);
        if (student == null) {
            return null;
        }
        return student.getGrades();
    }

    public Grade getStudentGrade(long index, int id) {
        Collection<Grade> grades = getStudentGrades(index);
        if (grades == null) {
            return null;
        }
        return grades.stream()
                .filter(grade -> grade.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addStudentGrade(long index, Grade grade) {
        Collection<Grade> grades = getStudentGrades(index);
        grades.add(grade);
    }

    public void addStudentGrade(Student student, Grade grade) {
        addStudentGrade(student.getIndex(), grade);
    }

    public void deleteStudentGrade(long index, int id) {
        getStudentGrades(index).removeIf(grade -> grade.getId() == id);
    }

    private void seed() {
        Student student1 = new Student(534816, "Herminia", "Schowalter", new Date(1957, 11, 11));
        Student student2 = new Student(534817, "Antwan", "Reinger", new Date(1951, 5, 19));
        Student student3 = new Student(534818, "Sharon" ,"Gleason", new Date(1946, 4, 15));

        addStudent(student1);
        addStudent(student2);
        addStudent(student3);

        Course course1 = new Course(1, "Distributed systems", "Adeline Boyle");
        Course course2 = new Course(2, "Machine learning", "Urban Satterfield");
        Course course3 = new Course(3, "Optimization techniques", "Velva Parker");

        addCourse(course1);
        addCourse(course2);
        addCourse(course3);

        addStudentGrade(student1, new Grade(1, Grade.Score.B, new Date(), course1));
        addStudentGrade(student1, new Grade(2, Grade.Score.A, new Date(), course1));
        addStudentGrade(student2, new Grade(3, Grade.Score.D, new Date(), course2));
        addStudentGrade(student3, new Grade(4, Grade.Score.C, new Date(), course2));
        addStudentGrade(student3, new Grade(5, Grade.Score.F, new Date(), course3));
    }
}
