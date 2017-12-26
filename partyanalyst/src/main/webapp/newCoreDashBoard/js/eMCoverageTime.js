var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));

var currentFromDate = moment().format("DD-MM-YYYY");
var currentToDate = moment().format("DD-MM-YYYY");
$(".chosen-select").chosen();
$(document).on("click",".EMCoverageSettingExpand",function(e){
	e.stopPropagation();
	$(this).closest("[expand-block-heading1='EMCoverageTime']").find(".eMCoverageTimeSettingsBody").show();
});
$(document).on("click",".eMCoverageTimeSettingsBody",function(e){
	e.stopPropagation();
});
$(document).on("click",function(){
	$(".eMCoverageTimeSettingsBody").hide();
});
$(document).on("click",".eMCoverageTimeSettingsCloseBody",function(){
	$(this).closest(".eMCoverageTimeSettingsBody").hide();
});
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
	
	
});

function onLoadEmCoverageTimeCalls(){
	var type;
	var checkedVal;
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
	$(".participatedCheckCls").each(function(){
		if (this.checked) {
			checkedVal = "Y";
		}else{
			checkedVal = "N";
		}
	});
	var categoryId = $("#categoryEmId").val();
	getCandidateAndPartyWiseNewsChannals(type,categoryId,checkedVal); //Main Block 
	if($(".EMCoverageTimeBlocksIcon").hasClass("expandBlockEm" )){
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal);
	}
	
}
$(document).on("click",".EMCoverageTimeCls",function(){
	var type = $(this).val();
	var categoryId = $("#categoryEmId").val();
	var checkedVal;
	if(type == "party"){
		$("#participatedCheckDivId").hide();
	}else if(type == "candidate"){
		$("#participatedCheckDivId").show();
	}
	$(".participatedCheckCls").each(function(){
		if (this.checked) {
			checkedVal = "Y";
		}else{
			checkedVal = "N";
		}
	});
	getCandidateAndPartyWiseNewsChannals(type,categoryId,checkedVal);
	if($(".EMCoverageTimeBlocksIcon").hasClass("expandBlockEm" )){
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal);
	}
	
});
$(document).on("click",".EMCoverageTimeIconExpand",function(){
	var type ='';
	var checkedVal;
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
	$(".participatedCheckCls").each(function(){
		if (this.checked) {
			checkedVal = "Y";
		}else{
			checkedVal = "N";
		}
	});
	var categoryId = $("#categoryEmId").val();
	getCandidateAndPartyWiseNewsChannals(type,categoryId,checkedVal);
	if($(".EMCoverageTimeBlocksIcon").hasClass("expandBlockEm" )){
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal);
	}
	
});
$(document).on("change","#categoryEmId",function(){
	var checkedVal;
	var categoryId = $(this).val();
	var type='';
		$(".EMCoverageTimeCls").each(function(){
			if (this.checked) {
				type = $(this).val();
			}
		});
	$(".participatedCheckCls").each(function(){
		if (this.checked) {
			checkedVal = "Y";
		}else{
			checkedVal = "N";
		}
	});
	getCandidateAndPartyWiseNewsChannals(type,categoryId,checkedVal);
	if($(".EMCoverageTimeBlocksIcon").hasClass("expandBlockEm" )){
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal);
	}
	
});
function getCandidateAndPartyWiseNewsChannals(type,categoryId,isParticipated){
	$("#EMCoverageTimeSummaryDivId").html(spinner);
	var type=type;
	var categoryId = categoryId;
	
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type+"/"+isParticipated
		//url: "http://192.168.11.194:8086/CommunityNewsPortal/webservice/getCandidateAndPartyWiseNewsChannals/"+currentFromDate+"/"+currentToDate+"/"+categoryId+"/"+type+"/"+isParticipated
	}).then(function(result){
		if(result != null && result.length > 0){
			getCandidateAndPartyWiseNewsChannelsBuilding(result,isParticipated);
		}else{
			$("#EMCoverageTimeSummaryDivId").html("No Data Available");
		}
	});
}
function getCandidateAndPartyWiseNewsChannelsBuilding(result,isParticipated){
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
							if(result[i].coreDashBoardVOList[j].positiveCountMain != null && result[i].coreDashBoardVOList[j].positiveCountMain>0){
								str+='<td><a class="emctClickCls" attr_benefit_id="1" attr_candidateId="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_channelId="'+result[i].organizationId+'" attr_participate="'+isParticipated+'" attr_partId="'+result[i].coreDashBoardVOList[j].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].positiveCountMain+'</a></td>';
							
							   str+='<td class="text-success">'+result[i].coreDashBoardVOList[j].positivePerc+'</td>';
							}else{
								str+='<td>'+result[i].coreDashBoardVOList[j].positiveCountMain+'</td>';
							
							str+='<td class="text-success">'+result[i].coreDashBoardVOList[j].positivePerc+'</td>';
							}
							if(result[i].coreDashBoardVOList[j].negativCountMain != null && result[i].coreDashBoardVOList[j].negativCountMain >0){
								str+='<td><a class="emctClickCls" attr_benefit_id="2" attr_candidateId="'+result[i].coreDashBoardVOList[j].organizationId+'" attr_channelId="'+result[i].organizationId+'" attr_participate="'+isParticipated+'" attr_partId="'+result[i].coreDashBoardVOList[j].organizationId+'" style="cursor:pointer;">'+result[i].coreDashBoardVOList[j].negativCountMain+'</a></td>';
							str+='<td class="text-danger">'+result[i].coreDashBoardVOList[j].negativePerc+'</td>';
							}else{
								str+='<td>'+result[i].coreDashBoardVOList[j].negativCountMain+'</td>';
							str+='<td class="text-danger">'+result[i].coreDashBoardVOList[j].negativePerc+'</td>';
							}
						
							
						}
					}
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#EMCoverageTimeSummaryDivId").html(str);
	$("#dataTableCanAndPartyWiseNewsChannel").dataTable({searching: false, paging: false});
}

