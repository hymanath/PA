// please do not try to edit these options which may cause the entire page to stop working.	
var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
onLoadCalls();
function onLoadCalls()
{
	getAllNominatedStatusListLevelWiseDataDashBoard();
	getAreaWiseDashboardCandidatesCountView();
	getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails("ageRange");
	getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails("casteCategory");
	getLocationWiseNominatedPostAnalysisDetails("ageGroup");
	getLocationWiseNominatedPostAnalysisDetails("casteGroup");
	getLocationWiseNominatedPostAnalysisDetails("mandal");
}
$(document).on("click",".casteGroupId",function(){
	var type = $(this).attr("attr_type");
	if(type == "casteGroup")
	{
		$("#subCasteBlockId").html('');
	}else{
		$("#casteGroupBlockId").html('');
	}
	getLocationWiseNominatedPostAnalysisDetails(type);
});	
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
function getAllNominatedStatusListLevelWiseDataDashBoard(){
	$("#overAllAnalysisBlockId").html(spinner);
	var jsObj={
		boardLevelId:4,  //state level 1 //parliament -4 or constituenct -4 // mandal Or muni -5 // panchayat/ward -7 //
		levelValues:["282"],
		levelId:4,
		fromDateStr:"28/09/2012",
      toDateStr:"07/10/2017",
      year:""
	}
	$.ajax({   
		type:'GET',
		url:'getAllNominatedStatusListLevelWiseDataDashBoardAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		return buildData(result)
	});
	function buildData(result)
	{
		var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-6" style="border-right:1px solid #ddd;">';
				str+='<div id="overAllAnalysisGraphId" style="height:270px"></div>';
				str+='<table class="table table-bordered m_top10">';
					str+='<tr>';
						str+='<td style="text-align:center;padding:20px 0px">';
							str+='<p><span style="margin-right:3px;display:inline-block;height:15px;width:15px;background-color:#FFD400;border:1px solid #ddd;border-radius:50%;"></span>Applications Received</p>';
							str+='<h2>'+result.applicatnsReceived+'</h2>';
						str+='</td>';
						str+='<td style="text-align:center;padding:20px 0px">';
							str+='<p><span style="margin-right:3px;display:inline-block;height:15px;width:15px;background-color:#DE7118;border:1px solid #ddd;border-radius:50%;"></span>Total Posts</p>';
							str+='<h2>'+result.totalPosts+'</h2>';
						str+='</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td style="text-align:center;padding:20px 0px">';
							str+='<p><span style="margin-right:3px;display:inline-block;height:15px;width:15px;background-color:#3366CC;border:1px solid #ddd;border-radius:50%;"></span>G.O Issued/ Finalized</p>';
							str+='<h2>'+result.finalizedAndGoIssued+'</h2>';
						str+='</td>';
						str+='<td style="text-align:center;padding:20px 0px">';
							str+='<p><span style="margin-right:3px;display:inline-block;height:15px;width:15px;background-color:#00BD06;border:1px solid #ddd;border-radius:50%;"></span>Open Posts</p>';
							str+='<h2>'+result.openPost+'</h2>';
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			str+='<div class="col-sm-6">';
				for(var i in result.positinsList)
				{
					str+='<div class="posts-block">';
						str+='<h4 class="panel-title">'+result.positinsList[i].name+'</h4>';
						str+='<div class="pad_10">';
							str+='<ul class="list-border list-border-responsive">';
								str+='<li>';
									str+='<p class="text-muted">Received</p>';
									str+='<h4 class="m_top10">'+result.positinsList[i].applicatnsReceived+'</h4>';
								str+='</li>';
								str+='<li>';
									str+='<p class="text-muted">Total Posts</p>';
									str+='<h4 class="m_top10">'+result.positinsList[i].totalPosts+'</h4>';
								str+='</li>';
								str+='<li>';
									str+='<p class="text-muted">G.O Issued</p>';
									str+='<h4 class="m_top10">'+result.positinsList[i].finalizedAndGoIssued+'</h4>';
								str+='</li>';
								str+='<li>';
									str+='<p class="text-muted">Open Posts</p>';
									str+='<h4 class="m_top10">'+result.positinsList[i].openPost+'</h4>';
								str+='</li>';
							str+='</ul>';
						str+='</div>';
					str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		
		var id = "overAllAnalysisGraphId";
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
				var cnt = this.point.count;
				return "<b style='color:"+this.point.color+"'>"+this.point.name+" -<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
			}  
		}; 
		var plotOptions ={
			pie: {
				innerSize: 100,
				depth: 90,
				dataLabels: {
					enabled: false,
					formatter: function() {
						return (Highcharts.numberFormat(this.percentage,1)) + ' %';
					},
					distance: -20,
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
				  name: 'Applications Received',
				  y: result.applicatnsReceived,
				  color:"#FFD400"
				},
				{
				  name: 'Total Posts',
				  y: result.totalPosts,
				  color:"#DE7118"
				},
				{
				  name: 'G.O Issued / Finalized',
				  y: result.finalizedAndGoIssued,
				  color:"#3366CC"
				},
				{
				  name: 'Open Posts',
				  y: result.openPost,
				  color:"#00BD06"
				}
			]
		}];
		$("#overAllAnalysisBlockId").html(str);
		highcharts(id,type,data,plotOptions,title,tooltip,legend);
	}
	
}
function getAreaWiseDashboardCandidatesCountView(){
	$("#LocationWiseLevelBlockId,#positionLevelBlockId,#levelWiseBlockOverviewId").html(spinner);
	var jsObj={
		levelValues:["282"],
		levelId:4,
		statusIds:[3,4],
		fromDateStr:"28/09/2012",
      toDateStr:"07/10/2017",
      year:""
	}
	$.ajax({   
		type:'GET',
		url:'getAreaWiseDashboardCandidatesCountViewAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null)
		{
			return buildData(result);
		}else{
			$("#LocationWiseLevelBlockId").html("NO DATA AVAILABLE");
		}
	});
	function buildData(result)
	{
		var str='';
		var overview='';
		
		table+='<div class="table-responsive m_top10">';
			table+='<table class="table table-bordered text-center" style="background-color:#f2f2f2;">';
				table+='<thead class="text-center">';
					for(var i in result.list)
					{
						table+='<th>'+result.list[i].name+'</th>';
					}				
				table+='</thead>';
				table+='<tr>';
					for(var i in result.list)
					{
						table+='<td><h4>'+result.list[i].totalCount+'</h4><p class="text-success"></p>';
							table+='<ul class="list-border list-border-responsive" style="margin-top:10px;border-top:1px solid #ddd;padding-top:10px;display:block">';
								table+='<li>';
									table+='<img src="coreApi/img/male.png" style="height:30px;width:30px"/>';
									table+='<p>'+result.list[i].maleCount+'</p>';
								table+='</li>';
								table+='<li>';
									table+='<img src="coreApi/img/female.png" style="height:30px;width:30px"/>';
									table+='<p>'+result.list[i].femaleCount+'</p>';
								table+='</li>';
							table+='</ul>'
						table+='</td>';
					}
				table+='</tr>';
			table+='</table>';
		table+='</div>';
		$("#positionLevelBlockId").html(table);
		
		overview+='<div class="table-responsive">';
			overview+='<table class="table table-bordered text-center" id="LocationWiseLevelTableId">';
				overview+='<tr>';
					overview+='<td><div class="media"><div class="media-left"><img class="media-object" src="coreApi/img/group.png" style="height:30px;width:30px;"/></div><div class="media-body">TOTAL<br/> MEMBERS</div></div><h4 class="text-center">'+result.totalCount+'</h4></td>';
					overview+='<td><div class="media"><div class="media-left"><img class="media-object" src="coreApi/img/male.png" style="height:30px;width:30px;"/></div><div class="media-body">MALE</div></div><h4 class="text-center">'+result.maleCount+'</h4><small>'+result.perc+'</small></td>';
					overview+='<td><div class="media"><div class="media-left"><img class="media-object" src="coreApi/img/female.png" style="height:30px;width:30px;"/></div><div class="media-body">FEMALE</div></div><h4 class="text-center">'+result.femaleCount+'</h4><small>'+result.perc1+'</small></td>';
				overview+='</tr>';
			overview+='</table>';
		overview+='</div>';
		$("#levelWiseBlockOverviewId").html(overview);
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered" id="LocationWiseLevelTableId">';
				str+='<thead>';
					str+='<th>Location / Members</th>';
					str+='<th>Total</th>';
				for(var i in result.subList[0].subList)
				{
					str+='<th>'+result.subList[0].subList[i].name+'</th>';
				}
				str+='</thead>';
				for(var i in result.subList)
				{
					str+='<tr>';
						if(result.subList[i].name.length > 0)
						{
							str+='<td>'+result.subList[i].name+'</td>';
						}else{
							str+='<td>Others</td>';
						}
						
						str+='<td>'+result.subList[i].totalCount+'</td>';
						for(var j in result.subList[i].subList)
						{
							str+='<td>'+result.subList[i].subList[j].totalCount+'</td>';
						}
					str+='</tr>';
				}
			str+='</table>';
		str+='</div>';
		$("#LocationWiseLevelBlockId").html(str);
		$("#LocationWiseLevelTableId").dataTable({
			"iDisplayLength": 5,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			//"dom": 'lfBrtip',
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row m_top30'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o tooltipCls"></i>',
					titleAttr: 'CSV',
					title:	   "LocationWiseLevelTableId",
					filename:  "LocationWiseLevelTableId"+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o tooltipCls"></i>',
					titleAttr: 'PDF',
					title:	   "LocationWiseLevelTableId",
					filename:  'LocationWiseLevelTableId'+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A4',
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		});
	}
}


function getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetails(type){
	$("#"+type+"BlockId").html(spinner);
	var jobj = {
		locationValues 	: [282],
		statusIds 		:[3,4],
		levelId 		:4,
		type 			:type,// "ageRange"//or(casteCategory)
		fromDateStr:"28/09/2012",
      toDateStr:"07/10/2017",
      year:""
	}	
	$.ajax({
		type : "POST",
		url  : "getLocationWiseNominatedPostCandidateAgeRangeAndCasteCategorDetailsAction.action",
		data : {task:JSON.stringify(jobj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result);
		}else{
			$("#LocationWiseLevelBlockId").html("NO DATA AVAILABLE");
		}
	});
	function buildData(result)
	{
		table='';
		table+='<div class="table table-responsive">';
			table+='<table class="table table-bordered">';
				table+='<thead>';
					for(var i in result)
					{
						table+='<th>'+result[i].name+'</th>';
					}				
				table+='</thead>';
				table+='<tr>';
					for(var i in result)
					{
						table+='<td><h4>'+result[i].count+'</h4><p class="text-success"><small>'+result[i].perc+'%</small></p></td>';
					}
				table+='</tr>';
			table+='</table>';
		table+='</div>';
		$("#"+type+"BlockId").html(table);
	}
} 


function getLocationWiseNominatedPostAnalysisDetails(type){
	$("#"+type+"BlockId").html(spinner);
	var jsObj={
		boardLevelId:0,
		levelValues:["282"],
		levelId:4,
		dataType:type,
		statusIds:[3,4],
		fromDateStr:"28/09/2012",
      toDateStr:"07/10/2017",
      year:""
	}
	$.ajax({   
		type:'GET',
		url:'getLocationWiseNominatedPostAnalysisDetailsAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null && result.length > 0)
		{
			return buildTable(result);
		}else{
			$("#"+type+"BlockId").html("NO DATA AVAILABLE");
		}
		
	});
	function buildTable(result)
	{
		var table='';
		table+='<table class="table table-bordered" id="dataTable'+type+'">';
			table+='<thead>';
				table+='<tr>';
					if(type == "casteGroup" || type == "subCaste")
					{
						table+='<th rowspan="2" class="f-12">Caste</th>';
					}else if(type == "ageGroup")
					{
						table+='<th rowspan="2" class="f-12">BETWEEN AGE</th>';
					}else if(type == "mandal")
					{
						table+='<th rowspan="2" class="f-12">LOCATION/ MANDALS</th>';
					}
					table+='<th colspan="3" class="f-12">TOTAL MEMBERS</th>';
					
					if(type == "mandal" )
					{
						for(var i in result[1].subList)
						{
							if(result[1].subList[i].name.length > 13)
							{
								table+='<th rowspan="2" class="f-12"><span class="tooltipCls" title="'+result[1].subList[i].name+'">'+result[1].subList[i].name.substr(0,13)+'..</span></th>';
							}else{
								table+='<th rowspan="2" class="f-12">'+result[1].subList[i].name+'</th>';
							}
							
						}
					}else{
						for(var i in result[0].subList)
						{
							if(result[0].subList[i].name.length > 13)
							{
								table+='<th rowspan="2" class="f-12"><span class="tooltipCls" title="'+result[0].subList[i].name+'">'+result[1].subList[i].name.substr(0,13)+'..</span></th>';
							}else{
								table+='<th rowspan="2" class="f-12">'+result[0].subList[i].name+'</th>';
							}
						}
					}
				table+='</tr>';
				table+='<tr>';
					table+='<th style="background-color:#F4F4F4"><img src="coreApi/img/group.png" style="height:30px;width:30px"/></th>';
					table+='<th style="background-color:#F4F8FF"><img src="coreApi/img/male.png" style="height:30px;width:30px"/></th>';					
					table+='<th style="background-color:#FFF3F8"><img src="coreApi/img/female.png" style="height:30px;width:30px"/></th>';					
				table+='</tr>';
			table+='</thead>';
			table+='<tbody>';
				for(var i in result)
				{
					if(type == "mandal" )
					{
						if(i != 0)
						{
							table+='<tr>';
								table+='<td>'+result[i].name+'</td>';
								table+='<td style="background-color:#F4F4F4">'+result[i].totalCount+'</td>';
								table+='<td style="background-color:#F4F8FF">'+result[i].maleCount+'</td>';
								table+='<td style="background-color:#FFF3F8">'+result[i].femaleCount+'</td>';
								for(var j in result[i].subList)
								{
									table+='<td>'+result[i].subList[j].totalCount+'</td>';
								}
							table+='</tr>';
						}
						
					}else{
						table+='<tr>';
							table+='<td>'+result[i].name+'</td>';
							table+='<td style="background-color:#F4F4F4">'+result[i].totalCount+'</td>';
							table+='<td style="background-color:#F4F8FF">'+result[i].maleCount+'</td>';
							table+='<td style="background-color:#FFF3F8">'+result[i].femaleCount+'</td>';
							for(var j in result[i].subList)
							{
								table+='<td>'+result[i].subList[j].totalCount+'</td>';
							}
						table+='</tr>';
					}
					
				}
			table+='</tbody>';
		table+='</table>';
		$("#"+type+"BlockId").html(table);
		$("#dataTable"+type).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			//"dom": 'lfBrtip',
			"scrollX":        true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			},
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row m_top30'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o tooltipCls"></i>',
					titleAttr: 'CSV',
					title:	   type,
					filename:  type+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o tooltipCls"></i>',
					titleAttr: 'PDF',
					title:	   type,
					filename:  type+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A4',
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		});
		$(".tooltipCls").tooltip();
	}
}