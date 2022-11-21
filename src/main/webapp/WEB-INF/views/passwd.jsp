<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../../style.css" />">
</head>
<body>
<div class="passwdView">
    <jsp:useBean id="passwds" scope="request" type="com.projects.passwc.Passwds"/>
    <c:out value="${passwds.resource_n}" /></div>
    <div>
        <span class="passwdPass"><c:out value="${passwds.passwd}" /></span>
    </div>
</body>
</html>
