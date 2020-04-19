package model;

import java.sql.*;

public class Hospital {
	
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
		}
		
		public String insertHospital(String name, String address)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
		
				// create a prepared statement
				String query = " insert into hoptital(`hospitalID`,`hospitalName`,`hospitalAddress`)" + " values (?, ?, ?)";
		
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, address);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully";
			}
			catch (Exception e)
				{
				output = "Error while inserting the hospital.";
				System.err.println(e.getMessage());
				}
			
				return output;
			}
		
			public String viewHospital()
			{
				String output = "";
				
				try
				{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Hospital Address</th><th>Update</th><th>Remove</th></tr>";
				
						String query = "select * from hospital";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						
				// iterate through the rows in the result set
				while (rs.next())
				{
					String hospitalID = Integer.toString(rs.getInt("hospitalID"));
					String hospitalName = rs.getString("hospitalName");
					String hospitalAddress = rs.getString("hospitalAddress");
				
					// Add into the html table
					output += "<td>" + hospitalName + "</td>";
					output += "<td>" + hospitalAddress + "</td>";
					
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"hospital.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">" + "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID + "\">" + "</form></td></tr>";
				}
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while viewing hospitals.";
				System.err.println(e.getMessage());
			}
				return output;
		}		
			
		public String updateHospitals(String ID, String name, String address)
			{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for updating."; }
			
				// create a prepared statement
				String query = "UPDATE items SET hospitalName=?,hospitalAddress=? WHERE hospitalID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, address);
				preparedStmt.setInt(3, Integer.parseInt(ID));
			
				// execute the statement
				preparedStmt.execute();
				con.close();
			
				output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the hospital.";
				System.err.println(e.getMessage());
			}
				return output;
		}
		
		public String deleteHospitals(String hospitalID)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
			
				// create a prepared statement
				String query = "delete from items where hospitalID=?";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(hospitalID));
			
				// execute the statement
				preparedStmt.execute();
				con.close();
			
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the hospitals.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

}
