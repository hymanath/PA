var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStatusObj={"SATISFIED":"#0FBE08","NOT SATISFIED":"#FF0909","PAR SATISFIED":"#FFBA00"}
$(".chosen-select").chosen();
getJalavaniDashBoardOverview();
var locationArr=['district','constituency','mandal'];
getJalavaniAlertSourceDetailsInformation()
levelWiseJalavaniDetails('jalavani',"onload");

$(document).on("click",".tab_bordered li",function(){
	$(this).closest("ul").find("li").removeClass("active_li");
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active_li");
	var blockType = $(this).attr("attr_type");
	var blockCount = $(this).attr("attr_block_count");
	if($(this).hasClass('active_li')){
		if(blockType == "0"){
			$("#viewTypeId").val('Alert');
			$("#viewTypeId").trigger("chosen:updated");
			getJalavaniDashBoardOverview();
			levelWiseJalavaniDetails('jalavani',"onload");
		}else{
			getJalavaniCategoryWiseDetailsInfo(blockType,blockCount);
			$("#viewTypeId").val('Alert');
			$("#viewTypeId").trigger("chosen:updated");
			if(blockType == "callcenter"){
				$("#alertTypeId").val(4);
				$("#alertTypeId").trigger("chosen:updated");
				levelWiseJalavaniDetails('jalavani',blockType);
			}else if(blockType == "print"){
				$("#alertTypeId").val(2);
				$("#alertTypeId").trigger("chosen:updated");
				levelWiseJalavaniDetails('jalavani',"printTabClick");
			}else if(blockType == "electronic"){
				$("#alertTypeId").val(3);
				$("#alertTypeId").trigger("chosen:updated");	
				levelWiseJalavaniDetails('jalavani',"electronicTabClick");
			}else if(blockType == "social"){
				$("#alertTypeId").val(5);
				$("#alertTypeId").trigger("chosen:updated");
				levelWiseJalavaniDetails('jalavani',"socialTabClick");
			}
		}
	}
	
});

function getJalavaniDashBoardOverview(){
	$("#jalavaniTabOverVewDivId").html(spinner);
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018"
	}
	$.ajax({                
	type:'POST',    
	url: 'getJalavaniDashBoardOverview',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
	}).done(function(result){
		if(result !=null){
			buildJalavaniDashBoardOverview(result);
		}
	});
}
function buildJalavaniDashBoardOverview(result){
	var str='';
	var allSourceCount=result.categoryCount+result.printCount+result.electCount;
	 str+='<div class="">';
		str+='<div class="col-sm-3">';
			str+='<ul class="nav nav-tabs tab_bordered">';
				str+='<li role="presentation" class="active_li" attr_type="All">';
					str+='<a href="#all_Sources_Id" data-toggle="tab" class="">';
						str+='<div class="row">';
							str+='<div class="col-sm-10">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									 str+='<img src="Assests/images/icon_all.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>ALL SOURCES</b></h5>';
										str+='<h4 class="color_black">'+allSourceCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';	
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="callcenter" attr_block_count="'+result.categoryCount+'">';
					str+='<a href="#callcenter" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-10">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/call_center_icon.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>CALL CENTER</b></h5>';
										str+='<h4 class="color_black">'+result.categoryCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="print" attr_block_count="'+result.printCount+'">';
					str+='<a href="#print" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-10">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/print_media_icon.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>PRINT MEDIA</b></h5>';
										str+='<h4 class="color_black">'+result.printCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="electronic" attr_block_count="'+result.electCount+'">';
					str+='<a href="#electronic" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-10">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/electronic_media_icon.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>ELECTRONIC MEDIA</b></h5>';
										str+='<h4 class="color_black">'+result.electCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="social" attr_block_count="'+result.electCount+'">';
					str+='<a href="#social" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-10">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/electronic_media_icon.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>SOCIAL MEDIA</b></h5>';
										str+='<h4 class="color_black">'+result.electCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-2">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
			str+='</ul>';
		str+='</div>';
		str+='<div class="col-sm-9 border_tab_content">';
			str+='<div class="">';
				str+='<div class="tab-content pad_10">';
					str+='<div class="tab-pane active" id="all_Sources_Id">';
						str+='<div class="row m_top10">';
							var totalAlertCount=0;
							for(var i in result.subList1){
								totalAlertCount = totalAlertCount+result.subList1[i].alertCnt;
							}
							str+='<div class="col-sm-2">';
								str+='<div class="brdR_3 pad_10" style="border: 1px solid #4C9DD6;">';
									str+='<h5 class="font_weight" style="color:#4C9DD6">TOTAL <br/>ALERTS</h5>';
									str+='<h4 class="m_top10">'+totalAlertCount+'</h4>';
								str+='</div>';
							str+='</div>';	
							var colorObj={'Print Media':'#575858','Electronic Media':'#F26532','Call Center':'#E952A0','Social Media':'#0849FF'}
							for(var i in result.subList1){
								str+='<div class="col-sm-2">';
									str+='<div class="brdR_3 pad_10" style="border: 1px solid '+colorObj[result.subList1[i].name]+';">';
										if(result.subList1[i].name == "Electronic Media"){
											str+='<h5 class="font_weight text-capital" style="color:'+colorObj[result.subList1[i].name]+'">Electronic&nbsp;Media <br/>Alerts</h5>';
										}else{
											str+='<h5 class="font_weight text-capital" style="color:'+colorObj[result.subList1[i].name]+'">'+result.subList1[i].name+' <br/>Alerts</h5>';
										}
										
										str+='<h4 class="m_top10">'+result.subList1[i].alertCnt+'</h4>';
									str+='</div>';
								str+='</div>';
							}
						str+='</div>';
						str+='<div class="bg_yash_color_10 m_top10">';
							str+='<h5 class="font_weight">ALERTS - MONTHLY OVERVIEW</h5>';
							str+='<div class="row">';
								str+='<div style="padding:15px;">';
									str+='<div id="areasplineChartId" style="height:300px;"></div>';
								str+='</div>';
							str+='</div>';
							var alertStatusTotalCount=0;
							for(var i in result.list){
								alertStatusTotalCount=alertStatusTotalCount+result.list[i].statusCount
							}
							str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';
							str+='<div class="row">';
								str+='<div style="padding:15px;">';
									str+='<div id="alertStatusChartId" style="height:300px;"></div>';
								str+='</div>';
							str+='</div>';
							
						str+='</div>';
							
					str+='</div>';
					str+='<div class="tab-pane active" id="callcenter">';
						str+='<div id="callcenterDetailsDivId"></div>';
					str+='</div>';
					str+='<div class="tab-pane active" id="print">';
						str+='<div id="printDetailsDivId"></div>';
					str+='</div>';
					str+='<div class="tab-pane active" id="electronic">';
						str+='<div id="electronicDetailsDivId"></div>';
					str+='</div>';
					str+='<div class="tab-pane active" id="social">';
						str+='<div id="socialDetailsDivId"></div>';
					str+='</div>';
					
					//tab close
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#jalavaniTabOverVewDivId").html(str);
	var mainArr=[];
	var monthNameArr=[];
	var statusNameArr=[];
	var dataArr=[];
	
	
	for(var i in result.subList2){
		monthNameArr.push(result.subList2[i].monthName)
		mainArr.push(result.subList2[i].percentage)
	}
	for(var i in result.list){
		statusNameArr.push(result.list[i].status);
		var tempArr = [];
		tempArr.push(result.list[i].statusCount);
		dataArr.push(tempArr);
		
	}
	$('#areasplineChartId').highcharts({
		colors:['#D9E8CE'],
	  chart: {
			type: 'areaspline'
		},
		title: {
			text: '',
		},
		legend: {
			enabled: false,
		},
		xAxis: {
			
			categories: monthNameArr,
			labels: {
				style: {
					color: '#333',
					fontSize:'14px',
					fontWeight:'bold',
				}
			},
			
		},
		yAxis: {
			min: 0,
			title: {
				text: '',
			},
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b> - ' +
					this.y+" %"
			}
		},
		credits: {
			enabled: false
		},
		plotOptions: {
				areaspline: {
				fillOpacity: 0.5,
				lineColor: '#25CAA1',
				 dataLabels: {
						enabled: true,
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'gray',
						formatter: function() {
							return (this.y)+"%";
						},
					},
			},
		series: {
				marker: {
					fillColor: '#FFFFFF',
					lineColor: '#25CAA1',
					lineWidth: 1,  // inherit from series
				}
			}
		},
		series: [{
			name: '',
			data: mainArr,
			
			
		}]
	});
	
	$('#alertStatusChartId').highcharts({
		colors:["#A27FC2","#0175F3","#3EC3FF","#049968","#F21A98","#FD6E07","#CF0001","#FE9900","#0C9514","#82CA9C","#C9AC82","#ababab","#FFA07A","#FFD07A"],
		 chart: {
			type: 'column',
		},
		title: {
			text: '',
		},
	  xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: statusNameArr,
			labels: {
				style: {
					color: '#333',
					fontSize:'10px',
					fontWeight:'bold',
				 }
			},
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
		},
		legend: {
		  enabled:false,
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b> - ' +
					this.y+""
			}
		},
		plotOptions: {
				column: {
					colorByPoint: true,
					pointWidth: 30,
					gridLineWidth: 15
					
				},
			},
		series:[{
				name: '',
				data: dataArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						return '<span>'+this.y+'</span>';
					}
				}
			}],
	});
}




