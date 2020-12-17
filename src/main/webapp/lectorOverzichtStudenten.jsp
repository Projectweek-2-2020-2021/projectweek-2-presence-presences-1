<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Presence 1 - Studenten overzicht</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp"/>
<main class="container">
    <h1><c:out value="${les}"/></h1>
    <div class="table-responsive">
        <h2>Aanwezigheden Studentenoverzicht</h2>
        <table class="table">
            <tr>
                <th>r-Nummer</th>
                <th>Voornaam</th>
                <th>Achternaam</th>
                <th>Bevestig</th>
                <th>Wijs af</th>
            </tr>
            <c:forEach items="${aanwezig}" var="student">
                <tr>
                    <td><c:out value='${student.rnummer}'/></td>
                    <td><c:out value='${student.voornaam}'/></td>
                    <td><c:out value='${student.naam}'/></td>
                    <td>
                        <form method="post" action="Controller?command=Bevestig&bevestiging=ja&student=${student.rnummer}&les=${les}&datum=${datum}" >
                            <input type="submit" value="Bevestigen">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="Controller?command=Bevestig&bevestiging=nee&student=${student.rnummer}&les=${les}&datum=${datum}">
                            <input type="submit" value="Afwijzen">
                        </form>
                    </td>
                    <td>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="table-responsive">
        <h2>Studentenoverzicht</h2>
        <table class="table table-hover">
            <tr>
                <th>r-Nummer</th>
                <th>Voornaam</th>
                <th>Achternaam</th>
                <th>Status</th>
                <th>Bevestig</th>
                <th>Gewettigd afwezig</th>
            </tr>
            <c:forEach items="${afwezig}" var="student">
                <tr>
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
            </c:forEach>
        </table>
    </div>
    <jsp:include page="footer.jsp"/>
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
