<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Profiles List</title>
</head>
<body>
	<center>
		<h1>Profiles</h1>
		<h2>
			<a href="/home.jsp">Home</a> <br/>        	
	    	<a href="/profiles?page=create">Add New Profile</a>
	    </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Profiles</h2></caption><br/>
         
            <tr>
                <th>ID</th>
                <th>Income Weight</th>
                <th>Prices Weight</th>
                <th>Population Weight</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="profile" items="${profiles}">
                <tr>
                    <td><c:out value="${profile.id}" /></td>
                    <td><c:out value="${profile.weightIncome}" /></td>
                    <td><c:out value="${profile.weightHPrice}" /></td>
                    <td><c:out value="${profile.weightPopulation}" /></td>
                  
                    <td>
                    	<a href="/action?action=deleteProfile&id=<c:out value='${profile.id}' />">Delete</a>                    	
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="/locations?page=list&profileId=<c:out value='${profile.id}' />">Run</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
                    	
</body>
</html>
