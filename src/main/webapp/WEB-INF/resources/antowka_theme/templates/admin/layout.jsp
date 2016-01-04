<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="<tiles:insertAttribute name="description" ignore="true" />">

  <spring:url value="/files" var="resourceUrl"/>
  <title><tiles:insertAttribute name="title" ignore="true" /></title>

  <link rel="stylesheet" href="${resourceUrl}/css/bootstrap.css">
  <link rel="stylesheet" href="${resourceUrl}/css/lightbox.css">
  <link rel="stylesheet" href="${resourceUrl}/css/admin/style.css">
</head>

<body ng-app="adminApp">

<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="footer" />

<!-- MODALS -->
<tiles:insertAttribute name="TicketViewModal" />
<tiles:insertAttribute name="CreateArticleCategoryModal" />
<tiles:insertAttribute name="CreateArticleModal" />


<!-- JS FILES -->
<script src="${resourceUrl}/js/libs/jquery-2.1.4.min.js"></script>
<script src="${resourceUrl}/js/libs/angular.min.js"></script>
<script src="${resourceUrl}/js/libs/ui-bootstrap-tpls-0.13.3.js"></script>
<script src="${resourceUrl}/js/libs/angular-file-upload.min.js"></script>
<script src="${resourceUrl}/js/libs/lightbox.js"></script>
<script src="${resourceUrl}/js/admin/app.js"></script>
<script src="${resourceUrl}/js/admin/services.js"></script>
<script src="${resourceUrl}/js/admin/controllers.js"></script>
<script src="${resourceUrl}/js/admin/directives.js"></script>

<script src="${resourceUrl}/js/admin/services/articleService.js"></script>
<script src="${resourceUrl}/js/admin/controllers/modals/createArticleViewCtrl.js"></script>
<script src="${resourceUrl}/js/admin/controllers/articleCtrl.js"></script>

</body>

</html>  