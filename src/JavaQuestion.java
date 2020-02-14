import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

class JavaQuestion extends JFrame implements ActionListener {
    JButton btn1, btn2;
    ButtonGroup bg;
    JLabel l;
    JRadioButton jb[] = new JRadioButton[5]; //for options of the question
    int counter = 0;
    int Scores = 0;
    public static ArrayList<Integer> Question_id = new ArrayList<>();
    public static ArrayList<Integer> Quest = new ArrayList<>();

    public JavaQuestion() {
        l = new JLabel();
        add(l);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        setTitle("Papers");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        btn1 = new JButton("Next");
        btn2 = new JButton("Result");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        add(btn1);
        add(btn2);
        questionanswers();
        l.setBounds(30, 40, 500, 20);  //labels question area
        btn1.setBounds(100, 240, 100, 30);
        btn2.setBounds(270, 240, 100, 30);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }
    void JAVASET() {
        jb[4].setSelected(true);
        try {
            PreparedStatement pst;
            ResultSet rs;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcqtestquestions", "root", "");
            String java = "java";
            String sql = "Select * from question where subject='" + java + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt("q_id");
                Question_id.add(a);
            }
            Collections.shuffle(Question_id);
            for (int v = 0; v < 10; v++) {
                int a = Question_id.get(v);
                Quest.add(a);
            }
        } catch (Exception e) {
            System.out.println("setnext\n" + e);
        }
    }

    void questionanswers() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcqtestquestions", "root", "");
            PreparedStatement pst;
            ResultSet rs;
            String java = "java";
            String sql = "Select * from question where subject='" + java + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt("q_id");
                Question_id.add(a);
            }
            System.out.println(Question_id);
            Collections.shuffle(Question_id);
            for (int v = 0; v < 10; v++) {
                int a = Question_id.get(v);
                Quest.add(a);
            }
            ArrayList<Integer> forloop = new ArrayList<>();
            for (int i = 0; i < Quest.size(); i++) {
                forloop.add(Quest.get(i));
            }
            String sql1 = "select * from question where q_id='" + forloop.get(counter) + "'";
            pst = con.prepareStatement(sql1);
            ResultSet rs1 = pst.executeQuery(sql1);
            while (rs1.next()) {
                String s1 = rs1.getString("question");
                String s2 = rs1.getString("rightans");
                String s3 = rs1.getString("wrong1");
                String s4 = rs1.getString("wrong2");
                String s5 = rs1.getString("wrong3");
                l.setText("Q: " + s1);
                jb[0].setText(s2);
                jb[1].setText(s3);
                jb[2].setText(s4);
                jb[3].setText(s5);
                for (int z = 0, j = 0; z <= 90; z += 30, j++) {
                    jb[j].setBounds(50, 80 + z, 400, 20);
                }
                if (jb[0].isSelected()) {
                    Scores++;
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            counter++;
            questionanswers();
            JAVASET();
            if (counter == 10) {
                System.out.println("stop");
                btn1.setEnabled(false);
                btn2.setText("Result");

            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (jb[0].isSelected()) {
                if (Scores == 10) {
                    Scores = Scores;
                } else
                    Scores++;
            }
            JOptionPane.showMessageDialog(this, "correct ans=" + Scores);
        }
    }
}