<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Now</title>
</head>
<body>

<style>
	.requ:after { content:"*";color:red;}
	span.ok{background-image:url("img/accept.png");}
	#errorImgId{display:none}
	#correctImgId{display:none;}
	#myModal {width: 700px;}
	#formDivId{width:800px;margin-left:auto;margin-right:auto;}
	#formDivId legend,#saveUser{margin-left:140px;margin-bottom:5px;}
	#status{background-image:url(img/accept.png)}
</style>

<div id="formDivId">
		   <form class="form-horizontal" name='userDetailsForm' action="registerUserProfile.action" method="post">
				<legend>Personal Information</legend>
				<div class="control-group">
					<label class="control-label requ" for="firstName">First Name</label>
					<div class="controls ">
						<input type="text" name="firstName" id="firstNameId" placeholder="First Name">  
					</div>
					
					
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="lastName">Last Name</label>
					<div class="controls">
						<input type="text" id="lastNameId" name="lastName" placeholder="Last Name">
					</div>
					
				</div>
				
				<div class="control-group ">
					<label class="control-label requ" for="emailid">Email-Id</label>
					<div class="controls ">
						<input type="text" id="emailId" name="emailId" placeholder="Email-Id"><img src="img/accept.png" id="correctImgId"/>  <img src="img/error.png" id="errorImgId"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="mobileno">Mobile Number</label>
					<div class="controls">
						<input type="text" id="mobileId" name="mobileNo" placeholder="Mobile Number"><div><img src="img/accept.png" id="correctImgId"/></div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="epicNo">EPIC Number</label>
					<div class="controls">
						<input type="text" id="epicNoId" name="epicId" placeholder="EPIC Number">
					</div>
				</div>
				
				<div class="control-group">
					<s:actionerror />
					<s:fielderror />
				</div>
				${resultStr}
			</form>
			
		
			<button class="btn btn-primary" id="saveUser">Save changes</button>
		
	</div>
		
		
		<script>
	
	$('#saveUser').click(function(){
		document.userDetailsForm.submit();
	});
	
	$(document).ready(function () {
		var loginStatus = '${resultStr}';
		if(loginStatus == "success")
		{
			$('#loginModal').modal('show');
			$('#errorMsg').html('<b style="color:green">Login Successfully Please Wait.....</b>');
			$('#loginModal').modal('show').delay('slow').modal('hide');
			window.location.href = "homePage.action";
			
		}
		else if(loginStatus == "failure")
		{
			$('#loginModal').modal('show');
			$('#errorMsg').html('<b style="color:red">Please Enter Valid User Name and Paaword</b>');
		}
	});
	$("#emailId").blur(function(){
		var emailId=$('#emailId').val();
		var s=isValidEmailAddress(emailId);
		if(!s){
			$('#errorImgId').css('display','inline-block');
			return;
		}
		
			
		var jsObj =
		{  	
			emailId:emailId,
			task:'emailValidating'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "validateEmailAction.action?"+rparam;
		callAjax(jsObj, url);
		
	});
	
	function isValidEmailAddress(emailAddress) {
		var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
				
		//var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		//return regex.test(emailAddress);
		
		return pattern.test(emailAddress);
	};
	
	function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="emailValidating")
								{
									statusOfEmail(myResults);
								}
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	function statusOfEmail(myResults){
		if(myResults=="FAILED"){
			$('#errorImgId').css('display','inline-block');
		}
		else{
			$('#correctImgId').css('display','inline-block');
		}
	}
	
	</script>
</body>
</html>