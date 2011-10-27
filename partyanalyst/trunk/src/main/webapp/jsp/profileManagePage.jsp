<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst-Know | Analyze | Act</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>


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
	 
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
<!-- JQuery files (Start) -->

<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

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
    margin-bottom:20px;
    margin-top:8px;
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
	-moz-border-radius:5px 5px 5px 5px;
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
.tdWidth {           
  font-weight:bold;
  color:#4b74c6;
  width:161px;
}
.tdWidth1 {           
  color:#4B74C6;
  font-weight:bold;
  width:181px;
  
}
.tdWidth2 {           
  font-weight:bold;
  color:#4b74c6;
  width:164px;
} 
.selectWidth{
 width:175px;
}
.selectWidthPadd{
padding-left:0px;
 width:175px;
}
.paddingCss {
padding-left:49px;
}
.requiredFont {
color:red;
font-size:18px;
}
</style>

<script type="text/javascript">
google.load("elements", "1", {packages : ["newsshow"]});
		var timeST = new Date().getTime();
		


function showPhotoGallary1()
{

if(!formValidation()){
showPhotoGallary();
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv5").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
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
		else if(jsObj.task == "candiadteVideoGallariesForUplaod")
			{
           showUploadVideoStatus(myResults);  
			}
		else if(jsObj.task == "saveDiscription")
			{
           showDiscriptionStatus(myResults);  
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
			else if(jsObj.task == "createNewGallary" && jsObj.contentType =='News Gallary')
			{  
               showNewsGallaryCreateMsg(myResults);
			}
		
			else if(jsObj.task == "createNewGallary")  
			{ 
               showGallaryCreateMsg(myResults);
			}
			
			else if(jsObj.task == "createVideoNewGallary")  
			{ 
               showVideoGallaryCreateMsg(myResults);
			}
			else if(jsObj.task == "candiadteGallariesForUplaod" && jsObj.contentType=="News Gallary")
			{ 
               clearOptionsListForSelectElmtId('gallaryId');
			   createOptionsForSelectElmtId('gallaryId',myResults);
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

	
		str+='<div id="content" style="width:650px;">';
		
		str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
		str += '</tr>';
		str += '</table>'

		str += '<fieldset class="imgFieldset">';
		str +='<table width="100%" style="margin-top:10px;">';
	if(results.length<=0)
	{
		str+='<b>&nbsp;No Photo Galleries Found </b>';
	}
	else
	{
		
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:220px;">';
			
			str += '<td width="33%">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div align="center"><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			if(results[i].path!=null)
			{
			 str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			else
			{
			  str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="images/icons/DefaultPhotoGalleryImage.jpg" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			str += '<tr><td><div align="center"><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			str+= '<tr><td><div align="center"><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
			str += '</table>';
			str += '</td>';
			
			if(j % no_of_imagesPerRow == 0)
				str+= '</tr>';
		
		}
	}
		str += ' </table>';
		str += ' </fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
	
}

function buildCreateGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 123px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right: 35px;"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';


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
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallaryId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
	str +='<tr><td><b><font color="#4B74C6">File Path<font class="requiredFont">*</font></font><b></td><td><input type="file" name="userImage" id="fileId"/></td></tr></table>';
	str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';

	
	
	str += '<table><tr><td style="padding-right: 40px;"><input type="button" class="imageButton" value="Upload Photo" style="background-color:#57B731" onClick="uploadAFile()"></td><td style="padding-right: 41px;"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';


	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	getCandiadteGallariesForUplaod('Photo Gallary');
}

	function createGallary(contentType)
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
		str += 'Gallery Name Required<br>';
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
			contentType : contentType,
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
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}
function showNewsGallaryCreateMsg(result)
{
	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
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


function getCandiadteGallariesForUplaod(contentType)
{
var candidateId=document.getElementById("candidateld").value;
	var jsObj =
		{ 
            candidateId : candidateId,
			contentType : contentType,
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
function uploadNews()
{
	if(validateNewsFileUpload())
	{
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus(uploadResult);				
				}
			};

		
		YAHOO.util.Connect.setForm('uploadNewsForm',true);
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
		str += 'Select Any Gallery.<br>';
		flag = false;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
}
function validateNewsFileUpload()
{
	var fileTitle = document.getElementById('fileTitle').value;
	var fileDesc = document.getElementById('fileDescription').value;
	var fileVal = document.getElementById("fileId").value;
	var source = document.getElementById("source").value;
	var flag = true;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += ' Title Is Required.<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Description is Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 200)
	{
		str += 'Description Should not exceed 200 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'File Is Required.<br>';
		flag = false;
	}
	if(source.length == 0)
	{
		str += 'Source Is Required.<br>';
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
function showNewsUploadStatus(myResult)
{
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '';

	if(result.search('success') != -1)
	{
		clearNewsUploadFileFields();
		str += '<font color="green"><b>News Uploaded Successfully.</b>';
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

function clearNewsUploadFileFields()
{
	document.getElementById('fileTitle').value = '';
	document.getElementById('fileDescription').value = '';
	document.getElementById('keywords').value = '';
	document.getElementById('existingFromText').value = '';
	document.getElementById('source').value = '';
	document.getElementById('fileId').value = '';	
	document.getElementById('publicRadioId').checked = true;
	getScopes();
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
	
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';

}
function showNewsGallaey()
{
  if(!formValidation()){
  document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'block';
  document.getElementById("profileManagementMainOuterDiv5").style.display = 'none';
  document.getElementById("videoGallaryDiv").innerHTML=''; 
  buildCreateNewsCategory();
  }
}
function buildCreateNewsCategory()
{
   var str ='';
	str+='<div id="content" style="width:650px;">';
	str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">Make This Private</font></b></input></tr></td></table>';
	
	str += '<table><tr><td><input type="button" class="imageButton" value="Create Category" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv();" style="background-color:#CF4740"></td></tr></table>';

	str += '<div>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
}
function getDistricts1(stateId){
  var jsObj =
		{ 
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
	function createCategory()
{
	var newsCatrgoryName = document.getElementById('newsCateName').value;
	var newsCatrgoryDesc = document.getElementById('newsCateDesc').value;
	var isPublic = document.getElementById('newsPublicRadio').checked;
	var candidateId = document.getElementById("candidateld").value;
	var makeThis = 'true';

	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';
	if(candidateId.length <= 0)
	{
	  document.getElementById('alertMsg1').innerHTML='<font color="red">CandidateId Is Required</font>';
	  eFlag = true;
	}

	if(newsCatrgoryName.length == 0)
	{
		str += 'Category Name Is Required<br>';
		eFlag = true;
	}
	if(newsCatrgoryDesc.length == 0)
	{
		str += 'Description Is Required<br>';
		eFlag = true;
	}
	
	if(newsCatrgoryDesc.length > 300)
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
            name : newsCatrgoryName,
		    desc : newsCatrgoryDesc,
			visibility : makeThis,
			candidateId : candidateId,
			contentType : 'News Gallary',
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getScopes(){
  
 var jsObj =
		{ 
            time : timeST,
			task:"getScopesForNewSearch"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function buildResults(results,divId){
  var elmt = document.getElementById(divId);
         if(divId=='scopeDiv')
		 {
		    var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Scope";
		 }
		 else
		 {
	     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Location";
		}
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
		for(var i in results)
	  {
		var option = document.createElement('option');
		if(divId =="scopeDiv")
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId =="stateDiv" || divId =="districtDiv")
		{
		  option.value=results[i].ids;
		  option.text=results[i].names;
		}
		else 
		{
		  if(results[i].id!=0)
		  {
		  option.value=results[i].id;
		  option.text=results[i].name;
		  }
		}
		if(results[i].id!=0)
		  {
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
		}
	  }
 
}
 function getAllDetails(id,task,areaType,constId)
   {
        var jsObj =
		{ 
            time : timeST,
			id:id,
			task:task,
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:areaType,
			constId:constId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	 callAjax(jsObj,url);
   }
function getStates()
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function clearAllForSelect(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=1;i--)
	{
		elmt.remove(i);
	}	
   }
function clearAll(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
   }
function clearAllElmts(scopeId,value){
 if(scopeId==4)
 { 
    if(value==1)
	clearAllForSelect("constituencyDiv");
	
 }
 if(scopeId==5)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	}
   if(value==2)
	clearAllForSelect("mandalDiv");
 }
 if(scopeId==6)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }
 if(scopeId==9)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }

}

 function buildSearchNewsDetails(){
 var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td style="color:#4B74C6;font-weight:bold;">Location Scope :</td>';
  str +='	   <td style="width:172px;padding-left:10px;"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
  str +='  </tr>';
  str +='</table>';
  str +='  <span id="showScopeSubs" />'; 

 document.getElementById("uploadNewsDiv").innerHTML = str;
 getScopes();
}
function getLocations(id){
   if(id==0)
  {
   var val ='';
  val +='<table>';
  val +='  <tr><td></td>';
  val +='  </tr>';
  val +='</table>';
  document.getElementById("showScopeSubs").innerHTML = val;
    
  }
  else if(id==1)
  {
    var str ='';
  str +='<table>';
  str +='  <tr><td></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
  }
  else if(id==2)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" name="locationValue"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" name="locationValue" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==5 || id==7)
  {
   if(id==5)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==7)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
   
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv"   class="selectWidth" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==6 || id==8)
  {
   if(id==6)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==8)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv"  class="selectWidth" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv"  class="selectWidth" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division :</td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division :</td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
}

function  buildUploadNews()
{
   var str ='';
	str+='<div id="content" style="width:650px;">';
	str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:480px;">';
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table  align="left" class="paddingCss"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';
	str += '<table>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Select Category</td><td class="selectWidthPadd"><select id="gallaryId" name="gallaryId" class="selectWidth" /></select></td>';
	str += '   </tr>';
    str += '   <tr>';
	str += '       <td class="tdWidth1">Title<font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="fileTitle" name="fileTitle" size="25" ></text></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="fileDescription" cols="20" rows="3" name="fileDescription"></textarea></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Keywords</td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" ></text></td></tr>';
	str += '   <TR>';
	str += '     <td colspan="2">';
	str += '<table>';
	str += '   <tr>';
	str += '     <td class="tdWidth2"><b><font color="#4B74C6">File Date</font></b></td>';
	str += '     <TD class="selectWidthPadd"><input type="text" id="existingFromText" readonly="true" name="fileDate" size="25"/>';
	str += '         <DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="source" name="source" size="25" ></text></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">File Path<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="fileId" size="25" /></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope</td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';
	 str += '</table>';
	str += '<table><tr><td><input type="button" class="imageButton" value="Upload News" style="background-color:#57B731" onClick="uploadNews()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv();" style="background-color:#CF4740"></td></tr></table>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getCandiadteGallariesForUplaod("News Gallary");
	 getScopes();
}
function clearDiv()
{
  document.getElementById("newsGallaryDiv").innerHTML = "";
}
function showOrHideVideoFilesDiv()
{
	
document.getElementById("profileManagementMainOuterDiv2").style.display = 'block';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
}

function showVideoGallaey1()
{
if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv2").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv5").style.display = 'none';
buildCreateVideoGallaryDiv();

}
return;
}
	

function buildCreateVideoGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right: 40px"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;

}

function buildUploadVideoDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Video</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallarySelectId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="100"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date</font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromText" readonly="true" name="fileDate" size="20"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '<tr><td><b><font color="#4B74C6">Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path" name="path" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Source</font></b></td><td><input type="text" id="source" name="source" size="25" maxlength="100"></td></tr>';
	str += '</table>';
	str += '<div style="padding-right: 72px;"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 88px;"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 18px;"><input type="button" class="imageButton" value="Upload Video" style="background-color:#57B731" onClick="uploadVideoGallary()"></td><td style="padding-right: 31px;"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;
	getCandiadteGallariesForUplaod('Video Gallary');
}

function getCompleteVideoGallaries(gallaryId){
    /*var jsObj =
		{ 
            time : timeST,
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);*/
}


function createVideoGallary(contentType)
{

	var galName = document.getElementById('pVGallaryNameId').value;
	var galDesc = document.getElementById('pVGallaryDescId').value;
	var isPublic = document.getElementById('vpublicRadioId').checked;
	var candidateId=document.getElementById("candidateld").value;
	var makeThis = 'true';

	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallery Name Required<br>';
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
			contentType : contentType,
			task : "createVideoNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function uploadVideoGallary()
{

	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var path = document.getElementById('path').value;
	var candidateId=document.getElementById("candidateld").value;
	var galId = document.getElementById("gallarySelectId").value;
	var keyword = document.getElementById("keyword").value;
	var source = document.getElementById("source").value;
	var fileDate = document.getElementById("existingFromText").value;
	var isPublic = document.getElementById('vpublicRadioId').checked;
	var makeThis = 'private';

	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += 'Title Is Required<br>';
		eFlag = true;
	}
	if(fileDesc.length ==0)
	{
		str += 'Description Is Required<br>';
		eFlag = true;
	}
	if(path.length ==0)
	{
		str += 'Path Is Required<br>';
		eFlag = true;
	}
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'public';

	var jsObj =
		{ 
		    
            candidateId	: candidateId,
			gallaryId	: galId,
			videoTitle	: fileTitle,
			videoDescription : fileDesc,
			path		: path,
			keywords	: keyword,
			source		: source,
			fileDate	: fileDate,
			visibility	: makeThis,
		   	task		: "candiadteVideoGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);
}

function showVideoGallaryCreateMsg(result)
{
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		clearVideoGallaryFields();
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}

function showUploadVideoStatus(result)
{
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';

	if(result.resultCode == 0)
	{
		clearUploadVideoFields();
		str += '<font color="green"><b>Video Uploaded Successfully.</b>';
	}
	else if(result.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	else
	{
	
		str += '<font color="red"><b>'+result+'</b>';
	}
	errorDivEle.innerHTML = str;
}

function clearVideoGallaryFields()
{
	document.getElementById('pVGallaryNameId').value = '';
	document.getElementById('pVGallaryDescId').value = '';
	document.getElementById('vpublicRadioId').checked = true;
}

function clearUploadVideoFields()
{
	document.getElementById('fileTitleId').value = '';
	document.getElementById('fileDescId').value = '';
	document.getElementById('vpublicRadioId').checked = true;
	document.getElementById('path').value = '';
	document.getElementById("keyword").value = '';
	document.getElementById("source").value = '';	
	document.getElementById('existingFromText').value = '';
}
//****** profile discription ***

function cleardescriptionFields()
{
	document.getElementById('profileDescId').value = '';

}
function insertProfileDiscription()
{

if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv5").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
profileDiscriptionDiv();
}
return;
}
 
function profileDiscriptionDiv()
{

	var str ='';
	str+='<div id="content" style="width:650px;">';
	str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Profile  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table><tr><td style="padding-left: 82px"><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td style="padding-left: 20px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("discriptionDiv").innerHTML = str;

} 
 
 function addProfileDiscription()
 {
 var fileDesc = document.getElementById('profileDescId').value;
 var candidateId=document.getElementById("candidateld").value;
 var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(fileDesc.length == 0)
	{
		str += 'Profile Discription Is Required<br>';
		eFlag = true;
	}
    str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	var jsObj =
		{ 
		    
            candidateId : candidateId,
			fileDesc : fileDesc,
		   	task : "saveDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
    
 }
 
 function showDiscriptionStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(myResult.resultCode == 0)
	{
		cleardescriptionFields();
		str += '<font color="green"><b>Profile Discription saved Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
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
			    	<td style="padding-left:0px"><b><input type="radio" name="group1" value="photoGallary" onClick="showPhotoGallary1()">Photo Gallery</b></td>
				    <td style="padding-left:75px"><b><input type="radio" name="group1" value="videoGallaey" onClick="showVideoGallaey1()">Video Gallery</b> </td>
					<td style="padding-left:80px"><b><input type="radio" name="group1" value="newsGallaey" onClick="showNewsGallaey()">News Gallery</b> </td>
					<td style="padding-left:80px"><b><input type="radio" name="group1" value="developmentActivity" onClick="showDevelopmentActivity()">Development Activity</b> </td>
					<td style="padding-left:80px"><b><input type="radio" name="group1" value="developmentActivity" onClick="insertProfileDiscription()">Profile Description</b> </td>
				  </tr>
				</table>
			</td>
		 </tr>
		</table>
	
</div>
</div>
</div>

<!-- for  body 1 start    result  -->
<HR>
<div id='profileManagementMainOuterDiv1' style="display:none">
	<div id='profileManagementHeaderDiv1'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Photo Gallery</span>
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

<div id='profileManagementMainOuterDiv2' style="display:none">
	<div id='profileManagementHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Video Gallery</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='videoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->
<div id='profileManagementMainOuterDiv3' style="display:none">
	<div id='profileManagementHeaderDiv3'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">News</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
		<div id='newsGallaryDiv' class="divInfo">
	 </div>		
	</div>
</div>

<!-- for  body Discription start      -->

<div id='profileManagementMainOuterDiv5' style="display:none">
	<div id='profileManagementHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Candidate Profile Discription</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='discriptionDiv' class="divInfo">
	
	</div>

</div>
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script>
$(document).ready(function() {
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
</body>
</html>