<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 10.08.15
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>

<div ng-controller="RequestFormCtrl">
  <script type="text/ng-template" id="requestModal.html">

    <section class="index-form" id="apply">
      <header class="modal-header">
        <h1>Open the Question</h1>
        <h4>Make World better!</h4>
      </header>
      <form class="request-form" action="" name="request">
        <div class="modal-body">
          <p class="help-block pull-right"><small>Be sure, your family name, email and phone number is confidential information and will not be shown at the site</small></p>
          <div class="form-group row">
            <div class="col-xs-6">
              <input type="text" class="form-control" placeholder="Your full name">
            </div>
            <div class="col-xs-6">
              <input type="text" class="form-control" placeholder="Email">
            </div>
          </div>
          <div class="form-group row">
            <div class="col-xs-4">
              <input type="text" class="form-control" placeholder="Your mobile">
            </div>
            <div class="col-xs-8">
              <input type="text" class="form-control" placeholder="Address, where happen a problem">
            </div>
          </div>
          <div class="form-group row">
            <div class="col-xs-4">
              <select class="form-control">
                <option value="" selected="selected" disabled>Please specify a problem</option>
                <option value="366">I want sleep</option>
                <option value="367">I need some food</option>
                <option value="368">I'm drunk</option>
              </select>
            </div>
            <div class="col-xs-8">
              <input type="text" class="form-control" placeholder="Title">
            </div>
          </div>
          <div class="form-group row">
            <div class="col-xs-4">
              <label for="exampleInputFile">Upload</label>
              <input type="file" id="exampleInputFile">
              <p class="help-block">Attache your screenshot.</p>
            </div>
            <div class="col-xs-8"><textarea class="form-control" rows="5" placeholder="Details"></textarea></div>
          </div>

        </div>
        <footer class="modal-footer">
          <div class="agreement pull-left">
            <div class="checkbox">
              <label>
                <input type="checkbox" ng-model="checked"> <small>Agree with site rules</small>
              </label>
            </div>
          </div>
          <button type="submit" ng-click="ok()" ng-disabled="!checked" class="btn btn-primary btn-lg">Send your request</button>
          <button type="cancel" ng-click="cancel()" class="btn btn-warning btn-lg">Cancel</button>
        </footer>
      </form>
    </section>
  </script>
</div>
