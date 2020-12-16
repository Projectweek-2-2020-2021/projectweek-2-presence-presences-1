<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Presence 1 - Lector lessen</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header>
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="lectorLessen"/>
    </jsp:include>
</header>
<main class="container">
    <div class="table-responsive">
        <c:forEach var="datum" items="${datums}">
            <table class="table table-hover">
                <tr>
                    <th><c:out value="${datum}"/></th>
                    <th>vak</th>
                    <th>studiepunten</th>
                    <th>studierichting</th>
                    <th>groep</th>
                </tr>
                <c:forEach var="les" items="${lessenLijst}">
                    <tr class="table-row" data-href="Controller?command=LectorOverzichtStudenten&vaknaam=<c:out value="${les.naam}"/>&datum=<c:out value="${datum}"/>">
                        <td><c:out value="${les.tijd}"/></td>
                        <td><c:out value="${les.naam}"/></td>
                        <td><c:out value="${les.studiepunten}"/></td>
                        <td><c:out value="${les.studierichting}"/></td>
                        <td>groep 1</td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>
</main>
<script>
    $(document).ready(function($) {
        $(".table-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });
</script>
</body>
</html>
