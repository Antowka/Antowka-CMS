<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Anton Nik
  Date: 25.07.15
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>

<div class="container" style="width: 300px;">
  <c:url value="/j_spring_security_check" var="loginUrl" />
  <form action="${loginUrl}" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus>
    <input type="password" class="form-control" name="j_password" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
  </form>
</div>
