import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class JTable_Filter extends javax.swing.JFrame {


    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton BackButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Users;
    private javax.swing.JTextField jText_Search;

    public JTable_Filter() {
        initComponents();
        findUsers();

    }



    public Connection getConnection()
    {
        Connection con = null;
        final String DB_URL = "jdbc:mysql://localhost/EmployeeManagement?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return con;
    }


    public ArrayList<Employee> ListUsers(String ValToSearch)
    {
        ArrayList<Employee> usersList = new ArrayList<Employee>();

        Statement st;
        ResultSet rs;

        try{
            Connection con = getConnection();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM `employee` WHERE  CONCAT(ID, Name, Address, Department, Phone, Salary, BloodGroup, Email) LIKE '%"+ValToSearch+"%' order by ID";
            rs = st.executeQuery(searchQuery);

            Employee user;

            while(rs.next())
            {
                user = new Employee(
                        rs.getString("ID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Department"),
                        rs.getString("Phone"),
                        rs.getString("Salary"),
                        rs.getString("BloodGroup"),
                        rs.getString("Email")


                );
                usersList.add(user);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return usersList;
    }

    public void findUsers()
    {
        ArrayList<Employee> users = ListUsers(jText_Search.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID","Name","Blood Group", "Phone","Department","Email","Address","Salary" });
        Object[] row = new Object[8];

        for(int i = 0; i < users.size(); i++)
        {
            row[0] = users.get(i).getID();
            row[1] = users.get(i).getName();
            row[2] = users.get(i).getSalary();//5
            row[3] = users.get(i).getPhone();
            row[4] = users.get(i).getDepartment();
            row[5] = users.get(i).getBlood_Group();//7
            row[6] = users.get(i).getAddress();
            row[7] = users.get(i).getEmail();//2,5



            model.addRow(row);
        }
        jTable_Users.setModel(model);

    }

    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton_Search = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jText_Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Users = new javax.swing.JTable();
        setTitle("Filter");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_Search.setText("Search");
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  dispose();
            }
        });

        jText_Search.setFont(new java.awt.Font("", 1, 18)); // NOI18N

        jTable_Users.setFont(new java.awt.Font("", 1, 14)); // NOI18N
        jTable_Users.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                        {null, null, null, null,null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"  , "Title 5", "Title 6", "Title 7", "Title 8"
                }
        ));
        jScrollPane1.setViewportView(jTable_Users);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jText_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton_Search)
                                                .addComponent(BackButton)
                                                .addGap(136, 136, 136))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29))))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton_Search)
                                        .addComponent(BackButton)
                                        .addComponent(jText_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }




    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {

        findUsers();

    }



    public static void main(String args[]) {
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


}
