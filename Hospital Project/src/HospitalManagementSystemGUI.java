import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

class Person {
    protected String name;
    protected String phoneNumber;

    public Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Patient extends Person {
    private String address;
    private double duePayment;

    public Patient(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
        this.duePayment = 0.0;
    }

    public void addPayment(double amount) {
        this.duePayment += amount;
    }

    public double getDuePayment() {
        return duePayment;
    }
}

class Doctor extends Person {
    public Doctor(String name, String phoneNumber) {
        super(name, phoneNumber);
    }
}

class Nurse extends Person {
    public Nurse(String name, String phoneNumber) {
        super(name, phoneNumber);
    }
}

public class HospitalManagementSystemGUI extends JFrame {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Nurse> nurses = new ArrayList<>();

    private JTextArea outputArea;

    public HospitalManagementSystemGUI() {
        super("Hospital Management System");

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 0, 0)); // red

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setForeground(new Color(0, 0, 255)); // blue
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right

        addButton("Add Patient", e -> addPatient(), buttonPanel);
        addButton("Add Doctor", e -> addDoctor(), buttonPanel);
        addButton("Add Nurse", e -> addNurse(), buttonPanel);
        addButton("Process Payment", e -> processPayment(), buttonPanel);
        addButton("Print All", e -> printAll(), buttonPanel);
        addButton("Exit", e -> System.exit(0), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addButton(String text, ActionListener listener, JPanel panel) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        panel.add(button);
    }

    private void addPatient() {
        String name = JOptionPane.showInputDialog("Enter patient's name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter patient's phone number:");
        String address = JOptionPane.showInputDialog("Enter patient's address:");
        patients.add(new Patient(name, phoneNumber, address));
    }

    private void addDoctor() {
        String name = JOptionPane.showInputDialog("Enter doctor's name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter doctor's phone number:");
        doctors.add(new Doctor(name, phoneNumber));
    }

    private void addNurse() {
        String name = JOptionPane.showInputDialog("Enter nurse's name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter nurse's phone number:");
        nurses.add(new Nurse(name, phoneNumber));
    } // add a method to add a nurse

    private void processPayment() {
        String name = JOptionPane.showInputDialog("Enter patient's name:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter payment amount:"));
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patient.addPayment(amount);
                JOptionPane.showMessageDialog(this, "Payment processed for " + name);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Patient not found!");
    }

    private String formatCurrency(double amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }

    private void printAll() {
        StringBuilder output = new StringBuilder("Patients:\n");
        for (Patient patient : patients) {
            output.append("Name: ").append(patient.getName()).append(", Phone Number: ").append(patient.getPhoneNumber()).append(", Due Payment: ").append(formatCurrency(patient.getDuePayment())).append("\n");
        }
        output.append("\nDoctors:\n");
        for (Doctor doctor : doctors) {
            output.append("Name: ").append(doctor.getName()).append(", Phone Number: ").append(doctor.getPhoneNumber()).append("\n");
        }
        output.append("\nNurses:\n");
        for (Nurse nurse : nurses) {
            output.append("Name: ").append(nurse.getName()).append(", Phone Number: ").append(nurse.getPhoneNumber()).append("\n");
        }
        outputArea.setText(output.toString());
    } // add a method to print all nurses

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalManagementSystemGUI hospitalGUI = new HospitalManagementSystemGUI();
            hospitalGUI.setSize(800, 600);
            hospitalGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            hospitalGUI.setVisible(true);
        }); // invokeLater() is used to ensure thread safety
    }
}
