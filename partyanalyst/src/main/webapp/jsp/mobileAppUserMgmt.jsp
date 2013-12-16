<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

<!-- YUI Dependency files (Start) -->
 <script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
 <script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
 <script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
 <script type="text/javascript" src="js/yahoo/element-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
 <script src="js/yahoo/resize-min.js"></script> 
 <script src="js/yahoo/layout-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/container-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/yui-min.js"></script>
 <script type="text/javascript" src="js/json/json-min.js"></script>
 <script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/get-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
 <script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
 <script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
 <!-- Skin CSS files resize.css must load before layout.css --> 
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
 <link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
 <link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
 <link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
 <link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
 <link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

<!-- YUI Dependency files (End) -->
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
 <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
 <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
 <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
 <script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

 <link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
 <script type="text/javascript" src="js/validationTools/validationTools.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<title>Party Analyst</title>

<style type="text/css">
 #mainDiv{margin: 20px auto; float: none; width: 950px;}
</style>
</head>
<body>
 <div id="mainDiv">

  <div id="userData" class="widget blue">
</div>
</div>
<script type="text/javascript">
function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getUserData")
								{
								 buildUserData(myResults);	
								
								}
								else if(jsObj.task == "changeAccess")
								{
									
									buildStatus(myResults);
									
								}
								
							}
									catch (e) {
							    //alert(Exception);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }

function getMobileAppUsersData()
 {
 var jsObj=
		{
		 
		 task:"getUserData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMobileAppUsersDataAction.action?"+rparam;						
		callAjax(jsObj,url);	
 }
 function buildUserData(result)
 {
	 if(result !=null && result !='')
	 {
	 var str='';
	  str+='<h2 style="text-align:center;"> Mobile App Users</h2>';
	 str+='<div style="margin-top:10px;color:#222;">';
	
	 str+='<input type="checkbox" id="selectAll"  style="margin-top:0px;"/>  &nbsp;Select All &nbsp;';
	 str+='<input type="checkbox" id="unselectAll" style="margin-top:0px;"/> &nbsp;Unselect All';
	 str+='</div>';
	 str+='<div style="margin-top:10px;">';
	str+='<div id="errorDiv"></div>';
	 str+='<table class="table table-bordered" style="color:#222;">';
	 str+='<tr>';
	 str+='<th>Select</th>';
	 str+='<th>SNO</th>';
	 str+='<th>Name</th>';
	 str+='<th>User</th>';
	 str+='<th>Unique Code</th>';
	 str+='<th>Access</th>';
	  str+='<th>Last Authorised Time</th>';
	 str+='</tr>';
	 var j=1;
	 for(var i in result)
	 {
	 str+='<tr>';
	 str+='<td><input type="checkbox" id="checkedEle" class="checkEle"/><input type="hidden" value='+result[i].appId+' class="uniquecodeCls"></td>';
	 str+='<td>'+j+'</td>';
	 str+='<td>'+result[i].name+'</td>';
	 str+='<td>'+result[i].userName+'</td>';
	 str+='<td>'+result[i].uniqueCode+'</td>';
	 str+='<td>'+result[i].accessValue+'</td>';
	 str+='<td>'+result[i].dateOfBirth+'</td>';
	
	 str+='</tr>';
	j++;
	 }

	 str+='</table>';
	 str+='<input type="button" class="btn btn-success" value="Enable" onclick="EnableorDisableAccess(\'enable\')"/>&nbsp;';
	 str+='<input type="button"  class="btn btn-success" value="Disable" onclick="EnableorDisableAccess(\'disable\')"/>';
	 str+='<img id="AjaxImg" src="images/icons/search.gif" style="display:none;"/>';
	  str+='</div><br/>';
	$("#userData").html(str);
	 }
 }

function EnableorDisableAccess(type)
{
	$("#AjaxImg").show();
	var mobileAppuserIds = [];
	$(".checkEle").each(function () {

	 if($(this).is(':checked'))
		{
	var  mobileAppuserId =$(this).closest("tr").find(".uniquecodeCls").val();
    mobileAppuserIds.push(mobileAppuserId);
		}
});
var jsObj=
		{
		 mobileAppuserIds:mobileAppuserIds,
		 type:type,
		 task:"changeAccess"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "enableOrdisableAccessAction.action?"+rparam;						
		callAjax(jsObj,url);	
}
$("#selectAll").live("click",function()
{
	$("#unselectAll").attr("checked",false);
	$(".checkEle").attr("checked",true);
});
$("#unselectAll").live("click",function()
{
	$("#selectAll").attr("checked",false);
	$(".checkEle").attr("checked",false);
});

function buildStatus(result)
{
	$("#errorDiv").html('');
	if(result.resultCode == 0)
	{
    getMobileAppUsersData();
	}
	else
	$("#errorDiv").html('Error Occured,Try again');
}

/*function getUserDetails(userId)
{
		var jsObj=
		{
		 userId:userId,
		 type:type,
		 task:"changeAccess"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "enableOrdisableAccessAction.action?"+rparam;						
		callAjax(jsObj,url);	
}

function openWindow(userId)
{
	var urlstr ='mobileAppUserInfo.action?userId="+userId+"&';
	var browser1 = window.open(urlstr,"userDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
		browser1.focus();
}*/
 getMobileAppUsersData();
 </script>
</body>
</html>

