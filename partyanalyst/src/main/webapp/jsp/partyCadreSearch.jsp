<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CadreSearch Details</title>
		<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://code.jquery.com/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
	<div class="container" style="margin-top:10px;background-color:#F7F8F9;">
	
	<div class="row offset4">
		<form>
			<label class="radio inline control-label"><input type="radio" name="radioType" value="1" id="cadreRadioId" checked="false"/><strong> Cadre </strong></label>
			<label class="radio inline control-label"><input type="radio" name="radioType" value="2" id="committeeRadioId"/><strong> Committee</strong></label>
		</form>
	</div>
	
		<div class="row offset3" id="cadreDivId" style="display:none;">
				<div id="errdiv" style="height:20px;margin-left: 20px;"" class="offset0"></div>
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Constituency<font color="#ff0000"> *</font>:</label>
					</div>
					<div class="span3">
						<select id="const" onchange="getAllPanchayatAndMuncipalities()"></select>
					</div>
				
				</div>
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Panchayat<font color="#ff0000"> *</font>:</label>
					</div>
					<div class="span3">
						<select id="panchayat"><option value='0'> Select Panchayat/Muncipalities</option></select>
					</div>
				</div>
				<div class="offset3">
					<button name="search" class="btn btn-primary" onclick="getCadreSearchDretailsBypanchayatAndMuncipalities()">Search</button>
									
				</div>
				<div id="ajaxcallimage"  class = "span3 offset3"  style="display:none;">
						<img src="./images/Loading-data.gif" style="width:70px;height:60px;"/>
					</div>	
				
		</div>
		
		<div class="row offset3" id="committeeDivId" style="display:none;">
			<div id="errdiv1" style="height:20px;margin-left: 20px;"" class="offset0"></div>
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Committee Level</label>
					</div>
					<div class="span3">
						<select id="committeeLevelId" onchange="getCommitteeLevelValues()"><option value='0'> Select Committee Level </option></select>
					</div>
				</div>
				
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Committee Level Value</label>
					</div>
					<div class="span3">
						<select id="committeeLevelValueId"  onchange="getCommittees()"><option value='0'> Select Committee Level Value </option></select>
					</div>
				</div>
				
				<div class="row-fluid span7">
					<div class="span3">
						<label>Select Committee</label>
					</div>
					<div class="span3">
						<select id="committeeId"><option value='0'> Select Committee</option></select>
					</div>
				</div>
				
				<button name="search" class="btn btn-primary offset3" onclick="getCommitteCadreDetails()">Search</button>
		</div>
		<div id="ajaxcallimage1"  class = "span3 offset5"  style="display:none;">
						<img src="./images/Loading-data.gif" style="width:70px;height:60px;"/>
					</div>
<div class="row">
		<div id="tableDiv" class="offset1 span10" style="margin-top:10px;"></div>
		</div>
	</div>
	
