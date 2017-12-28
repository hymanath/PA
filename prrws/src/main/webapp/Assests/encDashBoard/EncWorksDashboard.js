var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['state','district','constituency','mandal']
var glStartDate = "01-04-"+moment().subtract(40,'years').format("YYYY");

var glEndDate = "01-04-"+moment().add(10, 'years').format("YYYY");

var blocksArr=[{name:'Total Works',id:'totalworksId'},{name:'Target Works',id:'targetworksId'}];
onloadCalls();
function onloadCalls(){
	
	levelWiseOverview();
	getLocationWiseWorksInformation("state","s","graph");
	getLocationWiseWorkTargetsNacheivements("state","s","graph");
	getLocationWiseExceededWorkDetails("state","state","graph")
	
}

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

function levelWiseOverview()
{
	var collapse = '';
	collapse+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in levelWiseOverviewArr)
		{
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseOverviewArr[i]+'">';
					if(i ==0)
					{
						collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}
					
						collapse+='<h4 class="panel-title text-capital">'+levelWiseOverviewArr[i]+' level overview</h4>';
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}
				
				
					collapse+='<div class="panel-body">';
						
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-12">';
								collapse+='<div id="'+levelWiseOverviewArr[i]+'levelBlockId"></div>';
							collapse+='</div>';
						collapse+='</div>';
					
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	collapse+='</div>';
	$("#levelWiseOverviewId").html(collapse);
	$(".chosen-select").chosen({width :'100%'});
	for(var i in levelWiseOverviewArr)
	{
		if(levelWiseOverviewArr[i] == "state"){
			//getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','s','table');
			//getLocationWiseWorkTargetsNacheivements(levelWiseOverviewArr[i]+'levelBlockId','s','table');
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','state','table');
		}
		else if(levelWiseOverviewArr[i] == "district"){
			//getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','d','table');
			//getLocationWiseWorkTargetsNacheivements(levelWiseOverviewArr[i]+'levelBlockId','d','table');
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','district','table');
		}
		else if(levelWiseOverviewArr[i] == "constituency"){
			
			//getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','a','table');
			//getLocationWiseWorkTargetsNacheivements(levelWiseOverviewArr[i]+'levelBlockId','a','table');
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','constituency','table');
		}
		else if(levelWiseOverviewArr[i] == "mandal"){
			//getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','m','table');
			//getLocationWiseWorkTargetsNacheivements(levelWiseOverviewArr[i]+'levelBlockId','m','table');
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','mandal','table');
		}
		
	}
	
}

function getLocationWiseWorksInformation(blockId,locationType,type){
	if(type=='graph'){
		$("#enclocationWiseChart").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType
		}
		$.ajax({                
			type:'POST',    
			url: 'getLocationWiseWorksInformation',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildLocationWiseWorksGraph(result)
				}
				//locationwiseTableBlocks(result,blockId,locationType);
			}else{
				
				$('#'+blockId).html("NO DATA AVAILABLE");
			}
		});
}
function locationwiseTableBlocks(result,blockId,locationType){
	var table='';
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId">';
				table+='<thead>';
					if(blockId == 'statelevelBlockId'){
						table+='<th>STATE</th>';
					}
					else if(blockId == 'districtlevelBlockId'){
						table+='<th>DISTRICTS</th>';
					}else if(blockId == 'constituencylevelBlockId'){
						table+='<th>CONSTITUENCY</th>';
					}else if(blockId == 'mandallevelBlockId'){
						table+='<th>MANDALS</th>';
					}
					table+='<th>ADMIN SANCTIONED</th>';
					table+='<th>TECHNICALLY SANCTIONED</th>';
					table+='<th>ENTRUSTED</th>';
					table+='<th>UNDER PROCESS</th>';
					table+='<th>GROUNDED</th>';
					table+='<th>COMPLETED</th>';
					table+='<th>NOT GROUNDED</th>';
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					table+='<tr>';
						table+='<td>'+result[i].locationName+'</td>';
						table+='<td>'+result[i].adminSanctionCount+'</td>';
						table+='<td>'+result[i].technicallySanctionedCount+'</td>';
						table+='<td>'+result[i].totalWorksEntrusted+'</td>';
						table+='<td>'+result[i].underProcessCount+'</td>';
						table+='<td>'+result[i].groundedCount+'</td>';
						table+='<td>'+result[i].completedCount+'</td>';
						table+='<td>'+result[i].notGrounded+'</td>';
					table+='</tr>';
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockId).html(table);
		if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "ENC WORKS DASHBOARD",
					filename	: blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
}

