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
      <form class="request-form"  role="form" name="requestTicket" ng-controller="sendFormCtrl" novalidate>
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
              <input type="text" name="address" class="form-control" placeholder="<spring:message code="lang.Address" />">
            </div>
          </div>
          <div class="form-group row">

            <!-- CATEGORIES -->
            <div class="col-xs-4 form-control-wrap">
                <select class="form-control"  ng-model="category" ng-options="category.title for category in categories">
                  <option value="" selected="selected" disabled><spring:message code="lang.CategoryDefault" /></option>
                </select>
            </div>

            <!-- TITLE -->
            <div class="col-xs-8 form-control-wrap">
              <input type="text" class="form-control" placeholder="<spring:message code="lang.Title" />*" name="title" ng-model="title" required>
              <span ng-show="requestTicket.title.$dirty && requestTicket.title.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
            </div>
          </div>
          <div class="form-group row">

            <!-- FILE -->
            <div class="col-xs-4 file-control form-control-wrap" style="display: none;">
              <label for="exampleInputFile">Upload</label>
              <input type="file" name="file" id="exampleInputFile" ng-model="file" checkfile>
              <span ng-show="requestTicket.file.$error.checkfile" class="text-danger"><spring:message code="lang.fileError" /></span>
              <p class="help-block"><spring:message code="lang.fileHelp" /></p>
            </div>

            <!-- DESCRIPTION -->
            <div class="col-xs-8 form-control-wrap">
              <textarea class="form-control" rows="5" placeholder="<spring:message code="lang.Description" />*" name="description" ng-model="description" required></textarea>
              <span ng-show="requestTicket.description.$dirty && requestTicket.description.$error.required" class="text-danger"><spring:message code="lang.required" /></span>
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