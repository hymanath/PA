var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var globalUserWiseMemberMOMRslt;
var meetingDocsListDocuments=[];
var momdocsListImages=[];
//var levelWiseMOMArr;
var levelWiseMOMArr=[{name:'district',id:'2'},{name:'parliament',id:'9'},{name:'constituency',id:'3'}];
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
		  str+='<div class="panel-heading" style="padding-top: 8px;padding-bottom: 8px;">';
			str+='<h3 class="panel-title" style="font-size: 15px;">Overview</h3>';
		 str+=' </div>';
		 str+='<div class="panel-body">';
		 
			str+='<div class="row">';
				str+='<div class="col-sm-3">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">Planned Meetings</h5>';
						if(result.plannedCount !=null && result.plannedCount>0){
							str+='<h4 class="text-bold m_top10 f_16 meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="PlannedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Planned Meetings" attr_subSearchType="">'+result.plannedCount+'</h4>';
						}else{
							str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
						}
						
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-4">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">Total Conducted Meetings</h5>';
						if(result.conductedCount !=null && result.conductedCount>0){
							str+='<h4 class="text-bold m_top10 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalConductedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Total Conducted Meetings" attr_subSearchType="">'+result.conductedCount+'</span> <small style="color:green;">'+result.conductedCommentCntPer+'%</small></h4>';
						}else{
							str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
						}
						
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-5">';
					str+='<div class="bg_yash_color">';
						str+='<h5 class="f_12">MOM Not Updated <br/>Meetings</h5>';
						if(result.notUpdatedMomCount !=null && result.notUpdatedMomCount>0){
							str+='<h4 class="text-bold m_top10 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="MOM Not Updated Meetings" attr_subSearchType="">'+result.notUpdatedMomCount+'</span> <small style="color:green;">'+result.notUpdatedMomCntPer+'%</small></h4>';
						}else{
							str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
						}
						
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="bg_light_yash m_top10">';
				str+='<span class="text-invert">MOM</span>';
				str+='<div class="row">';
					str+='<div class="col-sm-3">';
						str+='<div class="border_pad">';
							str+='<h5 class="f_12">Total</h5>';
							if(result.totalMoms !=null && result.totalMoms>0){
								str+='<h4 class="text-bold m_top25 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Total MOM Meetings" attr_subSearchType="">'+result.totalMoms+'</span> </h4>';
							}else{
								str+='<h4 class="text-bold m_top25 f_16"> - </h4>';
							}
							
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<div class="border_pad">';
							str+='<h5 class="f_12">General</h5>';
							if(result.generalCount !=null && result.generalCount>0){
								str+='<h4 class="text-bold m_top25 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="General Meetings" attr_subSearchType="General">'+result.generalCount+'</span> <small style="color:green;">'+result.generalCntPer+'%</small></h4>';
							}else{
								str+='<h4 class="text-bold m_top25 f_16"> - </h4>';
							}
							
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-6">';
						str+='<div class="border_pad">';
							if(result.actionCount !=null && result.actionCount>0){
								str+='<h5 class="f_12">Actionable : <span style="font-size:16px;font-weight:bold;"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Total Actionable Meetings" attr_subSearchType="Actionable">'+result.actionCount+'</span> <small style="color:green;">'+result.actionCntPer+'%</small></span></h5>';
							}else{
								str+='<h5 class="f_12">Actionable : - </h5>';
							}
							
								
								str+='<div style="">';
									str+='<div class="row">';
										str+='<div class="col-sm-6 m_top5" style="border-right:1px solid #ccc;">';
											str+='<h5 class="f_12">Party</h5>';
											if(result.partyCount !=null && result.partyCount>0){
												str+='<h4 class="text-bold m_top5 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Actionable Party Meetings" attr_subSearchType="Party">'+result.partyCount+'</span> <small style="color:green;" class="pull-right m_top5">'+result.partyCntPer+'%</small></h4>';
											}else{
												str+='<h4 class="text-bold m_top5 f_16"> - </h4>';
											}
											
										str+='</div>';
										str+='<div class="col-sm-6 m_top5">';
											str+='<h5 class="f_12">Govt</h5>';
											if(result.govtCount !=null && result.govtCount>0){
												str+='<h4 class="text-bold m_top5 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="OverAll" attr_name="Actionable Govt Meetings" attr_subSearchType="Govt">'+result.govtCount+'</span> <small style="color:green;" class="pull-right m_top5">'+result.govtCntPer+'%</small></h4>';
											}else{
												str+='<h4 class="text-bold m_top5 f_16"> - </h4>';
											}
											
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
			str+='<div class="panel-group m_top20" id="MOMCollapse'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-blue">';
			  str+='<div class="panel-heading" id="headingsMOM'+i+'" style="padding-top: 8px;padding-bottom: 8px;">';
				  str+='<a class="collapsed collapseMOMIcon" role="button" data-toggle="collapse" data-parent="#MOMCollapse'+i+'" href="#collapsesMOM'+i+'" aria-expanded="false" aria-controls="collapsesMOM'+i+'">';
					   str+='<h3 class="panel-title" style="font-size: 15px;">'+result.partyMettingsVOList[i].name+' Level</h3>';
					str+='</a>';
				
			 str+=' </div>';
			 str+='<div id="collapsesMOM'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingsMOM'+i+'">';
			 str+='<div class="panel-body">';
			 
				str+='<div class="row">';
					str+='<div class="col-sm-3">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">Planned Meetings</h5>';
							if(result.partyMettingsVOList[i].plannedCount !=null && result.partyMettingsVOList[i].plannedCount>0){
								str+='<h4 class="text-bold m_top10 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="PlannedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Planned Meetings" attr_subSearchType="">'+result.partyMettingsVOList[i].plannedCount+'</span></h4>';
							}else{
								str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
							}
							
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">Total Conducted Meetings</h5>';
							if(result.partyMettingsVOList[i].conductedCount !=null && result.partyMettingsVOList[i].conductedCount>0){
								str+='<h4 class="text-bold m_top10 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalConductedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Total Conducted Meetings" attr_subSearchType="">'+result.partyMettingsVOList[i].conductedCount+'</span> <small style="color:green;">'+result.partyMettingsVOList[i].conductedCommentCntPer+'%</small></h4>';
							}else{
								str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
							}
							
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-5">';
						str+='<div class="bg_yash_color">';
							str+='<h5 class="f_12">MOM Not Updated <br/>Meetings</h5>';
							if(result.partyMettingsVOList[i].notUpdatedMomCount !=null && result.partyMettingsVOList[i].notUpdatedMomCount>0){
								str+='<h4 class="text-bold m_top10 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="MOM Not Updated Meetings" attr_subSearchType="">'+result.partyMettingsVOList[i].notUpdatedMomCount+'</span> <small style="color:green;">'+result.partyMettingsVOList[i].notUpdatedMomCntPer+'%</small></h4>';
							}else{
								str+='<h4 class="text-bold m_top10 f_16"> - </h4>';
							}
							
						str+='</div>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="bg_light_yash m_top10">';
				str+='<span class="text-invert">MOM</span>';
					str+='<div class="row">';
						str+='<div class="col-sm-3">';
							str+='<div class="border_pad">';
								str+='<h5 class="f_12">Total</h5>';
								if(result.partyMettingsVOList[i].totalMoms !=null && result.partyMettingsVOList[i].totalMoms>0){
									str+='<h4 class="text-bold m_top25 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Total MOM Meetings" attr_subSearchType="">'+result.partyMettingsVOList[i].totalMoms+'</span> </h4>';
								}else{
									str+='<h4 class="text-bold m_top25 f_16"> -  </h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<div class="border_pad">';
								str+='<h5 class="f_12">General</h5>';
								if(result.partyMettingsVOList[i].generalCount !=null && result.partyMettingsVOList[i].generalCount>0){
									str+='<h4 class="text-bold m_top25 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="General Meetings" attr_subSearchType="General">'+result.partyMettingsVOList[i].generalCount+'</span> <small style="color:green;">'+result.partyMettingsVOList[i].generalCntPer+'%</small></h4>';
								}else{
									str+='<h4 class="text-bold m_top25 f_16"> - </h4>';
								}
								
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<div class="border_pad">';
							if(result.partyMettingsVOList[i].actionCount !=null && result.partyMettingsVOList[i].actionCount>0){
								str+='<h5 class="f_12">Actionable : <span style="font-size:16px;font-weight:bold;"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Total Actionable Meetings" attr_subSearchType="Actionable">'+result.partyMettingsVOList[i].actionCount+'</span> <small style="color:green;">'+result.partyMettingsVOList[i].actionCntPer+'%</small></span></h5>';
							}else{
								str+='<h5 class="f_12">Actionable :  - </h5>';
							}
								
									
									str+='<div style="">';
										str+='<div class="row">';
											str+='<div class="col-sm-6 m_top5" style="border-right:1px solid #ccc;">';
												str+='<h5 class="f_12">Party</h5>';
												if(result.partyMettingsVOList[i].partyCount !=null && result.partyMettingsVOList[i].partyCount>0){
													str+='<h4 class="text-bold m_top5 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Actionable Party Meetings" attr_subSearchType="Party">'+result.partyMettingsVOList[i].partyCount+'</span> <small style="color:green;" class="pull-right m_top5">'+result.partyMettingsVOList[i].partyCntPer+'%</small></h4>';
												}else{
													str+='<h4 class="text-bold m_top5 f_16"> - </h4>';
												}
												
											str+='</div>';
											str+='<div class="col-sm-6 m_top5">';
												str+='<h5 class="f_12">Govt</h5>';
												if(result.partyMettingsVOList[i].govtCount !=null && result.partyMettingsVOList[i].govtCount>0){
													str+='<h4 class="text-bold m_top5 f_16"><span class="meetingDetailsCls" attr_meetinglevelId="'+result.partyMettingsVOList[i].id+'" attr_searchType="TotalMOM" attr_searchLevelId="0" attr_searchLevelValue="1" attr_type="'+result.partyMettingsVOList[i].name+'" attr_name="Actionable Govt Meetings" attr_subSearchType="Govt">'+result.partyMettingsVOList[i].govtCount+'</span> <small style="color:green;" class="pull-right m_top5">'+result.partyMettingsVOList[i].govtCntPer+'%</small></h4>';
												}else{
													str+='<h4 class="text-bold m_top5 f_16"> - </h4>';
												}
												
											str+='</div>';
										str+='</div>';
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
	/* if(result.partyMettingsVOList.length > 2)
		{
			$(".MOMMainScroll").mCustomScrollbar({setHeight:'297px'});
		} */
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
							 colors: ['#FF3A55','#95F895'],
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
							colors: ['#FF3A55','#95F895'],
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
		getMOMDetailedBlockDetailsAction(levelWiseMOMArr[i].name,levelWiseMOMArr[i].id);
	}	
}
function getMOMDetailedBlockDetailsAction(locationType,locationId)
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
			buildMOMDetailedBlockDetailsAction(result,locationType,locationId);
		}else{
			$("#MOMLevel"+locationType).html("No Data Available");
		}
	});
}

