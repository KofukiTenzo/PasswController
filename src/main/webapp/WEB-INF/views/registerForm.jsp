<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
<head>
    <title>Passwc</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="//resources//style.css" />">
</head>
<body>
<h1>Registration</h1>

<form method="post">
    User Name: <input type="text" name="username"/><br/>
    Password: <input type="text" name="password"/><br/>
    <input type="submit" name="Register">
</form>
</body>
</html>
