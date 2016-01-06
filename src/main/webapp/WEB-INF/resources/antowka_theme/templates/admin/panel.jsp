<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.07.15
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>

<div class="container-fluid">
<h1>Wellcome to Admin Panel</h1>

<sec:authorize access="isAuthenticated()">

  <c:url var="logoutUrl" value="/logout"/>

  <form action="${logoutUrl}" method="post" class="logout">
    <input type="submit" value="Log out" />
    <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>

  <div ng-controller="mainTabsCtrl">

    <uib-tabset justified="true">

      <uib-tab heading="Articles">
        <div class="row">

            <!-- ArticleCategory Block -->
            <div class="article-categories col-xs-2" ng-controller="articleCategoryCtrl">
                <button ng-click="openCreateCategoryModal(null)">Create main category</button>
                <ul ng-controller="createArticleCategoryViewCtrl">
                  <li ng-repeat="articleCategory in articleCategories">
                      {{articleCategory.pretitle}}<a href="#" alt="{{articleCategory.description}}">{{articleCategory.title}}</a>

                      <!-- Create child category -->
                                    |
                      <span class="glyphicon glyphicon-plus-sign"
                            aria-hidden="true"
                            ng-click="openCreateCategoryModal(articleCategory.articleCategoryId)">
                      </span>

                      <!-- Edit category -->
                                    |
                      <span class="glyphicon glyphicon-edit"
                            aria-hidden="true"
                            ng-click="openEditCategoryModal(articleCategory.articleCategoryId)">
                      </span>

                      <!-- Remove Category -->
                                    |
                      <span class="glyphicon glyphicon-trash"
                            aria-hidden="true"
                            ng-click="removeCategory(articleCategory.articleCategoryId)">
                      </span>
                                    |
                  </li>
                </ul>
            </div>

            <!-- Articles Block -->
            <div class="col-xs-10">
                <table class="articles table table-striped" ng-controller="articleCtrl">
                <thead>
                    <tr>
                        <th ng-click="sortingListArticle('ticketId', true, 0)">#</th>
                        <th ng-click="sortingListArticle('creationDate', true, 0)">Date</th>
                        <th ng-click="sortingListArticle('author', true, 0)">Author</th>
                        <th ng-click="sortingListArticle('title', true, 0)">Title</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody ng-controller="createArticleViewCtrl">
                    <tr ng-repeat="article in articles">
                        <td>{{article.articleId}}</td>
                        <td>{{article.creationDate}}</td>
                        <td>{{article.userOwner.login}}</td>
                        <td>{{article.title}}</td>
                        <td>
                            <span ng-click="publicTicket(ticket.ticketId)" class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            |
                            <span ng-click="removeTicket(ticket.ticketId)" class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
         </div>
      </uib-tab>

      <uib-tab heading="Tickers">
          <div class="row">
            <table class="table table-striped" ng-controller="ticketsCtrl">
              <thead>
                  <tr>
                    <th ng-click="sortingListTicket('ticketId', true, 0)">#</th>
                    <th ng-click="sortingListTicket('status', true, 0)">Status</th>
                    <th ng-click="sortingListTicket('creationDate', true, 0)">Date</th>
                    <th ng-click="sortingListTicket('phone', true, 0)">Phone</th>
                    <th ng-click="sortingListTicket('title', true, 0)">Name</th>
                    <th ng-click="sortingListTicket('firstName', true, 0)">UserName</th>
                    <th>Actions</th>
                  </tr>
              </thead>

              <tbody ng-controller="ticketViewCtrl">
                  <tr ng-repeat="ticket in tickets" ng-dblclick="openTicket(ticket.ticketId)">
                    <td>{{ticket.ticketId}}</td>
                    <td>{{ticket.status.status}}</td>
                    <td>{{ticket.creationDate}}</td>
                    <td>{{ticket.phone}}</td>
                    <td>{{ticket.title}}</td>
                    <td>{{ticket.firstName}} {{ticket.lastName}}</td>
                    <td>
                      <span ng-click="publicTicket(ticket.ticketId)" class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                      |
                      <span ng-click="removeTicket(ticket.ticketId)" class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </td>
                  </tr>
              </tbody>
          </table>
          </div>
      </uib-tab>

    </uib-tabset>

  </div>

</sec:authorize>
</div>