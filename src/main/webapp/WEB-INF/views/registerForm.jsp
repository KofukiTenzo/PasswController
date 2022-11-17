<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="false" %>
<html>
<head>
    <title>PasswcUser</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="style.css" />">
</head>
<body>
<h1>Registration</h1>

<sf:form method="post" modelAttribute="passwcUser">
    <sf:label path="username">User Name</sf:label>:
        <sf:input path="username" />
            <sf:errors path="username" cssClass="error" /> <br/>

    <sf:label path="email">Email</sf:label>:
        <sf:input path="email" type="email"/>
            <sf:errors path="email" cssClass="error" /> <br/>

    <sf:label path="passwd">Passwd</sf:label>:
        <sf:password path="passwd" />
            <sf:errors path="passwd" cssClass="error" /> <br/>

    <input type="submit" value="Register">
</sf:form>
</body>
</html>