function getJalavaniCategoryWiseDetailsInfo(searchType,blockCount){
	$("#"+searchType+"DetailsDivId").html(spinner);
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018",
		searchType:searchType//print or electronic or callcenter or social
		
	}
	$.ajax({                
	type:'POST',    
	url: 'getJalavaniCategoryWiseDetailsInfo',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
	}).done(function(result){
		if(result !=null){
			buildJalavaniCategoryWiseDetailsInfo(result,searchType,blockCount);
		}else{
			$("#"+searchType+"DetailsDivId").html("No Data Available");
		}
	});
}

function buildJalavaniCategoryWiseDetailsInfo(result,searchType,blockCount){
	var str='';
	if(searchType == "callcenter"){
		str+='<div class="row">';
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #447BB3;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							  str+='<img src="Assests/images/call_center_calls_icon.PNG" class="media-object" style="width:60px">';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h5 class="media-heading"><b>CALL CENTER CALLS</b></h5>';
								str+='<h3>'+blockCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';	
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #3C3B3B; background-color:#F6A323;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							 str+='<i class="fa fa-bell-o fa-4x"></i>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h5 class="media-heading"><b>CALL CENTER ALERTS</b></h5>';
								str+='<h3 class="m_top10">'+result.categoryCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';	
		str+='</div>';
	}else{
		str+='<div class="row">';
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #595959;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							if(searchType == "print"){
								str+='<img src="Assests/images/print_media_icon.PNG" class="media-object" style="width:50px">';
							}else{
								str+='<img src="Assests/images/electronic_media_icon.PNG" class="media-object" style="width:50px">';
							}	
							
							str+='</div>';
							str+='<div class="media-body">';
								if(searchType == "print"){
									str+='<h5 class="media-heading"><b>PRINT MEDIA NEWS</b></h5>';
								}else{
									str+='<h5 class="media-heading"><b>ELECTRONIC MEDIA NEWS</b></h5>';
								}
								
								str+='<h3>'+result.totalNewsCnt+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
					
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #67BD47;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							 str+='<i class="fa fa-smile-o fa-4x" style="color:#67BD47"></i>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h5 class="media-heading" style="color:#67BD47"><b>POSITIVE <br/>NEWS</b></h5>';
								str+='<h3 class="">'+result.posCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #EE4E5B;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							 str+='<i class="fa fa-frown-o fa-4x" style="color:#EE4E5B"></i>';
							str+='</div>';
							str+='<div class="media-body">';
								str+='<h5 class="media-heading " style="color:#EE4E5B"><b>NEGATIVE <br/>NEWS</b></h5>';
								str+='<h3 class="">'+result.negCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3">';
					str+='<div class="" style="border: 1px solid #3C3B3B; background-color:#F6A323;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							 str+='<i class="fa fa-bell-o fa-4x"></i>';
							str+='</div>';
							str+='<div class="media-body">';
							if(searchType == "print"){
								str+='<h5 class="media-heading"><b>PRINT MEDIA ALERTS</b></h5>';
							}else{
								str+='<h5 class="media-heading"><b>ELECTRONIC MEDIA ALERTS</b></h5>';
							}
								
								str+='<h3>'+result.categoryCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';
		str+='</div>';
		if(result.subList1 !=null && result.subList1.length>0){
			str+='<div class="bg_yash_color_10 m_top10">';
				str+='<h5 class="font_weight">IMPACT LEVEL</h5>';
				str+='<ul class="list-inline impactLevelCss m_top10">';
					for(var i in result.subList1){
						var totalCount=result.subList1[i].posCount+result.subList1[i].negCount
						str+='<li>';
							str+='<h6>'+result.subList1[i].name+' LEVEL</h6>';
								str+='<div class="row">';
									str+='<div class="col-sm-4 m_top5">';
										str+='<h3 class="m_top15" style="font-size: 20px;">'+totalCount+'</h3>';
									str+='</div>';
									str+='<div class="col-sm-8 m_top5">';
										str+='<div style="background-color:#fff;border:1px solid #ddd;padding:5px;">';
											str+='<h4><i class="fa fa-smile-o fa-1x" style="color:#67BD47;font-size: 24px;font-weight: bold;"></i>&nbsp;<span class="pull-right">'+result.subList1[i].posCount+'</span></h4>';
											str+='<hr class="m_bottom5"/>';	
											str+='<h4><i class="fa fa-frown-o fa-1x" style="color:#EE4E5B;font-size: 24px;font-weight: bold;"></i>&nbsp;<span class="pull-right">'+result.subList1[i].negCount+'</span></h4>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</li>';
					}
					
				str+='</ul>';
			str+='</div>';
		}
	}
	
	
	str+='<div class="bg_yash_color_10 m_top10">';
		str+='<h5 class="font_weight">ALERTS - MONTHLY OVERVIEW</h5>';
		str+='<div class="row">';
			str+='<div style="padding:15px;">';
				str+='<div id="areaspline'+searchType+'ChartId" style="height:300px;"></div>';
			str+='</div>';
		str+='</div>';
		var alertStatusTotalCount=0;
		for(var i in result.list){
			alertStatusTotalCount=alertStatusTotalCount+result.list[i].statusCount
		}
		
		str+='<div class="row">';
			str+='<div style="padding:15px;">';
				if(searchType == "callcenter"){
					str+='<div class="row">';
						str+='<div class="col-sm-8">';
							str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';
							str+='<div id="alertStatus'+searchType+'ChartId" style="height:300px;" class="m_top10"></div>';
						str+='</div>';
						str+='<div class="col-sm-4">';
							str+='<h5 class="font_weight">Jalavani Call Center IVR feedback</h5>';
							str+='<div id="callcenterIVRFeedBackId" style="height:300px;" class="m_top10"></div>';
						str+='</div>';
					str+='</div>';
					
				}else{
					str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';	
					str+='<div id="alertStatus'+searchType+'ChartId" style="height:300px;" class="m_top10"></div>';
				}
				
			str+='</div>';
		str+='</div>';
		
	str+='</div>';
	
	$("#"+searchType+"DetailsDivId").html(str);
	var mainArr=[];
	var monthNameArr=[];
	var statusNameArr=[];
	var dataArr=[];
	
	
	for(var i in result.subList2){
		monthNameArr.push(result.subList2[i].monthName)
		mainArr.push(result.subList2[i].percentage)
	}
	for(var i in result.list){
		statusNameArr.push(result.list[i].status);
		var tempArr = [];
		tempArr.push(result.list[i].statusCount);
		dataArr.push(tempArr);
		
	}
	var dataIVRArr=[];
	var AverageIVr=0;
	for(var i in result.subList1){
		AverageIVr=(AverageIVr+result.subList1[i].feedbackCount)/2;
		dataIVRArr.push(result.subList1[i].feedbackCount)
	}
	dataIVRArr.push(AverageIVr);
	$('#areaspline'+searchType+'ChartId').highcharts({
		colors:['#D9E8CE'],
	  chart: {
			type: 'areaspline'
		},
		title: {
			text: '',
		},
		legend: {
			enabled: false,
		},
		xAxis: {
			
			categories: monthNameArr,
			labels: {
				style: {
					color: '#333',
					fontSize:'14px',
					fontWeight:'bold',
				}
			},
			
		},
		yAxis: {
			min: 0,
			title: {
				text: '',
			},
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b> - ' +
					this.y+" %"
			}
		},
		credits: {
			enabled: false
		},
		plotOptions: {
				areaspline: {
				fillOpacity: 0.5,
				lineColor: '#25CAA1',
				 dataLabels: {
						enabled: true,
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'gray',
						formatter: function() {
							return (this.y)+"%";
						},
					},
			},
		series: {
				marker: {
					fillColor: '#FFFFFF',
					lineColor: '#25CAA1',
					lineWidth: 1,  // inherit from series
				}
			}
		},
		series: [{
			name: '',
			data: mainArr,
			
			
		}]
	});
	
	$('#alertStatus'+searchType+'ChartId').highcharts({
		colors:["#A27FC2","#0175F3","#3EC3FF","#049968","#F21A98","#FD6E07","#CF0001","#FE9900","#0C9514","#82CA9C","#C9AC82","#ababab","#FFA07A","#FFD07A"],
		 chart: {
			type: 'column',
		},
		title: {
			text: '',
		},
	  xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: statusNameArr,
			labels: {
				style: {
					color: '#333',
					fontSize:'10px',
					fontWeight:'bold',
				 }
			},
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
		},
		legend: {
		  enabled:false,
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b> - ' +
					this.y+""
			}
		},
		plotOptions: {
				column: {
					colorByPoint: true,
					pointWidth: 30,
					gridLineWidth: 15
					
				},
			},
		series:[{
				name: '',
				data: dataArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						return '<span>'+this.y+'</span>';
					}
				}
			}],
	});
	
	$('#callcenterIVRFeedBackId').highcharts({
		colors:["#0FBE08","#FF0909","#FFBA00"],
		 chart: {
			type: 'column',
		},
		title: {
			text: '',
		},
	  xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: ['SATISFIED','NOT SATISFIED','PAR SATISFIED'],
			labels: {
				useHTML:true,
				formatter: function() {
					return '<p style="font-size:9px;"><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
					
				},
				
			},
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
		},
		legend: {
		  enabled:false,
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b> - ' +
					this.y+""
			}
		},
		plotOptions: {
				column: {
					colorByPoint: true,
					pointWidth: 30,
					gridLineWidth: 15
					
				},
			},
		series:[{
				name: '',
				data: dataIVRArr,
				dataLabels: {
					enabled: true,
					color: '#fff',
					align: 'center',
					formatter: function() {
						return '<span>'+this.y+'</span>';
					}
				}
			}],
	});
}

