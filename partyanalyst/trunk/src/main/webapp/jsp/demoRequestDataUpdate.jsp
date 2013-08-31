<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
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

	<!-- YUI Dependency files (End) -->
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<title>Party Analyst</title>

<script type="text/javascript">
   var demoRequestId = '${demoRequestId}';
</script>

<style type="text/css">
#mainDiv{ float: none;
    margin: 31px auto 20px;
       width: 600px; margin-bottom: 60px;}
.headingCls{ text-align: center;margin-bottom: 25px;}
.headingSpan{ color: #fff; font-weight: bold; padding: 2px 30px 3px; border-radius: 3px 3px 3px 3px; font-size: 18px; background: none repeat scroll 0 0 #06ABEA;}
.demoRequestUpdateTab{border: 2px solid #D3D3D3; padding: 20px 20px 27px 124px; border-radius: 5px; }
#updateBtn{ margin-left: 134px;
    margin-top: 10px;}

#demoReqActionsTab{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#demoReqActionsTab tr:nth-child(even){background:#EdF5FF;}
#demoReqActionsTab td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#demoReqActionsTab th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}
.actionsHeadingDiv{margin-top: 21px; margin-bottom: 13px; font-weight: bold; font-size: 17px;}
#errorDiv{ margin-bottom: 8px;}
#ajaxImg{margin-left: 9px;
    margin-top: 8px;}
</style>

</head>
<body>

<div id="mainDiv">
<div class="headingCls"><span class="headingSpan">Demo Request Details</span></div>

<div id="demoRequestActionsDiv">
</div>
</div>

<script type="text/javascript">

function getDemoRequestData()
{
	var jsObj=
	{	
	  demoRequestId:demoRequestId,
	  task:"getSelectedDemoRequestData"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedDemoRequestDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	 var myResults;

	 var callback = {			
	               success : function( o ) {
					try {												
						myResults = YAHOO.lang.JSON.parse(o.responseText);					
						if(jsObj.task == "getSelectedDemoRequestData")
						  buildDemoRequestData(myResults);
						else if(jsObj.task == "upDateDemoRequestDetails")
						  showUpdateStatus(myResults);
								
						
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

function buildDemoRequestData(results)
{
  $("#demoRequestActionsDiv").html('');
  if(results == null)
   return;
  var str = '';
  str +='<div class="demoRequestUpdateTab">';
  str +='<div id="errorDiv"></div>';
  str +='<table>';
  str +='<tr>';
  str +='<td>Name:</td>';
  str +='<td><input type="text" value="'+results.name+'" id="candidateName"/></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>Constituency:</td>';
  str +='<td><input type="text" value="'+results.constituency+'" id="Constituency"/></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>Mobile:</td>';
  str +='<td><input type="text" value="'+results.mobileNo+'" id="mobileNo"/></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>Email:</td>';
  str +='<td><input type="text" value="'+results.email+'" id="Email"/></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>Assigned To:</td>';
  if(results.assignedTo != null)
   str +='<td><input type="text" value="'+results.assignedTo+'" id="assigned"/></td>';
  else
   str +='<td><input type="text" id="assignedTo"/></td>';
  str +='</tr>';
  str +='</table>';
  str +='<input id="updateBtn" type="button" value="update" class="btn btn-info" onclick="upDateDemoRequestDetails()"/>';
  str +='<img style="display:none;" src="images/icons/search.gif" id="ajaxImg"/>';
  str +='</div>';

  if(results.demoRequestVOList != null && results.demoRequestVOList.length > 0)
  {
	str +='<div class="actionsHeadingDiv">Actions Perform</div>';
	str +='<div>';
	str +='<table id="demoReqActionsTab">';
	str +='<th>SNO</th>';
	str +='<th>Time</th>';
	str +='<th>User</th>';
	str +='<th>Type</th>';
	str +='<th>Content</th>';
	str +='<th>Response</th>';

	for(var i in results.demoRequestVOList)
	{
	  str +='<tr>';
	  str +='<td>'+results.demoRequestVOList[i].id+'</td>';
	  str +='<td>'+results.demoRequestVOList[i].requestedTime+'</td>';
	  str +='<td>'+results.demoRequestVOList[i].name+'</td>';
	  str +='<td>'+results.demoRequestVOList[i].type+'</td>';
	  str +='<td>'+results.demoRequestVOList[i].content+'</td>';
	  str +='<td>'+results.demoRequestVOList[i].response+'</td>';
	  str +='</tr>';
	}
	str +='</div>';
  }
 $("#demoRequestActionsDiv").html(str);
}


function upDateDemoRequestDetails()
{
  $('#errorDiv').html('');

  var name = $("#candidateName").val();
  var Constituency = $("#Constituency").val();
  var mobileNo = $.trim($("#mobileNo").val());
  var email = $("#Email").val();
  var assignedTo = $("#assigned").val();
  if(mobileNo.length == 0)
  {
    $('#errorDiv').html('Mobile No is Mandatory').css("color","red");
	return;
  }
  $("#ajaxImg").css("display","inline-block");
  var jsObj=
	{	
	  demoRequestId:demoRequestId,
	  name:name,
      Constituency:Constituency,
      mobileNo:mobileNo,
      email:email,
	  assignedTo:assignedTo,
	  task:"upDateDemoRequestDetails"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "upDateDemoRequestDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);

}

function showUpdateStatus(results)
{
  $('#errorDiv').html('');
  $("#ajaxImg").css("display","none");
  if(results.resultCode == 0)
  {
   
   $('#errorDiv').html('Demo Request Updated Successfully.').css("color","green");
   window.opener.parentWindowRefresh();
   return;
  }
  else
  {
   $('#errorDiv').html('Error Occured! Try Again.').css("color","red");
   return;
  }
}
getDemoRequestData();
</script>

</body>
</html>