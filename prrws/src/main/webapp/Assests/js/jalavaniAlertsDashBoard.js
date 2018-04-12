var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStatusObj={"SATISFIED":"#0FBE08","NOT SATISFIED":"#FF0909","PAR SATISFIED":"#FFBA00"}
var currentFromDate=moment().format("DD-MM-YYYY");
var currentToDate=moment().format("DD-MM-YYYY");
$(".chosen-select").chosen();
 var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

//var wurl="http://mytdp.com"
var locationArr=['state','district','constituency','mandal'];
$(".chosen-select").chosen();
//responsiveTabs();
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
	$(".menuCls-table2").hide();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
	$(".menuCls-table2").hide();
});
$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'Today' : [moment(), moment()],
		   'Yesterday': [moment().subtract(1, 'day'), moment().subtract(1, 'day')],
		   'This Month': [moment().startOf('month'),moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'This Year': [moment().startOf('Year'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'year').startOf('year'), moment().subtract(1, 'year').endOf('year')],
		   //'OverAll':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10,'years').format("DD-MM-YYYY")]
		   'OverAll':['01-01-2015', moment()]
		}
	});
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('Today');
	}
	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		if(picker.chosenLabel == 'Today')
		{
			$("#dateRangePicker").val('Today');
		}
		$("#alertTypeId").val(0)
		$("#alertTypeId").trigger("chosen:updated")
		onloadCalls("change");
	});

setTimeout(function(){
	onloadCalls("onload");
},1500);	
	
function onloadCalls(type){
	if(type == "onload"){
		$("#departmentId").html('');
		if(globalDepartmentId == 0){
			$("#departmentId").append('<option value="0">All</option>')
			$("#departmentId").append('<option value="19">IT E& C DEPARTMENT</option>')
			$("#departmentId").append('<option value="20">PANCHAYAT RAJ</option>')
			$("#departmentId").append('<option value="48">RURAL DEVELOPMENT DEPARTMENT</option>')
			$("#departmentId").append('<option value="49">RURAL WATER SUPPLY</option>')
		}else if(globalDepartmentId == 19){
			$("#departmentId").append('<option value="19">IT E& C DEPARTMENT</option>')
		}else if(globalDepartmentId == 20){
			$("#departmentId").append('<option value="20">PANCHAYAT RAJ</option>')
		}else if(globalDepartmentId == 48){
			$("#departmentId").append('<option value="48">RURAL DEVELOPMENT DEPARTMENT</option>')
		}else if(globalDepartmentId == 49){
			$("#departmentId").append('<option value="49">RURAL WATER SUPPLY</option>')
		}
		$("#departmentId").trigger("chosen:updated")
	}
	getJalavaniDashBoardOverview();
	levelWiseJalavaniDetails('jalavani',"onload");
}


$(document).on("click",".tab_bordered li",function(){
	$(this).closest("ul").find("li").removeClass("active_li");
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active_li");
	var blockType = $(this).attr("attr_type");
	var blockCount = $(this).attr("attr_block_count");
	if($(this).hasClass('active_li')){
		if(blockType == "0"){
			/* $("#viewTypeId").val('Alert');
			$("#viewTypeId").trigger("chosen:updated"); */
			getJalavaniDashBoardOverview();
			levelWiseJalavaniDetails('jalavani',"onload");
		}else{
			getJalavaniCategoryWiseDetailsInfo(blockType,blockCount);
			/* $("#viewTypeId").val('Alert');
			$("#viewTypeId").trigger("chosen:updated"); */
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
			}else if(blockType == "All"){
				$("#alertTypeId").val(0);
				$("#alertTypeId").trigger("chosen:updated");
				levelWiseJalavaniDetails('jalavani',"onload");
			}
		}
	}
	
});