function levelWiseJalavaniDetails(divId,changeType)
{
	var alertSourceId=$("#alertTypeId").val();
	var viewTypeId=$("#viewTypeId").val();
	var collapse='';
	
	for(var i in locationArr)
	{
		if(viewTypeId == "Alert"){
			if(alertSourceId == 0){
				collapse+='<div class="col-sm-12">';
			}else{
				collapse+='<div class="col-sm-6">';
			}
		}else{
			collapse+='<div class="col-sm-12">';
		}
		
		
		collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+locationArr[i]+'">';
				if(viewTypeId == "Alert"){
					if(alertSourceId == 0){
						if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}
					}else{
						collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}
				}else{
					if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}
				}
				
					
					collapse+='<h4 class="panel-title text-capital">'+locationArr[i]+' level overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(viewTypeId == "Alert"){
					if(alertSourceId == 0){
						if(i == 0)
						{
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}else{
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
						}
					}else{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}
				}else{
					if(i == 0)
					{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}else{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}
				}
				
				
				
					collapse+='<div class="panel-body">';
						collapse+='<div id="'+divId.replace(/\s+/g, '')+''+locationArr[i]+'"></div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		collapse+='</div>';
	}
	
	$("#jalavaniTableViewDetailsDivId").html(collapse);
	for(var i in locationArr){
		if(changeType == "onload"){
			getJalavanilocationAndStatusDetailsInfo(locationArr[i],0,'Alert');
		}else{
			getJalavanilocationAndStatusDetailsInfo(locationArr[i],$("#alertTypeId").val(),$("#viewTypeId").val());
		}
		
	}
}

