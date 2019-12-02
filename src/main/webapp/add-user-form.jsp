<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Counties Database Web Application</title>
</head>
<body>
	<center>
		<h1>User Form</h1>
        <h2>
        	
        	<a href="login">Login page</a>
        	
        </h2>
	</center>
    <div align="center">
		
       
			<form action="insertUser" method="post">
        
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            	
            			Add New User
            		
            	</h2>
            </caption>
        		       
            <tr>
                <th>User Name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value=""
                		/>
                </td>
            </tr>
            
           
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
