var newsChannelsIdsGlbl = [1,2,3];
var impactScopeIds = [1,2,3,4,5,6,7,8,9];
var currentFromDateEmn = moment().format("DD-MM-YYYY");
var currentToDateEmn = moment().format("DD-MM-YYYY");

	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

$(document).on("click",".emnIconExpand",function(){
	$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".electronicMediaBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(".emnHead").toggleClass('col-md-9 col-sm-9').toggleClass('col-md-8 col-sm-8');
	$(".emnHead1").toggleClass('col-md-3 col-sm-3').toggleClass('col-md-4 col-sm-4');
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		//before expand
		$(".dateRangePickerClsForEmn").removeClass("hide");
		$(".moreBlockEMN,.moreEmnBlocksIcon").show();
		getEMMDetailedPartyMediaProgramsOnPartyProgramsWise("top","program");
	}else{
		//after expand	
		$(".dateRangePickerClsForEmn").addClass("hide");
		$(".moreBlockEMN").hide();
	}
});
$(document).on("click",".moreEmnBlocksIcon",function(){
	$(".newEmnHideCls").hide();
	$(".detailedPartyEmn,.selectEmnCate").show();
	getEMMDetailedPartyDistrictWiseProgramsOverview();
	getEMMDetailedPartiesVsChannelsPartiesDistrictWise("party");
	getEMMDetailedPartyStateWiseProgramsOverview();
	$("#detailedPartyLiIdEmn").addClass("active");
});

$(document).on("click","#detailedPartyLiIdEmn",function(){
	$(".newEmnHideCls").hide();
	$(".detailedPartyEmn").show();
	getEMMDetailedPartyStateWiseProgramsOverview();
	getEMMDetailedPartiesVsChannelsPartiesDistrictWise("party");
	getEMMDetailedPartyDistrictWiseProgramsOverview();
});
$(document).on("click","#detailedGovernmentLiIdEmn",function(){
	$(".newEmnHideCls").hide();
	$(".detailedGovtEmn").show();
	getProblemsDetailedOverView();
	getEMMDetailedGovtProblemsDetailedOverview();
});

$(document).on("click",".emnSetIcon",function(e){
	$(this).closest(".electronicMediaBlock").find(".emnBlockDropDown").toggle();
	e.stopPropagation();
});
$(document).on("click",".emnSetClose",function(){
	$(this).closest(".emnBlockDropDown").hide();
});

$(document).on("click","#impactSelectAllIdEmn",function(){
	 if ($(this).prop('checked')) {
		$(".impactCheckClsEmn").prop('checked', true);
	} else {
		$(".impactCheckClsEmn").prop('checked', false);
	}
});
$(document).on("click","#newsChannelSelectAllIdEmn",function(){
	 if ($(this).prop('checked')) {
		$(".newsChannelSelectAllClsEmn").prop('checked', true);
	} else {
		$(".newsChannelSelectAllClsEmn").prop('checked', false);
	}
});

$(document).on("click",".impactCheckClsEmn",function(){
	var checkAll = false;
	$(".impactCheckClsEmn").each(function(){
		if (!$(this).prop('checked')) {
			checkAll = true;
		}
	});
	
	if(checkAll){
		$("#impactSelectAllIdEmn").prop('checked', false);
	}else{
		$("#impactSelectAllIdEmn").prop('checked', true);
	}
	
});
$(document).on("click",".filtersSubmitDivIdEmn",function(){

	newsChannelsIdsGlbl=[];
	$(".newsChannelSelectAllClsEmn").each(function(){
		if($(this).is(":checked")){
			newsChannelsIdsGlbl.push($(this).val());
		}
	});
	
	impactScopeIds = [];
	$(".impactCheckClsEmn").each(function(){
		if($(this).is(":checked")){
			impactScopeIds.push($(this).val());
		}
	});
	
	if(newsChannelsIdsGlbl == null || newsChannelsIdsGlbl.length == 0){
		alert("Please Select Atleast One NewsPaper");
		return;
	}else if(impactScopeIds == null || impactScopeIds.length == 0){
		alert("Please Select Impact Scope");
		return;
	}else{
		console.log("commonBasic")
		//commonNewsBasicCalls();
	}
	
	getMediaProgramsOnParty();
	if($(".emnIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		getEMMDetailedPartyMediaProgramsOnPartyProgramsWise("party");
	}
	var activeUlId = $(".viewsLiClassEmn.active").attr('id')
	$( "#"+activeUlId).trigger("click");
});


//$("#emnHeadDate").html("("currentFromDateEmn "-" currentToDateEmn")");
$("#emnHeadDate").html("Today("+moment().format("DD/MM/YY")+" to "+moment().format("DD/MM/YY")+")");
$("#dateRangeIdForEmn").daterangepicker({
	opens: 'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
	  format: 'DD-MM-YYYY'
	},
	ranges: {
		'Today' : [moment(), moment()],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'This Month': [moment().startOf('month'), moment()],
		'This Year': [moment().startOf('Year'), moment()]
	}
});
$('.dateRangePickerClsForEmn').on('apply.daterangepicker', function(ev, picker) {
	currentFromDateEmn = picker.startDate.format('DD-MM-YYYY');
	currentToDateEmn = picker.endDate.format('DD-MM-YYYY');
	var searchType = $(".emnMediaPrograms .active").attr("attr_searchType");
	var type = $('input[name=emnSearchTypeName]:checked').val();
	getMediaProgramsOnParty();
	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type);
	var activeUlId = $(".viewsLiClassEmn.active").attr('id')
	$( "#"+activeUlId).trigger("click");
});