$(document).on("click",".getResultsCls",function(){
	levelWiseJalavaniDetails('jalavani',"change");
});	
function getJalavanilocationAndStatusDetailsInfo(type,alertCategoryId,searchType){
	 var  fromDateStr = "01-01-2018";
	 var toDateStr="14-03-2018";
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getJalavanilocationAndStatusDetailsInfo/"+fromDateStr+"/"+toDateStr+"/"+searchType+"/"+type+"/"+alertCategoryId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getJalavanilocationAndStatusDetailsInfo/"+fromDateStr+"/"+toDateStr+"/"+searchType+"/"+type+"/"+alertCategoryId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildJalavanilocationOverview(result,type,searchType)
		}
		
	});	
}
 function buildJalavanilocationOverview(result,type,searchType){
	 var alertSourceId=$("#alertTypeId").val();
	 var viewTypeId=$("#viewTypeId").val();
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
		if(searchType =="Alert"){
			str+='<table class="table table_custom_jalavani" id="dataTable'+type+'">';
		}else{
			str+='<table class="table table_custom_jalavani_status" id="dataTable'+type+'">';
		}
		
			str+='<thead>';
				str+='<tr>';
				if(type =="district"){
					str+='<th style="background-color:#F3F3F3;">District</th>';	
				}else if(type =="constituency"){
					str+='<th style="background-color:#F3F3F3;">District</th>';	
					str+='<th style="background-color:#F3F3F3;">Constituency</th>';	
				}else if(type =="mandal"){
					str+='<th style="background-color:#F3F3F3;">District</th>';	
					str+='<th style="background-color:#F3F3F3;">Constituency</th>';	
					str+='<th style="background-color:#F3F3F3;">Mandal</th>';	
				}
				if(searchType == "Alert"){
					if(alertSourceId == 0){
						str+='<th class="total_alerts_color total_alerts">TOTAL <br/>Alerts</th>';
					}
					
				}else{
					str+='<th class="total_alerts_color total_alerts">Total</th>';
				}
				
				var globalStatusBgObj={"Call Center":"#FBB9E0","Print Media":"#BFBFBF","Electronic Media":"#FFC3A5","Social Media":"#A8BFFF"}
				var globalStatusColorObj={"Call Center":"#F648A9","Print Media":"#584A4A","Electronic Media":"#FF6300","Social Media":"#1C49FF"}
				for(var i in result[0].voList){
					if(searchType == "Alert"){
						str+='<th style="background-color:'+globalStatusBgObj[result[0].voList[i].status]+';color:'+globalStatusColorObj[result[0].voList[i].status]+'">'+result[0].voList[i].status+' <br/>Alerts</th>';	
					}else{
						str+='<th style="background-color:'+result[0].voList[i].color+';">'+result[0].voList[i].status+'</th>';	
					}
					
				}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						if(type =="district"){
							str+='<td class="" style="text-align:left !important;">'+result[i].districtName+'</td>';
						}else if(type =="constituency"){
							str+='<td class="" style="text-align:left !important;border-right:1px solid #ddd !important;">'+result[i].districtName+'</td>';
							str+='<td class="" style="text-align:left !important;">'+result[i].constiruenctName+'</td>';
						}else if(type =="mandal"){
							str+='<td class="" style="text-align:left !important;border-right:1px solid #ddd !important;">'+result[i].districtName+'</td>';
							str+='<td class="" style="text-align:left !important;">'+result[i].constiruenctName+'</td>';
							str+='<td class="" style="text-align:left !important;">'+result[i].mandalName+'</td>';
						}
						if(searchType == "Alert"){
							if(alertSourceId == 0){
								str+='<td class="total_alerts_color total_alerts getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="0" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="">'+result[i].count+'</td>';
							}else if(alertSourceId == 2){
								str+='<td class="total_Print_body total_Print_color getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="2" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="Print Media">'+result[i].count+'</td>';
							}else if(alertSourceId == 3){
								str+='<td class="total_Electronic total_Electronic_color getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="3" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="Electronic Media">'+result[i].count+'</td>';
							}else if(alertSourceId == 4){
								str+='<td class="total_CallCenter total_CallCenter_color getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="4" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="Call Center Media">'+result[i].count+'</td>';
							}else if(alertSourceId == 5){
								str+='<td class="total_SocialMedia total_Social_color getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="5" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="Social Media">'+result[i].count+'</td>';
							}
						}else{
							str+='<td class="total_alerts_color total_alerts getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="">'+result[i].count+'</td>';
						}
							for(var j in result[i].voList){
								if(searchType == "Alert"){
									if(alertSourceId ==0){
										if(result[i].voList[j].count !=null && result[i].voList[j].count>0){
											str+='<td class="getAmsPopUpCls" style="background-color:'+globalStatusBgObj[result[i].voList[j].status]+';color:'+globalStatusColorObj[result[i].voList[j].status]+'" attr_alertCount="'+result[i].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="'+result[i].voList[j].status+'">'+result[i].voList[j].count+' <small style="color:green;margin-left:15px;">'+result[i].voList[j].percentage+'&nbsp;%</small></td>';
										}else{
											str+='<td style="background-color:'+globalStatusBgObj[result[i].voList[j].status]+';color:'+globalStatusColorObj[result[i].voList[j].status]+'"> - </td>';
										}
									}
								}else{
									if(result[i].voList[j].status == "Wrongly Mapped Department"){
										if(result[i].voList[j].count !=null && result[i].voList[j].count>0){
											str+='<td class="getAmsPopUpCls" style="background-color:'+result[i].voList[j].color+';" attr_alertCount="'+result[i].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="'+result[i].voList[j].statusId+'" attr_statusName="'+result[i].voList[j].status+'">'+result[i].voList[j].count+'&nbsp;<small style="color:#fff;margin-left:15px;">'+result[i].voList[j].percentage+'&nbsp;%</small></td>';
										}else{
											str+='<td style="background-color:'+result[i].voList[j].color+';"> - </td>';
										}
										
									}else{
										if(result[i].voList[j].count !=null && result[i].voList[j].count>0){
											str+='<td class="getAmsPopUpCls" style="background-color:'+result[i].voList[j].color+';" attr_alertCount="'+result[i].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="'+result[i].voList[j].statusId+'" attr_statusName="'+result[i].voList[j].status+'">'+result[i].voList[j].count+'&nbsp;<small style="color:green;margin-left:15px;">'+result[i].voList[j].percentage+'&nbsp;%</small></td>';
										}else{
											str+='<td style="background-color:'+result[i].voList[j].color+';"> - </td>';
										}
										
									}
									
								}
								
							}
						
						
					str+='</tr>';
				}
				
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	
	$("#jalavani"+type).html(str);
	$("#dataTable"+type).dataTable({
		"iDisplayLength": 13,
		"aaSorting": [],
		"aLengthMenu": [[13, 15, 20,50, -1], [13, 15, 20,50, "All"]]
	});
}
$(document).on("click",".getAmsPopUpCls",function(){
	var alertCount = $(this).attr("attr_alertCount");
	var categoryId = $(this).attr("attr_categoryId");
	var location_id = $(this).attr("attr_location_id");
	var district_id = $(this).attr("attr_location_district_id");
	var constituency_id = $(this).attr("attr_location_constituency_id");
	var mandal_id = $(this).attr("attr_location_mandal_id");
	var statusid = $(this).attr("attr_statusid");
	var statusName = $(this).attr("attr_statusName");
	
	var locationId='';
	var locationValue='';
	if(location_id == "district"){
		locationId =5;
		locationValue = district_id;
	}else if(location_id == "constituency"){
		locationId =5;
		locationValue = constituency_id;
	}else if(location_id == "mandal"){
		locationId =8;
		locationValue = mandal_id;
	}
	$("#alertManagementPopupBody").html('')
	
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
	
	getJalavaniAlertSourceDetailsInformation(alertCount,categoryId,statusid,locationId,locationValue,statusName)
});

function getJalavaniAlertSourceDetailsInformation(alertCount,categoryId,statusid,locationId,locationValue,statusName){
	//district-5,mandal-8,status-id/CategotyID
	
	 var fromDateStr = "01-01-2018";
	 var toDateStr="14-03-2018";
	
	
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getJalavaniAlertSourceDetailsInformation/"+fromDateStr+"/"+toDateStr+"/"+locationId+"/"+locationValue+"/"+statusid+"/"+categoryId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getJalavaniAlertSourceDetailsInformation/"+fromDateStr+"/"+toDateStr+"/"+locationId+"/"+locationValue+"/"+statusid+"/"+categoryId
	}).then(function(result){
		 if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,alertCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
		
	});	
}

function buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount)
{
	
	var str='';
	var alertId = '';
	$("#modalHeadingTotal").html("Total "+statusName+' - '+statuscount);
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10" expand-main="false">';
			str+='<div class="classicView">';
				str+='<table style="background-color:#fff;" id="dataTable" class="table table-bordered ">';
					str+='<thead>';
						str+='<th></th>';
						str+='<th>Id</th>';
						str+='<th>Title</th>';
						str+='<th><span class="channel-name">Source</span></th>';
						str+='<th><span class="location-name">Location</span></th>';
						str+='<th><span class="channel-name">Status</span></th>';
						str+='<th><span class="channel-name">Ofcr Name</span></th>';//alertStatus
						//str+='<th><span class="channel-name">Ofcr Designation</span></th>';
						//str+='<th><span class="channel-name">Lag Days</span></th>';
						//str+='<th>Subtask <i class="fa fa-level-down"></i></th>';
						str+='<th></th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result)
					{
						
							str+='<tr>';
								str+='<td>';
									
								/* if(result[i].severtyColor != null)
								{
									//str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:'+result[i].subList[j].severtyColor+';margin-right:3px;"></i>';
									str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:#eee;margin-right:3px;"></i>';
								}else{
									str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:#eee;margin-right:3px;"></i>';
								} */
								str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:#eee;margin-right:3px;"></i>';	
								str+='</td>';
								str+='<td> ';
									if(result[i].alertId != null)
									{
										str+=''+result[i].alertId+'</td>';
									}else{
										str+='-</td>';
									}
									
								str+='<td>';
									if(result[i].title != null)
									{
										str+='<span class="alert-title" data-toggle="tooltip" data-placement="top" title="'+result[i].title+'">'+result[i].title+'</span>';
									}else{
										str+='<span class="alert-title" data-toggle="tooltip" data-placement="top" title="-">-</span>';
									}
								str+='</td>';
								str+='<td>';
									if(result[i].source != null)
									{
										str+='<span data-toggle="tooltip" class="channel-name" data-placement="top" title="'+result[i].source+'">'+result[i].source+'</span>';
									}else{
										str+='<span data-toggle="tooltip" class="channel-name" data-placement="top" title="-">-</span>';
									}
								str+='</td>';
								
								str+='<td>';
									if(result[i].location != null)
									{
										str+='<span data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].location+'">'+result[i].location+'</span>';
									}else{
										str+='<span data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].designationName != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].designationName+'">'+result[i].designationName+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].alertStatus != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].alertStatus+'">'+result[i].alertStatus+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>'; 
								/* str+='<td class="text-center">';
									if(result[i].subList[j].relatedTo != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].relatedTo+'">'+result[i].subList[j].relatedTo+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>'; */
								/* str+='<td class="text-center">';
									if(result[i].subList[j].interval != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].subList[j].interval+'">'+result[i].subList[j].interval+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>'; */
								/* str+='<td class="text-center">';
									if(result[i].subList[j].subTaskCount != null && result[i].subList[j].subTaskCount > 0)
									{
										str+='<span class="arrow-icon" style="margin:0px 4px" attr_alertId="'+result[i].subList[j].id+'" expand-icon="block1">';
											str+=''+result[i].subList[j].subTaskCount+'';
										str+='</span>';
									}else{
										str+='-';
									}
								str+='</td>'; */
								str+='<td>';
									if(result[i].alertId != null && result[i].alertId > 0)
									{
										str+='<span class="arrow-icon pull-right alertIdCls" attr_statusId="" attr_alertId="'+result[i].alertId+'" expand-icon="block1">';
											str+='<i class="glyphicon glyphicon-menu-right"></i>';
										str+='</span>';

									}else{
										str+='-';
									}
									
								str+='</td>';
							str+='</tr>';
						
					}
						
					str+='</tbody>';
				str+='</table>';
			
			str+='</div>';
		str+='</div>';
		str+='<div id="rightSideExpandView"></div>';
	str+='</div>';
	$("#alertManagementPopupBody").html(str);
	$("#dataTable").dataTable();
	$('[data-toggle="tooltip"]').tooltip();
	//getAlertData(alertId);
}
$(document).on("click","[expand-icon]",function(){
        var expandBlockName = $(this).attr("expand-icon");
		var alertId = $(this).attr("attr_alertId");
		//var statusId = $(this).attr("attr_statusId");
		$("[expand-icon]").closest("li").removeClass("active");
		$("[expand-icon]").removeClass("text-primary");
		$(this).addClass("text-primary");
		$(this).closest("li").addClass("active");
		 rightSideExpandView(alertId);
		//glStr = '';
		setTimeout(function(){
			$("[expanded-block="+expandBlockName+"]").show().css("transition"," ease-in, width 0.7s ease-in-out");
		},750);
		setTimeout(function(){
			$("#alertManagementPopup").scrollTop(0);
		},780);
		if($("[expand-main]").attr("expand-main") === 'false')
		{	
			$("[expand-main]").attr("expand-main","true");
			$("[expanded-channel]").attr("expanded-channel","true");
			$("[expand-main]").addClass("col-sm-4").removeClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
		}
	});
    $(document).on("click","[expanded-close]",function(){
		var expandBlockName = $(this).attr("expanded-close");
		if($("[expand-main]").attr("expand-main") === 'true')
		{
			$("[expand-main]").attr("expand-main","false");
		}else{
			$("[expand-main]").attr("expand-main","true");
		}
		$("[expanded-block="+expandBlockName+"]").hide();
		$("[expand-main]").removeClass("col-sm-4").addClass("col-sm-12").css("transition"," ease-in-out, width 0.7s ease-in-out");
	});
	
function rightSideExpandView(alertId)
{
	$("#rightSideExpandView").html(spinner);
    $(".assignedUser1").html('');
	var str='';
	str+='<div class="col-sm-8 pad_left0" expanded-block="block1" style="display: none;">';
		str+='<div class="panel-right">';
			str+='<div style="box-shadow:0px 0px 2px 2px rgba(0,0,0,0.2)">';
				str+='<i class="glyphicon glyphicon-remove pull-right"  expanded-close="block1"></i>';
				str+='<div class="panel panel-default">';
				
					str+='<div class="panel-heading" id="mainBlockStates">';
						str+='<div class="row">';
							str+='<div class="col-sm-4">';
								str+='<div id="assignedUser"></div>';
							str+='</div>';
							str+='<div class="col-sm-8 pull-right" style="">';
								str+='<ul class="list-icons list-inline pull-right" status-icon="block1">';
									
									/*  str+='<li data-toggle="tooltip" data-placement="top" title="Departments" id="departDivId" style="display:none;">';
										str+='<span class=""></span><span id="mainDeprtmntId" attr_alert_id="'+alertId+'"><i class="fa fa-empire" aria-hidden="true"></i></span>';
									str+='</li>'; */
									
									str+='<li status-icon-block="alertStatus" attr_alert_id="'+alertId+'" subAlertId=""  data-toggle="tooltip" data-placement="top" title="alert status" id="displayStatusId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon" id="statusIdColor"></span><span id="statusId">Pending</span>';
									str+='</li>';
									
									str+='<li data-toggle="tooltip" data-placement="top" title="Present Proposal Status" id="proposalId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon"></span><span id="presntPrposalstatusId"></span>';
									str+='</li>';
								
									str+='<li data-toggle="tooltip" data-placement="top" title="Present Rejoinder Status" id="rejoinderId" style="display:none;" > ';
										str+='<span class="status-icon arrow-icon"></span><span id="presntRejoinderstatusId"></span>';
									str+='</li>';
									str+='<li id="historyId" style="display:none;" status-icon-block="alertHistory" attr_alert_id="'+alertId+'" subAlertId="" >';
										str+='<i class="fa fa-road" data-toggle="tooltip" data-placement="top" title="Alert History"></i>';
									str+='</li>';
								
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
					
					str+='<div id="main_alert_block">';
						str+='<div class="panel-body" >';
							str+='<p><i class="fa fa-fire"></i> Impact Level : <span id="impactLevel"></span>';
								str+='<span class="text-danger pull-right"><i class="glyphicon glyphicon-cog"></i> Priority:<span id="priorityBodyId"> HIGH</span></span>';
							str+='</p>';
							str+='<div id="callerDetailsDIv"></div>';
							str+='<div id="statusDtlsDiv"></div>';
							str+='<div id="alertDetails"></div>';
							str+='<div id="articleAttachment"></div>';
							str+='<div id="alertCategory"></div>';
							str+='<div id="rejoinderAttachments"></div>';
							str+='<div id="alertSubtask"></div>';
							//str+='<div id="alertComments"></div>';
							str+='<div id="alertGeneralComments"></div>';
							str+='<div status-body="task" class="m_top20"></div>';
							str+='<div status-body="subTask" class="m_top20"></div>';
						str+='</div>';
						
					str+='</div>';
				
				
				str+='</div>';
				
				
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	//google.setOnLoadCallback(onLoad);
	$(".chosenSelect").chosen({width:'100%'});
	
	/* $('.modal-date2').data('daterangepicker');
	
	$(function() {
		var start = moment();
		
		function cb(start) {
			$('.modal-date2').html(start.format('DD/MM/YYYY'));
		}
       
		$('.modal-date2').daterangepicker({
			startDate: start,
			singleDatePicker:true,
			locale:{
				format:"DD/MM/YYYY"
			}
			
		}, cb);		getGovtAllDepartmentDetails
	}); */
	
	/* initializeFile();
	dateRangePicker(alertId);
	assignedOfficersDetailsForAlert(alertId);//yes
	departmentsByAlert(alertId);//yes
	getAlertData(alertId);//yes
	getStatusCompletionInfo(alertId);//Change Parameter DO
	getGovtAllDepartmentDetails();//Change Parameter DO
	buildAssignUIAttributes(alertId);//No need
	getCommentsForAlert(alertId);//GIVEN DO
	alertDeptmentExistInLogin(alertId); //GIVEN DO Parameter*/
	
	getAssignedOfficersDetails(alertId);
	getDepartmentsByAlert(alertId);
	getAlertsDataByAlertId(alertId);
	
	
}
function getAlertsDataByAlertId(alertId){
	$("#alertDetails").html(spinner);
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getAlertsDataByAlertId/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getAlertsDataByAlertId/"+alertId
	}).then(function(result){
		$("#alertDetails").html('');
		getAlertCategoryByAlert(alertId);
		//getInvolvedMembersDetilas(alertId);
		getSubTaskInfoForAlert(alertId);
		//getCommentsForAlert(alertId); //GIVEN DO
		getDocumentsForAlerts(alertId);
		if(result != null && result.length > 0){
			buildAlertDataNew(result)
			if(result[0].categoryId == 2)
			{
				getGroupedArticlesInfo(result[0].alertCategoryTypeId)
			}
			if(result[0].categoryId == 5){
				buildSocialMediaImage(result[0].documentList);
			}
		}else{
			$("#alertDetails").html("NO DATA AVAILABLE...");
		}
		
	});	
}
function getGroupedArticlesInfo(articleId)
{
	$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
	}).then(function(result){
		//$("#alertDetails").append(str);
	});
}

