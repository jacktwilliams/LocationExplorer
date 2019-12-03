<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Details</title>
</head>
<body>
	<center>
		<h1>Details</h1>
		<h2>
			<a href="/home.jsp">Home</a>        	
	    </h2>
	</center>
    <div align="center">
       <table border="1" cellpadding="5">
            <caption><h2>Jobs</h2></caption>
            <tr>
                <th>Title</th>              
                <th>Description</th>
            </tr>
            <c:forEach var="job" items="${jobs}">
                <tr>
                    <td><c:out value="${job.title}" /></td>
                    <td><c:out value="${job.description}" /></td>                
                </tr>
            </c:forEach>
        </table>
    </div>	
                    	
</body>
</html>
