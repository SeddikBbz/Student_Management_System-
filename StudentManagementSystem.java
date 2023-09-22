package POO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {

    private JPanel panel;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel(new BorderLayout());
        add(panel);

        toBack(); // set main window on background

        // create student table with column headers
        String[] columnHeaders = { "First Name", "Last Name", "Birth Date", "Bac ID", "Bac Info", "Univ Cycle",
                "Email Address", "Home Address" };
        tableModel = new DefaultTableModel(columnHeaders, 0);
        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // set the size and font of the header
        JTableHeader header = studentTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 26)); // set the height to 30 pixels
        header.setFont(header.getFont().deriveFont(16f)); // set the font size to 16

        // create buttons for adding, deleting, modifying and searching students
        JButton addStudentButton = new JButton("Add Student");
        JButton deleteStudentButton = new JButton("Delete Student");
        JButton modifyStudentButton = new JButton("Modify Student");
        JButton searchButton = new JButton("Search");
        JButton exitButton = new JButton("Exit");

        // add action listeners to buttons
        addStudentButton.addActionListener(e -> {
            addStudent();
        });

        deleteStudentButton.addActionListener(e -> {
            deleteStudent();
        });

        modifyStudentButton.addActionListener(e -> {
            modifyStudent();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        searchButton.addActionListener(e -> {
            // create search dialog box
            JDialog searchDialog = new JDialog(this, "Search Students", true);
            searchDialog.setSize(400, 200);
            // setBounds(700, 150, 400, 200);

            searchDialog.setLocationRelativeTo(null);

            // create search panel
            JPanel searchPanel = new JPanel(new GridLayout(3, 2));
            JLabel firstNameLabel = new JLabel("First Name:");
            JTextField firstNameField = new JTextField();
            JLabel BacIdLabel = new JLabel("BAC ID:");
            JTextField BacIdField = new JTextField();
            JLabel BacInfoLabel = new JLabel("BAC Info:");
            JTextField BacInfoField = new JTextField();

            searchPanel.add(firstNameLabel);
            searchPanel.add(firstNameField);
            searchPanel.add(BacIdLabel);
            searchPanel.add(BacIdField);
            searchPanel.add(BacInfoLabel);
            searchPanel.add(BacInfoField);

            searchDialog.add(searchPanel, BorderLayout.CENTER);

            // create search button
            JButton searchDialogButton = new JButton("Search");

            searchDialogButton.addActionListener(e1 -> {
                // Get search terms from text fields
                String searchTerm1 = firstNameField.getText();
                String searchTerm2 = BacIdField.getText();
                String searchTerm3 = BacInfoField.getText();

                // Create new JFrame to display search results
                JFrame searchResultsFrame = new JFrame("Search Results");
                // searchResultsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                {
                    if ((searchTerm1.isEmpty()) && (searchTerm2.isEmpty()) && (searchTerm3.isEmpty())) {
                        // Execute code block if searchTerm1 and searchTerm2 and searchTerm3 are empty
                        JOptionPane.showMessageDialog(null, "Please Enter at least 1 Input !");
                        return;
                    } else if (searchTerm1.isEmpty() && searchTerm2.isEmpty()) {
                        // Execute code block if both searchTerm1 and searchTerm2 are empty

                    } else if (searchTerm2.isEmpty() && searchTerm3.isEmpty()) {
                        // Execute code block if searchTerm2 and searchTerm3 are empty

                    } else if (searchTerm1.isEmpty() && searchTerm3.isEmpty()) {
                        // Execute code block if searchTerm2 and searchTerm3 are empty
                    }
                }

                // Create JLabel to display search term
                JLabel searchLabel = new JLabel("Search Results for: "
                        + searchTerm1 + " " + searchTerm2 + " " + searchTerm3);
                searchResultsFrame.add(searchLabel, BorderLayout.NORTH);

                // Create table for displaying search results
                JTable searchResultsTable = new JTable();
                DefaultTableModel searchResultsModel = new DefaultTableModel(columnHeaders, 0);
                searchResultsTable.setModel(searchResultsModel);

                // Loop through students and add matching results to table
                boolean foundMatch = false;
                for (Student student : students) {
                    if ((searchTerm1.equalsIgnoreCase(student.getFirstName()) ||
                            searchTerm2.equalsIgnoreCase(student.getBacId()) ||
                            searchTerm3.equalsIgnoreCase(student.getBacInfo()))
                            ||
                            (searchTerm1.equalsIgnoreCase(student.getFirstName()) &&
                                    searchTerm2.equalsIgnoreCase(student.getBacId()))
                            ||
                            (searchTerm2.equalsIgnoreCase(student.getBacId()) &&
                                    searchTerm3.equalsIgnoreCase(student.getBacInfo()))
                            ||
                            (searchTerm1.equalsIgnoreCase(student.getFirstName()) &&
                                    searchTerm3.equalsIgnoreCase(student.getBacInfo()))) {

                        JOptionPane.showMessageDialog(null, "Your Search is find successfully");

                        Object[] row = { student.getFirstName(), student.getLastName(), student.getBirthDate(),
                                student.getBacId(), student.getBacInfo(), student.getUnivCycle(),
                                student.getEmailAdd(), student.getHomeAdd() };
                        searchResultsModel.addRow(row);
                        foundMatch = true;
                    }
                }

                if (!foundMatch) {
                    JOptionPane.showMessageDialog(null, "No matching results found.");
                }

                // Add table to scroll pane and to frame
                JScrollPane searchResultsScrollPane = new JScrollPane(searchResultsTable);
                searchResultsFrame.add(searchResultsScrollPane, BorderLayout.EAST);

                searchResultsFrame.pack();
                searchResultsFrame.setLocationRelativeTo(null);
                searchResultsFrame.setVisible(true);
                searchResultsFrame.toFront();
            });

            // add cancel button
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e1 -> searchDialog.dispose());

            // add buttons to search panel
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(searchDialogButton);
            buttonPanel.add(cancelButton);
            searchDialog.add(buttonPanel, BorderLayout.SOUTH);

            searchDialog.setVisible(true);
        });

        // add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.add(addStudentButton);
        buttonPanel.add(deleteStudentButton);
        buttonPanel.add(modifyStudentButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load students from file if it exists
        loadStudents();

        // Add existing students to table
        for (Student student : students) {
            Object[] row = { student.getFirstName(), student.getLastName(), student.getBirthDate(),
                    student.getBacId(), student.getBacInfo(), student.getUnivCycle(),
                    student.getEmailAdd(), student.getHomeAdd() };
            tableModel.addRow(row);
        }

        setVisible(true);
    }

    // Create new student
    private void addStudent() {
        boolean inputsEntered = false;

        while (!inputsEntered) {
            // create input dialog for entering student information
            JTextField firstNameField = new JTextField(10);
            JTextField lastNameField = new JTextField(10);
            JTextField birthDateField = new JTextField(10);
            JTextField bacIdField = new JTextField(10);
            JTextField bacInfoField = new JTextField(10);
            JTextField univCycleField = new JTextField(10);
            JTextField emailAddField = new JTextField(10);
            JTextField homeAddField = new JTextField(10);

            JPanel inputPanel = new JPanel(new GridLayout(0, 2));
            inputPanel.add(new JLabel("First Name:"));
            inputPanel.add(firstNameField);
            inputPanel.add(new JLabel("Last Name:"));
            inputPanel.add(lastNameField);
            inputPanel.add(new JLabel("Birth Date:"));
            inputPanel.add(birthDateField);
            inputPanel.add(new JLabel("Bac ID:"));
            inputPanel.add(bacIdField);
            inputPanel.add(new JLabel("Bac Year Success :"));
            inputPanel.add(bacInfoField);
            inputPanel.add(new JLabel("Univ Cycle:"));
            inputPanel.add(univCycleField);
            inputPanel.add(new JLabel("Email Address:"));
            inputPanel.add(emailAddField);
            inputPanel.add(new JLabel("Home Address:"));
            inputPanel.add(homeAddField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel,
                    "Enter Student Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // add student to table and array list
            if (result == JOptionPane.OK_OPTION) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String birthDate = birthDateField.getText();
                String bacId = bacIdField.getText();
                String bacInfo = bacInfoField.getText();
                String univCycle = univCycleField.getText();
                String emailAddress = emailAddField.getText();
                String homeAddress = homeAddField.getText();

                if (firstName.isEmpty() || bacId.isEmpty() || bacInfo.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter Necessary information :\n( `First Name` And `Bac ID` And `Bac year success` )\n To Add The Student.");
                } else {
                    Student student = new Student(firstName, lastName, birthDate, bacId, bacInfo, univCycle,
                            emailAddress, homeAddress);
                    students.add(student);

                    Object[] rowData = { firstName, lastName, birthDate, bacId, bacInfo, univCycle, emailAddress,
                            homeAddress };
                    tableModel.addRow(rowData);

                    JOptionPane.showMessageDialog(null, "The Student information are added successfully!");

                    saveStudents();

                    inputsEntered = true;
                }
            } else {
                inputsEntered = true;
            }
        }
    }

    private void deleteStudent() {
        // get selected row and delete student from table and array list
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            students.remove(row);
            JOptionPane.showMessageDialog(null, "The Student information are Deleted with success !");
            saveStudents();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }

    // Modify Student Information

    private void modifyStudent() {

        // get selected row and create input dialog with current student information
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            Student student = students.get(selectedRow);

            JTextField firstNameField = new JTextField(student.getFirstName(), 10);
            JTextField lastNameField = new JTextField(student.getLastName(), 10);
            JTextField birthDateField = new JTextField(student.getBirthDate(), 10);
            JTextField bacIdField = new JTextField(student.getBacId(), 10);
            JTextField bacInfoField = new JTextField(student.getBacInfo(), 10);
            JTextField univCycleField = new JTextField(student.getUnivCycle(), 10);
            JTextField emailAddField = new JTextField(student.getEmailAdd(), 10);
            JTextField homeAddField = new JTextField(student.getHomeAdd(), 10);

            JPanel inputPanel = new JPanel(new GridLayout(8, 2));
            inputPanel.add(new JLabel("First Name: "));
            inputPanel.add(firstNameField);
            inputPanel.add(new JLabel("Last Name: "));
            inputPanel.add(lastNameField);
            inputPanel.add(new JLabel("Birth Date (dd/mm/yyyy): "));
            inputPanel.add(birthDateField);
            inputPanel.add(new JLabel("Bac ID: "));
            inputPanel.add(bacIdField);
            inputPanel.add(new JLabel("Bac Info: "));
            inputPanel.add(bacInfoField);
            inputPanel.add(new JLabel("Univ Cycle: "));
            inputPanel.add(univCycleField);
            inputPanel.add(new JLabel("Email Address: "));
            inputPanel.add(emailAddField);
            inputPanel.add(new JLabel("Home Address: "));
            inputPanel.add(homeAddField);

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Modify Student", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // modify student and update table
            if (result == JOptionPane.OK_OPTION) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String birthDate = birthDateField.getText();
                String bacId = bacIdField.getText();
                String bacInfo = bacInfoField.getText();
                String univCycle = univCycleField.getText();
                String emailAdd = emailAddField.getText();
                String homeAdd = homeAddField.getText();

                // Check Statement to ensure lose & duplicate information , If one Field are
                // null info dons't Upadate !

                if (!firstName.equals("") || !lastName.equals("") || !birthDate.equals("") || !bacId.equals("")
                        || !bacInfo.equals("") || !univCycle.equals("") || !emailAdd.equals("")
                        || !homeAdd.equals("")) {
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setBirthDate(birthDate);
                    student.setBacId(bacId);
                    student.setBacInfo(bacInfo);
                    student.setUnivCycle(univCycle);
                    student.setEmailAdd(emailAdd);
                    student.setHomeAdd(homeAdd);

                    tableModel.setValueAt(firstName, selectedRow, 0);
                    tableModel.setValueAt(lastName, selectedRow, 1);
                    tableModel.setValueAt(birthDate, selectedRow, 2);
                    tableModel.setValueAt(bacId, selectedRow, 3);
                    tableModel.setValueAt(bacInfo, selectedRow, 4);
                    tableModel.setValueAt(univCycle, selectedRow, 5);
                    tableModel.setValueAt(emailAdd, selectedRow, 6);
                    tableModel.setValueAt(homeAdd, selectedRow, 7);

                    JOptionPane.showMessageDialog(null, "The Student information are Modified with success !");

                    // save students to file
                    saveStudents();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No new information entered. The student information remains unchanged.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select Student to Modify!");
        }
    }

    // Load Student from Student.txt file where are stored in
    private void loadStudents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
                students.add(student);
                Object[] rowData = { data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7] };
                tableModel.addRow(rowData);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save Student information to (Students.txt) file where are stored in .
    private void saveStudents() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));
            for (Student student : students) {
                writer.write(student.getFirstName() + "," + student.getLastName() + ","
                        + student.getBirthDate() + "," + student.getBacId() + "," + student.getBacInfo() + ","
                        + student.getUnivCycle() + "," + student.getEmailAdd() + "," + student.getHomeAdd() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}