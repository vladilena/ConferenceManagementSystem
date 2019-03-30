<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:50
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
    <title><fmt:message key="text.title.speakers.list"/></title>
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
<jsp:include page="../template/header.jsp"/>

<!--SIDEBAR-->
<jsp:include page="../template/sidebar.jsp"/>

<!--ALARMS-->
<jstl:if test="${not empty requestScope.not_change}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.not.change.rating"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.invalid_input}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.invalid.input.rating"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.success_change}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.success.change.rating"/></div>
</jstl:if>

<!--CONTENT-->
<jstl:forEach items="${requestScope.speakers}" var="speaker">
    <ul class="menu">
        <li><b><fmt:message key="text.user.login"/></b> ${speaker.login}</li>
        <li><b><fmt:message key="text.user.email"/></b> ${speaker.email}</li>
        <li><b><fmt:message key="text.user.first.name"/></b> ${speaker.firstName}</li>
        <li><b><fmt:message key="text.user.last.name"/></b> ${speaker.lastName}</li>
        <li><b><fmt:message key="text.user.rating"/></b> ${speaker.rating}</li>
    </ul>
    <form role="form" class="btn btn-success btn-sm" method="post"
          action="${pageContext.request.contextPath}/controller?action=change_rating">
        <input type="hidden" name="speaker_id" value="${speaker.id}">
        <input type="number" class="form-control" name="rating" id="rating"
               placeholder="<fmt:message key="text.input.rating.for.change"/>" required>
        <button type="submit" class="btn btn-success btn-sm"><fmt:message
                key="text.change.rating"/></button>
    </form>
</jstl:forEach>

<!--FOOTER-->
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
