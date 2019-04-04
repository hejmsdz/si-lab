package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store {
    private static Store ourInstance = new Store();
    private List<Student> students;
    private List<Course> courses;

    public static Store getInstance() {
        return ourInstance;
    }

    private Store() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        seed();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    private void seed() {
        Student student1 = new Student(534816, "Herminia", "Schowalter", new Date(1957, 11, 11));
        Student student2 = new Student(534817, "Antwan", "Reinger", new Date(1951, 5, 19));
        Student student3 = new Student(534818, "Sharon" ,"Gleason", new Date(1946, 4, 15));

        students.add(student1);
        students.add(student2);
        students.add(student3);

        Course course1 = new Course(1, "Distributed systems", "Adeline Boyle");
        Course course2 = new Course(2, "Machine learning", "Urban Satterfield");
        Course course3 = new Course(3, "Optimization techniques", "Velva Parker");

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        student1.getGrades().add(new Grade(1, Grade.Score.B, new Date(), course1));
        student2.getGrades().add(new Grade(1, Grade.Score.A, new Date(), course1));
        student2.getGrades().add(new Grade(1, Grade.Score.D, new Date(), course2));
        student3.getGrades().add(new Grade(1, Grade.Score.C, new Date(), course2));
        student3.getGrades().add(new Grade(1, Grade.Score.F, new Date(), course3));
    }
}
