<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">	
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
 <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootStrapDateRange/moment.js"></script>
<script type="text/javascript" src="js/bootStrapDateRange/daterangepicker.js"></script>

 <link rel="stylesheet" type="text/css" href="css/daterangepicker-bs2.css"/>
 <script src="js/cardsDashBoard/js2.3.2/Chart.js"></script>
	<script src="js/cardsDashBoard/js2.3.2/Chart.min.js"></script>
<script type="text/javascript" src="js/exportexcel.js"></script>

<title>IVR Report</title>
</head>
<body>
<div class="container ">	
	<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-0 ">
				<h3 class="text-center text-uppercase">Previous Calls Details</h3>
			</div>
		</div><!-- Title Row End-->
		<!--<div class = "row" style="margin-top:20px;">
				<table  style="margin-left: 270px;">
					<tr><td><label>From Date :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><label>To Date   :</label>&nbsp;</td><td>&nbsp;&nbsp;<input type="text" readonly="readonly" id="toDate" /></td></tr>		
				</table>
				<input type="button"  class="btn btn-success offset5"  value="Submit" onclick="getIvrPreviousCallsBasicInfo();"/>
				</div>-->

				
		<div class="row">
	<div class="span10 offset1 form-inline">
    <label>From Date: &nbsp; </label><input type="text" readonly="readonly" id="fromDate"/><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To Date: &nbsp; </label>
       <input type="text" readonly="readonly" id="toDate" />
    <button class="btn btn-success" onclick="getIvrPreviousCallsBasicInfo();">submit</button>
  </div>
</div>
				
<img style="height:18px;width:16px;margin-left:1px;display:none;" src="./images/icons/search.gif" id="basicajaxImg" class="offset1"/>
<div class="row-fluid" style="margin-top: 20px;">
<div class="span4 border-radius-0 mb-10" id="basicCountDiv"></div>
<div id="previousCntDiv" class="span8 border-radius-0 mb-10"></div>
</div>
	<div id="enquiryDataDiv" style="clear:both;"></div>		
		<!--<div id="enquiryDiv"  class="offset3">
		<div id="errorDiv"></div>
		<table>
		
		<tr>
		<tr id="constdisplaydivid" style="display: table-row;">
		<td><b>Select Constituency</b></td>
		<td>
		<select id="displayconstbox"></select>
		</td>
		</tr>
		<tr>
		<td><b>Select Location Type</b></td>
		<td>
		<select id="selLctnType">	
		<option value="0">all</option>		
		<option value="1">constituency</option>	
		<option value="2">tehsil/municipality</option>	
		
		</select>
		</td>
		</tr>
		</table>
		<input type="button" value="Submit" style="margin-top:10px;" class="btn btn-medium btn-success border-radius-0 offset2" onclick="getCadreIvrEnquiryDetails();" />
		<img style="height:18px;width:16px;margin-left:1px;display:none;" src="./images/icons/search.gif" id="ajaxImg" class="offset1"/>
		</div>
	    <div id="ivrEnquiryDetailsDiv"  style="margin-top:23px;"></div>-->
		</div>
		<script>

		function getDates()
		{
			var jObj ={
			task:"getDates"             
		}	
		$.ajax({
			type : "POST",
			url : "getAvailableDatesAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$("#fromDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			minDate:result[0],
			maxDate: result.length - 1
		});
			var minDate =result[0];
		$("#fromDate").datepicker("setDate", minDate);
		$("#toDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			minDate: result[0],
			maxDate:result.length - 1
		});
			var maxDate = result.length - 1;
		$("#toDate").datepicker("setDate", maxDate);
		getIvrPreviousCallsBasicInfo();
		});
	
		
		}
			
		function getConstituencies(){
		
		$("#displayconstbox").html("");
		
		var jObj ={
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getUserAccessConstituencyAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>Select Constituency</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox").html(str);
			//getCadreIvrEnquiryDetails();
		});
		
	}

	function getCadreIvrEnquiryDetails()
 {
	 $("#errorDiv").html('').css("color","red");
	 var constituencyId = $("#displayconstbox").val();
	 var locationLvl =  $("#selLctnType option:selected").text();

	if(constituencyId == 0)
	 {
	$("#errorDiv").html('Select Constituency');
	return;
	 }
	$("#ajaxImg").show();
	 $("#ivrEnquiryDetailsDiv").html("");
	   var jsObj = {	
			locationLvl:locationLvl,
			locationValue:constituencyId
			}
     $.ajax({
			type : "POST",
			url : "cadreIvrEnquiryInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		 $("#ajaxImg").hide();
		 buildEnquiryData(result);
		});
 }
