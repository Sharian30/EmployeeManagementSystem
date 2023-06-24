import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateDetails extends javax.swing.JFrame{
    private JTextField SearchbyIDtextField1;
    private JTextField EmployeeIDtextField1;
    private JTextField NametextField1;
    private JTextField EmailtextField1;
    private JTextField PhoneNotextField1;
    private JTextField DeptNametextField1;
    private JTextField SalarytextField1;
    private JTextField DistricttextField1;
    private JTextField BloodGrouptextField1;
    private JButton backButton;
    private JButton updateDetailsButton;

    private JPanel UpdateDetailsPanel;
    private JButton searchByEmpIDButton;

    public UpdateDetails(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Update Employee's Record");
        setContentPane(UpdateDetailsPanel);
        setMinimumSize(new Dimension(500,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Connect();
        searchByEmpIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    pst = con.prepareStatement("select * from employee where ID=?");
                    int id = Integer.parseInt(SearchbyIDtextField1.getText());
                    pst.setInt(1, id);
                    ResultSet rs1 = pst.executeQuery();
                    if(rs1.next()==false)
                    {

                        EmployeeIDtextField1.setText("");
                        NametextField1.setText("");
                        EmailtextField1.setText("");
                        PhoneNotextField1.setText("");
                        DeptNametextField1.setText("");
                        SalarytextField1.setText("");
                        DistricttextField1.setText("");
                        BloodGrouptextField1.setText("") ;
                    }
                    else
                    {

                        EmployeeIDtextField1.setText(rs1.getString("ID"));
                        NametextField1.setText(rs1.getString("Name"));
                        EmailtextField1.setText(rs1.getString("Email"));
                        PhoneNotextField1.setText(rs1.getString("Phone"));
                        DeptNametextField1.setText(rs1.getString("Department"));
                        SalarytextField1.setText(rs1.getString("Salary"));
                        DistricttextField1.setText(rs1.getString("Address"));
                        BloodGrouptextField1.setText(rs1.getString("BloodGroup"));
                    }

                } catch (SQLException ex) {

                }
            }
        });


        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
                MainMenu mainmenu=new MainMenu(null);
            }
        });


        updateDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID= EmployeeIDtextField1.getText();
                String Name=NametextField1.getText();
                String Email=EmailtextField1.getText();
                String Phone= PhoneNotextField1.getText();
                String Department= DeptNametextField1.getText();
                String Salary=SalarytextField1.getText();
                String Address=DistricttextField1.getText();
                String BloodGroup= BloodGrouptextField1.getText();
                int id = Integer.parseInt(SearchbyIDtextField1.getText());
                try
                {
                    pst = con.prepareStatement("UPDATE employee SET Name=?,Email=? ,Phone=?, Department=?,Salary=?,Address=?,BloodGroup=? where ID =?");

                    pst.setString(1,Name );
                    pst.setString(2,Email );
                    pst.setString(3,Phone );
                    pst.setString(4,Department );
                    pst.setString(5, Salary);
                    pst.setString(6,Address );
                    pst.setString(7,BloodGroup );
                    pst.setString(8,ID);

                    pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Record Updated");
                        EmployeeIDtextField1.setText("");
                        NametextField1.setText("");
                        EmailtextField1.setText("");
                        PhoneNotextField1.setText("");
                        DeptNametextField1.setText("");
                        SalarytextField1.setText("");
                        DistricttextField1.setText("");
                        BloodGrouptextField1.setText("");


                }
                catch (SQLException ex)
                {

                }
            }
        });

        setVisible(true);
    }

    private void setModal(boolean b) {
    }

    PreparedStatement pst;

    Connection con;
    final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }


    public static void main(String[] args) {

        UpdateDetails updatedetails=new UpdateDetails(null );
    }
}
