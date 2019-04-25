package si.lab.utils;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Score;
import si.lab.model.Student;
import si.lab.storage.MongoStore;
import si.lab.storage.Store;

import java.util.Date;

public class Seed {
    public static void seed(MongoStore store) {
        if (!store.getStudents().isEmpty() || !store.getCourses().isEmpty()) {
            return;
        }

        store.initSequence(1200);

        Student student1 = new Student(0, "Herminia", "Schowalter", new Date(1957, 11, 11));
        Student student2 = new Student(0, "Antwan", "Reinger", new Date(1951, 5, 19));
        Student student3 = new Student(0, "Sharon" ,"Gleason", new Date(1946, 4, 15));

        store.addStudent(student1);
        store.addStudent(student2);
        store.addStudent(student3);

        Course course1 = new Course(1, "Distributed systems", "Adeline Boyle");
        Course course2 = new Course(2, "Machine learning", "Urban Satterfield");
        Course course3 = new Course(3, "Optimization techniques", "Velva Parker");

        store.addCourse(course1);
        store.addCourse(course2);
        store.addCourse(course3);

        store.addStudentGrade(student1, new Grade(1, Score.B, new Date(), course1, null));
        store.addStudentGrade(student1, new Grade(2, Score.A, new Date(), course1, null));
        store.addStudentGrade(student2, new Grade(3, Score.D, new Date(), course2, null));
        store.addStudentGrade(student3, new Grade(4, Score.C, new Date(), course2, null));
        store.addStudentGrade(student3, new Grade(5, Score.F, new Date(), course3, null));
    }
}
