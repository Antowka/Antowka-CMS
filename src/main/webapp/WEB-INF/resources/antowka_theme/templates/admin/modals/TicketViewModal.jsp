<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 15.08.15
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/files" var="resourceUrl" />

<div ng-controller="ticketViewCtrl">
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
                            <img ng-if="mainAttachment == null" src="${resourceUrl}/img/random/home175.png"/>
                            <img ng-if="mainAttachment != null" src="${resourceUrl}/uploads/{{mainAttachment.previewPath}}"/>
                        </div>
                    </div>

                    <div class="col-xs-7">
                        <h4>{{ticket.firstName}} <small class="date">{{formatDate(ticket.creationDate) |  date:"dd.MM.yyyy" }}</small> <span class="label label-status label-default pull-right">{{ticket.status.status}}</span></h4>
                        <p class="small"><strong>Ticket ID:</strong> {{ticket.ticketId}}</p>
                        <p class="small"><strong>Address:</strong> {{ticket.address}}</p>
                        <p class="small"><strong>Phone:</strong> {{ticket.phone}}</p>
                        <p class="small"><strong>Email:</strong> {{ticket.email}}</p>
                        <p class="small"><strong>User:</strong> {{ticket.firstName}} {{ticket.lastName}}</p>
                        <p class="small"><strong>Region:</strong> {{ticket.region.title}}</p>
                        <div class="description">{{ticket.description}}</div>
                    </div>

                </div>

            </div>

            <div class="modal-footer">
                <ul class="row list-unstyled text-center">
                    <li ng-repeat="attachment in ticket.attachments"  class="col-xs-3">

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
            </div>

        </article>
    </script>
</div>