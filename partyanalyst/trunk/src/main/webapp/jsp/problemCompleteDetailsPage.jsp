<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst-Know | Analyze | Act</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
	<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript">
var id = '${problemHistoryId}';
var logInStat = '${sessionScope.loginStatus}';
var userType = '${sessionScope.UserType}';

function callAjax(jsObj,url)
{	
		var callback = {			
		               success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task == "getProblemCompleteDetails")
							{										
								showProblemDetails(myResults, jsObj);
							}						
							
						}catch (e) {   
						  // 	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function approvalCallAjax(jsObj,url)
{	
		var callback = {			
		               success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);							
							showConfirmation(myResults);
						}catch (e) {   
						  // 	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showConfirmation(results, obj)
{
	var rasonTextEl = document.getElementById("rasonText");
	var rasonTextEl1 = document.getElementById("rasonText1");
	var str = '';
	if(results.resultCode == 0)
	{
		str ="Your comment has been queued for moderation by the Moderator and will be published after approval.!";
		
	} else if(results.resultCode == 1)
	{
		str = results.exceptionMsg;
	}

	$( "#popupOuterDiv" ).dialog({
		title:"",
		autoOpen: true,
		show: "blind",
		width: 300,
		minHeight:100,
		modal: true,
		hide: "explode"
	});

var elmt = document.getElementById("popuoInnerDiv");
if(elmt)
	elmt.innerHTML = str;
 
	
	if(rasonTextEl)
		rasonTextEl.value = '';
	if(rasonTextEl1)
		rasonTextEl1.value = '';
	
	getProblemAllComments(id);
	
	
}
</script>
<style>
#problemHead
{
	
}
body {
	color:#000000;
	font-family:Arial,Helvetica,sans-serif;
	line-height:1.3em;
}
h2 {
	font-size:20px;
	font-weight:normal;
	margin:0;
	padding:0;
}
hr {
	-moz-background-clip:border;
	-moz-background-inline-policy:continuous;
	-moz-background-origin:padding;
	background:transparent url(images/hr.jpg) repeat-x scroll 0 0;
	border:medium none;
	padding:5px;
}
p{
	font-size:14px;
	margin:0;
	line-height:1.3em;	
}
.bluetext {
	color:#3B5998;
}
h3 {
	font-size:15px;
	font-weight:normal;
	margin:0;
	padding:0 0 5px;
}
#description, #description p {
	color:#000000;
	font-family:Verdana,Arial,Helvetica,sans-serif;
	font-size:12px;
	margin:0;
}
.bluebar_heading {
	background-color:ThreeDDarkShadow;
	color:#FFFFFF;
	font-size:15px;
	font-weight:bold;
	padding:8px 5px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
.accept
{
	background: url{images/accept.jpeg};
}
.reject
{
	background: url{images/reject.jpeg};
}
</style>
</head>
<body>
<div id="popupOuterDiv">
	<div id="popuoInnerDiv"></div>
</div>
<div id="problemDetailsPageMainDiv" style="margin:20px;">
<table width="100%" border="0">
	<tr>
		<td width="70%">
			<div id="problemDetails"></div>			
		</td>
		
		<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
		<td width="30%" valign="top">
				<div id="approveProblem" style="height:100px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
						<td width="98%">
							<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
								<span class="headerLabelSpan2" style="text-decoration:none;">
									Give Comments 
								</span>
							</div>
						</td>
						<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
					  </tr>
					</table>
					<div class="divInfo">
						
						<div id="description">
						<p style="margin-top:10px;">If you reside in the same area and know any details about this problem, please give your comments either by Accepting<img height="20" title="Accept" width="20" src="images/icons/accept.png"/> or <img height="20" width="20" title="Reject" src="images/icons/reject.png"/> Rejecting this problem </p>
						</div>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
							<div id="alertDiv" style="color:red;font-weight:bold;margin:2px;"></div>	
							<table style="margin-top:10px;">
							<tr>
								<td><label>Comment <span style="font-weight:bold;color:red;font-size: 18px;">*</span></label></td>
								<td><textarea id="rasonText" name="reasonText" onkeyup="clearError()"></textarea></td>
							</tr>
							</table>												
							<table style="margin:25px;" border="0" cellpadding="0" cellspacing="3">	
								<tr>
									<td><a href="javascript:{}" onclick="submitHandler('Accept')"><img border="0" title="Click To Accept" height="30" width="100" src="images/accept.jpg"></a></td>
									<td><a href="javascript:{}" onclick="submitHandler('Reject')"><img border="0" title="Click To Reject" height="30" width="100" src="images/reject.jpg"></a></td>
								</tr>
							</table>
						</c:if>
						<c:if test="${sessionScope.loginStatus != 'out'}">
						<s:form action="loginInputAction" method="POST">
						<input type="hidden" name="redirectLoc" value="PROBLEM_DISCUSSION" />
						<input type="hidden" name="problemHistoryId" value="${problemHistoryId}" />
						<input type="hidden" name="logInStatus" value="${logInStatus}" />
						<p style="margin-top:10px;">Registered Users Login to post comments</p>
						<input type="submit" Value="Login"/>						
						</s:form>
						</c:if>
					</div>							
				</div>
				
		</td>
		</c:if>		
	</tr>	
</table>
<div id="showAllPostsDiv" style="margin-top:10px;margin-bottom:10px;"></div>
<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
	<td width="98%">
	<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
	<span class="headerLabelSpan2" style="text-decoration:none;">Reply To Above Discussions</span>
	</div>
	</td>
	<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
	</tr>
</table>
<div class="divInfo">	
	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
							<div id="alertDiv1" style="color:red;font-weight:bold;margin:2px;"></div>	
							<table style="margin-top:10px;">
							<tr>
								<td><label>Comment <span style="font-weight:bold;color:red;">*</span></label></td>
								<td><textarea id="rasonText1" cols="100" name="reasonText" onkeyup="clearError()"></textarea></td>
							</tr>
							</table>												
							<table style="margin:25px;" border="0" cellpadding="0" cellspacing="3">	
								<tr>
									<td><input type="button" value="POST" onclick="submitHandler('FollowUp')"></td>									
								</tr>
							</table>
	</c:if>
	<c:if test="${sessionScope.loginStatus != 'out'}">
	<s:form action="loginInputAction" method="POST">
	<input type="hidden" name="redirectLoc" value="PROBLEM_DISCUSSION" />
	<input type="hidden" name="problemHistoryId" value="${problemHistoryId}" />
	<input type="hidden" name="logInStatus" value="${logInStatus}" />
	<p style="margin-top:10px;">Registered Users please Login to follow up in this discussion forum</p>
	<input type="submit" Value="Login"/>						
	</s:form>
	</c:if>
</div>
</c:if>
</div>

<script type="text/javascript">

executeOnload();

</script>
</body>
</html>