var globalProposalStatus;
  var globalRejoinderStatus;
function buildAlertDataNew(result)
{
	var str='';
	var str1='';
	$("#statusId").html(result[0].status);
	if($("#displayStatusId #statusId").html() == 'Proposal'){
		$("#proposalId").show();
		globalProposalStatus = result[0].committeeName;
		$("#presntPrposalstatusId").html(result[0].committeeName);
	}
	if($("#displayStatusId #statusId").html() == 'Rejoinder'){
		$("#rejoinderId").show();
		globalRejoinderStatus = result[0].comment;
		$("#presntRejoinderstatusId").html(result[0].comment);
		//globalRejoinderStatus = result[0].comment;
	}
	$("#impactLevel").html(result[0].regionScope);
	if(result[0].severity != null)
	{
		$("#priorityBodyId").html(result[0].severity);
	}
	$("#statusIdColor").css("background-color",result[0].statusColor);
	if(result[0].dueDate != null && result[0].dueDate.length>0)
	{
		$('.modal-date').data('daterangepicker').setStartDate(result[0].dueDate);
		$('.modal-date').data('daterangepicker').setEndDate(result[0].dueDate);
		if(result[0].dueDate != null && result[0].dueDate.length>0){
			$('.modal-date').html(result[0].dueDate);
			$('.modal-date1').html(result[0].dueDate);
		}
	}else{
			$('#displayDueDate2').hide();
			$('#displayDueDate1').hide();
		}
	
	//priorityRadioCls
	if(result[0].severityId != null && result[0].severityId > 0){
		$("input[name=alert-status-change-list][value='"+result[0].severityId+"']").prop("checked",true);
	}
	
	str+='<div class="row m_top20">';
		for(var i in result)
		{
			str+='<div class="col-sm-1 text-center body-icons">';
				str+='<i class="fa fa-check fa-2x"></i>';
			str+='</div>';
			str+='<div class="col-sm-11">';
				str+='<h3>'+result[i].title+'</h3>';
				str+='<p class="m_top10">'+result[i].desc+'</p>';
				str+='<p class="m_top10"><small> <i class="fa fa-map-marker"></i> '+result[i].locationVO.state+'(S),'+result[i].locationVO.districtName+'(D),'+result[i].locationVO.constituencyName+'(C),'+result[i].locationVO.tehsilName+'(M)'+result[i].locationVO.wardName+','+result[i].locationVO.villageName+'(P),'+result[i].locationVO.hamletName+'(H)</small></p>';
				str+='<p class="m_top10"><small> <i class="fa fa-calendar"></i> Created : '+result[i].date+'</small></p>';
			str+='</div>';
		}
	str+='</div>';
	/*str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-paperclip fa-2x"></i>';
		str+='</div>';*/
		str+='<div class="col-sm-11">';
			for(var i in result.documentList)
			{
				str+='<img class="articleDetailsCls img-responsive m_top20" src="../Reports/'+result.documentList[i]+'" style="width: 150px; height: 150px;cursor:pointer"/>';
			}
		str+='</div>';
	str+='</div>';
	str1+='<div class="row m_top20">';
	
		if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			if(result[i].imageUrl != null){
				str1+='<div class="col-sm-4">';
					str1+='<h4 class="text-muted text-capital">article attachment</h4>';
					str1+='<img class="articleDetailsCls img-responsive m_top20" attr_articleId='+result[i].alertCategoryTypeId+' src="../NewsReaderImages/'+result[i].imageUrl+'" style="width: 150px; height: 150px;cursor:pointer"/>';
				str1+='</div>';
				str1+='<div class="col-sm-7" id="existingDocsDivId"></div>';
			}else{
				str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
			}
		}else{
			str1+='<div class="col-sm-1 text-center body-icons">';
				str1+='<i class="fa fa-paperclip fa-2x"></i>';
			str1+='</div>';
			str1+='<div class="col-sm-11" id="existingDocsDivId"></div>';
		}
		
	str1+='</div>';
	
	var str2='';
	
	str2+='<div class="row m_top20">';
	
		if(result[i].rejinderDocList !=null && result[i].rejinderDocList.length>0){
			str2+='<div class="col-sm-1 text-center body-icons">';
				str2+='<i class="fa fa-reply-all fa-2x"></i>';
			str2+='</div>';
			str2+='<div class="col-sm-11">';
				str2+='<div class="bg_EE">';
					for(var j in result[i].rejinderDocList)
					{
						str2+='<div class="media">';
							str2+='<div class="media-left">';
							if(j == 0){
								str2+='<i class="fa fa-commenting-o fa-2x" aria-hidden="true" style="color:orange"></i>';
							}else if(j == 1 && result[i].rejinderDocList[j].subList1 != null && result[i].rejinderDocList[j].subList1.length > 0 ){
								str2+='<i class="fa fa-commenting fa-2x" aria-hidden="true" style="color:red"></i>';
							}
							str2+='</div>';
							str2+='<div class="media-body">';
								if(j == 0 && result[i].rejinderDocList[j].subList1 != null){
									str2+='<p><span style="color:orange">'+result[i].rejinderDocList[j].name+'</span></p>';
								}else if(j == 1 && result[i].rejinderDocList[j].subList1 != null && result[i].rejinderDocList[j].subList1.length > 0 ){
									str2+='<p><span style="color:red">'+result[i].rejinderDocList[j].name+'</span></p>';
								}
								
								for(var k in result[i].rejinderDocList[j].subList1)
								{
									 var nameArr = result[i].rejinderDocList[j].subList1[k].name.split('/');
									 var name = nameArr[3];
									str2+='<p><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;&nbsp;<a href="http://www.mytdp.com/images/"'+result[i].rejinderDocList[j].subList1[k].name+' style="cursor:pointer;">'+name+'</a>&nbsp;&nbsp;(<i class="fa fa-calendar" aria-hidden="true"></i> &nbsp;'+result[i].rejinderDocList[j].subList1[k].date1+')</p>';
								}
								
							str2+='</div>';
						str2+='</div>';
					}
					
				str2+='</div>';
			str2+='</div>';
		}
		
	str2+='</div>';
	
	$("#alertDetails").html(str);
	$("#articleAttachment").html(str1);
	$("#rejoinderAttachments").html(str2);
	
}
function buildSocialMediaImage(result){
	$("#existingDocsDivId").html('');
	var str='';
	
	if(result !=null && result.length>0){
		str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<span style="background-color: gray; display: inline-block; border-radius: 5px; height: 8px; width: 8px;"></span><li class="" style="margin-top:25px;" attr_doc_id="'+i+'"  attr_path="'+result[i]+'">';
					str+='<img src="../images/'+result[i]+'" style="width: 100px; height: 100px;cursor:pointer" />';
					//str+='<a target="_blank" href="http://www.mytdp.com/images/'+result[i].name+'" style="cursor:pointer;">'+result[i].name+'</a>';
				str+='</li>&nbsp;&nbsp;';
			}
		str+='</ul>';
		}
		setTimeout(function(){
			$("#existingDocsDivId").html(str);	
		},1000);
		
}

