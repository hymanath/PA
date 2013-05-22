<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problems Search</title>
</head>
<body>
  
  
  
  
 <link href="styles/rating/jquery.rating.css" rel="stylesheet" type="text/css">
<script src="js/rating/jquery.rating.js" type="text/javascript"></script>
<script src="js/rating/jquery.MetaData.js" type="text/javascript"></script>

	<script type="text/javascript" src="js/highcharts/js/highcharts.js"></script>
<link type="text/css" href="styles/assets/css/problemmanagstyle.css" rel="stylesheet">
<!-- pagination files -->


<script type="text/javascript" src="jPaginate/jquery.paginate.js"></script>
<link rel="stylesheet" type="text/css" href="jPaginate/css/style.css">


<!-- pagination files -->
</head><body>
<style>

.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
    background: url("images/ui-bg_glass_75_e6e6e6_1x400.png") repeat-x scroll 50% 50% #E6E6E6;
    border: 1px solid #D3D3D3;
    color: #555555;
    font-weight: normal;
}
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
    background: url("images/ui-bg_glass_75_e6e6e6_1x400.png") repeat-x scroll 50% 50% #E6E6E6;
    border: 1px solid #D3D3D3;
    color: #555555;
    font-weight: normal;
}
.ui-datepicker td span, .ui-datepicker td a {
    display: block;
    padding: 0.2em;
    text-align: right;
    text-decoration: none;
}


.ui-state-active, .ui-state-default {
    background-image: url("../../images/icons/homePage_new/accordianHeader.jpeg");
}
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
    background: url("images/ui-bg_glass_75_e6e6e6_1x400.png") repeat-x scroll 50% 50% #E6E6E6;
    border: 1px solid #D3D3D3;
    color: #555555;
    font-weight: normal;
}

.ui-datepicker table {
    border-collapse: collapse;
    font-size: 0.9em;
}

.ui-helper-clearfix:before, .ui-helper-clearfix:after {
    content: "";
    display: table;
}
.ui-helper-clearfix:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}
.ui-helper-clearfix:before, .ui-helper-clearfix:after {
    content: "";
    display: table;
}
.ui-datepicker .ui-datepicker-header {
    padding: 0.2em 0;
    position: relative;
}

.ui-widget-header {
    background: url("images/ui-bg_highlight-soft_75_cccccc_1x100.png") repeat-x scroll 50% 50% #CCCCCC;
    border: 1px solid #AAAAAA;
    color: #222222;
    font-weight: bold;
}

.ui-widget-header {
    background: url("images/ui-bg_highlight-soft_75_cccccc_1x100.png") repeat-x scroll 50% 50% #CCCCCC;
    border: 1px solid #AAAAAA;
    color: #222222;
    font-weight: bold;
}

.ui-datepicker {
    display: none;
    padding: 0.2em 0.2em 0;
    width: 15em;
}
.ui-datepicker table {
    border-collapse: collapse;
    font-size: 0.9em;
}
.ui-datepicker table {
    border-collapse: collapse;
    font-size: 0.9em;
    margin: 0 0 0.4em;
    width: 100%;
}

.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year {
    width:36%;
}
    .ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus {
    background: url("images/ui-bg_glass_75_dadada_1x400.png") repeat-x scroll 50% 50% #DADADA;
    border: 1px solid #999999;
    color: #212121;
    font-weight: normal;
}ui-datepicker-next
.ui-datepicker .ui-datepicker-prev span, .ui-datepicker .ui-datepicker-next span {
    display: block;
    left: 50%;
    margin-left: -8px;
    margin-top: -8px;
    position: absolute;
    top: 50%;
}
.ui-widget-header .ui-icon {
    background-image: url("images/ui-icons_222222_256x240.png");
	margin-left: 4px;
    margin-top: 6px;
}
.ui-datepicker .ui-datepicker-prev, .ui-datepicker .ui-datepicker-next {
    height: 1.8em;
    position: absolute;
    width: 1.8em;
}

.ui-datepicker-next{
  margin-left:199px;
}
.ui-datepicker-month{
  margin-left:30px;
}
.ui-datepicker-prev{
  padding-top:0px;
  padding-left:0px;
  
}
.validatecolor{
  color:red;
  margin-left:10px;
}
.span3{
  width: 227px;
}
 #locationstateimg,#locationscopsimg,#locationdistrictimg,#locationassmbconstimg,#thslmuncplycorpimg,#departmentstateimg{
  display:none;
}
#statisticalDataBodyDiv_head {
    padding: 10px;
}
#problemcheckboxes {
    background-color: #EEF4F6;
    font-size: 11px;
    font-weight: bold;
    height: 40px;
    padding-top: 20px;
    width: 705px;
}
#probnotexisterrmsg{
  font-weight: bold;
  margin-left:136px;
}
.anchorStyle{
 font-weight:bold;
}
#problemInfoDivHead,#problemInfoDivBodyCadrDeptHid{
 margin-top: 40px;
 margin-bottom: 20px;
 border-radius: 3px;
}
#problemInfoDivBody,#problemInfoDivBodyCadrDept{
 border:1px solid #D3D3D3;
}
#floatContainerDiv {
background: none repeat scroll 0 0 #FFFFFF;
    
    margin-left: 4px;
    padding: 16px;
    width: 187px;

}
</style>
<script type="text/javascript">
var impactlvl ;
var bothData;
google.load("visualization", "1", {packages:["corechart"]});
$(document).ready(function(){
		$("#fromDate").datepicker({
			changeMonth: true,
			changeYear: true
		});
		$("#toDate").datepicker({
			changeMonth: true,
			changeYear: true
		});
		$("#fromDate").datepicker('option', {maxDate: new Date()});
		$("#toDate").datepicker('option', {maxDate: new Date()});
		$("#ui-datepicker-div").hide();
	});
	
function getProblemTypes()
{
	var jsObj = {
	    time : new Date().getTime(),
		task    : "getProblemtypes"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url); 

}

