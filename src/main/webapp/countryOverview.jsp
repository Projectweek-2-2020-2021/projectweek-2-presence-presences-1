<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/sample.css">
    <meta charset="UTF-8">
    <title>Countries</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main id="container">
    <article>
        <c:if test="${not empty popular}">


            <p id="mostPopularCountry">
                The most popular country is
                    ${popular}.
            </p>
        </c:if>
        <c:if test="${not empty countries}">


            <table id="overview">
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
        </c:if>
    </article>
    <jsp:include page="footer.jsp"/>
</main>

</body>
</html>