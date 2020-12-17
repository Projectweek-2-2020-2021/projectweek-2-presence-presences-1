<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bevestig aanwezigheid</title>
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
    <p class="lead">Gelieve een opmerking toe te voegen.</p>
    <p>Student: <c:out value="${student.voornaam} ${student.naam}"/></p>
    <p>Datum: <c:out value="${datum}"/></p>
    <p>Vak: <c:out value="${les}"/></p>
    <form action="Controller?command=BevestigCommentaar&student=${student.rnummer}&vak=${les}&datum=${datum}"
          method="post" novalidate="novalidate">
        <div class="form-group">
            <label for="opmerking">Opmerking:</label>
            <textarea class="form-control" id="opmerking" name="opmerking" rows="3"
                      placeholder="Uw opmerking..."></textarea>
        </div>
        <div class="form-group">
            <button type="submit" name="bevestigen" value="bevestigen" class="btn btn-primary"
                    style="background-color: #343a40;">Bevestigen
            </button>
            <button type="submit" name="bevestigen" value="annuleren" class="btn btn-primary"
                    style="background-color: #343a40;">Annuleren
            </button>
        </div>
    </form>
</main>
</body>
</html>
