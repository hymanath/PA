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
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" href="styles/style.css" />

<!-- JQuery files (End) -->

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
	font-weight: bold;
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

.imageButton {
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}


.imgFieldset
{
	-moz-border-radius: 4px 4px 4px 4px;
	border			: 4px solid #9F81F7;
    margin-bottom	: 10px;
	margin-top		: 5px;
}


.gallaryImg
{
	width  : 150px;
	height : 130px;
}


</style>

<script type="text/javascript">
google.load("elements", "1", {packages : ["newsshow"]});
		var timeST = new Date().getTime();
		


function showPhotoGallary1()
{
if(!formValidation()){
showPhotoGallary();
showOrHideProblemFilesDiv();
}
return;
}

function showPhotoGallary(){
var candidateId=document.getElementById("candidateld").value;
    var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			startRecord:0,
			maxRecord:20,
			task:"getCandidatePhotoGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}


 function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
         if(jsObj.task == "getCandidatePhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary")
			{
               showPhotosInAGallary(myResults);
			}
       else if(jsObj.task == "getCandidateNewsGallaryDetail")
			{
               buildCandidateGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getNewsInAGallary")
			{ 
               buildGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getDevelopmentsInAGallary")
			{ 
               buildGallary(myResults,"DevelopmentsGallaryDiv","developments");
			}
		else if(jsObj.task == "getCandidateDevelopmentGallaryDetail")
			{ 
               buildCandidateGallary(myResults,"DevelopmentsGallaryDiv","developments");
			}
		else if(jsObj.task == "searchNewsDetails")
		{ 
           buildGallary(myResults,"zero","newsSearch");
		}	
			else if(jsObj.task == "getScopesForNewSearch")
			{ 
               buildResults(myResults,"scopeDiv");
			}
			else if(jsObj.task == "getStates")
			{ 
               buildResults(myResults,"stateDiv");
			}
			else if(jsObj.task == "getDistrictsByStateId")
			{ 
               buildResults(myResults,"districtDiv");
			}
			else if(jsObj.task == "constituenciesInDistrict")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{ 
               buildResults(myResults,"mandalDiv");
			}
			else if(jsObj.task == "hamletsOrWardsInRegion")
			{ 
               buildResults(myResults,"villageDiv");
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality")
			{ 
               buildResults(myResults,"villageDiv");
			}
		
			else if(jsObj.task == "createNewGallary")
			{ 
               showGallaryCreateMsg(myResults);
			}
			else if(jsObj.task == "candiadteGallariesForUplaod")
			{ 
               clearOptionsListForSelectElmtId('gallarySelectId');
			   createOptionsForSelectElmtId('gallarySelectId',myResults);
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

function buildCandidatePhotoGallary(results)
{
	var str ='';

	if(results.length<=0)
	{
		str+='<b>&nbsp;No Photo Galleries Found </b>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
	}
	else
	{
		str+='<div id="content" style="width:650px;">';
		
		str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallary" onclick="buildCreateGallaryDiv()"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
		str += '</tr>';
		str += '</table>'

		str += '<fieldset class="imgFieldset">';
		str +='<table width="100%" style="margin-top:10px;">';
		
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:220px;">';
			
			str += '<td width="33%">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div align="center"><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			str += '<tr><td><div align="center"><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			str+= '<tr><td><div align="center"><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
			str += '</table>';
			str += '</td>';
			
			if(j % no_of_imagesPerRow == 0)
				str+= '</tr>';
		
		}
		str += ' </table>';
		str += ' </fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
	}
}

function buildCreateGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallary" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallary</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallary Name</font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr></table>';

	str += '<div style=padding-left:4px;"><b><font color="#4B74C6">Description</font><b></div>';
	str += '<div style="padding-left:30px;"><textarea id="pGallaryDescId" cols="27" rows="3" name="requirement"></textarea></div>';
	str += '<div><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table><tr><td><input type="button" class="imageButton" value="Create Gallary" style="background-color:#57B731" onClick="createGallary()"></td><td><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';

	str += '<div>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;

}


function buildUploadPhotosDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallary" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;">';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallary</font></b></td><td><select id="gallarySelectId" name="gallaryId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Photo Title</font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="100"></td></tr></table>';

	str += '<div style=padding-left:4px;"><b><font color="#4B74C6">Description</font><b></div>';
	str += '<div style="padding-left:30px;"><textarea id="fileDescId" name="fileDescription" cols="27" rows="3" name="requirement"></textarea></div>';
	str += '<div><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';

	str +='<div style="margin:10px;"><input type="file" name="userImage" id="fileId"/></div>';
	
	str += '<table><tr><td><input type="button" class="imageButton" value="Upload Photo" style="background-color:#57B731" onClick="uploadAFile()"></td><td><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';

	str += '<div>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	getCandiadteGallariesForUplaod();
}

	function createGallary()
{
	var galName = document.getElementById('pGallaryNameId').value;
	var galDesc = document.getElementById('pGallaryDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;
	var candidateId=document.getElementById("candidateld").value;
	var makeThis = 'true';

	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallary Name Required<br>';
		eFlag = true;
	}
	if(galDesc.length > 300)
	{
		str += 'Description should be less than 300 Characters<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';
	
	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			candidateId : candidateId,
			contentType : 'Photo Gallary',
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}



function showGallaryCreateMsg(result)
{
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		clearGallaryFields();
		str += '<font color="green"><b>Gallary Created Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}
	

function getCompleteGallaries(gallaryId){
    var jsObj =
		{ 
            time : timeST,
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

	function showPhotosInAGallary(results){
   var str ='';
   str+='<div id="content" style="width:650px;">';
   str += '<fieldset class="imgFieldset">';
   str+='<table width="100%" style="margin-top:10px;">'
   str+='<tr><td>';
   str+='<input type="button" value="Back To Gallery"  class="imageButton" onclick="showPhotoGallary();" />';
   str+= '</td></tr>';
   for(var i in results)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }

   }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("photoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
}


function getCandiadteGallariesForUplaod()
{
var candidateId=document.getElementById("candidateld").value;
	var jsObj =
		{ 
            candidateId : candidateId,
		   	task : "candiadteGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandiadteGallariesForUplaodAction.action?"+rparam;
	callAjax(jsObj,url);
}

function uploadAFile()
{
	if(validateFileUpload())
	{
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showUploadStatus(uploadResult);				
				}
			};

		
		YAHOO.util.Connect.setForm('uploadFilesForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesAction.action',uploadHandler);
	}
	else
		return;
}

	
function validateFileUpload()
{
	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var fileVal = document.getElementById("fileId").value;
	var galId = document.getElementById("gallarySelectId").value;
	var flag = true;

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += 'Photo Title Required.<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Photo Description Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 200)
	{
		str += 'Photo Description Should not exceed 200 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'Photo Required.<br>';
		flag = false;
	}
	if(galId == 0)
	{
		alert(galId);
		str += 'Select Any Gallary.<br>';
		flag = false;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
}
	
function showUploadStatus(myResult)
{
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(result.search('success') != -1)
	{
		clearUploadFileFields();
		str += '<font color="green"><b>Photo Uploaded Successfully.</b>';
	}
	else if(result.search('fail') != -1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	else
	{
		str += '<font color="red"><b>'+result+'</b>';
	}
	errorDivEle.innerHTML = str;
}
	
function clearUploadFileFields()
{
	document.getElementById('fileTitleId').value = '';
	document.getElementById('fileDescId').value = '';
	document.getElementById('publicRadioId').checked = true;
	document.getElementById('fileId').value = '';
}

function clearGallaryFields()
{
	document.getElementById('pGallaryNameId').value = '';
	document.getElementById('pGallaryDescId').value = '';
	document.getElementById('publicRadioId').checked = true;
}
	
function formValidation()
{
	
	var elmt1 = document.getElementById("candidateld");
	
	
	var textFieldValue = elmt1.value;
	
	document.getElementById("alertMsg1").innerHTML ='';
   	


	if(alltrim(elmt1.value) ==''){
		document.getElementById("alertMsg1").innerHTML ='<font color="red">Please Enter CandidateId</font>';
		
		return true;
	}
	
}
	
	
function showOrHideProblemFilesDiv()
{
	
var problemDetailDivEle = document.getElementById("profileManagementMainOuterDiv1");
if(problemDetailDivEle.style.display =='none')
	problemDetailDivEle.style.display = 'block';
else
    problemDetailDivEle.style.display = 'none';

}

function showOrHideVideoFilesDiv()
{
	
var problemDetailDivEle = document.getElementById("profileManagementMainOuterDiv2");
if(problemDetailDivEle.style.display =='none')
	problemDetailDivEle.style.display = 'block';
else
    problemDetailDivEle.style.display = 'none';

}

function showVideoGallaey()
{
alert("testing...");

if(!formValidation()){

showOrHideVideoFilesDiv();
}
return;
}
	
</script>


</head>
<body>
<br>
<!-- For Heading -->
<div id='profileManagementMainOuterDiv'>

<div id='profileManagementHeaderDiv'>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
		  <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg">
		    </td>
		    <td width="98%">
		       <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
		         <span style="text-decoration: none;" class="headerLabelSpan2"><center>Profile Management Admin</center></span>
		       </div>
		    </td>
		   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
		   </td>
		</tr>
	</table>
</div>
 <!-- For Heading end -->
 
<div id='profileManagementMainInnerDiv' class="divInfo">
	<div id="profileManagementDiv">
		<table class="statusData_table" width="100%">	
		<tr>
			<td class="statusData_table_data" width="83%">
				<table>
				  <tr>
			    	<td style="padding-left:350px"><b>Candidate Id</b></td>
				     <td> <input type="text" id="candidateld" name="candidateld" size="20"/></td>
					 <span id="alertMsg1" style="padding-left:410px"></span>
				  </tr>
				</table>
			</td>
		</tr>
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:110px"><b><input type="radio" name="group1" value="photoGallary" onClick="showPhotoGallary1()">Photo Gallary</b></td>
				    <td style="padding-left:90px"><b><input type="radio" name="group1" value="videoGallaey" onClick="showVideoGallaey()">Video Gallary</b> </td>
					<td style="padding-left:90px"><b><input type="radio" name="group1" value="newsGallaey" onClick="showNewsGallaey()">News Gallary</b> </td>
					<td style="padding-left:90px"><b><input type="radio" name="group1" value="developmentActivity" onClick="showDevelopmentActivity()">Development Activity</b> </td>
				  </tr>
				</table>
			</td>
		 </tr>
		</table>
	
</div>
</div>
</div>

<!-- for  body 1 start    result  -->
<HR><BR>
<div id='profileManagementMainOuterDiv1' style="display:none">
	<div id='profileManagementHeaderDiv1'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Photo Gallary</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='photoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->

<!-- for  body 2 start    result  -->
<HR><BR>
<div id='profileManagementMainOuterDiv2' style="display:none">
	<div id='profileManagementHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Video Gallary</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='photoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->



<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>

</body>
</html>