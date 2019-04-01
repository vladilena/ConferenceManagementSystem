<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.profile"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>
<body>
<!-- HEADER -->
<jsp:include page="../template/header.jsp"/>

<!--SIDEBAR-->
<jsp:include page="../template/sidebar.jsp"/>

<!--CONTENT-->
<div class="h3"><fmt:message key="text.my.profile"/></div>
<hr/>
<div class="row">
<div class="col-2"></div>
<div class="col-8">
    <c:set var="speaker" value="${requestScope.speaker}" scope="request"/>
    <jstl:choose>
        <jstl:when test="${language == 'uk_UA'}">
            <ul class="menu">
                <li><b><fmt:message key="text.user.login"/></b> ${speaker.login}</li>
                <li><b><fmt:message key="text.user.email"/></b> ${speaker.email}</li>
                <li><b><fmt:message key="text.user.first.name"/></b> ${speaker.firstName}</li>
                <li><b><fmt:message key="text.user.last.name"/></b> ${speaker.lastName}</li>
                <li><b><fmt:message key="text.user.rating"/></b> ${speaker.rating}</li>
            </ul>
        </jstl:when>
        <jstl:when test="${language == 'en_US'}">
            <ul class="menu">
                <li><b><fmt:message key="text.user.login"/></b> ${speaker.login}</li>
                <li><b><fmt:message key="text.user.email"/></b> ${speaker.email}</li>
                <li><b><fmt:message key="text.user.first.name"/></b> ${speaker.firstNameEn}</li>
                <li><b><fmt:message key="text.user.last.name"/></b> ${speaker.lastNameEn}</li>
                <li><b><fmt:message key="text.user.rating"/></b> ${speaker.rating}</li>
            </ul>
        </jstl:when>
        <jstl:otherwise>
            <ul class="menu">
                <li><b><fmt:message key="text.user.login"/></b> ${speaker.login}</li>
                <li><b><fmt:message key="text.user.email"/></b> ${speaker.email}</li>
                <li><b><fmt:message key="text.user.first.name"/></b> ${speaker.firstName}</li>
                <li><b><fmt:message key="text.user.last.name"/></b> ${speaker.lastName}</li>
                <li><b><fmt:message key="text.user.rating"/></b> ${speaker.rating}</li>
            </ul>
        </jstl:otherwise>
    </jstl:choose>

    </div>
    <div class="col-2"></div>

    </div>


    <!--FOOTER-->
    <jsp:include page="../template/footer.jsp"/>
    </body>
    </html>
