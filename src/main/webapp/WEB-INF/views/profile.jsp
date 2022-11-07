<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Your Profile</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../../resources/style.css" />">
</head>
<body>
<jsp:useBean id="PasswcUser" scope="request" type="com.projects.passwc.PasswcUser"/>
<c:out value="${PasswcUser.username}" /><br/>
<%--<c:out value="${PasswcUser.email}" />--%>
</body>
</html>
