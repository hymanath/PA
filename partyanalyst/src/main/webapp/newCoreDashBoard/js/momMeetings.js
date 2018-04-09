var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var globalUserWiseMemberMOMRslt;
levelWiseMOMArr=[{name:'district',id:'3'},{name:'parliament',id:'10'},{name:'constituency',id:'4'}];

var currentFromDate = moment().startOf("month").format("DD/MM/YYYY");
var currentToDate = moment().endOf('month').format("DD/MM/YYYY");
$("#dateRangeIdForMOM").daterangepicker({
	opens: 'left',
	startDate:currentFromDate,
	endDate: currentToDate,
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
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangeIdForMOM").val('This Month');
	}
	
	$('#dateRangeIdForMOM').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD/MM/YYYY');
		currentToDate = picker.endDate.format('DD/MM/YYYY');
		if(picker.chosenLabel == 'This Month')
		{
			$("#dateRangeIdForMOM").val('This Month');
		}
		
		onloadMOMCalls();
	});	
function onloadMOMCalls(){
	getMOMBasicCountDetailsAction();
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
		 fromDate : currentFromDate,
		 toDate : currentToDate,
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
						str+='<h4 class="text-bold m_top10 f_16">'+result.notUpdatedCount+' <small style="color:green;">'+result.notUpdatedCommentCntPer+'%</small></h4>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="bg_light_yash m_top10">';
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
							str+='<h4 class="text-bold m_top10 f_16">'+result.partyMettingsVOList[i].notUpdatedCount+' <small style="color:green;">'+result.partyMettingsVOList[i].notUpdatedCommentCntPer+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="bg_light_yash m_top10">';
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
		
	$("#locationWiseMOMDetailsDivID").html(str);
}

function getUserTypeWisePartyMeetingMOMDetails(){
	
	$("#userTypeWiseMOMDiv").html(spinner);
		/*
		
		var jsObj ={  
			 activityMemberId:44,
			 userTypeId:2,
			 state:"AP",
			 committeeEnrollmentYearArray:[],
			 dateString:"09/02/2017-06/04/2018",
			 partyMeetingTypeArr:[2,3,15]
		};	
*/

		var jsObj ={  
			 activityMemberId:44,
			 userTypeId:2,
			 userAccessLevelId:2,
			 userAccessLevelValuesArray:[1],
			 state:"AP",
			 levelWiseBasicCommitteesArray:[{"committeeLevelId":11,"basicCommitteeIds":["1","2","3","4","6","7","8","9","18","11","5","15","10","16","20","21","19","14","12","13","1"]},{"committeeLevelId":5,"basicCommitteeIds":["1","2","3","4","6","7","8","9","18","11","5","15","10","16","20","21","19","14","12","13","1"]},{"committeeLevelId":6,"basicCommitteeIds":["1"]}],
			 dateString:"09/02/2017-06/04/2018",
			 committeeEnrollmentYearArray:["2"],
			 commiteType:"tdpCommittee"
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
						momNotUpdatedCountArr.push({"y":parseFloat(result[i][j].momCompletedCntPer),"extra":result[i][j].momCompletedCnt})
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
									name: 'MOM NOT UPDATED',
									data: momNotUpdatedCountArr
								},{
									name: 'MOM UPDATED',
									data: momUpdatedCountArr
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
						
						momNotUpdatedCountArr.push({"y":parseFloat(result[i][j].momCompletedCntPer),"extra":result[i][j].momCompletedCnt})
						momUpdatedCountArr.push({"y":parseFloat(result[i][j].totalPendingMomsCntPer),"extra":result[i][j].totalPendingMomsCnt})
						
						momNotUpdatedCount = result[i][j].momCompletedCnt;
						momUpdatedCount = result[i][j].totalPendingMomsCnt;
						
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
									name: 'MOM NOT UPDATED',
									data: momNotUpdatedCountArr
								},{
									name: 'MOM UPDATED',
									data: momUpdatedCountArr
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
						collapse+='<div id="MOM'+levelWiseMOMArr[i].name+'"></div>';
					collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	$("#levelWiseMOMDetailsDivId").html(collapse);
	for(var i in levelWiseArr)
	{
		//(levelWiseArr[i].name);
	}	
}
