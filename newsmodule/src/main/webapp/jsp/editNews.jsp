<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> TDP News Portal </title>
<!--<script type="text/javascript" src="js/jquery.js"></script>-->
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>

  <link rel="stylesheet" href="js/ui/1.10.3/smoothness/jquery1.10.3-ui.css" />
  <!-- <link rel="stylesheet" href="js/ui/1.10.3/smoothness/jquery-ui.css" /> -->
  <script src="js/jquery-1.9.1.js"></script>
  <script src="js/ui/1.10.3/jquery-ui.js"></script>

  <!-- <link rel="stylesheet" href="js/ui/1.9.0-themes-base/jquery-ui.css" /> -->
    <script src="js/jquery-1.8.2.js"></script>
    <script src="js/ui/1.9.0-themes-base/jquery-ui.js"></script>

	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	 <SCRIPT type="text/javascript" src="js/specialPage/specialPage.js"></SCRIPT>
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
<!-- JQuery files (Start) -->

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries.js"></script>
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


<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/partyManagement.js"></script>

<!-- JQuery files (End) -->
<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- keywords -->
    <link rel="stylesheet" type="text/css" href="styles/autoSuggest.css"> 
	<script type="text/javascript" src="js/jquery.autoSuggest.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.minified.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.packed.js"></script>

<!-- keywords -->
<link  rel="stylesheet" type="text/css" href="styles/partyManagement/partyManagement.css"/>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect1.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="js/editNews.js"></script>

	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>

<style type="text/css">
textarea{
resize:none;
}
#newsedition0{
	width: 150px;
}
#ratingLevel{
width: 115px;
}
#sourceLangId0,#filesourceId0{
	width:125px;
}
#candidateListForPartyForNewsTo{
	width:260px;
}
#whomRatingId0{
	width:115px;
}
.partiesListForWhomeCls{
width: 120px;
}
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
 .enadu
{
font-family: eFont;
font-size:20px;
}

#candidateListForPartyImg{margin-left:300px;}

#profileManagementMainOuterDiv4 table,#reportsDiv table,#locationWiseNewsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#profileManagementMainOuterDiv4 table tr:nth-child(even),#reportsDiv table tr:nth-child(even),#locationWiseNewsDiv table tr:nth-child(even){background:#EdF5FF;}
#profileManagementMainOuterDiv4 table td,#reportsDiv table td,#locationWiseNewsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#profileManagementMainOuterDiv4 table th,#reportsDiv table th,#locationWiseNewsDiv table th{
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
#profileManagementMainOuterDiv4{
	font-family : arial;
	font-size: 13px;
  margin-top:-20px;
	padding: 10px 10px 10px 15px;
}

#reportsDiv,#locationWiseNewsDiv
{
	font-family : arial;
	font-size: 13px;
    margin-top: 20px;
	padding: 10px 10px 10px 15px;
}
#profileManagementMainOuterDiv4 table th a{
color:#333333;
}
.newsLinkCls{ color: #0088CC;}
#totalSelectedNewsCount{float: none;
    margin-left: auto;
    margin-right: auto;
    text-align: left;
    width: 901px;font-weight:bold;}
	/*#selectLevel{float: none;
    margin-left: auto;
    margin-right: auto;
	width: 901px;font-weight:bold;}*/
#selectedNewsCount{color:red;}
.createNewCandidate{width: 20px; height: 20px;cursor:pointer;}
.m_topN65{margin-top: -24px;}
form{
border:1px solid #C5C5C5;
}
#reportGenaratorSpanCLS{color:red;}
#newsReportBtnDiv{text-align: center;}
#partiesList,#candidateListForParty{
width: 330px;
}
#whoRating{
width: 110px;
}
#scopeDiv{
width: 150px;
}
</style>
<script>
var reportGallary = new Array();
var keywordGallary = new Array();
var districtsArr = new Array();
var assemblyConstiArr = new Array();
var parliamentConstiArr = new Array();
var sourceObj = null;
var languagesObj = null;
var locationScop1e = '${sessionScope.USER.accessType}';
var statteId = '${userDetailsVO[0].stateId}';
var distritcId =  '${userDetailsVO[0].districtId}';
var cosntiId =  '${userDetailsVO[0].constituencyId}';

<c:forEach var="districts" items="${districtsList1}">
	var districtList ={
	id:"${districts.id}",
	name:"${districts.name}"
	}
	districtsArr.push(districtList);
</c:forEach>

<c:forEach var="assemConstiList" items="${assemConstiList1}">
	var assemblyConstiList ={
	id:"${assemConstiList.id}",
	name:"${assemConstiList.name}"
	}
	assemblyConstiArr.push(assemblyConstiList);
	
</c:forEach>

<c:forEach var="parlConstiList" items="${parlConstiList1}">
	var parliamentConstiList ={
	id:"${parlConstiList.id}",
	name:"${parlConstiList.name}"
	}
	parliamentConstiArr.push(parliamentConstiList);
</c:forEach>


var keywordsArray = new Array();
<c:forEach var="keywords" items="${keywordsList}">
   var obj = {value:${keywords.id},
			name:"${keywords.name}"}

		keywordsArray.push(obj);
</c:forEach>

