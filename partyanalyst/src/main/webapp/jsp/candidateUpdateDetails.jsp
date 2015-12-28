<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<head> 

	<link href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" /> 
	<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div>
		<div class="row offset1">
		  <div class="span6 offset2 well">
		  <div id="errorDiv" style="color:red;height:30px;"></div>
					  <div class="row-fluid" style="margin-top:10px;">
									<div  class="span3 offset2"> 
									<label>Election Type <font color="#ff0000">*</font>:</label>
									</div>
									<div class="span8"> 
									  <select class="input-block-level" id="electionTypeId" onchange="gettingElectionYears()">
																	<option value="0">Election Type</option>
																	<option value="3">MPTC</option>
																	<option value="4">ZPTC</option>
																	<option value="5">MUNICPALITY</option>
																	<option value="6">CORPORATION</option>
																	</select>
									</div>	
					  </div>
					<div class="row-fluid" style="margin-top:10px;">
									<div class="span3 offset2 "> 
									<label>Election Year <font color="#ff0000">*</font>:</label>
									</div>
									<div  class="span8"> 
										<select class="input-block-level" id="electionYearId">
																<option value="0">Select Election Year</option>
																</select>
									</div>
					  </div>
					<div class="row-fluid" style="margin-top: 10px;">
									<div class="span3 offset2 " > 
									<label>District <font color="#ff0000">*</font>:</label>
									</div>
									<div class="span8"> 
									  <select  id="districtId" class="input-block-level" style="width: 306px;" multiple="multiple">
																									
										</select>
									</div>
					  </div>

					<div style="margin-top: 10px;" class="row-fluid">
							
							<div  class="span12 text-center"> 
							  <input  type="button"  class="btn btn-success" value="submit" style="margin-left: 174px;" onclick="return candidateUpdateDetails()">
							</div>
					</div>
		  </div>
	  </div>
</div>
<div>
<input type="checkbox" id="casteId"  onClick="casteHideOrShow();"      >Caste</input>
<input type="checkbox" id="educationId" onClick="educationHideOrShow();"  >Education</input>
<input type="checkbox" id="workingId"   onClick="workingHideOrShow();"  >Working</input>
<input type="checkbox" id="mobileNoId"  onClick="mobileNoHideOrShow();" >Mobile</input>
</div>



</div>
<div id="tableDiv" style="margin-top:23px;overflow:scroll;margin-left:25px;width:990px;" ></div>
<span id="updateId" style="margin-left: 246px; margin-top:31px;display:none;width:70px"  class='btn btn-info' onclick="updatecandidateDetails()">Update</span>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!--<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>-->
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<!--<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>-->

<script>   
 
$(".eventsheader").find(".span2").removeClass("span2")
$(".eventsheader").find(".span1").removeClass("span1")
$(".eventsheader").find(".span3").removeClass("span3")
$(".eventsheader").find(".span5").removeClass("span5")

getAllDistrictsForAState();
function candidateUpdateDetails()
{

$("#errorDiv").html("");
var flag="false";
var electionTypeId= $("#electionTypeId option:selected").val();
var electionYearId=$("#electionYearId option:selected").val();
var districtIds= $("#districtId").val();

var str="";
if(electionTypeId==0)
{
str+="Please Select Election Type<br>";
flag="true";
}
if(districtIds==null)
{
str+="Please Select District.";
flag="true";
}
if(flag=="true")
{
 $("#errorDiv").html(str);
 return false;
}
var jsobj={
		    electionScopeId:electionTypeId,
		    electionId:electionYearId,
		    districtIds:districtIds
		  } ;
$.ajaxSetup({jsonp: null,jsonpCallback: null});
$.ajax({
type: "GET",
url: "gettingCandidateDetailsAction.action",
dataType: 'json',
data: {
       task :JSON.stringify(jsobj)
	  },
success:function(result)
{
  if(electionTypeId==3||electionTypeId==4)
    buildingCandidateDetailsForMptcOrZptc(result);
  else
    buildingCandidateDetailsForMuncOrCorp(result);
}

});
 
}

