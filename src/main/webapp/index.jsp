<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <title>Tourism</title>
    <link rel="stylesheet" href="css/sample.css">
</head>
<body>
<header role="banner">
    <img alt="Toscane" src="images/toscaneRibbon.jpg">
</header>
<main>
    <article>
        <c:if test="${error != null}">
            <p>Er werd een fout opgegooid in Servlet.</p>
            <p>Message: ${error}.</p>
            <p>Stacktrace:</p>
            <ul>
                <c:forEach items="${stacktrace}" var="s">
                    <li>${s}</li>
                </c:forEach>
            </ul>
        </c:if>
        <h1>Sweet Holidays</h1>
        <p>We <em>know</em> how busy you are. We <em>know</em> how important your holiday is for you.
            That's why we want to help you in making the right choice.</p>
        <p>This site offers you information about countries.
            To make sure you choose your ideal destination.</p>
        <p>
            <a href="Controller?command=Overview">Country overview</a>
        </p>
    </article>
</main>
</body>
</html>