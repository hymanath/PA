<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Membership Cards Print Status Report </title>


    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<script src="js/icheck/icheck.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

		<script type="text/javascript" src="js/exportexcel.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.f-16{font-size: 16px;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}

		#searchCadreTab  thead th {
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 15px !important;
		}

	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}

	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	.offset1 {
	    margin-left: 70px;
	}
	.span10 {
	    width: 840px;
	}

	#dayWiseUsersDetailsId  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
	#errorDiv
	{
			color:#ff0020;
	}	
	#statedisplaydivid,#trackingStatesDispalyMainDiv,#distdisplaydivid,#constdisplaydivid,#parlConstdisplaydivid{display:none;}
	#statedisplaydivid1,#distdisplaydivid1,#constdisplaydivid1{display:none;}
	#statedisplaydivid2,#distdisplaydivid2,#constdisplaydivid2,#parlConstdisplaydivid2{display:none;}
	</style>
   
   
</head>


<body  class="bgc">
		<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
	<div class="container " id="dashboadElmnt">	

		<div id="locationWiseCadreInfoDiv">
		    <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="offset2 text-uppercase"> Membership Cards Printing Status Report 
					<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<a class="btn btn-success" style="float: right;margin-right:25px;" href="cadreDashBoardAction.action"> home </a>					
					</c:if> 
					</h3>
				</div>
			</div>
			

			<div id="usersWorkingStatusDiv">
			
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
					 <tr>
					   <td><b>Select Scope : </b></td>
					   <td>
						 <c:if test="${sessionScope.USER.accessType == 'STATE'}">
							<select id="selLctnType" onchange="selectLocation(this.value)">						 
							<!-- <option value="0">All</option>   -->
							<option value="1">State</option>
							<option value="2">District</option>
							<option value="3">Constituency</option>
							 <option value="4">Parliament</option> 
							</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
							<select id="selLctnType" onChange="selectLocationByAccess(this.value)">	
								<option value="2">District</option>
								<option value="3">Constituency</option>
								<option value="4">Parliament</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'MP'}">
								<select id="selLctnType">	
								
								<option value="4">Parliament</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'MLA'}">
								<select id="selLctnType">	
								<option value="3">Constituency</option>			
								</select>
							</c:if>
						</td>
					 </tr>
				     <tr id="statedisplaydivid">
						<td><b>Select State</b></td>
						<td>
						 <c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="statesDivId">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						  <select id="statesDivId">
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						  <select id="statesDivId">
							<option value="1">AndhraPradesh</option>
						  </select>
						  </c:if>
						</td>
				     </tr>
				   <tr id="distdisplaydivid">
					   <td><b>Select District : </b></td>
					   <td><select id="displaydistbox"></select></td>
				   </tr>
				   <tr id="constdisplaydivid">
					   <td><b>Select constituency : </b></td>
					   <td><select id="displayconstbox"></select></td>
				   </tr>
				   <tr id="parlConstdisplaydivid">
					   <td><b>Select Parliament : </b></td>
					   <td><select id="displayParlConstbox"></select></td>
				   </tr>
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDateId"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDateId" /></td></tr>
				
				   <tr>
				      <td></td><td>
					  <a href="javascript:{generateDetailReports();}" class="btn btn-success col-xs-offset-4 border-radius-0 m_top20"> Get Report </a>
					  <a id="excelBtnId" href="javascript:{exportExcelREport('dayWiseUsersDetailsIdForExcel');}" class="btn btn-success col-xs-offset-4 border-radius-0  " style="display:none;margin-top: 19px;"> Export Excel </a>
				
				<div><img src='images/Loading-data.gif' class="offset5"  id="searchDashboardImg" style="width:70px;height:60px;display:none;"/>
				
					  </td>
				  </tr>
			</table>
					<div id="reportsStatusDiv" style="padding: 10px;overflow: scroll;display:none;"></div>
					<div id="reportsStatusExcelDiv" style="display:none;"></div>
			  </div>
			</div>
		</div>
		</div>
	</div>
		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					TDP Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
<script>
var accessType = "${sessionScope.USER.accessType}";
	var accessValue = "${sessionScope.USER.accessValue}";
	var accessState = "${sessionScope.USER.stateName}"
	$(document).ready(function(){
		$('#locationsDispalyId').val(1);
	});
	
	<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">	
	selectLocationByAccess(2);
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MLA'}">	
	selectLocationByAccess(3);
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MP'}">	
	selectLocationByAccess(4);
	</c:if>
	getLocationNameByAccessValues(accessType,accessValue);

