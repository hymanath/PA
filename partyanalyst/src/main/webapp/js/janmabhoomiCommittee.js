var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var blockLevel="";
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
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="3" attr_location_value="'+result[i].id+'" attr_type="name">'+result[i].name+'</span></td>';
						}else if(blockType == "parliament"){
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="10" attr_location_value="'+result[i].id+'" attr_type="name">'+result[i].name+'</span></td>';
						}else if(blockType == "constituency"){
							str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="4" attr_location_value="'+result[i].id+'" attr_type="name">'+result[i].name+'</span></td>';
						}
						
					}else{
						str+='<td>'+result[i].name+'</td>';
					}
					if(blockType == "district"){
						str+='<td style="color:'+statusColorobj[result[i].statusType.trim()]+'">'+result[i].statusType+'</td>';
					}
						for(var j in result[i].list){
							totalCount =result[i].list[j].notStartedCommitteeCnt+result[i].list[j].inprogressCommitteeCnt+result[i].list[j].readyForApprovelCommitteeCnt+result[i].list[j].totalApprovedCommitteeCnt+result[i].list[j].submitedCommittees
							var levelId=0;
							if(blockType == "district"){
								levelId = 3
							}else if(blockType == "parliament"){
								levelId = 10
							}else if(blockType == "constituency"){
								levelId = 4
							}
								
								if(totalCount >0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="total" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count" block_level="'+blockType+'">'+totalCount+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].list[j].notStartedCommitteeCnt !=null && result[i].list[j].notStartedCommitteeCnt>0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="Not Started" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count block_level="'+blockType+'">'+result[i].list[j].notStartedCommitteeCnt+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].list[j].inprogressCommitteeCnt !=null && result[i].list[j].inprogressCommitteeCnt>0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="Inprogress" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count" block_level="'+blockType+'">'+result[i].list[j].inprogressCommitteeCnt+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].list[j].readyForApprovelCommitteeCnt !=null && result[i].list[j].readyForApprovelCommitteeCnt>0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="readyforapproval" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count block_level="'+blockType+'">'+result[i].list[j].readyForApprovelCommitteeCnt+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].list[j].totalApprovedCommitteeCnt !=null && result[i].list[j].totalApprovedCommitteeCnt>0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="Approved" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count block_level="'+blockType+'">'+result[i].list[j].totalApprovedCommitteeCnt+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result[i].list[j].submitedCommittees !=null && result[i].list[j].submitedCommittees>0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].list[j].id+'" attr_status_type="" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count block_level="'+blockType+'">'+result[i].list[j].submitedCommittees+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
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
	var type = $(this).attr("attr_type");
	blockLevel = $(this).attr("block_level");
	$("#committeeWiseModalOpen").modal("show");
	
	if(type== "name"){
		$(".committeeSelectBoxCls").hide();
		$("#committeeWisePopUpDetailsId").html('');
		getJanmabhoomiCommitteeOverview(committeId,statusType);
	}else{
		$(".committeeSelectBoxCls").show();
		$("#committeeWisePopUpDetailsId").html('');
		getJanmabhoomiCommitteesByLocIdAndCommLvlId(levelValue,levelId,committeId,statusType);
	}
	
});
$(document).on("change","#committesLevelValuesId",function(){
	var committeId = $(this).val();
	var statusType = $(this).attr("attr_status_type")
	$("#committeeWisePopUpDetailsId").html('');
	getJanmabhoomiCommitteeOverview(committeId,statusType);
});
function getJanmabhoomiCommitteeOverview(committeId,statusType){
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
			buildJanmabhoomiCommitteeOverview(result,statusType,committeId);
		}else{
			$("#committeeWisePopUpDetailsId").html("No Data Available");
		}
	});
  }

  
