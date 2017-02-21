<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Constituency Committee Summary </title>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
   <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>

    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>
	    <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<style>
	
	label {
    display: inline-block;
    font-weight: normal !important;
	}

#constiTableId th{
    background-color: #d3d3d3 !important;
}
#constiTableId td{
    background-color: #F3F3F3 !important;
}

.widget
	{
		background-color:rgba(0,0,0,0.1);
		text-align:center;
		padding:10px 0px 10px 0px;
		margin-right:20px;
		border:1px solid #999;
		box-shadow:1px 1px 5px #999;
	}
	.borderbox
	{
		margin-top:10px;
		border:5px solid #CCC;
		border-radius:5px;
	}
	.tablecaption
	{
		background-color:#eee;
		padding-left:5px;
		font-size:16px;
		font-weight:bold;
		color:#ccc;
	}
	</style>
</head>
<body>
<style>
	.locationName{text-transform: uppercase;}
	
</style>

	<script>
		var userAccessType = '${pageAccessType}';
	</script>
<div class="container">
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<span  id="titleId" style="hieght:20px;margin-top:-7px;"></span>
					<select id="tdpCommitteeYearId"  class="form-control pull-right" style="width:150px;margin-top:-7px;">	
						<!--<option value="2">2016 - 2018</option>
						<option value="1">2014 - 2016</option>-->
					</select>
				</h4>
			</div>
			<div class="panel-body">
			   <div class="row m_top20 locationCls" style="display:none;">
				   <div class="col-md-4 col-md-offset-2 col-sm-6 col-xs-6">Select District:<select id="districtsId" class="form-control" onChange="getAllConstituencysForADistrict()"><option value="0">Select District</option></select> </div>
				   <div class="col-md-4  col-sm-6 col-xs-6">Select Constituency:<select id="constituencysId" class="form-control"><option value="0">Select Constituency</option></select> </div>
				</div>
				<c:set var="pageAccess" value="${pageAccessType}"/>
				<c:if test="${pageAccessType == 'ALL' || pageAccessType == 'MP' || fn:containsIgnoreCase(pageAccess, 'District')}">	
				<div class="row m_top20">				
					<img id="imgajax" src="images/icons/search.gif" alt="Processing Image" style="display:none;width:17px;height:11px;"/>
					<div class="col-md-2 col-md-offset-3">
						<label>
							Select State	
						</label>
						<select id="stateId" onchange="getUserAccessInfo();"  class="form-control">	
							<option value="0">All</option> 
							<option value="1"> Andhra Pradesh </option>
							<option value="2"> Telangana </option>
						</select>
					</div>
					<div class="col-md-2">
						<label>Select Constituency</label>
						<select id="userAccessconstituencyId" class="form-control" onchange="reload()"></select>
					</div>
					<div class="col-md-2">
						<label>Select Committee Type</label>
						<select id="committetypeId"  class="form-control" onchange="getRolesBasedReport(0)">	
							<option value="0">All</option> 
							<option value="1"> Main </option>
							<option value="2"> Affiliated </option>
						</select>
					</div>
					<!--<div class="col-md-2">
						<label>Select Enrollment Year</label>
						<select id="tdpCommitteeYearId"  class="form-control">	
							<option value="0">Select Year</option> 
						</select>
					</div>-->
					<div class="col-md-3 col-xs-12 col-sm-3">
						<label>Date</label>
						<div class="input-group">
							  <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
							  </span>
							  <input type="text" class="form-control" id="reportrange"/>
							</div>
					</div>
				</div>	
				
			</c:if>	
			<c:if test="${pageAccessType != 'ALL' && pageAccessType != 'MP' && not fn:containsIgnoreCase(pageAccess, 'District')}">
			<div  class="row m_top20 form-inline">
				<div class="col-md-4 col-md-offset-4">
					<label>Select Committee Type</label>
					<select id="committetypeId"  class="form-control" >	
						<option value="0">All</option> 
						<option value="1"> Main </option>
						<option value="2"> Affiliated </option>
					</select>
				</div>
				<div class="col-md-3 col-xs-12 col-sm-3">
						<label>Date</label>
						<div class="input-group">
							  <span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
							  </span>
							  <input type="text" class="form-control" id="reportrange"/>
						</div>
					</div>
			</div>
			</c:if>
			<div class="row">
				<div class="col-md-12 m_top20 form-inline">
					<div class="pull-right">
						<span class="btn btn-success btn-xs"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></span>&nbsp;&nbsp;
						<span class="btn btn-success btn-xs"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / TOWN / DIVISION</label></span>
					</div>
				</div>
			</div>
			
					<div class="row">
						<div class="col-md-12">
							<div id="constiRoleSummry" style="display:none;">
								<img id="summaryAjaxRole" src="./images/Loading-data.gif" class=" col-sm-offset-4" alt="Processing Image" style="display:none;"/>
							</div>
						</div>
					</div>
					
					<!--<a id="hide" href="javascript:{}" class="pull-right btn btn-warning summaryEventCls" title="Hide Constituency Wise Detailed Overview"> Hide <i class="glyphicon glyphicon-chevron-up"></i></a>
					<a id="show"  href="javascript:{}" class="pull-right btn btn-success summaryEventCls" style="display:none;" title="Show Constituency Wise Detailed Overview"> Show
					<i class="glyphicon glyphicon-chevron-down"></i></a>-->
					<div class="row">
						<div class="col-md-12">
							<div id="constiRoleSummary"></div>
						</div>
					</div>
					
					
					<!--<div class="row m_top20">
						<div class="col-md-4 col-md-offset-3"><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=1 name="reportType" checked="true"> VILLAGE / WARD</label></div>
						<div class="col-md-4 "><label class="radio"><input type="radio" style="vertical-align: text-bottom;" class="reportTypeCls" value=2 name="reportType">MANDAL / TOWN / DIVISION</label></div>
					</div>-->
					
					<span class="btn btn-info pull-right exportToExcel" onclick="exportToExcel()" style="display:none;"> Export To Excel </span>
					<div class="row">
						<div class="col-md-12">
							<div class="panel-group" id="constSummary" role="tablist" aria-multiselectable="true">
								<img id="summaryAjax" src="./images/Loading-data.gif" class="" alt="Processing Image" style="display:none;"/>
							</div>
						</div>
					</div>
					
				</div>

				<div id="dialogSummary" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">

				<div class="modal-dialog modal-lg">
				<div class="modal-content">
					
					 <div class="modal-header">
					  <button aria-label="Close" data-dismiss="modal" class="close" type="button"><span aria-hidden="true">x</span></button>
						<h4 class="modal-header text-center"></h4>
					  </div>
					<div id="cadreDetailsDiv" style="margin-top:25px;padding:10px;"></div>
				</div>


			</div>
		</div>
	</div>
</div>
 

</div>
<!-- Modal for Remove cadre -->
			<div class="modal fade" id="removeModalDivId">
			  <div class="modal-dialog modal-sm">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="removeModalTitleId" style="color:red;">Removing Cadre</h4>
				  </div>
				  <div class="modal-body" id="ramoveModalBodyDivId">
					<div id="errorDivId" style="color:red"></div>
					<div id="successDivId"></div>
					<div class="row">
						<div class="col-md-12">
							<div><b>Cadre Name :</b> <span id="cadreName"></span></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Reason <span style="color:red">*</span>:</b>
								<select id="reasonSelectId" class="form-control">
									<option value="0">Select Reason</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Remark <span style="color:red">*</span>:</b> 
							<textarea class="form-control" id="remarkTextAreaId"></textarea></div>
						</div>
					</div>
					</div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" id="saveRemovingCadreDetailsId">Remove</button>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
				  </div>
				</div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
