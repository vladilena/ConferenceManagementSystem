<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 10.04.2019
  Time: 11:04
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
    <title><fmt:message key="text.title.conference"/></title>
</head>
<body>
<%-- HEADER --%>
<jsp:include page="../template/header.jsp"/>

<%--ALARMS--%>
<jstl:if test="${not empty requestScope.success_update}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.update.conf"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_update_conf}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.update.conf"/></div>
</jstl:if>


<c:if test="${not empty requestScope.invalidData}">
    <div class="alert alert-danger capitalize">
        <strong><fmt:message key="text.alert.error"/></strong> <fmt:message key="text.alert.invalid-offer"/><br>
        <c:if test="${not empty requestScope.invalidData.invalidConfTitle}">
            -<fmt:message key="text.alert.invalid-title-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfTitleEn}">
            -<fmt:message key="text.alert.invalid-title-conf-en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfDescription}">
            -<fmt:message key="text.alert.invalid-description-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfDescriptionEn}">
            -<fmt:message key="text.alert.invalid-description-conf-en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfPlace}">
            -<fmt:message key="text.alert.invalid-place-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfPlaceEn}">
            -<fmt:message key="text.alert.invalid-place-conf-en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfDateTime}">
            -<fmt:message key="text.alert.invalid-data-time-conf"/><br>
        </c:if>
        <c:if test="${not empty requestScope.vinvalidData.invalidConfLecturesCapacity}">
            -<fmt:message key="text.alert.invalid-conf-lectures-capacity"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidConfPlaceCapacity}">
            -<fmt:message key="text.alert.invalid-conf-place-capacity"/><br>
        </c:if>
    </div>
</c:if>

<%--CONTENT--%>
<div class="bg">
    <div class="cust1">
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <c:set var="conference" value="${requestScope.conference}" scope="request"/>
                <form role="form" method="post"
                      action="${pageContext.request.contextPath}/conf?action=change_conference">
                    <div class="form-group">
                        <label for="title_ukr"><fmt:message key="text.conference.title.ukr"/></label>
                        <input type="text" class="form-control" name="title_ukr" id="title_ukr"
                               value="${conference.title}" required>
                    </div>
                    <div class="form-group">
                        <label for="title_en"><fmt:message key="text.conference.title.en"/></label>
                        <input type="text" class="form-control" name="title_en" id="title_en"
                               value="${conference.titleEn}" required>
                    </div>
                    <div class="form-group">
                        <label for="description_ukr"><fmt:message key="text.conference.description.ukr"/></label>
                        <input type="text" class="form-control" name="description_ukr" id="description_ukr"
                               value="${conference.description}" required>
                    </div>
                    <div class="form-group">
                        <label for="description_en"><fmt:message key="text.conference.description.en"/></label>
                        <input type="text" class="form-control" name="description_en" id="description_en"
                               value="${conference.descriptionEn}" required>
                    </div>
                    <div class="form-group">
                        <label for="place_ukr"><fmt:message key="text.conference.place.ukr"/></label>
                        <input type="text" class="form-control" name="place_ukr" id="place_ukr"
                               value="${conference.place}" required>
                    </div>
                    <div class="form-group">
                        <label for="place_en"><fmt:message key="text.conference.place.en"/></label>
                        <input type="text" class="form-control" name="place_en" id="place_en"
                               value="${conference.placeEn}" required>
                    </div>

                    <div class="form-group">
                        <label for="date"><fmt:message key="text.conference.dateTime"/></label>
                        <input type="datetime-local" class="form-control" name="date_time" id="date"
                               value="${conference.dateTime}" required>
                    </div>
                    <div class="form-group">
                        <label for="lecturesCapacity"><fmt:message key="text.conference.lecturesCapacity"/></label>
                        <input type="number" class="form-control" name="lectures_capacity" id="lecturesCapacity"
                               value="${conference.lecturesCapacity}" required>
                    </div>
                    <div class="form-group">
                        <label for="placeCapacity"><fmt:message key="text.conference.placeCapacity"/></label>
                        <input type="number" class="form-control" name="place_capacity" id="placeCapacity"
                               value="${conference.placeCapacity}" required>
                    </div>
                    <div class="btn-block">
                        <div align="center">
                            <input type="hidden" name="conference_id" value="${conference.id}">
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
                           href="${pageContext.request.contextPath}/conf?action=delete_conference&conference_id=${conference.id}">
                            <div align="center"><fmt:message key="text.delete.conference"/></div>
                        </a>
                    </div>
                </div>
                <br/>
                <br/>
                <br/>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</div>
<%--FOOTER--%>
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