function getAllTvChannels()
{

  $.ajax({
    //url: wurl+"CommunityNewsPortal/webservice/getAllTvChannels"
    url: "http://localhost:8080/CommunityNewsPortal/webservice/getAllTvChannels"
  }).then(function(result){
	  $("#emnNewsChannelsUlId").html("");
		if(result != null && result.length > 0){
			var str="";
			str+='<li>';
			str+='<label class="checkbox-inline">';
			str+='<input id="newsChannelSelectAllIdEmn" type="checkbox" value="0" class="">';
			str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">Select All</h5></div>';
			str+='</label>';
			str+='</li>';
			for(var i in result){
				str+='<li>';
				str+='<label class="checkbox-inline">';//alert(jQuery.inArray(result[i].id, newsPaperIdsGlob));
				//str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn">';
				if($.inArray(result[i].id, newsChannelsIdsGlbl) != -1){
					str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn" checked="checked">';
				}else{
					str+='<input type="checkbox"  value="'+result[i].id+'" class="newsChannelSelectAllClsEmn">';
				}
				str+='<div style="margin-top: 3px;"><h5 style="color:#54616C;" class="text-capital">'+result[i].name+'</h5></div>';
				str+='</label>';
				str+='</li>';
			}
			$("#emnNewsChannelsUlId").html(str);
			var newspaperlenght = $("#emnNewsChannelsUlId").find("li").length;
			if(newspaperlenght >= 7){
			$(".settingsUlEmn").mCustomScrollbar({setHeight:'245'})
			
			}else{
				$(".settingsUlEmn").css("height","auto");
			
			}
		}
  });
}

function getMediaProgramsOnParty()
{
	$("#electronicMediaChannelCountId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetails/2/1/01-01-2016/"+currentFromDateEmn+"/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBulletinPointBasicDetails/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N"
	}).then(function(result){
		getpartyWiseChannelCounts(result);
	});
}


