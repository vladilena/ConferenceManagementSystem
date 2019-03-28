<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:40
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
    <title><fmt:message key="text.title.profile"/></title>
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

<!--CONTENT-->
<div class="h3"><fmt:message key="text.my.profile"/></div>
<hr/>
<div class="row">
<div class="col-2"></div>
<div class="col-8">
<jstl:forEach items="${sessionScope.user}" var="user">
    <ul class="menu">
    <li><b><fmt:message key="text.user.login"/></b> ${user.login}</li>
    <li><b><fmt:message key="text.user.email"/></b> ${user.email}</li>
    <li><b><fmt:message key="text.user.first.name"/></b> ${user.firstName}</li>
    <li><b><fmt:message key="text.user.last.name"/></b> ${user.lastName}</li>
    <li><b><fmt:message key="text.user.rating"/></b> ${user.rating}</li>
    </ul>
</jstl:forEach>
    </div>
    <div class="col-2"></div>

    </div>


    <!--FOOTER-->
    <jsp:include page="template/footer.jsp"/>
    </body>
    </html>
