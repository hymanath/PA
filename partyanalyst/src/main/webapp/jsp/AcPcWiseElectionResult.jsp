<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<title>AC AND PC WISE RESULT</title>

<style>
.tableClass1  table {border: 3px solid #B6D9E9}
.tableClass1  thead th,.tableClass1  thead tr,.tableClass1  tbody tr,.tableClass1  tbody td {
	border: 2px solid #B6D9E9;
	padding:5px;
	font-weight:bold;
	font-size:13px;
}

.parlResultTable{background:#ffffff;}
	.parlResultTable tbody tr:nth-child(even){
		background:#F7FAFD;
	}

	.exitPolls tbody tr:nth-child(even){
		background:#F7FAFD;
	}
	.exitPolls tr{font-family: Tahoma;font-size: 12px;color:black;}
	.prevResults a{margin:10px;}

.thBorder{
	border: 2px solid #B6D9E9
}
.headingClass{
	background-color:#8497AD;
	color:#fff;
	font-size:15px
}





//sravanthi code
.chart-gauge1 {
width: 360px;
margin: auto;
height: 200px;
text-align:center;
}

.chart-gauge {
width: 360px;
margin: auto;
height: 200px;
text-align:center;
}

.chart-color1 {
fill: #FF3333;
}

.chart-color2 {
fill: #FFCCCC;
}

.chart-color3 {
fill: #CCFFCC;
}
.chart-color4 {
fill: #33FF33;
}
.chart-color5 {
fill: #59A2BE;
}
.needle ,.needle-center{
fill: #9f8868;
}


.d3-slider-handle1 {position: absolute;width: 1.2em;height: 1.2em;border: 1px solid #FFCC00;border-radius: 4px;background: #FFCC99;background: linear-gradient(to bottom, #FFCC99 0%, #FFD2A6 100%);z-index: 3;}
.d3-slider-handle1:hover {border: 1px solid #F2C100;}
.d3-slider-horizontal .d3-slider-handle1 {top: -.3em;margin-left: -.6em;}
.d3-slider-vertical .d3-slider-handle1 {left: -.25em;margin-left: 0;margin-bottom: -.6em;}


.d3-slider {position: relative;font-family: Verdana,Arial,sans-serif;font-size: 1.1em;border: 1px solid #aaaaaa;z-index: 2;}
.d3-slider-horizontal {height: .8em;}
.d3-slider-vertical {width: .8em;height: 100px;}
.d3-slider-handle {position: absolute;width: 1.2em;height: 1.2em;border: 1px solid #FFCC00;border-radius: 4px;background: #FFCC99;background: linear-gradient(to bottom, #FFCC99 0%, #FFD2A6 100%);z-index: 3;}
.d3-slider-handle:hover {border: 1px solid #F2C100;}
.d3-slider-horizontal .d3-slider-handle {top: -.3em;margin-left: -.6em;}
.d3-slider-axis {position: relative;z-index: 1;}
.d3-slider-axis-bottom {top: .8em;}
.d3-slider-axis-right {left: .8em;}
.d3-slider-axis path {stroke-width: 0;fill: none;}
.d3-slider-axis line {fill: none;stroke: #aaa;shape-rendering: crispEdges;}
.d3-slider-axis text {font-size: 11px;}
.d3-slider-vertical .d3-slider-handle {left: -.25em;margin-left: 0;margin-bottom: -.6em;}
.div_sld{width:300px;margin:10px 0 0 50px;}
.div_sld1{width:300px;margin:10px 0 0 50px;}
.div_upeffect{width:400px;}
.map_wrapper{width:100%;padding:5px;max-width:700px;}
@media only screen and (max-width:767px){
.map_wrapper{width:99%;padding:0px;}
#mapswidget{width:98%;}
.div_upeffect{width:100%;}

.div_sld{width:80%;margin:10px auto;}
.div_sld1{width:80%;margin:10px auto;}
}
@media only screen and (max-width:340px){
.div_upeffect center{font-size:12px;}
}
.ht10{display:block;font-size:1px;height:10px;line-height:10px}
#slider3{width:333px;margin-bottom: 40px;}
.div_sld{width:333px;margin:auto;}
.div_sld1{width:333px;margin:auto;}

select {
background-color: #FFFFFF;
border: 1px solid #CCCCCC;
width: 150px;
}

select,textarea,input[type="text"],input[type="password"],input[type="datetime"],input[type="datetime-local"],input[type="date"],input[type="month"],input[type="time"],input[type="week"],input[type="number"],input[type="email"],input[type="url"],input[type="search"],input[type="tel"],input[type="color"],.uneditable-input,#fromDate,#toDate
{
border-radius: 4px 4px 4px 4px;
color: #000000;
display: inline-block;
font-size: 13px;
line-height: 18px;
padding: 4px;
}
.hero-unit{padding:22px;color:black;font-size:15px;margin-bottom: 5px;margin-top: 10px;}
   .hero-unit {
    color: black;
    font-size: 15px;
    margin-bottom: 5px;
    margin-top: 10px;
    padding: 22px;
	}
	label {
    display: inline-block;
	}
	label {
		margin-bottom: 5px;
	}
	label, input, button, select, textarea {
		font-size: 13px;
		font-weight: normal;
		line-height: 18px;
	}
	

	.leaflet-popup-content-wrapper {
    border-bottom-left-radius: 0px !important;
    border-bottom-right-radius: 0px !important;
    border-top-left-radius: 0px !important;
    border-top-right-radius: 0px !important;
    padding-bottom: 1px;
    padding-left: 1px;
    padding-right: 1px;
    padding-top: 1px;
    text-align: left;
	
	.leaflet-popup-close-button{ color:red !important;
	font-size: 30px !important;
	padding-top: 8px !important;
	padding-right: 8px !important;
	
	}
	.leaflet-popup-close-button:hover{color:#00f !important;}
	
	
</style>
<script src="js/apac.js"></script> 
<script src="js/tgac.js"></script>
<script src="js/tgpc.js"></script>
<script src="js/appc.js"></script>
<script src="js/pc.js"></script>
<!--<script src="js/leaflet-lable.js"></script>-->
<script src="js/leaflet.js"></script>
<script src="js/leaflet-google.js"></script>
<!--<script src="http://cdn.leafletjs.com/leaflet-0.6.4/leaflet.js"></script>-->
<!--<script src="http://maps.google.com/maps/api/js?v=3.2&sensor=false"></script>-->
<script src="js/GOOGLE.js"></script>
<script src="js/Permalink.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles/politico.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet.css">
<link rel="stylesheet" type="text/css" href="styles/leaflet-lable.css">
</head>
<body>
<script>
$('document').ready(function(){
	 $('#scopeId').trigger('change');
	 //$('#locaionsId1').multiselect({ noneSelectedText:"Select"});
	 $('.reportType').change(function(){
		 $('#test,#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#marginAnalysis1').html('');
	 });

	 $('#scopeId').change(function(){
		 console.log(this);
		 $('#rgntxt').text("Select "+$('#scopeId  :selected').text());
	 });
});
</script>
<script>

var showConst=false;
	
	$('#stateButton').live('click',function(){
		
	if(showConst) {
			
			$(this).html('Show State Wise Report<i class="icon-chevron-down"></i>'); 
			$('#telanganaMainDiv').hide();
			$('#andhraMainDiv').hide();
			showConst=false;
		}
		else {
			
			$(this).html('Hide State Wise Report<i class="icon-chevron-up"></i>');
			$('#telanganaMainDiv').css("display","block");
			$('#andhraMainDiv').css("display","block");
			$("#stateAjaxImg").css("display","inline-block");
			getAndhraPartyResult('state');
			getAndhraPartyResultForMuncipal('state');
			showConst=true;
		}
	});




 function getTelanganaPartyResult(type)
	{
		$("#telanganaDiv").html('');
		
		var constituencyDetails={electionId:38,type:type,region:"telangana",taskToDo:"telangana"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getTelanganaPartyResultAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  //$("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'telanganaDiv');
			}
	   });
	   
	
	}


	function getAndhraPartyResult(type)
	{
		$("#telanganaDiv").html('');
		$("#andhraDiv").html('');
		$("#telanganaMuncipaDiv").html('');
		$("#andhraMuncipalDiv").html('');
		
		$("#regionHead").html('');
		$("#regionHead1").html('');
		$("#telanganaMainDiv").css("display","none");
		var constituencyDetails={electionId:38,type:type,region:"andhra",taskToDo:"andhra"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getAndhraPartyResultAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			 
			if(result != null){
				
				buildResultForPartyResult(result,constituencyDetails,'andhraDiv');
			}
	   });
	   
	
	}


function getTelanganaPartyResultForMuncipal(type)
	{
		$("#telanganaMuncipaDiv").html('');
		var constituencyDetails={electionId:40,type:type,region:"telangana",taskToDo:"telanganamuncipal",alliance:false};
	$.ajax({
          type:'POST',
          url: 'getTelanganaPartyResultForMuncipalAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  //$("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'telanganaMuncipaDiv');
			}
	   });
	   
	
	}

function getAndhraPartyResultForMuncipal(type)
	{
		$("#telanganaDiv").html('');
		$("#andhraDiv").html('');
		$("#telanganaMuncipaDiv").html('');
		$("#andhraMuncipalDiv").html('');
		$("#regionHead").html('');
		$("#regionHead1").html('');
		$("#telanganaMainDiv").css("display","none");
		var constituencyDetails={electionId:40,type:type,region:"andhra",taskToDo:"andhramuncipal"
		,alliance:false};
	$.ajax({
          type:'POST',
          url: 'getAndhraPartyResultForMuncipal.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			$("#stateAjaxImg").css("display","none");

			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				buildResultForPartyResult(result,constituencyDetails,'andhraMuncipalDiv');
				$("#stateAjaxImg").css("display","none");
				$("#districtAjaxImg").css("display","none");
			}
	   });
	   
	
	}

function buildResultForPartyResult(result,jObj,DivEle)
	{
	var str='';
	if(jObj.type == "district")
		{
	$("#telanganaMainDiv").css("display","block");
		}
	$("#andhraMainDiv").css("display","block");
	if(jObj.type == "district" && jObj.region == "andhra" && jObj.taskToDo != "andhramuncipal")
	$("#regionHead").html("ANDHRA REGION");
	else if(jObj.type == "district" && jObj.region == "telangana" && jObj.taskToDo != "telanganamuncipal")
		$("#regionHead1").html("TELANGANA REGION");
	
	if(jObj.taskToDo == "andhramuncipal" || jObj.taskToDo == "telanganamuncipal")
	str+='<h5>'+result[0].year+' - Muncipal Election Results</h5>';
	else
	str+='<h5>'+result[0].year+' - Assembly Election Results</h5>';
	str+='<div class="tableClass1">';
	str+='<table class="partyResultTable" style="width:40% !important;">';
	str+='<thead>';
	str+='<tr>';
	if(jObj.type == 'district')
	str+='<th>District</th>';
	else
	str+='<th>State</th>';
	for(var i in result[0].subList)
		{
	str+='<th>'+result[0].subList[i].partyName+' </th>';
		
		}
	str+='</tr>';
	str+='</thead>';
	for(var i in result)
		{
	
	str+='<tbody>';
	str+='<tr>';
	str+='<td>'+result[i].locationName+'</td>';
	for(var j in result[i].subList)
			{
	str+='<td>'+result[i].subList[j].percent+'</td>';
	
			}
	str+='</tr>';
	str+='</tbody>';
	
		}
	str+='</table>';
	str+='</div>';
	$("#"+DivEle).html(str);
	
	}





	
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
	
	$('#subReportId').attr('disabled',false); 
    if($('#scopeId').val() == 5)
	{
		$('#subReportId').attr('disabled',true);
	}
    
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

	//$("#").multiselect('refresh'); 


}

function showSelectedReport()
{

	$('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#errorDiv,#test,#marginAnalysis1').html('');
 

	if($('#locaionsId1').val() == null)
	{
        $('#errorDiv').html('Please select location(s)');
		return;
	}
      
	   $('#ajaxImage').show();

    if($('#scopeId').val() == 5)
	{
		getConstituencyWiseResults();

	}
	else
	{	  

		if($('input:radio[name=report]:checked').val() == "Matrix Report")
		{
			   getMatrixReport();
			   getMarginsCountOfParties();
		}
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
	$('#summaryDiv').addClass('offset1');

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
	   str+='<th class="thBorder">'+status+'</th>';
	    $.each(result[0].partiesDetails,function(index,value){
           str+='<th class="thBorder">'+value.name+'</th>';
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
	   str+='<label  style="margin-top: 10px; margin-bottom: 10px;padding:2px;" class="headingClass">Won Summary</label>';
   else
	   str+='<label  style="margin-top: 10px; margin-bottom: 10px;padding:2px;" class="headingClass">Lead Summary</label>';
	str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;">';
	str+='<thead>';
	str+='<tr>';

	$.each(result[0].summaryDetails,function(index,value){
		str+='<th style="border:1px solid #B6D9E9;" class="">'+value.name+'</th>';
		if(status == "Won")
		 str+='<th style="border:1px solid #B6D9E9;" class="">'+value.winTotalCount+'</th>';
		else
		 str+='<th style="border:1px solid #B6D9E9;" class="">'+value.leadTotalCount+'</th>';

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
	$('#summaryDiv').removeClass('offset1');

	var str ='';
	str+='<div class="tableClass1" style="margin-top:30px;">';

	str+='<label><b>NOTE:</b>W  - Won , L - Lead</label><br>';
	  if(type == "reservationType")
       str+='<label style="margin-top: 10px; margin-bottom: 10px;" class="headingClass">SC,ST,General Constituencies Analysis</label>';
	  else
	  str+='<label style="margin-top: 10px; margin-bottom: 10px;" class="headingClass">Rural,Urban,Rural-Urban Analysis</label>';

	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	 str+='<thead>';
	  str+='<tr>';
		  str+='<th rowspan="3"></th>';
		  $.each(result[0].reservationDetails,function(index,value){
			  var spanCnt = value.partiesDetails.length * 2;
			  if(value.name == "")
			   str+='<th colspan="'+spanCnt+'" class="thBorder">GENERAL</th>'
		      else
			   str+='<th colspan="'+spanCnt+'" class="thBorder">'+value.name+'</th>'
		  });
	  str+='</tr>';
	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				   str+='<th colspan="2" style="border:1px solid #B6D9E9;">'+value1.name+'</th>'
			   });
		  });
	  str+='</tr>';

	  str+='<tr>';
		  $.each(result[0].reservationDetails,function(index,value){
			   $.each(value.partiesDetails,function(index1,value1){
				   str+='<th style="border:1px solid #B6D9E9;">W</th>'
   				   str+='<th style="border:1px solid #B6D9E9;">L</th>'
			   });
		  });
	  str+='</tr>';

	  str+='</thead>';
	 str+='<tbody>';

     $.each(result,function(index,value){
		  str+='<tr>';
		  
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
	$('#ajaxImage').hide();
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
		str += '<th>P #</th>';
		str += '<th>P Name</th>';
		str += '<th>A #</th>';
		str += '<th>A Name</th>';
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
<div align="center" style="margin-top:20px;">
<img src="images/MEnuBG.jpg" width="960" height="32" border="0" usemap="#Map" />
</div>


<div class="container" style="font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px; margin-top: 24px;" id="mapDiv">
<h4 style="padding: 10px; margin-top: 10px; border-radius: 5px; text-align: center; background: none repeat scroll 0% 0% rgb(73, 175, 205);">LIVE ELECTION RESULTS COMPARISON</h4>
<div id="btnDivs">
	<!--<span id="optionsBtn1" class="btn btn-success" style="float:right;margin:10px 100px 10px 0px;"> Parliament Wise Results </span>
		<span id="optionsBtn2" class="btn btn-success" style="float:right;margin:10px 100px 10px 0px;display;none;"> Assembly Wise Results </span>-->
	<br><br>
</div>
<map name="Map" id="menuMap">
	<area shape="rect" coords="442,-5,503,31" href="javascript:{getRegionWiseResults('Telangana');}" title="Telangana"/>
	<area shape="rect" coords="504,-4,576,30" href="javascript:{getRegionWiseResults('Semandhra');}" title="Semandhra" />
	<area shape="rect" coords="577,-8,611,30" href="javascript:{getRegionWiseResults('India');}" title="India"/>
	<area shape="rect" coords="611,1,693,29" href="javascript:{getRegionWiseResults('StateAnalysis');}" title="State Analysis"/>
	<area shape="rect" coords="693,0,791,33" href="javascript:{getRegionWiseResults('DistrictAnalysis');}" title="District Analysis "/>
	<area shape="rect" coords="791,1,858,30" href="javascript:{getRegionWiseResults('CBNEffect');}" title="CBN Effect"/>
	<area shape="rect" coords="858,-4,929,29" href="javascript:{getRegionWiseResults('ModiEffect');}" title="Modi Effect"/>
</map>

<div id="stateSelectDiv" align="center" style="margin-bottom: 20px;" >
	<div class="span4 offset4">
	<b>Select State</b>
	<select id="stateId">
	<option value="0">Select State</option>
	<option value="1">SeeamAndhra</option>
	<option value="2">Telangana</option>
	</select>
	</div>
</div>
</br></br>
<div class="row-fluid " id="areaSelectionDiv">
	<div class="span6">
		<div class="form-inline">
	
		
		<b>From Level</b>
		<select id="levelId1" class="input-medium">
		<option value="0">Select Level</option>
		<option value="1">Assembly</option>
		<option value="2">Parliment</option>
		</select>
		
		
		
		<b>From Year</b>
		<select id="yearId1" class="input-small">
		<option value="0">Select Year</option>
		<option value="1">2009</option>
		<!-- <option value="2">2004</option> -->
		</select>
		</div>
	</div>
		
	<div class="span6">
		<div class="form-inline">
		<b>Select Level</b>
		<select id="levelId2" class="input-medium">
		<option value="0">Select Level</option>
		<option value="1">Assembly</option>
		<option value="2">Parliment</option>
		</select>
		
		
		
		<b>Select Year</b>
		<select id="yearId2" class="input-small">
		<option value="0">Select Year</option>
		<option value="1">2009</option>
		<!-- <option value="2">2004</option> -->
		</select>
		
		</div>
		</div>
	</div>

<br/>
<div class="row-fluid " id="submitButtionDiv">
	<div class="span2 offset5">
	 <a class="btn btn-info btn-block " value="Submit" onClick="getElectionDetails();" >Submit</a>	
	</div>
</div>
</br></br>
<div class="row-fluid" id="legend" style="display:none;">
<div><b>TDP : <span style="color: #FFD700 ;font-weight: bold; font-size: 19px;">-----</b><b> YSRC : <span style="color: #00CED1; font-weight: bold; font-size: 19px;">-----</b><b>INC : <span style="color: #228B22; font-weight: bold; font-size: 19px;">-----</b><b>TRS : <span style="color: #FF00FF; font-weight: bold; font-size: 19px;">-----</b><b>AIMIM : <span style="color: #006400; font-weight: bold; font-size: 19px;">-----</b></div>
<div><b>BJP : <span style="#FF7F50; font-weight: bold; font-size: 19px;">-----</b><b>CPM/CPI : <span style="color: #B22222; font-weight: bold; font-size: 19px;">-----</b><b>LSP : <span style="color: #4B0082; font-weight: bold; font-size: 19px;">-----</b><b>OTHERS : <span style="color: #FF0000; font-weight: bold; font-size: 19px;"></div>

</div>
</br></br>
<div class="row-fluid">
	<div id="weathermap"></div>
	<div id="weathermap1"></div>

</div>

<div id="result"></div>
<div id="resultDiv"></div>


<!--

<div align="center" style="" class="hero-unit">
<div id="partiesDiv"></div>
</div>
-->
<div id="subTitlesDiv" style="margin-left:200px;margin-top:20px;"></div>

<div id="results1Div" ></div>

</div>


<div class="parliamentCls offset2">
	<div class="parliamentResultsDiv" style="background:#ffffff;float:left;width:975px;"></div>
	<div class="partyWiseResultDiv" style="background:#ffffff;float:left;width:975px;"></div>
	<div style="background:#ffffff;float:left;width:975px;"> 
	
		
		<h2 class='offset2' style="margin-bottom:5px;color:#27AFA6;">Exit Polls From Different Sources</h2>
		<table width='800' cellspacing='0' cellpadding='2' border='0' class='exitPolls offset1'>
			<tbody>
				<tr style='font-weight:bold;'>
					<td style='border-bottom:1px solid #B0BDDA;'>CHANNEL</td>
					<td style='border-bottom:1px solid #B0BDDA;'>NDA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>UPA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>OTHERS</td>
				</tr>
			
				<tr>
					<td>Today's Chanakya</td>
					<td>340</td>
					<td>70</td>
					<td>133</td>
				</tr>
				<tr>
					<td>Times Now</td>
					<td>249</td>
					<td>148</td>
					<td>146</td>
				</tr>
				<tr>
					<td>CNN-IBN</td>
					<td>270 - 282</td>
					<td>92 - 102</td>
					<td>150 - 160</td>
				</tr>
				<tr>
					<td>HeadLines Today</td>
					<td>272(+/- 11)</td>
					<td>115(+/- 5)</td>
					<td>156</td>
				</tr>
				<tr>
					<td>ABP News</td>
					<td>281</td>
					<td>97</td>
					<td>161</td>
				</tr>
				<tr>
					<td>C-Voter</td>
					<td>289</td>
					<td>101</td>
					<td>153</td>
				</tr>
				<tr>
					<td>Aaj tdak</td>
					<td>272</td>
					<td>115</td>
					<td> - </td>
				</tr>
				<tr>
					<td>India TV</td>
					<td>289</td>
					<td>101</td>
					<td> - </td>
				</tr>
			</tbody>
		</table>
		</div>
</div>


<!-- SAMBA START  -->
<!--<div class="span12 container hide"  style="border:1px solid #BDA870;margin-left:180px;padding:8px;margin-top:20px;" id="liveResultsDiv">-->
<div class="container hide" style="font-family: verdana; font-size: 14px; border: 1px solid rgb(204, 204, 204); padding: 0px 10px 10px; margin-top: 24px;" id="liveResultsDiv">

<!--<h4 style="text-align:center;">Live Results Analysis</h4>-->
<div style="text-align:center;margin-top:10px;"><img src="images/Live AP State Election Results.jpg"></div>


<!--
<a id="stateButton" class="btn " style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;float:right;" href="javascript:{}" >Show State Wise Report<i class="icon-chevron-up"></i></a>-->
<img id="stateAjaxImg" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/>



<div class="span12 " id="telanganaMainDiv" >
<h5 id="regionHead1"></h5>
<div id="telanganaDiv" class="span5"></div>
<div id="telanganaMuncipaDiv" class="span5"></div>

</div>
<div class="span12 " id="andhraMainDiv" style="margin-bottom:20px;">
<h5 id="regionHead"></h5>
<div id="andhraDiv" class="span5"></div>
<div id="andhraMuncipalDiv" class="span5"></div>

</div>
<br/><br/>
<div>
<table class="offset1 headingTbl">

<tr><td class="span3">Select Election<select id="electionId" class="input-block-level">
	  <option value="258">2014 Assembly </option>
	  <option value="38">2009 Assembly </option>
	  <option value="3">2004 Assembly </option>
	 </select></td>
	 <td class="span3"> Select Level<select onchange="getLocationDetailsForSelectedScope(this.value)" class="input-block-level" id="scopeId" name="scopeId" style="width:99%;">
								<option value="3">Region</option>
								<option value="2">District</option>
								<option value="4">Parliament</option>
								<option value="5">Assembly</option>
						   </select>	</td>
			

<td class="span3"> Select Region<select class="input-block-level" id="locaionsId1" multiple="true" style="width:96%;height:55px;"></select></td>			
			
	<!-- <td></td>-->
	
</tr>
<!--<tr style="border:1px solid #ffffff;">
<td> Select Level<select onchange="getLocationDetailsForSelectedScope(this.value)" class="input-block-level" id="scopeId" name="scopeId" style="width:50%;">
								<option value="3">Region</option>
								<option value="2">District</option>
								<option value="4">Parliament</option>
								<option value="5">Assembly</option>
						   </select>	</td>
			

<td> Select Region<select class="input-block-level" id="locaionsId1" multiple="true" style="width:115%;height:55px;"></select></td>			
						   
						   
</tr>-->


</table>
</div>
 <div  class="offset1" style="clear:both;">


<label class="radio inline">

<input type="radio" class="reportType matrixRprt" id="matrixReportId" value="Matrix Report" name="report" checked="true" style="margin-top:-5px;"><span>Matrix Report</span>
</label>

<label class="radio inline">
<input type="radio" class="reportType matrixRprt" id="subReportId" value="Sub Report" name="report"  style="margin-top:-5px;"><span>Sub Report</span>
</label>

</div>
  <!--<div class="row-fluid offset2">
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
  </div>-->



					<!--<div class="row-fluid" style="margin-top:20px;">
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
					</div>-->

					<!--<div class="row-fluid offset1">

						
						 <input type="radio" class="reportType matrixRprt" id="matrixReportId" value="Matrix Report" name="report" checked="true"><span>Matrix Report</span>
					

						<label class=" radio inline"> 
						<input type="radio" class="reportType matrixRprt" id="subReportId" value="Sub Report" name="report"><span>Sub Report</span>
					   </label>

					</div>-->


					<div class="offset1" style="margin-top:20px;">

					
						<a onClick="showSelectedReport()" value="Display" class="btn" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC; color: #FFFFFF;font-weight: normal;">Display</a>
					

					
						<a onClick="clearFields()" value="Clear" class="btn" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;">Clear</a>
					

						
						<a onClick="exportToExcel()" class="btn" value="Export To Excel" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;">Export To Excel</a>

	<!--<a onClick="testIt()" class="btn" value="Export To Excel" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC;
    color: #FFFFFF;font-weight: normal;">testIt</a>-->
	
						
	<img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="margin-left:70px;display:none;"/>

					</div>

					<div id="errorDiv" style="font-weight:bold;color:red;"></div>


    <div class="offset1" id="summaryDiv">
	 <div id="matrixWonSummaryId"></div>
	 <div id="matrixLeadSummaryId"></div>
	</div>
		
	<div id="reportDiv" style="margin-top:30px;">

	<div id="constituencyResultsDiv"></div>

	<div id="test"  class="pull-left offset1"></div>
	<div id="matridLeadId"  class="pull-right" style="margin-right:100px;margin-bottom:15px;"></div>

	<div id="marginAnalysis1" class="offset1" style="clear:both;"></div>

	</div>

</div>

<!--<div id="modiDiv display:none;">
	<div class="chart-gauge" id="unemp_chart" style="display: none; width: 360px; margin: auto;"></div>
	<div class="chart-gauge1" id="unemchart1p_" style="display: block; width: 360px; margin: auto;"><svg width="360" height="195"><g transform="translate(180, 170)"><path class="arc chart-color1" d="M-169.78754426714426,-8.496458776015265A170,170 0 0,1 -142.35510191056113,-92.92483500138029L-108.85978381395851,-71.06016794223198A130,130 0 0,0 -129.83753385134563,-6.497292005188144Z" id="chart-color1"/><text style="font-size: 12px; font-family: sans-serif; font-weight: bold;" fill="black" dx="15" dy="22"><textPath href="#chart-color1" style="color: red;">115</textPath></text><path class="arc chart-color2" d="M-132.36691558005566,-106.67239408498534A170,170 0 0,1 -60.547849096098766,-158.8520002072245L-46.301296367604934,-121.4750589819952A130,130 0 0,0 -101.22175897298374,-81.57300724145938Z" id="chart-color2"/><text style="font-size: 12px; font-family: sans-serif; font-weight: bold;" fill="black" dx="15" dy="22"><textPath href="#chart-color2" style="color: red;">135</textPath></text><path class="arc chart-color3" d="M-44.3866241273738,-164.10310051481426A170,170 0 0,1 44.38662412737374,-164.1031005148143L33.942712567991684,-125.49060627603446A130,130 0 0,0 -33.94271256799173,-125.49060627603444Z" id="chart-color3"/><text style="font-size: 12px; font-family: sans-serif; font-weight: bold;" fill="black" dx="15" dy="22"><textPath href="#chart-color3" style="color: red;">155</textPath></text><path class="arc chart-color4" d="M60.54784909609871,-158.8520002072245A170,170 0 0,1 132.36691558005563,-106.6723940849854L101.22175897298371,-81.57300724145942A130,130 0 0,0 46.30129636760489,-121.47505898199522Z" id="chart-color4"/><text style="font-size: 12px; font-family: sans-serif; font-weight: bold;" fill="black" dx="15" dy="22"><textPath href="#chart-color4" style="color: red;">175</textPath></text><path class="arc chart-color5" d="M142.3551019105612,-92.92483500138022A170,170 0 0,1 170,1.0935234017801129e-13L130,8.36223777831851e-14A130,130 0 0,0 108.85978381395857,-71.06016794223193Z" id="chart-color5"/><text style="font-size: 12px; font-family: sans-serif; font-weight: bold;" fill="black" dx="15" dy="22"><textPath href="#chart-color5" style="color: red;">195</textPath></text><circle class="needle-center" cx="0" cy="0" r="15"/><path class="needle" d="M -8.816778784387097 12.135254915624213 L -105.17220926874317 -76.4120827980215 L 8.816778784387095 -12.135254915624213"/><text style="font-size: 12px; font-weight: bold; color: red;" x="-80" y="-50">Seats for CBN Effect</text></g></svg></div>

	<div class="div_sld" style="display: none;"><div id="slider3" class="d3-slider d3-slider-horizontal"><a class="d3-slider-handle" xlink:href="#"></a><svg class="d3-slider-axis d3-slider-axis-bottom" style="left: -12px;" width="558" height="32"><g transform="translate(12,0)"><g class="tick" style="opacity: 1;" transform="translate(0,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">0%</text></g><g class="tick" style="opacity: 1;" transform="translate(66.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">1%</text></g><g class="tick" style="opacity: 1;" transform="translate(133.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">2%</text></g><g class="tick" style="opacity: 1;" transform="translate(200.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">3%</text></g><g class="tick" style="opacity: 1;" transform="translate(267,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">4%</text></g><g class="tick" style="opacity: 1;" transform="translate(333.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">5%</text></g><path class="domain" d="M0,6V0H534V6"/></g></svg></div>
	</div>

	<div class="div_sld1" style="display: block;"><div id="slider3" class="d3-slider d3-slider-horizontal"><a class="d3-slider-handle1" xlink:href="#" style="left: 50%;"></a><svg class="d3-slider-axis d3-slider-axis-bottom" style="left: -12px;" width="558" height="32"><g transform="translate(12,0)"><g class="tick" style="opacity: 1;" transform="translate(0,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">0%</text></g><g class="tick" style="opacity: 1;" transform="translate(66.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">1%</text></g><g class="tick" style="opacity: 1;" transform="translate(133.5,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">2%</text></g><g class="tick" style="opacity: 1;" transform="translate(200.25,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">3%</text></g><g class="tick" style="opacity: 1;" transform="translate(267,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">4%</text></g><g class="tick" style="opacity: 1;" transform="translate(333.75,0)"><line y2="6" x2="0"/><text y="9" x="0" dy=".71em" style="text-anchor: middle;">5%</text></g><path class="domain" d="M0,6V0H534V6"/></g></svg></div>
	</div>

	</div>-->

 <div id="weathermap3"></div>
 <div id="weathermap4"></div>
</div>
<!-- SAMBA END -->














<style>


</style>
<script>
/*
	Leaflet.label, a plugin that adds labels to markers and vectors for Leaflet powered maps.
	(c) 2012-2013, Jacob Toye, Smartrak

	https://github.com/Leaflet/Leaflet.label
	http://leafletjs.com
	https://github.com/jacobtoye
*/
(function(){L.labelVersion="0.2.2-dev",L.Label=L.Class.extend({includes:L.Mixin.Events,options:{className:"",clickable:!1,direction:"right",noHide:!1,offset:[12,-15],opacity:1,zoomAnimation:!0},initialize:function(t,e){L.setOptions(this,t),this._source=e,this._animated=L.Browser.any3d&&this.options.zoomAnimation,this._isOpen=!1},onAdd:function(t){this._map=t,this._pane=this._source instanceof L.Marker?t._panes.markerPane:t._panes.popupPane,this._container||this._initLayout(),this._pane.appendChild(this._container),this._initInteraction(),this._update(),this.setOpacity(this.options.opacity),t.on("moveend",this._onMoveEnd,this).on("viewreset",this._onViewReset,this),this._animated&&t.on("zoomanim",this._zoomAnimation,this),L.Browser.touch&&!this.options.noHide&&L.DomEvent.on(this._container,"click",this.close,this)},onRemove:function(t){this._pane.removeChild(this._container),t.off({zoomanim:this._zoomAnimation,moveend:this._onMoveEnd,viewreset:this._onViewReset},this),this._removeInteraction(),this._map=null},setLatLng:function(t){return this._latlng=L.latLng(t),this._map&&this._updatePosition(),this},setContent:function(t){return this._previousContent=this._content,this._content=t,this._updateContent(),this},close:function(){var t=this._map;t&&(L.Browser.touch&&!this.options.noHide&&L.DomEvent.off(this._container,"click",this.close),t.removeLayer(this))},updateZIndex:function(t){this._zIndex=t,this._container&&this._zIndex&&(this._container.style.zIndex=t)},setOpacity:function(t){this.options.opacity=t,this._container&&L.DomUtil.setOpacity(this._container,t)},_initLayout:function(){this._container=L.DomUtil.create("div","leaflet-label "+this.options.className+" leaflet-zoom-animated"),this.updateZIndex(this._zIndex)},_update:function(){this._map&&(this._container.style.visibility="hidden",this._updateContent(),this._updatePosition(),this._container.style.visibility="")},_updateContent:function(){this._content&&this._map&&this._prevContent!==this._content&&"string"==typeof this._content&&(this._container.innerHTML=this._content,this._prevContent=this._content,this._labelWidth=this._container.offsetWidth)},_updatePosition:function(){var t=this._map.latLngToLayerPoint(this._latlng);this._setPosition(t)},_setPosition:function(t){var e=this._map,i=this._container,n=e.latLngToContainerPoint(e.getCenter()),o=e.layerPointToContainerPoint(t),s=this.options.direction,a=this._labelWidth,l=L.point(this.options.offset);"right"===s||"auto"===s&&o.x<n.x?(L.DomUtil.addClass(i,"leaflet-label-right"),L.DomUtil.removeClass(i,"leaflet-label-left"),t=t.add(l)):(L.DomUtil.addClass(i,"leaflet-label-left"),L.DomUtil.removeClass(i,"leaflet-label-right"),t=t.add(L.point(-l.x-a,l.y))),L.DomUtil.setPosition(i,t)},_zoomAnimation:function(t){var e=this._map._latLngToNewLayerPoint(this._latlng,t.zoom,t.center).round();this._setPosition(e)},_onMoveEnd:function(){this._animated&&"auto"!==this.options.direction||this._updatePosition()},_onViewReset:function(t){t&&t.hard&&this._update()},_initInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.addClass(t,"leaflet-clickable"),L.DomEvent.on(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.on(t,e[i],this._fireMouseEvent,this)}},_removeInteraction:function(){if(this.options.clickable){var t=this._container,e=["dblclick","mousedown","mouseover","mouseout","contextmenu"];L.DomUtil.removeClass(t,"leaflet-clickable"),L.DomEvent.off(t,"click",this._onMouseClick,this);for(var i=0;e.length>i;i++)L.DomEvent.off(t,e[i],this._fireMouseEvent,this)}},_onMouseClick:function(t){this.hasEventListeners(t.type)&&L.DomEvent.stopPropagation(t),this.fire(t.type,{originalEvent:t})},_fireMouseEvent:function(t){this.fire(t.type,{originalEvent:t}),"contextmenu"===t.type&&this.hasEventListeners(t.type)&&L.DomEvent.preventDefault(t),"mousedown"!==t.type?L.DomEvent.stopPropagation(t):L.DomEvent.preventDefault(t)}}),L.BaseMarkerMethods={showLabel:function(){return this.label&&this._map&&(this.label.setLatLng(this._latlng),this._map.showLabel(this.label)),this},hideLabel:function(){return this.label&&this.label.close(),this},setLabelNoHide:function(t){this._labelNoHide!==t&&(this._labelNoHide=t,t?(this._removeLabelRevealHandlers(),this.showLabel()):(this._addLabelRevealHandlers(),this.hideLabel()))},bindLabel:function(t,e){var i=this.options.icon?this.options.icon.options.labelAnchor:this.options.labelAnchor,n=L.point(i)||L.point(0,0);return n=n.add(L.Label.prototype.options.offset),e&&e.offset&&(n=n.add(e.offset)),e=L.Util.extend({offset:n},e),this._labelNoHide=e.noHide,this.label||(this._labelNoHide||this._addLabelRevealHandlers(),this.on("remove",this.hideLabel,this).on("move",this._moveLabel,this).on("add",this._onMarkerAdd,this),this._hasLabelHandlers=!0),this.label=new L.Label(e,this).setContent(t),this},unbindLabel:function(){return this.label&&(this.hideLabel(),this.label=null,this._hasLabelHandlers&&(this._labelNoHide||this._removeLabelRevealHandlers(),this.off("remove",this.hideLabel,this).off("move",this._moveLabel,this).off("add",this._onMarkerAdd,this)),this._hasLabelHandlers=!1),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},getLabel:function(){return this.label},_onMarkerAdd:function(){this._labelNoHide&&this.showLabel()},_addLabelRevealHandlers:function(){this.on("mouseover",this.showLabel,this).on("mouseout",this.hideLabel,this),L.Browser.touch&&this.on("click",this.showLabel,this)},_removeLabelRevealHandlers:function(){this.off("mouseover",this.showLabel,this).off("mouseout",this.hideLabel,this),L.Browser.touch&&this.off("click",this.showLabel,this)},_moveLabel:function(t){this.label.setLatLng(t.latlng)}},L.Icon.Default.mergeOptions({labelAnchor:new L.Point(9,-20)}),L.Marker.mergeOptions({icon:new L.Icon.Default}),L.Marker.include(L.BaseMarkerMethods),L.Marker.include({_originalUpdateZIndex:L.Marker.prototype._updateZIndex,_updateZIndex:function(t){var e=this._zIndex+t;this._originalUpdateZIndex(t),this.label&&this.label.updateZIndex(e)},_originalSetOpacity:L.Marker.prototype.setOpacity,setOpacity:function(t,e){this.options.labelHasSemiTransparency=e,this._originalSetOpacity(t)},_originalUpdateOpacity:L.Marker.prototype._updateOpacity,_updateOpacity:function(){var t=0===this.options.opacity?0:1;this._originalUpdateOpacity(),this.label&&this.label.setOpacity(this.options.labelHasSemiTransparency?this.options.opacity:t)},_originalSetLatLng:L.Marker.prototype.setLatLng,setLatLng:function(t){return this.label&&!this._labelNoHide&&this.hideLabel(),this._originalSetLatLng(t)}}),L.CircleMarker.mergeOptions({labelAnchor:new L.Point(0,0)}),L.CircleMarker.include(L.BaseMarkerMethods),L.Path.include({bindLabel:function(t,e){return this.label&&this.label.options===e||(this.label=new L.Label(e,this)),this.label.setContent(t),this._showLabelAdded||(this.on("mouseover",this._showLabel,this).on("mousemove",this._moveLabel,this).on("mouseout remove",this._hideLabel,this),L.Browser.touch&&this.on("click",this._showLabel,this),this._showLabelAdded=!0),this},unbindLabel:function(){return this.label&&(this._hideLabel(),this.label=null,this._showLabelAdded=!1,this.off("mouseover",this._showLabel,this).off("mousemove",this._moveLabel,this).off("mouseout remove",this._hideLabel,this)),this},updateLabelContent:function(t){this.label&&this.label.setContent(t)},_showLabel:function(t){this.label.setLatLng(t.latlng),this._map.showLabel(this.label)},_moveLabel:function(t){this.label.setLatLng(t.latlng)},_hideLabel:function(){this.label.close()}}),L.Map.include({showLabel:function(t){return this.addLayer(t)}}),L.FeatureGroup.include({clearLayers:function(){return this.unbindLabel(),this.eachLayer(this.removeLayer,this),this},bindLabel:function(t,e){return this.invoke("bindLabel",t,e)},unbindLabel:function(){return this.invoke("unbindLabel")},updateLabelContent:function(t){this.invoke("updateLabelContent",t)}})})(this,document);
</script>
<script>
var stateType = '';
	$('document').ready(function(){
		stateType = 'Semandhra';
/*$('#optionsBtn2').hide();
		$('#optionsBtn1').click(function(){
				$('#map1').show();
				$('#map').hide();
				$('#optionsBtn1').hide();
				$('#optionsBtn2').show();
				
		});
		$('#optionsBtn2').click(function(){
				$('#map').show();
				$('#map1').hide();
				$('#optionsBtn2').hide();
				$('#optionsBtn1').show();
				
		});*/
	});
	//getConstituenctSelection();
	var map = "";
	var map1 = "";
	var electionAcData = '';
	var electionPcData = '';
	function getElectionDetails()
	{
		$('#legend').show();
		var stateVal = $('#stateId option:selected').val();
		var levelVal1 = $('#levelId1 option:selected').val();
		var levelVal2 = $('#levelId2 option:selected').val();
		var year1 = $('#yearId1 option:selected').val();
		var year2 = $('#yearId2 option:selected').val();

		if(levelVal1 == 1 && year1 == 1)
		{
			getElectionResultForAssemblyPresent(stateVal,"first",levelVal1,year1);
		}
		if (levelVal1 == 1 && year1 == 2)
		{
			getElectionResultForAssemblyPrevious(stateVal,"first",levelVal1,year1);
		}
		
		if(levelVal1 == 2 && year1 == 1)
		{
			getElectionResultForParlimentPrevious(stateVal,"first",levelVal1,year1);
		}
		if (levelVal1 == 2 && year1 == 2)
		{
			getElectionResultForParlimentPresent(stateVal,"first",levelVal1,year1);
		}
		
		if(levelVal2 == 1 && year2 == 1)
		{
			getElectionResultForAssemblyPresent(stateVal,"second",levelVal2,year2);
		}
		if(levelVal2 == 1 && year2 == 2)
		{
			getElectionResultForAssemblyPrevious(stateVal,"second",levelVal2,year2);
		}
		
		if(levelVal2 == 2 && year2 == 1)
		{
			getElectionResultForParlimentPrevious(stateVal,"second",levelVal2,year2);
		}
		if(levelVal2 == 2 && year2 == 2)
		{
			getElectionResultForParlimentPresent(stateVal,"second",levelVal2,year2);
		}
		
		
	}
	
	function getMapType(type)
	{
		if(type == 1)
		{
			document.getElementById('weathermap').innerHTML = "<div id='map' class='span6' style='height: 500px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
			map = L.map('map', {
			center: [16.0000,80.0000],
			zoom: 6
			});
		}
		else
		{
			document.getElementById('weathermap1').innerHTML = "<div id='map1' class='span6' style='height: 500px; float: right ! important; left: 0px; position: relative; border: 1px solid rgb(51, 51, 51); border-radius: 10px; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
			map1 = L.map('map1', {
			center: [16.0000,80.0000],
			zoom: 6
			});
		}
		
	}
	
	function getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo)
	{
		if(stateVal == 1)
		{
			if(locationLevel == 1)
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForApACPrevious(map);
					}
					else
					{
						generateMapForApACPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForApACPresent(map);
					}
					else
					{
						generateMapForApACPresent(map1);
					}
					
				}
				
			}
			else
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForApPCPrevious(map);
					}
					else
					{
						generateMapForApPCPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForApPCPresent(map);
					}
					else
					{
						generateMapForApPCPresent(map1);
					}
					
				}
			}
			
		}
		else
		{
			if(locationLevel == 1)
			{
				
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForTgACPrevious(map);
					}
					else
					{
						generateMapForTgACPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForTgACPresent(map);
					}
					else
					{
						generateMapForTgACPresent(map1);
					}
					
				}
				
			}
			else
			{
				if(year == 1)
				{
					if(mapNo == "first")
					{
						generateMapForTgPCPrevious(map);
					}
					else
					{
						generateMapForTgPCPrevious(map1);
					}
					
				}
				else
				{
					if(mapNo == "first")
					{
						generateMapForTgPCPresent(map);
					}
					else
					{
						generateMapForTgPCPresent(map1);
					}
					
				}
			}
			
		}
	}
	function getElectionResultForAssemblyPrevious(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 3,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionAcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			
		});	
	}
	
	function getElectionResultForAssemblyPresent(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 38,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionAcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			
		});	
	}
	function getElectionResultForParlimentPrevious(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 17,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionPcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
		});	
	}
	
	function getElectionResultForParlimentPresent(stateVal,mapNo,locationLevel,year)
	{
		if(mapNo == "first") 
		{
			getMapType(1);
		}
		else
		{
			getMapType(2);
		}
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 260,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionPcData = result;
			getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
		});	
	}
	
	var areatype ;
	function generateMapForApACPrevious(mapName)
	{
		areatype  = "ac";
		L.geoJson(apaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
		
			
	}
	
	function generateMapForApACPresent(mapName)
	{
		areatype  = "ac";
		L.geoJson(apaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	function generateMapForTgACPrevious(mapName)
	{
		areatype  = "ac";
		L.geoJson(tgaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	
	
	function generateMapForTgACPresent(mapName)
	{
		areatype  = "ac";
		L.geoJson(tgaccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	function generateMapForApPCPrevious(mapName)
	{
		areatype  = "pc";
		L.geoJson(appccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	
	function generateMapForApPCPresent(mapName)
	{
		areatype  = "pc";
		L.geoJson(appccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	function generateMapForTgPCPrevious(mapName)
	{
		areatype  = "pc";
		L.geoJson(tgpccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	
	
	function generateMapForTgPCPresent(mapName)
	{
		areatype  = "pc";
		L.geoJson(tgpccampus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(mapName); 
	}
	
	/* var tableToExcel = (function() {
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
 */
	/* function getConstituenctSelection()
	{
		if($("input:radio[name=type]:checked").val() == 'ac')
		{
			$('#state').attr("disabled", false);
			getStatesForAssembly();
		}
		else
		{
			$('#state').attr("disabled", true); 
			getAssemblyElectionYears(1,"Parliament");
		}
	}	 */
	
	function onEachFeature(feature, layer)
	{
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	
		//console.log(areatype);
		if(areatype  == "pc")
		{
			onEachFeature1(feature, layer);
		}
		else if(areatype  == "totPc")
		{
			onEachFeatureForPc(feature, layer);
		}
		else if(areatype  == "totac")
		{
			onEachFeatureForAc(feature, layer);
		}
		else
		{
			var popupContent='';

			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;margin-left:-40px;">';
			popupContent +=' <header class="timeline-header">';
			popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.ac_name+'</a> </h3>';
			popupContent +=' </header>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;margin-bottom: 10px;">';
			popupContent +=' </header>';
			//popupContent +=' <b style="font-size: 12px;">  Reporting : 0 % <span style="font-weight:bold;float:right;">  Leading Party: TDP </span></b>';
			popupContent +=' <table>';
			popupContent +=' <tr>';
			popupContent +='<td style="width:700px;"> Candidate Name </td>';
			popupContent +='<td style="width:300px;padding-left:15px;"> Party </td>';
			popupContent +='<td style="width:600px;"> Votes Percentage  </td>';
			popupContent +=' </tr">';
			popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-color: #004276;border-bottom-width: 2px;">';
			popupContent +=' </header>';
			popupContent +=' <tr class="type-democrat">';
			popupContent +=' </table>';
			popupContent +=' <span style="float: right; margin-right: -50px; margin-top: -20px;"> Total Votes </span>';
		
			popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-width: 2px;">';
			popupContent +=' </header>';
			var partyName ;
			for(var i in electionAcData)
			{
				if(feature.properties.ac == electionAcData[i].hamletId)
				{
					for(var j in electionAcData[i].selectedCasteDetails)
					{
						if(electionAcData[i].selectedCasteDetails[j].casteName != null)
						{
							popupContent +=' <div class="results-dataset">';
							popupContent +=' <div class="results-row layout-full">';
							popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
							popupContent +=' <div class="results-message">';
							popupContent +=' <table class="results-table" style="width:650px">';
							popupContent +=' <tbody>';
							popupContent +=' <tr class="type-democrat">';
							popupContent +=' <td class="results-title" style="width:120px;">';
							popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].casteName+'</span>';
							popupContent +=' </span>';
							popupContent +=' </td>';
							popupContent +=' <td class="results-title" style="width:40px;">';
							//popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].name+'</span>';
							if(electionAcData[i].selectedCasteDetails[j].name =='TDP'){
								popupContent +=' <span > <img src="images/party_flags/TDP.PNG" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='INC'){
								popupContent +=' <span > <img src="images/party_flags/INC.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='TRS'){
								popupContent +=' <span > <img src="images/party_flags/TRS.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='BJP'){
								popupContent +=' <span > <img src="images/party_flags/BJP.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='AIMIM'){
								popupContent +=' <span > <img src="images/party_flags/AIMIM.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='CPM'){
								popupContent +=' <span > <img src="images/party_flags/CPM.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='CPI'){
								popupContent +=' <span > <img src="images/party_flags/CPI.png" width=125% /></span>';
							}
							if(electionAcData[i].selectedCasteDetails[j].name =='LSP'){
								popupContent +=' <span > <img src="images/party_flags/LSP.png" width=125% /></span>';
							}
							
							popupContent +=' </span>';
							popupContent +=' </td>';
							//popupContent +=' <td class="results-title" style="width: 30px;">';
							//popupContent +=' </td>';
							popupContent +=' <td class="results-percentage" style="width:100px;padding-left: 25px;">';
							if(electionAcData[i].selectedCasteDetails[j].persent != null){
							popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].persent+'%</span>';
							}
							else{
							popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
							}
							popupContent +=' <span class="graph">';
							popupContent +=' <span class="bar">';
							popupContent +=' <span style="width:'+electionAcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
							popupContent +=' </span>';
							popupContent +=' </span>';
							popupContent +=' </span>';
							popupContent +=' </td>';
							popupContent +=' <td style="width: 100px;padding-left:35px;">';
							popupContent +=' <span style="font-weight:#000000">'+electionAcData[i].selectedCasteDetails[j].count+' </span>';
							popupContent +=' </td>';
							popupContent +=' </tr>';
							popupContent +=' </tbody>';
							popupContent +=' </table>';
							popupContent +=' </div>';
							popupContent +=' </div>';
							popupContent +=' </div>';
							popupContent +=' </div>';

							partyName = electionAcData[i].selectedCasteDetails[0].name;
							fillColour(partyName,layer,popupContent);
						}
						
			
					}
					
				}
			} 
			

			popupContent +=' </article>';
			popupContent +=' </li> ';
			popupContent +=' </ol>';
			popupContent +=' </article>';


			$('.leaflet-popup-close-button').html('');
			if (feature.properties && feature.properties.popupContent)
			{
				popupContent += feature.properties.popupContent;
			}
			 
			
			layer.bindLabel(feature.properties.ac_name, {noHide:true});
		}
		
	}
	function onEachFeatureForAc(feature, layer)
	{
		layer.setStyle({
		color: '#000000', 
		weight: 1,
		opacity: 0.6,
		fillOpacity: 0.65,
		fillColor: '#FFFAF0'
		});
		for(var i in psDetails)
		{
			if(psDetails[i].name.toUpperCase() == feature.properties.ac_name.toUpperCase())
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FFD700'
				});
			}
			/* else
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FFFAF0'
				});
			} */
		}
	}
	function onEachFeatureForPc(feature, layer)
	{
		layer.setStyle({
		color: '#000000', 
		weight: 1,
		opacity: 0.6,
		fillOpacity: 0.65,
		fillColor: '#FFFAF0'
		});
		for(var i in psDetails)
		{
			if(psDetails[i].name.toUpperCase() == feature.properties.pc_name.toUpperCase())
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FF4500'
				});
			}
			/* else
			{
				layer.setStyle({
				color: '#000000', 
				weight: 1,
				opacity: 0.6,
				fillOpacity: 0.65,
				fillColor: '#FFFAF0'
				});
			} */
		}
	}
	
	
	function onEachFeature1(feature, layer)
	{
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	
		var popupContent='';

		popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif; margin-left: -40px;">';
		popupContent +=' <header class="timeline-header">';
		popupContent +=' <h3><b aria-hidden="true" class="stateface "></b> '+feature.properties.FIRST_pc_n+'</a></h3>';
		popupContent +=' </header>';
		popupContent +=' <ol class="timeline-list"> ';
		popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
		popupContent +=' <article class="results-group">';
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;margin-bottom: 10px;">';
		popupContent +=' </header>';
	//	popupContent +=' <b style="font-size: 12px;">  Reporting : 0 % <span style="font-weight:bold;float:right;">  Leading Party: TDP </span></b>';
			popupContent +=' <table>';
		popupContent +=' <tr>';
		popupContent +='<td style="width:700px;"> Candidate Name </td>';
		popupContent +='<td style="width:300px;padding-left:15px;"> Party </td>';
		popupContent +='<td style="width:600px;"> Votes Percentage  </td>';
		popupContent +=' </tr">';
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-color: #004276;border-bottom-width: 2px;">';
		popupContent +=' </header>';
		popupContent +=' <tr class="type-democrat">';
		popupContent +=' </table>';
		popupContent +=' <span style="float: right; margin-right: -50px; margin-top: -20px;"> Total Votes </span>';
						
		popupContent +=' <header class="results-header" style="width: 350px; margin-top: -10px;border-bottom-width: 2px;">';
		popupContent +=' </header>';
		//console.log(electionPcData);

		for(var i in electionPcData)
		{	
			if(feature.properties.pc == electionPcData[i].hamletId)
			{
				for(var j in electionPcData[i].selectedCasteDetails)
				{
					if(electionPcData[i].selectedCasteDetails[j].casteName != null)
					{
						popupContent +=' <div class="results-dataset">';
						popupContent +=' <div class="results-row layout-full">';
						popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
						popupContent +=' <div class="results-message">';
						popupContent +=' <table class="results-table" style="width:650px">';
						popupContent +=' <tbody>';
						popupContent +=' <tr class="type-democrat">';
						popupContent +=' <td class="results-title" style="width:120px;">';
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].casteName+'</span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td class="results-title" style="width: 25px;">';
						//popupContent +=' <span class="percentage-combo" ><span class="number">'+electionAcData[i].selectedCasteDetails[j].name+'</span>';
						if(electionPcData[i].selectedCasteDetails[j].name =='TDP'){
							popupContent +=' <span > <img src="images/party_flags/TDP.PNG" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='INC'){
							popupContent +=' <span > <img src="images/party_flags/INC.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='TRS'){
							popupContent +=' <span > <img src="images/party_flags/TRS.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='BJP'){
							popupContent +=' <span > <img src="images/party_flags/BJP.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='AIMIM'){
							popupContent +=' <span > <img src="images/party_flags/AIMIM.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='CPM'){
							popupContent +=' <span > <img src="images/party_flags/CPM.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='CPI'){
							popupContent +=' <span > <img src="images/party_flags/CPI.png" width=125% /></span>';
						}
						if(electionPcData[i].selectedCasteDetails[j].name =='LSP'){
							popupContent +=' <span > <img src="images/party_flags/LSP.png" width=125% /></span>';
						}
						
						popupContent +=' </span>';
						popupContent +=' </td>';
						//popupContent +=' <td class="results-title" style="width: 30px;">';
						//popupContent +=' </td>';
						popupContent +=' <td class="results-percentage" style="width: 100px;padding-left: 25px;">';
						if(electionPcData[i].selectedCasteDetails[j].persent != null){
						popupContent +=' <span class="percentage-combo" ><span class="number">'+electionPcData[i].selectedCasteDetails[j].persent+'%</span>';
						}
						else{
						popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
						}
						popupContent +=' <span class="graph">';
						popupContent +=' <span class="bar">';
						popupContent +=' <span style="width:'+electionPcData[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </span>';
						popupContent +=' </td>';
						popupContent +=' <td style="width: 150px;padding-left:35px;">';
						popupContent +=' <span style="font-weight:#000000">'+electionPcData[i].selectedCasteDetails[j].count+' </span>';
						popupContent +=' </td>';
						popupContent +=' </tr>';
						popupContent +=' </tbody>';
						popupContent +=' </table>';
						popupContent +=' </div>';
						popupContent +=' </div>';
						popupContent +=' </div>';
						popupContent +=' </div>';
						
						partyName = electionPcData[i].selectedCasteDetails[0].name;
						fillColour(partyName,layer,popupContent);
					}
					
		
				}
				
			}
		} 
		

		popupContent +=' </article>';
		popupContent +=' </li> ';
		popupContent +=' </ol>';
		popupContent +=' </article>';

		if (feature.properties && feature.properties.popupContent)
		{
			popupContent += feature.properties.popupContent;
		}
		 
		
			
		layer.bindLabel(feature.properties.FIRST_pc_n, {noHide:false});
	}
	/* function getStatesForAssembly()
	{	
		var jsObj=
		{
			electionType:2,
			task:"getStates"						
		};
		$.ajax({
		type: "GET",
		url: "getStatesForHomepage.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			$('#state').find('option').remove();
			$('#state').append('<option value="0">Select State </option>');
			$.each(result,function(index,value){
				$('#state').append('<option value="'+value.id+'">'+value.name+'</option>');
			});


		});		
	} */
	
	
	/* function getAssemblyElectionYears(id,electionType)
	{
		var jsObj=
		{
				stateId:id,
				electionType:electionType,
				task:"getElectionYearsForAState"						
		};
		$.ajax({
		type: "GET",
		url: "electionYearsForstateAndElectionTypeAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			$('#electionYear').find('option').remove();
			$.each(result,function(index,value){
				$('#electionYear').append('<option value="'+value.id+'">'+value.name+'</option>');
			});


		});		
	}
	function getPartiesForSelection()
	{
		var electionTypeId = '';
		if($("input:radio[name=type]:checked").val() == 'ac')
		{
			electionTypeId = 2;
		}
		else
		{
			electionTypeId = 1;
		}
		var jsObj=
		{
				elmtId : "electionScopeSelect",
				stateId : $('#state option:selected').val(),
				electionTypeId : electionTypeId,
				task : "getPartiesInState"
		};
		$.ajax({
		type: "GET",
		url: "getPartiesInState.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
		
				var str='';
				str+='<table><tr><th align="left">Parties : </th><td>';
				for(var i in result){
				if(result[i].id != 366)
				str+='<input id="parties-'+i+'"  class="partySelForPanc" type="checkbox" value="'+result[i].id+'" name="parties"><label class="checkboxLabel" for="parties-'+i+'">'+result[i].name+'</label>';
				}
				str+='</td></tr>';
				str+='<table>';
				
				$('#partiesDiv').html(str);
		});	
	}	 */
	
	
	function buildResult(result){

	$('#subTitlesDiv').html('');
	$('#results1Div').html('');
	
	var subMenu = '';
		if(stateType == 'Semandhra'){
			subMenu = '<h2 style="font-family:Georgia,Times;font-size:16px;font-size:25px;"> Andhra Pradesh Election Results - 2014 </h2>';
			//"ff-tisa-web-pro",Georgia,Times,"Times New Roman",serif
			subMenu +='<ul class="nav nav-pills" style="font-weight: 500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active">';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> State wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li >';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> Parliament wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li><a style="margin-left:20px;"> North Andhra </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> South Andhra </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> Rayalaseema </a></li>';
			subMenu +='</ul>';
		}
		else if(stateType == 'Telangana'){
			subMenu = '<h2 style="font-family: times new roman,serif,sans-serif;font-size:16px;font-size:25px;font-weight: 500;"> Telangana Election Results - 2014 </h2>';
			subMenu +='<ul class="nav nav-pills" style="font-weight:500;">';
			subMenu +='	<li style="margin-top:10px;color:#ADADAD;"> Filter Options : </li> ';
			subMenu +='	<li class="active">';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> State wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li >';
			subMenu +='	<a style="margin-left:20px;" onlcick="stateWiseResult();"> Parliament wise Election Result </a>';
			subMenu +='	</li>';
			subMenu +='	<li><a style="margin-left:20px;"> South Telangana </a></li>';
			subMenu +='	<li><a style="margin-left:20px;"> North Telangana </a></li>';
			subMenu +='</ul>';
		}

	$('#subTitlesDiv').html(subMenu);
	subMenu='';
	
	var today=new Date();

    var month=new Array();
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";
	

	var district = '';
		for(var i in result)
			{
			if(result[i].selectedCasteDetails[0]  != undefined){
				var popupContent='';
			popupContent +='<article class="timeline-group" id="stateAK" style="font-family: times new roman,serif,sans-serif;font-size:16px">';
				/*
					if(district.length ==0 || district !=''+result[i].mandalName+''){
					district = result[i].mandalName;
						popupContent +='<div style="background:#5080A6;">'+result[i].mandalName+'</div>';
					}
				*/
			popupContent +=' <header class="timeline-header">';
			popupContent +=' <h3 style="font-size:17px;color:#FF0000;"><b aria-hidden="true" class="stateface "></b> '+result[i].name+'</a></h3>';
			popupContent +=' </header>';
			//popupContent +=' <span style="color:#303030;">'+month[today.getMonth()]+" "+today.getDate()+","+today.getFullYear()+" "+'</span>';
			popupContent +=' <ol class="timeline-list"> ';
			popupContent +=' <li class="timeline-point is-standard" data-when="future"> ';
			popupContent +=' <article class="results-group">';
			popupContent +=' <header class="results-header" style="width: 950px; margin-top: -10px;margin-bottom: 10px;">';
			popupContent +=' </header>';
			popupContent +=' <span style="font-size: 12px;">  Reporting : 0 % <span style="margin-left:150px;font-size:16px;font-weight:bold;">  Leading Party: '+result[i].selectedCasteDetails[0].name+' </span> <span style="color:#303030;margin-left:300px">'+month[today.getMonth()]+" "+today.getDate()+",  "+today.getFullYear()+" "+'</span></span>';
			popupContent +=' <header class="results-header" style="width: 950px; border-bottom-color: #004276;border-bottom-width: 2px;">';
			popupContent +=' </header>';
			
			popupContent +=' <div class="results-dataset">';
					popupContent +=' <div class="results-row layout-full">';
					popupContent +=' <div class="results-data pos-omega contains-mix is-de-emphasized">';
					popupContent +=' <div class="results-message">';
					if(result[i].selectedCasteDetails.length >0){
					
						for(var j in result[i].selectedCasteDetails)
						{
							
								popupContent +=' <table class="results-table" style="font-weight:bold;font-family:Arial,sans-serif">';
								popupContent +=' <tbody>';
								popupContent +=' <tr class="type-democrat">';
								popupContent +=' <td class="results-title" >';
								popupContent +=' <span class="percentage-combo" ><span class="number">'+result[i].selectedCasteDetails[j].casteName+'</span>';
								popupContent +=' </span>';
								popupContent +=' </td>';
								popupContent +=' <td class="results-title" style="width:200px;">';
								if(result[i].selectedCasteDetails[j].name =='TDP'){
									popupContent +=' <span > <img src="images/party_flags/TDP01.jpg" /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='INC'){
									popupContent +=' <span > <img src="images/party_flags/INC01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='TRS'){
									popupContent +=' <span > <img src="images/party_flags/TRS01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='BJP'){
									popupContent +=' <span > <img src="images/party_flags/BJP01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='AIMIM'){
									popupContent +=' <span > <img src="images/party_flags/AIMIM01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='CPM'){
									popupContent +=' <span > <img src="images/party_flags/CPM01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='CPI'){
									popupContent +=' <span > <img src="images/party_flags/CPI01.jpg" width=125% /></span>';
								}
								if(result[i].selectedCasteDetails[j].name =='LSP'){
									popupContent +=' <span > <img src="images/party_flags/LSP01.jpg" width=125% /></span>';
								}
								
								popupContent +=' </td>';
								//popupContent +=' <td class="results-title" style="width: 30px;">';
								//popupContent +=' </td>';
								popupContent +=' <td class="results-percentage" style=" padding-left: 25px;">';
								if(result[i].selectedCasteDetails[j].persent != null){
								popupContent +=' <span class="percentage-combo" ><span class="number">'+result[i].selectedCasteDetails[j].persent+'%</span>';
								}
								else{
								popupContent +=' <span class="percentage-combo" ><span class="number">0 %</span>';
								}
								popupContent +=' <span class="graph">';
								popupContent +=' <span class="bar">';
								popupContent +=' <span style="width:'+result[i].selectedCasteDetails[j].persent+'%;" class="index"></span>';
								popupContent +=' </span>';
								popupContent +=' </span>';
								popupContent +=' </span>';
								popupContent +=' </td>';
								popupContent +=' <td style="padding-left:35px;">';
								popupContent +=' <span style="font-weight:#000000">'+result[i].selectedCasteDetails[j].count+' </span>';
								popupContent +=' </td>';
								popupContent +=' </tr>';
								popupContent +=' </tbody>';
								popupContent +=' </table>';
							
							}
			
					}
			
					popupContent +=' </div>';
					popupContent +=' </div>';
					popupContent +=' </div>';
					popupContent +=' </div>';
		
			popupContent +=' </article>';
		popupContent +=' </li> ';
		popupContent +=' </ol>';
		popupContent +=' </article>';
		
		$('#results1Div').append(popupContent);
		popupContent='';
		}
		}
		
	}
	/*function buildResult(result)
	{
		
		var str = '';
		str += '<table class="table table-hover table-bordered" id="subLevelTable">';
		str += '<thead>';
		str += '<tr>';
		str += '<th>DISTRICT</th>';
		str += '<th>AC NO</th>';
		str += '<th>AC NAME</th>';
		for(var i in result[0].selectedCasteDetails)
		{
			str += '<th>'+result[0].selectedCasteDetails[i].name+'</th>';
		}
		str += '</tr>';
		str += '</thead><tbody>';
		for(var i in result)
		{
			str += '<tr>';
			str += '<td>'+result[i].mandalName+'</td>';
			str += '<td>'+result[i].hamletId+'</td>';
			str += '<td>'+result[i].name+'</td>';
			
			for(var j in result[i].selectedCasteDetails)
			{
				str += '<th>'+result[i].selectedCasteDetails[j].count+'</th>';
			}
			
			str += '</tr>';
		}
		str+= '</tbody>';
		str += '</table>';
		
		$('#results1Div').html(str);
		
		$('#subLevelTable').dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 300,
		"aLengthMenu": [[300,200,100, -1], [300,200,100,"All"]]
		});
		
		//generateReport('subLevelTable');
	}
	*/
	
	/* function generateReport(tableId)
	{
		tableToExcel(tableId, 'ELECTION RESULT');
	}
	 */
	//getElectionResultForTotalParliment();
	function getElectionResultForTotalParliment()
	{
		
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 17,
				stateId : 1,
				electionScopeId : 1,
				parties : parties,
				scope : "pc",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			electionPcData = result;
			//getLocationRespectiveDetails(stateVal,locationLevel,year,mapNo);
			generateMapForTgPCTotal();
		});	
	}
	
	function generateMapForTgPCTotal()
	{
		areatype = "totPc";
		document.getElementById('weathermap3').innerHTML = "<div id='map3'  style='height: 900px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
		map3 = L.map('map3', {
		center: [18.0000,80.0000],
		zoom: 5
		});
		L.geoJson(campus, {

		style: function (feature) {
			return feature.properties && feature.properties.style;
		},
		
		onEachFeature: onEachFeature,

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				
			});
		}

		}).addTo(map3); 
	}

	function fillColour(partyName,layer,popupContent)
	{
		if(partyName == 'INC')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#228B22'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'TDP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FFD700'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'TRS')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF00FF'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'BJP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF7F50'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'AIMIM')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#006400'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'CPM' || partyName == 'CPI')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#B22222'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'LSP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#4B0082'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'PRP')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#B22222'
			});
			layer.bindPopup(popupContent);
		}
		else if (partyName == 'YSRC')
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#00CED1'
			});
			layer.bindPopup(popupContent);
		}
		else
		{
			layer.setStyle({
			color: '#000000', 
			weight: 1,
			opacity: 0.6,
			fillOpacity: 0.65,
			fillColor: '#FF0000'
			});
			layer.bindPopup(popupContent);
		} 
	}
