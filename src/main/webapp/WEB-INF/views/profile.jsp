<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>PasswcUser</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../../resources/style.css" />">
</head>
<body>
<%--<h1>Hello <c:out value="${username}" /><br/></h1>--%>

<%--&lt;%&ndash;<c:out value="${username}" /><br/>&ndash;%&gt;--%>
<%--Youre email: <label>--%>
<%--    <c:out value="${passwcuser.email}"/>--%>
<%--</label><br/>--%>

<div id="header" th:include="page :: header"></div>

<div id="content">
    <h1>Your Profile</h1>
    <c:out value="${username}" /><br/>
    <c:out value="${email}"/>
</div>

<div id="footer" th:include="page :: copy"></div>
</body>
</html>
