<%--
  Created by IntelliJ IDEA.
  User: ken
  Date: 9/20/2017
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navCustomer.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="viewStatement" method="post">
    Enter account Number<input type="number" name="account_number"><br>
    <input type="submit" value="View Statement"><br>
</form>
</body>
</html>
