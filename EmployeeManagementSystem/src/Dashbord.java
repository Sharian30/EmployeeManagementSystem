import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashbord extends JDialog{
    private JPanel dashpanel;
    private JButton btnEmployee;
    private JButton btnAdmin;

public Dashbord(JFrame parent){
    super(parent);
    setTitle("Dash Board");
    setContentPane(dashpanel);
    setMinimumSize(new Dimension(650,600));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    btnEmployee.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            dispose();
            LoginFormEmployee lgE = new LoginFormEmployee(parent);
        }
    });
    btnAdmin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

           dispose();
            LoginFormAdmin lgA = new LoginFormAdmin(parent);
        }
    });
    setVisible(true);
}

    public static void main(String[] args) {
       Dashbord dashbord = new Dashbord(null);
    }

}