function getAlertCategoryByAlert(alertId){
	$("#categoryId").html('');
	$("#alertCategory").html(spinner);
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getAlertCategoryByAlert/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getAlertCategoryByAlert/"+alertId
	}).then(function(result){
		$("#alertCategory").html('');
		if(result != null && result.length > 0)
		{
			var str='';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-1 text-center body-icons">';
					str+='<i class="fa fa-tags fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h4 class="text-muted text-capital">category</h4>';
					str+='<p class="m_top20"><span class="label label-default label-category">'+result+'</span></p>';
				str+='</div>';
			str+='</div>';
			
			$("#alertCategory").append(str);
		}
	});	
}
function getSubTaskInfoForAlert(alertId){
	$("#alertSubtask").html(spinner);
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getSubTaskInfoForAlert/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getSubTaskInfoForAlert/"+alertId
	}).then(function(result){
		$("#alertSubtask").html('');
		if(result != null && result.length > 0)
		{
			buildSubTaskInfoForAlert(result,alertId);
		}
	});	
}

function buildSubTaskInfoForAlert(result,alertId)
{
	var str='';
	
		
		for(var i in result)
		{
			if(result[i].attachementsList != null && result[i].attachementsList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
					
					str+='<div class="col-sm-11">';
						str+='<h4 class="text-muted text-capital"> My Sub Tasks : </h4>';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].attachementsList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;margin-left: 5px" attr_sub_alert_Id="'+result[i].attachementsList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].attachementsList[k].title+' ';
											
											str+='</p>';
											str+='<small class="pull-right">DEPT : <span style="color: #60bbfd;">'+result[i].attachementsList[k].deptName+'</span> DESIGNATION : <span style="color: #60bbfd;">'+result[i].attachementsList[k].designation+'</span> Location : <span style="color: #60bbfd;">'+result[i].attachementsList[k].location+'</span> </small>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<span class="icon-name icon-primary" style="background-color: '+result[i].attachementsList[k].color+'" title="'+result[i].attachementsList[k].status+'"></span>';
											/* str+='<ul class="list-icons list-inline">';
												str+='<li> <span class="status-icon arrow-icon" id="statusIdColor" style="background-color: '+result[i].attachementsList[k].color+'" title="'+result[i].attachementsList[k].status+'"></span> </li>';
											str+='</ul>'; */
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				str+='</div>';
				
			}
			if(result[i].commentList != null && result[i].commentList.length>0){
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons">';
						str+='<i class="fa fa-level-down fa-2x"></i>';
					str+='</div>';
				str+='<div class="col-sm-11 ">';
					str+='<h4 class="text-muted text-capital"> Sub Tasks involved : </h4>';
					str+='</div>';
					str+='<div class="col-sm-11 col-sm-offset-1">';
						str+='<ul class="assign-subtask-list m_top20">';
						for(var k in result[i].commentList){
							str+='<li class="assigned subTaskCls " style="cursor:pointer;" attr_sub_alert_Id="'+result[i].commentList[k].alertId+'" attr_alert_id="'+alertId+'">';
									str+='<div class="row">';
										str+='<div class="col-sm-1">';
											str+='<i class="glyphicon glyphicon-ok"></i>';
										str+='</div>';
										str+='<div class="col-sm-10" >';
											str+='<p>'+result[i].commentList[k].title+'';
											
											str+='</p>';
											str+='<small class="pull-right">DEPT : <span style="color: #60bbfd;">'+result[i].commentList[k].deptName+'</span> DESIGNATION : <span style="color: #60bbfd;">'+result[i].commentList[k].designation+'</span> Location : <span style="color: #60bbfd;">'+result[i].commentList[k].location+'</span> </small>';
										str+='</div>';
										str+='<div class="col-sm-1">';
											str+='<span class="icon-name icon-primary" id="statusIdColor" style="background-color: '+result[i].commentList[k].color+'"  title="'+result[i].commentList[k].status+'"></span>';
											/* str+='<ul class="list-icons list-inline">';
												str+='<li> <span class="status-icon arrow-icon" id="statusIdColor" style="background-color: '+result[i].commentList[k].color+'"  title="'+result[i].commentList[k].status+'"></span> </li>';
											str+='</ul>'; */
											//str+='<i class="glyphicon glyphicon-menu-right pull-right"></i>';
										//	str+='<span class="icon-name icon-primary"></span>';
											//str+='<span class="label label-default">...</span>';
										str+='</div>';
									str+='</div>';
							str+='</li>';
						}
						str+='</ul>';
					str+='</div>';
				
			}
		}	
	str+='</div>';	
