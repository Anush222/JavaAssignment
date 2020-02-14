import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

public class StudentRegistration extends JFrame implements ActionListener {
    JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    JTextField txtf1, txtf2, txtf3, txtf5, txtf6, txtf7;
    JButton butn1, butn2;
    String sql;
    Databaseconnection dbc = new Databaseconnection();

    StudentRegistration() {
        setVisible(true);
        setSize(800,500);
        setLayout(null);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Registration Form ");
        lbl1 = new JLabel("Signup here:");
        lbl1.setFont(new Font("Serif", Font.BOLD, 20));
        lbl1.setForeground(Color.decode("#99b433"));

        lbl2 = new JLabel("First Name:");
        lbl2.setForeground(Color.decode("#2c3e50"));

        lbl3 = new JLabel("Last Name:");
        lbl3.setForeground(Color.decode("#2c3e50"));

        lbl4 = new JLabel("Collage Name:");
        lbl4.setForeground(Color.decode("#2c3e50"));

        lbl5 = new JLabel("Country:");
        lbl5.setForeground(Color.decode("#2c3e50"));

        lbl6 = new JLabel("Email:");
        lbl6.setForeground(Color.decode("#2c3e50"));

        lbl7 = new JLabel("Phone No:");
        lbl7.setForeground(Color.decode("#2c3e50"));


        txtf1 = new JTextField();
        txtf1.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        txtf2 = new JTextField();
        txtf2.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        txtf3 = new JTextField();
        txtf3.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        txtf5 = new JTextField();
        txtf5.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        txtf6 = new JTextField();
        txtf6.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        txtf7 = new JTextField();
        txtf7.setBorder(BorderFactory.createLineBorder(Color.decode("#763626"), 2));

        butn1 = new JButton("Submit");
        butn2 = new JButton("Clear");
        butn1.addActionListener(this);
        butn2.addActionListener(this);
        butn1.setBackground(Color.decode("#FB6542"));
        butn2.setBackground(Color.decode("#375E97"));
        lbl1.setBounds(100, 30, 400, 30);
        lbl2.setBounds(80, 70, 200, 30);
        lbl3.setBounds(80, 110, 200, 30);
        lbl4.setBounds(80, 150, 200, 30);
        lbl5.setBounds(80, 190, 200, 30);
        lbl6.setBounds(80, 230, 200, 30);
        lbl7.setBounds(80, 270, 200, 30);
        txtf1.setBounds(300, 70, 200, 30);
        txtf2.setBounds(300, 110, 200, 30);
        txtf3.setBounds(300, 150, 200, 30);
        txtf5.setBounds(300, 190, 200, 30);
        txtf6.setBounds(300, 230, 200, 30);
        txtf7.setBounds(300, 270, 200, 30);
        butn1.setBounds(50, 350, 100, 30);
        butn2.setBounds(170, 350, 100, 30);
        add(lbl1);
        add(lbl2);
        add(txtf1);
        add(lbl3);
        add(txtf2);
        add(lbl4);
        add(txtf3);
        add(lbl5);
        add(lbl6);
        add(txtf5);
        add(lbl7);

        add(txtf6);
        add(txtf7);
        add(butn1);
        add(butn2);
        repaint();
        setResizable(false);
        getContentPane().setBackground(Color.decode("#e3e0cf"));;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            if (txtf1.getText().isEmpty() || txtf2.getText().isEmpty() || txtf3.getText().isEmpty() || txtf5.getText().isEmpty() || txtf6.getText().isEmpty() || txtf7.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please field all fields");
            }
            else {
                String first_name = txtf1.getText();
                String last_name = txtf2.getText();
                String collage = txtf3.getText();
                String country = txtf5.getText();
                String email = txtf6.getText();
                String phone = txtf6.getText();
                Random rand = new Random();
                int num = rand.nextInt(10000);
                String username = first_name + num;
                int num1 = rand.nextInt(20000);
                String password = last_name + num1;
                try {

                    sql = "insert into registration values(null,'" + first_name + "','" + last_name + "','" + collage + "','" + country + "','" + email + "','" + phone + "','" + username + "','" + password + "')";
                    int row = dbc.st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "username: " + username + "password:-" + password);
                    cleartext();
                    new Loginpage();
                } catch (SQLException ex) {
                    System.out.println("Database Error");
                }
            }
        }
        else if (e.getSource() == butn2) {
            String def = "";
            txtf1.setText(def);
            txtf2.setText(def);
            txtf3.setText(def);
            txtf5.setText(def);
            txtf6.setText(def);
            txtf7.setText(def);

        }

    }
    void cleartext() {
        txtf1.setText("");
        txtf2.setText("");
        txtf3.setText("");
        txtf5.setText("");
        txtf6.setText("");
        txtf7.setText("");
    }
    public static void main(String[] args) {
        new StudentRegistration();
    }

}


