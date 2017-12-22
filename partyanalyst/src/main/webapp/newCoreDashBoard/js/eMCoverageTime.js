var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

var currentFromDate = moment().format("DD-MM-YYYY");
var currentToDate = moment().format("DD-MM-YYYY");
$(".chosen-select").chosen();
$("#EMCoverageTimeHeadDate").html("Today("+currentFromDate+")"); 
	
$("#dateRangeEMCoverageTimeId").daterangepicker({
	opens: 'left',
	startDate:currentFromDate,
	endDate: currentToDate,
	locale: {
		format: 'DD-MM-YYYY'
	},
	ranges: {
		'Today': [moment(), moment()],
	   'This Month': [moment().startOf("month").format("DD-MM-YYYY"), moment().endOf('month').format("DD-MM-YYYY")],
	   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY"),moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Year': [moment().startOf('Year'), moment()]
	}
});

$('#dateRangeEMCoverageTimeId').on('apply.daterangepicker', function(ev, picker) {
	currentFromDate = picker.startDate.format('DD-MM-YYYY');
	currentToDate = picker.endDate.format('DD-MM-YYYY');
	$("#EMCoverageTimeHeadDate").html("("+picker.startDate.format("DD/MM/YY")+" to "+picker.endDate.format("DD/MM/YY")+")");
	onLoadEmCoverageTimeCalls();
	if($(".EMCoverageTimeIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		$(".dayWiseTimeBlock").show();
		var categoryId = $("#categoryEmId").val();
		var type = '';
		$(".EMCoverageTimeCls").each(function(){
			if (this.checked) {
				type = $(this).val();
			}
		});
		getDayWiseCandidateCoverageTime(type,categoryId,""); // Main Block right expand
	}
	
});

function onLoadEmCoverageTimeCalls(){
	getCandidateAndPartyWiseNewsChannals("candidate",0,"N"); //Main Block 
}
$(document).on("click",".EMCoverageTimeCls",function(){
	var type = $(this).val();
	var categoryId = $("#categoryEmId").val();
	getCandidateAndPartyWiseNewsChannals(type,categoryId,"");
	getDayWiseCandidateCoverageTime(type,categoryId,"N"); 
});
$(document).on("click",".EMCoverageTimeIconExpand",function(){
	var type ='';
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
	var categoryId = $("#categoryEmId").val();
	if($(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		$(".dayWiseTimeBlock").show();
		getDayWiseCandidateCoverageTime(type,categoryId,"N"); // Main Block right expand
	}else{
		$(".dayWiseTimeBlock").hide();
	}
});
$(document).on("change","#categoryEmId",function(){
	var categoryId = $(this).val();
	var type='';
		$(".EMCoverageTimeCls").each(function(){
			if (this.checked) {
				type = $(this).val();
			}
		});
		
	getCandidateAndPartyWiseNewsChannals(type,categoryId,"N");
	getDayWiseCandidateCoverageTime(type,categoryId,"N"); 
});
function getCandidateAndPartyWiseNewsChannals(type,categoryId,isParticipated){
	$("#EMCoverageTimeSummaryDivId").html(spinner);
	var type=type;
	var categoryId = categoryId;
	
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type+"/"+isParticipated
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type+"/"+isParticipated
	}).then(function(result){
		if(result != null && result.length > 0){
			getCandidateAndPartyWiseNewsChannelsBuilding(result);
		}else{
			$("#EMCoverageTimeSummaryDivId").html("No Data Available");
		}
	});
}
function getCandidateAndPartyWiseNewsChannelsBuilding(result){
	var str="";
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered" id="dataTableCanAndPartyWiseNewsChannel" style="width:100%">';
		str+='<thead>';
			str+='<tr>';
				if(result[0].coreDashBoardVOList != null && result[0].coreDashBoardVOList.length > 0){
					str+='<td rowspan="2">Channel</td>';
					for(var i in result[0].coreDashBoardVOList){
						str+='<td colspan="4">'+result[0].coreDashBoardVOList[i].organization+'</td>';
					}
				}
			str+='</tr>';
			str+='<tr>';
				if(result[0].coreDashBoardVOList != null && result[0].coreDashBoardVOList.length > 0){
					for(var i in result[0].coreDashBoardVOList){
						str+='<td>+ve</td>';
						str+='<td class="text-success">%</td>';
						str+='<td>-ve</td>';
						str+='<td class="text-danger">%</td>';
					}
				}
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].organization+'</td>';
					if(result[i].coreDashBoardVOList != null && result[i].coreDashBoardVOList.length > 0){
						for(var j in result[i].coreDashBoardVOList){
							str+='<td>'+result[i].coreDashBoardVOList[j].positiveCountMain+'</td>';
							str+='<td class="text-success">'+result[i].coreDashBoardVOList[j].positivePerc+'</td>';
							str+='<td>'+result[i].coreDashBoardVOList[j].negativCountMain+'</td>';
							str+='<td class="text-danger">'+result[i].coreDashBoardVOList[j].negativePerc+'</td>';
						}
					}
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#EMCoverageTimeSummaryDivId").html(str);
	$("#dataTableCanAndPartyWiseNewsChannel").dataTable();
}

