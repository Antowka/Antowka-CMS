<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 25.07.15
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>


<header>
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" ng-click="isCollapsed = !isCollapsed">
          <span class="sr-only">Меню</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#"><spring:message code="lang.siteTitle" />
          <small><spring:message code="lang.siteSubtitle" /> <span class="glyphicon glyphicon-home"></span></small>
        </a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="navbar-collapse collapse" collapse="!isCollapsed">
        <ul class="nav navbar-nav navbar-right">
          <li ng-controller="TicketFormCtrl">
            <a ng-click="open()"><span class="glyphicon glyphicon-edit"></span> <spring:message code="lang.createTicket" /></a>
          </li>
          <li><a href="#questions"> <spring:message code="lang.openTickets" /></a></li>
          <li ng-controller="AboutUsCtrl">
            <a ng-click="open()"><span class="glyphicon glyphicon-info-sign"> </span> <spring:message code="lang.aboutUs" /></a>
          </li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
</header>