<!-- Modal for update cadre -->
			<div class="modal fade" id="modalDivId">
			  <div class="modal-dialog">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="modalTitleId" style="color:red;"><span id="modalTitleNameId"></span></h4>
				  </div>
				  
				  <div class="modal-body" id="modalBodyDivId"></div>
				  
				  <div class="modal-footer">
					<span class="pull-left" id="modalSuccessId"></span>
					<span id="modalfooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal" id="updateCadreCls">Close</button>
				  </div>
				  
				</div>
			  </div>
			</div>
			<div class="modal fade" id="upadateCallerModalDivId" style="display:none;">
			  <div class="modal-dialog">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="addModalTitleId" style="color:red;"><span id="addModalId"></span>Update Caller Feedback Details</h4>
				  </div>
				  
				  <div class="modal-body" id="upadateCallerModalBodyId">
					<div class="row">
					<div id="errorUpCallId" style="color:red"></div>
					<input type="hidden" id="hiddenTdpCadreId"/>
						<div class="col-md-4">
						<label>Call Purpose:</label>
						<select class="form-control" id="callPurposeId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Status:</label>
						<select class="form-control" id="callStatusId">
							</select>
						</div>
						<div class="col-md-4">
						<label>Call Response Type:</label>
						<select class="form-control" id="callSupportTypId">
							</select>
						</div>
						<div class="col-md-12">
							<label>Description:</label>
							<textarea id="descriptionId" class="form-control"></textarea>
						</div>
					</div>
				  </div>
				  
				  <div class="modal-footer">
					<span class="pull-left" id="upadateCallerSuccessId"></span>
					<span id="updatefooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" id="closeButtonId" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary btn-sm" id="updateButtonId" onclick="updateCallerFeedBackDetails()">Save</button>
				  </div>
				  
				</div>
			  </div>
</div>
<div class="modal fade" id="myModalForImpCand">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="impCandTitleId"></h4>
      </div>
      <div class="modal-body">
		<span class="btn btn-info pull-right" id="impCandExportId" onclick="impCandExportToExcel()" style="display:none;margin-bottom: 10px;"> Export To Excel </span>
        <div class="" id="impCandBodyId"></div>
			<center><img id="dataLoadingsImgForImpCand" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
	  </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
