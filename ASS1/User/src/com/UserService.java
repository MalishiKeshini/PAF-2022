package com;

import model.User; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Users") 

public class UserService {
	User Userobj = new User(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	 { 
		return Userobj.readUsers(); 
	 } 
	
	//(`userid`,`acnumber`,`name`,`location`,`phone`)"
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("acnumber") String acnumber, 
	 @FormParam("name") String name, 
	 @FormParam("location") String location, 
	 @FormParam("phone") String phone) 
	{ 
	 String output = Userobj.insertUser(acnumber, name, location, phone); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUsers(String userData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String userid = userObject.get("userid").getAsString(); 
	 String acnumber = userObject.get("acnumber").getAsString(); 
	 String name = userObject.get("name").getAsString(); 
	 String location = userObject.get("location").getAsString(); 
	 String phone = userObject.get("phone").getAsString(); 
	 String output = Userobj.updateUsers(userid, acnumber, name, location, phone); 
	return output; 
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	
	
		public String deleteUser(String userData){
		
		
		
		//Convert the input string to an XML document
		
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
		//Read the value from the element <userID>
		 
		 
		 String userID = doc.select("userID").text();
		 String output = Userobj.deleteUser(userID);
		 
		 
		return output;
		}

	
	
}

