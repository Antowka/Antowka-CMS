<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Lika /people-06
  Date: 25.07.15
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/files" var="resourceUrl" />

<section class="hero">
    <h1 class="hero__title"><spring:message code="lang.heroTitle" /></h1>
</section>

<section class="index-questions" id="questions" ng-controller="ShowTicketsCtrl">
  <div class="container">
    <header class="section-header">
      <h1><spring:message code="lang.openTickets" /></h1>
      <h4><spring:message code="lang.weGot" /> <strong>{{tickets.length}}</strong> <spring:message code="lang.tickets" /></h4>
    </header>
    <div class="row">
      <article class="col-xs-2 col-sm-3 col-md-6 ticket-preview" ng-repeat="ticket in tickets" ng-controller="TicketViewCtrl">
        <a class="link-to-ticket" id="ticket-{{ticket.ticketId}}" ng-click="open(ticket);">

          <div  class="img-wrap">
            <div ng-if="ticket.attachments.length > 0" style="background-image:url(${resourceUrl}/uploads/{{ticket.attachments[0].previewPath}})" class="tp-img"></div>
            <img ng-if="ticket.attachments.length == 0" src="${resourceUrl}/img/random/home175.png" class="no-image"/>
          </div>

          <h3 class="caption">{{ticket.title}}</h3>
          <h4>{{ticket.region.title}}</h4>
        </a>
      </article>
    </div>

  <button class="btn btn-primary" ng-show="showMoreBtn" ng-click="showMore()"><spring:message code="lang.showMore" /></button>
  </div>
</section>
