<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 09.04.2019
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dt" uri="/WEB-INF/time_convertor.tld"%>

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
<c:set var="speaker" value="${requestScope.speaker}" scope="request"/>

<!-- ALARMS -->
<jstl:if test="${not empty requestScope.bonus_transfered}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.bonus.transfered"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.bonus_not_transfered}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.bonus.not.transfered"/></div>
</jstl:if>

<jstl:if test="${not empty requestScope.not_exist_speaker}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.not.exist.speaker"/></div>
</jstl:if>


<div class="bg">
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
                            <fmt:message key="text.user.rating"/>: ${speaker.rating} <fmt:message key="text.user.rating.unit"/>
                            <br/>
                            <i class="glyphicon glyphicon-gift"></i>
                            <fmt:message key="text.user.bonus"/>: ${speaker.bonus} <fmt:message key="text.user.bonus.unit"/>
                        </p>

                        <jstl:if test="${speaker.bonus > 0}">
                            <div class="btn-block">
                                <div align="center">
                                    <a class="btn btn-outline-dark btn-block"
                                       style="background-color: #5a6268; color: #c8cbcf" role="button"
                                       href="${pageContext.request.contextPath}/conf?action=get_bonus">
                                        <div align="center"><fmt:message key="text.get.bonus"/></div>
                                    </a>
                                </div>
                            </div>
                        </jstl:if>

                        <jstl:if test="${speaker.bonus == 0}">
                            <div class="btn-block" tabindex="0" data-toggle="tooltip"
                                 title="<fmt:message key="text.none.bonus"/>">
                                <div align="center">
                                    <a class="btn btn-outline-dark btn-block"
                                       style="background-color: #5a6268;pointer-events: none; color: #c8cbcf"
                                       role="button"
                                       aria-disabled="true">
                                        <div align="center"><fmt:message key="text.get.bonus"/></div>
                                    </a>
                                </div>
                            </div>
                        </jstl:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <jstl:if test="${not empty speaker.lectures}">
                <p><div class="text-center" style="background-color: #c8cbcf; color:  #1b1e21"><fmt:message key="text.my.lectures"/></div></p>
                <jstl:forEach items="${speaker.lectures}" var="lecture">
                    <jstl:choose>
                        <jstl:when test="${language.equals('uk_UA')}">
                            <li><b><fmt:message
                                    key="text.lecture.start.time"/></b>
                                <dt:dateFrm date="${lecture.startTime}" local="${language}"/></li>
                            <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.title}</li>
                            <li><b><fmt:message
                                    key="text.lecture.description"/></b> ${lecture.description}</li>
                        </jstl:when>
                        <jstl:when test="${language.equals('en_US')}">
                            <li><b><fmt:message
                                    key="text.lecture.start.time"/></b>
                                <dt:dateFrm date="${lecture.startTime}" local="${language}"/></li>
                            <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.titleEn}
                            </li>
                            <li><b><fmt:message
                                    key="text.lecture.description"/></b> ${lecture.descriptionEn}
                            </li>
                        </jstl:when>
                    </jstl:choose>
                    <hr/>
                </jstl:forEach>
            </jstl:if>
        </div>
            <div class="col-2"></div>
    </div>
    </div>
</div>

<!--FOOTER-->
<jsp:include page="/WEB-INF/view/template/footer.jsp"/>
</body>
</html>
