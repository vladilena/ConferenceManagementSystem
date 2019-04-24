<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 07.04.2019
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dt" uri="/WEB-INF/time_convertor.tld" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'uk_UA'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.main"/></title>
</head>

<body>
<%-- HEADER --%>
<jsp:include page="template/header.jsp"/>


<%-- ALARMS --%>
<jstl:if test="${not empty requestScope.success_registration}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.registration"/></div>
</jstl:if>

<%-- CONTENT --%>
<div class="bg">
    <div class="row">
        <div class="col-2">
        </div>
        <div class="col-9">
            <jstl:choose>
                <jstl:when test="${not empty sessionScope.user}">
                    <div id="accordion">
                        <div class="card">
                            <div class="card-header" id="headingOne">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" style="color: #1b1e21" data-toggle="collapse"
                                            data-target="#collapseOne"
                                            aria-expanded="false" aria-controls="collapseOne">
                                        <div class="text-justify"><h5><fmt:message
                                                key="text.ongoing"/></h5></div>
                                    </button>
                                </h5>
                            </div>
                            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                                 data-parent="#accordion">
                                <div class="card-body">
                                    <jstl:if test="${not empty requestScope.ongoing}">
                                        <section class="ongoing">
                                            <jstl:forEach items="${requestScope.ongoing}" var="conference">
                                                <jstl:choose>
                                                    <jstl:when test="${language.equals('uk_UA')}">
                                                        <ul class="container-fluid" style="border: #c8cbcf">
                                                            <li><b><fmt:message
                                                                    key="text.conference.title"/></b> ${conference.title}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.description"/></b> ${conference.description}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.dateTime"/></b>
                                                                <dt:dateFrm date="${conference.dateTime}"
                                                                            local="${language}"/>
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.place"/></b> ${conference.place}
                                                            </li>
                                                        </ul>
                                                    </jstl:when>
                                                    <jstl:when test="${language.equals('en_US')}">
                                                        <ul class="container-fluid" style="border: #c8cbcf">
                                                            <li><b><fmt:message
                                                                    key="text.conference.title"/></b> ${conference.titleEn}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.description"/></b> ${conference.descriptionEn}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.dateTime"/></b>
                                                                <dt:dateFrm date="${conference.dateTime}"
                                                                            local="${language}"/>
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.place"/></b> ${conference.placeEn}
                                                            </li>
                                                        </ul>
                                                    </jstl:when>
                                                </jstl:choose>
                                                <a class="btn-link btn-outline-dark btn-block"
                                                   style="border-color: #1b1e21; color: #1b1e21; background-color: #c8cbcf"
                                                   role="button"
                                                   href="${pageContext.request.contextPath}/conf?action=redirect_conference&conference_id=${conference.id}">
                                                    <div align="center"><fmt:message key="text.show.details"/></div>
                                                </a>
                                                <hr/>
                                            </jstl:forEach>
                                        </section>
                                    </jstl:if>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header" id="headingTwo">
                                <h5 class="mb-0">
                                    <button class="btn btn-link collapsed" style="color: #1b1e21" data-toggle="collapse"
                                            data-target="#collapseTwo" aria-expanded="false"
                                            aria-controls="collapseTwo">
                                        <div class="text-justify"><h5><fmt:message
                                                key="text.last"/></h5></div>
                                    </button>
                                </h5>
                            </div>
                            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                                 data-parent="#accordion">
                                <div class="card-body">
                                    <jstl:if test="${not empty requestScope.last}">
                                        <section class="last">
                                            <jstl:forEach items="${requestScope.last}" var="last_conference">
                                                <jstl:choose>
                                                    <jstl:when test="${language.equals('uk_UA')}">
                                                        <ul class="menu">
                                                            <li><b><fmt:message
                                                                    key="text.conference.title"/></b> ${last_conference.title}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.description"/></b> ${last_conference.description}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.dateTime"/></b>
                                                                <dt:dateFrm date="${last_conference.dateTime}"
                                                                            local="${language}"/>
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.place"/></b> ${last_conference.place}
                                                            </li>
                                                        </ul>
                                                    </jstl:when>
                                                    <jstl:when test="${language.equals('en_US')}">
                                                        <ul class="menu">
                                                            <li><b><fmt:message
                                                                    key="text.conference.title"/></b> ${last_conference.titleEn}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.description"/></b> ${last_conference.descriptionEn}
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.dateTime"/></b>
                                                                <dt:dateFrm date="${last_conference.dateTime}"
                                                                            local="${language}"/>
                                                            </li>
                                                            <li><b><fmt:message
                                                                    key="text.conference.place"/></b> ${last_conference.placeEn}
                                                            </li>
                                                        </ul>
                                                    </jstl:when>
                                                </jstl:choose>
                                                <a class="btn-link btn-outline-dark btn-block"
                                                   style="border-color: #1b1e21; color: #1b1e21; background-color: #c8cbcf"
                                                   role="button"
                                                   href="${pageContext.request.contextPath}/conf?action=redirect_conference&conference_id=${last_conference.id}">
                                                    <div align="center"><fmt:message key="text.show.details"/></div>
                                                </a>
                                                <hr/>
                                            </jstl:forEach>
                                        </section>
                                    </jstl:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </jstl:when>
                <jstl:otherwise>
                    <div class="jumbotron bg-transparent">
                        <h1 class="display-4"><fmt:message key="text.hello"/></h1>
                        <p class="lead"><fmt:message key="text.about.project"/></p>
                        <hr class="my-4">
                        <p><fmt:message key="text.need.be.registered"/></p>
                    </div>
                </jstl:otherwise>
            </jstl:choose>
        </div>
        <div class="col-1"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <br/>
</div>

<%--FOOTER--%>
<jsp:include page="template/footer.jsp"/>
</body>
</html>
