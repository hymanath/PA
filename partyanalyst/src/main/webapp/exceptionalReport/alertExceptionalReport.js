var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadAlertCalls();
function onloadAlertCalls(){
	//getAssignedCandidateWisePendingAlerts();
	getDistrictWiseAlertsDetails();
	getOverAllAlertsDetails();
}
 $(document).on("click",".alertExRRefresh",function(e){
	onloadAlertCalls();
});
function getAssignedCandidateWisePendingAlerts(){  
	var jsObj = { 
		fromDateStr : "01/01/2010",
		toDateStr : "01/01/2050",
		stateId : 1,
		size : 50,
		alertTypeIds : [1]
	}
	$.ajax({
		type : 'POST',      
		url : 'getAssignedCandidateWisePendingAlerts.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildAssignedCandidateWisePendingAlerts(result);
		}else{
			$("#assignedParliamentWiseAlertsDivId").html("No Data Available")
		}
	});
}
function buildAssignedCandidateWisePendingAlerts(result){
	var str='';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >District Wise Action Required Alerts</h5>';
				str+='<div class="table-responsive m_top10" >';
					str+='<table class="table details-overview1" id="dataTableId1">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>District</th>';
								str+='<th>Total Alerts</th>';
								str+='<th>Action Required</th>';
								str+='<th>In Progress</th>';
								str+='<th>%</th>';     
								
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result){
								str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									str+='<td>'+result[i].totalAlert+'</td>';
									str+='<td>'+result[i].actionRequired+'</td>';
									str+='<td>'+result[i].pendingCount+'</td>';
									str+='<td>'+result[i].pendingCountPer+'</td>';
								str+='</tr>';
								
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#assignedParliamentWiseAlertsDivId").html(str)
	 $("#dataTableId1").dataTable({
		"paging":   false,
		"info":     false,
		"searching": false,
		"autoWidth": true,
		"sDom": '<"top"iflp>rt<"bottom"><"clear">',
		"aaSorting": [[ 4, "desc" ]]
	});
}
function getOverAllAlertsDetails(){
	$("#overAllAlertsDivId").html(spinner);	
	var jsObj = { 
		fromDateStr : "01/01/2010",
		toDateStr : "01/01/2050",
		stateId : 1,
		size : 50,
		alertTypeIds : [1]
	}
	$.ajax({
		type : 'POST',      
		url : 'getOverAllAlertsDetails.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOverAllAlertsDetails(result);
		}else{
			$("#overAllAlertsDivId").html("No Data Available");	
		}
	});         
}
function buildOverAllAlertsDetails(result){
	var str='';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >OverAll Alerts</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview-tour">';
						str+='<thead>';
							str+='<tr>';
								str+='<th></th>';
								str+='<th>Total&nbsp;Alerts</th>';
								for(var i in result[0].subList){
									if(result[0].subList[i].count >0){
										if(result[0].subList[i].name == "Action required"){
											str+='<th style="border-left:1px solid #d1ab66;border-right:1px solid #d1ab66">'+result[0].subList[i].name+'</th>';
										}else{
											str+='<th>'+result[0].subList[i].name+'</th>';
										}
										
									}
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							//for(var i in result){
								str+='<tr style="text-align:center;">';
									if(result[0].name == "Total Alerts"){
										str+='<td>Total&nbsp;Alerts</td>';
									}
									str+='<td>'+result[0].totalAlert+'</td>';
									var compPerc=0;
									var ActionInProgressPerc=0;
									var completedCount=0;
									var ActionInProgressCount=0;
									var ActionReguiredCount=0;
									
									for(var j in result[0].subList){
										if(result[0].subList[j].count >0){
											
											if(result[0].subList[j].name == "Completed"){
												completedCount = result[0].subList[j].count;
												
											}else if(result[0].subList[j].name == "Action In Progress"){
												ActionInProgressCount = result[0].subList[j].count;
											}else if(result[0].subList[j].name == "Action required"){
												ActionReguiredCount = result[0].subList[j].count;
											}
											compPerc = (completedCount/ActionReguiredCount*100).toFixed(2);
											ActionInProgressPerc = (ActionInProgressCount/ActionReguiredCount*100).toFixed(2);
											if(result[0].subList[j].name == "Action required"){
												str+='<td style="border-left:1px solid #d1ab66;border-right:1px solid #d1ab66">'+result[0].subList[j].count+'';
													if(result[0].subList[j].id >0){
															if(result[0].subList[j].name == "Completed"){
																str+='<h6>('+compPerc+'%)</h6>';
															}else if(result[0].subList[j].name == "Action In Progress"){
																str+='<h6>('+ActionInProgressPerc+'%)</h6>';
															}else{
																str+='<h6>('+result[0].subList[j].countPerc+'%)</h6>';
															}
														
													}
													
													str+='</td>';
											}else{
												str+='<td>'+result[0].subList[j].count+'';
													if(result[0].subList[j].id >0){
															if(result[0].subList[j].name == "Completed"){
																str+='<h6>('+compPerc+'%)</h6>';
															}else if(result[0].subList[j].name == "Action In Progress"){
																str+='<h6>('+ActionInProgressPerc+'%)</h6>';
															}else{
																str+='<h6>('+result[0].subList[j].countPerc+'%)</h6>';
															}
														
													}
													
													str+='</td>';
											}
											
										}
									}
								str+='</tr>';
							//}
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview-tour">';
						str+='<thead>';
							str+='<tr>';
								str+='<th></th>';
								str+='<th>Total&nbsp;Alerts</th>';
								for(var i in result[1].subList){
									if(result[1].subList[i].count >0){
										if(result[1].subList[i].name == "Action required"){
											str+='<th style="border-left:1px solid #d1ab66;border-right:1px solid #d1ab66">'+result[1].subList[i].name+'</th>';
										}else{
											str+='<th>'+result[1].subList[i].name+'</th>';
										}
									}
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							//for(var i in result){
								str+='<tr style="text-align:center;">';
									if(result[1].name == "Last Month Alerts"){
										str+='<td>Last&nbsp;Month&nbsp;Alerts</td>';
									}
									str+='<td>'+result[1].totalAlert+'</td>';
									var compPerc=0;
									var ActionInProgressPerc=0;
									var completedCount=0;
									var ActionInProgressCount=0;
									var ActionReguiredCount=0;
									for(var j in result[1].subList){
										if(result[1].subList[j].count >0){
											
											if(result[1].subList[j].name == "Completed"){
												completedCount = result[1].subList[j].count;
												
											}else if(result[1].subList[j].name == "Action In Progress"){
												ActionInProgressCount = result[1].subList[j].count;
											}else if(result[1].subList[j].name == "Action required"){
												ActionReguiredCount = result[1].subList[j].count;
											}
											compPerc = (completedCount/ActionReguiredCount*100).toFixed(2);
											ActionInProgressPerc = (ActionInProgressCount/ActionReguiredCount*100).toFixed(2);
											if(result[1].subList[j].name == "Action required"){
												str+='<td style="border-left:1px solid #d1ab66;border-right:1px solid #d1ab66">'+result[1].subList[j].count+'';
													if(result[1].subList[j].id >0){
															if(result[1].subList[j].name == "Completed"){
																str+='<h6>('+compPerc+'%)</h6>';
															}else if(result[1].subList[j].name == "Action In Progress"){
																str+='<h6>('+ActionInProgressPerc+'%)</h6>';
															}else{
																str+='<h6>('+result[1].subList[j].countPerc+'%)</h6>';
															}
														
													}
													
													str+='</td>';
											}else{
												str+='<td>'+result[1].subList[j].count+'';
													if(result[1].subList[j].id >0){
															if(result[1].subList[j].name == "Completed"){
																str+='<h6>('+compPerc+'%)</h6>';
															}else if(result[1].subList[j].name == "Action In Progress"){
																str+='<h6>('+ActionInProgressPerc+'%)</h6>';
															}else{
																str+='<h6>('+result[1].subList[j].countPerc+'%)</h6>';
															}
														
													}
													
													str+='</td>';
											}
											
										}
									}
								str+='</tr>';
							//}
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#overAllAlertsDivId").html(str);
	
}


function getDistrictWiseAlertsDetails(){  
	var jsObj = { 
		fromDateStr : "01/01/2010",
		toDateStr : "01/01/2050",
		stateId : 1,
		size : 50,
		alertTypeIds : [1]
	}
	$.ajax({
		type : 'POST',      
		url : 'getDistrictWiseAlertsDetails.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildAssignedCandidateWisePendingAlerts(result);
		}else{
			$("#assignedParliamentWiseAlertsDivId").html("No Data Available")
		}
	});
}