function buildLocationWiseWorksGraph(result){

	$("#enclocationWiseChart").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
	   
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			type: 'category'
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}

		},
		legend: {
			enabled: false
		},
		plotOptions: {
			series: {
				borderWidth: 0,
				dataLabels: {
					enabled: true,
					format: '{point.y}'
				}
			}
		},

		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
		},

		series: [{
			name: 'Works',
			colorByPoint: true,
			data: [{
				name: 'Admin Sanctioned',
				y: result[0].adminSanctionCount,
				color:'#5fc24f'
				
			}, {
				name: 'Techincal Sancationed',
				y: result[0].technicallySanctionedCount,
				color:'#418CF0	'
			},{
				name: 'Entrusted',
				y: result[0].totalWorksEntrusted,
				color:'#FFBF00'
				
			},{
				name: 'Grounded',
				y: result[0].groundedCount,
				color:'#ACFA58'
			},{
				name: 'UnderProcess',
				y: result[0].underProcessCount,
				color:'#FA5858'
			},{
				name:'Completed',
				y:result[0].completedCount,
				color:'#009999'
			},{
				name: 'Not Grounded',
				y: result[0].notGrounded,
				color:'#DF013A'
			}]
		}]
	});
}

function getLocationWiseWorkTargetsNacheivements(blockId,locationType,type){
	if(type=='graph'){
		$("#TargetNAcheievementDetailsTotal").html(spinner);
		$("#quaterWiseTargetAchievement").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType
		}
		$.ajax({                
			type:'POST',    
			url: 'getEncTargetsAchievement',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildGraph(result)
				}
				buildTargetsAndAchievemntsBlocks(result,blockId,locationType);
			}else{
				
				$('#'+blockId).html("NO DATA AVAILABLE");
			}
		});
}


