<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Counties Database Web Application</title>
</head>
<body>
	<center>
		<h1>Counties information</h1>
        <h2>
        	<a href="filters.jsp">Return To Filters</a>
        	
        </h2>
	</center>
    <div align="center">
     <form action="<%=request.getContextPath()%>/updateInfo" method="post">
     
     	
    <input type="submit" value="Submit" />
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>              
                <th>Population</th>
                <th>Income</th>              
                <th>HPrices</th>
                <th>State</th>
                <th>Rank</th>              
                <th>Fav</th>
                <th>Bans</th>
            </tr>
            <c:forEach var="info" items="${info}">
                <tr>
                    <td><c:out value="${info.county.id}" /></td>
                    <td><c:out value="${info.county.name}" /></td>
                    <td><c:out value="${info.county.population}" /></td>
                    <td><c:out value="${info.county.avgIncome}" /></td>
                    <td><c:out value="${info.county.avgHPrice}" /></td>
                    <td><c:out value="${info.county.state.stateName}" /></td>
                    <td><c:out value="${info.rank}" /></td>
                    
                   <td>  <input type="checkbox" name="favs"    value="<c:out value="${info.county.id}"/>"/>  </td>
                   <td>  <input type="checkbox" name="bans"    value="<c:out value="${info.county.id}"/>"/>  </td>    
                  
                </tr>
            </c:forEach>
        </table>
        </form>
    </div>	
</body>
</html>
