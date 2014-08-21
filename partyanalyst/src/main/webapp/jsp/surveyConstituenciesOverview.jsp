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

<!--<h4 class="offset3">CONSTITUENCY WISE CASTE COLLECTION DETAILS</h4>-->
<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="cAjaxImg"/>
<div id="constituencyWiseReport"  class="span12"></div>

<!--<h4 class="offset3 hide" id="boothHeading">BOOTH WISE CASTE COLLECTION DETAILS</h4>-->
<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="bAjaxImg"/>
<div id="boothWiseReport" class="span12"></div>

<img src='images/Loading-data.gif' style="width:70px;height:60px;" class="hide offset6"  id="vAjaxImg"/>
<div id="votersReport"  class="span12"></div>	

<div class="span12">
	<div id="boothWiseStatusDtls"></div>
</div>

<script>
var reportType = '${constituencyId}';
if(reportType == 1)
{
	getConstituencyWiseCasteCollectionDetails('${regionId}','${userTypeId}');
}
else if(reportType == 2)
{
	getConstituencyWiseTeamCollecetdDetails('${regionId}','${userTypeId}');
}
else if(reportType == 3)
{
	getConstituencyWiseQcVerificationSummary('${regionId}','${userTypeId}')
}
else if(reportType == 4)
{
	getConstituencyWiseTeamDetails('${regionId}','${userTypeId}')
}

function getConstituencyWiseTeamCollecetdDetails(type,stateId)
{
	var jsObj = {
		type : type,
		stateId : stateId
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseTeamCollectedSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
			if(result != null)
			{
				buildTeamConstituencyWiseDetailsSummary(result,type,stateId);
			}
		});	
		
}

function buildTeamConstituencyWiseDetailsSummary(result,strTypr,stateId)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a onClick="getBoothWiseTeamCollecetdDetails('+result[i].dcVotersCount+',\''+strTypr+'\','+stateId+')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
}


function getConstituencyWiseQcVerificationSummary(type,stateId)
{
	var strTypr = '';
	if(type == 0)
	{
		strTypr = "null";
	}
	else if(type == 1)
	{
		strTypr = "Y";
	}
	else
	{
		strTypr = "N";
	}
	var jsObj = {
		type : strTypr,
		stateId : stateId
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseQcVerificationSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			 {
				buildQcConstituencyWiseSummary(result,strTypr,stateId);
			 }
		});	
}

function buildQcConstituencyWiseSummary(result,strTypr,stateId)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a onClick="getBoothWiseQcVerificationSummary('+result[i].dcVotersCount+',\''+strTypr+'\','+stateId+')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
}
function getBoothWiseQcVerificationSummary(constituencyId,type,stateId)
{
	var jsObj = {
		constituencyId : constituencyId,
		type : type,
		stateId : stateId
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseQcVerificationSummary.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildQcBoothWiseQCSummary(result);
			}
		});	
}

function buildQcBoothWiseQCSummary(result)
{
	$('#boothWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a>'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
}

function getBoothWiseTeamCollecetdDetails(constituencyId,surveyUserTypeId,stateId)
{
	var jsObj = {
		constituencyId : constituencyId,
		surveyUserTypeId : surveyUserTypeId,
		stateId : stateId
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildBoothWiseTeamCollecetedDetails(result);
			}
		});	

}

function buildBoothWiseTeamCollecetedDetails(result)
{
	$('#boothWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a>'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
}
function getConstituencyWiseTeamDetails(type,stateId)
{
	var jsObj = {
		type : type,
		stateId :stateId
	}
	$.ajax({
			type:'GET',
			url: 'getConstituencyWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){
			if(result != null)
			{
				buildTeamConstituencyWiseSummary(result,type,stateId);
			}
		});	
		
}

function buildTeamConstituencyWiseSummary(result,strTypr,stateId)
{
	$('#constituencyWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Constituency</th>';
	str += '<th>Booths</th>';
	str += '<th>Users</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a onClick="getBoothWiseTeamDetails('+result[i].qcVotersCount+',\''+strTypr+'\','+stateId+')">'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].dcBoothsCount+'</td>';
		str += '<td>'+result[i].dcConstituencysCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#constituencyWiseReport').html(str);
}

function getBoothWiseTeamDetails(constituencyId,surveyUserTypeId,stateId)
{
	var jsObj = {
		constituencyId : constituencyId,
		surveyUserTypeId : surveyUserTypeId,
		stateId : stateId
	}
	$.ajax({
			type:'GET',
			url: 'getBoothWiseTeamDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		 }).done(function(result){	
			if(result != null)
			{
				buildBoothWiseTeamDetails(result);
			}
		});	
}
function buildBoothWiseTeamDetails(result)
{
	$('#boothWiseReport').html('');
	var str = '';
	str += '<table class="table table-bordered m_top20 table-hover table-striped offset3">';
	str += '<thead>';
	str += '<tr>';
	str += '<th>Booth</th>';
	str += '<th>Survey User</th>';
	str += '<th>Mobile No</th>';
	str += '<th>Voters</th>';
	str += '</tr>';
	str += '</thead>';
	str += '<tbody>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td><a>'+result[i].dcPercentage+'</a></td>';
		str += '<td>'+result[i].qcPercentage+'</td>';
		str += '<td>'+result[i].verifierPercentage+'</td>';
		str += '<td>'+result[i].qcVotersCount+'</td>';
		str += '</tr>';
	}
	str += '</tbody>';
	str += '<table>';
	$('#boothWiseReport').html(str);
}


