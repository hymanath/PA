<html>
<header>

</header>
<body>


  
  <div class="container">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" id="unameId" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" id="pwdId" required>

    <button type="button" onclick="userLogin()">Login</button>
  </div>

  <div class="container" style="background-color:#f1f1f1">
	<span id="errorMsgId" ></span>
  </div>

</body>
<script type = "text/javascript" src = "Assests/js/jquery-1.11.3.js"></script>
<script src="Assests/js/login.js" type="text/javascript"></script>
</html>