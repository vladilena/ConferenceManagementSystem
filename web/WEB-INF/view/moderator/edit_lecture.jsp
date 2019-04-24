<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 10.04.2019
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.offer.lecture"/></title>
</head>
<body>
<%-- HEADER --%>
<jsp:include page="../template/header.jsp"/>

<%--ALARMS--%>
<jstl:if test="${not empty requestScope.success_update_lecture}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.update.lecture"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_update_lecture}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.update.lecture"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.lect_not_delete}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.delete.lecture"/></div>
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

<%--CONTENT--%>
<div class="bg">
    <div class="cust1">
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <c:set var="lecture" value="${requestScope.lecture}" scope="request"/>

                <form role="form" method="post"
                      action="${pageContext.request.contextPath}/conf?action=change_lecture">
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
                    <div class="form-group">
                        <label for="speaker"><fmt:message key="text.lecture.speaker"/></label>
                        <select class="form-control" name="speaker_id" id="speaker">
                            <c:forEach items="${requestScope.speakers}" var="speaker">
                                <c:choose>
                                    <c:when test="${language == 'en_US'}">
                                        <option value="${speaker.id}">${speaker.firstNameEn} ${speaker.lastNameEn}</option>
                                    </c:when>
                                    <c:when test="${language == 'uk_UA'}">
                                        <option value="${speaker.id}">${speaker.firstName} ${speaker.lastName}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <br/>
                    <div class="btn-block">
                        <div align="center">
                            <button type="submit" class="btn btn-block btn-outline-dark"
                                    style="background-color: #5a6268; color: #c8cbcf"><fmt:message
                                    key="text.save"/></button>
                        </div>
                    </div>
                </form>
                <div class="btn-block">
                    <div align="center">
                        <a class="btn btn-outline-dark btn-block"
                           style="background-color: #5a6268; color: #c8cbcf" role="button"
                           href="${pageContext.request.contextPath}/conf?action=delete_lecture&lecture_id=${lecture.id}">
                            <div align="center"><fmt:message key="text.delete.lecture"/></div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</div>

<%--FOOTER--%>
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