/*
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-comments-o fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital"> Sub Tasks Comments </h4>';
			for(var i in result)
			{
				str+='<div class="media">';
				
					str+='<div class="media-body">';
					if(result[i].commentList != null && result[i].commentList.length>0){
						for(var k in result[i].commentList){
							if(result[i].commentList[k].comment != null && result[i].commentList[k].comment.length > 0)
							{
								str+='<p class="m_top5">'+result[i].commentList[k].comment+'</p>';
							}
							
							if(result[i].commentList[k].date != null && result[i].commentList[k].date.length > 0)
							{
								str+='<p class="m_top5"><i class="glyphicon glyphicon-calendar"></i> '+result[i].commentList[k].date+'</p>';
							}
						}
					}
						
					str+='</div>';
				str+='</div>';
			}
		str+='</div>';
	str+='</div>';
	*/ 
	$("#alertSubtask").html(str);
}
function getDocumentsForAlerts(alertId){
	$("#existingDocsDivId").html("");
	$("#existingDocsDivId").html(spinner);
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getDocumentsForAlerts/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getDocumentsForAlerts/"+alertId
	}).then(function(result){
		$("#existingDocsDivId").html('');
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="text-muted text-capital">alert attachment</h4>';
			str+='<ul class="list-inline imageShowOpen">';
			for(var i in result){
				str+='<span style="background-color: gray; display: inline-block; border-radius: 5px; height: 8px; width: 8px;"></span><li class="" style="margin-top:25px;" attr_doc_id="'+result[i].id+'"  attr_path="'+result[i].name+'" id="imageAttachmentOpen'+result[i].id+'" >';
					str+='<img src="../images/'+result[i].name+'" style="width: 100px; height: 100px;cursor:pointer" />';
					//str+='<a target="_blank" href="http://www.mytdp.com/images/'+result[i].name+'" style="cursor:pointer;">'+result[i].name+'</a>';
				str+='</li>&nbsp;&nbsp;';
				
			}
			str+='</ul>';
			$("#existingDocsDivId").html(str);
		}
	});	
}
var globalSubTaskDeptId=0;
function getDepartmentsByAlert(alertId){
	
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getDepartmentsByAlert/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getDepartmentsByAlert/"+alertId
	}).then(function(result){
		var str='';
		str+='<p class="m_top20">';
			for(var i in result)
			{
				str+='<span class="label label-default label-category">'+result[i].name+'</span>';
				globalSubTaskDeptId=result[i].id;
			}
		str+='</p>';
		$("#alertDetails").append(str);
		
	});	
}	
function getAssignedOfficersDetails(alertId){
	
	$.ajax({
		//url: wurl+"/PartyAnalyst/WebService/getAssignedOfficersDetails/"+alertId
		url: "http://192.168.11.173:8085/PartyAnalyst/WebService/getAssignedOfficersDetails/"+alertId
	}).then(function(result){
		if(result != null && result.length > 0)
		{
			buildAssignedOfficersDetailsForAlert(result);
		}else{
			assignUser(alertId);
		}
		
	});	
}
function buildAssignedOfficersDetailsForAlert(result)
{
	var str='';
	var splitNameArr = result[0].name.split(" ");
	var value = "";
	if(splitNameArr != null && splitNameArr.length>1)
		value = splitNameArr[1];
	else
		value = splitNameArr[0];  
	
	str+='<div class="media">';
		/* str+='<div class="media-left">';
			str+='<span class="icon-name icon-primary">'+result[0].name.substring(0,1)+''+value.substring(0,1)+'</span>';
		str+='</div>'; */
		str+='<div class="media-body">';
			/* str+='<p> - '+result[0].designation+'<br> (<i class="glyphicon glyphicon-phone"></i> '+result[0].mobileNo+')</p>';
			str+='<p>Location : '+result[0].source+'</p>';
			
			str+='<p>'+result[0].name+' - '+result[0].department+'</p>'; */
			str+='<p> ASSIGN TO: <i class="fa fa-level-down "></i></p> ';
			str+='<p>'+result[0].designation+' <br> <i class="glyphicon glyphicon-phone"></i> : '+result[0].mobileNo+'</p>';
			str+='<p>Location :  '+result[0].source+'</p>';			
			str+='<p>Dept : '+result[0].department+'</p>'; 
			
			str+='<p></p>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".assign-user").hide();
}

function assignUser(alertId)
{
	var str='';
	str+='<div class="assign-user">';
		str+='<ul class="list-icons list-inline">';
			str+='<li id="displayAssignIconId" attr_alertId="'+alertId+'" style="display:none;">';
				str+='<i class="glyphicon glyphicon-user"></i>Click To Assignee  ';
			str+='</li>';
		str+='</ul>';
		str+='<div class="assign-user-body" style="display:none">';
			str+='<form id="alertAssign" name="alertAssignForm">';
				str+='<div class="arrow_box_top">';
					str+='<div>';
						str+='<div class="row">';  
							str+='<div class="col-sm-12">';
								str+='<div id="assignErrorDivId" class="text-danger text-capitalize" style="margin-bottom:10px;"></div>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="assignDepartmentId" name="alertAssigningVO.mainDepartmentId">';
									str+='<option value="0">Select Department</option>';
									//str+='<option value="49">RWS</option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Sub Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
								str+='<select class="chosenSelect" id="departmentsId" name="alertAssigningVO.departmentId">	';
									str+='<option value="0">Select Sub Department</option>';
									//str+='<option value="49">RWS</option>';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
								str+='<select  class="chosenSelect" id="locationLevelSelectId" name="alertAssigningVO.levelId">	';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
							str+='<div id="parentLevelDivId"> </div>';
							
							str+='<div class="col-sm-6">';
								str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
								str+='<select name="alertAssigningVO.designationId" id="designationsId" class="chosenSelect">';
									str+='<option></option>	';
								str+='</select>';
							str+='</div>';
							str+='<div class="col-sm-6">';
								str+='<label>Officer Name<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgOffcrId"></span></label>';
								str+='<select name="alertAssigningVO.govtOfficerId" id="officerNamesId" class="chosenSelect">';
									str+='<option></option>';
								str+='</select>';
							str+='</div>';
						str+='</div>';
						str+='<input type="hidden" id="hiddenAlertId" value="'+alertId+'" name="alertAssigningVO.alertId"/>';
					str+='</div>';
				str+='</div>';
			str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
				str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">assign</button>';
				str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
				str+='<span class="text-success" id="assignSuccess"></span>';
			str+='</div>';
			str+='</form>';
		str+='</div>';
	str+='</div>';
	$("#assignedUser").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	$('#displayAssignIconId').show();
	
	//getDepartmentDetailsOfAlert(alertId);
}

getArticlesMonthlyOverviewInfoBySearchType("alerts",2,"print");//print media Graph onchange
function getArticlesMonthlyOverviewInfoBySearchType(searchType,alertcategoryId,newsType){
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018",
		searchType:searchType,//news or alerts
		alertCategoryId:alertcategoryId,//Print-2,Electronic-3//0-All
		newsType:newsType //""-alert,news-Print,Electronic-electronic
	}
	$.ajax({                
	type:'POST',    
	url: 'getArticlesMonthlyOverviewInfoBySearchType',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
	}).done(function(result){
	});
}

