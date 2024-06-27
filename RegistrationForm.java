package RegistrationAPP.src.AssignmentApplet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;

//Class registration form extends  the super class Jfreme
public class RegistrationForm extends JFrame {
    //declaring the fields
    private JPanel contentPane;
    private JTextField fullNameField, birthYearField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> birthMonthComboBox, genderComboBox, countryComboBox;

    private static final String[] MONTHS = {"NotSelected", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static final String[] GENDERS = {"NotSelected", "Male", "Female"};
    private static final String[] COUNTRIES = {"NotSelected", "Burundi", "Kenya", "Rwanda", "South Sudan", "Tanzania", "Uganda"};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistrationForm frame = new RegistrationForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(9, 3, 10, 10));
        setResizable(false);
        JLabel lblFullName = new JLabel("Full Name:");
        contentPane.add(lblFullName);
        getContentPane().setBackground(Color.cyan);

        fullNameField = new JTextField();
        contentPane.add(fullNameField);
        fullNameField.setColumns(30);

        JLabel lblPassword = new JLabel("Password:");
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        contentPane.add(passwordField);
        passwordField.setColumns(15);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        contentPane.add(lblConfirmPassword);

        confirmPasswordField = new JPasswordField();
        contentPane.add(confirmPasswordField);
        confirmPasswordField.setColumns(15);

        JLabel lblBirthMonth = new JLabel("Birth Month:");
        contentPane.add(lblBirthMonth);

        birthMonthComboBox = new JComboBox<>(MONTHS);
        birthMonthComboBox.setMaximumRowCount(6);
        contentPane.add(birthMonthComboBox);

        JLabel lblBirthYear = new JLabel("Birth Year:");
        contentPane.add(lblBirthYear);

        birthYearField = new JTextField();
        contentPane.add(birthYearField);
        birthYearField.setColumns(6);

        JLabel lblGender = new JLabel("Gender:");
        contentPane.add(lblGender);

        genderComboBox = new JComboBox<>(GENDERS);
        contentPane.add(genderComboBox);

        JLabel lblCountry = new JLabel("Country:");
        contentPane.add(lblCountry);

        countryComboBox = new JComboBox<>(COUNTRIES);
        contentPane.add(countryComboBox);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRegistration();
            }
        });
        contentPane.add(btnSave);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        contentPane.add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnExit);
    }

    private void saveRegistration() {
        String fullName = fullNameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String birthMonth = (String) birthMonthComboBox.getSelectedItem();
        String birthYearStr = birthYearField.getText().trim();
        String gender = (String) genderComboBox.getSelectedItem();
        String country = (String) countryComboBox.getSelectedItem();

        // Validation
        boolean hasError = false;
        if (!isValidFullName(fullName)) {
            hasError = true;
        }
        if (!isValidPassword(password, confirmPassword)) {
            hasError = true;
        }
        if (!isValidComboBoxSelection(birthMonth)) {
            hasError = true;
        }
        if (!isValidComboBoxSelection(gender)) {
            hasError = true;
        }
        if (!isValidComboBoxSelection(country)) {
            hasError = true;
        }
        if (!isValidBirthYear(birthYearStr)) {
            hasError = true;
        }

        if (hasError) {
            JOptionPane.showMessageDialog(this, "Please check your entries, something is wrong.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int birthYear = Integer.parseInt(birthYearStr);
        int age = Year.now().getValue() - birthYear;

        // Save to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RegInfo.txt"))) {
            writer.write("Fullname: " + fullName + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Birth Month: " + birthMonth + "\n");
            writer.write("Birth Year: " + birthYear + "\n");
            writer.write("Age (yrs): " + age + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("Country: " + country + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Show popup
        showPopup(fullName, password, birthMonth, birthYear, age, gender, country);
    }

    private boolean isValidFullName(String fullName) {
        String[] names = fullName.split(" ");
        return names.length == 3;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        return password.length() >= 8 && password.equals(confirmPassword);
    }

    private boolean isValidComboBoxSelection(String selection) {
        return !selection.equals("NotSelected");
    }

    private boolean isValidBirthYear(String birthYearStr) {
        try {
            int birthYear = Integer.parseInt(birthYearStr);
            return birthYear >= 2005 && birthYear <= 2010;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showPopup(String fullName, String password, String birthMonth, int birthYear, int age, String gender, String country) {
        JDialog popup = new JDialog(this, "Registered Information", true);
        popup.setLayout(new GridLayout(7, 1));

        JLabel fullNameLabel = new JLabel("Fullname: " + fullName);
        JLabel passwordLabel = new JLabel("Password: " + password);
        JLabel birthMonthLabel = new JLabel("Birth Month: " + birthMonth);
        JLabel birthYearLabel = new JLabel("Birth Year: " + birthYear);
        JLabel ageLabel = new JLabel("Age (yrs): " + age);
        JLabel genderLabel = new JLabel("Gender: " + gender);
        JLabel countryLabel = new JLabel("Country: " + country);

        fullNameLabel.setFont(new Font("Serif", Font.BOLD, 28));
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 28));
        birthMonthLabel.setFont(new Font("Serif", Font.BOLD, 28));
        birthYearLabel.setFont(new Font("Serif", Font.BOLD, 28));
        ageLabel.setFont(new Font("Serif", Font.BOLD, 28));
        genderLabel.setFont(new Font("Serif", Font.BOLD, 28));
        countryLabel.setFont(new Font("Serif", Font.BOLD, 28));

        popup.add(fullNameLabel);
        popup.add(passwordLabel);
        popup.add(birthMonthLabel);
        popup.add(birthYearLabel);
        popup.add(ageLabel);
        popup.add(genderLabel);
        popup.add(countryLabel);

        popup.pack();
        popup.setLocationRelativeTo(this);
        popup.setVisible(true);
    }

    private void resetFields() {
        fullNameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        birthMonthComboBox.setSelectedIndex(0);
        birthYearField.setText("");
        genderComboBox.setSelectedIndex(0);
        countryComboBox.setSelectedIndex(0);
    }
}
