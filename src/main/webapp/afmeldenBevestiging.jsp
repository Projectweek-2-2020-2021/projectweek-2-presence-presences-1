<%--
  Created by IntelliJ IDEA.
  User: Maarten
  Date: 15/12/2020
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Presence 1 - Bevestig aanwezigheid</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="Afmelden"/>
</jsp:include>
<main class="container">
    <p class="lead">Bent u zeker om u af te melden?</p>
    <form action="Controller?command=AfmeldenBevestiging" method="post"
          novalidate="novalidate">
        <div class="form-group">
            <button type="submit" name="beslissing" value="ja" class="btn btn-primary"
                    style="background-color: #343a40;">Ja
            </button>
        </div>
        <div class="form-group">
            <button type="submit" name="beslissing" value="neen" class="btn btn-primary"
                    style="background-color: #343a40;">Neen
            </button>
        </div>
    </form>
</main>
</body>
</html>
