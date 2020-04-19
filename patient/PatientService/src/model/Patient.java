package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {
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
	
	public String insertPatient(String patientName, String address, String email, String age,
			String channelledDocName, String phoneNo) {

		
		String output = "";
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for inserting";}
			
			String query = " insert into patient (`patientId`,`patientName`,`address`,`email`,`age`,`channelledDocName`,'phoneNo`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1,0);
			preparedstmt.setString(2, patientName);
			preparedstmt.setString(3, address);
			preparedstmt.setString(4, email);
			preparedstmt.setInt(5, Integer.parseInt(age));
			preparedstmt.setString(6, channelledDocName);
			preparedstmt.setString(7, phoneNo);
			
			preparedstmt.execute();
			con.close();
			
			output = "Insert successfully";
		}
		catch (Exception e) {
			output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readPatient() {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for reading.";}
			
			output = "<table border=\"1\"><tr><th>patient Id</th><th>patient Name</th><th>address</th><th>email</th><th>age</th><th>channelledDocName</th><th>phoneNo</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);	
			
			while (rs.next()) {
				String patientId = Integer.toString(rs.getInt("patientId"));
				String patientName = rs.getString("patientName");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String age = Integer.toString(rs.getInt("age"));
				String channelledDocName = rs.getString("channelledDocName");
				String phoneNo = rs.getString("phoneNo");
				
				output += "<tr><td>" + patientId + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + channelledDocName + "</td>";
				output += "<td>" + phoneNo + "</td>";
				
				
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"patient.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">" + "<input name=\"patientId\" type=\"hidden\" value=\"" + patientId + "\">" + "</form></td></tr>";
			}
			con.close();
			
			output += "</table>";
		}
		catch (Exception e) {
			output = "Error while reading the patient.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updatePatient(String patientId, String patientName, String address, String email, String age, String channelledDocName, String phoneNo) {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for updating";}
			
			String query = "UPDATE patient SET patientName=?, address=?, email=?, age=?, channelledDocName=?, phoneNo=? WHERE patientId=?";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setString(1, patientName);
			preparedstmt.setString(2, address);
			preparedstmt.setString(3, email);
			preparedstmt.setInt(4, Integer.parseInt(age));
			preparedstmt.setString(5, channelledDocName);
			preparedstmt.setString(6, phoneNo);
			preparedstmt.setInt(7, Integer.parseInt(patientId));
			
			preparedstmt.execute();
			con.close();
			
			output = "Update successfully";
		}
		catch (Exception e) {
			output = "Error while updating the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deletePatient(String patientId) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from patient where patientId=?";
			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1, Integer.parseInt(patientId));
			
			preparedstmt.execute();
			con.close();
			
			output = "Deleted successfully";
			
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}


	
}
