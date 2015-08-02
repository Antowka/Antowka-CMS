<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.07.15
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<h1>Wellcome to Admin Panel</h1>

<sec:authorize access="isAuthenticated()">

  <c:url var="logoutUrl" value="/logout"/>

  <form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>

</sec:authorize>