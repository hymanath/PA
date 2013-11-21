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

   <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
 <!-- <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script> -->
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
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>

<style type="text/css">
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

#profileManagementMainOuterDiv4 table,#reportsDiv table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#profileManagementMainOuterDiv4 table tr:nth-child(even),#reportsDiv table tr:nth-child(even){background:#EdF5FF;}
#profileManagementMainOuterDiv4 table td,#reportsDiv table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#profileManagementMainOuterDiv4 table th,#reportsDiv table th{
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


#profileManagementMainOuterDiv4,#reportsDiv
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
#selectedNewsCount{color:red;}
.createNewCandidate{width: 20px; height: 20px;cursor:pointer;}
.m_topN65{margin-top: -24px;}
form{
border:1px solid #C5C5C5;
}
</style>
</head>
<script type="text/javascript">
var gGallaryId;
		var timeST = new Date().getTime();
		var sizeOfArray;

var sourceObj = null;
var languagesObj = null;
var fileCount=0;
var fileImgCount=0;

var partyIdsListArray = new Array();
var candidateIdsListArray = new Array();
var tempPartyIdsArray = new Array();

var sourcePartyArray = new Array();
var destinationPartyArray = new Array();
var sourceCandidatesArray = new Array();
var destinationCandidatesArray = new Array();

var sourceCandidateIdsArray = new Array();
var destinationCandidateIdsArray = new Array();
var tempCount = 0;
var keywordHiddenTempCount = 0;
var responseFileIdsArray = new Array();


var fileSourceMainArray = {
	
 fileSourceSubArray:[]

}
	  
var fileSourceSubArray = {

	filePaths:[],
    source:'',
	language:'',
	edition:'',
	pageNumber:'',
	newsLength:'',
	newsDescriptionInDetailed:'',
	fontCheckBox:''

};

function showPhotoGallary1()
{
if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';

document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv4").style.display = 'none';
$("#dateSelectDiv").css("display","none");
/*$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsEditId").css({"background":"none repeat scroll 0 0 #0063DC"});*/

 buildCreateGallaryDiv();
}
return;
}


function isKeywordExist(){
	$('#statusDiv1').html('');
	var keyValue = $('#newKeyword').val();
	if(keyValue.trim().length ==0){
		$('#statusDiv1').html('<span style="color:red;">Please enter keyword name.</span>');
		return;
	}	
	 $.ajax({
		type: "GET",
		url: "isKeywordExistAction.action",
		data: { keyword :keyValue,task:"addNewKeyword" }
		})
		.done(function(msg) {
		var status = msg.toLowerCase();
		if(status.indexOf("successfully") !=-1){
			$('#newKeyword').val('');
			$('#statusDiv1').html('<span style="color:green;font-weight:bold;">'+msg+'</span>');
			}
		else
			$('#statusDiv1').html('<span style="color:red;font-weight:bold;">'+msg+'</span>');
	});
}
function mergeKeywords(){
    $('#statusDiv2').html('');
    var selected_values = $("#keywordsList").multiselect("getChecked").map(function(){
       return this.value;    
    }).get();
    
    var keywordsList="";
	var aliasName = $('#aliasKeyword').val();

    for(var i in selected_values){
    keywordsList = keywordsList+""+selected_values[i]+",";
    }	

    if(keywordsList.length == 0){
	$('#statusDiv2').html('<span style="color:red;">Please select atleast one keyword.</span>');
	return;
	}	
	else if(aliasName.trim().length == 0)
	{
	$('#statusDiv2').html('<span style="color:red;">Please enter new keyword name .</span>');
	return;
	}

     $.ajax({
    	type: "GET",
		url: "isKeywordExistAction.action",
		data: { keywords :keywordsList,newKeyword:$('#aliasKeyword').val(),task:"mergeKeywords" }
		})
		.done(function( msg ) {
		var status = msg.toLowerCase();
		if(status.indexOf("success") !=-1){
		$('#aliasKeyword').val('');
			reFreshKeywordList();
			$('#statusDiv2').html('<span style="color:green;font-weight:bold;">'+msg+'</span>');
		}
		else{
			$('#statusDiv2').html('<span style="color:red;font-weight:bold;">'+msg+'</span>');
		}
			
      });
}