function getJalavaniDashBoardOverview(){
	$("#jalavaniTabOverVewDivId").html(spinner);
	var json={
		fromDateStr:currentFromDate,
		toDateStr:currentToDate,
		deptId:globalDepartmentId
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
	//var allSourceCount=result.categoryCount+result.printCount+result.electCount;
	var allSourceCount=0;
	var callcenterCount=0;
	var printMediaCount=0;
	var electronicMediaCount=0;
	var socialMedia=0;
	for(var i in result.subList1){
		allSourceCount = allSourceCount+result.subList1[i].alertCnt;
		if(result.subList1[i].name == "Print Media"){
			printMediaCount  = callcenterCount+result.subList1[i].alertCnt;
		}else if(result.subList1[i].name == "Electronic Media"){
			electronicMediaCount  = callcenterCount+result.subList1[i].alertCnt;
		}else if(result.subList1[i].name == "Call Center"){
			callcenterCount  = callcenterCount+result.subList1[i].alertCnt;
		}else if(result.subList1[i].name == "Social Media"){
			socialMedia  = socialMedia+result.subList1[i].alertCnt;
		}
	}
	 str+='<div class="">';
		str+='<div class="col-sm-3">';
			/*str+='<select class="form-control" role="tabListMobile">';
				str+='<option tab_id="all_Sources_Id">ALL SOURCES</option>';
				str+='<option tab_id="callcenter">CALL CENTER</option>';
				str+='<option tab_id="print">PRINT MEDIA</option>';
				str+='<option tab_id="electronic">ELECTRONIC MEDIA</option>';
				str+='<option tab_id="social">SOCIAL MEDIA</option>';
				str+='</select>';*/
				
			str+='<ul class="nav nav-tabs tab_bordered" role="tablist">';
				str+='<li role="presentation" class="active_li" attr_type="All">';
					str+='<a href="#all_Sources_Id" data-toggle="tab" class="">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									 str+='<img src="Assests/images/icon_all.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5 font_weight">ALL&nbsp;SOURCES</h5>';
										str+='<h4 class="color_black">'+allSourceCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';	
					str+='</a>';
				str+='</li>';
				if(globalDepartmentId == 49 || globalDepartmentId == 0){
				str+='<li role="presentation" class="" attr_type="callcenter" attr_block_count="'+callcenterCount+'">';
					str+='<a href="#callcenter" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/call_center_icon.PNG" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5 font_weight">CALL&nbsp;CENTER</h5>';
										str+='<h4 class="color_black">'+callcenterCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				}
				str+='<li role="presentation" class="" attr_type="print" attr_block_count="'+printMediaCount+'">';
					str+='<a href="#print" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/print_media_alert_icon.png" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5 font_weight">PRINT&nbsp;MEDIA</h5>';
										str+='<h4 class="color_black">'+printMediaCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="electronic" attr_block_count="'+electronicMediaCount+'">';
					str+='<a href="#electronic" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/electronic_media_alert_icon.png" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5 font_weight">ELECTRONIC MEDIA</h5>';
										str+='<h4 class="color_black">'+electronicMediaCount+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<i class="fa fa-angle-right color_black f_30"></i>';
							str+='</div>';
						str+='</div>';		
					str+='</a>';
				str+='</li>';
				str+='<li role="presentation" class="" attr_type="social" attr_block_count="'+socialMedia+'">';
					str+='<a href="#social" data-toggle="tab">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
									  str+='<img src="Assests/images/Twitter-Facebook.jpg" class="media-object" style="width:45px">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="media-heading color_black m_top5"><b>SOCIAL MEDIA</b></h5>';
										str+='<h4 class="color_black">'+socialMedia+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
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
								//if(globalDepartmentId == 49){}
								if(result.subList1[i].name == "Call Center"){
									if(globalDepartmentId == 49 || globalDepartmentId == 0){
										if(result.subList1.length == 4){
											str+='<div class="col-sm-2">';
										}else{
											str+='<div class="col-sm-3">';
										}
										
											str+='<div class="brdR_3 pad_10" style="border: 1px solid '+colorObj[result.subList1[i].name]+';">';
												str+='<h5 class="font_weight text-capital" style="color:'+colorObj[result.subList1[i].name]+'">'+result.subList1[i].name+' <br/>Alerts</h5>';
												str+='<h4 class="m_top10">'+result.subList1[i].alertCnt+'</h4>';
											str+='</div>';	
										str+='</div>';
									}
									
								}else{
									if(result.subList1.length == 4){
										if(result.subList1[i].name == "Electronic Media"){
											str+='<div class="col-sm-3">';
										}else if(result.subList1[i].name == "Print Media"){
											str+='<div class="col-sm-2">';
										}else{
											str+='<div class="col-sm-3">';
										}
									}else{
										str+='<div class="col-sm-3">';
									}
									
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
								
							}
						str+='</div>';
						str+='<div class="bg_yash_color_10 m_top10">';
							str+='<h5 class="font_weight">ALERTS - MONTHLY OVERVIEW</h5>';
							str+='<div class="row">';
								str+='<div style="padding:15px;">';
									str+='<div id="areasplineChartId" class="monthGraphCss"></div>';
									//str+='<div class="imagecls"></div>';
								str+='</div>';
							str+='</div>';
							var alertStatusTotalCount=0;
							for(var i in result.list){
								alertStatusTotalCount=alertStatusTotalCount+result.list[i].statusCount
							}
							str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';
							str+='<div class="row">';
								str+='<div style="padding:15px;">';
									str+='<div id="alertStatusChartId" class="monthGraphCss"></div>';
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
	//responsiveTabs();
	var mainArr=[];
	var monthNameArr=[];
	var statusNameArr=[];
	var dataArr=[];
	
	
	for(var i in result.subList2){
		//if(result.subList2[i].percentage !=null && result.subList2[i].percentage>0){
			if(result.subList2[i].monthType == "monthWise"){
				var monthAndYearSpilt=result.subList2[i].monthName.split('-');
				var monthNameObj={'1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUY','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'};
				var changedMonthYear = ""+monthNameObj[monthAndYearSpilt[0]]+"-"+monthAndYearSpilt[1];
				monthNameArr.push(changedMonthYear);
			}else if(result.subList2[i].monthType == "dayWise"){
				var changedMonthYear = result.subList2[i].monthName;
				monthNameArr.push(changedMonthYear);
			}
			mainArr.push({y:result.subList2[i].percentage,"extra":result.subList2[i].monthCount})
		//}
		/* else{
			$('#areasplineChartId').html("No Data Available");
			$('#areasplineChartId').removeClass('monthGraphCss').addClass('height_10')
		} */
		
	}
	for(var i in result.list){
		//if(result.list[i].statusCount !=null && result.list[i].statusCount>0){
			if(result.list[i].statusId !=5 && result.list[i].statusId !=14){
				statusNameArr.push(result.list[i].status);
				dataArr.push({"y":result.list[i].statusCount,"extra":result.list[i].statusPerc});
			}	
		//}
		/* else{
			$('#alertStatusChartId').html("No Data Available");
			$('#alertStatusChartId').removeClass('monthGraphCss').addClass('height_10')
		} */
		
	}
	if(result.subList2 !=null && result.subList2.length>0){
		$('#areaspline'+'ChartId').removeClass("height_10").addClass('monthGraphCss')
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
						fontSize:'10px',
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
					 formatter: function() {
						return  (this.x)+"<br/>"+(this.point.extra)+" - "+(this.y)+"%";
					},
					//headerFormat: '<b>{series.name}</b><br/>',
					//pointFormat: '{point.y}'
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
	
	}else{
		//$('#areasplineChartId').html("No Data Available");
		//$('#areasplineChartId').removeClass('monthGraphCss').addClass('height_10')
		$('#areaspline'+'ChartId').removeClass("monthGraphCss")
		$('#areaspline'+'ChartId').html('<img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;">')
	}
	
	if(result.list !=null && result.list.length>0){
		$('#alertStatus'+'ChartId').removeClass("height_10").addClass('monthGraphCss')
		$('#alertStatusChartId').highcharts({
			colors:["#EF5656","#FFB2B2","#FFB2B2","#4AAD50","#4AAD50","#4AAD50","#4AAD50","#A8BFFF","#A8BFFF","#A8BFFF","#A8BFFF"],
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
						return  (this.x)+"<br/>"+(this.y)+" - "+(this.point.extra)+"%";
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
	}else{
		//$('#alertStatusChartId').html("No Data Available");
		//$('#alertStatusChartId').removeClass('monthGraphCss').addClass('height_10')
		$('#alertStatus'+'ChartId').removeClass("monthGraphCss")
		$('#alertStatus'+'ChartId').html('<div><img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;"></div>')
	}
	
}



function getJalavaniCategoryWiseDetailsInfo(searchType,blockCount){
	$("#"+searchType+"DetailsDivId").html(spinner);
	var json={
		fromDateStr:currentFromDate,
		toDateStr:currentToDate,
		searchType:searchType,//print or electronic or callcenter or social
		deptId:globalDepartmentId
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
			/* str+='<div class="col-sm-3">';
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
			str+='</div>';	 */
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
		if(searchType == "print" || searchType == "electronic"){
			str+='<div class="col-sm-3">';
				str+='<div class="" style="border: 1px solid #595959;">';
					str+='<div class="panel-heading">';
						str+='<div class="media">';
							str+='<div class="media-left">';
							if(searchType == "print"){
								str+='<img src="Assests/images/print_media_icon.PNG" class="media-object" style="width:50px">';
							}else if(searchType == "electronic"){
								str+='<img src="Assests/images/electronic_media_icon.PNG" class="media-object" style="width:50px">';
							}/* else{
								str+='<img src="Assests/images/social_media_icon.PNG" class="media-object" style="width:45px">';
							}  */	
							
							str+='</div>';
							str+='<div class="media-body">';
								if(searchType == "print"){
									str+='<h5 class="media-heading"><b>PRINT MEDIA NEWS</b></h5>';
								}else if(searchType == "electronic"){
									str+='<h5 class="media-heading"><b>ELECTRONIC MEDIA NEWS</b></h5>';
								}/* else{
									str+='<h5 class="media-heading"><b>SOCIAL NEWS</b></h5>';
								} */
								
								str+='<h3>'+result.totalNewsCnt+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
					
				str+='</div>';
			str+='</div>';
		}
			if(searchType == "print" || searchType == "electronic"){
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
			}
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
							}else if(searchType == "electronic"){
								str+='<h5 class="media-heading"><b>ELECTRONIC MEDIA ALERTS</b></h5>';
							}else{
								str+='<h5 class="media-heading"><b>SOCIAL MEDIA ALERTS</b></h5>';
							}
								
								str+='<h3>'+result.categoryCount+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';	
				str+='</div>';
			str+='</div>';
		str+='</div>';
		/* if(result.subList1 !=null && result.subList1.length>0){
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
		} */
	}
	
	
	str+='<div class="bg_yash_color_10 m_top10">';
		str+='<h5 class="font_weight">ALERTS - MONTHLY OVERVIEW</h5>';
		str+='<div class="row">';
			str+='<div style="padding:15px;">';
				str+='<div id="areaspline'+searchType+'ChartId" class="monthGraphCss"></div>';
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
						str+='<div class="col-sm-12">';
							str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';
							str+='<div id="alertStatus'+searchType+'ChartId"  class="m_top10 monthGraphCss"></div>';
						str+='</div>';
						/* str+='<div class="col-sm-4">';
							str+='<h5 class="font_weight">Jalavani Call Center IVR feedback</h5>';
							str+='<div id="callcenterIVRFeedBackId" class="m_top10 monthGraphCss"></div>';
						str+='</div>'; */
					str+='</div>';
					
					
					
				}else{
					str+='<h5 class="font_weight">ALERTS STATUS - '+alertStatusTotalCount+'</h5>';	
					str+='<div id="alertStatus'+searchType+'ChartId" class="m_top10 monthGraphCss"></div>';
				}
				
			str+='</div>';
		str+='</div>';
		
		if(searchType == "callcenter"){
			//str+='<h5 class="font_weight text-capital">Call Center Feedback</h5>';	
			str+='<div id="ivrStatusBaseFeedBackDivId" style="padding:15px;background-color:#fff;" class="m_top10">';
				str+='<div id="ivrStatusFeedBackDivId"></div>';
			str+='</div>';
		}
	str+='</div>';
	
	$("#"+searchType+"DetailsDivId").html(str);
	getJalavaniFeedBackDetailsInfo();
	//getJalavaniAlertForClosedAndReopenDetails();
	var mainArr=[];
	var monthNameArr=[];
	var statusNameArr=[];
	var dataArr=[];
	
	for(var i in result.subList2){
		if(result.subList2[i].monthType == "monthWise"){
			var monthAndYearSpilt=result.subList2[i].monthName.split('-');
			var monthNameObj={'1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUY','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'};
			var changedMonthYear = ""+monthNameObj[monthAndYearSpilt[0]]+"-"+monthAndYearSpilt[1];
			monthNameArr.push(changedMonthYear);
		}else if(result.subList2[i].monthType == "dayWise"){
			var changedMonthYear = result.subList2[i].monthName;
			monthNameArr.push(changedMonthYear);
		}
		mainArr.push({y:result.subList2[i].percentage,"extra":result.subList2[i].monthCount})
	}
	
	
	for(var i in result.list){
		if(result.list[i].statusId !=5 && result.list[i].statusId !=14){
			statusNameArr.push(result.list[i].status);
			
			dataArr.push({"y":result.list[i].statusCount,"extra":result.list[i].statusPerc});
		}
	}
	var dataIVRArr=[];
	var AverageIVr=0;
	for(var i in result.subList1){
		AverageIVr=(AverageIVr+result.subList1[i].feedbackCount)/2;
		dataIVRArr.push(result.subList1[i].feedbackCount)
	}
	dataIVRArr.push(AverageIVr);
	if(result.subList2 !=null && result.subList2.length>0){
		$('#areaspline'+searchType+'ChartId').removeClass("height_10").addClass('monthGraphCss')
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
						fontSize:'10px',
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
				 formatter: function() {
					return  (this.x)+"<br/>"+(this.point.extra)+" - "+(this.y)+"%";
				},
				//headerFormat: '<b>{series.name}</b><br/>',
				//pointFormat: '{point.y}'
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
	}else{
		$('#areaspline'+searchType+'ChartId').removeClass("monthGraphCss")
		$('#areaspline'+searchType+'ChartId').html('<img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;">')
	}
	if(result.list !=null && result.list.length>0){
		$('#alertStatus'+searchType+'ChartId').removeClass("height_10").addClass('monthGraphCss')
		$('#alertStatus'+searchType+'ChartId').highcharts({
			colors:["#EF5656","#FFB2B2","#FFB2B2","#4AAD50","#4AAD50","#4AAD50","#4AAD50","#A8BFFF","#A8BFFF","#A8BFFF","#A8BFFF"],
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
					return  (this.x)+"<br/>"+(this.y)+" - "+(this.point.extra)+"%";
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
	}else{
		$('#alertStatus'+searchType+'ChartId').removeClass("monthGraphCss")
		$('#alertStatus'+searchType+'ChartId').html('<div><img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;"></div>')
		
	}
	if(result.subList1 !=null && result.subList1.length>0){
		$('#callcenterIVRFeedBackId').removeClass("height_10").addClass('monthGraphCss')
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
	}else{
		$('#callcenterIVRFeedBackId').html("No Data Available")
		$('#callcenterIVRFeedBackId').removeClass("monthGraphCss").addClass('height_10')
	}
}

function levelWiseJalavaniDetails(divId,changeType)
{
	var alertSourceId=$("#alertTypeId").val();
	var viewTypeId=$("#viewTypeId").val();
	var collapse='';
	
	for(var i in locationArr)
	{
		/* if(viewTypeId == "Alert"){
			if(alertSourceId == 0){
				collapse+='<div class="col-sm-12">';
			}else{
				collapse+='<div class="col-sm-6">';
			}
		}else{
			collapse+='<div class="col-sm-12">';
		} */
		
		collapse+='<div class="col-sm-12">';
		collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" role="tablist" aria-multiselectable="true">';
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+locationArr[i]+'">';
				if(i == 0)
					{
						collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+locationArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+locationArr[i]+'">';
					}
				/* if(viewTypeId == "Alert"){
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
				} */
				
					
					collapse+='<h4 class="panel-title text-capital">'+locationArr[i]+' level overview</h4>';
						
					collapse+='</a>';
				collapse+='</div>';
				/* if(viewTypeId == "Alert"){
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
				} */
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
		collapse+='</div>';
	}
	
	$("#jalavaniTableViewDetailsDivId").html(collapse);
	for(var i in locationArr){
		if(changeType == "onload"){
			getJalavanilocationAndStatusDetailsInfo(locationArr[i],0,'Status');
		}else{
			getJalavanilocationAndStatusDetailsInfo(locationArr[i],$("#alertTypeId").val(),'Status');
		}
		
	}
}

$(document).on("click",".getResultsCls",function(){
	levelWiseJalavaniDetails('jalavani',"change");
});	
function getJalavanilocationAndStatusDetailsInfo(type,alertCategoryId,searchType){
	$("#jalavani"+type).html(spinner);
	 
	$.ajax({
		url: wurl+"/WebService/getJalavanilocationAndStatusDetailsInfo/"+currentFromDate+"/"+currentToDate+"/"+searchType+"/"+type+"/"+alertCategoryId+"/"+globalDepartmentId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavanilocationAndStatusDetailsInfo/"+currentFromDate+"/"+currentToDate+"/"+searchType+"/"+type+"/"+alertCategoryId+"/"+globalDepartmentId
	}).then(function(result){
		if(result !=null && result.length>0){
			buildJalavanilocationOverview(result,type,searchType)
		}else{
			$("#jalavani"+type).html('<img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;">');
		}
		
	});	
}
 function buildJalavanilocationOverview(result,type,searchType){
	  var alertSourceId=$("#alertTypeId").val();
	 var str='';
	 
	
	 str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<div class="table-responsive">';
				str+='<table class="table table_custom_jalavani_status" id="dataTable'+type+'" style="width:100%">';
					str+='<thead>';
						str+='<tr>';
						if(type =="state"){
							str+='<th style="background-color:#F3F3F3;" rowspan="2">State</th>';	
						}else if(type =="district"){
							str+='<th style="background-color:#F3F3F3;" rowspan="2">District</th>';	
						}else if(type =="constituency"){
							str+='<th style="background-color:#F3F3F3;" rowspan="2">District</th>';	
							str+='<th style="background-color:#F3F3F3;" rowspan="2">Constituency</th>';	
						}else if(type =="mandal"){
							str+='<th style="background-color:#F3F3F3;" rowspan="2">District</th>';	
							str+='<th style="background-color:#F3F3F3;" rowspan="2">Constituency</th>';	
							str+='<th style="background-color:#F3F3F3;" rowspan="2">Mandal</th>';	
						}
						str+='<th class="" style="background-color:#c5e6f9;" rowspan="2">Total</th>';
						
						
						var globalStatusBgObj={"NOT STARTED":"#ef5656","INITIAL STAGE":"#ffb2b2","Finished":"#4aad50","MOVED TO OTHER CATEGORY":"#A8BFFF"}
						var globalStatusColorObj={"NOT STARTED":"#ef5656","INITIAL STAGE":"#ffb2b2","Finished":"#4aad50","MOVED TO OTHER CATEGORY":"#1C49FF"}
						var statuslength=0;
						
							
						for(var i in result[0].voList){
							statuslength = (result[0].voList[i].voList.length)+1;
							if(result[0].voList[i].title == "NOT STARTED"){
								str+='<th colspan="'+statuslength+'" style="background-color:'+globalStatusBgObj[result[0].voList[i].title]+';">NOT&nbsp;STARTED</th>';	
							}else{
								str+='<th colspan="'+statuslength+'" style="background-color:'+globalStatusBgObj[result[0].voList[i].title]+';">'+result[0].voList[i].title+'</th>';	
							}
							
						}
						
						str+='</tr>';
						str+='<tr>';
							for(var i in result[0].voList){
								for(var j in result[0].voList[i].voList){
									str+='<th style="background-color:'+globalStatusBgObj[result[0].voList[i].title]+';">'+result[0].voList[i].voList[j].status+'</th>';
										
								}
								str+='<th style="background-color:'+globalStatusBgObj[result[0].voList[i].title]+';">%</th>';	
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
						if(result[i].count != null && result[i].count > 0){
							str+='<tr>';
								
								if(type =="district" || type =="state"){
									str+='<td class="" style="text-align:left !important;text-transform: uppercase;">'+result[i].districtName+'</td>';
								}else if(type =="constituency"){
									str+='<td class="" style="text-align:left !important;border-right:1px solid #ddd !important;text-transform: uppercase;">'+result[i].districtName+'</td>';
									str+='<td class="" style="text-align:left !important;text-transform: uppercase;">'+result[i].constiruenctName+'</td>';
								}else if(type =="mandal"){
									str+='<td class="" style="text-align:left !important;border-right:1px solid #ddd !important;text-transform: uppercase;">'+result[i].districtName+'</td>';
									str+='<td class="" style="text-align:left !important;text-transform: uppercase;">'+result[i].constiruenctName+'</td>';
									str+='<td class="" style="text-align:left !important;text-transform: uppercase;">'+result[i].mandalName+'</td>';
								}
								
								str+='<td class="" style="background-color:#c5e6f9;"><span class="getAmsPopUpCls" attr_alertCount="'+result[i].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="0" attr_statusName="">'+result[i].count+'</span></td>';
								
								for(var j in result[i].voList){
									for(var k in result[i].voList[j].voList){
										if(result[i].voList[j].voList[k].count !=null && result[i].voList[j].voList[k].count>0){
											str+='<td class=""  style="background-color:'+globalStatusBgObj[result[i].voList[j].title]+'" ><span class="getAmsPopUpCls" attr_alertCount="'+result[i].voList[j].voList[k].count+'" attr_categoryId="'+alertSourceId+'" attr_location_id="'+type+'" attr_location_district_id="'+result[i].districtId+'" attr_location_constituency_id="'+result[i].constituenctId+'" attr_location_mandal_id="'+result[i].mandalId+'" attr_statusid="'+result[i].voList[j].voList[k].statusId+'" attr_statusName="'+result[i].voList[j].voList[k].status+'">'+result[i].voList[j].voList[k].count+'</span></td>';
											
										}else{
											str+='<td style="background-color:'+globalStatusBgObj[result[i].voList[j].title]+'"> - </td>';
											
										}
									}
									if(result[i].voList[j].percentage !=null && result[i].voList[j].percentage>0){
										str+='<td style="font-weight:bold;margin-left:15px;background-color:'+globalStatusBgObj[result[i].voList[j].title]+'" >'+result[i].voList[j].percentage+'</td>';
									}else{
										str+='<td style="background-color:'+globalStatusBgObj[result[i].voList[j].title]+'"> - </td>';
									}
									
								}
							str+='</tr>';
							}
						}
					str+='<tbody>';
				str+='<table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	 $("#jalavani"+type).html(str);
	 if(type == 'state'){
		 $("#dataTable"+type).dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": false,
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					text		:'<i class="fa fa-file-text-o generateExcelcdfdf" attr_id="dataTable'+type+'"></i>',
					titleAttr	: 'csv',
				}
			]
		});
	 }else if(type == 'district'){
		 $("#dataTable"+type).dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": false,
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					text		:'<i class="fa fa-file-text-o generateExcelcdfdf" attr_id="dataTable'+type+'"></i>',
					titleAttr	: 'csv',
				}
			]
		});
	 }else{
		 $("#dataTable"+type).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					text		:'<i class="fa fa-file-text-o generateExcelcdfdf" attr_id="dataTable'+type+'"></i>',
					titleAttr	: 'csv',
				}
			]
		});
	 }
}
$(document).on("click",".generateExcelcdfdf",function(){
	var id = $(this).attr("attr_id");
	tableToExcel(id, 'JALAVANI ALERT DASHBOARD');
});
$(document).on("click",".getAmsPopUpCls",function(){
	var alertCount = $(this).attr("attr_alertCount");
	var categoryId = $(this).attr("attr_categoryId");
	var location_id = $(this).attr("attr_location_id");
	var district_id = $(this).attr("attr_location_district_id");
	var constituency_id = $(this).attr("attr_location_constituency_id");
	var mandal_id = $(this).attr("attr_location_mandal_id");
	var statusid = $(this).attr("attr_statusid");
	var statusName = $(this).attr("attr_statusName");
	var statusType='Status';
	
	var locationValueName='';
	var locationValue='';
	if(location_id == "district"){
		locationValueName ='district';
		locationValue = district_id;
	}else if(location_id == "constituency"){
		locationValueName ='constituency';
		locationValue = constituency_id;
	}else if(location_id == "mandal"){
		locationValueName ='mandal';
		locationValue = mandal_id;
	}else if(location_id == "state"){
		locationValueName ='state';
		locationValue = 1;
	}else if(location_id == "ivrStatus"){
		locationValueName ='district';
		locationValue = district_id;
		categoryId =0;
	}
	$("#alertManagementPopupBody").html('')
	
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
	
	getJalavaniAlertSourceDetailsInformation(alertCount,categoryId,statusid,locationValueName,locationValue,statusName,statusType)
});

