<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.07.15
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>

<section class="hero"></section>

<section class="index-questions" id="questions" ng-controller="ShowRequestsCtrl">
  <div class="container">
    <header class="section-header">
      <h1>Open Questions</h1>
      <h4>We got <strong>{{requests.length}}</strong> requests</h4>
    </header>
    <div class="row">
      <article class="col-sm-6 col-md-3 request-preview" ng-repeat="request in requests">
        <a href="" id="request-{{request.ticketId}}">
          <div class="img-wrap no-image">
            <img src="${resourceUrl}/img/random/home175.png"/>
          </div>
          <h3 class="caption">{{request.description}}</h3>
        </a>
      </article>
    </div>
  </div>
</section>

