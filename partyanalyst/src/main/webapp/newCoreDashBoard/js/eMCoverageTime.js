var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

var currentFromDate = moment().format("DD-MM-YYYY");
var currentToDate = moment().format("DD-MM-YYYY");
$(".chosen-select").chosen();
$("#EMCoverageTimeHeadDate").html("Today("+currentFromDate+")"); 
	
$("#dateRangeEMCoverageTimeId").daterangepicker({
	opens: 'left',
	startDate:currentFromDate,
	endDate: currentToDate,
	locale: {
		format: 'DD-MM-YYYY'
	},
	ranges: {
		'Today': [moment(), moment()],
	   'This Month': [moment().startOf("month").format("DD-MM-YYYY"), moment().endOf('month').format("DD-MM-YYYY")],
	   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY"),moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});

$('#dateRangeEMCoverageTimeId').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD-MM-YYYY');
	currentToDate = picker.endDate.format('DD-MM-YYYY');
	$("#EMCoverageTimeHeadDate").html("("+picker.startDate.format("DD/MM/YY")+" to "+picker.endDate.format("DD/MM/YY")+")");
	onLoadEmCoverageTimeCalls();
	if($(".EMCoverageTimeIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getDayWiseCandidateCoverageTime(); // Main Block right expand
	}
	
});

function onLoadEmCoverageTimeCalls(){
	getCandidateAndPartyWiseNewsChannals(); //Main Block 
	
}
$(document).on("click",".EMCoverageTimeIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getDayWiseCandidateCoverageTime(); // Main Block right expand
	}
});

function getCandidateAndPartyWiseNewsChannals(){
	$("#EMCoverageTimeSummaryDivId").html(spinner);
	var type="candidate";
	var categoryId = 0;
	
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type
	}).then(function(result){
		if(result != null && result.length > 0){
			getCandidateAndPartyWiseNewsChannelsBuilding(result);
		}else{
			$("#EMCoverageTimeSummaryDivId").html("No Data Available");
		}
	});
}
function getCandidateAndPartyWiseNewsChannelsBuilding(result){
	var str="";
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered" style="width:100%">';
		str+='<thead>';
			str+='<tr>';
				if(result[0].coreDashBoardVOList != null && result[0].coreDashBoardVOList.length > 0){
					str+='<td rowspan="2">Channel</td>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<td colspan="2">'+result[0].coreDashBoardVOList[i].organization+'</td>';
					}
				}
			str+='</tr>';
			str+='<tr>';
				if(result[0].coreDashBoardVOList != null && result[0].coreDashBoardVOList.length > 0){
					for(var i in result[0].coreDashBoardVOList){
						str+='<td>+ve</td>';
						//str+='<td>%</td>';
						str+='<td>-ve</td>';
						//str+='<td>%</td>';
					}
				}
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].organization+'</td>';
					if(result[i].coreDashBoardVOList != null && result[i].coreDashBoardVOList.length > 0){
						for(var j in result[i].coreDashBoardVOList){
							str+='<td>'+result[i].coreDashBoardVOList[j].positiveCountMain+'</td>';
							str+='<td>'+result[i].coreDashBoardVOList[j].negativCountMain+'</td>';
						}
					}
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#EMCoverageTimeSummaryDivId").html(str);
}

function getDayWiseCandidateCoverageTime(){
	$("#EMCoverageTimeDayWiseDivId").html(spinner);
	var type="candidate";
	var categoryId = 0;
	
	$.ajax({	
		//url: wurl+"/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+categoryId+"/"+type+"/"+currentFromDate+"/"+currentToDate
	}).then(function(result){
		if(result != null && result.length > 0){
			//buildDayWiseCandidateCoverageTime(result);
		}else{
			$("#EMCoverageTimeDayWiseDivId").html("No Data Available");
		}
	});
}