function buildGraph(result)	{
		var colorsArr=['#EE6CA9','#C61379'];
		var cateArr = [];
		var targetsArr = [];
		var AchievmentArr = [];
		
		cateArr.push("Quarter-1");
		cateArr.push("Quarter-2");
		cateArr.push("Quarter-3");
		cateArr.push("Quarter-4");
		
		targetsArr.push({"y":result[0].q1Target,"extra":""});
		targetsArr.push({"y":result[0].q2Target,"extra":""});
		targetsArr.push({"y":result[0].q3Target,"extra":""});
		targetsArr.push({"y":result[0].q4Target,"extra":""});
		AchievmentArr.push({"y":result[0].q1Achv,"extra":""});
		AchievmentArr.push({"y":result[0].q2Achv,"extra":""});
		AchievmentArr.push({"y":result[0].q3Achv,"extra":""});
		AchievmentArr.push({"y":result[0].q4Achv,"extra":""});
		
		$("#TargetNAcheievementDetailsTotal").highcharts({
			chart: {
				type: 'column'
				
			},
			title: {
				text: null
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories:["Total"]
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.x + '</b><br/>' +
						this.series.name + ': ' + this.y + '<br/>'
						//+'Total: ' + this.point.stackTotal;
				}
			},
			plotOptions: {
				column: {
					stacking: 'normal',
					 pointPadding: 0.2,
				}
			},
			series: [{
				name: 'Targets',
				data: [result[0].totTarget],
				stack: 'targets',
				color:'#EE6CA9'
			}, {
				name: 'Achievments',
				data: [result[0].totAchv],
				stack: 'Achievments',
				color:'#C61379'
			}]
		});
		$("#quaterWiseTargetAchievement").highcharts({
			chart: {
				type: 'column'
			},
			title: {
				text: null
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories:cateArr
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			tooltip: {
				formatter: function () {
					var value = (this.point.extra).split("-");
					return '<b>' + this.x + '</b><br/>' +
						this.series.name + ': ' + this.y ;
						//'Total: ' + this.point.stackTotal + '<br/>' +
				}
			},
			plotOptions: {
				column: {
					stacking: 'normal',
					pointPadding: 0.2,
				}
			},
			series: [{
				name: 'Targets',
				data: targetsArr,
				stack: 'Targets',
				color:'#EE6CA9'
			}, {
				name: 'Achievments',
				data: AchievmentArr,
				stack: 'Achievments',
				color:'#C61379'
			}]
		});
	}
	
	
	function buildTargetsAndAchievemntsBlocks(result,blockId,locationType){
	var table='';
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId">';
				table+='<thead>';
					table+='<tr>';
						if(blockId == 'statelevelBlockId'){
							table+='<th rowspan="2">STATE</th>';
						}
						else if(blockId == 'districtlevelBlockId'){
							table+='<th rowspan="2">DISTRICTS</th>';
						}else if(blockId == 'constituencylevelBlockId'){
							table+='<th rowspan="2">CONSTITUENCY</th>';
						}else if(blockId == 'mandallevelBlockId'){
							table+='<th rowspan="2">MANDALS</th>';
						}
						table+='<th colspan="6" class="text-center">TOTAL</th>';
						table+='<th colspan="3" class="text-center">QUERTER 1</th>';
						table+='<th colspan="3" class="text-center">QUERTER 2</th>';
						table+='<th colspan="3" class="text-center">QUERTER 3</th>';
						table+='<th colspan="3" class="text-center">QUERTER 4</th>';
					table+='</tr>';
					table+='<tr>';
						table+='<th>TOTAL WORKS</th>';
						table+='<th>TARGET</th>';
						table+='<th>ACHIEVEMENT</th>';
						table+='<th>%</th>';
						table+='<th>LENGTH</th>'
						table+='<th>BENFITED POPULATION</th>';
						table+='<th>TARGET</th>';
						table+='<th>ACHIEVEMENT</th>';
						table+='<th>%</th>';
						table+='<th>TARGET</th>';
						table+='<th>ACHIEVEMENT</th>';
						table+='<th>%</th>';
						table+='<th>TARGET</th>';
						table+='<th>ACHIEVEMENT</th>';
						table+='<th>%</th>';
						table+='<th>TARGET</th>';
						table+='<th>ACHIEVEMENT</th>';
						table+='<th>%</th>';
					table+='</tr>';
					
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					table+='<tr>';
						table+='<td>'+result[i].locationName+'</td>';
						table+='<td>'+result[i].totWorks+'</td>';
						table+='<td>'+result[i].totTarget+'</td>';
						table+='<td>'+result[i].totAchv+'</td>';
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].totAchv/result[i].totTarget)*100).toFixed(2)+'%</small></td>';
						table+='<td>'+parseFloat(result[i].totLength).toFixed(2)+'</td>';
						table+='<td>'+parseFloat(result[i].totPopu).toFixed(2)+'</td>';
						table+='<td>'+result[i].q1Target+'</td>';
						table+='<td>'+result[i].q1Achv+'</td>';
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q1Achv/result[i].q1Target)*100).toFixed(2)+'%</small></td>';
						table+='<td>'+result[i].q2Target+'</td>';
						table+='<td>'+result[i].q2Achv+'</td>';
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q2Achv/result[i].q2Target)*100).toFixed(2)+'%</small></td>';
						table+='<td>'+result[i].q3Target+'</td>';
						table+='<td>'+result[i].q3Achv+'</td>';
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q3Achv/result[i].q3Target)*100).toFixed(2)+'%</small></td>';
						table+='<td>'+result[i].q4Target+'</td>';
						table+='<td>'+result[i].q4Achv+'</td>';
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q4Achv/result[i].q4Target)*100).toFixed(2)+'%</small></td>';
						
					table+='</tr>';
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockId).html(table);
		if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "ENC WORKS DASHBOARD",
					filename	: blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
}

