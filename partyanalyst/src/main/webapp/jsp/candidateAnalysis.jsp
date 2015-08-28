<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Candidate Analysis Details</title>
		<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://code.jquery.com/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
	<div class="container" style="margin-top:10px;">
	
		<div class="row offset3">
<div id="errorDiv" style="color:red;font-size:12px;font-family:verdana;"></div>
<div class="row-fluid span9">
					<div class="span3">
						<label>Select Election Type<font color="#ff0000"> *</font>:</label>
					
						<select class="input-block-level" id="electionTypeId" onchange="gettingElectionYears()">
						<option value="0">Election Type</option>
						<option value="3">MPTC</option>
						<option value="4">ZPTC</option>
						<!--<option value="5">MUNICPALITY</option>
						<option value="6">CORPORATION</option>-->
						</select>


					</div>
				
				
				
					<div class="span3">
						<label>Select Election Year<font color="#ff0000"> *</font>:</label>
					
						<select  class="input-block-level" id="electionYearId">
						<option value="0">Select Election Year</option>
						</select>
					</div>
				
				 
				
				</div>
				<div class="row">
				 <input  type="button"  class="btn btn-success" value="submit" style="margin-left: 174px;" onclick=" getCandidateDetails()"/>
				 <br/>
				 <img id="ajaxImg" src="./images/Loading-data.gif" alt="Processing Image" class="offset2" 
							style="width:70px;height:60px;display:none;"/>
				 </div>
				
		</div>
				
	 <div id="tableDiv" class="span12" style="margin-top:20px;display:none;min-height:200px;"></div>
		
	</div>
	
<script>
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


function getCandidateDetails()
{
$("#tableDiv").html("");
var ElectionId = $("#electionYearId").val();
var eleTypeId = $("#electionTypeId").val();
var eleType = $("#electionTypeId option:selected" ).text();
var flag = false;
if(eleTypeId == 0)
	{
$("#errorDiv").html("select Election Type");
flag = true;
	}
	else if(ElectionId == 0)
	{
	$("#errorDiv").html("select Election year");
	flag = true;
	}

if(flag == true)
	return;
$("#errorDiv").html('');
$("#ajaxImg").show();
var jsObj =
{
ElectionId : ElectionId,
eleType :	eleType,
task:"getDetails"
}

$.ajax({
type: "GET",
url:"getCandidateAnalysisAction.action",
dataType:'json',
data: {task:JSON.stringify(jsObj)},
}).done(function(result,jsObj){
$("#ajaxImg").hide();
buildCandidateDetails(result,eleType);
});
}

function buildCandidateDetails(result,eleType)
{
	if(result.length > 0)
	$("#tableDiv").show();
	var str ='';
str+='<table class="table table-bordered table-hover table-striped">';
str+='<thead class="alert alert-success">';
str+='<th>Constituency</th>';
str+='<th>Mandal</th>';
str+='<th>'+eleType+' Constituency</th>';
str+='<th>Candidate</th>';
str+='<th>Party</th>';
str+='<th>Rank</th>';
str+='<th>Votes Gained</th>';
str+='<th>Total Votes</th>';
str+='<th>Mobile No</th>';
str+='<th>Previous Party</th>';
str+='</thead>';
str+='<tbody>';
for(var i in result)
	{
str+='<tr>';
str+='<td>'+result[i].constituencyName+'</td>';
str+='<td>'+result[i].tehsilName+'</td>';
str+='<td>'+result[i].electionType+'</td>';
str+='<td>'+result[i].candidateName+'</td>';
str+='<td>'+result[i].party+'</td>';
str+='<td>'+result[i].rank+'</td>';
str+='<td>'+result[i].votesEarned+'</td>';
str+='<td>'+result[i].totalvotes+'</td>';
if(result[i].mobileNo == null)
	str+='<td>-</td>';
else
str+='<td>'+result[i].mobileNo+'</td>';
if(result[i].previousParty == null)
str+='<td>-</td>';
else
str+='<td>'+result[i].previousParty+'</td>';
str+='</tr>';
	}
str+='</tbody>';
str+='</table>';
$("#tableDiv").html(str);
}
</script>
</body>
</html>