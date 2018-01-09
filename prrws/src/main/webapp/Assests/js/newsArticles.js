	var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
   if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));   
	var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
	var glEndDate = moment().add(10, 'years').endOf('year').format("DD-MM-YYYY");
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	$("#dateRangePickerNA").daterangepicker({
				opens: 'left',
				startDate: glStartDate,
				endDate: glEndDate,
			locale: {
			  format: 'DD/MM/YYYY'
			},
			ranges: {
				'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
				'Today' : [moment(), moment()],
			   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
			   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
			   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			   'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
			   'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
			   'This Month': [moment().startOf('month'), moment()],
			   'This Year': [moment().startOf('Year'), moment()]
			}
		});
		var dates= $("#dateRangePickerNA").val();
		var pickerDates = glStartDate+' - '+glEndDate
		if(dates == pickerDates)
		{
			$("#dateRangePickerNA").val('All');
		}
		$('#dateRangePickerNA').on('apply.daterangepicker', function(ev, picker) {
			
			$(".collapseActiveVillageCls").addClass("collapsed");
			$(".collapseActiveMandalCls").addClass("collapsed");
			$(".collapseActiveConstCls").addClass("collapsed");
			$(".collapseActiveDistCls").addClass("collapsed");
			$("#collapseTwo").removeClass("in");		
			$("#collapseThree").removeClass("in");		
			$("#collapseFour").removeClass("in");		
			$("#collapseFive").removeClass("in");	
			glStartDate = picker.startDate.format('DD-MM-YYYY')
			glEndDate = picker.endDate.format('DD-MM-YYYY')
			if(picker.chosenLabel == 'All')
			{
				$("#dateRangePickerNA").val('All');
			}
			onloadcalls();
		});
		onloadcalls();
	function onloadcalls()
	{
		getPrintMediaCountsDetailsInfo(glStartDate,glEndDate);
		getDistrictWiseTotalOverViewInfo(glStartDate,glEndDate);
		getElectronicMediaCountsDetailsInfo(glStartDate,glEndDate);
	}
		
	
	function buildPrintMediaCountsDetailsInfoData(result,columnDivId,mainDivId,type)
	{
		var positiveCount=0;
		var negativeCount=0;
		var totalCount=0;
		var countSum=result[0].count+result[1].count;
		var countPer;
		
		
		var categories=['Total', 'Positive', 'Negative'];
		for(var i in result)
		{
			countPer=result[i].count*100/countSum;
			positiveCount=positiveCount+result[i].positiveCountMain;
			negativeCount=negativeCount+result[i].negativCountMain;
			totalCount=totalCount+result[i].count;
		var str='';
			str+='<div class="col-sm-4 col-sm-offset-0">';
				str+='<table class="table table-bordered borderNone">';
					str+='<tr>';
						str+='<td>';
							str+='<p style="margin:0px;">';
								str+=result[i].organization+' Edition';
							str+='</p>';
						str+='</td>';
						str+='<td>';
							str+='<p>'+countPer.toFixed(2)+'%</p>';
						str+='</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td>';
							str+='<div id="'+type+'container'+i+'" style="height:120px;width:110px;margin:0px;"></div>';
						str+='</td>';
						str+='<td class="text-center">Total <span style="font-weight:bold;">'+result[i].count+'</span>';
						str+='</td>';
					str+='</tr>';
					str+='<tr><td>positive <p style="font-weight:bold;">'+result[i].positiveCountMain+'<br><span style="color:#63C563"> '+result[i].positivePerc+'%<span></p></td>';
						str+='<td>negative <p style="font-weight:bold;">'+result[i].negativCountMain+'<br><span style="color:#EE6969"> '+result[i].negativePerc+'%<span></p></td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			$('#'+mainDivId).append(str);
			buildingPieGraph(type+"container"+i,result[i].positivePerc,result[i].negativePerc);
		}
		
		buildingHighchart(columnDivId,negativeCount,totalCount,positiveCount,categories);
	}
	function buildElectronicMediaCountsDetailsInfoData(result,columnDivId,mainDivId,type)
	{
		var positiveCount=0;
		var negativeCount=0;
		var totalCount=0;
		
		var categories=['Total', 'Positive', 'Negative'];
		for(var i in result)
		{
			positiveCount=positiveCount+result[i].positiveCountMain;
			negativeCount=negativeCount+result[i].negativCountMain;
			totalCount=totalCount+result[i].count;
			
			
		var str='';
			str+='<div class="col-sm-4 col-sm-offset-0">';
				str+='<table class="table table-bordered borderNone">';
					str+='<tr>';
						str+='<td>';
							str+='<p style="margin:0px;">';
								str+=result[i].organization+' Edition';
							str+='</p>';
						str+='</td>';
						str+='<td>';
							str+='<p>'+result[i].tdpPerc+'%</p>';
						str+='</td>';
					str+='</tr>';
					str+='<tr>';
						str+='<td>';
							str+='<div id="'+type+'container'+i+'" style="height:120px;width:110px;margin:0px;"></div>';
						str+='</td>';
						str+='<td class="text-center">Total <span style="font-weight:bold;">'+result[i].count+'</span><br><span>'+result[i].totalCount+'</span>';
						str+='</td>';
					str+='</tr>';
					str+='<tr><td>positive <p style="font-weight:bold;">'+result[i].positiveCountMain+'<br><span style="color:#63C563"> '+result[i].positivePerc+'%</span><br><span> '+result[i].positiveCountDist+'<span></p></td>';
						str+='<td>negative <p style="font-weight:bold;">'+result[i].negativCountMain+'<br><span style="color:#EE6969"> '+result[i].negativePerc+'%</span><br><span> '+result[i].negativCountDist+'<span></p></td>';
					str+='</tr>';
				str+='</table>';
			str+='</div>';
			$('#'+mainDivId).append(str);
			buildingPieGraph(type+"container"+i,result[i].positivePerc,result[i].negativePerc);
		}
		
		buildingHighchart(columnDivId,negativeCount,totalCount,positiveCount,categories);
	}
	function buildDistrictWiseTotalOverViewInfoData(result)
	{
		var categoriesArr=[];
		var positiveCountPerArr=[];
		var negativeCountPerArr=[];
		var stackedColumnChart=[];
		for(var i in result)
		{
				categoriesArr.push(result[i].organization);
				positiveCountPerArr.push(result[i].positivePerc);
				negativeCountPerArr.push(result[i].negativePerc);
		}
		
		stackedColumnChart.push({"name": 'positive',"data": positiveCountPerArr}, {"name": 'negative',"data": negativeCountPerArr});
	buildingStackedColumnChart("distWiseTotOveColChartId",categoriesArr,stackedColumnChart);

	}
	function buildingHighchart(colDivId,neg,tot,pos,categories)
	{
		
		Highcharts.setOptions({
				colors: ['#343B54', '#63C563','#EB2F2F']
			});	
		Highcharts.chart(colDivId, {
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			xAxis: {
				categories: categories
			},
			credits: {
			  enabled: false
		  },
			yAxis: {
				min: 0,
				title: {
					text: ''
				},
				visible: false,
				stackLabels: {
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			
			tooltip: {
				headerFormat: '<b>{point.x}</b><br/>',
				pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
			},
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333'
					}
				}
			},
			series: [{
				name: 'Total',
				data: [tot,'','']
			}, {
				name: 'Positive',
				data: ['',pos,'']
			}, {
				name: 'Negative',
				data: ['','',neg]
			}]
		});
	}
	function buildingPieGraph(divId,posper,negper)
	{
		Highcharts.setOptions({
			colors: ['#69C769', '#EB2F2F']
		});
		Highcharts.chart(divId, {
			chart: {
				type: 'pie'
			},
			credits: {
			  enabled: false
		  },
			title: {
				text: ''
			},

			plotOptions: {
				pie: {
					innerSize: '50',
					
				},
				series: {
					dataLabels: {
						enabled: false
					}
				},
			},


			series: [{
				data: [
					[' ',   posper],
					[' ',       negper],
				  
				]
			}]
		});	
	}
	function buildingStackedColumnChart(mainDivId,cateGories,data)
	{
		Highcharts.setOptions({
			colors: ['#69C769', '#EB2F2F']
		});
		Highcharts.chart(mainDivId, {
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			credits: {
            enabled: true,
			},
			xAxis: {
				categories: cateGories
			},
			yAxis: {
				min: 0,
				title: {
					text: ''
				},
				visible: false,
				stackLabels: {
					enabled: true,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				align: 'center',
				x: 0,
				verticalAlign: 'bottom',
				y: 0,
				
				backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				borderColor: '#CCC',
				borderWidth: 1,
				shadow: false
			},
			tooltip: {
				headerFormat: '<b>{point.x}</b><br/>',
				pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
			},
			plotOptions: {

    },
			plotOptions: {
				column: {
					stacking: 'normal',
					dataLabels: {
						enabled: true,
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || '#333'
					}
				},
				series: {
					pointWidth: 30
				},
			},
			series:data 
		});
	}
	function getPrintMediaCountsDetailsInfo(currentFromDate,currentToDate)
	{
		$("#printMediaColumnChartId").html(spinner);
		$("#printMediaBlockId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+currentFromDate+"/"+currentToDate+"/"
			//url: "http://192.168.11.107:9652/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+currentFromDate+"/"+currentToDate+"/"
		}).then(function(result){
			$("#printMediaBlockId").html('');
			buildPrintMediaCountsDetailsInfoData(result,"printMediaColumnChartId","printMediaBlockId","print");
		});
	}
	function getElectronicMediaCountsDetailsInfo(currentFromDate,currentToDate)
	{
		$("#electronicMediaColumnChartId").html(spinner);
		$("#electronicMediaBlockId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/ge/getDepartMentWiseAllNewsBulletinsAndPrograms/"+currentFromDate+"/"+currentToDate+"/All"
			//url: "http://192.168.11.107:9652/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+currentFromDate+"/"+currentToDate+"/All"
		}).then(function(result){
			$("#electronicMediaBlockId").html('');
			buildElectronicMediaCountsDetailsInfoData(result,"electronicMediaColumnChartId","electronicMediaBlockId","electronic");
		});
	}
	function getDistrictWiseTotalOverViewInfo(currentFromDate,currentToDate)
	{ 
		$("#distWiseTotOveColChartId").html(spinner);
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+currentFromDate+"/"+currentToDate+"/"
			//url: "http://192.168.11.107:9652/CommunityNewsPortal/webservice/getDistrictWiseTotalOverViewInfo/"+currentFromDate+"/"+currentToDate+"/"
		}).then(function(result){
			buildDistrictWiseTotalOverViewInfoData(result);
		});
	}