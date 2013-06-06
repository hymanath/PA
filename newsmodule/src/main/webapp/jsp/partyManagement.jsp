<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TDP Party Management</title>
<script type="text/javascript" src="js/jquery.js"></script>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>

   <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
    <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js">
	</script> 
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
	 <SCRIPT type="text/javascript" src="js/specialPage/specialPage.js"></SCRIPT>
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
<!-- JQuery files (Start) -->

<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
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
	font-size:15px;
    font-weight:bold;
    margin-bottom:20px;
    margin-top:8px;
    padding:0;
	font-family:verdana;
	color:#669900;
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
    background: none repeat scroll 0 0 #57B731;
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
.buttonStyle {
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
	width:499px;
	margin-left:auto;margin-right:auto;
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
  width:162px;
  text-align:left;
}
.tdWidth2 {           
  font-weight:bold;
  color:#4b74c6;
  width:82px;
} 
.selectWidth{
 width:175px;
}
.selectWidthPadd{
padding-left:0px;
 width:175px;
 text-align:left;
}
.paddingCss {
padding-left:49px;
}
.requiredFont {
color:red;
font-size:18px;
}
.imageStyle {
  vertical-align:top;
}

.canSelect
{
	width : 230px;
}

#content
{
	
	margin: 24px auto 24px auto;
	padding: 0 60px 30px 60px;
	border: solid 1px #cbcbcb;
	background: #fafafa;
	-moz-box-shadow: 0px 0px 10px #cbcbcb;
	-webkit-box-shadow: 0px 0px 10px #cbcbcb;
	
}
#pGallaryDescId,#photofileDescId,#pVGallaryDescId,#fileDescId,#profileDescId,#newsCateDesc,#newsfileDescription,#fileDescription,#galDescId{background : #ffffff !important;margin-top: 8px;width: 214px;}
#contenttable {
    /*padding-bottom: 0;
    padding-left: 0;
    padding-right: 0;
    padding-top: 0;*/
    background: #fff;
	margin-left:auto;
	margin-right:auto;
	padding-left:auto;
	padding-right:auto;width : 980px;
	
}




</style>

<script type="text/javascript">
var gGallaryId;
		var timeST = new Date().getTime();
		var sizeOfArray;

var sourceObj = null;
var languagesObj = null;
var fileCount=0;
function showPhotoGallary1()
{
if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';

document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';

$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});

 buildCreateGallaryDiv();
}
return;
}

