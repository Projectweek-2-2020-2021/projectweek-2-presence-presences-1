<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Presence 1 - Lesson overview</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<style>
    main span {
        padding: 10px 40px 10px 40px;
        text-align: center;
    }
</style>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="actual" value="${titel}"/>
</jsp:include>

<main class="container">
    <c:if test="${not empty student}">
        <p>Student: ${student}</p>
    </c:if>
    <div class="row justify-content-around mb-3">
        <span class="border border-success rounded col-sm-2"
              style="background-color: #c3e6cb"><strong>Aanwezig</strong></span>
        <span class="border border-danger rounded col-sm-2"
              style="background-color: #f5c6cb"><strong>Afwezig</strong></span>
        <span class="border border-warning rounded col-sm-2"
              style="background-color: #ffeeba"><strong>Pending</strong></span>
        <span class="border border-info rounded col-sm-2"
              style="background-color: #bee5eb"><strong>Gewettigd afwezig</strong></span>
        <span class="border border-secondary rounded col-sm-2"
              style="background-color: #fdfdfe"><strong>Onbekend</strong></span>
    </div>
    <div class="table-responsive">
        <c:forEach var="list" items="${lessenPerDag}" varStatus="statusdag">
            <table class="table table-hover">
                <tr>
                    <th><c:out value="${list.key}"/></th>
                    <th>Informatie</th>
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
                    <tr class="table-row ${studentStatus}"
                        data-href="Controller?command=AanwezigheidControle&naam=<c:out value="${les.naam}"/>&datum=<c:out value="${list.key}"/>">
                        <td class="col-2"><c:out value="${les.tijd}"/> - <c:out value="${les.getEindTijd()}"/></td>
                        <td class="col-7"><c:out value="${les.naam}"/>, dit vak heeft <c:out
                                value="${les.studiepunten}"/> studiepunten
                            in de richting <c:out value="${les.studierichting}"/></td>
                        <td class="col-1"><c:out value="${groeplijstperdag[statusdag.index][status.index]}"/></td>
                        <td class="col-1">
                            <c:forEach var = "lokaal" items = "${lokalenlijstperdag[statusdag.index][status.index]}">
                                <c:out value="${lokaal}"/>
                            </c:forEach>

                        </td>
                        <c:forEach var="lector" items="${lectorlijstperdag[statusdag.index][status.index]}">
                            <td class="col-1"><c:out value="${lector.achternaam}"/></td>
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
</body>
</html>
