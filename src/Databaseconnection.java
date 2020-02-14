
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Databaseconnection {
    public java.sql.Connection con;
    public java.sql.ResultSet rs;
    public java.sql.Statement st;
    public static void main(String[] args) {
        new Databaseconnection();
    }

    public Databaseconnection() {
        try{
            String url="jdbc:mysql://localhost:3306/mcqtestquestions";
            String userName="root";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,userName,"");
            st=con.createStatement();
        }catch(Exception e){}
    }
    public void close()
    {
        try{
            con.close();
            rs.close();
        }catch(Exception e){}
    }
    public void delete(int id){
        try{
            String sql="DELETE FROM question WHERE q_id='"+id+"'";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