function getJalavaniAlertSourceDetailsInformation(alertCount,categoryId,statusid,locationValueName,locationValue,statusName,statusType){
	//district-5,mandal-8,status-id/CategotyID
	$("#modalHeadingTotal").html("");
	$.ajax({
		url: wurl+"/WebService/getJalavaniAlertSourceDetailsInformation/"+currentFromDate+"/"+currentToDate+"/"+statusType+"/"+locationValueName+"/"+locationValue+"/"+categoryId+"/"+statusid+"/"+globalDepartmentId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniAlertSourceDetailsInformation/"+currentFromDate+"/"+currentToDate+"/"+statusType+"/"+locationValueName+"/"+locationValue+"/"+categoryId+"/"+statusid+"/"+globalDepartmentId
	}).then(function(result){
		 if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,alertCount,"");
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
		
	});	
}

function buildAlertDtlsBasedOnStatusClick(result,statusName,statuscount,blockType)
{
	var str='';
	var alertId = '';
	if(blockType == "closedAndReopend"){
		$("#modalHeadingTotal").html("Total "+statusName+' - '+result.length);
	}else{
		if(statuscount !=null && statuscount >0){
			$("#modalHeadingTotal").html("Total "+statusName+' - '+statuscount);
		}else{
			$("#modalHeadingTotal").html("Total "+statusName+' :');
		}
		
	}
	
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
						if(blockType =="closedAndReopend"){
							str+='<th><span class="channel-name">Present Status</span></th>';
						}else{
							str+='<th><span class="channel-name">Status</span></th>';
						}
						str+='<th><span class="channel-name">Ofcr Name</span></th>';//alertStatus
						str+='<th><span class="channel-name">Ofcr Location</span></th>';
						//str+='<th><span class="channel-name">Lag Days</span></th>';
						//str+='<th>Subtask <i class="fa fa-level-down"></i></th>';
						if(blockType !="closedReopen"){
							str+='<th></th>';
						}
						
					str+='</thead>';
					str+='<tbody>';
					for(var i in result)
					{
						
							str+='<tr>';
								str+='<td>';
									
								if(result[i].severtyColor != null)
								{
									str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:'+result[i].severtyColor+';margin-right:3px;"></i>';
								}else{
									str+='<i class="glyphicon glyphicon-cog text-danger"  style="color:#eee;margin-right:3px;"></i>';
								}
								
								str+='</td>';
								str+='<td> ';
									if(result[i].id != null)
									{
										str+=''+result[i].id+'</td>';
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
									if(result[i].status != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].status+'">'+result[i].status+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
								str+='<td class="text-center">';
									if(result[i].problem != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].problem+'">'+result[i].problem+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>'; 
								 str+='<td class="text-center">';
									if(result[i].relatedTo != null)
									{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="'+result[i].relatedTo+'">'+result[i].relatedTo+'</span>';
									}else{
										str+='<span class="channel-name" data-toggle="tooltip" class="location-name" data-placement="top" title="-">-</span>';
									}
									
								str+='</td>';
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
								if(blockType !="closedReopen"){
								str+='<td>';
									if(result[i].id != null && result[i].id > 0)
									{
										str+='<span class="arrow-icon pull-right alertIdCls" attr_statusId="'+result[i].statusId+'" attr_alertId="'+result[i].id+'" expand-icon="block1">';
											str+='<i class="glyphicon glyphicon-menu-right"></i>';
										str+='</span>';

									}else{
										str+='-';
									}
									
								str+='</td>';
								}
							str+='</tr>';
						
					}
						
					str+='</tbody>';
				str+='</table>';
			
			str+='</div>';
		str+='</div>';
		str+='<div id="rightSideExpandView"></div>';
	str+='</div>';
	$("#alertManagementPopupBody").html(str);
	$("#dataTable").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]]
		});
	$('[data-toggle="tooltip"]').tooltip();
	//getAlertData(alertId);
}
$(document).on("click","[expand-icon]",function(){
        var expandBlockName = $(this).attr("expand-icon");
		var alertId = $(this).attr("attr_alertId");
		var statusId = $(this).attr("attr_statusId");
		$("[expand-icon]").closest("li").removeClass("active");
		$("[expand-icon]").removeClass("text-primary");
		$(this).addClass("text-primary");
		$(this).closest("li").addClass("active");
		 rightSideExpandView(alertId);
		glStr = '';
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
							str+='<div id="alertGeneralComments"></div>';
						str+='</div>';
						
					str+='</div>';
				
				
				str+='</div>';
				
				
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#rightSideExpandView").html(str);
	$('[data-toggle="tooltip"]').tooltip();
	$(".chosenSelect").chosen({width:'100%'});
	
	
	getStatusCompletionInfo(alertId);
	getAssignedOfficersDetails(alertId);
	getDepartmentsByAlert(alertId);
	getAlertData(alertId);
	getCommentsForAlert(alertId);
	getAlertCategoryByAlert(alertId);
	getDocumentsForAlerts(alertId);
	
}
$(document).on("click","[status-icon] li",function(e){
        e.stopPropagation();
		var status = $(this).attr("status-icon-block");		
		var alertId = $(this).attr("attr_alert_id");
		var subalertid = $(this).attr("subalertid");
		if(status != null && status != undefined)
		{
			if(status == 'alertHistory')
			{
				$("#alertManagementPopup1").modal({
					show: true,
					keyboard: false,
					backdrop: 'static'
				});
				$("#alertManagementPopupHeading").html('ALERT HISTORY')
				$("#alertManagementPopup1 .modal-footer").html(' ');
				viewAlertHistory(alertId);
			}
		}
	});

function viewAlertHistory(alertId){
	$("#alertManagementPopupBody1").html(spinner)
	$.ajax({
		url: wurl+"/WebService/viewAlertHistory/"+alertId+"/task"
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/viewAlertHistory/"+alertId+"/task"
	}).then(function(result){
		if(result != null && result.length> 0)
		{
			alertHistory(result);
		}else{
			$("#alertManagementPopupBody1").html("NO DATA AVAILABLE...")
		}
		
	});	
}
function alertHistory(result)
{
	
	var str='';
	for(var i in result){
			str+='<ul class="alert-history m_top10" style="list-style:outside none none">';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i][0].trackingDate+'</span>';
			
			for(var j in result[i]){
				str+='<li>';
			
				str+='<span class="alert-history-time" >'+result[i][j].trackingTime+'</span>';
					if(result[i][j].actionType == 'Assigning'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Assigned BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>    </p>';
						
					}else if(result[i][j].actionType == 'Attachment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'</p>'; 
						str+='<p><span class="alert-history-body text-capital"><a target="_blank" href="../images/'+result[i][j].document+'" width="25%" style="margin-left: 25px;" class="m_top5" >'+result[i][j].document+'</a></span></p>';
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';      
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Due Date'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i][j].dueDate+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>'; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Priority'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i][j].severty+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>'; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Status Change'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Status </span> :';
						if(result[i][j].status == 'Proposal'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Status </span> :'+result[i][j].proposalStatus+'</p>';
							 if(result[i][j].categoryId == 1 ){
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'';
								if(result[i][j].proposalStatus == 'Proposal Accept'){
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/-';
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px">Approved Amount </span> :'+result[i][j].approvedAmount+'/-</p>';
								}else{
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/- </p>';
								}
							}else{
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'</p>';
							}
						 }else if(result[i][j].status == 'Rejoinder'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Rejoinder Status </span> :'+result[i][j].rejinderStatus+'</p>';
						}else {
							str+=''+result[i][j].status+'</p>';
						} 
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Comment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' </p>'; 
						
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span> '; 
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>'; 
					}else if(result[i][j].actionType == 'Feedback Status'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						if(result[i][j].alertFeedbackStatus != null && result[i][j].alertFeedbackStatus != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Feed back Status </span>: '+result[i][j].alertFeedbackStatus+'</p>'; 
						}
						
						if(result[i][j].status != null && result[i][j].status != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Alert Status </span>: '+result[i][j].status+'</p>';
						}
						if(result[i][j].alertCallerName != null && result[i][j].alertCallerName != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Caller Name </span>: '+result[i][j].alertCallerName+'</p>';
						}
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}
				
					
				str+='</li>';	
			}
		
		str+='</ul>';
		}
	
	
	$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertManagementPopupBody1").html(str);
}	

var isAdmin = "";
var globalUserType = "";
var globalStatusId = 0;
var isStatusAvailable=true;
var globalEntitlement= "";