function getProblemsContainStates(){
      var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  var count = 0;
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		 count = count+1;
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
		count = count+1;
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
		count = count+1;
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
		count = count+1;
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
		count = count+1;
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
		count = count+1;
	 }
	}
	if(count == 0)
	 allPublicProb = "true";
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		onlyUserProb   : '',
		impactLvl      : '',
		task           : 'getProblemsContainStates'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDetailsForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getProblemsPostedUser(){
  var jsObj = {
	    time : new Date().getTime(),
		task    : "getUserDetailsForProblems"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDetailsForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getStateDetailsForDept(){
  var jsObj = {
	    time : new Date().getTime(),
		task    : "getStateDetailsForDept"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDetailsForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getDepartmentDetails(){
  var stateId = $("#departmentstate").val();
  if(stateId == 0)
  {
      $("#departmentsinstate").val('0');
	  $("#departmentsinstate").hide();
       return;
  }
  $("#departmentstateimg").show();
  var jsObj = {
	    time : new Date().getTime(),
		stateId : stateId,
		task    : "getDepartmentDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDetailsForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getProblemImpactedRegions(){
   $("#locationscops").hide();
   hideSpecifiedFields(true,true,true,true);
   var stateId = $("#locationstate").val();
    if(stateId == 0)
    {
       return;
    }
	$("#locationstateimg").show();
	var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
	 }
	}
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		stateId        : stateId,
		onlyUserProb   : '',
		impactLvl      : '',
		task           : 'getimpactregionlevels'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemScopesDetailsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getdistrictsofstate(){
    hideSpecifiedFields(true,true,true,true);
   impactlvl = $("#locationscops").val();
   var stateId = $("#locationstate").val();
   if(impactlvl != 0 && impactlvl != 2 && stateId != 0){
     $("#locationscopsimg").show();
	 var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
	 }
	}
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		stateId        : stateId,
		onlyUserProb   : '',
		impactLvl      : impactlvl,
		task           : 'getdistricts'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemScopesDetailsAction.action?"+rparam;						
	callAjax(jsObj,url); 
   }

}

function getConstituencies(){
   hideSpecifiedFields(false,true,true,true);
   
   var districtId = $("#locationdistrict").val();
   if(impactlvl == 0 || impactlvl == 3 || districtId == 0)
    return;
   var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
	 }
	}
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		districtId     : districtId,
		onlyUserProb   : '',
		impactLvl      : impactlvl,
		task           : 'getconstituencies'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemScopesDetailsAction.action?"+rparam;						
	callAjax(jsObj,url); 
   
}

function getThesilMuncplyCorp(){
hideSpecifiedFields(false,false,true,true);
  var constituencyId = $("#locationassmbconst").val();
   if(impactlvl != 0 && impactlvl != 4 && constituencyId != 0){
     var task ='';
      if(impactlvl == 5 || impactlvl == 6 || impactlvl == 9)
	    task = "getmandals";
	  if(impactlvl == 7 || impactlvl == 8)
	    task ="getmuncpcorpgmc";
		$("#locationassmbconstimg").show();
		 var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
	 }
	}
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		constituencyId : constituencyId,
		onlyUserProb   : '',
		impactLvl      : impactlvl,
		task           : task
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemScopesDetailsAction.action?"+rparam;						
	callAjax(jsObj,url); 
   }
}

function getVillageWardBooth(){
hideSpecifiedFields(false,false,false,true)
  var mandalId = $("#thslmuncplycorp").val();
   if(impactlvl != 0 && impactlvl != 5 && impactlvl != 7 && mandalId != 0){
     var task ="";
     if(impactlvl == 6)
      task = "getvillages";
	  if(impactlvl == 8)
      task = "getwards";
	 var constId ="";
	 if(impactlvl == 9){
	    task = "getbooths";
	 }
	 $("#thslmuncplycorpimg").show();
	  var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
	 }
	}
  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		mandalId       : mandalId,
		localElection  : mandalId,
		onlyUserProb   : '',
		impactLvl      : impactlvl,
		task           : task
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemScopesDetailsAction.action?"+rparam;						
	callAjax(jsObj,url); 
   }
}

function hideSpecifiedFields(district,assembly,thmuncorp,villboothward){
 if(district)
 $("#locationdistrict").hide();
 if(assembly)
 $("#locationassmbconst").hide();
 if(thmuncorp)
 $("#thslmuncplycorp").hide();
 if(villboothward)
 $("#villagewardbooth").hide();
}

