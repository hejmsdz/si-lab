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
        if (courses.size() <= id) {
            return null;
        }
        return courses.get(id);
    }

    public void addStudent(Student student) {
        students.put(student.getIndex(), student);
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

    private void seed() {
        Student student1 = new Student(534816, "Herminia", "Schowalter", new Date(1957, 11, 11));
        Student student2 = new Student(534817, "Antwan", "Reinger", new Date(1951, 5, 19));
        Student student3 = new Student(534818, "Sharon" ,"Gleason", new Date(1946, 4, 15));

        addStudent(student1);
        addStudent(student2);
        addStudent(student3);

        Course course1 = new Course(0, "Distributed systems", "Adeline Boyle");
        Course course2 = new Course(1, "Machine learning", "Urban Satterfield");
        Course course3 = new Course(2, "Optimization techniques", "Velva Parker");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        student1.getGrades().add(new Grade(1, Grade.Score.B, new Date(), course1));
        student2.getGrades().add(new Grade(2, Grade.Score.A, new Date(), course1));
        student2.getGrades().add(new Grade(3, Grade.Score.D, new Date(), course2));
        student3.getGrades().add(new Grade(4, Grade.Score.C, new Date(), course2));
        student3.getGrades().add(new Grade(5, Grade.Score.F, new Date(), course3));
    }
}
