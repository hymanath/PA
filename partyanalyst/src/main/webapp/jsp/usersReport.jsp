<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<style>
#noDataDiv
{
  font-weight:bold;
  color:red;
}
</style>
<script>
 $(document).ready(function(){
	 $('#startDate,#endDate').datepicker({
   dateFormat: 'dd-mm-yy'
   });
});
</script>

 <h4 style="text-align:center;margin-top:20px;">USERS DAYWISE REPORT</h4>

 <div>
 <div class="span12 offset2" style="margin-top:30px;">
  <div class="span5">
   <div class="span2">
    StartDate
   </div>
   <div class="span2">
   <input type="text" id="startDate" readonly/>
   </div>

  </div>
  <div class="span5">
   <div class="span2">
   End Date
   </div>
   <div class="span2">
       <input type="text" id="endDate" readonly/>
   </div>
  </div>
  <a href="javascript:{getUsersDayWiseReport()}" class="btn btn-success pull-right">Report</a>
 </div>

 <div style="margin-top:50px;">
   <img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" class="hide"></img>
 </div>

 <div id="usersReportDivId" class="span8 offset3"></div>
 <div id="noDataDiv"></div>
 <div id="dialogDiv"></div>
 <div id="processDialog"  class="hide">Please wait while your request is in process..
	<img id="processImg" src="./images/icons/search.gif" alt="Processing Image">
 </div>

<script>
function getUsersDayWiseReport()
{
	$('#ajaxImage').show();
	$('#noDataDiv,#usersReportDivId').html('');

	
	var jsObj =
	{
		startDate :'',
		endDate : ''
	}

	jsObj.startDate = $('#startDate').val();
    jsObj.endDate = $('#endDate').val();

 $.ajax({
	type:'GET',
	url: 'getUsersCompleteDayWiseReport.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			$('#ajaxImage').hide();
		if($('#startDate').val() == $('#endDate').val())
			buildUsersDayWiseReportForSameDay(result);
		else
			buildUsersDayWiseReportBetweenDates(result);

	});
}
function buildUsersDayWiseReportForSameDay(result)
{
	$('#ajaxImage').hide();
	if(result == null || result.length == 0)
	{
		$('#noDataDiv').html('No Data Available');
		return;
	}

	var str ='';

	str+='<table class="table table-bordered offset2" id="sameDayUserReport">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>User Name</th>';
	   str+='<th>Leader-MobileNo</th>';
	   str+='<th>Start Time</th>';
	   str+='<th>End Time</th>';
	   str+='<th>Count</h>';
	   str+='<th>Caste Count</h>';
	   str+='<th>Hamlet Count</h>';
	   str+='<th>Mobile Number Count</h>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
	  str+='<tr>';
	   str+='<td>'+value.name+'</td>';
	   str+='<td>'+value.leaderName+'-'+value.mobileNo+'</td>';
	   str+='<td>'+value.startTime+'</td>';
	   str+='<td>'+value.endTime+'</td>';
	   str+='<td>'+value.count+'</td>';
	   str+='<td>'+value.casteCollectedCount+'</td>';
	   str+='<td>'+value.hamletCollectedCount+'</td>';
	   str+='<td>'+value.mobileNumberCollectedCount+'</td>';
	  str+='</tr>';

	 });

    str+='</tbody>';
	str+='</table>';

	$('#usersReportDivId').html(str);

	$('#sameDayUserReport').dataTable({
		"iDisplayLength": 100
	});
}

function buildUsersDayWiseReportBetweenDates(result)
{
	var str ='';

	str+='<table class="table table-bordered offset2" id="betweendatesId">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>User Name</th>';
	    str+='<th>Leader-MobileNo</th>';
	   $.each(result[0].subList,function(index,value){
		    str+='<th>'+value.surveyDate+'</th>';
	   });
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
	  $.each(result,function(index,value){
		  str+='<tr>';
		   str+='<td>'+value.name+'</td>';
		   str+='<td>'+value.leaderName+'-'+value.mobileNo+'</td>';
		    $.each(value.subList,function(index1,value1){
		     str+='<td><a href="javascript:{getUserReportForADate('+value.userid+',\''+value1.surveyDate+'\')}">'+value1.count+'</a></td>';
			});
		  str+='</tr>';
	  });
	  
	 str+='</tbody>';
	str+='</table>';
$('#usersReportDivId').html(str);

	$('#betweendatesId').dataTable({
		"iDisplayLength": 100
	});
}
function getUserReportForADate(userId,surveyDate)
{
	$('#processDialog').dialog({
		title:"Processing",
		closeOnEscape:false	 

	});

	var jsObj =
	{
		userId :userId,
		surveyDate :surveyDate
	}

	$.ajax({
	type:'GET',
	url: 'getUserReportForADate.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		buildUserDetailsForADate(result);
	});
}
function buildUserDetailsForADate(result)
{
	$('#processDialog').dialog('close');
	var str ='';

	str+='<table class="table table-bordered offset2" id="betweendatesId">';
		str+='<thead>';
		 str+='<tr>';
		  str+='<th>Constituency</th>';
		  str+='<th>Booth</th>';
		  str+='<th>Caste Collected</th>';
		  str+='<th>Hamlet Collected</th>';
		  str+='<th>Mobile Collected</th>';
		 str+='</tr>';
        str+='</thead>';
		str+='<tbody>';
		 
		 $.each(result,function(index,value){
			 $.each(value.subList,function(index1,value1){
				  str+='<tr>';
					str+='<td>'+value.name+'</td>';
				   	str+='<td>'+value1.name+'</td>';
					str+='<td>'+value1.casteCollectedCount+'</td>';
					str+='<td>'+value1.hamletCollectedCount+'</td>';
					str+='<td>'+value1.mobileNumberCollectedCount+'</td>';
				  str+='</tr>';
			 });
		 });

		str+='</tbody>';
		str+='</table>';

$('#dialogDiv').html(str);
$('#dialogDiv').dialog({
	title:"Collected Samples Details",
	width:800
});

}
</script>
 </body>
</html>