<script src="js/cadre_response.js/cadre_response.js" type="text/javascript"></script>
<script src="js/cadreCommittee/cadreCommitteeDashboard1.js" type="text/javascript"></script>
<script>
$('#reportrange').val(moment().format("DD/MM/YYYY") +'-'+ moment().format("DD/MM/YYYY"));
$("#reportrange").daterangepicker({
	startDate: moment(),
	endDate: moment(),
	opens: 'left',
	format: 'DD/MM/YYYY',
	ranges: {
	   'Today' : [moment(), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});		var casteArray=new Array();
		
            <c:forEach var="caste" items="${castes}">
				var casteObject = { id:"${caste.id}",name:"${caste.name}" }
				casteArray.push(casteObject);
			</c:forEach>
</script>
<script type="text/javascript">
$(document).on("click","#updateCadreCls",function(){
	setTimeout(function(){
		$("body").addClass("modal-open");
	},500)
	
})

var accessConstituency = '${accessConstituency}';
var accessConstituencyId = '${accessConstituencyId}';

$(document).ready(function(){
	getCadreEnrollmentYearsForSummary();
	 $('.summaryEventCls').click(function(){
		var btnId = $(this).attr('id');
		if(btnId == 'hide')
		{
			$('#show').show();
			$('#hide').hide();
			$('#constiRoleSummary').hide();
			
		}
		else if(btnId == 'show')
		{
			$('#show').hide();
			$('#hide').show();
			$('#constiRoleSummary').show();
		}
	}); 
});
  if(accessConstituencyId !='null' && accessConstituencyId!=""){
		$(".locationCls").hide();
		//getConstituencySummary();
		$("#constSummary").show();
	  
}else{
	getAllDistricts();
}  


function getAllDistricts()
{

 $.ajax({
			type : "GET",
			url : "getAllDistrictsAction.action",
			data : {} ,
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			$("#districtsId  option").remove();
			$("#districtsId").append('<option value="0">Select District</option>');
			if(result!=null){
			for(var i in result){
				   $("#districtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				 }
			}	 
		});
}

 /* $("#constituencysId").change(function(){
	$("#constSummary").show();
	$(".exportToExcel").hide();
	getConstituencySummary();
}); */ 
 $(".reportTypeCls").click(function(){
	getConstituencySummary();
	getRolesBasedReport(0);

	
}); 
$("#committetypeId").change(function(){
	getConstituencySummary();
	getRolesBasedReport(0);
});
 

	
function getAllConstituencysForADistrict()
{
    var districtId = $("#districtsId").val();
	$("#constituencysId  option").remove();
	$("#constituencysId").append('<option value="0">Select Constituency</option>');
	if(districtId==0){
	  return;	 
	}
	
	$("#constSummary").html('');
 
    $.ajax({
			type : "GET",
			url : "getAllConstituencysForADistrictAction.action",
			data : {districtId:districtId} ,
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			if(result!=null){
			   for(var i in result){
				   $("#constituencysId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			    }
			}	 
		});
}

function getConstituencySummary(){
	$("#titleId").html("");
	$("#constSummary").html('<img id="summaryAjax" src="./images/Loading-data.gif" class=" col-sm-offset-4" alt="Processing Image"/>');
	var constiId = "";
	if(accessConstituencyId!=null && accessConstituencyId!=""){
		constiId = accessConstituencyId;
	}else{
		constiId = $("#constituencysId").val();
	}
	
	if(constiId==0){
		$("#constSummary").html('');
		return;	 
	}
	var reportType = $("input[type='radio'][name='reportType']:checked").val();
	var committeeTypeId =$("#committetypeId").val();
	var enrollmentIdsArr = new Array();
	  enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
	   var dates = $("#reportrange").val();
	   var dateArray = dates.split("-");
		var fromDateStr=dateArray[0];
		var toDateStr=dateArray[1];

	var jsObj ={
		 constituencyId:constiId,reportType :reportType ,
		 committeeTypeId:committeeTypeId,
		 enrollmentIdsArr:enrollmentIdsArr,
		 startDate : fromDateStr,
		 endDate : toDateStr
     
	}	
	$.ajax({
		type : "POST",
		url : "getConstituencyCommitteSummary.action",
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
		}
		$(".exportToExcel").show();
		buildConstituencySummary(result,jsObj);
	});
	
}
$(document).on("click",".impCandCls",function(){
	$("#myModalForImpCand").modal("show");
	var location = $(this).attr("attr_location");
	var locationId = $(this).attr("attr_locationId");
	var searchType = $(this).attr("attr_search");
	$("#impCandTitleId").html(location.toUpperCase());
	getImportantCandidateDetails(locationId,searchType);
});

function getImportantCandidateDetails(locationId,searchType){
	$("#impCandBodyId").html('');
	$("#dataLoadingsImgForImpCand").show();
	$("#impCandExportId").hide();
	var jsObj ={
		 locationId:locationId,      
		 searchType:searchType      
	}	
	$.ajax({
		type : "POST",
		url : "getImpCandAndPublRepresentativeDetailsByLocationAction.action",
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null)
			buildImportantCandidateDetails(result);
		else{
			$("#dataLoadingsImgForImpCand").hide();
			$("#impCandBodyId").html(str);
		}
	});
}

function buildImportantCandidateDetails(result){
	var str='';
	
	str+='<table class="table m_0 table-bordered" id="impCandTable">';
		str+='<thead>';
			str+='<th class="text-center"> LEADER </th>';
			str+='<th class="text-center"> POSITION </th>';
			str+='<th class="text-center"> LOCATION </th>';
			str+='<th class="text-center"> MOBILE </th>';
			str+='<th class="text-center"> TYPE </th>';
		str+='</thead>';
		str+='<tbody>';
		if(result.idnameList != null && result.idnameList.length > 0){
			for(var i in result.idnameList){
				str+='<tr>';
					str+='<td>'+result.idnameList[i].name+'</td>';
					str+='<td class="text-center">'+result.idnameList[i].percentage+'</td>';
					str+='<td class="text-center">'+result.idnameList[i].dateStr+'</td>';
					str+='<td class="text-center">'+result.idnameList[i].mobileNo+'</td>';
					str+='<td class="text-center">'+result.idnameList[i].status+'</td>';
				str+='</tr>';
			}
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#impCandExportId").show();
	$("#dataLoadingsImgForImpCand").hide();
	$("#impCandBodyId").html(str);
	$("#impCandTable").dataTable();
}

function buildConstituencySummary(results,jsObj){
$("#titleId").html('');
	var repType = jsObj.reportType;
	
	$("#constSummary").html("");
		var str = '';
	
		if(repType==1){
		
			if((results.mandalsList==null && results.localBodiesList ==null) || (results.mandalsList.length<=0 && results.localBodiesList.length<=0)){
				$("#constSummary").html("<br><h4 style='text-align:center;;color:red'> NO RESULTS TO DISPLAY.</h4>");
				return;
			}
			$("#titleId").append(''+results.accessState+' CONSTITUENCY CADRE COMMITTEE OVERVIEW ');
			if(results.mandalsList!=null && results.mandalsList.length>0){
					for(var i in results.mandalsList){
				var rest = results.mandalsList[i];
				if(rest.locationsList!=null && rest.locationsList.length>0){
					var reqPositionsArray = new Array();
					
					
				
				  str += '<div class="panel panel-default">';
					str += '<div class="panel-heading" role="tab" id="headingOne2'+i+'"><i class="glyphicon glyphicon-user impCandCls pull-right" title="Click here for Important Candidates" style="cursor:pointer;" attr_search="mandal" attr_locationId="'+rest.locationId+'" attr_location="'+rest.locationName+'"></i>';
						str += '<a role="button" data-toggle="collapse" data-parent="#constSummary" href="#collapseOne2'+i+'" aria-expanded="true" aria-controls="collapseOne2'+i+'">';
						  str += '<h4 class="locationName panel-title">'+rest.locationName+'</h4>';
						str += '</a>';
					str += '</div>';
					str += '<div id="collapseOne2'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne2'+i+'">';
					  str += '<div class="panel-body">';
					  str += '<div class="table-responsive">';
						str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
						str+='<thead class="text-center">';
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<th  rowspan=2> Location </th>';

								for(var k in results.resultList){
									if(results.resultList[k].basicCommitteeName == 'Main'){
										if(reqPositionsArray.indexOf(k) == -1){
										  reqPositionsArray.push(k);
										}
										str+='<th colspan=1>'+results.resultList[k].basicCommitteeName+'</th>';
									}else{
										 for(var c in rest.locationsList){
											 if(rest.locationsList[c].resultList[k].membersCount!=null || rest.locationsList[c].resultList[k].electrolsCount!=null){
												 if(reqPositionsArray.indexOf(k) == -1){
													  str+='<th colspan=1>'+results.resultList[k].basicCommitteeName+'</th>';
													  reqPositionsArray.push(k);
													}
											 }
										 }
									}
									
								}
								if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
								{
									var length = (results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList.length -1)*2;
									str+='<th  colspan='+length+'> IVR Details </th>';
								}
								
								
							str+='</tr>';
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								for(var k in reqPositionsArray){
									str+='<th> Members </th>';
									//str+='<th> Electrols </th>';
								}
								if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
								{
									for(var tp in results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList)
									{
										 if(results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].id != 8){
										 str+='<th> '+results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].name+' </th>';
										  str+='<th> % </th>';
										  }
									}
								}
								
							str+='</tr>';
						str+='</thead>';
							str+='<tbody>';
							var percentage = 0;
							var perc = 0;
								for(var j in rest.locationsList){
									str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
										str+='<td>'+rest.locationsList[j].locationName+'</td>';
											for(var k in rest.locationsList[j].resultList){
											  if(reqPositionsArray.indexOf(k) != -1){
												if(rest.locationsList[j].resultList[k].membersCount!=null){
													//str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="members" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
													str+='<td><a href="javascript:{gettingCadreDetails(\'members\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
												}else{
													str+='<td> </td>';
												} 
												
												/* if(rest.locationsList[j].resultList[k].electrolsCount!=null){
													//str+='<td><a id="location'+rest.locationsList[j].locationId+'" class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\',\'electrols\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
													//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="6" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
													 str+='<td><a  href="javascript:{gettingCadreDetails(\'electrols\',6,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

												}else{
													str+='<td class="text-center"> </td>';
												} */
											  }
											}
											if(rest.locationsList[j].cadreIVRVO != null)
											{
												for(var pp in rest.locationsList[j].cadreIVRVO.optionsList)
												{
													if(rest.locationsList[j].cadreIVRVO.optionsList[pp].id != 8)
													{
														if(rest.locationsList[j].cadreIVRVO.total >0)
														{
															percentage = (rest.locationsList[j].cadreIVRVO.optionsList[pp].count *100)/ rest.locationsList[j].cadreIVRVO.total;
															perc = percentage.toFixed(0);
															str+='<td class="text-center">'+rest.locationsList[j].cadreIVRVO.optionsList[pp].count+'</td>';
															str+='<td class="text-center">'+perc+'</td>';
														}				
														else
														{
															str+='<td class="text-center"></td>';
															str+='<td class="text-center"></td>';
														}
													}
												}
											}
											
												
												
											
											
											
											
											
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table> ';
						str += '</div>';
					str += '</div>';
				  str += '</div>';
				str += '</div>';
				}
				
			}
			}
			
			if(results.localBodiesList !=null && results.localBodiesList.length>0){
				for(var i in results.localBodiesList){
				var rest = results.localBodiesList[i];
				var reqPositionsArray = new Array();
				str += '<div class="panel panel-default">';
					str += '<div class="panel-heading" role="tab" id="headingOne1'+i+'"><i class="glyphicon glyphicon-user impCandCls pull-right" title="Click here for Important Candidates" style="cursor:pointer;" attr_search="leb" attr_locationId="'+rest.locationId+'" attr_location="'+rest.locationName+'"></i>';
						str += '<a role="button" data-toggle="collapse" data-parent="#constSummary" href="#collapseOne1'+i+'" aria-expanded="true" aria-controls="collapseOne1'+i+'">';
							str += '<h4 class="locationName panel-title">'+rest.locationName+'</h4></a>'
					str += '</div>';
					str += '<div id="collapseOne1'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne1'+i+'">';
					  str += '<div class="panel-body">';
					  str += '<div class="table-responsive">';
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
				str+='<thead class="text-center">';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th rowspan=2> Location </th>';
						for(var k in results.resultList){
							if(results.resultList[k].basicCommitteeName == 'Main'){
								if(reqPositionsArray.indexOf(k) == -1){
									  reqPositionsArray.push(k);
									}
								str+='<th colspan=1>'+results.resultList[k].basicCommitteeName+'</th>';
							}else{
								 for(var c in rest.locationsList){
									 if(rest.locationsList[c].resultList[k].membersCount!=null || rest.locationsList[c].resultList[k].electrolsCount!=null){
										 if(reqPositionsArray.indexOf(k) == -1){
										      str+='<th colspan=1>'+results.resultList[k].basicCommitteeName+'</th>';
											  reqPositionsArray.push(k);
											}
									 }
								 }
							}
							
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							var length = (results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList.length -1)*2;
							str+='<th  colspan='+length+'> IVR Details </th>';
						}
					str+='</tr>';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						for(var k in reqPositionsArray){
							str+='<th> Members </th>';
							//str+='<th> Electrols </th>';
						}
						if(results.mandalsList[0].locationsList[0].cadreIVRVO != null)
						{
							for(var tp in results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList)
							{
								 if(results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].id != 8){
								 str+='<th> '+results.mandalsList[0].locationsList[0].cadreIVRVO.optionsList[tp].name+' </th>';
								  str+='<th> % </th>';
								  }
							}
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var j in rest.locationsList){
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
								str+='<td>'+rest.locationsList[j].locationName+'</td>';
									for(var k in rest.locationsList[j].resultList){
									  if(reqPositionsArray.indexOf(k) != -1){
										if(rest.locationsList[j].resultList[k].membersCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members">'+rest.locationsList[j].resultList[k].membersCount+'</td>';
										     // str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="members" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';
									        str+='<td><a href="javascript:{gettingCadreDetails(\'members\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].membersCount+'</a></td>';

										}else{
											str+='<td> </td>';
										}
										
										/* if(rest.locationsList[j].resultList[k].electrolsCount!=null){
											//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols">'+rest.locationsList[j].resultList[k].electrolsCount+'</td>';
										    //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.locationsList[j].resultList[k].basicCommitteeTypeId+' attr_locationId='+rest.locationsList[j].locationId+' attr_locationType="8" attr_memType="electrols" id="location'+rest.locationsList[j].locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationsList[j].locationId+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';
										    str+='<td><a href="javascript:{gettingCadreDetails(\'electrols\',8,'+rest.locationsList[j].locationId+','+rest.locationsList[j].resultList[k].basicCommitteeTypeId+',\''+rest.locationsList[j].locationName+'\',\''+rest.locationsList[j].resultList[k].basicCommitteeName+'\');}">'+rest.locationsList[j].resultList[k].electrolsCount+'</a></td>';

										}else{
											str+='<td> </td>';
										} */
									 }	
									}
									if(rest.locationsList[j].cadreIVRVO != null)
									{
										for(var pp in rest.locationsList[j].cadreIVRVO.optionsList1)
										{
											if(rest.locationsList[j].cadreIVRVO.optionsList1[pp].id != 13)
											{
												if(rest.locationsList[j].cadreIVRVO.total >0)
												{
													percentage = (rest.locationsList[j].cadreIVRVO.optionsList1[pp].count *100)/ rest.locationsList[j].cadreIVRVO.total;
													perc = percentage.toFixed(0);
													str+='<td class="text-center">'+rest.locationsList[j].cadreIVRVO.optionsList1[pp].count+'</td>';
													str+='<td class="text-center">'+perc+'</td>';
												}				
												else
												{
													str+='<td class="text-center">0</td>';
													str+='<td class="text-center">0</td>';
												}
											}
										}
									}
									
							str+='</tr>';
						}
					str+='</tbody>';
				str+='</table> ';
				str += '</div>';
			str += '</div>';
		  str += '</div>';
		str += '</div>';
			}
			}
			
			
		}else{
				str += '<div class="panel panel-default">';
					str += '<div class="panel-heading" role="tab" id="headingOne">';
						str += '<a role="button" data-toggle="collapse" data-parent="#constSummary" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne'+i+'">';
							str += '<h4 class="panel-title locationName"> Mandal/Town/Division Wise Committees Summary</h4></a></div>'
				str += '<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">';
				str += '<div class="panel-body">';
				str += '<div class="table-responsive">';
				str+='<table class="table table-yellow-bordered table-condensed " style="width:100%; ">';
				str+='<thead class="text-center">';
					str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
						str+='<th> Location </th>';
						for(var k in results.resultList){
							str+='<th>'+results.resultList[k].basicCommitteeName+'</th>';
						}
					str+='</tr>';
				str+='</thead>';
					str+='<tbody>';
						for(var i in results.mandalsList){
							var rest = results.mandalsList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5">'+rest.resultList[j].membersCount+'</td>';
							        //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="5"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									
									str+='<td class="text-center"><a href="javascript:{gettingCadreDetails(\'members\',5,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+',\''+rest.locationName+'\',\''+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									

								}else{
									str+='<td class="text-center"> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
						for(var i in results.localBodiesList){
							var rest = results.localBodiesList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7">'+rest.resultList[j].membersCount+'</td>';
								   //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="7"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
 								   str+='<td><a href="javascript:{gettingCadreDetails(\'members\',7,'+rest.locationId+',\''+rest.resultList[j].basicCommitteeTypeId+'\',\''+rest.locationName+'\',\''+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
								   }else{
									str+='<td class="text-center"> - </td>';
								}
							
							}
							str+='</tr>';
						}
						for(var i in results.divisionList){
							var rest = results.divisionList[i]
							str+='<tr style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1);">';
							str+='<td>'+rest.locationName+'</td>';
							for(var j in rest.resultList){
								if(rest.resultList[j].membersCount!=null){
									//str+='<td class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11">'+rest.resultList[j].membersCount+'</td>';
									 //str+='<td><a class="lctnCmmty" attr_cmmtyType='+rest.resultList[j].basicCommitteeTypeId+' attr_locationId='+rest.locationId+' attr_locationType="11"  id="location'+rest.locationId+'" href="javascript:{gettingCadreDetails(\'location'+rest.locationId+'\');}">'+rest.resultList[j].membersCount+'</a></td>';
									 //str+='<td><a href="javascript:{gettingCadreDetails(\'members\',9,'+rest.locationId+','+rest.resultList[j].basicCommitteeTypeId+','+rest.locationName+','+rest.resultList[j].basicCommitteeName+');}">'+rest.resultList[j].membersCount+'</a></td>';
									 str+='<td><a href="javascript:{gettingCadreDetails(\'members\',9,'+rest.locationId+',\''+rest.resultList[j].basicCommitteeTypeId+'\',\''+rest.locationName+','+rest.resultList[j].basicCommitteeName+'\');}">'+rest.resultList[j].membersCount+'</a></td>';

								}else{
									str+='<td class="text-center"> - </td>';
								}
								
							}
							str+='</tr>';
						}
						
					str+='</tbody>';
				str+='</table> ';
				str += '</div>';
			str += '</div>';
		  str += '</div>';
		str += '</div>';
			
		}
		
	$("#constSummary").html(str);
}
function gettingCadreDetails(type,locationType,location,basicCmmtyId,locationName,basicCmmtyName){
	
		var dates = $("#reportrange").val();
	   var dateArray = dates.split("-");
		var fromDateStr=dateArray[0];
		var toDateStr=dateArray[1];
		var enrollmentIdsArr = new Array();
		enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
		
		 var jsObj={
		         locationId:location,
				 locationType:locationType,
				 basicCommitteeTypeId:basicCmmtyId,
				 type:type,
				 casteStateId:0,
				 gender:"",
				 fromAge:0,
				 toAge:0,
				 committeeEnrollmentId:enrollmentIdsArr,
				startDate :fromDateStr ,
				endDate : toDateStr
		       };
			   
		 $.ajax({
			type : "GET",
			url : "gettingCadreDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			 buildingResults(result,type,locationName,basicCmmtyName,basicCmmtyId,locationType);
		});
		
	}
function buildingResults(result,type,locationName,basicCmmtyName,basicCmmtyId,locationTypeId)
{
	
  if(result!=null)
  {
    var name = '';
	if(locationName.indexOf("(") != -1){
	 name=(locationName.slice(0,locationName.indexOf("(")));
	 }
	else{
	name=locationName;
	}
	
    if(result.length>0){
    var str='';
	str+='<div>';
	str+='<table class="table">';
str+='<thead>';
str+='<tr>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:200px">';
str+='<thead class="text-center">';
str+='<tr>';
str+='<th style="background-color:#EDEDED;"> Total Candidates : </th>';
str+='<td> '+result[0].total+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:200px">';
str+='<thead class="text-center">';
str+='<tr>';
str+='<th  style="background-color:#EDEDED;"> Male Candidates : </th>';
str+='<td> '+result[0].maleCount+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='<td>';
str+='<span class="">';
str+='<table class="table table-bordered" style="width:250px">';
str+='<thead class="text-center">';
str+='<tr>';
str+='<th  style="background-color:#EDEDED;"> Female Candidates : </th>';
str+='<td> '+result[0].femaleCount+' </td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
str+='</span>';
str+='</td>';
str+='</tr>';
str+='</thead>';
str+='</table>';
	str+='</div>';
	//console.log(userAccessType);
	if(userAccessType != null && userAccessType == 'All') 
	{
		str += '<div>';
		str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
		str += '<caption class="tablecaption" >Caste Category Wise Information</caption>';
		str += '<thead class="text-center">';
		str += '<tr>';
		str += '<th width="25%" class="text-center">Caste Category</th>';
		str += '<th width="25%" class="text-center">Total Count</th>';
		str += '<th width="25%" class="text-center">Male</th>';
		str += '<th width="25%" class="text-center">Female</th>';
		str += '</tr>';
		str += '</thead>';
		for(var pr in result[0].casteGroupVO)
		{
			str += ' <tr>';
			str += '<td class="text-center">'+result[0].casteGroupVO[pr].castName+'</td>';
			str += '<td class="text-center">'+result[0].casteGroupVO[pr].casteId+'</td>';
			str += '<td class="text-center">'+result[0].casteGroupVO[pr].stateId+'</td>';
			str += '<td class="text-center">'+result[0].casteGroupVO[pr].castStateId+'</td>';
			str += '</tr>';
		}
		

		str += '</table>';
		str += '</div>';
		
	}
	
	str += '<div>';
	str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
	str += '<caption class="tablecaption">Age Range Wise Information';
	str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
	str += '<thead class="text-center">';
	str += '<tr>';
	str += ' <th width="25%" class="text-center">Between Age</th>';
	str += ' <th width="25%" class="text-center">Total Count</th>';
	str += ' <th width="25%" class="text-center">Male</th>';
	str += ' <th width="25%" class="text-center">Female</th>';
	str += '</tr>';
	str += '</thead>';
	for(var tp in result[0].ageDetailsIfoVO)
	{
		str += ' <tr>';
		str += '<td class="text-center">'+result[0].ageDetailsIfoVO[tp].castName+'</td>';
		str += '<td class="text-center">'+result[0].ageDetailsIfoVO[tp].casteId+'</td>';
		str += '<td class="text-center">'+result[0].ageDetailsIfoVO[tp].stateId+'</td>';
		str += '<td class="text-center">'+result[0].ageDetailsIfoVO[tp].castStateId+'</td>';
		str += '</tr>';
	}

	str += ' </table> ';
	str += '</div>';
	str+='<span onclick="exportToExcelForMembers()" class="btn btn-info pull-right excelId form-inline" style="display: block;"> Export To Excel </span>';
	str+='<table class="table table-bordered" id="constiTableId">';
	str+='<thead>';
	//if(basicCmmtyId == 1)
		//str+='<th  style="width: 178px; padding-left: 34px;">Designation</th>';
	
	str+='<th style="width:50px;"> </th>';
	str+='<th style="padding-left: 72px;"> MEMBER </th>';
	//str+='<th style="padding-left: 19px;">MemberShipNo</th>';
	str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
	str+='<th style="padding-left: 19px;"> AGE </th>';
	str+='<th style="padding-left: 19px;"> GENDER </th>';
	str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
	//str+='<th style="padding-left: 19px;"> Caller FeedBack</th>';
	str+='</thead>';
    for(var i in result){
	 str+='<tr>';
	/* if(basicCmmtyId == 1){
	 if(result[i].role!=null){
		str+='<td  style="padding-top: 14px; padding-left: 37px;">'+result[i].role+'</td>';
	 }else{
		str+='<td  style="padding-top: 14px; padding-left: 37px;">  </td>';
	 }
	 }*/
	 str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
	
	 str+=' </td>';
	 str+='<td> '+result[i].name+' ';
	 <c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
	/* str+='<div id="rc'+result[i].id+'" class="pull-right cadreRemoveCls" style="margin-left:3px;cursor:pointer;" attr_cadre_id='+result[i].id+' attr_cadre_name ="'+result[i].name+'"><i class="glyphicon glyphicon-remove remove-icon" data-toggle="tooltip" data-placement="bottom" title="Remove Cadre"></i></div>';
	 str+='<i class="glyphicon glyphicon-edit remove-icon" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;" title="Update Cadre MobileNo And Caste"></i>';*/
	str+='<div id="uc'+result[i].id+'" class="pull-right updateCadreClass" style="margin-left:3px;cursor:pointer;" attr_cadre_id='+result[i].id+' attr_mobile_no ="'+result[i].mobileNo+'" attr_caste_name ="'+result[i].casteName+'" attr_cadre_name ="'+result[i].name+'"><i class="glyphicon glyphicon-edit remove-icon" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;" title="Update Cadre MobileNo And Caste"></i></div>';
	 </c:if>
	  if(basicCmmtyId == 1){
	 if(result[i].role!=null){
		 str+='<br>'+result[i].role+'';
	 }else{
		 str+='';
	 }
	 }	 
	 
	  str+=' </td>';	  
	// str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
	 //str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
		 if(result[i].mobileNo != null)
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
	else
	 str+='<td></td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
	// str+='<td ><div class="updateBtnCls" attr_tdp_cadre_id="'+result[i].id+'"><!--<input type="button"  id="updateBtnId"/>--><i class="glyphicon glyphicon-ok pull-right" style="padding:4px;border-radius:50%;background:#ccc;color:#FFFFFF;color:green;right:25px;cursor:pointer"></i></div></td>';
	 str+='</tr>';
	}
   str+='</tbody>';
   str+='</table>';
   
   
   // For Export To Excel
   
   
   str+='<table class="table table-bordered" id="constiTableId1" style="display:none;">';
	str+='<thead>';
	
	
	str+='<th style="padding-left: 72px;"> MEMBER </th>';
	str+='<th style="padding-left: 72px;"> Role </th>';
	str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
	str+='<th style="padding-left: 19px;"> AGE </th>';
	str+='<th style="padding-left: 19px;"> GENDER </th>';
	str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
	str+='</thead>';
    for(var i in result){
	 str+='<tr>';
	
	 str+='<td> '+result[i].name+'';
	   str+=' </td>';
	  if(basicCmmtyId == 1){
	 if(result[i].role!=null){
		 str+=' <td> '+result[i].role+'</td>';
	 }else{
		 str+='<td></td>';
	 }
	 }	 
		  
	// str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
	 //str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
	 if(result[i].mobileNo != null)
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
 else
	 str+='<td></td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
	 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
	 str+='</tr>';
	}
   str+='</tbody>';
   str+='</table>';
   
	str+=' <div class="modal-footer" style="margin-top: 50px;">';
	str+=' <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>';
	str+='</div>';
	
   $("#cadreDetailsDiv").html(str);
   
   var areaType = "";
   if(locationTypeId != 8)
   {
	   areaType = "Village Committee Members";
   }
   //$('#dialogSummary').find('h3').html('<span>'+ capitalize(name)+' '+areaType+' '+ basicCmmtyName +' '+ capitalize(type) +' </span>');
   $('#dialogSummary').find('h4').html('<span>'+ capitalize(name)+' '+areaType+' ('+ basicCmmtyName +') </span>');
   $("#dialogSummary").modal("show");
  
  } 
  else{
        $("#cadreDetailsDiv").html("No Data Available.");
	}
	
	/*$("#constiTableId").dataTable({
			"iDisplayLength": 20,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
	*/
}
}	
function capitalize(str) {

   // return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
   var words = str.split(" ");
   var arr = Array();
   for (i in words)
   {
      temp = words[i].toLowerCase();
      temp = temp.charAt(0).toUpperCase() + temp.substring(1);
      arr.push(temp);
   }
   return arr.join(" ");
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
	  tableToExcel('constSummary', 'Constituency Committee Summary');
}
function exportToExcelForMembers()
{
	  tableToExcel('constiTableId1', 'Constituency Committee Summary');
}
function impCandExportToExcel()
{
	  tableToExcel('impCandTable', 'Important Candidates');
}

function getUserAccessInfo()
{
	$("#imgajax").show();
	var stateId = 0;
	$('#userAccessconstituencyId').find('option').remove();
	
	if ($('#stateId').length != null && $('#stateId').length != 0)
	 stateId = $("#stateId").val();
	var enrollmentIdsArr = new Array();
	enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
	var jObj ={
		stateId:stateId,
		enrollmentIdsArr:enrollmentIdsArr,
		task:"getConstituencies"
	}
	$.ajax({
		type: "GET",
		url: "getUserAccessAction.action",
		dataType: 'json',
		data: {task:JSON.stringify(jObj)},
		})
		.done(function( result ) {
			$("#imgajax").hide();
	$.each(result.hamletVoterInfo,function(index,value){
		$('#userAccessconstituencyId').append('<option value="'+value.id+'">'+value.name+'</option>');
	});
	$("#userAccessconstituencyId option[value="+accessConstituencyId+"]").attr('selected', 'selected');
});	

 
}
function reload() {
	accessConstituencyId = $("#userAccessconstituencyId").val();
	 window.location.href ='constituencyCommitteeSummaryAction.action?accessConstituencyId='+accessConstituencyId+'','location=no','_blank'; 
    
}

function getRolesBasedReport(roleId)
	{
		$('#constiRoleSummary').html("");
		$("#constiRoleSummry").show();
		var rolesArr = new Array();
		var casteCategoryArr = new Array();
		var casteCategoryGroupArr = new Array();
		var casteIdsArr = new Array();
		
		var locationLevelId =3;
		var committeeTypeId =$("#committetypeId").val();
		var userAccessType ="Constituency";
		var castePercentage =5;
		var searchTypeId =0;
		var selectedRadio = $("input[type='radio'][name='reportType']:checked").val();
		if(roleId == 0)
		{
			rolesArr.push(1);
			rolesArr.push(2);
			rolesArr.push(3);
			rolesArr.push(4);
			rolesArr.push(5);
			rolesArr.push(6);
			rolesArr.push(7);
			rolesArr.push(8);
			rolesArr.push(9);
		}
		else
		{
			rolesArr.push(roleId);
		}
		var enrollmentIdsArr = new Array();
		enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
		var dates = $("#reportrange").val();
	   var dateArray = dates.split("-");
		var fromDateStr=dateArray[0];
		var toDateStr=dateArray[1];
		
		var jObj = {
			rolesArr : rolesArr,
			casteCategoryArr : casteCategoryArr,
			casteCategoryGroupArr : casteCategoryGroupArr,
			casteIdsArr : casteIdsArr,
			locationLevelId: locationLevelId,
			committeeTypeId : committeeTypeId,
			userAccessType : userAccessType,
			castePercentage:'${accessConstituencyId}',
			searchTypeId:searchTypeId,
			selectedRadio:selectedRadio,
			enrollmentIdsArr:enrollmentIdsArr,
			startDate :fromDateStr ,
			endDate : toDateStr
		};
		$.ajax({
          type:'GET',
          url: 'getCommitteeRolesBasedReport.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			  $("#constiRoleSummry").hide();
			if(result != null)
			{
				buildRoleWiseDetailsInfo(result);
			}
			else{
				$('#constiRoleSummary').html('No Data Available');
			}
		});
	}
	
	function buildRoleWiseDetailsInfo(result)
	{
		$("#constiRoleSummary").html('<img id="summaryAjax" src="./images/Loading-data.gif" class=" col-sm-offset-4" alt="Processing Image"/>');
		var totalResult = result.cadreCommitteeRolesInfoVOList;
			var str='';
		if(totalResult != null)
		{
			for(var i in totalResult)
			{
				str+='<div class="col-md-8 col-md-offset-2" style="margin-top:10px;font-size: 16px;">';
				str+='<table class="table table-bordered text-center" style="background:#eee">';
					str+='<tr class="text-center">';
						str+='<td style="width:33.3%"><h4>Total Candidates</h4><h3>'+totalResult[i].totalCount+'</h3></td>';
						str+='<td style="width:33.3%"><h4>Male</h4><h3>'+totalResult[i].maleCount+'</h3></td>';
						var femaleCount = totalResult[i].femaleCount != null?totalResult[i].femaleCount:0;
						str+='<td style="width:33.3%"><h4>Female</h4><h3>'+femaleCount+'</h3></td>';
					str+='</tr>';
				str+='</table>';
				/*str+='<div class="col-md-4" >';
				str+='<div class="col-md-5" style="padding:5px;"></div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">'+totalResult[i].totalCount+'</div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-male"></i>';
				str+=' Candidates</div>';
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block"></div>';
				str+='</div>';
				str+='<div class="col-md-4" style="border-left:1px solid #FFF" >';
				str+='<div class="col-md-5" style="padding:0px;">';
				str+='<i class="icon-female"></i>';
				str+=' Candidates</div>';
				
				str+='<div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block"></div>';
				str+='</div>';*/
				str+='</div>';
			}			
		}
		
			str+='<div class="col-md-10 col-md-offset-1" style="margin-top:20px;">';
			str+='<div class="table-responsive">';
		var casteCategoryResult = result.casteCategoryWiseList;
		if(casteCategoryResult != null)
		{
			if(userAccessType != null && userAccessType == 'All')
			{
				str+='<table class="table table-bordered" style="border:2px solid #ddd !important;margin-bottom:35px" id="casteCategoryId">';
				str+='<caption class="tablecaption" style="background:#CCC;color:#666;">Caste Category Wise Information</caption>';
				str+='<thead class="text-center">';
				str+='<tr>';
				str+='<th width="25%">Caste Category</th>';
				str+='<th width="25%">Total Count</th>';
				str+='<th width="25%">Male</th>';
				str+='<th width="25%">Female</th>';
				str+='</tr>';
				str+='</thead>';
				for(var i in casteCategoryResult)
				{
					str+='<tr>';
					str+='<td>'+casteCategoryResult[i].casteCategory+'</td>';
					str+='<td>'+casteCategoryResult[i].totalCount+'</td>';
					str+='<td>'+casteCategoryResult[i].maleCount+'</td>';
					str+='<td>'+casteCategoryResult[i].femaleCount+'</td>';
					str+='</tr>';
				}
				str+='</table>';	
			}				
		}
			
		var casteWiseResult = result.casteWiseList;
		if(casteWiseResult != null)
		{
			if(userAccessType != null && userAccessType == 'All')
			{
				str+='<table class="table table-bordered" style="border:2px solid #eee !important" id="casteDetailsId">';
					str+='<caption class="tablecaption" style="background:#CCC;color:#666">Caste Wise Information</caption>';
				str+='<thead class="text-center">';
				str+='<tr>';
				str+='<th width="25%">Caste Name</th>';
				str+='<th width="25%">Total Count</th>';
				str+='<th width="25%">Male</th>';
				str+='<th width="25%">Female</th>';
				str+='</tr>';
				str+='</thead>';
				for(var i in casteWiseResult)
				{
					str+='<tr>';
					str+='<td>'+casteWiseResult[i].caste+'</td>';
						str+='<td>'+casteWiseResult[i].totalCount+'</td>';
						str+='<td>'+casteWiseResult[i].maleCount+'</td>';
						str+='<td>'+casteWiseResult[i].femaleCount+'</td>';
					str+='</tr>';
				}
				str+='</table>    ';
			}
		}	
		
		var ageRangeWiseResult = result.ageRangeWiseList;
		if(ageRangeWiseResult != null)
		{
			str+='<table class="table table-bordered text-center" style="border:2px solid #eee !important" id="ageRangeID">';
			str+='<caption class="tablecaption" style="background:#CCC;color:#666">Age Range Wise  Information</caption>';
			str+='<thead class="text-center">';
			str+='<tr>';
			str+='<th width="25%">Between Age</th>';
			str+='<th width="25%">Total Count</th>';
			str+='<th width="25%">Male</th>';
			str+='<th width="25%">Female</th>';
			str+='</tr>';
			str+='</thead>';
			for(var i in ageRangeWiseResult)
			{
				str+='<tr>';
					str+='<td class="text-center">'+ageRangeWiseResult[i].ageRange+'</td>';
					str+='<td class="text-center">'+ageRangeWiseResult[i].totalCount+'</td>';
					str+='<td class="text-center">'+ageRangeWiseResult[i].maleCount+'</td>';
					str+='<td class="text-center">'+ageRangeWiseResult[i].femaleCount+'</td>';
				str+='</tr>';
			}
		}
			str+='</table> ';
			str+='</div>  '; 
			str+='</div>';

		$('#constiRoleSummary').html(str);
		
		
		$("#casteDetailsId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#casteCategoryId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$("#ageRangeID").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [[ 1, "desc" ]],
			"aLengthMenu": [[15,30, 50, 100, -1], [15,30, 50, 100, "All"]]
		});
		
		$('#casteCategoryId_length,#ageRangeID_length').hide();
		$('#casteCategoryId_filter,#ageRangeID_filter').hide();
		$('#casteCategoryId_info,#ageRangeID_info').hide();
		$('#casteCategoryId_paginate,#ageRangeID_paginate').hide();
		
		$('#casteDetailsId_info').css('margin-bottom','35px');
	}
	  $(document).on("click",".updateCadreClass",function(){
	  $("#modalSuccessId").html('');
	  buildModal(this);
  });
    function buildModal(cadreDetails){
	    
		$("#modalTitleNameId").html('');
	    $("#modalBodyDivId").html('');
		$("#modalfooterNameId").html('');
		
		$("#modalTitleNameId").html('Update Cadre Mobile And Caste.');
		$("#modalfooterNameId").html('<button type="button" id="updatingCadreId" attr_cadre_id='+$(cadreDetails).attr("attr_cadre_id")+' class="btn btn-primary btn-sm">Update</button>');
		
	    var str='';
		str+='<div class="row">';
		str+='<div class="col-md-12">';
		str+='<div><b>Cadre Name :</b> <span>'+$(cadreDetails).attr("attr_cadre_name")+'</span></div>';
		str+='</div>';
		str+='</div>';

		str+='<div class="row">';
		str+='<div class="col-md-6 m_top10">';
		str+='<div><b>Mobile NO : <span style="color:red">*</span></b> ';
		str+='<input class="form-control" id="updateCadreMobileId" maxlength="10" value="'+$(cadreDetails).attr("attr_mobile_no")+'"></input></div>';
		str+='<div id="updateErrorMobileId" style="color:red;"></div>';
		str+='</div>';
		str+='<div class="col-md-6 m_top10">';
		str+='<div><b>Caste <span style="color:red">*</span>:</b>';
		    str+='<select id="updateCadreCasteSelectId" class="form-control">';
		        str+='<option value="0">Select Caste</option>';
				for(var i in casteArray){
					if(casteArray[i].name== $(cadreDetails).attr("attr_caste_name")){
						str+='<option value="'+casteArray[i].id+'" selected>'+casteArray[i].name+'</option>';
					}else{
						str+='<option value="'+casteArray[i].id+'">'+casteArray[i].name+'</option>';
					}	
				}
		    str+='</select>';
		str+='</div>';
		str+='<div id="updateErrorCasteId" style="color:red;"></div>';
		str+='</div>';
		
		str+='</div>';
		
	  $("#modalBodyDivId").html(str);
	  $("#modalDivId").modal("show");
  }
  
    $(document).on("click","#updatingCadreId",function(){
	  
	  $("#updateErrorMobileId").html('');
	  $("#updateErrorCasteId").html('');
	   
	  var tdpCadreId=$(this).attr("attr_cadre_id");
	  var mobileNo=$('#updateCadreMobileId').val();
      var casteId=$('#updateCadreCasteSelectId option:selected').val();
      var casteName=$('#updateCadreCasteSelectId option:selected').text();
	  
      if(isNaN(mobileNo) || mobileNo.indexOf(" ") != -1){
		 $("#updateErrorMobileId").html("Please Enter Numbers Only...");
		 return;
	  }
      if(mobileNo.trim().length < 10){
		 $("#updateErrorMobileId").html("Please Enter Valid Mobile Number...");
		 return;
      }
	  if(casteId==0){
		  $("#updateErrorCasteId").html("Please Select Caste.");
		  return;
	  }
	  if(!confirmDelete("Are you sure you want to Update Cadre ?")){
		 return;
	 }
	 $("#modalSuccessId").html("<span style='color:green;'>Please Wait ,While updating...</span>");
	 
	  var jObj={
		         tdpCadreId:tdpCadreId,
		         mobileNo:mobileNo,
				 casteId:casteId
		       };
	  $.ajax({
		  type:'POST',
		  url: 'updateMobileNumberAndCasteForCadreAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				if(result != null){
					
					if(result.resultCode == 0){
					   $("#modalSuccessId").html("<span style='color:green;'>MobileNo And Caste Updated Successfully.</span>");
					   
					   //Refreshing data.
					   $("#uc"+tdpCadreId).attr('attr_mobile_no',mobileNo);
					   $("#uc"+tdpCadreId).attr('attr_caste_name',casteName);
					   
					   $("#mobile"+tdpCadreId).html(mobileNo);
					   $("#caste"+tdpCadreId).html(casteName);
					   
					   setTimeout(function(){
				            $("#modalDivId").modal("hide");
				       }, 3000);
					}else if(result.resultCode == 1){
						$("#modalSuccessId").html("<span style='color:red;'>Sorry,MobileNo And Caste Are Not Updated.</span>");
					}
				}
		  });
  });
  
    $(document).on("click",".cadreRemoveCls",function(){
	  
	  var tdpCadreId = $(this).attr("attr_cadre_id");
	  var cadreName= $(this).attr("attr_cadre_name");
	  
	  $("#hiddenCadreId").val(tdpCadreId);
	  $("#cadreName").html(cadreName);
	  
	  $("#errorDivId").html("");
	  $("#successDivId").html("");
	   $("#remarkTextAreaId").val("");
	  
	   getAllCadreDeleteReasons();
	  
	$("#removeModalDivId").modal("show");
  });
   function getAllCadreDeleteReasons(){
	  $("#reasonSelectId option").remove();
	  
	  $("#reasonSelectId").append('<option value="0">Select Reason</option>');
	  
	  $.ajax({
          type:'GET',
          url: 'getAllCadreDeleteReasonsAction.action',
          dataType: 'json',
		  data: {}
	   }).done(function(result){
		   
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#reasonSelectId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		  
	   });
  }
    function confirmDelete(msg){
	var deleteCadre = confirm(msg);
	  if (deleteCadre)
		  return true;
	  else
		return false;
  }
    $(document).on("click","#saveRemovingCadreDetailsId",function(){
	  saveRemovingCadreDetails();
  });
  
  function saveRemovingCadreDetails(){
	  
	  var errorExist = false;
	  
	  var cadreId = $("#hiddenCadreId").val();
	  var reasonId = $("#reasonSelectId option:selected").val();
	  var reason = $("#reasonSelectId option:selected").text();
	  var remarkTxt = $("#remarkTextAreaId").val();
	  
	  if(reasonId != null && (reasonId == 0 || reasonId == undefined)){
		  $("#errorDivId").html("Please Select Reason");
		  errorExist=true;
	  }
	 else if(remarkTxt !=null && remarkTxt.trim().length ==0){
		  $("#errorDivId").html("Please Enter Remark");
		errorExist=true;
	 }
	 if(errorExist){
		 return;
	 }
	 
	 if(!confirmDelete("Are you sure you want to delete cadre ?")){
		 return;
	 }
	
	  var jsObj = {
		  cadreId : cadreId,
		  reasonId : reasonId,
		  remarkTxt : remarkTxt
	  }
	   $.ajax({
          type:'GET',
          url: 'saveRemovingCadreDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result.resultCode == 0){
				$("#successDivId").html("<span style='color:green;'>Cadre Removed Successfully.</span>");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
				$('#rc'+cadreId).remove();
				
				$("#main"+cadreId).css({"background":"rgba(255, 0, 0, 0.1)","padding":"5px","border-bottom":"1px solid rgb(51, 51, 51)"});
				
				$("#delete"+cadreId).append('<b style="color:red;">Deleted Reason</b> : '+reason);
				
			}
			else{
				$("#errorDivId").html("Cadre Not Removed Successfully.");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
			}
		});
	  
	  
  }
	getRolesBasedReport(0);
	
 $(document).on("change","#tdpCommitteeYearId",function(){
	getCommitteeDetailsByEnrollementForConSummary();
});
function getCommitteeDetailsByEnrollementForConSummary(){
	  var enrollmentIdsArr = new Array();
	  enrollmentIdsArr.push($("#tdpCommitteeYearId").val());
	var jsObj={
			enrollmentIdsArr:enrollmentIdsArr
		};
			   
		 $.ajax({
			type : "GET",
			url : "getCommitteeDetailsByEnrollementIdAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					 var fYear = result[i].fromDate.split('-')[0];
					 var fMonth = result[i].fromDate.split('-')[1];
					 var fDate = result[i].fromDate.split('-')[2];
					 var tYear = result[i].toDate.split('-')[0];
					 var tMonth = result[i].toDate.split('-')[1];
					 var tDate = result[i].toDate.split('-')[2].substring(0,2);
					$('#reportrange').data('daterangepicker').setStartDate(fDate+'/'+fMonth+'/'+fYear);
					$('#reportrange').data('daterangepicker').setEndDate(tDate+'/'+tMonth+'/'+tYear);
					}
				}
				getConstituencySummary();
				getRolesBasedReport(0);
			});
	}
$(document).on("change","#userAccessconstituencyId",function(){
	getConstituencySummary();
	getRolesBasedReport(0);
});	
function getCadreEnrollmentYearsForSummary(){
		 var jsObj={
		
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getCadreEnrollmentYearsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$("#tdpCommitteeYearId").append('<option value='+result[i].id+'>'+result[i].electionYear+'</option>');
				}
			}
			
			getUserAccessInfo();
			getRolesBasedReport(0);
			getConstituencySummary();
			getCommitteeDetailsByEnrollementForConSummary();
			
		});
	}
</script>
<script>
<c:set var="pageAccess" value="${pageAccessType}"/>
<c:if test="${pageAccessType == 'ALL' || pageAccessType == 'MP' || fn:contains(pageAccess, 'District')}">
		getUserAccessInfo();
</c:if>

</script>
</body>
</html>