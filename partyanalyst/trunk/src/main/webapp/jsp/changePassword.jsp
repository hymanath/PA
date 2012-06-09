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
		<p>Thanks <span style="font-weight:bold;"><c:out value="${sessionScope.UserName}" /></span>,</p>
		<p>Your registration completed successfully. We sent password to your email : <b><c:out value="${sessionScope.USER.email}" /></b></p>
		<p>Please verify email to change your password.</p>
		<div id="changePasswordDiv" style="margin-top: 21px; width: 373px; margin-left: 192px;">
		<div id="password_window" style="background-color: rgb(120, 152, 188); color: rgb(255, 255, 255); font-weight: bold; padding: 5px; width: 331px;">Change Password</div>
		<div id="password_window_body_div" style="background-color: rgb(255, 255, 255); color: rgb(58, 67, 71); padding: 5px; border: 1px solid rgb(211, 211, 211); width: 329px;">
		<table style="margin: 7px;">
		<tbody><tr><td colspan="2"><img src="images/icons/infoicon.png">
		Fields marked with (<font color="red">*</font>) are mandatory</td></tr><tr><td class="tdStyle"><b style="color:red">*</b>Current Password</td><td>  <input type="password" name="currentPassword" id="currentPwdId" cssclass="textFieldStyle"></td></tr>
		<tr><td class="tdStyle"><b style="color:red">*</b>New Password</td><td><input type="password" name="newPassword" id="newPwdId" cssclass="textFieldStyle">
	    </td></tr>
		<tr><td class="tdStyle"><b style="color:red">*</b>Confirm Password</td><td>  <input type="password" name="confirmPassword" id="confirmPwdId" cssclass="textFieldStyle">
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
		<div id="password_window_errorMsg" style="margin: 10px; font-weight: bold; font-size: 13px;"></div>
		</div>
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
</body>
</html>