function getStatusCompletionInfo(alertId){
	isStatusAvailable=true;
	$("#updateStatusChangeBody").html(spinner);
	$("#statusDtlsDiv").html(spinner);
	$.ajax({
		url: wurl+"/WebService/getStatusCompletionInfo/"+alertId+"/0/0/0/0/0"
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getStatusCompletionInfo/"+alertId+"/0/0/0/0/0"
	}).then(function(result){
		$("#updateStatusChangeBody").html('');
		$("#statusDtlsDiv").html('');
		$('#displayStatusId,#displayAssignIconId,#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority,#historyId').hide();
		$('#displayStatusId').attr('status-icon-block','alertStatus');
		$('#docAttachmentId').hide();	
		
		if(result != null && result.length>0){
			var buildTypeStr = result[0].applicationStatus.split('-')[0].trim();
			globalUserType = buildTypeStr;
			var sttatusId = result[0].applicationStatus.split('-')[1].trim();
			globalStatusId = sttatusId; 
			
			if(result.length  == 1)
				isStatusAvailable=false;
			
			if(result[0].idnameList != null && result[0].idnameList.length > 0)
			{
				var str='';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-1 text-center body-icons"><i class="fa fa-volume-control-phone fa-2x"></i></div>';
					str+='<div class="col-sm-11">';
						str+='<h3>Caller Details </h3>';
						str+='<ul class="list-inline slickSlider">';
						for(var  j in result[0].idnameList)
						{
							str+='<li style="padding:0px 8px;margin:0px 5px;border:1px solid #ddd;">';
								str+='<table class="table table-condensed">';
									str+='<tr>';
										str+='<td>Name</td>';
										str+='<td>: '+result[0].idnameList[j].callerName+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Mobile No</td>';
										str+='<td>: '+result[0].idnameList[j].mobileNo+'</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>Caller</td>';
										str+='<td>: '+result[0].idnameList[j].userType+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</li>';
						}
						str+='</ul>';
						
					str+='</div>';
				str+='</div>';
				$("#callerDetailsDIv").html(str);
				if(result[0].idnameList.length > 3)
				{
					$('.slickSlider').slick({
						slide: 'li',
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: false,
						swipe:false,
						touchMove:false,
						variableWidth: false
					});
				}
				
			}
			
			$('#historyId').show();
			var entitlement = result[0].positionName;
			globalEntitlement = entitlement;
			if(result[0].dueDateStr != null && result[0].dueDateStr.trim().length>0){
				$('.modal-date').html(result[0].dueDateStr)
				$('.modal-date1').html(result[0].dueDateStr)
			}else{
				$('#displayDueDate2').hide();
				$('#displayDueDate1').hide();
			}
			
			if(buildTypeStr=='own'){  
				$('#displayStatusId,#displaySubTaskli,#displaySubTasksliId').show();	
				$('#docAttachmentId').show();	
				$('#displayDueDate1').show();
				$('#displayDueDate2').hide();
				
				
				
				if(globalStatusId == 12 ){ // closed
					isStatusAvailable=false;
					$('#displaySubTasksliId,#docAttachmentId').hide();
				}else {
					isStatusAvailable=true;
				}				
			}
			else if(buildTypeStr=='subUser'){	
				$('#displaySubTasksliId').hide();		
				$('#displayDueDate1').hide();
				$('#displayStatusId').show();
				$('#displayStatusId').removeAttr('status-icon-block');
				
				$('#displayDueDate2,#displayPriority').show();
				
				// closed-12, completed-4, reopen-11
				if( globalStatusId == 12 || globalStatusId == 4 || globalStatusId == 11){
					isStatusAvailable=true;
				}
				if(globalStatusId != 12){ // for not closed status alerts 
					$('#displaySubTasksliId,#docAttachmentId').show();					
				}
			}else if(buildTypeStr=='same'){ 
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority').show();
				$('#displayStatusId').show();       
				$('#displayDueDate1').show(); 
				isStatusAvailable=false;
			}
			else if(buildTypeStr=='other'){
				$('#displaySubTasksliId').hide();				
				$('#displayDueDate1').hide();				
				$('#displayDueDate2').hide();				
				$('#displayStatusId').show();
				isStatusAvailable=false;				
			}
			if((sttatusId == 1  || sttatusId == 8 || sttatusId==9) && result[0].userStatus != null && result[0].userStatus =='admin'){
				$('#displayAssignIconId').show();
				$('#docAttachmentId').show();	
				 assignUser(alertId);
				 
			}
		
			if(result[0].userStatus != null && result[0].userStatus =="admin"){
				isAdmin = "true";
				//$('#displayStatusId').attr('status-icon-block','');
				$('#displaySubTasksliId,#displayDueDate1,#displayDueDate2,#displayPriority').hide(); 
				if(sttatusId !=1)
					$('#docAttachmentId').show(); 
			}else{
				$('#displayStatusId').attr('status-icon-block','alertStatus');
				isAdmin = "false";
			}
			
			if(isAdmin=='false'){				
				$('#displayStatusId').attr('status-icon-block','alertStatus');
			}
			alertStatus(result,alertId);	

			if(globalStatusId == 12 || globalStatusId ==8 || globalStatusId ==9  ){ // closed || Wrongly Mapped Designation || Wrongly Mapped Department
				$('#displaySubTasksliId,#docAttachmentId,#displayPriority,#displayDueDate2').hide();
				$('#displayDueDate1').show();
			}
			 if(globalStatusId ==8 || globalStatusId ==9){
				$("#departDivId").show();
			} 
			
			//alert(" isStatusAvailable :"+isStatusAvailable);
		}else{
			$('#displayAssignIconId').show();
			$('#displayStatusId').show();
			$('#displaySubTasksliId').hide();  
			$('#docAttachmentId').hide();  
		}	
		setTimeout(function(){
			$("body").addClass("modal-open");
		},1000);
		
	});	
}

var glStr='';
var globalPropasalStr='';
function alertStatus(result,alertId)
{
	glStr='';
	globalPropasalStr='';
	var str1='';
	var str=''; 
		str1+='<div class="panel panel-default panel-white m_top20 alert-status-change-body">';
			str1+='<div class="panel-heading" style="margin-left: 20px;">';
				str1+='<div class="row" id="changeStatusDivId">';
				for(var i in result)
				{
					
					str1+='<div class="col-sm-4">';
						str1+='<div class="radioStyling">';
							if(globalStatusId == parseInt(result[i].id))
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+result[i].id+'">';
							}else
							{
								str1+='<input class="alertStatusCls" attr_id="'+result[i].id+'" type="radio" name="group1" id="radio-'+result[i].id+'">';
							}
							str1+='<label for="radio-'+result[i].id+'"><span class="radio" >'+result[i].name+'</span></label>';
						str1+='</div>';
					str1+='</div>';
				}				
				str1+='</div>';
			str1+='</div>';
			str1+='<div class="panel panel-default proposalAppendBlockDivCls" style="display:none;background-color:#ededed">';
				str1+='<div class="panel-heading" style="background-color:#ededed;padding-left:15px;">';
					str1+='<h4 class="panel-title">Proposal Information</h4>';
				str1+='</div>';
				str1+='<div class="panel-body" style="background-color:#ededed">';
					str1+='<div class="row">';
						str1+='<div class="col-sm-12">';
							str1+='<div class="m_top10">';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="1" name="statusChekBx">Financial Assistance<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="2" name="statusChekBx">Policy Decision Required<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline">';
								  str1+='<input type="checkbox" class="proposalCheckbox" value="3" name="statusChekBx">Others<span style="color:red">*</span>';
								str1+='</label>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4">';
							str1+='<div class="input-group amountCls m_top20" style="display:none;">';
								str1+='<span class="input-group-addon">';
									str1+='<i class="fa fa-inr"></i>';
								str1+='</span>';
								str1+='<input type="text" class="form-control" placeholder="Enter Amount" id="amountId">';
							str1+='</div>';
							str1+='<span id="errMsgAmuntId"></span>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>';
			
			 str1+='<div class="panel panel-default rejoinderDivCls" style="display:none;background-color:#ededed">';
				str1+='<div class="panel-heading" style="background-color:#ededed;padding-left:15px;">';
					str1+='<h4 class="panel-title">Rejoinder Status</h4>';
				str1+='</div>';
				str1+='<div class="panel-body" style="background-color:#ededed" >';
					str1+='<div class="row">';
						str1+='<div class="col-sm-12">';
							str1+='<div class="m_top10">';
								str1+='<label class="checkbox-inline" id="rejinderReqId">';
								  str1+='<input type="checkbox" class="rejoinderCheckbox" value="1" name="RejoinderChekBx">Rejoinder Request<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<label class="checkbox-inline" id="rejinderRespnseId">';
								  str1+='<input type="checkbox" class="rejoinderCheckbox" value="2" name="RejoinderChekBx">Rejoinder Response<span style="color:red">*</span>';
								str1+='</label>';
								str1+='<form id="alertAssignAttachemntFrRejoinderStatusId" name="uploadAttachementFrRejoinderStatus">';
									str1+='<input type="file" id="rejoinderAttachmentId" name="imageForDisplay" style="margin-top:21px; margin-left:35px;">';
									str1+='<input type="hidden" value="'+alertId+'" name="alertVO.alertId">';
								str1+='</form>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
			str1+='</div>'; 

			
			str1+='<div class="panel-body pad_0 m_top20">';
				str1+='<textarea class="form-control" id="updateStatusChangeComment" placeholder="Comment.."></textarea>';
			str1+='</div>';
		str1+='</div>';

	
	str1+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subTaskId="" id="updateStatusChangeId">update</button>';
	str1+='<span id="updateStatusChangeAjaxSymbol"></span>';
	str1+='<span id="updateStatusChangeMsg"></span>';
	
		str+='<div class="col-sm-12">';
			str+='<div style="padding:10px;background-color:#ddd">';
			if(globalPropCategory != null){
				str+='<p><strong>Proposal Category </strong>:'+globalPropCategory+'</p>';
			}
			if(globalPropReqAunt != null && globalPropReqAunt.length > 0){
				str+='<p class="m_top5"><strong>Requested Amount </strong>:'+globalPropReqAunt+'/- </p>';
			}
			str+='</div>';	
		str+='</div>';	
		str+='<div class="col-sm-4">';
			str+='<div class="radioStyling">';
				str+='<input class="alertStatusCls alertStatusAmountCls" attr_id="3" type="radio" name="group1" id="radio-1">';
				str+='<label for="radio-1"><span class="radio">Proposal Accept<span style="color:red;"> *</span></span></label>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="radioStyling ">';
				str+='<input class="alertStatusCls alertStatusAmountCls" attr_id="2" type="radio" name="group1" id="radio-2">';
				str+='<label for="radio-2"><span class="radio">Proposal Reject<span style="color:red;"> *</span></span></label>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div class="input-group m_top5 alertStatusAmountInputCls" style="display:none;">';
				str+='<span class="input-group-addon">';
					str+='<i class="fa fa-inr"></i>';
				str+='</span>';
				str+='<input type="text" class="form-control" placeholder="Enter Approved Amount" id="approvedAmountId">';
			str+='</div>';
			str+='<span id="errMsgAprAmuntId"></span>';
		str+='</div>'; 
	   str+='<div class="panel-body pad_0">';
		str+='<textarea class="form-control m_top10" id="acceptedStatusChangeComment" placeholder="Comment.."></textarea>';
	str+='</div>';
	str+='<button class="btn btn-primary btn-sm text-capital" attr_alert_id="'+alertId+'" subTaskId="" id="updatePrposalStatsId">update</button>';
	str1+='<span id="updateProposalStatusChangeMsg"></span>';
	glStr=str1;
	globalPropasalStr=str;
	//$("#updateStatusChangeBody").html(str1);
	
}
function getCommentsForAlert(alertId){
	$("#alertGeneralComments").html(spinner);
	$.ajax({
		url: wurl+"/WebService/viewAlertHistory/"+alertId+"/task"
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/viewAlertHistory/"+alertId+"/task"
	}).then(function(result){
		
		if(result != null && result.length > 0)
		{
			buildCommentsForAlert(result);
		}else{
			$("#alertGeneralComments").html("NO DATA");
		}
		
	});	
}
var globalPropCategory;
var	globalPropReqAunt;	
var	globalPrposalCategoryId;	
function buildCommentsForAlert(result)
{
	var str='';
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-1 text-center body-icons">';
			str+='<i class="fa fa-road fa-2x"></i>';
		str+='</div>';
		str+='<div class="col-sm-11">';
			str+='<h4 class="text-muted text-capital">complete alert history</h4>';
		str+='<ul class="alert-myfoot m_top10" style="list-style:outside none none">';
		for(var i in result){
			
			str+='<li>';
			str+='<span class="alert-history-date"  style="background-color: lightpink;padding: 3px;border-radius: 5px;" >'+result[i][0].trackingDate+'</span>';
			for(var j in result[i]){
					if(result[i][j].actionType == 'Assigning'){     
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Assigned BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>    </p>';
						
					}else if(result[i][j].actionType == 'Attachment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						str+='<p><span class="alert-history-body text-capital"><a target="_blank"  href="../images/'+result[i][j].document+'" width="25%" style="margin-left: 25px;" class="m_top5" >'+result[i][j].document+'</a></span></p>';       
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">'+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';     
					}else if(result[i][j].actionType == 'Due Date'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Changed Date </span> : '+result[i][j].dueDate+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';  
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
						
					}else if(result[i][j].actionType == 'Priority'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Priority </span> : '+result[i][j].severty+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Status Change'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+'  <span class="pull-right"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						 str+='<p class="m_top20 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px">Status </span> :';
						if(result[i][j].status == 'Proposal'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Status </span> :'+result[i][j].proposalStatus+'</p>';
								globalPrposalCategoryId = result[i][j].categoryId;
							 if(result[i][j].categoryId == 1 ){
								 globalPropCategory = result[i][j].category;
								globalPropReqAunt = result[i][j].amount;
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'';
								if(result[i][j].proposalStatus == 'Proposal Accept'){
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/-';
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px">Approved Amount </span> :'+result[i][j].approvedAmount+'/-</p>';
								}else{
									str+='<span class="text-capital myfontStyle m_top5" style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Amount </span> :'+result[i][j].amount+'/- </p>';
								}
							}else{
								globalPropCategory = result[i][j].category;
								globalPropReqAunt = result[i][j].amount;
								str+='<p class="text-capital myfontStyle m_top5"><span style="color:slategrey;font-weight:bold;margin-left: 25px"> Proposal Categoty </span> :'+result[i][j].category+'</p>';
							}
						}else if(result[i][j].status == 'Rejoinder'){
							str+=''+result[i][j].status+'';
								str+='<p class="text-capital myfontStyle m_top5"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Rejoinder Status </span> :'+result[i][j].rejinderStatus+'</p>';
						}else {
							str+=''+result[i][j].status+'</p>';
						} 
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';     
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Comment'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}else if(result[i][j].actionType == 'Feedback Status'){
						str+='<p class="alert-history-status m_top20 text-capital" style="background-color: lightgrey;padding: 3px;border-radius: 5px;"><span class="status-icon arrow-icon"></span>Action : '+result[i][j].actionType+' <span class="pull-right"> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Time </span> : <span style="font-size:10px">  '+result[i][j].trackingTime+'  </span></span></p>'; 
						
						if(result[i][j].alertFeedbackStatus != null && result[i][j].alertFeedbackStatus != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Feed back Status </span>: '+result[i][j].alertFeedbackStatus+'</p>'; 
						}
						if(result[i][j].alertCallerName != null && result[i][j].alertCallerName != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Caller Name </span>: '+result[i][j].alertCallerName+'</p>';
						}
						if(result[i][j].status != null && result[i][j].status != ""){
							str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Alert Status </span>: '+result[i][j].status+'</p>';
						}
						
						str+='<p class="alert-history-body m_top5 text-capital myfontStyle"> <span style="color:slategrey;font-weight:bold;margin-left: 18px"> Comment </span>: '+result[i][j].comment+'</p>';
						
						str+='<p class=" alert-history-user m_top20 text-capital "> <span style="color:slategrey;font-weight:bold;margin-left: 25px"> UPDATED BY </span> : <span style="font-size:10px">  '+result[i][j].updatedUserName+'  </span>';
						if(result[i][j].position != "admin"){
							str+='<span style="color:slategrey;font-weight:bold;margin-left: 25px"> DEPT </span> : <span style="font-size:10px">  '+result[i][j].deptName+'  </span>   <span style="color:slategrey;font-weight:bold;margin-left: 25px"> DESIGNATION </span> : <span style="font-size:10px">  '+result[i][j].designation+'  </span>  <span style="color:slategrey;font-weight:bold;margin-left: 25px"> Location </span> : <span style="font-size:10px">  '+result[i][j].location+'  </span>';
						}
						str+='</p>';
					}
			}
		str+='</li>';	
		
		}
		str+='</ul>';
	str+='</div>';
	//$("#alertManagementPopup1 .modal-dialog").css("width","60%")
	$("#alertGeneralComments").html(str);
//}
}


