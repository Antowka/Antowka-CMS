<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 15.08.15
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/resources" var="resourceUrl" />

<div ng-controller="TicketViewCtrl">
  <script  type="text/ng-template" id="ticketViewModal.html">
    <article class="ticket-view">
      <div class="modal-header">
        <button type="button" class="close pull-right" aria-label="Close" ng-click="cancel()">
          <span aria-hidden="true">&#10007;</span>
          <span class="sr-only">Close</span>
        </button>
        <h2>{{ticket.title}}</h2>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xs-5">
            <div class="img-wrap no-image">
              <img src="${resourceUrl}/img/random/home175.png"/>
            </div></div>
          <div class="col-xs-7">
            <h4>{{ticket.firstName}} <small class="date">{{formatDate(ticket.creationDate) |  date:"dd.MM.yyyy" }}</small> <span class="label label-status label-default pull-right">{{ticket.status.status}}</span></h4>
            <p class="small"><strong>Address:</strong> {{ticket.address}}</p>
            <div class="description">{{ticket.description}}</div>
          </div>
        </div>

      </div>
      <div class="modal-footer">

      </div>
    </article>
  </script>
</div>