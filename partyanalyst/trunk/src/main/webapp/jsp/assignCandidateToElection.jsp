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
<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/jquery-ui-1.8.14.custom.css" />
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/demos.css" />
<style>

.mainDiv{
	margin-top:60px;
	padding:5px;
}
.selectDivStyle{
	font: bold 12px verdana;
	text-align:left;
	padding-left:142px;
	padding-top:20px;
}
.heading{
	font: bold 14px verdana;
	color:blue;
}
</style>
</head>
<body>
<div  class="mainDiv">
  <span class="heading"> Assign Candidates To Ministry</span>
  <div class="selectDivStyle">
Select Election Type :         
<select style="margin-left: 1px;" id="electionTypeId" onchange="getStates(this,this.options[this.selectedIndex].value)">
<option value="0">Select Election Type</option>
<option value="2">Assembly</option>
<option value="1">Parliament</option>
</select>
<span id="electionTypeSelect_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
</div>

<div class="selectDivStyle">
Select State :<select id ="stateId" style="width: 150px; margin-left: 58px;" onchange="getElectionYears()">
<option value="0">Select State</option>
</select>
</div>

<div class="selectDivStyle">
Select Election Year :
<select id="electionYearId" style="width: 150px;" onchange="getParties()" >
<option value="0">Select Election Year</option></select>
</div>
<div class="selectDivStyle">
Select Party  :
<select id="partyId" style="width: 150px; margin-left: 50px;">
</select>
</div>
<div class="selectDivStyle">
Candidate Name :<input type="text" id="candidateSearchBoxId" style="margin-left: 25px;"><input type="button" style="margin-left: 18px; background: none repeat scroll 0% 0% green; color: rgb(255, 255, 255); font-weight: bold; padding-top: 3px; padding-bottom: 3px;" value="Search Candidate" onclick="getCandidates()">
</div>
<div align="center" class="selectDivStyle">

</div>
<div id="selectCandidateDiv" class="selectDivStyle" style="display:none">
Select Candidate To Assign :
<select id="candidateId" style="width: 150px; margin-top: -14px;" onchange="viewProfileLink()">
</select><span id="viewProlinkId">
</div>
<div id="assignCandidateDiv"></div>
</div>

<script type="text/javascript">

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
function getStates(elmt,id)
	{	
		var jObj=
				{
						electionTypeId:id,
						task:"getStates"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
				var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
				callAjaxForAdmin(url,jObj);
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
			clearOptionsListForSelectElmtId("stateId");
			createOptionsForSelectElmtIdWithSelectOption("stateId",myResults);

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
</script>
</body>
</html>