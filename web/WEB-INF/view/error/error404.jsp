<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title><fmt:message key="text.title.error"/> 404</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>
<body>
<%-- HEADER --%>
<jsp:include page="/WEB-INF/view/template/header.jsp"/>

<div class="row">
    <div class="col-3">
        <img src="/resources/img/404.jpg" class="img-responsive" alt="">
    </div>
    <div class="col-6">
        <div class="col-md text-center">
            <br/>
            <br/>
            <h2 class="uppercase"><fmt:message key="text.error"/> 404</h2>
        </div>
        <div class="col-3"></div>
    </div>
</div>
</body>
</html>
