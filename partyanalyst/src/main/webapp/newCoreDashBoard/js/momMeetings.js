var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var globalUserWiseMemberMOMRslt;
//var levelWiseMOMArr;
var levelWiseMOMArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
//{name:'mandal',id:'5'}
//alert(globalUserAccessLevelId)
/* if(globalUserAccessLevelId == 2 || globalUserAccessLevelId == 3){
	levelWiseMOMArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
}else if(globalUserAccessLevelId == 4){
	levelWiseMOMArr=[{name:'parliament',id:'10'},{name:'constituency',id:'4'}];
}else if(globalUserAccessLevelId == 5){
	levelWiseMOMArr=[{name:'constituency',id:'4'}];
} */ 


var currentFromDateMOM = moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY");
var currentToDateMOM = moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY");
$("#dateRangeIdForMOM").daterangepicker({
	opens: 'left',
	startDate:currentFromDateMOM,
	endDate: currentToDateMOM,
	locale: {
		format: 'DD/MM/YYYY'
	},
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'This Month': [moment().startOf("month").format("DD/MM/YYYY"), moment().endOf('month').format("DD/MM/YYYY")],
	   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY"),moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()]
	   
	}
});

	var dates= $("#dateRangeIdForMOM").val();
	var pickerDates = currentFromDateMOM+' - '+currentToDateMOM
	if(dates == pickerDates)
	{
		$("#dateRangeIdForMOM").val('Last Month');
	}
	
	$('#dateRangeIdForMOM').on('apply.daterangepicker', function(ev, picker) {
		currentFromDateMOM = picker.startDate.format('DD/MM/YYYY');
		currentToDateMOM = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'Last Month')
		{
			$("#dateRangeIdForMOM").val('Last Month');
		}
		
		onloadMOMCalls();
	});	
function onloadMOMCalls(){
	getMOMBasicCountDetailsAction();
	//getPartyMeetingMOMDetailsCompletedCountClicks();
	if($(".momIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		 $("[role='tabCummulativeMOM'] li").removeClass("active");
		 $("[role='tabCummulativeMOM'] li:nth-child(1)").addClass("active");
		 getUserTypeWisePartyMeetingMOMDetails();
	}
	if($(".moreAttMOMBlocksIcon").hasClass("expandBlockMOM")){
		 locationWiseMOMDetails()
		
	 }
}

$(document).on("click",".momIconExpand",function(){
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getUserTypeWisePartyMeetingMOMDetails();
	}
		
});
$(document).on("click",".moreAttMOMBlocksIcon",function(){
		$(this).addClass("expandBlockMOM");
		$(".moreAttMOMBlocks").show(); 
		locationWiseMOMDetails()
		
});
$(document).on("click",".expandBlockMOM",function(){
		$(this).removeClass("expandBlockMOM");
		$(".moreAttMOMBlocks").hide();
});

