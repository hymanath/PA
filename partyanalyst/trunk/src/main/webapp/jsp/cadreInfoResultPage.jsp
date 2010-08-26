<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Info Page</title>

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

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
<!-- YUI Dependency files (End) -->

<style type="text/css">
	
	body
	{
		background-color:#FFFFFF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}
	
	#cadreInfo_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:10px;
	}
	
	.cadreProfileInfoTable th
	{
		color:#652D2D;
		padding:4px;
		text-align:left;
	}

	.cadreProfileInfoTable td
	{
		color:#31383C;
		padding:4px;
		text-align:left;
	}
	
	.tableHeadingDiv
	{
		color:#1C3752;
		font-size:15px;
		font-weight:bold;
		margin-left:0;
		padding:6px;
		text-decoration:underline;
	}
	
	#cadreInfo_footer
	{
		margin-right:10px;
		padding:10px;
		text-align:right;
	}

</style>

<script type="text/javascript">
	
var cadreId = '${cadreId}';

function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "getCadreInfoByCadreId")
								buildCadreInfo(jsObj,myResults);

						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildCadreInfo(jsObj,results)
{
	var elmt = document.getElementById("cadreInfoMain");
	if(!elmt)
		return;

	var str = '';
	str += '<div id="cadreInfo_head">';
	str += results.firstName+"'s Profile";
	str += '</div>';
	str += '<div id="cadreInfo_body">';
	str += '	<table width="100%">';
	str += '	<tr>';
	str += '		<td width="70%" valign="top">';
	str += '			<div class="tableHeadingDiv">Basic Info</div>';
	str += '			<table width="100%" class="cadreProfileInfoTable">';
	str += '			<tr>';
	str += '				<th style="width:125px">First Name </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.firstName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Last Name</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.lastName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Gender</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.gender+'</td>';
	str += '			</tr>';	
	str += '			<tr>';
	str += '				<th style="width:125px">Date Of Birth</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.dateOfBirth+'</td>';
	str += '			</tr>';	
	str += '			<tr>';
	str += '				<th style="width:125px">Email</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.email+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Mobile</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.mobile+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Land Line</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.landLineNo+'</td>';
	str += '			</tr>';
	str += '			</table>';
	str += '		</td>';
	str += '		<td width="50%" valign="top">';
	str += '			<img height="160px" width="130px" src="images/icons/indexPage/human.jpg">';
	str += '		</td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan="2" valign="top">';
	str += '			<div class="tableHeadingDiv">Address For Communication </div>';
	str += '			<table width="100%" class="cadreProfileInfoTable">';
	str += '			<tr>';
	str += '				<th style="width:125px">House No </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.houseNo+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Street </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.street+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">State </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.state+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Pincode</th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pinCode+'</td>';
	str += '			</tr>';
	str += '			</table>';
	str += '		</td>';
	str += '	</tr>';
	str += '	</table>';
	str += '</div>';
	str += '<div id="cadreInfo_footer">';
	str += ' <input type="button" value="Send SMS" onclick="senedSMSToCadre(\''+results.mobile+'\')">';
	str += '<span id="errorMsgSpan" style="color:red;font-size:10px;"></span>';
	str += '</div>';

	elmt.innerHTML = str;
}

function senedSMSToCadre(mobileNo)
{
	var elmt = document.getElementById("errorMsgSpan");
	if(!elmt)
		return;
	if(mobileNo == null || mobileNo == "")
	{
		elmt.innerHTML = 'SMS cannot be send due to absence of mobile number';
		return;
	}
	
	var selectedCadresArray = new Array();
	selectedCadresArray.push(mobileNo);
	

	var jsObj=
		{				
				cadreIds:selectedCadresArray,
				task:"sendSMSForCadreIds"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/sendSMSForCadresAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getCadreInfo()
{	
	var jsObj=
		{				
				cadreId:cadreId,
				task:"getCadreInfoByCadreId"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCadreInfoAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

</script>
</head>
<body>
	<div id="cadreInfoMain">
		
	</div>

	<script type="text/javascript">
		getCadreInfo();
	</script>
</body>
</html>