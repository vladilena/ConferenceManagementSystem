<?xml version="1.0" encoding="UTF-8" ?>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <div class="row">
            <div class="col-md text-center">
                <p class="text-center"><fmt:message
                        key="text.hello"/> ${sessionScope.user.firstName} ${sessionScope.user.lastName}</p>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/controller?action=logout" role="button"><fmt:message
                            key="text.logout"/></a>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="en_US">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="text.lang.eng"/></button>
                </form>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="uk_UA">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="text.lang.ukr"/></button>
                </form>
                </div>
                <br/>
            </div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-md text-center">
                <div class="menu-bar">
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/controller?action=redirect_registration" role="button"><fmt:message key="text.registration"/></a>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/controller?action=redirect_login" role="button"><fmt:message key="text.log.in"/></a></div>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="en_US">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="text.lang.eng"/></button>
                </form>
                <form role="form" class="btn btn-info btn-sm" method="post"
                      action="${pageContext.request.contextPath}/controller?action=change_language">
                    <input type="hidden" name="language" value="uk_UA">
                    <button type="submit" class="btn btn-info btn-sm"><fmt:message
                            key="text.lang.ukr"/></button>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>
