
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


class Subject extends JFrame {

    static JTable table;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                                   public void run() {
                                       Subject frame = new Subject();
                                       frame.setVisible(true);

                                   }
                               }
        );
    }
    public Subject() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 1000, 502);
        setTitle("Show Question");
        getContentPane().setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 65, 900, 350);
        getContentPane().add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);
        Questions();
    }
    private static void Questions() {
        table.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("question");
        model.addColumn("rightans");
        model.addColumn("wrong1");
        model.addColumn("wrong2");
        model.addColumn("wrong3");
        model.addColumn("q_id");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcqtestquestions","root","");
            Statement s = con.createStatement();
            String sql = "SELECT * FROM  question ORDER BY subject ASC";
            ResultSet rs = s.executeQuery(sql);
            int row = 0;
            while ((rs != null) && (rs.next())) {
                model.addRow(new Object[0]);
                model.setValueAt(rs.getString("question"), row, 0);
                model.setValueAt(rs.getString("rightans"), row, 1);
                model.setValueAt(rs.getString("wrong1"), row, 2);
                model.setValueAt(rs.getString("wrong2"), row, 3);
                model.setValueAt(rs.getString("wrong3"), row, 4);
                model.setValueAt(rs.getString("q_id"), row, 5);
                row++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
}
