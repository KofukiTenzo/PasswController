<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Recent Passwds</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../../resources/style.css" />">
</head>
<body>
<div class="listTitle">
    <h1>Recent Passwds</h1>
    <ul class="passwdList">
        <c:forEach items="${passwdList}" var="passwds">
            <li id="passwd_<c:out value="passwd.id"/>">
                <div class="passwdName"><c:out value="${passwds.name}" /></div>
                <div class="passwdPasswd"><c:out value="${passwds.passwd}" /></div>
                <div>
                    <span class="passwdTime"><c:out value="${passwds.date}" /></span>
                </div>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${fn:length(passwdList) gt 20}">
        <hr />
        <s:url value="/passwds?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
    </c:if>
</div>
</body>
</html>