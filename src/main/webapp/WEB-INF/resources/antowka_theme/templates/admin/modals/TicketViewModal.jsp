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
                <!-- IMAGES -->
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

                <!-- COMMENTS -->
                <div ng-controller="commentsCtrl" nv-file-drop="" uploader="uploader" filters="queueLimit, customFilter">

                    <!-- COMMENTS -->
                    <ul class="list-unstyled comments">
                        <li ng-repeat="comment in comments" class="comment well">
                            <h3 class="comment__title">{{comment.title}}
                                <span class="glyphicon glyphicon-remove pull-right" aria-hidden="true" ng-click="removeComment(ticket.ticketId, comment.commentId)"></span>
                            </h3>
                            <div class="row">
                                <div class="comment__info col-xs-12 col-sm-3">
                                    <div class="comment__info-avatar">
                                        <span class="glyphicon glyphicon-user default-avatar"></span>
                                    </div>
                                    <div class="comment__info-author"><strong>{{comment.user.firstName}} {{comment.user.lastName}}</strong></div>
                                    <div class="comment__info-date">{{comment.creationDate}}</div>
                                </div>
                                <div class="comment__description col-xs-12 col-sm-9">{{comment.description}}</div>
                            </div>

                            <div class="row">
                                <!-- Attachment for comment -->
                                <ul class="row list-unstyled text-center">
                                    <li ng-repeat="cmtAttachment in comment.attachments" class="col-xs-6 col-sm-3">{{cmtAttachment.isImage+5}}

                                        <!-- IF IMAGE -->
                                        <a ng-if="cmtAttachment.isImage" href="/files/uploads/{{cmtAttachment.filePathInStorage}}" target="_blank" data-lightbox="roadtrip">
                                            <img src="/files/uploads/{{cmtAttachment.previewPath}}">
                                        </a>

                                        <!-- IF NO IMAGE -->
                                        <a ng-if="!cmtAttachment.isImage" href="/files/uploads/{{cmtAttachment.filePathInStorage}}" target="_blank">
                                            <img src="/files/uploads/{{cmtAttachment.previewPath}}">
                                        </a>

                                        <!-- <span class="glyphicon glyphicon-remove pull-right" aria-hidden="true" ng-click="removeCommentAttachment(cmtAttachment.attachmentId)"></span> -->
                                    </li>
                                </ul>
                                <!-- END Attachment for comment -->
                            </div>
                        </li>
                    </ul>

                    <!-- COMMENTS FORM -->
                    <div class="comments-form">
                        <form class="request-form"  role="form" name="requestTicket">
                            <div class="form-group">
                                <input  class="form-control" type="text" placeholder="Title" ng-model="title">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" rows="3" placeholder="Comment" ng-model="description"></textarea>
                            </div>

                            <!-- FILES UPOADS -->
                            <div>
                                <!-- UPLOAD QUEUE -->
                                <div>
                                    <table class="table">

                                        <thead>
                                            <tr>
                                                <th width="50%">Name</th>
                                                <th ng-show="uploader.isHTML5">Size</th>
                                                <th ng-show="uploader.isHTML5">Progress</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                        <tr ng-repeat="item in uploader.queue">
                                            <td><strong>{{ item.file.name }}</strong></td>
                                            <td ng-show="uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }} MB</td>
                                            <td ng-show="uploader.isHTML5">
                                                <div class="progress" style="margin-bottom: 0;">
                                                    <div class="progress-bar" role="progressbar" ng-style="{ 'width': item.progress + '%' }"></div>
                                                </div>
                                            </td>
                                            <td class="text-center">
                                                <span ng-show="item.isSuccess"><i class="glyphicon glyphicon-ok"></i></span>
                                                <span ng-show="item.isCancel"><i class="glyphicon glyphicon-ban-circle"></i></span>
                                                <span ng-show="item.isError"><i class="glyphicon glyphicon-remove"></i></span>
                                            </td>
                                            <td nowrap>
                                                <button type="button" class="btn btn-success btn-xs" ng-click="item.upload()" ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                                    <span class="glyphicon glyphicon-upload"></span> Upload
                                                </button>
                                                <button type="button" class="btn btn-warning btn-xs" ng-click="item.cancel()" ng-disabled="!item.isUploading">
                                                    <span class="glyphicon glyphicon-ban-circle"></span> Cancel
                                                </button>
                                                <button type="button" class="btn btn-danger btn-xs" ng-click="item.remove()">
                                                    <span class="glyphicon glyphicon-trash"></span> Remove
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- UPLOAD BUTTON -->
                                <div>
                                    <label for="exampleInputFile">Upload</label>
                                    <input type="file" id="exampleInputFile" nv-file-select="" uploader="uploader" multiple>
                                </div>
                            </div>
                            <!-- END FILES UPLOADS -->

                            <button class="btn btn-primary" ng-click="createComment(ticket.ticketId)">Send Comment</button>
                        </form>
                    </div>
                </div>

            </div>



        </article>
    </script>
</div>