var partyArray = new Array();
<c:forEach var="party" items="${news.partyList}">
   var obj = {
			value:${party.id},
			name:"${party.name}"}

		partyArray.push(obj);
</c:forEach>



var selectedKeys = new Array();
//console.log("${news.sourceVOList[0].destinationVOList[0].fileKeywordList[0].name}");
<c:forEach var="list" items="${news.sourceVOList[0].destinationVOList}" varStatus="j">
var keyObj = {
		id:'${list.fileKeywordList[0].id}',
		name: '${list.fileKeywordList[0].name}'
	}
	selectedKeys.push(keyObj);
</c:forEach>

</script>
<script> 
$('document').ready(function(){

 $("#scopeDiv").prop("disabled", "disabled");
//candidate creation
$(".createNewCandidate").live("click",function(){	
$('#errorMsg1Div').html('');
$('#newCandidateName').val('');
$('#partySelectNewList').val(0);
$('#designationsList').val(0);
$('#locationId').val(0);
		$("#createCandidateDiv").dialog({
            modal: true,
            title: "<b>Create New Candidate</b>",
			width: 500,
            height: 350
           
        });
  
    $("#createCandidateInnerDiv").html('');
	
	var key = $(this).attr("key");
	var partyListId = $(this).attr("partyListId");
	
   getPartiesList("partySelectNewList",null);
   getDesignationList("designationsList");
   
});

$("#createCandidateId").live("click",function(){
    
	$("#errorMsgDiv").html('');
	var partyId = $("#partySelectNewList").val();
	var candidateName = $.trim($("#newCandidateName").val());
	var designationId = $("#designationsList").val();
	
	if(isValid(candidateName)){
		$('#errorMsg1Div').html('<b style="color:red;">Candidate Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
    if(partyId == 0)
	{
	  $("#errorMsgDiv").html("Please Select Party");
	  return;
	}
	if(candidateName.length == 0)
	{
	 $("#errorMsgDiv").html("Please Select Candidate");
	  return;
	}
	if(designationId == 0)
	{
	 $("#errorMsgDiv").html("Please Select Designation");
	  return;
	}
  var candidateListId = $(this).attr("key");
  var partyListId = $(this).attr("partyListId");
  var locationValue = "";
	if($('#locationId option:selected').val() == 1)
	{
		locationValue = $('#assembSelReportId option:selected').val();
	}
	else if($('#locationId option:selected').val() == 2)
	{
		locationValue = $('#parliamSelReportId option:selected').val();
	}
	var jsObj =
		{ 
            partyId : partyId,
			candidateName:candidateName,
			candidateListId:candidateListId,
			partyListId:partyListId,
			designationId:designationId,
			locationId : $('#locationId option:selected').val(),
			locationValue : locationValue,
			task:"saveCandidate"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveCandidateAndPartyAction.action?"+rparam;						
	callAjax(jsObj,url);
		
});

$(".candidateCloseImg").live("click",function(){
 var key = $(this).attr("key");
 
 $("#sourceCan"+key+"").removeClass("btn");
 $("#sourceCan"+key+"").html('');
 
 if(sourceCandidateIdsArray.indexOf(key) != -1)
 {
   sourceCandidateIdsArray = jQuery.removeFromArray(key, sourceCandidateIdsArray);
   sourceCandidatesArray.splice( $.inArray(key, sourceCandidatesArray), 1 );
 }

});

$(".destinationCandidateCloseImg").live("click",function(){
 var key = $(this).attr("key");

 $("#destinationCan"+key+"").removeClass("btn");
 $("#destinationCan"+key+"").html('');

 if(destinationCandidateIdsArray.indexOf(key) != -1)
 {
   destinationCandidateIdsArray = jQuery.removeFromArray(key, destinationCandidateIdsArray);
   destinationCandidatesArray.splice( $.inArray(key, destinationCandidatesArray), 1 );
 }

});
//$("#keywordId").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});  
});
</script>
</head>
<body>

<div id='newsGallaryDiv' class="divInfo">
<div id="uploadNewsFileErrorDiv" ></div>
<div id="keywordErrorMsgDivId" ></div>
<div id="keywordErrorMsgDivId1" ></div>

    <form name="uploadForm1" action="editUploadedFilesForPartyAndCandidatesKeywords.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">
	<!-- starting create candidate block-->
	<input type="hidden" name="fileId" value="${fileId}"/>
	<div id="createCandidateDiv" style="display:none;">

<div id="errorMsg1Div"> </div>
<table style="margin-top: 24px;"><tr>
	<td>Select Party</td>
	<td><select id="partySelectNewList">
	</select></td></tr>

	<tr><td>Candidate Name</td>
	<td><input type="text" id="newCandidateName"/></td></tr>
	<tr>
	<td>Designation</td>
	<td><select id="designationsList"></select></td>
	</tr>
	<tr>
	<td>Location</td>
	<td><select id="locationId" onChange="getTypeOfConstituency(this.value);"><option value=0>Select Location</option><option value=1>Assembly Constituency</option><option value=2>Parliment Constituency</option></select></td>
	</tr>  
	<tr style="display:none;" id="pcConstituencyRow">
	<td>Constituency</td>
	<td>
	<s:select name="parliamSelReport"  id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name"/></td>
	</tr>
	<tr style="display:none;" id="acConstituencyRow">
	<td>Constituency</td>
	<td>
	<s:select name="assembSelReport"  id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name"/></td>
	</tr>
</table>
<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>

</div>
	<!-- end create candidate block-->
	
	<!--1st block opening -->
		<div class="container"  align="left">
         <table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>
		
		<div class="span12"> 
		 <legend class="boxHeading text-center">Edit Selected News</legend> 

		<div class="well" style="border:1px solid #ADC248;width: 905px; margin-left: -10px;padding:5px 15px 15px 15px;" >
			<legend class="">&nbsp; Basic Information of News  </legend>
				<div class="row-fluid">       
						<div class="span6">
							<label><strong>News Title<span class="requiredFont">*</span></strong></label>
							<s:if test="news.titleCheckBox != null"> 				
							  <input type="text"  maxlength="800" size="25" name="fileTitle" value="${news.fileTitle}" placeholder="Enter Title Of the News" id="newsfileTitle" class="input-block-level enadu"/>							  
							  <span class="help-block"> <input type="checkbox" id="sourceTelugu" checked="checked" onclick="changeLanguage();" value="true" name="titleCheckBox">&nbsp;Please check if title is from eenadu.net</span> 				  
							</s:if>
							<s:else>
							  <input type="text"  maxlength="800" size="25" name="fileTitle" value="${news.fileTitle}" placeholder="Enter Title Of the News" id="newsfileTitle" class="input-block-level"/>
							  <span class="help-block"> <input type="checkbox" id="sourceTelugu" onclick="changeLanguage();" name="titleCheckBox" value="null"/>&nbsp;Please check if title is from eenadu.net</span> 				  
							</s:else>
								  
						</div>       
						<div class="span3 ">         
							<label><strong>News Date<span class="requiredFont">*</span><strong></strong></strong></label>  
							<input type="text" size="20" name="fileDate" readonly="true" class="dateField" placeholder="Select News Date" id="newsdatedatepic" style="cursor:text;" name="newsDatePicker"/>         
							
						</div>       
						<div class="span3 ">         
							<label><strong>News Importance<span class="requiredFont">*</span><strong></strong></strong></label>	
							<select name="newsimportance" id="newsimportance" value="${newsimportance}"><option value="3">High</option><option value="1">Low</option><option value="2">Medium</option></select>     
						</div>    
				</div> 
				<div class="span3" style="margin-left: 12px;"><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadioId" checked="true"><b><font id="newsfontDiv">Mark This News As Public</font></b></label></div>
<div class="span3"><label class="radio"><input type="radio" id="newsprivateRadioId" name="visibility" value="private"><b><font>Mark This News As Private</font></b></label></div>

				<div class="row-fluid">       
					<div class="span8 ">         
						<label><strong>News Description<span class="requiredFont">*</span><strong></strong></strong></label>         
				   
						<s:if test="news.fileDescriptionCheckBox != null"> 				
						  <textarea id="newsfileDescription" class="input-block-level enadu" cols="20" rows="2" name="fileDescription" maxlength="1800">${news.fileDescription}</textarea> 						 
						  <span class="help-block"> <input type="checkbox" id="sourceDescTelugu"  checked="checked" onclick="changeLanguage();" value="true" name="descCheckBox">&nbsp;Please check if description is from eenadu.net</span> 
						</s:if>
						<s:else>
							<textarea id="newsfileDescription" class="input-block-level" cols="20" rows="2" name="fileDescription" maxlength="1800">${news.fileDescription}</textarea> 
							<span class="help-block"> <input type="checkbox" id="sourceDescTelugu" onclick="changeLanguage();" name="descCheckBox" value="null">&nbsp;Please check if description is from eenadu.net</span> 
						</s:else>	
						<label><strong>News Synopsys : <span class="requiredFont">*</span><strong></strong></strong></label>         
					   <s:if test="news.synopsysCheckBox != null"> 				
						 	<textarea id="newsfileSynopsys" class="input-block-level enadu" cols="20" rows="2" name="newsSynopsysDesc" maxlength="1800">${news.synopsysDescription}</textarea> 
							<span class="help-block"> <input type="checkbox" id="synopsysDescTelugu" onclick="changeLanguage();" value="true" name="synopsysCheckBox" checked="checked">&nbsp;Please check if description is from eenadu.net</span> 
						</s:if>
						
						<s:else>
							<textarea id="newsfileSynopsys" class="input-block-level" cols="20" rows="2" name="newsSynopsysDesc" maxlength="1800">${news.synopsysDescription}</textarea> 
							<span class="help-block"> <input type="checkbox" id="synopsysDescTelugu" onclick="changeLanguage();" value="null" name="synopsysCheckBox">&nbsp;Please check if description is from eenadu.net</span> 
						</s:else>
							
					</div>
					
					<div class="span4 ">      
						<label><strong>Image To Display</strong></label>
						 <input type="file" id="imgToDisplay0" onchange="imgToDisplayRemove();" class="m_top10 newsFile1" name="imageForDisplay" style="width: 225px;"/>
						 <input type="hidden" id="imgToDisplayDeleted0" name="imgToDisplayDeleted" value="false"/>
						 
						 <s:if test="news.defaultImg != null"> 
							<span id="imgToDisplayRemove0"><span class="icon-zoom-in" style="cursor: pointer;" title="Click Here To View Existing Image" onclick="zoomSelectedImg('${news.defaultImg}');"></span>
							</span>
						</s:if>
							<span class="icon-remove" style="cursor: pointer;" title="Click Here To Delete Existing Image" onclick="clearExistingImg('imgToDisplay0','imgToDisplayRemove0');"></span>
							
							
							<div>( .jpeg or .jpg or .png or .gif formats only)</div>
					</div>  

					
				</div>
					
		</div>
	</div>
	
	</div> 
	<!--1st block closed -->
	
	<!--2nd block opening -->
	<div class="container" align="left">

	<div class="row-fluid"> 

	<div class="sapn12"> 
		<div id="myID" style="border:1px solid #ADC248;position: inherit; border-radius:5px; padding:5px 15px 15px 15px;width:906px;">   
		         <legend>From - Who</legend>
		   <div id="whoTalkedMainDIV">
			
	<s:iterator value="news.sourceVOList[0].sourceVOList" status="status">

			<input type="hidden" class="sources" name="candidatePartyNewsVOList.sourceVOList[${status.index}].candidatePartyFileId" value="${news.sourceVOList[0].sourceVOList[status.index].candidatePartyFileId}"/>
						
			  <div style="margin-left: 0px;margin-top:15px;" class="row alert alert-warning" id="whocandidate${status.index}">
					<div class="span5 well well-small " style="width: 300px;">
						<label style="margin-bottom: -10px;"><strong>Select Party</strong></label><span id="errDiv11" style="margin-top: -25px; color: red; margin-left: 100px; margin-bottom: 9px;" ></span>
						<s:select class="input-block-level" id="partiesList%{#status.index}" list="news.partyList" listKey="id" listValue="name" name="candidatePartyNewsVOList.sourceVOList[%{#status.index}].partyId" onchange="getCandidatesListByPartyId(this.value,'candidateListForParty%{#status.index}',11)" value="news.sourceVOList[0].sourceVOList[#status.index].partyId"/>
						<img src="images/search.jpg" style="display:none;" id="candidateListForPartyImg" />
						
					</div>

					<div class="span5 well well-small" style="width: 345px;">
						<label><strong>Select Candidate</strong></label>						
						<s:if test="#status.index == 0">
						<div class="btn btn-mini pull-right " style="margin-top: -24px; left: 390px; position: relative; float: left; margin-left: -140px;"> <a onclick="buildSearchDiv(11,${status.index});"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>
						</s:if>
						<s:if test="#status.index > 0">
						<div class="btn btn-mini pull-right " style="margin-top: -24px; left: 390px; position: relative; float: left; margin-left: -140px;"> <a onclick="buildSearchDiv(3${status.index},${status.index});"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>
						</s:if>
						
						
						<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesList" key="candidateListForParty" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>
						
						
						<s:select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList[%{#status.index}].candidateId" id="candidateListForParty%{#status.index}" list="news.sourceVOList[0].sourceVOList[#status.index].canidateList" listKey="id" listValue="name" value="news.sourceVOList[0].sourceVOList[#status.index].candidateId"/>

					</div>
					<div class="span2 well well-small"  style="width: 130px;">
				
					   <label><strong>Rating</strong></label>
					   <s:select class="input-block-level" id="whoRating" name="candidatePartyNewsVOList.sourceVOList[%{#status.index}].benefitId" value="news.sourceVOList[0].sourceVOList[#status.index].benefitId" list="news.benefitsOptionList" listKey="id" listValue="name">						 
					   </s:select>
					</div>
					  
					   <s:if test="#status.index > 0">
					           <div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <span id="deleteForWho"><a href="javascript:void(0);" onclick="deletethisDiv('whocandidate${status.index}','who',${news.sourceVOList[0].sourceVOList[status.index].candidatePartyFileId});" class="btn" ><i class="icon-trash"></i> Delete This Block</a>   </span> </p></div>	
					   </s:if>
					   
				</div>
				<s:if test="#status.index==news.sourceVOList[0].sourceVOList.size()-1">
				</div>
					<div class=" well well-small" style="margin-bottom: 0px;" id="addNewFromDivBtn"> 
					<a class="btn btn-danger" onclick="addNewFrom(${status.index});" href="javascript:void(0);">Click to add another From - Who</a>
					</div>
				</s:if>
			</s:iterator>
			<div id="deletedSourceCandidatePartyDivForWho"></div> 
		
		<legend>To - Whom</legend>	  

	<s:iterator value="news.sourceVOList[0].destinationVOList" var="whosDetails" status="stat">
	<input type="hidden" class="destinations" name="candidatePartyNewsVOList.destinationVOList[${stat.index}].candidatePartyFileId" value="${news.sourceVOList[0].destinationVOList[stat.index].candidatePartyFileId}"/>

	<div class="row alert alert-warning" style="margin-left: 0px;" id="whomecandidate${stat.index}">
			<div class="span2 well well-small ">		
			
				<label><strong>Select Party</strong></label> <span id="errDiv22" style="color: red; margin-bottom: 5px; margin-left: -5px; float: left; position: absolute; margin-top: 30px;"></span>	
				<s:if test="#stat.index == 0">
				<s:select cssClass="input-block-level partiesListForWhomeCls" id="partiesListForWhome0" name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo0\',22)" list="news.partyList" listKey="id" listValue="name" value="news.sourceVOList[0].destinationVOList[#stat.index].partyId"/>
				
				</s:if>
				
				<s:if test="#stat.index > 0">
				
				<s:select cssClass="input-block-level partiesListForWhomeCls" id="partiesListForWhome%{#stat.index}" name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo%{#stat.index}\',22)" list="news.partyList" listKey="id" listValue="name" value="news.sourceVOList[0].destinationVOList[#stat.index].partyId"/>
				
				</s:if>
				<img src="images/search.jpg" style="display:none;" id="candidateListForPartyForNewsToImg" />
			</div>

			<div class="span4 well well-small">
				<label><strong>Select Candidate</strong></label> 
				
				<s:if test="#stat.index == 0">
				
					<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 190px;"> 				
					<a onclick="buildSearchDiv(22,${stat.index});"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>
					
					<span class="btn btn-mini pull-right m_topN65">
					<img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForPartyForNewsTo" partyListId="partiesListForWhome" /></span>

					<s:select id="candidateListForPartyForNewsTo0" name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].candidateId" value="news.sourceVOList[0].destinationVOList[#stat.index].candidateId"	list="news.sourceVOList[0].destinationVOList[#stat.index].canidateList" listKey="id" listValue="name" />
				
				</s:if>
				<s:if test="#stat.index > 0">
				
					<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 190px;"> 				
					<a onclick="buildSearchDiv(4${stat.index},${stat.index});"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>
					
					<span class="btn btn-mini pull-right m_topN65">
					<img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForPartyForNewsTo" partyListId="partiesListForWhome" /></span>

					<s:select id="candidateListForPartyForNewsTo%{#stat.index}" name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].candidateId" value="news.sourceVOList[0].destinationVOList[#stat.index].candidateId"	list="news.sourceVOList[0].destinationVOList[#stat.index].canidateList" listKey="id" listValue="name" />
				
				</s:if>
				
						
				
		</div>
		<div class="span4 well well-small">
			<label><strong>Select Categories</strong></label>
			<span style="float:left;margin-left:135px;margin-top:-25px"><a title="refresh list" onclick="refreshCategories('whomegallaryId0');" href="javascript:{}"><i class="icon-refresh"></i></a></span>
			<s:select id="whomegallaryId%{#stat.index}"   multiple="true"          value="news.sourceVOList[0].destinationVOList[#stat.index].categoryIds"	name="keywordIdHiddenCat%{#stat.index}"	 list="news.sourceVOList[0].destinationVOList[#stat.index].categoriesList" listKey="id" listValue="name" />
			<s:hidden name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].categoryIdsStr" id="keywordIdHiddenCat%{#stat.index}" value="%{news.sourceVOList[0].destinationVOList[#stat.index].categoryIdsStr}" />
			
			<script type="text/javascript">
			   $('document').ready(function(){
			          $('#whomegallaryId'+'<s:property value="%{#stat.index}"/>').multiselect({
					noneSelectedText:"Select Category"});
			   });
			</script>
		</div>
		<div class="span2 well well-small">
			<label><strong>Rating</strong></label>
			<s:select id="ratingLevel" name="candidatePartyNewsVOList.destinationVOList[%{#stat.index}].benefitId" value="news.sourceVOList[0].destinationVOList[#stat.index].benefitId" class="input-block-level" list="news.benefitsOptionList" listKey="id" listValue="name" />				 
			</select>

		 </div>
		<div class="span12 well well-small" style="margin-left: 0px;">
			<label><strong>Enter Keywords</strong></label>

			<input type="text" class="input-block-level keyword${stat.index} destinationKeywords" key="keywordId${stat.index}" id="${stat.index}keywordId"/>
			
			<input type="hidden" id="keywordId${stat.index}Hidden" name="candidatePartyNewsVOList.destinationVOList[${stat.index}].keywordsList" />

			<script>			
			var existingKeyList = new Array();
			var keyCount = ${stat.index};
                 <c:forEach var="keywords" items="${news.sourceVOList[0].destinationVOList[stat.index].fileKeywordList}">
                  var obj = {value:${keywords.id},name:"${keywords.name}"}
				  existingKeyList.push(obj);
                 </c:forEach>
				$("#${stat.index}keywordId").autoSuggest(keywordsArray, {preFill:existingKeyList,selectedItemProp: "name", searchObjProps: "name"});

			</script>
			
			<span class="help-block">Enter multiple keywords with comma separator Ex : padayatra,scam,
				</span>
			<s:if test="#stat.index > 0">
				<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button  <span id="deleteForWhom"> <a href="javascript:void(0);" onclick="deletethisDiv('whomecandidate${stat.index}','whom',${news.sourceVOList[0].destinationVOList[stat.index].candidatePartyFileId});" class="btn" ><i class="icon-trash"></i> Delete This Block</a>  </span>  </p></div>
			</s:if>
		</div>
	
			</div>
			<s:if test="#stat.index==news.sourceVOList[0].destinationVOList.size()-1">
				<div id="whomeTalkedMainDIV"></div>			 
				<div class=" well well-small" id="addWhomDiv">     
				<a href="javascript:void(0);" onclick="addWhome(${stat.index});" class="btn btn-danger">Click to add another To - Whom</a>
				</div>
			</s:if>
	</s:iterator>
<div id="keywordsLisDiv"></div>
			</div> 
		</div>
		 </div>
		</div>
	<div id="deletedSourceCandidatePartyDivForWhom"></div>
	<!--2nd block closed -->
		
	<!--3rd block opening -->
	
	<div class="container" style="margin-top: 10px;margin-left:14px;border-radius:5px;padding:5px 15px 15px 15px;width: 906px;border:1px solid #ADC248; margin-bottom: 15px;" align="left">
	<legend class="">&nbsp;Add News From Different Sources</legend>
	<div class="row-fluid ">
		<div class="span12">
		   <div class="row-fluid">
			<div class="container">	

			<div id="hiddenFilesDiv">
			<s:iterator value="news.fileSourceVOList" var="fileSource" status="k">
			
			
				<input type="hidden" name="fileSourceVOList[${k.index}].deleted" id="removeExistSource${k.index}" value="false"/>
				<input type="hidden" name="fileSourceVOList[${k.index}].fileSourceLangId" value="${fileSource.fileSourceLangId}"/>
				<s:iterator value="news.fileSourceVOList[#k.index].sourceFileList" var="file" status="i">				
					<div id="hiddenFiles${k.index}">
						<input type="hidden" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].deleted" id="removeSource${k.index}File${i.index}"      value="false"/>
						<input type="hidden" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].filePathId" value="${fileSource.sourceFileList[i.index].filePathId}"/>
								
					</div>				
				</s:iterator>			
							
			</s:iterator>
			</div>
			<s:iterator value="news.fileSourceVOList" var="fileSource" status="k">
							
					<s:if test="#k.index <=0">
					<div id="addNewSourceToExisting"  style="float:left;">
					</s:if>
					
					<s:if test="#k.index ==1">
						<div id="newfilesourceremov${k.index}">
						<div id="${k.index}existingFile1"  style="float:left;">
					</s:if>
					<s:if test="#k.index >1">
					</div>					
					<div id="newfilesourceremov${k.index}">
					<div id="${k.index}existingFile1"  style="float:left;">
					</s:if>
					
			
			<s:iterator value="news.fileSourceVOList[#k.index].sourceFileList" var="file" status="i">
			
			
				<s:if test="#i.index <=0">
				
					<div class="span4" style="margin-left:0px;">
							<label style="margin-left: 15px;"><strong>File Path</strong></label>
				<s:if test="news.fileSourceVOList[#k.index].sourceFileList[#i.index].fileImageFileName != ''">
				<br/><input type="file" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].fileImage" class="btn fileImgCls" key="aaanewsfileDescription" style="margin-left: 13px;width:225px;"id="fileDescription0" onchange="clearExistingImg('${k.index}imgToDisplayRemove${i.index+1}','zoomSpanId${i.index}');" style="width: 225px;"/>	
					
					<span id="${k.index}imgToDisplayRemove${i.index+1}">
					<span id="zoomSpanId${i.index}">
					<span  class="icon-zoom-in" style="cursor: pointer;" title="Click Here To View Existing Image" onclick="zoomSelectedImg('${fileSource.sourceFileList[i.index].fileImageFileName}');"></span>	
					</span>
					
					<span class="icon-remove" style="cursor: pointer;float: right; position: absolute;margin-top: 12px; " title="Click Here To remove Image" onclick="clearExistingImg('fileDescription0','zoomSpanId${i.index}');"></span></span>
				</s:if>
				<s:else>
				<br/><input type="file" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].fileImage" class="btn fileImgCls" key="aaanewsfileDescription" style="margin-left: 13px;width:225px;" id="fileDescription0"/>	
				<span class="icon-remove" style="cursor: pointer;float: right; position: absolute;margin-top: 12px;" title="Click Here To remove Image" onclick="deleteExistingImg('fileDescription0');"></span>

				</s:else>
							
						
					</div>

					 
	
					<div class="span2 " style="margin-left: -12px;">					 
						<label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>
	
					<s:select  id="newsedition%{#i.index}"  class="input-block-level m_top20" value="news.fileSourceVOList[#k.index].sourceFileList[#i.index].newsEdition" name = "fileSourceVOList[%{#k.index}].sourceFileList[%{#i.index}].newsEdition" list="news.newsEdition" listKey="id" listValue="name"/>
					
					</div>					

					<div class="span1">						
						<label><strong>Page Number</strong><span class="requiredFont">*</span></label>	
					
						<input type="text" id="pageno0" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls" value="${fileSource.sourceFileList[i.index].pageNo}">
					</div>					

					<div class="span1">						
						<label><strong>News Length</strong><font class="requiredFont">*</font></label>
						<input type="text" id="newslength0" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls" value="${fileSource.sourceFileList[i.index].newsLength}">
					</div>
					
					<div class="span2 " style="margin-top:20px;">
						<label><strong>File Source</strong></label>
						<s:select  id="filesourceId0" style="margin-top:25px;"  cssClass="input-block-level m_top20 fileNewSourceCls" value="news.fileSourceVOList[#k.index].sourceFileList[0].fileSourceId" name = "fileSourceVOList[%{#k.index}].fileSourceId" list="news.fileSourceVOList[#i.index].sourceFileList[0].sourceList" listKey="id" listValue="name"/>
				
					</div>		
						
					<div class="span2 "  style="margin-top:20px;margin-left:-20px;">
						<label><strong>News Language</strong></label>
						<s:select id="sourceLangId0" style="margin-top:25px;" name="fileSourceVOList[%{#k.index}].sourceLangId" class="input-block-level m_top20" value="news.fileSourceVOList[#k.index].sourceFileList[0].sourceLangId" list="news.fileSourceVOList[#i.index].sourceFileList[0].languageList" listKey="id" listValue="name"/>
				
					</div>
						<s:if test="#k.index >0">
					<div style="float:left;margin-left:-15px;margin-top:20px">
									<a href="javascript:void(0);" onclick="removeAlreadyExistingSource('newfilesourceremov${k.index}','removeExistSource${k.index}')"> <i class="icon-trash"></i></a> 
						</div>
						</div>
						</s:if>
					<div id="source${k.index}newfile">
				</s:if>	
				
				<s:if test="#i.index >=1">						
				

					<div id="source${k.index}newfile">
						<div id="${k.index}newpart${i.index-1}" class="container " style="margin-left: 12px; float: left;">
						<script> addFile = '${i.index}';</script>

							<div class="span4" style="width: 275px;margin-left:0px;margin-right: -17px;"> 
								<input type="file" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].fileImage" class="btn addFileImgCls" onchange="clearExistingImg('${k.index}imgToDisplayRemove${i.index+1}');" style="width: 225px;"/> 
							<span id="${k.index}imgToDisplayRemove${i.index+1}"><span class="icon-zoom-in" style="cursor: pointer; margin-left: -40px;" title="Click Here To View Existing Image" onclick="zoomSelectedImg('${fileSource.sourceFileList[i.index].fileImageFileName}');"></span>
							</div> 

							<div class="span2 ">
								
							<s:select  id="newsedition0"  class="input-block-level m_top20" value="news.fileSourceVOList[#k.index].sourceFileList[#i.index].newsEdition" name = "fileSourceVOList[%{#k.index}].sourceFileList[%{#i.index}].newsEdition" list="news.newsEdition" listKey="id" listValue="name"/>
							</div> 
							<div class="span1"> 
								<input type="text" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls" value="${fileSource.sourceFileList[i.index].pageNo}"> 
							</div> 
							<div class="span1 "> 
								<input type="text" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls" value="${fileSource.sourceFileList[i.index].newsLength}"> 
							</div> 
							<div class="span2 ">
								<a href="javascript:void(0);" onclick="removeThisFile('${k.index}newpart${i.index-1}','removeSource${k.index}File${i.index}',${k.index} )" class="btn btn-block"><i class="icon-trash"></i> Delete This Row</a> 
							</div> 

							</div> 
						</div>
					
				</s:if>	
				

			<s:if test="#i.index==news.fileSourceVOList[#k.index].sourceFileList.size()-1">
		</div>	
			<div class="container m_top5">
				<span class="help-block" style="float: left;">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory. File must be .jpeg or .jpg or .png or .gif formats only.</span>
				<div class="span12 ">         
					<label><strong>Detailed News Description</strong></label>  
					
					<s:if test="news.fileSourceVOList[#k.index].sourceFileList[#i.index].newsFont != 0">				
					<span class="help-block">&nbsp;&nbsp;&nbsp;
						<textarea  name="fileSourceVOList[${k.index}].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls enadu" id="aaanewsfileDescription${k.index}" style="width: 900px;" >${news.fileSourceVOList[k.index].sourceFileList[i.index].newsDescCheck}</textarea>
						
						<input style="margin-top: -1px;" class="" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].newsDescCheck" onclick="changeToEEnadutxt('newsdetdescchk${k.index}','aaanewsfileDescription${k.index}',${k.index});" id="newsdetdescchk${k.index}" type="checkbox" checked="true"/>&nbsp;Please check if title is from eenadu.net</span>
					</s:if>
					<s:else>
						<span class="help-block">&nbsp;&nbsp;&nbsp;
						<textarea  name="fileSourceVOList[${k.index}].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls " id="aaanewsfileDescription${k.index}" style="width: 900px;" >${news.fileSourceVOList[k.index].sourceFileList[i.index].newsDescCheck}</textarea>
						
						<input style="margin-top: -1px;" class="" name="fileSourceVOList[${k.index}].sourceFileList[${i.index}].newsDescCheck" onclick="changeToEEnadutxt('newsdetdescchk${k.index}','aaanewsfileDescription${k.index}',${k.index});" id="newsdetdescchk${k.index}" type="checkbox" />&nbsp;Please check if title is from eenadu.net</span>
					</s:else>	
					<input type="hidden" id="fontStatus${k.index}" value="${news.fileSourceVOList[k.index].sourceFileList[i.index].newsFont}" name="fileSourceVOList[${k.index}].newsFont"/>		
				</div>
	        
			</div> 
		
		<div id="addButtonDivId${k.index}" class="">
		      <a href="javascript:void(0);" onclick="addNewFilePart(${k.index},${i.index});" class="btn btn-success span5" style="margin-left: 15px;">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source</a>
		</div>
		
		</s:if>

	</s:iterator>
		<s:if test="#k.index==news.fileSourceVOList.size()-1">	
			</div>
		</s:if>
		<script>
			addSource = '<s:property value="%{#k.index}"/>';
		</script>
			</s:iterator>
	</div>
		
		<div id="addNewsSourceToExisting1">&nbsp;</div>
		
	
	
		<div class="row-fluid">
		      <a style="margin-top:-30px" class="offset6 span5 btn btn-danger" onclick="addNewFileSource();" href="javascript:void(0);" style="margin-top: -60px;">Click here to  <span class="label ">Add <i class="icon-plus-sign icon-white"></i></span> another source</a>
		</div>