function callAjax(jsObj,url){

	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
		    if(jsObj.task == "getProblemtypes")
			{
			    clearOptionsList("problemtypeselectDiv");
				createOptions("problemtypeselectDiv",myResults,"Select Problem Type");
			}
			else if(jsObj.task == "getProblemsContainStates")
			{
			   clearOptionsList("locationstate");
			   createOptions("locationstate",myResults,"Select State");
			}
			else if(jsObj.task == "getUserDetailsForProblems")
			{
			   clearOptionsList("byUserDiv");
			   createOptions("byUserDiv",myResults,"Select User");
			}
			else if(jsObj.task == "getStateDetailsForDept")
			{
			   clearOptionsList("departmentstate");
			   createOptions("departmentstate",myResults,"Select State");
			}
			else if(jsObj.task == "getDepartmentDetails")
			{
			   $("#departmentstateimg").hide();
			   clearOptionsList("departmentsinstate");
			   createOptions("departmentsinstate",myResults,"Select Department Level");
			   $("#departmentsinstate").show();
			}
			else if(jsObj.task == "getimpactregionlevels")
			{
			   $("#locationstateimg").hide();
			   clearOptionsList("locationscops");
			   createOptions("locationscops",myResults,"Select Impacted Level");
			   $("#locationscops").show();
			}
			else if(jsObj.task == "getdistricts")
			{
			   $("#locationscopsimg").hide();
			   clearOptionsList("locationdistrict");
			   createOptions("locationdistrict",myResults,"Select District");
			   $("#locationdistrict").show();
			}
            else if(jsObj.task == "getconstituencies" )
			{
			   $("#locationdistrictimg").hide();
			   clearOptionsList("locationassmbconst");
			   createOptions("locationassmbconst",myResults,"Select Constituency");
			   $("#locationassmbconst").show();
			}	
			else if(jsObj.task == "getmandals")
			{	
               $("#locationassmbconstimg").hide();
			  clearOptionsList("thslmuncplycorp");			
			   createOptions("thslmuncplycorp",myResults,"Select Mandal");
			   $("#thslmuncplycorp").show();
			}	
            else if(jsObj.task == "getmuncpcorpgmc")
			{
			  $("#locationassmbconstimg").hide();
			  clearOptionsList("thslmuncplycorp");
			  
			    createOptions("thslmuncplycorp",myResults,"Select Muncipal-Corp-Gmc");
			  
			   $("#thslmuncplycorp").show();
			}	
            else if(jsObj.task == "getwards")
			{
			    $("#thslmuncplycorpimg").hide();
			   clearOptionsList("villagewardbooth");
			   createOptions("villagewardbooth",myResults,"Select Ward");
			   $("#villagewardbooth").show();
			}
			else if(jsObj.task == "getbooths")
			{
			    $("#thslmuncplycorpimg").hide();
			   clearOptionsList("villagewardbooth");
			   createOptions("villagewardbooth",myResults,"Select Booth");
			   $("#villagewardbooth").show();
			}
			else if(jsObj.task == "getvillages")
			{
			    $("#thslmuncplycorpimg").hide();
			   clearOptionsList("villagewardbooth");
			   createOptions("villagewardbooth",myResults,"Select Village");
			   $("#villagewardbooth").show();
			}
			else if(jsObj.task == "getOverViewGraph"){
               buildInitialGraph(jsObj,myResults);
            }	
            else if(jsObj.task == "status"){
               buildStatusWiseProblemDetailsChart(myResults);
            }
            else if(jsObj.task == "cadre"){
			  if(myResults != null && myResults.length > 0){
			   var count = 0;
			     for(var j in myResults)
				  count = count+myResults[j].id;
				 if(count > 0)
                 buildCadreProblemDetailsChart(myResults,null);
			   }
            }
            else if(jsObj.task == "department"){
			   var count = 0;
			    if(myResults != null && myResults.length > 0){
			        for(var i in myResults){
				      for(var j in myResults[i].departments){
						  count = count+myResults[i].departments[j].id;
				      }
			       }
			    }
			if(count > 0)
               buildDepartmentWiseProblemsCountChart(myResults,null);
            }			
			else if(jsObj.task == "cadreanddept"){
               if(myResults != null)
			   {
			     bothData = myResults;
			     if(myResults.deptcount == "true" && myResults.cadrecount =="true" ){
				    buildCadreProblemDetailsChart(myResults.cadreWiseCount,"Cadre Wise");
				 }
				 else if(myResults.deptcount == "true"){
				   buildDepartmentWiseProblemsCountChart(myResults.deptwiseCount,null);
				 }
				 else if(myResults.cadrecount =="true"){
				   buildCadreProblemDetailsChart(myResults.cadreWiseCount,null);
				 }
			   }
            }
			else if(jsObj.task == "buildinitialmaindata"){
			   json0bject = jsObj ;
               processTheResults(myResults , jsObj.selectedPage)
            }
	}
	catch(e){   
				//alert("Invalid JSON result" + e);   
			}  
		},
	   scope : this,
	   failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				 }
	   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function buildDataGraphForBoth(type){
  if(type == "Cadre Wise")
    buildCadreProblemDetailsChart(bothData.cadreWiseCount,"Cadre Wise");
  if(type == "Department Wise")
    buildDepartmentWiseProblemsCountChart(bothData.deptwiseCount,"Department Wise");
}
function createOptions(elmtId,optionsList,textValue,startVal)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
   if(textValue != null){
	var option = document.createElement('option');
	option.value="0";
	option.text=textValue;
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}
   }
	for(var i in optionsList)
	{
	   if(optionsList[i].name != 'Select Location')
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
	  }else if(optionsList[i].name == 'Select Location'){
	    var option = document.createElement('option');
		option.value="0";
		option.text=startVal;
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
function clearOptionsList(elmtId)
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
function getProblems(){
    if(document.getElementById("departmnterrmsg") != null)
    $("#departmnterrmsg").html('');
	$("#locationerrmsg").html('');
    var locationId = '';
    var locationValue = '';
    var statusId = '';
    var problemTypeId = '';
    var departmentId = '';
    var selectedUserId = '';
    var cadre = '';
    var fromDate = '';
    var toDate = '';
	var onlyUserProb ='';
	var locinvalid = false;
	var locstr = '';
	var deptinvalid = false;
	var deptstr = '';
	var myPrivateProb = '';
	var myPublicProb = '';
	var takenByMe = '';
	var commntByMe = '';
	var allPublicProb = '';
	var postedByMe = '';
	var checkcount = 0;
	
	if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myPrivateProb = "true";
		 checkcount =checkcount+1;
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    myPublicProb = "true";
		 checkcount =checkcount+1;
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenByMe = "true";
		 checkcount =checkcount+1;
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commntByMe = "true";
		 checkcount =checkcount+1;
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    allPublicProb = "true";
		 checkcount =checkcount+1;
	 }
	}
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedByMe = "true";
		 checkcount =checkcount+1;
	 }
	}
	if(checkcount == 0){
	   if(document.getElementById("publicprobs") != null){
	     document.getElementById("publicprobs").checked = true;
	       allPublicProb = "true";
	   
	  }
	}
	if($("#locationstate").val() != 0){
        if(document.getElementById("locationscops") != null){
	       locationId = $("#locationscops").val();
		   if(locationId == 0){
		     locstr+='<span class="validatecolor">Select Impact Level</span>';
			 locinvalid = true;
		   }else{
		       if(locationId == 2){
		        locationValue = $("#locationstate").val();
		       }else if(locationId == 3){
			    locationValue = $("#locationdistrict").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select District</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 4){
			    locationValue = $("#locationassmbconst").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Constituency</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 5){
			    locationValue = $("#thslmuncplycorp").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Mandal</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 6){
			    locationValue = $("#villagewardbooth").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Village</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 7){
			    locationValue = $("#thslmuncplycorp").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Municipal-Corp-Gmc</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 8){
			    locationValue = $("#villagewardbooth").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Ward</span>';
				  locinvalid = true;
				}
			   }else if(locationId == 9){
			    locationValue = $("#villagewardbooth").val();
				if(locationValue == 0 || locationValue == "" || locationValue == null){
				  locstr+='<span class="validatecolor">Select Booth</span>';
				  locinvalid = true;
				}
			   }
		   }
	    }
		if(locinvalid)
		$("#locationerrmsg").html(locstr);
	}
	if($("#statusList").val() != 0){
	  statusId = $("#statusList").val();
	}
	if($("#problemtypeselectDiv").val() != 0){
	  problemTypeId = $("#problemtypeselectDiv").val();
	}
	if(document.getElementById("departmentstate")!= null){
    if($("#departmentstate").val() != 0){
	  departmentId = $("#departmentsinstate").val();
	  if(departmentId == 0 || departmentId == "" || departmentId == null){
		 deptstr+='<span class="validatecolor">Select Department</span>';
		 deptinvalid = true;
		}
		if(deptinvalid)
		  $("#departmnterrmsg").html(deptstr);
	}
	}
	if(document.getElementById("cadreCheckbox")!= null){
	  cadre = $('#cadreCheckbox').is(':checked') ? "true" : "";
	}
	
	if((departmentId != 0 && departmentId != "" && departmentId != null) || (cadre == "true") )
	onlyUserProb = "true";
	if(deptinvalid || locinvalid)
	  return;
	if($("#byUserDiv").val() != 0){
	  selectedUserId = $("#byUserDiv").val();
	} 
	if($.trim($("#fromDate").val()) != "" && $.trim($("#fromDate").val()).length > 0){
	  fromDate = $("#fromDate").val();
	} 
	if($.trim($("#toDate").val()) != "" && $.trim($("#toDate").val()).length > 0){
	  toDate = $("#toDate").val();
	}
     var jsObj = {
	    time : new Date().getTime(),
		locationId     : locationId ,
		locationValue  : locationValue, 
		statusId       : statusId ,
		problemTypeId  : problemTypeId ,
		departmentId   : departmentId ,
		selectedUserId : selectedUserId,
		cadre          : cadre ,
		fromDate       : fromDate,
		toDate         : toDate,
		firstResult    : 0,
		maxResult      : 10,
		onlyUserProb   : onlyUserProb,
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		selectedPage   : '',
		impactLvl      : '',
		task           : 'buildinitialmaindata'
	};
	 <s:if test="userLoginDetails.name == 'PARTY_ANALYST_USER' || userLoginDetails.name == 'BOTH' " >
	   document.getElementById("problemInfoDivBodyCadrDeptHid").style.display = 'none';
	   document.getElementById("problemInfoDivBodyCadrDept").innerHTML = '';
	   var jsObj1 = {
	    time : new Date().getTime(),
		locationId     : locationId ,
		locationValue  : locationValue, 
		statusId       : statusId ,
		problemTypeId  : problemTypeId ,
		departmentId   : departmentId ,
		selectedUserId : selectedUserId,
		cadre          : cadre ,
		fromDate       : fromDate,
		toDate         : toDate,
		firstResult    : 0,
		maxResult      : 10,
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		impactLvl      : '',
		onlyUserProb   : onlyUserProb,
		task           : '' 
	  };
	   if((departmentId != 0 && departmentId != "" && departmentId != null) && (cadre == "true") )
	     getBothDataForGraph(jsObj1);
	   else if(departmentId != 0 && departmentId != "" && departmentId != null)
	     getDeptDataForGraph(jsObj1);
	   else if(cadre == "true")
	     getCadreDataForGraph(jsObj1);
	 </s:if>
	   var jsObj2 = {
	    time : new Date().getTime(),
		locationId     : locationId ,
		locationValue  : locationValue, 
		statusId       : statusId ,
		problemTypeId  : problemTypeId ,
		departmentId   : departmentId ,
		selectedUserId : selectedUserId,
		cadre          : cadre ,
		fromDate       : fromDate,
		toDate         : toDate,
		firstResult    : '',
		maxResult      : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		onlyUserProb   : onlyUserProb,
		impactLvl      : '',
		task           : 'status'
	};
	getStatusWiseCount(jsObj2);
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCompleteProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 	
}
function getStatusWiseCount(jsObj){
    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGraphDataForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getBothDataForGraph(jsObj){
    jsObj.task = 'cadreanddept';
    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGraphDataForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getDeptDataForGraph(jsObj){
    jsObj.task = 'department';
    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGraphDataForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
}

function getCadreDataForGraph(jsObj){
    jsObj.task = 'cadre';
    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGraphDataForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 
  
}
function buildCadreProblemDetailsChart(result,type)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBodyCadrDept");

	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return;
	var tot,personal,assigned,both;
   document.getElementById("problemInfoDivBodyCadrDeptHid").style.display = 'block';
	for(var i=0;i<result.length;i++)
	{
		if(result[i].name == 'Total')
			tot = result[i].id;
		else if(result[i].name == 'PERSONAL')
			personal = result[i].id;
		else if(result[i].name == 'ASSIGNED')
			assigned = result[i].id;
		else if(result[i].name == 'Both')
			both = result[i].id;
	}

	var str = '';
	
	str +='<div id="problemDetails_main" style="width:700px;" >';
	str +='	<div id="problemDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top"  width="450px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr>';
	str +='					<th>Cadre Personal - </th>';if(personal == 0)
	str += '                <td >'+personal+'</td>';
	else
	str += '                <td> <a onClick="javascript:{};" class="anchorStyle" >'+personal+'</a></td>';
	str +='					<th>Cadre Assigned - </th><td><a onClick="javascript:{};" class="anchorStyle">'+assigned+'</a></td>';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='			</tr>';
	if(type != null){
	str +='			<tr><td>';
	   str +='<div style="padding-left:185px;">';
	   str +='<b>Select &nbsp;&nbsp;</b><select onchange="buildDataGraphForBoth(this.options[this.selectedIndex].value)"><option value="'+type+'">'+type+'</option>';
	    if(type == "Cadre Wise")
		 str +='<option value="Department Wise">Department Wise</option>';
		else
		 str +='<option value="Cadre Wise">Cadre Wise</option>';
	   str +='</select>';
	   str +='</div>';
	   str +='				</td>';
	str +='			</tr>';
	}
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<div id="statusWiseProbGraphDivCadrDept">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='</div>';
	
	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " Personal ");
	data.setValue(0, 1, personal);
	data.setValue(1, 0, " Assigned ");
	data.setValue(1, 1, assigned);
	
	var chart = new google.visualization.PieChart(document.getElementById('statusWiseProbGraphDivCadrDept'));
        chart.draw(data, {width: 670, height: 250, title: 'Cadre Wise Problems Details For The Above Selection'});
}

