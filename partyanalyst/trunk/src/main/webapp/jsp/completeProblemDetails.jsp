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
<style>
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
</style>

<script type="text/javascript">
var problemFilesArray = new Array();
var cadreProblemDetails;
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
      
      str+= '<a rel="photo_gallery" href="'+result[i].file+'" title="'+result[i].description+'"><img alt="" src="'+result[i].file+'" height="100px" /></a>';
      
      }
      
      
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
      str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+result[i].title+'</div></td></tr></table></td>';
      
      
      }
      else{
      str+= '<td><table><tr><td>';
      str+= '<a rel="more_photo_gallery" href="'+result[i].file+'" title="'+result[i].description+'"><img alt="" src="'+result[i].file+'" height="100px" /></a></td>';
      str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+result[i].title+'</div></td></tr></table></td>';
      
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
	str += '<div> Title</div>';
	str += ' <div><input type="text" id="titleField" name="fileTitle" size="15"/></div>';
	str += '</div>';
	str+='<span id="alertMsg1"></span>';
	str += '<div>';
	str += '<div>Description </div>';
	str += '<div><textarea class="textareaid" name="fileDescription"  id="fileDescription" cols="20" rows="3"> </textarea></div>';
	str += '</div>';
	str+='<span id="alertMsg2"></span>';
	
	str+='<span id="alertMsg3"></span>';
	str += '<div>';
	str += '<div>Documents And Images</div>';
	str += '<input type="hidden" name="problemHistoryId" value="${completeProblemDetailsVO.problemId}">';
	str += '<div><input type="file" name="userImage" id="userImage"/></div>';
	str += ' <div><input type="button" style="float:none" class="button" value="Upload" onclick="postFilesAndImages()" ></div>';
	str += '</div>';
	str += '</form>';
	
	str += '</div>';
	document.getElementById("uploadfileInnerDiv").innerHTML = str;
  }
  
  function postFilesAndImages()
{
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
str += '		<b>Problem Resolving Dept Scope </b>';
str += '		</th>';
str += '		<td>';
str += '		<select id="resolvingDeptScopeId" class="selectWidth" name="resolvingDeptScope" onchange="javascript:{getProblemDepartments(this.options[this.selectedIndex].value,\'getDepartmentCategories\');populateDeptLocations(this.options[this.selectedIndex].value);}">';
str += '<option value="0">Select Problem Scope</option>';
str += '</select></td>';
str += '	</tr>';
str += '	<tr>';
str += '		<th><b>Select Department</b></th>';
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
str += '	<th>Select State <font class="requiredFont"> * </font></th>';
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
str += '		<th>Select District <font class="requiredFont"> * </font></th>';
str += '		<td>';
str += '<select id="DistrictId" cssClass="selectWidth" name="district" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'influencingPeopleReg\',\'ConstituencyId\',\'currentAdd\');showBusyImgWithId(\'districtajaxImgId\');">';
<c:forEach var="options" items="${districtsListForProb}">
	str += '		<option value="${options.id}">${options.name}</option>';
</c:forEach>
str += '		</select>';
str +='<img id="districtajaxImgId_ImgSpan" height="16" width="16" src="images/icons/search.gif"  style="display:none;float:right;"/>';
str += '		</td>';

str += '	</tr>';

str += '	<tr id="row3" style="display:none;">';
str += '		<th>Select Constituency <font class="requiredFont"> * </font></th>';
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
str += '		<th>Select Mandal/CORP/GMC <font class="requiredFont"> * </font></th>';
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

str += '		<th>Select village <font class="requiredFont"> * </font></th>';
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
							saveProblemRatingResults(myResults);
						  	
						     }
						    else if(jsObj.task =="getAvgProblemRating")
						    {
							getAvgProblemRatingResults(myResults);
							
						    }
						   else if(jsObj.task =="saveProblemRatingDetails")
						   {
							getRateWiseCountOfAProblem(myResults);
							
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
function buildcomments(myResults){
  if(myResults != null && myResults.length >0){
    var str='';
	  str+='<ul>';
	for(var i in myResults){
     str +='<li>';
	 str+='<div>'+myResults[i].comment+'';
     str+='<small class="label label-info pull-right"> by '+myResults[i].firstName+' '+myResults[i].lastName+' </small></div>';
     str+='</li>';
	 }
	str+='</ul>';
	document.getElementById("postedcomments").innerHTML = str;
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
	

	if(textAreaValue =='' || textAreaValue == null || alltrim(textAreaValue) ==''){
		return;
	}
	
	var jsObj = {
					pHistoryId:'${completeProblemDetailsVO.problemId}',
					comments:textAreaValue,
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
		if (result.problemRecentActivity[i].probClassification != null){
		str+='	to '+result.problemRecentActivity[i].probClassification+'';
		}
		if(result.problemRecentActivity[i].problemStatus != null){
		str+='   to '+result.problemRecentActivity[i].problemStatus+'';
		}
		str+=' <small class="label label-info pull-right"> ?  On '+result.problemRecentActivity[i].updatedDate+' </small>';
        str+='</div>';
		str+='</li>';
		}
		str+='</ul>';
		str+='</div>';
		}
		activitiesDiv.innerHTML = str;
 }
 function buildphotodetailsData(result){
   buildInitalfiles(result);
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
</script>
</table>

<article class="background">

<div class="container" ><!-- Container Opening -->

<h2 class="h1header"> Your Locality Problems</h2>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess != 'true' ">
<s:if test="completeProblemDetailsVO.userStatus == 'both' && completeProblemDetailsVO.isTaken == 'true' && completeProblemDetailsVO.changedToPrivate == 'true' ">
  <div class="errStyle">We Are Sorry To Inform,This Problem Is Moved To Private State By The Posted User</div>
</s:if>
<s:else>
<div class="span12" style="margin-top:25px;">

<div class="row">
<div class="span8 right-panel m5-left" style="background:#fff;">
<div class="span1 pull-left m0-left" id="userdiv">
			<div class="userdesc">
			
			 <h5>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</h5>
			   <!-- <span>Friends(25)</span>
				<s:if test=" (completeProblemDetailsVO.userStatus == 'both' || completeProblemDetailsVO.userStatus == 'freeuser') && completeProblemDetailsVO.isConnectPeopleReq !='false' ">
			      <a href="#"><i class="icon-plus-sign"></i>Connect Now</a>
			    </s:if> -->
			 </div>
			 
          <a class="thumbnail" href="#" >
             <s:if test="completeProblemDetailsVO.profileImg != null ">
                <img alt="" width="130" height="120" src="pictures/profiles/${completeProblemDetailsVO.profileImg}">
			 </s:if>
			  <s:if test="completeProblemDetailsVO.profileImg == null ">
                <img alt="" src="http://placehold.it/120x80">
			 </s:if>
			 
          </a>
		  <!--<a class="label">Posted by: <b>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</b></a>-->
</div>
<div class="span7 subheader-problem" >
      <h2 class="span5 pull-left">${completeProblemDetailsVO.problemTitle} </h2>
       <s:if test="completeProblemDetailsVO.isPublic == 'true' ">
	   <div class="pull-right">
        <div class="btn-group dropup inline" style="display:inline-block;width:100px;">
          <button class="btn btn-primary"><i class="icon-share icon-white"></i> Share</button>
          <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary"><span class="caret"></span></button>
          <ul class="dropdown-menu pull-right">
            <li><a href="#"><i class="icon-hand-right"></i> Facebook </a></li>
			<li><a href="#"><i class="icon-hand-right"></i> Twitter </a></li>
			<li><a href="#"><i class="icon-hand-right"></i> Google + </a></li>
			<li class="divider"></li>            
          </ul>
         </div>
		 <div class="m-t10">
                        <span >
						   
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span></div>
        </div>
        </s:if>
		
		  <div class="row m-t10">
            <div class="span5"><h5>${completeProblemDetailsVO.problemCompleteLoc}</h5></div>
          </div>
          <div>
           <div id="postedon">Posted On : ${completeProblemDetailsVO.identifiedOn}</div>
           <div style="float:right">Existing From : ${completeProblemDetailsVO.existingFrom}</div>
          </div>
      </div>


<div class="row m-t10">
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
		<s:if test="completeProblemDetailsVO.userStatus != 'notlogged' " >
        <li class="span6">

		<h3>Comments:</h3>
		 <div><div><textarea class="textareaid" id="commenttext"></textarea></div><div><a href="javascript:{}" onclick="postCommentForProblem()" class="pull-right btn btn-info">Post</a></div></div>
         <div id="postedcomments"></div>		
		</li>
		</s:if>
		<s:else>
		 <li class="span6">
		  <h3>Comments:</h3>
            <div id="postedcomments"></div>		
		</li>

		</s:else>
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
		<s:if test="%{#recentActivities.probClassification != null}" >
			to <s:property value="probClassification"/>
		</s:if>
		<s:if test="%{#recentActivities.problemStatus != null}" >
		   to <s:property value="problemStatus"/>
		</s:if>
		 <small class="label label-info pull-right"> ?  On <s:property value="updatedDate"/> </small>
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

					
</div> <!-- Left Panel _ Closed-->

<div class="span3 left-panel" >
<h3>Related Problems</h3>
<!-- Problesm Display Collection -->
<ul class="unstyled relatedproblem" style="width:200px;">
			<li>
						<h5>Electricity Problem</h5>
						<div>
							<span>
								<a href=# class="icon-star"></a>
								<a href=# class="icon-star"></a>
								<a href=# class="icon-star-empty"></a>
								<a href=# class="icon-star-empty"></a>
								<a href=# class="icon-star-empty"></a>
							</span>
							<h6>Hyderabad</h6>
						</div>
			</li>
			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>


			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>


			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>
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
<div id="selectedCadreDivouter"><div id="selectedCadreDiv"></div></div>
<input type="hidden" id="cadreInputId" name="cadreId"/>
</div><!-- container Closing-->
</article>

<script>
getcomments();
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
	
	
/* function saveRatingOfAProblem()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		rating     : 3,
		task       : "saveProblemRatingDetails"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveProblemRatingDetailsAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
  
  function getAvgProblemRating()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		task       : "getAvgProblemRating"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getAvgProblemRatingAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
function rateWiseCountOfAProblem()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		task       : "rateWiseCountOfAProblem"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "rateWiseCountOfAProblemAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
  */
 
 
 
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

</script>
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js">
	</script>
	<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js">
	</script>
