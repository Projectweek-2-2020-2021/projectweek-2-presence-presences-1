<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countries - bootstrap</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <main>
        <div class="row">
            <article class="col-sm-8 ">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th>Name</th>
                            <th>Capital</th>
                            <th class="number">Inhabitants</th>
                            <th class="number">Votes</th>
                        </tr>
                        <c:forEach items="${countries}" var="country">
                            <tr>
                                <td>${country.name}</td>
                                <td>${country.capital}</td>
                                <td class="number">${country.numberInhabitants}</td>
                                <td class="number">${country.votes}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </article>
            <aside class="col-sm-4">
                <c:if test="${not empty popular}">
                    <p id="mostPopularCountry">The most popular country is
                            ${popular}.</p>
                </c:if>
            </aside>
        </div>
    </main>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>