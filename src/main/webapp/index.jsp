<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Presence 1 - Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="actual" value="Aanmelden"/>
</jsp:include>

<main class="container">
    <p class="lead">Welkom bij de demonstratie hoofdpagina! <br>
        Gelieve u aan te melden hieronder. </p>
    <c:if test="${not empty notAuthorizedError}">
        <div class="row">
            <div class="alert alert-danger" role="alert">
                <ul>
                    <li><c:out value="${notAuthorizedError}"/></li>
                </ul>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty errors}">
        <div class="alert alert-danger" role="alert">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li><c:out value="${error}"/></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${not empty loggedIn}">
            <p>Welkom, <c:out value="${loggedIn.voornaam}"/>!</p>
        </c:when>
        <c:otherwise>
            <form action="Controller?command=Aanmelden" method="POST" novalidate>
                <div class="form-group">
                    <label for="gebruikersnaam">ID:</label>
                    <input type="text" class="form-control" style="width: auto" id="gebruikersnaam"
                           name="gebruikersnaam" placeholder="rxxxxxxx" required>
                    <small id="gebruikersnaamHulp" class="form-text text-muted">r- of u-nummer</small>
                </div>
                <div class="form-group">
                    <label for="wachtwoord">Wachtwoord:</label>
                    <input type="password" class="form-control" style="width: auto" id="wachtwoord" name="wachtwoord"
                           required>
                </div>
                <button type="submit" class="btn btn-primary">Aanmelden</button>
            </form>
        </c:otherwise>
    </c:choose>
</main>
</body>
</html>
