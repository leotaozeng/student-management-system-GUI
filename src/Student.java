import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private int age;
    private ArrayList<Course> enrolledCourses;
    private ArrayList<String> grades;

    // Create a class constructor for the Student class
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    // Getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, String grade) {
        int index = enrolledCourses.indexOf(course);
        if (index != -1) {
            grades.add(index, grade);
        }
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public ArrayList<String> getGrades() {
        return grades;
    }
}
