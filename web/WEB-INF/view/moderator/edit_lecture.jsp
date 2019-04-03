<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:15
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
    <title><fmt:message key="text.title.offer.lecture"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>
<body>
<!-- HEADER -->
<jsp:include page="../template/header.jsp"/>

<!--SIDEBAR-->
<jsp:include page="../template/sidebar.jsp"/>

<!--ALARMS-->
<jstl:if test="${not empty requestScope.success_update_lecture}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.update.lecture"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_update_lecture}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.update.lecture"/></div>
</jstl:if>


<c:if test="${not empty requestScope.invalidData}">
    <div class="alert alert-danger capitalize">
        <strong><fmt:message key="text.alert.error"/></strong> <fmt:message key="text.alert.invalid-offer"/><br>
        <c:if test="${not empty requestScope.invalidData.invalidTitle}">
            -<fmt:message key="text.alert.invalid-title"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidTitleEn}">
            -<fmt:message key="text.alert.invalid-title_en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidDescription}">
            -<fmt:message key="text.alert.invalid-description"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidDescriptionEn}">
            -<fmt:message key="text.alert.invalid-description_en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidLectureStartTime}">
            -<fmt:message key="text.alert.invalid-start_time"/><br>
        </c:if>

    </div>
</c:if>

<!--CONTENT-->
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">


        <c:set var="lecture" value="${requestScope.lecture}" scope="request" />

        <form role="form" method="post" action="${pageContext.request.contextPath}/controller?action=change_lecture">
            <input type="hidden" name="lecture_id" value="${lecture.id}">
            <input type="hidden" name="conference_id" value="${lecture.mainConference.id}">
            <div class="form-group">
                <label for="start_time"><fmt:message key="text.lecture.date_time"/></label>
                <input type="datetime-local" class="form-control" name="start_time" id="start_time"
                       value="${lecture.startTime}" required>
            </div>
            <div class="form-group">
                <label for="title"><fmt:message key="text.lecture.title"/></label>
                <input type="text" class="form-control" name="title" id="title"
                       value="${lecture.title}" required>
            </div>
            <div class="form-group">
                <label for="title_en"><fmt:message key="text.lecture.title.en"/></label>
                <input type="text" class="form-control" name="title_en" id="title_en"
                       value="${lecture.titleEn}" required>
            </div>
            <div class="form-group">
                <label for="desc"><fmt:message key="text.lecture.description"/></label>
                <input type="text" class="form-control" name="description" id="desc"
                       value="${lecture.description}" required>
            </div>
            <div class="form-group">
                <label for="desc_en"><fmt:message key="text.lecture.description.en"/></label>
                <input type="text" class="form-control" name="description_en" id="desc_en"
                       value="${lecture.descriptionEn}" required>
            </div>
            <select class="form-control" name="speaker_id">
                <c:forEach items="${requestScope.speakers}" var="speaker">
                    <c:choose>
                        <c:when test="${language == 'en_US'}">
                            <option value="${speaker.id}">${speaker.firstNameEn} ${speaker.lastNameEn}</option>
                        </c:when>
                        <c:when test="${language == 'uk_UA'}">
                            <option value="${speaker.id}">${speaker.firstName} ${speaker.lastName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${speaker.id}">${speaker.firstName} ${speaker.lastName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <button type="submit" class="btn btn-success"><fmt:message key="text.save"/></button>
        </form>

        <form role="form" class="btn btn-danger btn-sm" method="post"
              action="${pageContext.request.contextPath}/controller?action=delete_lecture">
            <input type="hidden" name="lecture_id" value="${lecture.id}">
            <button type="submit" class="btn btn-danger btn-sm"><fmt:message
                    key="text.delete.lecture"/></button>
        </form>
    </div>
    <div class="col-3"></div>
</div>
<!--FOOTER-->
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
