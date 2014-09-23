<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<style>
.ui-multiselect{
width:200px !important;
}

</style>
</head>
<body>
<script>
$(document).ready(function() {
 $('#districtId').multiselect({
			  noneSelectedText:"Select District"});
 $('#constituencyId').multiselect({
			  noneSelectedText:"Select Constituency"});
			  
$('#parliamentId').multiselect({
			  noneSelectedText:"Select Parliament"});
$('#mandalId').multiselect({
			  noneSelectedText:"Select Mandal"});
			  
			 

  });
</script>
<div class="container "><div style="margin-top:20px;" class="widget ">
<div class="row">
<div class="span8">

<h2>Mobile Numbers Extraction For IVR</h2></div>
<div class="span3">
<input type="button" onclick="reselMobileNumbers();" class="btn mytooltip" value="Reset" data-toggle="tooltip" data-title="Do you want to reset all mobile as not used ? click here"></input><img src="./images/icons/search.gif" id="ajaxImg1" style="display:none"/>
<input class="btn" type="button" value="View Counts" onClick="openDialog()"></div>
<div id="viewCountDialogDiv" style="display:none;"><div id="viewCountDialogInnerDiv"></div></div>
</div>

   <div id="errorDiv"></div>
    <div class="row-fluid " style="margin-top: 20px;">
	 <div class="span2">
    <label>Select Level</label>
    <select id="scopeId" onchange="showHide();correspondingCall();" class="input-block-level"><option value="0">Select Scope</option><option value="1">District</option>
	<option value="2">Constituency</option>
	<option value="3">Parliament</option>
	<option value="4">Mandal</option>
	</select>
	</div>
<div class="span3">
    <label>Select State</label>
    <select id="regionId" class="input-block-level" onchange="correspondingCall();">
	<option value="0">ALL</option>
	<option value="1">Andhra Pradesh</option>
	<option value="2">Telangana</option>
	</select>
	</div>

<div class="span3" id="districtDiv">
    <label>Select District</label>
    <select id="districtId"  multiple="true" onchange="getConstituencies();getMandals();calculateTotal();" class="input-block-level">
	</select>
	</div>
<div class="span3" id="constituencyDiv" style="display:none;">
	<label>Select Constituency</label>
    <select id="constituencyId" multiple="true" class="input-block-level" onchange="calculateTotal();">
	</select>
	</div>
<div class="span3" id="parliamentDiv" style="display:none;">
	<label>Select Parliamnet</label>
    <select id="parliamentId" multiple="true" class="input-block-level" onchange="calculateTotal();">
	</select>
	</div>
	<div class="span3" id="mandalDiv" style="display:none;">
	<label>Select Tehsil</label>
    <select id="mandalId" multiple="true" class="input-block-level" onchange="calculateTotal();">
	</select>
	</div>

</div>
<div class="row">
<div class="span12 form-inline" style="margin-top:26px;">
  <label>  Do you want to split the files as per Questions & options &nbsp;&nbsp;&nbsp;&nbsp;</label> <label class="radio">No<input type="radio" name="queOpt" checked value="1" class="QueRadio" id="queOpt1">
  </label>
  <label class="radio">Yes<input type="radio" name="queOpt"  value="2" class="QueRadio" id="queOpt2">
  </label> 
  </div>
</div>
<div class="row" style="text-align: center; margin-top: 20px;">
<div class="span3" id="questionDiv" style="display:none;">
	<label>Select No Of Quetions</label>
	 <select id="questionID" class="input-block-level" onchange="showOptions();">
	<option value="0">Select</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	</select>
	</div>
	</div>
	
<div class="row well form-inline" id="OptionsDiv" style="display:none;margin-left: 0px; margin-top: 20px;">

	</div>
<div style="margin-top: 20px;" class="row form-inline">
<div class="span2">
	<label>Enter No.of Mnos</label>
    <input type="text" class="input-block-level mytooltip" data-toggle="tooltip" data-title="Enter No of Mobile no.s from each location" id="maxIndex">
	</input>
</div>

