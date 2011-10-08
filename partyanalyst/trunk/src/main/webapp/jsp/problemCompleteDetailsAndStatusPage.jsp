

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problem Details</title>

<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

  
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/fonts/fonts-min.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">


<!-- JQuery files (Start) 
<link type="text/css" href="styles/bottom.css" rel="stylesheet" />-->
<script type="text/javascript" src=" https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.js"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js">
</script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js">
</script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.accordion.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>

<script>
		!window.jQuery && document.write('<script src="js/fancybox/jQuery/jquery-1.4.3.min.js"><\/script>');
	</script>
	
	<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 	<link rel="stylesheet" href="styles/style.css" />

<!--<script type="text/javascript" src="js/picasojs/jquery.pikachoose.full.js"></script>
<script type="text/javascript" src="js/picasojs/jquery.pikachoose.js"></script>-->
<!-- JQuery files (End) -->

<style type="text/css">

.problemReportHeader {
		background-image: url("images/icons/cadreReport/bg_center.png");
		background-repeat: repeat-x;
		color: #FFFFFF;
		font-size: 14px;
		font-weight: bold;
		height: 24px;
		padding-top: 1px;
		text-align: center;
		width: 225px;
	}

.bodyStyle {
	font-family:verdana;
	font-size:12px;
}

fieldset {
border:4px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
}

legend {
background-color:#567AAF;
color:#FFFFFF;
font-size:12px;
font-weight: bold;
padding:5px;
}

.problemDetailsTable td,th
{
	text-align:left;
}

.requiredFont
{
	color:red;
	margin-left:5px;
}

.selectWidth {
	width:250px;
}

.button {
background-attachment:scroll;
background-color:#335291;
background-image:none;
background-position:0 0;
background-repeat:repeat;
color:#FFFFFF;
float:left;
}

#thId {
color:#0000AA;
font-family:verdana;
font-weight:bold;
text-align:left;
}

.problemStagesTable
{
	text-align : center;
}

/*New Styles*/

#problemContentData_main
{
	padding:10px;
}

#problemContentData_details_main
{
	margin-top:49px;
}

#problemContentData_details_head
{
	background-color:#EEF4F6;
	color:#445762;
	font-size:13px;
	font-weight:bold;
	padding:5px;
}
#problemContentData_details_body
{
	position:absolute;
	background-color:#EBEDEE;
	width:92%;
	margin:0px 10px 0px 10px;
}

.problemDetailsTable th
{
	padding:10px;
	border-bottom:1px solid #ADADAD;
}

.problemDetailsTable td
{
	padding:10px;
	border-bottom:1px solid #ADADAD;
}

.statusData_table td
{
	padding:5px;
}

.statusData_table_label
{
	border-right:1px solid #E0E3E9;
}

.statusData_table_data
{
	border-bottom:1px solid #E0E3E9;
}

.statusData_table_links
{
	font-size:11px;
	color:#332824;
}


#problemContentData_status_main
{
	margin:20px 0 10px;
	/*padding-top:200px;*/
}

.changeAnc
{
	color:#466376;
	font-size:11px;
	text-decoration:none;
	margin:0px 20px 0px 5px;
}

.changeAnc:hover
{
	text-decoration:underline;
}

.problemStatusDataDiv_main
{
	margin:10px 0px 10px 0px;
	padding:5px;
	background-color:#EEF4F6;
}

.statusData_table_inner th
{
	color:#0156B7;
	font-size:11px;
}

.activitiesContentDiv_main
{
	margin: 10px 0;
	background-color: #EEF4F6;
}

.activitiesContentDiv_head
{
	padding: 5px;
	font-weight: bold;
	color: #0156B7;
	font-size:11px;
	border-bottom: 1px solid #E0E3E9;
}

.activitiesContentDiv_body
{
	padding: 5px;
}

.activitiesContent_table th
{
	padding:5px;
	color: #4A4731;
}

.activitiesContent_table td
{
	padding:5px;
}

.labelSpan
{
	text-decoration:underline;
}

#closeWindowDiv {
    float: right;
    margin-top: 11px;
    text-align: right;
}
.ui-widget-header {
    background: none repeat scroll 0 0 #4297D7;
    border: 1px solid #4297D7;
    color: #222222;
    font-weight: bold;
}
#tabsLiStyle {
    color: olive;
    font-weight: bold;
}
.ui-widget-content {
    background: url("images/ui-bg_flat_75_ffffff_40x100.png") repeat-x scroll 50% 50% transparent;
    border: 1px solid #4297D7;
    color: #222222;
}
.ui-dialog .ui-dialog-content {
    background: none repeat scroll 0 0 #FFFFFF;
    border: 0 none;
    overflow: auto;
    padding: 1.5em 1em;
    position: relative;
}
</style>

<script type="text/javascript">
 var status = '${problemCompleteDetailsVO.problemBasicDetails.problemStatus}';
 
 var pHistoryId = <%=request.getParameter("pHistoryId")%>;
 var problemStatus = '${problemCompleteDetailsVO.problemBasicDetails.problemStatus}';
 var cadreProblemDetails = null;
function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}
var cadreClickType;
function getCadreDetails(clickType)
{	
	cadreClickType = clickType; 
	if(clickType == "Delete")
	{
		addCadreToProblem(0);
		return;
	}
	var urlStr = "cadreSearchAction.action?windowTask=Search&addProblem=true";
	var cadreSearchForProblem = window.open(urlStr,"cadreSearchAndSMSPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");	
	cadreSearchForProblem.focus();
}

function setSelectedCadre(cadreId,cadreName)
{
	var cadreInputIdEle = document.getElementById("cadreInputId");
	cadreInputIdEle.value = cadreId;

	var cadreDetailsDivEle = document.getElementById("selectedCadreDiv");
	var cadreVar ='';
	cadreVar +='<table align="center">';
	cadreVar +='<tr><th>Selected Cadre is :</th>';
	cadreVar +='<th>';
	cadreVar += cadreName;
	cadreVar +='</th>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="show details" class="button" onclick="showCadreDetails(\''+cadreId+'\')"/></td>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="Proceed" class="button" onclick="addCadreToProblemAndSendingSMS(\''+cadreId+'\')"/></td>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="Cancel" class="button" onclick="clearCadreDiv()"/></td></tr>';
	cadreVar +='</table>';
	cadreDetailsDivEle.innerHTML = cadreVar;
	cadreDetailsDivEle.style.display = 'block';
}

