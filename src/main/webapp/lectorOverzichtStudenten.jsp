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
    <jsp:param name="actual" value="${les}"/>
    <jsp:param name="current" value="studentOverzicht"/>
</jsp:include>
<main class="container">
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
        <h2 style="margin-top: 35px"><strong>Aanwezigheden</strong></h2>
        <table class="table table-hover">
            <tr>
                <th class="col-2">r-Nummer</th>
                <th class="col-2">Voornaam</th>
                <th class="col-2">Achternaam</th>
                <th class="col-2">Status</th>
                <th class="col-2">Bevestig</th>
                <th class="col-2">Wijs af</th>
            </tr>
            <c:forEach items="${studenten}" var="student" varStatus="status">
                <c:if test="${student.status == 'Aanwezig' || student.status == 'Pending'}">
                    <c:if test="${student.status == 'Aanwezig'}">
                        <c:set var="studentStatus" value="table-success"/>
                    </c:if>
                    <c:if test="${student.status == 'Pending'}">
                        <c:set var="studentStatus" value="table-warning"/>
                    </c:if>
                    <tr class="table-row ${studentStatus}"
                        data-href="Controller?command=VoegCommentToe&student=${student.rnummer}&les=${les}&datum=${datum}">
                        <td><c:out value='${student.rnummer}'/></td>
                        <td><c:out value='${student.voornaam}'/></td>
                        <td><c:out value='${student.naam}'/></td>
                        <td><c:out value='${student.status}'/></td>
                        <td>
                            <form method="post"
                                  action="Controller?command=Bevestig&bevestiging=ja&student=${student.rnummer}&les=${les}&datum=${datum}">
                                <input type="submit" value="Bevestigen">
                            </form>
                        </td>
                        <td>
                            <form method="post"
                                  action="Controller?command=Bevestig&bevestiging=nee&student=${student.rnummer}&les=${les}&datum=${datum}">
                                <input type="submit" value="Afwijzen">
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <div class="table-responsive">
        <h2><strong>Afwezigheden</strong></h2>
        <table class="table table-hover">
            <tr>
                <th class="col-2">r-Nummer</th>
                <th class="col-2">Voornaam</th>
                <th class="col-2">Achternaam</th>
                <th class="col-2">Status</th>
                <th class="col-2">Bevestig</th>
                <th class="col-2">Gewettigd afwezig</th>
            </tr>
            <c:forEach items="${studenten}" var="student">
                <c:if test="${student.status == 'Afwezig' || student.status == 'Gewettigd afwezig'}">
                    <c:if test="${student.status == 'Afwezig'}">
                        <c:set var="studentStatus" value="table-danger"/>
                    </c:if>
                    <c:if test="${student.status == 'Gewettigd afwezig'}">
                        <c:set var="studentStatus" value="table-info"/>
                    </c:if>
                    <tr class="table-row ${studentStatus}"
                        data-href="Controller?command=VoegCommentToe&student=${student.rnummer}&les=${les}&datum=${datum}">
                        <td><c:out value='${student.rnummer}'/></td>
                        <td><c:out value='${student.voornaam}'/></td>
                        <td><c:out value='${student.naam}'/></td>
                        <td><c:out value='${student.status}'/></td>
                        <td>
                            <form action="Controller?command=Bevestig&bevestiging=ja&student=${student.rnummer}&les=${les}&datum=${datum}"
                                  method="post">
                                <input type="submit" value="Aanwezig">
                            </form>
                        </td>
                        <td>
                            <form action="Controller?command=GewettigdAfwezig&student=${student.rnummer}&les=${les}&datum=${datum}"
                                  method="post">
                                <input type="submit" value="Gewettigd afwezig">
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</main>
<script>
    $(document).ready(function ($) {
        $(".table-row").click(function () {
            window.document.location = $(this).data("href");
        });
    });
</script>
</body>
</html>
