package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
	private Connection connect() {
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/medicare", "root", "");
			System.out.print("Successfully connected");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertDoctor(String doctorName, String email, String location, String phoneNumber) {

		
		String output = "";
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for inserting";}
			
			String query = " insert into doctor (`doctorId`,`doctorName`,`email`,`location`,'phoneNumber`)" + " values (?, ?, ?, ?, ?)";
			
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1,0);
			preparedstmt.setString(2, doctorName);
			preparedstmt.setString(4, email);
			preparedstmt.setString(6, location);
			preparedstmt.setString(7, phoneNumber);
			
			preparedstmt.execute();
			con.close();
			
			output = "Insert successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readDoctor() {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for reading.";}
			
			output = "<table border=\"1\"><tr><th>doctor Id</th><th>doctor Name</th><th>email</th><th>location</th><th>phoneNumber</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);	
			
			while (rs.next()) {
				String doctorId = Integer.toString(rs.getInt("doctorId"));
				String doctorName = rs.getString("doctorName");
				String email = rs.getString("email");
				String location = rs.getString("location");
				String phoneNumber = rs.getString("phoneNumber");
				
				output += "<tr><td>" + doctorId + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + location + "</td>";
				output += "<td>" + phoneNumber + "</td>";
				
				
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"doctor.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">" + "<input name=\"doctorId\" type=\"hidden\" value=\"" + doctorId + "\">" + "</form></td></tr>";
			}
			con.close();
			
			output += "</table>";
		}
		catch (Exception e) {
			output = "Error while reading the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateDoctor(String doctorId, String doctorName, String email, String location, String phoneNumber) {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for updating";}
			
			String query = "UPDATE doctor SET doctorName=?, email=?, location=?, phoneNumber=? WHERE doctorId=?";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setString(1, doctorName);
			preparedstmt.setString(3, email);
			preparedstmt.setString(5, location);
			preparedstmt.setString(6, phoneNumber);
			preparedstmt.setInt(7, Integer.parseInt(doctorId));
			
			preparedstmt.execute();
			con.close();
			
			output = "Update successfully";
		}
		catch (Exception e) {
			output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteDoctor(String doctorId) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from doctor where doctorId=?";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1, Integer.parseInt(doctorId));
			
			preparedstmt.execute();
			con.close();
			
			output = "Deleted successfully";
			
		} catch (Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}


	
}