function getAlertData(alertId){
	$("#alertDetails").html(spinner);
	$.ajax({
		url: wurl+"/WebService/getAlertData/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getAlertData/"+alertId
	}).then(function(result){
		$("#alertDetails").html('');
		
		//getInvolvedMembersDetilas(alertId);
		//getSubTaskInfoForAlert(alertId);
		//getCommentsForAlert(alertId);
		
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
		  //url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
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
									 var name1 = nameArr[3];
									str2+='<p><i class="fa fa-file-o" aria-hidden="true"></i>&nbsp;&nbsp;<a href="http://www.mytdp.com/images/'+result[i].rejinderDocList[j].subList1[k].name+'" style="cursor:pointer;">'+name1+'</a>&nbsp;&nbsp;(<i class="fa fa-calendar" aria-hidden="true"></i> &nbsp;'+result[i].rejinderDocList[j].subList1[k].date1+')</p>';
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
	
	$("#alertCategory").html(spinner);
	$.ajax({
		url: wurl+"/WebService/getAlertCategoryByAlert/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getAlertCategoryByAlert/"+alertId
	}).then(function(result){
		if(result != null)
		{
			var str='';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-1 text-center body-icons">';
					str+='<i class="fa fa-tags fa-2x"></i>';
				str+='</div>';
				str+='<div class="col-sm-11">';
					str+='<h4 class="text-muted text-capital">category</h4>';
					if(result.name !=null){
						str+='<p class="m_top20"><span class="label label-default label-category">'+result.name+'</span></p>';
					}else{
						str+='<p class="m_top20"><span class="label label-default label-category"> - </span></p>';
					}
					
				str+='</div>';
			str+='</div>';
			$("#alertCategory").html(str);
		}
	});	
}
function getSubTaskInfoForAlert(alertId){
	$("#alertSubtask").html(spinner);
	$.ajax({
		url: wurl+"/WebService/getSubTaskInfoForAlert/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getSubTaskInfoForAlert/"+alertId
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

	$("#alertSubtask").html(str);
}
function getDocumentsForAlerts(alertId){
	$("#existingDocsDivId").html("");
	$("#existingDocsDivId").html(spinner);
	$.ajax({
		url: wurl+"/WebService/getDocumentsForAlerts/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getDocumentsForAlerts/"+alertId
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
		url: wurl+"/WebService/getDepartmentsByAlert/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getDepartmentsByAlert/"+alertId
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
		url: wurl+"/WebService/getAssignedOfficersDetails/"+alertId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getAssignedOfficersDetails/"+alertId
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

//getArticlesMonthlyOverviewInfoBySearchType("alerts",2,"print");//print media Graph onchange
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

$(document).on("click",".closeSecondModal",function(){
		setTimeout(function(){
			$("body").addClass("modal-open")
		},1000);
	});
$(document).on('click', '.imageShowOpen li', function(){
		var id = $(this).attr("attr_doc_id");
		var path = "../images/"+$(this).attr("attr_path");
		window.open(path);
	});
$(document).on("click",".articleDetailsCls",function(){
		var articleId= $(this).attr("attr_articleId");
		$("#alertManagementPopup1").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopup1 .modal-footer").html(' ');
		getTotalArticledetails(articleId);
	});
function getTotalArticledetails(articleId){
	
	$("#alertManagementPopupBody1,#alertManagementPopupHeading").html(spinner);
	$.ajax({
		  type : 'GET',      
		  	url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
			//url: "http://192.168.11.173:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","CORP-GMC","Ward","NATIONAL","INTERNATIONAL","MUNICIPALITY"];
		var result = results[0];
		var str = '';
		var heading = '';
		heading+='<h4 class="modal-title" id="myModalLabel">';
			heading+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
			heading+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
		heading+='</h4>';
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<img class="mainImage"  src="../NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
			str+='</div>';
			str+='<div class="col-md-12 m_top10">';
				str+='<h4 class="panel-title text-success">Description</h4>';
				str+='<p class="m_0 f_14">'+result.description+'</p>';
			str+='</div>';
			str+='<div class="col-md-12">';
			if( result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
				/* Candidate*/
				str+='<div class="row ">';
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
											str+='<tr>';
												if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
												}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
												var candidataExist = false;
												if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
												candidataExist = true; 
												str+=''+result.subList[i].fromList[j].candidateName;
												}
												if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
												candidataExist = true; 
												str+=' ('+result.subList[i].fromList[j].designation + ")";
												}
												if(!candidataExist){
												str+=' - ';
												}
												str+='</td>';
											str+='</tr>';
											str+='<tr>';
												str+='<td colspan="2">';
													if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
														str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
													}else{ 
														str+='<p class="m_0">Impact Level : - </p>';	
													}
													if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
														str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
													}else{ 
														str+='<p class="m_0">Category : - </p>';	
													}
													if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
														str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
													}else{ 
														str+='<p class="m_0">News Activity : - </p>';	
													}
													if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
														str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
													}else{ 
														str+='<p class="m_0">News type : - </p>';	
													}
													if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
													if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
														str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
													}else{ 
														str+='<p class="m_0">News Related : - </p>';	
													}
													if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
														str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
													}else{ 
														str+='<p class="m_0">Priority : - </p>';	
													}
													if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
														str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
													}else{ 
														str+='<p class="m_0">Solution : - </p>';	
													}
													}
												str+='</td>';
											str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
						str+='</div>';//panel
					str+='</div>';//colmd6
					str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
								str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
							/* TO Table*/
							if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
								for( var j in result.subList[i].toList){
									str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
											if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
												str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
											}else{
												str+='<td> - </td>';
											}
												str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';
											var candidataExist = false;
											if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
												str+=''+result.subList[i].toList[j].candidateName;
											}
											if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
											candidataExist = true; 
												str+=' ('+result.subList[i].toList[j].designation + ")";
											}
											if(!candidataExist){
												str+=' - ';
											}
											str+='</td>';
										str+='</tr>';
										str+='<tr>';
											str+='<td colspan="2">';

												if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
													str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
												}else{ 
													str+='<p class="m_0">Impact Level : - </p>';	
												}

												if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
													str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
												}else{ 
													str+='<p class="m_0">Category : - </p>';	
												}
												if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
													str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
												}else{ 
													str+='<p class="m_0">News Activity : - </p>';	
												}
												if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
													str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
												}else{ 
													str+='<p class="m_0">News type : - </p>';	
												}
												if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){

												if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
													str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
												}else{ 
													str+='<p class="m_0">News Related : - </p>';	
												}
												if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
													str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
												}else{ 
													str+='<p class="m_0">Priority : - </p>';	
												}
												if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
													str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
												}else{ 
													str+='<p class="m_0">Solution : - </p>';	
												}
												}
											str+='</td>';
										str+='</tr>';
									str+='</table>';
								}
							}

							str+='</div>';//panelbody
						str+='</div>';//panel
					str+='</div>';//colmd6

				str+='</div>';//row
				}
			}

			str+='</div>';//colmd12
		str+='</div>';//row
		/* Article Scope Location */
		str+='<div class="row">';
			str+='<div class="col-md-12">';
				str+='<div class="panel panel-default panelArticleGroup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						str+='<table class="table table-condensed">';
							str+='<tr>';
								str+='<td>Impact Scope : </td>';
								if(result.impactScopeId!=null){
									str+='<td>'+obj[result.impactScopeId]+'</td>';
								}else{
									str+='<td> - </td>';
								}
							str+='</tr>';
							str+='<tr>';
								str+='<td>Location : </td>';
								if(result.scopeLocation!=null){
									str+='<td>'+result.scopeLocation+'</td>';
								}else{
									str+='<td> - </td>';
								}
							str+='</tr>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="row">';
			/*Lnking*/
			str+='<div class="col-md-6">';
				str+='<div class="panel panel-default panelArticleGroup">';
					str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
					str+='</div>';
					str+='<div class="panel-body">';
						if( result.linkedList != null && result.linkedList.length > 1){
							str+='<div class="row">';
								for( var i in result.linkedList){
									if(result.linkedList[i].articleId !=articleId ){
										str+='<div class="col-md-4" style="margin-top:5px;">';
											str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="../NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
										str+='</div>';
									}
								}
							str+='</div>';
						}else{
							str+="<h5> No Linked Articles Available </h5>";
						}

					str+='</div>';
				str+='</div>';
			str+='</div>'; 
		str+='</div>';

		$("#alertManagementPopupBody1").html(str);
		$("#alertManagementPopupHeading").html(heading)
	});    
}
/*$(document).on("change","[role='tabListMobile']",function(){
	var id = $('option:selected', this).attr('tab_id');
	$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
	$("#"+id).addClass("active");
});

function responsiveTabs()
{
	var $this = $(this);
	var $windowWidth = $(window).width();
	if($windowWidth < 768)
	{
		$('[role="tabListMobile"]').show();
		$('[role="tablist"]').hide();
	}else{
		$('[role="tabListMobile"]').hide();
		$('[role="tablist"]').show();
	}
}*/

function getJalavaniFeedBackDetailsInfo(){
	$("#ivrStatusFeedBackDivId").html(spinner);
	 
	$.ajax({
		url: wurl+"/WebService/getJalavaniFeedBackDetailsInfo/"+currentFromDate+"/"+currentToDate
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniFeedBackDetailsInfo/"+currentFromDate+"/"+currentToDate
	}).then(function(result){
		if(result !=null && result.length>0){
			buildJalavaniFeedBackDetailsInfo(result);
		}else{
			$("#ivrStatusBaseFeedBackDivId").hide();
		}
	});	
}

