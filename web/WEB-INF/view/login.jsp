<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 05.04.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.login"/></title>
    <!-- Font Icon -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/fonts/material-icon/css/material-design-iconic-font.min.css"/>">
</head>
<body>
<!-- HEADER -->
<jsp:include page="template/header.jsp"/>

<!--ALARMS-->
<jstl:if test="${not empty requestScope.loginError}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.not.exist"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.user_already_login}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.already.login"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.user_already_exists}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.already.exists"/></div>
</jstl:if>
<!--CONTENT-->
<div class="main">

    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="/resources/images/signin-image.jpg" alt="sign up image"></figure>
                </div>
                <div class="signin-form">
                    <h2 class="form-title"><fmt:message key="text.title.login"/></h2>
                    <form method="POST" class="register-form" id="login-form" action="${pageContext.request.contextPath}/conf?action=login">

                        <div class="form-group">
                            <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" class="form-control" name="login" id="login"
                                   placeholder="<fmt:message key="text.your.login"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" class="form-control" name="password" id="pass"
                                   placeholder="<fmt:message key="text.your.password"/>" required>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="<fmt:message key="text.log.in"/>"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<!--FOOTER-->
<jsp:include page="/WEB-INF/view/template/footer.jsp"/>

</body>
</html>
