<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>PasswcUser</title>
    <link rel="stylesheet" type="text/css"
          href="<s:url value="/resources/style.css" />" >
</head>
<body>
<%--<h1>Hello <c:out value="${username}" /><br/></h1>--%>

<%--&lt;%&ndash;<c:out value="${username}" /><br/>&ndash;%&gt;--%>
<%--Youre email: <label>--%>
<%--    <c:out value="${passwcuser.email}"/>--%>
<%--</label><br/>--%>


<div>
    <h1>Your Profile</h1>
    <label class="username">Username:
        <c:out value="${username}" /><br/>
    </label>

    <label class="email">Email:
        <c:out value="${email}"/><br/>
    </label>
</div>

</body>
</html>