function getMOMBasicCountDetailsAction()
{ 
	$("#locationWiseMOMDetailsDivID").html(spinner);
	  var jsObj ={
		 activityMemberId : globalActivityMemberId,
		 stateId : 1,
		 fromDate : currentFromDateMOM,
		 toDate : currentToDateMOM,
		 partyMeetingTypeArr:[2,3,15]
		 
		}
	  $.ajax({
		type : 'POST',
		url : 'getMOMBasicCountDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	  }).done(function(result){
			if(result !=null){
				buildMOMBasicCountDetailsAction(result);
			}else{
				$("#locationWiseMOMDetailsDivID").html("No Data Available");
			}
	  });
}
function buildMOMBasicCountDetailsAction(result){
	
	var str='';
		
		str+='<div class="panel panel-default panel-blue">';
		  str+='<div class="panel-heading">';
			str+='<h3 class="panel-title">Overview</h3>';
		 str+=' </div>';
		 str+='<div class="panel-body">';
		 
			str+='<div class="row">';
				str+='<div class="col-sm-3">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">Planned Meetings</h5>';
						str+='<h4 class="text-bold m_top10 f_16">'+result.plannedCount+'</h4>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-4">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">Total Conducted Meetings</h5>';
						str+='<h4 class="text-bold m_top10 f_16">'+result.conductedCount+' <small style="color:green;">'+result.conductedCommentCntPer+'%</small></h4>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-5">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">MOM Not Updated <br/>Meetings</h5>';
						str+='<h4 class="text-bold m_top10 f_16">'+result.notUpdatedMomCount+' <small style="color:green;">'+result.notUpdatedMomCntPer+'%</small></h4>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="bg_light_yash m_top10">';
				str+='<span class="text-invert">MOM</span>';
				str+='<div class="row">';
					str+='<div class="col-sm-3">';
						str+='<div class="border_pad">';
							str+='<h5 class="f_12">Total</h5>';
							str+='<h4 class="text-bold m_top25 f_16">'+result.totalMoms+' </h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<div class="border_pad">';
							str+='<h5 class="f_12">General</h5>';
							str+='<h4 class="text-bold m_top25 f_16">'+result.generalCount+' <small style="color:green;">'+result.generalCntPer+'%</small></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<div class="border_pad">';
							str+='<h5 class="f_12">Actionable : <span style="font-size:16px;font-weight:bold;">'+result.actionCount+' <small style="color:green;">'+result.actionCntPer+'%</small></span></h5>';
								
								str+='<div style="">';
									str+='<div class="row">';
										str+='<div class="col-sm-6 m_top5" style="border-right:1px solid #ccc;">';
											str+='<h5 class="f_12">Party</h5>';
											str+='<h4 class="text-bold m_top5 f_16">'+result.partyCount+' <small style="color:green;" class="pull-right m_top5">'+result.partyCntPer+'%</small></h4>';
										str+='</div>';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="f_12">Govt</h5>';
											str+='<h4 class="text-bold m_top5 f_16">'+result.govtCount+' <small style="color:green;" class="pull-right m_top5">'+result.govtCntPer+'%</small></h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
								
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
		  str+='</div>';
		str+='</div>';
		
		str+='<div class="MOMMainScroll">';
		for(var i in result.partyMettingsVOList){
			str+='<div class="panel panel-default panel-blue">';
			  str+='<div class="panel-heading">';
				str+='<h3 class="panel-title">'+result.partyMettingsVOList[i].name+' Level</h3>';
			 str+=' </div>';
			 str+='<div class="panel-body">';
			 
				str+='<div class="row">';
					str+='<div class="col-sm-3">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">Planned Meetings</h5>';
							str+='<h4 class="text-bold m_top10 f_16">'+result.partyMettingsVOList[i].plannedCount+'</h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">Total Conducted Meetings</h5>';
							str+='<h4 class="text-bold m_top10 f_16">'+result.partyMettingsVOList[i].conductedCount+' <small style="color:green;">'+result.partyMettingsVOList[i].conductedCommentCntPer+'%</small></h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-5">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">MOM Not Updated <br/>Meetings</h5>';
							str+='<h4 class="text-bold m_top10 f_16">'+result.partyMettingsVOList[i].notUpdatedMomCount+' <small style="color:green;">'+result.partyMettingsVOList[i].notUpdatedMomCntPer+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="bg_light_yash m_top10">';
				str+='<span class="text-invert">MOM</span>';
					str+='<div class="row">';
						str+='<div class="col-sm-3">';
							str+='<div class="border_pad">';
								str+='<h5 class="f_12">Total</h5>';
								str+='<h4 class="text-bold m_top25 f_16">'+result.partyMettingsVOList[i].totalMoms+' </h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<div class="border_pad">';
								str+='<h5 class="f_12">General</h5>';
								str+='<h4 class="text-bold m_top25 f_16">'+result.partyMettingsVOList[i].generalCount+' <small style="color:green;">'+result.partyMettingsVOList[i].generalCntPer+'%</small></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<div class="border_pad">';
								str+='<h5 class="f_12">Actionable : <span style="font-size:16px;font-weight:bold;">'+result.partyMettingsVOList[i].actionCount+' <small style="color:green;">'+result.partyMettingsVOList[i].actionCntPer+'%</small></span></h5>';
									
									str+='<div style="">';
										str+='<div class="row">';
											str+='<div class="col-sm-6 m_top5" style="border-right:1px solid #ccc;">';
												str+='<h5 class="f_12">Party</h5>';
												str+='<h4 class="text-bold m_top5 f_16">'+result.partyMettingsVOList[i].partyCount+' <small style="color:green;" class="pull-right m_top5">'+result.partyMettingsVOList[i].partyCntPer+'%</small></h4>';
											str+='</div>';
											str+='<div class="col-sm-6 m_top5">';
												str+='<h5 class="f_12">Govt</h5>';
												str+='<h4 class="text-bold m_top5 f_16">'+result.partyMettingsVOList[i].govtCount+' <small style="color:green;" class="pull-right m_top5">'+result.partyMettingsVOList[i].govtCntPer+'%</small></h4>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
			  str+='</div>';
			str+='</div>';
		}
	str+='</div>';	
	$("#locationWiseMOMDetailsDivID").html(str);
	if(result.partyMettingsVOList.length > 2)
		{
			$(".MOMMainScroll").mCustomScrollbar({setHeight:'297px'});
		}
}