function getpartyWiseChannelCounts(result){
	var str='';
	if(result != null && result.length > 0)
	{
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span></h4>';
	
		str+='<table class="table tableEMN m_top20">';
			str+='<tr>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Total Program</h5>';
					str+='<h4	>'+result[0].categoryCount != ""?result[0].categoryCount:"00:00"+'</h4>';
					str+='<h5 class="text-capitalize m_top20">total time</h5>';
					str+='<h4>'+result[0].description != ""?result[0].description:"00:00"+'</h4>';
				str+='</td>';
				for(var j in result[0].tvNewsDetailsVOList)
				{
					str+='<td>';
						str+='<h5 class="text-capitalize">'+result[0].tvNewsDetailsVOList[j].organization+'</h5>';
						str+='<h4>'+result[0].tvNewsDetailsVOList[j].categoryCount+'</h4>';
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						//str+='<h4>'+result[0].tvNewsDetailsVOList[j].description+'</h4>';
						if(result[0].tvNewsDetailsVOList[j].description!= ""){
							str+='<h4>'+result[0].tvNewsDetailsVOList[j].description+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
				}
			str+='</tr>';
		str+='</table>';
		
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/opp.png" style="width:25px;" alt="tdp icon" class="debatesPartyIcon"/>Opposition Parties</span></h4>';
		for(var i=1;i<(result.length-1);i++)
		{
			str+='<h4 class="panel-title m_top20"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" class="debatesPartyIcon"/>'+result[i].organization+'</h4>';
			str+='<table class="table tableEMN">';
				str+='<tr>';
					str+='<td>';
						str+='<h5 class="text-capitalize">Total Program</h5>';
						str+='<h4>'+result[i].categoryCount != ""?result[i].categoryCount:"00:00"+'</h4>';
						str+='<h5 class="text-capitalize m_top20">total time</h5>';
						str+='<h4>'+result[i].description != ""?result[i].description:"00:00"+'</h4>';
					str+='</td>';
					for(var j in result[i].tvNewsDetailsVOList)
					{
						str+='<td>';
							str+='<h5 class="text-capitalize">'+result[i].tvNewsDetailsVOList[j].organization+'</h5>';
							str+='<h4>'+result[i].tvNewsDetailsVOList[j].categoryCount+'</h4>';
							str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
							str+='<h4>'+result[i].tvNewsDetailsVOList[j].description != ""?result[i].tvNewsDetailsVOList[j].description:"00:00"+'</h4>';
						str+='</td>';
					}
				str+='</tr>';
			str+='</table>';
		}
		
		str+='<h4 class="text-capital m_top10"><span class="headingColor"><img src="newCoreDashBoard/img/GOVT.png" style="width:25px;" alt="government icon" class="newsIcon"/>Government</span></h4>';
		var EmnG = result.length-1;
		str+='<table class="table tableEMN m_top20">';
			str+='<tr>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Total Program</h5>';
					str+='<h4>'+result[EmnG].categoryCount != ""?result[EmnG].categoryCount:"00:00"+'</h4>';
					str+='<h5 class="text-capitalize m_top20">total time</h5>';
					str+='<h4>'+result[EmnG].description != ""?result[EmnG].description:"00:00"+'</h4>';
				str+='</td>';
				for(var j in result[EmnG].tvNewsDetailsVOList)
				{
					str+='<td>';
						str+='<h5 class="text-capitalize">'+result[EmnG].tvNewsDetailsVOList[j].organization+'</h5>';
						str+='<h4>'+result[EmnG].tvNewsDetailsVOList[j].categoryCount+'</h4>';
						str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
						str+='<h4>'+result[EmnG].tvNewsDetailsVOList[j].description+'</h4>';
					str+='</td>';
				}
			str+='</tr>';
		str+='</table>';
	}else{
		str+='<h4 class="panel-title m_top20">NO DATA AVAILABLE</h4>';
	}
	$("#electronicMediaChannelCountId").html(str);
}
function getEMMDetailedPartyDistrictWiseProgramsOverview()
{
	$("#districtWiseProgramsOvrViewEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N"
	}).then(function(result){
		mediaProgramsOnParty(result);
	});
}
function mediaProgramsOnParty(result)
{
	var str='';
				
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="text-capital"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" style="width:25px;" alt="'+result[i].organization+' icon" class="debatesPartyIcon">'+result[i].organization+'</h4>';
		str+='<div id="EMNDistrictWiseGraph'+i+'" style="height:300px"></div>';
		str+='</div>'
	}
	$("#districtWiseProgramsOvrViewEMN").html(str);
	for(var i in result){
		var locationNameArr = [];
		var PositiveCntArr = [];
		var NegativeCntArr = [];
		var countVar =0;
		
		for(var j in result[i].tvNewsDetailsVOList1){
			locationNameArr.push(result[i].tvNewsDetailsVOList1[j].organization)
			PositiveCntArr.push(result[i].tvNewsDetailsVOList1[j].positiveCount)
			NegativeCntArr.push(result[i].tvNewsDetailsVOList1[j].negativeCount)
				
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
		}
		
		$("#EMNDistrictWiseGraph"+i).highcharts({
			colors: ['#64C664','#D33E39'],
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
				categories: locationNameArr,
				labels: {
						rotation: -45,
						style: {
							fontSize: '13px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				enabled: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				borderColor: '#CCC',
				borderWidth: 1,
				shadow: false
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
			
			plotOptions: {
				pointPadding: 0.2,
				borderWidth: 2,
				groupPadding: 0.2,
				column: {
					stacking: 'percent',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.percentage,1) +'%';
							}
						}
					  
					},
				},
				series: {
					cursor: 'pointer',
					point: {
						events: {
							click: function () {
								getArticlesForgetDetailedGovtDepartmentWiseDistrictsOverview(this.extra);
							}
						}
					}
				}
			},
			series: [{
				name: 'Positive',
				data: PositiveCntArr
			}, {
				name: 'Negative',
				data: NegativeCntArr
			}]
		});

	}
}

function getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type)
{
	$("#electronicMediaPrograms").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	if(type == 'program')
	{
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N/"+searchType+""
		}).then(function(result){
			buildEmmDetailedPartyMediaProgramsOnParty(result);
		});
	}else{
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyTimeWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N/"+searchType+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyTimeWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N/"+searchType+""
		}).then(function(result){
			buildEmmDetailedPartyMediaProgramsOnPartyTimeWise(result);
		});
	}
	
}

