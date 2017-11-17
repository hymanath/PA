var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

onLoadCalls();
function onLoadCalls(){
	getJbCommitteeStatusCount();	
	 levelWiseCommitteeData("level")
}
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		colors:['#FF9900','#8D4553','#CCCCCC','#F25C81','#0D9615'],
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}
function getJbCommitteeStatusCount(){
	$("#overAllCommitteeMainBlockId, #committeeWiseDetailsDivId").html(spinner);
	var jsObj={
		"fromDateStr"	:"",
		"toDateStr"	    :""
	}
   $.ajax({
	  type : "POST",
	  url : "getJbCommitteeStatusCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result !=null){
			buildJbCommitteeMainBlockStatusCount(result);
		}else{
			$("#overAllCommitteeMainBlockId").html("No Data Available");
		}
		if(result !=null && result.positinsList !=null && result.positinsList.length>0){
			buildCommitteeWiseDetailsBlock(result);
		}else{
			$("#committeeWiseDetailsDivId").html("No Data Available");
		}
	});
}

function buildJbCommitteeMainBlockStatusCount(result){
	
	var str='';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#F7931D;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.notStartedCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.notStartedCommitteePerc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Not-Started <br>Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#12A89D;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.inprogressCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.inprogressCommitteePerc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b> Inprogress/ <br/>Running <br> Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#20409A;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.readyForApprovelCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.readyForApprovelCommitteeperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Ready for<br>Approvel</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#7E3D97;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.totalApprovedCommitteeCnt+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.totalApprovedCommitteeperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Total Approved<br>Committess</b></h5>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<h4 class="pull-left barLengthCss" style="background-color:#00A651;"> </h4>';
			str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.submitedCommittees+'</b></h3>';
			str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.submitedCommitteesperc+' %</h5>';
			str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>Total District <br>Collector Approved & Letter Submited <br>Committees</b></h5>';
		str+='</div>';
	
	
	var notStartedCount	=parseFloat(result.notStartedCommitteePerc)
	var inProgressCount	=parseFloat(result.inprogressCommitteePerc)
	var readyApprovalCount	=parseFloat(result.readyForApprovelCommitteeperc)
	var approvalCount	=parseFloat(result.totalApprovedCommitteeperc)
	var collectorAppLettSub	=parseFloat(result.submitedCommitteesperc)
	
	var id = 'committeeMainBlockGraphId';
	var type = {
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+(this.y)+"%)</b>";
		}  
	}; 
	var plotOptions ={
		pie: {
			innerSize: 80,
			depth: 25,
			dataLabels: {
				enabled: false,
				formatter: function() {
					return (this.y) + ' %';
				},
				distance: -10,
				color:'#333'
			},
			showInLegend: false
		},
	};
	var legend={enabled: false};
	var data = [{
		name: '',
		data: [
			{
			  name: 'Not-Started',
			  y: notStartedCount,
			  color:"#F7931D"
			},
			{
			  name: 'Inprogress/Running',
			  y: inProgressCount,
			  color:"#4BBCB4"
			},
			{
			  name: 'Ready for Approvel',
			  y: readyApprovalCount,
			  color:"#20409A"
			},
			{
			  name: 'Approved',
			  y: approvalCount,
			  color:"#7E3D97"
			},
			{
			  name: 'Collector Approved Letter Submited',
			  y: collectorAppLettSub,
			  color:"#00A651"
			}
		]
	}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	$("#overAllCommitteeMainBlockId").html(str);
}
function buildCommitteeWiseDetailsBlock(result){
	
	var str='';
	
	str+='<div class="panel panel-default">';
	 str+='<div class="panel-heading panel_white">';
		str+='<h3 class="text_bold">Committee Overview</h3>';
	  str+='</div>';
	  str+='<div class="panel-body">';
		str+='<table class="table table_custom_JB">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Committee Levels</th>';
					str+='<th>Total</th>';
					str+='<th class="text-center" style="color:#F7931D">Not Started</th>';
					str+='<th class="text-center" style="color:#12A89D">Inprogress/ <br/>Running</th>';
					str+='<th class="text-center" style="color:#20409A">Ready for <br/>Approvel</th>';
					str+='<th class="text-center" style="color:#7E3D97">Approved</th>';
					str+='<th class="text-center" style="color:#00A651">Letter <br/>Submited</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result.positinsList){
					str+='<tr>';
						str+='<td>'+result.positinsList[i].name+'</td>';
						str+='<td><h5 class="borderContCss" style="border:1px solid #000;font-weight:bold;">'+result.positinsList[i].totalCommitteeCnt+'</h5></td>';
						str+='<td><h5 class="borderContCss" style="border:1px solid #F7931D"><span class="text_bold">'+result.positinsList[i].notStartedCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].notStartedCommitteePerc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #12A89D"><span class="text_bold">'+result.positinsList[i].inprogressCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].inprogressCommitteePerc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #20409A"><span class="text_bold">'+result.positinsList[i].readyForApprovelCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].readyForApprovelCommitteeperc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #7E3D97"><span class="text_bold">'+result.positinsList[i].totalApprovedCommitteeCnt+'</span> <span class="pull-right">'+result.positinsList[i].totalApprovedCommitteeperc+' %</span></h5></td>';
						
						str+='<td><h5 class="borderContCss" style="border:1px solid #00A651"><span class="text_bold">'+result.positinsList[i].submitedCommittees+'</span> <span class="pull-right">'+result.positinsList[i].submitedCommitteesperc+' %</span></h5></td>';
					str+='</tr>';
				}
				
			str+='</tbody>';
		str+='</table>';
	  str+='</div>';
	str+='</div>';

	$("#committeeWiseDetailsDivId").html(str);
}
function levelWiseCommitteeData(divId)
{
	var levelWiseSBArr = ['district','parliament','constituency'];
	
	var collapse='';
		for(var i in levelWiseSBArr)
		{
			collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black">';
					collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
						if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
						}
						collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' Wise Committee Details</h4>';
							
						collapse+='</a>';
					collapse+='</div>';
					if(i == 0)
					{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
					}else{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
					}
					
						collapse+='<div class="panel-body">';
							collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	$("#levelWisecommitteeDetailsDivId").html(collapse);
	for(var i in levelWiseSBArr){
		getDistrictWiseCommitteeDetails(levelWiseSBArr[i],divId);
	}
}
function getDistrictWiseCommitteeDetails(blockType,divId){
	$("#"+divId+blockType).html(spinner);
	
	var jsObj={
		
 		"fromDate"			:"",
 		"endDate"			:"",
		type				:blockType
 	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getDistrictWiseCommitteeDetailsAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			if(result !=null && result.length>0){
				buildDistrictWiseCommitteeDetails(result,blockType,divId);
			}else{
				$("#"+divId+blockType).html("No Data Available");
			}
 		});
  }
  