function getDayWiseCandidateCoverageTime(type,categoryId,isParticipated){
	$("#EMCoverageTimeDayWiseDivId").html(spinner);
	var type=type;
	var categoryId = categoryId;
	
	$.ajax({	
		url: wurl+"/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+categoryId+"/"+type+"/"+currentFromDate+"/"+currentToDate+"/"+isParticipated
		//url: "http://192.168.11.194:8086/CommunityNewsPortal/webservice/getDayWiseCandidateCoverageTime/"+categoryId+"/"+type+"/"+currentFromDate+"/"+currentToDate+"/"+isParticipated
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
	var checkedVal;
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
	$(".participatedCheckCls").each(function(){
		if (this.checked) {
			checkedVal = "Y";
		}else{
			checkedVal = "N";
		}
	});
	if($(".EMCoverageTimeIconExpand").find("i").hasClass("glyphicon glyphicon-resize-small" )){
		$(".dayWiseTimeBlock").show();
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal); // Main Block right expand
	}
	getCandidateAndPartyWiseNewsChannals(type,categoryId,checkedVal);
}
$('#participatedCheckBoxId').change(function(){
	$("#allParticipatedCheckBoxId").prop("checked", false);
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
 $('#allParticipatedCheckBoxId').change(function(){
	 $("#participatedCheckBoxId").prop("checked", false);
	var categoryId = $("#categoryEmId").val();
	var type='';
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
        if(this.checked) {
			getCandidateAndPartyWiseNewsChannals(type,0,"N");
            getDayWiseCandidateCoverageTime(type,categoryId,"N");
        }else{
		}
 });
$(document).on("click",".emctClickCls",function(){
	var categoryId = $("#categoryEmId").val();
	var type='';
	$(".EMCoverageTimeCls").each(function(){
		if (this.checked) {
			type = $(this).val();
		}
	});
		var benefitId = $(this).attr("attr_benefit_id"); 
		var candidateId = $(this).attr("attr_candidateId");
		var channelId = $(this).attr("attr_channelId");
		var participate = $(this).attr("attr_participate");
		var partyId =$(this).attr("attr_partId");
		if(type == "party"){
			candidateId =0;
		}else if(type == "candidate"){
			partyId =0;
		}
		
		window.open('showElectronicBulletinsAction.action?organizationId='+candidateId+'&benefitId='+benefitId+'&categoryId='+categoryId+'&sdat='+currentFromDate+'&edat='+currentToDate+'&npsStr='+channelId+'&type='+type+'&orgType='+participate+'&partyId='+partyId+'&stIdx=0&edIdx=6&callFrom=EMCT');
		
});
$(document).on("click",".EMCoverageTimeBlocksIcon",function(){
		$(this).addClass("expandBlockEm");
		$(".moreEMCoverageTimeBlocksDetailed").show(); 
		var categoryId = $("#categoryEmId").val();
		var type = '';
		var checkedVal;
		$(".EMCoverageTimeCls").each(function(){
			if (this.checked) {
				type = $(this).val();
			}
		});
		$(".participatedCheckCls").each(function(){
			if (this.checked) {
				checkedVal = "Y";
			}else{
				checkedVal = "N";
			}
		});
		getDayWiseCandidateCoverageTime(type,categoryId,checkedVal);
		 
});
$(document).on("click",".expandBlockEm",function(){
		$(this).removeClass("expandBlockEm");
		$(".moreEMCoverageTimeBlocksDetailed").hide(); 
		 
});