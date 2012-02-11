<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Candidate To Election</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style>

.mainDiv{
	margin-top:60px;
	padding:5px;
	border:1px solid blue;
	width:550px;
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
  <span class="heading"> Assign Candidates To Election</span>
  <div class="selectDivStyle">
Select Election Type :         
<select style="margin-left: 19px;" id="electionTypeId" onchange="getStates(this,this.options[this.selectedIndex].value)">
<option value="0">Select Election Type</option>
<option value="2">Assembly</option>
<option value="1">Parliament</option>
</select>
<span id="electionTypeSelect_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif"></span>
</div>

<div class="selectDivStyle">
Select State :<select id ="stateId" style="margin-left: 76px; width: 150px;" onchange="getElectionYears()">
<option value="0">Select State</option>
</select>
</div>

<div class="selectDivStyle">
Selection Election Year :
<select id="electionYearId" style="width: 150px;" onchange="getCandidates()" >
<option value="0">Select Election Year</option></select>
</div>
<div class="selectDivStyle">
Candidate Name :<select id="candidateId" style="width: 150px;">
<option value="0">Select Candidate</option></select>
</div>
<div align="center" class="selectDivStyle">
<input type="button" value="Assign Candidate" onclick="assignCandidates()">
</div>
</div>

<script type="text/javascript">

	function getStates(elmt,id)
		{	
		      if(id!=0){	
				showBusyImgWithId(elmt.id);
				var jObj=
				{
						electionTypeId:id,
						task:"getStates"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
				var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
				callAjaxForAdmin(url,jObj);
			}	
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
		 else if(jObj.task == "getCandidatesBasedOnElectionId"){
				clearOptionsListForSelectElmtId("candidateId");
				createOptionsForSelectElmtIdWithSelectOption("candidateId",myResults);

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
	
	var jObj = {
				electionId : electionId,
				task : "getCandidatesBasedOnElectionId"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);
}

</script>
</body>
</html>