function buildEmmDetailedPartyMediaProgramsOnParty(result)
{
	var str='';
	str+='<div class="row">';
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<h5 class="text-capital">'+result[i].organization+'</h5>';
			str+='<div id="emNewsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
		str+='</div>'
	}
	str+='</div>'
	$("#electronicMediaPrograms").html(str);
	for(var i in result){
		var PositiveCountArray = [];
		var NegativeCountArray = [];
		var candidateNameArray=[];
		var countVar =0;
		
		
		for(var j in result[i].tvNewsDetailsVOList1){
			candidateNameArray.push(result[i].tvNewsDetailsVOList1[j].channelName)
			PositiveCountArray.push(result[i].tvNewsDetailsVOList1[j].count)
			
				
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
		}
		
		$("#emNewsBlockGenSecStrong"+i).highcharts({
			colors: ['#339A99'],
			chart: {
				type: 'column'
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
				categories: candidateNameArray,
				type: 'category',
				labels: {
						formatter: function() {
							return this.value;
						},
						
					}
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled:false
				}
			},
			legend: {
				enabled: false
			},
			
					
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return (this.y);
							}
						}
					  
					}
				}
			},

			 tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/>'+(this.y);
					});

					return s;
				},
				shared: true
			},

			series: [{
				name: 'Negative',
				data: PositiveCountArray,
				
			}],
		 
		});
	}
	
}
function buildEmmDetailedPartyMediaProgramsOnPartyTimeWise(result)
{
	var str='';
	str+='<div class="row">';
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<h5 class="text-capital">'+result[i].organization+'</h5>';
			str+='<div id="emNewsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
		str+='</div>'
	}
	str+='</div>'
	$("#electronicMediaPrograms").html(str);
	for(var i in result){
		var PositiveCountArray = [];
		var NegativeCountArray = [];
		var candidateNameArray=[];
		var timeCounts=[];
		var countVar =0;
		
		
		for(var j in result[i].tvNewsDetailsVOList1){
			candidateNameArray.push(result[i].tvNewsDetailsVOList1[j].channelName);
			PositiveCountArray.push(result[i].tvNewsDetailsVOList1[j].coveredTime);
			timeCounts.push(result[i].tvNewsDetailsVOList1[j].description);
			countVar =countVar+1;
			if (countVar === 5) {
				break;
			}
		}
		
		$("#emNewsBlockGenSecStrong"+i).highcharts({
			colors: ['#339A99'],
			chart: {
				type: 'column'
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
				categories: candidateNameArray,
				type: 'category',
				labels: {
						formatter: function() {
							return this.value;
						},
						
					}
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled:false
				}
			},
			legend: {
				enabled: false
			},
			
					
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: false,
						 formatter: function() {
							var k = '<b>' + this.x + '</b>';
							var i = 0;
							$.each(this.points, function () {
								k += '<br/>'+timeCounts;
							});
						}
					  
					}
				}
			},

			 tooltip: {
				formatter: function () {
					var s = '<b>' + this.x + '</b>';

					$.each(this.points, function () {
						s += '<br/>'+(this.y);
					});

					return s;
				},
				shared: true
			},

			series: [{
				name: 'Negative',
				data: PositiveCountArray,
				
			}],
		 
		});
	}
	
}
$(document).on("click",".emnMediaPrograms li",function(){
	var searchType = $(this).attr("attr_searchType");
	var type = $('input[name=emnSearchTypeName]:checked').val();
	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type);
});

$(document).on("click",".emnSearchTypeCls",function(){
	var searchType = '';
	if($('.emnMediaPrograms li.active').attr("attr_searchType") == "top")
	{
		searchType="top";
	}else{
		searchType="poor";
	}
	var type = $('input[name=emnSearchTypeName]:checked').val();
	getEMMDetailedPartyMediaProgramsOnPartyProgramsWise(searchType,type);
});
$(document).on("click",".distWiseProgramOvrView li",function(){
	var type = $(this).attr("attr_type");
	if(type == 'party')
	{
		getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type);
	}else{
		getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type);
	}
	
});

