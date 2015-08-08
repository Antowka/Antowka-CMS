<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 25.07.15
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>

<h2>Main Page</h2>

<a href="/login">Login Page</a>
<br><br>
<button id="test_form">Send Test Form</button>
<br><br>
<p>Main blok: ${vars.settings.site_description}</p>


<script>

  $(document).ready(function() {
    $("#test_form").click(function(){

      console.log("Test send form");

      var ticket = {
        "firstName": "Anton",
        "lastName": "Nik",
        "email": "662307@gmail.com",
        "title": "Test ticket from client side",
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