function getLocationWiseExceededWorkDetails(blockId,locationType,type){
	if(type=='graph'){
		$("#ExceededWorkDetailsGraph").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType,
			frodateStr:"",
			toDateStr:"",
		}
		$.ajax({                
			type:'POST',    
			url: 'getExceededEncWorks',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildGraphforExceededWorks(result)
				}
				buildExceededWorksBlocks(result,blockId,locationType);
			}else{
				if(type=='graph'){
					$("#ExceededWorkDetailsGraph").html("NO DATA AVAILABLE");
				}else{
					$('#'+blockId).html("NO DATA AVAILABLE");
				}
			}
		});
}
function buildGraphforExceededWorks(response){
	
	var dataArr = [];
	var totalCount=0;
	var statusNamesArr=[];
	var colors = []
	for(var i in response){
	  for(var j in response[i].subList){
			var tempArr = [];
			statusNamesArr.push(response[i].subList[j].name);
			dataArr.push({"y":response[i].subList[j].count,"extra":""+response[i].subList[j].ongoingPWSExceededCount+"-"+response[i].subList[j].completedPWSExceededCount});
			totalCount=totalCount+response[i].subList[j].count;
			
			if(response[i].subList[j].name == "In Time"){
				colors.push('#14BAAD')
			}else if(response[i].subList[j].name == "1-30 Days"){
				colors.push('#FC5049')
			}else if(response[i].subList[j].name == "31-60 Days"){
				colors.push('#FC5059')
			}else if(response[i].subList[j].name == "91-180 Days"){
				colors.push('#FC5068')
			}else if(response[i].subList[j].name == "181-365 Days"){
				colors.push('#FC5079')
			}else{
				colors.push('#FFBF14')
			}
		}
	}
	$("#ExceededWorkDetailsGraph").highcharts({
		chart: {
			type: 'column'
			
		},
		title: {
			text: null
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: statusNamesArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function () {
				var value = (this.point.extra).split("-");
				return '<b>' + this.x + '</b><br/>' +
					'Total : ' + this.y + '<br/>'+
					//'Total: ' + this.point.stackTotal + '<br/>' +
					'OnGoingExceededWorks :' +value[0]+ '<br/>' +
					'CompletedExceededWorks :' +value[1]+ '';
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
		series: [{
			name: '',
			data: dataArr,
			showInLegend: false,
		}]
	});
}
 function buildExceededWorksBlocks(result,blockId,locationType){
	 var table='';
	 	table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId1">';
				table+='<thead>';
					table+='<tr>';
						if(blockId == 'statelevelBlockId'){
							table+='<th>STATE</th>';
						}else if(blockId == 'districtlevelBlockId'){
							table+='<th>DISTRICTS</th>';
						}else if(blockId == 'constituencylevelBlockId'){
							table+='<th>CONSTITUENCY</th>';
						}else if(blockId == 'mandallevelBlockId'){
							table+='<th>MANDALS</th>';
						}
						table+='<th>TOTAL WORKS</th>';
						for(var i in result[0].subList){
							table+='<th>'+result[0].subList[i].name+'</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							table+='<td>'+result[i].name+'</td>';
							table+='<td>'+result[i].count+'</td>';
							for(var j in result[i].subList){		
								table+='<td>'+result[i].subList[j].count+'</td>';
							}
						table+='</tr>';
					}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockId).html(table);
		
		if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId1").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "ENC WORKS DASHBOARD",
					filename	: blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
 }	 