$(document).on("click",".memberAddEditDetailsCls",function(){
	var type = $(this).attr("attr_type");
	var committeeId = $(this).attr("attr_committee_id");
	var roleId = $(this).attr("attr_role_id");
	$("#memberAddEditModalOpen").modal("show");
	var memberId = $(this).attr("attr_member_id");
	var memberName = $(this).attr("attr_member_name");
	var voterCardNo = $(this).attr("attr_voterCard_no");
	var mobileNo = $(this).attr("attr_mobile_no");
	var memberShipId = $(this).attr("attr_membership_no");
	var statusType = $(this).attr("attr_status_type");
	
	$("#memberAddEditPopUpDetailsId").html('');
	$("#memberAddedPopUpDetailsId").html('')
	buildMemberAddEditDetailsBlock(type,roleId,memberId,memberName,voterCardNo,mobileNo,memberShipId,committeeId,statusType);
});
$(document).on("click",".closeShowPdfCls",function(){
	setTimeout(function(){
	$('body').addClass("modal-open");
	}, 500);                     
});
	
function buildMemberAddEditDetailsBlock(type,roleId,memberId,memberName,voterCardNo,mobileNo,memberShipId,committeeId,statusType){
	
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
					str+='<option value="36">Telangana</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="districtDiv">';
				str+='<label>District</label>';
				str+='<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="districtId" class="chosen-select" >';
				str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="constitunecyDiv">';
				str+='<label>Constituency</label>';
				str+='<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="constituencyId"  class="chosen-select" >';
				str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="mandalDiv">';
				str+='<label>Mandal/Muncipality/Corporation</label>';
				str+='<span id="mandalListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="mandalList" class="chosen-select">';
					str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="panchayatDiv">';
				str+='<label>Panchayat/Ward/Division/City</label>';
				str+='<span id="panchaytListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="panchaytList" class="chosen-select">';
					str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top10">';
		
			str+='<div class="col-sm-6 pad_right0">';
				str+='<div class="pad_5 bg_ff" style="border:1px solid #ccc;padding:5px;">';
					str+='<label class="radio-inline">';
						str+='<input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls"  id="membershipId" value="1"/>Membership No';
					str+='</label>';
					str+='<label class="radio-inline">';
					str+='<input type="radio" name="searchBasedOn" class="searchTypeCls border_radius_none" id="voterId"   value="2" />Voter ID';
					str+='</label>';
				str+='</div>';
				str+='<input id="searchValue" class="form-control search-text border_radius_none" type="text">';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10">';
				str+='<button id="clickSearchbutton" class="btn btn-success btn-block btnSearch border_radius_none" id="searchbtn"  attr_role_id="'+roleId+'" attr_status_type="'+type+'" attr_committee_id="'+committeeId+'" attr_member_id="'+memberId+'">SEARCH</button>';
			str+='</div>';
		str+='</div>';
	}else{
		$("#memberAddEditPopUpDetailsId").removeClass("bg_class_Div")
		str+='<form name="addMemberSaving" id="addMember"  method="post" enctype="multipart/form-data">';
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
							str+='<h5><span class="text-bold">Name : </span> <span>'+memberName+'</span></h5>';
								str+='<h5><span class="text-bold">V.Id : </span> <span>'+voterCardNo+'</span></h5>';
								str+='<h5><span class="text-bold">M.Id : </span> <span>'+memberShipId+'</span></h5>';
								str+='<h5><span class="text-bold">Mobile : </span> <span>'+mobileNo+'</span></h5>';
							str+='</td>';
						str+='</tr>';
						str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.committeeId" value="'+committeeId+'"/>';
						str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.id" value="'+memberId+'"/>';
						str+='<tr>';
							 str+='<td colspan="2">';
							 str+='<select class="form-control chosen-select" id="memberStatusChangeId" name="janmabhoomiCommitteeMemberVO.status">';
									str+='<option value="0">Select Status</option>';
									str+='<option value="approval">Approve</option>';
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
	str+='</form>';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-3">';
	str+='<button id="" class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication('+committeeId+',\''+statusType+'\');">Update Member </button> <span id="loadingImgId"><img src="images/search.gif" style="display:none;"/></span>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div id="savingStatusDivId"></div>';
		str+='</div>';
	str+='</div>';
	
	}
	$("#memberAddEditPopUpDetailsId").html(str);
	$(".chosen-select").chosen();
	//$("#searchval").chosen();
}

