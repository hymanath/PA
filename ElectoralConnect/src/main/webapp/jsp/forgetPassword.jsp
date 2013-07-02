<http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
<script type="text/javascript"  src="js/RegisterFormValidation.js"></script>
<!--<div>
<span>Enter UserName : </span>
<input type="text" name="username" id="userName"></input>
</div>
<div><a class="btn btn-primary" id="forgetPassword" onclick="getForgetdPassword()">Submit</a></div>-->

<script type="text/javascript">
alert(1234);
var statusMessage = '${statusMessage}';
alert(statusMessage);
/* function getForgetdPassword()
{
	var username = $('#userName').val();
	var jsObj =
	{  	
		username : username,
		task     : 'getForgetdPassword'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "forgotPasswordAction.action?"+rparam;
	callAjax(jsObj, url);
}
function callAjax(jsObj,url){
	 var myResults;

		 var callback = {			
	               success : function( o ) {
						try {												
							myResults = YAHOO.lang.JSON.parse(o.responseText);					
							if(jsObj.task =="getForgetdPassword")
							{
								//alert(12345);
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
} */
</script>
</body>
</html>