<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Candidate To Ministry</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script
	src="js/jQuery/jquery-ui.min.js">
</script>
<script type="text/javascript" src="js/problemManagement/problemManagement.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/jquery-ui-1.8.14.custom.css" />
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/demos.css" />
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<style>
.tinyDateCal
{
position:absolute;
}
.mainDiv{
	margin-top:60px;
	padding:5px;
}
.selectDivStyle{
	font: bold 12px verdana;
	text-align:left;
	padding-left:125px;
	padding-top:20px;
}
.headingCss{
	font: bold 12px verdana;
	text-align:left;
	padding-left:120px;
}
.headingCss1{
	font: bold 12px verdana;
	text-align:left;
	padding-left:211px;
}
.heading{
	font: bold 14px verdana;
	color:blue;
	 padding-left: 113px;

}
.l2 {
color:navy;
font-size:12px;
font-weight:bold;
padding:5px;
}
.f3 {
-moz-border-radius:4px 4px 4px 4px;
border:2px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
width:591px;
}
.buttonStyle {
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
.requiredFont {
    color: red;
    font-size: 12px;
}
.errStyle{
    padding-left: 102px;
}
</style>
</head>
<body>
<div style="background-color:#F5F5F5;width:100%;">
  <fieldset class="f3" style="margin-left: 204px;">
    <legend class="l2"> Add New Position</legend>
	<div  id="newPosErrMsg"  class="errStyle"></div>
    <div class="selectDivStyle">
        Add Position<font class="requiredFont">*</font> :         
         <input type="text" id="newPosition" style="width:121px;"  maxlength="50" /> <input type="button" class="buttonStyle" onclick="createNewPosition();" value="Add" />
         <span id="electionTypeSelect_ImgSpan" style="padding-left:100px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span>
    </div>
  </fieldset>
  <fieldset class="f3" style="margin-left: 204px;">
    <legend class="l2">  Assign Scope To Position</legend>
	<div id="scopPosiErrMsg" class="errStyle"></div>
	<table style="text-align:left;">
	 <tr>
    
         <td class="headingCss">Select Position<font class="requiredFont">*</font> :</td>         
         <td><select id="position" style="width:125px;margin-left: 34px;"></select></td>
             
	</tr>
	<tr>
	
        <td class="headingCss">Election Type<font class="requiredFont">*</font> :</td>
		<td><select id="electionType" style="width:125px;margin-left: 34px;" onchange="showHideState();getAllStates();"></select></td>       
    
	</tr>
	
	<tr>
	
         <td class="headingCss"> <div id="stateShowDiv">Select State<font class="requiredFont">*</font> :</div></td>
		 <td><div id="stateShowDivVal"><select id="stateSel" style="width:125px;margin-left: 34px;" ><option value="0">select</option></select></div></td>
    
	</tr>
	<tr>
	
        <td class="headingCss">Type<font class="requiredFont">*</font> :</td>
		<td><select id="type" style="width:125px;margin-left: 34px;" ><option value="0">Select Type</option><option value="1">Main</option><option value="2">Sub</option></select></td>      
    
	 </tr>
    </table>
    <div style="padding-left:200px;padding-top:5px;"><input type="button"  class="buttonStyle" value="Assign" onclick="assignScope();" /><span id="submitSelect_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>   

   
  </fieldset>
<fieldset class="f3" style="margin-left: 204px;">
  <legend class="l2"> Assign  Candidate To A Position</legend>
  <div  id="addCandidErrMsg"  class="errStyle"></div>
  <table style="text-align:left;">
     <tr>
	    <td class="headingCss">Select Position<font class="requiredFont">*</font> :</td>
		<td><select style="margin-left: 0px;width: 125px;" id="positionSelId" /></td>
	 </tr>
	 <tr>	
        <td class="headingCss">Type<font class="requiredFont">*</font> :</td>
		<td><select id="positionType" style="width: 125px;"><option value="0">Select Type</option><option value="1">Main</option><option value="2">Sub</option></select></td>          
	</tr>
	<tr>
	    <td class="headingCss">From Date<font class="requiredFont">*</font> :</td>
		<td>											
			<input type="text" value="" style="width: 121px;"  READONLY="READONLY" name ="fromDate" id="fromDate" size="15"/>										
			<div class="yui-skin-sam"><div id="fromDate_Div" class="tinyDateCal"></div></div>										
		</td>										
		<td valign="top">										
			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('fromDate_Div','fromDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
		</td>
	 </tr>
	  <tr>
	    <td class="headingCss">To Date<font class="requiredFont">*</font> :</td>
		<td>										
			<input type="text" style="width: 121px;" READONLY="READONLY" name ="toDate" id="toDate" size="15"/>										
			<div class="yui-skin-sam"><div id="toDate_Div" class="tinyDateCal"></div></div>										
		</td>														
		<td valign="top">										
			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('toDate_Div','toDate','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
		</td>
	 </tr>
	  <tr>
	    <td class="headingCss">Present Status<font class="requiredFont">*</font> :</td>
		<td><select id="presentStsId" style="width: 125px;" ><option value="0">Select Status</option><option value="1">Working</option><option value="2">Completed</option></select></td>
	 </tr>
	 <tr>
	    <td class="headingCss">Select Election Type<font class="requiredFont">*</font> :</td>
		<td><select style="margin-left: 0px;width: 125px;" id="electionTypeId" onchange="showRemainingDetails();" /></td>
	 </tr>
	</table>
	<div id="showRemaining"></div>
	
   <table>
     <tr>
	    <td class="headingCss"> Select Year<font class="requiredFont">*</font> :</td>
		<td><select style="margin-left: 24px;width: 125px;" id="yearAssSelId" ><option value="0">select</option></select></td>
	 </tr>
	 <tr>
	    <td class="headingCss"> Candidate Name<font class="requiredFont">*</font> :</td>
		<td><input type="text" id="candidateName"  style="margin-left:24px;width: 121px;" /></td>
	 </tr>
	 <tr>
	    <td class="headingCss"> Select Party<font class="requiredFont">*</font> :</td>
		<td><select style="margin-left: 24px;width: 125px;" id="partySelect" ><option value="0">select</option></select></td>
	 </tr>	 
   </table>
   <table>
     <tr>
	    <td class="headingCss1"><input type="radio" value="All" name="radioChk">All</td>
		<td class="headingCss1" style="padding-left:20px;width: 125px;"><input type="radio" value="Won" checked="true" name="radioChk">Won</td>
	 </tr>  
   </table>
       <div style="padding-left:200px;padding-top:5px;"><input type="button"  class="buttonStyle" value="Search Candidate" onclick="getCandidates();" /><span id="searchCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>
	   <div style="padding-left:70px;" class="yui-skin-sam" id="searchCandidate"></div>
	   <div style="padding-left:200px;padding-top:5px;"><input type="button"  class="buttonStyle" value="Assign Candidate" onclick="assignCandidateToPosition();" /><span id="assgCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>
</fieldset>
</div>
<script type="text/javascript">
function showRemainingDetails()  
{
   document.getElementById("showRemaining").innerHTML = "";
       var elecTypEle = document.getElementById("electionTypeId");
       var electionTypeId = elecTypEle.options[elecTypEle.selectedIndex].value;
	   var str ='';
	   if(electionTypeId == 1)
		{
		   getElectionYear();
		   getStaticParties();
		}
	   if(electionTypeId == 2)
	   {
	      str +='<table>';
		  str +='    <tr>';
	       str +='      <td class="headingCss"> Select State<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 54px;width: 125px;" id="stateId" onchange="getElectionYear();getStaticParties();" /></td>';
	       str +='   </tr>';
		  str +='</table>';
		   document.getElementById("showRemaining").innerHTML = str;
		   getAllStatesForAssign();
	   }
	   else if(electionTypeId == 5 || electionTypeId == 6 || electionTypeId == 7 )
	   {
	       str +='<table>';
		   str +='    <tr>';
	       str +='      <td class="headingCss"> Select State<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 33px;width: 125px;" id="stateId" onchange="getSelectElmtForLocalBody();" /></td>';
	       str +='   </tr>';
		   str +='    <tr>';
	       str +='      <td class="headingCss"> Select Location<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 33px;width: 125px;" id="locationId" onchange="getElectionYear();getStaticParties();" /></td>';
	       str +='   </tr>';
		   str +='</table>';
		   document.getElementById("showRemaining").innerHTML = str;
		   getAllStatesForAssign();
	   }
	   else if(electionTypeId == 3 )
	   {
		   str +='<table>';
		  str +='    <tr>';
	       str +='      <td class="headingCss"> Select State<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 41px;width: 125px;" id="stateId" onchange="getDistricts(this.options[this.selectedIndex].value)" /></td>';
	       str +='   </tr>';
		   str +='    <tr>';
	       str +='      <td class="headingCss"> Select District<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 41px;width: 125px;" id="districtId"  onchange="getAllMandalsInDistrict(this.options[this.selectedIndex].value);" /></td>';
	       str +='   </tr>';
		   str +='    <tr>';
	       str +='      <td class="headingCss"> Select Mandal<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 41px;width: 125px;" id="mandalId"  onchange="getElectionYear();getStaticParties();"  /></td>';
	       str +='   </tr>';
		  str +='</table>';
		   document.getElementById("showRemaining").innerHTML = str;
		   getAllStatesForAssign();
	   }
	   else if(electionTypeId == 4 )
	   {
		   str +='<table>';
		  str +='    <tr>';
	       str +='      <td class="headingCss"> Select State<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 41px;width: 125px;" id="stateId" onchange="getDistricts(this.options[this.selectedIndex].value);" /></td>';
	       str +='   </tr>';
		   str +='    <tr>';
	       str +='      <td class="headingCss"> Select District<font class="requiredFont">*</font> :</td>';
		   str +='      <td><select style="margin-left: 41px;width: 125px;" id="districtId"  onchange="getElectionYear();getStaticParties();"  /></td>';
	       str +='   </tr>';
		  str +='</table>';
		   document.getElementById("showRemaining").innerHTML = str;
		   getAllStatesForAssign();
	   }
	  
}
function getAllPositions()
	{	
		var jObj=
				{
						task:"getAllPosition"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
				var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
				callAjaxForAdmin(url,jObj);
    }
function getAllElectionTypes()
	{	
		var jObj=
				{
						task:"getAllElectionTypes"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
				var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
				callAjaxForAdmin(url,jObj);
    }
function getAllStates()
   {
    
      var typeEle = document.getElementById("electionType");
	   var eleId =   typeEle.options[typeEle.selectedIndex].value;
	   if(eleId == 0)
	    return;
    var jsObj =
		{ 
            time : new Date().getTime(),
			eleType:eleId,
			type:"old",
			task:"getStatesForAssign"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjaxForAdmin(url,jsObj);
  }
  function getAllStatesForAssign()
   {
      var typeEle = document.getElementById("electionTypeId");
	   var eleId =   typeEle.options[typeEle.selectedIndex].value;
    var jsObj =
		{ 
            time : new Date().getTime(),
			eleType:eleId,
			type:"new",
			task:"getStatesForAssign"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjaxForAdmin(url,jsObj);
  }
function assignScope()
   {
      document.getElementById("scopPosiErrMsg").innerHTML ="";
      var str ='';
	  var validate = false;
	   var positionEle = document.getElementById("position");
       var positionId = positionEle.options[positionEle.selectedIndex].value;
	   if(positionId == 0)
	   {
	     validate = true;
	     str+='<b><font style="color:red;font-size:12px;">Please Select Position</font></b><br />';
	   }
	   var elecTypEle = document.getElementById("electionType");
       var electionTypeId = elecTypEle.options[elecTypEle.selectedIndex].value;
	   if(electionTypeId == 0)
	   {
	     validate = true;
	     str+='<b><font style="color:red;font-size:12px;">Please Select Election Type</font></b><br />';
	   }
	   var stateSelEle = document.getElementById("stateSel");
       var stateId = stateSelEle.options[stateSelEle.selectedIndex].value;
	   if(electionTypeId != 0 && electionTypeId != 1)
	   {  if(stateId == 0)
	      {
	        validate = true;
	        str+='<b><font style="color:red;font-size:12px;">Please Select State</font></b><br />';
	      }
	   }
	   var typeEle = document.getElementById("type");
       var type = typeEle.options[typeEle.selectedIndex].text;
       var typeId = typeEle.options[typeEle.selectedIndex].value;
	   if(typeId == 0)
	   {
	     validate = true;
	     str+='<b><font style="color:red;font-size:12px;">Please Select Type</font></b><br />';
	   }
	   
	   
	    if(validate)
		{
		   document.getElementById("scopPosiErrMsg").innerHTML = str;
		   return;
		}
		if(electionTypeId == 1)
		{
		   stateId = 0;
		}
		document.getElementById("scopPosiErrMsg").style.display = "block";
    var jsObj =
		{ 
            positionId : positionId,
			electionTypeId : electionTypeId,
			stateId : stateId,
			type : type,
			task:"assignScopeToPosition"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateElectionInformationAction.action?"+rparam;						
	callAjaxForAdmin(url,jsObj);
  }
function showHideState()
 {
     var elecTypEle = document.getElementById("electionType");
       var elecTypId = elecTypEle.options[elecTypEle.selectedIndex].value;
	   if(elecTypId == 1)
	   {
	      document.getElementById("stateShowDiv").style.display ="none";
		  document.getElementById("stateShowDivVal").style.display ="none";
		}
	   else
	   {
	      document.getElementById("stateShowDiv").style.display ="block"; 
		  document.getElementById("stateShowDivVal").style.display ="block";
       }		  
 } 
function viewProfileLink(){

	var	viewProlinkIdElmt = document.getElementById("viewProlinkId");
	var str='';
	var candidateId = document.getElementById("candidateId").value;
  if(candidateId != 0){
	 str+='<a style="background: green;color: #fff;padding: 3px;margin: 8px; text-decoration: none;" target="_blank"          href="candidateElectionResultsAction.action?candidateId='+candidateId+'">View Profile</a>';
	 str+='<a href="javascript:{}" onclick="assignCandidate()">Assign Candidate</a>';
	viewProlinkIdElmt.innerHTML = str;
}
else
	viewProlinkIdElmt.innerHTML = str;
}

function assignCandidate(){

	assignCandidateDivElmt = document.getElementById("assignCandidateDiv");
	var str='';
	str+='<select id="ministryId"></select>';
	str+='<s:textfield name="fromDate" value="From Date" id="fromDate"  cssClass="formbg12" onfocus="showCalendar(this.id)" theme="simple"/>'

	str+='<s:textfield name="endDate" value="To Date" id="endDate" cssClass="formbg13" style="margin-left: 20px;" onfocus="showCalendar(this.id)" theme="simple"/>';
	str+='<input type="text" id="fromDate">';
	str+='<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal("fromDate","reportedFromText",new Date())"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';

}


function hideBusyImgWithId(elmtId)
		{
			var spanElmt = document.getElementById(elmtId+"_ImgSpan");
			if(spanElmt)
				spanElmt.style.display = "none";
		}

		function showBusyImgWithId(elmtId)
		{
			var spanElmt = document.getElementById(elmtId+"_ImgSpan");
			if(spanElmt)
				spanElmt.style.display = "";
		}
 function getElectionYears(){

    var stateId = document.getElementById("stateId").value;
	var electionTypeId = document.getElementById("electionTypeId").value;
	
	var jObj = {
				electionTypeId : electionTypeId,
				stateId :stateId,
				task : "getElectionYears"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);
 }
 
 function callAjaxForAdmin(url,jObj){
	
  var callback = {
	 success : function(o){
	 try {
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
		 
		 if(jObj.task == "getElectionYears"){
			clearOptionsListForSelectElmtId("electionYearId");
		    createOptionsForSelectElmtIdWithSelectOption("electionYearId",myResults);
		 }
		 else if(jObj.task == "getStates"){
		     buildStates(myResults,"stateSel");
		 }
		  else if(jObj.task == "getStatesForAssign"){
		    if(jObj.type =="new")
			{
		       clearOptionsListForSelectElmtId("stateId");
			   createOptionsForSelectElmtIdWithSelectOption("stateId",myResults);
			 }
			 if(jObj.type =="old")
			{
		       clearOptionsListForSelectElmtId("stateSel");
			   createOptionsForSelectElmtIdWithSelectOption("stateSel",myResults);
			 }
		 }
		 else if(jObj.task == "getLocalBodiesSelectElmtForState"){
		     clearOptionsListForSelectElmtId("locationId");
			createOptionsForSelectElmtIdWithSelectOption("locationId",myResults);
		 }
		 else if(jObj.task == "getStaticParties"){
		     clearOptionsListForSelectElmtId("partySelect");
			createOptionsForSelectElmtIdWithSelectOption("partySelect",myResults);
		 }
		 else if(jObj.task == "getCandidates"){
			
			document.getElementById("selectCandidateDiv").style.display = 'block';
			
			clearOptionsListForSelectElmtId("candidateId");
				createOptionsForSelectElmtIdWithSelectOption("candidateId",myResults);

			 }
			 else if(jObj.task == "getPartiesParticipatedInElection"){

				clearOptionsListForSelectElmtId("partyId");
				createOptionsForSelectElmtIdWithSelectOption("partyId",myResults);

			 }
			 else if(jObj.task == "getAllParliamentConstituencies"  || jObj.task == "getConstituencies"){
				clearOptionsListForSelectElmtId("constituencyId");
				createOptionsForSelectElmtIdWithSelectOption("constituencyId",myResults);

			 }
			 
		 else if(jObj.task == "getAllPosition"){
		    clearOptionsListForSelectElmtId("position");
			clearOptionsListForSelectElmtId("positionSelId");
			createOptionsForSelectElmtIdWithSelectOption("position",myResults);
			createOptionsForSelectElmtIdWithSelectOption("positionSelId",myResults);
          }
		 else if(jObj.task == "getAllElectionTypes"){
			createOptionsForSelectElmtIdWithSelectOption("electionType",myResults);
			createOptionsForSelectElmtIdWithSelectOption("electionTypeId",myResults);
          }
		  else if(jObj.task == "getDistrictsByStateId"){
		   clearOptionsListForSelectElmtId("districtId");
			buildResults(myResults,"districtId");
          }
		  else if(jObj.task == "getMandals"){
		  clearOptionsListForSelectElmtId("mandalId");
			buildResults(myResults,"mandalId");
          }
		  else if(jObj.task == "getAllElectionYears"){
		  clearOptionsListForSelectElmtId("yearAssSelId");
			buildResults(myResults,"yearAssSelId");
          }
		  else if(jObj.task == "addNewPosition")
		  {
		    showErrorMessage(myResults,"newPosErrMsg","Position Added Successfully");
			document.getElementById("electionTypeSelect_ImgSpan").style.display="none";
			getAllPositions();
		  }
		   else if(jObj.task == "assignScopeToPosition")
		  {
		    showErrorMessage(myResults,"scopPosiErrMsg","Scope Assigned To Position Successfully");
			document.getElementById("submitSelect_ImgSpan").style.display="none";
		  }
		  else if(jObj.task == "getCandidatesToShow")
		  {
		    document.getElementById("searchCand_ImgSpan").style.display="none";
		    buildCandidates(myResults);
		  }
		  else if(jObj.task == "assignCandidateToPosition")
		  {
		    showErrorMessage(myResults,"addCandidErrMsg","Candidate Assigned Successfully");
			document.getElementById("assgCand_ImgSpan").style.display="none";
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
function showErrorMessage(result,id,text)
{
   if(result.resultCode == 0)
   {
      document.getElementById(id).innerHTML ='<b><font style="color:green;font-size:12px;">'+text+'</font></b>';
   }
   else if(result.resultCode == 1 && result.exceptionMsg !=null && result.exceptionMsg.length >0)
   {
     document.getElementById(id).innerHTML ='<b><font style="color:red;font-size:12px;">'+result.exceptionMsg+'</font></b>';
   }
   else if(result.resultCode == 1 )
   {
     document.getElementById(id).innerHTML ='<b><font style="color:red;font-size:12px;">Error Ocuured, Try Again.</font></b>';
   }
}
function getCandidates(){

	var electionId = document.getElementById("electionYearId").value;
	var partyId = document.getElementById("partyId").value;
	var candidateName = document.getElementById("candidateSearchBoxId").value;

	var jObj = {
				partyId :partyId,
				candidateName :candidateName,
				electionId : electionId,
				task : "getCandidates"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);
}
function getParties(){
	
	var electionId = document.getElementById("electionYearId").value;
	var jObj = {
				electionId : electionId,
				task : "getPartiesParticipatedInElection"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);

}
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function createNewPosition(){
 document.getElementById("newPosErrMsg").innerHTML ="";
 var newPosition = document.getElementById("newPosition").value;
   newPosition = trim(newPosition);
   if(!(newPosition.length >0))
   {
     document.getElementById("newPosErrMsg").innerHTML = '<b><font style="color:red;font-size:12px;">Add Position Value Is Required</font></b>';
	 return;
   }
   else if(newPosition.length >50)
   {
     document.getElementById("newPosErrMsg").innerHTML = '<b><font style="color:red;font-size:12px;">Add Position Length Must Not Exceed 50 characters</font></b>';
	 return;
   }
 document.getElementById("electionTypeSelect_ImgSpan").style.display="block";
   var jObj = {
				newPosition : newPosition,
				task : "addNewPosition"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "updateElectionInformationAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);
}
function buildStates(result,id)
 {
      var elmt = document.getElementById(id);
	  var option1 = document.createElement('option');
		 option1.value= 0;
		 option1.text= "Select State";
		
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
		
	 for(var i in result)
	  {
		var option = document.createElement('option');
		
		  option.value=result[i].ids;
		  option.text=result[i].names;
		
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
 function showHideStateForAssigCand(){  
    var electionTypeEle = document.getElementById("electionTypeId");
       var electionTypeId = electionTypeEle.options[electionTypeEle.selectedIndex].value;
	   if(electionTypeId == 1)
	   {
	      document.getElementById("stateShowHideDivTit").style.display = "none";
		  document.getElementById("stateShowHideDiv").style.display = "none";
		}
	   else
        {	   
	     document.getElementById("stateShowHideDivTit").style.display = "block";
		  document.getElementById("stateShowHideDiv").style.display = "block";
		}
 }
 function getDistricts(stateId){
  var jsObj =
		{ 
            time : new Date().getTime(),
			stateId : stateId,
			task:"getDistrictsByStateId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForAdmin(url,jsObj);
 
}
function getAllMandalsInDistrict(districtId){
  var jsObj =
		{ 
            time : new Date().getTime(),
			districtId : districtId,
			task:"getMandals"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMandalsAjaxAction.action?"+rparam;						
	callAjaxForAdmin(url,jsObj);
 
}
function buildResults(results,id){
  var elmt = document.getElementById(id);
      if(id =="districtId" || id =="mandalId" || id == "yearAssSelId" )
      {
        var option = document.createElement('option');
	      option.value="0";
		  option.text="select";
		  try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
     }
      for(var i in results)
	  {
		var option = document.createElement('option');
         if(id =="districtId" )
		{
		  option.value=results[i].ids;
		  option.text=results[i].names;
		}
		else
		{
		    option.value=results[i].id;
		    option.text=results[i].name;
         }
        
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
 function getElectionYear()
 {
    var stateId =0;
	  if(document.getElementById("stateId") != null)
	  {
       var stateEle = document.getElementById("stateId");
       stateId = stateEle.options[stateEle.selectedIndex].value;
	  }
	  var electionTypeEle = document.getElementById("electionTypeId");
       electionType = electionTypeEle.options[electionTypeEle.selectedIndex].text;
        var jObj = {
	        time : new Date().getTime(),
			stateId : stateId,
		    eleType: electionType,
			task: 'getAllElectionYears'
			};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	callAjaxForAdmin(url,jObj);
  }
  function getSelectElmtForLocalBody()
{
	var statelocalEl = document.getElementById("stateId");
	var stateSelectlocalElVal = statelocalEl.options[statelocalEl.selectedIndex].value;
	
	var elecTypeEle = document.getElementById("electionTypeId");
	var elecTypeId = elecTypeEle.options[elecTypeEle.selectedIndex].value;
	
	var jsObj =
		{
			stateId: stateSelectlocalElVal,
			electionTypeId:elecTypeId,
			task: "getLocalBodiesSelectElmtForState"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getLocalBodiesInAState.action?"+rparam; 
	callAjaxForAdmin(url,jsObj);	
}

 function getStaticParties()
{
      var  stateSelectlocalElVal = 0;
	if(document.getElementById("stateId") != null)
	{
	  var statelocalEl = document.getElementById("stateId");
	  stateSelectlocalElVal = statelocalEl.options[statelocalEl.selectedIndex].value;
	}
	var elecTypeEle = document.getElementById("electionTypeId");
	var elecType = elecTypeEle.options[elecTypeEle.selectedIndex].text;
	var elecTypeId = elecTypeEle.options[elecTypeEle.selectedIndex].value;
	var reportLevel;
	if(elecTypeId == 1)
	   reportLevel = "3";
	else
	   reportLevel = "1";
	var jsObj =
		{
			stateId: stateSelectlocalElVal,
			elecTypeId:elecType,
			reportLevel:reportLevel,
			task: "getStaticParties"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
	var url = "getStaticPartyDetailsAjax.action?"+rparam; 
	callAjaxForAdmin(url,jsObj);	
}
function getAllConstituencies()
{


}
function getAllParliamentConstInCountry()
 {
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,		
			task: "getAllParliamentConstituencies",
			elmtId: "constituency" 	
	}

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    var url = "getAllParliamentConstInCountry.action?"+rparam;						
   callAjaxForAdmin(url,jsObj);	
 }
 function getAllConstituenciesInStateByType(electionType, stateId, element)
 {
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllConstituenciesInState.action?"+rparam;						
   callAjaxForAdmin(url,jsObj);	
 }
 function getCandidates()
 {
   var timeST = new Date().getTime();  
 document.getElementById("addCandidErrMsg").innerHTML ="";
var  stateId = 0;
var   districtId = 0;
var  tehilId = 0;
var location = 0;
var result ;
var str='';
valid = false;
 var selectBoxEl = document.getElementsByName("radioChk");
   for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].checked == true)
		 {
		  result = selectBoxEl[i].value;
		 }
	}
 var candidateName = document.getElementById("candidateName").value; 
 
  if(trim(candidateName).length == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Candidate Name Is Required </font></b><br />';
  }  
 
 var yearEle = document.getElementById("yearAssSelId");  
 var year = yearEle.options[yearEle.selectedIndex].text;  
 var yearval  = yearEle.options[yearEle.selectedIndex].value;  
 if(yearval == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Year </font></b><br />';
  } 
 var partyEle = document.getElementById("partySelect")  
 var  partyId =partyEle.options[partyEle.selectedIndex].value; 
 if(partyId == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Select Party </font></b><br />';
  }
  if(document.getElementById("districtId") != null)
 {
 var districtEle = document.getElementById("districtId");  
   districtId = districtEle.options[districtEle.selectedIndex].value; 
  }
 if(document.getElementById("mandalId") != null)
 {  
 var tehilEle = document.getElementById("mandalId") ; 
  tehilId = tehilEle.options[tehilEle.selectedIndex].value; 
 }
 if(document.getElementById("locationId") != null)
 { 
 var locationEle = document.getElementById("locationId");  
  location =locationEle.options[locationEle.selectedIndex].value;
  }
 var electionTypeEle= document.getElementById("electionTypeId")  
 var electionTypeId= electionTypeEle.options[electionTypeEle.selectedIndex].value; 
 if(electionTypeId == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Election Type </font></b><br />';
  } 
  if(document.getElementById("stateId") != null)
 {
 var stateEle= document.getElementById("stateId");  
 stateId= stateEle.options[stateEle.selectedIndex].value;  
  } 
 if(electionTypeId !=0 && electionTypeId != 1)
  {
     if(stateId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select State </font></b><br />';
    }
  }
  if(electionTypeId == 3 || electionTypeId == 4)
  {
     if(districtId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select District </font></b><br />';
  } 
  }
  if(electionTypeId == 3 )
  {
     if(tehilId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select Mandal </font></b><br />';
  } 
  }
  if(electionTypeId == 5 || electionTypeId == 6 || electionTypeId == 7)
  {
     if(location == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select Location </font></b><br />';
  } 
  }
  if(valid)
  {
      document.getElementById("addCandidErrMsg").innerHTML = str;
	  return;
  }  
  document.getElementById("searchCand_ImgSpan").style.display="block";
	var jsObj=
	{		
	         result :result,
             candidateName : candidateName,
             year : year,
             partyId : partyId,    
             districtId : districtId,
             tehilId : tehilId,
             location : location,
             time : timeST,	
			 electionTypeId: electionTypeId,
			 stateId: stateId,
			 task: "getCandidatesToShow" 	
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
   callAjaxForAdmin(url,jsObj);
 }
 
 function buildCandidates(result)
 {
 
  YAHOO.widget.DataTable.lnk = function(elLiner, oRecord, oColumn, oData) 
  {
	
	var name = oRecord.getData("name");
	var id = oRecord.getData("id");
	var url = 'candidateElectionResultsAction.action?candidateId='+id+' ';
	elLiner.innerHTML ='<a href="javascript:{}" onclick="openURl(\''+url+'\')">'+name+'</a>';
  };
  YAHOO.widget.DataTable.chkbox = function(elLiner, oRecord, oColumn, oData) 
  {	
	var id = oRecord.getData("id");
	elLiner.innerHTML ='<input type="radio" name="candidateId" value="'+id+'"/>';
  };
  
  var newsResultColumnDefs = [ 		    	             
		    	            
							{key:"fileTitle1", label: "CHECK TO SELECT CANDIDATE",formatter:YAHOO.widget.DataTable.chkbox, sortable: false},
							{key:"fileTitle1", label: "NAME",formatter:YAHOO.widget.DataTable.lnk, sortable: true}
		    	        ]; 
	var newsResultDataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "id","name"]
					};

		var newsResultDataSource = new YAHOO.widget.DataTable("searchCandidate", newsResultColumnDefs,myDataSource, myConfigs);
}
function openURl(url)
{

window.open(url,'_blank');

}
function assignCandidateToPosition()
{
document.getElementById("addCandidErrMsg").innerHTML ="";
 var valid = false;
 var timeST = new Date().getTime();  
 var str='';
var  stateId = 0;
var   districtId = 0;
var  tehilId = 0;
var location = 0;
var candidateId ;
var count = 0;
 var selectBoxEl = document.getElementsByName("candidateId");
   for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].checked == true)
		 {
		  candidateId = selectBoxEl[i].value;
		  count = count+1;
		 }
	}
  if(count == 0)
  {
     valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Check Any Candidate </font></b><br />';
  }
 var yearEle = document.getElementById("yearAssSelId");  
 var year = yearEle.options[yearEle.selectedIndex].text;  
 var yearval  = yearEle.options[yearEle.selectedIndex].value;  
 if(yearval == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Year </font></b><br />';
  } 
 var partyEle = document.getElementById("partySelect")  
 var  partyId =partyEle.options[partyEle.selectedIndex].value; 
 if(partyId == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Select Party </font></b><br />';
  }  
  if(document.getElementById("districtId") != null)
 {
 var districtEle = document.getElementById("districtId");  
   districtId = districtEle.options[districtEle.selectedIndex].value; 
  }
 if(document.getElementById("mandalId") != null)
 {  
 var tehilEle = document.getElementById("mandalId") ; 
  tehilId = tehilEle.options[tehilEle.selectedIndex].value; 
 }
 if(document.getElementById("locationId") != null)
 { 
 var locationEle = document.getElementById("locationId");  
  location =locationEle.options[locationEle.selectedIndex].value;
  }
 var electionTypeEle= document.getElementById("electionTypeId")  
 var electionTypeId= electionTypeEle.options[electionTypeEle.selectedIndex].value; 
 if(electionTypeId == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Election Type </font></b><br />';
  }  
  if(document.getElementById("stateId") != null)
 {
 var stateEle= document.getElementById("stateId");  
 stateId= stateEle.options[stateEle.selectedIndex].value;  
  } 
  
var electionGovBodyPosEle = document.getElementById("positionSelId");
var electionGovBodyPosId  = electionGovBodyPosEle.options[electionGovBodyPosEle.selectedIndex].value; 

  if(electionGovBodyPosId == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Position </font></b><br />';
  }  

var typeEle = document.getElementById("positionType");
var type  = typeEle.options[typeEle.selectedIndex].text; 
var typeval  = typeEle.options[typeEle.selectedIndex].value;   
	 if(typeval == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Type </font></b><br />';
  }  
var statusEle = document.getElementById("presentStsId");
var status  = statusEle.options[statusEle.selectedIndex].text; 
 var statusval  = statusEle.options[statusEle.selectedIndex].value; 
if(statusval == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">Please Select Present Status </font></b><br />';
  }  
var fromDate  = document.getElementById("fromDate").value;
if(trim(fromDate).length == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">From Date is Required </font></b><br />';
  }  
var toDate   = document.getElementById("toDate").value;
if(trim(toDate).length == 0)
  {
    valid = true;
	str+='<b><font style="color:red;font-size:12px;">To Date is Required </font></b><br />';
  }  
 
 if(electionTypeId !=0 && electionTypeId != 1)
  {
     if(stateId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select State </font></b><br />';
    }
  }
  if(electionTypeId == 3 || electionTypeId == 4)
  {
     if(districtId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select District </font></b><br />';
  } 
  }
  if(electionTypeId == 3 )
  {
     if(tehilId == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select Mandal </font></b><br />';
  } 
  }
  if(electionTypeId == 5 || electionTypeId == 6 || electionTypeId == 7)
  {
     if(location == 0)
	 {
	   valid = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select Location </font></b><br />';
  } 
  }
  if(valid)
  {
      document.getElementById("addCandidErrMsg").innerHTML = str;
	  return;
  }
  document.getElementById("assgCand_ImgSpan").style.display="block";
	var jsObj=
	{		
	           fromDate : fromDate,
			   toDate : toDate,
	          status : status,
	          type : type,
	         electionGovBodyPosId : electionGovBodyPosId,
             candidateId : candidateId,
             year : year,
             partyId : partyId,    
             districtId : districtId,
             tehilId : tehilId,
             location : location,
             time : timeST,	
			 electionTypeId: electionTypeId,
			 stateId: stateId,
			 task: "assignCandidateToPosition" 	
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "updateElectionInformationAction.action?"+rparam;						
   callAjaxForAdmin(url,jsObj);


}
getAllPositions();
getAllElectionTypes();
</script>
</body>
</html>