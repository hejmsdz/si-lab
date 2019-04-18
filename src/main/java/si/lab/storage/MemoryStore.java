package si.lab.storage;

import si.lab.model.Course;
import si.lab.model.Grade;
import si.lab.model.Score;
import si.lab.model.Student;
import si.lab.utils.Seed;

import java.util.*;

public class MemoryStore implements Store {
    private static MemoryStore ourInstance = new MemoryStore();
    private Map<Long, Student> students;
    private List<Course> courses;

    public static MemoryStore getInstance() {
        return ourInstance;
    }

    private MemoryStore() {
        students = new HashMap<>();
        courses = new ArrayList<>();
        Seed.seed(this);
    }

    @Override
    public Collection<Student> getStudents() {
        return students.values();
    }

    @Override
    public Collection<Course> getCourses() {
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addCourse(Course course) {
        course.setId(courses.size() + 1);
        courses.add(course);
    }

    @Override
    public Course updateCourse(int id, Course newCourse) {
        Course course = getCourse(id);
        if (course == null) {
            return null;
        }
        course.setName(newCourse.getName());
        course.setTeacher(newCourse.getTeacher());
        return course;
    }

    @Override
    public boolean deleteCourse(int id) {
        students.values()
                .forEach(student -> student.getGrades().removeIf(grade -> grade.getCourse().getId() == id));
        return courses.removeIf(course -> course.getId() == id);
    }

    @Override
    public void addStudent(Student student) {
        student.setIndex(students.keySet().stream().reduce(Long::max).orElse(0L) + 1L);
        students.put(student.getIndex(), student);
    }

    @Override
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

    @Override
    public boolean deleteStudent(long index) {
        return students.remove(index) != null;
    }

    @Override
    public Student getStudent(long index) {
        return students.get(index);
    }

    @Override
    public Collection<Grade> getStudentGrades(long index) {
        Student student = getStudent(index);
        if (student == null) {
            return null;
        }
        return student.getGrades();
    }

    @Override
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

    @Override
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

    @Override
    public Grade addStudentGrade(Student student, Grade grade) {
        return addStudentGrade(student.getIndex(), grade);
    }

    @Override
    public boolean deleteStudentGrade(long index, int id) {
        return getStudentGrades(index).removeIf(grade -> grade.getId() == id);
    }

    @Override
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
}
