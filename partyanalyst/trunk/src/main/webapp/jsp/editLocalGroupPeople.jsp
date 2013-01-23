<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Local User Group Members  </title>
<!-- Then get JQuery UI -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
    
<!-- YUI Dependency files (End) -->
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />

<style>

#mainDiv {
    border: 1px solid #A1A1A1;
    border-radius: 25px 25px 25px 25px;
    height: 398px;
    padding: 10px 40px;
    width: 337px;
	margin-left: 100px;
}
input, textarea, .uneditable-input {
    width: 200px;
	
}
#localGroupMember_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:20px;
		margin-bottom:20px;
	}
</style>
<script type="text/javascript">

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "editLocalUserGroup")
									{
										buildLocalGroupDetails(myResults);
										
									}
								else if(jsObj.task == "getDesignationList")
									{
										buildDesignationList(myResults);
									}
								else if(jsObj.task == "updateGroupDate")
									{
									alert("UpdateSuccessfully");
									//window.location.reload();
									window.opener.location.reload();
									}
									
								}
								catch (e) {}  
						},
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
 	}
function editUserGroupDetaild()
{
	var userGroupId = '${userGroupId}';
	var jsObj= 
			{		
				userGroupId :userGroupId,		  			
				task: "editLocalUserGroup"		
			};
			
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editGropLocalAction.action?"+rparam;
	callAjax(jsObj,url);
}
function buildLocalGroupDetails(myResults)
{
	var name = myResults.name;
	var mobileNo = myResults.mobileNumber;
	var address = myResults.address;
	var location = myResults.location;
	var designation = myResults.designation;
	var emailId = myResults.emailId;
	var phoneNo = myResults.phoneNumber;
	var str = '';
	str+= '</br>';
	str+= '</br>';
	if(name != null)
	{
	
	str+= '<b><span>Name<font color="red">*</font>:</span></b><input type="text" style="margin-left: 49px;" id="nameId"';
	str+= 'value='+myResults.name+'></input></br>';
	}
	else
	{
	str+= '<b><span>Name<font color="red">*</font></b>: </span><input type="text" style="margin-left: 49px;" id="nameId"></input></br>';
	}
	if(mobileNo != null){
	str+= '';
	str+= '<b><span>Mobile No<font color="red">*</font>:</span></b><input type="text" style="margin-left: 18px;" id="mobileNoId" ';
	str+= 'value='+myResults.mobileNumber+'></input></br>';
	}
	else
	{
	str+= '<b><span>Mobile No<font color="red">*</font>:</span></b><input type="text" id="mobileNoId" style="margin-left: 18px;" ></input></br>';
	}
	if(address != null)
	{
	str+= '<b><span>Address : </span></b><input type="text" style="margin-left: 28px;" id="addressId"';
	str+= 'value='+myResults.address+'></input></br>';
	}
	else
	{
	str+= '<b><span>Address : </span></b><input type="text" id="addressId" style="margin-left: 28px;"></input></br>';
	}
	if(location != null)
	{
	str+= '<b><span>Location :</span></b><input type="text" style="margin-left: 31px;" id="locationId"';
	str+= 'value='+myResults.location+'></input></br>';
	}
	else
	{
	str+= '<b><span>Location : </span></b>input type="text" id="locationId" style="margin-left: 31px;" ></input></br>';
	}
	/*if(designation != null)
	{
	str+= 'Designation : <input type="text" id="designationId"';
	str+= 'value='+myResults.designation+'></input></br>';
	}
	else
	str+= 'Designation : <input type="text" id="designationId"></input></br>';*/
	if(emailId != null)
	{
	str+= '<b><span>Email Id : </span></b><input type="text" style="margin-left: 32px;" id="emailId"';
	str+= 'value='+myResults.emailId+'></input></br>';
	}
	else
	{
	str+= '<b><span>Email Id  : </span></b><input type="text" id="emailId" style="margin-left: 32px;"></input></br>';
	}
	if(phoneNo != null)
	{
	str+= '<b><span style="width:10px">PhoneNo : </span></b><input type="text" id="phoneNoId" style="margin-left: 26px;"';
	str+= 'value='+myResults.phoneNumber+'></input></br>';
	}
	else
	{
	str+= '<b><span>PhoneNo  :</span></b><input type="text" id="phoneNoId" style="margin-left: 26px;"></input></br>';
	}
	
	str+= '<b><span style="width:10px">Select Designation <font color="red"> * </font>:</span></b><select id="selectId" style="margin-left: 14px;" value ='+myResults.designationId+'';
	
	str+= 'name="selctDesignation" ></select></br>';
	str+= '<input type="button" value="Update" onClick="updateGroupDetails();" style="margin-left: 121px;;margin-top: 9px;"></input></br>';
	
	$("#mainDiv").html(str);
	
}
function getDesignationValues()
{
	
	var jsObj= 
			{	  			
				task: "getDesignationList"		
			};
			
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editGropLocalAction.action?"+rparam;
	callAjax(jsObj,url);
}
function buildDesignationList(myResults)
{
	for(i in myResults)
	{
	var id = myResults[i].id;
	var designation = myResults[i].name;
	$('#selectId').append("<option value="+id+">"+designation+"</option>");
	}
	
}
function updateGroupDetails()
{
	var userGroupId = '${userGroupId}';
	var name = $('#nameId').val();
	var mobileNo = $('#mobileNoId').val();
	var address = $('#addressId').val();
	var location = $('#locationId').val();
	//var designation = $('#designationId').val();
	var emailId = $('#emailId').val();
	var phoneNo = $('#phoneNoId').val();
	var designationId = $('#selectId').val();
	if(designationId == 0)
	{
		$('#errorDiv').html('Please select Designation');
	}
	else if(name == '')
	{
		$('#errorDiv').html('Pleae Enter Name');
	}
	else if(mobileNo == '')
	{
	
		$('#errorDiv').html('Please Enter MobileNo');
	}
	else
	{
	var jsObj= 
			{	
				userGroupId:userGroupId,
				name:name,
				mobileNo:mobileNo,
				address:address,
				phoneNo:phoneNo,
				location:location,
				//designation:designation,
				emailId:emailId,
				designationId:designationId,
				task: "updateGroupDate"		
			};
			
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editGropLocalAction.action?"+rparam;
	callAjax(jsObj,url);
	$('#successDiv').show();
	$('#errorDiv').hide();
	window.opener.location.reload();
	}
	$('#mandateryDiv').hide();
}
/*function prevWindowRefrsh()
{
	alert("prevWindowRefrsh");
}*/
editUserGroupDetaild();
getDesignationValues();

</script>
</head>
<body style="position: relative;">
</br></br>
<!--<div id="successDiv" style="float:left;color:red;display:none;margin-left: 115px;margin-top: 6px;">updated successfully</div>-->
<div id="localGroupMember_head">Update Local Group Member</div>
<div id="errorDiv" style="float:left;color:red;margin-top: 9px;margin-left: 117px;"></div>
<div id="mandateryDiv" style="float:left;color:red;margin-top: 10px;margin-left:0px;">Fields marked with * are complusory</div>
<div id="mainDiv" style="float:center;">
<div id="selectDiv"></div>
<div id="buttonDiv"></div>

</div>
</body>
</html>