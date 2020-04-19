package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Doctor;

@Path("/Doctor")
public class DoctorService {
	
	Doctor doctorObj = new Doctor();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readdoctor() {
		return doctorObj.readDoctor();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertdoctor(@FormParam("doctorName") String doctorName,
			@FormParam("address") String address,
			@FormParam("email") String email,
			@FormParam("location") String location,
			@FormParam("phoneNumber") String phoneNumber) 
	{
		String output = doctorObj.insertDoctor(doctorName, email, location, phoneNumber);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
		
		String doctorId = doctorObject.get("doctorId").getAsString();
		String doctorName = doctorObject.get("doctorName").getAsString();
		String email = doctorObject.get("email").getAsString();
		String location = doctorObject.get("location").getAsString();
		String phoneNumber = doctorObject.get("phoneNumber").getAsString();
		
		String output = doctorObj.updateDoctor(doctorId, doctorName, email, location, phoneNumber);
		return output;	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String itemData) {
		
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		String doctorId = doc.select("doctorId").text();
		
		String output = doctorObj.deleteDoctor(doctorId);
		return output;
		
	}
	
	
}
