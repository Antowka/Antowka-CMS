<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 31.07.15
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<div id="login-box">

  <h3>Login with Username and Password</h3>

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>

  <c:url value="/login_validator" var="loginUrl" />

  <form name='loginForm' action="${loginUrl}" method='POST'>

    <table>
      <tr>
        <td>User:</td>
        <td><input type='text' name='login'></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type='password' name="password" /></td>
      </tr>
      <tr>
        <td colspan='2'><input name="submit" type="submit" value="Login" /></td>
      </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

  </form>
</div>
