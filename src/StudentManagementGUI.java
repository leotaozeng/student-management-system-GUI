import javax.swing.*;
import java.awt.*;

public class StudentManagementGUI extends JFrame {
    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI frame = new StudentManagementGUI();
            frame.setVisible(true);
        });
    }

    private void initializeComponents() {
        // Set the layout for the frame
        setLayout(new BorderLayout());

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu studentMenu = new JMenu("Student");
        JMenu courseMenu = new JMenu("Course");
        JMenu gradeMenu = new JMenu("Grade");

        // Create Student menu items
        JMenuItem addStudent = new JMenuItem("Add Student");
        JMenuItem updateStudent = new JMenuItem("Update Student");
        JMenuItem viewStudentDetails = new JMenuItem("View Student Details");

        // Create Course menu items
        JMenuItem enrollCourse = new JMenuItem("Enroll in Course");

        // Create Grade menu items
        JMenuItem assignGrade = new JMenuItem("Assign Grade");

        // Add menu items to menus
        studentMenu.add(addStudent);
        studentMenu.add(updateStudent);
        studentMenu.add(viewStudentDetails);

        courseMenu.add(enrollCourse);

        gradeMenu.add(assignGrade);

        // Add menus to menu bar
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(gradeMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add main panel to the frame
        JPanel mainPanel = new JPanel(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Add event listeners for menu items
        addStudent.addActionListener(e -> showAddStudentForm(mainPanel));
        updateStudent.addActionListener(e -> showUpdateStudentForm(mainPanel));
        viewStudentDetails.addActionListener(e -> showStudentList(mainPanel));
        enrollCourse.addActionListener(e -> showEnrollCourseForm(mainPanel));
        assignGrade.addActionListener(e -> showAssignGradeForm(mainPanel));
    }

    private void showAddStudentForm(JPanel mainPanel) {
        // Implementation for showing the Add Student form
    }

    private void showUpdateStudentForm(JPanel mainPanel) {
        // Implementation for showing the Update Student form
    }

    private void showStudentList(JPanel mainPanel) {
        // Implementation for showing the Student List
    }

    private void showEnrollCourseForm(JPanel mainPanel) {
        // Implementation for showing the Enroll Course form
    }

    private void showAssignGradeForm(JPanel mainPanel) {
        // Implementation for showing the Assign Grade form
    }
}