<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Key Candidates</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style type="text/css">
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
.fieldSet{
     border: 2px solid #CFD6DF;
    border-radius: 4px 4px 4px 4px;
    margin-bottom: 10px;
    margin-left: 46px;
    padding: 10px;
    width: 684px;
	/*background-color: #F5F5F5;*/
}
.textStyle{
font-weight:bold;
}
</style>
</head>
<body>
<div style="height: 510px; width: 900px; margin-left: auto; margin-right: auto;background:#FFFFFF;float:none;">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<fieldset class="fieldSet">
	<legend class="textStyle" style="color: navy;"> Assign Key Candidates</legend> 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	<div id="errorDivElement" style="margin-left: 455px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		
		<div style="margin-left: 238px;">
			<input type="radio" name="electionTypeRadio" value="2" id="assemblyId" onclick="getStates(this.value),getAssemblyLabel()"><span class="textStyle">&nbsp;Assembly </span>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="electionTypeRadio" value="1" id="parliamentId" onclick="getStates(this.value),getParliamentLabel()"><span class="textStyle">&nbsp;Parliament</span>
			
		</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	
	<table align="center">
		<tr>
			
			<th align="left"><span id="assemblyLabel">Select State:</span>
			<span id="parliamentLabel"></span></th>
			<td><select id="stateId" style="width:150px;" onchange="getAllParties()">
				<option value="0">Select State</option></select></td>
		</tr>
		
		<tr>
			<th align="left">Select Party :</th>
			<td><select id="partyId" style="width:150px;"><option value="0">Select Party</option></select></td>
		</tr>

		<tr>
			<th align="left">Candidate Name :</th>
			<td><input type="text" id="candidateName"/><input type="button" value="Search Candidate" onclick="getAllCandidates()" class="buttonStyle" style="margin-left: 20px;"></td>
		</tr>
		
		<tr>
			<th width="125px;" align="left">Select Candidate:</th>
			<td><select id="candidateId" style="width:150px;"><option value="0">Select Candidate</option></select></td>
		</tr>
		
		<tr>
			<td></td>
			<td><textarea id="candidateDescription" height="300px" width="300px" style="background:#FFFFFF;"></textarea></td>
		</tr>

		<tr>
			<td></td>
			<td><input type="button" value="Assign" onclick="assignCandidate()" class="buttonStyle" style="margin-left:12px;"></td>
		</tr>
	</table>
