<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello JSP</h1>
	<%
		java.util.Date date = new java.util.Date();
	%>
	<h2>Now is <%= date.toString() %></h2>
	<%@ page import="java.util.*, java.text.*"  %>  
 
	<%@ page import="java.util.List, java.text.*" %>
	
	<%
	out.println("<h1>Now is "+ new Date()+"</h1>");

	String serverName= request.getServerName();
	 
	%>
</body>
</html>