<div class="span1">
	<label>Total</label>
    <input type="text" class="input-block-level" id="totalSize">
	</input>
</div>


<div class="span4 "  style="margin-top: 26px;" id="radioBtnDiv">
 <label class="radio">single File<input type="radio" onclick="showTextBox();" name="radiobtn" id="singleFileId" value="1" checked>
  </label>
<label class="radio">each File<input type="radio" onclick="showTextBox();" name="radiobtn" id="singleFileId" value="2" >
  </label>
 
  <label class="radio">Multiple Files<input type="radio" name="radiobtn" id="multipleFileId" value="3" onclick="showTextBox();" >
  </label>&nbsp;&nbsp;&nbsp;
  </div>

<div class="span2" style="margin-left: -21px;display:none;" id="noOfFileDiv">
	<label>Enter No.of files</label>
    <input type="text" class="input-block-level" id="noOfFileId">
	</input>
</div>

<div class="span3" style="margin-top:26px;">
  <label>  Select File Format &nbsp;&nbsp;&nbsp;&nbsp;</label> <label class="radio">txt<input type="radio" name="optionsRadios" id="optionsRadios2" value="2" checked>
  </label> <label class="radio">csv<input type="radio" name="optionsRadios" id="optionsRadios2" value="1" >
  </label></div>
</div>

<div class="row" style="text-align: center; margin-top: 20px;">

    <input type="button" onclick="createFile();" class="btn" value="Submit"></input>
	<img src="./images/icons/search.gif" id="ajaxImg" style="display:none"/>
	   <a id="downloadLink" style="margin-left: 11px;display:none;" href="${filePath}" class="btn btn-info" download>Download link</a>
</div>
   <div class="row" style="text-align: center; margin-top: 20px;" id="loactionCountDiv">
   </div>
  </div></div>
