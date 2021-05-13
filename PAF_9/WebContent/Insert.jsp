<%@ page import = "model.Funding" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%
 	//Save-------------------------------
 	if(request.getParameter("phone") != null)
 	{
 		Funding funds = new Funding();
 		String stsMsg = "";
 		
 		//Insert-----------------
 		
 		if (request.getParameter("hidItemIDSave")  == "")
 		{
 			stsMsg = funds.insertProj(request.getParameter("email"),
 		 		request.getParameter("name"),
 		 		request.getParameter("address"),
 				request.getParameter("phone"),
 		 		request.getParameter("des"));
 		
     	}
 	else
 	 //Update---------------------------------
 	{
 		stsMsg = funds.updateProj(request.getParameter("hidItemIDSave"),
 				request.getParameter("email"),
 				request.getParameter("name"),
 				request.getParameter("address"),
 				request.getParameter("phone"),
 				request.getParameter("des"));
 		
 	}
 		
 		session.setAttribute("statusMsg", stsMsg);
}
 
 	//Delete----------------------------------
 	
	 if(request.getParameter("hidItemIDDelete") != null){
 		
 		Funding funds = new Funding();
 		String stsMsg = funds.deleteProj(request.getParameter("hidItemIDDelete"));
 		session.setAttribute("statusMsg", stsMsg);
 	}
 	
 	
 	
 
 
 %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Insert.js"></script>

</head>
<body>
<h1>Funding Project Management</h1>

<form id="formInsert" name="formInsert" action="Insert.jsp" method="post">
	
	Email: <input id="email" name="email" type="text" class="form-control form-control-sm"><br><br>
	Name: <input id="name" name="name" type="text" class="form-control form-control-sm"><br><br>
	Address: <input id="address" name="address" type="text" class="form-control form-control-sm"><br><br>
	Phone: <input id="phone" name="phone" type="number" class="form-control form-control-sm" ><br><br>
	Description: <input id="des" name="des" type="text" class="form-control form-control-sm"><br><br>
	<input id="btnSubmit" name="btnSubmit" type="submit" value="Save" class="btn btn-primary"><br><br>
	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	
</form>

<%
	out.print(session.getAttribute("statusMsg"));

%>


<br>

<%
	Funding funds = new Funding();
	out.print(funds.readfunds());

%>


</body>
</html>