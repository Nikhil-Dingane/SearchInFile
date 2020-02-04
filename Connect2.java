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
			
			PreparedStatement pstmt=myconnection.prepareStatement("INSERT INTO `WordCount`(`Word`, `Frequency`) VALUES (?,?)");
			
			Statement stmt=myconnection.createStatement();
			try
			{
				stmt.executeUpdate("CREATE TABLE WordCount(Word varchar(255),Frequency integer, UNIQUE KEY(Word));");
			}
			catch(MySQLSyntaxErrorException e)
			{
				stmt.executeUpdate("truncate table WordCount");
				System.out.println("Table is already exist");
			}
			myconnection.commit();
			for(int i=0;i<=100;i++)
			{
				pstmt.setInt(2,i);
				pstmt.setString(1,"Krishagni"+i);
				pstmt.execute();
			}	
			
			myconnection.commit();
			//stmt.executeUpdate("INSERT INTO `WordCount`(`Word`, `Frequency`) VALUES ('dfasd',2)");	
			
			myconnection.commit();
			myconnection.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
 }
	