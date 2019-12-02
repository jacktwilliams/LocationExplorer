<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Counties DB</title>
</head>
<body>
 <div align="center">
  <h1>User Login Page</h1>
  <form action="<%=request.getContextPath()%>/login" method="post">
   <table style="with: 100%">
    <tr>
     <td>Username</td>
     <td><input type="text" name="name" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>