<script>

	$(document).ready(function(){
		getAllconstituenys();
		//$("#panchayat").html('');
		$("#errdiv").html('');
		$("#errdiv1").html('');
		getCommitteeLevel();
		$("#cadreDivId").show();
	});
	
	function getAllconstituenys()
	{
		$.ajaxSetup({jsonp: null,jsonpCallback: null});
		$.ajax({
		type: "GET",
		url: "getAllConstituencyaction.action",
		dataType: 'json',
		data: {electionscopeId:2,stateId:1},

			success:function(result){

				for(var i in result){
				
				$("#const").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				
				}
			}
		});
	
	}
	
	function getAllPanchayatAndMuncipalities()
	{
			var constituencyIds=$("#const option:selected").val();
			
			var jsObj = 
		{
			constituencyId:constituencyIds,
			task:"getAllPanchayatiesAndMuncipalities"
		}
				$.ajax({
					type: "GET",
					url:"getAllPanchayatAndMuncipalitiesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#panchayat").find('option').remove();
					 $("#panchayat").append("<option value='0'> Select Panchayat/Muncipalities </option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#panchayat").append("<option value="+result[i].id+">"+result[i].name+"</option>");
							}
						}
					});		
	}

	function getCadreSearchDretailsBypanchayatAndMuncipalities()
	{
		$("#tableDiv").html('');
		$("#errdiv").html('');
		
		var constituencyIds=$("#const option:selected").val();
		var panchayatIds=$("#panchayat option:selected").val();	
		var locationNames=$("#panchayat option:selected").text();
	
					 
		if(constituencyIds != null && constituencyIds== 0)
		{
			$('#errdiv').html('Please Select Constituency').css('color','red');
			return;
		}
		if(panchayatIds != null && panchayatIds == 0)
		{
			$('#errdiv').html('Please Select Panchayat/Muncipalities').css('color','red');
			return;
		}
		
			$("#ajaxcallimage").show();
			
				
	var jsObj = 
		{
			panchayatId:panchayatIds,
			locationName:locationNames,
			task:"getCadreDetails"
		}
		
				$.ajax({
					type: "GET",
					url:"getCadreDetailsByPanchayatAndMuncipalitiesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
						$("#ajaxcallimage").hide();
							cadreDetails(result);
							
					 });
	}
	function  cadreDetails(result)
	{
		var str='';
		str+="<table id='cadretable' class='table table-striped table-bordered table-condensed'>";
		str+="<thead>";
			str+="<tr>";
				//str+="<th>CadreId</th>";
				str+="<th>Name</th>";
				//str+="<th>LastName</th>";
				str+="<th>MobileNumber</th>";
				str+="<th>Age</th>";
				str+="<th>VoterId</th>";
				str+="<th>FatherName</th>";
			str+="</tr>"
		str+="</thead>";
		str+="<tbody>";
		for(var i=0;i<result.length;i++)
		{
			str+="<tr>";
				//str+="<td>"+result[i].cadreId+"</td>";
				str+="<td>"+result[i].firstName+"</td>";
				//str+="<td>"+result[i].lastName+"</td>";
				str+="<td>"+result[i].mobileNo+"</td>";
				if(result[i].age == 0)
				str+="<td></td>";
				else
				str+="<td>"+result[i].age+"</td>";
				str+="<td>"+result[i].voterCardId+"</td>";
				str+="<td>"+result[i].fatherName+"</td>";
			str+="</tr>";
		}
		str+="</tbody>";
		str+="</table>";
		$("#tableDiv").html(str);
		$("#cadretable").dataTable();
	}

	function getCommitteeLevel()
	{			
		var jsObj = 
		{
			task:"getCommitteeLevel"
		}
				$.ajax({
					type: "GET",
					url:"getCommitteeLevelAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#committeeLevelId").find('option').remove();
					 $("#committeeLevelId").append("<option value='0'> Select Committee Level </option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#committeeLevelId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
							}
						}
					});		
	}
	
	function getCommitteeLevelValues()
	{
			
			 $("select[id$=committeeId] > option:gt(0)").remove();
			var committeeLevelId=$("#committeeLevelId").val();
			
			var jsObj = 
		{
			committeeLevelId:committeeLevelId,
			task:"getCommitteeLevelValues"
		}
				$.ajax({
					type: "GET",
					url:"getCommitteeLevelValuesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#committeeLevelValueId").find('option').remove();
					 $("#committeeLevelValueId").append("<option value='0'> Select Committee Level Value </option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#committeeLevelValueId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
							}
						}
					});		
	}
		
	function getCommittees()
	{
			var committeeLevelValueId=$("#committeeLevelValueId").val();
			var committeeLIds = $("#committeeLevelId").val();
			if(committeeLevelValueId == 0)
				return;
			var jsObj = 
		{
			committeeLevelValueId:committeeLevelValueId,
				scopeId          : committeeLIds,
			task:"getCommitteeLevelValues"
		}
				$.ajax({
					type: "GET",
					url:"getCommitteesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#committeeId").find('option').remove();
					 $("#committeeId").append("<option value='0'> Select Committee</option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#committeeId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
							}
						}
					});		
	}
