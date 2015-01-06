<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

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
		<div id="enquiryDiv"  class="offset3">
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
	    <div id="ivrEnquiryDetailsDiv"  style="margin-top:23px;"></div>
		</div>
		<script>
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
 
		</script>
<script>
getConstituencies();

</script>
</body>
</html>
