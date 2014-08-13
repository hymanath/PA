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
<style>
#errorDiv,.errorClass
{
 font-weight:bold;
 color:red;
}
</style>
<script>
$( document ).ready(function() {
	$('#startDateId,#endDateId').datepicker({
		dateFormat:'dd/mm/yy'
	});
	
	$('#constituencyId').multiselect();
	
});
</script>

<div class="offset6 span8">
 <h4>DUPLICATE MOBILE NUMBERS REPORT</h4>
 <div class="span8">
 <div id="span4" style="margin-top: 10px;">
    Select Constituencies  :  

	<s:select theme="simple" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" multiple="true"/>
   </div>
 </div>
  <div class="span8">
   <div id="span4" style="margin-top: 10px; margin-left: 70px;" >
    Start Date   :   
    <input type="text" id="startDateId" readonly="true"/>
   </div>
  </div>
  <div class="span8">
   <div id="span4" style="margin-top: 10px; margin-left: 75px;" >
   End Date    : 
     <input type="text" id="endDateId" readonly="true"/>
   </div>
  </div>
</div>

<a class="btn btn-primary offset9" href="javascript:{getDuplicateMobileNumbersDetails()}">Report</a>
<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide"  id="ajaxImage"/>

<a class="btn btn-primary hide" href="javascript:{generateExcel()}" id="excelBtnId">Export To Excel</a>
<div id="errorDiv" class="offset5"></div>

<div id="duplicateDetailsDivId"></div>
<script>

function getDuplicateMobileNumbersDetails()
{
    $('#excelBtnId').hide();
	$('#duplicateDetailsDivId,#errorDiv').html('');

	var errorStr = '';

	if($('#constituencyId').val() == null)
		errorStr += 'Please select atlease one constituency<br>';

	if($('#startDateId').val() == "")
		errorStr += 'Please select start date <br>';

	if($('#endDateId').val() == "")
		errorStr += 'Please select nd date <br>';

	if(errorStr.length >0)
	{
		$('#errorDiv').html(errorStr);
		return;
	}
	$('#ajaxImage').show();

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
			buildDuplicateMobileNumbersDetails(result);
	});
}
function buildDuplicateMobileNumbersDetails(result)
{
		$('#ajaxImage').hide();

	if(result == null || result.length == 0)
	{
		$('#duplicateDetailsDivId').html('<h4 class="offset4 errorClass">No Data Available...</h4>');
		return;
	}
	var str ='';

     str+='<div class="span3 offset4">';
	 str+='<h4 class="offset2">SUMMARY</h4>';
	 str+='<table class="offset2 table table-bordered m_top20 table-hover table-striped">';
	  str+='<thead>';
	   str+='<tr>';
	   // str+='<th>No of Mobile Numbers</th>';
		//str+='<th>Count</th>';
		str+='<th></th>';
		 $.each(result[0].countList,function(index,value){
   		   str+='<th>'+value.count+'</th>';
	     });
	   str+='</tr>';
	  str+='</thead>';
	  str+='<tbody>';

	  str+='<tr>';
	  str+='<td>Count</td>';
	  $.each(result[0].countList,function(index,value){
		
		  // str+='<td>'+value.count+'</td>';
   		   str+='<td>'+value.total+'</td>';		
	  });
	  str+='</tr>';

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
				if(value1.tehsilName != null)
				  str+='<td>'+value1.tehsilName+'</td>';
				else
				  str+='<td>'+value1.muncipalityName+'</td>';
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
	$('#excelBtnId').show();

}
</script>
<script>

var tableToExcel = (function() {
var uri = 'data:application/vnd.ms-excel;base64,'
, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
return function(table, name) {
if (!table.nodeType) table = document.getElementById(table)
var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
window.location.href = uri + base64(format(template, ctx))
}
})()

function generateExcel(id)
{
	tableToExcel("duplicateDetailsDivId", 'Duplicate Numbers Report Report');
}

</script>
</body>
</html>