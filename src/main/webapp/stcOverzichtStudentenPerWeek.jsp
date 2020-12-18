<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Presence 1 - STC studenten overzicht per week</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="STC overzicht"/>
</jsp:include>
<main class="container">
    <p class="lead">Hier hebt u een overzicht van de studenten waar u STC'er van bent. Druk op een les om de opmerking bij die les te zien.</p>
    <div class="table-responsive">
        <c:forEach var="list" items="${lessenVoorStudenten}">
            <table class="table table-hover">
                <tr class="table-secondary">
                    <th><c:out value="${list.key.rnummer}"/></th>
                    <th><c:out value="${list.key.voornaam}"/> <c:out value="${list.key.naam}"/></th>

                </tr>
                <tr>
                    <th class="col-3">Datum</th>
                    <th class="col-3">Naam van vak</th>
                    <th class="col-3">Klaslokaal</th>
                    <th class="col-3">Status</th>
                </tr>
                <c:forEach var="les" items="${list.value}" varStatus="status">
                    <c:if test="${les.status == 'Aanwezig'}">
                        <c:set var="studentStatus" value="table-success"/>
                    </c:if>
                    <c:if test="${les.status == 'Afwezig'}">
                        <c:set var="studentStatus" value="table-danger"/>
                    </c:if>
                    <c:if test="${les.status == 'Pending'}">
                        <c:set var="studentStatus" value="table-warning"/>
                    </c:if>
                    <c:if test="${les.status == 'Gewettigd afwezig'}">
                        <c:set var="studentStatus" value="table-info"/>
                    </c:if>
                    <tr data-toggle="popover" title="Opmerking:" data-content="${les.opmerking}">
                        <td><c:out value="${les.datum}"/></td>
                        <td><c:out value="${les.lesNaam}"/></td>
                        <td><c:out value="${les.klaslokaal}"/></td>
                        <td class="${studentStatus}"><c:out value="${les.status}"/></td>
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
<script>
    $(document).ready(function(){
        $('[data-toggle="popover"]').popover();
    });
</script>
</body>
</html>
