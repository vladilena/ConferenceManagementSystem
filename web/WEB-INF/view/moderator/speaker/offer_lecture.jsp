<%--
  Created by IntelliJ IDEA.
  User: UVlad
  Date: 19.03.2019
  Time: 18:15
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
    <title><fmt:message key="text.title.offer.lecture"/></title>
</head>
<body>
<%-- HEADER --%>
<jsp:include page="../../template/header.jsp"/>

<%--ALARMS--%>
<jstl:if test="${not empty requestScope.success_offer}">
    <div class="alert alert-success" role="alert"><fmt:message key="text.alert.success.offer.lecture"/></div>
</jstl:if>
<jstl:if test="${not empty requestScope.not_add_lecture}">
    <div class="alert alert-danger" role="alert"><fmt:message key="text.alert.fault.offer.lecture"/></div>
</jstl:if>
<c:if test="${not empty requestScope.invalidData}">
    <div class="alert alert-danger capitalize">
        <strong><fmt:message key="text.alert.error"/></strong> <fmt:message key="text.alert.invalid-offer"/><br>
        <c:if test="${not empty requestScope.invalidData.invalidTitle}">
            -<fmt:message key="text.alert.invalid-title"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidTitleEn}">
            -<fmt:message key="text.alert.invalid-title_en"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidDescription}">
            -<fmt:message key="text.alert.invalid-description"/><br>
        </c:if>
        <c:if test="${not empty requestScope.invalidData.invalidDescriptionEn}">
            -<fmt:message key="text.alert.invalid-description_en"/><br>
        </c:if>
    </div>
</c:if>

<%--CONTENT--%>
<div class="bg">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <br/>
            <br/>
            <br/>
            <c:set var="in_lect" value="${requestScope. invalid_lecture}" scope="request"/>
            <div class="container-fluid">
                <form role="form" method="post"
                      action="${pageContext.request.contextPath}/conf?action=offer_lecture&conference_id=${requestScope.conference_id}">
                    <div class="form-group">
                        <label for="title"><fmt:message key="text.lecture.title"/></label>
                        <input type="text" class="form-control" name="title" id="title"
                               value="${in_lect.title}"
                               placeholder="<fmt:message key="text.input.lecture.title"/>" required>
                    </div>
                    <div class="form-group">
                        <label for="title_en"><fmt:message key="text.lecture.title.en"/></label>
                        <input type="text" class="form-control" name="title_en" id="title_en"
                               value="${in_lect.titleEn}"
                               placeholder="<fmt:message key="text.input.lecture.title.en"/>" required>
                    </div>
                    <div class="form-group">
                        <label for="desc"><fmt:message key="text.lecture.description"/></label>
                        <input type="text" class="form-control" name="description" id="desc"
                               value="${in_lect.description}"
                               placeholder="<fmt:message key="text.input.lecture.description"/>" required>
                    </div>
                    <div class="form-group">
                        <label for="desc_en"><fmt:message key="text.lecture.description.en"/></label>
                        <input type="text" class="form-control" name="description_en" id="desc_en"
                               value="${in_lect.descriptionEn}"
                               placeholder="<fmt:message key="text.input.lecture.description.en"/>" required>
                    </div>
                    <button type="submit" class="btn btn-block btn-outline-dark"
                            style="background-color: #5a6268; color: #c8cbcf"><fmt:message
                            key="text.offer"/></button>
                </form>
            </div>
        </div>
        <div class="col-3"></div>
    </div>
</div>

<%--FOOTER--%>
<jsp:include page="../../template/footer.jsp"/>

</body>
</html>