function gettingElectionYears()
{
$("#electionYearId option").remove();
var electionTypeId= $("#electionTypeId option:selected").val();
if(electionTypeId==0)
{
$("#electionYearId").append("<option value='0'>Election Year</option>");
return;
}

$.ajaxSetup({jsonp: null,jsonpCallback: null});
$.ajax({
type: "GET",
url: "gettingElectionYearsAction.action",
dataType: 'json',
data: {electionTypeId:electionTypeId},

success:function(result){

for(var i in result){
$("#electionYearId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
}
}
});
}

function getAllDistrictsForAState()
{

$.ajaxSetup({jsonp: null,jsonpCallback: null});
$.ajax({
type: "GET",
url: "getAllDistrictsForAStateAction.action",
dataType: 'json',
data: {stateId:1},

success:function(result){

		for(var i in result){
			$("#districtId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
		}
	   //$('#districtId').multiselect();
	}
});

}

function casteHideOrShow()
{
  
  if($("#casteId").is(':checked'))
  {
   $("#casteTableHeadingId").show();
   $(".casteTableTdClass").show();
   }
  else
  {
    $("#casteTableHeadingId").hide();
    $(".casteTableTdClass").hide();
}
}
function educationHideOrShow()
{
  if($("#educationId").is(':checked'))
  {
    $("#educationTableHeadingId").show();
    $(".educationTableTdClass").show();
   }
   else
   {$("#educationTableHeadingId").hide();
    $(".educationTableTdClass").hide();}
}
function workingHideOrShow()
{
 if($("#workingId").is(':checked'))
 {
  $("#workingTableHeadingId").show();
  $(".workingTableTdClass").show();
 }
 else
 {$("#workingTableHeadingId").hide();
  $(".workingTableTdClass").hide();}
  }
function mobileNoHideOrShow()
{
 if($("#mobileNoId").is(':checked'))
 {
  $("#mobileTableHeadingId").show();
  $(".mobileTableTdClass").show();
  }
  else
  {
   $("#mobileTableHeadingId").hide();
   $(".mobileTableTdClass").hide();
  }
}
function buildingCandidateDetailsForMptcOrZptc(result)
{
  
  var str="";
  str+="<table id='tableId' class='table table-striped table-bordered table-condensed'>";
  str+="<thead>";
  str+="<tr>";
  str+="<th></th>"
  str+="<th>ConId</th>";
  str+="<th>District</th>";
  str+="<th>Assembly</th>";
  str+="<th>Mandal</th>";
  str+="<th>Constituency</th>";
  str+="<th>Candidate Name</th>";
  str+="<th>Party</th>";
  str+="<th>Rank</th>";
  str+="<th  id='casteTableHeadingId'>Caste</th>";
  str+="<th  id='educationTableHeadingId'>Education</th>";
  str+="<th  id='workingTableHeadingId'>Working In Party</th>";
  str+="<th  id='mobileTableHeadingId'>Mobileno</th>";
  str+="</tr>";
  str+="</thead>";
  str+="<tbody>";
  for(i=0;i<result.length;i++)
  {
    str+="<tr>";
	str+="<td><input type='checkbox' class='mptcselect' id='"+result[i].candidateId+"'></td>";
	str+="<td style='width:10px'>"+result[i].id+"</td>";
	str+="<td style='width:10px'>"+result[i].district+"</td>";
	str+="<td>"+result[i].constituencyName+"</td>";
	str+="<td>"+result[i].tehsilName+"</td>";
	str+="<td>"+result[i].state+"</td>";
	str+="<td>"+result[i].candidateName+"</td>";
	str+="<td>"+result[i].party+"</td>";
	str+="<td>"+result[i].rank+"</td>";
	//select box for all castes.
	str+="<td class='casteTableTdClass'><select style='width:120px;'   id='caste"+result[i].candidateId+"'> ";
	for(j=0;j<result[0].candidatescastes.length;j++)
	{
	   if(result[0].candidatescastes[j].name == result[i].caste)
		 str+="<option value='"+result[0].candidatescastes[j].id+"' selected='selected'>"+result[0].candidatescastes[j].name+"</option>";
	  else
		 str+="<option value='"+result[0].candidatescastes[j].id+"'>"+result[0].candidatescastes[j].name+"</option>";
		
	}
	str+="</select></td>";
	
	//select box for education.
	str+="<td class='educationTableTdClass'><select  style='width:120px;'  id='education"+result[i].candidateId+"'> ";
	for(j=0;j<result[0].candidatesEducations.length;j++)
	{
	
	 if( result[0].candidatesEducations[j].name==result[i].education )
	    str+="<option value='"+result[0].candidatesEducations[j].id+"' selected='selected'>"+result[0].candidatesEducations[j].name+"</option>";
	 else
	    str+="<option value='"+result[0].candidatesEducations[j].id+"'>"+result[0].candidatesEducations[j].name+"</option>";
	
	}
	str+="</select></td>";
	str+="<td class='workingTableTdClass'><input type='text'  style='width:20px;' value='"+result[i].howLongWorkingInParty+"' id='working"+result[i].candidateId+"'/></td>"   ;
	str+="<td class='mobileTableTdClass'><input type='text'  style='width:80px;'  value='"+result[i].mobileNo+"'              id='mobile"+result[i].candidateId+"'/></td>"    ;
	str+="</tr>";
  
  }
  
  str+="</tbody>";
  str+="</table>"
   $("#tableDiv").html(str);
   $('#tableId').dataTable();
   $("#casteTableHeadingId").hide();
   $(".casteTableTdClass").hide();
   $("#educationTableHeadingId").hide();
   $(".educationTableTdClass").hide();
   $("#workingTableHeadingId").hide();
   $(".workingTableTdClass").hide();
   $("#mobileTableHeadingId").hide();
   $(".mobileTableTdClass").hide();
  document.getElementById("updateId").style.display="block";

}
function buildingCandidateDetailsForMuncOrCorp(result)
{

  var str="";
  str+="<table id='tableId' class='table table-striped table-bordered table-condensed'>";
  str+="<thead>";
  str+="<tr>";
  str+="<th></th>"
  str+="<th>ConId</th>";
  str+="<th>District</th>";
  str+="<th>Mandal</th>"
  str+="<th>Municipality</th>";
  str+="<th>Constituency</th>";
  str+="<th>Candidate Name</th>";
  str+="<th>Party</th>";
  str+="<th>Rank</th>";
  str+="<th>Caste</th>";
  str+="<th>Education</th>";
  str+="<th>Working In Party</th>";
  str+="<th>Mobileno</th>";
  str+="</tr>";
  str+="</thead>";
  str+="<tbody>";
  for(i=0;i<result.length;i++)
  {
    str+="<tr>";
	str+="<td><input type='checkbox' class='mptcselect' id='"+result[i].candidateId+"'></td>";
	str+="<td style='width:10px'>"+result[i].id+"</td>";
	str+="<td style='width:10px'>"+result[i].district+"</td>";
	str+="<td>"+result[i].tehsilName+"</td>";
	str+="<td>"+result[i].localBodyName+"</td>";
	str+="<td>"+result[i].state+"</td>";
	str+="<td>"+result[i].candidateName+"</td>";
	str+="<td>"+result[i].party+"</td>";
	str+="<td>"+result[i].rank+"</td>";
	//select box for all castes.
	str+="<td><select style='width:120px;'  id='caste"+result[i].candidateId+"'> ";
	for(j=0;j<result[0].candidatescastes.length;j++)
	{
	  if( result[0].candidatescastes[j].name.toUpperCase()== result[i].caste.toUpperCase() )
	   str+="<option value='"+result[0].candidatescastes[j].id+"' selected='selected'>"+result[0].candidatescastes[j].name+"</option>";
	  else
	   str+="<option value='"+result[0].candidatescastes[j].id+"'>"+result[0].candidatescastes[j].name+"</option>";

	}
	str+="</select></td>";
	
	//select box for education.
	str+="<td><select style='width:120px;' id='education"+result[i].candidateId+"'> ";
	for(j=0;j<result[0].candidatesEducations.length;j++)
	{
	  if( result[0].candidatesEducations[j].name== result[i].education )
	   str+="<option value='"+result[0].candidatesEducations[j].id+"' selected='selected'>"+result[0].candidatesEducations[j].name+"</option>";
	  else
	   str+="<option value='"+result[0].candidatesEducations[j].id+"'>"+result[0].candidatesEducations[j].name+"</option>";

	}
	str+="</select></td>";
	//candidate education.
	str+="<td><input style='width:20px;' value='"+result[i].howLongWorkingInParty+"' id='working"+result[i].candidateId+"'/></td>"
	str+="<td><input style='width:80px;' value='"+result[i].mobileNo+"'              id='mobile"+result[i].candidateId+"'/></td>"
	str+="</tr>";
  
  }
  
  str+="</tbody>";
  str+="</table>"
  $("#tableDiv").html(str);
  $('#tableId').dataTable();
  document.getElementById("updateId").style.display="block";
  
  }
 function updatecandidateDetails()
{
 
 var updatesArray=[];

 $('.mptcselect').each(function()
 {
   if($(this).is(':checked'))
   { 
   
    var jsobj =new Object();
	var id=$(this).attr('id');
    jsobj.candidateDetailsId=id;
	jsobj.casteId=$('#caste'+id+' option:selected').val();
	jsobj.educationId=$('#education'+id+' option:selected').val();
	jsobj.workingHours=$('#working'+id).val();
	jsobj.mobileNo=$('#mobile'+id).val();
	updatesArray.push(jsobj);
	
	}
   
 });
 
 var jsobj1={
		      candidateDetailsArray:updatesArray
		    };
  $.ajaxSetup({jsonp: null,jsonpCallback: null});
	  $.ajax({
               type     :  "GET",
               url      :  "updateDetailsofACandidateAction.action",
	           dataType :  'json',
               data     :  {task :JSON.stringify(jsobj1)},
			   success  :  function(result)
			              {
                            if(result.resultCode==0)
							{
							  alert("details updated successfully.....");
							}
							else 
							  alert("details not updated successfully.....");
                          }
        	         
             });

 
} 
</script>
</body>
</html>