import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingDao
{
	private static String url = "jdbc:oracle:thin:@usfdbjava.cvkuvhta6l54.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String connection_username = "patrick";
	private static final String connection_password = "pass1234";
	
	
	public void initDataBase() 
	{
		try (Connection con = DriverManager.getConnection(url, connection_username, connection_password);)
		{    
			String sql = "{call init()}"; 
			CallableStatement cs = con.prepareCall(sql); 
			int state = cs.executeUpdate(); 
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
