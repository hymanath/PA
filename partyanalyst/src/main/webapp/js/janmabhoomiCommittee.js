var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var blockLevel;
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
		"fromDateStr"	:"24/11/2012",
		"toDateStr"	    :"24/11/2012"
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
		if(result !=null && result.levelWisecommitteeStatusVOList !=null && result.levelWisecommitteeStatusVOList.length>0){
			buildCommitteeWiseDetailsBlock(result);
		}else{
			$("#committeeWiseDetailsDivId").html("No Data Available");
		}
	});
}

function buildJbCommitteeMainBlockStatusCount(result){
	var chartArr = [];
	var str='';
	    for(var i in result.committeeStatusVOList){
			str+='<div class="col-sm-2">';
				str+='<h4 class="pull-left barLengthCss" style="background-color:'+result.committeeStatusVOList[i].color+';"> </h4>';
				str+='<h3 class="m_top20" style="margin-left: 15px;"><b>'+result.committeeStatusVOList[i].statusCount+'</b></h3>';
				str+='<h5 class="m_top20" style="margin-left: 15px;color:#12A89D;">'+result.committeeStatusVOList[i].statusPercentage+' %</h5>';
				str+='<h5 class="m_top10" style="margin-left: 15px;line-height: 20px;""><b>'+result.committeeStatusVOList[i].status+
				'<br>committees</b></h5>';
		    str+='</div>';
			
			var statusObj = {};
				statusObj =
				{
					 "name"  : result.committeeStatusVOList[i].status,
					 "y"     : parseFloat(result.committeeStatusVOList[i].statusPercentage),
					 "color" :result.committeeStatusVOList[i].color
				}
			 
			chartArr.push(statusObj)
		}
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
		data: chartArr
	}];
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	$("#overAllCommitteeMainBlockId").html(str);
	$("#committeeMainBlockDivId").html('<h5 class="font_weight" style="font-weight:bold;">Total Committees : <span class="">'+result.totalCommitteeCnt+'</span></h5>');
}
function buildCommitteeWiseDetailsBlock(result){
	
	var str='';
	
	str+='<div class="panel panel-default">';
	 str+='<div class="panel-heading panel_white">';
		str+='<h3 class="text_bold">Committee Overview</h3>';
	  str+='</div>';
	  str+='<div class="panel-body">';
	  str+='<div class="table-responsive">';
		str+='<table class="table table_custom_JB">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Committee Levels</th>';
					str+='<th>Total</th>';
				for(var i in result.committeeStatusVOList){
					str+='<th class="text-center" style="color:'+result.committeeStatusVOList[i].color+';"">'+result.committeeStatusVOList[i].status+'</th>';
				}
					
					/* str+='<th class="text-center" style="color:'+result.committeeStatusVOList[j].color+';"">'+result.committeeStatusVOList[i].status+'</th>';
					str+='<th class="text-center" style="color:#12A89D">Inprogress/ <br/>Running</th>';
					str+='<th class="text-center" style="color:#20409A">Ready for <br/>Approvel</th>';
					str+='<th class="text-center" style="color:#7E3D97">Approved</th>';
					str+='<th class="text-center" style="color:#00A651">Letter <br/>Submited</th>'; */
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result.levelWisecommitteeStatusVOList){
					str+='<tr>';
						str+='<td class="text-bold">'+result.levelWisecommitteeStatusVOList[i].name+'</td>';
							str+='<td><h5 class="borderContCss committeeWiseDetailsClick" style="border:1px solid #000;font-weight:bold;" attr_commiteeId="0" attr_status_type="0" attr_level_id="0" attr_location_value="0" attr_type="count" attr_commitee_lvl_id="'+result.levelWisecommitteeStatusVOList[i].id+'" attr_level_name="'+result.levelWisecommitteeStatusVOList[i].name+'">'+result.levelWisecommitteeStatusVOList[i].totalCommitteeCnt+'</h5></td>';
						for(var j in result.levelWisecommitteeStatusVOList[i].committeeStatusVOList){
							if(result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusCount == 0){
							    str+='<td><h5 class="borderContCss" style="border:1px solid '+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].color+';"><span class="text_bold">'+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusCount+'</span> <span class="pull-right">'+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusPercentage+' %</span></h5></td>';
							}else{
								str+='<td><h5 class="borderContCss committeeWiseDetailsClick" style="border:1px solid '+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].color+';" attr_commiteeId="0" attr_status_type="'+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusId+'" attr_level_id="0" attr_location_value="0" attr_type="count" attr_commitee_lvl_id="'+result.levelWisecommitteeStatusVOList[i].id+'" attr_level_name="'+result.levelWisecommitteeStatusVOList[i].name+'"><span class="text_bold">'+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusCount+'</span> <span class="pull-right">'+result.levelWisecommitteeStatusVOList[i].committeeStatusVOList[j].statusPercentage+' %</span></h5></td>';
							}
						}
					str+='</tr>';
				}		
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
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
		str+='<table class="table table-bordered table-condensed table_custom_level" id="'+blockType+'tableId">';
			str+='<thead style="background-color:#CED0D0;">';
				str+='<tr>';
					if(blockType == "district"){
						str+='<th rowspan="2">District Name</th>';
						str+='<th rowspan="2">District Committee</th>';
					}else if(blockType == "parliament"){
						str+='<th rowspan="2">Parliament Name</th>';
					}else if(blockType == "constituency"){
						str+='<th rowspan="2">District Name</th>';
						str+='<th rowspan="2">Constituency Name</th>';
					}
					for(var i in result[0].list){
						var length = result[0].list.length;
						str+='<th colspan="'+length+'">'+result[0].list[i].name+'</th>';
					}
				str+='</tr>';
				str+='<tr>';
				
				for(var i in result[0].list){
					str+='<th style="vertical-align: middle;"><span style="background-color:#000;" class="squareColorCssView"></span>Total</th>';
					for(var j in result[0].list[i].positinsList){
						if(result[0].list[i].positinsList[j] != null){
						str+='<th style="vertical-align: middle;"><span style="background-color:'+result[0].list[i].positinsList[j].color+';" class="squareColorCssView"></span>'+result[0].list[i].positinsList[j].name+'</th>';
						}
					}
					
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					var totalCount=0;
					str+='<tr>';
					if(result[i].statusType !=null && result[i].statusType !=""){
						if(blockType == "district"){
							str+='<td><span attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="3" attr_location_value="'+result[i].id+'" attr_type="name" attr_commitee_lvl_id="0">'+result[i].name+'</span></td>';
						}else if(blockType == "parliament"){
							str+='<td><span attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="10" attr_location_value="'+result[i].id+'" attr_type="name" attr_commitee_lvl_id="0">'+result[i].name+'</span></td>';
						}else if(blockType == "constituency"){
							str+='<td><span attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="4" attr_location_value="'+result[i].id+'" attr_type="name" attr_commitee_lvl_id="0">'+result[i].name+'</span></td>';
						}
						
					}else{
						if(blockType == "constituency"){
							str+='<td>'+result[i].districtName+'</td>';
						}
						str+='<td>'+result[i].name+'</td>';
					}
					if(blockType == "district"){
						str+='<td style="color:'+result[i].statusType.trim()+'"><span class="committeeWiseDetailsClick" attr_commiteeId="'+result[i].committeeId+'" attr_status_type="'+result[i].statusType+'" attr_level_id="4" attr_location_value="'+result[i].id+'" attr_type="name" attr_commitee_lvl_id="1" attr_level_name="'+result[i].name+'">'+result[i].statusType+'</span></td>';
					}
						for(var j in result[i].list){
							//totalCount =result[i].list[j].notStartedCommitteeCnt+result[i].list[j].inprogressCommitteeCnt+result[i].list[j].readyForApprovelCommitteeCnt+result[i].list[j].totalApprovedCommitteeCnt+result[i].list[j].submitedCommittees
							totalCount =result[i].list[j].count;
							var levelId=0;
							if(blockType == "district"){
								levelId = 3
							}else if(blockType == "parliament"){
								levelId = 10
							}else if(blockType == "constituency"){
								levelId = 4
							}
								
								if(totalCount >0){
									str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="0" attr_status_type="0" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count" block_level="'+blockType+'" attr_commitee_lvl_id="'+result[i].list[j].id+'" attr_level_name="'+result[i].list[j].name+'">'+totalCount+'</span></td>';
								}else{
									str+='<td> - </td>';
								}
								for(var k in result[i].list[j].positinsList){
									if(result[i].list[j].positinsList[k] != null){
										if(result[i].list[j].positinsList[k].count !=null && result[i].list[j].positinsList[k].count>0){
											str+='<td><span class="committeeWiseDetailsClick" attr_commiteeId="0" attr_status_type="'+result[i].list[j].positinsList[k].id+'" attr_level_id="'+levelId+'" attr_location_value="'+result[i].id+'" attr_type="count" block_level="'+blockType+'" attr_commitee_lvl_id="'+result[i].list[j].id+'" attr_level_name="'+result[i].list[j].name+'">'+result[i].list[j].positinsList[k].count+'</span></td>';
										}else{
											str+='<td> - </td>';
										}
									}
								}
								
							}
						
					str+='</tr>';
				}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	$("#"+divId+blockType).html(str);
	if(blockType != "district"){
		$("#"+blockType+"tableId").dataTable();
	}
} 
$(document).on("click",".committeeWiseDetailsClick",function(){
	var committeId = $(this).attr("attr_commiteeId");
	var statusType = $(this).attr("attr_status_type");
	var levelId = $(this).attr("attr_level_id");
	var levelValue = $(this).attr("attr_location_value");
	var type = $(this).attr("attr_type");
	var committeeLvlId = $(this).attr("attr_commitee_lvl_id");
	var levelName = $(this).attr("attr_level_name");
	blockLevel = $(this).attr("block_level");
	$("#committeeWiseModalOpen").modal("show");
	if(committeeLvlId == 1){
		$("#committesLevelNameId").html("District Level - "+levelName +" Committee Member Details");
	}
	if(type== "name"){
		$(".committeeSelectBoxCls").hide();
		$("#committeeWisePopUpDetailsId").html('');
		getJanmabhoomiCommitteeOverview(committeId);
	}else{
		$(".committeeSelectBoxCls").show();
		$("#committeeWisePopUpDetailsId").html('');
		getJanmabhoomiCommitteesByLocIdAndCommLvlId(levelValue,levelId,committeeLvlId,statusType,levelName);
	}
	
});
$(document).on("change","#committesLevelValuesId",function(){
	var committeId = $(this).val();
	var statusType = $(this).attr("attr_status_type")
	var levelName = $(this).attr("attr_level_name")
	var selectedOptionName = $(this).find('option:selected').text();
	$("#committesLevelNameId").html(levelName+" Level - "+selectedOptionName+" Committee Member Details");
	$("#committeeWisePopUpDetailsId").html('');
	getJanmabhoomiCommitteeOverview(committeId);
});
function getJanmabhoomiCommitteeOverview(committeId){
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
			buildJanmabhoomiCommitteeOverview(result,committeId);
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
	var publicRepreTypeId = $(this).attr("attr_publicRepre_typeId");
	var committeeLvlId = $(this).attr("attr_committee_lvl_id");
	var committeeLvlVal = $(this).attr("attr_committee_lvl_val");
	
	var stateId = $(this).attr("attr_state_id");
	var districtId = $(this).attr("attr_district_id");
	var constituencyId = $(this).attr("attr_constituency_id");
	var mandalId = $(this).attr("attr_mandal_id");
	var panchayatId = $(this).attr("attr_panchayat_id");
	var localElectionBodyId = $(this).attr("attr_local_election_body");
	var wardId = $(this).attr("attr_ward_id");
	$("#memberAddEditPopUpDetailsId").html('');
	$("#memberAddedPopUpDetailsId").html('');
	//var committeeLevlVal=0;
	if(committeeLvlId != null && committeeLvlId ==2 ){
		mandalId = '2'+mandalId;
	}else if(committeeLvlId != null && (committeeLvlId ==3 || committeeLvlId ==4 )){
		mandalId = '1'+localElectionBodyId;
	}else if(committeeLvlId != null && committeeLvlId ==5 ){
		mandalId = '2'+mandalId;
		panchayatId = '1'+panchayatId;
	}else if(committeeLvlId != null && (committeeLvlId ==6 || committeeLvlId ==7)){
		mandalId = '2'+mandalId;
		panchayatId='2'+wardId;
	}
	if(publicRepreTypeId != null && publicRepreTypeId >0){
		getAdvancedSearchDetails(publicRepreTypeId,committeeLvlId,committeeLvlVal,committeeId,"approval",roleId,memberId,memberName,
		voterCardNo,mobileNo,memberShipId,stateId,districtId,constituencyId,mandalId,panchayatId);
	}else{
		buildMemberAddEditDetailsBlock(type,roleId,memberId,memberName,
		voterCardNo,mobileNo,memberShipId,committeeId,statusType,stateId,districtId,constituencyId,mandalId,panchayatId);
	}
});
$(document).on("click",".closeShowPdfCls",function(){
	setTimeout(function(){
	$('body').addClass("modal-open");
	}, 500);                     
});
	