function buildDistrictWiseCommitteeDetails(result,blockType,divId){
	
	var str='';
	var statusColorobj={"Not Started":"#F7931D","Approved":"#7E3D97","InProgress":"#12A89D","Ready For Approval":"#20409A"}	
	
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed table_custom_level">';
			str+='<thead style="background-color:#CED0D0;">';
				str+='<tr>';
					str+='<th rowspan="2">Location Name</th>';
					if(blockType == "district"){
						str+='<th rowspan="2">District Committee</th>';
					}else{
						str+='<th rowspan="2">Committee</th>';
					}
					
					for(var i in result[0].list){
						var length = result[0].list.length;
						str+='<th colspan="'+length+'">'+result[0].list[i].name+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].list){
					str+='<th style="vertical-align: middle;"><span style="background-color:#000;" class="squareColorCssView"></span>Total</th>';
					str+='<th style="vertical-align: middle;"><span style="background-color:#F7931D;" class="squareColorCssView"></span>Not-started</th>';
					str+='<th style="vertical-align: middle;"><span style="background-color:#12A89D;" class="squareColorCssView"></span>Inprogress/ Running</th>';
					str+='<th style="vertical-align: middle;"><span style="background-color:#20409A;" class="squareColorCssView"></span>Ready&nbsp;for Approvel</th>';
					str+='<th style="vertical-align: middle;"><span style="background-color:#7E3D97;" class="squareColorCssView"></span>Approved</th>';
					str+='<th style="vertical-align: middle;"><span style="background-color:#00A651;" class="squareColorCssView"></span>Letter Submited</th>';
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					var totalCount=0;
					str+='<tr>';
					if(result[i].statusType !=null && result[i].statusType !=""){
						if(blockType == "district"){
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="3" attr_location_value="'+result[i].id+'">'+result[i].name+'</span></td>';
						}else if(blockType == "parliament"){
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="10" attr_location_value="'+result[i].id+'">'+result[i].name+'</span></td>';
						}else if(blockType == "constituency"){
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="4" attr_location_value="'+result[i].id+'">'+result[i].name+'</span></td>';
						}
						
					}else{
						str+='<td>'+result[i].name+'</td>';
					}
						str+='<td style="color:'+statusColorobj[result[i].statusType.trim()]+'">'+result[i].statusType+'</td>';
						for(var j in result[i].list){
							totalCount =result[i].list[j].notStartedCommitteeCnt+result[i].list[j].inprogressCommitteeCnt+result[i].list[j].readyForApprovelCommitteeCnt+result[i].list[j].totalApprovedCommitteeCnt+result[i].list[j].submitedCommittees
							str+='<td>'+totalCount+'</td>';
							str+='<td>'+result[i].list[j].notStartedCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].inprogressCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].readyForApprovelCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].totalApprovedCommitteeCnt+'</td>';
							str+='<td>'+result[i].list[j].submitedCommittees+'</td>';
						}
						
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#"+divId+blockType).html(str);
} 
$(document).on("click",".committeeWiseDetailsClick",function(){
	var committeId = $(this).attr("attr_commiteeId");
	var statusType = $(this).attr("attr_status_type");
	var levelId = $(this).attr("attr_level_id");
	var levelValue = $(this).attr("attr_location_value");
	$("#committeeWiseModalOpen").modal("show");
	getJanmabhoomiCommitteeOverview(committeId,statusType,levelId,levelValue);
});
function getJanmabhoomiCommitteeOverview(committeId,statusType,levelId,levelValue){
	$("#committeeWisePopUpDetailsId").html(spinner);
	var jsObj={
 		"fromDate"			:"",
 		"toDate"			:"",
		committeId			:committeId
 	}
   $.ajax({
	  type : "POST",
	  url : "getJanmabhoomiCommitteeOverviewAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result !=null){
			buildJanmabhoomiCommitteeOverview(result,statusType,levelId,levelValue);
		}else{
			$("#committeeWisePopUpDetailsId").html("No Data Available");
		}
	});
  }

  function buildJanmabhoomiCommitteeOverview(result,statusType,levelId,levelValue){
	  var str='';
	
	str+='<div class="row">';
	
		str+='<div class="col-sm-3">';
			str+='<div class="bordermemberes" style="border:1px solid #0E0E0E;background-color:#DCDCDC">';
				str+='<h5 class="text-bold">Total Memberes</h5>';
				str+='<h4 class="m_top10">'+result.roleMemberCount+'</h4>';
				str+='<ul class="list-inline checkConditionChangeStatusCls">';
				var bcType=false;
				var ocType=false;
				var stType=false;
				var scType=false;
					for(var i in result.desinationVOList){
						for(var j in result.desinationVOList[i].desinationMebersVOList){
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "BC"){
								bcType = true;
							}
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "OC"){
								ocType = true;
							}
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "SC"){
								scType = true;
							}
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "ST"){
								stType = true;
							}
						}
					}
					if(ocType === true){
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss" style="background-color:#777;"></span> OC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> OC</li>';
					}
					if(bcType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> BC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> BC</li>';
					}
					if(scType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> SC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> SC</li>';
					}
					if(stType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> ST</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> ST</li>';
					}
				str+='</ul>';
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #45BC7E;background-color:#D7F1E4">';
				str+='<h5 class="text-bold">Added Members</h5>';
				str+='<h4 class="m_top35">'+result.addedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #75C0EA;background-color:#E2F2FB">';
				str+='<h5 class="text-bold">Not-Added Members</h5>';
				str+='<h4 class="m_top20">'+result.notAddedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #FF735D;background-color:#F6DADA">';
				str+='<h5 class="text-bold">Rejected Members</h5>';
				str+='<h4 class="m_top35">'+result.rejectedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2 border_left">';
			str+='<div class="bordermemberes" style="border:1px solid #ddd;border-radius:10px;">';
				str+='<h5 class="text-bold">Committee Status</h5>';
				str+='<h4 class="m_top35">'+statusType+'</h4>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table_custom_AddEdit">';
				str+='<thead class="bg-DD">';
					str+='<tr>';
						str+='<th>Designation</th>';
						str+='<th>No of Members Required</th>';
						str+='<th>Member Added</th>';
						str+='<th>Mobile Number</th>';
						str+='<th>Voter ID</th>';
						str+='<th>Party</th>';
						str+='<th>Status</th>';
						str+='<th>Add Member</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>'
					for(var i in result.desinationVOList){
						str+='<tr>';
						var length = result.desinationVOList[i].desinationMebersVOList.length+1;
							str+='<td rowspan="'+length+'">'+result.desinationVOList[i].designationName+'</td>';
							str+='<td rowspan="'+length+'">'+result.desinationVOList[i].roleMemberCount+'</td>';
						str+='</tr>';
						if(result.desinationVOList[i].desinationMebersVOList !=null && result.desinationVOList[i].desinationMebersVOList.length>0){
							for(var j in result.desinationVOList[i].desinationMebersVOList){
							str+='<tr>';
								if(result.desinationVOList[i].desinationMebersVOList[j].memeberName !=null && result.desinationVOList[i].desinationMebersVOList[j].memeberName.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].mobileNumber !=null && result.desinationVOList[i].desinationMebersVOList[j].mobileNumber.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].voterId !=null && result.desinationVOList[i].desinationMebersVOList[j].voterId.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'</td>';
								}else{
									str+='<td> - </td>';
								}
								str+='<td>TDP</td>';
								
								if(result.desinationVOList[i].desinationMebersVOList[j].status !=null && result.desinationVOList[i].desinationMebersVOList[j].status.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].status+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result.desinationVOList[i].desinationMebersVOList[j].status == "Approved" || result.desinationVOList[i].desinationMebersVOList[j].status == "Inprogress"){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="edit" attr_level_id="'+levelId+'" attr_location_value="'+levelValue+'">Edit</h5></td>';
								}else if(result.desinationVOList[i].desinationMebersVOList[j].status == "Rejected" || result.desinationVOList[i].desinationMebersVOList[j].status == "" || result.desinationVOList[i].desinationMebersVOList[j].status == null){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="proposal" attr_level_id="'+levelId+'" attr_location_value="'+levelValue+'" attr_role_id="'+result.desinationVOList[i].designationId+'">Add Member</h5></td>';
								}
								
							str+='</tr>';	
							}
						}
						
					}
				str+='</tbody>'
			str+='</table>';
		str+='</div>';
		str+='</div>';
		var approvedBooleanaVal=false;
		for(var i in result.desinationVOList){
			if(result.desinationVOList[i].desinationMebersVOList !=null && result.desinationVOList[i].desinationMebersVOList.length>0){
				for(var j in result.desinationVOList[i].desinationMebersVOList){
					if(result.desinationVOList[i].desinationMebersVOList[j].status == "Approved"){
						approvedBooleanaVal = true;
					}
				}
			}
		}
		
		if(bcType === true && scType === true && stType === true && approvedBooleanaVal === true){
			str+='<div class="col-sm-12 m_top20">';
				str+='<div class="row">';
				
					str+='<div class="col-sm-3">';
						str+='<label>Change Committee Status</label>';
						str+='<select class="form-control chosen-select" id="committeeStatusChangeId">';
							str+='<option value="approve">Approved</option>';
							str+='<option value="reject">Reject</option>';
						str+='</select>';
					str+='</div>';
					
					str+='<div class="col-sm-2">';
						str+='<button type="button" class="btn btn-success btn-sm">Submit</button>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
		}
		
	str+='</div>';
	
	$("#committeeWisePopUpDetailsId").html(str);
	$("#committeeStatusChangeId").chosen();
	$("#committeeStatusChangeId").trigger("chosen:updated");
  }
 
