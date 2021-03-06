import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
class WebQuestion extends JFrame implements ActionListener {
    JButton btn1, btn2;
    ButtonGroup bg;
    JLabel l;
    JRadioButton jb[] = new JRadioButton[5];
    int count = 0;
    PreparedStatement pst;
    int a = 0;
    public static ArrayList<Integer> Qid = new ArrayList<>();
    public static ArrayList<Integer> Questioner = new ArrayList<>();
    int marks = 0;

    public WebQuestion() {
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
        question();
        l.setBounds(30, 40, 500, 20);
        btn1.setBounds(100, 240, 100, 30);
        btn2.setBounds(270, 240, 100, 30);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        repaint();
        setResizable(false);
    }
    void questionset4() {
        jb[4].setSelected(true);
        try {
            PreparedStatement pst;
            ResultSet rs;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcqtestquestions", "root", "");
            String Web = "Web";
            String sql = "Select * from question where subject='" + Web + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt("q_id");
                Qid.add(a);
            }
            Collections.shuffle(Qid);
            for (int v = 0; v < 10; v++) {
                int a = Qid.get(v);
                Questioner.add(a);
            }
        }  catch (Exception e) {
            System.out.println("setnext\n" + e);
        }
    }

    void question() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcqtestquestions", "root", "");
            PreparedStatement pst;
            ResultSet rs;
            String Web = "Web";
            String sql = "SELECT * FROM `question` where 'subject' ='" + Web + "'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt("q_id");
                Qid.add(a);
            }
            System.out.println(Qid);
            Collections.shuffle(Qid);
            for (int v = 0; v < 10; v++) {
                int a = Qid.get(v);
                Questioner.add(a);
            }
            ArrayList<Integer> forloop=new ArrayList<>();
            for(int i=0;i<Questioner.size();i++){
                forloop.add(Questioner.get(i));
            }
            String sql1 = "select * from question where q_id='" + forloop.get(count) + "'";
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
                    marks++;
                }
            }
        }
        catch (Exception e) {
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            count++;
            question();
            questionset4();
            if (count == 10) {
                System.out.println("stop");
                btn1.setEnabled(false);
                btn2.setText("Result");

            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (jb[0].isSelected()) {
                if (marks == 10) {
                    marks = marks;
                } else
                    marks++;
            }
            JOptionPane.showMessageDialog(this, "correct ans=" + marks);
        }
    }
}