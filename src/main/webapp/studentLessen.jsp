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
        <c:forEach var="list" items="${lessenPerDag}" varStatus="statusdag">
            <table class="table table-hover">
                <tr>
                    <th><c:out value="${list.key}"/></th>
                    <th>Info</th>
                    <th>Groep</th>
                    <th>Lokaal</th>
                    <th>Leerkracht</th>
                </tr>
                <c:forEach var="les" items="${list.value}" varStatus="status">
                    <c:if test="${les.status == 'aanwezig'}">
                        <c:set var="studentStatus" value="table-success"/>
                    </c:if>
                    <c:if test="${les.status == 'afwezig'}">
                        <c:set var="studentStatus" value="table-danger"/>
                    </c:if>
                    <c:if test="${les.status == 'pending'}">
                        <c:set var="studentStatus" value="table-warning"/>
                    </c:if>
                    <c:if test="${les.status == 'gewettigd afwezig'}">
                        <c:set var="studentStatus" value="table-info"/>
                    </c:if>
                    <c:if test="${les.status == 'onbekend'}">
                        <c:set var="studentStatus" value="table-light"/>
                    </c:if>
                    <tr class="table-row ${studentStatus}"  data-href="Controller?command=AanwezigheidControle&naam=<c:out value="${les.naam}"/>&datum=<c:out value="${list.key}"/>">
                        <td><c:out value="${les.tijd}"/> - <c:out value="${les.getEindTijd()}"/></td>
                        <td><c:out value="${les.naam}"/>, dit vak heeft <c:out value="${les.studiepunten}"/> studiepunten
                            in de richting <c:out value="${les.studierichting}"/></td>
                        <td><c:out value="${groeplijstperdag[statusdag.index][status.index]}"/></td>
                        <td><c:out value="${lokalenlijstperdag[statusdag.index][status.index]}"/></td>
                        <c:forEach var="lector" items="${lectorlijstperdag[statusdag.index][status.index]}">
                            <td><c:out value="${lector.achternaam}"/></td>
                        </c:forEach>

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
