package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Student;

import java.util.ArrayList;
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
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
