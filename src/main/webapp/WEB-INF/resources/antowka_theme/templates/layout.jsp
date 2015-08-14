<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <spring:url value="/resources" var="resourceUrl"/>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>

        <link rel="stylesheet" href="${resourceUrl}/css/bootstrap.css">
        <link rel="stylesheet" href="${resourceUrl}/css/style.css">
    </head>

    <body ng-app="CommissionApp">

        <tiles:insertAttribute name="header" />

        <tiles:insertAttribute name="body" />

        <tiles:insertAttribute name="footer" />

        <tiles:insertAttribute name="AboutUsModal" />
        <tiles:insertAttribute name="TicketFormModal" />
        <tiles:insertAttribute name="TicketViewModal" />


        <!-- JS FILES -->
        <script src="${resourceUrl}/js/angular.min.js"></script>
        <script src="${resourceUrl}/js/ui-bootstrap-tpls-0.13.3.js"></script>
        <script src="${resourceUrl}/js/app.js"></script>

    </body>

</html>  