$(document).on("click",".memberAddEditDetailsCls",function(){
	var type = $(this).attr("attr_type");
	var levelId = $(this).attr("attr_level_id");
	var levelValue = $(this).attr("attr_location_value");
	var roleId = $(this).attr("attr_role_id");
	$("#memberAddEditModalOpen").modal("show");
	buildMemberAddEditDetailsBlock(type,levelId,levelValue,roleId);
});
$(document).on("click",".closeShowPdfCls",function(){
	setTimeout(function(){
	$('body').addClass("modal-open");
	}, 500);                     
});
	
function buildMemberAddEditDetailsBlock(type,levelId,levelValue,roleId){
	
	$("#memberAddEditPopUpDetailsId").html(spinner);
	if(type=="edit"){
		$("#memberAddedPopUpDetailsId").html('');
	}
	var str='';
	if(type == "proposal"){
		
		
		str+='<div class="row">';
			 str+='<div class="col-sm-2 m_top10" id="statedisplaydivid">';
				str+='<label>State</label>';
				str+='<span id="statesDivIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="statesDivId" class="chosen-select">';
					str+='<option value="0">All</option>';
					str+='<option value="1">Andhra Pradesh</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="districtDiv">';
				str+='<label>District</label>';
				str+='<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="districtId" class="chosen-select" >';
				
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="constitunecyDiv">';
				str+='<label>Constituency</label>';
				str+='<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="constituencyId"  class="chosen-select" >';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="mandalDiv">';
				str+='<label>Mandal/Muncipality/Corporation</label>';
				str+='<span id="mandalListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="mandalList" class="chosen-select">';
					
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="panchayatDiv">';
				str+='<label>Panchayat/Ward/Division/City</label>';
				str+='<span id="panchaytListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="panchaytList" class="chosen-select">';
					
				str+='</select>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-3 searchTypeCss">';
				str+='<select class="form-control" id="searchtypeVal">';
					str+='<option value="0">Search By</option>';
					str+='<option value="voter">Voter Id</option>';
					str+='<option value="membership">Membership Id</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				str+='<input id="searchValue" class="form-control search-text border_radius_none height_41" type="text">';
			str+='</div>';
			str+='<div class="col-sm-2">';
				str+='<button id="clickSearchbutton" class="btn btn-success border_radius_none height_41 text-bold" type="button" attr_level_id="'+levelId+'" attr_location_value="'+levelValue+'" attr_role_id="'+roleId+'" attr_status_type="'++type'">SEARCH</button>';
			str+='</div>';
		str+='</div>';
	}else{
		str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
			str+='<p>Edit Member Results</p>';
			str+='<div class="row m_top10">';
				str+='<div class="col-sm-4">';
					str+='<table class="table table-bordered">';
						str+='<tbody>';
						
						str+='<tr>';
							str+='<td style="vertical-align: middle; text-align: center;">';
							str+='<img src="images/User.png"/>';
							str+='</td>';
							str+='<td class="line_heightCss">';
							str+='<h5><span class="text-bold">Name : </span> <span>Ramesh</span></h5>';
								str+='<h5><span class="text-bold">V.Id : </span> <span>120563659</span></h5>';
								str+='<h5><span class="text-bold">M.Id : </span> <span>AP012356985</span></h5>';
								str+='<h5><span class="text-bold">Mobile : </span> <span>962365896</span></h5>';
							str+='</td>';
						str+='</tr>';
						
						str+='<tr>';
							 str+='<td colspan="2">';
							 str+='<select class="form-control chosen-select" id="memberStatusChangeId">';
									str+='<option value="0">Select Status</option>';
									str+='<option value="approve">Approve</option>';
									str+='<option value="reject">Reject</option>';
								str+='</select>';
							
							  str+='</td>';
						str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-3">';
			str+='<button id="clickSearchbutton" class="btn btn-success border_radius_none height_41 text-bold" type="button">Update Member</button>';
			
		str+='</div>';
	str+='</div>';
	
	}
	$("#memberAddEditPopUpDetailsId").html(str);
	$(".chosen-select").chosen();
	//$("#searchval").chosen();
}

