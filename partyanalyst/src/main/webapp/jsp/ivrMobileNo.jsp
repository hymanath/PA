<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
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

  });
</script>
<div class="container "><div style="margin-top:20px;" class="widget ">
<h2>Mobile Numbers Extraction For IVR</h2>
   <div id="errorDiv"></div>
    <div class="row-fluid " style="margin-top: 20px;">
	 <div class="span2">
    <label>Select Level</label>
    <select id="scopeId" onchange="showHide();correspondingCall();" class="input-block-level"><option value="0">Select Scope</option><option value="1">District</option>
	<option value="2">Constituency</option>
	</select>
	</div>
<div class="span3">
    <label>Select Region</label>
    <select id="regionId" class="input-block-level" onchange="getDistrictsForRegion();">
	<option value="0">ALL</option>
	<option value="1">Andhra</option>
	<option value="2">Telangana</option>
	</select>
	</div>

<div class="span3">
    <label>Select District</label>
    <select id="districtId"  multiple="true" onchange="getConstituencies();calculateTotal();" class="input-block-level">
	</select>
	</div>
<div class="span3" id="constituencyDiv">
	<label>Select Constituency</label>
    <select id="constituencyId" multiple="true" class="input-block-level" onchange="calculateTotal();">
	</select>
	</div>

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


<div class="span4"  style="margin-top: 26px;">
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

<div class="span4" style="margin-top:26px;">
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
	getDistrictsForRegion();
	if(scopeId == 2) 
	getConstituencies();

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
	if(scopeId == 0 || scopeId == 1)
		return;
	if(districtIds == null)
		return;
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

function createFile()
{

	$("#downloadLink").css("display","none");
	$("#loactionCountDiv").html('');
	var flag = false;
	var locationIds ;
	var scopeId = $("#scopeId").val();
	var checkedTypeVal = $('input:radio[name=radiobtn]:checked').val();
	var noOfFiles = $.trim($('#noOfFileId').val());
	if($.trim($('#noOfFileId').val()).length == 0)
	noOfFiles = 0;
	if(scopeId == 1) 
	locationIds = $('#districtId').val();
	else
	locationIds = $('#constituencyId').val();
	
	var fileFormat = $('input:radio[name=optionsRadios]:checked').val();
	var maxIndex = $.trim($('#maxIndex').val());
	if(maxIndex.length == 0)
	 maxIndex = 0;
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
		else
		{
		if($('#districtId').val() == null)
		str+='Select District<br/>';
		str+='Select Constituency';
		}
		flag =true;
	}
	else if(isNaN(maxIndex))
	{
		str+='Enter number';
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
	str+='</font>';
	$("#errorDiv").html(str);
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
	else
	str+='<h3>Constituency wise mobile Numbers Info</h3>';

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
	$("#constituencyDiv").hide();
else if(scopeId == 2)
$("#constituencyDiv").show();
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