function buildJalavaniFeedBackDetailsInfo(result){
	var str='';
		var globalStatushamletVoterInfo={"Completely Satisfied":"#0FBE08","Partially Satisfied":"#ec7b02","Not Satisfied":"#FF0909"}
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<h5 class="font_weight text-capital">Call Center Feedback</h5>';
			str+='</div>';
			for(var i in result[0].hamletVoterInfo){
				
				str+='<div class="col-sm-4 m_top10">';
					str+='<div class="bg_yash_border">';
						str+='<h5 class="font_weight" style="color:'+globalStatushamletVoterInfo[result[0].hamletVoterInfo[i].name]+';text-transform:uppercase;">'+result[0].hamletVoterInfo[i].name+'</h5>';
						if(result[0].hamletVoterInfo[i].id==1){
							if(result[0].hamletVoterInfo[i].satisfiedCount !=null && result[0].hamletVoterInfo[i].satisfiedCount>0){
								str+='<h4 class="m_top10"><span class="feedbackStatusCls" attr_location_district_id="0" attr_location_id="ivrStatus" attr_statusid="1" attr_statusName="COMPLETLY SATISIFIED" attr_alertCount="'+result[0].hamletVoterInfo[i].satisfiedCount+'" style="cursor:pointer;">'+result[0].hamletVoterInfo[i].satisfiedCount+'</span><small style="color:#0FBE08;"> ('+result[0].hamletVoterInfo[i].satisfiedPerc+'%)</small></h4>';
							}else{
								str+='<h4 class="m_top10"><span>'+result[0].hamletVoterInfo[i].satisfiedCount+'</span><small style="color:#0FBE08;"> ('+result[0].hamletVoterInfo[i].satisfiedPerc+'%)</small></h4>';
							}
						}else if(result[0].hamletVoterInfo[i].id==2){
							if(result[0].hamletVoterInfo[i].notSatisfiedCount !=null && result[0].hamletVoterInfo[i].notSatisfiedCount >0){
								str+='<h4 class="m_top10"><span class="feedbackStatusCls" attr_location_district_id="0" attr_location_id="ivrStatus" attr_statusid="2" attr_statusName="NOT SATISIFIED" attr_alertCount="'+result[0].hamletVoterInfo[i].notSatisfiedCount+'" style="cursor:pointer;">'+result[0].hamletVoterInfo[i].notSatisfiedCount+'</span><small style="color:#FF0909;">( '+result[0].hamletVoterInfo[i].notSatisfiedPerc+'%)</small></h4>';
							}else{
								str+='<h4 class="m_top10"><span>'+result[0].hamletVoterInfo[i].notSatisfiedCount+'</span><small style="color:#FF0909;">( '+result[0].hamletVoterInfo[i].notSatisfiedPerc+'%)</small></h4>';
							}
							
						}else if(result[0].hamletVoterInfo[i].id==3){
							if(result[0].hamletVoterInfo[i].partiallySatisfiedCount !=null && result[0].hamletVoterInfo[i].partiallySatisfiedCount>0){
								str+='<h4 class="m_top10"><span class="feedbackStatusCls" attr_location_district_id="0" attr_location_id="ivrStatus" attr_statusid="3" attr_statusName="PARTIALLY SATISIFIED" attr_alertCount="'+result[0].hamletVoterInfo[i].partiallySatisfiedCount+'" style="cursor:pointer;">'+result[0].hamletVoterInfo[i].partiallySatisfiedCount+'</span><small style="color:#ec7b02;">( '+result[0].hamletVoterInfo[i].partiallySatsifyPerc+'%)</small></h4>';
							}else{
								str+='<h4 class="m_top10"><span>'+result[0].hamletVoterInfo[i].partiallySatisfiedCount+'</span><small style="color:#ec7b02;">( '+result[0].hamletVoterInfo[i].partiallySatsifyPerc+'%)</small></h4>';
							}
						}
						str+='</div>'; 
						str+='</div>'; 
				}
				str+='</div>'; 
				
		
		
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<h5 class="font_weight text-capital">IVR SUMMARY</h5>';
			str+='</div>';
			var postiveAlerts=0,negativeAlerts=0,positiveRespondants=0,negativeRespondants=0,totalAlerts=0,totalRespondants=0;
			var postiveAlertsPerc=0,negativeAlertsPerc=0,positiveRespondantsPerc=0,negativeRespondantsPerc=0;
			for(var i in result[0].hamletVoterInfo){
				if(result[0].hamletVoterInfo[i].name != "Not Satisfied"){
					if(result[0].hamletVoterInfo[i].hamletVoterInfo !=null && result[0].hamletVoterInfo[i].hamletVoterInfo.length>0){
						for(var j in result[0].hamletVoterInfo[i].hamletVoterInfo){
							postiveAlerts = postiveAlerts+result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount;
							negativeAlerts = negativeAlerts+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount;
							
							positiveRespondants=positiveRespondants+result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertPositiveRespondentCount+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertPositiveRespondentCount;
							
							negativeRespondants=negativeRespondants+result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertNegativeRespondentCount+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertNegativeRespondentCount;
						}
					}
				}
			}
			totalAlerts =postiveAlerts+negativeAlerts;
			totalRespondants=positiveRespondants+negativeRespondants;
			
			postiveAlertsPerc  = (postiveAlerts* 100/totalAlerts).toFixed(2);
			negativeAlertsPerc  = (negativeAlerts* 100/totalAlerts).toFixed(2);
			positiveRespondantsPerc  = (positiveRespondants* 100/totalRespondants).toFixed(2);
			negativeRespondantsPerc  = (negativeRespondants* 100/totalRespondants).toFixed(2);
			
			str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered table_Ivr">';
						str+='<thead>';
							str+='<th>Overall Alerts</th>';
							str+='<th>Overall Respondants</th>';
							str+='<th>Positive Alerts</th>';
							str+='<th>Positive Respondants</th>';
							str+='<th>Negative Alerts</th>';
							str+='<th>Negative Respondants</th>';
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>'
							
								if(totalAlerts !=null && totalAlerts>0){
									str+='<td>'+totalAlerts+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(totalRespondants !=null && totalRespondants>0){
									str+='<td>'+totalRespondants+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								
								if(postiveAlerts !=null && postiveAlerts>0){
									str+='<td>'+postiveAlerts+' <span style="color:#0FBE08;">('+postiveAlertsPerc+') %</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(positiveRespondants !=null && positiveRespondants>0){
									str+='<td>'+positiveRespondants+'<span style="color:#0FBE08;">('+positiveRespondantsPerc+') %</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(negativeAlerts !=null && negativeAlerts>0){
									str+='<td>'+negativeAlerts+' <span style="color:#FE3131;">('+negativeAlertsPerc+') %</td>';
								}else{
									str+='<td> - </td>';
								}
								if(negativeRespondants !=null && negativeRespondants>0){
									str+='<td>'+negativeRespondants+' <span style="color:#FE3131;">('+negativeRespondantsPerc+') %</td>';
								}else{
									str+='<td> - </td>';
								}
								
								
							str+='</tr>'
						str+='</tbody>';
					str+='</table>';
				str+='</div>';	
			str+='</div>';
			str+='<div class="col-sm-12 m_top10">';
				str+='<h5 class="font_weight text-capital">IVR RESPONSE</h5>';
			str+='</div>';
			for(var i in result[0].hamletVoterInfo){
				if(result[0].hamletVoterInfo[i].name != "Not Satisfied"){
					str+='<div class="col-sm-6 m_top10">';
					str+='<div class="bg_yash_border">';
						str+='<h5 class="font_weight" style="color:'+globalStatushamletVoterInfo[result[0].hamletVoterInfo[i].name]+';text-transform:uppercase;">'+result[0].hamletVoterInfo[i].name+'</h5>';
						str+='<div class="row">';
							str+='<div class="col-sm-12 m_top10">';
								str+='<div class="table-responsive">';
									str+='<table class="table table-bordered table_Ivr">';
										str+='<thead>';	
											str+='<tr>';
												for(var j in result[0].hamletVoterInfo[i].hamletVoterInfo){
													str+='<th colspan="2">'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].name+'</th>';
												}
											str+='</tr>';
											str+='<tr>';
												for(var j in result[0].hamletVoterInfo[i].hamletVoterInfo){
													str+='<th>+ve</th>';
													str+='<th>-ve</th>';
												}
											str+='</tr>';
										str+='</thead>';
										str+='<tbody>';
											str+='<tr>';
												for(var j in result[0].hamletVoterInfo[i].hamletVoterInfo){
													var positiveAlertPositiveRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertPositiveRespondentCount;
													var positiveAlertNegativeRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertNegativeRespondentCount;
													
													var negativeAlertPositiveRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertPositiveRespondentCount;
													var negativeAlertNegativeRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertNegativeRespondentCount;
													
													var cumilativePositiveResponders = positiveAlertPositiveRespondentCount + negativeAlertPositiveRespondentCount;
													var cumilativeNegativeResponders = positiveAlertNegativeRespondentCount +negativeAlertNegativeRespondentCount;
													
													if(result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount !=null && result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount >0){
														str+='<td class="statusWiseIvrCls"  attr_status_id="'+result[0].hamletVoterInfo[i].id+'" attr_probTypeId="'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].id+'" attr_statusName="'+result[0].hamletVoterInfo[i].name+'" attr_alert_Count ="'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount+'" attr_district_id="0" attr_satisfied_status="Y" style="cursor:pointer;">'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount+'<i class="fa fa-info-circle tooltipClsStatusCls " aria-hidden="true" style="margin-left:5px;" data-toggle="tooltip" title="+ve Respondents : '+positiveAlertPositiveRespondentCount+' <br /> -ve Respondents : '+positiveAlertNegativeRespondentCount+'"></i></td>';
													}else{
														str+='<td>'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].postiveCount+'<i class="fa fa-info-circle tooltipClsStatusCls " aria-hidden="true" style="margin-left:5px;" data-toggle="tooltip" title="+ve Respondents : '+positiveAlertPositiveRespondentCount+' <br /> -ve Respondents : '+positiveAlertNegativeRespondentCount+'"></i></td>';
													}
													if(result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount !=null && result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount>0){
														str+='<td class="statusWiseIvrCls"  attr_status_id="'+result[0].hamletVoterInfo[i].id+'" attr_probTypeId="'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].id+'" attr_statusName="'+result[0].hamletVoterInfo[i].name+'" attr_district_id="0" attr_alert_Count ="'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount+'" attr_satisfied_status="N" style="cursor:pointer;">'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount+'<i class="fa fa-info-circle tooltipClsStatusCls" aria-hidden="true" style="margin-left:5px;" data-toggle="tooltip" title="+ve Respondents : '+negativeAlertPositiveRespondentCount+' <br /> -ve Respondents : '+negativeAlertNegativeRespondentCount+'"></i></td>';
													}else{
														str+='<td>'+result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeCount+'<i class="fa fa-info-circle tooltipClsStatusCls" aria-hidden="true" style="margin-left:5px;" data-toggle="tooltip" data-placement="top" data-container="body"  title="+ve Respondents : '+negativeAlertPositiveRespondentCount+' <br /> -ve Respondents : '+negativeAlertNegativeRespondentCount+'"></i></td>';
													}
												}
											str+='</tr>';
											
											str+='<tr>';
											for(var j in result[0].hamletVoterInfo[i].hamletVoterInfo){
													var positiveAlertPositiveRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertPositiveRespondentCount;
													var positiveAlertNegativeRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].positiveAlertNegativeRespondentCount;
													
													var negativeAlertPositiveRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertPositiveRespondentCount;
													var negativeAlertNegativeRespondentCount=result[0].hamletVoterInfo[i].hamletVoterInfo[j].negativeAlertNegativeRespondentCount;
													
													var cumilativePositiveResponders = positiveAlertPositiveRespondentCount + negativeAlertPositiveRespondentCount;
													var cumilativeNegativeResponders = positiveAlertNegativeRespondentCount +negativeAlertNegativeRespondentCount;
													
													totalResponderCount =cumilativePositiveResponders+cumilativeNegativeResponders;
													
													var postiveResponderPerc = (cumilativePositiveResponders*100/totalResponderCount).toFixed(2);
													var negativeResponderPerc= (cumilativeNegativeResponders*100/totalResponderCount).toFixed(2);
													
													if(cumilativePositiveResponders !=null && cumilativePositiveResponders>0){
														str+='<td title="Respondents"><span title="Respondents :'+cumilativePositiveResponders+'" style="color:#0FBE08;" data-toggle="tooltip" >('+postiveResponderPerc+' %)</span></td>';
													}else{
														str+='<td title="Respondents"> - </td>';
													}
													
													if(cumilativeNegativeResponders !=null && cumilativeNegativeResponders>0){
														str+='<td title="Respondents"><span title="Respondents :'+cumilativeNegativeResponders+'" style="color:#FE3131;" data-placement="top" data-container="body" data-toggle="tooltip">('+negativeResponderPerc+' %)</span></td>';
													}else{
														str+='<td title="Respondents"> - </td>';
													}
													
											}
											str+='</tr>';
											
											
										str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				}
			}
			str+='</div>';
			
			
			
		
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<button type="button" class="btn btn-success btn-sm timeSeriesWiseSummaryCls pull-right">Time Series Wise Summary</button>';
			str+='</div>';
		str+='</div>';
		
			var globalStatushamletVoterInfo1={"Completely Satisfied":"#6dd18b","Partially Satisfied":"#e59866","Not Satisfied":"#f35555"}
			
		str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<div class="table-responsive">';
				str+='<table class="table table_custom_jalavani_status table-bordered" id="dataTabledefaulters" style="width:100%">';
					str+='<thead>';
						str+='<tr>';
						str+='<th rowspan="3">LOCATION</th>';
						for(var i in result[0].feedbackStatusList){
							if(result[0].feedbackStatusList[i].id==1){
								str+='<th style="background-color:#6dd18b" rowspan="3">Caller Feedback '+result[0].feedbackStatusList[i].name+'</th>';
								str+='<th style="background-color:#6dd18b" rowspan="3">Perc(%)</th>';
								str+='<th style="background-color:#6dd18b" colspan="4" >Ivr '+result[0].feedbackStatusList[i].name+'</th>';
							} else if(result[0].feedbackStatusList[i].id==2){
								str+='<th style="background-color:#f35555" rowspan="3">Caller Feedback '+result[0].feedbackStatusList[i].name+'</th>';
								str+='<th style="background-color:#f35555" rowspan="3">Perc(%)</th>';
								//str+='<th style="background-color:#f35555" colspan="4" >'+result[0].feedbackStatusList[i].name+'</th>';
							}else if(result[0].feedbackStatusList[i].id==3){
								str+='<th style="background-color:#e59866" rowspan="3">Caller Feedback '+result[0].feedbackStatusList[i].name+'</th>';
								str+='<th style="background-color:#e59866" rowspan="3">Perc(%)</th>';
								str+='<th style="background-color:#e59866" colspan="4" >Ivr '+result[0].feedbackStatusList[i].name+'</th>';
							
							}
							
						}
						str+='</tr>';
						str+='<tr>';
							for(var i in result[0].feedbackStatusList){
								if(result[0].feedbackStatusList[i].id!=2){
									for(var j in result[0].feedbackStatusList[i].hamletVoterInfo){
										str+='<th colspan="2" style="background-color:'+globalStatushamletVoterInfo1[result[0].feedbackStatusList[i].name]+';text-transform:uppercase;">'+result[0].feedbackStatusList[i].hamletVoterInfo[j].name+'</th>';
									}
								}								
							}
						str+='</tr>';
							str+='<tr>';
							for(var i in result[0].feedbackStatusList){
								if(result[0].feedbackStatusList[i].id !=2){
									for(var j in result[0].feedbackStatusList[i].hamletVoterInfo){
										str+='<th style="background-color:'+globalStatushamletVoterInfo1[result[0].feedbackStatusList[i].name]+';text-transform:uppercase;">+ve</th>';
										str+='<th style="background-color:'+globalStatushamletVoterInfo1[result[0].feedbackStatusList[i].name]+';text-transform:uppercase;">-ve</th>';
										
									}
								}
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					
					
						for(var i in result){
							str+='<tr>';
								
								str+='<td  style="text-align:left !important;">'+result[i].name+'</td>';
								for(var j in result[i].feedbackStatusList){
									if(result[i].feedbackStatusList[j].id==1){
										if(result[i].feedbackStatusList[j].count !=null && result[i].feedbackStatusList[j].count>0){
											str+='<td class="feedbackStatusCls" attr_location_district_id="'+result[i].id+'" attr_location_id="ivrStatus" attr_statusid="'+result[i].feedbackStatusList[j].id+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_alertCount="'+result[i].feedbackStatusList[j].count+'" style="cursor:pointer;background-color:#6dd18b">'+result[i].feedbackStatusList[j].count+'</td>';
										}else{
											str+='<td style="background-color:#6dd18b">'+result[i].feedbackStatusList[j].count+'</td>';
										}
									} else if(result[i].feedbackStatusList[j].id==2){
										if(result[i].feedbackStatusList[j].count !=null && result[i].feedbackStatusList[j].count>0){
											str+='<td class="feedbackStatusCls" attr_location_district_id="'+result[i].id+'" attr_location_id="ivrStatus" attr_statusid="'+result[i].feedbackStatusList[j].id+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_alertCount="'+result[i].feedbackStatusList[j].count+'" style="cursor:pointer;background-color:#f35555">'+result[i].feedbackStatusList[j].count+'</td>';
										}else{
											str+='<td style="background-color:#f35555">'+result[i].feedbackStatusList[j].count+'</td>';
										}
									} else if(result[i].feedbackStatusList[j].id==3){
										if(result[i].feedbackStatusList[j].count !=null && result[i].feedbackStatusList[j].count >0){
											str+='<td class="feedbackStatusCls" attr_location_district_id="'+result[i].id+'" attr_location_id="ivrStatus" attr_statusid="'+result[i].feedbackStatusList[j].id+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_alertCount="'+result[i].feedbackStatusList[j].count+'" style="cursor:pointer;background-color:#e59866">'+result[i].feedbackStatusList[j].count+'</td>';
										}else{
											str+='<td style="background-color:#e59866">'+result[i].feedbackStatusList[j].count+'</td>';
										}
									}
									
									
									if(result[i].feedbackStatusList[j].id==1){
										str+='<td style="background-color:#6dd18b">'+result[i].feedbackStatusList[j].satisfiedPerc+'</td>';
									} else if(result[i].feedbackStatusList[j].id==2){
										str+='<td style="background-color:#f35555">'+result[i].feedbackStatusList[j].notSatisfiedPerc+'</td>';
									} else if(result[i].feedbackStatusList[j].id==3){
										str+='<td style="background-color:#e59866">'+result[i].feedbackStatusList[j].partiallySatsifyPerc+'</td>';
									}
									if(result[i].feedbackStatusList[j].hamletVoterInfo !=null && result[i].feedbackStatusList[j].hamletVoterInfo.length>0){
										for(var k in result[i].feedbackStatusList[j].hamletVoterInfo){
											if(result[i].feedbackStatusList[j].id==1){
												if(result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount !=null && result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount>0){
													str+='<td class="statusWiseIvrCls"  attr_status_id="1" attr_probTypeId="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].id
													+'" attr_alert_Count ="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_district_id="'+result[i].id+'" attr_satisfied_status="Y" style="background-color:#6dd18b;cursor:pointer;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'</td>';
												}else{
													str+='<td style="background-color:#6dd18b;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'</td>';
												}
												if(result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount !=null && result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount>0){
													str+='<td class="statusWiseIvrCls"  attr_status_id="1" attr_probTypeId="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].id+'" attr_alert_Count ="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_district_id="'+result[i].id+'" attr_satisfied_status="N"  style="background-color:#6dd18b;cursor:pointer;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'</td>';
												}else{
													str+='<td style="background-color:#6dd18b;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'</td>';
												}
											}/* else if(result[i].feedbackStatusList[j].id==2){
												str+='<td style="background-color:#f35555">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'</td>';
												str+='<td style="background-color:#f35555">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'</td>';
											} */else if(result[i].feedbackStatusList[j].id==3){
												if(result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount !=null && result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount>0){
													str+='<td class="statusWiseIvrCls"  attr_status_id="3" attr_probTypeId="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].id+'" attr_alert_Count ="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'"  attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_district_id="'+result[i].id+'" attr_satisfied_status="Y"  style="background-color:#e59866;cursor:pointer;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'</td>';
												}else{
													str+='<td style="background-color:#e59866;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].postiveCount+'</td>';
												}
												if(result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount !=null && result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount>0){
													str+='<td class="statusWiseIvrCls"  attr_status_id="3" attr_probTypeId="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].id+'" attr_alert_Count ="'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'" attr_statusName="'+result[i].feedbackStatusList[j].name+'" attr_district_id="'+result[i].id+'" attr_satisfied_status="N"  style="background-color:#e59866;cursor:pointer;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'</td>';
												}else{
													str+='<td style="background-color:#e59866;">'+result[i].feedbackStatusList[j].hamletVoterInfo[k].negativeCount+'</td>';
												}
											}
										}
									}else{
										if(result[i].feedbackStatusList[j].id==1){
											str+='<td style="background-color:#6dd18b"> - </td>';
											str+='<td style="background-color:#6dd18b"> - </td>';
											str+='<td style="background-color:#6dd18b"> - </td>';
											str+='<td style="background-color:#6dd18b"> - </td>';
										}/* else if(result[i].feedbackStatusList[j].id==2){
											str+='<td style="background-color:#f35555"> - </td>';
											str+='<td style="background-color:#f35555"> - </td>';
											str+='<td style="background-color:#f35555"> - </td>';
											str+='<td style="background-color:#f35555"> - </td>';
										} */else if(result[i].feedbackStatusList[j].id==3){
											str+='<td style="background-color:#e59866"> - </td>';
											str+='<td style="background-color:#e59866"> - </td>';
											str+='<td style="background-color:#e59866"> - </td>';
											str+='<td style="background-color:#e59866"> - </td>';
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
		$("#ivrStatusFeedBackDivId").html(str);
		$('.tooltipClsStatusCls').tooltip({"html":"true"})
		$("#dataTabledefaulters").dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 13,
			 "aaSorting": [[ 3, "desc" ]], 
			"aLengthMenu": [[13, 15, 20, -1], [13, 15, 20, "All"]]
	  });
	$('[data-toggle="tooltip"]').tooltip(); 
}
$(document).on("click",".feedbackStatusCls",function(){
	var locationId = $(this).attr("attr_location_district_id");
	var feedaBackstatusId = $(this).attr("attr_statusid");
	var statusName = $(this).attr("attr_statusName");
	var alertCount = $(this).attr("attr_alertCount");
	
	$("#alertManagementPopupBody").html('')
	
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
	getJalavaniFeedBackNotSatisifiedAlertsInfo(feedaBackstatusId,locationId,statusName,alertCount);
});

function getJalavaniFeedBackNotSatisifiedAlertsInfo(feedBackStatusId,districtId,statusName,alertCount){
	$("#modalHeadingTotal").html("");
	$.ajax({
		url: wurl+"/WebService/getJalavaniFeedBackNotSatisifiedAlertsInfo/"+currentFromDate+"/"+currentToDate+"/"+feedBackStatusId+"/"+districtId
		//url: "http://192.168.11.176:8080/PartyAnalyst/WebService/getJalavaniFeedBackNotSatisifiedAlertsInfo/"+currentFromDate+"/"+currentToDate+"/"+feedBackStatusId+"/"+districtId
	}).then(function(result){
		 if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,alertCount,"");
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});	
}

function getJalavaniStatusWiseSummaryGraphDetailsInfo(divId){
	$('#timeSeriesWisGraphDivId').html(spinner);
	$.ajax({
		url: wurl+"/WebService/getJalavaniStatusWiseSummaryGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniStatusWiseSummaryGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
	}).then(function(result){
		 if(result != null && result.length > 0){
			buildJalavaniStatusWiseSummaryGraphDetailsInfo(result,divId);
		}else{
			$("#"+divId).html('<img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;margin-top: 100px">')
		}
	});	
}
$(document).on("click",".timeSeriesWiseSummaryCls",function(){
	$("#timeSeriesWiseModalId").modal("show");
	getJalavaniStatusWiseSummaryGraphDetailsInfo("timeSeriesWisGraphDivId");
	getJalavaniIvrWiseSummaryGraphDetailsInfo("timeSeriesIVRGraphDivId");
	getJalavaniIvrRespondantsGraphDetailsInfo("timeSeriesIVRRespondantGraphDivId");
	
});

function buildJalavaniStatusWiseSummaryGraphDetailsInfo(result,divId){
	var categoryDateArr=[];
	var compleSatisfyArr=[];
	var partiallySatisfyArr=[];
	var notSatisfyArr=[];
	if(result !=null && result.length>0){
		for(var i in result){
			if(result[i].count !=null && result[i].count>0){
				categoryDateArr.push(result[i].date);
				if(result[i].feedbackStatusList !=null && result[i].feedbackStatusList.length>0){
					for(var j in result[i].feedbackStatusList){
						if(result[i].feedbackStatusList[j].id==1){
							compleSatisfyArr.push({y:result[i].feedbackStatusList[j].count,"extra":result[i].feedbackStatusList[j].satisfiedPerc})
						}else if(result[i].feedbackStatusList[j].id==2){
							notSatisfyArr.push({y:result[i].feedbackStatusList[j].count,"extra":result[i].feedbackStatusList[j].notSatisfiedPerc})
						}else if(result[i].feedbackStatusList[j].id==3){
							partiallySatisfyArr.push({y:result[i].feedbackStatusList[j].count,"extra":result[i].feedbackStatusList[j].partiallySatsifyPerc})
						}
						
						
					}
				}
			}
				
		}
	}
	
	$('#'+divId).highcharts({
		chart: {
			type: 'spline'
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories:categoryDateArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
			labels: {
				formatter: function () {
					return this.value + '';
				}
			}
		},
		tooltip: {
			formatter: function () {
			var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					if(this.series.name != "Series 1")  
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
					this.y+" - "+(this.point.extra)+"%";
				});

				return s;
			},
			shared: true
		},
		plotOptions: {
			
		},
		series: [{
			name: 'COMPLETLY SATISIFIED',
			data: compleSatisfyArr,
			color:'#6dd18b'
		}, {
			name: 'PARTIALLY SATISIFIED',
			data: partiallySatisfyArr,
			color:'#e59866'
		}, {
			name: 'NOT SATISIFIED',
			data: notSatisfyArr,
			color:'#f35555'
		}]
	});
}
$(document).on("click",".closedReopenAlertsCls",function(){
	var statusId = $(this).attr("attr_statusId");
	var statusName = $(this).attr("attr_statusName");
	var categoryId=0;
	 var blockType = ''; 
	$("[role='tablist'] li").each(function(i, obj){
		 if($(this).hasClass('active_li')){
			blockType = $(this).attr("attr_type");
		 }
	});
	if(blockType == "All"){
		categoryId=0;
	}else if(blockType == "print"){
		categoryId=2;
	}else if(blockType == "electronic"){
		categoryId=3;
	}else if(blockType == "callcenter"){
		categoryId=4;
	}else if(blockType == "social"){
		categoryId=5;
	}
	getJalavaniAlertForClosedAndReopenDetails(statusId,statusName,categoryId);
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
});
function getJalavaniAlertForClosedAndReopenDetails(statusId,statusName,categoryId){
	//$('#timeSeriesWisGraphDivId').html(spinner);
	$("#modalHeadingTotal").html("");
	$.ajax({
		url: wurl+"/WebService/getJalavaniAlertForClosedAndReopenDetails/"+currentFromDate+"/"+currentToDate+"/"+statusId+"/"+globalDepartmentId+"/"+categoryId
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniAlertForClosedAndReopenDetails/"+currentFromDate+"/"+currentToDate+"/"+statusId+"/"+globalDepartmentId+"/"+categoryId
	}).then(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,"","closedAndReopend");
		}else{
			$("#alertManagementPopupBody").html('<img src="Assests/images/no_data_available.png" style="display:block ;margin-left: auto;margin-right: auto;">')
		}
	});	
}
function getJalavaniIvrWiseSummaryGraphDetailsInfo(divId){
	$('#timeSeriesIVRGraphDivId').html(spinner);
	$.ajax({
		url: wurl+"/WebService/getJalavaniIvrWiseSummaryGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniIvrWiseSummaryGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
	}).then(function(result){
		if(result != null && result.length > 0){
			//buildJalavaniStatusWiseSummaryGraphDetailsInfo(result,divId);
			buildJalavaniIveGraph(result,divId);
		}else{
			$("#"+divId).html('NO DATA AVAILABLE')
		}
	});	
}
function getJalavaniIvrRespondantsGraphDetailsInfo(divId){
	$('#timeSeriesIVRRespondantGraphDivId').html(spinner);
	$.ajax({
		url: wurl+"/WebService/getJalavaniIvrRespondantsGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniIvrRespondantsGraphDetailsInfo/"+currentFromDate+"/"+currentToDate
	}).then(function(result){
		if(result != null && result.length > 0){
			buildJalavaniIvrRespondantGraph(result,divId);
		}else{
			$("#"+divId).html('NO DATA AVAILABLE')
		}
	});	
}

