<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Counties Database Web Application</title>
</head>
<body>
	<center>
		<h1>Create Profile</h1>
        <h2>
        	<a href="newProfile">Add New Profile</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="loginsuccess.jsp">Return To User Menu</a>
        	
        </h2>
	</center>
    <div align="center">
			<form action="/profiles" method="post">
       
        <table border="1" cellpadding="5">
            <caption>
            	<h2>            		
            			Enter Weights 		
            		
            	</h2><br/>
            	   Enter values from 0 to 0.9!
            </caption>
        		
        		        
            <tr>
                <th>Income Weight: </th>
                <td>
                <input type="text" name="weightIncome" size="45"
                			value=""
                		/>
                </td>
            </tr>    
            
              <tr>
                <th>Home Price Weight: </th>
                <td>
                	<input type="text" name="weightPrice" size="45"
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
