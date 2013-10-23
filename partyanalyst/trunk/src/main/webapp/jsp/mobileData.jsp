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
</style>
</head>
<body>
 <div id="mainDiv">

  <div class="widget blue">
   <!--<div id="errorMsgDiv"></div>
     <div id="selectDiv">
     Constituency<s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" /> 
  
   <input type="button" class="btn btn-info" value="create Dump File" id="createTempFile"/>

    <a style="margin-left: 11px;" href="${filePath}" class="btn btn-info" download>Download link</a>
	</div>-->
	
	 <!--mobile Data user Details  start-->
	
	<div>
    <form class="form-horizontal">
	<div id="errorMsgDiv" style="font-family:verdana;margin-left:100px;"></div>
	 <div class="control-group">
    <label class="control-label" for="inputconstituency">Constituency</label>
    <div class="controls">
    <s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" /> 
    </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputFirstName">FirstName</label>
    <div class="controls">
    <input type="text" id="FirstNameId" placeholder="FirstName">
    </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputLastName">LastName</label>
    <div class="controls">
    <input type="password" id="LastNameId" placeholder="LastName">
    </div>
    </div>
    <div class="control-group">
	 <label class="control-label" for="inputLastName">Gender</label>
    <div class="controls">
	<label class="radio inline">
	<input type="radio" name="optionsRadios" id="maleRadiobtn" value="Male" checked>
	Male
	</label>
	<label class="radio inline">
	<input type="radio" name="optionsRadios" id="FemaleRadiobtn" value="Female" checked>
	Female
	</label>
	 </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputUserName">UserName</label>
    <div class="controls">
    <input type="text" id="UserNameId" placeholder="UserName">
    </div>
    </div>
    <div class="control-group">
    <label class="control-label" for="inputPassword">Password</label>
    <div class="controls">
    <input type="text" id="PasswordId" placeholder="Password">
    </div>
    </div>
	   <div class="control-group">
    <label class="control-label" for="inputuniqueCode">Unique Code</label>
    <div class="controls">
    <input type="text" id="uniqueCodeId" placeholder="uniqueCode">
    </div>
    </div>
	
		   <div class="control-group">
    <label class="control-label" for="inputappId">App ID</label>
    <div class="controls">
    <input type="text" id="appId" placeholder="appId">
    </div>
    </div>
		   <div class="control-group">
    <label class="control-label" for="inputdeviceId">Device ID</label>
    <div class="controls">
    <input type="text" id="deviceId" placeholder="deviceId">
    </div>
    </div>
	  <div class="control-group">
    <label class="control-label" for="inputmacAddressId">Mac Address</label>
    <div class="controls">
    <input type="text" id="macAddressId" placeholder="macAddressId">
    </div>
    </div>
	
	<div  style="margin-left: 150px;">
	   <input type="button" class="btn btn-info" value="create Dump File" id="createTempFile"/>
	    <img src="./images/icons/search.gif" id="ajaxImg" style="display:none"/>
	   <a id="downloadLink" style="margin-left: 11px;display:none;" href="${filePath}" class="btn btn-info" download>Download link</a>
	<!--<input type="button" value="Submit" class="btn" id="savebtn"></button>-->

	</div>
	
    </form>
	</div><br/>
	 <!--mobile Data user Details end-->
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

var str = '<font color="red">';
 if(constituencyId == 0)
	{
	 str += 'Please Select Constituency<br>';
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
macAddressId:macAddressId
};
dataarr.push(obj);
console.log(dataarr);


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
	if(result == null || result.resultCode == 1)
	{
	  $("#errorMsgDiv").html("Error Occured! Try Again.").css("color","red");
	  return;
	}
	else
	{
	  $("#errorMsgDiv").html("Data Dump Create Successfully.").css("color","green");
	  $("#ajaxImg").css("display","none");
	$("#downloadLink").css("display","block");
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
</script>


</body>
</html>