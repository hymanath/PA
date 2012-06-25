<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script>
<title>Change Password</title>
</head>
<body>
<div style="margin-top:10px;margin-left:150px;background:#fff;width:700px;padding:10px;">
<div id="changeNewUserPassword">
		<p>Thanks <span style="font-weight:bold;"><c:out value="${sessionScope.name}" /></span>,</p>
		<p>Your registration completed successfully. We sent password to your email : <b><c:out value="${sessionScope.USER.email}" /></b></p>
		<p>Please verify email to change your password.</p>
		<div id="changePasswordDiv" style="margin-top: 21px; width: 373px; margin-left: 192px;">
		<div id="password_window" style="background-color: rgb(120, 152, 188); color: rgb(255, 255, 255); font-weight: bold; padding: 5px; width: 331px;">Change Password</div>
		<div id="password_window_body_div" style="background-color: rgb(255, 255, 255); color: rgb(58, 67, 71); padding: 5px; border: 1px solid rgb(211, 211, 211); width: 329px;">
		<table style="margin: 7px;">
		<tbody><tr><td colspan="2"><img src="images/icons/infoicon.png">
		Fields marked with (<font color="red">*</font>) are mandatory</td></tr><tr><td class="tdStyle"><font style="color:red">*</font>&nbsp;Current Password</td><td>  <input type="password" name="currentPassword" id="currentPwdId" cssclass="textFieldStyle"></td></tr>
		<tr><td class="tdStyle"><font style="color:red">*</font>&nbsp;New Password</td><td><input type="password" name="newPassword" id="newPwdId" cssclass="textFieldStyle">
	    </td></tr>
		<tr><td class="tdStyle"><font style="color:red">*</font>&nbsp;Confirm Password</td><td>  <input type="password" name="confirmPassword" id="confirmPwdId" cssclass="textFieldStyle">
        </td></tr></tbody></table>
        	</div>
		<div id="password_window_footer_div" class="yui-skin-sam" style="background-color: rgb(214, 229, 233); padding: 5px; width: 331px;">
			<table width="97%">
			<tbody><tr>
			
			<td align="center" width="99px">
				<input type="button" value="Change Password" onclick="changePassword();" id="changeButton">
				<input type="button" value="No" id="cancelButton" style="width:52px; text-align:center;">
			</td>
			</tr>
			</tbody></table>	
		</div>
		</div>
		</div>
		<div id="password_window_errorMsg" style="margin: 10px; font-weight: bold; font-size: 13px;"></div>
		
		<div id="loginDiv" style="display:none;font-size: 13px;">
		<div style="font-weight: bold; margin-top: 22px; margin-left: 188px;">Please Login to continue</div>
		 <div style="border: 1px solid rgb(211, 211, 211); width: 344px; margin-top: 13px; margin-left: 188px;"">
		<s:form name="loginForm" action="loginAction" method="POST">
		
		<table class="loginDetailTable" width="100%"  style="background:#e3e3e3;">
								<tr>
									<td width="10"><img src="images/icons/indexPage/listIcon.png"></img></td>
									<th style="font-size:12px;color:#AB6413;text-decoration:underline;">Sign in with your Party Analyst account</th>
								</tr>
							</table>
							<table class="loginDetailTable" style="padding:9px;margin-left:20px;">								
								<tr>
									<th><s:label theme="simple" value="%{getText('userName')}"></s:label></th>
									<td>
									<div id="validate"></div>
										<s:textfield theme="simple" name="userName" id="userName" label="%{getText('userName')}"/>
									</td>
								</tr>
								<tr style="width: 250px; height: 43px;">
									<th><s:label theme="simple" value="%{getText('password')}"></s:label></th>
									<td>
										<s:password theme="simple" name="password" label="%{getText('password')}"/>
									</td>
								</tr>
								<tr>
									<td align="right"><s:submit value="Sign In" cssClass="btnStyle" align="center"/></td></tr>
									<!--<tr>
									<td colspan="2"><a href="javascript:{}" onclick="showForgotPasswordPanel()" style="margin-left:179px;color:#0174DF;" >Forgot Password</a></td>
								</tr>-->
							</table>
							
						</s:form></div></div>
		</div>

		<script type="text/javascript">
		
	function changePassword()
	{
		
	var cpwd = document.getElementById("currentPwdId").value;
	var npwd = document.getElementById("newPwdId").value;
	var cfmpwd = document.getElementById("confirmPwdId").value;
	var resultDIVEle = document.getElementById("password_window_errorMsg");
	resultDIVEle.innerHTML = "";
	if(cpwd.length > 0 &&cpwd.length < 6){
		resultDIVEle.innerHTML = "<font color='red'>Current Password Minimum Of 6 Characters.</font>";
	        return;
	}
	if(npwd.length > 0 &&npwd.length < 6){
		resultDIVEle.innerHTML = "<font color='red'>New Password Minimum Of 6 Characters.</font>";
	        return;
	}
	if(cpwd.value=="")
     resultDIVEle.innerHTML="<font color='red'>Please enter password.</font>";	
     if(npwd.length > 0 && cfmpwd.length > 0 && npwd != cfmpwd){
 		resultDIVEle.innerHTML = "<font color='red'>Passwords do not match.</font>";
         return
	}
	if(cpwd==''){
     resultDIVEle.innerHTML = "<font color='red'>Please enter current password.</font>";
	return;
	}
	if(npwd==''){
     resultDIVEle.innerHTML = "<font color='red'>Please enter new password.</font>";
	return;
	}
	if(cfmpwd==''){
	resultDIVEle.innerHTML = "<font color='red'>Please enter confirm password.</font>";
	return;
	}
	if(cpwd == npwd)
		{
		resultDIVEle.innerHTML = "<font color='green'>Your new passward is same as existing one.</font>";
		setTimeout("closewdw()",3000);
		return;
		}
	if(cpwd!='')
		{
		str = '<font color="#000000">Sending Your Request.Please wait</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
       	var jsObj={
          crntPassword:cpwd,
          newPassword:npwd,
		  task:"changePassword"	 
	};
        var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "changeNewUserPasswordAction.action?"+rparam;						
		callAjax(jsObj,url);
		}
}


function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
									
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "changePassword")
					{
						showResults(results);
					}

			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

		function showResults(results)
		{
			
		if(results.resultCode==0){
			document.getElementById("changeNewUserPassword").style.display = 'none';
			
			var result = document.getElementById("password_window_errorMsg");
		result.innerHTML ='<div style="color:green">Password changed successfully, Window Closing...</div>';
        
		setTimeout("closewdw()",3000);
		if(document.getElementById("loginDiv"))
			{
		  document.getElementById("loginDiv").style.display ='block';
			result.innerHTML ='<div style="color:green">Password changed successfully.....</div>';
			}
		}	
		else if(resultsresultCode==1){
			var result = document.getElementById("password_window_errorMsg");
		var str='';
			str+='<div style="color:red"> Invalid Current Password</div>';	
		result.innerHTML = str;

		}
}
		</script>
</body>
</html>