<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Presence 1 - Student Overview</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp"/>
<main class="container">
    <div class="table-responsive">
        <table class="table">
            <tr>
                <th>r-Nummer</th>
                <th>Voornaam</th>
                <th>Achternaam</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${studentspervak}" var="student">
                <tr>
                    <td><c:out value='${student.rnummer}'/></td>
                    <td><c:out value='${student.voornaam}'/></td>
                    <td><c:out value='${student.naam}'/></td>
                    <c:choose>
                        <c:when test="${aanwezig}">
                            <td>aanwezig</td>
                        </c:when>
                        <c:otherwise>
                            <td>Niet aanwezig</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            <caption>Studentenoverzicht</caption>
        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</main>
<script src="vendor/jquery/jquery.slim.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
