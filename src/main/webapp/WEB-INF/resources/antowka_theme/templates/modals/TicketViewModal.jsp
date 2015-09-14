<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 15.08.15
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/files" var="resourceUrl" />

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
          <div class="col-sm-4 img-wrap">
              <img ng-if="mainAttachment == null" src="${resourceUrl}/img/random/home175.png"/>
              <img ng-if="mainAttachment != null" src="${resourceUrl}/uploads/{{mainAttachment.previewPath}}"/>
          </div>

          <div class="col-xs-12 col-sm-8">
            <h4>{{ticket.firstName}} <small class="date">{{formatDate(ticket.creationDate) |  date:"dd.MM.yyyy" }}</small> <span class="label label-status label-default pull-right">{{ticket.status.status}}</span></h4>
            <p class="small"><strong>Address:</strong> {{ticket.address}}</p>
            <div class="description">{{ticket.description}}</div>
          </div>

        </div>

      </div>

      <div class="modal-footer">
            <ul class="row list-unstyled text-center">
              <li ng-repeat="attachment in ticket.attachments" class="col-xs-6 col-sm-3">

                <!-- IF IMAGE -->
                <a ng-if="attachment.isImage" href="/files/uploads/{{attachment.filePathInStorage}}" target="_blank" data-lightbox="roadtrip">
                  <img src="/files/uploads/{{attachment.previewPath}}">
                </a>

                <!-- IF NO IMAGE -->
                <a ng-if="!attachment.isImage" href="/files/uploads/{{attachment.filePathInStorage}}" target="_blank">
                  <img src="/files/uploads/{{attachment.previewPath}}">
                </a>

              </li>
            </ul>

            <!-- COMMENTS -->
            <ul>
              <li ng-repeat="comment in ticket.comments">
                <h4>{{comment.title}}</h4>
                <span>{{comment.creationDate}}</span>
                <span>{{comment.description}}</span>
                <span>{{comment.user.firstName}} {{comment.user.lastName}}</span>
              </li>
            </ul>
      </div>

    </article>
  </script>
</div>