function buildDepartmentWiseProblemsCountChart(result,type)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBodyCadrDept");
	
	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return
    
	var total = 0,newp = 0,prog = 0,pend = 0,fix = 0;
    document.getElementById("problemInfoDivBodyCadrDeptHid").style.display = 'block';
	for(var i=0;i<result.length;i++)
	{
		total += result[i].problemsCount;

		for(var j=0;j<result[i].departments.length;j++)
		{
			var name = result[i].departments[j].name;
			var count = result[i].departments[j].id;

			if(name == 'NEW')
				newp += count;
			else if(name == 'PROGRESS')
				prog += count;
			else if(name == 'PENDING')
				pend += count;
			else if(name == 'FIXED')
				fix += count;
		}
	}

	var str = '';

	str +='<div id="problemDetails_main">';
	
	str +='	<div id="problemDetails_body" class="problemDetailsBody" >';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr>';
	str +='					<th>New - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+newp+'</a></td>';
	str +='					<th>Progress - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+prog+'</a></td>';
	str +='					<th>Pending - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+pend+'</a></td>';
	str +='					<th>Fixed - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+fix+'</a></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='				</td></tr>';
	if(type != null){
	str +='<tr><td>';
	   str +='<div style="padding-left:185px;">';
	   str +='<b>Select</b> &nbsp;&nbsp;<select onchange="buildDataGraphForBoth(this.options[this.selectedIndex].value)"><option value="'+type+'">'+type+'</option>';
	    if(type == "Cadre Wise")
		 str +='<option value="Department Wise">Department Wise</option>';
		else
		 str +='<option value="Cadre Wise">Cadre Wise</option>';
	   str +='</select>';
	   str +='</div>';
	   str +='</td></tr>';
	}
	str +='			<tr><td><div id="statusWiseProbGraphDivCadrDept"></td></tr>';
	str +='		</table>';
	str +='</div>';

	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;
	
	var data = new google.visualization.DataTable();
       
		data.addColumn('string','Department');
		data.addColumn('number','New');
        data.addColumn('number','Progress');
        data.addColumn('number','Pending');
		data.addColumn('number','Fixed');

		data.addRows(result.length);
				
		for(var i=0;i<result.length;i++)
		{
			data.setValue(i,0,result[i].deptName);
			data.setValue(i,1,result[i].departments[0].id);
			data.setValue(i,2,result[i].departments[1].id);
			data.setValue(i,3,result[i].departments[2].id);
			data.setValue(i,4,result[i].departments[3].id);
		}
		
		var chart =  new google.visualization.ColumnChart(document.getElementById('statusWiseProbGraphDivCadrDept'));
        chart.draw(data, {width: 670, height: 300, title: 'Department Wise Problems Details For The Above Selection'}); 
                         
	return;
}

