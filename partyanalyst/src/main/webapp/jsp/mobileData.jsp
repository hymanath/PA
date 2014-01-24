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

<title>Party Analyst</title>

<style type="text/css">
 #mainDiv{margin: 20px auto; float: none; width: 950px;}
 #selectDiv{padding-bottom: 45px; margin-left: 26px;}
 /*#constituencyList{margin-left: 23px;}*/
 #createTempFile{margin-left: 28px;}
 #errorMsgDiv{margin-bottom: 9px; margin-top: 12px; margin-left: 26px; font-size: 12px;}
 .selectWidth{width:218px !important;}
#createMobileApp{font-size:12px;font-family:verdana;width:100%;margin-top:10px;}
</style>
</head>
<body>
<script type="text/javascript">
var populateId ;
</script>
 <div id="mainDiv">

  <div class="widget blue">
  
	
	 <!--mobile Data user Details  start-->
	
	<div>
   <table id="createMobileApp">
	<tr id="errorMsgDiv" style="font-family:verdana;margin-left:100px;"></tr>
	<tr>
    <td>Constituency</td><td></td>
    <td>
    <s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate()"/> 
    </td>
   
    <td>Publication Date</td><td></td>
    <td>
    <select theme="simple" Class="selectWidth" name="publicationList" id="publicationDateList"  /> 
    </td>
   
   
  
   </tr>
   <tr><td></td><td></td></tr>  <tr><td></td><td></td></tr>
    <tr>
	 <td> User</td><td></td>
   <td><select id="usersId" Class="selectWidth" onchange="getMobileAppUserData();">
										<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
											<option value="${allUsers.id}"> ${allUsers.name} </option>	
										</c:forEach>
									</select>
      </td>
    <td>FirstName</td>
    <td></td>
    <td>
    <input type="text" id="FirstNameId" placeholder="FirstName">
    </td>
  </tr>
	
	<tr>
	
    <td>
   LastName</td> <td></td>
     <td><input type="text" id="LastNameId" placeholder="LastName"></td>
	   <td>
	   
	Gender &nbsp; &nbsp; &nbsp;<input type="radio" name="optionsRadios" id="maleRadiobtn" value="Male" style="margin-top:0px;" checked> Male &nbsp;<input type="radio" name="optionsRadios" id="FemaleRadiobtn" value="Female" style="margin-top:0px;" checked>&nbsp;Female</td>
	</tr>
	
    <tr>
    <td>UserName</td>   <td></td>
   <td>
    <input type="text" id="UserNameId" placeholder="UserName">
    </td>
      <td> Password</td> <td></td>
   
    <td>
    <input type="text" id="PasswordId" placeholder="Password">
    </td></tr>
   
	<tr>
   <td>Unique Code</td> <td></td>
    <td>
    <input type="text" id="uniqueCodeId" placeholder="uniqueCode">
    </td>
    
	
    <td>MobileNo</td><td></td>
   <td>
    <input type="text" id="mobileNoId" placeholder="mobileNo">
    </td></tr>
	<tr>
   <td>email</td> <td></td>
    <td>
    <input type="text" id="emailId" placeholder="email">
    </td>
    
    <td>App ID</td> <td></td>
    <td>
    <input type="text" id="appId" placeholder="appId">
    </td></tr>
   
   <tr><td>Device ID</td> <td></td>
    <td>
    <input type="text" id="deviceId" placeholder="deviceId">
    </td>
   
	<td>Mac Address </td><td></td>
    <td>
    <input type="text" id="macAddressId" placeholder="macAddressId">
    </td>
    </tr>
	
	</table>
	<div id="superAdminDiv">
	
	<div id="superAdminInnerDiv"></div>
	Super Admin 
	<select id="superAdminusersId" Class="selectWidth" >
										<c:forEach var="Users" varStatus="stat" items="${superAdminUsersList}">		
											<option value="${Users.id}"> ${Users.name} </option>	
										</c:forEach>
									</select>
