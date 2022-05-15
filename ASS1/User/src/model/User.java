package model;

import java.sql.*;

import javax.ws.rs.Path;

@Path("/Hello") 
public class User 
{ 
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	public String insertUser(String acnumber , String name, String location, String phone) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 
	 // create a prepared statement
	 String query = " insert into users (`userid`,`acnumber`,`name`,`location`,`phone`)" + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, acnumber); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setString(4, location); 
	 preparedStmt.setString(5, phone); 
	 
	 // execute the statement
	
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the User."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String readUsers() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
			
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table class=\"table table-dark table-striped\"><thead><table border=\"5\"><tr class=\"table-primary\"><th>Account Number</th>"
	 			+"<th scope=\"col\">Name</th>" 
			 	+"<th scope=\"col\">Location</th>" 
	 			+"<th scope=\"col\">P.Number</th>" 
			 	+"<th scope=\"col\">Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from users"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
		//userid acnumber name location phone
		 
		 String userid =  Integer.toString(rs.getInt("userid"));  
		 String acnumber = rs.getString("acnumber"); 
		 String name = rs.getString("name"); 
		 String location = rs.getString("location"); 
		 String phone = rs.getString("phone"); 
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidItemIDUpdate'  name='hidItemIDUpdate' type='hidden' value='" + userid + "'>"+ acnumber + "</td>"; 
		 
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + location + "</td>"; 
		 output += "<td>" + phone + "</td>"; 
		 
		 // buttons
		 
		 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td><form method='post' action='users.jsp'><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'><input name='hidItemIDDelete' type='hidden'value='" + userid + "'>" + "</form></td></tr>";
		 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Users."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateUsers(String userid, String acnumber, String name, String location, String phone) 
	 
	 { 
		 String output = ""; 
		 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE users SET acnumber=?,name=?,location=?,phone=? WHERE userid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, acnumber); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, location); 
			 preparedStmt.setString(4, phone); 
			 preparedStmt.setInt(5, Integer.parseInt(userid)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } catch (Exception e) { 
				 output = "Error while updating the Users."; 
				 System.err.println(e.getMessage()); 
				 } 
			return output; 
	 } 
	
	public String deleteUser(String userid){ 
		 String output = ""; 
		 
			 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {return "Error while connecting to the database for deleting."; } 
					 // create a prepared statement
					 String query = "delete from users where userid=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(userid));
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Deleted successfully"; 
				 } 
			 catch (Exception e) 
			 { 
			 output = "Error while deleting the User."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } 
	
	
} 