function deleteFile(fileTableId)
{
	var confirmFile = confirm('Do you want to delete file');
	if(confirmFile)
		$("#"+fileTableId+"").html('');

}
function getLanguage(fillOptionId)
{
	if(languagesObj == null)
	{
		var jsObj={
		fillOptionId :fillOptionId,
			time:timeST,
		  task:"getLanguage"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	else
		{
			clearOptionsListForSelectElmtId(fillOptionId);
		   createOptionsForSelectElmtId(fillOptionId,languagesObj);
		}

}
function getSource(selectOptionId){
	
	if(sourceObj ==null)
	{
		var jsObj = {
			time : timeST,
			selectOptionId :selectOptionId,
			task : "getSource"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
	}
	 else
		{
			clearOptionsListForSelectElmtId(selectOptionId);
		   createOptionsForSelectElmtId(selectOptionId,sourceObj);
		}
}
 function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
         	 
         if(jsObj.task == "getPartyPhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "partyGallariesForUplaod" && jsObj.updatePhoto== "UpdatePhoto")
			{
               clearOptionsListForSelectElmtId('gallaryPhotoSelectId');
			   createListData('gallaryPhotoSelectId',myResults);
			   $("#gallaryPhotoSelectId").prepend("<option value='0'>Select Gallary</option>");
			   document.getElementById('gallaryPhotoSelectId').value = 0;
			}else if(jsObj.task == "getCategory")
			{ 
               clearOptionsListForSelectElmtId('category');
			   createOptionsForSelectElmtId('category',myResults);
			}
			else if(jsObj.task == "partyVideoGallariesForUplaod")
			{
		
           showUploadVideoStatus(myResults);  
			}

			else if(jsObj.task == "partyVideoVisibility")
			{
				
			
				hideVedioVisibility(myResults);
			}
		else if(jsObj.task == "partyNewsVisibility")
			{
				hideNewsVisibility(myResults);
			}

			else if(jsObj.task == "partyPhotoVisibility")
			{
				hidePhotoVisibility(myResults);
			}
		else if(jsObj.task == "saveDiscription")
			{
           showDiscriptionStatus(myResults);  
			}	
		else if(jsObj.task == "deleteDiscription")
			{
           showDeleteStatus(myResults);  
			}	
        else if(jsObj.task == "deleteFilesAndPhotos")
			{
           showDeleteFilesAndPhotosStatus(myResults);  
			}	
        else if(jsObj.task == "deletePartyGallary")  
			{
           showDeleteGallary(myResults);  
			}
			
		else if(jsObj.task == "UpdatePartyGallary")  
			{
           updateGallaryDiv(myResults);  
			}		
		else if(jsObj.task == "UpdatePhotoGallary")  
			{
           updateGallaryStatus(myResults);  
			}	
		else if(jsObj.task == "updateFilesAndPhotos")  
			{
           updateFilesAndPhotosDiv(myResults,jsObj.fileId);  
			}		
		else if(jsObj.task == "updateProfileDiscription")
			{
           showDiscriptionUpdateStatus(myResults);  
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
			   clearOptionsListForSelectElmtId("scopeDiv");
               buildResults(myResults,"scopeDiv");
			}
			else if(jsObj.task == "getElectionType")
			{ 
			   clearOptionsListForSelectElmtId("electionType");
               buildResultsForElectType(myResults);
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
               showGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			
			else if(jsObj.task == "createVideoNewGallary")  
			{ 
               showVideoGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType=="News Gallary")
			{ 
			
				/*$('#existingFromText').attr({
				   id : this.id + '_' + 1 
				 });*/
               clearOptionsListForSelectElmtId('gallaryId');
			   createOptionsForSelectElmtId('gallaryId',myResults);
			   $("#gallaryId").prepend("<option value='0'>Select Gallary</option>");
			document.getElementById('gallaryId').value=0;

			}
			
			else if(jsObj.task == "getSource")
			{ 
               sourceObj = myResults;
			   clearOptionsListForSelectElmtId(jsObj.selectOptionId);
			   createOptionsForSelectElmtId(jsObj.selectOptionId,sourceObj);
			}
			else if(jsObj.task == "getLanguage")
			{ 
               languagesObj = myResults;
               clearOptionsListForSelectElmtId(jsObj.fillOptionId);
			   createOptionsForSelectElmtId(jsObj.fillOptionId,languagesObj);
			}
			else if(jsObj.task == "getNewsImportance")
			{ 
				
               clearOptionsListForSelectElmtId('newsimportance');
			   createOptionsForSelectElmtId('newsimportance',myResults);
			}
			
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType == "Video Gallary")
			{ 
				/*$('#existingFromText').attr({
				   id : this.id + '_' + 2 
				 });*/
               clearOptionsListForSelectElmtId('gallarySelectId');
			   createOptionsForSelectElmtId('gallarySelectId',myResults);
			   $("#gallarySelectId").prepend("<option value = '0'> Select Gallary</option>")
					document.getElementById('gallarySelectId').value = 0;
			}
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType == "Photo Gallary")
			{ 
               clearOptionsListForSelectElmtId('gallaryPhotoSelectId');
			   createOptionsForSelectElmtId('gallaryPhotoSelectId',myResults);
			   $("#gallaryPhotoSelectId").prepend("<option value = '0'> Select Gallary</option>")
					document.getElementById('gallaryPhotoSelectId').value = 0;
			}


			else if(jsObj.task == "partyDescriptionUpdate")
			{ 

               showPartyDescription(myResults);
			}
			else if(jsObj.task == "updateIndividualPhotoDetails")
			{ 
               showPhotoUpdateDetails(myResults);
			}
			else if(jsObj.task == "getAssmblParlElecYears")
			{ 
               createListData('assmblParlElecYearsSelect',myResults);
			}
			else if(jsObj.task == "candiadteVideoGallariesForUplaod")
			{
				 showUploadVideoStatus(myResults);
				
			}
			
     	}
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
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
function createListData(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	 if(elmtId != 'assmblParlElecYearsSelect')
     document.getElementById('gallarySelectId').value = gGallaryId;
}

function createListData(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	 if(elmtId != 'assmblParlElecYearsSelect')
     document.getElementById('gallaryPhotoSelectId').value = gGallaryId;
}

function showPhotoUpdateDetails(result)
{ 
  var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
	   document.getElementById('fileTitleId').value='';
	   document.getElementById('fileDescId').value='';
		str += '<font color="green"><b>Photo Updated Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}
function buildCandidatePhotoGallary(results)
{
	var str ='';

	
		str+='<div id="content" style="width:650px;">';
		
		//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str +='<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-top:7px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
		str += '</tr>';
		str += '</table>'
        str += '<center><div id="fileUploadErrorMsgDivId"></div></center>'; 
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
			
			str += '<td width="33%" class="imageStyle">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			if(results[i].path!=null)
			{
			 str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			else
			{
			  str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="images/icons/DefaultPhotoGalleryImage.jpg" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			str += '<tr><td><div><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			str+= '<tr><td><div><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
			str +='<tr>';
			str +='<table><tr>';
			str +='<td> <input type="button" class="buttonStyle" style="background: none repeat scroll 0 0 #0063dc;" value="Update" id= "updateGallary_'+i+'" onclick="updateGallary('+results[i].gallaryId+')"></td>';
			str +='<td style="padding-right:10px;"><input type="button" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value="Delete" id= "deleteGallary_'+i+'" onclick="deleteGallary('+results[i].gallaryId+')"></td></tr>';
			str +='</tr>';
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
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset"  style="width:540px;margin-left:auto;margin-right:auto;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="margin-left:52px;"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="margin-left:52px;margin-top:1px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-bottom:7px;"><tr><td><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td ><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';


	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;

}


function buildUploadPhotosDiv()
{
	var tempPartyId = 872; 
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	str += '<fieldset class="imgFieldset" style="width:540px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select onchange=" buildPhotoVisibility()" id="gallaryPhotoSelectId" name="gallaryId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="photofileTitleId" name="fileTitle" size="25" maxlength="50" style="margin-top:8px;"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="photofileDescId" name="fileDescription" cols="19" rows="3" name="requirement" style="margin-top:8px;"></textarea></td></tr>';
	str +='<tr><td><b><font color="#4B74C6">File Path<font class="requiredFont">*</font></font><b></td><td><input type="file" name="userImage" id="photofileId" style="margin-top:8px;"/></td>';
	str +='<td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMorePhotos()" title="Click here to add more Photos" alt=""Click here to add more images""/></td></tr>';
	str +='<tr><td colspan="3"><div id="addMorePhotosDiv"></div></td></tr>';
	/*str += '   <TR>';
	str += '     <td colspan="2">';
	str += '<table>';
	str += '   <tr>';
	str += '     <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '     <TD class="selectWidthPadd"><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="25"/>';
	str += '         <DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR></table>';*/
	str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextPhoto" class="dateField" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR></table>';

	str += '<div id="photoPublicRadioDiv" style="margin-left:52px;"><input type="radio" value="public" name="visibility" id="PhotopublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div id="photoPrivateRadioDiv" style="margin-left:52px;margin-top:1px;"><input type="radio" value="private" name="visibility" id="photoprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="photo_gallery">';
	//str+='<input type="radio" style="margin-left:57px;" onclick="otherProfiles(\'otherProPhotoDiv\',\'fromPartyProfile\',\'Photo Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProPhotoDiv" style="margin: 10px;"></div>'; 
	str += '<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-bottom:7px;"><tr><td><input type="button" id="uploadPhotoId" class="imageButton" value="Upload Photo" style="background-color:#57B731" onClick="uploadAFile()"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')"  style="background-color:#CF4740"></td></tr></table>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod('Photo Gallary');
}

	function createGallary(contentType,createOrUpdate)
    {
	var galName = document.getElementById('pGallaryNameId').value;
	var galDesc = document.getElementById('pGallaryDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;
	var partyId=872; 	
	var makeThis = 'true';
    var gallaryId ='';
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	//var galEle = document.getElementById('gallaryPhotoSelectId').value;
	var eFlag = false;
     if(createOrUpdate=='Update')
	{
	  gallaryId = document.getElementById("gallaryId").value;
	}
	var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallery Name Required<br>';
		eFlag = true;
	}

	/*if(galEle == 0)
		{
		str +='Select Gallary';
		eFlag = true;
		}*/
	if(galName.length >100)
	{
		str += 'Gallery Name should be less than 100 Characters<br>';
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
	
	galName = removeAllUnwantedCharacters(galName);
	galDesc = removeAllUnwantedCharacters(galDesc);

	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			partyId : partyId,
			contentType : contentType,
			gallaryId : gallaryId,
			createOrUpdate:createOrUpdate,
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showGallaryCreateMsg(result,createOrUpdate)
{
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
	clearGallaryFields();
		if(createOrUpdate=='Create')
		{
			if(result.exceptionMsg == null)
			 str += '<font color="green"><b>Gallery Created Successfully.</b>';
			else
			 str += '<font color="red"><b>Gallery Name is already exist.</b>'; 

		}
		else
		str += '<font color="green"><b>Gallery Updated Successfully.</b>';
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
		if(result.exceptionMsg == null)
			str += '<font color="green"><b>Gallery Created Successfully.</b>';
		else
			str += '<font color="red"><b>Gallery Name is already exist.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	document.getElementById('newsCateName').value='';
	document.getElementById('newsCateDesc').value='';
}
		
	
function getCompleteGallaries(gallaryId){
    gGallaryId=gallaryId;
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
   if(results.length>0)
   {
   str +='<table>';
   str +='   <tr>';
   str +='     <td><b style="color:green;font-size:14px;">'+results[0].gallaryName+'</b></td>'; 
   str +='   </tr>';
   str +='</table>';
   }
   str += '<center><div id="fileUploadErrorMsgDivId"></div></center>';
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
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
	 str += '<tr><td><table><tr><td><input type = "button" class="buttonStyle" style="background: none repeat scroll 0 0 #0063dc;" value = "Update" id= "updateFile_'+i+'" onclick="updateFilesAndPhotos('+results[i].fileId+')"></td>';
	 str += '<td> <input type = "button" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" id= "deleteFile_'+i+'" onclick="deleteFilesAndPhotos('+results[i].fileId+')"></td></tr></table>';
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


function getPartyGallariesForUplaod(contentType)
{
	var partyId=872;
	var jsObj =
		{ 
            partyId : partyId,
			contentType : contentType,
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
function getCandidateGallariesForUpdatePhoto()
{
var partyId=872;
	var jsObj =
		{ 
            partyId : partyId,
			contentType :'Photo Gallary',
			updatePhoto:'UpdatePhoto',
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
function uploadAFile()
{
	document.getElementById('uploadPhotoId');
	if(validateFileUpload())
	{
		disableButton('uploadPhotoId');

		/* var photoprivateRadioId = document.getElementById('photoprivateRadioId').checked;
		if(photoprivateRadioId == true)
		document.getElementById('photoprivateRadioId').checked= 'true';
		if(photoprivateRadioId == false)
		document.getElementById('PhotopublicRadioId').checked= 'true';*/

		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showUploadStatus(uploadResult);
					enableButton('uploadPhotoId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadFilesForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesAction.action',uploadHandler);
	}
	else
		return;
}
 function uploadNewsFromPartyPage()
{
	document.getElementById('uploadNewsBtnId');
	if(validateNewsFileUpload())
	{
		disableButton('uploadNewsBtnId');
	var newsprivateRadioId = document.getElementById('newsprivateRadioId').checked;
		if(newsprivateRadioId == true)
	document.getElementById('newsprivateRadioId').checked = 'true';
		if(newsprivateRadioId == false)
	document.getElementById('newsPublicRadioId').checked = 'true';
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus(uploadResult);	
					enableButton('uploadNewsBtnId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadNewsForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesAction.action',uploadHandler);
	}
	else
		return;
}
	
	function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{

	
	document.getElementById(id).disabled  = true;
}
function validateFileUpload()
{
	var fileTitle = document.getElementById('photofileTitleId').value;
	var fileDesc = document.getElementById('photofileDescId').value;
	var fileVal = document.getElementById("photofileId").value;
	var galId = document.getElementById("gallaryPhotoSelectId").value;
	var SpecialPageGalId = document.getElementById("uploadSpecialPageGalleryId");
	var spSelectId = document.getElementById("spSelectId");
	var spcheckboxIdElmt = document.getElementById("spcheckboxId");
	var canGalId = document.getElementById("uploadCandidateGalleryId");
	var candidateSelectId = document.getElementById("candidateSelectId");
	var ccheckboxIdElmt = document.getElementById("ccheckboxId");
	var fileDate = document.getElementById("existingFromTextPhoto").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters(fileTitle);
	fileDesc =  removeAllUnwantedCharacters(fileDesc);
	document.getElementById('photofileTitleId').value = fileTitle;
	document.getElementById('photofileDescId').value = fileDesc;

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '<font color="red">';
	if(fileDate.length == 0)
	{
		str +='File Date is Required';
		flag = false;
	}

	if(galId == 0)
	{
		str += 'Select Gallary';
		flag = false;
	}
	if(fileTitle.length == 0)
	{
		str += 'Photo Title Required.<br>';
		flag = false;
	}
	if(fileTitle.length > 50)
	{
		str += 'Photo Title Should not exceed 50 Characters.<br><br>';
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
	if(spcheckboxIdElmt!=null && spcheckboxIdElmt.checked)
	{
	   if(spSelectId !=null && spSelectId.value == 0)
	  {
		str += 'Select Special Page <br>';
		flag = false;
	  }
     else if(SpecialPageGalId !=null && SpecialPageGalId.value == 0)
	  {
		str += 'Select Special Page Gallery.<br>';
		flag = false;
	  }
	  
	}
	if(ccheckboxIdElmt!=null && ccheckboxIdElmt.checked)
	{
		   if(candidateSelectId !=null && candidateSelectId.value == 0)
		  {
			str += 'Select Candidate <br>';
			flag = false;
		  }
		 else if(canGalId !=null && canGalId.value == 0)
		  {
			str += 'Select Candidate Gallery.<br>';
			flag = false;
		  }
		  
		}
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
}

function validateNewsFileUpload()
{
	var fileTitle = document.getElementById('newsfileTitle').value;
	var fileDesc = document.getElementById('newsfileDescription').value;
	var fileVal = document.getElementById("newsfileId").value;
	var source = document.getElementById("source").value;
	var languageId = document.getElementById("language").value;
	var keywords = document.getElementById("keywords").value;
	var galEle = document.getElementById("gallaryId").value;
	var fileDate = document.getElementById("existingFromTextNews").value;
	var scope = document.getElementById("scopeDiv").value;
	
	//var pageNo = document.getElementById("pageno").value;
	//var pageNo = document.getElementById("newslength").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters1(fileTitle);	
	fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('newsfileTitle').value = fileTitle;
	document.getElementById('newsfileDescription').value = fileDesc;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';
	if(galEle == 0)
	{
		str += 'Select Gallary<br>';
		flag = false;
	}
	if(fileDate.length == 0)
	{
		str +='File Date is Required<br>';
		flag = false;
	}
	if(fileTitle.length == 0)
	{
		str += ' Title is Required.<br>';
		flag = false;
	}
	 $('.pageno').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' Page Number is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.pageno').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' Page Number must be Integer.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	   $('.newslength').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' News Length is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.newslength').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' News Length must be number.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	if(fileTitle.length >50)
	{
		str += 'Title should be less than 50 Characters<br>';
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
		str += 'File is Required.<br>';
		flag = false;
	}
	if(source.length == 0)
	{
		str += 'Source is Required.<br>';
		flag = false;
	}
	if(languageId.length == 0)
	{
		str += 'Language is Required.<br>';
		flag = false;
	}
	if(keywords.length >200)
	{
		str += 'Keywords should be less than 200 Characters<br>';
		flag = false;
	}
	if(scope == 0 )
	{
		str += 'Location Scope is Required.';
		flag = false;
	}
	if(scope == 2)
	{
		var stateVal=document.getElementById('stateDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.';
		flag = false;
		}
	}
	if(scope == 3)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.';
		flag = false;
		}
	}
	if(scope == 4)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.';
		flag = false;
		}
	}
	if(scope == 5)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.';
		flag = false;
		}
	}
	if(scope == 6 || scope == 8 || scope == 9)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		var villageVal=document.getElementById('villageDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.<br>';
		flag = false;
		}
		if(villageVal == 0)
		{
		str += 'Villiage Name is Required.';
		flag = false;
		}
	}
	if(scope == 7)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.';
		flag = false;
		}
		
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
function removeAllUnwantedCharacters1(str)
{
   var strng = str.replace(/[\\\%\&\#\"+"]/g," ");
  
   return replaceEnterKey(strng,"  ");
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
	$("#addMoreFilesDiv").html('');
	$("#otherProNewsDiv").html('');
	document.getElementById('newsfileTitle').value = '';
	document.getElementById('newsfileDescription').value = '';
	document.getElementById('keywords').value = '';
	//document.getElementById('existingFromText').value = '';
	document.getElementById('source').value = '';
	document.getElementById('newsfileId').value = '';
	//document.getElementById('publicRadioId').checked = true;
	document.getElementById('existingFromTextNews').value = '';
	document.getElementById('ImagenewsfileId').value = '';
	getScopes();
}
	
function clearUploadFileFields()
{
	$("#addMorePhotosDiv").html('');
	$("#otherProPhotoDiv").html('');
	document.getElementById('photofileTitleId').value = '';
	document.getElementById('photofileDescId').value = '';
	//document.getElementById('publicRadioId').checked = true;
	document.getElementById('photofileId').value = '';
	
	document.getElementById('existingFromTextPhoto').value = '';
}

function clearGallaryFields()
{
	
	document.getElementById('pGallaryNameId').value = '';
	document.getElementById('pGallaryDescId').value = '';
	//document.getElementById('publicRadioId').checked = true;
}
	
function formValidation()
{
	
		return false;
	
	
}
	
function showNewsGallaey()
{
  if(!formValidation()){
  document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'block';
  document.getElementById("videoGallaryDiv").innerHTML=''; 
  $("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
  buildCreateNewsCategory();
  }
}
function buildCreateNewsCategory()
{
   var str ='';
	str+='<div id="content" style="width:650px;">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;width:37%;margin-left:auto;margin-right:auto;margin-top:7px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:449px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table style="margin-left:48px;"><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">Make This Private</font></b></input></tr></td></table>';
	
	str += '<table align="center" style="margin-left:auto;width:34%;margin-right:auto;margin-bottom:7px;"><tr><td><input type="button" class="imageButton" value="Create Category" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';

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
	var partyId = 872;
	var makeThis = 'true';

	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';
	if(partyId.length <= 0)
	{
	  document.getElementById('alertMsg1').innerHTML='<font color="red">partyId is Required</font>';
	  eFlag = true;
	}

	if(newsCatrgoryName.length == 0)
	{
		str += 'Category Name is Required<br>';
		eFlag = true;
	}
	if(newsCatrgoryDesc.length == 0)
	{
		str += 'Description is Required<br>';
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
	
	newsCatrgoryName = removeAllUnwantedCharacters(newsCatrgoryName);
	newsCatrgoryDesc = removeAllUnwantedCharacters(newsCatrgoryDesc);

	var jsObj =
		{ 
            name : newsCatrgoryName,
		    desc : newsCatrgoryDesc,
			visibility : makeThis,
			createOrUpdate:'Create',
			partyId : partyId,
			contentType : 'News Gallary',
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
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
		 else if(divId=='electionType')
		   {
		     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Election Type";
		   }
		 else{
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
		else if(divId=="electionType")
		{
		option.value=results[i].candidateId;
		option.text=results[i].file;
		}
		else{
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
function buildResultsForElectType(results){

  var elmt = document.getElementById('electionType'); 
  var option1 = document.createElement('option');	
      option1.value='0';
	  option1.text='Select Election Type';	
	  try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
  for(var i=0 ;i<2 ;i++)
	{
	  var option = document.createElement('option');	
      option.value=results[i].candidateId;
	  option.text=results[i].file;		
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
function getStatesForPartyManifesto()
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
function buildAssmblParlElecYears(electionType)
{ 
     var str ='';
		 str +='<table style="margin-left:45px;">';
		 str +='  <tr>';
		 str +='	   <td class="tdWidth1" style="width:153px;">Election Year<font class="requiredFont">*</font></td>';
		 str +='	   <td class="selectWidthPadd"><select class="selectWidth" id="assmblParlElecYearsSelect" style="width:175px;text-align:left;" name="electionId"/></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("assmblParlElecYears").innerHTML = str; 
		 getAssmblParlElecYears(electionType);
}
function getAssmblParlElecYears(electionType)
{
    var stateId = "";
	if(document.getElementById("stateDiv") != null)
	{
	  var stateEle = document.getElementById("stateDiv");
	  stateId = stateEle.options[stateEle.selectedIndex].value;
	}
    var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAssmblParlElecYears",
			electionType:electionType,
			stateId:stateId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getElectionIdsAndYears.action?"+rparam;						
	 callAjax(jsObj,url);
}
function buildStatesForAssParl(type)
{   
    document.getElementById("assmblParlElecYears").innerHTML="";
    var str ='';
		 str +='<table style="margin-left:45px;">';
		 str +='  <tr>';
		 str +='	   <td class="tdWidth1" style="width:153px;">State<font class="requiredFont">*</font></td>';
		 str +='	   <td><select id="stateDiv" class="selectWidth" name="locationValue" onchange="buildAssmblParlElecYears(\''+type+'\');"/></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("assemblyStates").innerHTML = str;
		 getStatesForPartyManifesto(); 
}
 function getLocationsForPartyManifUpld(id){
  document.getElementById("assemblyStates").innerHTML = "";
 if(document.getElementById("assmblParlElecYears") != null)
   document.getElementById("assmblParlElecYears").innerHTML = "";
   document.getElementById("parlCounState").innerHTML = "";
     if(id==1)
	 {
	   var str ='';
		 str +='<table style="padding-left:203px;">';
		 str +='  <tr>';
		 str +='	   <td><input type="radio" name="assemblyRadio" id="CountryRadio" checked="checked" onclick="buildParlCountryYears();">Country</input></td>';
		 str +='	   <td><input type="radio" name="assemblyRadio" id="stateRadio" onclick="buildStatesForAssParl(\'Parliament\');">State</input></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("parlCounState").innerHTML = str;
	   buildAssmblParlElecYears('Parliament');
	   
	 }
	 if(id==2)
	 { 
	    document.getElementById("parlCounState").innerHTML ="";
        buildStatesForAssParl('Assembly');		 
	 }
 }
 function buildParlCountryYears()
 {
    document.getElementById("assemblyStates").innerHTML = "";
    if(document.getElementById("assmblParlElecYears") != null)
      document.getElementById("assmblParlElecYears").innerHTML = "";
    buildAssmblParlElecYears('Parliament');
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
  str +='	    <td class="tdWidth1" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" name="locationValue" style="width:175px;"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 102px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" name="locationValue" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 102px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 102px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
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
  str +='	   <td class="tdWidth" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv"   class="selectWidth" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
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
  str +='	   <td class="tdWidth" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv"  class="selectWidth" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv"  class="selectWidth" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Village/Ward/Division :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 102px;">Village/Ward/Division :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
}
function addMoreFiles()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+fileCount+'" size="25" /></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language<font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+fileCount+'" name="newsedition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+fileCount+'" name="pageno" class="pageno" size="25" maxlength="200" style="margin-top:8px;"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+fileCount+'" class="newslength" name="newslength" size="25" maxlength="200" style="margin-top:8px;"></input></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
	str += '   </tr>';
	str +='</table>';
	moreDivElmt.innerHTML = str;
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	getSource("filesourceId"+fileCount+"");
	getLanguage('sourceLangId'+fileCount+'');
	fileCount++;
} 
function  buildUploadNews()
{
   var tempPartyId = 872;
   var str ='';
	str+='<div id="content" style="width:650px;">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;width:37%;margin-left:auto;margin-right:auto;margin-top:7px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:504px;">';
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table  align="left" class="paddingCss"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';
	str += '<table style="margin-left:50px;">';
	str += '   <tr>';
	str += ' <td class="tdWidth1">Select Category</td><td class="selectWidthPadd"><select onchange="buildPartyNewsVisibility()" id="gallaryId" name="gallaryId" class="selectWidth" /></select></td>';
	str += '   </tr>';
    str += '   <tr>';
	str += '       <td class="tdWidth1">Title<font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="newsfileTitle" name="fileTitle" size="25" maxlength="50" style="margin-top:8px;"></input></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsfileDescription" cols="20" rows="3" name="fileDescription" style="margin-top:8px;"></textarea></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Keywords</td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" maxlength="200" style="margin-top:8px;"></input></td></tr>';
	str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextNews" class="dateField" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="source" name="fileSourceId" style="width:175px;margin-top:8px;"><option value="0">Select Source</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Language<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="language" name="sourceLanguageId" style="width:175px;margin-top:8px;"><option value="0">Select Language</option></select></td>';
	str += '   </tr>';
	str += '   </tr>';
	str += '       <td class="tdWidth1">Category<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="category" name="category" style="width:175px;margin-top:8px;"><option value="0">Select Category</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Importance<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsimportance" name="newsimportance" style="width:175px;margin-top:8px;"><option value="0">Select NewsImportance</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Edition<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition" name="newsedition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno" name="pageno" size="25" class="pageno" maxlength="200" style="margin-top:8px;"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength" name="newslength" class="newslength" size="25" maxlength="200" style="margin-top:8px;"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">File Path<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsfileId" size="25" style="margin-top:8px;"/></td>';
	str += '       <td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt=""Click here to add more images""/></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv"></div></td>';
	str += '   </tr>';
	str += '   </tr>';

    str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display:</td><td><input type="file" name="imageForDisplay" id="ImagenewsfileId" size="25" style="margin-top:8px;"/></td></tr>';

	str += '   <tr>';
	str += '       <td></td>';
	str += ' <td id="newsPublicRadioDiv"><input type="radio" value="public" name="visibility" id="newsPublicRadioId" checked="true"><b><font id="newsfontDiv" color="#4B74C6">Visible to Public Also</font></b></input></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td id="newsprivateRadioDiv"><input type="radio" value="private" name="visibility" id="newsprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope<font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';
	str += '</table>';

	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';
	//str+='<input type="radio" style="margin-left:55px;" onclick="otherProfiles(\'otherProNewsDiv\',\'fromPartyProfile\',\'News Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProNewsDiv" style="margin: 10px;"></div>'; 
	str += '<table align="center" style="margin-left:auto;width:31%;margin-right:auto;margin-bottom:7px;"><tr><td><input id="uploadNewsBtnId" type="button" class="imageButton" value="Upload News" style="background-color:#57B731" onClick="uploadNewsFromPartyPage()"></td><td><input id="uploadNewsBtnId" type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod("News Gallary");
	
	 getScopes();
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 getCategory();
}
function getCategory()
{
var jsObj =
		{ 
            time : timeST,
			task:"getCategory"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getNewsGalleryForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function getNewsImportance()
{
	var jsObj =
		{ 
            time : timeST,
			task:"getNewsImportance"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getNewsGalleryForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function clearDiv(divId)
{
  document.getElementById(divId).innerHTML = "";
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

$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});

buildCreateVideoGallaryDiv();

}
return;
}
	

function buildCreateVideoGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="width:35%;margin-top:7px;margin-left:auto;margin-right:auto;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:449px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="margin-left:50px;"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="margin-left:50px;margin-top:1px;"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table align="center" style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-bottom:7px;"><tr><td><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"      style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;

}

function buildUploadVideoDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="width:35%;margin-top:7px;margin-left:auto;margin-right:auto;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	str += '<fieldset class="imgFieldset" style="width:504px;">';
	str += '<h2 align="center">Upload A Video</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td>';
	str+='<div id="fileSuccessDiv"></div><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select onchange="buildPartyVedioVisibility()" id="gallarySelectId" name="gallarySelectId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="50" style="margin-top:8px;"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement" style="margin-top:8px;"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '<tr><td><b><font color="#4B74C6">Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path" name="path" size="25" maxlength="200" style="margin-top:8px;"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="200" style="margin-top:8px;"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Source</font><font class="requiredFont">*</font></b></td><td><select id="source" name="fileSourceId" style="width:175px;margin-top:8px;"><option value="0">Select Source</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Language</font><font class="requiredFont">*</font></b></td><td><select id="language" name="sourceLanguageId" style="width:175px;margin-top:8px;"><option value="0">Select Language</option></select></td><td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreVideos()" title="Click here to add more Videos" alt="Click here to add more images"/></td></tr>';
	str += '<tr><td colspan="3"><div id="addMoreVideosDiv"></div></td></tr>';
	str += '</table>';
	str += '<div id="vedioPublicDiv" style=" margin-left: 52px;"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div id="vedioPrivateDiv" style="margin-top:1px;margin-left:52px;"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	//str+='<input type="radio" style="margin-left:57px;" onclick="otherProfiles(\'otherProVideoDiv\',\'fromPartyProfile\',\'Video Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProVideoDiv" style="margin: 10px;"></div>'; 
	str += '<table align="center" style="margin:5px;margin-left:auto;width:26%;margin-right:auto;margin-bottom:7px;"><tr><td><input type="button" id="uploadVideoBtnId" class="imageButton" value="Upload Video" style="background-color:#57B731" onclick="uploadVideoGallary()"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"   style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod('Video Gallary');
	getSource("source");
	getLanguage("language");
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
	var partyId=872;
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
	
	galName = removeAllUnwantedCharacters(galName);
	galDesc = removeAllUnwantedCharacters(galDesc);

	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			partyId : partyId,
			contentType : contentType,
			createOrUpdate:'Create',
			task : "createVideoNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
	callAjax(jsObj,url);
}



function buildPartyVedioVisibility()
{

	var galEle = document.getElementById('gallarySelectId');
	var galId = galEle.options[galEle.selectedIndex].value;
	var jsObj =
	{
		gallaryId : galId,
			task : "partyVideoVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}



function buildPartyNewsVisibility()
{
	var galEle = document.getElementById('gallaryId');
	var galId = galEle.options[galEle.selectedIndex].value;

	var jsObj =
	{
		gallaryId : galId,
			task : "partyNewsVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}

function buildPhotoVisibility()
{
var galEle = document.getElementById('gallaryPhotoSelectId');
	var galId = galEle.options[galEle.selectedIndex].value;

	var jsObj =
	{
		gallaryId : galId,
		task : "partyPhotoVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}
function showVideoGallaryCreateMsg(result)
{
	
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		clearVideoGallaryFields();
		if(result.exceptionMsg == null)
			str += '<font color="green"><b>Gallery Created Successfully.</b>';
		else
			str += '<font color="red"><b>Gallery Name is already exist.</b>';
			
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}

function showUploadVideoStatus(result)
{
	var errorDivEle = document.getElementById('fileSuccessDiv');
	document.getElementById('uploadVideoBtnId');
	var str = '';

	if(result.resultCode == 0)
	{
		clearUploadVideoFields();
		enableButton('uploadVideoBtnId');
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
	//document.getElementById('vpublicRadioId').checked = true;
}

function clearUploadVideoFields()
{
	$("#addMoreVideosDiv").html('');
	$("#otherProVideoDiv").html('');
	document.getElementById('fileTitleId').value = '';
	document.getElementById('fileDescId').value = '';
	//document.getElementById('vpublicRadioId').checked = true;
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

document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';

$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});

profileDiscriptionDiv();
}
return;
}
 
function profileDiscriptionDiv()
{

	var str ='';
	str += '<div id="content" style="width:650px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:37%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Add The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Profile  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table align="center" style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:33%"><tr><td><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'discriptionDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("discriptionDiv").innerHTML = str;

} 
 function addDescriptionDiv()
 {
 var str ='';
	str += '<div id="content" style="width:650px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:37%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Add The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Profile  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table align="center" style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:33%"><tr><td><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'discriptionDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("discriptionDiv").innerHTML = str;
 }
 
 function updateDescriptionDiv()
 {	
   
	var jsObj =
		{ 
            partyId : 872,
			
		   	task : "partyDescriptionUpdate"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodAction.action?"+rparam;
	callAjax(jsObj,url); 
	
 }

function  showPartyDescription(myResults)
 {

	var listSize = myResults.length;
	sizeOfArray = listSize;
	var i;
	var str ='';
	str += '<div id="content" style="width:750px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table align="center" style="width:32%;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
	str += '<fieldset class="imgFieldset" style="margin-left:-16px;width:718px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Update The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileupdateUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Order No</font></b></td><td style="padding-left: 82px"><b><font color="#4B74C6">Description </font></b></td></tr>';
	for(i=0 ; i<listSize ; i++)
	{
	str += ' <tr><td style="padding-right:38px"><input type="text" id="orderNoId_'+i+'" value= "'+myResults[i].orderNo+'" size="5"></td>';
	str += ' <td style="padding-right: 29px"> <textarea id="descId_'+i+'" cols="25" rows="4" style="background:#ffffff !important;"> '+myResults[i].description+'</textarea> </td>';
	str += ' <td style="padding-right: 82px"> <input type = "button" id="delete_'+i+'" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" onClick="deleteProfileById('+myResults[i].partyProfileDescriptionId+')"></td></tr>';
	str += ' <input type="hidden" id="partyProfileDescriptionId_'+i+'" value="'+myResults[i].partyProfileDescriptionId+'">';
	}
	str += '</table>';
	str += '<table style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:23%"><tr><td><input type="button" class="imageButton" value="Update Discription" style="background-color:#57B731" onClick="updateProfileDiscription()"></td><td><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'discriptionDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	
	document.getElementById("discriptionDiv").innerHTML = str;
	
	}
function addProfileDiscription()
 {
   var fileDesc = document.getElementById('profileDescId').value;
   var partyId=872;
   var errorDivEle = document.getElementById('galErrorMsgDivId');
   var eFlag = false;

	var str = '<font color="red">';

	if(fileDesc.length == 0)
	{
		str += 'Profile Discription is Required<br>';
		eFlag = true;
	}
    str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	var jsObj =
		{ 
		    
            partyId : partyId,
			fileDesc : fileDesc,
		   	task : "saveDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;
	callAjax(jsObj,url);	
    
 }
 
 function deleteProfileById(profDescId)
 {

 var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
     		profDescId : profDescId,
		   	task : "deleteDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewPartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
	
 }
 function showDevelopmentActivity()
 {
   $("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
   $("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#manifestoId").css({"background":"none repeat scroll 0 0 #0063DC"});
 }
 function showDiscriptionStatus(myResult)  
 {
	var errorDivEle = document.getElementById('fileUploadErrorMsgDiv');
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
function removeAllUnwantedCharactersInArray(str)
{
   var strng = str.replace(/[\\\\%\&\#\"+"]/g," ");
   return replaceEnterKey(strng,"  ");
} 
function updateProfileDiscription()
{
	
	var partyId=872;
	var orderNoArr = [];
	var descriptionArr = [];
	var profDescIdArr = [];
	   for(i=0 ; i < sizeOfArray ; i++)
   		{
		
			orderNoArr.push(document.getElementById('orderNoId_'+i).value);
			profDescIdArr.push(removeAllUnwantedCharactersInArray(document.getElementById('partyProfileDescriptionId_'+i).value));
			descriptionArr.push(document.getElementById('descId_'+i).value);		
   		}
  
  var jsObj =
		{ 
		    partyId :partyId,
			orderNoArr : orderNoArr,
			descriptionArr : descriptionArr,
			profDescIdArr : profDescIdArr,
		   	task : "updateProfileDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createUpdateGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
    
}

function showDiscriptionUpdateStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileupdateUploadErrorMsgDiv');
	var str = '';

	if(myResult.resultCode == 0)
	{
		str += '<font color="green"><b>Profile Discription Updated Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}

function showDeleteStatus(myResult) 
{

var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(myResult.resultCode == 0)
	{
		updateDescriptionDiv();
		str += '<font color="green"><b>Profile Discription Deleted Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}

function showDeleteFilesAndPhotosStatus(myResult)
	{
	
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
		var str = '';

		if(myResult.resultCode == 0)
		{
			getCompleteGallaries(gGallaryId);
			str += '<font color="green"><b>Photo Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;
		
}

function showDeleteGallary(myResult)   
	{
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
		var str = '';

		if(myResult.resultCode == 0)
		{
			showPhotoGallary();
			str += '<font color="green"><b>Gallary Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;
		
}

function deleteFilesAndPhotos(fileId)
{
var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
		    gallaryId : gGallaryId,
     		fileId : fileId,
		   	task : "deleteFilesAndPhotos"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}

function deleteGallary(gallaryId)
{
var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
     		gallaryId : gallaryId,
		   	task : "deletePartyGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	//var url = "createNewGallaryAction.action?"+rparam;
	var url = "deletePartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}
function updateGallary(gallaryId)
 {
  var r=confirm("Do you want to update!");
 if (r==true)
    {
	//var candidateId=document.getElementById("candidateId").value;
	var partyId = 872;
	var jsObj =
		{ 
     		gallaryId : gallaryId,
			partyId : partyId,
		   	task : "UpdatePartyGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updatePartyPhotoGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
 
 }
 
 function updateGallaryDiv(myResults)
 {
    var str ='';
	document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
	
	str+='<div id="content" style="width:650px;">';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Update A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement">'+myResults.description+'</textarea></td></tr></table>';
	str +='<input type = "hidden" name ="gallaryId" id ="gallaryId" value ='+myResults.gallaryId+'>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table style="margin-left:auto;margin-right:auto;"><tr><td style="padding-right: 4px;"><input type="button" class="imageButton" value="Update Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Update\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="showPhotoGallary()" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("updateGallaryDiv").innerHTML = str;
	document.getElementById("pGallaryNameId").value=myResults.gallaryName;
	/*if(myResults.fileName1 != 'false')
	document.getElementById("privateRadioId").checked= true;
	else
	document.getElementById("publicRadioId").checked= true;*/
 }
 
 function  updateGallaryStatus(myResults)
    {
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(myResults.resultCode == 0)
	{
		showPhotoGallary();
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	

	}
	
	function updateFilesAndPhotos(fileId)
		{
			var r=confirm("Do you want to update the photo(or files)!");
			 if (r==true)
			 {
			 var jsObj =
					{ 
						gallaryId : gGallaryId,
						fileId : fileId,
						task : "updateFilesAndPhotos"
					};

				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "candidatePhotoGallaryUpdateAction.action?"+rparam;
				callAjax(jsObj,url);	
				}
		}
		
function updateFilesAndPhotosDiv(myResults,fileId)
     {
	 var str ='';
		str+='<div id="content" style="width:650px;">';
		str += '<fieldset class="imgFieldset" style="width:400px;">';
		str += '<h2 align="center">Update A Photo</h2>';
		str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
		str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
		str += '<table width="75%">';
		str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallaryPhotoSelectId" class="gallaryTitleVal" name="gallaryId" style="width:175px;"></select></td></tr>';
		str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="50"></td></tr>';
		str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
		str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement">'+myResults.fileDescription1+'</textarea></td></tr></table>';
		str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
		str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
		str += '<table><tr><td style="padding-right: 40px;"><input type="button" class="imageButton" value="Update Photo" style="background-color:#57B731" onClick="updatePhoto('+fileId+','+myResults.fileTypeId+')"></td><td style="padding-right: 41px;"><input type="button" class="imageButton" value="Cancel" onclick="getCompleteGallaries(gGallaryId)"  style="background-color:#CF4740"></td></tr></table>';
		str += '</fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
		document.getElementById('fileTitleId').value = myResults.fileTitle1;
		/*if(myResults.file != 'false')
		document.getElementById('privateRadioId').checked = true;
		else 
		document.getElementById('publicRadioId').checked = true;*/
		getCandidateGallariesForUpdatePhoto();
	 }	 
function updatePhoto(fileId,fileGallaryId)
{ 
  var galEle = document.getElementById('gallaryPhotoSelectId');
     var gallaryId = galEle.options[galEle.selectedIndex].value;
	 var title = document.getElementById('fileTitleId').value;
	var galDesc = document.getElementById('fileDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;	
	var makeThis = 'true';
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var eFlag = false;
	var str = '<font color="red">';

	if(title.length == 0)
	{
		str += 'Title is  Required<br>';
		eFlag = true;
	}
	if(title.length >50)
	{
		str += 'Title should be less than 50 Characters<br>';
		eFlag = true;
	}
	if(galDesc.length == 0)
	{
		str += 'Description is  Required<br>';
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

	/*if(isPublic)
		makeThis = 'false';*/
	
	var jsObj =
		{ 
            title : title,
		    desc : galDesc,
			visibility : makeThis,
             fileId:fileId,
			gallaryId : gallaryId,
			fileGallaryId:fileGallaryId,
			task : "updateIndividualPhotoDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}	 
	
 

	  function hideVedioVisibility(myResults)
	  {
		

		  if(myResults.isPrivate == "true"){

			  document.getElementById('vedioPublicDiv').style.display = 'none';

			document.getElementById('vedioPrivateDiv').style.display = 'block';
			document.getElementById('vprivateRadioId').checked = true;
		  }
		  else
		  {
			document.getElementById('vedioPublicDiv').style.display = 'block';
		   document.getElementById('vpublicRadioId').checked = true;

		   document.getElementById('vedioPrivateDiv').style.display = 'block';
		  }
	  }


	  function hideNewsVisibility(myResults)
	  {
		  if(myResults.isPrivate == "true")
		  {
			 document.getElementById('newsPublicRadioDiv').style.display = 'none';
			 
			document.getElementById('newsprivateRadioDiv').style.display = 'block';
			document.getElementById('newsprivateRadioId').checked = true;
		  }
		  else
		  {
			 document.getElementById('newsPublicRadioDiv').style.display = 'block';
			document.getElementById('newsPublicRadioId').checked = true;
		   
		   document.getElementById('newsprivateRadioDiv').style.display='block';
		   
		  }
	  }


	  function hidePhotoVisibility(myResults)
	  {
		  
		if(myResults.isPrivate == "true")
		  {
			 document.getElementById('photoPublicRadioDiv').style.display = 'none';

			document.getElementById('photoPrivateRadioDiv').style.display = 'block';
			document.getElementById('photoprivateRadioId').checked = true;
		  }
		  else
		  {
			  document.getElementById('photoPublicRadioDiv').style.display = 'block';
		   document.getElementById('PhotopublicRadioId').checked = true;
		   document.getElementById('photoPrivateRadioDiv').style.display = 'block';

		  }
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
		         <span style="text-decoration: none;" class="headerLabelSpan2"><center>Party Management Admin</center></span>
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
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:315px"><b><input type="button" class="buttonStyle" value="Photo Gallery" id="photoGalleryId" onClick="showPhotoGallary1()"></b></td>
				    <td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Video Gallery" id="videoGalleryId" onClick="showVideoGallaey1()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()"></b> </td>
					<!--<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Development Activity" id="developmentGalleryId" onClick="showDevelopmentActivity()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Profile Description" id="profileGalleryId" onClick="insertProfileDiscription()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Party Manifesto" id="manifestoId" onClick="uploadPartyManifesto()"></b> </td>-->
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

<script>
$(document).ready(function() {
	$(".dateField").live("click", function(){
       $(this).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
            changeYear: true,
			maxDate: new Date()
			
		}).datepicker("show");
     });
});


showNewsGallaey();
</script>
</body>
</html>