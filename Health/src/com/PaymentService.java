package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Payment")
public class PaymentService
{
Payment paymentObj = new Payment();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readPayment()
{
return "Hello";
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(@FormParam("AppoinmentCode") String AppoinmentCode,
 @FormParam("cardName") String cardName,
 @FormParam("Price") String Price,
 @FormParam("cardNo") String cardNo)
{
 String output = paymentObj.insertItem(AppoinmentCode, cardName, Price, cardNo);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String paymentData)
{
//Convert the input string to a JSON object
 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
//Read the values from the JSON object
 String CustomerID = paymentObject.get("CustomerID").getAsString();
 String AppoinmentCode = paymentObject.get("AppoinmentCode").getAsString();
 String cardName = paymentObject.get("cardName").getAsString();
 String Price = paymentObject.get("Price").getAsString();
 String cardNo = paymentObject.get("cardNo").getAsString();
 String output = paymentObj.updateItem(CustomerID, AppoinmentCode, cardName, Price, cardNo);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletePayment(String paymentData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String CustomerID = doc.select("CustomerID").text();
 String output = paymentObj.deleteItem(CustomerID);
return output;
}



}



