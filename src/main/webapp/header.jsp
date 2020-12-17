<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="Controller?command=Index">Projectweek hakkaton</a>
        <%--        toggle button--%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <%--        toggle button--%>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li ${param.actual eq 'Aanmelden'? 'class="nav-item active"':'class="nav-item"'}>
                    <a class="nav-link" href="Controller?command=Index">Home</a>
                </li>
                <c:if test="${rol == 'student'}">
                    <li ${param.actual eq 'Lesrooster' || param.actual eq 'Bevestig'? 'class="nav-item active"':'class="nav-item"'}>
                        <a class="nav-link" href="Controller?command=StudentLessen">Lesrooster</a>
                    </li>
                </c:if>
                <c:if test="${rol == 'lector'}">
                    <li ${param.actual eq 'Mijn lessen' || param.current eq 'studentOverzicht' || param.actual eq 'Opmerking'? 'class="nav-item active"':'class="nav-item"'}>
                        <a class="nav-link" href="Controller?command=LectorLessen">Mijn lessen</a>
                    </li>
                    <c:if test="${loggedIn.stc == true}">
                        <li>
                            <a class="nav-link" href="Controller?command=StcStudentenOverzicht">Stc studenten</a>
                        </li>
                    </c:if>

                </c:if>
                <c:if test="${not empty loggedIn}">
                    <li>
                        <form style="margin-bottom: 0;" action="Controller?command=Afmelden" method="POST" novalidate>
                            <button type="submit" class="btn btn-primary" style="background-color: #343a40;"><strong>Afmelden</strong>
                            </button>
                        </form>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<h1 class="container" style="margin-bottom: 15px;">Presence 1 - <c:out value="${param.actual}"/></h1>
