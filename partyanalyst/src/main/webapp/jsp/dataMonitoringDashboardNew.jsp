<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DATA MONITORING DASHBOARD</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/> 
<style type="text/css">
.tableOverView , .tableOverView tr td
{
	border-right:1px solid #ddd !important;
	border-top:0px !important;
}

</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelWhite">
            	<div class="panel-heading">
                	<div class="row">
                    	<div class="col-md-7 col-xs-12 col-sm-9">
                        	<h4 class="panel-title">DATA MONITORING DASHBOARD</h4>
                        </div>
						<div class="col-md-2 col-xs-12 col-sm-9">
                        	<input type="radio" name="radio" value="1" checked="true" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> AP</input>
							<input type="radio" name="radio" value="36" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> TS</input>
							 <input type="radio" name="radio" value="0" class="stateWiseCls" onclick ="stateWisePopulateData(this.value);"> All</input>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-3">
                        	<div class="input-group inputGCustom">
                            	<span class="input-group-addon">
                                	<i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                <input type="text" class="form-control multiDateRangePicker"/>
                            </div>
                        </div>
                    </div>
                </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20" id="overAllDetailsId">
						</div>
					</div>
                	<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
						<h4 class="panel-title text-capital">district wise detailed overview</h4>
							<div class="table-responsive m_top10">
								<div id="districtWiseOverviewDetailsId"></div>
							</div>
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
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>
<script type="text/javascript">

$(".multiDateRangePicker").daterangepicker({
		opens: 'left',
	 	locale: {
		  format: 'MM/DD/YYYY'
		}	
});

getLocationWiseOverViewDetails("district",1,"districtWiseOverviewDetailsId");
getVerificationCountsAction();

$(document).on("click",".applyBtn",function(){
	var stateId = '';
		$('.stateWiseCls').each(function (index, value){
			stateId = $(":radio:checked").val();
		});
	getLocationWiseOverViewDetails("district",stateId,"districtWiseOverviewDetailsId");
	getVerificationCountsAction();
});

function stateWisePopulateData(state)
{
	getLocationWiseOverViewDetails("district",state,"districtWiseOverviewDetailsId");	
	getVerificationCountsAction();
}

function getLocationWiseOverViewDetails(locationType,locationVal,divId){
	$("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var dates = $(".multiDateRangePicker").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	var jsObj = { 
	  fromDate : fromDate,		//"10/01/2016",
	  toDate : toDate,		 	//"10/18/2016"  
	  locationType : locationType,
	  locationVal  : locationVal
	}
	$.ajax({
		type : 'GET',
		url : 'getLocationWiseOverViewDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildLocationWiseDetails(result,locationType,divId);
		}
		else{
			$("#"+divId).html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildLocationWiseDetails(result,locationType,divId){
	var str='';
	
	str+='<table class="table table-condensed b_1">';
		str+='<thead>';
			if(locationType == 'district')
				str+='<th>District</th>';
			else if(locationType == 'constituency')
				str+='<th>Constituency</th>';
			str+='<th>Registered</th>';
			str+='<th>Verified - Passed</th>';
			str+='<th>Verified - Rejected</th>';
			str+='<th>Data Verification Pending</th>';
			str+='<th>Open Issues</th>';
			str+='<th>Fixed Issues</th>';
			str+='<th>Closed Issues</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				if(locationType == 'district'){
					str+='<td class="locationWiseCls" attr_locationVal="'+result[i].id+'" attr_locationType="constituency" attr_divId="constituencyWiseOverAllDetailsId'+result[i].id+''+i+'" attr_td_id="constituencyWiseOverAlltdId'+result[i].id+''+i+'" attr_location_name="'+result[i].name+'" attr_heading="constNameid'+result[i].id+''+i+'"><a style="cursor:pointer;">'+result[i].name+'<a/></td>';
				}
				else{
					str+='<td>'+result[i].name+'</td>';
				}
				
				str+='<td>'+result[i].count+'</td>';
				str+='<td>'+result[i].verifiedCount+'</td>';
				str+='<td>'+result[i].rejectedCount+'</td>';
				str+='<td>'+result[i].notVerifiedCount+'</td>';
				str+='<td>'+result[i].openIssues+'</td>';
				str+='<td>'+result[i].fixedIssues+'</td>';
				str+='<td>'+result[i].closedIssues+'</td>';
			str+='</tr>';
			if(locationType == 'district'){
				str+='<tr>';
					str+='<td class="locationtdCls" colspan="8" id="constituencyWiseOverAlltdId'+result[i].id+''+i+'" style="display:none;">';
						str+='<h4 class="panel-title text-capital"><span id="constNameid'+result[i].id+''+i+'"></span>&nbsp;&nbsp;district - constituency wise detailed overview</h4>';
						str+='<i class="glyphicon glyphicon-remove pull-right removecls" style="cursor:pointer;"></i>';
						str+='<div class="table-responsive m_top20" id="constituencyWiseOverAllDetailsId'+result[i].id+''+i+'"></div>';
					str+='</td>';
				str+='</tr>';
			}
		}
		str+='</tbody>';
	str+='</table>';
	
	$("#"+divId).html(str);
}

$(document).on("click",".locationWiseCls",function(){
	var locationType = $(this).attr("attr_locationType");
	var locationVal = $(this).attr("attr_locationVal");
	var tdId = $(this).attr("attr_td_id");
	var divId = $(this).attr("attr_divId");
	var locationName = $(this).attr("attr_location_name");
	var headingId = $(this).attr("attr_heading");
	
	$("#"+headingId).html(locationName);
	$("#"+tdId).show();
	getLocationWiseOverViewDetails(locationType,locationVal,divId);
});

$(document).on("click",".removecls",function(){
	var divId = $(this).parent().attr("id");
	$("#"+divId).hide();
});

function getVerificationCountsAction(){
	
	var stateId = $('input[name=radio]:checked').val();
	
	var dates = $(".multiDateRangePicker").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	var jsObj = { 
	  stateId : stateId,
	  districtId : 0,
	  constituencyId  : 0,
	  userId : 0,
	  fromDate : fromDate,		//"10/01/2016",
	  toDate : toDate		 	//"10/18/2016"  
	}
	$.ajax({
		type : 'GET',
		url : 'getVerificationCountsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null)
			buildOverAllCounts(result);
		else
			$("#overAllDetailsId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	});
}

function buildOverAllCounts(result){
	var str = '';
	
	str+='<table class="table tableOverView text-center">';
		str+='<tr>';
			str+='<td>';
				str+='<h4>REGISTERED</h4>';
				str+='<h2>'+result.todayRegCount+'</h2>';
			str+='</td>';
			str+='<td>';
				str+='<h4>VERIFIED-PASSED</h4>';
				str+='<h2>'+result.passedcount+'</h2>';
			str+='</td>';
			str+='<td>';
				str+='<h4>VERIFIED-REJECTED</h4>';
				str+='<h2>'+result.rejectedCount+'</h2>';
			str+='</td>';
			str+='<td>';
				str+='<h4>PENDING</h4>';
				str+='<h2>'+result.pendingCount+'</h2>';
			str+='</td>';
		str+='</tr>';
	str+='</table>';
	
	$("#overAllDetailsId").html(str);
}
</script>
</body>
</html>