function getEMMDetailedPartiesVsChannelsPartiesDistrictWise(type)
{
	
	$("#partiesVsChannelsEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	if(type == 'party')
	{
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsPartiesDistrictWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N/"
		}).then(function(result){
			buildEMMDetailedPartiesVsChannelsPartiesDistrictWise(result);
		});
	}else{
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartiesVsChannelsTvChannelWise/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N/"
		}).then(function(result){
			buildgetEMMDetailedPartiesVsChannelsTvChannelWise(result);
		});
	}
	
}
function buildEMMDetailedPartiesVsChannelsPartiesDistrictWise(result)
{
	var str = '';
	if(result !=null && result.length >0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
				str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
					str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+result[i].organization+'.png" class="debatesPartyIcon" />'+result[i].organization+'</h4>';
				str+='</div>';
				str+='<div class="col-xs-11 col-sm-10 col-md-11">';
					str+='<ul class="partyVsChannelSlickApplyEmn" style="padding:0px;">';
					for(var j in result[i].tvNewsDetailsVOList)
					{
						str+='<li><div id="partyVsChannelGraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';	
		}
	}else{
		str+='<h4>NO DATA AVAILABLE</h4>';
	}
	$("#partiesVsChannelsEMN").html(str);
	var districtNameEmn = []
	var positivePercArrayEmn = []
	var negativePercArrayEmn = []
	var paperNamesArrayEmn = []
	for(var i in result)
	{
		for(var j in result[i].tvNewsDetailsVOList)
		{
			districtNameEmn.push(result[i].tvNewsDetailsVOList[j].groupTitle)
			for(var k in result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList)
			{
				positivePercArrayEmn.push(result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].positivePerc)
				negativePercArrayEmn.push(result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].negativePerc)
				paperNamesArrayEmn.push(result[i].tvNewsDetailsVOList[j].tvNewsDetailsVOList[k].organization)
			}
			$('#partyVsChannelGraph'+i+''+j+'').highcharts({
				 colors: ['#64C664','#D33E39'],
				chart: {
					type: 'column'
				},
				title: {
					text: districtNameEmn
				},
			   
				xAxis: {
					 min: 0,
						 gridLineWidth: 0,
						 minorGridLineWidth: 0,
						 categories: paperNamesArrayEmn,
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
							enabled: false,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							}
						},
						
					},
				},
				series: [{
					name: 'Positive',
					data: positivePercArrayEmn
				}, {
					name: 'Negative',
					data: negativePercArrayEmn
				}]
			});
		}
	}
	$(".partyVsChannelSlickApplyEmn").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		 swipeToSlide:false,
		 swipe:false,
		 touchMove:false,
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
function buildgetEMMDetailedPartiesVsChannelsTvChannelWise(result)
{
	var str = '';
	if(result !=null && result.length >0){
		for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-ms-12">';
				str+='<div class="col-xs-1 col-md-1 col-sm-2 pad_left0">';
					str+='<h4  style="height:150px;border-right:1px solid #ddd;padding-top:50px !important;"><img src="newCoreDashBoard/img/'+result[i].channelName+'.png" class="debatesPartyIcon" />'+result[i].channelName+'</h4>';
				str+='</div>';
				str+='<div class="col-xs-11 col-sm-10 col-md-11">';
					str+='<ul class="partyVsChannelSlickApplyEmn">';
					for(var j in result[i].tvNewsDetailsVOList1)
					{
						str+='<li><div id="partyVsChannelGraph'+i+''+j+'"  style="height:200px;width:220px"></div></li>';
					}
					str+='</ul>';
				str+='</div>';
			str+='</div>';	
		}
	}else{
		str+='<h4>NO DATA AVAILABLE</h4>';
	}
	$("#partiesVsChannelsEMN").html(str);
	var channelNameEmn =[]
	var positivePercArrayEmn = []
	var negativePercArrayEmn = []
	var paperNamesArrayEmn = []
	for(var i in result)
	{
		//channelNameEmn.push(result[i].channelName	)
		for(var j in result[i].tvNewsDetailsVOList1)
		{
				positivePercArrayEmn.push(result[i].tvNewsDetailsVOList1[j].positivePerc)
				negativePercArrayEmn.push(result[i].tvNewsDetailsVOList1[j].negativePerc)
				paperNamesArrayEmn.push(result[i].tvNewsDetailsVOList1[j].organization)
		
			$('#partyVsChannelGraph'+i+''+j+'').highcharts({
				 colors: ['#64C664','#D33E39'],
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
						 categories: paperNamesArrayEmn,
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
							enabled: false,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.percentage,1) + '%';
								}
							}
						},
						
					},
				},
				series: [{
					name: 'Positive',
					data: positivePercArrayEmn
				}, {
					name: 'Negative',
					data: negativePercArrayEmn
				}]
			});
		}
	}
	$(".partyVsChannelSlickApplyEmn").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		 swipeToSlide:false,
		 swipe:false,
		 touchMove:false,
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

