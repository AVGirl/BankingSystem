<%--
  Created by IntelliJ IDEA.
  User: ken
  Date: 8/25/2017
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="navCustomer.jsp" %>
<html>
<head>
    <title>View Statement</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/tableExport.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>
    <script type="text/javascript" src="js/sprintf.js"></script>
    <script type="text/javascript" src="js/jspdf.js"></script>
    <script type="text/javascript" src="js/base64.js"></script>
    <script type="text/javascript" src="js/html2canvas.js"></script>

</head>
<body>
<div class="container">
    <div class="jumbotron">
        <button class="btn btn-success"><a href="" onclick="$('#tableID').tableExport({type:'doc',escape:'false'});">Download</a> </button>
        <table class="table table-bordered" id="tableID">
            <tr>
                <thead>
                <td>Account Number</td>
                <td> Details</td>
                <td> Amount</td>
                <td> Balance</td>
                <td> Time</td>
                </thead>
            </tr>
            <c:forEach var="statement" items="${statements}">
                <tr>
                    <td><c:out value="${statement.account_number}"></c:out></td>
                    <td><c:out value="${statement.details}"></c:out></td>
                    <td><c:out value="${statement.amount}"></c:out></td>
                    <td><c:out value="${statement.bal}"></c:out></td>
                    <td><c:out value="${statement.time}"></c:out></td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>


</body>
</html>