function reFreshKeywordList(){
 $.ajax({
    	type: "GET",
		url: "getKeywordsListAction.action",
		data: { task:"getKeywordsList" }
		})
		.done(function( result ) {
			//console.log(result);
		$('#keywordsList').find('option').remove();
		$.each(result,function(index,value){
			$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		
			$("#keywordsList").multiselect({	
					multiple: true,
					selectedList: 10,
					hide: "explode"	
			}).multiselectfilter({
				header:"Select Keyword"    
			});
		});
}
function createPartyKeywordDiv(){

 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","block");
  $('#statusDiv1').html('');
  $('#statusDiv2').html('');
 }

function clearDivsForGallary(){
 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","block");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","block");
  $("#profileManagementHeaderDiv3").css("display","block");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');

}


</script>

<body>
<br>
<!-- For Heading -->

<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>

<div id='profileManagementMainOuterDiv' style="margin-top:-40px;">

<div id='profileManagementHeaderDiv'>
	<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
		<span class="span12" style="background:red">Party Management Admin</span>
	</table>-->
	
	
</div><br>
 <!-- For Heading end -->
 
<div id='profileManagementMainInnerDiv' class="divInfo">
	<div id="profileManagementDiv">
		<!--<table class="statusData_table" width="100%">	
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:315px"><b><input type="button" class="buttonStyle" value="Photo Gallery" id="photoGalleryId" onClick="showPhotoGallary1()"></b></td>
				    <td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Video Gallery" id="videoGalleryId" onClick="showVideoGallaey1()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Update News" id="newsEditId" onClick="showTheNewsToUpdate()"></b> </td>
					</tr>
				</table>
			</td>
		 </tr>
		</table>-->
		
	
<!-- <ul class="nav nav-tabs" style="margin-left:Auto;margin-right:Auto;width:940px;">
    <li class="active" id="newsGallaryLiId"><a value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()" style="cursor:pointer;color: blue;">News Gallery</a></li>
	<li id="addResponseToNewsLiId"><a value="News Gallery" id="responseNewsId" onClick="showTheNewsToUpdate()" style="cursor:pointer;color: blue;">Add Response To News</a></li>
	<li><a value="create Report" id="createReportId" onClick="createReport()" style="cursor:pointer;color: blue;">Create Report </a></li>
	<li><a value="viewReport" id="viewReports" onclick=" getNewsReports();" style="cursor:pointer;color: blue;">View Report</a>
		
		</li>	
		
		<li><a value="viewReport" id="viewReports" onclick=" createPartyKeywordDiv();" style="cursor:pointer;color: blue;">Party Management</a>
		
		</li>
    </ul> -->
	
				<!---Tab Header --Menu--->
				<ul class="nav nav-tabs" id="myTab">
					<!-- <li class="active">
					<a data-toggle="tab" value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()" style="cursor:pointer;color: #005580;">News Gallery</a>
					
					</li> -->
					
					
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" style="cursor:pointer;color: #005580;" >News Gallery <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();buildCreateNewsCategory();" >Create News Category</a>
							</li>
							<li>
								<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();uploadNewsForPartyAndCandidate(null);">Upload News</a>
							</li>
							<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
								<li>
									<a data-toggle="tab" style="cursor:pointer;color: #005580;" onclick="clearDivsForGallary();createNewSource();">Create New Source</a>
								</li>
							</c:if>
						</ul>
					</li>
					
					<li class="">
					<!-- <a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showTheNewsToUpdate()">Add Response To News</a> --> 
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="showTheNewsToUpdate()" style="cursor:pointer;color: #005580;">Add Response To News</a>
					</li>
					
					<li class="">
					<!-- <a data-toggle="tab" href="#CreateReport">Create Report</a> -->
					<a data-toggle="tab" value="create Report" id="createReportId" onClick="getNewsReports()" style="cursor:pointer;color: #005580;">Create Report </a>
					</li>
					<li class="">
					<!-- <a data-toggle="tab" href="#ViewReport">View Report</a>-->
					<a data-toggle="tab" value="viewReport" id="viewReports" onclick=" getNewsReports();" style="cursor:pointer;color: #005580;">View Report</a>
					</li>
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" style="cursor:pointer;color: #005580;" >Keyword Management <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<li><a data-toggle="tab" id="newKeywordBtn" style="cursor:pointer;color: #005580;" onclick="createPartyKeywordDiv();">Create New Keyword</a></li>
							</li>
							<li>
								<a data-toggle="tab" id="mergeKeywordBtn" style="cursor:pointer;color: #005580;" onClick="createPartyKeywordDiv();reFreshKeywordList();"> Merge Keywords </a>
							</li>
						</ul>
					</li>
					
				</ul>
				
				<!----Tab Header Menu End--->
</div>
</div>
</div>

<div id="dialog" title="Update Category">
<div>
	<div style="clear:both;"><span>Name</span>: <input type="text" id="categoryNameId"/></div>
	<div style="clear:both;"><span>Visibility</span>: 
				<input type="radio"  name="visibility" value="public" checked="checked" style="margin-top:0px;margin-left:4px;">Public
				<input type="radio"  name="visibility" value="private" style="margin-top:0px;margin-left:4px;">Private</div>
				<input id="idVal" type="hidden" value="" />
				
	<div id="errMsg" style="margin:5px;"></div>
	
	<span class="btn btn-mini pull-right btn-inverse" id="updateCategoryId">Update</span>
</div>
</div>
<!--<button id="opener">Open Dialog</button>-->


<!-- for  body 1 start    result  -->
<HR>
<div id='profileManagementMainOuterDiv1' style="display:none">
	<div id='profileManagementHeaderDiv1' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
		</table>-->
		
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></div>

		
	</div>

	<div id='photoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->

<!-- for  body 2 start    result  -->

<div id='profileManagementMainOuterDiv2' style="display:none">
	<div id='profileManagementHeaderDiv2' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
		</table>-->
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload Video" onclick="buildUploadVideoDiv()">
		</div>
		
	</div>	

	<div id='videoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->
<div id='profileManagementMainOuterDiv3' style="display:none">
	<div id='profileManagementHeaderDiv3' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
		</table>-->
<!--	<div class="span11 offset1 text-center alert" style="margin-left:30px;">

	<input type="button" class="btn btn-success highlight" value="Create News Category" onclick="buildCreateNewsCategory()">
	<input type="button" class="btn btn-success highlight" value="Upload News" onclick="uploadNewsForPartyAndCandidate(null)">


	<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
		<input type="button" class="btn btn-success highlight" value="Create New Source" onclick="createNewSource()">
	</c:if>
	</div>
	-->
</div>
		<div id='newsGallaryDiv' class="divInfo">
	 </div>		
		<div id='newsAssignGallaryDiv' class="divInfo"> </div>		 
	</div>
</div>


<!-- for  body 4  result  start -->

<div id="dateSelectDiv" style="display:none;">
  <h2 style="text-align: center;">Update News</h2>
 <span style="margin-right:30px;"><b>Start Date:<font class="requiredFont">*</font></b><input type="text" name="fromDate" class="inputClass dateField" id="newsFromDateId" readonly="true"/></span>
 <span><b>End Date:<font class="requiredFont">*</font></b><input type="text" name="toDate" readonly="true" class="inputClass dateField" id="newsToDateId"/></span>
 <input type="button" value="submit" onclick="getTotalNewsWithPagination();" class="btn btn-info" style="margin-right: 20px;"/>
 <input type="button" value="Add Response" onclick="addToNewsResponse()" class="btn btn-info"/>
 <div id="errorMsgNewsDiv"></div>
 
 <div id="totalSelectedNewsCount">Total Selected News Count: <span id="selectedNewsCount"></span></div>
</div>

<div id='profileManagementMainOuterDiv4' style="display:none">
	<div id='profileManagementHeaderDiv4' class="row-fluid">

		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
		</table>-->
		<div class="span10 offset1 text-center alert">NEWS</div>
	
</div>
<div id='newsGallaryDiv' class="divInfo">
	 </div>		

<!-- for  body 4  result  end -->

<div id="ajaxImg" style="display:none;margin-left:300px;margin-top:30px;"><img src="images/icons/goldAjaxLoad.gif"></img></div>
</div>
<!-- for  body 5  result  start -->
<div id='profileManagementMainOuterDiv5' style="display:none">
	<div id='profileManagementHeaderDiv5' class="row-fluid">

		<div class="span10 offset1 text-center alert">Report</div>
	
</div>
<div id='newsReportDiv' class="divInfo">
 <div class="container well">
  <h2 style="text-align: center;"> News Report</h2>
  <div id="newsReportInnerDiv">
    <div id="newsReporterrorMessageDiv"></div>
    <label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>
    <label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>
    <table>
      <tr>
        <td>Report Description </td><td><textarea maxlength="330" name="fileDescription" rows="3" cols="20" id="newsreportfileDescription"></textarea></td>
      </tr>
	    <tr><td></td><td><input type="radio" class="reporttypeclass" onclick="showHideLocationLvl('level');" checked="checked" value="byLocationLvl" name="byLocationLvl"></input> By Location Level&nbsp;&nbsp;<input class="reporttypeclass" type="radio" onclick="showHideLocationLvl('location');" value="byLocation" name="byLocationLvl"></input> By Location</td></tr>
      <tr class="regionLvlClass">
        <td>Select Level</td><td><select id="regionlevel"><option value="1">All</option><option value="2">STATE</option><option value="3">DISTRICT</option><option value="4">CONSTITUENCY</option></select></td>
      </tr>
	  <tr class="regionClass regSelReport">
        <td>Select Level</td><td><select id="reportRegionLevel" onchange="showCorrespondingLocations();"><option value="1">STATE</option><option value="2">DISTRICT</option><option value="3">PARLIAMENT CONSTITUENCY</option><option value="4">ASSEMBLY CONSTITUENCY</option></select></td>
      </tr>
	  <tr class="regionClass districtSelReport">
        <td>Select District</td><td><s:select name="districtSelReport" id="districtSelReportId" list="districtsList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  <tr class="regionClass parliamSelReport">
        <td>Select Parliament</td><td><s:select name="parliamSelReport" id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  <tr class="regionClass assembSelReport">
        <td>Select Assembly</td><td><s:select name="assembSelReport" id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name"/></td>
      </tr>
	  <tr>
        <td>Select News</td><td><select id="newsPriority"><option value="0">All</option><option value="1">Low</option><option value="2">Medium</option><option value="3">High</option></select></td>
      </tr>
	</table>
  </div>
  <div class="form-actions text-center">
    <input type="button" value="submit" class="btn btn-info" id="getNewsreport" onclick="getNews()"/>
    <img id="newsReportAjaxImg" src="images/search.jpg" style="display:none;"/>
  </div>
  <div id="locationWiseNewsDiv" class="divInfo" style="display:none;"></div>
 </div>
</div>			
</div>

	 <!-- for  body 5  result  end -->
	 
<!-- for  body 6  result  start -->
<div id='profileManagementMainOuterDiv6' style="display:none">
	<div id='profileManagementHeaderDiv6' class="row-fluid">
	<div class="span10 offset1 text-center alert">KeyWords</div>
	</div>
<div id='keyWordsMainDiv' class="divInfo">
		
	 </div>	
</div>	 

<!-- for  body 6  result  end -->
<!-- for  body 7  result  start -->
<div id='profileManagementMainOuterDiv7' style="display:none">
	<div id='profileManagementHeaderDiv7' class="row-fluid">
	<div class="span10 offset1 text-center alert">News Report</div>
	</div>
<div id='newsReportsMainDiv' class="divInfo">
		
	 </div>	
</div>	 

<div id="createCandidateDiv"><div id="createCandidateInnerDiv"></div></div>

<div id='keyWordsMainDiv' class="divInfo">
		
</div>
<div id="alterKeywords">
	<div id='profileManagementMainOuterDiv8' style="display:none">

		<div id='keywordsDiv' class="divInfo">


		<div id="newKeywordDiv" align="center">
		<h2 align="center">Create A New Keyword</h2>
			<div align="center" style="width: 400px; margin: 15px 0px 0px 40px;border:1px solid #CCCCCC;padding:15px;">
				<div id="statusDiv1" align="center" style="margin-bottom: 10px;"></div>
				
							Enter Keyword <span style="margin-left: 15px;">:</span> <input type="text" id="newKeyword" style="margin-top: 10px;"/>
							<br>
							<button class="btn btn-success" onclick="isKeywordExist();" style="margin-left: 55px;">Create New Keyword </button>
			</div>
		</div>
		
		
		<div id="mergeKeywordDiv" align="center" style="display:none;">
		<h2 align="center">Merge Keywords </h2>
		<div style="border:1px solid #CCCCCC;margin-top:25px;padding:15px;width: 600px;">
				<div id="statusDiv2" align="center" style="margin-bottom: 10px;"></div>
				
						<div id="keywordLists" style="width: 550px; margin-left: 60px;">
						  	Select Keywords <span style="margin-left: 15px;">:</span>
							<select  style="width:400px;" id="keywordsList"> <select>
						</div>
						<div style="width: 400px; margin-left: -108px;">
							Enter New Keyword <span>:</span> <input type="text" id="aliasKeyword" style="margin-top: 10px;"/>
						</div>
							<button class="btn btn-success" onclick="mergeKeywords();" style="margin-left: 90px;"> Merge Keywords </button>
		</div>
	
		</div>	
	</div>
</div>
			
</div>
<!-- for  body 7  result  end -->
<script>
var keywordsArray = new Array();
<c:forEach var="keywords" items="${keywordsList}">
   var obj = {value:${keywords.id},
			name:"${keywords.name}"}

		keywordsArray.push(obj);
</c:forEach>

var keywordGallaries;
var newsDetails;
var bvalue = false;
var noOfRowsPerPage = 10;
var modifiedRecord = 0;
$(document).ready(function() {

$(document).ready(function(){

	
	$('#newKeywordBtn').click(function(){	
		$('#newKeywordDiv').css("display","block");
		$('#mergeKeywordDiv').css("display","none");
		$('#statusDiv1').html('');
		$('#statusDiv2').html('');		
	});

	$('#mergeKeywordBtn').click(function(){
		$('#newKeywordDiv').css("display","none");
		$('#mergeKeywordDiv').css("display","block");
		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
	});

	$('#partyManagementTabId').addClass('menuActive');
});


	showNewsGallaey(null);

	$(".nav-tabs li a").click(function()
			{
				$(".nav-tabs li").removeClass('active');
				$(this).parent("li").addClass('active');
				
			});
			$(".highlight").live("click",function(){
				
				$(this).css("background","#BBBB51");
			});
			$(".highlight").live("blur",function(){
				
				$(this).css("background","#51A351");
			});
$(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});


	 $(document).on("change",'#gallariesList', function(){
		 $("#noNewsError").html('');
		 $('#dateErrorMessage').html("");

       if($('#fromDateId').val() == "" || $('#toDateId').val() == ""){
			$('#dateErrorMessage').html("<b style='color:red;'>Select Date Range</b>");
		}else{
		getAllNewsOfAGallary(this.value);
		}
     });

     $(document).on("change",'#partyList', function(){
         $('#candidateNewsList , #respenseNewsList').find('option').remove();
		 $('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();

		getNewsForAParty(this.value);
     });

	$(document).on("change",'.typeRadio', function(){
		$('#noNewsError ,#dateErrorMessage').html('');
		$('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();
        $("#gallaryShowHideDiv").css("display","none");
		$("#categoryShowHideDiv").css("display","none");
		if(this.value == "candidate"){
			$('#candidatesDiv').show('slow');
			$('#partyNewsDiv').hide('slow');
			$('#candidatesList').val("");
		}else
		{
			$("#categoryGallaryHideShowDiv").css("display","none");
			$('#candidatesDiv').hide();
			$('#partyNewsDiv').show('slow');
			$('#partyList').val(872);
			getNewsForAParty(872);

		}
     });


$(document).on("click",'#fromDateId1 , #toDateId1', function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
        changeYear: true, 
		maxDate: new Date()
	    
	}).datepicker("show");
});
	
$(document).on("click",'#fromDateId , #toDateId', function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
        changeYear: true,
		maxDate: new Date(),
	    onSelect: function(dateText, inst) { 
			isDatesValid();
			if($('input:radio[name=newsType]:checked').val() == "party")
 				   checkAllValuesAndSendAjaxForNews();
			else if($('input:radio[name=newsType]:checked').val() == "candidate")
                   checkAllValuesAndSendAjax();
	}
		
	}).datepicker("show");
});

    $(document).on('click','#responseDiv',function(){
		$('#candidateNewsList').empty();
		$('#respenseNewsList').empty();		
		  if($('#responseContentDiv').css('display') == "block"){
			  $('#candidatesList').val("");
			  $('#dateErrorMessage').html("");
              $('#noNewsError').html('');
		  }

		   $('#responseContentDiv').toggle('slow');
		   		//alert($('#responseContentDiv').is(":visible"))


     });


	    $(document).on('click','#addFile',function(){
    $("#candidateNewsList > option:selected").each(function(){
      $(this).remove().appendTo("#respenseNewsList");
    });
	  $('#respenseNewsList option').prop('selected', 'selected')
  });

  $(document).on('click','#deleteFile',function(){
   $("#respenseNewsList > option:selected").each(function(){
      $(this).remove().appendTo("#candidateNewsList");
   });

	  var my_options = $("#candidateNewsList option");

	my_options.sort(function(a,b) {
		if (a.text > b.text) return 1;
		else if (a.text < b.text) return -1;
		else return 0
	})

	$("#candidateNewsList").empty().append( my_options );

	 $('#respenseNewsList option').prop('selected', 'selected');
	});


	
  $(document).on('click','#button1',function(){
    $("#list1 > option:selected").each(function(){
      $(this).remove().appendTo("#candidateList");
		// showUploadFilesDiv($(this).val(),$(this).text());

        addMoreFiles1($(this).val(),$(this).text());
    });
	  $('#candidateList option').prop('selected', 'selected');

	 

  });

  $(document).on('click','#button2',function(){

		

   $("#candidateList > option:selected").each(function(){
		 $('.div'+$(this).val()).remove();

		/* $("input:[value='"+ $(this).val()+"']").each(function(){
			 alert($(this).attr('id'));

		 });*/
      $(this).remove().appendTo("#list1");
   });

	 var my_options = $("#list1 option");

	my_options.sort(function(a,b) {
		if (a.text > b.text) return 1;
		else if (a.text < b.text) return -1;
		else return 0
	})

	$("#list1").empty().append( my_options );
	 $('#candidateList option').prop('selected', 'selected');

   });

    $(document).on('change','#candidatesList',function(){
        $('#dateErrorMessage').html("");
		$('#noNewsError').html("");
		
		if($('#fromDateId').val() == "" || $('#toDateId').val() == ""){
			$('#dateErrorMessage').html("<b style='color:red;'>Select Date Range</b>");
		}else if($(this).val() != ""){

		 $("#categoryGallaryHideShowDiv").css('display','inline-block');
		 $("#gallaryShowHideDiv").css("display","none");

         $("#respenseNewsList").find('option').remove();
		 $("#candidateNewsList").find('option').remove();

		 $("#gallaryAllCheckboxId").attr("checked",true);
		 getCandidateNews();

         //getCandidateNews($(this).val());
		 //$("#gallaryAllCheckboxId").trigger('click');
		}
   
    });