/* function buildJalavaniIveGraph(result,divId){
	var categoriesArr=[],wpPosArr=[],wpNegArr=[],tpPosArr=[],tpNegArr=[];
	
	if(result != null && result.length > 0){
		for(var i in result){
			categoriesArr.push(result[i].date);
			if(result[i].hamletVoterInfo != null && result[i].hamletVoterInfo.length > 0){
				for(var j in result[i].hamletVoterInfo){
					if(result[i].hamletVoterInfo[j].id == 1){
						wpPosArr.push({y:result[i].hamletVoterInfo[j].postiveCount,"extra":result[i].hamletVoterInfo[j].positivePerc});
						wpNegArr.push({y:result[i].hamletVoterInfo[j].negativeCount,"extra":result[i].hamletVoterInfo[j].negativePerc});
					}else if(result[i].hamletVoterInfo[j].id == 2){
						tpPosArr.push({y:result[i].hamletVoterInfo[j].postiveCount,"extra":result[i].hamletVoterInfo[j].positivePerc});
						tpNegArr.push({y:result[i].hamletVoterInfo[j].negativeCount,"extra":result[i].hamletVoterInfo[j].negativePerc});
					}
				}
			}
		}
	}
	
	$('#'+divId).highcharts({
		colors:["green","red","green","red"],
		chart: {
			type: 'column'
		},

		title: {
			text: ''
		},

		xAxis: {
			categories: categoriesArr
		},

		yAxis: {
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}
		},

		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b><br/>' +
					'Total: ' + this.point.stackTotal+'<br/>'+
					this.series.name + ': ' + this.y+"-"+this.point.extra+"%";
					
			}
		},

		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},

		series: [{
			name: 'Water Prob - Satisfied',
			data: wpPosArr,
			stack: 'Water Problem'
		}, {
			name: 'Water Prob - Not Satisfied',
			data: wpNegArr,
			stack: 'Water Problem'
		}, {
			name: 'Tanker Prob - Satisfied',
			data: tpPosArr,
			stack: 'Tankers Problem'
		}, {
			name: 'Tanker Prob - Not Satisfied',
			data: tpNegArr,
			stack: 'Tankers Problem'
		}]
	});
} */
function buildJalavaniIveGraph(result,divId){
	var categoryDateArr=[],wpPosArr=[],wpNegArr=[],tpPosArr=[],tpNegArr=[];
	
	if(result != null && result.length > 0){
		for(var i in result){
			categoryDateArr.push(result[i].date);
			if(result[i].hamletVoterInfo != null && result[i].hamletVoterInfo.length > 0){
				for(var j in result[i].hamletVoterInfo){
					if(result[i].hamletVoterInfo[j].id == 1){
						wpPosArr.push({y:result[i].hamletVoterInfo[j].postiveCount,"extra":result[i].hamletVoterInfo[j].positivePerc});
						wpNegArr.push({y:result[i].hamletVoterInfo[j].negativeCount,"extra":result[i].hamletVoterInfo[j].negativePerc});
					}else if(result[i].hamletVoterInfo[j].id == 2){
						tpPosArr.push({y:result[i].hamletVoterInfo[j].postiveCount,"extra":result[i].hamletVoterInfo[j].positivePerc});
						tpNegArr.push({y:result[i].hamletVoterInfo[j].negativeCount,"extra":result[i].hamletVoterInfo[j].negativePerc});
					}
				}
			}
		}
	}
	$('#'+divId).highcharts({
		chart: {
			type: 'spline'
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories:categoryDateArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
			labels: {
				formatter: function () {
					return this.value + '';
				}
			}
		},
		tooltip: {
			formatter: function () {
			var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					if(this.series.name != "Series 1")  
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
					this.y+" - "+(this.point.extra)+"%";
				});

				return s;
			},
			shared: true
		},
		plotOptions: {
			
		},
		series: [{
			name: 'WATER PROB-SATISIFIED',
			data: wpPosArr,
			color:'#0FBE08'
		}, {
			name: 'WATER PROB-NOT SATISIFIED',
			data: wpNegArr,
			color:'#FE3131'
		}, {
			name: 'TANKER PROB-SATISIFIED',
			data: tpPosArr,
			color:'#0FBE08'
		}, {
			name: 'TANKER PROB-NOT SATISIFIED',
			data: tpNegArr,
			color:'#FE3131'
		}]
	});
	/* $('#'+divId).highcharts({
		colors:["green","red","green","red"],
		chart: {
			type: 'column'
		},

		title: {
			text: ''
		},

		xAxis: {
			categories: categoriesArr
		},

		yAxis: {
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}
		},

		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b><br/>' +
					'Total: ' + this.point.stackTotal+'<br/>'+
					this.series.name + ': ' + this.y+"-"+this.point.extra+"%";
					
			}
		},

		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},

		series: [{
			name: 'Water Prob - Satisfied',
			data: wpPosArr,
			stack: 'Water Problem'
		}, {
			name: 'Water Prob - Not Satisfied',
			data: wpNegArr,
			stack: 'Water Problem'
		}, {
			name: 'Tanker Prob - Satisfied',
			data: tpPosArr,
			stack: 'Tankers Problem'
		}, {
			name: 'Tanker Prob - Not Satisfied',
			data: tpNegArr,
			stack: 'Tankers Problem'
		}]
	}); */
}
function buildJalavaniIvrRespondantGraph(result,divId){
	var categoryDateArr=[],satisfiedArr=[],notSatisfiedArr=[];
	
	if(result != null && result.length > 0){
		for(var i in result){
			categoryDateArr.push(result[i].date);
			satisfiedArr.push({y:result[i].positivePerc,"extra":result[i].postiveCount});
			notSatisfiedArr.push({y:result[i].negativePerc,"extra":result[i].negativeCount});	
		}
	}
	$('#'+divId).highcharts({
		chart: {
			type: 'spline'
		},
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories:categoryDateArr
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			title: {
				text: ''
			},
			labels: {
				formatter: function () {
					return this.value + '%';
				}
			}
		},
		tooltip: {
			formatter: function () {
			var s = '<b>' + this.x + '</b>';

				$.each(this.points, function () {
					if(this.series.name != "Series 1")  
					s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
					this.y+" % - "+(this.point.extra)+"";
				});

				return s;
			},
			shared: true
		},
		plotOptions: {
			
		},
		series: [{
			name: 'SATISIFIED',
			data: satisfiedArr,
			color:'#0FBE08'
		}, {
			name: 'NOT-SATISIFIED',
			data: notSatisfiedArr,
			color:'#FE3131'
		}]
	});
}
$(document).on("click",".menu-cls2",function(e){
	e.stopPropagation();
	$(".menuCls-table2").toggle();
	$(".menu-data-cls").hide();
});
$(document).on("click",".statusWiseIvrCls",function(){
	var statusId = $(this).attr("attr_status_id");
	var probTypeId = $(this).attr("attr_probTypeId");
	var statusName = $(this).attr("attr_statusName");
	var districtId =$(this).attr("attr_district_id");
	var satisfiedStatus =$(this).attr("attr_satisfied_status");
	var alertCount =$(this).attr("attr_alert_Count");
	getJalavaniIvrSummaryWiseClick(statusId,probTypeId,statusName,districtId,satisfiedStatus,alertCount);
	$("#alertManagementPopup").modal({
		show: true,
		keyboard: false,
		backdrop: 'static'
	});
	$("#alertManagementPopupBody").html(spinner);
});

