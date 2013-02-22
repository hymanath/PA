<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" href="styles/assets/css/problemmanagstyle.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script src="styles/assets/js/jquery.js"></script>

<script src="styles/assets/js/bootstrap-dropdown.js"></script>
<script src="styles/assets/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/rating/jquery.rateit.js" type="text/javascript"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link href="styles/rating/rateit.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js">
	</script>
	<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js">
	</script>

<link href="styles/rating/bigstars.css" rel="stylesheet" type="text/css">
    <link href="styles/rating/antenna.css" rel="stylesheet" type="text/css">
    <!-- syntax highlighter -->
    <link href="styles/rating/shCore.css" rel="stylesheet" type="text/css">
    <link href="styles/rating/shCoreDefault.css" rel="stylesheet" type="text/css">
	<title>Problem Details</title>
<style>
.commentSection{display:inline-block;clear:both;width:100%;position:relative;}
.commentSection a{position:absolute;right:4px;bottom:1px;width:70px;}
.button {
    background-attachment: scroll;
    background-color: #335291;
    background-image: none;
    background-position: 0 0;
    background-repeat: repeat;
	font-weight: bold;
    color: #FFFFFF;
}
#departmentassignid{
  width:88px;
  padding:2px;
}
table {
    font-size: inherit;
}
.requiredFont {
    color: red;
    margin-left: 5px;
}
.problemDetailsTable td, th {
    text-align: left;
	width: 252px;
}
#thId {
    color: #0000AA;
    font-family: verdana;
    font-weight: bold;
    text-align: left;
}
.textareaid {
    background-color: #FFFFFF !important;
    border: 1px solid #CCCCCC !important;
}
#postedcomments{
  margin-top: 30px;
}
  #postedon{
    float:left;
    margin-left:19px;
   }
   .btn-group > .dropdown-toggle {
    height: 28px;
  }
  .errStyle{
    background-color:#FFFFFF;
	font-weight:bold;
	width:100%;
	height:300px;
	text-align:center;
	padding-top:50px;
  }
  #imagesdiv{
    margin-bottom:5px;
  }
  h2 {
    font-size: 19px;
	line-height: 30px;
}

.clear-b{clear:both;}

