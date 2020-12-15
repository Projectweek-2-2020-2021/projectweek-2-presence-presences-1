<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/sample.css">
    <meta charset="UTF-8">
    <title>Countries</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main id="container">
    <table>
        <tr>
            <th>r-Nummer</th>
            <th>Voornaam</th>
            <th>Achternaam</th>
            <th>Status</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td><c:out value='${student.rnummer}'/></td>
                <td><c:out value='${student.firstName}'/></td>
                <td><c:out value='${student.lastName}'/></td>
                <c:choose>
                    <c:when test="${aanwezig == true}">
                        <td>aanwezig</td>
                    </c:when>
                    <c:otherwise>
                        <td>Niet aanwezig</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>

        <caption>Students overview</caption>
    </table>
    <jsp:include page="footer.jsp"/>
</main>

</body>
</html>