function buildMOMDetailedBlockDetailsAction(result,locationType,locationId){
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
						if(result.partyMettingsVOList[i].conductedCount !=null && result.partyMettingsVOList[i].conductedCount>0){
							str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalConductedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total Conducted Meetings" attr_subSearchType="">'+result.partyMettingsVOList[i].conductedCount+'</span></td>';
						}else{
							str+='<td>-</td>';
						}
						
						if(locationType == "district"){
							if(result.partyMettingsVOList[i].notUpdatedCount !=null && result.partyMettingsVOList[i].notUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total MOM Not Updated Meetings" attr_subSearchType="Total">'+result.partyMettingsVOList[i].notUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].mandalNotUpdatedCount !=null && result.partyMettingsVOList[i].mandalNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Mandal MOM Not Updated Meetings" attr_subSearchType="Mandal">'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].constituencyNotUpdatedCount !=null && result.partyMettingsVOList[i].constituencyNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="AC MOM Not Updated Meetings" attr_subSearchType="AC">'+result.partyMettingsVOList[i].constituencyNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].districtNotUpdatedCount !=null && result.partyMettingsVOList[i].districtNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="District MOM Not Updated Meetings" attr_subSearchType="District" >'+result.partyMettingsVOList[i].districtNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							
						}else if(locationType == "mandal"){
							if(result.partyMettingsVOList[i].notUpdatedCount !=null && result.partyMettingsVOList[i].notUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total MOM Not Updated Meetings" attr_subSearchType="Total">'+result.partyMettingsVOList[i].notUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].mandalNotUpdatedCount !=null && result.partyMettingsVOList[i].mandalNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Mandal MOM Not Updated Meetings" attr_subSearchType="Mandal">'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							
						}else{
							if(result.partyMettingsVOList[i].notUpdatedCount !=null && result.partyMettingsVOList[i].notUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total MOM Not Updated Meetings" attr_subSearchType="Total">'+result.partyMettingsVOList[i].notUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].mandalNotUpdatedCount !=null && result.partyMettingsVOList[i].mandalNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Mandal MOM Not Updated Meetings" attr_subSearchType="Mandal">'+result.partyMettingsVOList[i].mandalNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							if(result.partyMettingsVOList[i].constituencyNotUpdatedCount !=null && result.partyMettingsVOList[i].constituencyNotUpdatedCount>0){
								str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="MOMNotUpdatedMeetings" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="AC MOM Not Updated Meetings" attr_subSearchType="AC">'+result.partyMettingsVOList[i].constituencyNotUpdatedCount+'</span></td>';
							}else{
								str+='<td>-</td>';
							}
							
							
						}
						if(result.partyMettingsVOList[i].totalMoms != null && result.partyMettingsVOList[i].totalMoms > 0){
							str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total MOM Meetings" attr_subSearchType="Total1">'+result.partyMettingsVOList[i].totalMoms+'</span></td>';
						}else{
							str+='<td>-</td>';
						}
						if(result.partyMettingsVOList[i].generalCount != null && result.partyMettingsVOList[i].generalCount > 0){
							str+='<td><span class="meetingDetailsCls"  attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="MOM General Meetings" attr_subSearchType="General1">'+result.partyMettingsVOList[i].generalCount+'</span></td>';
							str+='<td>'+result.partyMettingsVOList[i].generalCntPer+'</td>';
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
						}
						
						
						if(result.partyMettingsVOList[i].actionCount != null && result.partyMettingsVOList[i].actionCount > 0){
							str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total Actionable Meetings" attr_subSearchType="Actionable1">'+result.partyMettingsVOList[i].actionCount+'</span></td>';
							str+='<td>'+result.partyMettingsVOList[i].actionCntPer+'</td>';
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
						}
						
						if(result.partyMettingsVOList[i].partyCount != null && result.partyMettingsVOList[i].partyCount > 0){
							str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total Actionable Party Meetings" attr_subSearchType="Party1">'+result.partyMettingsVOList[i].partyCount+'</span></td>';
							str+='<td>'+result.partyMettingsVOList[i].partyCntPer+'</td>';
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
						}
						
						if(result.partyMettingsVOList[i].govtCount != null && result.partyMettingsVOList[i].govtCount > 0){
							str+='<td><span class="meetingDetailsCls" attr_meetinglevelId="0" attr_searchType="TotalMOM" attr_searchLevelId="'+locationId+'" attr_searchLevelValue="'+result.partyMettingsVOList[i].id+'" attr_type="'+locationType+'" attr_name="Total Actionable Govt Meetings" attr_subSearchType="Govt1">'+result.partyMettingsVOList[i].govtCount+'</span></td>';
							str+='<td>'+result.partyMettingsVOList[i].govtCntPer+'</td>';
						}else{
							str+='<td>-</td>';
							str+='<td>-</td>';
						}
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

 $(document).on("click",".meetingDetailsCls",function(){
	$("#momModalDivId").modal("show");
	$("#docsViewModalId").html('');
	var meetinglevelId = $(this).attr("attr_meetinglevelId");
	var searchType = $(this).attr("attr_searchType");
	var searchLevelId = $(this).attr("attr_searchLevelId");
	var searchLevelValue = $(this).attr("attr_searchLevelValue");
	var levelName = $(this).attr("attr_type");
	var name = $(this).attr("attr_name");
	var subsearchtype = $(this).attr("attr_subsearchtype");
	if(levelName == "OverAll"){
		$("#levelWiseMeetingPopupHeadingId").html(name+" Details - "+levelName+"");
	}else{
		$("#levelWiseMeetingPopupHeadingId").html(name+" Details - "+levelName+" Level");
	}
	getPartyMeetingMOMDetailsCompletedCountsClicks(meetinglevelId,searchType,searchLevelId,searchLevelValue,subsearchtype);
});

function getPartyMeetingMOMDetailsCompletedCountsClicks(meetinglevelId,searchType,searchLevelId,searchLevelValue,subsearchtype){
	$("#docsViewModalId").html('');
	meetingDocsListDocuments = [];
	momdocsListImages = [];
	var dateString = currentFromDateMOM+"-"+currentToDateMOM;
	//var dateString = "01/03/2018-14/03/2018";
	var attr_meetinglevelid = meetinglevelId;
	var sourceTypeId=0;
	var momType="";
	var searchTypeVal="";
	
	//alert(searchLevelId);
	if(parseInt(searchLevelId)>0){
		if(parseInt(searchLevelId) == 2)
			searchLevelId = 3;
		else if(parseInt(searchLevelId) == 3)
			searchLevelId = 5;
		else if(parseInt(searchLevelId) == 9)
			searchLevelId = 4;
	}
	  
	var meetinglevelIdArr=[2,3,4,5,6];
	if(searchLevelId != null && parseInt(searchLevelId)==0){
		if(attr_meetinglevelid != null && parseInt(attr_meetinglevelid)>0){
		  meetinglevelIdArr=[];
		  if(parseInt(attr_meetinglevelid)==4){
			meetinglevelIdArr.push(4);
			meetinglevelIdArr.push(5);
			meetinglevelIdArr.push(6);
		  }else{
			meetinglevelIdArr.push(attr_meetinglevelid);
		  }
		}
	}
	
	if(searchType != null && searchType.length>0){
		searchTypeVal=searchType.replace("+"," ");
	}
	
	if(searchType == "Party"){
		sourceTypeId=2;
		searchTypeVal = "Actionable"
	}else if(searchType == "Govt"){
		sourceTypeId=1;
		searchTypeVal = "Actionable"
	}else{
		searchTypeVal = searchType;
	}
	
	$("#meetingLevelDetailsTableDivId").html(spinner);
	  var jsObj ={  
		 activityMemberId:globalActivityMemberId,
		 userTypeId:1,
		 state:"AP",
		 stateId:1,
		 dateString:dateString,
		 partyMeetingTypeArr:[2,3,15],
		 meetinglevelIdArr:meetinglevelIdArr,// mandal - 5 , constituency : 4, district-3       
		 searchType:searchTypeVal,
		 searchLevelId:searchLevelId,
		 searchLevelValue:searchLevelValue,
		 sourceTypeId:sourceTypeId,
		 momType:momType,
		 subSearchtype:subsearchtype
	  };
    $.ajax({
      type : 'POST',
      url : 'getPartyMeetingMOMDetailsCompletedCountsClicks.action',
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			if(searchTypeVal =='TotalMOM')
				buildPartyMeetingMOMDetailsCompletedMOMClicks(result);
			else
				buildPartyMeetingMOMDetailsCompletedCountsClicks(result);
				
		}else{
			$("#meetingLevelDetailsTableDivId").html("No Data Available");
		}
    });
}

function buildPartyMeetingMOMDetailsCompletedCountsClicks(result){
	var str='';
	
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="table-responsive">';
                    str+='<table id="example3" class="table-data table-striped table-bordered" style="width:100%">';
                     str+='<thead>';
                        str+='<tr class="line-height30">';
                          str+='<th rowspan="2">Meeting Level</th>';
                          str+='<th rowspan="2">Meeting Name</th>';
                          str+='<th rowspan="2">Meeting Date</th>';
                          str+='<th colspan="4">MOMS</th>';
                         // str+='<th rowspan="2">Meeting Images</th>';
                         // str+='<th rowspan="2">MOM Documents</th>';
                        str+='</tr>';
						 str+='<tr class="line-height30">';
                          str+='<th>Total</th>';
                          str+='<th>General</th>';
                          str+='<th>Party</th>';
                          str+='<th>Govt</th>';
                        str+='</tr>';
						
                      str+='</thead>';
                      str+='<tbody>';
						for(var i in result){							
							if((result[i].filesList1 != null && result[i].filesList1.length>0) || (result[i].filesList != null && result[i].filesList.length>0)){
								var meetingObj ={
									id:result[i].meetingId,
									fileList:result[i].filesList1
								};
								meetingDocsListDocuments.push(meetingObj);
								var momObj ={
									id:result[i].meetingId,
									fileList:result[i].filesList
								};
								momdocsListImages.push(momObj);
							}
								str+='<tr>';
									str+='<td style="text-align:left !important;">'+result[i].meetingLevel+'</td>';
									str+='<td style="text-align:left !important;">'+result[i].meetingName+'</td>';
									str+='<td>'+result[i].date+'</td>';
									if(result[i].totalCount !=null && result[i].totalCount>0){
										str+='<td>'+result[i].totalCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].generalCount !=null && result[i].generalCount>0){
										str+='<td>'+result[i].generalCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].partyCount !=null && result[i].partyCount>0){
										str+='<td>'+result[i].partyCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].govtCount !=null && result[i].govtCount>0){
										str+='<td>'+result[i].govtCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
									/*if(result[i].filesList1 != null && result[i].filesList1.length>0){
										str+='<td><button type="button" class="btn btn-success btn-sm docsViewCls" attr_meeting_level_id="'+result[i].meetingId+'" attr_meeting_name="'+result[i].meetingName+'" attr_image_type="meetingDocuments">Meeting Images</button></td>';
									}else{
										str+='<td> - </td>';
									}
									if(result[i].filesList != null && result[i].filesList.length>0){
										str+='<td><button type="button" class="btn btn-success btn-sm docsViewCls" attr_meeting_level_id="'+result[i].meetingId+'" attr_meeting_name="'+result[i].meetingName+'" attr_image_type="momMeetingDocuments">MOM Documents</button></td>';
									}else{
										str+='<td> - </td>';
									}
								*/
								str+='</tr>';
						}
					  str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
	  str+='</div>';
    $("#meetingLevelDetailsTableDivId").html(str);
	$("#example3").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10,50, 100, 500, -1], [10,50, 100, 500, "All"]]
	});  
}
 
 
function buildPartyMeetingMOMDetailsCompletedMOMClicks(myResult){
	var str='';
	
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="table-responsive">';
                    str+='<table id="example3" class="table-data table-striped table-bordered" style="width:100%">';
                     str+='<thead>';
                        str+='<tr class="line-height30">';
                          str+='<th>Meeting Level</th>';
                          str+='<th>Meeting Name</th>';
                          str+='<th>Meeting Date</th>';
                          str+='<th>MOM Description</th>';
                          str+='<th> MOM Type </th>';
                          str+='<th>MOM Documents</th>';
                        str+='</tr>';
                      str+='</thead>';
                      str+='<tbody>';
					  for(var j in myResult){
						  var result = myResult[j].minutesList;
							  for(var i in result){							
								if((result[i].filesList1 != null && result[i].filesList1.length>0) || (result[i].filesList != null && result[i].filesList.length>0)){
									var meetingObj ={
										id:result[i].meetingId,
										fileList:result[i].filesList1
									};
									meetingDocsListDocuments.push(meetingObj);
									var momObj ={
										id:result[i].meetingId,
										fileList:result[i].filesList
									};
									momdocsListImages.push(momObj);
								}
									str+='<tr>';
										str+='<td style="text-align:left !important;">'+result[i].meetingLevel+'</td>';
										str+='<td style="text-align:left !important;">'+result[i].meetingName+'</td>';
										if(result[i].date !=null && result[i].date.length>0)
											str+='<td>'+result[i].date+'</td>';
										else{
											str+='<td> - </td>';
										}
										if(result[i].momPoints !=null && result[i].momPoints.length>0)
											str+='<td>'+result[i].momPoints+'</td>';
										else{
											str+='<td> - </td>';
										}
										if(result[i].sourceName !=null && result[i].sourceName.length>0)
											str+='<td>'+result[i].sourceName+'</td>';
										else{
											str+='<td> General </td>';
										}
										if(result[i].filesList != null && result[i].filesList.length>0){
											str+='<td><button type="button" class="btn btn-success btn-sm docsViewCls" attr_meeting_level_id="'+result[i].meetingId+'" attr_meeting_name="'+result[i].meetingName+'" attr_image_type="momMeetingDocuments">MOM Documents</button></td>';
										}else{
											str+='<td> - </td>';
										}
									
									str+='</tr>';
							}
					  }
						
					  str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
	  str+='</div>';
    $("#meetingLevelDetailsTableDivId").html(str);
	$("#example3").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10,50, 100, 500, -1], [10,50, 100, 500, "All"]]
	});  
}

 $(document).on("click",".docsViewCls",function(){
	
	 $('html, body, #momModalDivId').stop().animate({
        scrollTop: $("#docsViewModalId").offset().top
    }, 1500);
  
	   
	 
	var docsList = [];
	var tempDocsList = [];
	var str="";
	var documentMeetingId =[];
	var meetingId = $(this).attr("attr_meeting_level_id");
	var meetingName = $(this).attr("attr_meeting_name");		
		if($(this).attr("attr_image_type") == "meetingDocuments"){
			tempDocsList = meetingDocsListDocuments;
		}else if($(this).attr("attr_image_type") == "momMeetingDocuments"){
			tempDocsList = momdocsListImages;
		}
		
		for(var j in tempDocsList){
			if(parseInt(tempDocsList[j].id)==meetingId){
				docsList = tempDocsList[j].fileList;
			}
		}
		//if ( $.inArray(meetingId.toString(), documentMeetingId) != -1) {
			str+='<div style="padding:10px;border:1px solid #ddd;" class="m_top10">';
				str+='<div class="row">';
					str+='<div class="col-sm-12">';
						str+='<h4 style="font-weight:bold">'+meetingName+'</h4>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="row m_top10">';
				str+='<div class="col-sm-12">';
					if(docsList != null && docsList.length >0){
						for(var j in docsList){
								var fileName = docsList[j].path.split("/"); 
								var scanCopySpl = docsList[j].path.split("."); 
								var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
									str+='<div class="col-sm-4 m_top10">';
										str+='<div class="viewImageCss">';
										if(scanCopyExt =="pdf"){
											str+='<div style="border:1px solid #ccc;padding:5px">';
												str+='<object data="http://mytdp.com/party_meetings/'+docsList[j].path+'" type="application/pdf" width="100%"height="150px;" class="thumbnail m_5"></object>';
												str+='<h5>View Document <a class="fancyboxView image_open_link" href="#inlinePDFD'+j+'"><i class="fa fa-external-link" aria-hidden="true"></i></a></h5>';
											str+='</div>';
											
											str+='<div id="inlinePDFD'+j+'" style="width:100%;display: none;">';
												str+='<object data="http://mytdp.com/party_meetings/'+docsList[j].path+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
												
											str+='</div>';
											
										}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
											str+='<div style="border:1px solid #ccc;padding:5px">';
												str+='<img src="http://mytdp.com/party_meetings/'+docsList[j].path+'"  width="100%" height="150px;" class="thumbnail m_5"></img>';
												str+='<h5>View Document <a class="fancyboxView image_open_link" href="#inlineImgeD'+j+'"><i class="fa fa-external-link" aria-hidden="true"></i></a></h5>';
											str+='</div>';
												
											str+='<div id="inlineImgeD'+j+'" style="width:100%;display: none;">';
												str+='<img src="http://mytdp.com/party_meetings/'+docsList[j].path+'"   style="cursor:pointer;height:1000px;width:1000px"></object>';
												
											str+='</div>';
										}else{
											str+='<b>Click <a href="javascript:{};" onclick="openDoc(\'http://mytdp.com/party_meetings/'+docsList[j].path+'\')">Here</a> To View Document</b>';
											
										}
							
								str+='</div>';
							str+='</div>';

						}
					//}	
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}	
	$("#docsViewModalId").html(str);
	$(".fancyboxView").fancybox();
});