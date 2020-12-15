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
        <c:forEach var="datum" items="${datums}">
            <table class="table table-hover">
                <tr>
                    <th><c:out value="${datum}"/></th>
                    <th>info</th>
                    <th>Leerkracht</th>
                </tr>
                <c:forEach var="les" items="${lessenLijst}">
                    <tr class="table-row" data-href="Controller?command=AanwezigheidControle&naam=<c:out value="${les.naam}"/>">
                        <td><c:out value="${les.tijd}"/></td>
                        <td><c:out value="${les.naam}"/>, dit vak heeft <c:out value="${les.studiepunten}"/> studiepunten
                            in de richting <c:out value="${les.studierichting}"/></td>
                        <!--<td><a href="Controller?command=AanwezigheidControle&naam=${les.naam}">Aanwezig</a></td> !-->
                        <td><c:out value="${leerkracht}"/></td>
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


<jsp:include page="footer.jsp"/>
</body>
</html>
