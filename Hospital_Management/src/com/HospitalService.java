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

import model.Hospital;

@Path("/Hospital")

public class HospitalService {
	
Hospital hospitalObj = new Hospital();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewHospital()
	{
		return hospitalObj.viewHospital();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hospitalName") String hospitalName,
							@FormParam("hospitalAddress") String hospitalAddress)

	{
			String output = hospitalObj.insertHospital(hospitalName, hospitalAddress);
			return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitals(String hospitalDetails)
	{
		//Convert the input string to a JSON object
		JsonObject hospitalObject = new JsonParser().parse(hospitalDetails).getAsJsonObject();
		//Read the values from the JSON object
		String hospitalID = hospitalObject.get("hospitalID").getAsString();
		String hospitalName = hospitalObject.get("hospitalName").getAsString();
		String hospitalAddress = hospitalObject.get("hospitalAddress").getAsString();
		
		String output = hospitalObj.updateHospitals(hospitalID, hospitalName, hospitalAddress);
	
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospitals(String itemData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		//Read the value from the element <itemID>
		String itemID = doc.select("itemID").text();
	
		String output = hospitalObj.deleteHospitals(itemID);
	
		return output;
	}

}
