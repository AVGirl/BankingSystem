<%--
  Created by IntelliJ IDEA.
  User: ken
  Date: 9/8/2017
  Time: 5:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navCustomer.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form action="checkBalance" method="post">
    <table>
        <tr>
            <td>Account Number:</td>
            <td><input type="number" name="account_number" required/></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="Check Balance"/></td>
        </tr>
    </table>
</form>
</body>
</html>