function getUserTypeWisePartyMeetingMOMDetails(){
	
	$("#userTypeWiseMOMDiv").html(spinner);
		
    var dateString = currentFromDateMOM+"-"+currentToDateMOM;
		var jsObj ={  
			 activityMemberId:globalActivityMemberId,
			 userTypeId:globalUserTypeId,
			 state:"AP",
			 stateId:1,
			 dateString:dateString,
			 partyMeetingTypeArr:[2,3,15]
		};		
		
		$.ajax({
			type : 'POST',
			url : 'getUserTypeWisePartyMeetingMOMDetailsCounts1.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildgetUserTypeWiseMOMTopFiveStrong(result);
			globalUserWiseMemberMOMRslt = result;	
		});
	}
$(document).on("click",".momLicls li",function(){
	var memberType=$(this).attr("attr_value");
		buildgetUserTypeWiseMOMTopFiveStrong(globalUserWiseMemberMOMRslt); 
	 if(memberType != null && memberType == "strong"){
	 }else if(memberType == "poor"){
		buildgetUserTypeWiseMOMTopFivePoor(globalUserWiseMemberMOMRslt)
	 }
});

function buildgetUserTypeWiseMOMTopFiveStrong(result){
		$("#userTypeWiseMOMDiv").html('');
		
		if(result != null && result.length > 0){
			for(var i in result){
				var momNotUpdatedCountArr=[];
				var momUpdatedCountArr=[];
				
				var candidateNameArray=[]; 
				var countVar =0;
				
				if(result[i] !=null && result[i].length>0){
					for(var j in result[i]){
						candidateNameArray.push(result[i][j].name.toUpperCase());
						momNotUpdatedCountArr.push({"y":parseFloat(result[i][j].momCompletedCntPer),"extra":result[i][j].totalMomsCnt})
						momUpdatedCountArr.push({"y":parseFloat(result[i][j].totalPendingMomsCntPer),"extra":result[i][j].totalPendingMomsCnt})
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
				
					
				if( result[i][j].momNotUpdatedCountArr !=0 || result[i][j].momUpdatedCountArr !=0){
					
					var str='';
					//str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecMOM'+i+'" style="height:170px;" class="scrollLenDiv"></div>';
					//str+='</div>'
					$("#userTypeWiseMOMDiv").append(str);
					
					$(function () {
						 $("#genSecMOM"+i).highcharts({
							 colors: ['#95F895','#FF3A55'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								},
								labels: {
									enabled:false
								}
							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return (this.y) +'%';
											}
										}
									  
									}
								}
							},
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';
									$.each(this.points, function () {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+this.point.extra+' -  ' +
											(this.y)+'%';
									});

									return s;
								},
								shared: true
							},
								
								series: [{
									name: 'MOM UPDATED',
									data: momUpdatedCountArr
								},{
									name: 'MOM NOT UPDATED',
									data: momNotUpdatedCountArr
								}],
						 
						});
					});
				} 
				else{
					$("#genSecMOM"+i).html("No Data Available");
					$("#genSecMOM"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseMOMDiv").html("");
		}
		
		$("#userTypeWiseMOMDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarMOM").mCustomScrollbar({setHeight:'930px'})
				
			}else{
				$(".verticalScrollBarMOM").css("height","auto");
			
			}
		});
		
	}
	
	function buildgetUserTypeWiseMOMTopFivePoor(result){
		$("#userTypeWiseMOMDiv").html('');
		if(result != null && result.length > 0){
			for(var i in result){
				
				var momNotUpdatedCountArr=[];
				var momUpdatedCountArr=[];
				
				var candidateNameArray=[]; 
				var countVar =0;
				var momNotUpdatedCount=0;
				var momUpdatedCount=0;
				
				
				if(result[i] !=null && result[i].length  >0){
					for(var j = result[i].length -1; j >= 0; j--){
						
						momNotUpdatedCountArr.push({"y":parseFloat(result[i][j].momCompletedCntPer),"extra":result[i][j].totalMomsCnt})
						momUpdatedCountArr.push({"y":parseFloat(result[i][j].totalPendingMomsCntPer),"extra":result[i][j].totalPendingMomsCnt})
						
						momNotUpdatedCount = momNotUpdatedCount+parseInt(result[i][j].totalMomsCnt);
						momUpdatedCount = momUpdatedCount+parseInt(result[i][j].totalPendingMomsCnt);
						
						candidateNameArray.push(result[i][j].name.toUpperCase());
						
						
						countVar =countVar+1;
						if (countVar === 5) {
							break;
						}
					}
				}
			  		
				if( momNotUpdatedCount !=0 || momUpdatedCount !=0){
					var str='';
					
					//str+='<div class="col-sm-12">';
					str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';	
					str+='<div id="genSecMOM1'+i+'" class="m_top20 scrollLenPoorDiv" style="height:170px;" ></div>';
					//str+='</div>';
				
					$("#userTypeWiseMOMDiv").append(str);
					
					var getWidth = $("#genSecMOM1"+i).parent().width()+'px';
					$("#genSecKaizala1"+i).width(getWidth);
					$(function () {
						 $("#genSecMOM1"+i).highcharts({
							colors: ['#95F895','#FF3A55'],
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								categories: candidateNameArray,
								type: 'category',
								labels: {
											formatter: function() {
												return this.value.toString().substring(0, 10)+'...';
											},
											
										}
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								}

							},
							legend: {
								enabled: false
							},
							
									
							plotOptions: {
								column: {
									stacking: 'percent',
									dataLabels: {
										enabled: true,
										 formatter: function() {
											if (this.y === 0) {
												return null;
											} else {
												return (this.y) + '%';
											}
										}
									  
									}
								}
							},

							/* tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%</b>'
							}, */
							tooltip: {
								formatter: function () {
									var s = '<b>' + this.x + '</b>';
									$.each(this.points, function () {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :'+this.point.extra+' -  ' +
											(this.y)+'%';
									});

									return s;
								},
								shared: true
							},
								series: [{
									name: 'MOM UPDATED',
									data: momUpdatedCountArr
								},{
									name: 'MOM NOT UPDATED',
									data: momNotUpdatedCountArr
								}],
						 
						});
					});
				}else{
					$("#genSecMOM1"+i).html("No Data Available");
					$("#genSecMOM1"+i).css("height","35px");
						
				} 
				
			}
			
		}else{
			$("#userTypeWiseMOMDiv").html("No Data Available");
		}
		
		$("#userTypeWiseMOMDiv").each(function(){
			var scrollengthDiv = $(this).find(".scrollLenPoorDiv").length;
			if(scrollengthDiv >= 4){
				$(".verticalScrollBarMOM").mCustomScrollbar({setHeight:'930px'})
				
			}else{
				$(".verticalScrollBarMOM").css("height","auto");
			
			}
		});
		
	}