/*detailed Govt */
function getProblemsDetailedOverView()
{
	$("#stateWiseEMNRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getProblemsDetailedOverView/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getProblemsDetailedOverView/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3,4,5,6,7/"+impactScopeIds+"/ /Y/"
	}).then(function(result){
		buildProblemsDetailedOverView(result);
		buildDistrictWiseEMNRelatedToProblem(result);
	});
}
function getEMMDetailedGovtProblemsDetailedOverview()
{
	$("#districtWiseEMNRelatedToProblem").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#emmDetailedGovtDistDetailedOverview").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedGovtProblemsDetailedOverview/2/1/"+currentFromDateEmn+"/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/ /Y/"
	}).then(function(result){
		buildEMMDetailedGovtDistDetailedOverview(result);
		buildEMMDetailedGovtProblemsDetailedOverview(result);
	});
}
function buildEMMDetailedGovtDistDetailedOverview(result)
{
	var str='';
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
				str+='<h4 class="m_top20">DISTRICT WISE</h4>';
				str+='<div id="comaprisonDistrictWiseArticleEmn" class="m_top20" style="height:150px;"></div>';
			str+='</div>';
		str+='</div>';
		
		var distWiseArticlesRelated = [];
		for(var i in result.tvNewsDetailsVOList1){
			var obj1 = {
				name: result.tvNewsDetailsVOList1[i].organization,
				y: result.tvNewsDetailsVOList1[i].positivePerc,
				extra:result.tvNewsDetailsVOList1[i].count
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#emmDetailedGovtDistDetailedOverview").html(str)
		$(function () {
			 $("#comaprisonDistrictWiseArticleEmn").highcharts({
				colors: ['#AA3732'],
				chart: {
					type: 'column'
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
					
					type: 'category',
					labels: {
								formatter: function() {
									return this.value.toString().substring(0, 10)+'...';
								},
								
							}
					
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: ''
					},
					labels: {
						enabled:false
					}
				},
				legend: {
					enabled: false
				},
				
						
				plotOptions: {
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,1) + '%';
								}
							}
						  
						}
					}
				},

				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}% - {point.extra}</b>'
				},

				series: [{
					name: 'Completed',
					data: distWiseArticlesRelated
				}],
			 
			});
		});
		
}
function buildEMMDetailedGovtProblemsDetailedOverview(result)
{
	var str='';
		var deptWiseEMNRelatedGraph = [];
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<h4>DEPARTMENTS WISE</h4>';
					str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
					str+='<ul style="list-style:none;padding-left:0px;" id="getLiLength" class="textAlignDepartment">';
						for(var i in result.tvNewsDetailsDeptVOList)
						{
							str+='<li>'+result.tvNewsDetailsDeptVOList[i].organization+'</li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-5 col-xs-12 col-sm-6">';
					str+='<div id="districtWiseEMNRelatedToProblemGraph" class="dynHeight"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		var problemRelatedPieChartArray =[];
			for(var i in result.tvNewsDetailsDeptVOList){
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].organization);
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].count);
				deptWiseEMNRelatedGraph.push(problemRelatedPieChartArray);
			}
		
		$("#districtWiseEMNRelatedToProblem").html(str);
		var getLiLength = result.tvNewsDetailsDeptVOList.length;
		getLiLength = (getLiLength*36)+"px";
		$("#districtWiseEMNRelatedToProblemGraph").attr("style","height:"+getLiLength+"")
		$(".emnChannelWiseSlickDist").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
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
			  ]
		}); 
		if(result.tvNewsDetailsDeptVOList.length > 0)
		{
			$('#districtWiseEMNRelatedToProblemGraph').highcharts({
				chart: {
					type: 'bar'
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
					categories: '',
					labels: {
					enabled: false,
						
					}
			},
			yAxis: {
				min: 0,
					   gridLineWidth: 0,
						minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled: false,
						
					},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				
				series: [{
					name: '',
					colorByPoint: true,
					data: deptWiseEMNRelatedGraph,
					dataLabels:{
							enabled: true,
							 distance: -20,
							  formatter: function() {
									if (this.y === 0) {
										return null;
									} else {
										return Highcharts.numberFormat(this.y,1)+ '%';
									}
								} 
						},
				}]
			});
		}else{
			str+='<h4>NO DATA AVAILABLE</h4>';
		}
}
function buildProblemsDetailedOverView(result)
{
	var str='';
	str+='<div class="row">';
		str+='<div class="col-md-6 col-xs-12 col-sm-6">';
			str+='<table class="table tableEMN m_top20">';
				str+='<tr>';
				for(var i in result.tvNewsDetailsVOList[0].tvNewsDetailsVOList)
				{
					str+='<td>';
						str+='<h5 class="text-capitalize">'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].organization+'</h5>';
						str+='<h4>'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].tvNewsDetailsProgramVOList.length+'</h4>';
						str+='<h5 class="text-capitalize m_top20">'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].title+'</h5>';
						if(result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].description != ""){
							str+='<h4>'+result.tvNewsDetailsVOList[0].tvNewsDetailsVOList[i].description+'</h4>';
						}else{
							str+='<h4>00:00</h4>';
						}
					str+='</td>';
				}
				str+='</tr>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	$("#problemsDetailedOvrViewEMN").html(str)
}

