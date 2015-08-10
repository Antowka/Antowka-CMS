<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>

<%--
  Created by IntelliJ IDEA.
  User: Lika / people-06
  Date: 25.07.15
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>


<header>
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Меню</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Commission
          <small>which help build LIFE <span class="glyphicon glyphicon-home"></span></small>
        </a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
          <li ng-controller="RequestFormCtrl">
            <a ng-click="open()"><span class="glyphicon glyphicon-edit"></span> Send request</a>
          </li>
          <li><a href="#questions">Open questions</a></li>
          <li ng-controller="AboutUsCtrl">
            <a ng-click="open()"><span class="glyphicon glyphicon-info-sign"></span> About Us</a>
          </li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>
</header>

