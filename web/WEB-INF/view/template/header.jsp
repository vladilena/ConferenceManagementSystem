<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <div class="row">
            <div class="col-md text-center">
                <p class="text-center"><fmt:message
                        key="text.hello"/> ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
                    <a class="btn btn-info" href="controller?action=logout" role="button"><fmt:message
                            key="text.logout"/></a>
                <a class="btn btn-info" href="?language=en" role="button"><fmt:message
                        key="text.lang.eng"/></a>
                <a class="btn btn-info" href="?language=ukr" role="button"><fmt:message
                        key="text.lang.ukr"/></a>
                </div>
                <br/>
            </div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-md text-center">
                <div class="menu-bar">
                    <a class="btn btn-info" href="controller?action=redirect_registration" role="button"><fmt:message key="text.registration"/></a>
                    <a class="btn btn-info" href="controller?action=redirect_login" role="button"><fmt:message key="text.log.in"/></a></div>
                <a class="btn btn-info" href="?language=en" role="button"><fmt:message
                        key="text.lang.eng"/></a>
                <a class="btn btn-info" href="?language=ukr" role="button"><fmt:message
                        key="text.lang.ukr"/></a>
            </div>
        </div>
    </c:otherwise>
</c:choose>
