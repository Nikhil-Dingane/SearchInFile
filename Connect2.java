import java.sql.*;
import com.mysql.jdbc.exceptions.jdbc4.*;

class Connect2
{
    public static void main(String args[])
    {
		try
		{
			String dbUrl="jdbc:mysql://localhost:3306/Demo";
			String username="root";
			String password="";
			Class.forName("com.mysql.jdbc.Driver");
			Connection myconnection=DriverManager.getConnection(dbUrl,username,password);
			myconnection.setAutoCommit(false);
			
			Statement stmt=myconnection.createStatement();
			
			stmt.executeUpdate("INSERT INTO `WordCount`(`Word`, `Frequency`) VALUES ('nikhil',12)");
			stmt.executeUpdate("INSERT INTO `WordCount`(`Word`, `Frequency`) VALUES ('sri',34)");
			stmt.executeUpdate("INSERT INTO `WordCount`(`Word`, `Frequency`) VALUES ('new',36)");
			myconnection.commit();
		
			stmt.executeUpdate("UPDATE WordCount SET Frequency=Frequency+1 WHERE Word='nikhil'");
			myconnection.commit();
			
			ResultSet rs=stmt.executeQuery("select * from WordCount where Word='nikhil'");
			
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getInt(2));
			}
			
			myconnection.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
 }
	