<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="passwdView">
    <div class="spittleMessage"><c:out value="${passwds.nameOfResource}" /></div>
    <div>
        <span class="spittleTime"><c:out value="${passwds.passwd}" /></span>
    </div>
</div>
</body>
</html>