function getJalavaniIvrSummaryWiseClick(statusId,probTypeId,statusName,districtId,satisfiedStatus,alertCount){
	$("#modalHeadingTotal").html("");
	$.ajax({
		url: wurl+"/WebService/getJalavaniIvrSummaryWiseClick/"+currentFromDate+"/"+currentToDate+"/"+statusId+"/"+probTypeId+"/"+districtId+"/"+satisfiedStatus
		//url: "http://192.168.11.173:8080/PartyAnalyst/WebService/getJalavaniIvrSummaryWiseClick/"+currentFromDate+"/"+currentToDate+"/"+statusId+"/"+probTypeId+"/"+districtId+"/"+satisfiedStatus
	}).then(function(result){
		if(result != null && result.length > 0){
			buildAlertDtlsBasedOnStatusClick(result,statusName,alertCount,"");
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});	
}
$(document).on("change","#departmentId",function(e){
	globalDepartmentId = $(this).val();
	$("#alertTypeId").val(0);
	$("#alertTypeId").trigger("chosen:updated")
	onloadCalls("change");
});
$(document).on("click",".linkedArticlesClickId",function(){	 
	var temp=$(this).attr('src');
	$(this).attr('src',$(".mainImage").attr('src'));
	$(".mainImage").attr('src',temp);
});