function buildMemberAddEditDetailsBlock(type,roleId,memberId,memberName,voterCardNo,
mobileNo,memberShipId,committeeId,statusType,stateId,districtId,constituencyId,mandalId,panchayatId){
	$("#memberAddEditPopUpDetailsId").html(spinner);
	if(type=="edit"){
		$("#memberAddedPopUpDetailsId").html('');
	}
	var str='';
	if(type == "approval"){
		
		str+='<div class="row">';
			 str+='<div class="col-sm-2 m_top10" id="statedisplaydivid">';
				str+='<label>State</label>';
				str+='<span id="statesDivIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="statesDivId" class="chosen-select">';
					str+='<option value="0">All</option>';
					str+='<option value="1" selected>Andhra Pradesh</option>';
					//str+='<option value="36">Telangana</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="districtDiv">';
				str+='<label>District</label>';
				str+='<span id="districtIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="districtId"  attr_committee_dist="'+districtId+'" class="chosen-select" >';
				str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-2 m_top10" id="constitunecyDiv">';
				str+='<label>Constituency</label>';
				str+='<span id="constituencyIdImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="constituencyId"  attr_committee_const="'+constituencyId+'" class="chosen-select" >';
				str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="mandalDiv">';
				str+='<label>Mandal/Muncipality/Corporation</label>';
				str+='<span id="mandalListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="mandalList" attr_committee_mandal="'+mandalId+'" class="chosen-select">';
					str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10" id="panchayatDiv">';
				str+='<label>Panchayat/Ward/Division/City</label>';
				str+='<span id="panchaytListImg"><img src="images/search.gif" style="display:none;"/></span>';
				str+='<select id="panchaytList" attr_committee_panchayat="'+panchayatId+'"  class="chosen-select">';
					str+='<option value="0">All</option>';
				str+='</select>';
			str+='</div>';
		str+='</div>';
		
		
		str+='<div class="row m_top10">';
		
			str+='<div class="col-sm-6 pad_right0">';
			    str+='<div id="searchErrDiv" style="color:red;"> </div>';
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
						if(memberId != null && memberId>0)
						str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.id" value="'+memberId+'"/>';
						str+='<tr>';
							 str+='<td colspan="2">';
							 str+='<select class="form-control chosen-select" id="memberStatusChangeId" name="janmabhoomiCommitteeMemberVO.status">';
									str+='<option value="0">Select Status</option>';
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
		str+='<div id="addMemberErrDiv" style="color:red;"></div>';
		str+='<div class="col-sm-3">';
	str+='<button id="" class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication('+committeeId+',\''+statusType+'\');">Reject Member </button> <span class="loadingImgId"><img src="images/search.gif" style="display:none;"/></span>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div class="savingStatusDivId"></div>';
		str+='</div>';
	str+='</div>';
	
	}
	$("#memberAddEditPopUpDetailsId").html(str);
	$(".chosen-select").chosen();
	if(type == "approval"){
		getDistrictsForStates(stateId,"statesDivId");
	}
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
			$("#districtId").append('<option value="0">Select District</option>');		
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
		var committeeDist =$("#districtId").attr("attr_committee_dist");
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id == 0){
					//$("#districtId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
					if(committeeDist == "null"){
						$("#districtId").append('<option value='+result[i].id+' >'+result[i].name+'</option>');
					}
					else if(committeeDist == result[i].id){
					    $("#districtId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
					} 
				}
			 }
			$("#districtId").trigger("chosen:updated") 
		}
		getConstituenciesForDistricts(committeeDist,$("#districtId").attr("id"));
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
			$("#constituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">All</option>');		
			$("#panchaytList").append('<option value="0">All</option>');			
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
		var committeeConst =$("#constituencyId").attr("attr_committee_const");
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].id == 0){
					//$("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
				}else{
					if(committeeConst == "null"){
						$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
					else if(committeeConst == result[i].id){
					   $("#constituencyId").append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
					} 
				}
			 }
			$("#constituencyId").trigger("chosen:updated")  
		}
		getMandalDetailsByConstituencyAction(committeeConst,$("#constituencyId").attr("id"));
	});
  }
  function getMandalDetailsByConstituencyAction(constituencyId,id){
	$("#constituencyIdImg").show();
	var constituencyId  =0;
	if(id == "constituencyId"){
		$("#mandalListImg").show();
		constituencyId = $('#constituencyId').val();
		$("#mandalList  option").remove();
		$("#mandalList").append('<option value="0">Select Mandal</option>');
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
		var committeeMandal =$("#mandalList").attr("attr_committee_mandal");
		if(result !=null && result.length>0){
			if(id == "constituencyId"){
				$("#mandalListImg").hide();
				$("#mandalList").empty();
			}
			for(var i in result){
				if(result[i].id == 0){
					//$("#mandalList").append('<option value='+result[i].locationId+'>ALL</option>');
				}else{
					if(committeeMandal == "null"){
						$("#mandalList").append('<option value='+result[i].locationId+' >'+result[i].locationName+'</option>');
					}
					else if(committeeMandal == result[i].locationId){
					   $("#mandalList").append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
					} 
				}
			 }
			 $("#mandalList").trigger("chosen:updated") 
		}
		getPanchayatWardByMandalAction(constituencyId,committeeMandal,$("#mandalList").attr("id"));
	});
  }
 function getPanchayatWardByMandalAction(constituencyId,mandalId,id){
	$("#mandalListImg").show();
	if(id == "mandalList"){	
			$("#panchaytListImg").show();		
			mandalId=$("#mandalList").val();
			constituencyId = $('#constituencyId').val();
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
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
		var committeePanchayat =$("#panchaytList").attr("attr_committee_panchayat");
		if(result !=null && result.length>0){
			if(id == "mandalList"){
				$("#panchaytListImg").hide();
				$("#panchaytList").empty();
			}
			for(var i in result){
				if(committeePanchayat == "null"){
					$("#panchaytList").append('<option value='+result[i].locationId+' >'+result[i].locationName+'</option>');
				}
				else if(committeePanchayat == result[i].locationId){
				  $("#panchaytList").append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				} 
			 }
			 $("#panchaytList").trigger("chosen:updated") 
		}
	});
  }
 /* $(document).on("change",".searchTypeCls",function(){
	  $("#searchErrDiv").html('');
	  $("#searchValue").val('');
	  $("#statesDivId").val(0).trigger("chosen:updated");
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
}) ;*/
$(document).on("click","#clickSearchbutton",function(){
	$("#searchErrDiv").html('');
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
	var memberId =$(this).attr("attr_member_id");
	var roleId = $(this).attr("attr_role_id");
	var committeeId=$(this).attr("attr_committee_id");
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
		if(memberShipCardNo ==null || memberShipCardNo.length ==0 ||memberShipCardNo =="undefined" || memberShipCardNo ==0){
		$("#searchErrDiv").html("Please Enter MemberShip CardNo");
		return;
		}
	}else{
		memberShipCardNo = '';
		voterCardNo =voterMembershipVal;
		var stateId=$("#statesDivId").val();
		var districtId=$("#districtId").val();
		var constituencyId=$("#constituencyId").val();
		var mandalId=$("#mandalList").val();
		var panchayatId=$("#panchaytList").val();
		if(stateId ==null || stateId.length ==0 ||stateId =="undefined" || stateId ==0){
		$("#searchErrDiv").html("Please Select State");
		return;
		}if(districtId ==null || districtId.length ==0 ||districtId =="undefined" || districtId ==0){
		$("#searchErrDiv").html("Please Select District");
		return;
		}if(constituencyId ==null || constituencyId.length ==0 ||constituencyId =="undefined" || constituencyId ==0){
		$("#searchErrDiv").html("Please Select Constituency");
		return;
		}if(mandalId ==null || mandalId.length ==0 ||mandalId =="undefined" || mandalId ==0){
		$("#searchErrDiv").html("Please Select Mandal");
		return;
		}
		/*if(panchayatId ==null || panchayatId.length ==0 ||panchayatId =="undefined" || panchayatId ==0){
		$("#searchErrDiv").html("Please Select Panchayat");
		return;
		}*/
		if(voterCardNo ==null || voterCardNo.length ==0 ||voterCardNo =="undefined" || voterCardNo ==0){
		$("#searchErrDiv").html("Please Enter Voter CardNo");
		return;
		}
	}
	$("#memberAddedPopUpDetailsId").html(spinner)
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
	  }else{
		  $("#memberAddedPopUpDetailsId").html("No Data Available");
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
			str+='<div id="addMemberErrDiv" style="color:red;"></div>';
			str+='<div class="col-sm-2">';
				str+='<label>';
				if(result.name == null)
					result.name="";
					str+='<input type="text" class="form-control" id="memberNameId" placeholder="Enter Name" name="janmabhoomiCommitteeMemberVO.name" value="'+result.name+'">';
				str+='</label>';
			str+='</div>';
			
			str+='<div class="col-sm-2">';
				str+='<label>';
				if(result.mobileNumber == null)
					result.mobileNumber="";
					str+='<input type="text" class="form-control" id="memberMobileNoId" placeholder="Enter MobileNo" name="janmabhoomiCommitteeMemberVO.mobileNumber" value="'+result.mobileNumber+'">';
				str+='</label>';
			str+='</div>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.designationId" value="'+roleId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.voterId" value="'+result.voterId+'"/>';
			var tdpCadreId = 0;
			if(result.tdpCadreId != null && result.tdpCadreId>0){
				tdpCadreId = result.tdpCadreId;
			}
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.tdpCadreId" value="'+tdpCadreId+'"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.enrollmentYrId" value="1"/>';
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.status" value="'+statusType+'"/>';memberId
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.committeeId" value="'+committeeId+'"/>';
			if(memberId != null && memberId>0)
			str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.id" value="'+memberId+'"/>';
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
				str+='<button class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication('+committeeId+',\''+statusType+'\');">Add Member</button><span class="loadingImgId"><img src="images/search.gif" style="display:none;"/></span>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				str+='<div class="savingStatusDivId"></div>';
			str+='</div>';
		str+='</div>'; 
	  
	$("#memberAddedPopUpDetailsId").html(str);
	 getAllCategoriesAction();
	$(".chosen-select").chosen();
  }
  function getJanmabhoomiCommitteesByLocIdAndCommLvlId(levelValue,levelId,committeeLvlId,statusType,levelName){
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
			 //$("#committesLevelValuesId").append('<option value="0">Select Committee</option>')
			 if(result !=null && result.length>0){
				for(var i in result){
					  $("#committesLevelValuesId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
				  }
				  $("#committesLevelValuesId").chosen();
				  $("#committesLevelValuesId").val(result[0].id).trigger("chosen:updated");

	            $("#committesLevelNameId").html(levelName+" Level - "+result[0].name+" Committee Member Details");

				  getJanmabhoomiCommitteeOverview(result[0].id);
			}else{
				$("#committeeWisePopUpDetailsId").html('No Data Available');
			}
			$("#committesLevelValuesId").attr("attr_status_type",statusType)
			$("#committesLevelValuesId").attr("attr_level_name",levelName)
			
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
			  //$("#casteCategoryPubId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
		  }
		  $("#casteCategoryId").trigger("chosen:updated");
		  //$("#casteCategoryPubId").trigger("chosen:updated");
	  }
    });
}

