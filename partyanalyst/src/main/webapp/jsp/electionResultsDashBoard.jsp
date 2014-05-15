<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Live Election Results Analysis</title>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<!--<script type="text/javascript" src="js/jquery.dataTables.js"></script>-->
 <!--  <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>-->
	<!--<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>-->
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />


 
 </head>
<body>
<style>
.button
{
	width:200px;
}
.tableClass1 table {border: 3px solid #000}
.tableClass1 table td, tr {
	border: 2px solid #000;
	padding:5px;
	font-weight:bold;
}


</style>
<script>
 $( document ).ready(function() {
	 $('#scopeId').trigger('change');

	//$('#locaionsId1').multiselect();

	 $('.reportType').change(function(){
		 $('#test,#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId').html('');
	 });

	 $('#scopeId').change(function(){
		 console.log(this);
		 $('#rgntxt').text("Select "+$('#scopeId  :selected').text());
	 });
  
 });
</script>

<div class="span12 container"  style="border:1px solid #BDA870;margin-left:180px;padding:8px;margin-top:20px;">

<h4 style="text-align:center;">Live Results Analysis</h4>

  <div class="row-fluid offset2">
   <div class="span10">
    <div class="span3">
	 Select Election
	</div>
	<div class="span5">
	 <select id="electionId" style="width:250px;">
	  <option value="258">2014 Assembly Election</option>
	  <option value="38">2009 Assembly Election</option>
	  <option value="3">2004 Assembly Election</option>
	 </select>
	</div>
   </div>
  </div>



					<div class="row-fluid">
					 <div class="span5">
					   <div class="row-fluid">
					    <div class="span4">
                          <label class="pull-left">Select Level</label>						  
						</div>
						 <div class="span8">
							<select onchange="getLocationDetailsForSelectedScope(this.value)" class="input-block-level" id="scopeId" name="scopeId">
								<option value="3">Region</option>
								<option value="2">District</option>
								<option value="4">Parliament</option>
								<option value="5">Assembly</option>
						   </select>	
                          </div>
					   </div>

					 </div>
					  <div class="span5">
					   <div class="row-fluid">
					    <div class="span3">
                          <label class="pull-left" id="rgntxt">Select Region</label>			  
						</div>
						 <div class="span8">
						   <select class="input-block-level" id="locaionsId1" multiple="true"></select>

						 </div>
					   </div>
					 </div>
					</div>

					<div class="row-fluid offset1">

						<label class=" radio inline"> 
						 <input type="radio" class="reportType matrixRprt" id="matrixReportId" value="Matrix Report" name="report" checked="true">Matrix Report
						</label>

						<label class=" radio inline"> 
						<input type="radio" class="reportType matrixRprt" id="subReportId" value="Sub Report" name="report">Sub Report
					   </label>

					</div>


					<div class="row-fluid offset1" style="margin-top:20px;">

						<div class="span1"> 
						<input type="button" onClick="showSelectedReport()" value="Disply" style="padding:3px;"/>
						</div>

						<div class="span1"> 
						<input type="button" onClick="clearFields()" value="Clear" style="padding:3px;"/>
						</div>

						<div class="span1"> 
						<input type="button" onClick="exportToExcel()" value="Export To Excel" style="padding:3px;"/>
						</div>
	<img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="margin-left:70px;display:none;"/>

					</div>

					<div id="errorDiv" style="font-weight:bold;color:red;"></div>


    <div class="offset1">
	 <div id="matrixWonSummaryId"></div>
	 <div id="matrixLeadSummaryId"></div>
	</div>
		
	<div id="reportDiv" style="margin-top:30px;">

	<div id="constituencyResultsDiv"></div>

	<div id="test"  class="pull-left offset1"></div>
	<div id="matridLeadId"  class="pull-right" style="margin-right:100px;"></div>

	</div>

</div>
 
</div>

<!--<input type="button" value="TEST" onClick="testIt();"/>-->

<script>

function testIt()
{
	$.ajax({
          type:'POST',
          url: 'getElectionResultsSummary.action',
          dataType: 'json',
          data: {},

          success: function(result){ 
			   buildReservationCategoryResult(result);
			   buildResultByConstituencyType(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}

function buildReservationCategoryResult(result)
{
	var str ='';

	str+='<table border="1">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th rowspan="3"></th>';
	   str+='<th colspan="12">SC</th>';
	   str+='<th colspan="12">ST</th>';
	   str+='<th colspan="12">GENERAL</th>';
	   //str+='<th colspan="6">RURAL</th>';
	   //str+='<th colspan="6">URBAN</th>';
	   //str+='<th colspan="6">RURAL-URBAN</th>';
	  str+='</tr>';

      str+='<tr>';
	   $.each(result.subList[0].reservationDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th colspan="2">'+value1.name+'</th>';			 
	       });
	   });
	  str+='</tr>';

	   str+='<tr>';
	   $.each(result.subList[0].reservationDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th>W</th>';	
				 str+='<th>L</th>';	
	       });
	   });
	  str+='</tr>';

	 str+='</thead>';
	 str+='<tbody>';
    
	 $.each(result.subList,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.name+'</td>';
		  $.each(value.reservationDetails,function(index1,value1){
		     $.each(value1.partiesDetails,function(index2,value2){
			     str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">'+value2.count+'</a></th>';
				  str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">0</a></th>';
	       });
	     });
		 str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';

	$('#test').html(str);

}

