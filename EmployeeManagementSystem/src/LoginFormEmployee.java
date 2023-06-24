import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFormEmployee extends JDialog{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnRegistration;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginFormEmployee(JFrame parent){
      super(parent);
        setTitle("User Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(490,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String email = tfEmail.getText();
               String password = String.valueOf(pfPassword.getPassword());

             employee = getAuthenticatedUser(email,password);
             if(employee != null){
                 dispose();
             }
             else {
                 JOptionPane.showMessageDialog(LoginFormEmployee.this,"Email Or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
             }
            }
        });

        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              RegistrationEmployee reg = new RegistrationEmployee(parent);

             // dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             dispose();
             Dashbord dash = new Dashbord(parent);
            }
        });
        setVisible(true);

    }

    public Employee employee;
    private Employee getAuthenticatedUser(String email,String password){
        Employee employee = null;

        final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM employee WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                employee = new Employee();
                employee.Name = resultSet.getString("Name");
                employee.Address = resultSet.getString("Address");
                employee.Department = resultSet.getString("Department");
                employee.Phone = resultSet.getString("Phone");
                employee.Email = resultSet.getString("Email");
                employee.Password = resultSet.getString("Password");

            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }


    public static void main(String[] args) {
        LoginFormEmployee loginForm = new LoginFormEmployee(null);
        Employee employee = loginForm.employee;
        if (employee != null){
            JOptionPane.showMessageDialog(null,"Successful Authentication of "+employee.Name,"Successfully Login",JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Successful Authentication of "+employee.Name);
            System.out.println("    Address: "+employee.Address);
            System.out.println("    Department: "+employee.Department);
            System.out.println("    Phone: "+employee.Phone);
            System.out.println("    Email: "+ employee.Email);
        }
        else {
            JOptionPane.showMessageDialog(null,"Authentication Canceled","Canceled Login",JOptionPane.INFORMATION_MESSAGE);

            System.out.println("Authentication Canceled");
        }
    }
}
