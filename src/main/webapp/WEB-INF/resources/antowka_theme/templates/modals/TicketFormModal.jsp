<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 10.08.15
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>

<div ng-controller="TicketFormCtrl">
  <script type="text/ng-template" id="ticketFormModal.html">

    <section class="index-form" id="apply">
      <header class="modal-header">
        <h1><spring:message code="lang.createTicket" /></h1>
        <h4><spring:message code="lang.MakeCityBetter" />!</h4>
      </header>
      <form class="request-form"  role="form" name="requestTicket" ng-controller="sendFormCtrl" nv-file-drop="" uploader="uploader" filters="queueLimit, customFilter" novalidate>
        <div class="modal-body">
          <p class="help-block pull-right"><small><spring:message code="lang.ConditionsPrivacy" /></small></p>
          <div class="form-group row">

            <!-- NAME -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.FirstName" />*" name="name" ng-model="name" checkname required>
              <span ng-show="requestTicket.name.$error.checkname" class="text-danger"><spring:message code="lang.InvalidFullName" /></span>
              <span ng-show="requestTicket.name.$dirty && requestTicket.name.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>

            <!-- SURNAME -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.Surname" />*" name="surname" ng-model="surname" checkName required>
              <span ng-show="requestTicket.surname.$error.checkname" class="text-danger"><spring:message code="lang.InvalidFullName" /></span>
              <span ng-show="requestTicket.surname.$dirty && requestTicket.surname.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>

            <!-- EMAIL -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.Email" />*" name="email" ng-model="email" checkemail required>
              <span ng-show="requestTicket.email.$dirty && requestTicket.email.$invalid && !requestTicket.email.$error.required" class="text-danger"><spring:message code="lang.InvalidEmail" /></span>
              <span ng-show="requestTicket.email.$dirty && requestTicket.email.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>
          </div>
          <div class="form-group row">

            <!-- PHONE -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.Phone" />*" name="phone" ng-model="phone" checkphone required>
              <span ng-show="requestTicket.phone.$dirty && requestTicket.phone.$invalid && !requestTicket.phone.$error.required" class="text-danger"><spring:message code="lang.InvalidPhone" /></span>
              <span ng-show="requestTicket.phone.$dirty && requestTicket.phone.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>

            <!-- ADDRESS -->
            <div class="col-xs-8 form-control-wrap">
              <input type="text" name="address" ng-model="address" class="form-control" placeholder="<spring:message code="lang.Address" />">
            </div>
          </div>
          <div class="form-group row">

            <!-- CATEGORIES -->
            <div class="col-xs-4 form-control-wrap">
                <select class="form-control"  ng-model="category" ng-options="category.title for category in categories" required>
                  <option value="" selected="selected" disabled><spring:message code="lang.CategoryDefault" /></option>
                </select>
            </div>

            <!-- REGION -->
            <div class="col-xs-4 form-control-wrap">
              <select class="form-control"  ng-model="region" ng-options="region.title for region in regions" required>
                <option value="" selected="selected" disabled><spring:message code="lang.region" /></option>
              </select>
            </div>

            <!-- TITLE -->
            <div class="col-xs-8 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.Title" />*" name="title" ng-model="title" required>
              <span ng-show="requestTicket.title.$dirty && requestTicket.title.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>
          </div>

          <div class="form-group row">
            <!-- DESCRIPTION -->
            <div class="col-xs-8 form-control-wrap">
              <textarea class="form-control" rows="5" placeholder="<spring:message code="lang.Description" />*" name="description" ng-model="description" required></textarea>
              <span ng-show="requestTicket.description.$dirty && requestTicket.description.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>
          </div>


          <!-- FILE -->
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
                <span ng-show="requestTicket.file.$error.checkfile" class="text-danger"><spring:message code="lang.fileError" /></span>
                <p class="help-block"><spring:message code="lang.fileHelp" /></p>
            </div>
          </div>

        </div>
        <footer class="modal-footer">
          <!-- AGREEMENT -->
          <div class="agreement pull-left">
            <div class="checkbox">
              <label>
                <input type="checkbox" name="agreement" ng-model="agreement" required> <small><spring:message code="lang.Agreement" /></small>
              </label>
            </div>
          </div>
          <!-- BUTTONS -->
          <button type="submit" ng-click="ok(); processForm()" ng-disabled="requestTicket.$invalid" class="btn btn-primary btn-lg"><spring:message code="lang.btnSubmit" /></button>
          <button type="cancel" ng-click="cancel()" class="btn btn-warning btn-lg"><spring:message code="lang.btnCancel" /></button>
        </footer>
      </form>
    </section>
  </script>
</div>

<div ng-controller="sendFormCtrl">

  <script  type="text/ng-template" id="SuccessModal.html">
    <article class="success">

      <header class="modal-header">
        <button type="button" class="close pull-right" aria-label="Close" ng-click="cancel()">
          <span aria-hidden="true">&#10007;</span>
          <span class="sr-only">Close</span>
        </button>
        <h3><spring:message code="lang.successTitle" /></h3>
      </header>
      <div class="modal-body">
        <h2><spring:message code="lang.successInfo" /></h2>
      </div>

    </article>
  </script>
</div>