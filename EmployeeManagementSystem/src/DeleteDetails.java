import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteDetails extends javax.swing.JFrame{
    private JTextField EmployeeidtextField1;
    private JTextField Searchbyemp_idtextField1;
    private JTextField NametextField1;
    private JTextField EmailtextField1;
    private JTextField PhoneNotextField1;
    private JTextField DeptNametextField1;
    private JTextField SalarytextField1;
    private JTextField DistricttextField1;
    private JTextField BloodGrouptextField1;
    private JButton deleteDetailsButton;
    private JButton backButton;
    private JButton searchForDeleteButton;
    private JPanel DeletedetailsPanel;


    public DeleteDetails(JFrame parent) {
        super();
        setTitle("Delete Employee's Record");
        setContentPane(DeletedetailsPanel);
        setMinimumSize(new Dimension(650,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Connect();
        searchForDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    try{
                        pst = con.prepareStatement("select * from employee where ID=?");
                        int id = Integer.parseInt(Searchbyemp_idtextField1.getText());
                        pst.setInt(1, id);
                        ResultSet rs1 = pst.executeQuery();
                        if(rs1.next()==false)
                        {
                            EmployeeidtextField1.setText("");
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

                            EmployeeidtextField1.setText(rs1.getString("ID"));
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
        deleteDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(Searchbyemp_idtextField1.getText());
                    
                    pst = con.prepareStatement("delete from employee where ID =?");
                    pst.setInt(1, id);
                    int k = pst.executeUpdate();

                    if(k==1)
                    {

                        EmployeeidtextField1.setText("");
                        NametextField1.setText("");
                        EmailtextField1.setText("");
                        PhoneNotextField1.setText("");
                        DeptNametextField1.setText("");
                        SalarytextField1.setText("");
                        DistricttextField1.setText("");
                        BloodGrouptextField1.setText("");


                    }
                    else
                    {

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

        setVisible(true);


    }

    private void setModal(boolean b) {
    }


    PreparedStatement pst;

       Connection con;
    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC", "root", "");

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }
        public static void main(String[] args) {

        DeleteDetails deletedetails=new DeleteDetails(null);
    }

}