function exportExcelREport(tableId)
{
		tableToExcel(tableId, 'Membership Cards Print Status Report');
}
function selectLocationByAccess(value){
		if(value==2){
			
			$("#statedisplaydivid").show();
			$("#distdisplaydivid").show();
			$("#constdisplaydivid").hide();
			$("#parlConstdisplaydivid").hide();
			$("#distdisplaydivid2").show();
			$("#constdisplaydivid2").hide();
			$("#parlConstdisplaydivid2").hide();
			getLocationNameByAccessValues(value,"DISTRICT");
		}
		if(value==3){
			$("#statedisplaydivid").show();		
			$("#distdisplaydivid").hide();
			$("#constdisplaydivid").show();
			$("#parlConstdisplaydivid").hide();
			$("#distdisplaydivid2").hide();
			$("#constdisplaydivid2").show();
			$("#parlConstdisplaydivid2").hide();
			getAssemblyParlConstituencies(accessValue,"Assembly");
		}
		if(value==4){
			$("#statedisplaydivid").show();
			$("#distdisplaydivid").hide();
			$("#constdisplaydivid").hide();
			$("#parlConstdisplaydivid").show();
			$("#distdisplaydivid2").hide();
			$("#constdisplaydivid2").hide();
			$("#parlConstdisplaydivid2").show();
			getAssemblyParlConstituencies(accessValue,"Parliament");
		}	
  }
function getAssemblyParlConstituencies(districtId,type){

		var str='';
		var jsObj={
			mainUserLocationId:districtId,
			reportLevel:type
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubUserAccessValueAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
				for(var i in result)
				{
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				if(type=="Parliament"){				
					$("#displayParlConstbox").html(str);
					$("#displayParlConstbox2").html(str);
					$("#trackingParlDispalyId").html(str);
				}	
				if(type=="Assembly"){
					$("#displayconstbox").html(str);
					$("#displayconstbox2").html(str);
					$("#trackingConstituencyDispalyId").html(str);
					$("#constituencyDispalyId").html(str);
					
				}		
	   });		
	}
	function getLocationNameByAccessValues(type,value){

	  var str='';
		var jsObj={
			usersType:type,
			areaType:value
		}
		$.ajax({
			  type:'GET',
			  url: 'getLocationNameByIdAndTypeAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
			if(type == 'DISTRICT'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displaydistbox").html(str);
				
			}
			if(type == 'MLA'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displayconstbox").html(str);
				
			}
			if(type == 'MP'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displayParlConstbox").html(str);
				
			}
	   });	
	
	
	}
	
$(document).ready(function(){
	  $("#fromDateId,#toDateId").datepicker({
		dateFormat: "dd-mm-yy",
		maxDate: new Date()
	 });
	$("#fromDateId,#toDateId").datepicker("setDate", new Date());	

	$("#statesDivId").change(function(){
			var lctnType = $("#selLctnType").val();
			if(lctnType == 3){
				getConstituenciesUWS();
				$("#distdisplaydivid").hide();
			}
			if(lctnType ==2) {
				getdistrictsUWS();
				$("#constdisplaydivid").hide();
			}
			if(lctnType ==4) {
				getparliamentConstituencies('displayParlConstbox');
			}
		});	
});

	function getReqIds(id){
    var selectedId = $("#"+id).val();
	if(selectedId == null){
	  return "";
	}
	if(selectedId == 0){
	 selectedId = "";
	 var options = $('#'+id+' option');
	 var values = $.map(options ,function(option) {
        return option.value;
     });
	  for(var i in values){
	    if(values[i] != 0){
		   if(selectedId.length > 0){
		     selectedId=selectedId+','+values[i];
		   }else{
		     selectedId = values[i];
		   }
		}
	  }
	}
	return selectedId;
  }
