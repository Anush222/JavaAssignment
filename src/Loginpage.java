import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Loginpage extends JFrame implements ActionListener {
    JTextField text_username;
    JLabel label_username, label_password;
    JPasswordField passwordField;
    JButton button_login,button_cancel, button_signup;
    String email, password, query;

    public static void main(String[] args) {
        new Loginpage().setVisible(true);
    }

    public Loginpage() {
        setTitle("Login");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(500, 100, 600, 400);
        setLayout(null);
        label_username = new JLabel("UserName");
        label_username.setForeground(Color.decode("#FC4445"));
        text_username = new JTextField();
        text_username.setBorder(BorderFactory.createLineBorder(Color.decode("#000075"),1));
        text_username.setColumns(18);
        label_password = new JLabel("Password");
        label_password.setForeground(Color.decode("#FC4445"));
        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#000075"),1));
        passwordField.setColumns(18);
        label_username.setBounds(5, 10, 150, 20);
        passwordField.setBounds(100, 50, 150, 20);
        text_username.setBounds(100, 10, 150, 20);
        label_password.setBounds(5, 50, 150, 20);
        add(label_username);
        add(text_username);
//        add(text_password);
        add(label_password);
        add(passwordField);
        button_login = new JButton("Login");
        button_cancel = new JButton("Cancel");
        button_signup = new JButton("Sign Up");
        button_login.setBounds(5, 130, 80, 20);
        button_cancel.setBounds(90, 130, 100, 20);
        button_signup.setBounds(205, 130, 140, 20);
        add(button_login);
        button_login.addActionListener(this);
        add(button_cancel);
        button_cancel.addActionListener(this);
        add(button_signup);
        button_signup.addActionListener(this);
        getContentPane().setBackground( Color.decode("#FFD8B1"));
        button_login.setBackground(Color.decode("#97CAEF"));
        button_cancel.setBackground(Color.decode("#CAFAFE"));
        button_signup.setBackground(Color.decode("#C2CAD0"));
        setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String lg = e.getActionCommand();
        if (lg == "Login") {
            try {
                Databaseconnection dcon = new Databaseconnection();
                email = text_username.getText();
                password = passwordField.getText();

                if (email.equals("admin")) {
                    if (password.equals("admin")) {
                        new AdminPannel();
                    }
                    else {
                        JOptionPane.showInputDialog("For Admin Only");
                    }
                } else {
                    query = "select * from  registration where username='" + email + "'";
                    dcon.rs = dcon.st.executeQuery(query);
                    dcon.rs.next();
                    String Password = dcon.rs.getString(9);
                    if (password.equals(Password)) {
                      new StudentPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Doesn't match");
                    }

                }
            } catch (Exception ex) {

            }
        }
        if (e.getSource().equals(button_signup)) {
            StudentRegistration su = new StudentRegistration();
            su.setVisible(true);
        }
    }
}