<input type="button"  class="btn btn-success" value="create User" id="createUserId"/><br/>
    
	</div>
	<br/>
	<div  style="margin-left: 150px;">
	   <input type="button" class="btn btn-info" value="create Dump File" id="createTempFile"/>
	    <img src="./images/icons/search.gif" id="ajaxImg" style="display:none"/>
	   <a id="downloadLink" style="margin-left: 11px;display:none;" href="${filePath}" class="btn btn-info" download>Download link</a>
	<!--<input type="button" value="Submit" class="btn" id="savebtn"></button>-->

	</div>
	
	</div><br/>
	 <!--mobile Data user Details end-->
  </div>
 <div class="widget blue">
  <form class="form-horizontal" style="margin-top:10px;">
  <div id="errorMsgDiv1" style="font-family:verdana;margin-left:100px;"></div>
  <div class="control-group">
    <label class="control-label" for="inputconstituency">User</label>
    <div class="controls">
    <select theme="simple" cssClass="selectWidth" label="Select Your user" name="userList" id="userList" > </select>
    </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputMobileNo">MobileNo</label>
    <div class="controls">
    <input type="text" id="MobileNoId" placeholder="MobileNo">
    </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputAccessKey">AccessKey</label>
    <div class="controls">
    <input type="text" id="AccessKeyId" placeholder="AccessKey">
    </div>
    </div>
	<div  style="margin-left: 150px;">
	   <input type="button" class="btn btn-info" value="SendSMS" id="SentSMSId" onclick="sendSmsToUser();"/>
	   <img src="./images/icons/search.gif" id="ajaxImg1" style="display:none"/>
	</div><br/>
   </form>
 </div>
</div>

<script type="text/javascript">

