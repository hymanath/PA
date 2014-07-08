<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<head>

</head>
<body>

	
	<div class="container">
		<div class="row">
		  <div class="span6 offset2 well">
		  <div id="errorDiv" style="color:red"></div>
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
									  <select  id="districtId" class="input-block-level">
																	<option value="0">Select District</option>
																	
																	</select>
									</div>
					  </div>

					<div style="margin-top: 10px;" class="row-fluid">
							
							<div  class="span12 text-center"> 
							  <input type="button" class="btn btn-success" value="submit" style="margin-left: 174px;" onclick="return candidateUpdateDetails()">
							</div>
				
					</div>
					
		  </div>
	  </div>
</div>
<script>
getAllDistrictsForAState();

function candidateUpdateDetails()
{
$("#errorDiv").html("");
var flag="false";
var electionTypeId= $("#electionTypeId option:selected").val();
var districtId= $("#districtId option:selected").val();
var str="";
if(electionTypeId==0)
{
str+="Please Select Election Type<br>";
flag="true";
}
if(districtId==0)
{
str+="Please Select District.";
flag="true";
}
if(flag=="true")
{
$("#errorDiv").html(str);
return false;
}
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
}}
});

}


</script>
	
</body>
</html>