<script>

			  
function correspondingCall()
{
	
	var scopeId = $("#scopeId").val();
	
	if(scopeId == 0)
	{
		$('#districtId').find('option').remove();
		$('#districtId').multiselect('refresh');
		$('#constituencyId').find('option').remove();
		$('#constituencyId').multiselect('refresh');
		return;
	}
	if(scopeId == 1 || scopeId == 2 || scopeId == 4)
	getDistrictsForRegion();
	if(scopeId == 2) 
	getConstituencies();
	if(scopeId == 3) 
	getParliamentConstituencies();
	if(scopeId == 4)
		getMandals();
	


}
function getDistricts()
{
    $('#districtId').find('option').remove();
	
	var jsObj=
	{
		task : "getDistricts"
	};
	$.ajax({
	type: "GET",
	url: "getDistrictsAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
		$.each(result,function(index,value){
			$('#districtId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		$('#districtId').multiselect('refresh');	
	});	
	
}

function getConstituencies()
{
	$('#constituencyId').find('option').remove();
	$('#constituencyId').multiselect('refresh');
	var scopeId = $("#scopeId").val();
	var districtIds = $('#districtId').val();
	
	if(districtIds == null)
		return;
	if(scopeId == 2)
	{
	var jsObj=
	{
		    districtIds : districtIds,
			task : "getConstituencies"
	};
	$.ajax({
	type: "GET",
	url: "getConstituenciesAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
	
		$.each(result,function(index,value){
			$('#constituencyId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
			$('#constituencyId').multiselect('refresh');	
		
	});	
	}
}
function getMandals()
{
	$('#mandalId').find('option').remove();
	$('#mandalId').multiselect('refresh');
	var scopeId = $("#scopeId").val();
	var districtIds = $('#districtId').val();
	
	if(districtIds == null)
		return;
	if(scopeId == 4)
	{
	var jsObj=
	{
		    districtIds : districtIds,
			task : "getTehsils"
	};
	$.ajax({
	type: "GET",
	url: "gettehsilsAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
	
		$.each(result,function(index,value){
			$('#mandalId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
			$('#mandalId').multiselect('refresh');	
		
	});		
	}
}
function reselMobileNumbers()
{
	if(confirm("Do you want to proceed to reset all mobile numbers as not used ? "))
	{
	var jsObj = {
		task : "reset"
	}
	$("#errorDiv").html('');
	$("#ajaxImg1").css("display","inline-block");
	$.ajax({
	type: "GET",
	url: "resetMobileNoAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
		$("#ajaxImg1").css("display","none");
		if(result.resultCode == 0)
			$("#errorDiv").html(" Reset Successfully..").css("color","green");
		else
	$("#errorDiv").html(" Exception Occured ,Try again..").css("color","red");
	
	});	
	}
}
function createFile()
{
	$("#downloadLink").css("display","none");
	$("#loactionCountDiv").html('');
	var flag = false;
	var questions = false;
	var queOptionsArr = new Array();
	var locationIds ;
	var scopeId = $("#scopeId").val();
	var checkedTypeVal = $('input:radio[name=radiobtn]:checked').val();
	var queradio = $('input:radio[name=queOpt]:checked').val();
	if(queradio == 2)
	questions = true;
	var noOfFiles = $.trim($('#noOfFileId').val());
	if($.trim($('#noOfFileId').val()).length == 0)
	noOfFiles = 0;

	if(scopeId == 1) 
	locationIds = $('#districtId').val();
	else if(scopeId == 2)
	locationIds = $('#constituencyId').val();
	else if(scopeId == 3)
	locationIds = $('#parliamentId').val();
	else if(scopeId == 4)
	locationIds = $('#mandalId').val();

	var fileFormat = $('input:radio[name=optionsRadios]:checked').val();
	var maxIndex = $.trim($('#maxIndex').val());
	
	$("#errorDiv").html("");
	var str='<font color="red" style="font-size: 12px; font-weight: bold;">';
	if(scopeId == 0)
	{
	str+='Please Select Scope<br/>';
	flag =true;
	}
	else if(locationIds == null || locationIds.length ==0)
	{
		if(scopeId == 1)
		str+='Select District';
		else if(scopeId == 2)
		{
		if($('#districtId').val() == null)
		str+='Select District<br/>';
		str+='Select Constituency';
		}
		else if(scopeId == 3)
		{
		if($('#parliamentId').val() == null)
		
		str+='Select Parliament';
		}
		else if(scopeId == 4)
		{
		if($('#districtId').val() == null)
		str+='Select District<br/>';
		str+='Select Mandal';
		}
		flag =true;
	}
	else if(maxIndex.length == 0)
	{
	str+='No Of Mobile Nos is required';
	flag =true;
	}
	else if(isNaN(maxIndex))
	{
		str+='Enter number';
		flag =true;
	}
	else if(maxIndex < 100)
	{
	str+='minimum 100 mobileNo.s';
	flag =true;
	}
	else if(checkedTypeVal == 3)
	{
		if(noOfFiles == 0)
		{
		str+='No of files is required <br/>';
		flag = true;
		}
	}
	else if(questions == true)
	{
		var questionID = $("#questionID").val();
		if(questionID == 0)
		{
		str+='No of Questions is required <br/>';
		flag = true;
		}
		
	$(".optionsCls").each(function(){
		
		var optionId = $(this).attr("id");
		var optionsCnt = $.trim($(this).val());
		var optnum = optionId.replace( /\D+/g, '');
		$(".opterror"+optnum).html('');
		if(optionsCnt == 0)
		{
		$(".opterror"+optnum).html("options count is required").css("color","red");
		flag =true;
	    }
		else if(isNaN(optionsCnt))
		{
		str+='Enter number';
		flag =true;
		}
		if(flag == true)
			return;
		var question = "Q".concat(optnum);
		var obj = {
		question : question,
		optionsCnt : optionsCnt
	  }
	queOptionsArr.push(obj);
	});
}
	str+='</font>';
	$("#errorDiv").html(str);
		console.log(queOptionsArr);
	if(flag == true)
	return;
	$("#errorDiv").html('');
	$("#ajaxImg").show();

	var jsObj=
	{
			locationIds : locationIds,
			fileFormat:fileFormat,
			scopeId:scopeId,
			maxIndex:maxIndex,
			checkedTypeVal:checkedTypeVal,
			noOfFiles:noOfFiles,
			questions:questions,
			queOptionsArr:queOptionsArr,
			task : "createFilepath"
	};
	$.ajax({
	type: "GET",
	url: "getivrMobileNoAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
		$("#ajaxImg").hide();
		clearDiv();
		if(result.resultCode == 0)
		{
	  $("#errorDiv").html("<font color='green' style='font-size: 12px; font-weight: bold;'>file created successfully</font>");
	  $("#downloadLink").attr('href',result.status);
	  $("#downloadLink").css("display","block").css("display","inline-block");
	 
	  buildLocationData(result,jsObj);
		}
		else if(result.resultCode == 2)
		{
		$("#errorDiv").html("<font color='red' style='font-size: 12px; font-weight: bold;'>Exception Occured,try again..</font>");
		}
	   else if(result.resultCode == 1)
		{
	   $("#errorDiv").html("<font color='red' style='font-size: 12px; font-weight: bold;'>No Mobile No.s available for selection criteria...</font>");
		}
	});		
}
function buildLocationData(result,jObj)
{
	var str ='';
	if(jObj.scopeId == 1)
	str+='<h3>District wise mobile Numbers Info</h3>';
	else if(jObj.scopeId == 2)
	str+='<h3>Constituency wise mobile Numbers Info</h3>';
	else if(jObj.scopeId == 3)
	str+='<h3>Parliament wise mobile Numbers Info</h3>';
	else if(jObj.scopeId == 4)
	str+='<h3>Mandal wise mobile Numbers Info</h3>';

	str+='<table class="table table-bordered offset3 " style="width: 50%;font-family:verdana;">';
	
	if(jObj.scopeId == 1)
	str+='<th>District</th>';
	else
	str+='<th>Constituency</th>';
	str+='<th>No.of mobiles</th>';
	var count = 0;
	for(var i in result.list)
	{
		str+='<tr>';
		str+='<td>'+result.list[i].name+'</td>';
		str+='<td>'+result.list[i].count+'</td>';
		str+='</tr>';
		count = count + result.list[i].count;
	}
	str+='</table>';
	$("#loactionCountDiv").html(str);
}
function showHide()
{
var scopeId = $("#scopeId").val();
if(scopeId == 1)
	{
	$("#constituencyDiv").hide();
	$("#mandalDiv").hide();
	$("#parliamentDiv").hide();
	$("#districtDiv").show();
	}
else if(scopeId == 2)
	{
   $("#constituencyDiv").show();
   $("#districtDiv").show();
	$("#parliamentDiv").hide();
	$("#mandalDiv").hide();
	}
	else if(scopeId == 3)
	{
	$("#constituencyDiv").hide();
	$("#parliamentDiv").show();
	$("#mandalDiv").hide();
	$("#districtDiv").hide();
	
	}
	else if(scopeId == 4)
	{
	$("#constituencyDiv").hide();
	$("#parliamentDiv").hide();
	$("#mandalDiv").show();
	$("#districtDiv").show();
	}
}
function getParliamentConstituencies()
{
	$('#parliamentId').find('option').remove();
	$('#parliamentId').multiselect('refresh');
	var regionId = $("#regionId").val();
	
	var jsObj=
	{
		    regionId : regionId,
			task : "getPcs"
	};
	$.ajax({
	type: "GET",
	url: "getParliamnetsForRegionAction.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	})
	.done(function( result ) {
	
		$.each(result,function(index,value){
			$('#parliamentId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
			$('#parliamentId').multiselect('refresh');	
		
	});		
}
function calculateTotal()
{
	$("#totalSize").val('');
	var scopeId =  $("#scopeId").val();
	var maxIndex = $("#maxIndex").val();
	if(scopeId == 0 || isNaN(maxIndex) || maxIndex.length == 0)
		return;
	var locationIds;
	if(scopeId == 1) 
	locationIds = $('#districtId').val();
	else if(scopeId == 2)
	locationIds = $('#constituencyId').val();
	else if(scopeId == 3)
	locationIds = $('#parliamentId').val();
	else if(scopeId == 4)
	locationIds = $('#mandalId').val();
	if(locationIds != null)
	var total = locationIds.length * maxIndex;
	if(total > 0)
	$("#totalSize").val(Math.round(total));
}
function calculateMaxIndex()
{
	$("#maxIndex").val('');
	var scopeId =  $("#scopeId").val();
	var totalSize = $.trim($("#totalSize").val());
	if(scopeId == 0 || isNaN(totalSize) || totalSize.length == 0)
		return;
	var locationIds;
	if(scopeId == 1) 
	locationIds = $('#districtId').val();
	else if(scopeId == 2)
	locationIds = $('#constituencyId').val();
	
	if(locationIds != null)
	{
    var total =  totalSize / locationIds.length;
	if(total > 0)
	$("#maxIndex").val(Math.round(total));
	}
}
$("#maxIndex").blur(function() {
calculateTotal();
});
$("#totalSize").blur(function() {
calculateMaxIndex();
});

function getDistrictsForRegion()
{
	
	$('#districtId').find('option').remove();
	$("#constituencyId").find('option').remove();
	$('#constituencyId').multiselect('refresh');
	var stateType = $("#regionId option:selected").text();
	var jObj = {
	stateType :stateType,
		task :   "district"
		}
	$.ajax({
          type:'GET',
          url: 'getElectionResultsLocations.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},
		  success: function(result){ 
			$.each(result,function(index,value){
			$('#districtId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		$('#districtId').multiselect('refresh');	  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}
function showTextBox()
{
var radiobtnVal = $('input:radio[name=radiobtn]:checked').val();
if(radiobtnVal == 1)
{
$("#noOfFileDiv").hide();
$('#noOfFileId').val('');
}
else if(radiobtnVal == 2)
	{
	$("#noOfFileDiv").hide();
    $('#noOfFileId').val('');
	}
else
$("#noOfFileDiv").show();
}
function ShowHideForQuestions()
{
var checkedVal = $('input:radio[name=queOpt]:checked').val();
$("#questionID").val(0);
$("#OptionsDiv").html('');
if(checkedVal == 2)
{
$("#radioBtnDiv").hide();
$("#questionDiv").show();
if($("#questionID").val() > 0)
$("#OptionsDiv").show();
}
else
	{
$("#radioBtnDiv").show();
$("#questionDiv").hide();
$("#OptionsDiv").hide();
}
}
function showOptions()
{
$("#OptionsDiv").html('');
$("#OptionsDiv").hide();
var queSize = $("#questionID").val();
if(queSize == 0)
return;
$("#OptionsDiv").show();
var str ='';
str+='<h2>Enter No.of Options(files) for Question</h2>';
var j = 0;
for(var i=0;i<queSize;i++)
	{
	j++;
str+='<div class="span2">';
str+='<span class="clearCls opterror'+j+'"></span>';
str+='<label>Question'+j+'</label>';
str+='<input type="text" class="input-block-level span2 optionsCls" id="option'+j+'">';
str+='</input>';
str+='</div>';
}
$("#OptionsDiv").html(str);
}

$(".QueRadio").live("click",function(){
ShowHideForQuestions();
})
function clearDiv()
{
		$('#districtId').find('option').remove();
		$('#districtId').multiselect('refresh');
		$('#constituencyId').find('option').remove();
		$('#constituencyId').multiselect('refresh');
		$('#parliamentId').find('option').remove();
		$('#parliamentId').multiselect('refresh');
		$('#mandalId').find('option').remove();
		$('#mandalId').multiselect('refresh');
		$('#regionId').val(0);
		$("#maxIndex").val('');
		$("#totalSize").val('');
		$("#scopeId").val(0);
		$( "#queOpt1" ).prop( "checked", true );
		ShowHideForQuestions();
		
}
function openDialog(){
$("#viewCountDialogDiv").dialog({
width:600,
height:500,
modal: true,
resizable: false,

title:"View Counts"
});

var str='';
str+='<div class="row-fluid " style="margin-top: 20px;">';
str+='<div id ="dialogErrorDiv"></div>';

str+='<div class="span3">';
str+='<label>Select Region</label>';
str+='<select id="dialogRegionId" class="input-block-level" onchange="correspondingCall();">';
str+='<option value="0">ALL</option><option value="1">Andhra Pradesh</option><option value="2">Telangana</option>';
str+='</select>';
str+='</div>';
str+='<div class="span3">';
str+='<label>Select Scope</label>';
str+='<select id="dialogScopeId" onchange="showHide();correspondingCall();" class="input-block-level"><option value="0">Select Scope</option><option value="2">District</option><option value="3">Constituency</option><option value="4">Parliament</option><option value="5">Mandal</option>';
str+='</select>';
str+='</div>';
str+='<div class="span3">';
str+='<input class="btn" type="button" value="Submit" style="margin-top: 20px;" onClick="getMobileNumbersCount()"></div>';
str+='<img src="./images/icons/search.gif" id="dialogueAjaxImg" style="display:none"/>';
str+='</div>';
str+='<div id ="dialogDataDiv" style="margin-top:10px;"></div>';
str+='</div>';

$('#viewCountDialogInnerDiv').html(str);
}

function getMobileNumbersCount(){
$("#dialogDataDiv").html('');
var regionId = $("#dialogRegionId").val();
var scopeId = $("#dialogScopeId").val();
var region = $("#dialogRegionId option:selected").text();

if(scopeId == 0)
{
$("#dialogErrorDiv").html("Please Select Scope").css("color","red");
return;
}
$("#dialogErrorDiv").html("");
$("#dialogueAjaxImg").show();
var jsObj=
{
regionType: region,
scopeId:scopeId,
task : "mobileNumbersCount"
};
$.ajax({
type: "GET",
url: "getLocationWiseMobileNosCountAction.action",
dataType: 'json',
data: {task:JSON.stringify(jsObj)},
})
.done(function( result ) {
$("#dialogueAjaxImg").hide();
buildLocationWiseData(result.list,jsObj);
});
}
function buildLocationWiseData(result,jsObj)
{
	var str = '';
	str+='<table class="table table-bordered" id="datatableId">';
	str+='<thead>';
	str+='<tr>';
	if(jsObj.scopeId == 2)
	{
	str+='<th>District</th>';
	str+='<th>Total</th>';
	str+='<th>DistrictWiseCount</th>';
	str+='<th>ConstituencyWiseCount</th>';
	}
	else if(jsObj.scopeId == 3 || jsObj.scopeId == 4)
	{
	str+='<th>Constituency</th>';
	str+='<th>Total</th>';
	str+='<th>ConstituencyWiseCount</th>';
	}
	else if(jsObj.scopeId == 5)
	{
	str+='<th>Mandal</th>';
	str+='<th>Total</th>';
	}
	str+='<th>MandalWiseCount</th>';
	str+='<th>PanchayatWiseCount</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result)
	{
		str+='<tr>';
		str+='<td>'+result[i].name+'</td>';
		var total = result[i].distictWiseCount + result[i].constituencyWiseCount + 
			result[i].tehsilWiseCount + result[i].panchayatWiseCount;
		str+='<td>'+total+'</td>';
		if(jsObj.scopeId == 2)
		{
		str+='<td>'+result[i].distictWiseCount+'</td>';
		str+='<td>'+result[i].constituencyWiseCount+'</td>';
		}
		if(jsObj.scopeId == 3 || jsObj.scopeId == 4)
		{
		str+='<td>'+result[i].constituencyWiseCount+'</td>';
		}
		str+='<td>'+result[i].tehsilWiseCount+'</td>';
		str+='<td>'+result[i].panchayatWiseCount+'</td>';
		str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	$("#dialogDataDiv").html(str);
	$("#datatableId").dataTable();
}

</script>
<script>

correspondingCall(1);
showHide();

</script>
<script>
$(".mytooltip").tooltip();
</script>
</body>
</html>