<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 07.04.2019
  Time: 13:33
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
    <title><fmt:message key="text.title.registration"/></title>
    <!-- Font Icon -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/fonts/material-icon/css/material-design-iconic-font.min.css"/>">
</head>
<body>
<!-- HEADER -->
<jsp:include page="template/header.jsp"/>

<!-- ALARMS -->
<jstl:if test="${not empty requestScope.not_add_user}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.registration"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.user_already_exists}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.already.exists"/></div>
</jstl:if>
<!--CONTENT-->

<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title"><fmt:message key="text.title.registration"/></h2>
                    <c:if test="${not empty requestScope.invalidData}">
                        <div class="alert alert-danger capitalize">
                            <strong><fmt:message key="text.alert.error"/></strong> <fmt:message
                                key="text.alert.invalid-data"/><br>
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
                    <br/><c:set var="in_user" value="${requestScope.invalid_user}" scope="request"/>
                    <form method="POST" class="register-form" id="register-form"
                          action="${pageContext.request.contextPath}/conf?action=register">
                        <div class="form-group">
                            <label for="login"><i class="zmdi zmdi-lock"></i></label>
                            <input type="text" class="form-control" name="login" id="login"
                                   value="${in_user.login}"
                                   placeholder="<fmt:message key="text.create.your.login"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="<fmt:message key="text.create.your.password"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <input type="email" class="form-control" name="email" id="email"
                                   value="${in_user.email}"
                                   placeholder="<fmt:message key="text.your.email"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="firstName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" class="form-control" name="firstName" id="firstName"
                                   value="${in_user.firstName}"
                                   placeholder="<fmt:message key="text.first.name"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="firstName_en"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" class="form-control" name="firstName_en" id="firstName_en"
                                   value="${in_user.firstNameEn}"
                                   placeholder="<fmt:message key="text.first.name.en"/>" required>
                        </div>
                        <div class="form-group">
                            <label for="lastName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" class="form-control" name="lastName" id="lastName"
                                   value="${in_user.lastName}"
                                   placeholder="<fmt:message key="text.last.name"/>" required>
                        </div>

                        <div class="form-group">
                            <label for="lastName_en"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" class="form-control" name="lastName_en" id="lastName_en"
                                   value="${in_user.lastNameEn}"
                                   placeholder="<fmt:message key="text.last.name.en"/>" required>
                        </div>
                            <div class="form-group">
                                <input type="radio" name="role" id="role1" class="radio" value="SPEAKER" required/>
                                <label for="role1"><span><span></span></span><fmt:message
                                        key="text.speaker"/></label>
                            </div>
                            <div class="form-group">
                                <input type="radio" name="role" id="role2" class="radio" value="USER" required/>
                                <label for="role2"><span><span></span></span><fmt:message
                                        key="text.user"/></label>
                            </div>
                        <%--<div class="form-group">--%>
                        <%--<div class="form-group-prepend">--%>
                        <%--<div class="input-group-text">--%>
                        <%--<input type="radio" aria-label="Radio button for following text input">--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<input type="text" class="form-control" aria-label="Text input with radio button">--%>
                        <%--</div>--%>

                        <p class="help-block"><fmt:message key="text.help.registration"/></p>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit"
                                   value="<fmt:message key="text.registration"/>"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="/resources/images/signup-image.jpg" alt="sign up image"></figure>
                </div>
            </div>
        </div>
    </section>
</div>


<!--FOOTER-->
<jsp:include page="/WEB-INF/view/template/footer.jsp"/>

</body>
</html>
