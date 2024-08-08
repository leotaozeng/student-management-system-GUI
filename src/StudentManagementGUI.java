import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentManagementGUI extends JFrame {
    private JTable studentTable;
    private DefaultTableModel studentTableModel;

    private JTable courseTable;
    private DefaultTableModel courseTableModel;

    private JTable gradeTable;
    private DefaultTableModel gradeTableModel;

    private final ArrayList<Student> students;
    private final ArrayList<Course> courses;

    public StudentManagementGUI() {
        students = new ArrayList<>();
        courses = new ArrayList<>();

        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel studentPanel = createStudentPanel();
        JPanel coursePanel = createCoursePanel();
        JPanel gradePanel = createGradePanel();

        tabbedPane.addTab("Students", studentPanel);
        tabbedPane.addTab("Courses", coursePanel);
        tabbedPane.addTab("Grades", gradePanel);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        studentTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age"}, 0);
        studentTable = new JTable(studentTableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton viewButton = new JButton("View Student Details");

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        viewButton.addActionListener(e -> viewStudentDetails());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(viewButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        courseTableModel = new DefaultTableModel(new Object[]{"Course ID", "Course Name"}, 0);
        courseTable = new JTable(courseTableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Course");
        JButton enrollButton = new JButton("Enroll Student");

        addButton.addActionListener(e -> addCourse());
        enrollButton.addActionListener(e -> enrollStudentInCourse());

        buttonPanel.add(addButton);
        buttonPanel.add(enrollButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createGradePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        gradeTableModel = new DefaultTableModel(new Object[]{"Student ID", "Course ID", "Grade"}, 0);
        gradeTable = new JTable(gradeTableModel);
        JScrollPane scrollPane = new JScrollPane(gradeTable);

        JPanel buttonPanel = new JPanel();
        JButton assignGradeButton = new JButton("Assign Grade");

        assignGradeButton.addActionListener(e -> assignGrade());

        buttonPanel.add(assignGradeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void addStudent() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();

        Object[] message = {
                "ID:", idField,
                "Name:", nameField,
                "Age:", ageField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());

            Student student = new Student(id, name, age);
            students.add(student);

            studentTableModel.addRow(new Object[]{id, name, age});
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) studentTableModel.getValueAt(selectedRow, 0);
            Student student = findStudentById(id);

            if (student != null) {
                JTextField nameField = new JTextField(student.getName());
                JTextField ageField = new JTextField(String.valueOf(student.getAge()));

                Object[] message = {
                        "Name:", nameField,
                        "Age:", ageField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Student", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    student.setName(nameField.getText());
                    student.setAge(Integer.parseInt(ageField.getText()));

                    studentTableModel.setValueAt(student.getName(), selectedRow, 1);
                    studentTableModel.setValueAt(student.getAge(), selectedRow, 2);
                }
            }
        }
    }

    private void viewStudentDetails() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) studentTableModel.getValueAt(selectedRow, 0);
            Student student = findStudentById(id);

            if (student != null) {
                JOptionPane.showMessageDialog(this, "ID: " + student.getId() +
                        "\nName: " + student.getName() +
                        "\nAge: " + student.getAge(), "Student Details", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void addCourse() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] message = {
                "Course ID:", idField,
                "Course Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();

            Course course = new Course(id, name);
            courses.add(course);

            courseTableModel.addRow(new Object[]{id, name});
        }
    }

    private void enrollStudentInCourse() {
        String[] studentIds = students.stream().map(Student::getId).toArray(String[]::new);
        String studentId = (String) JOptionPane.showInputDialog(this, "Select Student", "Enroll Student",
                JOptionPane.PLAIN_MESSAGE, null, studentIds, studentIds[0]);

        if (studentId != null) {
            Student student = findStudentById(studentId);
            if (student != null) {
                String[] courseIds = courses.stream().map(Course::getId).toArray(String[]::new);
                String courseId = (String) JOptionPane.showInputDialog(this, "Select Course", "Enroll Student",
                        JOptionPane.PLAIN_MESSAGE, null, courseIds, courseIds[0]);

                if (courseId != null) {
                    Course course = findCourseById(courseId);
                    if (course != null) {
                        student.enrollInCourse(course);
                        JOptionPane.showMessageDialog(this, "Student enrolled in course successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    private void assignGrade() {
        String[] studentIds = students.stream().map(Student::getId).toArray(String[]::new);
        String studentId = (String) JOptionPane.showInputDialog(this, "Select Student", "Assign Grade",
                JOptionPane.PLAIN_MESSAGE, null, studentIds, studentIds[0]);

        if (studentId != null) {
            Student student = findStudentById(studentId);
            if (student != null) {
                String[] courseIds = student.getEnrolledCourses().stream().map(Course::getId).toArray(String[]::new);
                String courseId = (String) JOptionPane.showInputDialog(this, "Select Course", "Assign Grade",
                        JOptionPane.PLAIN_MESSAGE, null, courseIds, courseIds[0]);

                if (courseId != null) {
                    Course course = findCourseById(courseId);
                    if (course != null) {
                        JTextField gradeField = new JTextField();

                        Object[] message = {
                                "Grade:", gradeField
                        };

                        int option = JOptionPane.showConfirmDialog(this, message, "Assign Grade", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            String grade = gradeField.getText();
                            student.assignGrade(course, grade);
                            gradeTableModel.addRow(new Object[]{studentId, courseId, grade});
                        }
                    }
                }
            }
        }
    }

    private Student findStudentById(String id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    private Course findCourseById(String id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementGUI::new);
    }
}