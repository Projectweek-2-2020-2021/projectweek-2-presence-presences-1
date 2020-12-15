<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Presence 1 - Studenten</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="lectorOverzichtStudenten"/>
</jsp:include>
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
