<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title.conference"/></title>
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
<jstl:if test="${not empty requestScope.success_subscribe}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.subscribe"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.already_subscribed}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.already.exists"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_approved}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.not.approved"/></div>
</jstl:if>
<!--CONTENT-->
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <jstl:if test="${not empty requestScope.conference}">
            <ul class="menu">
                <li><b><fmt:message key="text.conference.title"/></b> ${requestScope.conference.title}</li>
                <li><b><fmt:message key="text.conference.description"/></b> ${requestScope.conference.description}</li>
                <li><b><fmt:message key="text.conference.dateTime"/></b> ${requestScope.conference.dateTime}</li>
                <li><b><fmt:message key="text.conference.place"/></b> ${requestScope.conference.place}</li>
                <li><b><fmt:message key="text.conference.placeCapacity"/></b> ${requestScope.conference.placeCapacity}
                </li>
            </ul>
            <jstl:if test="${not empty requestScope.conference.placeCapacity}">
                <jstl:if test="${'USER' == sessionScope.user.role.name() ||
                                'SPEAKER' == sessionScope.user.role.name() ||
                                'MODERATOR' == sessionScope.user.role.name()}">
                    <form role="form" class="btn btn-success btn-sm" method="post"
                          action="${pageContext.request.contextPath}/controller?action=participate">
                        <input type="hidden" name="conference_id" value="${requestScope.conference.id}">
                        <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                key="text.participate"/></button>
                    </form>
                </jstl:if>
                <jstl:if test="${'SPEAKER' == sessionScope.user.role.name() ||
                                'MODERATOR' == sessionScope.user.role.name()}">
                    <form role="form" class="btn btn-success btn-sm" method="post"
                          action="${pageContext.request.contextPath}/controller?action=redirect_offer_lecture&conference_id=${requestScope.conference.id}">
                        <input type="hidden" name="conference_id" value="${requestScope.conference.id}">
                        <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                key="text.offer.lecture"/></button>
                    </form>
                </jstl:if>
                <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                    <form role="form" class="btn btn-success btn-sm" method="post"
                          action="${pageContext.request.contextPath}/controller?action=redirect_change_conference">
                        <input type="hidden" name="conference_id" value="${requestScope.conference.id}">
                        <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                key="text.change.conference.parameters"/></button>
                    </form>
                </jstl:if>
            </jstl:if>
            <jstl:forEach items="${requestScope.conference.conferenceLectures}" var="lecture">
                <jstl:if test="${lecture.approved == true}">
                    <ul class="menu">
                        <li><b><fmt:message key="text.lecture.start.time"/></b> ${lecture.startTime}</li>
                        <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.title}</li>
                        <li><b><fmt:message key="text.lecture.description"/></b> ${lecture.description}</li>
                        <li><b><fmt:message key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstName} ${lecture.mainSpeaker.lastName}</li>

                    </ul>
                    <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                        <form role="form" class="btn btn-success btn-sm" method="post"
                              action="${pageContext.request.contextPath}/controller?action=redirect_change_lecture">
                            <input type="hidden" name="lecture_id" value="${lecture.id}">
                            <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                    key="text.change.lecture.parameters"/></button>
                        </form>
                    </jstl:if>
                </jstl:if>
                <jstl:if test="${lecture.approved == false}">
                    <ul class="menu">
                        <li><b><fmt:message key="text.lecture.start.time"/></b> ${lecture.startTime}</li>
                        <li><b><fmt:message key="text.lecture.title"/></b> ${lecture.title}</li>
                        <li><b><fmt:message key="text.lecture.description"/></b> ${lecture.description}</li>
                        <li><b><fmt:message key="text.lecture.speaker"/></b> ${lecture.mainSpeaker.firstName} ${lecture.mainSpeaker.lastName}</li>

                    </ul>
                    <jstl:if test="${'MODERATOR' == sessionScope.user.role.name()}">
                        <form role="form" class="btn btn-success btn-sm" method="post"
                              action="${pageContext.request.contextPath}/controller?action=approve">
                            <input type="hidden" name="lecture_id" value="${lecture.id}">
                            <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                    key="text.approve"/></button>
                        </form>
                    </jstl:if>
                </jstl:if>
            </jstl:forEach>
        </jstl:if>
    </div>
    <div class="col-2"></div>
</div>
<!--FOOTER-->
<jsp:include page="../template/footer.jsp"/>
</body>
</html>
