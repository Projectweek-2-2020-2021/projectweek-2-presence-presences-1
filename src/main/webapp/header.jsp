<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="#">Projectweek</a>
        <%--        toggle button--%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li ${param.actual eq 'index'? 'class="nav-item active"':'class="nav-item"'}>
                    <a class="nav-link" href="Controller?command=Index">Home</a>
                </li>
                <li ${param.actual eq 'studentLessen'? 'class="nav-item active"':'class="nav-item"'}>
                    <a class="nav-link" href="Controller?command=StudentLessen">Student lessen</a>
                </li>
                <li ${param.actual eq 'lectorLessen'? 'class="nav-item active"':'class="nav-item"'}>
                    <a class="nav-link" href="Controller?command=LectorLessen">Lector lessen</a>
                </li>
                <li ${param.actual eq 'lectorOverzichtStudenten'? 'class="nav-item active"':'class="nav-item"'}>
                    <a class="nav-link" href="Controller?command=LectorOverviewStudents">Studenten</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1 class="container" style="margin-bottom: 15px;">Presences 1 - Demonstratie</h1>