function getgraphdata(){
var jsObj = {
	    time : new Date().getTime(),
		task   : 'getOverViewGraph'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "problemsOverViewGraphAction.action?"+rparam;						
	callAjax(jsObj,url); 

}
function  buildInitialGraph(jsObj,results){		
		
		
		var elmt = document.getElementById("statisticalDataBodyDiv_head");

		if(results.chartDataVO == null || results.chartDataVO.length == 0){
            elmt.innerHTML = "";
			return;
		}

		var totalCount = 0;
		var hStr = '';
		hStr += '<table class="statsData_table">';
		hStr += '<tr>';
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			totalCount += results.chartDataVO[i].problemsCount;
			hStr += '<th>'+results.chartDataVO[i].chartLabel+' - </th>';
			hStr += '<td>'+results.chartDataVO[i].problemsCount+'</td>';
		}
		hStr += '<th> Total - </th>';
		hStr += '<td>'+totalCount+'</td>';
		hStr += '</tr>';		
		hStr += '</table>';
		elmt.innerHTML = hStr;

		var chart;
		var xAxisLabels = [];
		var series = [];
		var localArray = [];
		
		// For legends (start)
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			xAxisLabels.push(results.chartDataVO[i].chartLabel);
		}
		// For legends (End)
		
		// For Coloumn Chart data (start)
		for(var i=0; i<results.chartDataVO[0].chartValues.length; i++)
		{
			var loopLocalArray = [];
			for(j=0; j<results.chartDataVO.length; j++)
			{
				loopLocalArray.push(results.chartDataVO[j].chartValues[i]);
			}
			localArray.push(loopLocalArray);
		}

		
		for(var i=0; i<results.chartDataVO[0].chartValues.length; i++)
		{
			var obj = {
						type:'column',
						name:results.chartLegends[i].name,
						data:localArray[i]
					  };
			series.push(obj);
		}
		// For Coloumn Chart data (End)
		
		// For Spline Chart data (start)
		/*var spineDataArr = [];
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			spineDataArr.push(results.chartDataVO[i].avgCount);			
		}
		var obj = {
					type:'spline',
					name:'Average',
					data:spineDataArr
				  };
		series.push(obj);*/
		// For Spline Chart data (End)
		
		// For Pie Chart data (Start)
		var pieDataArr = [];
		var color = ["#4572A7","#AA4643","#89A54E","#89D54A","#29C56E","#11A54D"];
		for(var i=0; i<results.chartDataVO.length; i++)
		{
			var obj = {
						name: results.chartDataVO[i].chartLabel,
						y: results.chartDataVO[i].problemsCount,
						color: color[i] 
					}
			pieDataArr.push(obj);
		}
		
		
		var obj = {
					type: 'pie',
					name: 'Total consumption',
					data: pieDataArr,
					center: [100, 80],
					size: 100,
					showInLegend: false,
					dataLabels: {
						enabled: false
					}
				};
		series.push(obj);
		// For Pie Chart data (End)

		chart = new Highcharts.Chart({
					chart: {
						renderTo: 'statisticalDataBodyDiv_body'
					},
					title: {
						//text: ''+results.chartTitle
						text:'Source Wise Your Problems Details'
					},
					xAxis: {
						categories: xAxisLabels
					},
					tooltip: {
						formatter: function() {
							var s;
							if (this.point.name) { // the pie chart
								s = ''+
									this.point.name +': '+ this.y +' problem(s)';
							} else {
								s = ''+
									this.x  +': '+ this.y;
							}
							return s;
						}
					},
					labels: {
						items: [{
							html: 'Total Posted Problems',
							style: {
								left: '40px',
								top: '8px',
								color: 'black'				
							}
						}]
					},
					series: series
				});
				$('#showgraph').show();
	}
	function getCadreAndDeptData(){
	
	
	}
	function getInitialProblemDetails(){
	  var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if('${userLoginDetails.name}' == 'PARTY_ANALYST_USER' || '${userLoginDetails.name}' == 'BOTH')
	  {
	     myPrivateProb = 'true';
	     myPublicProb =  'true';
	     takenByMe = 'true';
	  }
	  if('${userLoginDetails.name}' == 'FREE_USER')
	  {
	     commntByMe =  'true';
	     postedByMe = 'true';
	  }
	  if('${userLoginDetails.name}' == 'NOT_LOGGED_IN')
	  {
	     allPublicProb =  'true';
	  }
	   var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : 0,
		maxResult      : 10,
		onlyUserProb   : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		selectedPage   : 1,
		impactLvl      : '',
		task           : 'buildinitialmaindata'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCompleteProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 	
	}
    function getInitialProblemStatusCount(){
	 var myPrivateProb = '';
	  var myPublicProb = '';
	  var takenByMe = '';
	  var commntByMe = '';
	  var allPublicProb = '';
	  var postedByMe = '';
	  if('${userLoginDetails.name}' == 'PARTY_ANALYST_USER' || '${userLoginDetails.name}' == 'BOTH')
	  {
	     myPrivateProb = 'true';
	     myPublicProb =  'true';
	     takenByMe = 'true';
	  }
	  if('${userLoginDetails.name}' == 'FREE_USER')
	  {
	     commntByMe =  'true';
	     postedByMe = 'true';
	  }
	  if('${userLoginDetails.name}' == 'NOT_LOGGED_IN')
	  {
	     allPublicProb =  'true';
	  }
	  var jsObj = {
	    time : new Date().getTime(),
		locationId     : '' ,
		locationValue  : '', 
		statusId       : '' ,
		problemTypeId  : '' ,
		departmentId   : '' ,
		selectedUserId : '',
		cadre          : '' ,
		fromDate       : '',
		toDate         : '',
		firstResult    : '',
		myPrivateProb  : myPrivateProb,
		myPublicProb   : myPublicProb,
		takenByMe      : takenByMe,
		commntByMe     : commntByMe,
		allPublicProb  : allPublicProb,
		postedByMe     : postedByMe,
		maxResult      : '',
		onlyUserProb   : '',
		impactLvl      : '',
		task           : 'status'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGraphDataForProblemsAction.action?"+rparam;						
	callAjax(jsObj,url); 	
	}
	function buildStatusWiseProblemDetailsChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");

	if(!problemInfoDivBodyEle || result == null || result.length == 0){
	    if(problemInfoDivBodyEle != null){
		 
		 problemInfoDivBodyEle.innerHTML = '';
		 }
		return;
		}
		
	var statuscount = 0;
	for(var i in result){
	if(result[i].id > 0)
	  statuscount = statuscount + 1;	
	}
	if(statuscount == 0){
	  problemInfoDivBodyEle.innerHTML = '';
	  document.getElementById("problemInfoDivHead").style.display = 'none';
		return;
	}
	document.getElementById("problemInfoDivHead").style.display = 'block';
	var tot,newp,prog,pend,fix;

	for(var i=0;i<result.length;i++)
	{
		if(result[i].name == 'Total')
			tot = result[i].id;
		else if(result[i].name == 'NEW')
			newp = result[i].id;
		else if(result[i].name == 'PROGRESS')
			prog = result[i].id;
		else if(result[i].name == 'PENDING')
			pend = result[i].id;
		else if(result[i].name == 'FIXED')
			fix = result[i].id;
	}

	var str = '';
	
	str +='<div id="problemDetails_main" style="width:550px;" >';
	str +='	<div id="problemDetails_head">';
	
	str +='	</div>';
	str +='	<div id="problemDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top"  width="150px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr>';
	str +='					<th>New -</th><td><a onClick="javascript:{};" class="anchorStyle" >'+newp+'</td>&nbsp;';
	str +='					<th>Progress - </th><td><a onClick="javascript:{};" class="anchorStyle">'+prog+'</td>&nbsp;';
	str +='					<th>Pending - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+pend+'</td>&nbsp;';
	str +='					<th>Fixed - </th><td><a onClick="javascript:{};" class="anchorStyle" >'+fix+'</td>&nbsp;';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='			</tr>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<div id="statusWiseProbGraphDiv">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='</div>';
	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " New ");
	data.setValue(0, 1, newp);
	data.setValue(1, 0, " Progress ");
	data.setValue(1, 1, prog);
	data.setValue(2, 0, " Pending ");
	data.setValue(2, 1, pend);
	data.setValue(3, 0, " Fixed ");
	data.setValue(3, 1, fix);

	var chart = new google.visualization.PieChart(document.getElementById('statusWiseProbGraphDiv'));
        chart.draw(data, {width: 670, height: 250, title: 'Status Wise Problem Details For The Above Selection'});
}
function disableseluser(){
   if(document.getElementById("problemCheckbox").checked == true)
   {
     document.getElementById("byUserDiv").value = 0;
	 document.getElementById("byUserDiv").disabled  = true;
	 
   }
   else{
     document.getElementById("byUserDiv").disabled  = false;
   }
}
function clearFilterOpts(){
  $("#locationscops").hide();
   hideSpecifiedFields(true,true,true,true);
   document.getElementById("locationstate").value = 0;
   document.getElementById("problemCheckbox").checked = false;
   document.getElementById("statusList").value = 0;
   document.getElementById("problemtypeselectDiv").value = 0;
   if(document.getElementById("cadreCheckbox") != null)
    document.getElementById("cadreCheckbox").checked = false;  
   if(document.getElementById("departmentstate") != null){
     document.getElementById("departmentstate").value = 0;
	 $("#departmentsinstate").val('0');
	 $("#departmentsinstate").hide();
   }
   if(document.getElementById("publicprobs").checked == true){
   document.getElementById("byUserDiv").disabled  = false;
   }
   document.getElementById("byUserDiv").value = 0;
   document.getElementById("fromDate").value = '';
   document.getElementById("toDate").value = '';
}
function showRelatedFilters(){
  var myprivateprob = false;
  var mypublicprob = false;
  var takenbymeprob = false;
  var commentbyme = false;
  var publicprobs = false;
 if(document.getElementById("myprivateprob") != null){
	   if(document.getElementById("myprivateprob").checked){
	     myprivateprob = true;
		}
	}
	if(document.getElementById("mypublicprob") != null){
	   if(document.getElementById("mypublicprob").checked){
	    mypublicprob = true;
		
	  }
	}
	if(document.getElementById("takenbymeprob") != null){
	  if(document.getElementById("takenbymeprob").checked){
	    takenbymeprob = true;
		 
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commentbyme = true;
		 
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    publicprobs = true;
		 
	 }
	}
	if((myprivateprob || mypublicprob || takenbymeprob) && commentbyme == false && publicprobs == false){
	   document.getElementById("cadreCheckbox").disabled = false;
	   document.getElementById("byUserDiv").disabled = true;
	   document.getElementById("departmentstate").disabled = false;
	}
	else{
	  document.getElementById("cadreCheckbox").checked = false;
	  document.getElementById("cadreCheckbox").disabled = true; 
	  $("#byUserDiv").val('0');
	  document.getElementById("byUserDiv").disabled = false;
	  $("#departmentstate").val('0');
	  $("#departmentsinstate").val('0');
	  $("#departmentsinstate").hide();
	  document.getElementById("departmentstate").disabled = true;
	}
}
function showRelatedFiltersForUser(){
  var postedbyme = false;
  var commentbyme = false;
  var publicprobs = false;
 
	
	if(document.getElementById("postedbyme") != null){
	  if(document.getElementById("postedbyme").checked){
	    postedbyme = true;
		 
	 }
	}
	if(document.getElementById("commentbyme") != null){
	  if(document.getElementById("commentbyme").checked){
	    commentbyme = true;
		 
	 }
	}
	if(document.getElementById("publicprobs") != null){
	  if(document.getElementById("publicprobs").checked){
	    publicprobs = true;
		 
	 }
	}
	if(postedbyme == false ){
	   document.getElementById("byUserDiv").disabled = false;
	}
	else{ 
	  $("#byUserDiv").val('0');
	  document.getElementById("byUserDiv").disabled = true;
	}
}
function showGraph(){
$('#hidegraph').show();
$('#statisticalDataBodyDiv_head').show();
$('#statisticalDataBodyDiv_body').show();
$('#showgraph').hide();
}
function hideGraph(){
$('#hidegraph').hide();
$('#statisticalDataBodyDiv_head').hide();
$('#statisticalDataBodyDiv_body').hide();
$('#showgraph').show();
}
</script>
<article>
<div class="container"><!-- Container Opening -->

