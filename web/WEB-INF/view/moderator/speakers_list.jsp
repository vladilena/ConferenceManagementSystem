<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 09.04.2019
  Time: 19:23
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
    <title><fmt:message key="text.title.profile"/></title>
</head>
<body>

<!-- HEADER -->
<jsp:include page="/WEB-INF/view/template/header.jsp"/>

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

<div class="bg">
    <div class="cust1">
    <jstl:forEach items="${requestScope.speakers}" var="speaker">
        <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6">
                        <div class="row">
                            <div class="col-sm-6 col-md-8">
                                <jstl:choose>
                                    <jstl:when test="${language == 'en_US'}">
                                        <h4>
                                                ${speaker.firstNameEn} ${speaker.lastNameEn}</h4>
                                    </jstl:when>
                                    <jstl:when test="${language == 'uk_UA'}">
                                        <h4>
                                                ${speaker.firstName} ${speaker.lastName}</h4>
                                    </jstl:when>
                                </jstl:choose>
                                <p>
                                    <i class="glyphicon glyphicon-envelope"></i>
                                    <fmt:message key="text.user.login"/>: ${speaker.login}
                                    <br/>
                                    <i class="glyphicon glyphicon-envelope"></i>
                                    <fmt:message key="text.user.email"/>: ${speaker.email}
                                    <br/>
                                    <i class="glyphicon glyphicon-gift"></i>
                                    <fmt:message key="text.user.rating"/>: ${speaker.rating} <fmt:message
                                        key="text.user.rating.unit"/>
                                    <br/>
                                    <i class="glyphicon glyphicon-gift"></i>
                                    <fmt:message key="text.user.bonus"/>: ${speaker.bonus} <fmt:message
                                        key="text.user.bonus.unit"/>
                                </p>
                            </div>
                            <form role="form" class="form-inline" method="post"
                                  action="${pageContext.request.contextPath}/conf?action=change_rating&speaker_id=${speaker.id}">
                                <input type="number" step=".01" class="form-control" name="rating" id="rating"
                                       placeholder="<fmt:message key="text.input.rating.for.change"/>" required>
                                <button type="submit" class="btn btn-dark" style="background-color: #5a6268">
                                    <fmt:message
                                            key="text.change.rating"/></button>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
        <br/>
    </jstl:forEach>
    </div>
</div>
<!--FOOTER-->
<jsp:include page="/WEB-INF/view/template/footer.jsp"/>
</body>
</html>