$(document).ready(function(){
$("#createTempFile").click(function(){

$("#errorMsgDiv").html("");
var flag = false;
var errorDiv= document.getElementById("errorMsgDiv");
	
var constituencyId = $("#constituencyList").val();  
var dataarr =[];
var firstName = $("#FirstNameId").val();
var lastName = $("#LastNameId").val();
var gender = $("input[name='optionsRadios']:checked").val();
var userName = $("#UserNameId").val();
var password = $("#PasswordId").val();
var uniqueCode = $("#uniqueCodeId").val();
var appId = $("#appId").val();
var deviceId = $("#deviceId").val();
var macAddressId = $("#macAddressId").val();
var userId = $("#usersId").val();
var mobileNo =$("#mobileNoId").val();
var email =$("#emailId").val(); 
var publicationId = $("#publicationDateList").val();
var superAdminusersId =0;

var str = '<font color="red">';
if($("#superAdminusersId").val()  > 0)
superAdminusersId = $("#superAdminusersId").val();
 if(constituencyId == 0)
	{
	 str += 'Please Select Constituency<br>';
	  flag = true;
	 
	}
	 if(publicationId == 0)
	{
	 str += 'Please Select Publication<br>';
	  flag = true;
	 
	}
	if(userId == 0)
	{
	 str += 'Please Select User<br>';
	  flag = true;
	 
	}
	if(firstName == 0 || firstName == null)
	{
	str += 'firstName is Required<br>';
	flag = true;
	}
	 if(lastName == 0 || lastName == null)
	{
		str += 'lastName is Required<br>';
		flag = true;
	}
  if(gender == 0 || gender == null)
	{
		str += 'gender is Required<br>';
		flag = true;
	}
  if(userName == 0 || userName == null)
	{
		str += 'userName is Required<br>';
		flag = true;
	}
 if(password == 0 || password == null)
	{
		str += 'password is Required<br>';
		flag = true;
	}
 if(uniqueCode == 0 || uniqueCode == null)
	{
		str += 'uniqueCode is Required<br>';
		flag = true;
	}
	if(mobileNo == 0 || mobileNo == null)
	{
		str += 'mobileNo is Required<br>';
		flag = true;
	}
	if(email == 0 || email == null)
	{
		str += 'email is Required<br>';
		flag = true;
	}
 if(appId == 0 || appId == null)
	{
		str += 'appId is Required<br>';
		flag = true;
	}
 if(deviceId == 0 || deviceId == null)
	{
		str += 'deviceId is Required<br>';
		flag = true;
	}
 if(macAddressId == 0 || macAddressId == null)
	{
		str += 'macAddressId is Required<br>';
		flag = true;
	}
	if(flag == true)
	{
errorDiv.innerHTML =str;
return;
	}
else
	{
errorDiv.innerHTML = '';
$("#ajaxImg").css("display","block");
var obj = {
firstName : firstName,
lastName:lastName,
gender:gender,
userName:userName,
password:password,
uniqueCode:uniqueCode,
appId:appId,
deviceId:deviceId,
macAddressId:macAddressId,
userId:userId,
email:email,
mobileNo:mobileNo,
superAdmin:superAdminusersId,
publicationId:publicationId
};
dataarr.push(obj);



	var jsObj=
		{
		 constituencyId:constituencyId,
		 userData:dataarr,
		 task:"createDataDump"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "createDataDumpAction.action?"+rparam;						
		callAjax(jsObj,url);	
}
	});
});

function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "createDataDump")
								{
								 showStatus(myResults);	
								
								}
								else if(jsObj.task == "getUsers")
								{
								   clearOptionsListForSelectElmtId('userList');
								  createOptionsForSelectElmtId('userList',myResults);;
							
							}
							else if(jsObj.task == "sendSms")
							{
							 showStatusForSms(myResults);	
							}
							else if(jsObj.task == "saveSuperAdmin")
								{
									 populateId = myResults;
							
									buildStatusForSuperAdmin(myResults);
								}

								else if(jsObj.task == "getSuperAdminUsersList")
								{
									buildSelectList(myResults);
								}
								else if(jsObj.task == "getPublicationDate")
								buildPublicationDateList(myResults);
								else if(jsObj.task == "populateMobileAppUserData")
							     populateMobileAppUserData(myResults);
						  
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

 function showStatus(result)
 {
	$("#errorMsgDiv").html("");
	$("#ajaxImg").css("display","none");
	if(result == null || result.resultCode == 1)
	{
	  $("#errorMsgDiv").html("Error Occured! Try Again.").css("color","red");
	  return;
	}
	else
	{
	  $("#errorMsgDiv").html("Data Dump Create Successfully.").css("color","green");
	  
	 $("#downloadLink").css("display","block").css("display","inline-block");
	  return;
	}
 }
 function showStatusForSms(result)
 {
	$("#errorMsgDiv1").html("");
	 $("#ajaxImg1").css("display","none");
	
	if(result == null || result.resultCode == 1)
	{
	  $("#errorMsgDiv1").html("Error Occured! Try Again.").css("color","red");
	  return;
	}
	else
	{
	  $("#errorMsgDiv1").html("sms send successfully").css("color","green");
	 
	  return;
	}
 }
 
 function showUserDetailsStatus(result)
 {
	var str='';
	var divEle = document.getElementById("errorMsgDiv1");
	if(result == null || result.resultCode == 1)
	{
	  $("#errorMsgDiv1").html("Error Occured! Try Again.").css("color","red");
	  return;
	}
	else
	{
	  $("#errorMsgDiv1").html("Data Saved Successfully.").css("color","green");
	   $("#selectDiv").css("display","block");
	  return;
	 
	}

 }
 
 function getMobileAppUsers()
 {
 var jsObj=
		{
		 
		 task:"getUsers"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMobileAppUsersAction.action?"+rparam;						
		callAjax(jsObj,url);	
 }
 function sendSmsToUser()
 {

$("#errorMsgDiv1").html("");
var flag = false;
var errorDiv= document.getElementById("errorMsgDiv1");
 var accessKey = $("#AccessKeyId").val();
 var mobileNo =$("#MobileNoId").val();
 var mobileAppuser = $("#userList").val();
  var str = '<font color="red">';
/*if(mobileAppuser == 0)
	{
	 str += 'Please Select User<br>';
	  flag = true;
	 
	}*/
	if(mobileNo == 0 || mobileNo == null)
	{
	str += 'mobileNo is Required<br>';
	flag = true;
	}
	if(accessKey == 0 || accessKey == null)
	{
	str += 'accessKey is Required<br>';
	flag = true;
	}
 if(flag == true)
	{
errorDiv.innerHTML =str;
return;
	}
else
	{
$("#ajaxImg1").css("display","inline-block");
errorDiv.innerHTML = '';
 var jsObj=
		{
		 mobileNo:mobileNo,
		 mobileAppuserId:mobileAppuser,
		 accessKey:accessKey,
		 task:"sendSms"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendSmsToMobileAppUsersAction.action?"+rparam;						
		callAjax(jsObj,url);	
		}
 }
 $("#createUserId").live("click",function()
 {
	$("#superAdminInnerDiv").show();
 var str='';
  str+='<div class="widget">';
   str+='<form class="form-horizontal" style="margin-top:10px;">';
  str+=' <div id="errorMsgDiv2" style="font-family:verdana;margin-left:100px;"></div>';
   str+='<div class="control-group">';
    str+=' <label class="control-label" for="inputuname">User Name</label>';
     str+='<div class="controls">';
     str+='<input type="text" placeholder="UserName" id="UserNameId1">';
     str+='</div>';
     str+='</div>';
     str+='<div class="control-group">';
     str+='<label class="control-label" for="inputPassword">Password</label>';
     str+='<div class="controls">';
    str+=' <input id="PasswordId1" type="text" placeholder="Password">';
    str+=' </div>';
    str+=' </div>';
     str+='<div class="control-group">';
    str+=' <label class="control-label" for="inputniqueCode">unique Code</label>';
    str+=' <div class="controls">';
     str+='<input type="text" placeholder="uniqueCode" id="uniqueCodeId1">';
     str+='</div>';
    str+=' </div>';
	 str+='<div  style="margin-left: 150px;">';
	   str+=' <input type="button" class="btn btn-info" value="create User" id="saveSuperAdminId"/>';
	   str+=' <img src="./images/icons/search.gif" id="ajaxImg2" style="display:none"/>';
	 str+='</div><br/>';
    str+='</form>';
  str+='</div>';
   $("#superAdminInnerDiv").html(str);

 });

$("#saveSuperAdminId").live("click",function()
{
	var str = '<font color="red">';
	var userName=$("#UserNameId1").val() ;
	var password =$("#PasswordId1").val() ;
	var uniqueCode=$("#uniqueCodeId1").val() ;
	
	var flag = false;
	 if(userName == 0 || userName == null)
	{
		str += 'userName is Required<br>';
		flag = true;
	}
 if(password == 0 || password == null)
	{
		str += 'password is Required<br>';
		flag = true;
	}
 if(uniqueCode == 0 || uniqueCode == null)
	{
		str += 'uniqueCode is Required<br>';
		flag = true;
	}
	if(flag == true)
	{
	$("#errorMsgDiv2").html(str);
	}
	else
	{
		$('#ajaxImg2').show();
	$("#errorMsgDiv2").html('');
 	var jsObj=
		{
		 userName:userName,
		 password:password,
	     uniqueCode:uniqueCode,
		 task:"saveSuperAdmin"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveSuperAdminAction.action?"+rparam;						
		callAjax(jsObj,url);	
	}

});
function getSuperAdminUserList()
{
		var jsObj=
		{
		
		 task:"getSuperAdminUsersList"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSuperAdminUsersListAction.action?"+rparam;						
		callAjax(jsObj,url);	

}
function buildSelectList(results)
{
	$('#superAdminusersId').children().remove();
	var selectedElmt=document.getElementById("superAdminusersId");
	 for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
		
		$('#superAdminusersId').val(populateId);
}
function buildStatusForSuperAdmin(result)
{
	$("#errorMsgDiv2").html('');

	if(result > 0)
	{

	$('#ajaxImg2').hide();
	getSuperAdminUserList();
	
	$("#errorMsgDiv2").html('created user successfully').css("color","green");
    $("#UserNameId1").val('');
    $("#PasswordId1").val('');
	$("#uniqueCodeId1").val('');
   
	}
	else
	{
$("#errorMsgDiv2").html('user already exist').css("color","red");
	}
}

function getPublicationDate()
	{
	var constituencyID =$("#constituencyList").val();
	

	var jsObj=
	{
		selected:constituencyID,
		task:"getPublicationDate"
	};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);	
	
}

var publicationDatesList;
	function buildPublicationDateList(results)
	{

	$('#publicationDateList').children().remove();
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	
	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	var largest = Math.max.apply(Math, publicationIdsArray);

	$('#publicationDateList').val(largest);
	$('#publicationDateList').trigger("change");

}
function getMobileAppUserData()
{
	var userId = $("#usersId").val();
		var jsObj=
		{
			userId:userId,
		 task:"populateMobileAppUserData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMobileAppUserDataAction.action?"+rparam;						
		callAjax(jsObj,url);	

}
function populateMobileAppUserData(result)
{
	if(result.length == 0 || result == null)
	{
	 document.getElementById('FirstNameId').value ='';
	 document.getElementById('LastNameId').value = '';
	 document.getElementById('UserNameId').value ='';
	 document.getElementById('PasswordId').value = '';
	 document.getElementById('uniqueCodeId').value = '';
	 document.getElementById('mobileNoId').value = '';
	 document.getElementById('emailId').value = '';
	 document.getElementById('appId').value = '';
	 document.getElementById('deviceId').value = '';
	 document.getElementById('macAddressId').value = '';
	
	}
	if(result.length > 0)
	{
	 document.getElementById('FirstNameId').value = result[0].firstName;
	 document.getElementById('LastNameId').value = result[0].lastName;
	 document.getElementById('UserNameId').value = result[0].userName;
	 document.getElementById('PasswordId').value = result[0].password;
	 document.getElementById('uniqueCodeId').value = result[0].uniqueCode;
	 document.getElementById('mobileNoId').value = result[0].mobile;
	 document.getElementById('emailId').value = result[0].email;
	 document.getElementById('appId').value = result[0].appId;
	 document.getElementById('deviceId').value = result[0].deviceId;
	 document.getElementById('macAddressId').value = result[0].address;
	if(result[0].gender == "Male")
		$("#maleRadiobtn").attr("checked","checked");
		else
$("#FemaleRadiobtn").attr("checked","checked");
	 document.getElementById('FirstNameId').value = result[0].firstName;
	 $("#superAdminusersId option[value="+result[0].superAdminId+"]").attr('selected','selected');
	}
}
getMobileAppUsers();


</script>


</body>
</html>