$(document).on("click",".momBlockRefresh",function(){
	onloadMOMCalls();
});

function locationWiseMOMDetails()
{
	var collapse='';
	for(var i in levelWiseMOMArr)
	{
		collapse+='<div class="panel-group" id="accordion'+levelWiseMOMArr[i].id+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default m_top20">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseMOMArr[i].id+'" style="background-color:#f1f1f1;">';
					if(i == 0)
					{
						collapse+='<a role="button" class="collapseDebatesIcon '+levelWiseMOMArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseMOMArr[i].id+'" href="#collapse'+levelWiseMOMArr[i].id+'" aria-expanded="true" level_name="'+levelWiseMOMArr[i].name+'" aria-controls="collapse'+levelWiseMOMArr[i].id+'">';
					}else{
						collapse+='<a role="button" class="collapseDebatesIcon collapsed '+levelWiseMOMArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+levelWiseMOMArr[i].id+'" href="#collapse'+levelWiseMOMArr[i].id+'" level_name="'+levelWiseMOMArr[i].name+'" aria-expanded="true" aria-controls="collapse'+levelWiseMOMArr[i].id+'">';
					}
					collapse+='<h4 class="panel-title text-capital">'+levelWiseMOMArr[i].name+' Wise Overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseMOMArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseMOMArr[i].id+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseMOMArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseMOMArr[i].id+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
						collapse+='<div class="col-sm-12">';
						collapse+='<div id="MOMLevel'+levelWiseMOMArr[i].name+'"></div>';
					collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	$("#levelWiseMOMDetailsDivId").html(collapse);
	for(var i in levelWiseMOMArr)
	{
		getMOMDetailedBlockDetailsAction(levelWiseMOMArr[i].name);
	}	
}
function getMOMDetailedBlockDetailsAction(locationType)
{ 
	$("#MOMLevel"+locationType).html(spinner);

	var jsObj ={
		 activityMemberId : globalActivityMemberId,
		 stateId : 1,
		 fromDate : currentFromDateMOM,
		 toDate : currentToDateMOM,
		 partyMeetingTypeArr:[2,3,15],
		 levelType : locationType
		 
	  }
	$.ajax({
		type : 'POST',
		url : 'getMOMDetailedBlockDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.partyMettingsVOList.length>0){
			buildMOMDetailedBlockDetailsAction(result,locationType);
		}else{
			$("#MOMLevel"+locationType).html("No Data Available");
		}
	});
}

