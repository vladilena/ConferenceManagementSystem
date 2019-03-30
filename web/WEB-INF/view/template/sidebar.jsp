<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<div class="row">
    <div class="col-1"></div>
    <div class="col-10">

        <div class="menu-bar">
            <jstl:if test="${'SPEAKER' == sessionScope.user.role}">
                <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/controller?action=redirect_profile" role="button"><fmt:message
                        key="text.profile"/></a>
            </jstl:if>
            <jstl:if test="${'MODERATOR' == sessionScope.user.role}">
                <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/controller?action=redirect_speakers" role="button"><fmt:message
                        key="text.speakers.list"/></a>
                <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/controller?action=redirect_create_conference"
                   role="button"><fmt:message
                        key="text.create.conference"/></a>
            </jstl:if>

            <jstl:if test="${'USER' == sessionScope.user.role ||
'SPEAKER' == sessionScope.user.role ||
'MODERATOR' == sessionScope.user.role}">
                <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/controller?action=main"
                   role="button"><fmt:message
                        key="text.main"/></a>
            </jstl:if>
        </div>
    </div>


    <div class="col-1"></div>
</div>

