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
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<style type="text/css">
 input[type="search"]{
  padding: 4px 1px;
}
#errorMsgDiv {
    color: #FF0000;
    font-size: 15px;
    font-weight: bold;
    margin-top: 10px;
}
</style>

<script type="text/javascript" >
$(document).ready(function(){
     getConstituencyList(); 
	
	 $("#candidateCastesId").multiselect({ noneSelectedText:"Select Castes"}).multiselectfilter({});
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
<div style="margin-left:65px;"><h3 class="offset4">Strategy Analysis Report</h3></div>
<div id="mainDiv" align="center">
     <div id="errorMsgDiv" style="margin-right: -159px;">&nbsp;</div><br><br>
	 <!--<div id="warningMsgs"></div>-->
     <div style="margin-left:198px; margin-top:-30px;">
	  <div id="helpWindow"><div id="helpWindowInner"></div></div>
		<table>
		 <tr>
		    <td>Constituency Name :<font id="requiredValue" class="requiredFont">*</font> </td>
		    <td><select id="constituencyId" onchange="getPublicationDate();removeCastesPerc();getCasteContainConstituency()" style="width:190px;"><option value="0"> Select   
		    Constituency </option></select></td>			
		</tr>
		<tr>
		  <td>Publication Date:<font id="requiredValue" class="requiredFont">*</font></td>
		  <td><select id="publicationId" onchange="getPartyDetails(this.options[this.selectedIndex].value);getCandidateCastes(this.options[this.selectedIndex].value);" class="selectWidth" style="width:190px;"><option value="0">Select Publication Date</option></select></td>
		</tr>
		<tr>
			<td>Party Name:<font id="requiredValue" class="requiredFont">*</font> </td>
			<td><select id="partyId" onchange="getElectionYears(this.options[this.selectedIndex].value)"style="width:190px;"><option value="0"> Select Party </option></select></td>
		</tr>

	<tr>
		<td>From Year :<font id="requiredValue" class="requiredFont">*</font></td>		
		<td><select id="electionYear1" onchange="validateYear1(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text);" style="width:190px;"><option value="0"> Select Year </option></select></td>
	</tr>
	<tr>
		<td>To Year :<font id="requiredValue" class="requiredFont">*</font> </td>	
		<td><select id="electionYear2" style="width:190px;" ><option value="0"> Select Year </option></select></td>
	</tr>
	
    <tr>
        <td>PRP Effect Weightage :<a  id="helpbutt" onCLick="popUpForHelp(' TDP is major looser with existence of PRP in 2009 Election. As a new Party, majority of the votes are Splitted to PRP from TDP and others, To recollect those votes, firstly we are trying to analyze how much Votes we lost due to that Party, and how much that loss were effected to our party, Along with other aspects we needs takes this effect into Consideration and gives certain Weightages to recollect ');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		<td><input id="prpEffId" type="text" style="width:176px;"></td>
	</tr>
	<tr>
        <td>Cast Weightage :<font class="mandatory">*</font> <a  id="helpbutt" onCLick="popUpForHelp(' As our election is caste based Election, with the top castes in the constituency how many votes can get apart from 2009 votes, as one of the criteria with other aspects, we give certain Weightages to calculate where they can prioritize the Villages to get the votes. Weightage for this category does not less than 50% but will take after giving weightages to all other categories');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		<td><input id="totalId" type="text" style="width:176px;"></td>
	</tr>
	<tr>
         <td>Young Voters Weightage :<font class="mandatory">*</font><a id="helpbutt" onCLick="popUpForHelp('These are First time voters, Party can grab these voters by Various schemes, here we are trying to grab these voters where they are populated highly and targeted them along with Top castes in Constituency, we give least weightage to this category like 2.5 to 5%');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="YVotersId" type="text" style="width:176px;"></td>
    </tr>
	<tr>
	     <td>Age Wise Weightage :<font class="mandatory">*</font><a  id="helpbutt" onCLick="popUpForHelp(' These are Above 60 aged voters, Party cadre can mobilize these voters by on the day of Election, here we are trying to grab these voters where they are populated highly and targeted them along with Top castes in Constituency, we give least weightages to this category like 2.5 to 5% ');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="ageWiseId" type="text" style="width:176px;"></td>
    </tr>
	<tr>
	     <td>Previous Trendz Weightage :<font class="mandatory">*</font><a  id="helpbutt" onCLick="popUpForHelp(' This is the Category where we analyse the previous results like 2004 and 2009, based on this we analyse the villages from 2004 to 2009, by giving certain weightage to this category with all other categories and gives second importance to this like 15% to 30% ');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="prevTrendsId" type="text" style="width:176px;"></td>
    </tr>
    <tr>
	     <td>Excepted Polling Percentage :<font class="mandatory">*</font><a  id="helpbutt" onCLick="popUpForHelp('  Candidate can give the Expected Percentage of Polling in Particular Constituency  by taking consideration of Previous Election Polling Percentage');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="base" type="text" style="width:176px;"></td>
    </tr>
    <tr>
	     <td>Voter Base Percentage :<font class="mandatory">*</font><a  id="helpbutt" onCLick="popUpForHelp(' Candidate can provide the Voter base of the Party in Particular Constituency along with considering minimum voting percentage in past three election years');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="assured" type="text" style="width:176px;"></td>
    </tr>
    <tr>
	     <td>Targeted Votes Percentage :<font class="mandatory">*</font><a  id="helpbutt" onCLick="popUpForHelp('  Candidate can provide the Assumed target percentage of   Polling to his Party in Particular Constituency');" value="Help" style="float: right;width: 20px;"  title="Click To View Help"><img alt="" src="./images/help.jpeg"></a></td>
		 <td><input id="partyPerc" type="text" style="width:176px;"></td>
    </tr>
    
    
    
    <tr style="display:none;">
	     <td>Worst Max :<font class="mandatory">*</font></td>
		 <td><input id="worstMax" value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Very Poor Min :<font class="mandatory">*</font></td>
		 <td><input id="veryPoorMin" value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Very Poor Max :<font class="mandatory">*</font></td>
		 <td><input id="veryPoorMax" value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td> Poor Min :<font class="mandatory">*</font></td>
		 <td><input id="poorMin" value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td> Poor Max :<font class="mandatory">*</font></td>
		 <td><input id="poorMax" value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Ok Min :<font class="mandatory">*</font></td>
		 <td><input id="okMin"  value="0" type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Ok Max :<font class="mandatory">*</font></td>
		 <td><input id="okMax"  value="0"  type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Strong Min :<font class="mandatory">*</font></td>
		 <td><input id="strongMin" value="0"  type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Strong Max :<font class="mandatory">*</font></td>
		 <td><input id="strongMax" value="0"  type="text"></td>
    </tr>
    <tr style="display:none;">
	     <td>Very Strong Min :<font class="mandatory">*</font></td>
		 <td><input id="veryStrongMin" value="0"  type="text"></td>
    </tr>
   
	<tr id="castesId">
	    <td>Caste Names :</td>		
		<td><select id="candidateCastesId" multiple="multiple" style="position:relative;"></select></td>
		<td><input type="button" id="castePerId" value="Caste Percentage" onClick="getSelectedCastes();"  class="btn btn-success"></input></td>
    </tr>
</table></div>
<div style="padding-top:20px;"><input type="button" id="submitId" value="Submit" class="btn btn-success" onClick="submitDetails();"></input><img id="ajaxImgShowHide" style="margin-left:20px;display:none;" width="25" height="25" src="images/icons/loading.gif"/></div> 
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

var isCasteDataAvail = true;
function getCasteContainConstituency()
{

$('#errorMsgDiv').html('');
	    var constituencyId = $("#constituencyId").val();
		  var jsObj = 
	       {
		      constituencyId:constituencyId,
		      task:"getCasteContainConstituency"
	       }	
		    $.ajax({
				type : "POST",
				url : "casteContainConstituencies.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			        
					if(result.resultCode==1)
					{
					isCasteDataAvail=false;
					$('#errorMsgDiv').html("Caste data for this constituency is not available");
					$("#castesId").hide();	
					$("#castePerId").hide();
                    $("#totalId").attr("disabled","true");					
					}
					else
					{
					  $("#totalId").removeAttr("disabled");	
					  $("#castePerId").show();
					  $("#castesId").show();
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
			
			if(result.partiesInMandal[i].name == "TDP")
                $("#partyId").append("<option value="+result.partiesInMandal[i].id+">"+result.partiesInMandal[i].name+"</option>");
			}
	});
}

function getCandidateCastes(constituencyIds){
	$("#errorMsgDiv").html("");
	var constituencyId = $("#constituencyId").val();
	var publicationId = $("#publicationId").val();
	if(publicationId == 0){
	 $("#errorMsgDiv").html("Please Select Publication");
		 return;
	return;
	}
	
     $("#candidateCastesId option").remove();
     $("#candidateCastesId").multiselect('refresh'); 
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
		expCasteArray= new Array();
		$("#candidateCastesId option").remove();
			for(var i in result){			
                $("#candidateCastesId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
			}
			if(result != null && result.length > 0){
			  $("#candidateCastesId").append("<option value=0 disabled='disabled' selected='selected' >Others</option>");
		    }
			$("#candidateCastesId").multiselect('refresh'); 
	});
}
function validateYear1(yearId,yearValue){
	$('#errorMsgDiv').html('');
		$("#electionYearSelectEl1").css("border","1px solid lightBlue");
	if(yearId == 0){
		$("#electionYearSelectEl1").css("border","1px solid IndianRed");
		$('#errorMsgDiv').html('Please Select Election Years');
		return;
	}

var select = new Array();
$('#electionYear1 option').each(function(){
	if(!(yearValue == $(this).text()|| yearValue < $(this).text()))
		{
		 var obj = {
			 id : $(this).val(),
			 name : $(this).text()
		}
	select.push(obj);
	}
});

	$('#electionYear2').children().remove();
	$('<option>').val(0).text('Select Year').appendTo('#electionYear2');

for(var i=0;i<select.length;i++)
	{	 					$('<option>').val(''+select[i].id+'').text(''+select[i].name+'').appendTo('#electionYear2');
	}
	/*alert($('select#electionYear2 option').length);
	alert($('#electionYear2').options.length);
	if($('#electionYear2').length>1)
	{
	  $('#errorMsgDiv').html('Please Select Election Years');
	}*/

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
    expCasteArray = new Array();
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
    str+='<div><input type="button" class="btn btn-primary" value="OK" onClick="getAllExpcetedCasteDetails();"></input></div></div>';

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
      $('#castesDisplayDiv').dialog('close');		
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
	var base = $("#base").val().trim();
	var assured = $("#assured").val().trim();
	var partyPerc = $("#partyPerc").val().trim();
	var selectedCastes1 = new Array();
	var worstMax = $("#worstMax").val().trim();
	var veryPoorMin = $("#veryPoorMin").val().trim();
	var veryPoorMax	= $("#veryPoorMax").val().trim();	  
	var poorMin = $("#poorMin").val().trim();
	var poorMax = $("#poorMax").val().trim();
	var okMin = $("#okMin").val().trim();
	var okMax = $("#okMax").val().trim();
	var strongMin = $("#strongMin").val().trim();
	var strongMax = $("#strongMax").val().trim();
	var veryStrongMin  = $("#veryStrongMin").val().trim();
	
	var total = 0;
	if(isCasteDataAvail){
		 total = parseFloat(prpEffId) + parseFloat(prevTrendsId) + parseFloat(totalId) + parseFloat(YVotersId)+ parseFloat(ageWiseId);
	}else{
		 total = parseFloat(prpEffId) + parseFloat(prevTrendsId) + parseFloat(YVotersId)+ parseFloat(ageWiseId);
	}
	
	
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
	if(electionYear1 == 0)
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
  
	if(isCasteDataAvail){
		if(total == ""){
			$("#errorMsgDiv").html("Please enter all the text fields");
			return;
		}
		if(isNaN(total)){
			 $("#errorMsgDiv").html("Weightage values must be Numeric");
			return;
		}
	}
	if(prevTrendsId == "" || YVotersId == "" || ageWiseId == "" || base =="" || assured == "" || partyPerc == "")
    {
		 $("#errorMsgDiv").html("Please enter all the text fields");
		 return;
	}  
	if(isNaN(prevTrendsId) || isNaN(YVotersId) || isNaN(ageWiseId) || isNaN(base) || isNaN(assured) || isNaN(partyPerc))
    {
		 $("#errorMsgDiv").html("Weightage values must be Numeric");
		 return;
	}  
     
	if(total != 100)
	{
        $("#errorMsgDiv").html("Sum of all weigthages must be equal to 100");
       return;
	}
	 $("#ajaxImgShowHide").show();
	 $("#submitId").attr("disabled","disabled");	
	 var jsObj=
			{	
				constituencyId:constituencyId,
				publicationId:publicationId,
				partyId:partyId,
				electionYear1:electionYear1,
				electionYear2:electionYear2,
				selCastesArray : selectedCastes1,
				expCasteArray :expCasteArray,
				prevTrnzWt:prevTrendsId,
				youngWt:YVotersId,
				prpWt:prpEffId,
				agedWt:ageWiseId,
				totalCastWt:totalId,
				effectPartyId:662,
				effectElectionId:38,
				base:base,
				assured:assured,
				partyPerc:partyPerc,
				considerRange:false,
				worstMax        :worstMax,
				veryPoorMin     :veryPoorMin,
				veryPoorMax		:veryPoorMax	 ,
				poorMin         :poorMin,
				poorMax         :poorMax,
				okMin           :okMin,
				okMax           :okMax,
				strongMin       :strongMin,
				strongMax       :strongMax,
				veryStrongMin   :veryStrongMin,
				task:"saveDetails"						
			};
		
		$.ajax({
		      type : "POST",
		      url : "generateStrategyAction.action",
		      data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		    $("#ajaxImgShowHide").hide();			
			$("#submitId").removeAttr('disabled');
			window.open(result);
	    });

}

function removeCastesPerc(){
expCasteArray = new Array();
$("#candidateCastesId option").remove();
$("#candidateCastesId").multiselect('refresh'); 

}

function popUpForHelp(text)
{

$("#helpWindow").dialog({
				resizable:false,
				title:'Help',
				height: 'auto',
				width:'400',
				top:250,
				left:100,
				modal: true
				
	});
	$("#helpWindowInner").html(text);
	
	}

		
</script>
</body>
</html>
