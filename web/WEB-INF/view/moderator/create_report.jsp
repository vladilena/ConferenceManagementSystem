<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 05.04.2019
  Time: 12:33
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
    <title><fmt:message key="text.title.create.report"/></title>
</head>
<body>
<!-- HEADER -->
<jsp:include page="../template/header.jsp"/>

<!--ALARMS-->
<jstl:if test="${not empty requestScope.invalid_participants}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.invalid.actual.participants"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.report_not_create}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.create.report"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.report_created}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.report.created"/></div>
</jstl:if>

<!--CONTENT-->
<div class="bg">
<div class="row">
    <div class="col-3"></div>
    <div class="col-6">

        <c:set var="report" value="${requestScope.report}" scope="request"/>

        <form role="form" method="post" action="${pageContext.request.contextPath}/conf?action=create_report">
            <div class="form-group row">
                <label for="title_ukr" class="col-sm-4 col-form-label"><fmt:message
                        key="text.conference.title.ukr"/></label>
                <div class="col-sm-8">
                    <input type="text" readonly class="form-control-plaintext" name="title_ukr" id="title_ukr"
                           value="${report.conferenceTitle}">
                </div>
            </div>
            <div class="form-group row">
                <label for="title_en" class="col-sm-4 col-form-label"><fmt:message
                        key="text.conference.title.en"/></label>
                <div class="col-sm-8">
                    <input type="text" readonly class="form-control-plaintext" name="title_en" id="title_en"
                           value="${report.conferenceTitleEn}">
                </div>
            </div>
            <div class="form-group row">
                <label for="date" class="col-sm-4 col-form-label"><fmt:message key="text.conference.dateTime"/></label>
                <div class="col-sm-8">
                    <input type="datetime-local" readonly class="form-control-plaintext" name="date_time" id="date"
                           value="${report.dateTime}">
                </div>
            </div>
            <div class="form-group row">
                <label for="lecturesAmount" class="col-sm-4 col-form-label"><fmt:message
                        key="text.report.provided.lectures"/></label>
                <div class="col-sm-8">
                    <input type="number" readonly class="form-control-plaintext" name="provided_lectures"
                           id="lecturesAmount"
                           value="${report.providedLecturesAmount}">
                </div>
            </div>
            <%--<div class="form-group row">--%>
                <%--<label for="registeredAmount" class="col-sm-4 col-form-label"><fmt:message--%>
                        <%--key="text.report.registered.amount"/></label>--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input type="number" readonly class="form-control-plaintext" name="registered_participants"--%>
                           <%--id="registeredAmount"--%>
                           <%--value="${report.registeredParticipantsAmount}">--%>
                <%--</div>--%>
            </div>
            <div class="form-group row">
                <label for="actualAmount" class="col-sm-4 col-form-label"><fmt:message
                        key="text.report.actual.amount"/></label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" name="actual_participants" id="actualAmount"
                           placeholder="<fmt:message key="text.input.actual.amount"/>" required>
                </div>
            </div>
            <button type="submit" class="btn btn-block btn-outline-dark" style="background-color: #5a6268; color: #c8cbcf"><fmt:message key="text.send"/></button>
        </form>
    </div>
    <div class="col-3"></div>
</div>
</div>

<!--FOOTER-->
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
