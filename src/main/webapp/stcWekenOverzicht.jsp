<%--
  Created by IntelliJ IDEA.
  User: Maarten
  Date: 17/12/2020
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Presence 1 - Stc week overzicht</title>
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
    <p class="lead">Hier hebt u een overzicht van de studenten waar u STC'er van bent per week.</p>
    <table class="table table-hover">
        <tr>
            <th>Week</th>
            <th>Data</th>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-09-21&tot=2020-09-25">
            <td>Week 1</td>
            <td>21/09/2020-25/09/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-09-28&tot=2020-10-02">
            <td>Week 2</td>
            <td>28/09/2020-02/10/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-10-05&tot=2020-10-09">
            <td>Week 3</td>
            <td>05/10/2020-09/10/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-10-12&tot=2020-10-16">
            <td>Week 4</td>
            <td>12/10/2020-16/10/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-10-19&tot=2020-10-23">
            <td>Week 5</td>
            <td>19/10/2020-23/10/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-10-26&tot=2020-10-30">
            <td>Week 6</td>
            <td>26/10/2020-30/10/2020</td>
        </tr>
        <tr class="table-row"  data-href="Controller?command=StcOverzichtStudentenPerWeek&van=2020-11-02&tot=2020-11-06">
            <td>Week 7</td>
            <td>02/11/2020-06/11/2020</td>
        </tr>
    </table>
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


