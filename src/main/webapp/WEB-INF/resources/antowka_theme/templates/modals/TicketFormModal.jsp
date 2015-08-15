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
        <h1>Open the Question</h1>
        <h4>Make World better!</h4>
      </header>
      <form class="request-form"  role="form" name="requestTicket" ng-controller="sendFormCtrl" novalidate ng-submit="processForm()">
        <div class="modal-body">
          <p class="help-block pull-right"><small>Be sure, your surname, email and phone number is confidential information and will not be shown at the site</small></p>
          <div class="form-group row">

            <!-- NAME -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="First Name*" name="name" ng-model="name" checkname required>
              <span ng-show="requestTicket.name.$error.checkname" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.name.$dirty && requestTicket.name.$error.required" class="text-danger">First Name is required.</span>
            </div>

            <!-- SURNAME -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="Surname*" name="surname" ng-model="surname" checkName required>
              <span ng-show="requestTicket.surname.$error.checkname" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.surname.$dirty && requestTicket.surname.$error.required" class="text-danger">Surname is required.</span>
            </div>

            <!-- EMAIL -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="Email*" name="email" ng-model="email" checkemail required>
              <span ng-show="requestTicket.email.$dirty && requestTicket.email.$invalid && !requestTicket.email.$error.required" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.email.$dirty && requestTicket.email.$error.required" class="text-danger">Email is required.</span>
            </div>
          </div>
          <div class="form-group row">

            <!-- PHONE -->
            <div class="col-xs-4 form-control-wrap">
              <input type="text" class="form-control" placeholder="Your phone number*" name="phone" ng-model="phone" checkphone required>
              <span ng-show="requestTicket.phone.$dirty && requestTicket.phone.$invalid && !requestTicket.phone.$error.required" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.phone.$dirty && requestTicket.phone.$error.required" class="text-danger">Phone number is required.</span>
            </div>

            <!-- ADDRESS -->
            <div class="col-xs-8 form-control-wrap">
              <input type="text" class="form-control" placeholder="Address, where happen a problem">
            </div>
          </div>
          <div class="form-group row">

            <!-- CATEGORIES -->
            <div class="col-xs-4 form-control-wrap">
              <select class="form-control">
                <option value="" selected="selected" disabled>Please specify a problem</option>
                <option ng-repeat="category in categories"  name="categoryID" value="{{category.ticketCategoryId}}" title="{{category.description}}">
                  {{category.title}}</option>
              </select>
            </div>

            <!-- TITLE -->
            <div class="col-xs-8 form-control-wrap">
              <input type="text" class="form-control" placeholder="Title*" name="title" ng-model="title" required>
              <span ng-show="requestTicket.title.$dirty && requestTicket.title.$invalid && !requestTicket.title.$error.required" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.title.$dirty && requestTicket.title.$error.required" class="text-danger">Title is required.</span>
            </div>
          </div>
          <div class="form-group row">

            <!-- FILE -->
            <div class="col-xs-4 file-control form-control-wrap">
              <label for="exampleInputFile">Upload</label>
              <input type="file" name="file" id="exampleInputFile" ng-model="file" checkfile>
              <span ng-show="requestTicket.file.$error.checkfile" class="text-danger">Your file to big or have wrong size.</span>
              <p class="help-block">Attache your screenshot or document. We support this file types: jpg, png, doc, pdf. Max file size 10mb.</p>
            </div>

            <!-- DESCRIPTION -->
            <div class="col-xs-8 form-control-wrap">
              <textarea class="form-control" rows="5" placeholder="Description*" placeholder="" name="description" ng-model="description" required></textarea>
              <span ng-show="requestTicket.description.$dirty && requestTicket.description.$invalid && !requestTicket.description.$error.required" class="text-danger">Invalid.</span>
              <span ng-show="requestTicket.description.$dirty && requestTicket.description.$error.required" class="text-danger">Description is required.</span>
            </div>
          </div>

        </div>
        <footer class="modal-footer">
          <!-- AGREEMENT -->
          <div class="agreement pull-left">
            <div class="checkbox">
              <label>
                <input type="checkbox" name="agreement" ng-model="agreement" required> <small>Agree with site rules</small>
              </label>
            </div>
          </div>
          <!-- BUTTONS -->
          <button type="submit" ng-click="ok()" ng-disabled="requestTicket.$invalid" class="btn btn-primary btn-lg">Send your request</button>
          <button type="cancel" ng-click="cancel()" class="btn btn-warning btn-lg">Cancel</button>
        </footer>
      </form>
    </section>
  </script>
</div>