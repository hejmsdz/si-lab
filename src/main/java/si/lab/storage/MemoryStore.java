package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Student;

import java.util.*;

public class MemoryStore {
    private static MemoryStore ourInstance = new MemoryStore();
    private Map<Long, Student> students;
    private List<Course> courses;

    public static MemoryStore getInstance() {
        return ourInstance;
    }

    private MemoryStore() {
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
        course.setId(courses.size() + 1);
        courses.add(course);
    }

    public Course updateCourse(int id, Course newCourse) {
        Course course = getCourse(id);
        if (course == null) {
            return null;
        }
        course.setName(newCourse.getName());
        course.setTeacher(newCourse.getTeacher());
        return course;
    }

    public boolean deleteCourse(int id) {
        students.values()
                .forEach(student -> student.getGrades().removeIf(grade -> grade.getCourse().getId() == id));
        return courses.removeIf(course -> course.getId() == id);
    }

    public void addStudent(Student student) {
        student.setIndex(students.keySet().stream().reduce(Long::max).orElse(0L) + 1L);
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

    public boolean deleteStudent(long index) {
        return students.remove(index) != null;
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

    public Grade addStudentGrade(long index, Grade grade) {
        Student student = getStudent(index);
        if (student == null || grade.getCourse() == null) {
            return null;
        }
        Collection<Grade> grades = student.getGrades();
        grade.setStudent(student);
        grade.setId(grades.size() + 1);
        grades.add(grade);
        return grade;
    }

    public Grade addStudentGrade(Student student, Grade grade) {
        return addStudentGrade(student.getIndex(), grade);
    }

    public boolean deleteStudentGrade(long index, int id) {
        return getStudentGrades(index).removeIf(grade -> grade.getId() == id);
    }

    public Grade updateStudentGrade(long index, int id, Grade newGrade) {
        Grade grade = getStudentGrade(index, id);
        if (grade == null) {
            return null;
        }
        grade.setScore(newGrade.getScore());
        grade.setCourse(newGrade.getCourse());
        grade.setInsertedAt(newGrade.getInsertedAt());
        return grade;
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

        addStudentGrade(student1, new Grade(1, Grade.Score.B, new Date(), course1, null));
        addStudentGrade(student1, new Grade(2, Grade.Score.A, new Date(), course1, null));
        addStudentGrade(student2, new Grade(3, Grade.Score.D, new Date(), course2, null));
        addStudentGrade(student3, new Grade(4, Grade.Score.C, new Date(), course2, null));
        addStudentGrade(student3, new Grade(5, Grade.Score.F, new Date(), course3, null));
    }
}
