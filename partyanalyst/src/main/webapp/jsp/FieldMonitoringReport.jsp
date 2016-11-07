<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FIELD MONITORING REPORT</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.bg_ff
{
	background-color:#fff
}
</style>
</head>
<body>
<div class="container m_top20">
  <h4></h4>
  <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading bg_ff">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6 m_top10">
							<h4>FIELD MONITORING REPORT</h4>
						</div>
						<!--<div class="col-md-3 col-xs-12 col-sm-3 pull-right">
							<div class="input-group">
								<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</span>
								<input type="text" class="form-control" id="singleDate"/>
							</div>
						</div>-->
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<!--<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="btn-group pull-right">
							  <button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Export to PDF/Excel <span class="caret"></span>
							  </button>
							  <ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
							  </ul>
							</div>
						</div>-->
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="fieldUserDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
							<div id="errorDivId"></div>
							<div id="fieldUserDetailsId"></div>
						</div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="dataCollectorsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
							<div id="errorDivId"></div>
							<div id="dataCollectorsDivId"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>


<script type="text/javascript">
$("#singleDate").daterangepicker({
	singleDatePicker : true,
	maxDate:moment()
})
getFieldMonitoringReport();

function getFieldMonitoringReport(){
	$("#fieldUserDetailsImgId").show();
	$("#fieldUserDetailsId").html("");
	
	var jsObj = { 
	}
	$.ajax({
		type : 'GET',
		url : 'getFieldMonitoringUserWiseDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildFieldMonitoringReport(result);
		}
		else{
			$("#fieldUserDetailsImgId").hide();
			$("#fieldUserDetailsId").html('<h4>NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildFieldMonitoringReport(result){
	var str = '';
	
	str+='<div class="table-responsive">';
		str+='<table class="table b_1 m_top10" id="tabWiseDtlsId">';
			str+='<thead>';
				str+='<th>FM USER ID</th>';
				str+='<th>FM USER CONTACT NUMBER</th>';
				str+='<th>DISTRICT</th>';
				if(result[0].idnameList != null && result[0].idnameList.length > 0){
					for(var i in result[0].idnameList){
						str+='<th>'+result[0].idnameList[i].name+'</th>'
					}
				}
				if(result[0].idnameList1 != null && result[0].idnameList1.length > 0){
					for(var i in result[0].idnameList1){
						str+='<th>'+result[0].idnameList1[i].name+'</th>'
					}
				}
			str+='</thead>';
			for(var i in result){   
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td> - </td>';
					str+='<td>'+result[i].districtName+'</td>';
					if(result[i].idnameList != null && result[i].idnameList.length > 0){
						for(var j in result[i].idnameList){
							str+='<td>'+result[i].idnameList[j].attenteeCount+'</td>'
						}
					}
					if(result[i].idnameList1 != null && result[i].idnameList1.length > 0){
						for(var j in result[i].idnameList1){
							str+='<td>'+result[i].idnameList1[j].attenteeCount+'</td>'
						}
					}
				str+='</tr>';  
			}
		str+='</table>';
	str+='</div>';
	
	$("#fieldUserDetailsImgId").hide();
	$("#fieldUserDetailsId").html(str);
	$("#tabWiseDtlsId").dataTable();
}
getDataCollectorsConstituencyWiseCount();
function getDataCollectorsConstituencyWiseCount(){
	 $("#dataCollectorsDivId").html('');
	$("#dataCollectorsImgId").show();
		var jsObj = {}
		$.ajax({
			type : 'GET',
			url : 'getDataCollectorsConstituencyWiseCountAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			
			buildDataCollectorsPerformanceDetails(result);
		});
	}
function buildDataCollectorsPerformanceDetails(result){

	if(result != null && result.length > 0){
		var str = '';
		
        //str+='<h4 class="text-capital">total data collectors - <span id="totalDataCollectorsId">'+result.length+'</span></h4>';
       // $("#totalDataCollectorsId span").text("-"  +result.length);
		//str+='<span class="btn btn-info excelId form-inline pull-right btn-sm btn-xs" style="float:left;margin-top: 10px" onclick="exportToExcel(\'detailsTable\')"><b> Export To Excel </b></span>';
		str+='<table class="table b_1 m_top10 " id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>district</th>';
				str+='<th>constituency</th>';
				str+='<th>FM User</th>';
				str+='<th>Total Users</th>';
				str+='<th>Registered</th>';
				str+='<th>Not Yet Started</th>';
				str+='<th>Not Started Issues</th>';
				str+='<th>Started Issues</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					
						if(result[i].districtName != null)
							str+='<td class="issueCmpltd" id="'+result[i].districtId+'">'+result[i].districtName+'</td>';
						else
							str+='<td> - </td>';
					
					if(result[i].constituencyName != null)
						str+='<td id="'+result[i].constituencyId+'">'+result[i].constituencyName+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].lastHourCount != null && result[i].lastHourCount > 0)
						str+='<td class="issueCmpltd" title="UserId : '+result[i].userName+'" id="'+result[i].tabUserId+'">'+result[i].tabUserName+'</td>';
					else
						str+='<td class="issuePending" title="UserId : '+result[i].userName+'" id="'+result[i].tabUserId+'">'+result[i].tabUserName+'</td>';
						//str+='<td class="issuePending">'+result[i].userName+'</td>';*/
					if(result[i].name != null)
						str+='<td id="'+result[i].id+'">'+result[i].name+'</td>';
					else
						str+='<td> - </td>';
						
					if(result[i].totalCount != null)
						str+='<td>'+result[i].totalCount+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].todayRegCount != null)
						str+='<td>'+result[i].todayRegCount+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].notYetStartedUsers != null)
						str+='<td>'+result[i].notYetStartedUsers+'</td>';
					else
						str+='<td> - </td>';
					/*if(result[i].districtName != null)
						str+='<td>'+result[i].districtName+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].constituencyName != null)
						str+='<td>'+result[i].constituencyName+'</td>';
					else
						str+='<td> - </td>';*/
					if(result[i].notYetStartedIssues != null)
						str+='<td>'+result[i].notYetStartedIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].startedIssues != null)
						str+='<td>'+result[i].startedIssues+'</td>';
					else
						str+='<td> - </td>';
					
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#dataCollectorsDivId").html(str);
		$("#dataCollectorsImgId").hide();
		$('#detailsTable').dataTable({
	         "aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 20,
	         "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
	    });
		//$('html,body').animate({scrollTop: $("#dataCollectorsDivId").offset().top}, 'slow');
	}
	else{
			$("#dataCollectorsImgId").hide();
			$("#dataCollectorsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	}
}
</script>
</body>
</html>