function getCommitteCadreDetails()
{
	$("#tableDiv").html('');
var committeeLIds = $("#committeeLevelId").val();
var committeeLVIds = $("#committeeLevelValueId").val();
var committeeIds = $("#committeeId").val();

	if(committeeLIds != null && committeeLIds == 0){
		$("#errdiv1").html("Please Select Committee level").css('color','red');
		return;
	}
	if(committeeLVIds != null && committeeLVIds == 0){
		$("#errdiv1").html("Please Select Committee level Value").css('color','red');
		return;
	}
	if(committeeIds != null && committeeIds == 0){
		$("#errdiv1").html("Please Select Committee").css('color','red');
		return;
	}
	
	$("#ajaxcallimage1").show();


var jsObj =
{
committeeId : committeeIds,
committeeLevelId :	committeeLIds,
task:"getCommitteCadreDetails"
}

$.ajax({
type: "GET",
url:"getCommitteeMeberDetailsAction.action",
dataType:'json',
data: {task:JSON.stringify(jsObj)},
}).done(function(result,jsObj){
$("#ajaxcallimage1").hide();
getCommitteCadreDetailsTable(result);

});
}

function getCommitteCadreDetailsTable(result)
{
var str='';
str+="<table id='Commitecadretable' class='table table-striped table-bordered table-condensed'>";
str+="<thead>";
str+="<tr>";
//str+="<th>CadreId</th>";
str+="<th>Name</th>";

str+="<th>MobileNumber</th>";
str+="<th>Age</th>";
str+="<th>VoterId</th>";
str+="<th>RelativeName</th>";
str+="<th>Role</th>";
str+="</tr>"
str+="</thead>";
str+="<tbody>";
for(var i=0;i<result.length;i++)
{
str+="<tr>";
//str+="<td>"+result[i].cadreId+"</td>";
str+="<td>"+result[i].firstName+"</td>";

str+="<td>"+result[i].mobileNo+"</td>";
if(result[i].age == null || result[i].age == 0)
str+="<td></td>";
else
{
str+="<td>"+result[i].age+"</td>";
}
str+="<td>"+result[i].voterCardId+"</td>";
str+="<td>"+result[i].fatherName+"</td>";
str+="<td>"+result[i].role+"</td>";
str+="</tr>";
}
str+="</tbody>";
str+="</table>";
$("#tableDiv").html(str);
$("#Commitecadretable").dataTable();
}

	$("#cadreRadioId").click(function() {
	$("#const").val(0);
	$("#panchayat").val(0);
	
	$("#tableDiv").html('');
		$("#committeeDivId").hide();
	    $("#cadreDivId").show();
    });
	$("#committeeRadioId").click(function()
	{
	$("#committeeLevelId").val(0);
	$("#committeeLevelValueId").val(0);
	$("#committeeId").val(0);
	$("#tableDiv").html('');
	    $("#cadreDivId").hide();
		$("#committeeDivId").show();		
    });
/*
	function getAllMuncipalities()
	{
			var constituencyIds=$("#const option:selected").val();
			
			var jsObj = 
		{
			constituencyId:constituencyIds,
			task:"getAllMuncipalities"
		}
				$.ajax({
					type: "GET",
					url:"getAllMuncipalitiesAction.action",
					dataType:'json',
					 data: {task:JSON.stringify(jsObj)},
					 }).done(function(result,jsObj){
					 $("#muncipality").find('option').remove();
					 $("#muncipality").append("<option value='0'> Select Muncipality </option>");
						 if(result !=null && result.length >0){
							for(var i in result){
								$("#muncipality").append("<option value="+result[i].cadreId+">"+result[i].firstName+"</option>");
							}
						}
					});		
	}
*/
</script>
</body>
</html>