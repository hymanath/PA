var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStatusObj={"SATISFIED":"#0FBE08","NOT SATISFIED":"#FF0909","PAR SATISFIED":"#FFBA00"}
$(".chosen-select").chosen();
getJalavaniDashBoardOverview();
var locationArr=['district','constituency','mandal'];
levelWiseJalavaniDetails('jalavani',"onload");

$(document).on("change","#sourceTypeId",function(){
	var sourceType = $("#sourceTypeId").val();
	if(sourceType =='All'){
		$("#viewTypeId").html('');
		$("#viewTypeId").append('<option value="0">Select View Type</option>');
		$("#viewTypeId").append('<option value="Alert">Alert</option>');
		$("#viewTypeId").append('<option value="Status">Status</option>');
		$("#viewTypeId").trigger("chosen:updated");
	}else{
		$("#viewTypeId").html('');
		$("#viewTypeId").append('<option value="0">Select View Type</option>');
		$("#viewTypeId").append('<option value="Alert">Alert</option>');
		$("#viewTypeId").trigger("chosen:updated");
	}
	if(sourceType !='All'){
		$("#alertTypeId").html('');
		$("#alertTypeId").append('<option value="0">Select Alert Type</option>');
		$("#alertTypeId").append('<option value="print">Print Media</option>');
		$("#alertTypeId").append('<option value="electronic">Electronic Media</option>');
		$("#alertTypeId").append('<option value="callcenter">Call Center</option>');
		$("#alertTypeId").trigger("chosen:updated");
	}else{
		$("#alertTypeId").html('');
		$("#alertTypeId").append('<option value="All">All</option>');
		$("#alertTypeId").append('<option value="print">Print Media</option>');
		$("#alertTypeId").append('<option value="electronic">Electronic Media</option>');
		$("#alertTypeId").append('<option value="callcenter">Call Center</option>');
		$("#alertTypeId").trigger("chosen:updated");
	}
});	
$(document).on("change","#alertTypeId",function(){
	var sourceType = $("#alertTypeId").val();
	if(sourceType =='All'){
		$("#viewTypeId").html('');
		$("#viewTypeId").append('<option value="0">Select View Type</option>');
		$("#viewTypeId").append('<option value="Alert">Alert</option>');
		$("#viewTypeId").append('<option value="Status">Status</option>');
		$("#viewTypeId").trigger("chosen:updated");
	}else{
		$("#viewTypeId").html('');
		$("#viewTypeId").append('<option value="0">Select View Type</option>');
		$("#viewTypeId").append('<option value="Alert">Alert</option>');
		$("#viewTypeId").trigger("chosen:updated");
	}
	
});		
$(document).on("click",".getResultsCls",function(){
	levelWiseJalavaniDetails('jalavani',"change");
});	
$(document).on("click",".tab_bordered li",function(){
	$(this).closest("ul").find("li").removeClass("active_li");
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active_li");
	var blockType = $(this).attr("attr_type");
	var blockCount = $(this).attr("attr_block_count");
	if($(this).hasClass('active_li')){
		if(blockType == "All"){
			getJalavaniDashBoardOverview();
			$("#viewTypeId").html('');
			$("#viewTypeId").append('<option value="0">Select View Type</option>');
			$("#viewTypeId").append('<option value="Alert" selected>Alert</option>');
			$("#viewTypeId").append('<option value="Status">Status</option>');
			$("#viewTypeId").trigger("chosen:updated");
			
			$("#sourceTypeId").val('All');
			$("#sourceTypeId").trigger("chosen:updated");
			
			$("#alertTypeId").html('');
			$("#alertTypeId").append('<option value="All">All</option>');
			$("#alertTypeId").append('<option value="print">Print Media</option>');
			$("#alertTypeId").append('<option value="electronic">Electronic Media</option>');
			$("#alertTypeId").append('<option value="callcenter">Call Center</option>');
			$("#alertTypeId").trigger("chosen:updated");
			levelWiseJalavaniDetails('jalavani',"onload");
			
		}else{
			$("#viewTypeId").html('');
			$("#viewTypeId").append('<option value="Alert">Alert</option>');
			$("#viewTypeId").trigger("chosen:updated");
			getJalavaniCategoryWiseDetailsInfo(blockType,blockCount);
			if(blockType == "callcenter"){
				$("#sourceTypeId").val('alert');
				$("#sourceTypeId").trigger("chosen:updated");
				
				$("#alertTypeId").html('');
				$("#alertTypeId").append('<option value="0">Select Alert Type</option>');
				$("#alertTypeId").append('<option value="print">Print Media</option>');
				$("#alertTypeId").append('<option value="electronic">Electronic Media</option>');
				$("#alertTypeId").append('<option value="callcenter" selected>Call Center</option>');
				$("#alertTypeId").trigger("chosen:updated");
				
				levelWiseJalavaniDetails('jalavani',"callCenterTabClick");
			}else if(blockType == "print"){
				$("#sourceTypeId").val('news');
				$("#sourceTypeId").trigger("chosen:updated");
				
				$("#alertTypeId").html('');
				$("#alertTypeId").append('<option value="0">Select Alert Type</option>');
				$("#alertTypeId").append('<option value="print" selected>Print Media</option>');
				$("#alertTypeId").append('<option value="electronic">Electronic Media</option>');
				$("#alertTypeId").append('<option value="callcenter">Call Center</option>');
				$("#alertTypeId").trigger("chosen:updated");
				
				levelWiseJalavaniDetails('jalavani',"printTabClick");
			}else if(blockType == "electronic"){
				$("#sourceTypeId").val('news');
				$("#sourceTypeId").trigger("chosen:updated");
				
				$("#alertTypeId").html('');
				$("#alertTypeId").append('<option value="0">Select Alert Type</option>');
				$("#alertTypeId").append('<option value="print">Print Media</option>');
				$("#alertTypeId").append('<option value="electronic" selected>Electronic Media</option>');
				$("#alertTypeId").append('<option value="callcenter">Call Center</option>');
				$("#alertTypeId").trigger("chosen:updated");
				
				levelWiseJalavaniDetails('jalavani',"electronicTabClick");
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
							str+='<div class="col-sm-3">';
								str+='<div class="brdR_3 pad_10" style="border: 1px solid #4C9DD6;">';
									str+='<h5 class="font_weight" style="color:#4C9DD6">TOTAL <br/>ALERTS</h5>';
									str+='<h4 class="m_top10">'+totalAlertCount+'</h4>';
								str+='</div>';
							str+='</div>';	
							var colorObj={'Print Media':'#575858','Electronic Media':'#F26532','Call Center':'#E952A0'}
							for(var i in result.subList1){
								str+='<div class="col-sm-3">';
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
		searchType:searchType//print or electronic or callcenter
		
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
function getJalavanilocationOverview(locationType){//All Table Build
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018",
		"searchType":"",
		"type":locationType,//constituency,mandal
		"alertCategoryId":0

	}
	$.ajax({                
	type:'POST',    
	url: 'getJalavanilocationOverview',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildJalavanilocationOverview(result,locationType);
		}
	});
}
function levelWiseJalavaniDetails(divId,changeType)
{
	var collapse='';
	collapse+='<div class="col-sm-12">';
	for(var i in locationArr)
	{
		collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+locationArr[i]+'">';
					if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}
					collapse+='<h4 class="panel-title text-capital">'+locationArr[i]+' level overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
				}
				
					collapse+='<div class="panel-body">';
						collapse+='<div id="'+divId.replace(/\s+/g, '')+''+locationArr[i]+'"></div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	}
	collapse+='</div>';
	$("#jalavaniTableViewDetailsDivId").html(collapse);
	var sourceType = $("#sourceTypeId").val();
	var alertType = $("#alertTypeId").val();
	var viewType = $("#viewTypeId").val();
	
	
	for(var i in locationArr){
		if(changeType == "onload"){
			getJalavanilocationOverview(locationArr[i]);
		}else if(changeType == "change"){
			if(sourceType =='All' && alertType == "All" && viewType=="Status"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],0,viewType,sourceType);
			}else if(sourceType =='news' && alertType == "print" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],2,"PrintMedia",sourceType);
			}else if(sourceType =='news' && alertType == "electronic" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],3,"ElectronicMedia",sourceType);
			}else if(sourceType =='news' && alertType == "callcenter" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],4,"",sourceType);
			}else if(sourceType =='alert' && alertType == "callcenter" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],4,"",sourceType);
			}else if(sourceType =='alert' && alertType == "print" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],2,"PrintMedia",sourceType);
			}else if(sourceType =='alert' && alertType == "electronic" && viewType=="Alert"){
				getJalavanilocationAndStatusDetailsInfo(viewType,locationArr[i],3,"ElectronicMedia",sourceType);
			}
		}else if(changeType == "callCenterTabClick"){
			getJalavanilocationAndStatusDetailsInfo('Alert',locationArr[i],4,"",sourceType);
		}else if(changeType == "printTabClick"){
			getJalavanilocationAndStatusDetailsInfo('Alert',locationArr[i],2,"PrintMedia",sourceType);
		}else if(changeType == "electronicTabClick"){
			getJalavanilocationAndStatusDetailsInfo('Alert',locationArr[i],3,"ElectronicMedia",sourceType);
		}
	}
}
function buildJalavanilocationOverview(result,locationType){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
		str+='<table class="table table_custom_jalavani" id="">';
			str+='<thead>';
				str+='<tr>';
				str+='<th class="">Location</th>';	
				str+='<th class="total_alerts_color total_alerts">TOTAL <br/>ALERTS</th>';
				str+='<th class="total_CallCenter_color total_CallCenter">CALL CENTER <br/>ALERTS</th>';
				str+='<th class="total_Print_color total_Print">PRINT MEDIA <br/>ALERTS</th>';
				str+='<th class="total_Electronic_color total_Electronic">ELECTRONIC MEDIA <br/>ALERTS</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					str+='<td class="">dddddd</td>';
					str+='<td class="total_alerts_color total_alerts">20</td>';
					str+='<td class="total_CallCenter_color total_CallCenter">20</td>';
					str+='<td class="total_Print_color total_Print">20</td>';
					str+='<td class="total_Electronic_color total_Electronic">20</td>';
				str+='</tr>';
				str+='<tr>';
					str+='<td class="">dddddd</td>';
					str+='<td class="total_alerts_color total_alerts">20</td>';
					str+='<td class="total_CallCenter_color total_CallCenter">20</td>';
					str+='<td class="total_Print_color total_Print">20</td>';
					str+='<td class="total_Electronic_color total_Electronic">20</td>';
				str+='</tr>';
				str+='<tr>';
					str+='<td class="">dddddd</td>';
					str+='<td class="total_alerts_color total_alerts">20</td>';
					str+='<td class="total_CallCenter_color total_CallCenter">20</td>';
					str+='<td class="total_Print_color total_Print">20</td>';
					str+='<td class="total_Electronic_color total_Electronic">20</td>';
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	
	$("#jalavani"+locationType).html(str);
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


function getJalavanilocationAndStatusDetailsInfo(searchType,locationType,alertCategoryId,newsType,sourceType){
	/* dates
getSearchType- Alert or Status
type -district or constituency or mandal */
	var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018",
		searchType:searchType,//Alert or Status
		type:locationType,//district or constituency or mandal
		alertCategoryId:alertCategoryId,//print or electronic or callcenter // print -2,electronic-3/call cneter -4
		newsType:newsType//call center=""/print -PrintMedia/electrronic -ElectronicMedia
		
	}
	$.ajax({                
	type:'POST',    
	url: 'getJalavanilocationAndStatusDetailsInfo',
	dataType: 'json',
	data : JSON.stringify(json),
	beforeSend :   function(xhr){
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
	}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildJalavanilocationNewsTypeOverview(result,locationType,newsType,sourceType);
		}
	});
}
function buildJalavanilocationNewsTypeOverview(result,locationType,newsType,sourceType){
	var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
			str+='<div class="table-responsive">';
			str+='<table class="table table_custom_jalavani" id="">';
				str+='<thead>';
				if(newsType == "Status"){
					str+='<tr>';
						str+='<th class="" style="background-color:#F3F3F3;">Location</th>';	
						str+='<th class="total_alerts_color total_alerts">TOTAL ALERTS</th>';
						for(var i in result[0].subList1){
							str+='<th class="" style="background-color:'+result[0].subList1[i].color+'">'+result[0].subList1[i].status+'</th>';
						}
					str+='</tr>';
				}else if(newsType == "PrintMedia"){
					str+='<tr>';
						str+='<th class="" rowspan="2" style="background-color:#F3F3F3;">Location</th>';	
						if(sourceType == "news"){
							str+='<th class="total_alerts_color total_alerts" rowspan="2">TOTAL NEWS</th>';
							str+='<th class="total_Print_color total_Print" colspan="2">PRINT MEDIA NEWS</th>';
						}else{
							str+='<th class="total_alerts_color total_alerts" rowspan="2">TOTAL ALERTS</th>';
							str+='<th class="total_Print_color total_Print" colspan="2">PRINT MEDIA ALERTS</th>';
						}
					str+='</tr>';
					str+='<tr>';
						if(sourceType == "news"){
							str+='<th class="total_Print_color total_Print" style="border-right:1px solid #ddd !important;">+Ve</th>';
							str+='<th class="total_Print_color total_Print">-Ve</th>';
						}else{
							str+='<th class="total_Print_color total_Print" style="border-right:1px solid #ddd !important;">+Ve</th>';
							str+='<th class="total_Print_color total_Print">-Ve</th>';
						}
					str+='</tr>';
				}else if(newsType == "ElectronicMedia"){
						str+='<tr>';
							str+='<th class="" rowspan="2" style="background-color:#F3F3F3;">Location</th>';	
							if(sourceType == "news"){
								str+='<th class="total_alerts_color total_alerts" rowspan="2">TOTAL NEWS</th>';
								str+='<th class="total_Electronic_color total_Electronic" colspan="2">ELECTRONIC MEDIA NEWS</th>';
							}else{
								str+='<th class="total_alerts_color total_alerts" rowspan="2">TOTAL <br/>ALERTS</th>';
								str+='<th class="total_Electronic_color total_Electronic" colspan="2">ELECTRONIC MEDIA ALERTS</th>';
							}
						
						str+='</tr>';
						str+='<tr>';
						if(sourceType == "news"){
							str+='<th class="total_Electronic_color total_Electronic" style="border-right:1px solid #ddd !important;">+Ve</th>';
							str+='<th class="total_Electronic_color total_Electronic">-Ve</th>';
						}else{
							str+='<th class="total_Electronic_color total_Electronic" style="border-right:1px solid #ddd !important;">+Ve</th>';
							str+='<th class="total_Electronic_color total_Electronic">-Ve</th>';
						}
					str+='</tr>';
				}else if(newsType == ""){
						str+='<tr>';
							str+='<th class="" style="background-color:#F3F3F3;">Location</th>';	
							str+='<th class="total_CallCenter_color total_CallCenter">CALL CENTER ALERTS</th>';
						str+='</tr>'
				}
					
				str+='</thead>';
				str+='<tbody>';
				if(newsType == "Status"){
					var totalStatusCount=0;
					for(var i in result){
						str+='<tr>';
							str+='<td class="">'+result[i].locationName+'</td>';
							str+='<td class="">'+totalStatusCount+'</td>';
							for(var j in result[i].subList1){
								totalStatusCount = totalStatusCount+result[i].subList1[j].alertCnt;
								str+='<td class="" style="background-color:'+result[i].subList1[j].color+'">'+result[i].subList1[j].alertCnt+'</td>';
							}
						str+='</tr>';
					}
					
				}else if(newsType == "PrintMedia"){
					for(var i in result){
						str+='<tr>';
							str+='<td class="">'+result[i].locationName+'</td>';
							str+='<td class="total_alerts_color total_alerts">'+result[i].count+' <small style="color:green;margin-left:15px;">'+result[i].percentage+' %</small></td>';
							for(var j in result[i].subList1){
								str+='<td class="total_Print_body" style="border-right:1px solid #BFBFBF !important;border-bottom:1px solid #BFBFBF !important;">'+result[i].subList1[j].posCount+' <small style="color:green;margin-left:15px;">'+result[i].subList1[j].posPerc+' %</small></td>';
								str+='<td class="total_Print_body" style="border-bottom:1px solid #BFBFBF !important;">'+result[i].subList1[j].negCount+' <small style="color:green;margin-left:15px;">'+result[i].subList1[j].negPerc+' %</small></td>';
							}
						str+='</tr>';
					}
				}else if(newsType == "ElectronicMedia"){
					for(var i in result){
						str+='<tr>';
							str+='<td class="">'+result[i].locationName+'</td>';
							str+='<td class="total_alerts_color total_alerts">'+result[i].count+' <small style="color:green;margin-left:15px;">'+result[i].percentage+' %</small></td>';
							for(var j in result[i].subList1){
								str+='<td class="total_Electronic" style="">'+result[i].subList1[j].posCount+' <small style="color:green;margin-left:15px;">'+result[i].subList1[j].posPerc+' %</small></td>';
								str+='<td class="total_Electronic" style="">'+result[i].subList1[j].negCount+' <small style="color:green;margin-left:15px;">'+result[i].subList1[j].negPerc+' %</small></td>';
							}
						str+='</tr>';
					}
				}else if(newsType == ""){
					for(var i in result){
						str+='<tr>';
							str+='<td class="">'+result[i].locationName+'</td>';
							str+='<td class="total_CallCenter">'+result[i].count+' <small style="color:green;margin-left:15px;">'+result[i].percentage+' %</small></td>';
						str+='</tr>';
					}
				}
					
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		
		$("#jalavani"+locationType).html(str);
}