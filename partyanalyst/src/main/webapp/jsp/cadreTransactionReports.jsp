<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Cadre Transactions Details </title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/exportexcel.js"></script>
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
	 .bgc{background-color:#3598db;  font-family: "Helvetica";}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	
		#daywiseReportTab  thead th ,#locationWiseReportTab  thead th{
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 15px !important;
		}
		.survey_nav{height:38px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:38px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
		
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
		
	<div class="container m_top10">
        <div class="row-fluid" style="margin-top:10px;">
		  <div class="survey_nav">
			<ul class="inline ">
				<li><a onclick="showHideTabs(this.id);" id="userReportTab" class="highlight selected ">Day Wise Transaction Report </a></li>
                <li><a onclick="showHideTabs(this.id);" id="locationReportTab" class="highlight " >Location Wise Transaction Report </a></li>
			</ul>
		  </div>
		</div>
		
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> Day Wise Transaction Details </h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
				<label style="margin-left:100px;"><b>From Date :</b>&nbsp;<input type="text" readonly="readonly" id="fromDate"/></label>
					 <label><b>To Date   :</b>&nbsp;<input type="text" readonly="readonly" id="toDate" /> </label>
					<input type="button" class="btn btn-success" id="getCandidateDataCollectionInfoId" onclick="daywiseReport();" value="Submit"/>
					<input type="button" class="btn btn-success" style="display:none;" id="exportExclId1" onclick="generateExcel('daywiseReportTab');" value="Excel Excel"/>
					
					<img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					<div id="daywiseReportDiv"  align="center" style="margin:20px;"></div>
			  </div>
			</div>
		</div>
		<div id="locationWiseCadreInfoDiv"  style="display:none;">
		    <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Location Wise Transaction Details </h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="errMsgDiv"></div>
				  <table  style="margin-left: 270px;">
				     <tr>
					   <td><b>Select Report Level :</b></td>
					   <td><select id="locationsDispalyId" onchange="showCorrespondingLocs(this.value);">
							 <option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Parliament Constituency</option>
					       </select> 
					   </td>
				     </tr>
				     <tr id="statesDispalyMainDiv">
						 <td><b>Select State :</b></td>
						 <td>  
						   <select id="statesDispalyId" onchange="getLocationDetailsForState(this.value);">
							 <option value="0">All</option>
							 <option value="1">Andhra Pradesh</option>
							 <option value="2">Telangana</option>
						   </select> 
						 </td>
					 </tr>
					 <tr id="districtsDispalyMainDiv" style="display:none;">
						 <td><b>Select District :</b></td>
						 <td>  
						   <select id="districtsDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="constituencyDispalyMainDiv" style="display:none;">
						 <td><b>Select Constituency :</b></td>
						 <td>  
						   <div id="constituencySelectDIV"><select id="constituencyDispalyId">
						   </select></div>
						 </td>
					 </tr>
					 <tr id="mandalDispalyMainDiv" style="display:none;">
						 <td><b>Select Parliament :</b></td>
						 <td>  
						   <select id="mandalDispalyId">
						   </select> 
						 </td>
					 </tr>
					 
					  <tr id="formDateDiv" >
						 <td><b>From Date :</b></td>
						 <td>  
						   <input type="text" id="fromDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					  <tr id="toDateDiv">
						 <td><b>To Date :</b></td>
						 <td>  
						    <input type="text" id="toDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls "  placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					 
					 <tr>
					 <td></td>
					 <td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="locationWiseTransactionReport();" value="Submit"/>
					  <input style="display:none;margin-top: 10px;" type="button" id="exportExclId2" class="btn btn-success" onclick="generateExcel('locationWiseReportTab');" value="Export Excel"/>
					 <img id="ajaxImgStyleNew" style="display:none;margin-left:10px; margin-top:10px;" src="images/icons/search.gif"/></td>
					 </tr>
				  </table>
				  <div id="locationWiseTransactionDiv" align="center" style="margin:20px;"></div>
			  </div>
		   </div>
		</div>
	</div>
		
	</div>
	
		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		
	<script>
	$(document).ready(function(){
	showHideTabs("userReportTab");
		//daywiseReport();
		//locationWiseTransactionReport();
		 $("#fromDate,#fromDate1,#toDate,#toDate1").datepicker({
			dateFormat: "yy-mm-dd",
			maxDate: new Date()
		})
		$("#fromDate,#fromDate1,#toDate,#toDate1").datepicker("setDate", new Date());
		
	});	
	
	function getLocationDetailsForState()
	{
	  var locationLvl = $('#locationsDispalyId').val();
	  var divId = '';
	  var stateId =  $('#statesDispalyId').val();
	  var dataType = '';
	  var selectOption = '';
	 
	  if(locationLvl == 3)
	  {
		divId = 'districtsDispalyId';
		dataType = "district";
		selectOption = 'Select District ';
	  }
	  else if(locationLvl == 4)
	  {
	  	divId = 'constituencyDispalyId';
		dataType = "assembly";
		selectOption = 'Select Constituency ';
	  }
	  else if(locationLvl == 5)
	  {
		divId = 'mandalDispalyId';	
		dataType = "parliament";
		selectOption = 'Select Parliament ';
	  }
	  
	  if(locationLvl != 2)
	  {	 
		  var jsObj = {
				searchType :dataType,
				stateTypeId : stateId,
				tesk:"locationWiseTransactionReport"            
		   }
	  
			$.ajax({
				type : "POST",
				url : "getParliamentsForStateAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> '+selectOption+' </option>');
				if(result != null && result.length >0)
				{
					for(var i in result)
					{
						$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
					}
				}
			});
		}
	}
	function showCorrespondingLocs(locationLvl)
	{
	  $("#statesDispalyMainDiv").show();
	  $("#statesDispalyId").val(0);
	  
	  if(locationLvl == 2)
	  {
		$("#constituencyDispalyMainDiv").hide();
		$("#districtsDispalyMainDiv").hide();
		$("#mandalDispalyMainDiv").hide();
	  }
	  if(locationLvl == 3)
	  {
		$("#districtsDispalyMainDiv").show();
		$("#districtsDispalyId").val(0);
		$("#constituencyDispalyMainDiv").hide();
		$("#mandalDispalyMainDiv").hide();
		
	  }else if(locationLvl == 4)
	  {
		$("#districtsDispalyMainDiv").hide();
		$("#constituencyDispalyMainDiv").show();
		$("#mandalDispalyMainDiv").hide();
	  }
	 else if(locationLvl == 5)
	  {
		$("#mandalDispalyMainDiv").show();
		$("#districtsDispalyMainDiv").hide();
		$("#constituencyDispalyMainDiv").hide();		
	  }
	getLocationDetailsForState();
   }
	
	function locationWiseTransactionReport()
	{	
	var locationIdsArr = new Array();	
	$('#exportExclId2').hide();
	$('#locationWiseTransactionDiv').html('');
	$('#ajaxImgStyleNew').show();
	
	var fromDate = $("#fromDate1").val();
	var toDate = $("#toDate1").val();
	
	 var locationLvl = $('#locationsDispalyId').val();
	 var dataType = '';
	  
	  if(locationLvl == 2)
	  {	 
		dataType = "state";
		var locationId = $('#statesDispalyId').val();
		locationIdsArr.push(locationId);
	  }
	  else if(locationLvl == 3)
	  {
		dataType = "district";
		var locationId = $('#districtsDispalyId').val();
		locationIdsArr.push(locationId);
	  }
	  else if(locationLvl == 4)
	  {
		dataType = "assembly";
		var locationId = $('#constituencyDispalyId').val();
		locationIdsArr.push(locationId);
	  }
	  else if(locationLvl == 5)
	  {
		dataType = "parliament";
		var locationId = $('#mandalDispalyId').val();
		locationIdsArr.push(locationId);
	  }  
	  
		var jsObj = {
			fromDate:fromDate,
			toDate : toDate,
			searchType :dataType,
			locationIds : locationIdsArr,
			tesk:"locationWiseTransactionReport"            
	   }	
		$.ajax({
			type : "POST",
			url : "locationWiseTransactionReportAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$('#ajaxImgStyleNew').hide();
			if(result !=null && result.surveyTransactionVOList != null && result.surveyTransactionVOList.length >0)
			{
				buildlocationWiseTransactionReports(result);	
			}
			else
			{
				$('#locationWiseTransactionDiv').html('<span class="span12" style="font-weight:bold;" align="center"> No Data Available...</span>');
			}			
		});			
	}
	
	function buildlocationWiseTransactionReports(result)
	{
		var str ='';
		str+='<h4 align="center"> Location Wise Transaction Report </h4>';
		str +='<table class="table table-bordered " id="locationWiseReportTab">';
		str +='<thead>';
		str +='<tr>';
		str +='<th> User Name </th>';
		str +='<th> Constituency </th>';
		str +='<th> No of Days Worked </th>';
		str +='<th> No of OTP Payments Done </th>';
		str +='<th> No of records collected As per live  </th>';
		str +='<th> No of records collected As per OTP Recieved  </th>';
		str +='<th> Payments Recieved  </th>';
		str +='</tr>';
		str +='</thead>';
		str +='<tbody>';
		
		for(var i in result.surveyTransactionVOList)
		{
			str +='<tr>';
			str +='<td>'+result.surveyTransactionVOList[i].name+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].locationName+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].totalCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].submittedCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].recordsCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].otpConfirmCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].depositedAmount+'</td>';
			str +='</tr>';		
		}
		str +='</tbody>';
		str +='</table>';
		$('#locationWiseTransactionDiv').html(str);
		$('#locationWiseReportTab').dataTable({});
		$('#exportExclId2').show();
	}
	function daywiseReport()
	{	
	$('#daywiseReportDiv').html('');
	$('#exportExclId1').hide();
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	
		var jsObj = {
		fromDate:fromDate,
		toDate : toDate,
		 tesk:"daywiseTransactionReports"            
	   }	
		$.ajax({
			type : "POST",
			url : "daywiseTransactionReportsAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result !=null && result.surveyTransactionVOList != null && result.surveyTransactionVOList.length >0)
			{
				buildDadyWiseTransactionReport(result);	
			}
			else
			{
				$('#daywiseReportDiv').html(' <span class="span12" style="font-weight:bold;" align="center">No Data Available...</span>');
			}
			
		});
			
	}
	
	function buildDadyWiseTransactionReport(result)
	{
		var str ='';
		//str+='<h4 align="center"> Day wise Transaction Report </h4>';
		str +='<table class="table table-bordered " id="daywiseReportTab">';
		str +='<thead>';
		str +='<tr>';
		str +='<th> Date </th>';
		str +='<th> Team Size </th>';
		str +='<th> Submitted Records </th>';
		str +='<th> Team OTP Payment </th>';
		str +='<th> Amount Recieved  </th>';
		str +='</tr>';
		str +='</thead>';
		str +='<tbody>';
		
		for(var i in result.surveyTransactionVOList)
		{
			str +='<tr>';
			str +='<td>'+result.surveyTransactionVOList[i].surveyDate+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].teamSize+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].submittedCount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].actualAmount+'</td>';
			str +='<td>'+result.surveyTransactionVOList[i].depositedAmount+'</td>';
			str +='</tr>';		
		}
		str +='</tbody>';
		str +='</table>';
		$('#daywiseReportDiv').html(str);
		$('#daywiseReportTab').dataTable({});
		$('#exportExclId1').show();
	}
	
	function getReportsForCadre()
	{
		window.open('cadreTransactionReportsAction.action');
	}
	function showHideTabs(id){
     $("#userReportTab").removeClass("selected");
	 $("#locationReportTab").removeClass("selected");

	 $("#userStatusDialogDIV").html("");
	 $("#locationStatusDialogDIV").html("");
     if(id == "userReportTab")
	 {
       $("#userReportTab").addClass("selected");
	   $("#locationWiseCadreInfoDiv").hide();
	   $("#usersWorkingStatusDiv").show();
	 }else
	 {
       $("#locationReportTab").addClass("selected");
	   $("#usersWorkingStatusDiv").hide();
	   $("#locationWiseCadreInfoDiv").show();
	   
	 }
  }
  function generateExcel(reqId){
     tableToExcel(reqId, 'Transaction Details');
   }
   
	</script>

  </body>
</html>