import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFormAdmin extends JDialog{
    private JPanel AdminPanel;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnCancel;

    public LoginFormAdmin(JFrame parent){
        super(parent);
        setTitle("Login Admin");
        setContentPane(AdminPanel);
        setMinimumSize(new Dimension(490,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                admin = getAuthenticatedAdmin(email,password);
                if(admin != null){
                    dispose();
                    MainMenu mn = new MainMenu(parent);
                }
                else {
                    JOptionPane.showMessageDialog(LoginFormAdmin.this,"Email Or Password Invalid","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationAdmin reg = new RegistrationAdmin(parent);
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

    public Admin admin;
    private Admin getAuthenticatedAdmin(String email,String password){
        Admin admin = null;
        final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM admin WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
               admin = new Admin();
                admin.Name = resultSet.getString("Name");
                admin.Email = resultSet.getString("Email");
                admin.Password = resultSet.getString("Password");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static void main(String[] args) {
      LoginFormAdmin loginFormAdmin = new LoginFormAdmin(null);
      Admin admin = loginFormAdmin.admin;
      if (admin != null){
          JOptionPane.showMessageDialog(null,"Successful Authentication of "+admin.Name,"Successfully Login",JOptionPane.INFORMATION_MESSAGE);

          System.out.println("Successful Authentication of "+admin.Name);
          System.out.println("    Email: "+ admin.Email);
      }
      else {
          JOptionPane.showMessageDialog(null,"Authentication Canceled","Canceled Login",JOptionPane.INFORMATION_MESSAGE);

          System.out.println("Authentication Canceled");
      }
    }
}
