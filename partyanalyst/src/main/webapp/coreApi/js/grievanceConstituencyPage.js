var grivanceColorObj={"APPROVED":"#2DCC70","COMPLETED":"#449C43","IN PROGRESS":"#FFB84F","NOT ELIGIBLE":"#C0392B","NOT POSSIBLE":"#EF8379","NOT VERIFIED":"#31708F"}
var insuranceColorObj={"Waiting For Documents":"#2DCC70","Documents Submitted In Party":"#449C43","Forwarded to Insurance":"#FFB84F","Closed at Insurance":"#8F43AF","Closed at Party":"#9B88B3","Approved - Compensated":"#2BCD72","Closed Letters":"#32708F","Account Rejected":"#65CBCC"}
var financialColorObj={"EDUCATIONAL":"#00B07C","CM RELIEF":"#66CDCC","GENERAL":"#FFB84F","FINANCIAL SUPPORT":"#4D73E2","PERSONAL":"#50CCF3","PENSION":"#663300","OTHERS":"#94979A"}
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';


setTimeout(function(){
	onloadCalls();
},1500)

function onloadCalls(){
	getGrivenceOverviewDtls("")
	getGrivenceFinancialSupportDtls("");
	getGrivenceComplaintCountDepartmentWise("");
	getLocationWiseTypeOfIssueGrivenceComplaintCount("");
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
function getGrivenceOverviewDtls(yearId){
$("#CategoryBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			:customStartGrivanceDate,
			"toDate"			:customEndGrivanceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getGrivenceOverviewDtlsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList2 !=null && result.subList2.length>0){
			return buildGrivenceOverviewDtls(result);
		}else{
			$("#CategoryBlockDivId").html("No Data Available");
		}
	});	
	
	function buildGrivenceOverviewDtls(result){
		var str='';
		for(var i in result.subList2){
			str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
				str+='<div class="col-sm-12 borderCss m_top10">';
					str+='<div style="padding:10px">';
						str+='<h4 class="theme-title-color text-capital">'+result.subList2[i].name+' CATEGORY WISE DETAILS</h4>';
						str+='<div class="table-responsive m_top10">';
							str+='<table class="table table_griveance_pad">';
								str+='<thead class="bg-E9">';
									str+='<tr>';
										str+='<th>Category</th>';
										str+='<th>TOTAL</th>';
										for(var j in result.subList2[i].subList[0].subList){
											str+='<th>'+result.subList2[i].subList[0].subList[j].name+'</th>';
										}
									str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
									for(var j in result.subList2[i].subList){
										str+='<tr>';
											str+='<td>'+result.subList2[i].subList[j].name+'</td>';
											if(result.subList2[i].subList[j].count !=null && result.subList2[i].subList[j].count>0){
												str+='<td>'+result.subList2[i].subList[j].count+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
											for(var k in result.subList2[i].subList[j].subList){
												if(result.subList2[i].subList[j].subList[k].count !=null && result.subList2[i].subList[j].subList[k].count>0){
													str+='<td>'+result.subList2[i].subList[j].subList[k].count+'</td>';
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
		}
		$("#CategoryBlockDivId").html(str);
	}
}

function getGrivenceFinancialSupportDtls(yearId){
	$("#financialBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			:customStartGrivanceDate,
			"toDate"			:customEndGrivanceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getGrivenceFinancialSupportDtlsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.subList !=null && result.subList.length>0){
			return buildGrivenceFinancialSupportDtls(result);
		}else{
			$("#financialBlockDivId").html("No Data Available");
		}
	});
		
	function buildGrivenceFinancialSupportDtls(result){
		var str='';
		str+='<h4 class="theme-title-color">Financial Support</h4>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-4">';
				str+='<div class="media_padding_grievance">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:45px"></i>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h3>Total Financial Support</h3>';
							if(result.totalAmount !=null && result.totalAmount>0){
								str+='<h4 class="m_top5">'+result.totalAmount+'</h4>';
							}else{
								str+='<h4 class="m_top5"> - </h4>';
							}
							
						str+=' </div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-4">';
				str+='<div class="media_padding_grievance">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:45px"></i>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h3>Donations to Govt</h3>';
							if(result.govtCount !=null && result.govtCount>0){
								str+='<h4 class="m_top5">'+result.govtCount+'</h4>';
							}else{
								str+='<h4 class="m_top5"> - </h4>';
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-4">';
				str+='<div class="media_padding_grievance">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:45px"></i>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h3>Donations to Party</h3>';
							if(result.partyCount !=null && result.partyCount>0){
								str+='<h4 class="m_top5">'+result.partyCount+' ('+result.memberCount+' Memberes)</h4>';
							}else{
								str+='<h4 class="m_top5"> - </h4>';
							}
						str+=' </div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
			str+='<div class="col-sm-12 borderCss">';
				str+='<div class="col-sm-4">';
					str+='<h4 class="m_top10">Total  -  <i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:18px"></i> '+result.totalAmount+'/-</h4>';
					str+='<div id="financialGraphDivId" style="height:250px;"></div>';
				str+='</div>';
				str+='<div class="col-sm-8 border_left pad_left0">';
					str+='<div class="table-responsive">';
						str+='<table class="table table_griveance_pad">';
							str+='<thead class="bg-E9">';
								str+='<tr>';
									str+='<th></th>';
									str+='<th>TOTAL MEMBERS</th>';
									str+='<th>REQUESTED</th>';
									str+='<th>APPROVED</th>';
									str+='<th>COMPLETED</th>';
									str+='<th>TOTAL AMOUNT</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result.subList){
									str+='<tr>';
										str+='<td><span class="squareCss" style="background-color:'+financialColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+'</td>';
										if(result.subList[i].memberCount !=null && result.subList[i].memberCount>0){
											str+='<td>'+result.subList[i].memberCount+'</td>';
										}else{
											str+='<td> - </td>';
										}
										if(result.subList[i].expectedAmount !=null && result.subList[i].expectedAmount>0){
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList[i].expectedAmount+'</td>';
										}else{
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> -</td>';
										}
										if(result.subList[i].approvedAmount !=null && result.subList[i].approvedAmount>0){
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList[i].approvedAmount+'</td>';
										}else{
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> -</td>';
										}
										if(result.subList[i].completedCount !=null && result.subList[i].completedCount>0){
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList[i].completedCount+'</td>';
										}else{
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> -</td>';
										}
										if(result.subList[i].totalAmount !=null && result.subList[i].totalAmount>0){
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList[i].totalAmount+'</td>';
										}else{
											str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> -</td>';
										}
										
									str+='</tr>';
								}
								
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#financialBlockDivId").html(str);
		if(result !=null && result.subList !=null && result.subList.length>0){
			var mainArr=[];
				for(var i in result.subList){
					var obj = {
						name: result.subList[i].name,
						y:result.subList[i].totalAmount,
						color:financialColorObj[result.subList[i].name.trim()]
					}
					mainArr.push(obj);
				}
			
			var id = 'financialGraphDivId';
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
					return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+this.y+")</b>";
				}  
			}; 
			var plotOptions ={
				pie: {
					innerSize: 120,
					depth: 80,
					dataLabels: {
						enabled: false,
						formatter: function() {
							return (this.y);
						},
						distance: -10,
						color:'#333'
					},
					showInLegend: legend
				},
			};
			var legend = {
				enabled: false,
				x:80,
				y:50,
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'top',
				useHTML: true,
				
				labelFormatter: function() {
					return '<div><span style="color:'+this.color+'">'+this.name + '-' + this.y + '</span></div>';
				}
			};
			var data = [{
				name: '',
				data: mainArr
			}];
			highcharts(id,type,data,plotOptions,title,tooltip,legend);
		}
	}
}