function addCadreToProblemAndSendingSMS(cadreId)
{
	if(cadreClickType == 'Assign' || cadreClickType == 'Change')
	getCadreProblemDetailsForSms(cadreId,pHistoryId);
	else if(cadreClickType == 'Delete')
	addCadreToProblem(cadreId);
}

function addCadreToProblem(cadreId)
{
	var jsObj = {
				pHistoryId:pHistoryId,
				cadreId:cadreId,
				cadreClickType:cadreClickType,
				task:"addCadreToProblem"
			};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "addCadreToProblemAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}


function openCadreSmsPopup(cadreId,pHistoryId)
{
	$("#departmentPanel_main" ).dialog({
		title:"<font color='#ffffff'>Sending SMS To Cadre About Problem</font>",
		autoOpen: true,
		show: "blind",
		width: 500,
		minHeight:250,
		modal: true,
		hide: "explode"
	});
	document.getElementById("departmentPanel_main" ).style.background = "#ffffff";
	
	var elmt = document.getElementById("departmentPanel_content");
	if(!elmt)
	return;
	
	var str = '';
	str += '<DIV id="problemAssigningDiv">';
	str += '<DIV id="cadreSMSErrDiv"></DIV>'
	str += '<TABLE>';
	str += '	<tr>';
	str += '		<th width="110px" style="color:royalBlue">Mobile No</th>';
	str += '		<td><input type="text" id="mobileNoTextId" maxlength="12"></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<th width="110px" style="color:royalBlue">Message</th>';
	str += '        <td><textarea rows="6" cols="45" id="messageTextId" theme="simple" name="message" maxlength="800"></textarea></td>';
	str += '	</tr>';
	str += '</TABLE>';
	
	str += '<TABLE>';
	str += '	<tr>';
	str += '	<th width="110px">';
	str += '	<td><input type="button" class="button" style="float:none;font-weight:bold;height:30px;" value="Proceed" onclick="sendSMSToCadre('+cadreId+')"/></td><td><span id="cadreSmsAjaxImgDiv" style="display:none;padding-left:15px;"><img src="images/icons/loading.gif" align="top" height="30px" width="30px"></img></span>';
	str += '	</tr>';
	str += '</TABLE>';
	str += '</DIV>'; 
	elmt.innerHTML = str;
	
	if(cadreProblemDetails != null)
	{
		document.getElementById('mobileNoTextId').value= cadreProblemDetails[0].id;
		document.getElementById('messageTextId').value= cadreProblemDetails[0].name;
	}
		
}


function getCadreProblemDetailsForSms(cadreId,pHistoryId)
{
	var jsObj=
		{
			cadreId		 : cadreId,
			pHistoryId	 : pHistoryId,
			task		 : "getCadreProblemDetailsForSms"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemDepartmentsAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function sendSMSToCadre(cadreId)
{
	var mobileNo = document.getElementById("mobileNoTextId").value.trim();
	var messageStr = document.getElementById("messageTextId").value.trim();
	var errorDivEle = document.getElementById("cadreSMSErrDiv");
	var flag = false;
	var str = '<font color="red"><b>';
	if(isNaN(mobileNo)|| mobileNo.indexOf(" ")!=-1)
	{
		str += "Mobile Number Should Contain only Numbers.<BR>";
		flag = true;
	}
	else if((mobileNo.length > 0 && mobileNo.length < 10) || mobileNo.length > 12)
	{
		str += "Please provide correct Mobile number.<BR>";
		flag = true;
	}
	if(messageStr.length == 0)	
	{
		str += "Please Enter A Message.<BR>";
		flag = true;
	}
	else if(messageStr.length >= 800)	
	{
		str += 'Message Should not exceed 800 Characters.<BR>';
		flag = true;
	}
	str += '</b></font>';
	if(flag)
	{
		errorDivEle.innerHTML = str;
		return;
	}

	addCadreToProblem(cadreId);
	showAjaxImage('cadreSmsAjaxImgDiv');
	var jsObj=
		{
			MobileNo	 : mobileNo,
			message		 : messageStr,
			task		 : "sendSMS"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProblemStatusAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function clearCadreDiv()
{
	var cadreDetailsDivEle = document.getElementById("selectedCadreDiv");
	cadreDetailsDivEle.innerHTML = '';
}

function showAjaxImage(imgDiv)
{
	document.getElementById(imgDiv).style.display = 'block';
}

function hideAjaxImage(imgDiv)
{
	document.getElementById(imgDiv).style.display = 'none';
}

function showSMSResult(result)
{
	hideAjaxImage('cadreSmsAjaxImgDiv');
	var errorDivEle = document.getElementById("cadreSMSErrDiv");
	str = '';
	if(result.resultCode == 0)
	{
		str += '<font color="green"><b>SMS Sent Successfully,Window is Closing...</b></font>';
	}
	else if(result.resultCode == 1)
	{
		str += '<font color="red"><b>Error Occured,SMS Not Sent,Window is Closing...</b></font>';
	}
	errorDivEle.innerHTML = str;
	setTimeout("closewindow()",2000);
}

function closewindow()
{
	$("#departmentPanel_main").dialog("destroy");
}

function showCadreDetails(cadreId)
{
	var showCadreDetailsBrowser = window.open("<s:url action="getCadreInfoAction.action"/>?windowTask=cadreInfoPopup&cadreId="+cadreId,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	showCadreDetailsBrowser.focus();
}

function doExecuteOnLoad()
{
	var probHisEle = document.getElementById("probHistoryId");
	probHisEle.value = pHistoryId;
	setStatusToChange(problemStatus,null);
}

function setStatusToChange(probStatus,pValue)
{
	if(probStatus == 'NEW' || probStatus == 'PENDING')
	{
		document.getElementById("statusToChangeId").value='PROGRESS';
	}
	else if(probStatus == 'PROGRESS')
	{
		if(pValue == 'PENDING')
			document.getElementById("statusToChangeId").value='PROGRESS';
	}

}

function showProbStatusDetails()
{ 
	var AssignTabEle = document.getElementById("probAssigningTabId");
	AssignTabEle.style.display = "block";
}

function getProblemDepartments(selected,task)
{
	var deptCatId = '';
	if(task == 'getDepartments')
	{
		var deptCatEle = document.getElementById("resolvingDeptScopeId");
         deptCatId = deptCatEle.options[deptCatEle.selectedIndex].value;
	}
		var jsObj=
		{
			scopeId		 : selected,
			task		 : task,
			deptCategoryId :  deptCatId
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemDepartmentsAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task == "getProblemResolvingDeptScopes")
							{ 
							  if(myResults == null){
							      alert("Your Session Expired Please LogIn");	
                                   return;								  
							     }
								clearOptionsListForSelectElmtId('resolvingDeptScopeId');
								fillOptionsForSelectedElmt('resolvingDeptScopeId', myResults);
							}
							else if(jsObj.task == "getProblemType")
							{
								clearOptionsListForSelectElmtId('problemTypeId');
								fillOptionsForSelectedElmt('problemTypeId', myResults);
							}
							else if(jsObj.task == "getDepartmentCategories")
							{
								clearOptionsListForSelectElmtId('deptId');
								fillOptionsForSelectedElmt('deptId', myResults);
							}
							else if(jsObj.task == "getDepartments")
							{
								clearOptionsListForSelectElmtId('deptId');
								fillOptionsForSelectedElmt('deptId', myResults);
							}
							else if(jsObj.task == "getProblemPresentStatus")
							{
								buildProblemPresentStatus(jsObj,myResults);
							}
							else if(jsObj.task == "getProblemRecentActivities")
							{
								buildProblemRecentActivities(jsObj,myResults);
							}
							else if(jsObj.task == "changeProbClassification")
							{ 
							    if(myResults.resultCode == 0)
								  {
								      alert("Your Session Expired Please LogIn");
									  return;
								   }
								getProblemPresentStatus(pHistoryId);
								getProblemActivities(pHistoryId);	
							}
							else if(jsObj.task == "changeProbClassification" || jsObj.task == "addCadreToProblem" || jsObj.task == "changeDepartmentForProblem" || jsObj.task == "PostCommentsToProblem")
							{
                                getProblemPresentStatus(pHistoryId);
								getProblemActivities(pHistoryId);
								
								if(jsObj.task == "PostCommentsToProblem"){
							     var alertMsgElmt;
								  if(myResults.resultCode == 0)
								  {
								      alert("Your Session Expired Please LogIn To Post");
									  return;
								   }
								 if(myResults.exceptionEncountered == null){
									
								alertMsgElmt = document.getElementById("alertMsg");
								alertMsgElmt.innerHTML=
								'<font color="green"><b>		Comments Posted Successfully</b></font>';
									return;
								   }
								}
								var deptElmt = document.getElementById("departmentAssignedStatusDiv");
                                if(deptElmt != null)
								{
									if(myResults.exceptionEncountered == null)
									{
										var str = '';
										str += 'Successfully Assigned To Department ..';
										deptElmt.innerHTML = str;
									}
								}
						
							}
							else if(jsObj.task == "changeProblemStatus")
							{
							  if(myResults.resultCode == 0)
								 {    
								      alert("Your Session Expired Please LogIn");
									  return;
								   }
                                if(myResults.resultState == null)
								{
                                   var statusDivElmt = document.getElementById("statusErrorDiv");
								   if(statusDivElmt == null)
									   return;

								   var str = '';
								   str+=' ' + myResults.exceptionMsg;

                                   statusDivElmt.innerHTML = str;
								}
								else
								{
                                  pHistoryId = myResults.resultState;
									
								       window.location="problemDetailsAndStatusAction.action?pHistoryId="+myResults.resultState; 

								 /* getProblemPresentStatus(pHistoryId);
								  getProblemActivities(pHistoryId);*/
								}
							}
							else if(jsObj.task == "getCadreProblemDetailsForSms")
							{
								cadreProblemDetails = myResults;
								openCadreSmsPopup(jsObj.cadreId,jsObj.pHistoryId);
							}
							else if(jsObj.task == "sendSMS")
							{
								showSMSResult(myResults);
							}
							if(jsObj.task == "addCadreToProblem"){
								  if(myResults.resultCode == 0)
								  {
								      alert("Your Session Expired Please LogIn");
									  return;
								   }
							}
							if(jsObj.task =="getProblemImages"){
								builImagesDiv(myResults);
							}
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o )
					 {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
					 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function builImagesDiv(results){

var problemRelatedImagesElmt = document.getElementById("problemRelatedImages");
var str ='';
str+='<b>Problem Related Images</b>';
var str ='';
str+='<div id="content">';
str+='<table>'
for(var i in results)
{
no_of_imagesPerRow = 4; 
j = i;
if(j++ % no_of_imagesPerRow == 0){
str+= '<tr>';
}
var fileType = results[i].file.split(".");

if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){


str+= '<td><table><tr><td>';
str+= '<a  href="javascript:{}" title="View file" ">';

if(fileType[(fileType.length-1)] == "pdf"  ){
str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 				onclick="javascript:{openFile(\''+results[i].pathOfFile+'\')}"/>';
}
else if(fileType[(fileType.length-1)] == 'text'){
str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 				onclick="javascript:{openFile(\''+results[i].pathOfFile+'\')}"/>';
}
else if(fileType[(fileType.length-1)].indexOf('word') != -1){
str+= '<a href="'+results[i].pathOfFile+'"><img alt="" src="images/doc_images/wordImage.png" height="100px" ></img></a>';
}

str+= '</a></td>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+results[i].title+'</div></td></tr></table></td>';


}
else{
str+= '<td><table><tr><td>';
str+= '<a rel="photo_gallery" href="'+results[i].pathOfFile+'" title="'+results[i].description+'"><img alt="" src="'+results[i].pathOfFile+'" height="100px" /></a></td>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+results[i].title+'</div></td></tr></table></td>';

}

if(j % no_of_imagesPerRow == 0){
str+= '</tr>';
}

}
str+= ' </table>';
str+='</div>';
problemRelatedImagesElmt.innerHTML = str;

$("a[rel=photo_gallery]").fancybox({
'transitionIn'		: 'none',
'transitionOut'		: 'none',
'titlePosition' 	: 'over',
'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
	return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
}
});

}
 
function openFile(filePath,fileType){

window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}
function buildProblemRecentActivities(jsObj,results)
{
	

	var bodyElmt = document.getElementById("problemContentData_activities_body");
	if(results == null || results.length == 0)
	{
       bodyElmt.innerHTML = 'No Problem Activities are taken place';
	   return;
	}

	var str = '';
	str += '<fieldset><legend>Activities</legend>';
	str += '<div id="problemContentData_activities_dataDiv">';
	for(var i=0; i<results.length; i++)
	{
		str += '<div class="activitiesContentDiv_main">';
		str += '		<div class="activitiesContentDiv_head">';
		str += '			<table>';
		str += '			<tr>';
		str += '			<td><img src="images/icons/constituencyManagement/calendar.jpeg"></td>';
		str += '			<td> On '+results[i].updatedDate+'</td>';
		str += '			</tr>';
		str += '			</table>';		
		str += '		</div>';
		str += '		<div class="activitiesContentDiv_body">';
		str += '			<table class="activitiesContent_table">';
		str += '				<tr>';
		str += '					<th>Activity</th>';
		str += '					<td colspan="5">'+results[i].activityHapened+'</td>';
		str += '				</tr>';
		if(results[i].cadre != null)
		{
		str += '				<tr>';
		str += '					<th>Cadre</th>';		
		str += '					<td colspan="5">'+results[i].cadre+'</td>';
		str += '				</tr>';
		}
		if(results[i].departmentOrganisation != null && results[i].department != null && results[i].deptLocation == null)
		{
			str += '				<tr>';
			str += '					<th colspan="6"><span class="labelSpan">Department</span></th>';
			str += '				</tr>';
			str += '				<tr>';
			str += '					<th>Name</th>';
			if(results[i].departmentOrganisation == "" || results[i].departmentOrganisation == null)
				str += '					<td> Not Assigned</td>';
			else
				str += '					<td>'+results[i].departmentOrganisation+'</td>';

			str += '					<th>Category</th>';
			if(results[i].department == "" || results[i].department == null)
				str += '					<td> Not Assigned</td>';
			else
				str += '					<td>'+results[i].department+'</td>';

			str += '					<th>Location</th>';
			if(results[i].deptLocation == "" || results[i].deptLocation == null)
				str += '					<td> Not Assigned</td>';
			else
				str += '					<td>'+results[i].deptLocation+'</td>';
			
			str += '				</tr>';
		}
		if(results[i].probClassification != null)
		{
			str += '				<tr>';
			str += '					<th>Problem Type</th>';
			str += '					<td>'+results[i].probClassification+'</td>';
			str += '				</tr>';
		}
		if(results[i].problemStatus != null)
		{
			str += '				<tr>';
			str += '					<th>Problem Status</th>';
			str += '					<td>'+results[i].problemStatus+'</td>';
			str += '				</tr>';
		}
		str += '				<tr>';
		str += '					<th>Comments</th>';
		if(results[i].comments == "" || results[i].comments == null)
			str += '					<td colspan="5"> -- </td>';
		else			
			str += '					<td colspan="5">'+results[i].comments+'</td>';
		str += '				</tr>';
		str += '			</table>';
		str += '		</div>';
		str += '	</div>';
	}


	str += '</div></fieldset>';

	bodyElmt.innerHTML = str;
}

function clearDepartmentDiv()
{
	var elmt = document.getElementById("deleteDepartmentConfirmDiv");
	elmt.innerHTML = '';
}

function DeleteDepartment()
{
	var jsObj =
		{
			pHistoryId:pHistoryId,
			deptScopeId:0,
			deptId:0,
			regionId:0,
			status:"Delete",
			task:"changeDepartmentForProblem"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProblemDepartmentAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function handleDepartmentChange(type)
{	
	if(type == "Delete")
	{
		var elmt = document.getElementById("deleteDepartmentConfirmDiv");

		var str = '';
		str += '<table>';
		str += '<tr>';
		str += '<td>Do you want to delete department</td>';
		str += '<td><input type="button" class="button" value="Delete" onclick="DeleteDepartment()"/></td>';
		str += '<td><input type="button" class="button" value="No" onclick="clearDepartmentDiv()"/></td>';
		str += '</tr>';
		str += '</table>';		

		elmt.innerHTML = str;


	}

	getProblemDepartments(0,'getProblemResolvingDeptScopes');
	$( "#departmentPanel_main" ).dialog({
		title:"",
		autoOpen: true,
		show: "blind",
		width: 550,
		minHeight:300,
		modal: true,
		hide: "explode"
	});

var elmt = document.getElementById("departmentPanel_content");
if(!elmt)
	return;

var str = '';
str += '<DIV id="problemAssigningDiv">';
str += '<TABLE>';
str += '	<tr>';
str += '		<th width="225px">';
str += '		<s:label for="resolvingDeptScopeId" id="resolvingDeptScope" theme="simple" value="Problem Resolving Dept Scope"/>';
str += '		</th>';
str += '		<td>';
str += '		<select id="resolvingDeptScopeId" class="selectWidth" name="resolvingDeptScope" onchange="javascript:{getProblemDepartments(this.options[this.selectedIndex].value,\'getDepartmentCategories\');populateDeptLocations(this.options[this.selectedIndex].value);}">';
str += '<option value="0">Select Problem Scope</option>';
str += '</select></td>';
str += '	</tr>';
str += '	<tr>';
str += '		<th><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Select Department"/></th>';
str += '		<td>';
str += '			<select id="deptId" class="selectWidth" name="dept">';
str += '				<option value="0">Select Dept</option>';
str += '			</select>';
str += '		</td>';
str += '	</tr>';
str += '	<tr id="deptAreaHeadId" style="display:none;">';
str += '		<th id="thId" colspan="4"><u>Problem Resolving Dept Area</u></th>';
str += '	</tr>';
str += '	<tr id="row1" style="display:none;">';
str += '	<th><s:label for="stateId" id="stateLabelId" theme="simple" value="Select State"/><font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '		<select id="StateId" class="selectWidth" name="state" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'districtsInState\',\'influencingPeopleReg\',\'DistrictId\', \'currentAdd\');">';
<c:forEach var="options" items="${statesListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str += '		</td>';
str += '	</tr>';

str += '	<tr id="row2" style="display:none;">';
str += '		<th><s:label for="districtId" id="districtLabelId" theme="simple" value="Select District"/><font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '		<select id="DistrictId" cssClass="selectWidth" name="district" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'influencingPeopleReg\',\'ConstituencyId\',\'currentAdd\');">';
<c:forEach var="options" items="${districtsListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str += '		</td>';
str += '	</tr>';

str += '	<tr id="row3" style="display:none;">';
str += '		<th><s:label for="constituencyId" id="constituencyLabelId" theme="simple" value="Select Constituency"/><font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '		<select id="ConstituencyId" class="selectWidth" name="constituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'influencingPeopleReg\',\'MandalId\',\'currentAdd\', \'null\')">';
<c:forEach var="options" items="${costituenciesListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str += '		</td>';
str += '	</tr>';

str += '	<tr id="row4" style="display:none;">';
str += '		<th><s:label for="mandalId" id="mandalLabelId" theme="simple" value="Select Mandal/CORP/GMC"/><font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '			<select id="MandalId" class="selectWidth" name="mandal" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'influencingPeopleReg\',\'VillageId\',\'currentAdd\');">';
<c:forEach var="options" items="${mandalsListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '			</select>';
str += '		</td>';
str += '	</tr>';

str += '	<tr id="row5" style="display:none;">';
str += '		<th><s:label for="villageId" id="villageLabelId" theme="simple" value="Select village"/><font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '		<select id="VillageId" class="selectWidth" name="village">';
<c:forEach var="options" items="${villagesListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str += '		</td>';
str += '	</tr>';
str += '</table>';
str += '</DIV>';

str += '<div id="errorMsgDiv" style="text-align:right;padding:10px;color:red;">';
str += '</div>';

str += '<div id="departmentAssignedStatusDiv"></div>';
str += '<div style="text-align:right;padding:10px;">';
str += '<input type="button" class="button" style="float:none;" value="Proceed" onclick="saveDepartmentToProblem(\''+type+'\')"/>';
str += '</div>';
str += '<div id="departmentAssignedStatusDiv"></div>'

elmt.innerHTML = str;
}

function saveDepartmentToProblem(type)
{
	var errorDiv = document.getElementById("errorMsgDiv");
	
	var deptScopeElmt = document.getElementById("resolvingDeptScopeId");
	var deptScopeElmtValue = deptScopeElmt.options[deptScopeElmt.selectedIndex].value;
	var deptScopeElmtText = deptScopeElmt.options[deptScopeElmt.selectedIndex].text;

	var deptNameElmt = document.getElementById("deptId");
	var deptNameElmtValue = deptNameElmt.options[deptNameElmt.selectedIndex].value;

	var regionElmt;
	var regionElmtValue;
	
	if(deptScopeElmtValue == "0")
	{
		errorDiv.innerHTML = "Please select Dept Scope";
		return;
	}
	else if(deptNameElmtValue == "0")
	{
		errorDiv.innerHTML = "Please select Department";
		return;
	}

	regionElmt = document.getElementById(deptScopeElmtText+"Id");
	regionElmtValue = regionElmt.options[regionElmt.selectedIndex].value;		

	if(regionElmtValue == "0")
	{
		errorDiv.innerHTML = "Please Resolving Department Area";
		return;
	}

	if(deptScopeElmtValue != "0" && deptNameElmtValue != "0" && regionElmtValue != "0")
	{
		errorDiv.innerHTML = "";
	}

	var jsObj =
		{
			pHistoryId:pHistoryId,
			deptScopeId:deptScopeElmtValue,
			deptId:deptNameElmtValue,
			regionId:regionElmtValue,
			status:type,
			task:"changeDepartmentForProblem"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProblemDepartmentAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
	
}

function buildProblemPresentStatus(jsObj,results)
{
	var elmt = document.getElementById("problemContentData_status_dataDiv");

	var str = '';	
	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="2" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	
	str += '	</table>';
	str += '</div>';
	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="2" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/usergroups/system_grps.png"></td>';
	str += '						<th>Posted By</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	str += '			<td class="statusData_table_data">Problem posted by '+results.postedBy+'</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td class="statusData_table_links">Name : '+results.postedByName+' </td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';
	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="3" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>';
	str += '						<th>Department</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	if(results.department == "" || results.department == null)
		str += '			<td class="statusData_table_data">Currently not assigned to any department</td>';
	else
		str += '			<td class="statusData_table_data">Currently assigned to <font color="#FF8000"><B>'+results.departmentOrganisation+'</B></font></td>';
	str += '		</tr>';
	str += '		<tr>';	
	str += '			<td class="statusData_table_links">';
	if(results.department == "" || results.department == null)
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="handleDepartmentChange(\'Assign\')" class="changeAnc">Assign</a>';
	else
	{
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="handleDepartmentChange(\'Change\')" class="changeAnc">Change</a>';
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="handleDepartmentChange(\'Delete\')" class="changeAnc">Remove</a>';
	}
	str += '			</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '		<td><div id="deleteDepartmentConfirmDiv"></div></td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';

	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="3" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>';
	str += '						<th>Problem Type</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	if(results.probClassification == "" || results.probClassification == null)
		str += '			<td class="statusData_table_data">Currently not assigned to any problem Type</td>';
	else
		str += '			<td class="statusData_table_data">Currently assigned to '+results.probClassification+'</td>';
	str += '		</tr>';
	str += '		<tr>';	
	str += '			<td class="statusData_table_links">';
	if(results.probClassification == "" || results.probClassification == null)
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" class="changeAnc" onclick="showProbClassification(\'Assign\')">Assign</a>';
	else
	{
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" class="changeAnc" onclick="showProbClassification(\'Change\')">Change</a>';		
	}
	str += '			</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td id="probClassification_row" style="display:none;">';
	str += '			Select Type <select id="probClassification" style="margin:0px 20px 0px 10px;padding:2px;">';
	str += '				<option>Social</option>';
	str += '				<option>Economical</option>';
	str += '				<option>Personal</option>';
	str += '			</select>';
	str += '			<input type="button" style="width:90px;height:25px;float:none;" value="Proceed" class="button" onclick="changeProbClassification()"/>';
	str += '			<input type="button" style="width:90px;height:25px;float:none;" value="Cancel" class="button" onclick="hideProbClassification()"/></td></tr>';
	str += '			</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';

	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="3" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/usergroups/icon_groups.png"></td>';
	str += '						<th>Cadre</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	if(results.cadre == "" || results.cadre == null)
		str += '			<td class="statusData_table_data">This problem has not been assigned to any cadre</td>';
	else
		str += '			<td class="statusData_table_data">This problem has been assigned to '+results.cadre+'</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td class="statusData_table_links">';
	if(results.cadre == "" || results.cadre == null)
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" class="changeAnc" onclick="getCadreDetails(\'Assign\')">Assign</a>';
	else
	{
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="getCadreDetails(\'Change\')" class="changeAnc">Change</a>';
		str += '<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="getCadreDetails(\'Delete\')" class="changeAnc">Remove</a>';
	}
	str += '			</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '		<td><div id="selectedCadreDiv"></div></td>';			
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';
	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="2" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>';
	str += '						<th>Status</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	str += '			<td class="statusData_table_data">This problem is under <font color="#FF8000"><B>'+results.problemStatus+'                               </B></font>stage</td>';
	str += '               <div id = "statusErrorDiv"></div>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td class="statusData_table_links">';
	str += '				Move to ';
	str += '				<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="changeProblemStatus(\'Progress\')" class="changeAnc">Progress</a>';
	str += '				<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="changeProblemStatus(\'Pending\')" class="changeAnc">Pending</a>';
	str += '				<img src="images/icons/districtPage/listIcon.png"/><a href="javascript:{}" onclick="changeProblemStatus(\'Fixed\')" class="changeAnc">Fixed</a>';
	str += '			</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';
	str += '<div class="problemStatusDataDiv_main">';
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="2" width="20%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>';
	str += '						<th>Post Comment</th>';
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	str += '			<td class="statusData_table_data">';
	str += '				<textarea rows="3" cols="45" onkeyup="limitText(\'descTextArea\',\'maxcount\',500)" id="descTextArea" theme="simple" name="comments"></textarea>';
	str += '				<input type="button" style="float:none" class="button" value="Post" onclick="postCommentForProblem()">'
	str += '			</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td class="statusData_table_links">';
	str+='<span id="alertMsg"></span>';
	str += '			<table style="width:65%;">';
	str += '				<tr>';
	str += '				<td style="width:50%;"><div id="remainChars"><span id="maxcount">500 </span> <span>chars remaining..</span></div></td>';
	str += '				<td style="width:50%;"><div>Should not exceed 500 chars</div></td>';
	str += '				</tr></table>';	
	str += '			</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</div>';
	
	elmt.innerHTML = str;
}

function postCommentForProblem()
{
	var elmt = document.getElementById("descTextArea");
	var textAreaValue = elmt.value;
	document.getElementById("alertMsg").innerHTML ='';

	if(elmt.value ==''){
		document.getElementById("alertMsg").innerHTML ='<font color="red">Please enter comments</font>';
		return;
	}
	
	var jsObj = {
					pHistoryId:pHistoryId,
					comments:textAreaValue,
					task:"PostCommentsToProblem"
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "postCommentsToProblemAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
	
   }

function showProbClassification()
{
	var elmt = document.getElementById("probClassification_row");
	elmt.style.display = "block";
}

function hideProbClassification()
{
	var elmt = document.getElementById("probClassification_row");
	elmt.style.display = "none";
}

function changeProbClassification()
{
	var elmt = document.getElementById("probClassification");
	var typeValue = elmt.options[elmt.selectedIndex].text;
	var jsObj = {
					typeValue:typeValue,
					pHistoryId:pHistoryId,
					status:'Change',
					task:"changeProbClassification"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProbClassificationAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function changeProblemStatus(status)
{
	var jsObj = {
					status:status,
					pHistoryId:pHistoryId,
					task:"changeProblemStatus"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProblemStatusAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function populateDeptLocations(index)
{
	var deptEl = document.getElementById("deptAreaHeadId");
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	
	deptEl.style.display = 'none';
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	
	if(index >= 1)
	{
		deptEl.style.display = '';
		row1El.style.display = '';
	}
	if(index >= 2)
	{
		row2El.style.display = '';
	}
	if(index >= 3)
	{
		row3El.style.display = '';
		row4El.style.display = '';
	}
	if(index >= 7)
	{
		row5El.style.display = '';
	}

}

function getProblemPresentStatus(pHistoryId)
{
	var jsObj = {
					pHistoryId:pHistoryId,
					task:"getProblemPresentStatus"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemPresentStatusAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}


function getProblemActivities(pHistoryId)
{
	var jsObj = {
					pHistoryId:pHistoryId,
					task:"getProblemRecentActivities"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemRecentActivitiesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getProblemRelatedImages(pHistoryId){
    
	var jsObj ={
				pHistoryId:pHistoryId,
				task:"getProblemImages"

	           };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemImagesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function initializePage()
{
	var detailsButton = new YAHOO.widget.Button("detailsButton");
	/*detailsButton.on("click",function (){
		$('#problemContentData_details_body').slideToggle();
	});*/

    sliderFunc();
	getProblemPresentStatus(pHistoryId);
	getProblemActivities(pHistoryId);
	getProblemRelatedImages(pHistoryId);
}

function sliderFunc(){
		$('#problemContentData_details_body').slideToggle();
}

function closeCompleteDetails()
{
   window.opener.document.location.reload(true);
   window.close();
}
function openAddNewProblemWindow()
{	
	var browser1 = 
	window.open("addNewProblemAction.action","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser1.focus();
}
function openEditProblemWindow()
{	
	var browser = 
	window.open("addNewProblemAction.action?problemHistoryId="+pHistoryId,"addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser.focus();
}	
var statesListForProb = [];
var districtsListForProb = [];
var costituenciesListForProb = [];
var mandalsListForProb = [];
var villagesListForProb = [];


</script>
</head>
<body class="bodyStyle">
<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<TD>
			<div class="problemReportHeader"><span style="margin-top:2px;">Problem Complete Details</span></div>
		</TD>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
		
	</TR>
</TABLE>
</CENTER>
<div id="closeWindowDiv">
<table>
 <tr>
 <c:if test="${Request == true}">
  <td><a href="javaScript:{}" style="padding-right: 12px; color: green;" onclick="openAddNewProblemWindow()">Add New Problem</a>
  </td>
  <td><a href="javaScript:{}" style="color: green;" onclick="openEditProblemWindow()">Create Similar Problem</a>
  </td>
  </c:if>
  <td style="padding-left: 19px;"><input type="button" value="Close" onClick="closeCompleteDetails()" />
  </td>
  </tr>
 </table>
 </div>
<div id="departmentPanel_main"><div id="departmentPanel_content"></div></div>
<div id="problemContentData_main">
	<!-- Problem Details Start -->
	<div id="problemContentData_details_main">
		<div id="problemContentData_details_head">
			<table width="100%">
				<tr>
				<th>Title Of The Problem  :                    ${problemCompleteDetailsVO.problemBasicDetails.problem}</th>
					<!--<td class="yui-skin-sam" align="right"><input id="detailsButton" type="button" value="Details"/></td>-->					
				</tr>
			</table>
			
		</div>
		<div id="problemContentData_details_body" style="display:none;">
			<TABLE width="100%" class="problemDetailsTable" align="left" cellpadding="0" cellspacing="0">							

				<tr>
					<th width="20%" valign="top">Location</th>
					<td valign="top">:</td>
					<td valign="top">${problemCompleteDetailsVO.problemBasicDetails.problemLocation}</td>
				</tr>
				<tr>
					<th width="20%" valign="top">Existing From</th>
					<td valign="top">:</td>
					<td valign="top">${problemCompleteDetailsVO.problemBasicDetails.existingFrom}</td>
				</tr>

				<tr>
				    <th valign="top">Posted on</th>
				    <td>:</td>
				    <td>${problemCompleteDetailsVO.problemBasicDetails.reportedDate}</td>
				</tr>

				<tr>
					<th width="20%" valign="top">Description </th>
					<td valign="top">:</td>
					<td valign="top">${problemCompleteDetailsVO.problemBasicDetails.description}</td>
				</tr>
			</TABLE>
		</div>
		
	</div>
	
	<!-- Problem Details End -->
	
   	<!-- Problem Status End -->


	<div id="tabs" style="margin-top: 165px;">
    <ul>
        <li><a href="#problemContentData_status_main">
		<span id="tabsLiStyle">Problem Status</span></a></li>
        <li>
		<a href="#problemContentData_activities_main">
		<span id="tabsLiStyle">Activities</span></a></li>
		<li>
		<a href="#problemRelatedImages">
		<span id="tabsLiStyle">Images</span></a></li>
        
    </ul>
	<div id="problemContentData_status_main">
		<div id="problemContentData_status_head">			
		</div>
		<div id="problemContentData_status_body">
			<FIELDSET>
			<LEGEND>Problem Status</LEGEND>
			<div id="problemContentData_status_dataDiv">
				
			</div>
			</FIELDSET>
		</div>
	</div>
	<!-- Problem Status End -->
	
	<!-- Problem Activities End -->
	<div id="problemContentData_activities_main">
		<div id="problemContentData_activities_head">			
		</div>
		<div id="problemContentData_activities_body">
			<!--<fieldset>
				<legend>Activities</legend>
				<div id="problemContentData_activities_dataDiv">
				</div>
			</fieldset> -->
		</div>
	</div>
	<div id="problemRelatedImages"></div>
</div>
	<!-- Problem Activities End -->

</div>

<!--
<DIV><P>Fields marked with <font class="requiredFont"> * </font> are mandatory</P></DIV>
<DIV>
<table>
	<tr>
		<td colspan="2">
			<div style="color: red;">
				<s:actionerror />
				<s:fielderror />
			</div>
		</td>
	</tr>
</table>
</DIV>




<DIV id="assigningButtonDiv">
<c:if test="${problemCompleteDetailsVO.problemBasicDetails.problemStatus  == 'NEW'}">
<Table align="center">
	<TR>
		<td><input type="button" style="width:160px;height:30px;" value='MOVE TO PROGRESS' class="button" onclick="showProbStatusDetails();getProblemDepartments(0,'getProblemResolvingDeptScopes');getProblemDepartments(0,'getProblemType')"/></td> 
	</TR>
<Table>
</c:if>

<c:if test="${problemCompleteDetailsVO.problemBasicDetails.problemStatus  == 'PENDING'}">
<Table align="center">
	<TR>
		<td><input type="button" style="width:160px;height:30px;" value='MOVE TO PROGRESS' class="button" onclick="showProbStatusDetails();getProblemDepartments(0,'getProblemResolvingDeptScopes');getProblemDepartments(0,'getProblemType')"/></td> 
	</TR>
<Table>
</c:if>

<c:if test="${problemCompleteDetailsVO.problemBasicDetails.problemStatus  == 'PROGRESS'}">
<Table align="center">
	<TR>
		<th><input type="radio" name="probProgress" id="pendingRadioId" value="PENDING" onclick="showProbStatusDetails();setStatusToChange('PROGRESS',this.value)">Move TO Pending</input>
		</th>
		<th><input type="radio" name="probProgress" id="fixedRadioId" value="FIXED" onclick="showProbStatusDetails();setStatusToChange('PROGRESS',this.value)">Move TO Fixed</input>
		</th>
	</TR>
</c:if>

</DIV>-->

<form method="post" action="problemAssigningAction.action">
<table width="100%" id="probAssigningTabId" style="display:block;">
<tr><td>
<!--<DIV id="problemAssigningDiv">
<FIELDSET>
<LEGEND>Problem Details</LEGEND>
<TABLE>
	<tr>
		<th width="225px"><s:label for="problemTypeId" id="problemTypeLabelId" theme="simple" value="Problem Type"/></th>
		<td><s:select id="problemTypeId" cssClass="selectWidth" name="problemType" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type"></s:select></td>
	</tr>

	<tr>
		<th width="225px"><s:label for="resolvingDeptScopeId" id="resolvingDeptScope" theme="simple" value="Problem Resolving Dept Scope"/></th>
		<td><s:select id="resolvingDeptScopeId" cssClass="selectWidth" name="resolvingDeptScope" theme="simple" list="{}" onChange="getProblemDepartments(this.options[this.selectedIndex].value,'getDepartmentCategories');populateDeptLocations(this.options[this.selectedIndex].value);" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope"></s:select></td>
	</tr>

	<tr>
		<th><s:label for="deptCategoryId" id="deptCategoryLabelId"  theme="simple" value="Department Category"/></th>
		<td>
			<s:select id="deptCategoryId" cssClass="selectWidth" name="deptCategory" theme="simple" onChange="getProblemDepartments(this.options[this.selectedIndex].value,'getDepartments')" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type"></s:select>
		</td>
	</Tr>

	<tr>
		<th><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Select Department"/></th>
		<td>
			<s:select id="deptId" cssClass="selectWidth" name="dept" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Dept"></s:select>
		</td>
	</tr>

	<tr id="deptAreaHeadId" style="display:none;">
		<th id="thId" colspan="4"><u>Problem Resolving Dept Area</u></th>
	</tr>

	<tr id="row1" style="display:none;">
		<th><s:label for="stateId" id="stateLabelId" theme="simple" value="Select State"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="stateId" cssClass="selectWidth" name="state" theme="simple" list="#session.statesListForProb" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','influencingPeopleReg','districtId','currentAdd');"></s:select>
		</td>
	</tr>

	<tr id="row2" style="display:none;">
		<th><s:label for="districtId" id="districtLabelId" theme="simple" value="Select District"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="districtId" cssClass="selectWidth" name="district" theme="simple" list="#session.districtsListForProb" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','influencingPeopleReg','constituencyId','currentAdd');"></s:select>
		</td>
	</tr>

	<tr id="row3" style="display:none;">
		<th><s:label for="constituencyId" id="constituencyLabelId" theme="simple" value="Select Constituency"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="constituencyId" cssClass="selectWidth" name="constituency" theme="simple" list="#session.costituenciesListForProb" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'subRegionsInConstituency','influencingPeopleReg','mandalId','currentAdd', 'null');"></s:select>
		</td>
	</tr>

	<tr id="row4" style="display:none;">
		<th><s:label for="mandalId" id="mandalLabelId" theme="simple" value="Select Mandal/CORP/GMC"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="mandalId" cssClass="selectWidth" name="mandal" theme="simple" list="#session.mandalsListForProb" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'hamletsOrWardsInRegion','influencingPeopleReg','villageId','currentAdd');" ></s:select>
		</td>
	</tr>

	<tr id="row5" style="display:none;">
		<th><s:label for="villageId" id="villageLabelId" theme="simple" value="Select village"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="villageId" cssClass="selectWidth" name="village" theme="simple" list="#session.villagesListForProb" listKey="id" listValue="name" ></s:select>
		</td>
	</tr>

</table>
</FIELDSET>
</DIV>-->
</td></tr>
<!--
<tr><td>
	<div>
		<table>
			
			<tr>
				<th width="225px"><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Assign This Problem To Cadre"/></th>
				<td><input type="button" style="width:120px;height:30px;" value="Get Cadre" class="button" onclick="getCadreDetails()"/></td>
			</tr>
			<tr>
				<div id="cadreDetailsDiv" style="display:none;" theme="simple" ></div></tr>
			</tr>
		</table>
	</div>
</td></tr>

<tr><td>
	<div>
		<table>
			<tr>
				<th width="100px;" theme="simple">Comments</th>
				<td style="padding-left: 15px;"><s:textarea rows="3" cols="45" id="descTextArea" theme="simple"  onkeyup="limitText('descTextArea','maxcount',500)"  name="comments"/></td>
			</tr>	
		</table>
		<table style="width:100%;"><tr>
				<td style="width:50%;"><div id="remainChars"><span id="maxcount">500 </span> <span>chars remaining..</span></div></td>
				<td style="width:50%;"><div>Should not exceed 500 chars</div></td>
		</tr></table>
	</div>
</td></tr>

<tr><td>
	<div>
		<br><br>
		<table align="center" id="sumitTableId">
			<tr>
				<td width="110px"><s:submit name="Save" cssClass="button" style="width:100px;height:30px;background-color:#9871F3;" theme="simple" /></td>

				<td width="110px"><input type="button" value="Exit" class="button" style="width:100px;height:30px;background-color:#9871F3;" onclick="window.close()"/></td>
			</tr>
		</table>
	</div>
</td></tr>


-->
</div>
</table>
<input type="hidden" id="cadreInputId" name="cadreId"/>
<input type="hidden" id="probHistoryId" name="probHistoryId"/>
<input type="hidden" id="statusToChangeId" name="statusToChange"/>
<input type="hidden" id="isSubmitId" name="isSubmit" value="true"/>
</form>
<script>
doExecuteOnLoad();
initializePage();
 
</script>
<script>
  $(document).ready(function() {
    $("#tabs").tabs();

   $("a[rel=photo_gallery]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
  });
  </script> 
  <script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js">
	</script>
	<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js">
	</script>
</body>
</html>