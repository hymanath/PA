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
#trigger{
  font-size: 12px !important;
    height: 15px;
    line-height: 15px;
    padding: 3px;
}
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:12px; padding:12px 2px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			
			table tr td a{color:#333;}

			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			/*-----*/
			.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:136px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
			.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
			.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
			.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
	
			.requiredFont{
				color:red;
				font-size:13px;
			}

			#errorDiv
			{
			 font-weight:bold;
			 color:red;
			}
			
			.highlight{
			cursor: pointer;
			}
			
			.datePickerCls{
			 cursor: text !important;
			}
			
			.survey_nav ul li .dropdown-menu{background:#ffcc00; border-top-left-radius:0px; border-top-right-radius:0px;margin-top:-1px; margin-right:5px;}
			.survey_nav ul li .dropdown li a{color:#333; }
			
			.ui-multiselect{
				width:200px !important;
				}

			#reportTab  thead th , #panchayatStatusTable thead th ,#userReportTable thead th{
				border-bottom: 1px solid black  !important;
				cursor: pointer  !important;
				font-weight: bold  !important;
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 30px !important;
			}
			
			.errorRed{background:red;}
			.errorYellow{background:yellow;}
			.errorLgreen{background:lightgreen;}
			.errorGreen{background:green;}
	
	.ui-icon-closethick 
		{
			margin-top:-8px !important;
			margin-left:-8px !important;
		}
		</style>
<script>

$( document ).ready(function() {
		
		$('#startDateId,#endDateId').datepicker({
		dateFormat:'dd/mm/yy'
	});
	
		$('#constituencyId').multiselect({
			  noneSelectedText:"Select Constituency(s)"});
		});	
</script>
<div class="container">
<div class="row" id="boothWise">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>DUPLICATE MOBILE NUMBERS REPORT</h4>	
							
						<div class="row-fluid">
							<div class="span4">
								<label>Select Constituency</label>
							<s:select theme="simple" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" multiple="true"/>
					
							</div>
							<div class="span4">
								<label>From Date</label>
								<input type="text" id="startDateId" readonly="true"/>
							</div>	
							<div class="span4">
								<label>To Date</label>
								<input type="text" id="endDateId" readonly="true"/>
							</div>	
								
						</div>
						<a class="btn btn-primary offset4" href="javascript:{getDuplicateMobileNumbersDetails()}">Report</a>
						<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide"  id="ajaxImage"/>
						<a class="btn btn-primary hide" href="javascript:{generateExcel()}" id="excelBtnId">Export To Excel</a>
						<div id="errorDiv" class="offset5"></div>
						<div id="duplicateDetailsDivId"></div>
					</div>
					

				</div>
			</div>
		</div>
</div>
</div>
<!--<div class="offset6 span8">
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

<div id="duplicateDetailsDivId"></div>-->
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
		$('#duplicateDetailsDivId').html('<h4 class="errorClass">No Data Available...</h4>');
		return;
	}
	var str ='';
	str += '<div class="row-fluid">';
     str+='<div class="span12 m_top20">';
	 str+='<h4 class="">SUMMARY REPORT</h4>';
	 str+='<table class="table table-bordered m_top20 table-hover table-striped">';
	  str+='<thead  class="alert alert-success">';
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
	 str+='</div>';
	str += '<div class="row-fluid" style="overflow-x:scroll">';
    str+='<div class="span12 m_top20">';

	str+='<h4>DUPLICATE MOBILE NUMBERS DETAILS</h4>';
	str+='<table class="table table-bordered m_top20 table-hover table-striped">';
	 str+='<thead class="alert alert-success">';
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