function getDayWiseCandidateCoverageTime(type,categoryId,isParticipated){
	$("#EMCoverageTimeDayWiseDivId").html(spinner);
	var type=type;
	var categoryId = categoryId;
	
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+categoryId+"/"+type+"/"+currentFromDate+"/"+currentToDate+"/"+isParticipated
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+categoryId+"/"+type+"/"+currentFromDate+"/"+currentToDate+"/"+isParticipated
	}).then(function(result){
		if(result != null && result.length > 0){
			buildDayWiseCandidateCoverageTime(result);
		}else{
			$("#EMCoverageTimeDayWiseDivId").html("No Data Available");
		}
	});
}
function buildDayWiseCandidateCoverageTime(result){
	var str='';
	for(var i in result){
		str+='<div style="padding:10px;border:1px solid #ddd;margin-top:10px;">';
			str+='<div class="row">';
			str+='<div class="col-sm-12">';
			str+='<div class="col-sm-1" style="margin-top: 30px;">';
				str+='<p>'+result[i].organization+'</p>';
			str+='</div>';
			str+='<div class="col-sm-11">';
				str+='<ul class="partyWiseSlickApplyEm">';
					for(var j in result[i].coreDashBoardVOList){
						str+='<li><div id="partywisegraphEm'+i+''+j+'"  style="height:200px;width:350px"></div></li>';
					}
				str+='</ul>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	$("#EMCoverageTimeDayWiseDivId").html(str);
	if(result !=null && result.length >0){
				for(var i in result){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						var channelName='';
						for(var j in result[i].coreDashBoardVOList){
								channelName = result[i].coreDashBoardVOList[j].organization;
								var dateNames=[];
								var positivePercArray=[];
								var negativePercArray=[];
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								
								for(var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									dateNames.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].organization)
									positivePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].positiveCountMain)
									negativePercArray.push(result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain)
								}
							}
							
							$('#partywisegraphEm'+i+''+j+'').highcharts({
								 colors: ['#64C664','#D33E39'],
								chart: {
									type: 'column'
								},
								title: {
									text: channelName
								},
							   
								xAxis: {
									 min: 0,
										 gridLineWidth: 0,
										 minorGridLineWidth: 0,
										 categories: dateNames,
									labels: {
											rotation: -45,
											style: {
												fontSize: '13px',
												fontFamily: 'Verdana, sans-serif'
											},
										}
								},
								yAxis: {
									min: 0,
										   gridLineWidth: 0,
											minorGridLineWidth: 0,
									title: {
										text: ''
									}
								},
								tooltip: {
									formatter: function () {
										var s = '<b>' + this.x + '</b>';

										$.each(this.points, function () {
											s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> : ' +
												Highcharts.numberFormat(this.percentage,1)+'%' +' - ' +
												(this.y);
										});

										return s;
									},
									shared: true
								},
								legend: {
														
										enabled: false,				
														
									},				
								plotOptions: {
									column: {
										stacking: 'percent',
										dataLabels:{
											enabled: true,
											formatter: function() {
												if (this.y === 0) {
													return null;
												} else {
													return Highcharts.numberFormat(this.percentage,0) + '%';
												}
											}
										},
										
									},
								},
								series: [{
									name: 'Positive',
									data: positivePercArray
								}, {
									name: 'Negative',
									data: negativePercArray
								}]
							});
								
							
							
						}
						
					}
					
					
					
				}
			}
				
			else{
				$("#EMCoverageTimeDayWiseDivId").html("<div class='col-md-12 col-xs-12 col-sm-12'>No Data Available</div>")
			}
			
			$(".partyWiseSlickApplyEm").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
			 variableWidth: true,
			 responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 3
				  }
				},
				{
				  breakpoint: 768,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				// You can unslick at a given breakpoint now by adding:
				// settings: "unslick"
				// instead of a settings object
			  ]
		});
}
function refreshEm(){
	var categoryId = $("#categoryEmId").val();
	var type='';
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
	if($(".EMCoverageTimeIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		$(".dayWiseTimeBlock").show();
		getDayWiseCandidateCoverageTime(type,categoryId,"N"); // Main Block right expand
	}
	getCandidateAndPartyWiseNewsChannals(type,categoryId,"N");
}
$('#participatedCheckBoxId').change(function(){
	var categoryId = $("#categoryEmId").val();
	var type='';
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
        if(this.checked) {
			getCandidateAndPartyWiseNewsChannals(type,0,"y");
            getDayWiseCandidateCoverageTime(type,categoryId,"Y");
        }else{
			getCandidateAndPartyWiseNewsChannals(type,0,"N");
            getDayWiseCandidateCoverageTime(type,categoryId,"N");
		}
 });
   