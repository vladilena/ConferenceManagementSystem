<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.registration"/></title>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"--%>
          <%--integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"--%>
            <%--integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"--%>
            <%--crossorigin="anonymous"></script>--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/js" href="<c:url value="/resources/js/bootstrap.js"/>">
</head>
<body>
<!-- HEADER -->
<jsp:include page="template/header.jsp"/>

<!-- ALARMS -->
<jstl:if test="${not empty requestScope.not_add_user}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.registration"/></div>
</jstl:if>
<!--CONTENT-->

<div class="row">
    <div class="col-3"></div>
    <div class="col-6">
        <br/>
        <c:if test="${not empty requestScope.invalidData}">
            <div class="alert alert-danger capitalize">
                <strong><fmt:message key="text.alert.error"/></strong> <fmt:message key="text.alert.invalid-data"/><br>
                <c:if test="${not empty requestScope.invalidData.invalidLogin}">
                    -<fmt:message key="text.alert.invalid-login"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidEmail}">
                    -<fmt:message key="text.alert.invalid-email"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidPassword}">
                    -<fmt:message key="text.alert.invalid-pass"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidFirstName}">
                    -<fmt:message key="text.alert.invalid-first.name"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidLastName}">
                    -<fmt:message key="text.alert.invalid-last.name"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidFirstNameEn}">
                    -<fmt:message key="text.alert.invalid-first.name.en"/><br>
                </c:if>
                <c:if test="${not empty requestScope.invalidData.invalidLastNameEn}">
                    -<fmt:message key="text.alert.invalid-last.name.en"/><br>
                </c:if>
            </div>
        </c:if>

        <br/>
        <form role="form" method="post" action="${pageContext.request.contextPath}/controller?action=register">
            <div class="form-group ${requestScope.invalidData.invalidLogin} has-feedback">
                <label for="login"><fmt:message key="text.login"/></label>
                <input type="text" class="form-control" name="login" id="login"
                       placeholder="<fmt:message key="text.create.your.login"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidPassword} has-feedback">
                <label for="password"><fmt:message key="text.password"/></label>
                <input type="password" class="form-control" name="password" id="password"
                       placeholder="<fmt:message key="text.create.your.password"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidEmail} has-feedback">
                <label for="email"><fmt:message key="text.email"/></label>
                <input type="email" class="form-control" name="email" id="email"
                       placeholder="<fmt:message key="text.your.email"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidFirstName} has-feedback">
                <label for="firstName"><fmt:message key="text.first.name"/></label>
                <input type="text" class="form-control" name="firstName" id="firstName"
                       placeholder="<fmt:message key="text.first.name"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidFirstNameEn} has-feedback">
                <label for="firstName_en"><fmt:message key="text.first.name.en"/></label>
                <input type="text" class="form-control" name="firstName_en" id="firstName_en"
                       placeholder="<fmt:message key="text.first.name.en"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidLastName} has-feedback">
                <label for="lastName"><fmt:message key="text.last.name"/></label>
                <input type="text" class="form-control" name="lastName" id="lastName"
                       placeholder="<fmt:message key="text.last.name"/>" required>
            </div>
            <div class="form-group ${requestScope.invalidData.invalidLastNameEn} has-feedback">
                <label for="lastName_en"><fmt:message key="text.last.name.en"/></label>
                <input type="text" class="form-control" name="lastName_en" id="lastName_en"
                       placeholder="<fmt:message key="text.last.name.en"/>" required>
            </div>

            <label class="radio-inline">
                <input type="radio" name="role" id="radio1" value="SPEAKER" required> Speaker
            </label>
            <label class="radio-inline">
                <input type="radio" name="role" id="radio2" value="USER" required> User
            </label>
            <p class="help-block"><fmt:message key="text.help.registration"/></p>
            <button type="submit" class="btn btn-success"><fmt:message key="text.registration"/></button>

        </form>

    </div>
    <div class="col-3"></div>
</div>
<!--FOOTER-->
<jsp:include page="template/footer.jsp"/>
</body>

</html>