</div>

	<!--3rd block closed -->	
	
	<!--4rt block opened -->
	
	<div class="row-fluid"><div id="formdata" style="display:none;"></div></div>
		
	
		<div class="row-fluid" style="margin-top: 15px;" align="left">
			<div class="container m_top10" style="padding: 5px 15px 15px; border: 1px solid rgb(173, 194, 72);  border-radius: 5px; width: 870px;"><legend class="">Select News Location</legend>
				<div class="span12 ">  
			<div id="scopeDiv1">
			 <input type="hidden" id="locationScope1" name="locationScope" value="${news.selectOptionVOList[0].id}"/>
			 <input type="hidden" id="locationValue1" name="locationValue" value="${news.selectOptionVOList[0].location}"/>
			 </div>
					<div class="row-fluid">    <div class="span2" style="margin-right: 50px;">    
						<label>Location Scope    </label>
						<s:select class="input-block-level" id="scopeDiv" name="locationScope" onchange="getLocations(this.options[this.selectedIndex].value)"  listValue="locationScopeValue" list="news.fileVOLIst" listKey="locationScope" value="news.selectOptionVOList[0].id" />
					</div>
					
					<div id="showScopeSubs"></div>
					
					</div>
						<div id="showScopeSubs" style="margin-left: 160px;"></div>
						<div id="editLocationValue">
						<span style="font-weight:bold;">Location Value:</span>  ${news.selectOptionVOList[0].name}  
						<a class="btn" onclick='changeLocationValue()' id="editLocationBtn"> Edit Location </a>
						</div>
						
				</div>   
			</div>
		</div>

	<!--4rt block closed -->
	<div align="center"> <input type="button" class="btn btn-success" value="update changes" onclick="uploadNewsFromPartyPage1();" id="uploadNewsBtnId" style="width: 190px; height: 42px; margin-top: 25px;"/></div>
	<!--  onclick="validateNewsFileUpload1();" -->
	</form>
    <div style="display:none;" id="selectedImgZoom"></div>