function buildResultByConstituencyType(result)
{
	
	var str ='';

	str+='<table border="1">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th rowspan="2"></th>';
	  // str+='<th colspan="6">SC</th>';
	  // str+='<th colspan="6">ST</th>';
	  // str+='<th colspan="6">GENERAL</th>';
	   str+='<th colspan="6">RURAL</th>';
	   str+='<th colspan="6">URBAN</th>';
	   str+='<th colspan="6">RURAL-URBAN</th>';
	  str+='</tr>';

      str+='<tr>';
	   $.each(result.subList[0].constituencyWiseDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th>'+value1.name+'</th>';			 
	       });
	   });
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
    
	 $.each(result.subList,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.name+'</td>';
		  $.each(value.constituencyWiseDetails,function(index1,value1){
		     $.each(value1.partiesDetails,function(index2,value2){
			     str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport(\''+value1.name+'\','+value2.id+','+value.id+')}">'+value2.count+'</a></th>';			 
	       });
	     });
		 str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';

	$('#matridLeadId').html(str);

}

function getConstituenciesDetailsForSubReport(type,partyId,locationId)
{
	$.ajax({
          type:'POST',
          url: 'getConstituenciesDetailsForSubReport.action',
          dataType: 'json',
          data: {type:type,partyId:partyId,locationId:locationId,locationType:2},

          success: function(result){ 

			   console.log(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

var locationDtls={
	task:''	 
};
function getLocationDetailsForSelectedScope()
{
	locationDtls.task =$('#scopeId :selected').text();

	$.ajax({
          type:'POST',
          url: 'getElectionResultsLocations.action',
          dataType: 'json',
          data: {task:JSON.stringify(locationDtls)},

          success: function(result){ 
			   buildLocationDetails(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function buildLocationDetails(result)
{
	$('#locaionsId1').find('option').remove();

	$.each(result,function(index,value){
		$('#locaionsId1').append('<option value="'+value.id+'">'+value.name+'</option>');
	});

		//$('#locaionsId1').multiselect('refresh');


}

function showSelectedReport()
{

	$('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#errorDiv').html('');

	if($('#locaionsId1').val() == null)
	{
        $('#errorDiv').html('Please select location(s)');
		return;
	}
      
	   $('#ajaxImage').show();

	$('#subReportId').attr('disabled',false);

    if($('#scopeId').val() == 5)
	{
		getConstituencyWiseResults();
		$('#subReportId').attr('disabled',true);
	}
	else
	{
	  

	if($('input:radio[name=report]:checked').val() == "Matrix Report")
           getMatrixReport();
	else
	{
            getSubReportForElectionResultByConstituencyType();
            getSubReportForElectionResultByConstituencyReservation();
	}

	}
}
var matrixReportDtls={
	electionId:'',
    scopeId:'',
    locationIds:[]
		
};

function getMatrixReport()
{
	matrixReportDtls.electionId = $('#electionId').val();
	matrixReportDtls.scopeId = $('#scopeId').val();
	matrixReportDtls.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getMatrixReportForElectionResult.action',
          dataType: 'json',
          data: {task:JSON.stringify(matrixReportDtls)},

          success: function(result){ 
			  	   $('#ajaxImage').hide();

				   if(result == null || result.length == 0)
			       {
					   $('#errorDiv').html('<h5>No Data Available..</h5>');
                     return;
				   }
			  $('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId').html('');
			    buildMatrixReportSummaryDetails(result,"Won");
				buildMatrixReportSummaryDetails(result,"Lead");
			    buildMatrixReport(result,"Won");
			    buildMatrixReport(result,"Lead");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function  buildMatrixReport(result,status)
{
	var str ='';

str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>'+status+'</th>';
	    $.each(result[0].partiesDetails,function(index,value){
           str+='<th>'+value.name+'</th>';
		});
	  str+='</tr>';
	 str+='</thead>';

	 str+='<tbody>';
	
	  
	  $.each(result,function(index,value){
		   str+='<tr>';
		    str+='<td>'+value.name+'</td>';
            $.each(value.partiesDetails,function(index1,value1){
				if(status == "Won")
                 //str+='<td><a href="javascript:{getConstituencyDetails('+value.id+','+value1.id+',1);}">'+value1.winCount+'</a></td>';
				str+='<td>'+value1.winCount+'</td>';
				else
				 //str+='<td><a href="javascript:{getConstituencyDetails('+value.id+','+value1.id+',0);}">'+value1.leadCount+'</td>';
				str+='<td>'+value1.leadCount+'</td>';
		    });
			 str+='</tr>';
	  });
	  
	
 	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

  if(status == "Won")
	$('#test').html(str);
  else
	 $('#matridLeadId').html(str);
}

function buildMatrixReportSummaryDetails(result,status)
{
	if(result == null || result.length  == 0)
		return;
   var str ='';
   if(status == "Won")
	   str+='<h5>Won Summary</h5>';
   else
	   str+='<h5>Lead Summary</h5>';
	str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;">';
	str+='<thead>';
	str+='<tr>';

	$.each(result[0].summaryDetails,function(index,value){
		str+='<th style="border:1px solid #000;" class="alert alert-info">'+value.name+'</th>';
		if(status == "Won")
		 str+='<th style="border:1px solid #000;" class="alert alert-info">'+value.winTotalCount+'</th>';
		else
		 str+='<th style="border:1px solid #000;" class="alert alert-info">'+value.leadTotalCount+'</th>';

	});

	str+='</tr>';
	str+='</thead>';
	str+='</table>';
	str+='</div>';
	
	if(status == "Won")
		$('#matrixWonSummaryId').html(str);
	else
		$('#matrixLeadSummaryId').html(str);

}

var subReportDtls={
	electionId:'',
    scopeId:'',
    locationIds:[]
		
};


function getSubReportForElectionResultByConstituencyType()
{
	subReportDtls.electionId = $('#electionId').val();
	subReportDtls.scopeId = $('#scopeId').val();
	subReportDtls.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getSubReportForElectionResultByConstituencyType.action',
          dataType: 'json',
          data: {task:JSON.stringify(subReportDtls)},

          success: function(result){ 
			   $('#ajaxImage').hide();
			buildSubReportByConstituencyType(result,"constnType");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function getSubReportForElectionResultByConstituencyReservation()
{
	subReportDtls.electionId = $('#electionId').val();
	subReportDtls.scopeId = $('#scopeId').val();
	subReportDtls.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getSubReportForElectionResultByConstituencyReservationType.action',
          dataType: 'json',
          data: {task:JSON.stringify(subReportDtls)},

          success: function(result){
			   $('#ajaxImage').hide();
			buildSubReportByConstituencyType(result,"reservationType");
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildSubReportByConstituencyType(result,type)
{

	 if(result == null || result.length == 0)
	 {	
		 $('#errorDiv').html('<h5>No Data Available..</h5>');
		 return;
	 }


	var str ='';
	str+='<div class="tableClass1">';
	  if(type == "reservationType")
       str+='<h5>SC,ST,General Constituencies Analysis</h5>';
	  else
	  str+='<h5>Rural,Urban,Rural-Urban Analysis</h5>';

	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	 str+='<thead>';
	  str+='<tr>';
		  str+='<th rowspan="3"></th>';
		  $.each(result[0].reservationDetails,function(index,value){
			  var spanCnt = value.partiesDetails.length * 2;
			   str+='<th colspan="'+spanCnt+'" style="border:1px solid #000;">'+value.name+'</th>'
		  });
	  str+='</tr>';
	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				   str+='<th colspan="2" style="border:1px solid #000;">'+value1.name+'</th>'
			   });
		  });
	  str+='</tr>';

	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				   str+='<th style="border:1px solid #000;">W</th>'
   				   str+='<th style="border:1px solid #000;">L</th>'
			   });
		  });
	  str+='</tr>';

	  str+='</thead>';
	 str+='<tbody>';

     $.each(result,function(index,value){
		  str+='<tr>';
		  if(value.name == "")
		   str+='<td>GENERAL</td>';
		  else
 		   str+='<td>'+value.name+'</td>';

		   $.each(value.reservationDetails,function(index1,value1){
			    $.each(value1.partiesDetails,function(index2,value2){
					str+='<td>'+value2.winCount+'</td>';
					str+='<td>'+value2.leadCount+'</td>';
				 });
		   });

		  str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';
	str+='</div>';


  if(type == "reservationType")
	$('#matrixLeadSummaryId').html(str);
  else
	$('#matrixWonSummaryId').html(str);

}
var constnDtls = {
	locationId:'',
	scopeId:'',
	partyId:'',
	statusId:''
};

function getConstituencyDetails(locationId,partyId,statusId)
{
	constnDtls.locationId = locationId;	
	constnDtls.partyId = partyId;
	constnDtls.statusId = statusId;
	constnDtls.scopeId = $('#scopeId').val();
	$.ajax({
          type:'POST',
          url: 'getElectionResultsSummary.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			   buildReservationCategoryResult(result);
			   buildResultByConstituencyType(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
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
function exportToExcel()
{
	  tableToExcel('reportDiv', 'Election Results');
}

function clearFields()
{
 	$('#test,#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId').html('');
}
</script>
<script>
var details={
	electionId:'',
	constituencyIds:[] 	
};
function getConstituencyWiseResults()
	 {
		details.electionId = $('#electionId').val();
		details.constituencyIds = $('#locaionsId1').val();
		$.ajax({
	          type:'POST',
	          url: 'getdashBoardConstituencyWiseResults.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(details)},
	     	  }).done(function(result){ 
				  buildConstituencyResults(result);
		   });
	 }
	 function buildConstituencyResults(result)
	 {
	   $('#constituencyResultsDiv').show();
	   $('#constituencyResultsDiv').show();
	   if(result != null)
	   {		
		var str = '';
		//str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">CONSTITUENCY WISE LIVE RESULTS</h4>';
		str+='<div class="tableClass1 offset1">';
		str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
		//str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Parliament Id</th>';
		str += '<th>Parliament Name</th>';
		str += '<th>Assembly Id</th>';
		str += '<th>Assembly Name</th>';
		str += '<th>First</th>';
		str += '<th>Second</th>';
		str += '<th>Lead By</th>';
		str += '</tr>';
		
		for(var i in result)
		{	
            str += '<tr>';		
			str += '<td>'+result[i].parliamentNo+'</td>';
			str += '<td>'+result[i].parliamentName+'</td>';  
			str += '<td>'+result[i].assemblyNo+'</td>';
			str += '<td>'+result[i].assemblyName+'</td>';			
			str += '<td>'+result[i].firstRankCandidateName+'</td>';
			str += '<td>'+result[i].secondRankCandidateName+'</td>';
			str += '<td>'+result[i].leadBy+'</td>';
			str += '</tr>';
		}
		str += '</table>';
		str+='</div>';
		$('#constituencyResultsDiv').html(str);
	} 
}
</script>
</body>
</html>