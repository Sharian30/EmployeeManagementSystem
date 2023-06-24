import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends javax.swing.JFrame{
    private JButton addEmpInfoButton;
    private JButton deleteEmpInfoButton;
    private JButton viewSearchButton;
    private JButton updateEmpInfoButton;
    private JButton exitButton;
    private JButton backButton;
    private JPanel mainmenupanel;
    private JButton calculateSalaryButton;
    private JButton selectedInfoButton;
    public  MainMenu(JFrame parent){
        super(String.valueOf(parent));
        setTitle("Main Menu");
        setContentPane(mainmenupanel);
        setMinimumSize(new Dimension(590,474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
                LoginFormAdmin loginForm=new LoginFormAdmin(parent );
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
            }
        });

        addEmpInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
                Dashbord dsh = new Dashbord(parent);
            }
        });
        deleteEmpInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dispose();
                DeleteDetails deletedetails=new DeleteDetails(null);
            }
        });
        updateEmpInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   dispose();
                UpdateDetails updatedetails=new UpdateDetails(null );
            }
        });


        viewSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(JTable_Filter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(JTable_Filter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(JTable_Filter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(JTable_Filter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new JTable_Filter().setVisible(true);
                    }
                });
            }
        });
        calculateSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Calculation calculation=new Calculation(null );
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
    
        MainMenu mainmenu=new MainMenu(null);
    }
}
