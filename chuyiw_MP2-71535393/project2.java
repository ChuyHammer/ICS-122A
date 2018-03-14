package mini_project2;
import java.sql.*;

public class project2 {
	
	public static String changeName(String name) {
		String split_name[] = name.split(", ");
		String result = split_name[1] + " " + split_name[0];
		return result;
	}
	
	public static void problem1(Connection conn) {
		try {
			System.out.println("question:");
			Statement s = conn.createStatement();
			s.executeQuery("SELECT name, A, B FROM question");
			ResultSet rs = s.getResultSet();
			
			int count = 0;
			while (rs.next()) {
				String nameVal = rs.getString("name");
				int AVal = rs.getInt("A");
				int BVal = rs.getInt("B");
				nameVal = changeName(nameVal);
				System.out.println(nameVal + ", " 
						+ AVal + ", " + BVal);
				++count;
		
			}
			rs.close();
			s.close();
			System.out.println(count + " rows were retrieved");
		}
		catch (Exception e) {
			System.err.println ("Error message: " + e.getMessage ());
		}
	}
	
	public static void problem2(Connection conn) {
		try {
			Statement s = conn.createStatement();
			
			s.executeQuery("SELECT name, A, B FROM question");
			ResultSet rs = s.getResultSet();
			
			
			while (rs.next()) {
				PreparedStatement s2;
				s2 = conn.prepareStatement (
				               "INSERT IGNORE INTO result(name, id2d, result) VALUES(?,?,?)");
				
				String nameVal = rs.getString("name");
				int AVal = rs.getInt("A");
				int BVal = rs.getInt("B");
				nameVal = changeName(nameVal);
				int result = AVal * BVal + 93;
				
				s2.setString(1, nameVal);
				s2.setInt(2, 93);
				s2.setInt(3, result);
				s2.executeUpdate();
				
				s2.close();
			}
			rs.close();
			s.close();
		}
		catch (Exception e) {
			System.err.println ("Error message: " + e.getMessage ());
		}
	}
	
	
	public static void problem3(Connection conn) {
		System.out.println("result:");
		try {
			Statement s = conn.createStatement();
			s.executeQuery("SELECT name, result FROM result");
			ResultSet rs = s.getResultSet();
			
			int count = 0;
			while (rs.next()) {
				String nameVal = rs.getString("name");
				int resultVal = rs.getInt("result");
				
				System.out.println(nameVal + ", " 
						+ resultVal);
				++count;
		
			}
			rs.close();
			s.close();
			System.out.println(count + " rows were retrieved");
		}
		catch (Exception e) {
			System.err.println ("Error message: " + e.getMessage ());
		}
	}
	public static void main (String[] args)
    {
        Connection conn = null;

        try
        {
        	String userName = "root";
        	String password = "Chuyi2015@";
            String url = "jdbc:mysql://localhost/sample_python?useSSL=true";
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
        	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
        	conn = DriverManager.getConnection (url, userName, password);
        	   
            System.out.println ("Database connection established");
            
            problem1(conn);
            System.out.println();
            
            problem2(conn);
            System.out.println();
            
            problem3(conn);
            System.out.println();
        }
        catch (Exception e)
        {
        	System.out.println(e);
            System.err.println ("Cannot connect to database server");
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }
}
