var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadCommitteeCalls();
function onloadCommitteeCalls(){
	getTdpMandalTowndivisionCommitteePerformanceDetails("mandalTownDivisionCommDivId","Mandal");
	getTdpVillageWardCommitteePerformanceDetails("villageWardCommDivId","Village");
	getMandalTowndivisionAffliatedTdpCommitteePerformanceDetails("affmandalTownDivision","Affiliated");
	getBoothInchargeCommitteePerformanceDetails("boothCommDivId","Booth");
}

$(document).on("click",".committeesExSettingsIcon",function(e){
	e.stopPropagation();
	$(this).closest(".committeeblockOpen").find(".debatesSettingsBody").show();
});
$(document).on("click",".debatesSettingsBody",function(e){
	e.stopPropagation();
});
$(document).on("click",function(){
	$(".debatesSettingsBody").hide();
});
$(document).on("click",".committeesExSettingsCloseBody",function(){
	$(this).closest(".debatesSettingsBody").hide();
});

$(document).on("click",".commlevelWiseCls",function(e){
	var levelName=['mandalTownDivisionCommDivId','villageWardCommDivId','boothCommDivId','affmandalTownDivision'];
	for(var i in levelName){
		$("#"+levelName[i]).hide();
	}
	var selectedlevelValArr=[];
	$(".getCommLevelValCls").each(function(){
       if($(this).is(":checked")){
			
		    selectedlevelValArr.push($(this).val());
		    
	   }
    });
	console.log(selectedlevelValArr)
	$("#"+selectedlevelValArr).show();
	$(".debatesSettingsBody").hide();
});

function getTdpMandalTowndivisionCommitteePerformanceDetails(id,levelName) {
	$("#"+id).html(spinner);
	var jsObj = {
		tdpCommitteeLevel : "mandalTownDivision",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : [ 1 ],
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		if(result !=null){
			buildCommiteeLevel(result,id,levelName);
		}else{
			$("#"+id).html("No Data Available");
		}
	});
}
function getTdpVillageWardCommitteePerformanceDetails(id,levelName) {
	$("#"+id).html(spinner);
	var jsObj = {
		tdpCommitteeLevel : "villageWard",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : [ 1 ],
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		if(result !=null){
			buildCommiteeLevel(result,id,levelName);
		}else{
			$("#"+id).html("No Data Available");
		}
	});
}

function getMandalTowndivisionAffliatedTdpCommitteePerformanceDetails(id,levelName) {
	$("#"+id).html(spinner);
	var affliatedCommitteeIdArr = [2,3,4,6,7,8,9,11];
	var jsObj = {
		tdpCommitteeLevel : "mandalTownDivision",
		fromDate : "",
		toDate : "",
		tdpBasicCommitteeIds : affliatedCommitteeIdArr,
		tdpCommitteeEnrollmentId : 2,
		stateId : 1

	}
	$.ajax({
		type : 'POST',
		url : 'getCommiteeOverviewPerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		if(result !=null){
			buildCommiteeLevel(result,id,levelName);
		}else{
			$("#"+id).html("No Data Available");
		}
	});
}
function getBoothInchargeCommitteePerformanceDetails(id,levelName) {
	$("#"+id).html(spinner);
	var jsObj = {
		fromDate : "",
		toDate : "",
		boothInchargeEnrollmentId : 1,
		stateId :1
	}
	$.ajax({
		type : 'POST',
		url : 'getBoothInchargeCommitteePerformanceDetailsAction.action',
		dataType : 'json',
		data : {
			task : JSON.stringify(jsObj)
		}
	}).done(function(result) {
		if(result !=null){
			buildCommiteeLevel(result,id,levelName);
		}else{
			$("#"+id).html("No Data Available");
		}
	});
}

function buildCommiteeLevel(result,id,levelName){
	var str='';
	str+='<div class="row">';
		if(levelName == "Mandal"){
			str+='<div class="col-sm-12 m_top10">';
		}else{
			str+='<div class="col-sm-12 m_top20">';
		}
		
			str+='<h4 class="text_bold text-capital font_size24" >'+levelName+' Mandal Level Committee Details</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Total Committees</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.totalCount+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Completed Committees</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.completedCount+' <small style="color:green;">'+result.completedPerc+' %</small></h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Pending Committees</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.notCompletedCommitteeCount+' <small style="color:green;">'+result.notCompletedCommitteePer+' %</small></h3>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 7 Parliaments with Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Parliament Name</th>';
								str+='<th>Total</th>';
								str+='<th>Pending Committees</th>';
								str+='<th>Pending Committees %</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							for(var i in result.subList){
								str+='<tr>';
								str+='<td>'+result.subList[i].addressVO.parliamentName+'</td>';
								str+='<td>'+result.subList[i].totalCount+'</td>';
								str+='<td>'+result.subList[i].notCompletedCommitteeCount+'</td>';
								str+='<td>'+result.subList[i].notCompletedCommitteePer+'</td>';
								str+='</tr>';
								countVar =countVar+1;
								if (countVar === 7) {
									break;
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row pagebreak">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 10 Assembly Constituency with Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Parliament</th>';
								str+='<th>Total</th>';
								str+='<th>Pending Committees</th>';
								str+='<th>Pending Committees %</th>';
								
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar1 =0;
							for(var i in result.subList1){
								str+='<tr>';
									str+='<td>'+result.subList1[i].addressVO.constituencyName+'</td>';
									str+='<td>'+result.subList1[i].addressVO.parliamentName+'</td>';
									str+='<td>'+result.subList1[i].totalCount+'</td>';
									str+='<td>'+result.subList1[i].notCompletedCommitteeCount+'</td>';
									str+='<td>'+result.subList1[i].notCompletedCommitteePer+'</td>';
								
								str+='</tr>';
								countVar1 =countVar1+1;
								if (countVar1 === 10) {
									break;
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#"+id).html(str);
	
}
$(document).on("click",".committeesExRefresh",function(e){
	onloadCommitteeCalls();
});