function buildDistrictWiseEMNRelatedToProblem(result){
		var str='';
		
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<h4>CHANNELS WISE</h4>';
			str+='</div>';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<ul class="emnChannelWiseSlick">';
				for(var i in result.tvNewsDetailsChnlVOList)
				{
					str+='<li>';
						str+='<img src="newCoreDashBoard/img/'+result.tvNewsDetailsChnlVOList[i].channelName+'.png" class="debatesPartyIcon"/>'+result.tvNewsDetailsChnlVOList[i].channelName+' News';
						str+='<p class="m_top10 text-muted">Total Programs</p>';
						str+='<p>'+result.tvNewsDetailsChnlVOList[i].channelName+'</p>';
					str+='</li>';
				}
				str+='</ul>';
			str+='</div>';
		str+='</div>';
		
		var deptWiseEMNRelated = [];
		str+='<div class="row">';
			str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
				str+='<h4>DEPARTMENTS WISE</h4>';
					str+='<div class="col-md-7 col-xs-12 col-sm-6 m_top10">';
					str+='<ul style="list-style:none;padding-left:0px;" id="getLiLength" class="textAlignDepartment">';
						for(var i in result.tvNewsDetailsDeptVOList)
						{
							str+='<li>'+result.tvNewsDetailsDeptVOList[i].organization+'</li>';
						}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-5 col-xs-12 col-sm-6">';
					str+='<div id="comaprisonStateWiseArticle" class="dynHeight"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		var problemRelatedPieChartArray =[];
			for(var i in result.tvNewsDetailsDeptVOList){
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].organization);
				problemRelatedPieChartArray.push(result.tvNewsDetailsDeptVOList[i].positivePerc);
				deptWiseEMNRelated.push(problemRelatedPieChartArray);
			}
		
		$("#stateWiseEMNRelatedToProblem").html(str);
		var liLength = result.tvNewsDetailsDeptVOList.length;
		liLength = (liLength*36)+"px";
		$("#comaprisonStateWiseArticle").css("height",liLength);
		$(".emnChannelWiseSlick").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false,
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
		$(function () {
			$('#comaprisonStateWiseArticle').highcharts({
				chart: {
					type: 'bar'
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
					categories: '',
					labels: {
					enabled: false,
						
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: ''
				},
				labels: {
					enabled: false,
						
					},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
				tooltip: {
					valueSuffix: '%'
				},
				plotOptions: {
					bar: {
						dataLabels: {
							enabled: true
						}
					}
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				
				series: [{
						name: '',
						colorByPoint: true,
						data: deptWiseEMNRelated,
						dataLabels:{
								enabled: true,
								 distance: -20,
								  formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y,1)+ '%';
										}
									} 
							},
					}]
			});
		});
	
	}
	

function getEMMDetailedPartyStateWiseProgramsOverview()
{
	$("#stateWiseProgramsOvrViewEMN").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		//url: wurl+"/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/2/1/"+currentToDateEmn+"/"+currentFromDateEmn+"/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyStateWiseProgramsOverview/2/1/01-01-2016/"+currentToDateEmn+"/"+newsChannelsIdsGlbl+"/"+impactScopeIds+"/872,1117,163,362/N/party"
	}).then(function(result){
		//console.log(result)
		buildEMMDetailedPartyStateWiseProgramsOverview(result)
	});
}

