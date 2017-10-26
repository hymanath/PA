var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

setTimeout(function(){
	onloadCalls();
},1500)

function onloadCalls(){
	getInsuranceOverviewDetails("")
	getLocationWiseInsuranceIssueTypeComplaintCount("");
	
}
function getInsuranceOverviewDetails(yearId){
	$("#CategoryInsuranceBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			: customStartInsuranceDate,
			"toDate"			: customEndInsuranceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getInsuranceOverviewDetailsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList1 !=null && result.subList1.length>0){
			return buildInsuranceOverviewDetails(result);
		}else{
			$("#CategoryInsuranceBlockDivId").html("No Data Available");
		}
	});	
	function buildInsuranceOverviewDetails(result){
		var str='';
			str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
				str+='<div class="col-sm-12 borderCss m_top10">';
					str+='<div style="padding:10px">';
						str+='<h4 class="theme-title-color">STATUS WISE INSURANCE DETAILS</h4>';
					str+='<div class="table-responsive">';
						str+='<table class="table table_griveance_pad">';
							str+='<thead class="bg-E9">';
								str+='<tr>';
									str+='<th>CATEGORY</th>';
									str+='<th>TOTAL</th>';
									for(var i in result.subList[0].subList){
										str+='<th>'+result.subList[0].subList[i].name+'</th>';
									}
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result.subList){
										str+='<tr>';
										str+='<td>'+result.subList[i].name+'</td>';
										if(result.subList[i].count !=null && result.subList[i].count>0){
											str+='<td>'+result.subList[i].count+'</td>';
										}else{
											str+='<td> - </td>';
										}
										
										for(var k in result.subList[i].subList){
											if(result.subList[i].subList[k].count !=null && result.subList[i].subList[k].count>0){
												str+='<td>'+result.subList[i].subList[k].count+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
										}
									str+='</tr>';
								
								}
								
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		$("#CategoryInsuranceBlockDivId").html(str);
	}
}
function getLocationWiseInsuranceIssueTypeComplaintCount(yearId){
	$("#locationIsuranceBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			: customStartInsuranceDate,
			"toDate"			: customEndInsuranceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getLocationWiseInsuranceIssueTypeComplaintCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.length>0){
			return buildLocationWiseInsuranceIssueTypeComplaintCount(result,locationLevelId);
		}else{
			$("#locationIsuranceBlockDivId").html("No Data Available");
		}
	});	
	function buildLocationWiseInsuranceIssueTypeComplaintCount(result,locationLevelId){
		var str='';
		var headingStr = getDynamicHeading(locationLevelId);
			str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
				str+='<div class="col-sm-12 borderCss m_top10">';
					str+='<div style="padding:10px">';
						str+='<h4 class="theme-title-color">'+headingStr.toUpperCase()+' WISE DETAILS</h4>';
						str+='<div class="table-responsive m_top10">';
							str+='<table class="table table_griveance_pad" id="insuranceLocationWiseDataTblId">';
								str+='<thead class="bg-E9">';
									str+='<tr>';
										str+='<th>'+headingStr.toUpperCase()+' NAME</th>';
										str+='<th>TOTAL</th>';
										for(var j in result[0].subList){
											str+='<th>'+result[0].subList[j].name+'</th>';
										}
									str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
									for(var i in result){
										str+='<tr>';
											str+='<td>'+result[i].name+'</td>';
											if(result[i].count !=null && result[i].count>0){
												str+='<td>'+result[i].count+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
											for(var j in result[i].subList){
												if(result[i].subList[j].count !=null && result[i].subList[j].count>0){
													str+='<td>'+result[i].subList[j].count+'</td>';
												}else{
													str+='<td> - </td>';
												}
												
											}
										str+='</tr>';
									}
								str+='</tbody>';
							str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
		$("#locationIsuranceBlockDivId").html(str);
		if (result != null && result.length > 10) {
			$("#insuranceLocationWiseDataTblId").dataTable({
			"paging":   true,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		  });
		}
	}
}

function getDynamicHeading(locationScopeId) {
	var headingStr="";
	 if (locationScopeId != null ) {
		   if (locationScopeId == 2) { // state
			   headingStr = "District";
		   } else if (locationScopeId == 3) {
			   headingStr = "Constituency";
			} else if (locationScopeId == 10) {
				headingStr = "Constituency";
			} else if (locationScopeId == 4) {
				headingStr = "Mandal";
			} else if (locationScopeId == 5) {
				headingStr = "Village";
			} else if (locationScopeId == 7) { // town/division
				headingStr = "ward";
			} else if (locationScopeId == 6) {
				headingStr = "Village";
			} else if (locationScopeId == 8) {
				headingStr = "ward";
			}
		}
	 return headingStr;
}