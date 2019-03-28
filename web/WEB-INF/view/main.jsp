<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 16:43
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
    <title><fmt:message key="text.title.main"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<!-- HEADER -->
<jsp:include page="template/header.jsp"/>

<!-- SIDEBAR -->
<jsp:include page="template/sidebar.jsp"/>

<!-- ALARMS -->
<jstl:if test="${not empty requestScope.success_registration}">
<div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.registration"/></div>
</jstl:if>

<!-- CONTENT -->

<div class="row">
    <div class="col-2"></div>
    <div class="col-8>">
        <jstl:choose>
            <jstl:when test="${not empty sessionScope.user}">
                <p><fmt:message key="text.ongoing"/></p>
                <jstl:choose>
                    <jstl:when test="${not empty requestScope.ongoing}">
                        <jstl:forEach items="${requestScope.ongoing}" var="conference">
                            <ul class="menu">
                                <li><b><fmt:message key="text.conference.title"/></b> ${conference.title}</li>
                                <li><b><fmt:message key="text.conference.description"/></b> ${conference.description}</li>
                                <li><b><fmt:message key="text.conference.dateTime"/></b> ${conference.dateTime}</li>
                                <li><b><fmt:message key="text.conference.place"/></b> ${conference.place}</li>
                            </ul>

                            <div class="menu-bar">
                                <form role="form" class="btn btn-success btn-sm" method="post"
                                      action="controller?action=redirect_conference">
                                    <input type="hidden" name="conference_id" value="${conference.id}">
                                    <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                            key="text.show.details"/></button>
                                </form>
                            </div>
                        </jstl:forEach>
                    </jstl:when>
                    <jstl:otherwise>
                        <fmt:message key="text.not.ongoing.conferences"/>
                    </jstl:otherwise>
                </jstl:choose>

                <p><fmt:message key="text.last"/></p>
                <jstl:choose>
                    <jstl:when test="${not empty requestScope.last}">
                        <jstl:forEach items="${requestScope.last}" var="last_conference">
                            <ul class="menu">
                                <li><b><fmt:message key="text.conference.title"/></b> ${last_conference.title}</li>
                                <li><b><fmt:message key="text.conference.description"/></b> ${last_conference.description}</li>
                                <li><b><fmt:message key="text.conference.dateTime"/></b> ${last_conference.dateTime}</li>
                                <li><b><fmt:message key="text.conference.place"/></b> ${last_conference.place}</li>
                            </ul>

                            <div class="menu-bar">
                                <form role="form" class="btn btn-success btn-sm" method="post"
                                      action="controller?action=redirect_conference">
                                    <input type="hidden" name="id" value="${last_conference.id}">
                                    <button type="submit" class="btn btn-success btn-sm"><fmt:message
                                            key="text.show.details"/></button>
                                </form>
                            </div>
                        </jstl:forEach>
                    </jstl:when>
                    <jstl:otherwise>
                        <fmt:message key="text.not.last.conferences"/>
                    </jstl:otherwise>
                </jstl:choose>
            </jstl:when>
            <jstl:otherwise>
                <div class="text" >
                    <p><fmt:message key="text.about.project"/></p>
                    <br/>
                    <br/>
                    <br/>
                    <p><fmt:message key="text.need.be.registered"/></p>
                </div>
            </jstl:otherwise>
        </jstl:choose>
    </div>
    <div class="col-2"></div>
</div>
<!--FOOTER-->
<jsp:include page="template/footer.jsp"/>
</body>
</html>