function getConstituencyWiseCasteCollectionDetails(regionId,userTypeId)
{
	$('#constituencyWiseReport').html('');
	$('#cAjaxImg').show();

	$.ajax({
		type:'GET',
		url: 'getCasteCollectedDetails.action',
		dataType: 'json',
		data: {regionId:regionId,userTypeId:userTypeId},
	}).done(function(result){
			buildConstituencyWiseDetails(result,userTypeId);
	});
}
function buildConstituencyWiseDetails(result,userTypeId)
{
	var str ='';

	str+='<h4 class="offset3">CONSTITUENCY WISE CASTE COLLECTION DETAILS</h4>';
	str+='<div class="span10 offset2">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="constnDtls">';
	str+='<thead>';
	 str+='<tr>';
	  str+='<th>Constituency Name</th>';
	  str+='<th>Total Booths</th>';
	  str+='<th>Total Voters</th>';
	  str+='<th>Completed Booths</th>';
	  str+='<th>Completed Voters</th>';
	 str+='</tr>';
	str+='</thead>';

    str+='<tbody>';
	$.each(result,function(index,value){
	 str+='<tr>';
	  str+='<td><a href="javascript:{getConstituencySurveyDetails('+value.locationId+','+userTypeId+',\''+value.locationName+'\')}">'+value.locationName+'</a></td>';
	  str+='<td>'+value.totalBooths+'</td>';
	  str+='<td>'+value.totalVoters+'</td>';
	  str+='<td>'+value.collectedBoothsCount+'</td>';
	  str+='<td>'+value.collectedVotersCount+'</td>';
	 str+='</tr>';

	});
	str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#constituencyWiseReport').html(str);

	$('#constnDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});

	$('#cAjaxImg').hide();

}

function getConstituencySurveyDetails(constituencyId,userTypeId,locationName)
{
	$('#boothWiseReport,#votersReport').html('');
	//$('#boothHeading').show();

	 $('html, body').animate({
        scrollTop: $("#boothWiseReport").offset().top
    }, 2000);

	$('#bAjaxImg').show();

	$.ajax({
		type:'GET',
		url: 'getSurveyDetailsByConstituencyId.action',
		dataType: 'json',
		data: {constituencyId:constituencyId,userTypeId:userTypeId},
	}).done(function(result){
			buildConstituencySurveyDetails(result,userTypeId,locationName);
	});
}
function buildConstituencySurveyDetails(result,userTypeId,locationName)
{
	var str = '';

	str+='<h4 class="offset3">'+locationName+' CONSTITUENCY BOOTH WISE CASTE COLLECTION DETAILS</h4>';

	str+='<div class="span10 offset2">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="boothDtls">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>Booth No</th>';
	   str+='<th>Total Voters</th>';
	   str+='<th>Collected Voters</th>';
	   str+='<th>Users</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
	  $.each(result,function(index,value){
		  str+='<tr>';
			str+='<td><a href="javascript:{getBoothWiseCollectedcasteDetails('+value.locationId+','+value.locationName+','+userTypeId+')}">'+value.partNo+'</a></td>';
			str+='<td>'+value.totalVoters+'</td>';
			str+='<td>'+value.collectedVotersCount+'</td>';
			str+='<td>'+value.name+'</td>';
		  str+='</tr>';
	  });
	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#boothWiseReport').html(str);

	$('#boothDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	$('#bAjaxImg').hide();

}

function getBoothWiseCollectedcasteDetails(boothId,boothNo,userTypeId)
{
	$('#votersReport').html('');
	$('#vAjaxImg').show();
    $('html, body').animate({
		scrollTop: $("#votersReport").offset().top
	}, 2000);

	$.ajax({
		type:'GET',
		url: 'getBoothWiseCollectedcasteDetails.action',
		dataType: 'json',
		data: {constituencyId:boothId,userTypeId:userTypeId},
	}).done(function(result){
			if(userTypeId != 0)
  			  buildBoothWiseCasteCollectedDetails(result,boothNo);
			else
			  buildBoothWiseVerifiedCasteCollectedDetails(result,boothNo);
			  $('#vAjaxImg').hide();
			 // $('#votersReport').scrollTo(500);		
	});

}
function buildBoothWiseCasteCollectedDetails(result,boothNo)
{

	var str ='';

	str+='<h4 class="offset3">COLLECTED CASTE DETAILS FOR BOOTH-'+boothNo+'</h4>';

	str+='<div class="span10 offset2">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="votersDtls">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>Voter Name</th>';
	   str+='<th>Relative Name</th>';
	   str+='<th>Gender</th>';
	   str+='<th>Age</th>';
	   str+='<th>House No</th>';
	   str+='<th>Caste</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		  str+='<tr>';
		    str+='<td>'+value.voterName+'</td>';
			str+='<td>'+value.relativeName+'</td>';
			str+='<td>'+value.gender+'</td>';
			str+='<td>'+value.age+'</td>';
			str+='<td>'+value.houseNo+'</td>';
			str+='<td>'+value.casteName+'</td>';
		  str+='</tr>';

	 });

	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#votersReport').html(str);
   $('#votersDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});

}

function buildBoothWiseVerifiedCasteCollectedDetails(result,boothNo)
{
	var str ='';

	str+='<h4 class="offset3">COLLECTED CASTE DETAILS FOR BOOTH-'+boothNo+'</h4>';

    str+='<div class="span10 offset2">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped" id="votersDtls">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th>Voter Name</th>';
	   str+='<th>Relative Name</th>';
	   str+='<th>Gender</th>';
	   str+='<th>Age</th>';
	   str+='<th>House No</th>';
	   str+='<th>Collected Caste</th>';
	   str+='<th>Verified Caste</th>';
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		  str+='<tr>';
		    str+='<td>'+value.voterName+'</td>';
			str+='<td>'+value.relativeName+'</td>';
			str+='<td>'+value.gender+'</td>';
			str+='<td>'+value.age+'</td>';
			str+='<td>'+value.houseNo+'</td>';

			if(value.dcCasteName != null)
 			 str+='<td>'+value.dcCasteName+'</td>';
			else
			 str+='<td>-</td>';

			if(value.dcCorrectedCasteName != null)
 			 str+='<td>'+value.dcCorrectedCasteName+'</td>';
			else
			 str+='<td>-</td>';
		  str+='</tr>';

	 });

	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#votersReport').html(str);

	$('#votersDtls').dataTable({
		"iDisplayLength": -1,
		"aLengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
}
</script>
<script>
//boothWiseStatusDetailsByConstituency();
function boothWiseStatusDetailsByConstituency()
{
	$.ajax({
		type:'GET',
		url: 'getAllBoothsStatusDetailsByConstituencyId.action',
		dataType: 'json',
		data: {constituencyId:217},
	}).done(function(result){
		buildBoothWiseStatusDetails(result);
	});
}
function buildBoothWiseStatusDetails(result)
{
	var str ='';

    str+='<div class="span3 offset3">';
	str+='<table class="table table-bordered m_top20 table-hover table-striped">';
	 str+='<thead>';
      str+='<tr>';
	    str+='<th>Boot No</th>';
		str+='<th>DC</th>';
		str+='<th>DV</th>';
		str+='<th>QC</th>';
		str+='<th>WM-DC</th>';
		str+='<th>WM-DV</th>';
      str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';

	 $.each(result,function(index,value){
		 str+='<tr>';
		    str+='<td>'+value.partNo+'</td>';
			str+='<td>'+value.dcCompleted+'</td>';
			str+='<td>'+value.dvCompleted+'</td>';
			str+='<td>'+value.qcCompleted+'</td>';
			str+='<td>'+value.wmDcCompleted+'</td>';
			str+='<td>'+value.wmDvCompleted+'</td>';
		 str+='</tr>';
	 });
	 str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#boothWiseStatusDtls').html(str);
}
</script>
</body>
</html>