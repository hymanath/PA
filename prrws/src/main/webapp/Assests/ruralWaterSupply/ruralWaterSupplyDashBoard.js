 //Angular Start
	var myApp = angular.module('ruralWaterSupply',[]);
	
	myApp.controller('UserController', function($scope, $http) {
		onloadCalls();

		function onloadCalls(){
			tabBlocks('stateBlockId','state');
			tabBlocks('districtBlockId','district');
			tabBlocks('constituencyBlockId','constituency');
			tabBlocks('mandalBlockId','mandal');
			responsiveTabs();
			getHabitationCoverageByStatusByLocationType('state','');
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails();
			getAssetInfoBetweenDates();
			getWaterSourceInfo();
			/* getAlertDetailsOfCategoryByStatusWise();
			getAlertFeedbackStatusDetails(); */
		}
		function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title)
		{
			
			'use strict';
			$('#'+id).highcharts({
				 colors: colors,
				chart: type,
				title: title,
				subtitle: {
					text: null
				},
				xAxis: xAxis,
				yAxis: yAxis,
				tooltip: tooltip,
				plotOptions: plotOptions,
				legend: legend,
				series: data
			});
		}
		
		
		function getHabitationCoverageByStatusByLocationType(locationType,divId)
		{
			var json = {
				locationType:locationType,
				year:"2017"
			}
			$http({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data != null && response.data.length > 0){
					buildChartForHabitationCoverage(response.data);
					buildChartForHabitationCoverageStatus(response.data);
					buildTableForHabitationCoverage(response.data,locationType,divId);
				}
			});
		}
		
		function buildChartForHabitationCoverage(response){
			
			var dataArr = [];
			var pcCount = 0;
			var totalCount=0;
			for(var i in response){
				for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						if(response[i].statusList[j].status == "PC1" || response[i].statusList[j].status == "PC2" || response[i].statusList[j].status == "PC3" || response[i].statusList[j].status == "PC4"){
							pcCount = pcCount+parseInt(response[i].statusList[j].count);
							totalCount =totalCount+parseInt(response[i].statusList[j].count);
							if(response[i].statusList[j].status == "PC4"){
								var pcArr = [];
								pcArr.push("PC");
								pcArr.push(parseInt(pcCount));
								dataArr.push(pcArr);
							}
						}else{
							var tempArr = [];
							response[i].statusList[j].status == "NSS" ? tempArr.push("QA"):tempArr.push(response[i].statusList[j].status);
							tempArr.push(parseInt(response[i].statusList[j].count));
							dataArr.push(tempArr);
							totalCount =totalCount+parseInt(response[i].statusList[j].count);
						}
					}
				}
			}
			$("#totalCntTtlValues").html(totalCount)
			var colors = ['#494949','#FC5049','#14BAAD']
			var id = 'totalValues';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = {
				text: 'Habitation'
			};
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
				
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		
		function buildChartForHabitationCoverageStatus(response){
			var dataArr = [];
			for(var i in response){
			  for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						var tempArr = [];
						tempArr.push(response[i].statusList[j].status);
						tempArr.push(parseInt(response[i].statusList[j].count));
						dataArr.push(tempArr);
					}
				}
			}
			var colors = ['#14BAAD']
			var id = 'habitation';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var title = {
				text: 'Habitation Coverage Status'
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
				
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
		function getLabTestDetails()
		{
			var json = {
				year:"2017"
			}
			$http({
				url: 'getLabTestDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 
			}).then(function(response) {
				if(response.data != null){
					buildChartForLabTestDetails(response.data);
				}
			});
		}
		
		function buildChartForLabTestDetails(response){
			var dataArr = [];
			var arr1 = [],arr2 = [];
			arr1.push("physicalTestCount");
			arr1.push(parseInt(response.physicalTestCount));
			dataArr.push(arr1);
			arr2.push("bacterialTestCount");
			arr2.push(parseInt(response.bacterialTestCount));
			dataArr.push(arr2);
			var colors = ['#14BAAD','#FC5049']
			var id = 'overView';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var title = {
				text: ''
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: []
			};
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#FFFFFF',
					align: 'right',
					format: '{point.y}',
				}
			}];
			
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		
		function getHabitationSupplyDetails(){
			var json = {
				year:"2017"
			}
			$http({
				url: 'getHabitationSupplyDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				if(response.data !=null){
					buildHabitationSupplyDetails(response.data);
				}
			});
		}
		
		function buildHabitationSupplyDetails(result){
			var dataArr = [];
				var subDataArr1=[],subDataArr2=[];
				
				subDataArr1.push("SAFE");
				subDataArr1.push(result.safeMLD);
				dataArr.push(subDataArr1);
				
				subDataArr2.push("UN-SAFE");
				subDataArr2.push(result.unsafeMLD);
				dataArr.push(subDataArr2);
				
				var colors = ['#14BAAD','#FC5049']
				var id = 'levelOfSupply1';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = {
					text: 'Habitation Coverage Status'
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data:dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				
				
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		
		function getSchemesDetails(){
			var json = {
				fromDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getSchemesDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data !=null && response.data.length>0){
					buildSchemesDetails(response.data);
				}
			});
		}
		function buildSchemesDetails(result){
			var dataArr = [];

				for(var i in result)
				  {
					var tempArr = [];
					
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						if(result[i].assetType == "PWS"){
							result[i].assetType = "SINGAL VILLAGE";
						}else{
							result[i].assetType = "MULTI VILLAGE";
						}
						tempArr.push(result[i].assetType);
						tempArr.push(parseInt(result[i].count));
						dataArr.push(tempArr);
						
					}
					
				  }
				  
				var id = 'schemes';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = {
					text: 'Schemes'
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				var colors = ['#14BAAD','#FC5049']
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		function getSchemeWiseWorkDetails(){
			var json = {
					formDateStr:"01-01-2016",
					toDateStr:"01-04-2017"
			}
			$http({
				url: 'getSchemeWiseWorkDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				if(response.data !=null && response.data.length>0){
					buildSchemeWiseWorkDetails(response.data);
				}
			});
		}
		function buildSchemeWiseWorkDetails(result){
			var dataArr = [];
			var assetTypeArr = [];
			var workOngoingArr = [];
			var workNotGroundedArr = [];
			var workCompletedArr = [];
			var workComissionedArr = [];
				for(var i in result)
				  {					 
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						assetTypeArr.push(result[i].assetType);						
						workOngoingArr.push({"y":result[i].workOngoingCount,color:'#14BBAE'});
						workNotGroundedArr.push({"y":result[i].workNotGroundedCount,color:'#FC5E57'});
						workCompletedArr.push({"y":result[i].workCompletedCount,color:'#FFBF14'});
						workComissionedArr.push({"y":result[i].workComissionedCount,color:'#465556'});						
					}
					
				  }
				  
				var id = 'habitationWorks';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					//enabled: true
				};
				var title = {
					text: 'Works'
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: assetTypeArr
				};
				var plotOptions ={ 
						column: {
						//colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};
				var colors = []
				var data =  [{
								name: 'Ongoing',
								data: workOngoingArr
							}, {
								name: 'Not Grounded',
								data: workNotGroundedArr
							}, {
								name: 'Completed',
								data: workCompletedArr,
							}, {
								name: 'Commissioned',
								data: workComissionedArr
							}]
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		function getAssetInfoBetweenDates(){ 
			var json = {
				formDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				
				var dataArr = [];
				for(var i in response.data)
				{
				  //console.log(response.data);
					var tempArr = [];
					tempArr.push(response.data[i].assetType);
					tempArr.push(parseInt(response.data[i].count));
					dataArr.push(tempArr);
				  
				}
				var colors = ['#14BBAE'];
				var id = 'assets';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = {
					text: 'Assets'
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: false
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			});
		}
		
		
		function getWaterSourceInfo(){ 
			var json = {
					  year : "2014",
					  fromDate : "2014-01-01",
					  toDate : "2014-06-30"
			}
			$http({
				url: 'getWaterSourceInfo',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				
				var dataArr = [];
				for(var i in response.data)
				{
				  //console.log(response.data);
					var tempArr = [];
					tempArr.push(response.data[i].name);
					tempArr.push(parseInt(response.data[i].count));
					dataArr.push(tempArr);
				  
				}
				var colors = ['#14BBAE'];
				var id = 'waterSources';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = {
					text: 'Assets'
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: false
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			});
		}
		
		function getKeyPerformanceIndicatorsInfo(){
			var json = {
					  year : "2014",
					  fromDate : "2014-01-01",
					  toDate : "2014-06-30",
					locationType : "state"	
					}

			$http({
				url: 'getKeyPerformanceIndicatorsInfo',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				console.log(response.data);
				
			});
		}
		
		
		
		/* function getAlertDetailsOfCategoryByStatusWise(){
			var json = {
				fromDate:dd-MM-yyyy,
				toDate:dd-MM-yyyy,
				deptId:Long,
				year:String
			}
			$http({
				url: 'getAlertDetailsOfCategoryByStatusWise',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				
			});
		}
		
		function getAlertFeedbackStatusDetails(){
			var json = {
				fromDate:dd-MM-yyyy,
				toDate:dd-MM-yyyy,
				deptId:Long,
				year:String
			}
			$http({
				url: 'getAlertFeedbackStatusDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				
			});
		} */
	
	
	function tabBlocks(blockId,blockName){
		var tabBlock = '';
		var blocksArr = [{name:'Coverage Status Of<br/> Habitation',id:'habitation'},{name:'Key<br/> Performance',id:'performance'},{name:'Alert Status <br/>Jalavani',id:'jalavani'},{name:'Plan Of Action for Stressed Habitations <br/>Water Budget has to be prepared for all habitations',id:'planAction'}];
		var tableId = '';
		tabBlock+='<div class="panel panel-black panel-default">';
			tabBlock+='<div class="panel-heading">';
				tabBlock+='<h4 class="panel-title text-capital">'+blockName+' level overview</h4>';
			tabBlock+='</div>';
			tabBlock+='<div>';
				tabBlock+='<div class="row">';
					tabBlock+='<div class="col-sm-12">';
						tabBlock+='<div>';
							tabBlock+='<select class="form-control" role="tabListMobile">';
								for(var i in blocksArr)
								{
									tabBlock+='<option tab_id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].name+'</option>';
								}
							tabBlock+='</select>';
							tabBlock+='<ul class="nav nav-tabs nav-tabs-custom" role="tablist">';
								for(var i in blocksArr)
								{
									if(i == 0)
									{
										tabBlock+='<li class="active" ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].name+'</a></li>';
									}else{
										tabBlock+='<li ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].name+'</a></li>';
									}
									
								}
							tabBlock+='</ul>';
						tabBlock+='</div>';
					tabBlock+='</div>';
				tabBlock+='</div>';
			tabBlock+='</div>';
			tabBlock+='<div class="panel-body">';
				tabBlock+='<div class="tab-content">';
					
					if(blockId == 'consBlockId')
					{
						tabBlock+='<div class="row">';
							tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constituencySelect'+blockId+'"></div></div>';
						tabBlock+='</div>';
					}else if(blockId == 'mandalBlockId')
					{
						tabBlock+='<div class="row">';
							tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constituencySelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="mandalSelect'+blockId+'"></div></div>';
						tabBlock+='</div>';
					}
					
					for(var i in blocksArr)
					{
						if(i == 0)
						{
							tabBlock+='<div role="tabpanel" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';	
						}else{
							tabBlock+='<div role="tabpanel" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
						}
					}
				tabBlock+='</div>';
			tabBlock+='</div>';
		tabBlock+='</div>';
		$("#"+blockId).html(tabBlock);
		for(var i in blocksArr)
		{
			if(blocksArr[i].id == "habitation"){
				getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
			}
			
			tableId = blockId+''+blocksArr[i].id;
			//tableView(tableId);
		}
		if(blockId == 'constituencyBlockId')
		{
			selectBox('districtSelect'+blockId+'')
			selectBox('constituencySelect'+blockId+'')
		}else if(blockId == 'mandalBlockId')
		{
			selectBox('districtSelect'+blockId+'')
			selectBox('constituencySelect'+blockId+'')
			selectBox('mandalSelect'+blockId+'')
		}
		
	}
	
		function buildTableForHabitationCoverage(result,locationType,divId){
			
			var theadArr=[];
			var tbodyArr=[];
			var totalCount=0;
			var percArr=[];
			if(result !=null && result.length>0){
				theadArr.push(locationType)
				if(locationType == "state"){
					tbodyArr.push("TOTAL")
				}else{
					tbodyArr.push("")
				}
				
				percArr.push("")
				for(var i in result){
					if(result[i].statusList !=null && result[i].statusList.length>0){
						for(var j in result[i].statusList){
							if(result[i].statusList[j].status != 'NC'){
								theadArr.push(result[i].statusList[j].status)
								tbodyArr.push(result[i].statusList[j].count)
								totalCount =totalCount+result[i].statusList[j].count;
								percArr.push(result[i].statusList[j].percentage)
							}
						}
						
					}
				}
				theadArr.push('TOTAL')
				tbodyArr.push(totalCount)
				percArr.push("")
			}
			tableView(divId,theadArr,tbodyArr,locationType,percArr)
		}
		function tableView(divId,theadArr,tbodyArr,locationType,percArr)
		{
			var tableView='';
			
			var $windowWidth = $(window).width();
			
			tableView+='<table class="table table-bordered dataTable'+locationType+'">';
				tableView+='<thead class="text-capital">';
				
					for(var i in theadArr)
					{
						tableView+='<th>'+theadArr[i]+'</th>';
					}
				tableView+='</thead>';
				tableView+='<tbody>';
				tableView+='<tr>';
					 for(var i in tbodyArr)
					{
						
						tableView+='<td>'+tbodyArr[i]+' <small>'+percArr[i]+'</small></td>';
						
					}  
					tableView+='</tr>'; 
				tableView+='</tbody>';
			tableView+='</table>';
			 for(var i in divId){
				if(divId[i].id == "habitation"){
					$("#"+locationType+"BlockId"+divId[i].id).html(tableView)
						if($windowWidth < 768){
							$(".dataTable"+locationType).wrap("<div class='table-responsive'></div>");
						}					
				}
			} 
			
			
		}
		function selectBox(id)
		{
			var id = id;
			var selectBox='';
			selectBox+='<select class="chosen" id="chosen'+id+'"><option>ss</option></select>';
			$("#"+id).html(selectBox);
			$("#chosen"+id).chosen();
		}
	});
	
	$(".chosenSelect").chosen({width:'100%'});
	$(window,document).on('resize', function(){
		responsiveTabs();
	});
	$(document).on('click','[attr_click="questionMark"]', function(){
		$("#modalDivId").modal('show');
		$("#modalHeadingId").html($(this).attr("attr_title"));
		tableView('modalTable');
	});
	function responsiveTabs(){
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
	}
	$(document).on("change","[role='tabListMobile']",function(){
			var id = $('option:selected', this).attr('tab_id');
			$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
			$("#"+id).addClass("active");
			
	});