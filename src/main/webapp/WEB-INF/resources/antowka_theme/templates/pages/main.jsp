<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Lika /people-06
  Date: 25.07.15
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/resources" var="resourceUrl" />

<section class="hero"></section>

<section class="index-questions" id="questions" ng-controller="ShowTicketsCtrl">
  <div class="container">
    <header class="section-header">
      <h1><spring:message code="lang.openTickets" /></h1>
      <h4><spring:message code="lang.weGot" /> <strong>{{tickets.length}}</strong> <spring:message code="lang.tickets" /></h4>
    </header>
    <div class="row">
      <article class="col-sm-6 col-md-3 ticket-preview" ng-repeat="ticket in tickets" ng-controller="TicketViewCtrl">
        <a class="link-to-ticket" id="ticket-{{ticket.ticketId}}" ng-click="open(ticket);">
          <div class="img-wrap no-image">
            <img src="${resourceUrl}/img/random/home175.png"/>
          </div>
          <h3 class="caption">{{ticket.title}}</h3>
        </a>
      </article>
    </div>

  <button class="btn btn-primary" ng-show="showMoreBtn" ng-click="showMore()">Show more</button>
  </div>
</section>
