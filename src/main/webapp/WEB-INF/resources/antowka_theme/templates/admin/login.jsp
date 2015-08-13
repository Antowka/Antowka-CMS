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
        <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
      </tr>

    </table>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

  </form>

  <br><br><br>

  <article>

    <form method="POST" enctype="multipart/form-data" action="/upload" name="uploadFiles">
      <input type="file" name="files"><br />
      <input type="submit" value="Upload">
    </form>

    <br><br><br>

        <button id="test_form">Send Test Form</button>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
        <script>
          $(document).ready(function() {
            $("#test_form").click(function(){
              console.log("Test send form");

              var category = {
                "ticketCategoryId":3,
                "parentCategoryId":0,
       /*         "title":"Test 12344",
                "description":"dfsdgdfh gjhfjghj", */
              }

              var ticket = {
                "firstName": "Anton",
                "lastName": "Nik",
                "email": "662307@gmail.com",
                "title": "Test ticket from client side",
                "phone": "911-99-99",
                "categories": [category],
                "description": "Description for ticket from client side"
              };


              $.ajax({
                url: "tickets/create-ticket",
                type: "post",
                data: JSON.stringify(ticket),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                processData: false,
                success: function (a) {
                  console.log(a);
                },
                error: function (e) {
                  console.log(e);
                }
              });
            });
          });
        </script>
  </article>
</div>
