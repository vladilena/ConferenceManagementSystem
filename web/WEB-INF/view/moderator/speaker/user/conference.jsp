<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 09.04.2019
  Time: 10:33
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
    <title><fmt:message key="text.title.conference"/></title>
</head>
<body>
<!-- HEADER -->
<jsp:include page="../../../template/header.jsp"/>

<!--ALARMS-->
<jstl:if test="${not empty requestScope.success_subscribe}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.subscribe"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.already_subscribed}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.already.exists"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_approved}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.approved"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_enough_lect}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.enough.lect"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_enough_places}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.enough.places"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.conf_not_delete}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.delete.conf"/></div>
</jstl:if>


<!--CONTENT-->
<div class="bg">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <jstl:if test="${not empty requestScope.conference}">
                <c:set var="conference" value="${requestScope.conference}" scope="request"/>
                <c:set var="current" value="${pageContext.session.creationTime}" scope="session"/>

                <div class="text-justify" style="background-color: #c8cbcf"><h5><fmt:message
                        key="text.conference"/></h5></div>
                <ul class="container-fluid" style="border: #c8cbcf">
                    <jstl:choose>
                        <jstl:when test="${language == 'uk_UA'}">
                            <li><b><fmt:message
                                    key="text.conference.title"/></b> ${conference.title}</li>
                            <li><b><fmt:message
                                    key="text.conference.description"/></b> ${conference.description}
                            </li>
                            <li><b><fmt:message
                                    key="text.conference.dateTime"/></b>
                                <dt:dateFrm date="${conference.dateTime}" local="${language}"/>
                            </li>
                            <li><b><fmt:message
                                    key="text.conference.place"/></b> ${conference.place}</li>
                            <li><b><fmt:message
                                    key="text.conference.available"/></b> ${conference.placeCapacity - conference.registeredParticipants.size()}
                            </li>
                        </jstl:when>
                        <jstl:when test="${language.equals('en_US')}">
                            <li><b><fmt:message
                                    key="text.conference.title"/></b> ${conference.titleEn}</li>
                            <li><b><fmt:message
                                    key="text.conference.description"/></b> ${conference.descriptionEn}
                            </li>
                            <li><b><fmt:message
                                    key="text.conference.dateTime"/></b>
                                <dt:dateFrm date="${conference.dateTime}" local="${language}"/>
                            </li>
                            <li><b><fmt:message
                                    key="text.conference.place"/></b> ${conference.placeEn}</li>
                            <li><b><fmt:message
                                    key="text.conference.available"/></b> ${conference.placeCapacity - conference.registeredParticipants.size()}
                            </li>
                        </jstl:when>
                    </jstl:choose>

                    <jstl:choose>
                        <jstl:when test="${not empty sessionScope.future}">

                            <div class="btn-block" style="background-color: #c8cbcf">
                                <div align="center">
                                    <!-- Conference is ongoing-->
                                    <jstl:if test="${'USER' == sessionScope.user.role.name() ||
                                'SPEAKER' == sessionScope.user.role.name() ||
                                'MODERATOR' == sessionScope.user.role.name()}">
                                        <a class="btn-link btn-outline-secondary"
                                           style="border-color: #1b1e21; color: #1b1e21" role="button"
                                           href="${pageContext.request.contextPath}/conf?action=participate"><fmt:message
                                                key="text.participate"/></a>
                                    </jstl:if>
                                    <jstl:if test="${'SPEAKER' == sessionScope.user.role.name() ||
                                'MODERATOR' == sessionScope.user.role.name()}">
                                        <a class="btn-link btn-outline-secondary"
                                           style="border-color: #1b1e21; color: #1b1e21" role="button"
                                           href="${pageContext.request.contextPath}/conf?action=redirect_offer_lecture"><fmt:message
                                                key="text.offer.lecture"/></a>
                                    </jstl:if>
                                    <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                                        <a class="btn-link btn-outline-secondary"
                                           style="border-color: #1b1e21; color: #1b1e21" role="button"
                                           href="${pageContext.request.contextPath}/conf?action=redirect_change_conference"><fmt:message
                                                key="text.change.conference.parameters"/></a>
                                    </jstl:if>
                                </div>
                            </div>
                            <br/>
                            <jstl:if test="${not empty requestScope.conference.conferenceLectures}">
                                <div class="text-justify" style="background-color: #c8cbcf"><h5><fmt:message
                                        key="text.lectures"/></h5></div>
                                <div class="cust2">
                                    <jstl:forEach items="${requestScope.conference.conferenceLectures}" var="lecture">
                                        <!-- Lecture's parameters with locale-->
                                        <ul class="menu">
                                            <jstl:choose>
                                                <jstl:when test="${language.equals('uk_UA')}">
                                                    <li><b><fmt:message
                                                            key="text.lecture.start.time"/></b>
                                                        <dt:dateFrm date="${lecture.startTime}" local="${language}"/>
                                                    </li>

                                                    <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.title}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.description"/></b> ${lecture.description}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstName} ${lecture.mainSpeaker.lastName}
                                                    </li>
                                                </jstl:when>
                                                <jstl:when test="${language.equals('en_US')}">
                                                    <li><b><fmt:message
                                                            key="text.lecture.start.time"/></b>
                                                        <dt:dateFrm date="${lecture.startTime}" local="${language}"/>
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.title"/></b> ${lecture.titleEn}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.description"/></b> ${lecture.descriptionEn}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstNameEn} ${lecture.mainSpeaker.lastNameEn}
                                                    </li>
                                                </jstl:when>
                                            </jstl:choose>
                                            <!-- Buttons for each lecture in ingoing  conference-->
                                            <div class="btn-block" style="background-color: #c8cbcf">
                                                <div align="center">
                                                    <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                                                        <a class="btn-link btn-outline-secondary"
                                                           style="border-color: #1b1e21; color: #1b1e21" role="button"
                                                           href="${pageContext.request.contextPath}/conf?action=redirect_change_lecture&lecture_id=${lecture.id}"><fmt:message
                                                                key="text.change.lecture.parameters"/></a>
                                                        <jstl:if test="${lecture.approved == false}">
                                                            <a class="btn-link btn-outline-secondary"
                                                               style="border-color: #1b1e21; color: #1b1e21"
                                                               role="button"
                                                               href="${pageContext.request.contextPath}/conf?action=approve&lecture_id=${lecture.id}"><fmt:message
                                                                    key="text.approve"/></a>
                                                        </jstl:if>
                                                    </jstl:if>
                                                </div>
                                            </div>
                                        </ul>
                                        <hr/>
                                    </jstl:forEach>
                                </div>
                            </jstl:if>

                        </jstl:when>
                        <jstl:otherwise>
                            <!-- Conference is past-->
                            <div class="btn-block" style="background-color: #c8cbcf">
                                <div align="center">
                                    <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                                        <a class="btn-link btn-outline-secondary"
                                           style="border-color: #1b1e21; color: #1b1e21" role="button"
                                           href="${pageContext.request.contextPath}/conf?action=redirect_create_report&conference_id=${conference.id}"><fmt:message
                                                key="text.create.report"/></a>
                                    </jstl:if>
                                </div>
                            </div>
                            <br/>
                            <jstl:if test="${not empty requestScope.conference.conferenceLectures}">
                                <div class="text-justify" style="background-color: #c8cbcf"><h5><fmt:message
                                        key="text.lectures"/></h5></div>
                                <div class="cust2">
                                    <jstl:forEach items="${requestScope.conference.conferenceLectures}" var="lecture">
                                        <!-- Lecture's parameters with locale-->
                                        <ul class="menu">
                                            <jstl:choose>
                                                <jstl:when test="${language.equals('uk_UA')}">
                                                    <li><b><fmt:message
                                                            key="text.lecture.start.time"/></b>
                                                        <dt:dateFrm date="${lecture.startTime}" local="${language}"/>
                                                    </li>
                                                    <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.title}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.description"/></b> ${lecture.description}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstName} ${lecture.mainSpeaker.lastName}
                                                    </li>
                                                </jstl:when>
                                                <jstl:when test="${language.equals('en_US')}">
                                                    <li><b><fmt:message
                                                            key="text.lecture.start.time"/></b>
                                                        <dt:dateFrm date="${lecture.startTime}" local="${language}"/>
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.title"/></b> ${lecture.titleEn}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.description"/></b> ${lecture.descriptionEn}
                                                    </li>
                                                    <li><b><fmt:message
                                                            key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstNameEn} ${lecture.mainSpeaker.lastNameEn}
                                                    </li>
                                                </jstl:when>
                                            </jstl:choose>
                                        </ul>
                                        <hr/>
                                    </jstl:forEach>
                                </div>
                            </jstl:if>
                        </jstl:otherwise>
                    </jstl:choose>
                </ul>
            </jstl:if>
        </div>
        <div class="col-2"></div>
    </div>
</div>


<!--FOOTER-->
<jsp:include page="/WEB-INF/view/template/footer.jsp"/>
</body>
</html>
