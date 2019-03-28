<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jstl:set var="language" value="${pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.create.conference"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<!-- HEADER -->
<jsp:include page="template/header.jsp"/>

<!--SIDEBAR-->
<jsp:include page="template/sidebar.jsp"/>


<!--ALARMS-->
<jstl:if test="${not empty requestScope.success_offer}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.create.conf"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_add_conf}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.create.conf"/></div>
</jstl:if>
<c:if test="${not empty invalidData}">
    <div class="alert alert-danger capitalize">
        <strong><fmt:message key="text.alert.error"/></strong> <fmt:message key="text.alert.invalid-offer"/><br>
        <c:if test="${not empty invalidData.invalidConfTitle}">
            -<fmt:message key="text.alert.invalid-title-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfTitleEn}">
            -<fmt:message key="text.alert.invalid-title-conf-en"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfDescription}">
            -<fmt:message key="text.alert.invalid-description-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfDescriptionEn}">
            -<fmt:message key="text.alert.invalid-description-conf-en"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfPlace}">
            -<fmt:message key="text.alert.invalid-place-conf-ukr"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfPlaceEn}">
            -<fmt:message key="text.alert.invalid-place-conf-en"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfDateTime}">
            -<fmt:message key="text.alert.invalid-data-time-conf"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfLecturesCapacity}">
            -<fmt:message key="text.alert.invalid-conf-lectures-capacity"/><br>
        </c:if>
        <c:if test="${not empty invalidData.invalidConfPlaceCapacity}">
            -<fmt:message key="text.alert.invalid-conf-place-capacity"/><br>
        </c:if>
    </div>
</c:if>

<!--CONTENT-->
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <form role="form" method="post" action="controller?action=create_conference">
            <div class="form-group">
                <label for="title_ukr"><fmt:message key="text.conference.title.ukr"/></label>
                <input type="text" class="form-control" name="title_ukr" id="title_ukr"
                       placeholder="<fmt:message key="text.input.conference.title.ukr"/>" required>
            </div>
            <div class="form-group">
                <label for="title_en"><fmt:message key="text.conference.title.en"/></label>
                <input type="text" class="form-control" name="title_en" id="title_en"
                       placeholder="<fmt:message key="text.input.conference.title.en"/>" required>
            </div>
            <div class="form-group">
                <label for="description_ukr"><fmt:message key="text.conference.description.ukr"/></label>
                <input type="text" class="form-control" name="description_ukr" id="description_ukr"
                       placeholder="<fmt:message key="text.input.conference.description.ukr"/>" required>
            </div>
            <div class="form-group">
                <label for="description_en"><fmt:message key="text.conference.description.en"/></label>
                <input type="text" class="form-control" name="description_en" id="description_en"
                       placeholder="<fmt:message key="text.input.conference.description.en"/>" required>
            </div>
            <div class="form-group">
                <label for="place_ukr"><fmt:message key="text.conference.place.ukr"/></label>
                <input type="text" class="form-control" name="place_ukr" id="place_ukr"
                       placeholder="<fmt:message key="text.input.conference.place.ukr"/>" required>
            </div>
            <div class="form-group">
                <label for="place_en"><fmt:message key="text.conference.place.en"/></label>
                <input type="text" class="form-control" name="place_en" id="place_en"
                       placeholder="<fmt:message key="text.input.conference.place.en"/>" required>
            </div>

            <div class="form-group">
                <label for="date"><fmt:message key="text.conference.dateTime"/></label>
                <input type="datetime-local" class="form-control" name="date_time" id="date"
                       placeholder="<fmt:message key="text.input.conference.dateTime"/>" required>
            </div>
            <div class="form-group">
                <label for="lecturesCapacity"><fmt:message key="text.conference.lecturesCapacity"/></label>
                <input type="number" class="form-control" name="lectures_capacity" id="lecturesCapacity"
                       placeholder="<fmt:message key="text.input.conference.lecturesCapacity"/>" required>
            </div>
            <div class="form-group">
                <label for="placeCapacity"><fmt:message key="text.conference.placeCapacity"/></label>
                <input type="number" class="form-control" name="place_capacity" id="placeCapacity"
                       placeholder="<fmt:message key="text.input.conference.placeCapacity"/>" required>
            </div>

            <button type="submit" class="btn btn-success"><fmt:message key="text.create"/></button>
        </form>
    </div>
    <div class="col-3"></div>
</div>




<!--FOOTER-->
<jsp:include page="template/footer.jsp"/>
</body>
</html>