$(document).on("change","#casteCategoryId",function(){//#casteCategoryPubId
if($(this).val() != 0){
		getStatewiseCastNamesByCasteCategoryGroupIdAction($(this).val());
	}
	
}) ;

//getStatewiseCastNamesByCasteCategoryGroupIdAction();
function getStatewiseCastNamesByCasteCategoryGroupIdAction(casteCategoryId){  
$("#casteId").html("");
$("#castePubId").html("");
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
			  //$("#castePubId").append('<option value='+result[i].id+'>'+result[i].name+'</option>')
		  }
		  $("#casteId").trigger("chosen:updated");
		  //$("#castePubId").trigger("chosen:updated");
	  }
    });
}
function savingApplication(committeeId,statusType){
	$("#addMemberErrDiv").html("");
	if(statusType == "approval"){
		var memberNameId = $("#memberNameId").val();
		var memberMobileNoId = $("#memberMobileNoId").val();
		var casteCategoryId = $("#casteCategoryId").val();
		var casteId = $("#casteId").val();
		var partyId = $("#partyId").val();
		if(memberNameId ==null || memberNameId.length ==0 ||memberNameId =="undefined" || memberNameId ==0){
			$("#addMemberErrDiv").html("Please Enter Name");
		return;
		}
		if(memberMobileNoId ==null || memberMobileNoId.length ==0 ||memberMobileNoId =="undefined" || memberMobileNoId ==0){
			$("#addMemberErrDiv").html("Please Enter MobileNo");
		return;
		}
		if(casteCategoryId ==null || casteCategoryId.length ==0 ||casteCategoryId =="undefined" || casteCategoryId ==0){
			$("#addMemberErrDiv").html("Please Select CasteCategory");
		return;
		}
		if(casteId ==null || casteId.length ==0 ||casteId =="undefined" || casteId ==0){
			$("#addMemberErrDiv").html("Please Select Caste");
		return;
		}
		if(partyId ==null || partyId.length ==0 ||partyId =="undefined" || partyId ==0){
			$("#addMemberErrDiv").html("Please Select Party");
		return;
		}
	}else if(statusType =="undefined"){ 
		var memberStatusChangeId = $("#memberStatusChangeId").val();
		if(memberStatusChangeId ==null || memberStatusChangeId.length ==0 ||memberStatusChangeId =="undefined" || memberStatusChangeId ==0){
				$("#addMemberErrDiv").html("Please Select Status");
			return;
		}
	}
	$(".savingStatusDivId").html(spinner);
		var uploadHandler = {
			upload: function(o) {
				$(".loadingImgId").css("display","block");
				uploadResult = o.responseText;
				showSbmitStatus(uploadResult,committeeId,statusType);
			}
		};
		YAHOO.util.Connect.setForm('addMemberSaving',true);
		YAHOO.util.Connect.asyncRequest('POST','saveJanmabhoomiCommitteeMemberAction.action',uploadHandler);
			
	}
	
	function showSbmitStatus(result,committeeId,statusType){
		$(".loadingImgId").css("display","none");
		if(result.indexOf("SUCCESS") > -1){
			
		  $(".savingStatusDivId").html("<span style='color: green;font-size:22px;'>Application Received Successfully...</span>");
		 
		  setTimeout(function(){
			 $("#memberAddEditModalOpen").modal("hide");
			 
		  }, 1000);
		  setTimeout(function(){
			$('body').addClass("modal-open");
			getDistrictWiseCommitteeDetails(blockLevel,"level");	
			getJanmabhoomiCommitteeOverview(committeeId)	
		  }, 2000);
		    
			
		}else if(result.indexOf("DUPLICATE") > -1){
			setTimeout(function(){
		  $(".savingStatusDivId").html("<span style='color: red;font-size:22px;'>This person already added for this committee</span>");
		  }, 1000);
		}else {
		  setTimeout(function(){
		  $(".savingStatusDivId").html("<span style='color: red;font-size:22px;'>Application Submission Failed. Please Try Again.</span>");
		  }, 1000);
		}
	  }
	  $(document).on("click","#submitCommitteeStatusChangeId",function(){
		  var committeeId= $(this).attr("attr_committee_submit");
		   var status= $("#committeeStatusChangeId").val();
saveCommitteeStatus(committeeId,status);
	
}) ;
	  function saveCommitteeStatus(committeeId,status){
		  $(".committeeSavingStatusDivId").html(spinner);
		  var jsObj={
    "committeeId"  :committeeId,
	status:status
    }
     $.ajax({
      type : "POST",
      url : "updateCommitteStatusByCommiteeIdAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){ 
       if(result != null){
		 if(result.exceptionMsg.indexOf("SUCCESS") > -1){
			setTimeout(function(){
		  $(".committeeSavingStatusDivId").html("<span style='color: green;font-size:22px;'>Committee Status Updated Successfully</span>");
		  }, 1000); 
		  setTimeout(function(){
			 $("#memberAddEditModalOpen").modal("hide");
			 
		  }, 1000);
		  setTimeout(function(){
			$('body').addClass("modal-open");
			getDistrictWiseCommitteeDetails(blockLevel,"level");	
			getJanmabhoomiCommitteeOverview(committeeId)	
		  }, 2000);
		 }else if(result.exceptionMsg.indexOf("NotFilled") > -1){
			 setTimeout(function(){
		  $(".committeeSavingStatusDivId").html("<span style='color: red;font-size:22px;'>Total committee members are not added</span>");
		  }, 1000);
		 }else{
			 setTimeout(function(){
		  $(".committeeSavingStatusDivId").html("<span style='color: red;font-size:22px;'>Committee Status Updation Failed. Please Try Again.</span>");
		  }, 1000); 
		 }
	  }
    });
	  }
	  function setDefaultImage(img){
			  img.src = "dist/Appointment/img/thumb.jpg";
		}
	  function getAdvancedSearchDetails(publicRepresentativeTypeId,levelId,levelVal,committeeId,statusType,roleId,memberId,memberName,
		voterCardNo,mobileNo,memberShipId,attrStateId,attrDistrictId,attrConstituencyId,attrMandalId,attrPanchayatId){
		$("#memberAddEditPopUpDetailsId").html(spinner);
		var statusArr=[publicRepresentativeTypeId];
		var tdpCadreIds=[];
		var level;
		var levelValue;
		var tehsilId = 0;
		var referCommitteeId;
		var levelStr = 'state';
		$("#errorDivId").html('');
		var searchType;
		var searchValue = "";
		var districtId=0;
		var constituencyId=0;
		var mandalId = 0;
		var panchayatId=0;
		var levelId=levelId;
		var stateId=0;
		searchType = "publicRepresentative";
		searchValue = publicRepresentativeTypeId;
		if(levelId == 1)
		{
			level = "state";
			if(publicRepresentativeTypeId > 0 && publicRepresentativeTypeId != 31)
			districtId=levelVal;
			alertLevelId = 10; 
		}
		if(levelId == 2 || levelId == 3 || levelId == 4)
		{
			tehsilId=levelVal;
			levelStr = "mandal";
			alertLevelId = 5;
		}
			
		if(levelId == 5 || levelId == 6 || levelId == 7)
		{
			panchayatId=levelVal;
			levelStr = "village";
			alertLevelId = 6;
		}
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			designations:statusArr,
			committeeId:0, // "PR" -- if public representatives
			levelId:alertLevelId,
			districtId:districtId,
			constituencyId:constituencyId,
			mandalId:tehsilId,
			panchayatId:panchayatId,
			stateId:stateId,
			levelStr:levelStr,
			aptUserId:0
		}
		
		  	$.ajax({
				type : 'POST',
				url : 'getAdvancedSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result !=null && result.length>0){
					buildAdvancedSearchDetails(result,committeeId,statusType,roleId,memberId);
				}else{
					buildMemberAddEditDetailsBlock("approval",roleId,memberId,memberName,voterCardNo,mobileNo,memberShipId,committeeId,statusType,attrStateId,attrDistrictId,attrConstituencyId,attrMandalId,attrPanchayatId)
				}
			}); 
	}
	
	function buildAdvancedSearchDetails(result,committeeId,statusType,roleId,memberId){
		$("#memberAddEditPopUpDetailsId").removeClass("bg_class_Div");
		var str='';
		str+='<div class="table-responsive">';
			str+='<table id="searchedMembersId">';
			str+='<thead><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
					if( xindex == 0){
						str+='<tr>';
					}
				str+='<td style="padding:0px !important;">';
					str+='<div class="row">';
						str+='<div class="col-sm-12">';
							str+='<ul class="createAppointmentSearch">';
						
								str+='<li class="">';
									str+='<div class="row">';
											str+='<div class="col-sm-12">';
												str+='<div class="media">';
													str+='<div class="media-left">';
														str+='<img class="media-object thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
													str+='</div>';
													str+='<div class="media-body namesalignmentCss">';
														str+='<h5 style="color:#34A7C1;">Name : '+result[i].name+'</h5>';
														str+='<h5 style="color:#34A7C1;">Membership No : '+result[i].memberShipId+'</h5>';
														str+='<h5 style="color:#34A7C1;">Voter ID : '+result[i].voterCardNo+'</h5>';
														str+='<h5><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp; '+result[i].mobileNo+'</span></h5>';
														str+='<h5 style="font-size:12px">Desg : '+result[i].designation+'</h5>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
											
											str+='<div class="btn btn-success btn-sm col-sm-6 col-md-offset-4 m_top10" style="border-radius:20px;">';
												str+='<label style="margin-bottom: 0px; line-height: 10px;">';
													str+='<input style="margin-left: 0px; margin-top: 0px;" type="radio" name="optionsRadios" class="selectedMemberDetailsAppend" attr_name="'+result[i].name+'" attr_mobile_no="'+result[i].mobileNo+'" attr_tdp_cadreId="'+result[i].id+'" attr_commiteeId="'+committeeId+'" attr_status_type="'+statusType+'" attr_voter_Id="'+result[i].voterId+'" attr_roleId="'+roleId+'" attr_member_Id="'+memberId+'"> &nbsp;SELECT';
												str+='</label>';
											str+='</div>';
									str+='</div>';
								str+='</li>';
							
						
							
							str+='</ul>';
						str+='</div>';
				str+='</div>';
				str+='</td>';
				xindex++;
				if(result.length-1 == i){
					if(xindex % 4 == 3){
						str+='<td></td>';
						str+='</tr>';
					}
					if(xindex % 4 == 2){
						str+='<td></td>';
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 4){
					str+='</tr>';
					xindex = 0;
				}
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div id="selectedMemberDetailsId"></div>';
		$("#memberAddEditPopUpDetailsId").html(str);
		$('#searchedMembersId').DataTable({
			
		});
	}
	
	$(document).on("click",".selectedMemberDetailsAppend",function(){
		var name = $(this).attr("attr_name");
		var mobileNo = $(this).attr("attr_mobile_no");
		var tdpCadreId = $(this).attr("attr_tdp_cadreId");
		var committeId = $(this).attr("attr_commiteeId");
		var statusType = $(this).attr("attr_status_type");
		var voterId = $(this).attr("attr_voter_Id");
		var roleId = $(this).attr("attr_roleId");
		var memberId = $(this).attr("attr_member_Id");
		
		buildSelectedMemberBlockDiv(name,mobileNo,tdpCadreId,committeId,statusType,voterId,roleId,memberId);
		
	});
	
	function buildSelectedMemberBlockDiv(name,mobileNo,tdpCadreId,committeId,statusType,voterId,roleId,memberId){
		var str='';
		str+='<form name="addMemberSaving" id="addMemberPub"  method="post" enctype="multipart/form-data">';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-12">';
				str+='<div id="addMemberErrDiv" style="color:red;"></div>';
				str+='<div class="col-sm-2">';
					str+='<label>';
					if(name == null)
						name="";
						str+='<input type="text" class="form-control" id="memberNameId" placeholder="Enter Name" name="janmabhoomiCommitteeMemberVO.name" value="'+name+'">';
					str+='</label>';
				str+='</div>';
				
				str+='<div class="col-sm-2">';
					str+='<label>';
					if(mobileNo == null)
						mobileNo="";
						str+='<input type="text" class="form-control" id="memberMobileNoId" placeholder="Enter MobileNo" name="janmabhoomiCommitteeMemberVO.mobileNumber" value="'+mobileNo+'">';
					str+='</label>';
				str+='</div>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.designationId" value="'+roleId+'"/>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.voterId" value="'+voterId+'"/>';
				if(memberId != null && memberId>0)
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.id" value="'+memberId+'"/>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.tdpCadreId" value="'+tdpCadreId+'"/>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.enrollmentYrId" value="1"/>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.status" value="approval"/>';
				str+='<input type="hidden" name="janmabhoomiCommitteeMemberVO.committeeId" value="'+committeId+'"/>';
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
					str+='<button class="btn btn-success border_radius_none height_41 text-bold" type="button" onclick="savingApplication('+committeId+',\''+statusType+'\');">Add Member</button><span class="loadingImgId"><img src="images/search.gif" style="display:none;"/></span>';
				str+='</div>';
				str+='<div class="col-sm-6">';
					str+='<div class="savingStatusDivId"></div>';
				str+='</div>';
			str+='</div>'; 
		  
		$("#selectedMemberDetailsId").html(str);
		 getAllCategoriesAction();
		$(".chosen-select").chosen();
	}
	