function getGrivenceComplaintCountDepartmentWise(yearId){
	$("#departmentBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			: customStartGrivanceDate,
			"toDate"			: customEndGrivanceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getGrivenceComplaintCountDepartmentWiseAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.length>0){
			buildGrivenceComplaintCountDepartmentWise(result,"department",locationLevelId);
		}else{
			$("#departmentBlockDivId").html("No Data Available");
		}
	});	
}
	function buildGrivenceComplaintCountDepartmentWise(result,type,locationLevelId){
		var str='';
		var headingStr = getDynamicHeading(locationLevelId);
			str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
				str+='<div class="col-sm-12 borderCss m_top10">';
					str+='<div style="padding:10px">';
					if(type == "department"){
						str+='<h4 class="theme-title-color">DEPARTMENT WISE DETAILS</h4>';
					}else{
						str+='<h4 class="theme-title-color">'+headingStr.toUpperCase()+' WISE DETAILS</h4>';
					}
						
						str+='<div class="table-responsive m_top10">';
							str+='<table class="table table_griveance_pad" id="grievanceDataTblId'+type+'">';
								str+='<thead class="bg-E9">';
									str+='<tr>';
									if(type == "department"){
										str+='<th>Department Name</th>';
									}else{
										str+='<th>Location Name</th>';
									}
										
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
		
		$("#"+type+"BlockDivId").html(str);
		if (result != null && result.length > 10) {
			$("#grievanceDataTblId"+type).dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		  });
		}
	}


function getLocationWiseTypeOfIssueGrivenceComplaintCount(yearId){
	$("#locationBlockDivId").html(spinner);
	var jsObj={
			"fromDate" 			: customStartGrivanceDate,
			"toDate"			: customEndGrivanceDate,
			"locationTypeId" 	: locationLevelId,
			"locationValuesArr" : userAccessLevelValuesArray,
			"year"				: yearId,
			stateId 			: 1
		}
	 $.ajax({
	  type : "POST",
	  url : "getLocationWiseTypeOfIssueGrivenceComplaintCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.length>0){
			buildGrivenceComplaintCountDepartmentWise(result,"location",locationLevelId);
		}else{
			$("#locationBlockDivId").html("No Data Available");
		}
	});	
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
	