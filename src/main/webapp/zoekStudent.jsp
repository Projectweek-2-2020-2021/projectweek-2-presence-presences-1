<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bevestig aanwezigheid</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="Zoeken"/>
</jsp:include>
<main class="container">
    <p class="lead">Gelieve een r-Nummer te geven.</p>
    <c:if test="${not empty error}">
        <div class="row">
            <div class="alert alert-danger" role="alert">
                <ul>
                    <li><c:out value="${error}"/></li>
                </ul>
            </div>
        </div>
    </c:if>
    <form action="Controller?command=ZoekStudent"
          method="post" novalidate="novalidate">
        <div class="form-group">
            <label for="zoekOpdracht">Zoeken:</label>
            <input type="text" id="zoekOpdracht" name="zoekOpdracht" placeholder="r-Nummer...">
        </div>
        <div class="form-group">
            <button type="submit" name="bevestigen" value="bevestigen" class="btn btn-primary"
                    style="background-color: #343a40;">Bevestigen
            </button>
        </div>
    </form>
</main>
</body>
</html>
