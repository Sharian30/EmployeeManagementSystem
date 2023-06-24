import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationEmployee extends JDialog {
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfDepartment;
    private JTextField tfPhone;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private JTextField tfSalary;
    private JTextField tfBlood;

    public RegistrationEmployee(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500,550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerEmployee();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerEmployee() {
        String Name = tfName.getText();
        String Address = tfAddress.getText();
        String Department = tfDepartment.getText();
        String Phone = tfPhone.getText();
        String Email = tfEmail.getText();
        String Salary = tfSalary.getText();
        String BloodGroup = tfBlood.getText();
        String Password = String.valueOf(pfPassword.getPassword());
        String ConfirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (Name.isEmpty() || Address.isEmpty()|| BloodGroup.isEmpty() || Department.isEmpty() || Salary.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Password.equals(ConfirmPassword)){
            JOptionPane.showMessageDialog(this,"Confirm Password does not match","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        employee = addEmployeeToDatabase(Name,Address,Department,Phone,Salary,BloodGroup,Email,Password);

        if (employee != null){
            JOptionPane.showMessageDialog(null,"Your registration successfully completed.", "Congratulations",JOptionPane.INFORMATION_MESSAGE );
            dispose();
            LoginFormEmployee lg = new LoginFormEmployee(null);

        }
    }
    public Employee employee;
    private Employee addEmployeeToDatabase(String Name, String Address,String Department, String Phone,String Salary, String BloodGroup,String Email, String Password){
        Employee employee = null;
        final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "Insert INTO employee(Name,Address,Department,Phone,Salary,BloodGroup,Email,Password)" + "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,Address);
            preparedStatement.setString(3,Department);
            preparedStatement.setString(4,Phone);
            preparedStatement.setString(5,Salary);
            preparedStatement.setString(6,BloodGroup);
            preparedStatement.setString(7,Email);
            preparedStatement.setString(8,Password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                employee = new Employee();
                employee.Name = Name;
                employee.Address = Address;
                employee.Department = Department;
                employee.Phone = Phone;
                employee.Salary = Salary;
                employee.BloodGroup = BloodGroup;
                employee.Email = Email;
                employee.Password = Password;
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return employee;
    }


    public static void main(String[] args) {
        RegistrationEmployee myForm = new RegistrationEmployee(null);
        Employee employee = myForm.employee;

        if (employee != null){

            System.out.println("Successfully complete "+ employee.Name +"'s registration");
        }
        else{
            System.out.println("Registration processes canceled ");
        }
    }

}
