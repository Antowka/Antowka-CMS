<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.07.15
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/resources" var="resourceUrl" />

<section class="hero"></section>

<section class="index-questions" id="questions" ng-controller="ShowTicketsCtrl">
  <div class="container">
    <header class="section-header">
      <h1><spring:message code="lang.openTicket" /></h1>
      <h4>We got <strong>{{tickets.length}}</strong> requests</h4>
    </header>
    <div class="row">
      <article class="col-sm-6 col-md-3 request-preview" ng-repeat="ticket in tickets">
        <a class="link-to-ticket" id="request-{{ticket.ticketId}}" ng-click="open(ticket);">
          <div class="img-wrap no-image">
            <img src="${resourceUrl}/img/random/home175.png"/>
          </div>
          <h3 class="caption">{{ticket.description}}</h3>
        </a>
      </article>
    </div>
  </div>
</section>

