<%--
  Created by IntelliJ IDEA.
  User: ken
  Date: 9/21/2017
  Time: 8:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navCustomer.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<body>
<form action="applyLoan" method="post">
    <table>
        <tr>
            <td>Customer ID:</td>
            <td><input type="number" name="customer_id" required/></td>
        </tr>
        <tr>
            <td>Account Number:</td>
            <td><input type="number" name="account_number" required/></td>
        </tr>
        <tr>
            <td>Loan Type:</td>
            <td>
                <select name="loantype">
                    <option>Long term</option>
                    <option>Short term</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Amount:</td>
            <td><input type="number" name="amount" required/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Apply Loan"/></td>
        </tr>
    </table>
</form>
</body>
</html>