$(document).on("change","#statesDivId",function(){
	var stateId = $(this).val();
	var id = $(this).attr("id");
	getDistrictsForStates(stateId,id)	
});
$(document).on("change","#districtId",function(){
	var districtId = $(this).val();
	var id = $(this).attr("id");
	getConstituenciesForDistricts(districtId,id)
});
$(document).on("change","#constituencyId",function(){
	var constituencyId = $(this).val();
	var id = $(this).attr("id");
	getMandalDetailsByConstituencyAction(constituencyId,id)
});
$(document).on("change","#mandalList",function(){
	var mandalId = $(this).val();
	var constituencyId = $("#constituencyId").val();
	var id = $(this).attr("id");
	getPanchayatWardByMandalAction(constituencyId,mandalId,id)
});
function getDistrictsForStates(stateId,id){
	$("#statesDivIdImg").show();
	if(id == "statesDivId"){
		$('#districtIdImg').show();
			$("#districtId").empty();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();			
			$("#districtId").append('<option value="0">All</option>');		
			$("#constituencyId").append('<option value="0">All</option>');		
			$("#mandalList").append('<option value="0">All</option>');		
			$("#panchaytList").append('<option value="0">All</option>');
			
			$("#districtId").trigger("chosen:updated");
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
	}
	
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
					//$("#districtId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
				   $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			 }
			$("#districtId").trigger("chosen:updated") 
		}
	});
  }
  
  function getConstituenciesForDistricts(districtId,id){
	$("#districtIdImg").show();
	 if(id == "districtId"){
		 $("#constituencyIdImg").show();
			$("#searchDataImgForConst").show();
			//refreshExistingDetails();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();					
			$("#constituencyId").append('<option value="0">All</option>');		
			$("#mandalList").append('<option value="0">All</option>');		
			$("#panchaytList").append('<option value="0">All/option>');			
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
	 }
	
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
					//$("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
				   $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			 }
			$("#constituencyId").trigger("chosen:updated")  
		}
	});
  }
  function getMandalDetailsByConstituencyAction(constituencyId,id){
	$("#constituencyIdImg").show();
	var constituencyId  =0;
	if(id == "constituencyId"){
		$("#mandalListImg").show();
		constituencyId = $('#constituencyId').val();
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">All</option>');
		$("#panchaytList  option").remove();
		$("#panchaytList").append('<option value="0">All</option>');
	}
	
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
			if(id == "constituencyId"){
				$("#mandalListImg").hide();
				$("#mandalList").empty();
			}
			for(var i in result){
				if(result[i].id == 0){
					//$("#mandalList").append('<option value='+result[i].locationId+'>ALL</option>');
				}else{
				   $("#mandalList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			 }
			 $("#mandalList").trigger("chosen:updated") 
		}
	});
  }
 function getPanchayatWardByMandalAction(constituencyId,mandalId,id){
	$("#mandalListImg").show();
	var mandalId=0;
	var constituencyId = 0; //cadreSearchDtls
	if(id == "mandalList"){	
			$("#panchaytListImg").show();		
			mandalId=$("#mandalList").val();
			constituencyId = $('#constituencyId').val();
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">All</option>');
	}
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
			if(id == "mandalList"){
				$("#panchaytListImg").hide();
				$("#panchaytList").empty();
			}
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
	var roleId = $(this).attr("attr_role_id");
	var committeeId=$(this).attr("attr_committee_id");
	var memberId = $(this).attr("attr_member_id");
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
	
	searchByMemberIdOrVoterId(locationLevel,locationValue,voterMembershipVal,searchType,roleId,statusType,committeeId,memberId);	
});	 

