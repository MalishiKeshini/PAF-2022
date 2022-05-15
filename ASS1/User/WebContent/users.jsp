<%@ page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
//Save---------------------------------
if (request.getParameter("acnumber") != null) 
{ 
	User userObj = new User(); 
 String stsMsg = ""; 
 
 	String acnumber= request.getParameter("acnumber");
 	String name= request.getParameter("name");
 	String location= request.getParameter("location");
 	String phone= request.getParameter("phone");
 	
 	
//Insert--------------------------
if (request.getParameter("hidUserIDSave") == "") 
 { 
 stsMsg = userObj.insertUser(acnumber,name,location,phone);
 } 
else//Update----------------------
 { 
 stsMsg = userObj.updateUsers(request.getParameter("hidUserIDSave"),acnumber,name,location,phone);
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
 User itemObj = new User(); 
 String stsMsg = 
 itemObj.deleteUser(request.getParameter("hidItemIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); }
 %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
	<h1>User Details</h1>
			<form id="formUser" name="formUser" method="post" action="users.jsp" >
					
				<!--  Account Number --> 
				Account Number :
 				<input id="acnumber" name="acnumber" type="text"
				class="form-control form-control-sm">
				
				<!-- Name -->
				<br> Name :
				<input id="name" name="name" type="text" class="form-control form-control-sm">
				
				<!-- Location -->
				<br> Location :
				<input id="location" name="location" type="text" class="form-control form-control-sm">
				
				<!-- Phone Number -->
				<br> Phone :
				<input id="phone" name="phone" type="text" class="form-control form-control-sm">
				<br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<input type="hidden" id="hidUserIDSave"name="hidUserIDSave" value="">
		
			</form>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id=divUsersGrid>
			 <%
			 User userObj = new User();
			 out.print(userObj.readUsers());
			 %>
			</div>
			
</div> </div> </div>
</body>
</html>