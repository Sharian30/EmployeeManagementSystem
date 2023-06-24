import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationAdmin extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel regAdminPanel;
    public RegistrationAdmin(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(regAdminPanel);
        setMinimumSize(new Dimension(630,510));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            registerAdmin();    
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

    private void registerAdmin() {
        String Name = tfName.getText();
        String Email = tfEmail.getText();
        String Password = String.valueOf(pfPassword.getPassword());
        String ConfirmPassword = String.valueOf(pfConPassword.getPassword());

        if(Name.isEmpty() || Email.isEmpty() || Password.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Password.equals(ConfirmPassword)){
            JOptionPane.showMessageDialog(this,"Confirm Password does not match","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        admin = addAdminToDatabase(Name,Email,Password);

        if (admin != null){
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,"Failed to register","Try again",JOptionPane.ERROR_MESSAGE);

        }
    }
    public Admin admin;

    private Admin addAdminToDatabase(String Name, String Email, String Password){
        Admin employee = null;
        final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "Insert INTO admin(Name,Email,Password)" + "VALUES(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,Email);
            preparedStatement.setString(3,Password);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                admin = new Admin();
                admin.Name = Name;
                admin.Email = Email;
                admin.Password = Password;
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return admin;
    }


    public static void main(String[] args) {
        RegistrationAdmin myForm = new RegistrationAdmin(null);
        Admin admin = myForm.admin;
        if (admin != null){
            JOptionPane.showMessageDialog(null,"Your registration successfully completed.", "Congratulations",JOptionPane.INFORMATION_MESSAGE );

            System.out.println("Successfully complete "+ admin.Name +"'s registration");
        }
        else{
            System.out.println("Registration processes canceled ");
        }

    }
}
