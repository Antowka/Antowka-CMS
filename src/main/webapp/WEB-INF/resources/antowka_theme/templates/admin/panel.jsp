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

  <form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input id="csrf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>


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

</sec:authorize>
</div>