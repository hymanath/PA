<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Electoral Connect Election Commission of India-AP</title>
		<meta name="Electoral Connect" content="Electoral Connect Election Commission of India-AP">
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" href="css/style.css">
		<!-----Fonts----->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		
		<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	
		<script type="text/javascript" src="js/jquery/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="js/jquery/jquery-ui-1.8.5.custom.min.js"></script>
		<script type="text/javascript" src="bootstrap/bootstrap.js"></script>
		
		<!--YUI SCRIPT-->
		<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
		
		<script type="text/javascript" src="js/jquery/jquery.validate-1.7.min.js"></script>
    </head>
<body>
<style>
	#loginModal{width:600px;}
</style>
<!---  Header ---->
		<header>
			<div class="row">
				<!---header Container---->
				<div class="container">
					<div class="row-fluid">
					
						<div class="span6">
							<h1>
								<a href="/" title="Electoral Connect-Election Commission of India-AP">
									<img src="image/logo.png" Alt="Electoral Connect Election Commission of India-AP" />
								</a>
							</h1>
						</div>
						
						<div class="span6">
							<nav>
								<ul class="inline pull-right">
									<!--<li><a href="#" title="Home">Home</a></li>
									<li><a href="#" title="About Us">About US</a></li>
									<li><a href="#" title="Contact Us">Contact Us</a></li>-->
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'out'}">
									<li><a title="Home" id='loginId'>Login</a></li>
									<!--<li><a title="Admin" id='adminId' onClick="openAdminPage();">Admin</a></li>-->
									<li><a title="About Us" id='registerId'>Register</a></li>
									</c:if>
									
									<c:if test="${sessionScope.loginStatus == 'in'}">
									<li style="color: white;"><c:out value="Welcome, ${sessionScope.USER.firstName} ${sessionScope.USER.lastName}|"/></li>
									</c:if>
									<c:if test="${sessionScope.USER.isAdmin == 'true' && sessionScope.loginStatus == 'in'}">
									<li><a title="Admin" id='adminId' onClick="openAdminPage();">Admin</a></li>|
									</c:if>
									<c:if test="${sessionScope.loginStatus == 'in'}">
									<li><a title="Logout" id='logoutId' href="logoutAction.action">Logout</a></li>
									</c:if>
																		
									
									
								</ul>
							</nav>
						</div>
						
					</div>
				</div><!---header Container---->
			</div>
		</header><!---header End---->
		
		<div id="decoBodyId">
			<decorator:body/>
			
		<div class="modal hide fade" id="loginModal">
				
		<div class="modal-body">
			<a class="close" data-dismiss="modal">X</a>
			<p style="font-size:16px;font-weight:bold;"></p>
			 <form class="form-horizontal" name='personalInfoForm' action='userLonginVerficationAction.action' method='post'>
				<legend>Please Login</legend>
				<div class="control-group">
					<label class="control-label" for="username">UserName</label>
					<div class="controls">
						<input type="text" id="userName" placeholder="Your Name" name='userName'>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="password" >Password</label>
					<div class="controls">
						<input type="password" id="password" placeholder="password" name='password'>
					</div>
				</div>
				<div id="loginStatusDiv"></div>	
				<div style="float:right;"><a id="forgetPassword">forget password</a></div>
			
		</div>
		<div class="modal-footer">
			<div id="successMsg" style="float:left;"></div>
			<div id="errorMsg" style="float:left;"></div>
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" type="submit">Login</button>
		</div>
		</form>
	</div>
	
	<div class="modal hide fade" id="passwordModal">
			
		<div class="modal-body">
		<div class="control-group" style="color:red;">
					<s:actionerror />
					<s:fielderror />
				</div>	
			<div id="errorMessage"></div>
			<a class="close" data-dismiss="modal">X</a>
			<p style="font-size:16px;font-weight:bold;"></p>
			 <form class="form-horizontal" name='personalInfoForm' action='changePasswordAction.action' method='post' >
				<legend>Change Password</legend>
				<div class="control-group">
					<label class="control-label" for="passward">Enter Password</label>
					<div class="controls">
						<input type="password" id="orginalpassword" placeholder="Enter Password" name='orginalpassword' onChange="">
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="newPassword" >New Password</label>
					<div class="controls">
						<input type="password" id="newPassword" placeholder="New password" name='newPassword'>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="conformPassword" >Conform Password</label>
					<div class="controls">
						<input type="password" id="conformPassword" placeholder="Conform password" name='conformPassword'>
					</div>
				</div>
				<div id="loginStatusDiv"></div>				
			
		</div>
		<div class="modal-footer">
			<div id="successMsg" style="float:left;"></div>
			<div id="errorMsg" style="float:left;"></div>
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" type="submit">Save</button>
		</div>
		</form>
	</div>
	
	<div class="modal hide fade" id="forgetPasswordModel">
				
		<div class="modal-body">
			<a class="close" data-dismiss="modal">X</a>
			<p style="font-size:16px;font-weight:bold;"></p>
			 
				<legend>Forgot Password</legend>
				<div class="control-group">
					<label class="control-label" for="username">UserName</label>
					<div class="controls">
						<input type="text" id="userNameForPassword" placeholder="Your Name" name='userName'>
					</div>
				</div>
				<div id="errorDiv"></div>
		</div>
		<div class="modal-footer">
			<div id="successMsg" style="float:left;"></div>
			<div id="errorMsg" style="float:left;"></div>
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<a class="btn btn-primary" onClick="getForgetdPassword();">Submit</a>
		</div>
		
	</div>
		</div>
		
		<footer>
			&copy;2013 Electoral Connect
		</footer>
		<script>
			$('#registerId').click(function(){
				$('#registerId').attr('href','registerUser.action');
			});
			$('#adminId').click(function(){
				$('#adminId').attr('href','adminPageAction.action');
			});
			
			$('#loginId').click(function(){
				$('#loginModal').modal('show');
			});
			$('#forgetPassword').click(function(){
				$('.close').trigger('click',function(){
				});
				$('#forgetPasswordModel').modal('show');
				});

			function getForgetdPassword()
			{
				var username = $('#userNameForPassword').val();
				var jsObj =
				{  	
					username : username,
					task     : 'getForgetdPassword'
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "forgotPasswordAction.action?"+rparam;
				callAjax(jsObj, url);
			}
			/* function checkingForValidUser()
			{
				var userName = $('#userName').val();
				var passward = $('#password').val();
				var jsObj =
				{  	
					userName : userName,
					passward : passward,
					task     : 'checkForUserLogin'
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
				var url = "userLonginVerficationAction.action?"+rparam;
				callAjax(jsObj, url);
			}
			
			function ShowLoginStatus(myResults)
			{
				loginStatusDiv
				if(myResults == "failure")
				{
					$('#loginStatusDiv').html("<b style='color:red;'>Please Enter Valid Username And Passward</b>");
				}
				else
				{
					$('#loginStatusDiv').html("<b style='color:green;'>Successfully Login Please Wait...</b>");
					
				}
			}*/
			
		   function callAjax(jsObj,url)
		   {
				var myResults;

				var callback = 
				{			
				   success : function( o )
				   {
						try {												
							myResults = YAHOO.lang.JSON.parse(o.responseText);					
							if(jsObj.task =="getForgetdPassword")
							{
								if(myResults == "success")
								{
									//alert("Password is send to your mail please check once..");
									$('#errorDiv').html('<b style="color:green">Password is send to your mail please check once..</b>');
									//$('.close').trigger('click',function(){
									//});
								}
								else
								{
									//alert("Please Enter Correct Username?");
									$('#errorDiv').html('<b style="color:red">Please Enter Correct Username?</b>');
								}
								
							}
							}catch (e) {
							 
							}  
				},
				scope : this,
				failure : function( o )
					{
						//alert( "Failed to load result" + o.status + " " + o.statusText);
					}
				};

				YAHOO.util.Connect.asyncRequest('POST', url, callback);
		} 
		</script>
</body>
</html>

