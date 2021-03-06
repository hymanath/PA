<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Voters </title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<!-- Then get JQuery UI -->
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/jQuery/image-crop/css/jquery.Jcrop.css" type="text/css" />

<link rel="stylesheet" href="https://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>	
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

<style type="text/css">
  #mainDiv{margin-left: auto;
    margin-right: auto;
    width: 741px;
  }
  fieldset {
    border:2px solid #d3d3d3;
    margin-bottom: 10px;
	width:500px;
	margin-left:auto;
	margin-right:auto;
}
legend {
    background-color: #567AAF;
    color: #FFFFFF;
    font-size: 12px;
    padding: 5px;
}
.widget {
    background: none repeat scroll 0 0 #FAFAFA;
    border-top: 5px solid #000000;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 10px 0 20px;
    padding: 0 20px;
    position: relative;
}
#pingDetailsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}


#pingDetailsDiv table tr:nth-child(even){background:#EdF5FF;}

#pingDetailsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#pingDetailsDiv table th{
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
	#pingTable_info,#pingTable_paginate{
	font-family: verdana;
    font-size: 12px;
    margin-top: 12px;
	
}
.dataTables_length {
   
    padding-bottom: 15px;
  
}
  input[type="text"]{  background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	border-radius: 4px;
	 padding: 5px;}
</style>
</head>
<body>
 <div id="mainDiv" class="contenttable widget">
 <div id="userDetailsDiv" style="font-family: verdana;font-size: 12px; font-weight: bold;"></div>
  <div id="pingDetailsDiv" class="yui-skin-sam yui-dt-sortable" style="margin-top:10px;margin-bottom:20px;"></div><br/><br/>
 </div>
 <script type="text/javascript">
 var userId = ${userId};
function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getUserDetailInfo")
								{
								 buildUserData(myResults);	
								
								}
								else if(jsObj.task == "getPingDetails")
								{
									buildPingDetails(myResults);
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

function getMobileAppUsersInfo()
 {

 var jsObj=
		{
		 mobileAppuserId:userId,
		 task:"getUserDetailInfo"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMobileAppUsersDataAction.action?"+rparam;						
		callAjax(jsObj,url);	
 }
 function getPingIngInfo()
 {

 var jsObj=
		{
		 mobileAppuserId:userId,
		 task:"getPingDetails"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPingDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);	
 }

 function buildUserData(results)
 {
				var str = '';
				str+='<h2 style="text-align:center;">MOBILE APP USER DETAILS</h2>';
				str+='<fieldset>';
			 	str+='<legend>User Profile:</legend>';
				str+='<table>';
				str+='<tr><td>Name</td><td>:</td><td>'+results[0].name+'</td></tr>';
				str+='<tr><td>Gender</td><td>:</td><td>'+results[0].gender+'</td></tr>';
				str+='<tr><td>Mobile No</td><td>:</td><td>'+results[0].mobile+'</td></tr>';
		    	str+='<tr><td>Email</td><td>:</td><td> '+results[0].email+'</td></tr>';
				str+='</table>';
         	    str+=' </fieldset>';
			    str+='<fieldset>';
			 	str+='<legend>User Access Details:</legend>';
				str+='<table>';
				str+='<tr><td>User Name</td><td>:</td><td>'+results[0].userName+'</td></tr>';
				str+='<tr><td>Pass Word</td><td>:</td><td>'+results[0].password+'</td></tr>';
		    	str+='<tr><td>Unique Code</td><td>:</td><td> '+results[0].uniqueCode+'</td></tr>';
				str+='<tr><td>Access</td><td>:</td><td>'+results[0].accessValue+'</td></tr>';
		    	str+='<tr><td>Last Authorised Time</td><td>:</td><td> '+results[0].dateOfBirth+'</td></tr>';
				str+='<tr><td>App Id</td><td>:</td><td>'+results[0].appId+'</td></tr>';
				str+='<tr><td>Device</td><td>:</td><td>'+results[0].deviceId+'</td></tr>';
		    	str+='<tr><td>Mac Address</td><td>:</td><td> '+results[0].address+'</td></tr>';
				str+='</table>';
         	    str+=' </fieldset>';
				if(results[0].registeredUsersList.length > 0)
				 {
				str+='<fieldset>';
			 	str+='<legend>Super Admin Details:</legend>';
				str+='<table>';
				str+='<tr><td>User Name</td><td>:</td><td>'+results[0].registeredUsersList[0].userName+'</td></tr>';
				str+='<tr><td>Pass Word</td><td>:</td><td>'+results[0].registeredUsersList[0].password+'</td></tr>';
		    	str+='<tr><td>Unique Code</td><td>:</td><td> '+results[0].registeredUsersList[0].uniqueCode+'</td></tr>';
				
				str+='</table>';
				str+=' </fieldset>';
			 }
		 $("#userDetailsDiv").html(str);
 }
function buildPingDetails(results)
{
	
	if(results.length > 0)
	{
	var str='';
	str+='<h2 style="text-align:center;">MOBILE APP PING DETAILS</h2>';
	str+='<table id="pingTable" cellpadding="0" cellspacing="0" border="0" width="100%">';
	str+='<thead>';
	 str+='   <tr>';
	str+='<th>Ping Type</th>';
	str+='<th>Ping Time</th>';
	 str+='   </tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in results)
	{
	str+='<tr>';
	str+='<td>'+results[i].type+'</td>';
	str+='<td>'+results[i].value+'</td>';
	str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	 $("#pingDetailsDiv").html(str);
	
	  	$('#pingTable').dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null
     
	  
    ] 
		});
	$("#pingTable tr").removeClass("even"); 
	$("#pingTable tr").removeClass("odd"); 
	}
}
 </script>
 <script type="text/javascript">
  getMobileAppUsersInfo();
  getPingIngInfo();
 </script>
 </body>
 </html>