<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
  <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script src="js/maps/googleMap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/callCenter/surveyCallCenter.js"></script>
<script src="js/callCenter/surveyCallCenter1.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
</head>
<body>
<script>
$( document ).ready(function() {
	$('#startDateId,#endDateId').datepicker({
		dateFormat:'dd/mm/yy'
	});
});
</script>

<div class="offset6">
 <div class="span12">
 <div id="span4">
    Select Constituencies
   </div>
   <div id="span4">
 <s:select theme="simple" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" multiple="true"/>
   </div>
 </div>
  <div class="span12">
   <div id="span4">
    Start Date
   </div>
   <div id="span4">
    <input type="text" id="startDateId"/>
   </div>
  </div>
  <div class="span12">
   <div id="span4">
   End Date
   </div>
   <div id="span4">
     <input type="text" id="endDateId"/>
   </div>
  </div>
</div>


<a class="btn btn-primary offset6" href="javascript:{getDuplicateMobileNumbersDetails()}">Report</a>

<div id="duplicateDetailsDivId"></div>
<script>

function getDuplicateMobileNumbersDetails()
{
	$('#duplicateDetailsDivId').html('');

	var jsObj = 
		     {
		         startDate:$('#startDateId').val(),
                 endDate:$('#endDateId').val(),
                 frequencyCount:3,
				 constituencyIds : $('#constituencyId').val()
			 }


	$.ajax({
		type:'GET',
		url: 'getDuplicateMobileNumbersDetails.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
			console.log(result);
			buildDuplicateMobileNumbersDetails(result);
	});
}
function buildDuplicateMobileNumbersDetails(result)
{
	if(result == null || result.length == 0)
	{
		$('#duplicateDetailsDivId').html('<h4>No Data Available...</h4>');
		return;
	}
	var str ='';

     str+='<div class="span3 offset4">';
	 str+='<h4 class="offset2">SUMMARY</h4>';
	 str+='<table class="offset1 table table-bordered m_top20 table-hover table-striped">';
	  str+='<thead>';
	   str+='<tr>';
	    str+='<th>No of Mobile Numbers</th>';
		str+='<th>Count</th>';
	   str+='</tr>';
	  str+='</thead>';
	  str+='<tbody>';

	  $.each(result[0].countList,function(index,value){
		  str+='<tr>';
		   str+='<td>'+value.count+'</td>';
   		   str+='<td>'+value.total+'</td>';
		  str+='</tr>';
	  });
	  str+='</tbody>';
	 str+='</table>';
	 str+='</div>';
	 
    str+='<div class="span12">';

	str+='<h4 class="offset2">DUPLICATE MOBILE NUMBERS DETAILS</h4>';
	str+='<table class="offset2 table table-bordered m_top20 table-hover table-striped">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>Mobile Number</th>';
   	   str+='<th>Constituency</th>';
 	   str+='<th>Mandal/Muncipality</th>';
	   str+='<th>Booth No</th>';
	   str+='<th>User</th>';
	   str+='<th>Date</th>';
	   str+='<th>Voter Name</th>';
	   str+='<th>House No</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		 str+='<tr>';
		 	str+='<td rowspan='+value.subList.length+'>'+value.mobileNumber+'('+value.count+')</td>';

		 $.each(value.subList,function(index1,value1){
			
				str+='<td>'+value1.constituencyName+'</td>';
				str+='<td>'+value1.tehsilName+'</td>';
				str+='<td>'+value1.partNo+'</td>';
				str+='<td>'+value1.userName+'</td>';
				str+='<td>'+value1.date+'</td>';
				str+='<td>'+value1.voterName+'</td>';
				str+='<td>'+value1.hoseNo+'</td>';
			str+='</tr>';

		 });	 
		  
	 });
	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

$('#duplicateDetailsDivId').html(str);
}
</script>
</body>
</html>