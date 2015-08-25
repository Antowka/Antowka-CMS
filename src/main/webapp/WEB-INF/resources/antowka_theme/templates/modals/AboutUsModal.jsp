<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 10.08.15
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/files" var="resourceUrl"/>

<div ng-controller="AboutUsCtrl">
  <script  type="text/ng-template" id="AboutUsModal.html">
    <article class="index-about">

      <header class="modal-header">
        <button type="button" class="close pull-right" aria-label="Close" ng-click="cancel()">
          <span aria-hidden="true">&#10007;</span>
          <span class="sr-only">Close</span>
        </button>
        <h1 class="text-center">${vars.settings.site_title}</h1>
      </header>
      <div class="modal-body">
        ${vars.settings.site_description}
        <img src="${resourceUrl}/img/animation/city.png"  class="img-responsive city-img" alt="Our city">
      </div>

    </article>
  </script>
</div>