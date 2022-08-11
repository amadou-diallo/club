<%-- 
    Document   : Purchases
    Created on : Sep 16, 2021, 6:18:34 AM
    Author     : kadiatou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Purchases</title>
    </head>
    <body>
        <h1>Transactions on File for:</h1>
        <h2>${m.memid}</h2>
        <h2>${m.firstname} ${m.lastname}</h2>
        <br>
        <table border="1">
            <tr>
                <th>Purchase Dt</th>
                <th>Trans Type</th>
                <th>Trans Cd</th>
                <th>Trans Desc</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="p" items="${pur}">
                <tr>
                    <td align="right">${p.purchasedt}</td>
                    <td align="right">${p.transtype}</td>
                    <td align="right">${p.transcd}</td>
                    <td align="right">${p.transdesc}</td>
                    <td align="right">${p.amount}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        ${msg}
        <br>
        <a href="MemberScreen.jsp">Back to member screen</a>
    </body>
</html>