function searchByMemberIdOrVoterId(levelId,levelValue,voterMembershipVal,searchType,roleId,statusType,committeeId,memberId){  
	$("#memberAddedPopUpDetailsId").html(spinner)
	var memberShipCardNo='';
	var voterCardNo     ='';
	var searchType=0;
	$(".searchTypeCls").each(function(){
		if($(this).is(':checked')){
			searchType = $(this).val();
		}
	}); 
	if(searchType == 1){
		memberShipCardNo = voterMembershipVal;
		voterCardNo ='';
	}else{
		memberShipCardNo = '';
		voterCardNo =voterMembershipVal;
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
		  builldLevelWiseMemberDetailsAppend(result,roleId,statusType,committeeId,memberId);
	  }
    });
}
  
  function builldLevelWiseMemberDetailsAppend(result,roleId,statusType,committeeId,memberId){
	  var str='';
	 str+='<div class="row m_top20">';
				str+='<div class="col-sm-12">';
					str+='<div style="padding:8px;border:1px solid #ddd;">';
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
										if(result.name !=null && result.name.trim().length>0){
											str+='<h5><span class="text-bold">Name : </span> <span>'+result.name+'</span></h5>';
										}else{
											str+='<h5><span class="text-bold">Name : </span> <span> - </span></h5>';
										}
										if(result.voterCardNo !=null && result.voterCardNo.trim().length>0){
											str+='<h5><span class="text-bold">V.Id : </span> <span>'+result.voterCardNo+'</span></h5>';
										}else{
											str+='<h5><span class="text-bold">V.Id : </span> <span> - </span></h5>';
										}
										if(result.memberShipCardId !=null && result.memberShipCardId.trim().length>0){
											str+='<h5><span class="text-bold">M.Id : </span> <span>'+result.memberShipCardId+'</span></h5>';
										}else{
											str+='<h5><span class="text-bold">M.Id : </span> <span> - </span></h5>';
										}
										if(result.mobileNumber !=null && result.mobileNumber.trim().length>0){
											str+='<h5><span class="text-bold">Mobile : </span> <span>'+result.mobileNumber+'</span></h5>';
										}else{
											str+='<h5><span class="text-bold">Mobile : </span> <span> - </span></h5>';
										}
									str+='</td>';
								str+='</tr>';
								
								/* str+='<tr>';
									 str+='<td colspan="2">';
									 str+='<label class="checkbox-inline">';
											str+='<input type="checkbox" value="">Select Member';
										  str+='</label>';
									
									  str+='</td>';
								str+='</tr>'; */
								str+='</tbody>';
							str+='</table>';
						str+='</div>';
					str+='</div>';
			
		
		str+='<form name="addMemberSaving" id="addMember"  method="post" enctype="multipart/form-data">';
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
			
			str+='<div class="col-sm-2">';
				str+='<label>';
					str+='<input type="text" class="form-control" id="" placeholder="Enter Name" name="janmabhoomiCommitteeMemberVO.name" >';
				str+='</label>';
			str+='</div>';
			
			str+='<div class="col-sm-2">';
				str+='<label>';
					str+='<input type="text" class="form-control" id="" placeholder="Enter MobileNo" name="janmabhoomiCommitteeMemberVO.mobileNumber">';
				str+='</label>';
			str+='</div>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.designationId" value="'+roleId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.voterId" value="'+result.voterId+'"/>';
			var tdpCadreId = 0;
			if(result.tdpCadreId != null && result.tdpCadreId>0){
				tdpCadreId = result.tdpCadreId;
			}
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.id" value="'+memberId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.tdpCadreId" value="'+tdpCadreId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.enrollmentYrId" value="1"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.status" value="'+statusType+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.committeeId" value="'+committeeId+'"/>';
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
			
			str+='<div class="col-sm-3">';
				str+='<select class="form-control chosen-select" id="partyId" name="janmabhoomiCommitteeMemberVO.partyId">';
					str+='<option value="0">Select Affiliated Party</option>';
					str+='<option value="872">TDP</option>';
					str+='<option value="362">INC</option>';
					str+='<option value="1117">YSRC</option>';
					str+='<option value="163">BJP</option>';
				str+='</select>';
			str+='</div>';
			str+='</div>';	
		str+='</div>';
			str+='</div>';
		str+='</div>';	
		str+='</form>';
		str+='<div class="col-sm-12 m_top20">';
			str+='<div class="col-sm-3">';
				str+='<button class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication('+committeeId+',\''+statusType+'\');">Add Member</button><span id="loadingImgId"><img src="images/search.gif" style="display:none;"/></span>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				str+='<div id="savingStatusDivId"></div>';
			str+='</div>';
		str+='</div>'; 
	  
	$("#memberAddedPopUpDetailsId").html(str);
	 getAllCategoriesAction();
	$(".chosen-select").chosen();
  }
  function getJanmabhoomiCommitteesByLocIdAndCommLvlId(levelValue,levelId,committeeLvlId,statusType){
		$("#committesLevelValuesId").html('');
		$("#committesLevelValuesId").trigger("chosen:updated");
	  var jsObj={
		
 		"fromDate"			:"",
 		"endDate"			:"",
		locationId:parseInt(levelValue),
		levelId:parseInt(levelId),
		committeeLvlId:committeeLvlId,
		status:statusType//NotStarted,Approved,Inprogress, readyforapproval
	}
 	   $.ajax({
 		  type : "POST",
 		  url : "getJanmabhoomiCommitteesByLocIdAndCommLvlIdAction.action",
 		  dataType : 'json',
 		  data : {task :JSON.stringify(jsObj)}
 		}).done(function(result){ 
			 $("#committesLevelValuesId").append('<option value="0">Select Committee</option>')
			 if(result !=null && result.length>0){
				for(var i in result){
					  $("#committesLevelValuesId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
				  }
				  $("#committesLevelValuesId").chosen();
				  $("#committesLevelValuesId").val(result[0].id).trigger("chosen:updated");
				  getJanmabhoomiCommitteeOverview(result[0].id,statusType);
			}else{
				$("#committeeWisePopUpDetailsId").html('No Data Available');
			}
			$("#committesLevelValuesId").attr("attr_status_type",statusType)
			
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
$("#casteId").html("");
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
function savingApplication(committeeId,statusType){
		var uploadHandler = {
			upload: function(o) {
				$("#loadingImgId").css("display","block");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult,committeeId,statusType);
			}
		};
		YAHOO.util.Connect.setForm('addMemberSaving',true);
		YAHOO.util.Connect.asyncRequest('POST','saveJanmabhoomiCommitteeMemberAction.action',uploadHandler);
			
	}
	
	function showSbmitStatus(result,committeeId,statusType){
		$("#loadingImgId").css("display","none");
		if(result.indexOf("SUCCESS") > -1){
			
		  $("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application Received Successfully...</span>");
		 
		  setTimeout(function(){
			 $("#memberAddEditModalOpen").modal("hide");
			 
		  }, 1000);
		  setTimeout(function(){
			$('body').addClass("modal-open");
			getDistrictWiseCommitteeDetails(blockLevel,"level")	
			getJanmabhoomiCommitteeOverview(committeeId,statusType)	
		  }, 2000);
		    
			
		}else if(result.indexOf("DUPLICATE") > -1){
			setTimeout(function(){
		  $("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>This person already added for this committee</span>");
		  }, 1000);
		}else {
		  setTimeout(function(){
		  $("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Application Submission Failed. Please Try Again.</span>");
		  }, 1000);
		}
	  }
	  
	  function saveCommitteeStatus(committeeId){
		  var jsObj={
    "committeeId"  :committeeId
    }
     $.ajax({
      type : "POST",
      url : "updateCommitteStatusByCommiteeIdAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
       if(result != null && result.length >0){
		 if(result.indexOf("SUCCESS") > -1){
			setTimeout(function(){
		  $("#savingStatusDivId").html("<span style='color: green;font-size:22px;'>Committee Status Updated Successfully</span>");
		  }, 1000); 
		 }else if(result.indexOf("NotFilled") > -1){
			 setTimeout(function(){
		  $("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Total committee members are not added</span>");
		  }, 1000);
		 }else{
			 setTimeout(function(){
		  $("#savingStatusDivId").html("<span style='color: red;font-size:22px;'>Committee Status Updation Failed. Please Try Again.</span>");
		  }, 1000); 
		 }
	  }
    });
	  }