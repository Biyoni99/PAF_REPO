package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funding {

	private static String url = "jdbc:mysql://localhost:3306/funds";
	private static String userName = "root";
	private static String password = "12345";
	private static Connection con;

	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, userName, password);
		}	
		
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection is not success!!!");
		}
		
		return con;


	}
	
	
	public String insertProj (String email, String name, String address, String phone, String des)  {
		
		
		String output = "";
		
		try 
		{
			Connection con = getConnection();
		
			if(con == null) {
			
				return "Error while connecting to the database";
			}
		
		
			String query = "insert into pending_tb values(?,?,?,?,?,?)";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2,email);
			preparedStmt.setString(3,name);
			preparedStmt.setString(4,address);
			preparedStmt.setInt(5,Integer.parseInt(phone));
			preparedStmt.setString(6,des);
		
			preparedStmt.execute();
			
			output = "inserted successfully";
			
			con.close();
			
			
		
		}
		catch(Exception e){
			
			System.err.println(e.getMessage());
		}
		
		
		return output;
		
		
	}
	
	public String readfunds() {
		
		
		String output = "";
		
		
		try 
		{
			Connection con = getConnection();
			if(con == null) {
			 return "Error while connecting to the database for reading";
			}
			
			//prepare the html table to be displayed
			 output = "<table class=\"table table-bordered\">\r\n"
				 		+ "  <thead>\r\n"
				 		+ "    <tr>\r\n"
				 		+ "      <th scope=\"col\">Email</th>\r\n"
				 		+ "		<th scope=\"col\">Name</th>\r\n"
				 		+ "		<th scope=\"col\">Address</th>\r\n"
				 		+ "      <th scope=\"col\">Phone</th>\r\n"
				 		+ "      <th scope=\"col\">Description</th>\r\n"
				 		+ "      <th scope=\"col\" colspan=\"2\">Upadate/Delete</th>\r\n"
				 		+ "    </tr>\r\n"
				 		+ "  </thead>\r\n"
				 		+ "</table";
			
			String query = "select * from pending_tb";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//iterate through the row in the result set
			while(rs.next())
			{
				String ID = Integer.toString(rs.getInt("id"));
				String email = rs.getString("email");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = Integer.toString(rs.getInt("phone"));
				String des = rs.getString("description");
				
				//add into the html table
				output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + ID + "'>"
				 		  + email + "</td>";
		 output += "<td>" + name + "</td>";
		 output += "<td>" + address + "</td>"; 
		 output += "<td>" + phone + "</td>";
		 output += "<td>" + des + "</td>";		
				
				//buttons
		 output += "<td>"
				 + "<input name='btnUpdate' "
			     + " type='button' class='btnUpdate btn btn-outline-dark' value='Update'>"
				 + "<input name='itemID' type='hidden' "
				 + " value='" + ID + "'>" + "</td>"
				 + "<td><form method='post' action='Insert.jsp'>"
				 + "<input name='btnRemove' "
				 + " type='submit' class='btn btn-outline-danger' value='Remove'>"
				 + "<input name='hidItemIDDelete' type='hidden' "
				 + " value='" + ID + "'>" + "</form></td></tr>";
				}
			
			con.close();
			
			//complete the html table
			output += "</table>";
			}
			catch(Exception e){
				output = "Error while reading the projects.";
				System.err.println(e.getMessage());
			}
			
		return output;
		
		}
	
	public String updateProj(String id,String email, String name, String address, String phone, String des) 
	{
		
		String output = "";
		try {
			Connection con = getConnection();
			if(con == null) {
				return "Error while connecting to the database for the Update";
			}
			
			String query ="Update pending_tb set email=?, name=?, address=?, phone=?, description=? where id='"+id+"'";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
		//	preparedStmt.setInt(1, 0);
			preparedStmt.setString(1,email);
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,address);
			preparedStmt.setInt(4,Integer.parseInt(phone));
			preparedStmt.setString(5,des);
			
			preparedStmt.execute();
			con.close();
			output = "Updated Successfully";
			
		}catch(Exception e) {
			
			output = "Error while Updating the data";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String deleteProj(String id) {
	
	String output = "";
	
	try 
	{
		Connection con = getConnection();
		if (con == null) {
			return "Error while connecting to the database for deleting";
		}
		
		String query = "delete from pending_tb where id=?";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1,Integer.parseInt(id));
		
		preparedStmt.execute();
		con.close();
		output = "Deleted Successfully";
		
	}catch (Exception e) {
		output = "Error while deleting the funds";
		System.err.println(e.getMessage());
	}
	
	return output;
}
	
	
	
	
}




