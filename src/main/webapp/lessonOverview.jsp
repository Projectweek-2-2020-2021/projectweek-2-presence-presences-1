<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Presence 1 - Lesson overview</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="header.jsp"/>

<main class="container">
    <table class="table-striped">
        <tr>
            <th>Name</th>
            <th>zie studenten</th>
        </tr>
        <c:forEach var="lesson" items="${lessonLijst}">
            <tr>
                <td><c:out value="${lesson.name}"/></td>
                <td><a href="Controller?command=LectorOverviewStudents&lesson=${lesson.name}">hier</a></td>
            </tr>
        </c:forEach>
    </table>
</main>

<jsp:include page="footer.jsp"/>

<script src="vendor/jquery/jquery.slim.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
