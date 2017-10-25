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
			"fromDate" 			: "",
			"toDate"			: "",
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
						str+='<h4 class="theme-title-color">Insurance Details</h4>';
					str+='<div class="table-responsive">';
						str+='<table class="table table_griveance_pad">';
							str+='<thead class="bg-E9">';
								str+='<tr>';
									str+='<th>Category</th>';
									str+='<th>TOTAL</th>';
									for(var i in result.subList1[0].subList[0].subList){
										str+='<th>'+result.subList1[0].subList[0].subList[i].name+'</th>';
									}
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result.subList1){
									for(var j in result.subList1[i].subList){
										str+='<tr>';
										str+='<td>'+result.subList1[i].subList[j].name+'</td>';
										if(result.subList1[i].subList[j].count !=null && result.subList1[i].subList[j].count>0){
											str+='<td>'+result.subList1[i].subList[j].count+'</td>';
										}else{
											str+='<td> - </td>';
										}
										
										for(var k in result.subList1[i].subList[j].subList){
											if(result.subList1[i].subList[j].subList[k].count !=null && result.subList1[i].subList[j].subList[k].count>0){
												str+='<td>'+result.subList1[i].subList[j].subList[k].count+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
										}
									str+='</tr>';
									}
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
			"fromDate" 			: "",
			"toDate"			: "",
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
			return buildLocationWiseInsuranceIssueTypeComplaintCount(result);
		}else{
			$("#locationIsuranceBlockDivId").html("No Data Available");
		}
	});	
	function buildLocationWiseInsuranceIssueTypeComplaintCount(result){
		var str='';
			str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
				str+='<div class="col-sm-12 borderCss m_top10">';
					str+='<div style="padding:10px">';
						str+='<h4 class="theme-title-color">Location Wise Detailes</h4>';
						str+='<div class="table-responsive m_top10">';
							str+='<table class="table table_griveance_pad">';
								str+='<thead class="bg-E9">';
									str+='<tr>';
										str+='<th>Location Name</th>';
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
	}
}