function buildEnquiryData(resultList)
 {
	 var result = resultList.apList;
	  var str ='';
	 if(result == null || result.length == 0)
	 {
		str+='<span style="color:red">No Data Available...</span>';
		$("#ivrEnquiryDetailsDiv").html(str);
		return;
	 }
	
	 str+='<table class="table table-bordered">';
	 str+='<thead class="alert-success">';
	 str+='<th>Location</th>';
	 str+='<th>MobileNo</th>';
	 str+='<th>Details</th>';
	 str+='<th>Status</th>';
	 str+='<th>Received</th>';
	 str+='<th>Delivered</th>';
	 str+='</thead>';
	 str+='<tbody>';
	 for(var i in result)
	 {
	 str+='<tr>';
	 str+='<td>'+result[i].locationName+'</td>';
	 str+='<td>'+result[i].jobCode+'</td>';
	 str+='<td>'+result[i].name+'</td>';
	 str+='<td>'+result[i].areaName+'</td>';
	 //str+='<td>'+result[i].received+'</td>';
	// str+='<td>'+result[i].notReceived+'</td>';
	 if(result[i].received != null){
		   str += '  <td>' +result[i].received+ '</td>';
		}else{
		  str += '  <td></td>';
		}
		if(result[i].notReceived != null){
		  str += '  <td>' +result[i].notReceived+ '</td>';
		 }else{
		  str += '  <td></td>';
		}
	 str+='</tr>';
	 }
	 str+='</tbody>';
	 str+='</table>';
	 $("#ivrEnquiryDetailsDiv").html(str);

 }
 function getIvrBasicCount()
	{
		$("#basicajaxImg").show();
		$("#basicCountDiv").html('');
		var jsObj = {	
			state:0,
			task:"basicCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getCadreIVRBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				$("#basicajaxImg").hide();
		buildIvrCount(result);
		});
	}
	function getIvrPreviousCallsBasicInfo()
	{
		
		$("#previousCntDiv").html('');
		var fromDate = $("#fromDate").val();
		var toDate =  $("#toDate").val();
		var jsObj = {	
			fromDate:fromDate,
			toDate:toDate,
			task:"previousCallsCnt"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getIvrPreviousCallBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				
		buildPreviousCount(result,fromDate,toDate);
		});
	}
	function buildIvrCount(result)
	{
		var str='';
	str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover">';
	str+='<tbody>';
	str+=' <tr>';
								
	str+='<td><div><h2>'+result.printingCompleted+'</h2><p>Dispatch Count</p></div></td>';
	str+='<td><div ><h2>'+result.total+'</h2><p>Total Ivr Calls</p></div></td>';
	str+='<td><div ><h2>'+result.totalError+'</h2><p>Error Count</p></div></td>';
	
	str+='</tr>';
	str+='</tbody>';
	str+='</table>';
	$("#basicCountDiv").html(str);
	}
	function buildPreviousCount(result,fromDate,toDate)
	{
			var str='';
	str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover">';
	str+='<tbody>';
	str+=' <tr>';
		if(result.totalCalls!= null && result.totalCalls > 0)						
	str+='<td><div><h2><a style="cursor:pointer;" onclick="getIvrDataByType(\'Constituency\',\''+fromDate+'\',\''+toDate+'\')">'+result.totalCalls+'</a></h2><p>No Of Constituencies</p></div></td>';
		else
	str+='<td><div><h2>'+result.totalCalls+'</h2><p>No Of Constituencies</p></div></td>';
	if(result.totalIvrCalls!= null && result.totalIvrCalls > 0)					
	str+='<td><div ><h2><a style="cursor:pointer;" onclick="getIvrDataByType(\'Mandal\',\''+fromDate+'\',\''+toDate+'\')">'+result.totalIvrCalls+'</a></h2><p>No Of mandals</p></div></td>';
	else
	str+='<td><div ><h2>'+result.totalIvrCalls+'</h2><p>No Of mandals</p></div></td>';
	str+='<td><div ><h2>'+result.received+'</h2><p>Received in Constituencies</p></div></td>';
	str+='<td><div ><h2>'+result.notReceived+'</h2><p>Delivered in Constituencies</p></div></td>';
	str+='<td><div ><h2>'+result.totalAnswerdCalls+'</h2><p>Received in mandals</p></div></td>';
	str+='<td><div ><h2>'+result.notMember+'</h2><p>Delivered in mandals</p></div></td>';
	str+='</tr>';
	str+='</tbody>';
	str+='</table>';
	$("#previousCntDiv").html(str);
	}

	function getIvrDataByType(type,fromDate,toDate)
	{
		
		var jsObj = {	
			fromDate:fromDate,
			toDate:toDate,
			locationType:type,
			task:"previousCallsData"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getIvrPreviousCallBasicInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				
		buildPreviousCallsData(result,type);
		});
	}

	function buildPreviousCallsData(resultList,type)
	{
	 var result = resultList.apList;
	 var str ='';
	 if(result == null || result.length == 0)
	 {
		str+='<span style="color:red">No Data Available...</span>';
		$("#enquiryDataDiv").html(str);
		return;
	 }
	 str+='<table class="table table-bordered">';
	 str+='<thead class="alert-success">';
	 if(type =="Constituency")
		{
	 str+='<th>District</th>';
	 str+='<th>Constituency</th>';
		}
	 else if(type =="Mandal")
		{
	 str+='<th>Constituency</th>';
	 str+='<th>Mandal</th>';
		}
	 str+='<th>Total Ivr Calls</th>';
	 str+='<th>Cards Received</th>';
	 str+='<th>Cards Not Received</th>';
	 str+='<th>Not Member</th>';
	 str+='<th>Error</th>';
	 str+='</thead>';
	 str+='<tbody>';
	 for(var i in result)
	 {
	 var error = (result[i].totalCalls) - (result[i].received +result[i].notReceived+result[i].notMember) 
	 str+='<tr>';
	 str+='<td>'+result[i].locationName+'</td>';
	 str+='<td>'+result[i].name+'</td>';
	 str+='<td>'+result[i].totalCalls+'</td>';
	 str+='<td>'+result[i].received+'</td>';
	 str+='<td>'+result[i].notReceived+'</td>';
	 str+='<td>'+result[i].notMember+'</td>';
	 str+='<td>'+error+'</td>';
	 str+='</tr>';
	 }
	 str+='</tbody>';
	 str+='</table>';
	 $("#enquiryDataDiv").html(str);
	}
		</script>
<script>
getDates();
getConstituencies();
getIvrBasicCount();

</script>
</body>
</html>
