import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminPannel implements ActionListener {

    JButton Add, Reset, showQuestion;
    JTextField questiontxt, righttxt, wrongtxt1, wrongtxt2, wrongtxt3;
    JLabel question, right, wrong1, wrong2, wrong3, TitleLab, subjectlbl, title;
    JFrame frame;
    String subject[] = {"java", "ECS", "Ethics", "Web"};
    JComboBox combo;
    String query;
    Databaseconnection dbc = new Databaseconnection();


    AdminPannel() {

        frame = new JFrame();
        combo = new JComboBox(subject);
        combo.setBounds(130, 60, 150, 30);
        frame.add(combo);
        frame.setTitle("Add Questions");
        frame.setTitle("Admin Page");


        question = new JLabel("Question Here");
        question.setBounds(10, 150, 100, 30);
        question.setFont(new Font("Serif", Font.BOLD, 15));
        question.setForeground(Color.decode("#99b433"));
        frame.add(question);

        questiontxt = new JTextField();
        questiontxt.setBounds(130, 150, 540, 30);
        questiontxt.setFont(new Font("Serif", Font.BOLD, 15));
        questiontxt.setBorder(BorderFactory.createLineBorder(Color.decode("#0084ff"), 2));
        frame.add(questiontxt);

        right = new JLabel("Right Answer");
        right.setBounds(10, 200, 100, 30);
        right.setFont(new Font("Serif", Font.BOLD, 15));
        right.setForeground(Color.decode("#99b433"));
        frame.add(right);


        righttxt = new JTextField();
        righttxt.setBounds(130, 200, 200, 30);
        righttxt.setFont(new Font("Serif", Font.BOLD, 15));
        righttxt.setBorder(BorderFactory.createLineBorder(Color.decode("#0084ff"), 2));

        frame.add(righttxt);

        wrong1 = new JLabel("Wrong Answer");
        wrong1.setBounds(350, 200, 200, 30);
        wrong1.setFont(new Font("Serif", Font.BOLD, 15));
        wrong1.setForeground(Color.decode("#99b433"));

        frame.add(wrong1);


        wrongtxt1 = new JTextField();
        wrongtxt1.setBounds(470, 200, 200, 30);
        wrongtxt1.setFont(new Font("Serif", Font.BOLD, 15));
        wrongtxt1.setBorder(BorderFactory.createLineBorder(Color.decode("#0084ff"), 2));

        frame.add(wrongtxt1);

        wrong2 = new JLabel("Wrong Answer");
        wrong2.setBounds(10, 250, 100, 30);
        wrong2.setFont(new Font("Serif", Font.BOLD, 15));
        wrong2.setForeground(Color.decode("#99b433"));

        frame.add(wrong2);


        wrongtxt2 = new JTextField();
        wrongtxt2.setBounds(130, 250, 200, 30);
        wrongtxt2.setFont(new Font("Serif", Font.BOLD, 15));
        // wrongtxt2.setForeground(Color.decode("#99b433"));
        wrongtxt2.setBorder(BorderFactory.createLineBorder(Color.decode("#0084ff"), 2));
        frame.add(wrongtxt2);

        wrong3 = new JLabel("Wrong Answer");
        wrong3.setBounds(350, 250, 200, 30);
        wrong3.setFont(new Font("Serif", Font.BOLD, 15));
        wrong3.setForeground(Color.decode("#99b433"));

        frame.add(wrong3);


        wrongtxt3 = new JTextField();
        wrongtxt3.setBounds(470, 250, 200, 30);
        wrongtxt3.setFont(new Font("Serif", Font.BOLD, 15));
        wrongtxt3.setBorder(BorderFactory.createLineBorder(Color.decode("#0084ff"), 2));
        frame.add(wrongtxt3);

        Add = new JButton("Add");
        Add.setFont(new Font("Serif", Font.BOLD, 20));
        Add.setBackground(Color.decode("#006266"));

        Add.setBounds(130, 350, 200, 40);
        Add.addActionListener(this);
        frame.add(Add);

        Reset = new JButton("Reset");
        Reset.setFont(new Font("Serif", Font.BOLD, 20));
        Reset.setBackground(Color.decode("#009432"));

        Reset.setBounds(470, 350, 200, 40);
        frame.add(Reset);

        title = new JLabel("View Modules Questions");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.decode("#99b433"));

        title.setBounds(200, 400, 400, 100);
        frame.add(title);

        showQuestion = new JButton("Show Question");
        showQuestion.setBounds(270, 500, 150, 30);
        showQuestion.setForeground(Color.decode("#99b433"));

        showQuestion.addActionListener(this);
        frame.add(showQuestion);
        showQuestion.setForeground(Color.decode("#FF420E"));

        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#e3e0cf"));
        frame.setResizable(false);

    }

    public static void main(String[] args) {

        new AdminPannel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            try {
                String question = questiontxt.getText();
                String rightans = righttxt.getText();
                String wrong1 = wrongtxt1.getText();
                String wrong2 = wrongtxt2.getText();
                String wrong3 = wrongtxt3.getText();
                String subject = combo.getItemAt(combo.getSelectedIndex()).toString();
                query = "insert into question VALUES(null,'" + question + "','" + rightans + "','" + wrong1 + "','" + wrong2 + "','" + wrong3 + "','" + subject + "')";
                int row = dbc.st.executeUpdate(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Sucessful");
            questiontxt.setText("");
            righttxt.setText("");
            wrongtxt1.setText("");
            wrongtxt2.setText("");
            wrongtxt3.setText("");

        }
        else if (e.getActionCommand().equals("Show Question")) {
         Subject sbj = new Subject();
         sbj.setVisible(true);
        }
    }
}
