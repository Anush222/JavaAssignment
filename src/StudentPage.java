import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class StudentPage extends JFrame implements ActionListener {
    JLabel label3;
    JButton button1, button2, button3,button4,btn5;
    StudentPage() {
        setVisible(true);
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Student Dash");
        label3 = new JLabel("Choose your subject below:");
        label3.setFont(new Font("Serif", Font.BOLD, 20));
        button1 = new JButton("Java");
        button2 = new JButton("ECS");
        button3=new JButton("Ethics");
        button4=new JButton("Web");
        btn5=new JButton("Cancel");
        label3.setBounds(80, 60, 600, 30);
        button1.setBounds(50, 130, 100, 30);
        button2.setBounds(50, 170, 100, 30);
        button3.setBounds(280, 130, 100, 30);
        button4.setBounds(280, 170, 100, 30);
        btn5.setBounds(160, 150, 100, 30);
        add(label3);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(btn5);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        btn5.addActionListener(this);

    }
    public static void main(String[] args) {

        new StudentPage().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(button1)) {
            JavaQuestion java = new JavaQuestion();
            java.setVisible(true);
        }
        if (e.getSource().equals(button2)) {
            ECSQuestion ecsq = new ECSQuestion();
            ecsq.setVisible(true);
        }
        if (e.getSource().equals(button3)) {
            EthicsQuestion etq = new EthicsQuestion();
            etq.setVisible(true);
        }
        if (e.getSource().equals(button4)) {
            WebQuestion wbq = new WebQuestion();
            wbq.setVisible(true);
        }
        if (e.getSource().equals(btn5)) {
            dispose();
        }
    }
}
