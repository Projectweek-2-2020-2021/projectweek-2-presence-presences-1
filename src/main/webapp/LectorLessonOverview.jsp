<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lesoverzicht - Lector</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">
    <table class="table-striped">
        <tr>
            <th>Vak</th>
        </tr>
        <c:forEach var="lessen" items="${lessenLijst}">
            <tr>
                <td><c:out value="${lessen.name}"/></td>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>