</div>	
<div id="searchDiv"></div>	
<script type="text/javascript">
			 
$("#newsimportance").val('${news.newsimportance}');
uploadNewsForPartyAndCandidate(19);
function uploadNewsForPartyAndCandidate(fileId)
{
getPartyGallariesForUplaod("News Gallary","whomegallaryId");

	/* 
	$("#newsdatedatepic").datepicker({ dateFormat: 'dd/mm/yy' });
    $("#newsdatedatepic").datepicker("setDate", new Date(${news.year}, ${news.month}, ${news.day}));
	
	*/
	$("#newsdatedatepic").datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date(),	
		
	}).datepicker("hide");
	$("#newsdatedatepic").datepicker("setDate", new Date(${news.year}, ${news.month}, ${news.day}));
	
		$("#newsimportance").val(${newsimportance});	
		
		$("#KeywordPartiesListForNewsTo").multiselect({
		noneSelectedText:"Select Party"});
		$("#KeywordPartiesListForNewsTo").multiselect('refresh');
		$("#KeywordPartiesListForNewsTo").multiselect('create');

}
function getDesignationList(designationList)
{
  var jsObj={
		designationList:designationList,
		task:'getDesignationsList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getDesignationsListAction.action?"+rparam;
	callAjax(jsObj, url);
}

var locationDisabled = true;
function changeLocationValue()
{
	locationDisabled = false;
	$("#editLocationBtn").addClass("disabled");  
	$("#scopeDiv").prop("disabled", false);
	$("#scopeDiv").val(${news.selectOptionVOList[0].id});
	getLocations(${news.selectOptionVOList[0].id});
	$("#scopeDiv1").html("");
					
 }
function clearExistingImg(id,zoomId){

$("#"+zoomId+"").html('');
$("#"+id+"").val('');

}
function isValid(str){
 var iChars = "#$%&";
 var flag = false;
	for (var i = 0; i < str.length; i++) {
		if (iChars.indexOf(str.charAt(i)) != -1) {			
			flag = true;
		}
    }
	return flag;
}
</script>
</body>
</html>


