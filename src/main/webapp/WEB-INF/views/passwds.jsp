<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Recent Passwds</title>
</head>
<body>
<div class="listTitle">
    <h1>Recent Passwds</h1>
    <ul class="passwdList">
        <c:forEach items="${passwdList}" var="passwd">
            <li id="spittle_<c:out value="spittle.id"/>">
                <div class="passwcName"><c:out value="${passwd.name}" /></div>
                <div class="passwcPasswd"><c:out value="${passwd.passwd}" /></div>
                <div>
                    <span class="passwcTime"><c:out value="${passwd.date}" /></span>
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