<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 29.03.2019
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<html>
<head>
    <title><fmt:message key="text.title.error.permission"/></title>
</head>
<body>
<div class="row">
    <div class="col-md text-center">
        <br/>
        <br/>
        <h2 class="uppercase"><fmt:message key="text.title.error.permission"/></h2>
    </div>
</div>
</body>
</html>