function generateDetailReports()
{		
$('#reportsStatusDiv').hide();
$('#excelBtnId').hide();
	var locationType = $("#selLctnType").val();
	var stateTyleId = $("#statesDivId").val();
	var searchType = "constituency";
	var constituencyIds;
	if(locationType==0){
		searchType = "constituency";
		constituencyIds=0;
	}
	if(locationType==1){
		searchType = "state";
		constituencyIds=getReqIds("statesDivId");
	}
	if(locationType==2){
		searchType = "district";
		constituencyIds=getReqIds("displaydistbox");
	}
	if(locationType==3){
	searchType = "constituency";
	constituencyIds=getReqIds("displayconstbox");
	}
	if(locationType==4){
	searchType = "parliament";
	constituencyIds=getReqIds("displayParlConstbox");
	}
	
	$('#reportsStatusDiv').html('');
	$('#reportsStatusExcelDiv').html('');
	var startDate = $('#fromDateId').val();
	var endDate = $('#toDateId').val();
	$("#errorDiv").html("");
	var district= $("#districtsList option:selected").val();
	
	var isError = '';
	
	if(district == 0)
	{
		isError = 'error';
			$("#errorDiv").html("Please Select District.");
	}
		
	if(startDate != null && endDate != null)
	{
		if((startDate != null && startDate.trim().length >0) && (endDate != null && endDate.trim().length >0))
		{		
			var arrr = startDate.split("-");
				var fromyear=arrr[2];
				var frommonth=arrr[1];
				var fromDat=arrr[0];
		   var arr = endDate.split("-");
				var toyear=arr[2];
				var tomonth=arr[1];
				var toDat=arr[0];

				if(fromyear>toyear){
				isError = 'error';
					$('#errorDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
				}
			
				if(frommonth>tomonth){
					   if(fromyear == toyear){
					  isError = 'error';
						$('#errorDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
					}
					
				}
				if(fromDat>toDat){
					if(frommonth == tomonth && fromyear == toyear){		
									
						$('#errorDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  isError = 'error';		
					   }
				}
		}
			
	}

		if(isError.length == 0)
		{			
			$('#searchDashboardImg').show();
			var jsObj =
			{ 		
				 constituencyIds : constituencyIds,
				 stateTyleId : stateTyleId,
				 searchType : searchType,
				 fromDate : startDate,
				 toDate   : endDate,
				 task: "printingStatus"
			};
			$.ajax({
			type: "POST",
			url: "getMemberShipCardDetailsAction.action",
			data: {task : JSON.stringify(jsObj)}
			})
			.done(function( result ) {
			$('#searchDashboardImg').hide();
			$('#reportsStatusDiv').show();
				if(result != null &&  ( result.datesList != null && result.datesList.length > 0 ))
				{
				$('#excelBtnId').show();
					$('#reportsStatusDiv').css("overflow","scroll").css("padding","10px;");;
					buildCategoeryDetails(result,locationType);
					buildForExcelSheet(result,locationType);
				}
				else
				{
					$('#reportsStatusDiv').removeAttr("style");
					$('#reportsStatusDiv').html('<span class="span12 offset5 m_top20" style="font-weight:bold;"> No Data Avaialable...</span>');
				}
				
			 });
			
		}
	}

	function buildCategoeryDetails(result,locationType)
	{
		var str ='';
		str+='<h4 align="center" >  DAY WISE PRINTING REPORT </h4>';
		str+='<div class="span12  m_top20" style="font-weight:bold;margin-bottom: 30px;">';
		str+='<div class="span3 show-grid" style="background-color:#D3D3D3;padding:5px;"> Total Registered :'+result.totalPushCount+' </div> ';
		str+='<div class="span3 show-grid offset1" style="background-color:#D3D3D3;padding:5px;"> Cards Printed :'+result.printStatusCount+' </div> ';
		str+='<div class="span3 show-grid offset1" style="background-color:#D3D3D3;padding:5px;"> Errors  :'+result.errorStatusCount+' </div> ';
		str+='</div><br></br>';
			str+='<table id="dayWiseUsersDetailsId" class="table table-bordered ">';
			str+='<thead>';
			str+='<tr>';
			str+='<th> Date </th>';
			str+='<th> District  </th>';
			if(locationType !=2)
			{
				str+='<th> Parliament </th>';
				str+='<th> Constituency </th>';
			}			
			
			str+='<th> Total Cards </th>';
			for(var i in result.datesList)
			{
				str+='<th COLSPAN="2"> '+result.datesList[i]+' </th>';
			}
			str+='</tr>';

			str+='<tr>';
			str+='<th></th>';
			str+='<th> </th>';
			if(locationType !=2)
			{
				str+='<th></th>';
				str+='<th></th>';
			}
			
			str+='<th></th>';
			for(var i in result.datesList)
			{
				str+='<th> Printed </th>';
				str+='<th> Errors </th>';
			}
			
			str+='</tr>';
			
			str+='</thead>';
			
			str+='<tbody>';
			
			if(result.zebraPrintDetailsVOList != null && result.zebraPrintDetailsVOList.length > 0)
			{
				for(var i in result.zebraPrintDetailsVOList)
				{
				str+='<tr>';
				str+='<td> '+result.zebraPrintDetailsVOList[i].dataPushDate+'</td>';
				str+='<td> '+result.zebraPrintDetailsVOList[i].district+'</td>';
				if(locationType !=2)
				{
					str+='<td> '+result.zebraPrintDetailsVOList[i].parliament+'</td>';				
					str+='<td> '+result.zebraPrintDetailsVOList[i].name+'</td>';
				}
				str+='<td> '+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
				
				if(result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList != null && result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList.length >0)
				{
					for(var j in result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList)
					{
						str+='<td> '+result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList[j].printStatusCount+'</td>';
						str+='<td> '+result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList[j].errorStatusCount+'</td>';
					}
				}
				
				str+='</tr>';
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$('#reportsStatusDiv').html(str);
		    $('#dayWiseUsersDetailsId').dataTable({
				  "aaSorting": [[ 0, "desc" ]],
			      "iDisplayLength": 20,
			      "aLengthMenu": [[20,50, 100, 200, -1], [20,50, 100, 200, "All"]]
		         });
	}

	function buildForExcelSheet(result,locationType)
	{
		var str ='';			
			str+='<h4 align="center" >  DAY WISE PRINTING REPORT </h4>';
			str+='<table id="dayWiseUsersDetailsIdForExcel" class="table table-bordered ">';
			str+='<thead>';
			str+='<tr>';
			//str+='<th> Date </th>';
			str+='<th> District  </th>';
			if(locationType !=2)
			{
				str+='<th> Parliament </th>';
				str+='<th> Constituency </th>';
			}			
			
			str+='<th> Total Cards </th>';
			for(var i in result.datesList)
			{
				str+='<th COLSPAN="2"> '+result.datesList[i]+' </th>';
			}
			str+='</tr>';

			str+='<tr>';
			//str+='<th></th>';
			str+='<th> </th>';
			if(locationType !=2)
			{
				str+='<th></th>';
				str+='<th></th>';
			}
			
			str+='<th></th>';
			for(var i in result.datesList)
			{
				str+='<th> Printed </th>';
				str+='<th> Errors </th>';
			}
			
			str+='</tr>';
			
			str+='</thead>';
			
			str+='<tbody>';
			
			if(result.zebraPrintDetailsVOList != null && result.zebraPrintDetailsVOList.length > 0)
			{
				for(var i in result.zebraPrintDetailsVOList)
				{
				str+='<tr>';
			//	str+='<td> '+result.zebraPrintDetailsVOList[i].dataPushDate+'</td>';
				str+='<td> '+result.zebraPrintDetailsVOList[i].district+'</td>';
				if(locationType !=2)
				{
					str+='<td> '+result.zebraPrintDetailsVOList[i].parliament+'</td>';				
					str+='<td> '+result.zebraPrintDetailsVOList[i].name+'</td>';
				}
				str+='<td> '+result.zebraPrintDetailsVOList[i].totalPushCount+'</td>';
				
				if(result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList != null && result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList.length >0)
				{
					for(var j in result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList)
					{
						str+='<td> '+result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList[j].printStatusCount+'</td>';
						str+='<td> '+result.zebraPrintDetailsVOList[i].zebraPrintDetailsVOList[j].errorStatusCount+'</td>';
					}
				}
				
				str+='</tr>';
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$('#reportsStatusExcelDiv').html(str);
	}
	
 function selectLocation(value){
	if(value==0){
		$("#statedisplaydivid").hide();
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
	}
	if(value==1){
		$("#statedisplaydivid").show();	
		$('#statesDivId').val('0');
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
	}
	if(value==2){
		$("#statedisplaydivid").show();	
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getdistrictsUWS();
	}
	if(value==3){
		$("#statedisplaydivid").show();
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getConstituenciesUWS();
	}
		if(value==4){
		$("#statedisplaydivid").show();
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getparliamentConstituencies('displayParlConstbox');
	}
  }
  
  
	function getparliamentConstituencies(divId)
	{
		var stateTypeId = $('#statesDivId').val();
		$("#Pconstdisplaydivid").show();
		var jsObj = {
		searchType :"parliament",
		stateTypeId : stateTypeId,
		tesk:"locationWiseTransactionReport"
		}
	$("#parlConstdisplaydivid").show();
	$('#'+divId+'').find('option').remove();
	$('#'+divId+'').append('<option value="0"> All </option>');

		$.ajax({
		type : "POST",
		url : "getParliamentsForStateAction.action",
		data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){

			var constiArr = new Array();
			if(result != null && result.length >0)
			{
				for(var i in result)
				{
					if(constiArr.indexOf(result[i].id) <0)
					{
						$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						constiArr.push(result[i].id);
					}
					
				}
			}
		});
	}
	
	function getConstituenciesUWS(){
		var selState = $("#statesDivId").val();
		$("#displayconstbox").html("");
		
		var jObj ={
			stateid:selState,				  
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox").html(str);
			$("#constdisplaydivid").show();
		});
	}
	
  
  function getdistrictsUWS(){
	var selState = $("#statesDivId").val();
	
	$("#displaydistbox").html("");
	
	
		var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displaydistbox").html(str);
			$("#distdisplaydivid").show();
	   });	
	
  }
  
</script>	
	
</body>
</html>