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
	getLocationWiseExceededWorkDetails("state","state","graph");
	getLocationWiseNotGroundedExceededWorkDetails("state","state","graph");
	
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
								collapse+='<ul class="list-inline switch-btn workWiseDetailsCls">';
									collapse+='<li attr_type="works" attr_location_type="'+levelWiseOverviewArr[i]+'">Complete Work OverView</li>';
									collapse+='<li class="active" attr_type="exceed" attr_location_type="'+levelWiseOverviewArr[i]+'">Exceed Works OverView</li>';
									collapse+='<li attr_type="notGrounded" attr_location_type="'+levelWiseOverviewArr[i]+'">NotGrounded Exceed Works OverView</li>';
									collapse+='<li attr_type="targets" attr_location_type="'+levelWiseOverviewArr[i]+'">Target&Achivements OverView</li>';
								collapse+='</ul>';
							collapse+='</div>';
						collapse+='</div>';
						collapse+='<div class="row m_top20">';
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
	var url="";
	if(type=='graph'){
		$("#enclocationWiseChart").html(spinner);
		$("#enclocationWiseChart2").html(spinner);
		url="getLocationWiseWorksgraphInformation";
	}else{
		$("#"+blockId).html(spinner);
		url="getLocationWiseWorksInformation";
	}
		var json = {
			locationType:locationType
		}
		$.ajax({                
			type:'POST',    
			url: url,
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null){
				if(type=='graph'){
					buildLocationWiseWorksGraph(result)
				}
				locationwiseTableBlocks(result,blockId,locationType);
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
									
					table+='<th style="background-color:#def2f7">ADMIN SANCTIONED</th>';
					table+='<th style="background-color:#def2f7">TECHNICALLY SANCTIONED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#def2f7">ENTRUSTED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#f9e3f0">GROUNDED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">UNDER PROCESS</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">COMPLETED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">NOT GROUNDED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					table+='<tr>';
						table+='<td style="background-color:#fff">'+result[i].locationName+'</td>';
						table+='<td style="background-color:#def2f7">'+result[i].adminSanctionCount+'</td>';
						table+='<td style="background-color:#def2f7">'+result[i].technicallySanctionedCount+'</td>';
						table+='<td style="background-color:#def2f7">'+parseFloat((result[i].technicallySanctionedCount/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
						table+='<td style="background-color:#def2f7">'+result[i].totalWorksEntrusted+'</td>';
						table+='<td style="background-color:#def2f7">'+parseFloat((result[i].totalWorksEntrusted/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
						table+='<td style="background-color:#f9e3f0">'+result[i].groundedCount+'</td>';
						table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].groundedCount/result[i].totalWorksEntrusted)*100).toFixed(2)+'</td>';
						table+='<td style="background-color:#f9e3f0">'+result[i].underProcessCount+'</td>';
						table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].underProcessCount/result[i].groundedCount)*100).toFixed(2)+'</td>';
						table+='<td style="background-color:#f9e3f0">'+result[i].completedCount+'</td>';
						table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].completedCount/result[i].groundedCount)*100).toFixed(2)+'</td>';
						table+='<td style="background-color:#f9e3f0">'+result[i].notGrounded+'</td>';
						table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].notGrounded/result[i].totalWorksEntrusted)*100).toFixed(2)+'</td>';
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
	var str='';
	var str1='';
	str+='<table id="datatable">';
		str+='<thead>';
			str+='<tr>';
				str+='<th></th>';
				str+='<th>OverAll Works</th>';
				str+='<th>Exceed Works</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result.subList){
			if(result.subList[i].locationName !="entrusted" && result.subList[i].locationName !="technicallSanctioned"){
				str+='<tr>';
					str+='<th>'+result.subList[i].locationName+'</th>';
					str+='<td>'+result.subList[i].notGrounded+'</td>';
					str+='<td>'+result.subList[i].notGroundedExceededCount+'</td>';
				str+='</tr>';
			}
			
		}
			
		str+='</tbody>';
	str+='</table>';

	str1+='<table id="datatable2">';
		str1+='<thead>';
			str1+='<tr>';
				str1+='<th></th>';
				str1+='<th>sanctioned Works</th>';
				str1+='<th>Pending Works</th>';
			str1+='</tr>';
		str1+='</thead>';
		str1+='<tbody>';
		for(var i in result.subList){
			if(result.subList[i].locationName =="entrusted" || result.subList[i].locationName=="technicallSanctioned"){
				str1+='<tr>';
					str1+='<th>'+result.subList[i].locationName+'</th>';
					str1+='<td>'+result.subList[i].notGrounded+'</td>';
					str1+='<td>'+result.subList[i].notGroundedExceededCount+'</td>';
				str1+='</tr>';
			}
			
		}
			
		str1+='</tbody>';
	str1+='</table>';
	$("#datatable1").html(str);
	$("#datatable3").html(str1);
	
	$("#enclocationWiseChart").highcharts({
		colors:['#16af18','red'],
		data: {
			table: 'datatable'
		},
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		plotOptions: {
			column: {
				//colorByPoint: true
				dataLabels: {
					useHTML:true,
					enabled: true,
					formatter: function() {
						if(this.y == 0){
							return null;
						}else{
							return '<span>'+this.y+'</span>';
						}
						
					}
				}
			}/* ,
			series: {
				pointWidth: 10
			} */
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			title: {
				text: 'Units'
			}
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.series.name + '</b><br/>' +
					this.point.y + '';
			}
		}
	});
	$("#enclocationWiseChart2").highcharts({
		colors:['#16af18','red'],
		data: {
			table: 'datatable2'
		},
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
		plotOptions: {
			column: {
				//colorByPoint: true
				dataLabels: {
					useHTML:true,
					enabled: true,
					formatter: function() {
						if(this.y == 0){
							return null;
						}else{
							return '<span>'+this.y+'</span>';
						}
						
					}
				}
			}
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			title: {
				text: 'Units'
			}
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.series.name + '</b><br/>' +
					this.point.y + '';
			}
		}
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
					//colorByPoint: true
					dataLabels: {
						useHTML:true,
						enabled: true,
						formatter: function() {
							if(this.y == 0){
								return null;
							}else{
								return '<span>'+this.y+'</span>';
							}
							
						}
					}
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
					//colorByPoint: true
					dataLabels: {
						useHTML:true,
						enabled: true,
						formatter: function() {
							if(this.y == 0){
								return null;
							}else{
								return '<span>'+this.y+'</span>';
							}
							
						}
					}
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
						if(result[i].totTarget != 0 && result[i].totTarget != null){
							table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].totAchv/result[i].totTarget)*100).toFixed(2)+'%</small></td>';
						}else{
							table+='<td>-</td>';
						}
						table+='<td>'+parseFloat(result[i].totLength).toFixed(2)+'</td>';
						table+='<td>'+parseFloat(result[i].totPopu).toFixed(2)+'</td>';
						table+='<td>'+result[i].q1Target+'</td>';
						table+='<td>'+result[i].q1Achv+'</td>';
						if(result[i].q1Target != 0 && result[i].q1Target != null){
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q1Achv/result[i].q1Target)*100).toFixed(2)+'%</small></td>';
						}else{
							table+='<td>-</td>';
						}
						table+='<td>'+result[i].q2Target+'</td>';
						table+='<td>'+result[i].q2Achv+'</td>';
						if(result[i].q2Target != 0 && result[i].q2Target != null){
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q2Achv/result[i].q2Target)*100).toFixed(2)+'%</small></td>';
						}else{
							table+='<td>-</td>';
						}
						table+='<td>'+result[i].q3Target+'</td>';
						table+='<td>'+result[i].q3Achv+'</td>';
						if(result[i].q3Target != 0 && result[i].q3Target != null){
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q3Achv/result[i].q3Target)*100).toFixed(2)+'%</small></td>';
						}else{
							table+='<td>-</td>';
						}
						table+='<td>'+result[i].q4Target+'</td>';
						table+='<td>'+result[i].q4Achv+'</td>';
						if(result[i].q4Target != 0 && result[i].q4Target != null){
						table+='<td><small style="color:#0FBE08">'+parseFloat((result[i].q4Achv/result[i].q4Target)*100).toFixed(2)+'%</small></td>';
						}else{
							table+='<td>-</td>';
						}
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
			dataArr.push({"y":response[i].subList[j].count,color: "#EE6CA9","extra":""+response[i].subList[j].ongoingPWSExceededCount+"-"+response[i].subList[j].completedPWSExceededCount});
			totalCount=totalCount+response[i].subList[j].count;
			
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
				//colorByPoint: true
				dataLabels: {
					useHTML:true,
					enabled: true,
					color: '#000',
				align: 'center',
					formatter: function() {
						if(this.y == 0){
							return null;
						}else{
							var pcnt = (this.y / totalCount) * 100;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}
						
					}
				}
			}
		},
		series: [{
			name: '',
			data: dataArr,
			showInLegend: false
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
							table+='<th rowspan="2">STATE</th>';
						}else if(blockId == 'districtlevelBlockId'){
							table+='<th rowspan="2">DISTRICTS</th>';
						}else if(blockId == 'constituencylevelBlockId'){
							table+='<th rowspan="2">CONSTITUENCY</th>';
						}else if(blockId == 'mandallevelBlockId'){
							table+='<th rowspan="2">MANDALS</th>';
						}
						table+='<th rowspan="2">TOTAL WORKS</th>';
						for(var i in result[0].subList){
							table+='<th colspan="2">'+result[0].subList[i].name+'</th>';			
						}
					table+='</tr>';
					table+='<tr>';
						for(var i in result[0].subList){
							table+='<th>works</th>';
							table+='<th><span class="colspanLenIncstate"><i class="fa fa-inr m_top5" style="font-size:12px" aria-hidden="true"></i> In Crores</span></th>';
							
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
								table+='<td>'+parseFloat(result[i].subList[j].sanctionedAmount/10000000).toFixed(2)+'</td>';
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

$(document).on("click",".workWiseDetailsCls li",function(e){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var type = $(this).attr("attr_type");
	var locationType = $(this).attr("attr_location_type")
	if(type == "works"){
		if(locationType == "state"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','s','table');
		}
		else if(locationType == "district"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','d','table');
			
		}
		else if(locationType == "constituency"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','a','table');
		}
		else if(locationType == "mandal"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','m','table');
		}
	}else if(type == "exceed"){
		if(locationType == "state"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','state','table');
		}
		else if(locationType == "district"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','district','table');
		}
		else if(locationType == "constituency"){
			
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','constituency','table');
		}
		else if(locationType == "mandal"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','mandal','table');
		}
	}else if(type == "targets"){
		if(locationType == "state"){
			getLocationWiseWorkTargetsNacheivements(locationType+'levelBlockId','s','table');
		}
		else if(locationType == "district"){
			getLocationWiseWorkTargetsNacheivements(locationType+'levelBlockId','d','table');
		}
		else if(locationType == "constituency"){
			getLocationWiseWorkTargetsNacheivements(locationType+'levelBlockId','a','table');
		}
		else if(locationType == "mandal"){
			getLocationWiseWorkTargetsNacheivements(locationType+'levelBlockId','m','table');
			
		}
	}else if(type == "notGrounded"){
		if(locationType == "state"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','state','table');
		}
		else if(locationType == "district"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','district','table');
		}
		else if(locationType == "constituency"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','constituency','table');
		}
		else if(locationType == "mandal"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','mandal','table');
			
		}
	}
});

function getLocationWiseNotGroundedExceededWorkDetails(blockId,locationType,type){
	if(type=='graph'){
		$("#NotGroundedExceededWorkDetailsGraph").html(spinner);
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
			url: 'getLocationWiseNotGroundedWorks',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildNotGroundedGraphforExceededWorks(result)
				}
				buildExceededWorksBlocks(result,blockId,locationType);
			}else{
				if(type=='graph'){
					$("#NotGroundedExceededWorkDetailsGraph").html("NO DATA AVAILABLE");
				}else{
					$('#'+blockId).html("NO DATA AVAILABLE");
				}
			}
		});
}
function buildNotGroundedGraphforExceededWorks(response){
	
	var dataArr = [];
	var totalCount=0;
	var statusnotGroundNamesArr=[];
	var colors1 = []
	for(var i in response){
		for(var j in response[i].subList){
			if(response[i].subList[j].name != "In Time"){
				var tempArr = [];
				statusnotGroundNamesArr.push(response[i].subList[j].name);
				dataArr.push({"y":response[i].subList[j].count,color: "#C61379","extra":""+response[i].subList[j].ongoingPWSExceededCount+"-"+response[i].subList[j].completedPWSExceededCount});
				totalCount=totalCount+response[i].subList[j].count;
			}
		}
	}
	$("#NotGroundedExceededWorkDetailsGraph").highcharts({
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
			categories: statusnotGroundNamesArr,
			
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
					'Not grounded Exceeded : ' + this.y + '';
			}
		},
		plotOptions: {
			column: {
				//colorByPoint: true
				dataLabels: {
					useHTML:true,
					enabled: true,
					color: '#000',
				align: 'center',
					formatter: function() {
						if(this.y == 0){
							return null;
						}else{
							var pcnt = (this.y / totalCount) * 100;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}
						
					}
				}
			}
		},
		series: [{
			name: '',
			data: dataArr,
			showInLegend: false
		}]
	});
}
/*  $(document).ready(function() {
	alert(1);
        $('#showmenu').click(function() {
             $('.menu').slideToggle("fast");
			getLocationWiseWorkTargetsNacheivements("state","s","graph");
        });
    });  */

$(".collapseClick").click(function(){
	getLocationWiseWorkTargetsNacheivements("state","s","graph");
});