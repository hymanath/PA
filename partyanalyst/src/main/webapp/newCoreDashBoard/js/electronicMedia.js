	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

$(document).on("click",".emnIconExpand",function(){
	$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".electronicMediaBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		//before expand
		$(".moreBlockEMN").show();
		getEMMDetailedPartyMediaProgramsOnPartyProgramsWise();
	}else{
		//after expand	
		$(".moreBlockEMN").hide();
	}
});
var currentFromDateEmn = moment().format("DD-MM-YYYY");
var currentToDateEmn = moment().format("DD-MM-YYYY");
$(".dateRangePickerClsForEmn").daterangepicker({
	opens: 'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
	  format: 'DD-MM-YYYY'
	}
});
$('.dateRangePickerClsForEmn').on('apply.daterangepicker', function(ev, picker) {
	currentFromDateEmn = picker.startDate.format('DD-MM-YYYY');
	currentToDateEmn = picker.endDate.format('DD-MM-YYYY');
});

getEMMDetailedPartyDistrictWiseProgramsOverview();
//getMediaProgramsOnParty();

function getEMMDetailedPartyDistrictWiseProgramsOverview()
{
	$("#electronicMediaChannelCountId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		//url: wurl+"CommunityNewsPortal/webservice/getNewsPartyWiseArticleCounts/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N/category"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsPartyWiseArticleCounts/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N/category"
	}).then(function(result){
		getpartyWiseChannelCounts(result);
	});
}


function getpartyWiseChannelCounts(result){
	var str='';
		str+='<h4 class="text-capital"><span class="headingColor" style="margin-right:5px"><img src="newCoreDashBoard/img/TDP.png" alt="tdp icon" class="newsIcon"/>Telugu Desam Party</span></h4>';
		str+='<table class="table tableEMN m_top20">';
			str+='<tr>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Total Program</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList[0].channelId+'</h4>';
					str+='<h5 class="text-capitalize m_top20">total time</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList[0].description+'</h4>';
				str+='</td>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Bulletin</h5>';
					str+='<h4>100</h4>';
					str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList.description+'</h4>';
				str+='</td>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Spl Program</h5>';
					str+='<h4>100</h4>';
					str+='<h5 class="text-capitalize m_top20">Covered Time</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList.description+'</h4>';
				str+='</td>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Positive</h5>';
					str+='<h4>100</h4>';
					str+='<h5 class="text-capitalize m_top20">Positive Time</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList.description+'</h4>';
				str+='</td>';
				str+='<td>';
					str+='<h5 class="text-capitalize">Negative</h5>';
					str+='<h4>100</h4>';
					str+='<h5 class="text-capitalize m_top20">Negative Time</h5>';
					str+='<h4>'+result[0].tvNewsDetailsVOList.description+'</h4>';
				str+='</td>';
			str+='</tr>';
		str+='</table>';
	
	
	$("#electronicMediaChannelCountId").html(str);
}
function getMediaProgramsOnParty()
{
	//$("#electronicMediaChannelCountId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		//url: wurl+"CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"
	}).then(function(result){
		mediaProgramsOnParty(result);
	});
}
function mediaProgramsOnParty(result)
{
	var str='';
				
	for(var i in result){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			if(result[i][0].userTypeId == 4 || result[i][0].userTypeId == 11){
				str+='<h5 class="text-capital">ORGANIZING SECRETARY / SECRETARY</h5>';
			}else{
				str+='<h5 class="text-capital">'+result[i][0].userType+'</h5>';
			}
			str+='<div id="newsBlockGenSecStrong'+i+'" style="height:130px;"></div>';
		str+='</div>'
			
	}
}

function getEMMDetailedPartyMediaProgramsOnPartyProgramsWise()
{
	$("#electronicMediaPrograms").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		//url: wurl+"CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyMediaProgramsOnPartyProgramsWise/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N/top"
	}).then(function(result){
		EMMDetailedPartyMediaProgramsOnParty(result);
	});
}

function EMMDetailedPartyMediaProgramsOnParty(result)
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
					stacking: 'percent',
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.percentage,1) + '%';
							}
						}
					  
					}
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

			series: [{
				name: 'Negative',
				data: PositiveCountArray,
				
			}],
		 
		});
	}
	
}