</fieldset>
</div>
<script type="text/javascript">

	function getStates(electionTypeId)
	{	
		
	var jObj=
		{
			electionTypeId:electionTypeId,
			task:"getStates"						
		};
			
		var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
		var url = "getStatesBasedOnElectionTypeAction.action?"+rparam;						
		callAjaxForAssignKeyCandidatePage(url,jObj);
		
	}

	function getAllParties(){
		
		var stateSelEle = document.getElementById("stateId");
		var stateId = stateSelEle.options[stateSelEle.selectedIndex].value;
		if(document.getElementById("assemblyId").checked == true)
		var electionType ="Assembly";

		if(document.getElementById("parliamentId").checked == true)
		var electionType = "Parliament";

	var jObj=
		{
		stateId : stateId,
		electionType:electionType,
		task : "getParties"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);	
	var url = "getStatesBasedOnElectionTypeAction.action?"+rparam;
	callAjaxForAssignKeyCandidatePage(url,jObj);
	
	}
	function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
	}

	function getAllCandidates(){

	var candidateName = document.getElementById("candidateName").value;
	var partyId = document.getElementById("partyId").value;
	var stateId = document.getElementById("stateId").value;
	var divEle = document.getElementById("errorDivElement");
	candidateName = trim(candidateName);
	var flag = false;
		var str = '';
		
		if(stateId == 0){
			str += '<b><font style="color:red;font-size:12px;">Please Select State</font></b><br>';
			flag = true;
		}
		if(partyId == 0){
			str += '<b><font style="color:red;font-size:12px;">Please Select Party</font></b><br>';
			flag = true;
		}
		if(candidateName.length == 0)
		{
			str += '<b><font style="color:red;font-size:12px;">Candidate Name is Required</font></b><br>';
			flag = true;
		}
	
	
		divEle.innerHTML = str;
		if(flag)
		return;
	var jObj=
		{
		candidateName : candidateName,
		partyId : partyId,
		stateId:stateId,
		task : "getCandidates"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);	
		var url = "getStatesBasedOnElectionTypeAction.action?"+rparam;
		callAjaxForAssignKeyCandidatePage(url,jObj);
	
	}
	
	function assignCandidate()
	{
		var candidateSelEle = document.getElementById("candidateId");
		var candidateId = candidateSelEle.options[candidateSelEle.selectedIndex].value;
		var description = document.getElementById("candidateDescription").value;
		var partyId = document.getElementById("partyId").value;
		var stateId = document.getElementById("stateId").value;
		var candidateName = document.getElementById("candidateName").value;
		description = trim(description);
		var divEle = document.getElementById("errorDivElement");
		var flag = false;
		var str = '';
		if(document.getElementById("assemblyId").checked == false && document.getElementById("parliamentId").checked == false ){
			str += '<b><font style="color:red;font-size:12px;">Please Select Assembly or Parliament</font></b><br>';
			flag = true;
		}
		if(stateId == 0){
			str += '<b><font style="color:red;font-size:12px;">Please Select State</font></b><br>';
			flag = true;
		}
		if(partyId == 0){
			str += '<b><font style="color:red;font-size:12px;">Please Select Party</font></b><br>';
			flag = true;
		}
		if(candidateName.length == 0)
		{
			str += '<b><font style="color:red;font-size:12px;">Candidate Name is required</font></b><br>';
			flag = true;
		}
		if(candidateId == 0)
		{
			str += '<b><font style="color:red;font-size:12px;">Please Select Candidate</font></b><br>';
			flag = true;
		}
		
		if(description.length == 0)
		{
			str += '<b><font style="color:red;font-size:12px;">Description is Required</font></b>';
			flag = true;
		}
		if(description.length>500)
		{
			str += '<b><font style="color:red;font-size:12px;">Description Must Be Lessthan 500 Charactors</font></b>';
			flag = true;
		}
		
		divEle.innerHTML = str;
		if(flag)
			return;
		
		var jObj=
			{
			candidateId : candidateId,
			description : description,
			task :"assignKeyCandidate"
			}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);	
		var url = "saveKeyCandidatesAction.action?"+rparam;
		callAjaxForAssignKeyCandidatePage(url,jObj);
	}

	function saveAssignKeyCandidate(myResults){

		if(myResults.resultCode == 0){
		
		clearData("candidateId");
		clearData("partyId");
		clearData("stateId");
		document.getElementById("candidateDescription").value = '';
		document.getElementById("candidateName").value = '';

		document.getElementById("errorDivElement").innerHTML = '<b><font style="color:green;font-size:12px;">Candidate Assigned Successfully</font></b>';
		}
		if(myResults.resultCode == 1){

		document.getElementById("errorDivElement").innerHTML = '<b><font style="color:red;font-size:12px;">Error Occured, Try Again.</font></b>';
			}

	}
	function getAssemblyLabel(){
	document.getElementById("parliamentLabel").innerHTML  = '';
	document.getElementById("assemblyLabel").innerHTML ='Select State:';

	}
	function getParliamentLabel(){
		document.getElementById("assemblyLabel").innerHTML  = '';
		document.getElementById("parliamentLabel").innerHTML  = 'Select Country:';

	}

	function callAjaxForAssignKeyCandidatePage(url,jObj){
		
	var callback = {
		 success : function(o){
		 try {
			 
			 myResults = YAHOO.lang.JSON.parse(o.responseText);
			
			 if(jObj.task == "getStates"){

				clearOptionsListForSelectElmtId("stateId");
				createOptionsForSelectElmtIdWithSelectOption("stateId",myResults);

				}
			 else if(jObj.task == "getParties"){

			clearOptionsListForSelectElmtId("partyId");
			createOptionsForSelectElmtIdWithSelectOption("partyId",myResults);

			}
			 else if(jObj.task == "getCandidates"){
				 
				 if(myResults == ''){
					var candidateName = document.getElementById("candidateName").value; 
					document.getElementById("errorDivElement").innerHTML = '<b><font style="color:red;font-size:12px;">No candidates are found for given "'+candidateName+'"</font></b>';
					}

			    else{
					clearOptionsListForSelectElmtId("candidateId");
					createOptionsForSelectElmtIdWithSelectOption("candidateId",myResults);
				}
			 }
			 else if(jObj.task == "assignKeyCandidate"){

				saveAssignKeyCandidate(myResults);
				
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
	function clearData(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>0;i--)
	{
		elmt.remove(i);
	}	
}
 
			 
</script>
</body>
</html>