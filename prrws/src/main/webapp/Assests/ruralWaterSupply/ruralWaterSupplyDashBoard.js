 //Angular Start
		var globalStatusObj={"QA":"#494949","PC":"#FC5049","FC":"#14BAAD","ground":"#14BAAD","surface":"#FC5049","SAFE":"#14BAAD","UN-SAFE":"#FC5049",
		"SINGAL VILLAGE":"#14BAAD","MULTI VILLAGE":"#FC5049","physicalTestCount":"#14BAAD","bacterialTestCount":"#FC5049","Completely Satisfied":"#0FBE08","Not Satisfied":"#FF0909","Partially Satisfied":"#FFBA00"}
		var blocksArr = [{name:'Coverage Status Of<br/> Habitation',id:'habitation'},{name:'Key<br/> Performance',id:'performance'},{name:'Alert Status <br/>Jalavani',id:'jalavani'},{name:'Plan Of Action for Stressed Habitations <br/>Water Budget has to be prepared for all habitations',id:'planAction'}];
		onloadCalls();
		onLoadInitialisations();
		function onloadCalls(){
			tabBlocks('stateBlockId','state');
			tabBlocks('districtBlockId','district');
			tabBlocks('constituencyBlockId','constituency');
			tabBlocks('mandalBlockId','mandal');
			responsiveTabs();
			getHabitationCoverageByStatusByLocationTypeForGraph('state','');
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails();
			getAssetInfoBetweenDates();
			getWaterSourceInfo();
			getKeyPerformanceIndicatorsInfo();
			getPlanofActionForStressedHabitations();
			getStressedHabitationsInfoByLocationType();
			getAlertDetailsOfCategoryByStatusWise();
			getAlertFeedbackStatusDetails();
			getLocationBasedOnSelection();//get dist/const/mandal
			getHamletWiseIvrStatusCounts();
		
		}
		function onLoadInitialisations()
	{
		$(document).keydown(function(event){
			if(event.keyCode==123){
				alert("Hoo no! don't try to expose me");
				return false;
			}
			else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
				alert("Hoo no! don't try to expose me");
				return false;  //Prevent from ctrl+shift+i
			}
		});
		var width = $(window).width()
		if(width > 767)
		{
			header = $('header section'),
			$(window).scroll(function(){
				var windowScrollTop = $(window).scrollTop();

				if (windowScrollTop>50) {
					header.addClass("header-fixed");
				} else {
					header.removeClass("header-fixed");
				}
			});
		}
		$(".chosenSelect").chosen();
		
		
		getAllFiniancialYears();		
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
		
		
		function getHabitationCoverageByStatusByLocationTypeForGraph(locationType,divId){
			var json = {
				locationType:locationType,
				year:2017,
				stressedHabitationYear:2017,
				filterType:"",
				filterValue:""
			}
			$.ajax({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildChartForHabitationCoverage(ajaxresp);
						buildChartForHabitationCoverageStatus(ajaxresp);
					}
				}
			});
		}
		function getHabitationCoverageByStatusByLocationType(locationType,divId)
		{
			var json = {
				locationType:locationType,
				year:"2017",
				stressedHabitationYear:2017,
				filterType:"",
				filterValue:""
			}
			$.ajax({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildTableForHabitationCoverage(ajaxresp,locationType,divId);
					}
				}
			});
			
		}
		function buildChartForHabitationCoverage(response){
			
			var dataArr = [];
			var pcCount = 0;
			var totalCount=0;
			var statusNamesArr=[];
			for(var i in response){
				for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						if(response[i].statusList[j].status == "PC1" || response[i].statusList[j].status == "PC2" || response[i].statusList[j].status == "PC3" || response[i].statusList[j].status == "PC4"){
							pcCount = pcCount+parseInt(response[i].statusList[j].count);
							totalCount =totalCount+parseInt(response[i].statusList[j].count);
							if(response[i].statusList[j].status == "PC4"){
								var pcArr = [];
								statusNamesArr.push("PC");
								pcArr.push(parseInt(pcCount));
								dataArr.push(pcArr);
							}
						}else{
							var tempArr = [];
							response[i].statusList[j].status == "NSS" ? statusNamesArr.push("QA"):statusNamesArr.push(response[i].statusList[j].status);
							tempArr.push(parseInt(response[i].statusList[j].count));
							dataArr.push(tempArr);
							totalCount =totalCount+parseInt(response[i].statusList[j].count);
						}
					}
				}
			}
			$("#totalCntTtlValues").html("TOTAL:"+totalCount)
			var colors = ['#494949','#FC5049','#14BAAD']
			var id = 'totalValues';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
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
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						
					},
					
				}
				
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
					color: '#000',
					align: 'center',
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
				text: '',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } 
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
					color: '#000',
					align: 'canter',
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
			$.ajax({
				url: 'getLabTestDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildChartForLabTestDetails(ajaxresp);
					}
				}
			});
		}
		
		function buildChartForLabTestDetails(response){
			var dataArr = [];
			var arr1 = [],arr2 = [];
			var statusNamesArr=[];
			statusNamesArr.push("physicalTestCount");
			arr1.push(parseInt(response.physicalTestCount));
			dataArr.push(arr1);
			statusNamesArr.push("bacterialTestCount");
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
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
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
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						if(this.value.toString().length >= 6){
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 6)+'..'+'</p>';
						}else{
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						}
						
						
					},
					
				}
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
					color: '#000',
					align: 'center',
					format: '{point.y}',
				}
			}];
			
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		
		function getHabitationSupplyDetails(){
			var json = {
				year:"2017"
			}
			$.ajax({
				url: 'getHabitationSupplyDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildHabitationSupplyDetails(ajaxresp);
					}
				}
			});
		}
		
		function buildHabitationSupplyDetails(result){
			var dataArr = [];
			var subDataArr1=[],subDataArr2=[];
			var statusNamesArr=[];
			var totalCount=0;	
				statusNamesArr.push("SAFE");
				subDataArr1.push(result.safeMLD);
				dataArr.push(subDataArr1);
				
				statusNamesArr.push("UN-SAFE");
				subDataArr2.push(result.unsafeMLD);
				dataArr.push(subDataArr2);
				totalCount =totalCount+result.safeMLD+result.unsafeMLD;
				
				$("#levelSupplyTtlValues").html("TOTAL:"+totalCount)
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
					 text: '',
					align:'left',
					 style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  } 
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
					categories: statusNamesArr,
					labels: {
						useHTML:true,
						formatter: function() {
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
							
						},
						
					}
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
						color: '#000',
						align: 'center',
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
			$.ajax({
				url: 'getSchemesDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildSchemesDetails(ajaxresp);
					}
				}
			});
			
		}
		function buildSchemesDetails(result){
			var dataArr = [];
			var statusNamesArr=[];
			var totalCount=0;
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
						statusNamesArr.push(result[i].assetType);
						tempArr.push(parseInt(result[i].count));
						dataArr.push(tempArr);
						totalCount=totalCount+parseInt(result[i].count);
						
					}
					
				  }
				$("#schemesTtlValues").html("TOTAL:"+totalCount)
				var colors = ['#14BAAD','#FC5049']
				var id = 'schemes';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = { 
					text: '',
					align:'left',
					 style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  } 
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
					categories: statusNamesArr,
					labels: {
						useHTML:true,
						formatter: function() {
							
							if(this.value.toString().length >= 6){
								return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 6)+'..'+'</p>';
							}else{
								return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
							}
							
							
						},
						
					}
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
						color: '#000',
						align: 'center',
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
			$.ajax({
				url: 'getSchemeWiseWorkDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildSchemeWiseWorkDetails(ajaxresp);
					}
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
				$("#habitationWorks").highcharts({
					chart: {
						type: 'column'
					},
					title: {
						text: '',
						align:'left',
						style: {
							color: '#000',
							font: 'bold 16px "Lato", sans-serif'
						}
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: assetTypeArr
					},
					yAxis:{
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
							title: {
								text: null
							}
					}, 
					
					legend: {
						symbolHeight: 12,
						symbolWidth: 12,
						symbolRadius: 6,
						enabled: true
					},
					tooltip: {
						pointFormat: '{point.y}'
					},
					plotOptions: {
						column: {
							//colorByPoint: true
						}
					},
					series: [{
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
				});
				
		}
		function getAssetInfoBetweenDates(){ 
			var json = {
				formDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$.ajax({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						var dataArr = [];
							for(var i in ajaxresp)
							{
								var tempArr = [];
								tempArr.push(ajaxresp[i].assetType);
								tempArr.push(parseInt(ajaxresp[i].count));
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
								text: ''
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
									color: '#000',
									align: 'center',
									format: '{point.y}',
								}
							}];
							highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
					}
				}
			});
		}
		
		
		function getWaterSourceInfo(){ 
			var json = {
			  year : "2014",
			  fromDate : "2014-01-01",
			  toDate : "2014-06-30"
			}
			$.ajax({
				url: 'getWaterSourceInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
							var dataArr = [];
							var statusNamesArr=[];
							var totalCount=0;
							if(ajaxresp !=null && ajaxresp.length>0){
								for(var i in ajaxresp)
								{
								  var tempArr = [];
									statusNamesArr.push(ajaxresp[i].name);
									tempArr.push(parseInt(ajaxresp[i].count));
									dataArr.push(tempArr);
									totalCount =totalCount+parseInt(ajaxresp[i].count);
								  
								}
							}
							$("#waterSourcesTtlValues").html("TOTAL:"+totalCount)
							var colors = ['#14BAAD','#FC5049']
							var id = 'waterSources';
							var type = {
								type: 'column',
								backgroundColor:'transparent'
							};
							var legend = {
								enabled: false
							};
							var title = { 
								text: '',
								align:'left',
								 style: {
									 color: '#000',
									 font: 'bold 16px "Lato", sans-serif'
								  } 
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
								categories: statusNamesArr,
								labels: {
									useHTML:true,
									formatter: function() {
										return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
										
									},
									
								}
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
									color: '#000',
									align: 'center',
									format: '{point.y}',
								}
							}];
							highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
					}
				}
			});	
		}
		
		function getKeyPerformanceIndicatorsInfo(){
			var json = {
					  year : "2014",
					  fromDate : "2014-01-01",
					  toDate : "2014-06-30",
					locationType : "state"	
					}
			$.ajax({
				url: 'getKeyPerformanceIndicatorsInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildKeyPerformanceIndicatorsInfo(ajaxresp)
					}
				}
			});
			
		}
		function getKeyPerformanceIndicatorsInfoTable(locationType,divId){
			var json = {
					  year : "2014",
					  fromDate : "2014-01-01",
					  toDate : "2014-06-30",
					  locationType : locationType	
					}
			$.ajax({
				url: 'getKeyPerformanceIndicatorsInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildTableForHabitationCoverage(ajaxresp,locationType,divId);
					}
				}
			});
			
		}
		function buildKeyPerformanceIndicatorsInfo(result){
				var keyNamesArr =[];
				var targetArr=[];
				var achivedArr=[];
				if(result !=null && result.length>0){
					for(var i in result){
						if(result[i].statusList !=null && result[i].statusList.length>0){
							for(var j in result[i].statusList){
								keyNamesArr.push(result[i].statusList[j].status);
								targetArr.push(result[i].statusList[j].target)
								achivedArr.push(result[i].statusList[j].achived)
							}
						}
					}
				}
				$("#keyPerformance").highcharts({
					chart: {
						type: 'column'
					},
					title: {
						useHTML:true,
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: keyNamesArr
					},
					yAxis: [{
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						}
					}, {
						title: {
							text: ''
						},
						opposite: true
					}],
					legend: {
						symbolHeight: 12,
						symbolWidth: 12,
						symbolRadius: 6,
						shadow: false
					},
					tooltip: {
						shared: true
					},
					plotOptions: {
						column: {
							grouping: false,
							shadow: false,
							borderWidth: 0
						}
					},
					series: [{
						name: 'Target',
						color: '#FC5049',
						data: targetArr,
						tooltip: {
							valuePrefix: ' ',
							valueSuffix: ' '
						},
						pointPadding: 0.2,
						pointPlacement: 0.1,
						yAxis: 1
					}, {
						name: 'Achived',
						color: '#14BAAD',
						data: achivedArr,
						tooltip: {
							valuePrefix: ' ',
							valueSuffix: ' '
						},
						pointPadding: 0.3,
						pointPlacement: 0.1,
						yAxis: 1
					}]
				});
		}
		function getStressedHabitationsInfoByLocationType(){
			var json = {
					  year : "2014",
					  fromDate : "2014-01-01",
					  toDate : "2014-06-30",
					  locationType : "district",
					  locationId : "1"

					}
			$.ajax({
				url: 'getStressedHabitationsInfoByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						
					}
				}
			});
			
		}
		
		function getPlanofActionForStressedHabitations(){
			var json = {
					year : "2014",
					fromDate : "2014-01-01",
					toDate : "2014-06-30"	
				}
			$.ajax({
				url: 'getPlanofActionForStressedHabitations',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildPlanofActionForStressedHabitations(ajaxresp);
					}
				}
			});
			
		}
		
		function buildPlanofActionForStressedHabitations(result){
			var targetArr=[];
			var achivedArr=[];
			if(result !=null){
				targetArr.push({"y":result.target,color:"#14BAAD"})
				achivedArr.push({"y":result.achived,color:"#FC5049"})
			}
			$("#planOfAction").highcharts({
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align:'left',
					style: {
						color: '#000',
						font: 'bold 16px "Lato", sans-serif'
					}
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['Habitations']
				},
				yAxis:{
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled: false
					},
					title: {
						text: null
					}
				}, 
				
				legend: {
					symbolHeight: 12,
					symbolWidth: 12,
					symbolRadius: 6,
					enabled: true
				},
				tooltip: {
					 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b>'
				},
				plotOptions: {
					column: {
						//colorByPoint: true
					}
				},
				series: [{
						name: 'Target',
						data: targetArr
					}, {
						name: 'Achived',
						data: achivedArr
					}]
			});
		}
		function getAlertDetailsOfCategoryByStatusWise(){
			var json = {
				fromDate:"",
				toDate:"",
				deptId:49,
				year:"2017"
			}
			$.ajax({
				url: 'getAlertDetailsOfCategoryByStatusWise',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildAlertDetailsOfCategoryByStatusWise(ajaxresp);
					}
				}
			});
		}
		
		function buildAlertDetailsOfCategoryByStatusWise(result){
			
			if(result !=null && result.length>0){
				var statusCountArr = [];
				var CompleteClosedCount=0;
				var othersCount=0;	
				for(var i in result){
					
					 if(result[i].id==2){
						statusCountArr.push(result[i].count);
					}else if(result[i].id==13){
						statusCountArr.push(result[i].count);
					}else if(result[i].id==3){
						 statusCountArr.push(result[i].count);
					}else if(result[i].id==4 ||  result[i].id == 12){
						 CompleteClosedCount =CompleteClosedCount+result[i].count;
					}else{
						 othersCount =othersCount+result[i].count;
					} 
				}
				statusCountArr.push(CompleteClosedCount);
				statusCountArr.push(othersCount);
				var colors = ['#FC5049']
				var id = 'alertStatus';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				
				var title = {
					text: '',
					align:'left',
					 style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  } 
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
					categories: ['Notified','Action In Progess','Proposal','Completed & Closed','Others']
					
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: statusCountArr,

					dataLabels: {
						enabled: true,
						color: '#000',
						align: 'center',
						format: '{point.y}',
					}
				}];
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			}
		}
		function getAlertFeedbackStatusDetails(){
			var json = {
				fromDate:"",
				toDate:"",
				deptId:49,
				year:"2017"
			}
			$.ajax({
				url: 'getAlertFeedbackStatusDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildAlertFeedbackStatusDetails(ajaxresp);
					}
				}
			});
		} 
	
		function buildAlertFeedbackStatusDetails(result){
			
			var statusNamesArr=[];
			var statusCountArr=[];
			if(result !=null && result.length>0){
				for(var i in result){
					statusNamesArr.push(result[i].name)
					statusCountArr.push(result[i].count)
				}
			}
			var colors = ['#0FBE08','#FF0909','#FFBA00']
			var id = 'drinkingWater';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
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
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						if(this.value.toString().length >= 3){
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 3)+'..'+'</p>';
						}else{
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						}
					},
					
				}
				
			};
			
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				pointFormat: '{point.y}'
			};

			var data = [{
				name: '',
				data: statusCountArr,

				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					format: '{point.y}',
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
	
	function tabBlocks(blockId,blockName){
		var tabBlock = '';
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
										tabBlock+='<li class="active" ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'>'+blocksArr[i].name+'</a></li>';
									}else{
										tabBlock+='<li ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'>'+blocksArr[i].name+'</a></li>';
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
			if(blocksArr[i].id == "habitation")
			getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
			
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
			var GLtbodyArr=[];
			if(result !=null && result.length>0){
				for(var i in result){
					GLtbodyArr.push(result[i]);
				}
			}
			tableView(divId,GLtbodyArr,locationType)
		}
		
		function tableView(divId,GLtbodyArr,locationType)
		{
			
			var $windowWidth = $(window).width();
			 for(var k in divId){
				 var tableView='';
					tableView+='<table class="table table-bordered dataTable'+locationType+'">';
						tableView+='<thead class="text-capital">';
						tableView+='<tr>';
						if(divId[k].id=="habitation"){
							tableView+='<th>'+locationType+'</th>';
						}else if(divId[k].id=="performance"){
							tableView+='<th rowspan="2">'+locationType+'</th>';
						}
							if(GLtbodyArr[0].statusList !=null && GLtbodyArr[0].statusList.length>0){
								for(var j in GLtbodyArr[0].statusList){
									if(divId[k].id=="habitation"){
										if(GLtbodyArr[0].statusList[j].status != 'NC'){
											tableView+='<th>'+GLtbodyArr[0].statusList[j].status+'</th>';
											
										}
									}else if(divId[k].id=="performance"){
											tableView+='<th colspan="3">'+GLtbodyArr[0].statusList[j].status+'</th>';
									}
								}
							}
					if(divId[k].id=="habitation"){
						tableView+='<th>TOTAL</th>';
					}
					tableView+='</tr>'; 
					tableView+='<tr>'; 
					if(divId[k].id=="performance"){
						if(GLtbodyArr[0].statusList !=null && GLtbodyArr[0].statusList.length>0){
							for(var j in GLtbodyArr[0].statusList){
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
							}
						}
						
					}					
					tableView+='</tr>'; 
					tableView+='</thead>';
					tableView+='<tbody>';
						 for(var i in GLtbodyArr){
							 var totalCount=0;
							tableView+='<tr>';
							tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
							if(GLtbodyArr[i].statusList !=null && GLtbodyArr[i].statusList.length>0){
								for(var j in GLtbodyArr[i].statusList){
									if(divId[k].id=="habitation"){
										if(GLtbodyArr[i].statusList[j].status != 'NC'){
											tableView+='<td>'+GLtbodyArr[i].statusList[j].count+' <small>'+GLtbodyArr[i].statusList[j].percentage+'</small></td>';
											totalCount += GLtbodyArr[i].statusList[j].count;
										}
									}else if(divId[k].id=="performance"){
										tableView+='<td>'+GLtbodyArr[i].statusList[j].target+'</td>';
										tableView+='<td>'+GLtbodyArr[i].statusList[j].achived+'</td>';
										tableView+='<td>'+GLtbodyArr[i].statusList[j].percentage+'</td>';
									}
									
									
								}
							}
							if(divId[k].id=="habitation"){
								tableView+='<td>'+totalCount+'</td>';
							}
							tableView+='</tr>';
						}  
						 
					tableView+='</tbody>';
					tableView+='</table>';	
				$("#"+locationType+"BlockId"+divId[k].id).html(tableView)
				
			 }
		}
		
		$(document).on("click","[role='tablist'] li a",function(){
				var id = $(this).attr('tab_id');
				var blockName = $(this).attr('attr_block_name');
				var blockId = $(this).attr('attr_block_id');
				if(blockName == "state"){
					if(id == "stateBlockIdhabitation"){
						getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
					}else if(id="stateBlockIdperformance"){
						getKeyPerformanceIndicatorsInfoTable(blockName,blocksArr);
					}
				}else if(blockName == "district"){
					if(id == "districtBlockIdhabitation"){
						getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
					}else if(id="districtBlockIdperformance"){
						getKeyPerformanceIndicatorsInfoTable(blockName,blocksArr);
					}
				}else if(blockName == "constituency"){
					if(id == "constituencyBlockIdhabitation"){
						getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
					}else if(id="constituencyBlockIdperformance"){
						getKeyPerformanceIndicatorsInfoTable(blockName,blocksArr);
					}
				}else if(blockName == "mandal"){
					if(id == "mandalBlockIdhabitation"){
						getHabitationCoverageByStatusByLocationType(blockName,blocksArr);
					}else if(id="mandalBlockIdperformance"){
						getKeyPerformanceIndicatorsInfoTable(blockName,blocksArr);
					}
				}
		});
	
	
	
	
	
	
	function selectBox(id)
		{
			var id = id;
			var selectBox='';
			selectBox+='<select class="chosen" id="chosen'+id+'"><option>ss</option></select>';
			$("#"+id).html(selectBox);
			$("#chosen"+id).chosen();
		}
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
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	
	function getLocationBasedOnSelection(){
		var json = {
			locationType:"district",
			year:2017
		}
		
		$.ajax({
			url: 'getLocationBasedOnSelection',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp){
				
			}
		});
	}
	
	function getHamletWiseIvrStatusCounts(){
		var json = {
			fromDate:"",
			toDate:"",
			locationValues:[1],
			locationTypeId:2,
			year:2017
		}
		
		$.ajax({
			url: 'getHamletWiseIvrStatusCounts',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp){
				console.log(ajaxresp.data);
			}
		});
	}
	
	var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
  var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
  $("#dateRangePickerAUM").daterangepicker({
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
    var dates= $("#dateRangePickerAUM").val();
  var pickerDates = glStartDate+' - '+glEndDate
  if(dates == pickerDates)
  {
    $("#dateRangePickerAUM").val('All');
  }
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
    
    $(".switch-btn li").removeClass("active");
    $(".switch-btn li:first-child").addClass("active");
    $('[role="tablist"] li:first-child a').trigger('click');
    $('#tabCons a[href="#consLevelGraph"]').trigger('click');
    glStartDate = picker.startDate.format('DD/MM/YYYY')
    glEndDate = picker.endDate.format('DD/MM/YYYY')
    if(picker.chosenLabel == 'All')
    {
      $("#dateRangePickerAUM").val('All');
    }
    
    
  });
  
  function getAllFiniancialYears()
	{
		alert("ghhnm");
		$("#financialYearId").html('');
		$(".compMultiFinancialYear").html('');
		$(".compSingleFinancialYear").html('');
		var json = {
		}
		$.ajax({                
			type:'POST',    
			url: 'getAllFiniancialYears',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			//$("#financialYearId").find('option').remove();
			$("#financialYearId").append("<option value='0'>All</option>");
			if(result != null && result.length >0){
				for(var i in result){
					$("#financialYearId").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
					$(".compMultiFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
					$(".compSingleFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
				}
				$("#financialYearId").val(0);
				//$(".compMultiFinancialYear").val(['1','2']);
				$(".compMultiFinancialYear").val(1);
				$(".compSingleFinancialYear").val(3);
			}
			$("#financialYearId").chosen();
			$("#financialYearId").trigger('chosen:updated');
			$(".compMultiFinancialYear").trigger('chosen:updated');
			$(".compSingleFinancialYear").trigger('chosen:updated');
			onLoadCalls();	
		});
   }
	
	
	