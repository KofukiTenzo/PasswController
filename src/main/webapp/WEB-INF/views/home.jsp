<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
    <head>
        <title>Passwc</title>
        <link rel="stylesheet"
            type="text/css"
            href="<c:url value="/resources/style.css" />">
    </head>
    <body>
        <h1>Welcome to Passwc</h1>

        <a href="<c:url value="/passwds" />">Passwds</a> |
        <a href="<c:url value="/passwcUser/register" />">Register</a>
    </body>
</html>
