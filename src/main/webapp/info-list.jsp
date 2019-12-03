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
        	<a href="/home.jsp">Home</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Best Counties</h2></caption>
            <tr>
				<th>Rank</th>              
                <th>Name</th>              
                <th>Population</th>
                <th>Income</th>              
                <th>HPrices</th>
                <th>State</th>
                <th>Fav</th>
            </tr>
            <c:forEach var="county" items="${topCounties}">
                <tr>
					<td><c:out value="${county.rank}" /></td>
                    <td>
                    	<a href="/locations?page=details&countyId=<c:out value="${county.id}" />">
                    	<c:out value="${county.name}" /> </a>
                    </td>
                    <td><c:out value="${county.population}" /></td>
                    <td><c:out value="${county.avgIncome}" /></td>
                    <td><c:out value="${county.avgHPrice}" /></td>
                    <td><c:out value="${county.stateName}" /></td>
                    
                   <td>  <a href="/action?action=favorite&countyId=<c:out value="${county.id}"/>">Fav</a>  </td>
                  
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