<h2 class="h1header"> Your Locality Problems</h2>

<div class="span12">
<div class="row" style="background-color:#ffffff;">
<div class="span3 left-panel m5-left">
<h4 class="subheader"><i class="icon-filter icon-white"></i>Apply Filters</h4>
<ul class="unstyled">
<li class="filter-box">
	<h4>Location</h4>
	<div id="locationerrmsg"></div>
	<select class="span2" id="locationstate" onchange="getProblemImpactedRegions();" > </select><img id= "locationstateimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="locationscops" onchange="getdistrictsofstate();" > </select><img id= "locationscopsimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="locationdistrict" onchange="getConstituencies();"> </select><img id= "locationdistrictimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="locationassmbconst" onchange="getThesilMuncplyCorp();"> </select><img id= "locationassmbconstimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="thslmuncplycorp" onchange="getVillageWardBooth();"> </select><img id= "thslmuncplycorpimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="villagewardbooth"> </select>
</li>

	
	<div style="display:none">
              <label>
                <input type="checkbox" value="option1" onclick="disableseluser();" id="problemCheckbox"> Show Problems Posted By Me &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Only
              </label>
	</div>

<li class="filter-box">
	<h4>Status</h4>
	<s:select theme="simple" cssClass="span2" id="statusList" name="probStatus" list="statusList" listKey = "id" listValue="name" /> 