function buildEMMDetailedPartyStateWiseProgramsOverview(result)
{
	var str='';
	for(var i in result)
	{
		str+='<div class="row">';
			str+='<div class="col-md-3 col-xs-12 col-sm-3" style="border-right:1px solid #333">';
				
				str+='<h4 class="panel-title">'+result[i].name+'</h4>';
				str+=''+result[i].positivePerc+'';
				str+=''+result[i].negativePerc+'';
				str+='<h4 class="panel-title">Total Programs - '+result[i].categoryCount+'</h4>';
				for(var j in result[i].tvNewsDetailsVOList)
				{
					str+='<div id="newsChannels'+i+''+j+'" style="height:100px;"></div>';
					str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCoveredTime+'</p>';
					str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCoveredTime+'</p>';
					str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveTimePerc+'</p>';
					str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeTimePerc+'</p>';
				}
			str+='</div>';
			str+='<div class="col-md-9 col-xs-12 col-sm-9">';
					for(var k in result[i].tvNewsDetailsVOList1)
					{
						str+='<h4 style="background-color:#ddd;padding:5px;"><img src="newCoreDashBoard/img/'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'.png" class="newsIcon"/>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[0].channelName+'</h4>';
						str+='<div class="row">';
						str+='<div class="col-md-4 col-xs-12 col-sm-4">';
							str+='<h4 class="panel-title">Total Programs - '+result[i].tvNewsDetailsVOList1[k].categoryCount+'</h4>';
							str+='<div class="row">';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<div id="newsChannelsPrograms'+k+'" style="height:100px;"></div>';
								str+='</div>';
								str+='<div class="col-md-6 col-xs-12 col-sm-6">';
									str+='<p class="text-muted">Positive Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].positiveCoveredTime+'<small>'+result[i].tvNewsDetailsVOList1[k].positiveTimePerc+'</small></p>';
									str+='<p class="text-muted">Negative Time</p>';
									str+='<p>'+result[i].tvNewsDetailsVOList1[k].negativeCoveredTime+'<small>'+result[i].tvNewsDetailsVOList1[k].negativeTimePerc+'</small></p>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
						{
							str+='<div class="col-md-4 col-xs-12 col-sm-4">';
								str+='<h4 class="panel-title">'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].categoryName+' - '+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].	categoryCount+'</h4>';
								str+='<div class="row">';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<div id="newsChannelsPrograms'+k+l+'" style="height:100px;"></div>';
									str+='</div>';
									str+='<div class="col-md-6 col-xs-12 col-sm-6">';
										str+='<p class="text-muted">Positive Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].positiveCoveredTime+'</p>';
										str+='<p class="text-muted">Negative Time</p>';
										str+='<p>'+result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCoveredTime+'</p>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						}
						str+='</div>';
						
					}
				
			str+='</div>';
		str+='</div>';
		str+='<hr style="border-color:#555"/>';
	}
	
	$("#stateWiseProgramsOvrViewEMN").html(str);
	for(var i in result)
	{
		
		for(var j in result[i].tvNewsDetailsVOList)
		{
			
			var partyProgramsArr = []	
			str+='<p>'+result[i].tvNewsDetailsVOList[j].positiveCount+'</p>';
			str+='<p>'+result[i].tvNewsDetailsVOList[j].negativeCount+'</p>';
			var partyProgramsSubArr = [];
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].positiveCount,"color":'#64C664'});
			partyProgramsSubArr.push({"y":result[i].tvNewsDetailsVOList[j].negativeCount,"color":'#D33E39'});
		
			$("#newsChannels"+i+j).highcharts({
				chart: {
					type: 'column',
					backgroundColor:'transparent'
				},

				title: {
					text: null
				},

				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['Positive', 'Negative']
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
						legend:{
					enabled:false
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>' +
							'Total: ' + this.point.stackTotal;
					}
				},

				plotOptions: {
					column: {
						stacking: 'normal'
					}
				},

				series: [{
					name: 'John',
					data: partyProgramsSubArr
				}]
			});
		}
	
		for(var k in result[i].tvNewsDetailsVOList1)
		{
			var resultArr = [];
			var resultArrColors = ['#333','#555']
			for(var l in result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList)
			{
				
				var resultSubArr = [];
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].positiveCoveredTime,"color":'#64C664'});
				resultSubArr.push({"y":result[i].tvNewsDetailsVOList1[k].tvNewsDetailsVOList[l].negativeCoveredTime,"color":'#D33E39'});
				resultArr.push({"data":resultSubArr})
				$("#newsChannelsPrograms"+k+l).highcharts({
					chart: {
						type: 'column',
						backgroundColor:'transparent'
					},

					title: {
						text: null
					},

					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: ['Positive', 'Negative']
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
							legend:{
						enabled:false
					},
					tooltip: {
						formatter: function () {
							return '<b>' + this.x + '</b><br/>' +
								this.series.name + ': ' + this.y + '<br/>' +
								'Total: ' + this.point.stackTotal;
						}
					},

					plotOptions: {
						column: {
							stacking: 'normal'
						}
					},

					series: [{
						name: 'John',
						data: resultSubArr
					}]
				});
			}
		}
	}
}

