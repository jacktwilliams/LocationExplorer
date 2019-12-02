<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Counties Filters</title>
</head>
<body>
 <div align="center">
  <h1>Counties Filters</h1>
  <form action="<%=request.getContextPath()%>/runProfile" method="post">
   <table style="with: 100%">
    <tr>
        <input type = "checkbox" name = "onlyfav"  /> Display Only Favorites
        <br/>
         <input type = "checkbox" name = "hidebanned"  /> Hide Banned States
    </tr>
   

   </table>
  
   <input type="submit" value="Submit" />
   
    <br/><br/><br/><h2>
    <a href="loginsuccess.jsp">Return To User Menu</a>
    </h2>
        	
  </form>
 </div>
</body>
</html>