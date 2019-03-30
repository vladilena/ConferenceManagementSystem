<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 14:26
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
    <title><fmt:message key="text.title.login"/></title>
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

<!--ALARMS-->
<jstl:if test="${not empty requestScope.loginError}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.not.exist"/></div>
</jstl:if>

<!--CONTENT-->
<div class="row">
    <div class="col-4">
    </div>
    <div class="col-6">
        <br/>
        <br/>
        <form role="form" class="form-inline" method="post" action="${pageContext.request.contextPath}/controller?action=login">
            <div class="form-group">
                <label for="login"><fmt:message key="text.login"/></label>
                <input type="text" class="form-control" name="login" id="login"
                       placeholder="<fmt:message key="text.your.login"/>" required>
            </div>
            <div class="form-group">
                <label for="pass"><fmt:message key="text.password"/></label>
                <input type="password" class="form-control" name="password" id="pass"
                       placeholder="<fmt:message key="text.your.password"/>" required>
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="text.log.in"/></button>
        </form>
    </div>
    <div class="col-2">
    </div>
</div>
<!--FOOTER-->
<jsp:include page="template/footer.jsp"/>
</body>
</html>
