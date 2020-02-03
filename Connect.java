import java.sql.*;

class Connect
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
			int i=1;
			PreparedStatement pstmt=myconnection.prepareStatement("insert into Student(Roll_No,Name) values(?,?)");
			
			long endTime;
			long startTime;
		
			startTime=System.nanoTime();
			
			for(i=0;i<=1000;i++)
			{
				pstmt.setInt(1,i);
				pstmt.setString(2,"Krishagni");
				pstmt.execute();
			}				
			
			endTime=System.nanoTime();
			System.out.println("Required time for insertion in inbuilt vector is : "+ (endTime-startTime)/1000000000+" seconds");
			
			myconnection.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
 }
	