</li>
<li class="filter-box">
	<h4>Problem Type</h4>
	<select class="span2" id="problemtypeselectDiv"></select>
</li>
<s:if test="userLoginDetails.name == 'PARTY_ANALYST_USER' || userLoginDetails.name == 'BOTH' " >	
<li class="filter-box">
	<h4>Cadre</h4>
	<div>
              <label>
                <input type="checkbox" value="option1" id="cadreCheckbox" title="To Enable Uncheck Commented By Me , All Public Problems On Top Check Boxes" > Cadre Details
              </label>
	</div>
</li>

<li class="filter-box">
	<h4>Department</h4>
	<div id="departmnterrmsg"></div>
	<select class="span2" title="To Enable Uncheck Commented By Me , All Public Problems On Top Check Boxes" id="departmentstate" onchange="getDepartmentDetails();" > </select><img id="departmentstateimg" src="images/icons/search.gif" ></img>
	<select class="span2" style="display:none;" id="departmentsinstate"  > </select>
	 
</li>
</s:if>
<li class="filter-box">
	<h4>By User</h4>
	<select class="span2" id="byUserDiv" title="Enable Only For All Public Problems" ></select>
</li>

<li class="filter-box">
	<h4>By Dates</h4>
	<div  style="margin-left:10px;"><b>From</b> <input type="text" style="width:100px;" id="fromDate" /></div>
	<div style="margin-top:5px;margin-left:9px;"><b>To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text" style="width:100px;" id="toDate" /></div>
