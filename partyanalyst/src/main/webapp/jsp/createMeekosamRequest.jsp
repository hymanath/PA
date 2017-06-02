<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> MEEKOSAM GRIEVANCE </title>
<!-- Bootstrap -->
<!--<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">-->
<link href="alertDepartment/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="alertDepartment/css/responsive.css" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="alertDepartment/css/customLess.less" rel="stylesheet" type="text/less">
<!--<link href="alertDepartment/css/custom.css" rel="stylesheet" type="text/css">-->
<!-- JQuery files (Start) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/less.js/2.7.2/less.min.js"></script>

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
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<!-- YUI Dependency files (End) -->
	
</head>  	
<body>
<div class="container" style="margin-top:30px">
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading headingColor">
					<h4 class="panel-title text-capital fontColor">Create New Grievance</h4>
				</div>
				<div class="panel-body">
					<div class="row">      
						<div class="col-sm-12">
							<h4 class="panel-title text-capital">grievance using</h4>
						</div>
						<div class="col-sm-12 m_top10">
							<label class="radio-inline">
								<input type="radio" name="typeOfSearch" class="typeOfSearch" value="aadhar"/>Aadhar Number
							</label>
							<label class="radio-inline">
								<input type="radio" name="typeOfSearch" class="typeOfSearch" value="mobile"/>Mobile Number
							</label>
							<label class="radio-inline">
								<input type="radio" name="typeOfSearch" class="typeOfSearch" value="voter"/>Voter Card
							</label>
						</div>
						<div class="m_top20 col-sm-4">
							<input type="text" class="form-control searchValue" placeholder="Enter Aadhar Number"/>
						</div>
						<div class="m_top20 col-sm-4">
							<button class="btn btn-success text-capital" onclick="searchPetitionerDetailsByVoterNoAadharNoMobileNo()">search</button>
						</div>
						<div class="col-sm-12 m_top20">
							<div id="searchPetitionerDetailsByVoterNoAadharNoMobileNo"></div>
						</div>
						<div id="buildProfileData"></div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top20">
							<h4 class="panel-title text-capital fontColor">Details From Petitioner</h4>
						</div>
						<div class="col-sm-3 m_top10">
							<label>Category <span style="color:red">*</span></label>
							<select class="selectChosen" id="categoryId">
								<option value="0">Select Category</option>
								<option value="6">Monday Grievance</option>
								<option value="7">Janmabhoomi</option>
								<option value="8">Special Grievance - SC/ST</option>
								<option value="9">General Grievance</option>
							</select>
						</div>
						<div class="col-sm-3 m_top10">
							<label>Department <span style="color:red">*</span></label>
							<select class="selectChosen" id="departmentId" onchange="getIssueTypesForDepartment();">
								<option value="0">Select Department</option>
							</select>
						</div>
						<div class="col-sm-3 m_top10">
							<label>Issue Type <span style="color:red">*</span></label>
							<select class="selectChosen" id="issueTypeId" onchange="getIssueSubTypes();">
								<option value="0">Select Issue Type</option>
							</select>
						</div>
						<div class="col-sm-3 m_top10">
							<label>Issue Sub Type <span style="color:red">*</span></label>
							<select class="selectChosen" id="issueSubTypeId" onchange="getDynamicValuesForIssue();">
								<option value="0">Select Issue Sub Type</option>
							</select>
						</div>
						<div id="buildPetitionerData" class="col-sm-12"></div>
						<div class="col-sm-12 m_top20">
							<h4 class="panel-title text-capital fontColor">Other Details</h4>
						</div>
						<div class="col-sm-12 m_top20">
							<table class="table" id="petitionerTableId">
								<thead>
									<th>District</th>
									<th>Mandal</th>
									<th>Village</th>
									<th>Survey Number</th>
									<th>Land in Acres</th>
									<th>Land in Cent</th>
									<th></th>
								</thead>
							</table>
						</div>
						<div class="col-sm-4 m_top20">
							<label>District</label>
							<select class="selectChosen" id="districtsPetitionerId">
								<option>Select District</option>
							</select>
						</div>
						<div class="col-sm-4 m_top20">
							<label>Mandal</label>
							<select class="selectChosen" id="mandalsPetitionerId">
								<option></option>
							</select>
						</div>
						<div class="col-sm-4 m_top20">
							<label>Village</label>
							<select class="selectChosen" id="villagePetitionerId">
								<option></option>
							</select>
						</div>
						<div class="col-sm-4 m_top20">
							<label>Survey Number</label>
							<input type="text" class="form-control" id="surveyNoPetitionerId"/>
						</div>
						<div class="col-sm-4 m_top20">
							<label>Land In Acres</label>
							<input type="text" class="form-control" id="landInAcresPetitionerId"/>
						</div>
						<div class="col-sm-4 m_top20">
							<label>Land In Cent</label>
							<input type="text" class="form-control" id="landInCentPeitionerId"/>
						</div>
						<div class="col-sm-4 m_top20">
							<button class="btn btn-success" id="addOneMorePetitionerId"><i class="fa fa-plus"></i> ADD MORE</button>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-8 m_top10">
							<label>Grievance Title<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgTitleId"></span></label>
							<label class="radio-inline" style="margin-bottom: 5px;">
								<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
							</label>
							<label class="radio-inline" style="margin-bottom: 5px;">
								<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
							</label>
							<input type="text" class="form-control" id="alertTitleId" name="grievanceAlertVO.alertTitle"/>
						</div>
						<div class="col-sm-12 m_top10">
							<label>Grievance Description : <span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgDescId"></span></label>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top10">
							<textarea class="form-control" id="alertdescriptionId" name="grievanceAlertVO.description"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 m_top10">
							<h4 class="text-success text-capital">assign alert to department officer</h4>
						</div>
						<div class="col-sm-4 m_top10">
							<label>Sub Department<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLvlId"></span></label>
							<select  class="selectChosen" id="subDepartmentSelectId">  
								<option value="0">Select Level</option>
								<!--<option value="5">MANDAL</option>-->
							</select>
						</div>
						<div class="col-sm-4 m_top10">
							<label>Location Level<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgLvlId"></span></label>
							<select  class="selectChosen" id="locationLevelSelectId">  
								<option value="0">Select Level</option>
								<!--<option value="5">MANDAL</option>-->
							</select>
						</div>
						<div id="parentLevelDivId"> </div>
						<div class="col-sm-4 m_top10">
							<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgOffcrId"></span></label>
							<select id="designationsId" class="selectChosen">
								<option></option>
							</select>
						</div>
						<div class="col-sm-4 m_top10">
							<label>Department Officer<span style="color:red">*</span>&nbsp;&nbsp; <span class="errorMsgClas" style="color:#FF4C64;" id="errMsgOffcrId"></span></label>
							<select id="officerNamesId" class="selectChosen">
								<option></option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="alertDepartment/js/createMeekosamRequest.js"></script>
<script type="text/javascript">
google.load("elements", "1", {
	packages: "transliteration"
});
var control;
var lang;

function onLoad() {
   lang = $("input[name=language]:checked").val();
	var options = {
		sourceLanguage:google.elements.transliteration.LanguageCode.ENGLISH,
		destinationLanguage:[''+lang+''],
		shortcutKey: 'alt+t',
		transliterationEnabled: true
	};
	// Create an instance on TransliterationControl with the required options.
	control = new google.elements.transliteration.TransliterationControl(options);
	// Enable transliteration in the textbox with id 'descrptionId'.
	if ($('#alertTitleId').length){
		control.makeTransliteratable(['alertTitleId']);
	}
	if ($('#alertdescriptionId').length){
		control.makeTransliteratable(['alertdescriptionId']);
	}
	
}
function languageChangeHandler(){
   var lang1 = $("input[name=language]:checked").val();
	if(lang1 =="en"){
		control.disableTransliteration();
	}else{
		control.enableTransliteration();
		control.setLanguagePair(
		google.elements.transliteration.LanguageCode.ENGLISH,
		lang1);
	}
}
google.setOnLoadCallback(onLoad);
</script>
</body>
</html>