#postedcomments ul{list-style-type:none;margin:1px;}
#postedcomments ul li{display:inline-block;width:98%;clear:both;margin:7px 0px; background:#f5f5f5;padding:5px;}
#postedcomments ul li p{width:98%;background:#fff;clear:both;display:table;padding:5px;border-radius:5px;}
#postedcomments ul li div{float:left;display:inline-block;margin:5px 5px;}
#postedcomments ul li .commentimage img{width:40px;height:40px;vertical-align:middle;}
.commentname{font-weight:bold;font-size:14px;color:#06ABEA;}
.commentdate{color:#888;font-size:10px;}

#userratingchangedDiv {
    border: 1px solid #D3D3D3;
    height: 109px;
    margin-left: 20px;
    width: 630px;
    padding-top: 7px;
}
.fontalign{
  font-family: verdana;font-size: 13px;
}
.imageButton {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    -moz-text-blink: none;
    -moz-text-decoration-color: -moz-use-text-color;
    -moz-text-decoration-line: none;
    -moz-text-decoration-style: solid;
    background-attachment: scroll;
    background-clip: border-box;
    background-color: #0063DC;
    background-image: none;
    background-origin: padding-box;
    background-position: 0 0;
    background-repeat: repeat;
    background-size: auto auto;
    border-bottom-color: -moz-use-text-color;
    border-bottom-left-radius: 4px;
    border-bottom-right-radius: 4px;
    border-bottom-style: none;
    border-bottom-width: medium;
    border-left-color-ltr-source: physical;
    border-left-color-rtl-source: physical;
    border-left-color-value: -moz-use-text-color;
    border-left-style-ltr-source: physical;
    border-left-style-rtl-source: physical;
    border-left-style-value: none;
    border-left-width-ltr-source: physical;
    border-left-width-rtl-source: physical;
    border-left-width-value: medium;
    border-right-color-ltr-source: physical;
    border-right-color-rtl-source: physical;
    border-right-color-value: -moz-use-text-color;
    border-right-style-ltr-source: physical;
    border-right-style-rtl-source: physical;
    border-right-style-value: none;
    border-right-width-ltr-source: physical;
    border-right-width-rtl-source: physical;
    border-right-width-value: medium;
    border-top-color: -moz-use-text-color;
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
    border-top-style: none;
    border-top-width: medium;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 6px;
    padding-right: 6px;
    padding-top: 4px;
    white-space: nowrap;
}
.errorMsgDivId{color:red;font-size:13px;}
.mainDiv{height: 300px;margin-left: auto;margin-right: auto;float: none;text-align: center;padding-top: 50px;font-weight: bold;background: #fff;font-size: 14px;font-family: arial;margin-bottom: 10px;width: 100%;}
.fontStyle{font-Size:13px;}
.heading{margin-bottom: 8px;margin-top: 10px;padding: 0;}
.selectWidth{width:170px;}
.errorClass{ background: none repeat scroll 0 0 whitesmoke;float: none;
 margin-bottom: -20px;margin-left: auto;margin-top: 15px;padding: 5px;text-align: center;
 width: 200px;}
.fileUploadDiv{margin-top:10px;}
.fileUploadDiv span{font-size:14px;}

#postedcomments
{
border: 1px solid #CCCCCC;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 2px 2px #DDDDDD;
    margin: 6px 5px;
    padding: 10px;
}
</style>

<script type="text/javascript">
var problemFilesArray = new Array();
var ratingtrue = true;
var cadreProblemDetails;
var problemDetailsInEdit = null;
<c:if test="${completeProblemDetailsVO.problemFiles != null}">
	<c:forEach var="file" items="${completeProblemDetailsVO.problemFiles}">	
	var fileObj={
						file : '${file.file}',
						title : '${file.title}',
						description:'${file.description}',
						pathoffile:'${file.pathOfFile}',
						candidateId : '${file.candidateId}',
						firstname:' ${file.name}',
						lastname : '${file.names}',
						problemFileId:'${file.problemFileId}',
					 };		
	
	problemFilesArray.push(fileObj);
	</c:forEach>
	</c:if>
	$(document).ready(function() {
   
	 buildInitalfiles(problemFilesArray);
	 
	});
	function buildInitalfiles(result){
		 if(result.length > 4)
		   document.getElementById("moreimags").style.display="block";
      var problemRelatedImagesElmt = document.getElementById("imagesdisplaydiv");
	  if(problemRelatedImagesElmt == null)
	    return;
      var str ='';
 
       var count = 0;
      for(var i in result)
      {
	  count = count+1;
	  if(count == 5)
	   break;
      var fileType = result[i].file.split(".");
      
      if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){
       
      if(fileType[(fileType.length-1)] == "pdf"  ){
      str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 				onclick="javascript:{openFile(\''+result[i].file+'\')}"/>';
      }
      else if(fileType[(fileType.length-1)] == 'text'){
      str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 				onclick="javascript:{openFile(\''+result[i].file+'\')}"/>';
      }
      else if(fileType[(fileType.length-1)].indexOf('word') != -1){
      str+= '<a href="'+result[i].file+'"><img alt="" src="images/doc_images/wordImage.png" height="100px" ></img></a>';
      }
      
      }
      else{
      
      str+= '<a rel="photo_gallery" href="'+result[i].file+'" title="'+result[i].description+'"><img alt="" src="'+result[i].file+'" height="100px" width="135px" /></a>';
      
      }
      
     str +='<input type="button" value="delete" id="buttonId" onclick="deleteProblemFile(\''+result[i].problemFileId+'\',this.id);">';

      }

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
	function showtotalImages(result){
	result = result.problemFiles;
	  $( "#gallaryOutrDiv" ).dialog({
		title:"Problem Related Files",
		autoOpen: true,
		show: "blind",
		width: 600,
		minHeight:300,
		modal: true,
		hide: "explode"
	});
       var problemRelatedImagesElmt = document.getElementById("gallaryinnerDiv");
      var str ='';
      str+='<div id="content">';
      str+='<table>'
      for(var i in result)
      {
      no_of_imagesPerRow = 4; 
      j = i;
      if(j++ % no_of_imagesPerRow == 0){
      str+= '<tr>';
      }
      var fileType = result[i].file.split(".");
      
      if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){
      
      
      str+= '<td><table><tr><td>';
      str+= '<a  href="javascript:{}" title="View file" ">';
      
      if(fileType[(fileType.length-1)] == "pdf"  ){
      str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 				onclick="javascript:{openFile(\''+result[i].file+'\')}"/>';
      }
      else if(fileType[(fileType.length-1)] == 'text'){
      str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 				onclick="javascript:{openFile(\''+result[i].file+'\')}"/>';
      }
      else if(fileType[(fileType.length-1)].indexOf('word') != -1){
      str+= '<a href="'+result[i].file+'"><img alt="" src="images/doc_images/wordImage.png" height="100px" ></img></a>';
      }
      
      str+= '</a></td>';
      str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+result[i].title+'</div></td>';
	  str+='<td><input type="button" value="delete" id="popupButton" onclick="deleteProblemFile(\''+result[i].problemFileId+'\',this.id);"></td></tr></table></td>';
      
      
      }
      else{
      str+= '<td><table><tr><td>';
      str+= '<a rel="more_photo_gallery" href="'+result[i].file+'" title="'+result[i].description+'"><img alt="" src="'+result[i].file+'" height="100px" width="135px" /></a></td>';
      str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+result[i].title+'</div></td>';
	  str+='<td><input type="button" value="delete" id="popupButton" onclick="deleteProblemFile(\''+result[i].problemFileId+'\',this.id);"></td></tr></table></td>';
      
      }
      
      if(j % no_of_imagesPerRow == 0){
      str+= '</tr>';
      }
     
      }
      str+= ' </table>';
      str+='</div>';
      problemRelatedImagesElmt.innerHTML = str;
      
      $("a[rel=more_photo_gallery]").fancybox({
      'transitionIn'		: 'none',
      'transitionOut'		: 'none',
      'titlePosition' 	: 'over',
      'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
      	return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
      }
      });

	}
	function openFile(filePath){
    window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}
  function showUploadFilePopUp(){
     $( "#uploadfileOutrDiv" ).dialog({
		title:"Upload Problem Related Files",
		autoOpen: true,
		show: "blind",
		width: 550,
		minHeight:300,
		modal: true,
		hide: "explode"
	});
	var str ='';
    str += '<div class="problemStatusDataDiv_main" id="fileUploadDiv" >';
	str += '<form name="uploadForm" action="postImagesAndFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadPicForm">'; 
	str +='  <DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px; width: 200%; display:none; padding-left: 261px;">File Saved Successfully...</DIV>';
	
	
	str +='<center><DIV id="errorMsgDivId" class="errorDiv"></DIV></center>';
	str += '<div>';
	str += '<div class="fileUploadDiv"> <span style="margin-right: 62px;">Title</span><span><input type="text" id="titleField" name="fileTitle" size="15"/></span></div>';
	str += '</div>';
	str+='<span id="alertMsg1"></span>';
	str += '<div>';
	str +='<div class="fileUploadDiv"><span style="margin-right: 13px;">Description</span>';
	str += '<span><textarea class="textareaid" name="fileDescription"  id="fileDescription" cols="20" rows="3"> </textarea></span></div>';
	str+='<span id="alertMsg2"></span>';
	str+='<span id="alertMsg3"></span>';
	str += '<div class="fileUploadDiv">';
	str += '<div><span style="margin-right: 8px;">Documents And Images</span>';
	str += '<input type="hidden" name="problemHistoryId" value="${completeProblemDetailsVO.problemId}">';
	str += '<span><input type="file" name="userImage" id="userImage"/></span></div>';
	str += ' <div style="margin-top: 20px; width: 120px; margin-left: 375px;"><input type="button" style="float:none;padding: 2px;" class="button" value="Upload" onclick="postFilesAndImages()" ></div>';
	
	str += '</div>';
	str += '</form>';
	
	str += '</div>';
	document.getElementById("uploadfileInnerDiv").innerHTML = str;
  }
  
  function postFilesAndImages()
{
	$("#errorMsgDivId").html('');
	$(".errorMsgDivId").html('');
	var str = '';
	var eFlag = false;
	var userImgVal = $("#userImage").val();
	var title = $("#titleField").val();
	var fileDesc = $("#fileDescription").val();
	
		if(alltrim(title) == '' && alltrim(fileDesc) == '')
		{
			str +='Title And Description is Required.';
			eFlag = true;
		}
		else if(alltrim(title) == '')
		{
		  str +='Title is Required.';
		  eFlag = true;
		}
		else if(alltrim(fileDesc) == '')
		{
		  str +='Description is Required.';
		  eFlag = true;
		}
		else if(userImgVal =='' || userImgVal == null || alltrim(userImgVal) =='')
		{
		str +='File is Required.';
		eFlag = true; 
		}
	
	if(eFlag)
	{
	$("#errorMsgDivId").addClass("errorMsgDivId").html(str);
	return false;
	}
		
   $( "#uploadfileOutrDiv" ).dialog("close");
   if(!uploadFormValidation()){
	var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				getMessage();				
			}
		};

	
	YAHOO.util.Connect.setForm('uploadPicForm',true);
	YAHOO.util.Connect.asyncRequest('POST', 'postImagesAndFilesAction.action', uploadHandler);
	}
	return;
   }
    function getMessage()
		{
			 getNewImagesDetails('initial');
		}
		
  function deleteDepartment()
{
    var r=confirm("Do you want to delete Department");
   if (r==true)
  {
  
  
	var jsObj =
		{
			pHistoryId:'${completeProblemDetailsVO.problemId}',
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
}
function getDepartmentAndActivities(){

}
function alltrim(str) {
                return str.replace(/^\s+|\s+$/g, '');
            }
function uploadFormValidation()
{
	
	var elmt1 = document.getElementById("titleField");
	var elmt2 = document.getElementById("fileDescription");
	var elmt3 = document.getElementById("userImage");
	
	var textFieldValue = elmt1.value;
	var textAreaValue = elmt2.value;
	var fileValue = elmt3.value;
	document.getElementById("alertMsg1").innerHTML ='';
   	document.getElementById("alertMsg2").innerHTML ='';
    document.getElementById("alertMsg3").innerHTML ='';


	if(alltrim(elmt1.value) ==''){
		document.getElementById("alertMsg1").innerHTML ='<font color="red">Please enter Title</font>';
		
		return true;
	}
	
	if(alltrim(elmt2.value) ==''){
		document.getElementById("alertMsg2").innerHTML ='<font color="red">Please enter Description</font>';
		
		return true;
	}

	if(alltrim(elmt3.value) ==''){
		document.getElementById("alertMsg3").innerHTML ='<font color="red">Please enter File</font>';
		
		return true;
	}
	
   }
   function setSelectedCadre(cadreId,cadreName)
{
	var cadreInputIdEle = document.getElementById("cadreInputId");
	cadreInputIdEle.value = cadreId;
    
	$( "#selectedCadreDivouter").dialog({
		title:"",
		autoOpen: true,
		show: "blind",
		width: 580,
		minHeight:250,
		modal: true,
		hide: "explode"
	});
	var cadreDetailsDivEle = document.getElementById("selectedCadreDiv");
	var cadreVar ='';
	cadreVar +='<table align="center">';
	cadreVar +='<tr><th>Selected Cadre is :</th>';
	cadreVar +='<th>';
	cadreVar += cadreName;
	cadreVar +='</th>';
	cadreVar +='<td><input type="button" style="width:108px;height:25px;" value="Show details" class="button" onclick="showCadreDetails(\''+cadreId+'\')"/></td>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="Proceed" class="button" onclick="addCadreToProblemAndSendingSMS(\''+cadreId+'\')"/></td>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="Cancel" class="button" onclick="clearCadreDiv()"/></td></tr>';
	cadreVar +='</table>';
	cadreDetailsDivEle.innerHTML = cadreVar;
}
function clearCadreDiv()
{
	$("#selectedCadreDivouter").dialog("close");
}
function addCadreToProblem(cadreId)
{
	var jsObj = {
				pHistoryId:'${completeProblemDetailsVO.problemId}',
				cadreId:cadreId,
				cadreClickType:cadreClickType,
				task:"addCadreToProblem"
			};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "addCadreToProblemAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function addCadreToProblemAndSendingSMS(cadreId)
{
	if(cadreClickType == 'Assign' || cadreClickType == 'Change')
	getCadreProblemDetailsForSms(cadreId,'${completeProblemDetailsVO.problemId}');
	else if(cadreClickType == 'Delete')
	addCadreToProblem(cadreId);
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
function showCadreDetails(cadreId)
{
	var showCadreDetailsBrowser = window.open("<s:url action="getCadreInfoAction.action"/>?windowTask=cadreInfoPopup&cadreId="+cadreId,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	showCadreDetailsBrowser.focus();
}
var cadreClickType;
function getCadreDetails(clickType)
{	
	cadreClickType = clickType; 
	if(clickType == "Delete")
	{ 
	    var r=confirm("Do you want to remove this Cadre");
       if (r==true)
       {
		addCadreToProblem(0);
	   }
		return;
	}
	var urlStr = "cadreSearchAction.action?windowTask=Search&addProblem=true";
	var cadreSearchForProblem = window.open(urlStr,"cadreSearchAndSMSPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");	
	cadreSearchForProblem.focus();
}

function handleDepartmentChange(type)
{	

	var elmt = document.getElementById("departmentPanel_content");
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


if(!elmt)
	return;

var str = '';
str += '<DIV id="problemAssigningDiv">';
str += '<TABLE>';
str += '	<tr>';
str += '		<th width="225px">';
str += '		<span class="fontStyle"><b>Problem Resolving Dept Scope </b></span>';
str += '		</th>';
str += '		<td>';
str += '		<select id="resolvingDeptScopeId" class="selectWidth" name="resolvingDeptScope" onchange="javascript:{getProblemDepartments(this.options[this.selectedIndex].value,\'getDepartmentCategories\');populateDeptLocations(this.options[this.selectedIndex].value);}">';
str += '<option value="0">Select Problem Scope</option>';
str += '</select></td>';
str += '	</tr>';
str += '	<tr>';
str += '		<th><span class="fontStyle"><b>Select Department</b></span></th>';
str += '		<td>';
str += '			<select id="deptId" class="selectWidth" name="dept">';
str += '				<option value="0">Select Dept</option>';
str += '			</select>';
str += '		</td>';
str += '	</tr>';
str += '	<tr id="deptAreaHeadId" style="display:none;">';
str += '		<th id="thId" colspan="4"><p class="heading"><u>Problem Resolving Dept Area</u></p></th>';
str += '	</tr>';
str += '	<tr id="row1" style="display:none;">';
str += '	<th><span class="fontStyle">Select State <font class="requiredFont"> * </font></span></th>';
str += '		<td>';
str += '<select id="StateId" class="selectWidth" name="state" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'districtsInState\',\'influencingPeopleReg\',\'DistrictId\', \'currentAdd\');showBusyImgWithId(\'stateajaxImgId\');">';
<c:forEach var="options" items="${statesListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str +='<img id="stateajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif" style="display:none;margin-right:10px;" />'; 

str += '		</td>';
str += '	</tr>';

str += '	<tr id="row2" style="display:none;">';
str += '		<th><span class="fontStyle">Select District <font class="requiredFont"> * </font></span></th>';
str += '		<td>';
str += '<select id="DistrictId" Class="selectWidth" name="district" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'influencingPeopleReg\',\'ConstituencyId\',\'currentAdd\');showBusyImgWithId(\'districtajaxImgId\');">';
<c:forEach var="options" items="${districtsListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str +='<img id="districtajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif"  style="display:none;float:right;"/>';
str += '		</td>';

str += '	</tr>';

str += '	<tr id="row3" style="display:none;">';
str += '		<th><span class="fontStyle">Select Constituency <font class="requiredFont"> * </font></span></th>';
str += '		<td>';
str += '<select id="ConstituencyId" class="selectWidth" name="constituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'influencingPeopleReg\',\'MandalId\',\'currentAdd\', \'null\');showBusyImgWithId(\'constituencyajaxImgId\');">';
<c:forEach var="options" items="${costituenciesListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str +='<img id="constituencyajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif" style="display:none;float:right;"/>'; 
str += '		</td>';
str += '	</tr>';

str += '	<tr id="row4" style="display:none;">';
str += '		<th><span class="fontStyle">Select Mandal/CORP/GMC <font class="requiredFont"> * </font></span></th>';
str += '		<td>';
str += '<select id="MandalId" class="selectWidth" name="mandal" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'influencingPeopleReg\',\'VillageId\',\'currentAdd\');">';
<c:forEach var="options" items="${mandalsListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '			</select>';
//str +='<img id="mandalajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif" style="display:none;float:right;"/>'; 

str += '		</td>';
str += '	</tr>';

str += '	<tr id="row5" style="display:none;">';

str += '		<th><span class="fontStyle">Select village <font class="requiredFont"> * </font><span class="fontStyle"></th>';
str += '		<td>';

str += '<select id="VillageId" class="selectWidth" name="village">';
<c:forEach var="options" items="${villagesListForProb}">
str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';

str +='<img id="villageajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif" style="display:none;float:right;"/>'; 
str += '		</td>';
str += '	</tr>';
str += '</table>';
str += '</DIV>';

str += '<div id="errorMsgDiv" style="text-align:right;padding:10px;color:red;">';
str += '</div>';

str += '<div id="departmentAssignedStatusDiv"></div>';
str += '<div style="text-align:right;padding:10px;">';
str += '<input type="button" id="departmentassignid" class="button" style="float:none;" value="Proceed" onclick="saveDepartmentToProblem(\''+type+'\')"/>';
str += '</div>';
str += '<div id="departmentAssignedStatusDiv"></div>'

elmt.innerHTML = str;
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
function saveDepartmentToProblem(type)
{
    $("#departmentPanel_main").dialog("close");
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
			pHistoryId:'${completeProblemDetailsVO.problemId}',
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
function changeProblemStatus(status)
{

   if(status == 'Select')
     return;
	var jsObj = {
					status:status,
					pHistoryId:'${completeProblemDetailsVO.problemId}',
					task:"changeProblemStatus"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProblemStatusAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function changeProbClassification(typeValue)
{
    if(status == 'Select')
     return;
	var jsObj = {
					typeValue:typeValue,
					pHistoryId:'${completeProblemDetailsVO.problemId}',
					status:'Change',
					task:"changeProbClassification"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeProbClassificationAjaxAction.action?"+rparam;						
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
							else if(jsObj.task == "changeProbClassification" )
							{
							    getNewProblemTypeDetails();
						
							}
							else if( jsObj.task == "addCadreToProblem" )
							{
							    getNewCadreDetails();
						
							}
							else if( jsObj.task == "changeDepartmentForProblem" )
							{
							    getNewDepartmentDetails();
						
							}
							else if(jsObj.task == "changeProblemStatus")
							{
							   getNewStatsDetails();
						
							}
							else if(jsObj.task == "getDepartmentCategories")
							{
								clearOptionsListForSelectElmtId('deptId');
								fillOptionsForSelectedElmt('deptId', myResults);
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
							if(jsObj.task =="saveProblemRatingDetails")
						    {
							 ratingtrue = true;
							getAvgProblemRating();
							rateWiseCountOfAProblem();
							document.getElementById("initialchangDiv").style.display = 'none';
							document.getElementById("afterchangeDiv").style.display = 'block';
							$('#rateitbyuserchange').rateit('value',jsObj.rating);
						  	  $("#rateitbyuserchange").rateit('readonly',!$("#rateitbyuserchange").rateit('readonly'));
						     }
						    else if(jsObj.task =="getAvgProblemRating")
						    {
							if(myResults != null && myResults != '' && myResults != 'null'){
							   if(document.getElementById("rateitmainrating")!= null)
							   $('#rateitmainrating').rateit('value',parseFloat(myResults.avgRating) );
							    if(document.getElementById("rateitavgall")!= null){
								 if(myResults.avgRating != null){
							       $('#rateitavgall').rateit('value',parseFloat(myResults.avgRating) );
								   document.getElementById("avgratingnumeric").innerHTML = myResults.avgRating; 
								  } 
								 else{
								    $('#rateitavgall').rateit('value',0);
									document.getElementById("avgratingnumeric").innerHTML = 0;
									}
									if(myResults.totalpeople != null){
									  document.getElementById("avgratpeplcount").innerHTML = myResults.totalpeople;
									}else{
									  document.getElementById("avgratpeplcount").innerHTML = 0;
									}
								}
							}
						    }
						   else if(jsObj.task =="rateWiseCountOfAProblem")
						   {
							  if(myResults != null){
							   var color = new Array();						     
								 color.push('progress-success');
								 color.push('progress-info');
								 color.push('progress-warning');
								 color.push('progress-danger');
								 color.push('progress-info');
							     var str ='';
								 for(var i in myResults){				 
								 str+= '<div style="width:100%"><div class="pull-left">'+myResults[i].ratingGiven+' Star </div> <div class="progress pull-left '+color[i]+'" style="margin-bottom:3px;margin-left:10px;width:100px;"><div class="bar" style="width: '+myResults[i].rating+'%;"></div></div><div style="margin-left:10px;" class="pull-left">'+myResults[i].totalpeople+'</div></div>';
								 }
								   if(document.getElementById("individualcountrate") != null)
								     document.getElementById("individualcountrate").innerHTML = str;
							  }
							
						   }
						   else if(jsObj.task =="getproblemcomments")
						   {
							buildcomments(myResults);
							
						   }else if(jsObj.task == "PostCommentsToProblem"){
						   document.getElementById("commenttext").value ='';
                              getcomments();
							  getNewActivityDetails();
						   }else if(jsObj.task == "getactivitydetails"){
						       buildActivitiesData(myResults);
						   }else if(jsObj.task == "getphotodetails"  && jsObj.type == "initial"){
						     buildphotodetailsData(myResults);
						   }else if(jsObj.task == "getphotodetails"  && jsObj.type == "final"){
						     buildphotodetailsDatafinal(myResults);
						   }else if(jsObj.task == "getotheractvdetails" && jsObj.type == "department"){
						     builddepartmentdetailsData(myResults);
						   }else if(jsObj.task == "getotheractvdetails" && jsObj.type == "problemtype"){
						     buildproblemtypeData(myResults);
						   }else if(jsObj.task == "getotheractvdetails" && jsObj.type == "cadre"){
						       buildcadredetailsData(myResults);
						   }else if(jsObj.task == "getstatustypedetails"){
						      buildstatusdetailsData(myResults);
						   }
						   else if(jsObj.task == "deleteProblemDetails")
							{
							  showDeleteStatus(myResults);
							}
							else if(jsObj.task == "getProblemDetailsForUpdate")
							{
								
								problemDetailsInEdit = myResults;
								showProblemDetailsForUpdate(myResults);
							}
							else if(jsObj.task == "updateProblemDetails")
							{
								showProblemUpdateStatus(myResults);
							}

							else if(jsObj.task == "getProblemtypes")
							{
								
								createOptionsForSelectElmtIdWithSelectOption("editProblemTypeId",myResults);
								if(problemDetailsInEdit[0].problemTypeId != null)
								document.getElementById("editProblemTypeId").value = problemDetailsInEdit[0].problemTypeId;
							}
							else if(jsObj.task == "saveAbusedComments")
							{
								showAbusedCommentsResults(myResults,jsObj);
							}
							else if(jsObj.task == "deleteProblemFile")
							{
								showProblemFileDeleteStatus(myResults,jsObj);
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
function showProblemFileDeleteStatus(result,jsObj)
{

	if(result.resultCode == 0 && jsObj.id == "buttonId")
	{
	getMessage();
	}
	else if(result.resultCode == 0 && jsObj.id == "popupButton")
	{
		getMessage();
		getNewImagesDetails('final');
	}
	
}
function showProblemDetailsForUpdate(myresults)
{

	
  $("#editProblemsOuter").dialog({ stack: false,
							    height: 340,
								width: 700,
								position:[150,120],								
								modal: true,
								title:'Edit Problem Details',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#editProblemsOuter").dialog();
	var str ='';

	
	var problemEditDiv = document.getElementById('editProblemsInner');
	str+='<div>';
	str += '<fieldset>';
	
	str += '<table id="editDiv"><tr><td><div style="padding-left:40px;" /></td></tr></table>';
	str += '<table>';
	for(i=0 ; i<myresults.length ; i++)
	{
    str += '   <tr>';
	str += '       <td class="tdWidth">Problem<font class="requiredFont">*</font></td>';
	str += '       <td><input type="text" id="problemTitleId" size="25" maxlength="100" value="'+myresults[i].problemTitle+'"></text></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth">Problem Description<font class="requiredFont">*</font></td>';
	str += '       <td><textarea style="background:#ffffff;margin-top:5px;" id="problemDescription" cols="25" rows="4">'+myResults[i].problemDesc+'</textarea></td></tr>';

	str += '	<tr><td colspan="2">';
	str+='<table><tr> <td class="tdWidth2" style="width:156px;">Existing From<font class="requiredFont"> * </font></td>';
	str += '	<td class="selectWidthPadd">';
	str += ' <TD class="selectWidthPadd"><input type="text" READONLY="READONLY" name ="reportedDate" id="existingFromText" size="25" style="margin-top:5px;" value="'+myResults[i].existingFrom+'"/>';
	str += '		<div class="yui-skin-sam"><div id="existingFromText_Div" class="tinyDateCal" style="font-size:12px;"></div></div>';
	str += '	</td>';				
	str += '	<td>';
	str += '<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(\'existingFromText_Div\',\'existingFromText\',new Date())"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';
	str += '	</td></table></td>';
	
	str += '   </tr>'
	 str += '   <tr>';
	   str += '       <td class="tdWidth">Problem Type<font class="requiredFont">*</font></td>';
	str += '  <td><select id="editProblemTypeId" style="width:222px;margin-top:5px;"  ></select></td>';
	   str += '   </tr>';
	str +='</table>';
	}
	str += '<div style="padding-left:223px;padding-top:10px;"> <input type="button" value="Update" onclick="updateProblem(${completeProblemDetailsVO.problemId})" class="imageButton"/></div>';
	str +='</fieldset>';
	problemEditDiv.innerHTML = str;
	
	getProblemtypes();
}

function bildDate(optionsList){

	
  
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
	
	}



function getProblemtypes()
{
	var jsObj = {
		time : new Date().getTime(),
		task : "getProblemtypes"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url); 
}

function showProblemUpdateStatus(result)
{
	var errorDivEle = document.getElementById('editDiv');
	var str = '';
	if(result.resultCode == 0)
	{
		str +='<font color="green">Problem Updated Successfully</font>';
		window.location.reload();
	}
	else
	{
		str +='<font color="green">error Occured try  again</font>';
	}
	errorDivEle.innerHTML = str;
}

function buildcomments(myResults){
  if(myResults != null && myResults.length >0){
    var str='';
	  str+='<ul>';
	for(var i in myResults){
     str +='<li>';
	 if(myResults[i].profileImg != null)
	   str+='<div class="commentimage"><img  width="45" height="45" src="pictures/profiles/'+myResults[i].profileImg+'" /> </div>';
	 else
	  str+='<div class="commentimage"><img  width="45" height="45" src="pictures/profiles/human.jpg" /> </div>';
	 str +='<span><input type="button" value="Abuse Comment"   class="btn btn-danger pull-right btn-mini" onclick="saveAbusedCommentsToProblem('+myResults[i].commentId+')" title="Click Here to Abuse the Comments" /></span>';
	 str+='<div><span class="commentname">'+myResults[i].firstName+' '+myResults[i].lastName+' </span><br><span class="commentdate">'+myResults[i].date+'</span><span id="showDivId'+myResults[i].commentId+'" style="color: red;float: right;margin-left: 47px;margin-top: -18px;"></span></div>';
	 str+='<p>'+myResults[i].comment+'</p>';
     str+='</li>';
	 }
	str+='</ul>';
	if(document.getElementById("postedcomments") != null)
	document.getElementById("postedcomments").innerHTML = str;
  }else{
     <s:if test="completeProblemDetailsVO.userStatus == 'notlogged' " >
	  if(document.getElementById("postedcomments") != null)
       document.getElementById("postedcomments").innerHTML = "<span style='margin-left:10px;'>No Comments Posted</span>";
     </s:if>
  }
  

}
function closewindow()
{
    $("#selectedCadreDivouter").dialog("destroy");
	$("#departmentPanel_main").dialog("destroy");
}
function showSMSResult(result)
{
	//hideAjaxImage('cadreSmsAjaxImgDiv');
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
function postCommentForProblem()
{
	var elmt = document.getElementById("commenttext");
	var textAreaValue = elmt.value;
	var errotext=document.getElementById("errormsgdiv");
	
	var str='';
	if(textAreaValue =='' || textAreaValue == null || alltrim(textAreaValue) ==''){
		str +='<font color="red">Please Enter comments</font>';
		errotext.style.display='block';
		errotext.innerHTML=str
		return;
	}
	errotext.style.display='none';
	var jsObj = {
					pHistoryId:'${completeProblemDetailsVO.problemId}',
					comments:removeAllUnwantedCharacters(textAreaValue),
					task:"PostCommentsToProblem"
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "postCommentsToProblemAjaxAction.action?"+rparam;						
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
	//showAjaxImage('cadreSmsAjaxImgDiv');
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
	str += '		<th width="80px" style="color:royalBlue">Mobile No</th>';
	str += '		<td><input type="text" id="mobileNoTextId" maxlength="12"></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<th width="80px" style="color:royalBlue">Message</th>';
	str += '        <td><textarea class="textareaid" rows="6" cols="45" id="messageTextId" theme="simple" name="message" maxlength="800"></textarea></td>';
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
function getcomments(){
	var problemId = '${completeProblemDetailsVO.problemId}';
	if(alltrim(problemId) != '' && alltrim(problemId).length > 0){
	 
	var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getproblemcomments"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCompleteProblemCommentsAction.action?"+rparam;						
	callAjax(jsObj,url);
}
}
function getNewImagesDetails(type){
  var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getphotodetails",
					type:type
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getNewDepartmentDetails(){
 var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getotheractvdetails",
					type:"department"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getNewProblemTypeDetails(){
 var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getotheractvdetails",
					type:"problemtype"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getNewCadreDetails(){
  var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getotheractvdetails",
					type:"cadre"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getNewStatsDetails(){
  var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getstatustypedetails"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getNewActivityDetails(){
   var jsObj = {
					problemId:'${completeProblemDetailsVO.problemId}',
					task:"getactivitydetails"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemStatusChangeAction.action?"+rparam;						
	callAjax(jsObj,url);
}
  function buildActivitiesData(result){
     var activitiesDiv = document.getElementById("problemActivitiesChgDiv");
	if(activitiesDiv == null || result == null || result.length == 0)
	return;
        var str ='';
		str+='<h2>Activities</h2>';
       if(result.problemRecentActivity == null || result.problemRecentActivity.length == 0 ){	
         str+='<div>No Problem Activities are taken place</div>';
      }
	  else{
	  str+='<div id="problemactivities">';
	  str+='<ul>';
	  for(var i in result.problemRecentActivity){
	  str+='<li class="span6">';
	  str+='  <div>'+result.problemRecentActivity[i].activityHapened+''; 
		
		if(result.problemRecentActivity[i].cadre != null && result.problemRecentActivity[i].activityHapened != 'Assigned cadre has been deleted' ){	
		str+='   <div>Cadre</div>';		
		str+='     <div>'+result.problemRecentActivity[i].cadre+'</div>';		 
		}
		if(result.problemRecentActivity[i].departmentOrganisation != null && result.problemRecentActivity[i].department != null && result.problemRecentActivity[i].deptLocation == null  && result.problemRecentActivity[i].activityHapened != 'Department has been deleted' ){		
			
		str+='	<div>Name ';
			if(result.problemRecentActivity[i].departmentOrganisation == '' || result.problemRecentActivity[i].departmentOrganisation == null){
		str+='		 Not Assigned';
			}
			else{
		str+='	 '+result.problemRecentActivity[i].departmentOrganisation+' ';
            }
		str+='     Category';
			if(result.problemRecentActivity[i].department == '' || result.problemRecentActivity[i].department == null ){
		str+='		Not Assigned ';
			}
			else{
		str+='		 '+result.problemRecentActivity[i].department+'';
            }
		str+='	<br>Location';
			if(result.problemRecentActivity[i].deptLocation == '' || result.problemRecentActivity[i].deptLocation == null){
		str+='		 Not Assigned';
			}
			else{
		str+='		'+result.problemRecentActivity[i].deptLocation+'';
			}
		str+='	</div>';
		}

		str+=' <small class="label label-info pull-right">  On '+result.problemRecentActivity[i].updatedDate+' </small>';
        str+='</div>';
		str+='</li>';
		}
		str+='</ul>';
		str+='</div>';
		}
		activitiesDiv.innerHTML = str;
 }
 function buildphotodetailsData(result){
   buildInitalfiles(result.problemFiles);
 }
 function buildphotodetailsDatafinal(result){
   showtotalImages(result);
 }
 function builddepartmentdetailsData(result){
    var deptDiv = document.getElementById("departmentChangeDiv");
	if(deptDiv == null || result == null || result.length == 0)
	return;
	
	var str ='';
		str+='<h3>Department</h3>';
		if( result.problemStatus.departmentOrganisation != null){
		str+='    <p>Currently Assigned To <b>'+result.problemStatus.departmentOrganisation+'</b>.</p>';
			if( result.modifyAccess == 'true' ){
		str+='	<div>';
		str+='    <a href="javascript:{}" onclick="handleDepartmentChange(\'Change\')" class="pull-left btn">Change</a>';
		str+='    <a href="javascript:{}" onclick="deleteDepartment()" class="pull-right btn">Remove</a>'; 		  
		 str+='  </div>';
		  }
		  }
		else{
		str+='    <p>Not Assigned To Any Department.</p>';
		if (result.modifyAccess == 'true'){
		str+='	<div>';
		str+='    <a href="javascript:{}" onclick="handleDepartmentChange(\'Assign\')" class="pull-left btn">Assign</a>';	  
		str+='   </div>';
		   }
		   }
 deptDiv.innerHTML = str;
 buildActivitiesData(result);
 }
 function buildproblemtypeData(result){
  var problemDiv = document.getElementById("problemTypeChangeDiv");
	if(problemDiv == null || result == null || result.length == 0)
	return;
	
	var str ='';
    str+='<h3>Problem Type</h3>';
		if(result.problemStatus.probClassification != null){
	str+='	   <p>Currenly Assigned To <b>'+result.problemStatus.probClassification+'</b>.</p>';
		   if(result.modifyAccess == 'true' ){
	str+='	   <div>';
	str+='	    Change to : <select id="problemtypeId" onchange="changeProbClassification(this.options[this.selectedIndex].text);" >';		   
	str+='	                <option value="SOCIAL">Social</option>';
	str+='					<option value="ECONOMICAL">Economical</option>';
	str+='					<option value="PERSONAL">Personal</option>';
	str+='	                </select>'; 
	str+='	   </div>';
		   }
		}
		if(result.problemStatus.probClassification == null){
	str+='	   <p>Not Assigned To Any Problem Type.</p>';
		   if(result.modifyAccess == 'true' ){
	str+='	   <div>';
	str+='	    Assign to : <select id="problemtypeId" onchange="changeProbClassification(this.options[this.selectedIndex].text);" >';
     str+='                   <option value="Select">Select</option>';		   
	str+='	                <option value="SOCIAL">Social</option>';
	str+='					<option value="ECONOMICAL">Economical</option>';
	str+='					<option value="PERSONAL">Personal</option>';
	str+='	                </select> '; 
	str+='	   </div>';
		   }
		   }
 problemDiv.innerHTML = str;
 if(document.getElementById("problemtypeId") != null)
  document.getElementById("problemtypeId").value= result.problemStatus.probClassification;
  buildActivitiesData(result);
 }
 function buildcadredetailsData(result){
  var cadreDiv = document.getElementById("cadreChangeDiv");
	if(cadreDiv == null || result == null || result.length == 0)
	return;
	
    var str ='';
		str+='<h3>Cadre</h3>';
		if(result.problemStatus.cadre != null){
		str+='<p>This Problem Has Been Assigned To <b>'+result.problemStatus.cadre+'</b>.</p>';
		  if(result.modifyAccess == 'true'){
		str+='  <div>';
		str+='    <a href="javascript:{}" onclick="getCadreDetails(\'Change\')" class="pull-left btn">Change</a>';
		 str+='   <a href="javascript:{}" onclick="getCadreDetails(\'Delete\')" class="pull-right btn">Remove</a> ';		  
		 str+='  </div>';
		  }
		}
		else{
		str+='<p>This Problem Has Not Been Assigned To Any Cadre.</p>';
		  if(result.modifyAccess == 'true'){
		str+='  <div>';
		 str+='   <a href="javascript:{}" onclick="getCadreDetails(\'Assign\')" class="pull-left btn">Assign</a>';		  
		 str+='  </div>';
		 }
		 }
	cadreDiv.innerHTML = str;
	buildActivitiesData(result);
 }
 function buildstatusdetailsData(result){
    var statusDiv = document.getElementById("StatusChangeDiv");
	if(statusDiv == null || result == null || result.length == 0)
	return;
	
	 var str ='';
		str+='<h3>Status:</h3>';
		str+='<p>This Problem Is Under <b>'+result.status+'</b> Stage.</p>';
        if(result.modifyAccess == 'true' ){	
	    str+='<div>';
		str+='   Move to : <select id="problemstatusId" onchange="changeProblemStatus(this.options[this.selectedIndex].text);" >';
        str+='                <option value="Select">Select</option>	';	   
		 str+='               <option value="PENDING">Pending</option>';
		str+='				<option value="PROGRESS">Progress</option>';
		str+='				<option value="FIXED">Fixed</option>';
		str+='             </select>';
		
					   
			
		str+='</div>';
		}
		statusDiv.innerHTML = str;
		if(document.getElementById("problemstatusId") != null)
		document.getElementById("problemstatusId").value=result.status;
		buildActivitiesData(result);
 }


 //delete

 function deleteProblemDetails()
 {
	var problemId = '${completeProblemDetailsVO.problemId}';
	var r=confirm("Do you want to delete This Problem");
    if (r==true)
    {
  	 if(alltrim(problemId) != '' && alltrim(problemId).length > 0){
	 var jsObj = {
		time       : new Date().getTime(),
		problemId  : problemId,
		task       : "deleteProblemDetails"

	};
	
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "deleteProblemDetailsAction.action?"+rparam;
	 callAjax(jsObj,url);
	}
 }
}

function showDeleteStatus(myResult)
{
	var errorDivEle = document.getElementById('errorMsgDivEle');
		var str = '';

		if(myResult.resultCode == 0)
		{
			//window.location.reload();
			$("#mainDiv").addClass("mainDiv").html("Problem Successfully Removed.");
			return;
			//str += '<font color="green"><b>Problem Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;
}

function saveAbusedCommentsToProblem(commentId)
{
	var jsObj = {
		time       : new Date().getTime(),
		commentId  : commentId,
		task       : "saveAbusedComments"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveProblemAbusedCommentsAction.action?"+rparam;
	 callAjax(jsObj,url);
	
}

function showAbusedCommentsResults(result,jsObj)
{

	var errorDivEle = document.getElementById('abusedErrorDiv');
		var str = '';
	if(result.resultCode == 0)
	{
		var cssObj = {    
					'font-weight' : 'bold',
					'color' : 'green'
				}
				var commentId = jsObj.commentId;
		$('#showDivId'+commentId).text("Your Request Sent Successfully").css(cssObj).show().delay(2000).fadeOut(400);
	}
	else if(result.resultCode == 1)
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	errorDivEle.innerHTML = str;
}
</script>
<script type="text/javascript">

var day = new Date().getDate();
var month = new Date().getMonth()+1;
var year = new Date().getFullYear();

var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var textBoxDivId;
function showDateCal(divId, textBoxId,pageDate) {
	
	textBoxDivId = textBoxId;
	var id = document.getElementById(divId);
	if (dateCalendar)
		dateCalendar.destroy();

	var navConfig = {
		strings : {
			month : "Choose Month",
			year : "Enter Year",
			submit : "OK",
			cancel : "Cancel",
			invalidYear : "Please enter a valid year"
		},
		monthFormat : YAHOO.widget.Calendar.SHORT,
		initialFocus : "year"
	};

	var dateCalendar = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		pagedate: pageDate,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}
function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById(textBoxDivId);
	txtDate1.value = year + "/" + month + "/" + day;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
}
</script>
</table>

<article class="background">

<div class="container" ><!-- Container Opening -->

<h2 class="h1header"> Your Locality Problems</h2>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.isProblemDel != 'true' ">
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess != 'true' ">
<s:if test="completeProblemDetailsVO.userStatus == 'both' && completeProblemDetailsVO.isTaken == 'true' && completeProblemDetailsVO.changedToPrivate == 'true' ">
  <div class="errStyle">We Are Sorry To Inform,This Problem Is Moved To Private State By The Posted User</div>
</s:if>
<s:else>
<div id="mainDiv">
<div class="span12" style="margin-top:25px;">

<div class="row">
<div class="span8 right-panel m5-left" style="background:#ffffff;position:relative;">
<c:if test="${completeProblemDetailsVO.isOwner && completeProblemDetailsVO.userStatus != 'NOT_LOGGED_IN'}">
	<c:if test="${completeProblemDetailsVO.userStatus == 'PARTY_ANALYST_USER' || completeProblemDetailsVO.userStatus != 'BOTH'}">

<div class="the-icons" style="position:absolute;top:-22px;right:5px;">
<a onclick=" openProblemEditForm('${completeProblemDetailsVO.problemId}')" class="btn btn-mini"><i class="icon-tag"></i> Edit</a>
<a onclick="deleteProblemDetails()" class="btn btn-mini"><i class="icon-remove"></i>Remove</a>
</div>

	<div style="width: 100px; margin-left: 499px; margin-top: 12px; padding-top: 0px; margin-bottom: 0px;">
	
		
</div>

<div id="problemEditDiv">
		<div id="editProblemsOuter">
		<div id="editProblemsInner"></div>
		</div></div>
 </c:if>
</c:if>
<div class="span1 pull-left m0-left" id="userdiv">
			<div class="userdesc">
			
			 <h5>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</h5>
			   <!-- <span>Friends(25)</span>
				<s:if test=" (completeProblemDetailsVO.userStatus == 'both' || completeProblemDetailsVO.userStatus == 'freeuser') && completeProblemDetailsVO.isConnectPeopleReq !='false' ">
			      <a href="#"><i class="icon-plus-sign"></i>Connect Now</a>
			    </s:if> -->
			 </div>
			 
          <a class="thumbnail" href="javascript:{}" >
             <s:if test="completeProblemDetailsVO.profileImg != null ">
                <img alt="" width="130" height="120" src="pictures/profiles/${completeProblemDetailsVO.profileImg}">
			 </s:if>
			  <s:if test="completeProblemDetailsVO.profileImg == null ">
                <img alt="" width="130" height="120" src="pictures/profiles/human.jpg">
			 </s:if>
			 
          </a>
		  <!--<a class="label">Posted by: <b>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</b></a>-->
</div>
<div class="span7 subheader-problem">
      <h2 class="span5 pull-left">${completeProblemDetailsVO.problemTitle} </h2>
<div id="errorMsgDivEle"></div>
<!--
<c:if test="${completeProblemDetailsVO.isOwner && completeProblemDetailsVO.userStatus != 'NOT_LOGGED_IN'}">
	<c:if test="${completeProblemDetailsVO.userStatus == 'PARTY_ANALYST_USER' || completeProblemDetailsVO.userStatus != 'BOTH'}">


	<div style="width: 100px; margin-left: 499px; margin-top: 12px; padding-top: 0px; margin-bottom: 0px;">
	<a href="javascript:{}" onclick="deleteProblemDetails()" title="Click Here to Delete the Problem">
		<img width="20px" height="20px;" style="text-decoration: none; border: 0px none; padding-top: 0px;margin-bottom: -2px" src="images/icons/delete.png">
	</a>
</div>

<div id="problemEditDiv" class="pull-right" style="margin-left: 0px; margin-right: 170px; margin-top: -22px;">
		<div id="editProblemsOuter">
		<div id="editProblemsInner"></div>
		</div><a onclick="getProblemDetails();"><img src="images/icons/edit.png" style="text-decoration: none; border: 0px none;"></a></div>
 </c:if>
</c:if>-->
		
       <s:if test="completeProblemDetailsVO.isPublic == 'true' ">
	   <div class="pull-right" style="margin-left: 0px; margin-right: 47px; margin-top: -22px;">
        <div class="btn-group dropup inline" style="display:inline-block;width:100px;">
          <button class="btn btn-primary"><i class="icon-share icon-white"></i> Share</button>
          <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary"><span class="caret"></span></button>
          <ul class="dropdown-menu pull-right">
            <li><div style="padding-left:15px;"><a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/completeProblemDetailsAction.action?problemId=${completeProblemDetailsVO.problemId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a></div></li>
			<li><div style="padding-left:30px;"><a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/completeProblemDetailsAction.action?problemId=${completeProblemDetailsVO.problemId}">
				Tweet</a></div>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script> </li>
			<li><div style="padding-left:30px;margin-top: 5px;">
			<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			 (function() {
			  var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
				 po.src = 'https://apis.google.com/js/plusone.js';
				 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
			 })();
			</script>
		</div></li>
			<li class="divider"></li>            
          </ul>
         </div>
		<div class="m-t10">
                     
						</div>
						<div class="rateit" id="rateitmainrating"></div>
        </div>
		
        </s:if>
		
		  <div class="row m-t10">
            <div class="span5"><h5>${completeProblemDetailsVO.problemCompleteLoc}</h5></div>
          </div>
          <div>
           <div id="postedon" style="margin-right: 24px;">Posted On : ${completeProblemDetailsVO.identifiedOn}</div>
           <div style="">Existing From : ${completeProblemDetailsVO.existingFrom}</div>
		   <div  style="float: right; margin-top: -20px; clear: both; margin-right: 29px;">Ref NO : ${completeProblemDetailsVO.referenceNo}</div>
          </div>
      </div>


<div class="row clear-b" style="margin: 5px; padding: 20px 5px 5px;">
		<div class="span pull-left">
                     <p>${completeProblemDetailsVO.problemDesc}</p>
					
	</div>
</div>	
<div id="imagesdiv" class="breadcrumb">
   <h3>Submissions  <div class="span2 pull-right">
          <s:if test="completeProblemDetailsVO.userStatus != 'notlogged' " >
		   <a href="javascript:{}" onclick="showUploadFilePopUp();" class="pull-right btn">Upload Files</a>
		   <div id="uploadfileOutrDiv"><div id="uploadfileInnerDiv"></div></div>
		  </s:if>
		</div></h3>		
		 <div id="imagesdisplaydiv" class="thumbnails">
		 </div>
		 <div id="moreimags" style="display:none;" class="pager">
		   <a href="javascript:{}" onclick="getNewImagesDetails('final');" class="pull-right">More ...</a>
		</div>
		<div id="gallaryOutrDiv"><div id="gallaryinnerDiv"></div></div>
</div>

<div class="tabbable tabs-left">
        <ul class="nav nav-tabs">
          <li class="active"><a data-toggle="tab" href="#lA">Details</a></li>
          <li><a data-toggle="tab" href="#lB">Activities</a></li>
          <!--<li><a data-toggle="tab" href="#lC">Problem Status</a></li>-->
        </ul>
        <div class="tab-content">
          <div id="lA" class="tab-pane active">
		    
            <p><ul class="thumbnails">
		<s:if test="completeProblemDetailsVO.problemStatus != null  && completeProblemDetailsVO.modifyAccess == 'true'  && (completeProblemDetailsVO.userStatus == 'customer' || completeProblemDetailsVO.userStatus == 'both') " >
		
        <li class="span6">
		<div id="departmentChangeDiv">
		<h3>Department</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.departmentOrganisation != null" >
		    <p>Currently Assigned To <b>${completeProblemDetailsVO.problemStatus.departmentOrganisation}</b>.</p>
			<s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
			<div>
		    <a href="javascript:{}" onclick="handleDepartmentChange('Change')" class="pull-left btn">Change</a>
		    <a href="javascript:{}" onclick="deleteDepartment()" class="pull-right btn">Remove</a> 		  
		   </div>
		   </s:if>
		</s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.departmentOrganisation == null" >
		    <p>Not Assigned To Any Department.</p>
			<s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
			<div>
		    <a href="javascript:{}" onclick="handleDepartmentChange('Assign')" class="pull-left btn">Assign</a>	  
		   </div>
		   </s:if>
		</s:if>
        </div>
        </li>
		
        <li class="span6">
		<div id="problemTypeChangeDiv">
		<h3>Problem Type</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.probClassification != null" >
		   <p>Currenly Assigned To <b>${completeProblemDetailsVO.problemStatus.probClassification}</b>.</p>
		   <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		   <div>
		    Change to : <select id="problemtypeId" onchange="changeProbClassification(this.options[this.selectedIndex].text);" >		   
		                <option value="SOCIAL">Social</option>
						<option value="ECONOMICAL">Economical</option>
						<option value="PERSONAL">Personal</option>
		                </select>
					 <script>
					   document.getElementById("problemtypeId").value="${completeProblemDetailsVO.problemStatus.probClassification}";
					 </script>		  
		   </div>
		   </s:if>
		</s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.probClassification == null" >
		   <p>Not Assigned To Any Problem Type.</p>
		   <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		   <div>
		    Assign to : <select id="problemtypeId" onchange="changeProbClassification(this.options[this.selectedIndex].text);" >
                        <option value="Select">Select</option>		   
		                <option value="SOCIAL">Social</option>
						<option value="ECONOMICAL">Economical</option>
						<option value="PERSONAL">Personal</option>
		                </select>  
		   </div>
		   </s:if>
		</s:if>
		</div>
        </li>
        <li class="span6">
         <div id="cadreChangeDiv">
		<h3>Cadre</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.cadre != null" >
		<p>This Problem Has Been Assigned To <b>${completeProblemDetailsVO.problemStatus.cadre}</b>.</p>
		  <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		  <div>
		    <a href="javascript:{}" onclick="getCadreDetails('Change')" class="pull-left btn">Change</a>
		    <a href="javascript:{}" onclick="getCadreDetails('Delete')" class="pull-right btn">Remove</a> 		  
		   </div>
		  </s:if>
		 </s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.cadre == null" >
		<p>This Problem Has Not Been Assigned To Any Cadre.</p>
		  <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		  <div>
		    <a href="javascript:{}" onclick="getCadreDetails('Assign')" class="pull-left btn">Assign</a>		  
		   </div>
		  </s:if>
		 </s:if>
		 </div>
        </li>
		</s:if>
        <li class="span6">
         <div id="StatusChangeDiv">
		<h3>Status:</h3>
		<p>This Problem Is Under <b>${completeProblemDetailsVO.status}</b> Stage.</p>
        <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >		
	    <div>
		   Move to : <select id="problemstatusId" onchange="changeProblemStatus(this.options[this.selectedIndex].text);" >
                        <option value="Select">Select</option>		   
		                <option value="PENDING">Pending</option>
						<option value="PROGRESS">Progress</option>
						<option value="FIXED">Fixed</option>
		             </select>
					 <script>
					   document.getElementById("problemstatusId").value="${completeProblemDetailsVO.status}";
					 </script>
		</div>
		</s:if>
		</div>
        </li>
		
      </ul></p>
          </div>
          <div id="lB" class="tab-pane">
		  <div id="problemActivitiesChgDiv">
		  <h2>Activities</h2>
		
       <s:if test="completeProblemDetailsVO.problemRecentActivity == null || completeProblemDetailsVO.problemRecentActivity.size() == 0 " >	
         <div>No Problem Activities are taken place</div>
      </s:if>
	  <s:else>
	  <div id="problemactivities">
	  <ul>
	  <s:iterator value="completeProblemDetailsVO.problemRecentActivity" var="recentActivities">
	  <li class="span6">
	    <div><s:property value="activityHapened"/> 
		
		<s:if test="%{#recentActivities.cadre != null && #recentActivities.activityHapened != 'Assigned cadre has been deleted' }" >	
		   <div>Cadre</div>		
		     <div><s:property value="cadre"/></div>		 
		</s:if>
		<s:if test="%{#recentActivities.departmentOrganisation != null && #recentActivities.department != null && #recentActivities.deptLocation == null && #recentActivities.activityHapened != 'Department has been deleted' }" >		
			
			<div>Name 
			<s:if test="%{#recentActivities.departmentOrganisation == '' || #recentActivities.departmentOrganisation == null}" >
				 Not Assigned
			</s:if>
			<s:else>
			 <s:property value="departmentOrganisation"/> 
            </s:else>
		     Category
			<s:if test="%{#recentActivities.department == '' || #recentActivities.department == null }" >
				Not Assigned 
			</s:if>
			<s:else>
				 <s:property value="department"/>
             </s:else>
			<br>Location
			<s:if test="%{#recentActivities.deptLocation == '' || #recentActivities.deptLocation == null}" >
				 Not Assigned
			</s:if>
			<s:else>
				<s:property value="deptLocation"/>
			</s:else>
			</div>
		</s:if>
		
		 <small class="label label-info pull-right">  On <s:property value="updatedDate"/> </small>
        </div>
		</li>
		</s:iterator>
		</ul>
		</div>
		</s:else>
		</div>
          </div>
         <!-- <div id="lC" class="tab-pane">
		  <h2>PROBLEM Status</h2>
            <p>Tabbed 3</p>
          </div>-->
        </div>
      </div>
<div id="userratingchangedDiv" >
    <s:if test="completeProblemDetailsVO.isAlreadyRated =='true' ">
	<div class="pull-left" style="margin-left:39px;"> <div class="fontalign" style="color: #06ABEA;">Problem Rating By You </div><div class="rateit" style="margin-top:5px;" id="rateitbyuser"></div></div>
	<script type="text/javascript">
	$(document).ready(function() {
	  $('#rateitbyuser').rateit('value',parseFloat(${completeProblemDetailsVO.ratingByyou}) );
	  $("#rateitbyuser").rateit('readonly',!$("#rateitbyuser").rateit('readonly'));
	});
	</script>
	</s:if>
	<s:if test="completeProblemDetailsVO.isAlreadyRated !='true' && completeProblemDetailsVO.userStatus != 'notlogged' ">
     <div id="initialchangDiv" class="pull-left" style="margin-left:8px;"><div class="fontalign" style="color: #06ABEA;">Are You Facing The Same Problem ? </div><div class="fontalign" style="color: #06ABEA;">Rate It</div>
	 <input type="range"  step="1" id="rateitbyuser" >
     <div class="rateit" onclick="saveRatingOfAProblem()" style="margin-top:5px;" id="ratingtest" data-rateit-backingfld="#rateitbyuser" data-rateit-resetable="false" style="max-width:0px;" data-rateit-ispreset="true" data-rateit-min="0" data-rateit-max="5"></div>	
      </div>
	  <div id="afterchangeDiv" class="pull-left" style="display:none;margin-left:39px;" >
	    <div class="fontalign" style="color: #06ABEA;">Problem Rating By You </div><div class="rateit" style="margin-top:5px;" id="rateitbyuserchange"></div>
	  </div>
	  <script type="text/javascript">

      $(document).ready(function() {
	     $(".rateit-selected").css("width","0px");
	   });
	</script>
	</s:if>
	<div class="pull-left" style="margin-left:40px;"><div class="fontalign" style="color: #06ABEA;" > Average Rating</div><div id="avgratingnumeric" style="margin-top:5px;margin-left:30px;"></div> <div style="margin-top:5px;" class="rateit" id="rateitavgall"></div><div id="avgratpeplcount" style="margin-top:5px;margin-left:30px;"></div></div>
	<div class="pull-left" style="margin-left:49px;"><div style="width:181px;" id="individualcountrate"></div></div>
</div>
			<s:if test="completeProblemDetailsVO.userStatus != 'notlogged' " >
        <div class="span8">
			<div id="errormsgdiv" class="errorClass"></div>
		<h3>Comments:</h3>
		 <div class="commentSection"><div>
		 <textarea class="textareaid" id="commenttext" style="width:104%;"></textarea></div><a href="javascript:{}" onclick="postCommentForProblem()" style="margin-bottom:-31px;margin-right:-44px;margin-top: 4px;width: 21px;" class="pull-right btn btn-info">Post</a></div>
		 <div id="abusedErrorDiv" style="padding-top: 0px; margin-left: 290px; margin-top: 36px; margin-bottom: -29px;"></div>
         <div id="postedcomments"></div>	
		</div>
		</s:if>
		<s:else>
		 <div class="span8">
		  <h3>Comments:</h3>
            <div id="postedcomments"></div>		
		</div>

		</s:else>		
</div> <!-- Left Panel _ Closed-->

<div class="span3 left-panel" >
<h3>Related Problems</h3>
<!-- Problesm Display Collection -->
<ul class="unstyled relatedproblem" style="width:200px;">
<s:if test="completeProblemDetailsVO.relatedProblems == null || completeProblemDetailsVO.relatedProblems.size() == 0 " >	
         <div>No Problem Activities are taken place</div>
      </s:if>
	  <s:else>
<s:iterator value="completeProblemDetailsVO.relatedProblems" var="relatedProblems">
            <li>
						<h5><a href="completeProblemDetailsAction.action?problemId=<s:property value='problemId'/>"><s:property value="problemTitle"/></a> </h5>
						<div>
							<div class="rateit" style="margin-top:5px;" id="rateit<s:property value='problemId'/>"></div>
							<h6><s:property value="problemCompleteLoc"/></h6>
						</div>
			</li>
    <script type="text/javascript">
	<s:if test="completeProblemDetailsVO.relatedProblems.rating == null " >
	$(document).ready(function() {
	  $("#rateit<s:property value='problemId'/>").rateit('value',0);
	  $("#rateit<s:property value='problemId'/>").rateit('readonly',!$("#rateit9<s:property value='problemId'/>").rateit('readonly'));
	  });
	</s:if>
	<s:else>
	$(document).ready(function() {
	  $("#rateit<s:property value='problemId'/>").rateit('value',parseFloat(<s:property value='rating'/>));
	  $("#rateit<s:property value='problemId'/>").rateit('readonly',!$("#rateit<s:property value='problemId'/>").rateit('readonly'));
	  });
	</s:else>
    </script>
</s:iterator>
</s:else>
	
</ul>
<!-- Problem Display Collection End-->



</div>

</div> <!-- Row [Span12]closed-->


</div>
<div id="departmentPanel_main"><div id="departmentPanel_content"></div></div>
</s:else>
</s:if>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess == 'true' ">
<div class="errStyle">You Didn't Have Access To View This Content </div>
</s:if>
</s:if>
<s:else>
  <div class="errStyle"> This Problem is not Existed. </div>
</s:else>
<div id="selectedCadreDivouter"><div id="selectedCadreDiv"></div></div>
<input type="hidden" id="cadreInputId" name="cadreId"/>
</div><!-- container Closing-->
</article>
</div><!-- main Div Close-->
<script>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.isProblemDel != 'true' ">
getcomments();
</s:if>
$('.dropdown-toggle').dropdown();
/*
$('#myTab a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
    });
/*var arr;
$(".icon-star-empty").hover(
      function () {
	  alert("test");
	     arr=$(this).parent().html();
        $(this).addClass("icon-star").removeClass("icon-star-empty").prevAll().addClass("icon-star").removeClass("icon-star-empty");
		}, 
      function () {
	    $(this).parent().html(arr);
        //$(this).removeClass("icon-star").addClass("icon-star-empty").prevAll().removeClass("icon-star").addClass("icon-star-empty");
      }
    );*/
	
	
	$(document).ready(function() {
	if(document.getElementById("rateitmainrating") != null){
	if('${completeProblemDetailsVO.problemId}' == 'null' || '${completeProblemDetailsVO.problemId}' == ''){
	    $("#rateitmainrating").rateit('value',0);
	    $("#rateitmainrating").rateit('readonly',!$("rateitmainrating").rateit('readonly'));
	  }
	  else{
	  $("#rateitmainrating").rateit('value',parseFloat(${completeProblemDetailsVO.rating}));
	  $("#rateitmainrating").rateit('readonly',!$("#rateitmainrating").rateit('readonly'));
	}
	}
	  });
	
	
	
	
 function saveRatingOfAProblem()
  {
    if(ratingtrue){
	ratingtrue = false;
    var problemId = '${completeProblemDetailsVO.problemId}';
	if(alltrim(problemId) != '' && alltrim(problemId).length > 0){
	var jsObj = {
		time       : new Date().getTime(),
		problemId  : problemId,
		rating     : $('#ratingtest').rateit('value'),
		task       : "saveProblemRatingDetails"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveProblemRatingDetailsAction.action?"+rparam;
	 callAjax(jsObj,url);
	}
  }
  }
  function getAvgProblemRating()
  {
var problemId = '${completeProblemDetailsVO.problemId}';
	if(alltrim(problemId) != '' && alltrim(problemId).length > 0){
	var jsObj = {
		time       : new Date().getTime(),
		problemId  : problemId,
		task       : "getAvgProblemRating"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getAvgProblemRatingAction.action?"+rparam;
	 callAjax(jsObj,url);
	}
  }

  function getProblemDetails()
  {
	var problemId = '${completeProblemDetailsVO.problemId}';
	var jsObj = {
					problemId :problemId,
					task      :"getProblemDetailsForUpdate"
				};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getProblemDetailsForUpdateAction.action?"+rparam;
	 callAjax(jsObj,url);
	
  }

  function updateProblem(problemId)
{
	
	var str = '';
	var errorDivEle = document.getElementById('editDiv');
	var problemId ='${completeProblemDetailsVO.problemId}' ;
	var problemTitle = document.getElementById('problemTitleId').value;
	var problemDesc = document.getElementById('problemDescription').value;
	var existingFrom = document.getElementById('existingFromText').value;
		
	var problemTypeEle =document.getElementById('editProblemTypeId');

	var problemType = problemTypeEle.options[problemTypeEle.selectedIndex].value;
	var eflag = true;
	if(problemTitle == '')
	{
		str +='<font color="red">Title is Required</font><br>';
		
		eflag = false;
		
	}
	 if(problemDesc == '')
	{
		str +='<font color="red">Description is Required</font>';
		
		eflag = false;
		
	}
	errorDivEle.innerHTML = str;
		
	if(!eflag)
		return;
	var jsObj ={
					problemId :problemId,
					problemtitle:problemTitle,
					problemDesc : problemDesc,
					existingFrom : existingFrom,
					problemType : problemType,
						
					task      :"updateProblemDetails"
				};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "updateProblemDetailsAction.action?"+rparam;
	 callAjax(jsObj,url);
	
  }

  function openProblemEditForm(id)
{
	
	var urlStr = "addNewProblemAction.action?problemId="+id+"&windowTask=update_existing";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();	
}


function rateWiseCountOfAProblem()
  {
   var problemId = '${completeProblemDetailsVO.problemId}';
	if(alltrim(problemId) != '' && alltrim(problemId).length > 0){
	var jsObj = {
		time       : new Date().getTime(),
		problemId  : problemId,
		task       : "rateWiseCountOfAProblem"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "rateWiseCountOfAProblemAction.action?"+rparam;
	 callAjax(jsObj,url);
	}
  }
  
 function deleteProblemFile(problemFileId,id)
 {
	var jsObj = {
		problemFileId  :problemFileId,
		id             :id,
		task       : "deleteProblemFile"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "deleteProblemFileAction.action?"+rparam;
	 callAjax(jsObj,url);

}
 
 
 
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
	   $("a[rel=more_photo_gallery]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
  });
  $(document).ready(function(){
	 if(document.getElementById("rateitavgall") != null)
	    $("#rateitavgall").rateit('readonly',!$("#rateitavgall").rateit('readonly'));
	 });
  $(document).ready(function(){
  if(document.getElementById("rateitbyuser") != null){
     $('#rateitbyuser').rateit('step',1);
	 $('#rateitbyuser').rateit('max', 5);
	 }
	 });

<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.isProblemDel != 'true' ">
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess != 'true' ">
getAvgProblemRating();
rateWiseCountOfAProblem();
</s:if>
</s:if>

</script>

