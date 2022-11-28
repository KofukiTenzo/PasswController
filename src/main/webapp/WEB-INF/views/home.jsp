<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
    <title>Passwc</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../../style.css" />" >

</head>
<body>
<h1><s:message code="passwc.welcome"/></h1>

<s:url value="/user/register" var="registerUrl" />

<a href="<s:url value="/passwds" />">Passwds</a>
<a href="${registerUrl}">Register</a>

</body>
</html>
