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
		<link rel="SHORTCUT ICON" type="image/x-icon" href="image/fevicon.png">
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" href="css/style.css">
		<!-----Fonts----->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
		
		<link  rel="stylesheet" type="text/css" href="js/jquery/development-bundle/themes/base/jquery.ui.core.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jquery/development-bundle/themes/base/jquery.ui.theme.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jquery/development-bundle/themes/base/jquery.ui.accordion.css"/>
		<link  rel="stylesheet" type="text/css" href="js/jquery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	
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
	label.valid {
		  color: green !important;
		  background: url(img/accept.png) center no-repeat !important;
		  display: inline-block;
		  text-indent: -9999px;  
		}
		label.error {
		  font-weight: bold; 
		  color: red;
		  background: url(img/error.png) no-repeat left center;
		  padding: 1px 16px;
		  display: inline-block;
		}
		
		.mainMenu nav .changePassword ul li  a:hover{padding:3px 20px;width:auto;height:auto;}
		.mainMenu .dropdown-menu{position:absolute;}
		
		
		ul.nav li.dropdown:hover > ul.dropdown-menu{
			display: block;    
		}
		
		a.menu:after, .dropdown-toggle:after {
			content: none;
		}
</style>
<!---  Header ---->
		<header>
			<div class="row">
				<!---header Container---->
				<div class="container">
					<div class="row-fluid">
					
						<div class="span6">
							<h1>
								<a href="homePage.action" title="Electoral Connect-Election Commission of India-AP">
									<img src="image/logo.png" Alt="Electoral Connect Election Commission of India-AP" />
								</a>
							</h1>
						</div>
						
						<div class="span6 mainMenu">
							<nav>
								<ul class="inline pull-right">
									<!--<li><a href="#" title="Home">Home</a></li>
									<li><a href="#" title="About Us">About US</a></li>
									<li><a href="#" title="Contact Us">Contact Us</a></li>-->
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'out'}">
									<li><a title="Home" id='loginId'>Login</a></li> |
									<!--<li><a title="Admin" id='adminId' onClick="openAdminPage();">Admin</a></li>-->
									<li><a title="About Us" id='registerId'>Register</a></li> |
									</c:if>
									
									<c:if test="${sessionScope.loginStatus == 'in'}">
									 <div class="btn-group changePassword">
										<a class="btn dropdown-toggle " data-toggle="dropdown" href="#">
										<i class="icon-user"></i>
										<span class="caret"></span>
										</a>
										<ul class="dropdown-menu">
											<li id="changePassword"><a>Change Password</a></li>
										</ul>
									</div>
									<li style="color: white;"><c:out value="Welcome, ${sessionScope.USER.firstName} ${sessionScope.USER.lastName} |"/></li>
									</c:if>
									<c:if test="${sessionScope.USER.isAdmin == 'true' && sessionScope.loginStatus == 'in'}">
									<li><a title="Admin" id='adminId'>Admin</a></li> |
									</c:if>
									<c:if test="${sessionScope.loginStatus == 'in'}">
									<li><a title="Logout" id='logoutId' href="logoutAction.action">Logout</a></li> |
									</c:if>
									<li><a title="Search Now" id='SearchId' href="searchPageAction.action">Search</a></li> 
									  							
	
								</ul>
							</nav>
						</div>
						<div class="span10 " style="margin-top: -10px;"><a class="btn btn-inverse pull-left" href="homePage.action"><i class="icon-home icon-white" style="height:20px;"></i></a></div>

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
			 <form class="form-horizontal" name='loginInfoForm' action='userLonginVerficationAction.action' method='post'>
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
				<button class="btn btn-primary pull-right" type="submit" id="loginNow">Login</button>
				
			
		</div>
		<div class="modal-footer">
			<div id="successMsg" style="float:left;"></div>
			<div id="errorMsg" style="float:left;"></div>
			<div class="btn btn-info"><span id="forgetPassword">Forget Password</span></div>
			<button class="btn btn-info" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
		</form>
	</div>
	
	<div class="modal hide fade" id="passwordModal">
			
		<div class="modal-body">
			
			<div id="errorMessage"></div>
			<a class="close" data-dismiss="modal">X</a>
			<p style="font-size:16px;font-weight:bold;"></p>
			 <form class="form-horizontal" name='changePwdForm' action='changePasswordAction.action' method='post' >
				<legend>Change Password</legend>
				<div class="control-group">
					<label class="control-label" for="orginalpassword">Enter Password</label>
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
					<label class="control-label" for="conformPassword" >Confirm Password</label>
					<div class="controls">
						<input type="password" id="conformPassword" placeholder="Conform password" name='conformPassword'>
					</div>
				</div>
				<div class="control-group" style="color:red;">
					<s:actionerror />
					<s:fielderror />
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
			 <form class="form-horizontal" name="forgotpwd">
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
			<a class="btn btn-primary" onClick="getForgetdPassword();">Submit</a>
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		</div>
		</form>
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
			
			$('#loginNow').click(function(){
				document.loginInfoForm.submit();
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
	
		$(function(){
			
	     $('[name="loginInfoForm"]').validate({
	      onfocusout: function(e) {
          this.element(e);
      }, onkeyup: false,
	  rules: {
		userName: {	
			required: true
			},
		password: {
		    required :true
			}
	  },	
	  messages: {
		userName: {
			required: " Enter the Username" 
			},
		password: {
			required: " Enter the password"
		    }
	  },
	  success: function(element) {
	      element.text('ok').addClass('valid');
			},
	});
});
$(function(){
	$('[name="forgotpwd"]').validate({
	    onfocusout: function(e) {
         this.element(e);
         }, onkeyup: false,
	  rules: {
		userName: {	
			required: true,
			email: true
			}
	  },	
	  messages: {
		userName: {
			required: "Please enter Email ID" ,
            email: "Enter valid emailid"
			}	
	  },
	  success: function(element) {
	      element.text('ok').addClass('valid');
			},
    });

});

$(function(){
			
	     $('[name="changePwdForm"]').validate({
	      onfocusout: function(e) {
          this.element(e);
			  }, onkeyup: false,
			  rules: {
				orginalpassword: {	
					required: true
					},
				newPassword: {
					required :true
					},
				conformPassword: {
					required :true,
					equalTo: "#newPassword"
					}
			  },	
			  messages: {
				orginalpassword: {
					required: " Enter Old Password" 
					},
				newPassword: {
					required: " Enter New Password"
					},
				conformPassword: {
					required: "Enter Confirm password",
					equalTo  : "Enter Same Passwords For both new and conform passwords"
					}
			  },
			  success: function(element) {
				  element.text('ok').addClass('valid');
					},
			});
});

$('#changePassword').click(function(){
	$('#passwordModal').modal('show');
});

</script>
</body>
</html>

