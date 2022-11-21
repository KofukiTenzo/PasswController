<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="false" %>
<html>
<head>
    <title>PasswcUser</title>

    <link rel="stylesheet"
          type="text/css"
          href="<s:url value="../../style.css" />">

</head>
<body>

<div class="register-box">
    <h1>Registration</h1>

    <sf:form method="post" modelAttribute="passwcUser">

    <div class="user-box">
        <sf:label path="username"
                  cssErrorClass="error">Username</sf:label>:
        <sf:input path="username"/>
        <sf:errors path="username" element="errors" cssClass="error"/> <br/>
    </div>

    <div class="user-box">
        <sf:label path="email"
                  cssErrorClass="error">Email</sf:label>:
        <sf:input path="email"/>
        <sf:errors path="email" element="errors" cssClass="error"/> <br/>
        <div/>

        <div class="user-box">
            <sf:label path="passwd"
                      cssErrorClass="error">Password</sf:label>:
            <sf:password path="passwd"/>
            <sf:errors path="passwd" element="errors" cssClass="error"/> <br/>
        </div>

        <input type="submit" value="Register">
        </sf:form>
    </div>
</body>
</html>
