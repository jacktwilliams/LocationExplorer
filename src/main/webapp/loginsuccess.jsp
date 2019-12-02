<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@page import="county.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
 <div align="center">
 User name : <%= session.getAttribute("username") %>   <br/>
  <h1>User Menu Page</h1>  
   		<h2>      	
        	<a href="listProfile">Profiles list</a>        	
        </h2>        
         <h2>      	
        	<a href="listUser">Users list</a>        	        
        </h2>
 </div>
</body>
</html>