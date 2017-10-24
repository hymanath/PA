var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var ntrTrustColorObj={"NTR MODEL SCHOOL":"#00B07C","NTR JR COLLEGE":"#4D73E2","OTHER":"#663300"}

setTimeout(function(){
	onloadCalls();
},1500)

function onloadCalls(){
	getTrustEducationSubjectForDetails("")
	getLocationWiseTrustEducationComplaintCount("");
	
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
function getTrustEducationSubjectForDetails(yearId){
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
	  url : "getTrustEducationSubjectForDetailsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null){
			return buildTrustEducationSubjectForDetails(result);
		}
	});	
	
	function buildTrustEducationSubjectForDetails(result){
		var str='';
		str+='<h4 class="theme-title-color">Fee Concession Details</h4>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-4">';
				str+='<div class="media_padding_grievance">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:45px"></i>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h3>Expected Amount</h3>';
							if(result.expectedAmount !=null && result.expectedAmount>0){
								str+='<h4 class="m_top5">'+result.expectedAmount+'</h4>';
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
							str+='<h3>Approved Amount</h3>';
							if(result.approvedAmount !=null && result.approvedAmount>0){
								str+='<h4 class="m_top5">'+result.approvedAmount+'</h4>';
							}else{
								str+='<h4 class="m_top5"> - </h4>';
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
		str+='</div>';
		
		str+='<div class="row m_top20" style="margin-left: 0px; margin-right: 0px;">';
			str+='<div class="col-sm-12 borderCss">';
				str+='<div class="col-sm-4 border_right">';
					str+='<h4 class="m_top10">Total  -  <i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:18px"></i> '+result.count+'/-</h4>';
					str+='<div id="financialNtrTrustGraphDivId" style="height:250px;"></div>';
				str+='</div>';
				str+='<div class="col-sm-8 pad_left0">';
					str+='<div class="table-responsive">';
						str+='<table class="table table_griveance_pad">';
							str+='<thead class="bg-E9">';
								str+='<tr>';
									str+='<th></th>';
									str+='<th>TOTAL MEMBERS</th>';
									for(var i in result.subList[0].subList){
										str+='<th>'+result.subList[0].subList[i].name+'</th>';
									}
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result.subList){
									str+='<tr>';
										str+='<td><span class="squareCss" style="background-color:'+ntrTrustColorObj[result.subList[i].name.trim()]+'"></span>  '+result.subList[i].name+'</td>';
										str+='<td>'+result.subList[i].count+'</td>';
										for(var j in result.subList[i].subList)
										str+='<td><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i> '+result.subList[i].subList[j].count+'</td>';
										
									str+='</tr>';
								}
								
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#financialNtrTrustBlockDivId").html(str);
		if(result !=null && result.subList !=null && result.subList.length>0){
			var mainArr=[];
				for(var i in result.subList){
					var obj = {
						name: result.subList[i].name,
						y:result.subList[i].count,
						color:ntrTrustColorObj[result.subList[i].name.trim()]
					}
					mainArr.push(obj);
				}
			
			var id = 'financialNtrTrustGraphDivId';
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
function getLocationWiseTrustEducationComplaintCount(yearId){
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
	  url : "getLocationWiseTrustEducationComplaintCountAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){  
		if(result !=null && result.length>0){
			return buildLocationWiseTrustEducationComplaintCount(result);
		}
	});
	
	function buildLocationWiseTrustEducationComplaintCount(result){
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
											str+='<td>'+result[i].count+'</td>';
											for(var j in result[i].subList){
												str+='<td>'+result[i].subList[j].count+'</td>';
											}
										str+='</tr>';
									}
								str+='</tbody>';
							str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		
		$("#locationNtrTrustBlockDivId").html(str);
	}
}
	