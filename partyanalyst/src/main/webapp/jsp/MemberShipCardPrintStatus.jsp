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
					<h3 class="offset2 text-uppercase"> Membership Cards Printing Status Report  </h3>
				</div>
			</div>
			

			<div class="row-fluid show-grid">
			<div id="errorDiv" style="" class="offset4"></div><br>
			   <div class="row-fluid offset4">						
					<div class="span8" style="margin-bottom:-20px;">
					<h5 class="text-align1" style="margin-left: -8px;">  State : 
					<select id="statesList" onChange="getdistricts(this.value);"> 
					<option value="0"> All </option>
					<option value="1"> Andhra Pradesh </option>
					<option value="2"> Telangana </option>
					
					</select></h5>
					</div>	
				</div>
				<div class="row-fluid offset4">						
					<div class="span8">
						<h5 class="text-align1" style="margin-left: -22px;">  District : 
					<select id="districtsList"  onChange="getConstituencies(this.value)"> 
						<option value="0"> Select District </option>							
					</select></h5>
					</div>
				</div>
				<div class="row-fluid offset4">						
					<div class="span8">
						<h5 class="text-align1" style="margin-left: -65px;margin-top:-10px">  Constituency : 
					<select id="constiList"> 
						<option value="0"> Select Constituency </option>							
					</select></h5>
					</div>
				</div>
				<div class="row-fluid offset4">						
					<div class="span8"  style="margin-top: -20px">
						<h5 class="text-align1" style="margin-left: -45px;">  From Date : 
					    <input type="text" id="fromDateId" class="datePickerCls" placeholder="From Date"  readOnly="true" style="cursor:text;"></input></h5>
					</div>
				</div>
				<div class="row-fluid offset4">						
					<div class="span8"  style="margin-top: -20px;margin-left: 20px;">
						<h5 class="text-align1" style="margin-left: -45px;">  To Date : 
					<input type="text" id="toDateId" class="datePickerCls" placeholder="To Date"  readOnly="true" style="cursor:text;"></input></h5>
					</div>
				</div>
				<a href="javascript:{generateDetailReports();}" class="btn btn-success col-xs-offset-4 border-radius-0 offset5  m_top20"> Submit <span class="glyphicon glyphicon-chevron-right"></span></a>
				
				<div><img src='images/Loading-data.gif' class="offset5"  id="searchDashboardImg" style="width:70px;height:60px;display:none;"/>
				</div>
				<div id="reportsStatusDiv" style="padding: 10px;"></div>
						
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


$(document).ready(function(){
	  $("#fromDateId,#toDateId").datepicker({
		dateFormat: "dd-mm-yy",
		maxDate: new Date()
	 });
	$("#fromDateId,#toDateId").datepicker("setDate", new Date());	 
});
	function getdistricts(id){	
	
	var str ='';
		var jsObj={
			stateid:id
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
	   $("#districtsList").html("");
			str +='<option value="0"> Select District </option>';
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#districtsList").html(str);
			
	   });	
	
  }



 function getConstituencies(districtId){
	
		var str ="";
		var jObj ={
			districtIds:districtId,				  
			task:"getConstituencyNames"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstituencyDetailsInDistrictsAtion.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			$("#constiList").html("");
			$('#constiList').find('option').remove();
			$('#constiList').append('<option value="0"> All </option>')
			if(result.surveyTransactionVOList != null && result.surveyTransactionVOList.length)
			{
				for(var i in result.surveyTransactionVOList)
				{
					$('#constiList').append('<option value='+result.surveyTransactionVOList[i].id+'>'+result.surveyTransactionVOList[i].name+'</option>');
				}			
			}
		   
			// $('#constiList').multiselect('refresh');
			  
		});
	}
	
	function getConstituencyValues(stateId){
	if(stateId == 1)	
		districtId = 11;
	else
		districtId = 1;
		
		var str ="";
		var jObj ={
			districtId:districtId,				  
			task:"getConstituencyNames"             
		}	
		$.ajax({
			type : "POST",
			url : "sendUpdatesByemailsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
				$('#constiList').find('option').remove();
		   for(var i in result){
		   
				$('#constiList').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}			
			 $('#constiList').multiselect('refresh');
			  
		});
	}
	
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
function generateDetailReports(){		

	//var constituencyIds = $("#constiList").val();
	
	var constituencyIds=getReqIds("constiList");
	
	$('#reportsStatusDiv').html('');
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
				 district:district,	 
				 constituencyIds : constituencyIds,
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
				if(result != null )
				{
					buildCategoeryDetails(result);
				}
				else
				{
					$('#reportsStatusDiv').html('<span class="span12 offset4"> No Data Avaialable...</span>');
				}
				
			 });
			
		}
	}

	function buildCategoeryDetails(result)
	{
		var str ='';			
			str+='<h4 align="center" > PRINTING DAY WISE REPORT </h4>';
			str+='<table id="dayWiseUsersDetailsId" class="table table-bordered ">';
			str+='<thead>';
			str+='<tr>';
			str+='<th> Date </th>';
			str+='<th> District  </th>';
			str+='<th> Parliament </th>';
			str+='<th> Constituency </th>';
			str+='<th> Total Cards </th>';
			str+='<th> Print Completed </th>';
			str+='<th> Errors in Printing </th>';
			str+='</tr>';
			str+='</thead>';
			
			str+='<tbody>';
			if(result.surveyTransactionVOList != null && result.surveyTransactionVOList.length > 0)
			{
				for(var i in result.surveyTransactionVOList)
				{
				str+='<tr>';
				str+='<td> '+result.surveyTransactionVOList[i].surveyDate+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].name+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].parliamentName+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].locationName+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].totalCount+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].submittedCount+'</td>';
				str+='<td> '+result.surveyTransactionVOList[i].notSubmittedCount+'</td>';
				str+='</tr>';
				}
			}
			str+='</tbody>';
			str+='</table>';
			
			$('#reportsStatusDiv').html(str);
		$('#dayWiseUsersDetailsId').dataTable({
				  "aaSorting": [[ 0, "desc" ]],
			      "iDisplayLength": 10,
			      "aLengthMenu": [[10,20,50, 100, 200, -1], [10,20,50, 100, 200, "All"]]
		         });
	}
getdistricts(0);
//getConstituencies(11);
//$('#constiList').multiselect({noneSelectedText:"Select Constittuency(s)"});
</script>	
	
</body>
</html>