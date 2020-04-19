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

import model.Patient;

@Path("/Patient")
public class PatientService {
	
	Patient patientObj = new Patient();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return patientObj.readPatient();
		
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("patientName") String patientName,
			@FormParam("address") String address,
			@FormParam("email") String email,
			@FormParam("age") String age,
			@FormParam("channelledDocName") String channelledDocName,
			@FormParam("phoneNo") String phoneNo) 
	{
		String output = patientObj.insertPatient(patientName, address, email, age, channelledDocName, phoneNo);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData) {
		
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
		
		String patientId = patientObject.get("patientId").getAsString();
		String patientName = patientObject.get("patientName").getAsString();
		String address = patientObject.get("address").getAsString();
		String email = patientObject.get("email").getAsString();
		String age = patientObject.get("age").getAsString();
		String channelledDocName = patientObject.get("channelledDocName").getAsString();
		String phoneNo = patientObject.get("phoneNo").getAsString();
		
		String output = patientObj.updatePatient(patientId, patientName, address, email, age, channelledDocName, phoneNo);
		return output;	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String itemData) {
		
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		String patientId = doc.select("patientId").text();
		
		String output = patientObj.deletePatient(patientId);
		return output;
		
	}
	
	
}