</li>

</ul>
   <div id="floatContainerDiv"> 
      <input  class="btn"  type="button" value="Submit" onclick="getProblems();" />
      <input class="btn"  type="button" onclick="clearFilterOpts()" value="Reset Filters"/>
   </div>
</div> <!-- Left Panel _ Closed-->

<div class="span8 right-panel">

<s:if test="userLoginDetails.name == 'PARTY_ANALYST_USER' || userLoginDetails.name == 'BOTH' " >
 <ul>
  <li><div id="problemcheckboxes"><input type="checkbox" checked="true" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFilters();" id="myprivateprob" />My Private Problems(${myProblemsCountVO.myPrivateProbCount})&nbsp;&nbsp;<input type="checkbox" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFilters();" checked="true" id="mypublicprob" />My Public Problems(${myProblemsCountVO.myPublicProbCount})&nbsp;&nbsp;<input type="checkbox" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFilters();" checked="true" id="takenbymeprob" />Taken By Me(${myProblemsCountVO.takenByMeCount})&nbsp;&nbsp;<input type="checkbox" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFilters();" id="commentbyme" />Commented By Me(${myProblemsCountVO.commntByMecount})&nbsp;&nbsp;<input type="checkbox" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFilters();" id="publicprobs" />All Public Problems(${myProblemsCountVO.allPublicProbcount})</div></li>
 </ul>
</s:if>
<s:if test="userLoginDetails.name == 'FREE_USER' " >
 <ul> 
  <li><div id="problemcheckboxes"><input type="checkbox" checked="true" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFiltersForUser();" id="postedbyme" />Posted By Me(${myProblemsCountVO.postedByMeCount})&nbsp;&nbsp;<input type="checkbox" checked="true" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFiltersForUser();" id="commentbyme" />Commented By Me(${myProblemsCountVO.commntByMecount})&nbsp;&nbsp;<input type="checkbox" onclick="clearFilterOpts();getProblems();getProblemsContainStates();showRelatedFiltersForUser();" id="publicprobs" />All Public Problems(${myProblemsCountVO.allPublicProbcount})</div></li>
 </ul>
</s:if>
<s:if test="userLoginDetails.name == 'NOT_LOGGED_IN' " >

  <div style="display:none;"><input type="checkbox" checked="true" onclick="clearFilterOpts();getProblems();getProblemsContainStates();" id="publicprobs" />All Public Problems(${myProblemsCountVO.allPublicProbcount})</div></li>
 
</s:if>
<ul class="unstyled">
<li><div id="showgraph" style="display:none;margin-top:10px;font-weight:bold;" class="pull-right"><a href="javascript:{};" onclick="showGraph();" ><img style="margin-top:-5px;" src="images/plus.png" />&nbsp;Click Here To View</a></div></li>
<li><div id="hidegraph" style="display:none;margin-top:10px;font-weight:bold;" class="pull-right"><a href="javascript:{};" onclick="hideGraph();" ><img style="margin-top:-5px;" src="images/minus.png" />&nbsp;Click Here To Hide</a></div></li>
<li><div id="statisticalDataBodyDiv_head" style="display:none;"></div></li>
 <li><div id="statisticalDataBodyDiv_body" style="display:none;"></div></li>
</ul>
<ul class="unstyled">
<li><div id="problemInfoDivBodyCadrDeptHid" style="display:none;"><div id="problemInfoDivBodyCadrDept"></div></div></li>
</ul>
<ul class="unstyled">
<li><div id="problemInfoDivHead"><div id="problemInfoDivBody"></div></div></li>
</ul>
<!-- top sort bar -->
<ul class="breadcrumb problem-searchbar" style="margin-top: 40px;">
    <li>
    	<div class="input-append">
		
           <span class="add-on add-on-color">Sort By</span><select class="span2" style="width:121px;padding-bottom:2px;padding-top:2px;" id="prependedInput" onChange="filterTheProblemsSearched();" >
					<option value="">Select</option>
		            <option value="Problem">Problem</option>
		            <option value="location1">Location</option>
		            <option value="Rating">Rating</option>
				  </select>
              </div>
    </li>
   
	 
	 <li class="pull-right" style="margin-top:7px;">
	 <div id="demo2" style="margin-right:40px;"> </div>   
       	
    </li>
</ul>
<!-- top sort bar END-->
<!-- Problesm Display Collection -->

<div id="allProblemsDisplayDiv"></div>
<!-- Problem Display Collection End-->

<!-- Bottom sort bar -->


<!-- Bottom sort bar END-->


</div>

</div> <!-- Row [Span12]closed-->


</div>


</div><!-- container Closing-->
</article>
<script type="text/javascript">

  getProblemTypes();
  <s:if test="userLoginDetails.name == 'PARTY_ANALYST_USER' || userLoginDetails.name == 'BOTH' " >
  getStateDetailsForDept();
  getgraphdata();
  </s:if>
  <s:if test="userLoginDetails.name != 'NOT_LOGGED_IN' " >
  $("#byUserDiv").val('0');
	  document.getElementById("byUserDiv").disabled = true;
  </s:if>
  getProblemsPostedUser();
  getProblemsContainStates();
  getInitialProblemDetails();
  getInitialProblemStatusCount();
  
</script>
</body>
</html>