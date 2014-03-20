<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Strategy Report</title>

<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />

<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<style type="text/css">	</style>

<script type="text/javascript" >
$(document).ready(function(){
     getConstituencyList(); 
	
	 $("#candidateCastesId").multiselect({ noneSelectedText:"Select Castes"});
});

function getConstituencyList(){
    var jsObj= 
	{	
		task:"getConstituencies"		
	};
    $.ajax({
		   type: "POST",
		   url: "getConstituenciesByPartyNYearAction.action",		
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(result) {
		   $("#constituencyId option").remove();
           for(var i in result)
           {
		       $("#constituencyId").append("<option value="+result[i].id+">"+result[i].name+"</option>");         
           }
		  
	});

}
</script>
</head>
<body>
<div id="mainDiv" align="center">
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
     <div>
		<table>
		 <tr>
		    <td>Constituency Name :<font id="requiredValue" class="requiredFont">*</font> </td>
		    <td><select id="constituencyId" onchange="getPublicationDate();"><option value="0"> Select   
		    Constituency </option></select></td>			
		</tr>
		<tr>
		  <td>Publication Date:<font id="requiredValue" class="requiredFont">*</font></td>
		  <td><select id="publicationId" onchange="getPartyDetails(this.options[this.selectedIndex].value);getCandidateCastes(this.options[this.selectedIndex].value);" class="selectWidth"><option value="0">Select Publication Date</option></select></td>
		</tr>
		<tr>
			<td>Party Name:<font id="requiredValue" class="requiredFont">*</font> </td>
			<td><select id="partyId" onchange="getElectionYears(this.options[this.selectedIndex].value)"><option value="0"> Select Party </option></select></td>
		</tr>

	<tr>
		<td>From Year :<font id="requiredValue" class="requiredFont">*</font></td>		
		<td><select id="electionYear1" onchange="validateYear1(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);"><option value="0"> Select Year </option></select></td>
	</tr>
	<tr>
		<td>To Year :<font id="requiredValue" class="requiredFont">*</font> </td>	
		<td><select id="electionYear2" onchange="validateYear2(this.options[this.selectedIndex].value)"><option value="0"> Select Year </option></select></td>
	</tr>
	
    <tr>
        <td>PRP Effect Weightage :</td>
		<td><input id="prpEffId" type="text"></td>
	</tr>
	<tr>
        <td>Total Weightage :<font class="mandatory">*</font></td>
		<td><input id="totalId" type="text"></td>
	</tr>
	<tr>
         <td>Young Voters Weightage :<font class="mandatory">*</font></td>
		 <td><input id="YVotersId" type="text"></td>
    </tr>
	<tr>
	     <td>Age Wise Weightage :<font class="mandatory">*</font></td>
		 <td><input id="ageWiseId" type="text"></td>
    </tr>
	<tr>
	     <td>Previous Trendz Weightage :<font class="mandatory">*</font></td>
		 <td><input id="prevTrendsId" type="text"></td>
    </tr>
	<tr>
	    <td>Caste Names :<font id="requiredValue" class="requiredFont">*</font></td>		
		<td><select id="candidateCastesId" multiple="multiple" ></select></td>
		<td><input type="button" id="castePerId" value="Caste Percentage" onClick="getSelectedCastes();"  class="btn btn-success"></input></td>
    </tr>
</table></div>
<div><input type="button" id="submitId" value="Submit" class="btn btn-success" onClick="submitDetails();"></input></div> 
</div>

<div id="castesDisplayDiv"></div>
<script>
function getPublicationDate()
{
	    var constituencyId = $("#constituencyId").val();
		  var jsObj = 
	       {
		      selected:constituencyId,
		      task:"getPublicationDate"
	       }	
		    $.ajax({
				type : "POST",
				url : "voterAnalysisAjaxAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					$("#publicationId option").remove();
					for(var i in result){
                       $("#publicationId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
					}
			});
		
}

function getPartyDetails(id){
	
	$('#errorMsgDiv').html('');
	var constituencyId = $("#constituencyId").val();	
	
	var jsObj = 
	{
	mandalId : constituencyId,
	task:"getPartyDetails"
	}	
	$.ajax({
		type : "POST",
		url : "getParticipatedPartyForSuggestiveAction.action",
		data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#partyId option").remove();
			$("#partyId").append("<option value='0'>Select Party</option>");
			for(var i in result.partiesInMandal){
                $("#partyId").append("<option value="+result.partiesInMandal[i].id+">"+result.partiesInMandal[i].name+"</option>");
			}
	});
}

function getCandidateCastes(constituencyIds){
	
	var constituencyId = $("#constituencyId").val();
	var publicationId = $("#publicationId").val();

	var jsObj ={
		constituencyId : constituencyId,
		publicationId : publicationId,
		task : "getUserAssignedVoterCastes"
		};
	$.ajax({
		type : "POST",
		url : "getUserAssignedVoterCastesAction.action",
		data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			for(var i in result){
			
                $("#candidateCastesId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
			}
			$("#candidateCastesId").append("<option value=0>Others</option>");
		    $("#candidateCastesId").multiselect('refresh'); 
	});
}

function getElectionYears(id){
	
		var constituencyId = $("#constituencyId").val();
		var partyId = $("#partyId").val();
		var jsObj=
			{
				electionScopeId:2,
				partyId:partyId,
				constituencyId:constituencyId,
				task:"getElectionYears"						
			};
		
		$.ajax({
		      type : "POST",
		      url : "getElectionsYearsForParties.action",
		      data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#electionYear1 option").remove();
			$("#electionYear2 option").remove();
			$("#electionYear1,#electionYear2").append("<option value='0'>Select Election Year</option>");
			for(var i in result){
                $("#electionYear1").append("<option value="+result[i].id+">"+result[i].name+"</option>");
			}			
			for(var i in result){
                $("#electionYear2").append("<option value="+result[i].id+">"+result[i].name+"</option>");
			}
			 
	   });		
	   
	}

var selectedCastes = [];
function getSelectedCastes()
{
	$('#castesDisplayDiv').html('');
	selectedCastes = new Array();
	$('#castesDisplayDiv').dialog({
		 title : "EXPECTED CASTE PERCENTAGES",
		 width: 500,
		 height:500
	});
     $('#candidateCastesId :selected').each(function(i, selected){ 
		var casteDetails = {};
        casteDetails["casteId"] =  $(this).val();
		casteDetails["casteName"] =  $(this).text();
		selectedCastes.push(casteDetails);
	});
	 var str ='';
	 str +='<div><div id="errorDiv"></div>';
	 str +='<div align="center"><table class="table table-hover table-bordered"><th>Caste</th><th>Percentage</th>';
	
	for(var i in selectedCastes)
	{
		   str +='<tr><td>'+selectedCastes[i].casteName+'</td><td><input type="text" style="width:50px;" value="0.0"  id="percId'+i+'"></input></td></tr>';
		
	}
	str+='</table></div>';
    str+='<div><input type="button" class="btn btn-primary" value="Get values" onClick="getAllExpcetedCasteDetails();"></input></div></div>';

	$('#castesDisplayDiv').append(str);
	
}

var expCasteArray = [];
function getAllExpcetedCasteDetails()
{
	$("#errorDiv").html("");
   var pattern= /((0(\.[0-9]*)?)|(1(\.0)))/;  
   expCasteArray = new Array();
   for(var j = 0 ; j < selectedCastes.length ; j++)
   {
	   var casteDetails = {};
		var casteId = selectedCastes[j].casteId;
		var casteName = selectedCastes[j].casteName;
        var expPerc = $('#percId'+j+'').val();

		 if(!pattern.test(expPerc))
	     {
              $("#errorDiv").html("Expected caste percentage must be between 0 to 1");
			  return;
		 }

		casteDetails["casteId"]      =    casteId,
		casteDetails["expPerc"]      =    expPerc
		expCasteArray.push(casteDetails);					
   }
}

function submitDetails()
{
	$("#errorMsgDiv").html("");
    var constituencyId = $("#constituencyId").val();
    var publicationId = $("#publicationId").val();
    var partyId = $("#partyId").val();
	var electionYear1 = $("#electionYear1").val();
    var electionYear2 = $("#electionYear2").val();
    var prpEffId = $("#prpEffId").val().trim();
    var prevTrendsId = $("#prevTrendsId").val().trim();
	var totalId = $("#totalId").val().trim();
	var YVotersId = $("#YVotersId").val().trim();
	var ageWiseId = $("#ageWiseId").val().trim();
	var selectedCastes1 = new Array();
	
	var total = parseFloat(prpEffId) + parseFloat(prevTrendsId) + parseFloat(totalId) + parseFloat(YVotersId)+ parseFloat(ageWiseId);
	
    $('#candidateCastesId :selected').each(function(i, selected){ 
		var casteDetails = {};
        casteDetails["casteId"] =  $(this).val();
		selectedCastes1.push(casteDetails);
	});
    
	if(constituencyId == 0)
    {
		 $("#errorMsgDiv").html("Please Select the Constituency");
		 return;
	}  
	if(publicationId == 0)
    {
		 $("#errorMsgDiv").html("Please Select the Publication date");
		 return;
	}
	if(partyId == 0)
    {
		 $("#errorMsgDiv").html("Please Select Party");
		 return;
	}
	if(electionYear1 == 0 || electionYear2 == 0)
    {
		 $("#errorMsgDiv").html("Please Select Election Year");
		 return;
	}

	if(prpEffId == "")
    {
		if(selectedCastes1.length >0){
		 $("#errorMsgDiv").html("Enter the value for PRP Effect Weightage");
		}
		else
         $("#errorMsgDiv").html("");
	} 
  
	if(totalId == "" || prevTrendsId == "" || YVotersId == "" || ageWiseId == "")
    {
		 $("#errorMsgDiv").html("Please enter all the text fields");
		 return;
	}  
	if(isNaN(totalId) || isNaN(prevTrendsId) || isNaN(YVotersId) || isNaN(ageWiseId))
    {
		 $("#errorMsgDiv").html("Weightage values must be Numeric");
		 return;
	}  
     
	if(total != 100)
	{
        $("#errorMsgDiv").html("Sum of all weigthages must be equal to 100");
       return;
	}
	 
	 var jsObj=
			{	
				constituencyId:constituencyId,
				publicationId:publicationId,
				partyId:partyId,
				electionYear1:electionYear1,
				electionYear2:electionYear2,
				selCastesArray : selectedCastes1,
				expCasteArray :expCasteArray,
				task:"saveDetails"						
			};
		
		$.ajax({
		      type : "POST",
		      url : "",
		      data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
	    });

}



</script>
</body>
</html>
