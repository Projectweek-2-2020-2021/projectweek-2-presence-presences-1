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

<jsp:include page="header.jsp">
    <jsp:param name="actual" value="studentLessen"/>
</jsp:include>

<main class="container">
    <div class="table-responsive">
        <table class="table">
            <tr>
                <th>Vak</th>
                <th>Studiepunten</th>
                <th>Studierichting</th>
                <th>Aanwezig?</th>
            </tr>
            <c:forEach var="les" items="${lessenLijst}">
                <tr>
                    <td><c:out value="${les.naam}"/></td>
                    <td><c:out value="${les.studiepunten}"/></td>
                    <td><c:out value="${les.studierichting}"/></td>
                    <td><a href="Controller?command=AanwezigheidControle&naam=${les.naam}">Aanwezig</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>