function showResultDiv()
{
	$('#liveResultsDiv').show();
}


function getRegionWiseResults(searchType)
{
	if(searchType == 'Telangana')
	{
		getElectionResultForAssemblyPrevious(2,"first",1,2);
		getElectionResultForParlimentPresent(2,"second",2,2);
		$('#liveResultsDiv').hide();
		$('#mapDiv').show();
		$('#legend').show();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
	}
	else if(searchType == 'Semandhra')
	{
		getElectionResultForAssemblyPrevious(1,"first",1,2);
		getElectionResultForParlimentPresent(1,"second",2,2);
		$('#liveResultsDiv').hide();
		$('#mapDiv').show();
		$('#legend').show();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
	}
	else if(searchType == 'India')
	{
		$(".parliamentCls").show();
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
	}
	else if(searchType == 'StateAnalysis')
	{
		$('#matridLeadId,#matrixWonSummaryId,#matrixLeadSummaryId,#errorDiv,#test').html('');
		$('#liveResultsDiv').show();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
	}
	else if(searchType == 'DistrictAnalysis')
	{
		console.log(5);
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').hide();
	}
	else if(searchType == 'CBNEffect')
	{
		console.log(6);
		$('#liveResultsDiv').hide();
		$('#mapDiv').show();
		$('#legend').show();
		$('#areaSelectionDiv').show();
		$('#stateSelectDiv').show();
		$('#submitButtionDiv').show();
		$('#modiDiv').hide();
		$('#weathermap3').hide();
		$('#unemchart1p_').hide();
		$('#div_sld1').hide();
		$('#slider3').hide();
		$('#weathermap4').show();
		getParliments(1,38,872,2);
	}
	else if(searchType == 'ModiEffect')
	{
		console.log(7);
		$('#liveResultsDiv').hide();
		$('#mapDiv').hide();
		$('#legend').hide();
		$('#areaSelectionDiv').hide();
		$('#stateSelectDiv').hide();
		$('#submitButtionDiv').hide();
		$('#modiDiv').hide();
		$('#weathermap3').show();
		$('#unemchart1p_').show();
		$('#div_sld1').show();
		$('#slider3').show();
		$('#weathermap4').hide();
		getParliments(1,18,163,1);
		
	}
	}
	var psDetails ;
	function getParliments(stateId,electionId,partyId,electionScopeId)
	{
		var jsObj=
		{
				electionId : stateId,
				stateId : electionId,
				electionScopeId : electionScopeId,
				partyId : partyId,
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "cbnOrModiEffectAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			psDetails = result;
			if(electionScopeId == 1)
			{
				getElectionResultForTotalParliment();
			}
			
			else
			{
				getElectionResultForTotalAssembly();
			}
		});	
	}
	
	function getElectionResultForTotalAssembly()
	{
		var parties = new Array();
		parties.push(872);
		parties.push(362);
		parties.push(163);
		parties.push(72);
		parties.push(886);
		parties.push(662);
		var jsObj=
		{
				electionId : 38,
				stateId : 1,
				electionScopeId : 2,
				parties : parties,
				scope : "ac",
				task : "getElectionResults"
		};
		$.ajax({
		type: "GET",
		url: "getElectionResultsAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		})
		.done(function( result ) {
			//electionAcData = result;
			getMapForAssembly();
			
		});	
		
		function getMapForAssembly()
		{
			areatype = "totac";
			document.getElementById('weathermap4').innerHTML = "<div id='map4'  style='height: 500px; border: 1px solid rgb(51, 51, 51); border-radius: 10px; position: relative; background: none repeat scroll 0% 0% rgb(255, 255, 255);'></div>"
			map4 = L.map('map4', {
			center: [18.0000,80.0000],
			zoom: 6
			});
			L.geoJson(apaccampus, {

			style: function (feature) {
				return feature.properties && feature.properties.style;
			},
			
			onEachFeature: onEachFeature,

			pointToLayer: function (feature, latlng) {
				return L.circleMarker(latlng, {
					
				});
			}

			}).addTo(map4); 
		}

	}
	
	<!-- From SASI -->
	
	getStateWideParliamentsSummary();
	getPartyWiseWonLeadCountInLive();

	function getStateWideParliamentsSummary(){
		var jsObj={};
		$.ajax(
		  {
				type: "POST",
				url:"getStateWideParliamentLiveResults.action",
				data:{task :JSON.stringify(jsObj)}
		  }
		  ).done(function(result){
				buildStateWideParliaments(result);
		  });
	}

	function getPartyWiseWonLeadCountInLive(){
		var jsObj={};
		$.ajax(
		  {
				type: "POST",
				url:"getPartyWiseWonLeadCountInLive.action",
				data:{task :JSON.stringify(jsObj)}
		  }
		  ).done(function(result){
				buildPartyWideWonLeadCount(result);
		  });
	}
	
	function buildPartyWideWonLeadCount(results){
	if(results.statesList.length>0){
	var str = "";
		str += "<h2 class='offset3' style='margin-bottom:5px;margin-top:10px;color:#27AFA6;'>Party Wise Won/Lead Counts</h2>";
		str +="<table class='parlResultTable offset1' width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str+="<tr style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> PARTY </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> ALLIANCE </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> WON </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> LEAD </td>";
			str+="</tr>";
			for(var i in results.statesList){
				str+="<tr class='bodyRows' style='color:black;'>";
					var path = "images/party_flags/"+results.statesList[i].party+"01.jpg";
					
					str+="<td style='border-bottom:1px solid #B0BDDA;height:25px;'><img src="+path+" alt="+results.statesList[i].party+"></td>";
					str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].allianceGroup+"</td>";
					if(results.statesList[i].partyWonCount == null){
						str+="<td style='border-bottom:1px solid #B0BDDA;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].partyWonCount+"</td>";
					}
					if(results.statesList[i].partyLeadCount == null){
						str+="<td style='border-bottom:1px solid #B0BDDA;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].partyLeadCount+"</td>";
					}
				str+="</tr>";
			}
			
		str+="</tbody>";
	str+="</table>";
	
	$(".partyWiseResultDiv").html(str);
	}
	
}

function buildStateWideParliaments(results){
	var str = "";
	str +="<table class='parlResultTable offset1' width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str +="<tr>";
				str +="<td colspan='5'><img width='300' height='130' src='images/specialPage/2014Ele.png'></td>";
				str +="<td colspan='3'><img width='140' height='150' src='images/specialPage/Modi.png'></td>";
				str +="<td colspan='3'><img width='140' height='150' src='images/specialPage/Rahul.png'></td>";
			str +="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='14'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
			str +="<tr>";
				str +="<td rowspan='3' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>STATE</td>";
				str +="<td rowspan='3' align='center' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>TOTAL</td>";
				str +="<td colspan='6' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #B0BDDA;'>2014</td>";
				str +="<td width='4%'> </td>";
				str +="<td colspan='4' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #B0BDDA'>2009</td>";
			str +="</tr>";
			
			
			str +="<tr>";
				
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f2be8e;'>NDA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#8dbfa0;'>UPA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f4f4a4'>OTHERS</td>";
				str +="<td width='4%'> </td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f2be8e;'>NDA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#8dbfa0'>UPA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA ;background:#f4f4a4'>OTHERS</td>";
			str +="</tr>";
			str +="<tr style='color:black;'>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>WON</td>";
			str +="</tr>";
			
		
			
			for(var i in results.statesList){
				
			str +="<tr class='bodyRows' style='color:black;'>";
				str +="<td align='left' style='color:#000066'>"+results.statesList[i].state+"</td>";
				str +="<td align='center' >"+results.statesList[i].statesTotalCount+"</td>";
				if(results.statesList[i].ndaWonCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				if(results.statesList[i].ndaLeadCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				if(results.statesList[i].upaWonCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				if(results.statesList[i].upaLeadCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				
				if(results.statesList[i].othersWonCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
				
				if(results.statesList[i].othersLeadCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
				
				str +="<td width='4%'> </td>";
				
				if(results.statesList[i].ndaAlliancesCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				
				if(results.statesList[i].upaAlliancesCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				
				if(results.statesList[i].othersCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
			str +="</tr>";
			
			}
			
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
			str+="<tr style='font-weight:bold;color:black;'>";
				str +="<td>Totals</td>";
				str +="<td align='center'>"+results.overallStatesCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersWonCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersLeadCount+"</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right'>"+results.overAllNdaCount+"</td>";
				str +="<td align='right'>"+results.overAllUpaCount+"</td>";
				str +="<td align='right'>"+results.overAllOthersCount+"</td>";
			str+="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
		str +="</tbody>";
	str +="</table>";
	
	$(".parliamentResultsDiv").html(str);
}

var marginDetails =	{
			electionId : '',
			type:'',
			locationIds:[]
		}
		
function getMarginsCountOfParties(){

		marginDetails.electionId = $('#electionId').val();
		marginDetails.type = $('#scopeId').val();
		marginDetails.locationIds = $('#locaionsId1').val() ;
		
		$.ajax({
				type: "POST",
				url:"getMarginAnalysisOnLiveResultsForAssemblies.action",
				data:{task :JSON.stringify(marginDetails)}
		  }).done(function(result){
				if(result.partiesList != null && result.partiesList.length>0){
					buildPartyWiseMarginCount(result);
				}
		  });
}

function buildPartyWiseMarginCount(result){
	var str = '';
	str+='<label style="margin-bottom:3px;"  class="headingClass">Party Wise Margin Analysis</label>';

	str+='<div class="tableClass1">';
	str+='<table width="80%" class="" style="clear:both;" style="margin-top:25px;">';
	// str += "<table width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody>";
			str +="<tr>";
				str+="<td>PARTY</td>";
				for(var i in result.partiesList[0].marginsVO){
					str+="<td>"+result.partiesList[0].marginsVO[i].margin+"</td>";
				}
			str +="</tr>";
		
			for(var i in result.partiesList){
			str +="<tr>";
				str +="<td>"+result.partiesList[i].partyName+"</td>";
				for(var j in result.partiesList[i].marginsVO){
					
						if(result.partiesList[i].marginsVO[j].count == null){
							str+="<td> - </td>";
						}else{
							str+="<td>"+result.partiesList[i].marginsVO[j].count+"</td>";
						}
				
				}
			str +="</tr>";
			}
		str +="</tbody>";
	str += "</table>";
	str+='</div>';
	
	$("#marginAnalysis1").html(str);
}

var matrixReportDtls1={
	electionId:'',
    scopeId:'',
    locationIds:[]
		
};
function testIt()
{
	matrixReportDtls1.electionId = $('#electionId').val();
	matrixReportDtls1.scopeId = $('#scopeId').val();
	matrixReportDtls1.locationIds = $('#locaionsId1').val();

	$.ajax({
          type:'POST',
          url: 'getWonAndLeadCountPartyWise.action',
          dataType: 'json',
          data: {task:JSON.stringify(matrixReportDtls1)},

          success: function(result){ 
			   console.log(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}
</script>

</body>
</html>