//CategoryGallary
//gallary
$("#gallaryCheckboxId").live("click",function(){
 $("#categoryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#respenseNewsList").find('option').remove();
 $("#respenseNewsList").css('display','none');
 $("#buttonsDiv").css('display','none');
 $("#categoryShowHideDiv").css('display','none');
 if($("#gallaryCheckboxId").is(":checked"))
 {
  getCandidateGallaries();
 }
	
});
$("#gallaryList").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
$("#categoryGallarySelect").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
//category
$("#categoryCheckboxId").live("click",function(){
 $("#gallaryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#candidateNewsList").css("display","none");
 $("#buttonsDiv").css("display","none");
 $("#respenseNewsList").find("option").remove();
 $("#respenseNewsList").css("display","none");
 $("#categoryGallary").attr('checked',false);

 $("#candidateNewsList").css("display","none");

 $("#respenseNewsList").find('option');
 $("#respenseNewsList").css("display","none");

 $("#gallaryShowHideDiv").css("display","none");

 if($("#categoryCheckboxId").is(":checked"))
  getCandidatecategories();
	
});
$("#gallaryList").live("change",function(){
 $("#respenseNewsList").find('option').remove();
	
});

$("#categoryGallary").live("click",function(){

	var candidateId = $("#candidatesList").val();
	$('#dateErrorMessage').html('');
	$("#candidateNewsList").css('display','none');
    $("#buttonsDiv").css('display','none');
	$("#respenseNewsList").css('display','none');
	$('#noNewsError').html('');

  if($("#categoryGallary").is(":checked"))
  {
	 
	 if(candidateId == 0)
	 {
       $('#dateErrorMessage').html('Please Select Candidate.');
		return;
	 }
      if($("#candidateCategoryId").val() == 0)
	  {
        $('#dateErrorMessage').html('Please Select Category.');
		return;
	  }

	 var categoryIdsArray = new Array();
    
	  $('#candidateCategoryId > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });
       if(categoryIdsArray == null || categoryIdsArray == "null" || categoryIdsArray.length == 0)
	   {
         $('#dateErrorMessage').html('Please Select Category.');
		 return;
	   }
	$("#gallaryShowHideDiv").css('display','block');
	
      var jsObj={
		candidateId:candidateId,
		categoryIds:categoryIdsArray,
		task:'getGallariesForSelectedCategory'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGallariesForSelectedCategoryAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
  }
  else
	{
     clearOptionsListForSelectElmtId('categoryGallarySelect');
	 $("#gallaryShowHideDiv").css("display","none");
	}

});
$("#candidateCategoryId").live("click",function(){
	
	$("#categoryGallary").attr('checked', false);
	$("#gallaryShowHideDiv").css("display","none");
});


//Category Onchange

$("#candidateCategoryId").live("change",function(){

  var candidateId = $("#candidatesList").val();
  var categoryId = $("#candidateCategoryId").val();
  var fromDate = $("#fromDateId").val();
  var toDate = $("#toDateId").val();
  $("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

  $("#noNewsError").html('');
  $("#dateErrorMessage").html('');

  if(categoryId == 0)
  {
    $("#noNewsError").html('Please Select Category.');
	return;
  }
  
  
  if($("#categoryGallary").is(":checked") == false)
  {
	 $("#respenseNewsList").css('display','inline-block');
     $("#candidateNewsList").css('display','inline-block');
	 $("#buttonsDiv").css('display','inline-block');

    var jsObj={
		candidateId:candidateId,
		categoryId:categoryId,
		fromDate:fromDate,
	    toDate:toDate,
		task:'getNewsForACandidateByCategoryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsForACandidateByCategoryIdAction.action?"+rparam;
	 callAjax(jsObj, url);
  }
	
});


$("#assignNewsgallaryList").live("change",function(){
getNewsTitlesByGalleryId("assignNewsgallaryList");
 
});
$("#responseNewsgallaryList").live("change",function(){
	
getNewsTitlesByGalleryId("responseNewsgallaryList");	
});

$("#assignNewsbtn").live("click",function(){
   
    $("#errorMessageDiv").html('');
    
	var gallaryId = $("#assignNewsgallaryList").val();
    var fileGalleryId = $("#newsTitlesSelectList").val();
	var radioVal = $('input[name=assignNewsRadio]:checked').val();
	var responseId =0;
	var candidateId =0;
	var tempVar = "";
	var resFileGalId;
	if(gallaryId == 0)
	{
     $("#errorMessageDiv").html('Please Select Gallery.');
	 return;
	}
    else if(fileGalleryId == 0)
    {
     $("#errorMessageDiv").html('Please Select News.');
	 return;
	}
	if(radioVal == "assignResponse")
	{

	  var responseGalleryId = $("#responseNewsgallaryList").val();
	  resFileGalId = $("#responseNewsTitlesSelectList").val();
      
	   if(responseGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select Response Gallery.');
	   return;
	  }
	  else if(fileGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select News.');
	   return;
	  }
	  else if(fileGalleryId == resFileGalId)
	  {
       $("#errorMessageDiv").html("We Can't assign this news to response.");
	   return;
	  }
	  tempVar = "assignResponse";
	}
	else
    {
		var partyId = $("#partiesList").val();
		candidateId = $("#candidatesLists").val();
		resFileGalId = 0;
		if(partyId == 0)
		{
		 $("#errorMessageDiv").html('Please Select Party.');
	     return;
		}
		else if(candidateId == 0)
		{
         $("#errorMessageDiv").html('Please Select Candidate.');
	     return;
		}

	   
	   tempVar = "assignToCandidate";

	}
	var jsObj={
		candidateId:candidateId,
		fileGalleryId:fileGalleryId,
		resFileGalId:resFileGalId,
	    tempVar:tempVar,
		task:'assignResToCandidateOrAGallary'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "assignResToCandidateOrAGallaryAction.action?"+rparam;
	 callAjax1(jsObj, url);

	
});


$(".assignNewsRadioCls").live("click",function(){
 var radioVal = $('input[name=assignNewsRadio]:checked').val();
 
 if(radioVal == "assignToCandidate")
 {
  $("#candidateShowHideDiv").css("display","block");
  $("#showHideResponseGallaryDiv").css("display","none");
  getCandidatesByPartyId();
 }
 else
 {
   $("#showHideResponseGallaryDiv").css("display","block");
    $("#candidateShowHideDiv").css("display","none");
 }
	
});


});//End of Ready 





</script>

<script>







var count = 0;
function changeLanguage(){
//alert(123456);
	if($("#sourceTelugu").is(':checked') == true)
	{
		$('#newsfileTitle').addClass('enadu');
		$('#newsfileDescription').addClass('enadu');
		//$('#newsDesc').addClass('enadu');
		//$('#aaanewsfileDescription').addClass('enadu');
	}
	else
	{
		$('#newsfileTitle').removeClass('enadu');
		$('#newsfileDescription').removeClass('enadu');
		//$('#newsDesc').removeClass('enadu');
		//$('#aaanewsfileDescription').removeClass('enadu');
	}
/* var str='';
if(count == 0)
	str='Eenadu Telugu';	
if(/Eenadu Telugu/i.test(str))
{
 count = count+1; 
//$('#sourceTelugu').css("display","none");
//$('#sourceEnglish').css("display","block");
if(!$('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').addClass('enadu');
$('#newsfileDescription').addClass('enadu');
$('#newsDesc').addClass('enadu');
}
}else{
	count = 0;
//$('#sourceTelugu').css("display","block");
//$('#sourceEnglish').css("display","none");
if($('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').removeClass('enadu');
$('#newsfileDescription').removeClass('enadu');
$('#newsDesc').removeClass('enadu');
}
}
 */

}


//changes by anil
function htmlEntity(aa) {

var bb = '';
for (i = 0; i < aa.length; i++)
bb += hoj(aa.charAt(i));
return bb;
}
function hoj(d) 
{         if (d == '<')
         return '&lt;';
       else
        if (d == '>')
          return '&gt;';
    
if (d == '<')
return '&lt;';
if (d == '>')
return '&gt;';
if (d == '&')
return '&amp;';
  
   if (d.charCodeAt(0) > 127)
   return '&#' + d.charCodeAt(0) + ';';
  return d;
    }

	function buildNewsOfAGallary(divId , results)
{
 $('#noNewsError').html('');
  $('#'+divId).find('option').remove();
  if(results != null && results.length > 0){
	  	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').show();


	 $.each(results , function(index , value){

		if(value.flag == true)
		  $('#'+divId).append('<option class="enadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');
		else
			$('#'+divId).append('<option class="notEenadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');


	});
  }else{
	  $('#noNewsError').html('<b style="color:red;">No news found.Changing the date range may help you.</b>');
	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();

  }

}



function buildGallaries(divId , results)
{
   $('#'+divId).find('option').remove();
   $('#'+divId).append('<option value="0">Select Gallery</option>');

  $.each(results,function(key , value){
   $('#'+divId).append('<option value="'+value.id+'">'+value.name+'</option>');

  });


}
function IsNumeric(val) {
	$('#Err4Numer').hide();
	if (!(Number(val)==val)) {
	$('#Err4Numer').show();
		$('.pageno').val('');
	}
}
function IsNumeric1(val) {
	$('#Err4Numer1').hide();
	if (!(Number(val)==val)) {
		$('#Err4Numer1').show();
		$('.newslength').val('');
	}
}


//Gallary

function getCandidateGallaries()
{
  $("#candidateNewsList").css("display","none");
  $("#gallaryShowHideDiv").css("display","block");
    var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
	if(candidateId == 0)
	{
	  $("#noNewsError").html('Please Select Candidate');
	  return;	
	}
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#noNewsError").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#noNewsError").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#noNewsError").html('Please Select To Date');
		 return;
	   }
	   /* else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $("#noNewsError").html('Invalid Date Selection.');
         return;
	   }  */
	

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedGallaries'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedGallariesAction.action?"+rparam;
	callAjax(jsObj, url);


}

function getCandidatecategories()
{
	$("#categoryShowHideDiv").css("display","block");
   var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#dateErrorMessage").html('');
	$('#noNewsError').html("");
	if(candidateId == 0)
	{
	  $("#dateErrorMessage").html('Please Select Candidate');
	  return;	
	}
	
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#dateErrorMessage").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select To Date');
		 return;
	   }
	  /*  else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $("#dateErrorMessage").html('Invalid Date Selection.');
         return;
	    
	} */

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedCategories'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedCategoriesAction.action?"+rparam;
	callAjax(jsObj, url);

}

function getNewsTitlesForACandidateByGallaryId()
{
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
  var candidateId = $("#candidatesList").val();
	var gallaryId = $("#gallaryList").val();
	var fromDate = $("#fromDateId").val();
	var toDate = $("#toDateId").val();

	$("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

	if(gallaryId == 0)
	{
	  $("#noNewsError").html('Please Select Gallery.');
	  return;
	}

    $("#respenseNewsList").css('display','inline-block');
    $("#candidateNewsList").css('display','inline-block');
    $("#buttonsDiv").css('display','inline-block');

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		gallaryId:gallaryId,
		task:'getNewsTitlesForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getNewsTitlesForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);
}

function createOptionsForSelectElement(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

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


function assignNewsToCandidate()
{
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","block");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","block");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
   $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
  var str = '';
  str +='<div id="content" style="width:650px;" class="assignNewsDivCls">';
  str +='<h2 style="text-align: center;">Assign News</h2>';
  str +='<div id="errorMessageDiv"></div>';
  str +='<div id="assignNewsInnerDiv">';
  str +='<label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>';
  str +='<label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>';
  str +='<label>Select Gallery: <select id="assignNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="newsTitlesSelectList"></select></label>';
  str +='<div id="assignNewsRadioDiv">';
  str +='<input id="assignNewsResradio" type="radio" value="assignResponse" name="assignNewsRadio" class="assignNewsRadioCls" checked="true"/>Set As Response';
  str +='<input id="assignNewsCandidateRadio" type="radio" value="assignToCandidate" name="assignNewsRadio" class="assignNewsRadioCls"/>Assign Candidate';
  str +='</div>';
  
  str +='<div id="showHideResponseGallaryDiv">';
  str +='<label>Select Gallery: <select id="responseNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="responseNewsTitlesSelectList"></select></label>';
  str +='</div>';

  str +='<div id="candidateShowHideDiv" style="display:none;">';
  str += '<label>Select Party : <font class="requiredFont">*</font>';
  str += ' <select id="partiesList" name="party" onchange="getCandidatesByPartyId()">';
  //str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option><option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" selected>TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
  str +='</select></label>';
	

  str +='<label>Select Candidate : <font class="requiredFont">*</font><select id="candidatesLists"></select></label>';
  str +='</div>';
  str +='<input type="button" value="submit" class="btn btn-info" id="assignNewsbtn"/>';

  str +='</div>';
  str +='</div>';
  $("#newsAssignGallaryDiv").html(str);

  getGalleryListForAParty();
}


function getGalleryListForAParty()
{
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
    var locationIdsArray = new Array();
	var locationScope = "";
	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		locationIdsArray:locationIdsArray,
		locationScope:locationScope,
		task:'getGalleryListForAParty'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGalleryListForAPartyAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getNewsTitlesByGalleryId(divId)
{
	var gallaryId;
	var fromDate;
	var toDate;
  if(divId == "assignNewsgallaryList"){
   gallaryId = $("#assignNewsgallaryList").val();
	fromDate = $("#fromDateId1").val();
	toDate = $("#toDateId1").val();
   }

  if(divId == "responseNewsgallaryList"){
    gallaryId = $("#responseNewsgallaryList").val();
	toDate = $("#toDateId1").val();
	fromDate = $("#fromDateId1").val();
   }
 $("errorMsgDiv").html('');
 if(gallaryId == 0)
 {
   $("errorMsgDiv").html('Please Select Gallery.');
   return;
 }

 var jsObj={
		gallaryId:gallaryId,
		fromDate:fromDate,
	    toDate:toDate,
        divId:divId,
		task:'getNewsByGalleryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsByGalleryIdAction.action?"+rparam;
	 callAjax(jsObj, url);

}

function getCandidatesByPartyId()
{
 
 $("#errorMessageDiv").html('');
 var partyId = $("#partiesList").val();
 if(partyId == 0)
 {
   $("#errorMessageDiv").html('Please Select Party.');
   return;
 }

  var jsObj = {
			partyId :partyId,
			task : "getCandidatesByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
  callAjax(jsObj,url);
}

function showAssignNewsStatus(results)
{
	$("#errorMessageDiv").html('');
  if(results.resultCode == 0 && results.message != null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");

	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News is Already Assigned.').css("color","green");
   return;
  }
  else if(results.resultCode == 0 && results.message == null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");
	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News Assigned Successfully.').css("color","green");
   return;
  }
  else
  {
   $("#errorMessageDiv").html('Error occured! try again.').css("color","red");
   return;
  }
}	
	
function isDatesValid()
{
	$('#noNewsError').html("");
	var fromDate = $("#fromDateId").val();
	var toDate  = $("#toDateId").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{
		 $('#noNewsError').html("Start Date should be Less Than End Date");
		}
	}
}

function isDatesValid1()
{
	$('#errorMessageDiv').html("");
	var fromDate = $("#fromDateId1").val();
	var toDate  = $("#toDateId1").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorMessageDiv').html("From Date should be Less Than To Date");
		}
	}
}
function saveNewSourceDetails()
{
	$('#errorDiv').html('');
	var sourceName =  $.trim($('#sourceName').val());
	if(sourceName.length <=0){
		$('#errorDiv').html('Source Name Should not be empty');
		$('#errorDiv').css('color','red')
		return false;
	}
	var jsObj=
	{
		name  : sourceName,
		task  : "storeSource"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "storeSourceDetails.action?"+rparam;	

	callAjax(jsObj,url);
}

function createNewSource()
{
	//$('#sourceDetails').show();
	var str = "";
	str +='<h2 align="center">Create A New Source</h2>';
	str+='<div id="sourceDetails"  style="width: 400px; border: 1px solid #CCCCCC; border-radius: 4px 4px 4px 4px; padding: 4px;margin-left: 298px;">';
	str +=  '<span>Source Name : </span><input type="text" id="sourceName"></input></br>';
	str += "<div id='errorDiv' style='color:red'> </div>";
	str +=  '<input type="button" value="create new Source" onClick="saveNewSourceDetails();" class="btn btn-info" style="margin-left: 244px;"></input>';
	
	str+='</div>';
	$('#newsGallaryDiv').html(str);
	
	
}

$('#updateCategoryId').live('click',function(){
	var jsObj=
	{
		task  : "getAllCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});
function buildCategoriesOfUser(results,jsObj){
var visibility="";
var sno=0;
	var str='';
	str+='<table class="table table-bordered" id="categoriesDT">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>S.No';
			str+='</th>';
			
			str+='<th>Category';
			str+='</th>';
			
			str+='<th>Access Type';
			str+='</th>';
			
			str+='<th>Is Deleted';
			str+='</th>';
			
			str+='<th>Update';
			str+='</th>';
			
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
		for(var i in results){
		sno=sno+1;
			str+='<tr>';
				str+='<td>'+sno+'</td>';
			
				str+='<td>'+results[i].name+'</td>';
			
			if(results[i].visibility=="false"||results[i].visibility=="public"){
				visibility="public";
			}else{
				visibility="private";
			}
			
				str+='<td>'+visibility+'</td>';
			
				if(results[i].isDeleted=="false"){
					str+='<td onclick="onCategory('+results[i].id+',\'delete\')" title="Delete Category"><img src="images/icons/delete.png"/></td>';
                   
				 str+='<td onclick="editCategory('+results[i].id+',\''+results[i].name+'\',\''+visibility+'\')"><img src="images/icons/edit.png"/></td>';

				}else{
					str+='<td onclick="onCategory('+results[i].id+',\'accept\')" title="Enable this Category"><img src="images/icons/accept.png"/></td>';

					str+='<td><img src="images/icons/edit.png"/></td>';
				}
			
			str+='</tr>';
		}
	str+='</tbody>';
	str+='</table>';
	$('#categoriesTable').html(str);
	
	$('#categoriesDT').dataTable({
		   "iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
	});
}
 $(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
	    modal: true,
		position:'center',
		resizable: false,

	});
});

function onCategory(id,name){
$('#errMsg').html('');
 var jsObj=
	{
		idVal:id,
		name :name,
		task : "onOroffCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteOrAcceptCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
 
}

function editCategory(id,name,visibility){
	$('#errMsg').html('');
	$( "#dialog" ).dialog( "open" );
	$('#categoryNameId').val(name);
	$('input[name=visibility][value='+visibility+']').prop("checked",true);
	$('#idVal').val(id);
	
}

$('#updateCategoryId').click(function(){
	var categoryName=$('#categoryNameId').val();
	var visibility = $('input:radio[name=visibility]:checked').val();
	var idVal=$('#idVal').val();
	
	var jsObj=
	{
		category:categoryName,
		visibility:visibility,
		idVal:idVal,
		task  : "updateCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});

function buildUpdateStatus(result)
{
	
	$('#newsSuccessDiv').html("<font style='font-weight:bold;color:green;margin-left:50px;'>News Updated Successfully.</font>");
       setTimeout(hideDialog,3000);
    buildNewsDetails();
}

function getAllCategoriesForGallary()
{
 var jsObj=
	{
		task  : "getTotalCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCategoriesListAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function getGallariesForSelectedCategory()
{

  $("#uploadNewsFileErrorDiv1").html('');
  var categoryId = $("#category").val();
  if(categoryId == 0)
  {
    $("#uploadNewsFileErrorDiv1").html('Please Select Category.').css("color","red");
     return;
  }
	 var jsObj=
	{
	    categoryId:categoryId,
		task  : "getGallariesInCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}
function getScopeForUser(){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv",
  		    task:"getLocationScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationScopeAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}

function showUserAccessLocationScopeList1(results)
{
  
  
  var id = results.scopeId;
 
  $("#scopeDiv").val(id);
  //$("#scopeDiv option:selected").val(id);
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select style="margin-left:5px;width: 137px;" name="locationValue" id="stateDiv" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
  str +='  </tr>';
  
  /*str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
  str +='  </tr>';*/

  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   buildSelectOptionVOList(results.stateList,'stateDiv',1);
   //buildSelectOptionVOList(results.districtList,'districtDiv',null);

  }

  else if(id == 3)
  {

    var str ='';
    str +='<table>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
    str +='  </tr>';
		
	/*str +='  <tr>';
    str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue" ></select></td>';
    str +='  </tr>';*/

   

    str +='</table>';
    document.getElementById("showScopeSubs").innerHTML = str;
    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);
	//buildSelectOptionVOList(results.constituencyList,'constituencyDiv',null);
	
  }
  


  else if(id == 4)
  {

    var str ='';
    str +='<table>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
    str +='  </tr>';
		
	str +='  <tr>';
    str +='	   <td class="tdWidth1">Assembly Consti : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue" ></select></td>';
    str +='  </tr>';

   /* str +='  <tr>';
    str +='	   <td class="tdWidth1">Mandal/ Municipality/ Corp/GMC : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="mandalDiv"    onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"></select></td>';
    str +='  </tr>';*/

    str +='</table>';
    document.getElementById("showScopeSubs").innerHTML = str;
    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);
	buildSelectOptionVOList(results.constituencyList,'constituencyDiv',results.constituencyId);
	//buildSelectOptionVOList(results.mandalList,'mandalDiv',null);
  }
  
  
  
}



function showUserAccessLocationScopeList(results)
{
  
 
  var id = results.scopeId;
  var str = '';

  $("#scopeDiv").val(id);
  
  if(id==0 || id==1)
   str +='';

  else if(id==2)
  {
    str += '<div class="span2">';
    str += ' <label>State</label>';
    str += ' <select class="input-block-level" name="locationValue" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
	str +='</div>';
	document.getElementById("showScopeSubs").innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv',1);
  }

  else if(id == 3)
  {
   
    str += '<div class="span2">';
    str += ' <label>State</label>';
    str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>District</label>';
    str += ' <select class="input-block-level" name="locationValue" id="districtDiv" ></select>';
	str +='</div>';

    document.getElementById("showScopeSubs").innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);

  }
  


  else if(id == 4)
  {
  
    str += '<div class="span2">';
    str += ' <label>State</label>';
    str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>District</label>';
    str += ' <select class="input-block-level" id="districtDiv"></select>';
	str +='</div>';

	str += '<div class="span2">';
    str += ' <label>Assembly Consti</label>';
    str += ' <select class="input-block-level" id="constituencyDiv" name="locationValue"></select>';
	str +='</div>';
    
	document.getElementById("showScopeSubs").innerHTML = str;

    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);
	buildSelectOptionVOList(results.constituencyList,'constituencyDiv',results.constituencyId);
	
  }
  
   
  
}



	
	

function buildSelectOptionVOList(optionsList,elmt,populatedId)
{
	
 if(!elmt || optionsList == null)
  return;
 
 var divEle = document.getElementById(elmt);
  var option = document.createElement('option');
	option.value="0";
	option.text="Select";
  if(populatedId == null)
  {
	try
	{
		divEle.add(option,null); // standards compliant
	}
	catch(ex)
	{
		divEle.add(option); // IE only
	}
  }

	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			divEle.add(option,null); // standards compliant
		}
		catch(ex)
		{
			divEle.add(option); // IE only
		}
	}

	if(populatedId != null)
     divEle.value = populatedId;
	 


}


function createReport()
{

 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","block");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
  
$("#fromDateId1").datepicker({ dateFormat: 'dd/mm/yy' });
$("#fromDateId1").datepicker("setDate", new Date());
$("#toDateId1").datepicker({ dateFormat: 'dd/mm/yy' });
$("#toDateId1").datepicker("setDate", new Date());

}
function getNews()
{

$("#newsReportAjaxImg").css({ 'display': 'inline-block' });
$("#locationWiseNewsDiv").css("display","none");
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
	var regionLevel = $("#regionlevel").val();
	var importance = $("#newsPriority").val();
	var reportRegionLevel = $("#reportRegionLevel").val();
	var reportRegionLevelVal = 0;
	 var type="";
	if($("#byLocationLvl").is(':checked')){
	  type = "byLevel";
	}else{
	  type = "byRegion";
	  if(reportRegionLevel == 1){
	      reportRegionLevelVal = 1;
	  }else if(reportRegionLevel == 2){
		  reportRegionLevelVal = $("#districtSelReportId option:selected").val();
	  }else if(reportRegionLevel == 3){
		  reportRegionLevelVal = $("#parliamSelReportId option:selected").val();
	  }else if(reportRegionLevel == 4){
		  reportRegionLevelVal = $("#assembSelReportId option:selected").val();
	  }
	} 
	var jsObj = {
			task: 'getNews',
			fromDate:fromDate,
			toDate:toDate,
			regionLevel:regionLevel,
			importance:importance,
			reportRegionLevel:reportRegionLevel,
			reportRegionLevelVal:reportRegionLevelVal,
			type:type
	};
   /* var jsObj = {
			task: 'getNews',
			fromDate:fromDate,
			toDate:toDate,
			regionLevel:regionLevel,
			importance:importance
			
	};*/
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllNewsForAUserAction.action?"+rparam;
	callnewAjax(jsObj,url);
}


function saveNewsReport()
{


var newsreportfileDescription = $("#newsreportfileDescription").val();
var fileGallaryIds = [];
   $(".find-table").each(function() {
      
		  if($(this).is(":checked")){
		fileGallaryIds.push($(this).val());
	   }
    });
   var str = '<font color="red">';
   var flag =false;
	if(fileGallaryIds == "")
	{
		str +='Select atleast one file<br/>';
		flag =true;
	}
	
	 if(newsreportfileDescription == 0)
	{
		str +='description is required<br/>';
		flag = true;
	}
	
	newsReporterrorMessageDiv.innerHTML = str;
	 if(flag == true)
	{
		$('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
		
	return;
	}
	else
	{
	$("#savenewsAjaxImg").css({'display': 'inline-block' });
	newsReporterrorMessageDiv.innerHTML = '';
    var jsObj = {
			fileGallaryIds:fileGallaryIds,
			description:newsreportfileDescription,
			task: 'saveNews',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "saveNewsUserAction.action?"+rparam;
	callnewAjax(jsObj,url);
	}
}
/*function buildLocationWiseNews(results)
{
	var str='';
	var divEle = document.getElementById("locationWiseNewsDiv");
	$("#newsReportAjaxImg").css("display","none");
	if(results != null && results != '')
	{
		for(var i in results)
		{
			str+='<div id="mainDiv">';
			str+='<h4 id="mainheading">'+results[i].scope+' Wise </h4>';
			for(var j=0; j< results[i].fileVOList.length;j++)
			{
			
			str+='<h4 >'+ results[i].fileVOList[j].locationName+' </h4>';
			str+='<div style="border:1px solid #d3d3d3;">';
			str+='<table class="table">'
			str+='<tr>';
			
			str+='<th>check</th>';
			str+='<th>Source</th>';
			str+='<th>Language</th>';
			str+='<th>File Date</th>';
			str+='<th>Candidate Name</th>';
			str+='<th>Location Name</th>';
			str+='</tr>';
			
			for(var k=0;k<results[i].fileVOList[j].fileVOList.length;k++)
			{
				var source = results[i].fileVOList[j].fileVOList[k].source.trim();
		
				str+='<tr >';
				str+='<td><input type="checkbox" class="find-table" value='+results[i].fileVOList[j].fileVOList[k].contentId+'></td>';
				str+='<td colspan="5" >';
				if(source == "Eenadu Telugu")
				{
				str+="<span class='enadu fontStyle pagerRow' style='font-weight:bold;'><a href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></span>"
				//str+=' <div class="pagerRow">'+results[i].fileVOList[j].fileVOList[k].fileTitle1+'</div>';
				}
				else
			{
			str+="<h4 style='text-transform: capitalize;color: #005580;'><a  href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></h4>";
			}
			    str+='</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].source+'</span>';
				str+='</td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].language+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].fileDate+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].candidateName+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].locationName+' </span></td>';
				str+='</tr>';
			
			}
	
			str+='</table>'
			str+='</div>';
		}
			
	}
	str +='<br/><input type="button" value="submit" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/>';
	
	str+='<img style="display: none;" src="images/search.jpg" id="savenewsAjaxImg">';
	}
	divEle.innerHTML =str;
}*/
function showReportFileNewsStatus(result)
{
	
	$("#savenewsAjaxImg").css("display","none");
	if(result.resultCode == 0)
	{
	 $("#newsreportfileDescription").val('');
	 $("#newsReporterrorMessageDiv").html('Saved successfully..').css('color','green');
	}
	else
	{
  $("#newsReporterrorMessageDiv").html('Error Occured! Try Again..').css('color','red');
   }
 $('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
		 return;
}

var data = {items:keywordsArray};


$(document).ready(function(){
	$(function(){
    $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


  });
});

function getUnassignedKeyWords()
{
	
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","block");
  $("#profileManagementHeaderDiv6").css("display","none");
    $("#profileManagementMainOuterDiv7").css("display","none");
  $("#profileManagementMainOuterDiv8").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
    var str ='';
	str+='<div class="container well">';

	str+='<div class="row clearfix">';
	str+='<legend class="text-center ">Map KeyWords To Gallaries</legend>';
	str+='<div id="keywordErrorMsgDivId" ></div>';
	str+='<div class="row-fluid"><div class="span6 well well-small " id="keywordsDiv">';
	str+=' </div>';
	str+='<div class="span6"> '; 
	str+='<div class="row-fluid">';
	str+='<div class="span12 well well-small ">';
    str+='<label><strong>Select Category Name</strong></label>';
    str+=' <select class="input-block-level"  id="keywordCategory"  onchange="getGallariesForSelectedCategory1(\'keywordCategory\')">';
	str+='</select>';
    str+='</div>';
	str+='</div></div>';
	str+='<div class="row-fluid">';
    str+='<div class="span12 well well-small " id="categorygallary" style="display:none;">';
    str+='<label><strong>List oF (Category) Gallarys</strong></label>';
	str+='</div>';
	str+='</div>';
    str+='</div>';
	str+='<div class="form-actions text-center">';
	str+=' <button type="submit " class="btn btn-success btn-large" onclick="updateGallaryKeyword();">Submit</button>';
	str+='<img style="display:none;" src="images/search.jpg" id="keywordAjaxImg">';
	str+='</div>';
	str+='</div>';

	str+='</div>';
	
		str+='<div class="container well">';
		str+='<div class="row clearfix">';
	str+='<legend class="text-center ">Update KeyWord</legend>';
	str+='<div id="keywordErrorMsgDivId1" ></div>';
    str+='<div class="row-fluid"><div class="span6 well well-small " id="gallaryMapedkeywordsDiv">';
	 str+='<label><strong>Select KeyWord</strong></label>';
     str+=' <select class="input-block-level" id="keywords">';
	 str+='</select>';
	str+=' </div>';
	str+='<div class="span6"> '; 
	str+='<div class="row-fluid">';
	str+='<div class="span12 well well-small ">';
     str+='<label><strong>Select Category Name</strong></label>';
     str+=' <select class="input-block-level" id="keywordCategory1" onchange="getGallaryId();getGallariesForSelectedCategory1(\'keywordCategory1\');">';
	 str+='</select>';
    str+='</div>';
	str+='</div></div>';
	str+='<div class="row-fluid">';
    str+='<div class="span12 well well-small " id="categorygallary1" style="display:none;">';
	
	str+='</div>';
	str+='</div>';
    str+='</div>';
	str+='<div class="form-actions text-center">';
	str+=' <button type="submit " class="btn btn-success btn-large" onclick="updateExistingKeyword();">Submit</button>';
	str+='<img style="display:none;" src="images/search.jpg" id="keywordAjaxImg1">';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("keyWordsMainDiv").innerHTML = str;
	getCategory();
	getKeyWords();
	getMappedKeyWords();
}

function getKeyWords()
{
	 var jsObj={
		
		task:'getKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}
function getMappedKeyWords()
{
	 var jsObj={
		
		task:'getGallaryMapedKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}

function getGallariesForSelectedCategory1(selEle)
{
 if(selEle == "keywordCategory")
  $("#categorygallary").css("display","block");
  if(selEle == "keywordCategory1")
  $("#categorygallary1").css("display","block");;
  var categoryId = $('#'+selEle+'').val();
 var jsObj=
	{
	    categoryId:categoryId,
		selEle : selEle,
		task  : "getGallariesInCategory1"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function updateGallaryKeyword()
{
var keywordsArr =[]; 
var gallariesArr = [];
$(".keywords").each(function() {
		 if($(this).is(":checked")){
		
		keywordsArr.push($(this).val());
	   }
    });
	$(".gallary").each(function() {
		 if($(this).is(":checked")){
		gallariesArr.push($(this).val());
	   }
    });
	var keywordCategory = $("#keywordCategory").val();
	 var flag =false;
	 var str='<font color="red">';
	var keywordErrorMsgDivId = document.getElementById('keywordErrorMsgDivId');
	
	if(keywordCategory == 0)
	{
	str +='Select Category<br/>';
		flag =true;
	}
	else if(gallariesArr == "")
	{
		str +='Select atleast one Gallary<br/>';
		flag =true;
	}
	else if(keywordsArr == "")
	{
		str +='Select atleast one keyWord<br/>';
		flag =true;
	}
	if(flag == true)
	{
	keywordErrorMsgDivId.innerHTML = str;
	return;
	}
	else
	keywordErrorMsgDivId.innerHTML = '';
	$("#keywordAjaxImg").css("display","inline-block");
		var jsObj = {
			gallariesArr:gallariesArr,
			keywordsArr:keywordsArr,
			task: 'updateGallaryKeyword',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "updateGallaryKeywordAction.action?"+rparam;
	callnewAjax(jsObj,url);

}

function uploadNewsForPartyAndCandidate(fileId)
{

 if(fileId != null)
 {
  $("#addResponseToNewsLiId").removeClass("active");
  $("#newsGallaryLiId").addClass("active");
 }

  var tempPartyId = 872;
   var str ='';
     
        str += '<form name="uploadForm1" action="uploadFilesForPartyAndCandidatesKeywords.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
		str+='<div class="container">';
         str += '<table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';
		
		str+='<div class="span12"> ';
		if(fileId == null || fileId == '')
		 str +='<legend class="boxHeading text-center">Upload A News   </legend> ';
		else
		 str +='<legend class="boxHeading text-center">Adding Response To Selected News</legend> ';

			str+='<div class="well" style="border:1px solid #ADC248;width: 905px; margin-left: -10px;padding:5px 15px 15px 15px;" >';
	str+='	<legend class="">&nbsp; Basic Information of News  </legend>';
	str+='	<div class="row-fluid">       ';
	str+='<div class="span6">';
				str+='<label><strong>News Title<span class="requiredFont">*</span></strong></label>  ';
				str+='<input type="text" maxlength="160" size="25" name="fileTitle" placeholder="Enter Title Of the News" id="newsfileTitle" class="input-block-level">       ';
				str+='<span class="help-block"> <input type="checkbox" onclick="changeLanguage();" name="title">&nbsp;Please check if title is from eenadu.net</span>       ';
			str+='</div>       ';
			str+='<div class="span3 ">         ';
				str+='<label><strong>News Date<span class="requiredFont">*</span><strong></strong></strong></label>  ';
				str+='<input type="text" size="20" name="fileDate" readonly="true" class="dateField" placeholder="Select News Date" id="newsdatedatepic" style="cursor:text;">         ';
				str+='<span class="help-block"> Select Date of News</span> ';
			str+='</div>       ';
			str+='<div class="span3 ">         ';
				str+='<label><strong>News Importance<span class="requiredFont">*</span><strong></strong></strong></label>   ';      
				str+='<select name="newsimportance" id="newsimportance"><option value="3">High</option><option value="1">Low</option><option value="2">Medium</option></select>';
				str+='<span class="help-block"> &nbsp;Please select News Importance</span>       ';
			str+='</div>    ';
		str+='</div> ';

		str+='<div class="row-fluid">       <div class="span8 ">         <label><strong>News Discription<span class="requiredFont">*</span><strong></strong></strong></label>         <textarea id="newsfileDescription" class="input-block-level" cols="20" rows="2" name="fileDescription" maxlength="330"></textarea>     </div>';
		str+='<div class="span4 ">      ';
		str+='<label><strong>Image To Display<span class="requiredFont">*</span></strong></label>';
		 str+='<input type="file" class="m_top10"></div>    </div>';
		
	str+='</div></div>';
	
		str+='<div class="container">';

		str+='<div class="row-fluid"> ';

		str+='    <div class="sapn12"> <div id="myID" style="border:1px solid #ADC248;position: inherit; margin-top: 355px;border-radius:5px; padding:5px 15px 15px 15px;width:906px;"> ';  
		str+='         <legend>From - Who</legend>';
		str+='    <div id="whoTalkedMainDIV"><div style="margin-left: 0px;" class="row alert alert-warning">';
		str+='    <div class="span5 well well-small ">';
		str+='<label><strong>Select Party</strong></label><select class="input-block-level" id="partiesList" name="candidatePartyNewsVOList.sourceVOList[0].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForParty\')">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" >TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" style="display:none;" id="candidateListForPartyImg" />';
		str+='</div>';

		str+='    <div class="span5 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesList" key="candidateListForParty" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str +='<select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList[0].candidateId" id="candidateListForParty">';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
				
		str+='</div>';
		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList[0].benefitId">';
        str +='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
        str +='   </select>';
		
		str+='    </div>';
				   
		str+='</div></div>  ';

		str+='<div class=" well well-small" style="margin-bottom: 0px;"> <a class="btn btn-danger" onclick="addNewFrom();" href="javascript:void(0);">Click to add another From - Who</a></div><legend>To - Whom</legend>';
		str+='   <div id="whomeTalkedMainDIV"> <div class="row alert alert-warning" style="margin-left: 0px;">';
		str+='    <div class="span2 well well-small ">';
		str+='<label><strong>Select Party</strong></label><select class="input-block-level" id="partiesListForWhome" name="candidatePartyNewsVOList.destinationVOList[0].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo\')">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872">TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" style="display:none;" id="candidateListForPartyForNewsToImg" />';
		str+='</div>';

		str+='    <div class="span4 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForPartyForNewsTo" partyListId="partiesListForWhome" ></span>';
		str +='<select id="candidateListForPartyForNewsTo" name="candidatePartyNewsVOList.destinationVOList[0].candidateId" class="input-block-level">';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		
		str+='</div>';
		str+='<div class="span4 well well-small">';
		str+='<label><strong>Select Categories</strong></label><select key="keywordIdHiddenCat0" style="width: 252px;" id="whomegallaryId" >';
		str+='    <option>Select Category</option>';
		str+='</select>';
		
		str+='    </span>';
		str+='</div>';

		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select name="candidatePartyNewsVOList.destinationVOList[0].benefitId" class="input-block-level">';
        str+='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
		str+='</select>';

		str+='    </div>';
		str+='<div class="span12 well well-small" style="margin-left: 0px;">';
		str+='<label><strong>Enter Keywords</strong></label><input type="text" class="input-block-level keyword0 destinationKeywords" key="keywordId0" id="keywordId">';
		str+='<span class="help-block">Enter multiple keywords with comma separator Ex : padayatra,scam,';
		str+='    </span>';
		str+='</div>';
		str +='<input type="hidden" id="keywordId0Hidden" name="candidatePartyNewsVOList.destinationVOList[0].keywordsList" />';
        str +='<input type="hidden" id="keywordIdHiddenCat0" name="candidatePartyNewsVOList.destinationVOList[0].categoryIdsStr" />';		   	   
		str+='</div></div>';
		
				   
		str+='<div class=" well well-small">     <a href="javascript:void(0);" onclick="addWhome();" class="btn btn-danger">Click to add another To - Whom</a></div>';

		str+='</div>   </div>';
		str+=' </div>';
		str+='</div>';

		str+='<div class="row-fluid"><div id="formdata" style="display:none;"></div></div><div class="container" style="margin-top: 10px;margin-left:0px;border-radius:5px;padding:5px 15px 15px 15px;width: 906px;border:1px solid #ADC248;" ><legend class="">&nbsp;Add News From Different Sources</legend><div class="row-fluid ">';
		str+='    <div class="span12">';
		str+='        <div class="row-fluid">';
		str+='<div class="container ">';
		str+='        <div class="span4" style="width: 290px;">';
		str+='        <label><strong>File Path</strong></label>';
			   
		str+='<br/><input type="file" name="fileSourceVOList[0].sourceFileList[0].fileImage" class="btn fileImgCls" key="aaanewsfileDescription">';
		

		str+='        </div>';

		str+='<div class="span2 " style="margin-left: -5px;">';
		str+='        <label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>';
		str+='        <select id="newsedition0" name="fileSourceVOList[0].sourceFileList[0].newsEdition"  class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
		str+='        <label><strong>Page Number</strong><span class="requiredFont">*</span></label>';
		str+='        <input type="text" id="pageno0" name="fileSourceVOList[0].sourceFileList[0].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
		str+='        <label><strong>News Length</strong><font class="requiredFont">*</font></label>';
		str+='        <input type="text" id="newslength0" name="fileSourceVOList[0].sourceFileList[0].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>File Source</strong></label>';
		str+='        <select  id="filesourceId0" style="margin-top:25px;" name="fileSourceVOList[0].fileSourceId"  class="input-block-level m_top20"><option value="0">Select Source</option></select>';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>News Language</strong></label>';
		str+='        <select id="sourceLangId0" style="margin-top:25px;" name="fileSourceVOList[0].sourceLangId" class="input-block-level m_top20"><option value="0">Select Language</option></select></select>';

		str+='        </div>';
		str+='</div>';
		str+='<div id="source0newfile"></div>';
		str+='<div class="container m_top5">';
		str +='<span class="help-block">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory.</span>';
		str+='<div class="span12 ">         <label><strong>Detailed News Description</strong></label>         <textarea maxlength="330" name="fileSourceVOList[0].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls" id="aaanewsfileDescription" style="width: 900px;"></textarea><span class="help-block">&nbsp;&nbsp;&nbsp;<input style="margin-top: -1px;" name="fileSourceVOList[0].newsDescCheck" onclick="changeToEEnadutxt(\'newsdetdescchk\',\'aaanewsfileDescription\');" id="newsdetdescchk" type="checkbox"/>&nbsp;Please check if title is from eenadu.net</span>       </div>';

		str+='        </div>';
			 
		str+='</div>';
		str+='</div>';
		str+='<div class="">';
		str+='      <a href="javascript:void(0);" onclick="addNewFilePart(0);" class="btn btn-success span5" style="margin-left: 15px;">Clik here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source                   </a></div></div>';
        str+='<div id="addNewSourceToExisting"></div>';


		str+='<div class="row-fluid">';
		str+='      <a style="margin-top:-30px" class="offset6 span5 btn btn-danger" onclick="addNewFileSource();" href="javascript:void(0);" style="margin-top: -60px;">Click here to  <span class="label ">Add <i class="icon-plus-sign icon-white"></i></span> another source</a></div></div>';
		str+='<div class="row-fluid"><div class="container m_top10" style="padding:5px 15px 15px 15px;width: 920px;border:1px solid #ADC248;margin-left:0px;border-radius:5px;width:906px;"><legend class="">Select News Location</legend><div class="span12 ">    <div class="row-fluid">    <div class="span2">    <label>Location Scope    </label>';
		str += '<select class="input-block-level" id="scopeDiv" name="locationScope" onchange="getLocations(this.options[this.selectedIndex].value)"></select></div>';
        str +='<div id="showScopeSubs"></div>';
		str +='</div><div id="showScopeSubs" style="margin-left: 160px;"></div></div>    </div></div></div><div class="form-actions text-center"><input type="button" id="uploadNewsBtnId" onclick="uploadFile()" value="Save changes" class="btn btn-success btn-large">                         </div></div></div>';
       
	   if(fileId != null)
        str +='<input type="hidden" name="responseFileIdsStr" value="'+fileId+'">';  	
	  
	  str+='</form>';
   
  
    str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';  
    

  
	
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod("News Gallary","whomegallaryId");

	$("#newsdatedatepic").datepicker({ dateFormat: 'dd/mm/yy' });
    $("#newsdatedatepic").datepicker("setDate", new Date());
	
	 getScopes();
	 getSource("filesourceId0");
	getLanguage('sourceLangId0');
	 getNewsImportance();


	  getScopeForUser();


	 getBenefitList();
	 getPartiesList("partiesList","partiesListForWhome");
	 

$("#keywordId").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


  
  $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


		$("#KeywordPartiesListForNewsTo").multiselect({
	  noneSelectedText:"Select Party"});
		$("#KeywordPartiesListForNewsTo").multiselect('refresh');
		$("#KeywordPartiesListForNewsTo").multiselect('create');

$('#newsdatedatepic').val('');
}
var addSource = 0;
function addNewFileSource(){
        addSource = addSource+1;
        var str ='';
        str+='<div id="newfilesourceremov'+addSource+'" class="row-fluid ">';
		str+='    <div class="span12">';
		str+='        <div class="row-fluid">';
		str+='<div class="container ">';
		str+='        <div class="span4" style="width: 275px;">';
		str+='        <label><strong>File Path</strong></label>';
			   
		str+='<br/><input type="file" name="fileSourceVOList['+addSource+'].sourceFileList[0].fileImage" class="btn fileImgCls" key="'+addSource+'aaanewsfileDescription">';
		

		str+='        </div>';

		str+='<div class="span2 ">';
		str+='        <label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>';
		str+='        <select  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsEdition"  class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
		str+='        <label><strong>Page Number</strong><span class="requiredFont">*</span></label>';
		str+='        <input type="text" name="fileSourceVOList['+addSource+'].sourceFileList[0].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
		str+='        <label><strong>News Length</strong><font class="requiredFont">*</font></label>';
		str+='        <input type="text"  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>File Source</strong></label>';
		str+='        <select id="'+addSource+'addfilenewSource" style="margin-top:25px;" name="fileSourceVOList['+addSource+'].fileSourceId"  class="input-block-level m_top20"><option value="0">Select Source</option></select>';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>News Language</strong><i class="icon-trash" title="Click here to remove this source" onclick="removeThisFileSource(\'newfilesourceremov'+addSource+'\')" style="margin-left: 20px;"></i></label>';
		str+='        <select  id="'+addSource+'addfilenewLanguage" style="margin-top:25px;"  name="fileSourceVOList['+addSource+'].sourceLangId" class="input-block-level m_top20"><option value="0">Select Language</option></select></select>';

		str+='        </div>';
		str+='</div>';
		str+='<div id="source'+addSource+'newfile"></div>';
		str+='<div class="container m_top5">';
		str +='<span class="help-block">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory.</span>';
		str+='<div class="span12 ">         <label><strong>Detailed News Description</strong></label>         <textarea maxlength="330" id="'+addSource+'aaanewsfileDescription" name="fileSourceVOList['+addSource+'].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls" style="width: 875px;" ></textarea> <span class="help-block">&nbsp;&nbsp;&nbsp;<input id="'+addSource+'newsdetdescchk" onclick="changeToEEnadutxt(\''+addSource+'newsdetdescchk\',\''+addSource+'aaanewsfileDescription\');" style="margin-top:-1px;" name="fileSourceVOList['+addSource+'].newsDescCheck" type="checkbox"/>&nbsp;Please check if detailed news description is from eenadu.net</span>       </div>';

		str+='        </div>';
			 
		str+='</div>';
		str+='</div>';
		str+='<div class="">';
		str+='      <a href="javascript:void(0);" onclick="addNewFilePart('+addSource+');" class="btn btn-success span6">Clik here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source                   </a></div></div>';
        $("#addNewSourceToExisting").append(str);
		getSource(addSource+'addfilenewSource');
	getLanguage(addSource+'addfilenewLanguage');
}
function changeToEEnadutxt(ck,id){
   
   if($('#'+ck).is(':checked')){
     $('#'+id).addClass('enadu');
   }else{
		 $('#'+id).removeClass('enadu');
   }
}
var addFile = 0;
function addNewFilePart(id){
     addFile = addFile+1;
        var str ='';
        str+='<div id ="'+addFile+'newpart'+id+'" class="container ">';
		str+='        <div class="span4" style="width: 275px;">';
				
		str+='<input type="file" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].fileImage" class="btn addFileImgCls">';

		str+='        </div>';

		str+='<div class="span2 ">';
				
		str+='        <select name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsEdition" class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
			  
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].pageNo" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
			   
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsLength"  class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
			   
		str+='        <a href="javascript:void(0);" onclick="removeThisFile(\''+addFile+'newpart'+id+'\')" class="btn btn-block"><i class="icon-trash"></i> Delete This Row</a>';

		str+='        </div>';

		str+='</div>';
		
		$("#source"+id+"newfile").append(str);
}
var who = 0;  
function addNewFrom(){
 who = who+1;
 var str ='';
 str+='    <div id="whocandidate'+who+'" style="margin-left: 0px;" class="row alert alert-warning">';
		str+='    <div class="span5 well well-small ">';
		str+='<label><strong>Select Party</strong></label><select class="input-block-level" id="partiesList'+who+'" name="candidatePartyNewsVOList.sourceVOList['+who+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForParty'+who+'\')">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" >TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" id="candidateListForParty'+who+'Img" style="display:none;"/>';
		str+='</div>';

		str+='    <div class="span5 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForParty'+who+'" partylistid="partiesList'+who+'"></span>';
		str +='<select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].candidateId" id="candidateListForParty'+who+'" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		
		str+='</div>';
		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].benefitId">';
        str +='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
        str +='   </select>';
		
		str+='    </div>';
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whocandidate'+who+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	';	   
		str+='</div>  ';
		

$( "#whoTalkedMainDIV").append(str);

getPartiesList("partiesList"+who+"",null);

}
function deletethisDiv(id){
 if(confirm("Do you want to delete this Party/Candidate?")){
    $("#"+id).remove();
  }
}
var whome = 0;
function addWhome(){

 whome = whome+1;
var str ='';

	    str+='    <div id="whomecandidate'+whome+'"><div class="row alert alert-warning" style="margin-left: 0px;">';
		str+='    <div class="span2 well well-small ">';
		str+='<label><strong>Select Party</strong></label><select class="input-block-level" id="partiesListForWhome'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo'+whome+'\')">';
		//str +='<option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872">TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" id="candidateListForPartyForNewsTo'+whome+'Img" style="display:none;" />';
		str+='</div>';

		str+='    <div class="span4 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesListForWhome'+whome+'" key="candidateListForPartyForNewsTo'+whome+'" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str +='<select id="candidateListForPartyForNewsTo'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].candidateId" class="input-block-level" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		//str +='<span style="display: inline-block;"><img partylistid="partiesListForWhome'+whome+'" key="candidateListForPartyForNewsTo'+whome+'" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str+='</div>';
		str+='<div class="span4 well well-small">';
		str+='<label><strong>Select Categories</strong></label><select key="keywordIdHiddenCat'+whome+'" style="width: 252px;" id="'+whome+'whomegallaryId" >';
		str+='    <option>Select Category</option>';
		str+='</select>';
		
		str+='    </span>';
		str+='</div>';

		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select name="candidatePartyNewsVOList.destinationVOList['+whome+'].benefitId" class="input-block-level">';
        str+='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
		str+='</select>';

		str+='    </div>';
		str+='<div class="span12 well well-small" style="margin-left: 0px;">';
		str+='<label><strong>Enter Keywords</strong></label><input type="text" class="input-block-level keyword'+whome+' destinationKeywords" key="keywordId'+whome+'" id="'+whome+'keywordId">';

		str+='</div>';
		str +='<input type="hidden" id="keywordId'+whome+'Hidden" name="candidatePartyNewsVOList.destinationVOList['+whome+'].keywordsList" />';
        str +='<input type="hidden" id="keywordIdHiddenCat'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].categoryIdsStr" />';		   
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whomecandidate'+whome+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	   </div>';


$( "#whomeTalkedMainDIV").append(str);


$("#"+whome+"keywordId").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});
getPartyGallariesForUplaod("News Gallary",whome+"whomegallaryId");
getPartiesList("partiesListForWhome"+whome+"",null);

}
var o = 0;
function addNewFileToUpload(id){
   o = o+1;
   $("#multifile"+id).append('<div id="removeThisFil'+o+'"><table><tr><td><input type="file" name="fileSourceVOList['+id+'].fileImage"  size="25"  /></td><td><a class="btn btn-small btn-block" style="width: 17px;" href="javascript:void(0)" onclick="removeThisFile(\'removeThisFil'+o+'\');"> <i class="icon-minus-sign"></i></a></td></tr></table></div>');

}
function removeThisFile(id){
 if(confirm("Do you want to delete this row?")){
  $("#"+id).remove();
  }
}
var t =0;
function addNewSourceToDiv(){
t=t+1;
var str='';
str += '        <tr id="newFileSourceFile'+t+'">';
str += '            <td><input  type="file" name="fileSourceVOList['+t+'].fileImage"  size="25"  /><div id="multifile'+t+'"></div>';
str += '<a href="javascript:void(0)" onclick="addNewFileToUpload('+t+');" style="width: 100px; margin-left: 51px; margin-top: 11px;" class="btn btn-small btn-block"> <i class="icon-plus-sign"></i></a></td>';
str += '            <td><select id="filesourceId'+t+'" name="fileSourceVOList['+t+'].fileSourceId" class="input-small"><option value="0">Select Source</option></select></td>';
str += '            <td><select id="sourceLangId'+t+'" name="fileSourceVOList['+t+'].sourceLangId" class="input-small"><option value="0">Select Language</option></select></td>';
str += '            <td><select id="newsedition'+t+'" name="fileSourceVOList['+t+'].newsEdition" class="input-small"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
str += '            <td><input type="text" id="pageno'+t+'" name="fileSourceVOList['+t+'].pageNo" class="pageno input-small"  onKeyup="IsNumeric(this.value);"></input></td>';
str += '            <td><input type="text" id="newslength'+t+'" class="newslength input-small" name="fileSourceVOList['+t+'].newsLength" onKeyup="IsNumeric1(this.value);"></input></td>';
str += '            <td><textarea  id="completeDesc'+t+'" name="fileSourceVOList['+t+'].completeDesc" class="completeDescCls'+t+' input-block-level"></textarea></td>';
str += '            <td><a onclick="removeThisFileSource(\'newFileSourceFile'+t+'\');" href="javascript:void(0)" style="width: 17px;" class="btn btn-small btn-block"> <i class="icon-minus-sign"></i></a></td>';
str += '               </tr>';

$('#uploadFilesTable > tbody:last').append(str);
  getSource("filesourceId"+t);
	getLanguage('sourceLangId'+t);
}
function removeThisFileSource(id){
  if(confirm("Do you want to delete this Source?")){
  $("#"+id).remove();
  }
}
function addMoreFilesForPartyCandidate()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'" class="moreFileDivCls" key="'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="fileSourceVOList['+fileCount+'].fileImage" class="newsFileId'+fileCount+'" size="25"  /></td>';
    str +='<td><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreImagesForPartyCandidate(\''+fileCount+'\')" title="Click here to add more images" alt="Click here to add more images"></td>';

	str += ' </tr>';
    
	str +='<tr>';
	str +='<td colspan="2"><div id="moreFilePathsImagesDiv'+fileCount+'"></div></td>';
	str +='</tr>';

	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceVOList['+fileCount+'].fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="fileSourceVOList['+fileCount+'].sourceLangId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsEdition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+fileCount+'" name="fileSourceVOList['+fileCount+'].pageNo" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+fileCount+'" class="newslength newsLengthCls" name="fileSourceVOList['+fileCount+'].newsLength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';

	/*str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';*/
	str += '   </tr>';
    
	str +='<tr>';
	str +='<td>News description in details : </td>';
	str +='<td><textarea rows="3" columns = "20" id="completeDesc'+fileCount+'" name="fileSourceVOList['+fileCount+'].completeDesc" class="completeDescCls'+fileCount+'"></textarea></td>';
	str +='<td><input type="checkbox" id="newsDescCheck'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsDescCheck" value="true" class="newsDescCheckId" key="'+fileCount+'" /><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
	str +='</tr>';

	str +='</table>';
	moreDivElmt.innerHTML = str;
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	getSource("filesourceId"+fileCount+"");
	getLanguage('sourceLangId'+fileCount+'');
	fileCount++;
}

function addMoreImagesForPartyCandidate(count)
{
  
  
  var divElmt = document.createElement("moreFilePathsImagesDiv"+count+"");
  var str = '';
  str +='<table id="moreFilePathsId'+count+''+fileImgCount+'">';
  str += '<tr>';
  str += '<td>File Path : </td>';
  str += '<td><input type="file" class="newsFileId'+count+'" size="50" name="fileSourceVOList['+count+'].fileImage"/></td>';
  str += '<td><img onclick="deleteFileImagePaths(\''+count+''+fileImgCount+'\');" title="Click here to delete file" src="images/minus.png" style="background: #fff; border-radius: 11px; padding: 4px;"></td>';
  str +='<tr>';
  str +='</table>';
  divElmt.innerHTML = str;
  document.getElementById("moreFilePathsImagesDiv"+count+"").appendChild(divElmt);
  
  fileImgCount++;
}

function deleteFileImagePaths(imgId)
{
 
 $('#moreFilePathsId'+imgId+'').html('');
}

function getPartyCandidatesList()
{
	
  if(partyIdsListArray != null && partyIdsListArray.length > 0)
  {
    
	 buildPartyKeywordsDiv();

	 var partyIds = new Array();
	 for(var i=0;i<partyIdsListArray.length;i++)
	  partyIds.push(partyIdsListArray[i].id);

	 var jsObj={
		partyIds:partyIds,
		task:'getCandidatesByPartyIds'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidatesByPartyIdsAction.action?"+rparam;
	callAjax(jsObj, url);
  }
}

function buildCandidateList(result)
{
	
	$("#candidateListForParty").find('option').remove();
 if(result != null && result.length > 0)
 {
    $.each(result,function(index,value){
			$("#candidateListForParty").append($("<option></option>").attr("value",value.id).text(value.name));
	});
	

 }

}


function buildPartyKeywordsDiv()
{
  
  $('#partyKeywordsDiv').html('');
  var divElmt = document.createElement("partyKeywordsDiv");
  var str = '';
  str +='<table>';
  
  
  for(var i=0;i<partyIdsListArray.length;i++)
  {
   str += '<tr>';
   str +='<td>'+partyIdsListArray[i].name+'</td>';
   str += '  <td class="selectWidthPadd"><input type="text" name="keywordList1" id="keywordListId1"/></td>';
   str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteSelectedParty(\'party'+partyIdsListArray[i].id+'\')"></td>';
   str += '</tr>';
   str +='<input type="hidden" value="'+partyIdsListArray[i].id+'" name="partyKeywordsList"/>';
  }
  
  str +='</table>';
  divElmt.innerHTML = str;
  
 

  document.getElementById("partyKeywordsDiv").appendChild(divElmt);
   $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});
}








function buildCandidateKeywordsList()
{
  
  $('#candidateKeywordsDiv').html('');
  var divElmt = document.createElement("candidateKeywordsDiv");
  var str = '';
  str +='<table id="moreFilePathsId'+count+''+fileImgCount+'">';
  
  
  for(var i=0;i<candidateIdsListArray.length;i++)
  {
   str += '<tr>';
   str +='<td>'+candidateIdsListArray[i].name+'</td>';
   str += '  <td class="selectWidthPadd"><input type="text" name="keywordList1" id="keywordListId1"/></td>';
   str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" ></td>';
   str += '</tr>';
   str +='<input type="hidden" value="'+candidateIdsListArray[i].id+'" name="candidateKeywordsList"/>';
  }
  
  str +='</table>';
  divElmt.innerHTML = str;
  
  document.getElementById("candidateKeywordsDiv").appendChild(divElmt);
   $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});

   /*//sourceCandidateIdsArray
   
   var divElmt = document.createElement("newsFromCanListLabel");
   var str = '';
  
  for(var i=0;i<candidateIdsListArray.length;i++)
  {
	if(sourceCandidateIdsArray.indexOf(candidateIdsListArray[i].id) == -1)
	{
	 str +='<label class="btn" id="sourceCan'+candidateIdsListArray[i].id+'" class="sourceCandidateImgCls">';
	 str +=''+candidateIdsListArray[i].name+'';
	 str +='<img src="images/closeImg.png" key="'+candidateIdsListArray[i].id+'" class="candidateCloseImg"/>';
	 str +='</label>';
	 sourceCandidateIdsArray.push(candidateIdsListArray[i].id);
	}
  }
  divElmt.innerHTML = str;
 document.getElementById("newsFromCanListLabel").appendChild(divElmt);*/
  
}

function buildSourceCandidateKeywordsList(){

 var divElmt = document.createElement("newsFromCanListLabel");
   var str = '';

 if(sourceCandidatesArray != null && sourceCandidatesArray.length > 0)
  for(var i=0;i<sourceCandidatesArray.length;i++)
  {
	if(sourceCandidateIdsArray.indexOf(sourceCandidatesArray[i].id) == -1)
	{
	 str +='<label class="btn" id="sourceCan'+sourceCandidatesArray[i].id+'" class="sourceCandidateImgCls">';
	 str +=''+sourceCandidatesArray[i].name+'';
	 str +='<img src="images/closeImg.png" key="'+sourceCandidatesArray[i].id+'" class="candidateCloseImg"/>';
	 str +='</label>';
	 sourceCandidateIdsArray.push(sourceCandidatesArray[i].id);
	}
  }
  divElmt.innerHTML = str;
 document.getElementById("newsFromCanListLabel").appendChild(divElmt);

}

function buildDestinationCandidateKeywordsList(){

 var divElmt = document.createElement("newsToCanListLabel");
   var str = '';
  if(destinationCandidatesArray != null && destinationCandidatesArray.length > 0)
  for(var i=0;i<destinationCandidatesArray.length;i++)
  {
	if(destinationCandidateIdsArray.indexOf(destinationCandidatesArray[i].id) == -1)
	{
	 str +='<label class="btn" id="destinationCan'+destinationCandidatesArray[i].id+'" class="destinationCandidateImgCls">';
	 str +=''+destinationCandidatesArray[i].name+'';
	 str +='<img src="images/closeImg.png" key="'+destinationCandidatesArray[i].id+'" class="destinationCandidateCloseImg" id="descCan'+destinationCandidatesArray[i].id+'"/>';
	 str +='</label>';
	 destinationCandidateIdsArray.push(destinationCandidatesArray[i].id);
	}
  }
  divElmt.innerHTML = str;
 document.getElementById("newsToCanListLabel").appendChild(divElmt);

}



function deleteSelectedParty(id)
{
	

 $("#"+id+"").closest('table').html('');
}

function getCandidatesListByPartyId(partyId,type)
{
    $("#"+type+"Img").css("display","block");
		var jsObj = {
			partyId :partyId,
			type:type,
			task : "getCandidatesListByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
   callAjax(jsObj,url);
}



function setSourceDestinationPartyCandidateIds()
{

 if(sourcePartyArray != null && sourcePartyArray.length > 0)
 {
   var str = '';
   for(var i in sourcePartyArray)
    str +=sourcePartyArray[i]+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#sourcePartyListId").val(str);
 }

 if(destinationPartyArray != null && destinationPartyArray.length > 0)
 {
   var str = '';
   for(var i in destinationPartyArray)
    str +=destinationPartyArray[i]+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#destinationPartyListId").val(str);
 }

 if(sourceCandidatesArray != null && sourceCandidatesArray.length > 0)
 {
   var str = '';
   for(var i in sourceCandidatesArray)
    str +=sourceCandidatesArray[i].id+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#sourceCandidatesListId").val(str);
 }

 if(destinationCandidatesArray != null && destinationCandidatesArray.length > 0)
 {
   var str = '';
   for(var i in destinationCandidatesArray)
    str +=destinationCandidatesArray[i].id+",";

   var length = str.length;
    str = str.substr(0,length-1); 

	$("#destinationCandidateListId").val(str);
 }

}

$(document).ready(function(){
	
 $(".newsDescCheckId").live("click",function(){
	 
	var key = $(this).attr("key");	
	
	if($(this).is(':checked'))
	 $("#completeDesc"+key+"").addClass('enadu');
	else
     $("#completeDesc"+key+"").removeClass('enadu');
 
 });


//news From
$("#newsFromCanCheckboxId").live("click",function(){

  if($(this).is(':checked'))
	{
     $("#newsFromCandidateDiv").css("display","block");
	  $("#newsFromPartyDiv").css("display","none");
	 $("#newsFromPartyCheckboxId").attr("checked",false);
	 $("#newsFromBothCheckboxId").attr("checked",false);
	}
  else
	$("#newsFromCandidateDiv").css("display","none");
});

$("#newsFromPartyCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	 $("#newsFromPartyDiv").css("display","block");
     $("#newsFromCandidateDiv").css("display","none");
	 $("#newsFromCanCheckboxId").attr("checked",false);
	 $("#newsFromBothCheckboxId").attr("checked",false);

	}
  else
	$("#newsFromPartyDiv").css("display","none");

});

$("#newsFromBothCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	
	 $("#newsFromCandidateDiv").css("display","block");
	 $("#newsFromPartyDiv").css("display","block");

	 $("#newsFromCanCheckboxId").attr("checked",false);
	 $("#newsFromPartyCheckboxId").attr("checked",false);
	}
  else{
	 $("#newsFromCandidateDiv").css("display","none");
	$("#newsFromPartyDiv").css("display","none");
  }

});


//news to
$("#newsToCanCheckboxId").live("click",function(){
  if($(this).is(':checked'))
	{
	 $("#newsToCandidateDiv").css("display","block");
	 $("#newsToPartyDiv").css("display","none");
     $("#newsToPartyCheckboxId").attr("checked",false);
     $("#newsToBothChechboxId").attr("checked",false);

	}
  else
	$("#newsToCandidateDiv").css("display","none");
});

$("#newsToPartyCheckboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	 $("#newsToPartyDiv").css("display","block");
	 $("#newsToCandidateDiv").css("display","none");
     $("#newsToCanCheckboxId").attr("checked",false);
     $("#newsToBothChechboxId").attr("checked",false);
	}
  else
	$("#newsToPartyDiv").css("display","none");

});

$("#newsToBothChechboxId").live("click",function(){
	
  if($(this).is(':checked'))
	{
	
	 $("#newsToCandidateDiv").css("display","block");
	 $("#newsToPartyDiv").css("display","block");
	 $("#newsToCanCheckboxId").attr("checked",false);
     $("#newsToPartyCheckboxId").attr("checked",false);
	}
  else{
	 $("#newsToCandidateDiv").css("display","none");
	$("#newsToPartyDiv").css("display","none");
  }

});

//candidate creation
$(".createNewCandidate").live("click",function(){
	
	
		$("#createCandidateDiv").dialog({
            modal: true,
            title: "<b>Create New Candidate</b>",
			width: 500,
            height: 300
           
        });
  
    $("#createCandidateInnerDiv").html('');
	
	var key = $(this).attr("key");
	var partyListId = $(this).attr("partyListId");
	
   var str = '';
   str +='<div>';
   str +='<div id="errorMsgDiv"></div>';
   str +='<table style="margin-top: 24px;"><tr>';
   str +='<td>Select Party</td>';
   str +='<td><select id="partySelectNewList">';
   
   /*str +='<option value="0">Select</option><option value="163">BJP</option>';
   str +='<option value="265">CPI</option><option value="269">CPM</option>';
   str +='<option value="362">INC</option><option value="990">MIM</option>';
   str +='<option value="872">TDP</option><option value="886">TRS</option>';
   str +='<option value="1117">YSRCP</option>';*/
   
   str +='</select></td></tr>';

   str +='<tr><td>Candidate Name</td>';
   str +='<td><input type="text" id="newCandidateName"/></td></tr>';
   str +='<tr>';
   str +='<td>Designation</td>';
   str +='<td><select id="designationsList"></select></td>';
   str +='</tr>';
   str +='</table>';
   str +='<input type="button" value="submit" class="btn" id="createCandidateId" key="'+key+'" partyListId="'+partyListId+'"/>';
   str +='</div>';
   $("#createCandidateInnerDiv").html(str);
   
   getPartiesList("partySelectNewList",null);
   getDesignationList("designationsList");
   
});


//candidate creation

$("#createCandidateId").live("click",function(){
    
	$("#errorMsgDiv").html('');
	var partyId = $("#partySelectNewList").val();
	var candidateName = $.trim($("#newCandidateName").val());
	var designationId = $("#designationsList").val();
	
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
	
	var jsObj =
		{ 
            partyId : partyId,
			candidateName:candidateName,
			candidateListId:candidateListId,
			partyListId:partyListId,
			designationId:designationId,
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


jQuery.removeFromArray = function(value, arr) {
return jQuery.grep(arr, function(elem, index) {
return elem !== value;
});
};


$(".newsResponseCheckId").live("click",function(){
 var fileId = $(this).attr('value');
  if($(this).is(':checked') && responseFileIdsArray.indexOf(fileId) == -1)
   responseFileIdsArray.push(fileId);
  else{
   if(responseFileIdsArray.indexOf(fileId) != -1)
    responseFileIdsArray = jQuery.removeFromArray(fileId, responseFileIdsArray);
  }
   var length = responseFileIdsArray.length;
 $("#selectedNewsCount").html(''+length+''); 
});

	
});//End ready
function testValues(){
  
   $('.destinationKeywords').each(function() {
   var str = '';
			var $ul = $(this).closest('ul');
			  $ul.find('li').each(function(){
			  str +=''+$(this).text()+',';
			  //alert($(this).text());
			});	
           
        });
}
function getReportFiles(id){
	  var win=window.open('createReportAction.action?reportId='+id, '_blank');
	   win.focus();
	}
	
function generateKey(reportId,id){
   var jsObj =
		{ 
            reportId : reportId,
			id:id,
			task:"generateKeyForReport"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "generateReportKeyAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showHideLocationLvl(key){
if(key == 'level'){
   $(".regionLvlClass").show();
   $('.regionClass').each(function() {
          $(this).hide();
   });
}else if(key == 'location'){
   $(".regionLvlClass").hide();
	 $(".regSelReport").show();
   showCorrespondingLocations();
}
}

function showCorrespondingLocations(){
 var loc = $("#reportRegionLevel").val();
 if(loc == 1){
  showHideLocations(false,false,false);
 }else if(loc == 2){
  showHideLocations(true,false,false);
 }else if(loc == 3){
  showHideLocations(false,true,false);
 }else if(loc == 4){
  showHideLocations(false,false,true);
 }
}

function showHideLocations(dist,pc,ac){
 if(dist){
  $(".districtSelReport").show();
 }else{
  $(".districtSelReport").hide();
 }
 if(pc){
  $(".parliamSelReport").show();
 }else{
  $(".parliamSelReport").hide();
 }
 if(ac){
  $(".assembSelReport").show();
 }else{
  $(".assembSelReport").hide();
 }
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

</script>
</body>
</html>