function buildMOMDetailedBlockDetailsAction(result,locationType){
	var str='';
	console.log("#MOMLevel"+locationType)
	 str+='<div class="table-responsive">';
		str+='<table class="table table_custom_Mom table-bordered dataTableMOM'+locationType+'" style="width:100% !important;">';
			str+='<thead>';
				str+='<tr>';
					if(locationType == "district"){
						str+='<th rowspan="3">District</th>';
					}else if(locationType == "parliament"){
						str+='<th rowspan="3">Parliament</th>';
					}else if(locationType == "constituency"){
						str+='<th rowspan="3">Constituency</th>';
					}else if(locationType == "mandal"){
						str+='<th rowspan="3">Mandal</th>';
					}
					str+='<th rowspan="3">Total Conducted Meetings</th>';
					if(locationType == "district"){
						str+='<th colspan="4" >MOM Not Updated Meetings</th>';
					}else if(locationType == "mandal"){
						str+='<th colspan="2" >MOM Not Updated Meetings</th>';
					}else{
						str+='<th colspan="3" >MOM Not Updated Meetings</th>';
					}
					str+='<th colspan="9">MOM Details</th>';
				str+='</tr>';
				str+='<tr>';
				if(locationType == "district"){
					str+='<th rowspan="2">Total</th>';
					str+='<th rowspan="2">Mandal</th>';
					str+='<th rowspan="2">AC</th>';
					str+='<th rowspan="2">District</th>';
				}else if(locationType == "mandal"){
					str+='<th rowspan="2">Total</th>';
					str+='<th rowspan="2">Mandal</th>';
				}else{
					str+='<th rowspan="2">Total</th>';
					str+='<th rowspan="2">Mandal</th>';
					str+='<th rowspan="2">AC</th>';
				}
				str+='<th rowspan="2">Total</th>';
				str+='<th colspan="2">General</th>';
				str+='<th colspan="6">Actionable</th>';
				str+='</tr>';
				
				str+='<tr>';
					str+='<th >General</th>';
					str+='<th >%</th>';
					str+='<th >Total</th>';
					str+='<th >%</th>';
					str+='<th >Party</th>';
					str+='<th >%</th>';
					str+='<th >Govt</th>';
					str+='<th >%</th>';
				str+='</tr>';
			str+='</thead>';
			
			str+='<tbody>';
				for(var i in result.partyMettingsVOList){
					str+='<tr>';
						str+='<td style="text-align:left !important;">'+result.partyMettingsVOList[i].name+'</td>';
						str+='<td>'+result.partyMettingsVOList[i].conductedCount+'</td>';
						if(locationType == "district"){
							str+='<td>'+result.partyMettingsVOList[i].notUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].constituencyNotUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].districtNotUpdatedCount+'</td>';
						}else if(locationType == "mandal"){
							str+='<td>'+result.partyMettingsVOList[i].notUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</td>';
						}else{
							str+='<td>'+result.partyMettingsVOList[i].notUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</td>';
							str+='<td>'+result.partyMettingsVOList[i].constituencyNotUpdatedCount+'</td>';
							
						}
						//if(result.partyMettingsVOList[i].totalMoms != null && result.partyMettingsVOList[i].totalMoms.length > 0){
							str+='<td>'+result.partyMettingsVOList[i].totalMoms+'</td>';
						//}else{
							//str+='<td>-</td>';
						//}
						//if(result.partyMettingsVOList[i].generalCount != null && result.partyMettingsVOList[i].generalCount.length > 0){
							str+='<td>'+result.partyMettingsVOList[i].generalCount+'</td>';
						//}else{
							//str+='<td>-</td>';
						//}
						str+='<td>'+result.partyMettingsVOList[i].generalCntPer+'</td>';
						//if(result.partyMettingsVOList[i].actionCount != null && result.partyMettingsVOList[i].actionCount.length > 0){
							str+='<td>'+result.partyMettingsVOList[i].actionCount+'</td>';
						/* }else{
							str+='<td>-</td>';
						} */
						str+='<td>'+result.partyMettingsVOList[i].actionCntPer+'</td>';
						//if(result.partyMettingsVOList[i].partyCount != null && result.partyMettingsVOList[i].partyCount.length > 0){
							str+='<td>'+result.partyMettingsVOList[i].partyCount+'</td>';
						/* }else{
							str+='<td>-</td>';
						} */
						str+='<td>'+result.partyMettingsVOList[i].partyCntPer+'</td>';
						//if(result.partyMettingsVOList[i].govtCount != null && result.partyMettingsVOList[i].govtCount.length > 0){
							str+='<td>'+result.partyMettingsVOList[i].govtCount+'</td>';
						/* }else{
							str+='<td>-</td>';
						} */
						
						str+='<td>'+result.partyMettingsVOList[i].govtCntPer+'</td>';
						
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	 str+='</div>';
	 console.log("#MOMLevel"+locationType)
	 
	$("#MOMLevel"+locationType).html(str);
	if(locationType == "district"){
		$(".dataTableMOM"+locationType).dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": true
		});
	}else{
		$(".dataTableMOM"+locationType).dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			
		});
	}
	
	
}

/* $(document).on("click",".clickCls",function(){
	$("#momModalDivId").modal("show");
	
});
function getPartyMeetingMOMDetailsCompletedCountClicks(){
   
    
    var jsObj ={  
		
    };      
    $.ajax({
      type : 'POST',
      url : 'getPartyMeetingMOMDetailsCompletedCountsClicks.action',
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)}
    }).done(function(result){
      
       
    });
  } */
  
  
  $(document).on("click",".clicksCountCls",function(){
  var dateString = currentFromDateMOM+"-"+currentToDateMOM;
    var jsObj ={  
       activityMemberId:44,
       userTypeId:1,
       state:"AP",
       stateId:1,
       dateString:dateString,
       partyMeetingTypeArr:[2,3,15],
       meetinglevelIdArr:[3,4,5],// mandal - 5 , constituency : 4, district-3       
       searchType:"PlannedMeetings",
       
       searchLevelId:2,
       searchLevelValue:1,
    };
    $.ajax({
      type : 'POST',
      url : 'getPartyMeetingMOMDetailsCompletedCountsClicks.action',
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)}
    }).done(function(result){
      
       console.log(result);
    });
});
