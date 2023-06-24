import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Calculation extends javax.swing.JFrame {
    private JButton totalEmployeesButton;
    private JButton avgSalaryButton;
    private JButton totalSalaryButton;
    private JButton maxSalaryButton;
    private JButton minSalaryButton;
    private JTextField AnstextField1;
    private JPanel Calculationpanel;
    private JButton backButton;

    public Calculation(JFrame parent) {
        super(String.valueOf(parent));
        setTitle("Calculation");
        Container UpdateDetailsPanel;
        Container CalculationPanel;
        setContentPane(Calculationpanel);
        setMinimumSize(new Dimension(450,474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Connect();



        totalEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    pst = con.prepareStatement("select count(ID) from employee where 1");
                    ResultSet rs = pst.executeQuery();
                    if(rs.next())
                    {

                        AnstextField1.setText(rs.getString(1));
                        
                    }
                    else
                    {

                        JOptionPane.showMessageDialog(null, "Sorry Data Not Found");
                    }

                } catch (SQLException ex) {

                }
            }
        });

        totalSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    pst = con.prepareStatement("select sum(Salary) from employee where 1");
                    ResultSet rs = pst.executeQuery();
                    if(rs.next())
                    {

                        AnstextField1.setText(rs.getString(1));

                    }
                    else
                    {

                        JOptionPane.showMessageDialog(null, "Sorry Data Not Found");
                    }

                } catch (SQLException ex) {

                }
            }
        });

        avgSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    pst = con.prepareStatement("select CAST(AVG(Salary) AS DECIMAL(10,2)) from employee where 1");
                    ResultSet rs = pst.executeQuery();
                    if(rs.next())
                    {

                        AnstextField1.setText(rs.getString(1));

                    }
                    else
                    {

                        JOptionPane.showMessageDialog(null, "Sorry Data Not Found");
                    }

                } catch (SQLException ex) {

                }
            }
        });

        maxSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    pst = con.prepareStatement("select max(Salary)  from employee where 1");
                    ResultSet rs = pst.executeQuery();
                    if(rs.next())
                    {

                        AnstextField1.setText(rs.getString(1));

                    }
                    else
                    {

                        JOptionPane.showMessageDialog(null, "Sorry Data Not Found");
                    }

                } catch (SQLException ex) {

                }
            }
        });


        minSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    pst = con.prepareStatement("select min(Salary)  from employee where 1");
                    ResultSet rs = pst.executeQuery();
                    if(rs.next())
                    {

                        AnstextField1.setText(rs.getString(1));

                    }
                    else
                    {

                        JOptionPane.showMessageDialog(null, "Sorry Data Not Found");
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

        Calculation calculation=new Calculation(null );
    }

}
