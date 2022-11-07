<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
<head>
    <title>Passwc</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="style.css" />">
</head>
<body>
<h1>Registration</h1>

<form method="post">
    User Name: <label>
    <input type="text" name="username"/>
</label><br/>
    Password: <label>
    <input type="text" name="password"/>
</label><br/>
    <input type="submit" name="Register">
</form>
</body>
</html>