$(document).on("change","#statesDivId",function(){
	var stateId = $(this).val();
	getDistrictsForStates(stateId)	
});
$(document).on("change","#districtId",function(){
	var districtId = $(this).val();
	getConstituenciesForDistricts(districtId)
});
$(document).on("change","#constituencyId",function(){
	var constituencyId = $(this).val();
	getMandalDetailsByConstituencyAction(constituencyId)
});
$(document).on("change","#mandalList",function(){
	var mandalId = $(this).val();
	var constituencyId = $("#constituencyId").val();
	getPanchayatWardByMandalAction(constituencyId,mandalId)
});
function getDistrictsForStates(stateId){
	$("#statesDivIdImg").show();
	$("#districtId").html('');
	$("#constituencyId").html('');
	$("#mandalList").html('');
	$("#panchaytList").html('');
	
	$("#districtId").trigger("chosen:updated") 
	$("#constituencyId").trigger("chosen:updated") 
	$("#mandalList").trigger("chosen:updated") 
	$("#panchaytList").trigger("chosen:updated") 
   var jsObj=
   {				
		stateId:stateId,
		elmtId:"districtList_d",
		type:"default",
		task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){  
		$("#statesDivIdImg").hide();
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id == 0){
					$("#districtId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
				   $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			 }
			$("#districtId").trigger("chosen:updated") 
		}
	});
  }
  
  function getConstituenciesForDistricts(districtId){
	$("#districtIdImg").show();
	$("#constituencyId").html('');
	$("#mandalList").html('');
	$("#panchaytList").html('');
	
	$("#mandalList").trigger("chosen:updated") 
	$("#panchaytList").trigger("chosen:updated") 
	
   var jsObj=
   {				
		districtId:districtId,
		elmtId:"districtList_d",
		type:"default",
		task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){  
		$("#districtIdImg").hide();
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id == 0){
					$("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
				   $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			 }
			$("#constituencyId").trigger("chosen:updated")  
		}
	});
  }
  function getMandalDetailsByConstituencyAction(constituencyId){
	$("#constituencyIdImg").show();
	$("#mandalList").html('');
	$("#panchaytList").html('');
	$("#panchaytList").trigger("chosen:updated") 
	
   var jsObj=
   {				
		constituencyId:constituencyId,
	}
    $.ajax({
          type:'GET',
          url: 'getMandalDetailsByConstituencyAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){  
		$("#constituencyIdImg").hide();
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id == 0){
					$("#mandalList").append('<option value='+result[i].locationId+'>ALL</option>');
				}else{
				   $("#mandalList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			 }
			 $("#mandalList").trigger("chosen:updated") 
		}
	});
  }
 function getPanchayatWardByMandalAction(constituencyId,mandalId){
	$("#mandalListImg").show();
	$("#panchaytList").html('');
   var jsObj=
   {				
		mandalId:mandalId,
		constituencyId : constituencyId
	}
    $.ajax({
          type:'GET',
          url: 'getPanchayatWardByMandalAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){  
		$("#mandalListImg").hide();
		if(result !=null && result.length>0){
			for(var i in result){
				 $("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			 }
			 $("#panchaytList").trigger("chosen:updated") 
		}
	});
  }
$(document).on("click","#clickSearchbutton",function(){
	
	var voterMembershipVal = $("#searchValue").val();
	var searchType = $("#searchtypeVal").val();
	var statusType = $(this).attr("attr_status_type");
	var stateId=$("#statesDivId").val();
	var districtId=$("#districtId").val();
	var constituencyId=$("#constituencyId").val();
	var mandalId=$("#mandalList").val();
	var panchayatId=$("#panchaytList").val();
	var locationLevel = 0;
	var locationValue = 0;
	var roleId = $(this).attr("attr_role_id"):
	
	if(panchayatId !=0 && panchayatId>0)
	{
		if(panchayatId.substr(0,1) == 1){
			  locationLevel = 6;
		}
		else if(panchayatId.substr(0,1) == 2){
			 locationLevel = 8;
			 
		}								
		locationValue = panchayatId.substr(1);
	}
	else if(mandalId !=0 && mandalId>0)
	{
		if(mandalId.substr(0,1) == 1){
			 locationLevel = 7;
		}
		else if(mandalId.substr(0,1) == 2){
			 locationLevel = 5;
		}
		else if(mandalId.substr(0,1) == 3){
			 locationLevel = 8;
		}
		locationValue = mandalId.substr(1);
	}
	
	else if(constituencyId != 0 && constituencyId>0)
	{
		locationValue = constituencyId;
		locationLevel = 4;	
	}
	else if(districtId != 0 && districtId>0)
	{
		locationValue = districtId;
		locationLevel = 3;
	}
	else if(stateId !=0 && stateId>0){
		locationValue = stateId;
		locationLevel = 2;
	}
	/* 7-localEle
	8-Greater Cites
	9-booth
	10-parliament */
	searchByMemberIdOrVoterId(locationLevel,locationValue,voterMembershipVal,searchType,roleId,statusType);	
});	 

function searchByMemberIdOrVoterId(levelId,levelValue,voterMembershipVal,searchType,roleId,statusType){  
	$("#memberAddedPopUpDetailsId").html(spinner)
	var memberShipCardNo='';
	var voterCardNo     ='';
	if(searchType == "voter"){
		memberShipCardNo = '';
		voterCardNo =voterMembershipVal;
	}else if(searchType == "membership"){
		memberShipCardNo = voterMembershipVal;
		voterCardNo ='';
	}
	
    var jsObj={
    "locationLevel"     :parseInt(levelId),//4
    "locationValue"     :parseInt(levelValue),//323
    "memberShipCardNo"  :memberShipCardNo,//12345678
    "voterCardNo"       :voterCardNo//zdr1655333,XFS0758567
    }
     $.ajax({
      type : "POST",
      url : "searchByMemberIdOrVoterIdAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
      if(result !=null){
		  builldLevelWiseMemberDetailsAppend(result,roleId,statusType);
	  }
    });
}
  
  function builldLevelWiseMemberDetailsAppend(result,roleId,statusType){
	  var str='';
	  
	  str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
				str+='<p>Search Results</p>';
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-4">';
						str+='<table class="table table-bordered">';
							str+='<tbody>';
							
							str+='<tr>';
								str+='<td style="vertical-align: middle; text-align: center;">';
								if(result.imageURL !=null && result.imageURL.trim().length>0){
									str+='<img src="'+result.imageURL+'"/>';
								}else{
									str+='<img src="images/User.png"/>';
								}
								
									
								str+='</td>';
								str+='<td class="line_heightCss">';
								str+='<h5><span class="text-bold">Name : </span> <span>'+result.name+'</span></h5>';
									str+='<h5><span class="text-bold">V.Id : </span> <span>'+result.voterCardNo+'</span></h5>';
									str+='<h5><span class="text-bold">M.Id : </span> <span>'+result.memberShipCardId+'</span></h5>';
									str+='<h5><span class="text-bold">Mobile : </span> <span>'+result.mobileNumber+'</span></h5>';
								str+='</td>';
							str+='</tr>';
							
							str+='<tr>';
								 str+='<td colspan="2">';
								 str+='<label class="checkbox-inline">';
										str+='<input type="checkbox" value="">Select Member';
									  str+='</label>';
								
								  str+='</td>';
							str+='</tr>';
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<form name="addMember" id="addMember"  method="post" enctype="multipart/form-data">';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
			
			str+='<div class="col-sm-2">';
				str+='<label>';
					str+='<input type="text" class="form-control" id="" placeholder="Enter Name" name="janmabhoomiCommitteeMemberVO.memberName" >';
				str+='</label>';
			str+='</div>';
			
			str+='<div class="col-sm-2">';
				str+='<label>';
					str+='<input type="text" class="form-control" id="" placeholder="Enter MobileNo" name="janmabhoomiCommitteeMemberVO.mobileNumber">';
				str+='</label>';
			str+='</div>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.designationId" value="'+roleId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.voterId" value="'+result.voterId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.tdpCadreId" value="'+result.tdpCadreId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.enrollmentYrId" value="1"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.status" value="'+statusType+'"/>';
			str+='<div class="col-sm-2">';
				str+='<select class="form-control chosen-select" id="casteCategoryId" name="janmabhoomiCommitteeMemberVO.categoryId">';
					str+='<option value="0">Select Category</option>';
				str+='</select>';
			str+='</div>';
			
			str+='<div class="col-sm-2">';
				str+='<select class="form-control chosen-select" id="casteId" name="janmabhoomiCommitteeMemberVO.casteId">';
					str+='<option value="0">Select Caste</option>';
				str+='</select>';
			str+='</div>';
			
			str+='<div class="col-sm-2">';
				str+='<select class="form-control chosen-select" id="partyId" name="janmabhoomiCommitteeMemberVO.partyId">';
					str+='<option value="0">Select Party</option>';
					str+='<option value="1117">YSRC</option>';
					str+='<option value="872">TDP</option>';
					str+='<option value="362">INC</option>';
				str+='</select>';
			str+='</div>';
			
			str+='</div>';
		str+='</div>';	
		str+='</form>';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-3">';
				str+='<button id="clickSearchbutton" class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication();">Add Member</button>';
				
			str+='</div>';
		str+='</div>';
	$("#memberAddedPopUpDetailsId").html(str);
	 getAllCategoriesAction();
	$(".chosen-select").chosen();
  }
//getJanmabhoomiCommitteesByLocIdAndCommLvlId();
  
  function getJanmabhoomiCommitteesByLocIdAndCommLvlId(){
	  var jsObj={
		
 		"fromDate"			:"",
 		"endDate"			:"",
		locationId:11,
		levelId:3,
		committeeLvlId:3,
		status:"total"//NotStarted,Approved,Inprogress, readyforapproval
	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getJanmabhoomiCommitteesByLocIdAndCommLvlIdAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			
 		});
  }

function getAllCategoriesAction(){  
    
     $.ajax({
      type : "GET",
      url : "getAllMainCategoriesAction.action",
      dataType : 'json'
    }).done(function(result){ 
      if(result != null && result.length >0){
		  for(var i in result){
			  $("#casteCategoryId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
		  }
		  $("#casteCategoryId").trigger("chosen:updated");
	  }
    });
}

$(document).on("change","#casteCategoryId",function(){
if($(this).val() != 0){
		getStatewiseCastNamesByCasteCategoryGroupIdAction($(this).val());
	}
	
}) ;

//getStatewiseCastNamesByCasteCategoryGroupIdAction();
function getStatewiseCastNamesByCasteCategoryGroupIdAction(casteCategoryId){  
var categoryGrouIdsList=[];
categoryGrouIdsList.push(casteCategoryId);
    var jsObj={
    "categoryGrouIdsList"  :categoryGrouIdsList
    }
     $.ajax({
      type : "POST",
      url : "getStatewiseCastNamesByCasteCategoryGroupIdAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
       if(result != null && result.length >0){
		  for(var i in result){
			  $("#casteId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
		  }
		  $("#casteId").trigger("chosen:updated");
	  }
    });
}
function savingApplication(){
	
			alert(5)
			var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					//showSbmitStatus(uploadResult);
				}
			};
	
			YAHOO.util.Connect.setForm('addMemberSaving',true);
			YAHOO.util.Connect.asyncRequest('POST','saveJanmabhoomiCommitteeMemberAction.action',uploadHandler);
			
	}
		
	}