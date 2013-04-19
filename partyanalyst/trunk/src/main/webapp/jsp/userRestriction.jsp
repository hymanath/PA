
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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
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
	<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 

	<!-- YUI Dependency files (End) -->

	<style>
	#MainDiv{
	margin-left:auto;
	margin-right:auto;
	width:980px;
	}
.requiredFont{
	color:red;
	font-size:13px;
}
.selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
	</style>
</head>
<body>
<script type="text/javascript">
function buildAllusersInList(results)
{
var selectOption = document.getElementById("users");
	for(var i in results)
		{
			if(results[i] == null)
				continue;
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				selectOption.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				selectOption.add(opElmt); // IE only
				}
		}
}
function buildusersInList(results)
{
	var selectOption = document.getElementById("usersList");
	for(var i in results)
		{
			if(results[i] == null)
				continue;
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				selectOption.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				selectOption.add(opElmt); // IE only
				}
		}
}
function showStatusForUser(result)
{
if(result.resultCode == 0)
	{
	$("#errormsg").html("login restriction Saved Successfully").css("color","green");
	getAllUsers();
	}
}

function showStatusForUserIpAddress(result)
{
	if(result.resultCode == 1)
	{
	$("#errorDiv").html("IP Address already exist").css("color","red");
	}
	else
	$("#errorDiv").html("IP Address Saved Successfully").css("color","green");
	document.getElementById("Iptext").value = '';
	getAllIpAddressForUser();
}
function callAjax(jObj,url)
{
	 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);			if(jObj.task == "getAllRestrictedUsers")
								{
									buildAllusersInList(myResults);
								}
								else if(jObj.task == "getAllUsers")
								{
									buildusersInList(myResults);
								}
									else if(jObj.task == "saveRestrictedUser")
								{
										showStatusForUser(myResults);
										
								}
								else if(jObj.task == "saveUserIpAddress")
								{
										showStatusForUserIpAddress(myResults);
								}

								else if(jObj.task == "getAllIpAddressForUser")
								{
								buildIpAddress(myResults);	
								}
								else if(jObj.task == "deleteUserIpAddress")
								{
									showDeleteStatus(myResults);
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

	function buildIpAddress(result)
	{
		
		
		if(result.length==0)
		{
			
			$("#deleteIpAddressDiv").css("display","none");
			return;
		
		}
		$("#deleteIpAddressDiv").css("display","block");
		var str ='';
		
		var divId=document.getElementById("IpAddressDiv");
		str +='<div>';
		str +='<table>';
		
		for(var i in result)
		{
		if(i%3==0)
		str +='<tr>';
				
		str +='<td><input type="checkbox" name="type" class="checkedvalue">&nbsp;&nbsp;&nbsp;';
		
		str +='<input type="hidden" name="useraccessIp" id="userAccessRegionId" value='+result[i].userAccessRegionId+' style="display:none;">';
			str +='</td>';
		str +='<td>'+result[i].ipAddress+'&nbsp;&nbsp;&nbsp;</td>';
		if((i%3)+1==0)
		str +='</tr>';
		}
		
		str +='</table>';
		
		
		str +='</div>';
		
		str +='<div style="margin-top:10px;margin-bottom:10px;text-align: center;">';
		str+='<input style="position: absolute:top: 50%;" class="btn btn-success" type="button" value="Delete" onclick="deleteUserIpAddress();">';
		str +='</div>';
		str+='<br>';
		divId.innerHTML=str;
		
	}

	function showDeleteStatus(result)
	{
	if(result.resultCode == 0)
	{
	getAllIpAddressForUser();
	}
	}
</script>
<br><br>
<div id="MainDiv">
<div id="usersouterDiv" class="widget green" style="width:90%;margin-left:auto;margin-right:auto;">
<div id="errormsg"></div>
<div id="userSelectDiv" class="selectDiv" style="width:52%;">
	 PartyAnalyst Users <font class="requiredFont">*</font><select style="margin-left:27px;" cssClass="selectWidth" name="users" id="users"></select>
</div>

<div style="margin-top:10px;margin-bottom:10px;text-align: center;clear:both;">
<input class="btn btn-success" type="button" value="Add" onclick="saveRestrictedUser();" style="position: absolute:top: 50%;">

</div><br>
</div>

<div id="addIpAddressDiv" class="widget green" style="width:90%;margin-left:auto;margin-right:auto;">
<h2>Add IP Address to User</h2><span id="errorDiv"></span>
<div id="userSelectDiv" class="selectDiv">
	Select Users <font class="requiredFont">*</font><select cssClass="selectWidth" name="usersList" id="usersList" onchange="getAllIpAddressForUser();" style="margin-left:60px;"> </select>&nbsp;&nbsp;
	IP Address <font class="requiredFont">*</font>&nbsp;&nbsp;<input type="text" id="Iptext" maxlength="20" /> 
</div>
<div style="margin-top:10px;margin-bottom:10px;text-align: center;clear:both;">

<input class="btn btn-success" type="button" value="Add" onclick="saveUserIpAddress();" style="position: absolute:top: 50%;">
</div><br>
</div>


<div class="widget green" id="deleteIpAddressDiv" style="width:90%;margin-left:auto;margin-right:auto;display:none;">
<div id="errormsgDiv"></div>
<div id="IpAddressDiv">
</div>
</div>
</div>
<script type="text/javascript">

function getAllRestrictedUsers()
{
var jObj=
	{
		
		task:"getAllRestrictedUsers"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAllUserListAjaxAction.action?"+rparam;	

	callAjax(jObj,url);
}
function getAllUsers()
{
var jObj=
	{
		
		task:"getAllUsers"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAllUserListAjaxAction.action?"+rparam;	

	callAjax(jObj,url);
}

function getAllIpAddressForUser()
{


var selectedUser = $("#usersList").val();
var jObj=
	{
		userId : selectedUser,
		
		task:"getAllIpAddressForUser"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getAllIpAddressForUserAction.action?"+rparam;	

	callAjax(jObj,url);
}
function saveRestrictedUser()
{
	$("#errormsg").html('');
	var selectedUser = $("#users").val();
	if(selectedUser == 0)
	{
		$("#errormsg").html("please Select user").css("color","red");
		return;
	}
	var jObj=
	{
		userId : selectedUser,
		task:"saveRestrictedUser"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "saveRestrictedUserAction.action?"+rparam;	

	callAjax(jObj,url);

}
function saveUserIpAddress()
{
	$("#errorDiv").html('');
var selectedUser = $("#usersList").val();
var IpAddress = $.trim($("#Iptext").val());
if(selectedUser == 0)
	{
$("#errorDiv").html('Please Select user').css("color","red");
return;
	}

if(IpAddress == "")
	{
$("#errorDiv").html('Enter Ip Address').css("color","red");
	return ;
	}

	var jObj=
	{
		userId : selectedUser,
		IpAddress:IpAddress,
		task:"saveUserIpAddress"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "saveRestrictedUserAction.action?"+rparam;	

	callAjax(jObj,url);
}


function deleteUserIpAddress()
{

var selected = new Array();
$('.checkedvalue').each(function() {
           if($(this).is(':checked')){
		     
			   selected.push($(this).closest("td").find("#userAccessRegionId").val());
		 }
        });
		
if(selected.length == 0)
	{
	$("#errormsgDiv").html("Please select at least one IpAddress").css("color","red");
return;
	}
if(selected.length > 0)	
	{
	$("#errormsgDiv").html('');
var jObj=
	{
		IpList : selected,
		task:"deleteUserIpAddress"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "saveRestrictedUserAction.action?"+rparam;	
	callAjax(jObj,url);
}
}
//getAllIpAddressForUser();
getAllRestrictedUsers();
getAllUsers();
</script>
</body>
</html>